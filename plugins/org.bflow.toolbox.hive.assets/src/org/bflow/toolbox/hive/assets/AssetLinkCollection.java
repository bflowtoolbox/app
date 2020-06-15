package org.bflow.toolbox.hive.assets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.bflow.toolbox.hive.libs.aprogu.lang.Cast;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.ui.IEditorPart;

/**
 * Manages collections of {@link AssetLink}.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 23.04.2019
 *
 */
public class AssetLinkCollection {
	private static final String SplitToken = "::";
	private final ArrayList<ICollectionListener> _listenerSet = new ArrayList<>(5);
	private final ArrayList<AssetLink> _assetLinkSet = new ArrayList<>(20);
	private final ArrayList<AssetLink> _assetLinkPool = new ArrayList<>(50);
	
	private DiagramEditor _activeEditor;
	private IGraphicalEditPart _selectedEditPart;
	
	/** Initializes the new instance. */
	public AssetLinkCollection() {
		// Nothing to do here
	}
	
	/** Disposes the instance and all related resources. */
	public void dispose() {
		_listenerSet.clear();
		_assetLinkSet.clear();
		_assetLinkPool.clear();
		_activeEditor = null;
		_selectedEditPart = null;
	}
	
	/** Adds the given {@code listener} as collection listener. */
	public void addCollectionChangedListener(ICollectionListener listener) {
		if (listener == null) return;
		_listenerSet.add(listener);
	}
	
	/** Removes the given {@code listener} as collection listener. */
	public void removeCollectionChangedListener(ICollectionListener listener) {
		if (listener == null) return;
		_listenerSet.remove(listener);
	}
	
	/**
	 * Notifies the instance about the change of the active editor part. 
	 * The argument {@code editorPart} provides the newly actived editor part.
	 */
	public void onActiveEditorPartChanged(IEditorPart editorPart) {
		if (editorPart == _activeEditor) return;
		
		synchronized(_assetLinkPool) {
			_assetLinkPool.clear();
		}
		
		DiagramEditor diagramEditor = Cast.as(DiagramEditor.class, editorPart);		
		_activeEditor = diagramEditor;	
		
		if (_activeEditor != null) {			
			File indexFile = getIndexFile();
			List<String> fileRecords;
			
			try {
				@SuppressWarnings("unchecked")
				List<String> lines = (List<String>) FileUtils.readLines(indexFile);
				fileRecords = lines;
			} catch (Exception ex) {
				AssetsViewPlugin.getDefault().logError("Error on reading asset index file", ex);
				fileRecords = new ArrayList<>(0);
			}
			
			for (int i = -1; ++i != fileRecords.size();) {
				String record = fileRecords.get(i);
				if (record == null) continue;
				
				AssetLink assetLink = deserializeAssetLink(record);
				
				synchronized(_assetLinkPool) {
					_assetLinkPool.add(assetLink);
				}
			}
		}	
				
		onSelectedEditPartChanged(null);
	}
	
	/**
	 * Notifies the instance about the change of the selected edit part. 
	 * The argument {@code editPart} provides the newly selected edit part.
	 */
	public void onSelectedEditPartChanged(IGraphicalEditPart editPart) {
		if (editPart == _selectedEditPart) return;
			
		_selectedEditPart = editPart;		
		refresh();
	}
	
	/** Adds a new asset link for the specified {@code url}. */
	public void addLink(String url) {
		if (url == null) return;
		if (_selectedEditPart == null) return;
		
		add(EAssetLinkType.Url, url);
	}
	
	/** 
	 * Adds a new asset link for the specified {@code file}. 
	 * If {@code isSymlink} is FALSE, the file is hard-copied 
	 * into the asset folder and link to the copied element 
	 * is stored.
	 */
	public void addLink(File file, boolean isSymlink) {
		if (file == null) return;
		if (_selectedEditPart == null) return;
		
		EAssetLinkType linkType;
		String assetPath;
		if (isSymlink) {
			linkType = EAssetLinkType.Symlink;
			assetPath = file.getAbsolutePath();
		} else {
			linkType = EAssetLinkType.File;
			String fileName = file.getName();
			Path cpyAssetFilePath = Paths.get(getIndexFile().getParent(), fileName);
			File cpyAssetFile = cpyAssetFilePath.toFile();
			
			// Don't override existing files
			if (cpyAssetFile.exists()) return;
			
			try {
				FileUtils.copyFile(file, cpyAssetFile);
			} catch (IOException ex) {
				AssetsViewPlugin.getDefault().logError("Error on copying file into asset folder", ex);
				return;
			}
			
			assetPath = cpyAssetFile.getAbsolutePath();
		}
		
		add(linkType, assetPath);
	}
	
	/** Removes the given {@code assetLink} from the collection. */
	public void removeLink(AssetLink assetLink) {
		if (assetLink == null) return;
		if (_selectedEditPart == null) return;
		
		if (assetLink.LinkType() == EAssetLinkType.File) {
			File assetFile = new File(assetLink.AssetUrl());
			FileUtils.deleteQuietly(assetFile);
		}		
		
		remove(assetLink);
	}
	
	/**
	 * Refreshes the collection based on the current selected edit part and notifies
	 * all listener.
	 */
	public void refresh() {
		_assetLinkSet.clear();
		
		try {
			if (_selectedEditPart == null) return;
			String elementId = getElementId(_selectedEditPart);
			synchronized(_assetLinkPool) {
				for (int i = -1; ++i != _assetLinkPool.size();) {
					AssetLink assetLink = _assetLinkPool.get(i);
					if (assetLink.ElementId().compareTo(elementId) != 0) continue;
					_assetLinkSet.add(assetLink);
				}
			}
		} finally {
			raiseCollectionChangedEvent();
		}
	}
	
	/** Returns the elements of the collection as array. */
	public AssetLink[] toArray() {
		return _assetLinkSet.toArray(new AssetLink[_assetLinkSet.size()]);
	}
	
	/**
	 * Adds a new asset link with the given arguments to the collection. 
	 * Note, the index file is updated immediately.
	 */
	protected void add(EAssetLinkType linkType, String assetUrl) {
		if (_selectedEditPart == null) return;
		String elementId = getElementId(_selectedEditPart);
		
		synchronized(_assetLinkPool) {
			_assetLinkPool.add(new AssetLink(linkType, elementId, assetUrl));
		}
		
		refresh();			
		Executors.defaultThreadFactory().newThread(this::updateIndexFile).start();
	}
	
	/** Removes the given {@code assetLink} from the collection. */
	protected void remove(AssetLink assetLink) {
		if (_selectedEditPart == null) return;
		if (assetLink == null) return;
		
		synchronized(_assetLinkPool) {
			_assetLinkPool.remove(assetLink);
		}
		
		refresh();
		Executors.defaultThreadFactory().newThread(this::updateIndexFile).start();
	}
	
	/**
	 * Raises {@link ICollectionListener#onCollectionChanged()} for each registered
	 * listener.
	 */
	protected void raiseCollectionChangedEvent() {
		for (int i = -1; ++i != _listenerSet.size();) {
			ICollectionListener listener = _listenerSet.get(i);
			listener.onCollectionChanged();
		}
	}
	
	/**
	 * Returns the unique id of the model element that is wrapped by 
	 * the given {@code editPart}. If {@code editPart} is NULL, 
	 * NULL is returned.
	 */
	protected String getElementId(IGraphicalEditPart editPart) {
		if (editPart == null) return null;
		EObject eobj = editPart.resolveSemanticElement();
		return EMFCoreUtil.getProxyID(eobj);
	}
	
	/** Returns the index file for the assets of the currently selected editor. */
	protected File getIndexFile() {
		if (_activeEditor == null) return null;
		
		File assetsFolder = ResourcesPlugin.getWorkspace().getRoot()
										.getLocation().append(".assets")
										.toFile();
		assetsFolder.mkdirs();

		String diagramEditPartId = getElementId(_activeEditor.getDiagramEditPart());
		String diagrAssetsFolderPath = FilenameUtils.concat(assetsFolder.getAbsolutePath(), diagramEditPartId);
		File diagrAssetsFolder = new File(diagrAssetsFolderPath);
		diagrAssetsFolder.mkdirs();
		
		String diagrAssetsIndexPath = FilenameUtils.concat(diagrAssetsFolderPath, ".index");
		File indexFile = new File(diagrAssetsIndexPath);
		
		try {
			indexFile.createNewFile();
		} catch (IOException ex) {
			AssetsViewPlugin.getDefault().logError("Error on creating asset index file", ex);
			return null;
		}
		
		return indexFile;
	}
	
	/** Updates the index file for the assets of the currently selected editor. */
	protected void updateIndexFile() {
		synchronized(_assetLinkPool) {		
			File indexFile = getIndexFile();
			if (indexFile == null) return;
			
			AssetLink[] assetLinks = _assetLinkPool.toArray(new AssetLink[0]);
			ArrayList<String> records = new ArrayList<>(assetLinks.length);
			for (int i = -1; ++i != assetLinks.length;) {
				AssetLink assetLink = assetLinks[i];
				String serializedAssetLink = serializeAssetLink(assetLink);
				records.add(serializedAssetLink);
			}
			
			try {
				FileUtils.writeLines(indexFile, records);
			} catch (Exception ex) {
				AssetsViewPlugin.getDefault().logError("Error on updating assets index file", ex);
			}
		}
	}
	
	/** Returns the given {@code assetLink} serialized as string. */
	protected String serializeAssetLink(AssetLink assetLink) {
		String linkType = assetLink.LinkType().name();
		String elementId = assetLink.ElementId();
		String assetUrl = assetLink.AssetUrl();
		
		return linkType
				.concat(SplitToken).concat(elementId)
				.concat(SplitToken).concat(assetUrl)
				;
	}
	
	/** Returns an instance of {@link AssetLink} deserialized from the given {@code value}. */
	protected AssetLink deserializeAssetLink(String value) {
		String[] parts = value.split(SplitToken);
		EAssetLinkType linkType = EAssetLinkType.valueOf(parts[0]);
		String elementId = parts[1];
		String assetUrl = parts[2];
		
		return new AssetLink(linkType, elementId, assetUrl);
	}
}

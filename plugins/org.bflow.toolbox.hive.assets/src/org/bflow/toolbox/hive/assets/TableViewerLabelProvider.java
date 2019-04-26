package org.bflow.toolbox.hive.assets;

import org.apache.commons.io.FilenameUtils;
import org.bflow.toolbox.hive.libs.aprogu.lang.Cast;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Display;

/**
 * Extends {@link LabelProvider} to display the asset link labels in the table
 * viewer.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-04-23
 *
 */
public class TableViewerLabelProvider extends LabelProvider {
	private final Image _defaultImage = new Image(Display.getCurrent(), TableViewerLabelProvider.class.getResourceAsStream("/icons/File-16.png"));
	private final ImageRegistry _imageRegistry = new ImageRegistry();
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.BaseLabelProvider#dispose()
	 */
	@Override
	public void dispose() {
		_defaultImage.dispose();
		_imageRegistry.dispose();
		super.dispose();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
	 */
	@Override
	public Image getImage(Object element) {
		AssetLink assetLink = Cast.as(AssetLink.class, element);
		if (assetLink == null) return null;
		
		return getIcon(assetLink);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		AssetLink assetLink = Cast.as(AssetLink.class, element);
		if (assetLink == null) return null;
		
		if (assetLink.LinkType() == EAssetLinkType.Url)
			return assetLink.AssetUrl();
		
		return FilenameUtils.getName(assetLink.AssetUrl());
	}
	
	/**
	 * Returns an icon for the specified {@code assetLink}. If the 
	 * {@code assetLink} is NULL, NULL is returned.
	 */
	private Image getIcon(AssetLink assetLink) {
		if (assetLink == null) return null;
		
		EAssetLinkType linkType = assetLink.LinkType();
		String fileExtension = null;
		
		if (linkType == EAssetLinkType.Url)
			fileExtension = getFileExtension("html");
		
		if (linkType == EAssetLinkType.File
		 || linkType == EAssetLinkType.Symlink)
			fileExtension = getFileExtension(assetLink.AssetUrl());
		
		Image icon = getIconForFileExtension(fileExtension);
		return icon;
	}
	
	/** Returns the file extension of a file with the specified {@code path}. */
	private String getFileExtension(String path) {	
		String[] parts = path.split("\\.");
		String extension = parts[parts.length - 1];			
		return extension;
	}
	
	/** Returns an icon for the specified {@code fileExtension}. */
	private Image getIconForFileExtension(String fileExtension) {
		Image image = _imageRegistry.get(fileExtension);
		if (image != null) return image;

		Program program = Program.findProgram(fileExtension);
		if (program == null) return _defaultImage;
		
		ImageData imageData = program.getImageData();
		if (imageData == null) return _defaultImage;
		
		image = new Image(Display.getCurrent(), imageData);
		_imageRegistry.put(fileExtension, image);

		return image;
	}
}

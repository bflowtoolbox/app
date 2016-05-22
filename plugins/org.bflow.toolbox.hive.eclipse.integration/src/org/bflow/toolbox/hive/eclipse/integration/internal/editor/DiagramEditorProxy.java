package org.bflow.toolbox.hive.eclipse.integration.internal.editor;

import java.io.IOException;
import java.io.InputStream;

import org.bflow.toolbox.hive.eclipse.integration.EclipseIntegrator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

/**
 * Implements {@link IEditorPart} to proxy an origin diagram editor. This offers
 * the possibility to hook the Eclipse API.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 12.07.2015
 *
 */
public class DiagramEditorProxy extends EditorPart {
	
	/** Eclipse Editor Id */
	static public final String EditorId = "org.bflow.toolbox.hive.eclipse.integration.internal.editor.diagramEditorProxy";

	static private Image fTitleImage;
	
	private IEditorPart fOriginEditorPart;
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {
		fOriginEditorPart.doSave(monitor);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#doSaveAs()
	 */
	@Override
	public void doSaveAs() {
		fOriginEditorPart.doSaveAs();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#dispose()
	 */
	@Override
	public void dispose() {
		fOriginEditorPart.dispose();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#getAdapter(java.lang.Class)
	 */
	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
		return fOriginEditorPart.getAdapter(adapter);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		setSite(site);
		setInput(input);
		
		String fileName = input.getName();
		IEditorPart originEditorPart = EclipseIntegrator.getOriginDefaultEditorInstance(fileName);
		if (originEditorPart == null) throw new PartInitException("Could not resolve origin editor part!");
		
		fOriginEditorPart = originEditorPart;
		fOriginEditorPart.init(site, input);
		
		fOriginEditorPart.addPropertyListener(new IPropertyListener() {
			@Override
			public void propertyChanged(Object source, int propId) {
				firePropertyChange(propId);
				
				// Properties may change later again
				setPartName(fOriginEditorPart.getTitle());
				setTitleToolTip(fOriginEditorPart.getTitleToolTip());
			}
		});
		
		if (fTitleImage == null) {
			try (InputStream inputStream = getClass().getResourceAsStream("/images/Beejive-Im-flat-circle-16.png")) {
				Image image = new Image(Display.getCurrent(), inputStream);
				fTitleImage = image;
			} catch (IOException ex) {
				ex.printStackTrace();
			}	
		}
		
		setPartName(fOriginEditorPart.getTitle());
		setTitleToolTip(fOriginEditorPart.getTitleToolTip());
		setTitleImage(fTitleImage);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#isDirty()
	 */
	@Override
	public boolean isDirty() {
		return fOriginEditorPart.isDirty();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#isSaveAsAllowed()
	 */
	@Override
	public boolean isSaveAsAllowed() {
		return fOriginEditorPart.isSaveAsAllowed();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		fOriginEditorPart.createPartControl(parent);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		fOriginEditorPart.setFocus();
	}
}

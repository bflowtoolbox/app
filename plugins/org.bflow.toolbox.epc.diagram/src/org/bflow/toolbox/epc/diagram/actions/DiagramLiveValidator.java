package org.bflow.toolbox.epc.diagram.actions;

import java.io.File;

import org.bflow.toolbox.epc.diagram.part.ValidateAction;
import org.bflow.toolbox.hive.interchange.events.ImportListenerRegistry;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeProcessListener;
import org.bflow.toolbox.hive.interchange.mif.core.InterchangeProcessInfo;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;


/**
 * If the model resource is change, the <code>ResourceSetListener</code> will
 * call me to run validation.
 * 
 * @see EpcResourceListener
 * @see ResourceSetListener
 * @author Joerg Hartmann
 * @version 16/12/13 modified by Arian Storch<arian.storch@bflow.org>
 *
 */
public class DiagramLiveValidator implements IInterchangeProcessListener {
	
	
	/**
	 * The correspond diagram edit part which provides me.
	 */
	private DiagramEditPart fDiagramEditPart;
	
	
	/**
	 * Indicates if validation should be started.
	 */
	private boolean fDoValidate;
	
	/**
	 * Indicates if the live validation is enabled
	 */
	private boolean fIsEnabled;
	
	/**
	 * If <code>doValidate</code> is set to true, this listener will be set
	 * to your resource set.
	 * Otherwise it will be removed.
	 */
	private EpcResourceListener fResourceListener;
	private static DiagramLiveValidator fInstance;
	
	/**
	 * Returns an instance which operates on the given diagram edit part.
	 * 
	 * @param editPart
	 *            The diagram edit part which will be processed by this instance
	 * @return instance associated with the given diagram edit part
	 */
	public static DiagramLiveValidator getInstance(DiagramEditPart editPart) {
		if(fInstance == null)
			fInstance = new DiagramLiveValidator();
		
		fInstance.fDiagramEditPart = editPart;
		fInstance.doValidate(true);
		return fInstance;
	}
	
	/**
	 * Creating the validator by delivering the diagram edit part.
	 * Also initializes the <code>doValidate</code> flag to false.
	 * @param editPart
	 */
	private DiagramLiveValidator()
	{
		ImportListenerRegistry.addInterchangeProcessImportListener(this);
		fIsEnabled = true;
	}
	
	
	/**
	 * This method will be called if you want to validate the diagram.
	 * Validation will only be started if the <code>doValidate</code> is true
	 * and the <code>diagramEditPart</code> is not null.
	 * @see ValidateAction.runValidation(DiagramEditPart, View)
	 */
	public void runValidation() {		
		if(fIsEnabled && fDoValidate && fDiagramEditPart != null) {			
			ValidateAction.runValidation(fDiagramEditPart, fDiagramEditPart.getDiagramView());
		}
	}
	
	
	/**
	 * Sets the <code>doValidate</code> flag. If you deliver true, the 
	 * resource listener will be instantiated and set. Otherwise remove the 
	 * listener.
	 * @param fDoValidate
	 */
	public void doValidate(boolean value) {
		if(value) {
			fResourceListener = new EpcResourceListener(this);			
			fDiagramEditPart.getEditingDomain().addResourceSetListener(fResourceListener);
		} else {
			fDiagramEditPart.getEditingDomain().removeResourceSetListener(fResourceListener);
			fResourceListener = null;
		}
		fDoValidate = value;
	}
	
	
	/**
	 * Returns the value of <code>doValidate</code>.
	 * @return
	 */
	public boolean isDoValidate() {
		return fDoValidate;
	}
	
	/**
	 * Returns true if the live validation is enabled.
	 * @return true or false
	 */
	public boolean isEnabled() {
		return fIsEnabled;
	}
	
	/**
	 * Sets the state of the live validation.
	 * @param enabled true or false
	 */
	public void setEnabled(boolean enabled) {
		this.fIsEnabled = enabled;
	}
	
	/**
	 * To access the diagram edit part.
	 * @return <code>diagramEditPart</code>
	 */
	public DiagramEditPart getDiagramEditPart() {
		return fDiagramEditPart;
	}
	
	private boolean fBeforeImportState = false;
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IInterchangeProcessListener#onInitialize(org.bflow.toolbox.interchange.mif.core.InterchangeProcessInfo)
	 */
	@Override
	public void onInitialize(InterchangeProcessInfo interchangeProcessInfo) {
		fBeforeImportState = isEnabled();
		setEnabled(false);
	}
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IInterchangeProcessListener#onFinished(org.bflow.toolbox.interchange.mif.core.InterchangeProcessInfo)
	 */
	@Override
	public void onFinished(InterchangeProcessInfo interchangeProcessInfo) {
		setEnabled(fBeforeImportState);
	}
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IInterchangeProcessListener#afterProcessing(java.io.File, java.io.File)
	 */
	@Override
	public void afterProcessing(File sourceFile, File targetFile) {	
	}
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IInterchangeProcessListener#beforeProcessing(java.io.File, java.io.File)
	 */
	@Override
	public void beforeProcessing(File sourceFile, File targetFile) {		
	}
}

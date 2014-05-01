package org.bflow.toolbox.contributions.addons;

import java.util.Map;

import org.bflow.toolbox.bflow.BflowPackage;
import org.bflow.toolbox.epc.EpcPackage;
import org.bflow.toolbox.epc.diagram.part.EpcDiagramEditorUtil;
import org.bflow.toolbox.extensions.colorschemes.OriginalColorSchema;
import org.bflow.toolbox.hive.attributes.AttributeFile;
import org.bflow.toolbox.hive.interchange.mif.core.IModelBuilderAttendant;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;

/**
 * Implements {@link IModelBuilderAttendant} to support the creation of EPCs.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 12/07/13
 * @version 01/11/13
 */
public class EPCModelBuilderAttendant implements IModelBuilderAttendant  {
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IMetaModelInformationProvider#getEStructuralFeatureFor(java.lang.String, java.lang.String)
	 */
	@Override
	public EStructuralFeature getEStructuralFeatureFor(String type, String name) {
		return EpcPackage.eINSTANCE.getEvent().getEStructuralFeature(BflowPackage.ELEMENT__NAME);
	}
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IMetaModelInformationProvider#createDiagramInstance(org.eclipse.emf.common.util.URI, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public Resource createDiagramInstance(URI diagramURI, IProgressMonitor progressMonitor) {
		return EpcDiagramEditorUtil.createDiagram(diagramURI, progressMonitor);
	}
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IModelBuilderAttendant#getSaveOptions()
	 */
	@Override
	public Map<?, ?> getSaveOptions() {
		return org.bflow.toolbox.epc.diagram.part.EpcDiagramEditorUtil.getSaveOptions();
	}
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IModelBuilderAttendant#onBeforeFinish(org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart)
	 */
	@Override
	public void onBeforeFinish(DiagramEditPart diagramEditPart) throws Exception {
		@SuppressWarnings("unchecked")
		Command cmd = new OriginalColorSchema().getCommand(diagramEditPart.getChildren()); 
		diagramEditPart.getDiagramEditDomain().getDiagramCommandStack().execute(cmd);
		
		if(attrFile != null) {
			attrFile.save();
			attrFile = null;
		}
	}
	
	AttributeFile attrFile;
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IModelBuilderAttendant#createAttributeFile(org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart)
	 */
	@Override
	public void createAttributeFile(DiagramEditPart diagramEditPart) throws Exception {
		attrFile = new AttributeFile(diagramEditPart);
		attrFile.load();
	}
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IModelBuilderAttendant#setAttributes(org.eclipse.gef.EditPart, java.util.Map)
	 */
	@Override
	public void setAttributes(EditPart editPart, Map<String, String> attributesMap) {
		if(attrFile == null) 
			return;
		
		if(attributesMap == null || attributesMap.isEmpty())
			return;
		
		IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart)editPart;
		EObject eObject = null;
		
		if(graphicalEditPart instanceof DiagramEditPart) {
			eObject = ((DiagramEditPart)editPart).getNotationView().getElement();
		} else {
			eObject = graphicalEditPart.getPrimaryView().getElement();
		}
		
		String proxyId = EMFCoreUtil.getProxyID(eObject);
		
		for(String key:attributesMap.keySet()) {
			String value = attributesMap.get(key);
			attrFile.add(proxyId, key, value);	
		}
		
	}

}

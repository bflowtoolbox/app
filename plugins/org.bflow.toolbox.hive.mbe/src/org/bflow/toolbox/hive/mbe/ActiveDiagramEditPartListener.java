package org.bflow.toolbox.hive.mbe;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartListener;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;

/**
 * Listens to the changes of a diagram edit part to provide various features.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 07.03.2015
 *
 */
public class ActiveDiagramEditPartListener implements EditPartListener {
	
	/**
	 * Static instance. Can be used because this implementation is stateless.
	 */
	public static final ActiveDiagramEditPartListener Default = new ActiveDiagramEditPartListener();
	
	private DiagramEditPart currentOperatedDiagramEditPart;
	
	private final boolean fixCopiedElementNames = true;
	
	/**
	 * Tells this instance that it has been registered to the given diagram edit
	 * part.
	 * 
	 * @param diagramEditPart
	 *            Diagram edit part that this instance has been applied
	 */
	public void addedToDiagramEditPart(DiagramEditPart diagramEditPart) {
		currentOperatedDiagramEditPart = diagramEditPart;
		
		final ActiveDiagramEditPartListener _self = this;
		
		/* If the user saves the diagram, the diagram edit part will 
		 * be removed and disposed. However, the diagram edit part is the only 
		 * child of the root edit part. So, if the diagram edit part 
		 * changes, it will be removed and added as the first child 
		 * of the root edit part. In this case we will have to re-register 
		 * the current listener.
		 */
		RootEditPart root = currentOperatedDiagramEditPart.getRoot();
		root.addEditPartListener(new EditPartListener.Stub() {
			@Override
			public void childAdded(EditPart child, int index) {
				DiagramEditPart diagramEditPart = (DiagramEditPart) child;
				diagramEditPart.addEditPartListener(_self);
				currentOperatedDiagramEditPart = diagramEditPart;
			}
			
			/* (non-Javadoc)
			 * @see org.eclipse.gef.EditPartListener.Stub#removingChild(org.eclipse.gef.EditPart, int)
			 */
			@Override
			public void removingChild(EditPart child, int index) {
				DiagramEditPart diagramEditPart = (DiagramEditPart) child;
				diagramEditPart.removeEditPartListener(_self);
				currentOperatedDiagramEditPart = null;
			}
		});
		
		// TODO Unregister root part listener
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.EditPartListener#childAdded(org.eclipse.gef.EditPart, int)
	 */
	@Override
	public void childAdded(EditPart child, int index) {	
		if (!(child instanceof IGraphicalEditPart)) return;
		
		IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) child;
		
		// TODO Use Feature.Enabled pattern
		if (fixCopiedElementNames) { 
			fixCopiedElementName(graphicalEditPart, currentOperatedDiagramEditPart);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.EditPartListener#partActivated(org.eclipse.gef.EditPart)
	 */
	@Override
	public void partActivated(EditPart editpart) {	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.EditPartListener#partDeactivated(org.eclipse.gef.EditPart)
	 */
	@Override
	public void partDeactivated(EditPart editpart) { }

	/* (non-Javadoc)
	 * @see org.eclipse.gef.EditPartListener#removingChild(org.eclipse.gef.EditPart, int)
	 */
	@Override
	public void removingChild(EditPart child, int index) { }

	/* (non-Javadoc)
	 * @see org.eclipse.gef.EditPartListener#selectedStateChanged(org.eclipse.gef.EditPart)
	 */
	@Override
	public void selectedStateChanged(EditPart editpart) { }
	
	
	private static final Pattern CopyPrefixPattern = Pattern.compile(getLocalisedCopyPrefixPattern());
	
	/**
	 * Checks if the given edit part has a name that indicates that it has been
	 * created by copy and paste (for instance, starting with 'Copy_1'). In this
	 * case this prefix is removed.
	 * 
	 * @param editPart
	 *            Edit part to check
	 * @param diagramEditPart
	 *            Containing edit part
	 */
	private void fixCopiedElementName(IGraphicalEditPart editPart, DiagramEditPart diagramEditPart) {
		final EObject modelElement = editPart.resolveSemanticElement();
		if (modelElement == null) return;
		
		String modelElementName = EMFCoreUtil.getName(modelElement);
		if (StringUtils.isBlank(modelElementName)) return;
		
		Matcher matcher = CopyPrefixPattern.matcher(modelElementName);
		if (!matcher.find()) return;
		
		final String fixedName = matcher.replaceFirst(StringUtils.EMPTY);
		
		TransactionalEditingDomain transactionalEditingDomain = diagramEditPart.getEditingDomain();
		
		RecordingCommand recordingCommand = new RecordingCommand(transactionalEditingDomain) {			
			@Override
			protected void doExecute() {
				EMFCoreUtil.setName(modelElement, fixedName);
			}
		};
		
		transactionalEditingDomain.getCommandStack().execute(recordingCommand);
	}
	
	/**
	 * Returns the copy prefix pattern derived from the platform natural
	 * language setting.
	 * 
	 * @return String
	 */
	private static String getLocalisedCopyPrefixPattern() {
		String platformNL = Platform.getNL();
		if (platformNL.toLowerCase().startsWith("de".toLowerCase())) return "Kopieren_\\d_";
		return "Copy_\\d_"; // default
	}
}
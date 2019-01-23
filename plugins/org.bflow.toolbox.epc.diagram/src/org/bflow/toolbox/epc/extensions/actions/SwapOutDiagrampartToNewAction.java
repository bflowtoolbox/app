package org.bflow.toolbox.epc.extensions.actions;

import java.util.ArrayList;
import java.util.List;

import org.bflow.toolbox.epc.Function;
import org.bflow.toolbox.epc.diagram.Messages;
import org.bflow.toolbox.epc.diagram.edit.parts.ArcEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.EpcEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.EventEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.FunctionEditPart;
import org.bflow.toolbox.epc.diagram.part.EpcCreationWizard;
import org.bflow.toolbox.epc.diagram.part.EpcDiagramEditor;
import org.bflow.toolbox.epc.diagram.part.EpcDiagramEditorPlugin;
import org.bflow.toolbox.epc.diagram.part.EpcDiagramEditorUtil;
import org.bflow.toolbox.epc.diagram.providers.EpcElementTypes;
import org.bflow.toolbox.extensions.edit.parts.ColoredNodeEditPart;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequestFactory;
import org.eclipse.gmf.runtime.emf.clipboard.core.ClipboardUtil;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Implements the {@link IObjectActionDelegate} to handle the request.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2010-03-23
 * @version 2013-12-14
 */
public class SwapOutDiagrampartToNewAction implements IObjectActionDelegate {
	private DiagramEditor editor;
	private DiagramEditPart editPart;
	private IStructuredSelection selection;
	private IWorkbench workbench;
	private Shell myShell;

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		workbench = targetPart.getSite().getWorkbenchWindow().getWorkbench();
		myShell = targetPart.getSite().getShell();

		editor = (DiagramEditor) targetPart.getSite().getWorkbenchWindow()
				.getActivePage().getActiveEditor();
		editPart = (DiagramEditPart) editor.getDiagramEditPart();
	}

	@Override
	public void run(IAction action) {

		/*
		 * disable live validation to increase speed
		 */
		DiagramLiveValidator validator = EpcDiagramEditorPlugin.getInstance()
				.getDiagramLiveValidator();
		validator.doValidate(false);

		List<EObject> list = new java.util.ArrayList<EObject>();

		ColoredNodeEditPart selStart = getBegin(selection.toList());
		ColoredNodeEditPart selEnd = getEnd(selection.toList());

		if (selection.size() == 1) {
			MessageDialog.openInformation(myShell, 
					Messages.SwapOutDiagrampartToNewAction_DialogTitle,
					Messages.SwapOutDiagrampartToNewAction_1);
			return;

		}

		if (!isSESE(selStart, selEnd)) {
			MessageDialog.openError(myShell, 
					Messages.SwapOutDiagrampartToNewAction_DialogTitle,
					Messages.SwapOutDiagrampartToNewAction_3);
			return;
		}

		ColoredNodeEditPart anchor = null;
		ColoredNodeEditPart harbour = null;

		// target of another element?
		if (selStart.getTargetConnections().size() > 0)
			anchor = (ColoredNodeEditPart) ((ArcEditPart) selStart
					.getTargetConnections().get(0)).getSource();

		// source of another element?
		if (selEnd.getSourceConnections().size() > 0)
			harbour = (ColoredNodeEditPart) ((ArcEditPart) selEnd
					.getSourceConnections().get(0)).getTarget();

		try {

			/*
			 * prepare Elements
			 */
			for (Object object : selection.toArray()) {
				if (object.getClass() == ArcEditPart.class)
					continue;

				ColoredNodeEditPart editPart = (ColoredNodeEditPart) object;

				if (editPart.getSourceConnections().size() > 0)
					for (Object o2 : editPart.getSourceConnections().toArray())
						list.add(((ArcEditPart) o2).resolveSemanticElement());

				if (editPart.getTargetConnections().size() > 0)
					for (Object o2 : editPart.getTargetConnections().toArray())
						list.add(((ArcEditPart) o2).resolveSemanticElement());

				EObject eo = editPart.resolveSemanticElement();

				list.add(eo);
			}

			/*
			 * Create new diagram and open it
			 */
			EpcCreationWizard wizard = new EpcCreationWizard();
			wizard.init(workbench, StructuredSelection.EMPTY);
			WizardDialog wizardDialog = new WizardDialog(myShell, wizard);
			wizardDialog.open();

			Resource resource = wizard.getDiagram();

			if (resource == null)
				return;

			final String path = wizard.getDiagram().getURI().toPlatformString(
					true);

			EpcDiagramEditorUtil.openDiagram(resource);

			EpcDiagramEditor newEditor = (EpcDiagramEditor) workbench
					.getActiveWorkbenchWindow().getActivePage()
					.getActiveEditor();

			final EpcEditPart newEditPart = (EpcEditPart) newEditor
					.getDiagramEditPart();

			/*
			 * do copy
			 */
			final String s = ClipboardUtil.copyElementsToString(list, null,
					null);

			newEditPart.getEditingDomain().getCommandStack().execute(
					new RecordingCommand(newEditPart.getEditingDomain()) {

						@Override
						protected void doExecute() {
							try {
								ClipboardUtil.pasteElementsFromString(s,
										newEditPart.resolveSemanticElement(),
										null, null);
							} catch (Exception ex) {
								ex.printStackTrace();
							}

						}
					});

			/*
			 * do remove
			 */
			boolean keepSelStart = false;
			boolean keepSelEnd = false;
			// does selection start with event? -> don't delete it
			if (selStart instanceof EventEditPart) {
				list.remove(selStart.resolveSemanticElement());
				keepSelStart = true;
			}
			// does selection end with event? -> don't delete it
			if (selEnd instanceof EventEditPart) {
				list.remove(selEnd.resolveSemanticElement());
				keepSelEnd = true;
			}
			if (MessageDialog.openQuestion(myShell, 
					Messages.SwapOutDiagrampartToNewAction_4,
					Messages.SwapOutDiagrampartToNewAction_5)) {
				editPart.getEditingDomain().getCommandStack().execute(
						new DeleteCommand(editPart.getEditingDomain(), list) {
						});
			} else
				return;

			/*
			 * do chain
			 */
			if (MessageDialog.openQuestion(myShell, 
					Messages.SwapOutDiagrampartToNewAction_6,
					Messages.SwapOutDiagrampartToNewAction_7)) {
				InputDialog dlg = new InputDialog(
						myShell,
						Messages.SwapOutDiagrampartToNewAction_8,
						Messages.SwapOutDiagrampartToNewAction_8 + 
						": ", "", null); //$NON-NLS-1$ //$NON-NLS-2$

				if (dlg.open() == InputDialog.CANCEL)
					return;

				final String name = dlg.getValue();

				CreateViewRequest request = CreateViewRequestFactory
						.getCreateShapeRequest(EpcElementTypes.Function_2007,
								editPart.getDiagramPreferencesHint());

				// edit position
				Point p = selStart.getLocation();
				p.y = 30;
				request.setLocation(p);

				Command command = editor.getDiagramEditPart().getCommand(
						request);

				editor.getDiagramEditDomain().getDiagramCommandStack().execute(
						command);

				final FunctionEditPart fEditPart = (FunctionEditPart) editPart
						.getChildren().get(editPart.getChildren().size() - 1);
				final Function child = (Function) fEditPart.getPrimaryView()
						.getElement();

				editor.getDiagramEditDomain().getDiagramCommandStack().execute(
						new Command() {
							@Override
							public void execute() {
								super.execute();

								fEditPart.getEditingDomain().getCommandStack()
										.execute(
												new RecordingCommand(fEditPart
														.getEditingDomain()) {

													@Override
													protected void doExecute() {
														child.setName(name);
														child.getSubdiagram()
																.add(path);
														fEditPart.refresh();
													}
												});
							}
						});

				/*
				 * place in connection
				 */
				CreateConnectionViewRequest r = CreateViewRequestFactory
						.getCreateConnectionRequest(EpcElementTypes.Arc_4001,
								editor.getDiagramEditPart()
										.getDiagramPreferencesHint());

				if (anchor != null) {
					if (keepSelStart) {
						CreateConnectionViewRequest.getCreateCommand(r, anchor,
								selStart).execute();
					} else
						CreateConnectionViewRequest.getCreateCommand(r, anchor,
								fEditPart).execute();
				} 
					
				if (keepSelStart) {
						CreateConnectionViewRequest.getCreateCommand(r,
								selStart, fEditPart).execute();
					}
				

				if (harbour != null) {
					if (keepSelEnd) {
						CreateConnectionViewRequest.getCreateCommand(r, selEnd,
								harbour).execute();
					} else
						CreateConnectionViewRequest.getCreateCommand(r,
								fEditPart, harbour).execute();
				} 
					
				if (keepSelEnd) {
						CreateConnectionViewRequest.getCreateCommand(r,
								fEditPart, selEnd).execute();
					}
				
				editor.getDiagramEditPart().refresh();
			}

			/*
			 * activate and run validation
			 */
			validator.doValidate(true);
			validator.runValidation();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private ColoredNodeEditPart getBegin(List<?> list) {
		List<ColoredNodeEditPart> l = new ArrayList<ColoredNodeEditPart>();

		for (Object o : list)
			if (o.getClass() != ArcEditPart.class)
				l.add((ColoredNodeEditPart) o);

		for (ColoredNodeEditPart part : l) {
			// if(part.getClass() != EventEditPart.class)
			// continue;

			if (part.getTargetConnections().size() == 0)
				return part;

			ArcEditPart arcConnection = (ArcEditPart) part
					.getTargetConnections().get(0);
			ColoredNodeEditPart source = (ColoredNodeEditPart) arcConnection
					.getSource();

			if (!list.contains(source))
				return part;
		}

		return null;
	}

	private ColoredNodeEditPart getEnd(List<?> list) {
		List<ColoredNodeEditPart> l = new ArrayList<ColoredNodeEditPart>();

		for (Object o : list)
			if (o.getClass() != ArcEditPart.class)
				l.add((ColoredNodeEditPart) o);

		for (ColoredNodeEditPart part : l) {
			// if(part.getClass() != EventEditPart.class)
			// continue;

			if (part.getSourceConnections().size() == 0)
				return part;

			ArcEditPart arcConnection = (ArcEditPart) part
					.getSourceConnections().get(0);
			ColoredNodeEditPart source = (ColoredNodeEditPart) arcConnection
					.getTarget();

			if (!list.contains(source))
				return part;
		}

		return null;
	}

	private boolean isSESE(ColoredNodeEditPart first, ColoredNodeEditPart last) {

		if (first == last)
			return true;

		if (first.getSourceConnections().size() == 0
				|| last.getTargetConnections().size() == 0)
			return false;

		List<?> list = selection.toList();

		for (int i = 0; i < first.getSourceConnections().size(); i++) {
			ArcEditPart arc = (ArcEditPart) first.getSourceConnections().get(i);
			ColoredNodeEditPart alt = (ColoredNodeEditPart) arc.getTarget();

			if (!isSESE(alt, last))
				return false;
		}

		ColoredNodeEditPart next = (ColoredNodeEditPart) ((ArcEditPart) first
				.getSourceConnections().get(0)).getTarget();

		while (next != last) {
			if (next.getSourceConnections().size() == 0 || !list.contains(next))
				return false;

			next = (ColoredNodeEditPart) ((ArcEditPart) next
					.getSourceConnections().get(0)).getTarget();
		}

		return true;
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = (IStructuredSelection) selection;

	}

}

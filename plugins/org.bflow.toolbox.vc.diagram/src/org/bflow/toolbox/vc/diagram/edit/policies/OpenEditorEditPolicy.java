package org.bflow.toolbox.vc.diagram.edit.policies;

import org.bflow.toolbox.vc.diagram.edit.commands.OpenEditorCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.OpenEditPolicy;
import org.eclipse.gmf.runtime.notation.View;


public class OpenEditorEditPolicy extends OpenEditPolicy {
    protected Command getOpenCommand(Request request) {
           EditPart targetEditPart = getTargetEditPart(request);
           if (targetEditPart instanceof IGraphicalEditPart){
                   IGraphicalEditPart editPart = (IGraphicalEditPart)targetEditPart;
                   View view = editPart.getNotationView();
                   if (view!=null){
                          EObject element = ViewUtil.resolveSemanticElement(view);
                          if (element instanceof org.bflow.toolbox.vc.impl.ValueChainImpl || element instanceof org.bflow.toolbox.vc.impl.ValueChain2Impl)  {
                                  return new ICommandProxy(
                                         new OpenEditorCommand(element, editPart.getViewer().getControl().getShell()));
                          }
                   }
           }
           return null;
    }
}

package edu.toronto.cs.openome.evaluation.handlers;

import java.util.List;
import java.util.Vector;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.emf.core.resources.GMFResource;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.impl.DiagramImpl;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import edu.toronto.cs.openome.evaluation.commands.RemoveHumanJudgmentCommand;
import edu.toronto.cs.openome.evaluation.commands.RemoveReverseJudgmentCommand;
import edu.toronto.cs.openome_model.HumanJudgment;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart;

import edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart;

import edu.toronto.cs.openome_model.diagram.edit.parts.ActorActorCompartmentEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart;



import edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditor;
import edu.toronto.cs.openome_model.impl.GoalImpl;
import edu.toronto.cs.openome_model.impl.ModelImpl;
import edu.toronto.cs.openome_model.impl.ResourceImpl;
import edu.toronto.cs.openome_model.impl.SoftgoalImpl;
import edu.toronto.cs.openome_model.impl.TaskImpl;

public class ReasonerHandler implements IHandler {
	
	private Openome_modelDiagramEditor mDE;
	
	private DiagramCommandStack dcs;
	

	public ReasonerHandler() {
		mDE = null;
	}

	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

	public void dispose() {
		// TODO Auto-generated method stub

	}

	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isHandled() {
		// TODO Auto-generated method stub
		return true;
	}

	public void removeHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * @author jenhork
	 * The Reasoner needs a modelImpl and we are going to get it.
	 * Most of this code is copied of Yijun and Neil's Versioning code.  I'm not entirely sure how it works, 
	 * but it seems to work.
	 */
	public ModelImpl getModelImpl() {
		getModelDiagramEditor();
		
		EditingDomain editingDomain = mDE.getEditingDomain();
				
		ResourceSet resourceSet = editingDomain.getResourceSet();
		
				
		XMIResourceImpl xmires = null;
		
		for(Resource tmp: resourceSet.getResources()) {
			//System.out.println(tmp.toString());
			if (tmp instanceof XMIResourceImpl) {
				xmires = (XMIResourceImpl) tmp;
			}			
		}	
			
		ModelImpl model = null;
						
		for(EObject tmp2: xmires.getContents()){ 
			if (tmp2 instanceof ModelImpl) 
				model = (ModelImpl) tmp2; 
		}
		
		return model;
		
	}
	
	public List getEditParts() {
		DiagramEditPart dep = mDE.getDiagramEditPart();
		List l = dep.getPrimaryEditParts();	
		
		System.out.println(l.toString());
		
		return l;
	}
	
	/**
	 * @author jenhork
	 * This is an important somethingorrather that is needed to get both the modelImplementation and the command stack.
	 */
	private void getModelDiagramEditor() {
		
		try {
			IWorkbenchWindow iww = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			
			IWorkbenchPage iwp = iww.getActivePage(); //assume correct page is showing ... dubious
			
			IEditorPart iep= iwp.getActiveEditor(); //
			
			mDE = (Openome_modelDiagramEditor) iep; //
			
		}
		catch (Exception e) {
			System.out.println("Exception getting modelEditor");
		}
		 
	}
	
	public DiagramCommandStack getDiagramCommandStack() {
		IWorkbenchWindow iww = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		
		IWorkbenchPage iwp = iww.getActivePage(); //assume correct page is showing ... dubious
		IWorkbenchPart part = iwp.getActivePart();
		IDiagramWorkbenchPart diagramPart = (IDiagramWorkbenchPart) part;
		return diagramPart.getDiagramEditDomain().getDiagramCommandStack();
	}
	
	public CommandStack getCommandStack() {
		EditingDomain editingDomain = mDE.getEditingDomain();
		
		CommandStack cs = editingDomain.getCommandStack();
	
		return cs;
	}
	
	public void clearAllJudgments(ModelImpl m, CommandStack c) {
		System.out.println("Clear all judgments");
		Command removeJudgment;
		Command removeReverseJudgment;
		if (c == null)
			System.out.println("command stack is null in clear");
		if (m == null)
			System.out.println("model is null in clear");
		for (Intention i: m.getAllIntentions()) {
			System.out.println("Clearing judgments for: " + i.getName());			
			if (i.getHumanJudgments() != null)  {
				int size = i.getHumanJudgments().size();
				System.out.println("# human judgments: " + size);				
				for (int j = 0; j < size; j++) {
					HumanJudgment togo = i.getHumanJudgments().get(0);
					System.out.println("\tClearing judgment: " + togo.getResultLabel());
					removeJudgment = new RemoveHumanJudgmentCommand(i, togo);
					c.execute(removeJudgment);
				}
			}
			
			if (i.getReverseLabelBag() != null) {
				removeReverseJudgment = new RemoveReverseJudgmentCommand(i);
				c.execute(removeReverseJudgment);				
			}
		}
	}

}

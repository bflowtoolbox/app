package edu.toronto.cs.openome.testing;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Vector;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionChangeRecorder;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.core.DiagramEditingDomainFactory;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.wizard.ProgressMonitorPart;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.junit.Test;

import edu.toronto.cs.openome_model.Container;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Model;
import edu.toronto.cs.openome_model.diagram.edit.commands.HighlightIntentionsCommand;
import edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorUtil;
import edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes;
import edu.toronto.cs.openome_model.impl.ActorImpl;
import edu.toronto.cs.openome_model.impl.AgentImpl;
import edu.toronto.cs.openome_model.impl.GoalImpl;
import edu.toronto.cs.openome_model.impl.ModelImpl;
import edu.toronto.cs.openome_model.impl.PositionImpl;
import edu.toronto.cs.openome_model.impl.RoleImpl;

public class TestGetColor{
   
    private static Diagram diagram;
    private static edu.toronto.cs.openome_model.Model model;
    private static TransactionalEditingDomain domain;
    private static CommandStack cs;
	private static List editParts;

    /*
	 * Our containers
	 * We should test adding a goal into all four types of containers
	 */
	private static ActorImpl actor;
	private static AgentImpl agent;
	private static RoleImpl role;
	private static PositionImpl pos;
	private Vector<Intention> intentionsTest = null;
   
    @Test
    public void initializeWorkspace(){
    	// Create the workspace
		URI diagramURI = URI.createURI("platform:/resource/test/default.ood");
		//URI modelURI = URI.createURI("platform:/resource/test/default.oom");

		Resource diagramResource = Openome_modelDiagramEditorUtil.createDiagram(diagramURI, new NullProgressMonitor());
		for (Object o : diagramResource.getContents()) {
			if (o instanceof Diagram) {
				diagram = (Diagram) o;
			} else if (o instanceof Model) {
				model = (Model) o;
			}
		}

		ResourceSet rs = diagramResource.getResourceSet();
		domain =((TransactionChangeRecorder) rs.eAdapters().get(0)).getEditingDomain();
		cs = domain.getCommandStack();
		
		// Add the containers
		cs.execute(new ActorImplCreateCommand((ModelImpl) model));
		cs.execute(new AgentImplCreateCommand((ModelImpl) model));
		cs.execute(new RoleImplCreateCommand((ModelImpl) model));
		cs.execute(new PositionImplCreateCommand((ModelImpl) model));
		
		//get container references
		for (Container c : model.getContainers()){
			if (c instanceof ActorImpl)
				actor = (ActorImpl) c;
			else if (c instanceof AgentImpl)
				agent = (AgentImpl) c;
			else if (c instanceof RoleImpl)
				role = (RoleImpl) c;
			else if (c instanceof PositionImpl)
				pos = (PositionImpl) c;
		}
		
		assertNotNull(actor);
		assertNotNull(agent);
		assertNotNull(role);
		assertNotNull(pos);
	}
   
    /**
     * Tests that we can obtain the correct color of a highlighted intention.
     */
    @Test
    public void canGetColor() {
		String name = "testGoal";
		
		assertTrue(model.getIntentions().isEmpty());
		
		Command create = new GoalImplCreateCommand((ModelImpl) model, name);
		cs.execute(create);
		
		assertFalse(model.getIntentions().isEmpty());
		
		assertTrue(model.getIntentions().size() == 1);
		
		intentionsTest.addAll(model.getIntentions());
		
		//highlightIntentions(intentionsTest,"orange");
		GoalImpl gi = (GoalImpl) model.getIntentions().get(0);
		//EditPart ep = gi.
		//unHighlightIntentions(intentionsTest);
		
//		assertTrue(model.getIntentions().get(0).getModel().equals(model));
		
		//Test that the intention is fresh
		//intentionIsNotLinked(model.getIntentions().get(0));
		//intentionHasNoEvalLabels(model.getIntentions().get(0));
	}
	
	private void highlightIntentions(Vector<Intention> conflictIntentions, String color) {
		System.out.println("highlighting conflict intentions");
		HighlightIntentionsCommand highlight = new HighlightIntentionsCommand(editParts, conflictIntentions, color);
		
		cs.execute(highlight);		
	}
	private void unHighlightIntentions(Vector<Intention> conflictIntentions) {
		System.out.println("unhighlighting intentions");
		HighlightIntentionsCommand highlight = new HighlightIntentionsCommand(editParts, conflictIntentions, "");
		
		cs.execute(highlight);		
	}
	
    }

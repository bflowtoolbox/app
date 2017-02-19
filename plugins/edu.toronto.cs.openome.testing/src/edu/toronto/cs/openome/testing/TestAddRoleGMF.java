package edu.toronto.cs.openome.testing;

import static org.junit.Assert.*;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionChangeRecorder;
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

import edu.toronto.cs.openome_model.Model;
import edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorUtil;
import edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes;
import edu.toronto.cs.openome_model.impl.ActorImpl;
import edu.toronto.cs.openome_model.impl.AgentImpl;
import edu.toronto.cs.openome_model.impl.ModelImpl;
import edu.toronto.cs.openome_model.impl.RoleImpl;

public class TestAddRoleGMF{
	
	private static Diagram diagram;
	private static edu.toronto.cs.openome_model.Model model;
	private static TransactionalEditingDomain domain;
	private static CommandStack cs;
	
	
	@Test
	public void initializeWorkspace(){
		URI diagramURI = URI.createURI("platform:/resource/test/default.ood");
		URI modelURI = URI.createURI("platform:/resource/test/default.oom");
		//TransactionalEditingDomain domain = TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain();
		
		
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
	}
	
	/**
	 * Tests that a new role can be created
	 */
	@Test
	public void canAddRole() {
		String name = "testRole";
		
		assertTrue(model.getContainers().isEmpty());
		
		Command create = new RoleImplCreateCommand((ModelImpl) model, name);
		cs.execute(create);
		
		assertFalse(model.getContainers().isEmpty());
		
		assertTrue(model.getContainers().size() == 1);
		
		assertTrue(model.getContainers().get(0) instanceof RoleImpl);
		assertTrue(model.getContainers().get(0).getName().equals(name));
		
	}
	
	/**
	 * Tests that a new role is originally empty
	 * i.e. contains no intentions
	 */
	@Test
	public void roleIsEmpty(){
		assertTrue(model.getContainers().get(0).getIntentions().isEmpty());
	}
	
	/**
	 * Tests that a new role initially has no connecting line to any other objects
	 */
	@Test
	public void roleIsNotLinked(){
		assertTrue(model.getContainers().get(0).getAssociationFrom().isEmpty());
		assertTrue(model.getContainers().get(0).getAssociationTo().isEmpty());
		assertTrue(model.getContainers().get(0).getDependencyFrom().isEmpty());
		assertTrue(model.getContainers().get(0).getAssociationTo().isEmpty());
	}
}
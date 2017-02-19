package edu.toronto.cs.openome.testing;

import static org.junit.Assert.assertTrue;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionChangeRecorder;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.junit.Before;

import edu.toronto.cs.openome.evaluation.SATSolver.DualHashMap;
import edu.toronto.cs.openome.evaluation.SATSolver.ModeltoAxiomsConverter;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Model;
import edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorUtil;
import edu.toronto.cs.openome_model.impl.ModelImpl;

public class CreateExampleModel {
	
	protected static Diagram diagram;
	protected static edu.toronto.cs.openome_model.Model model;
	protected static TransactionalEditingDomain domain;
	protected static CommandStack cs;
	protected static String testfile = "";
	
	
	
	//@Test
	@Before
	public void initializeWorkspace(){
		URI diagramURI = URI.createFileURI(testfile);
		URI modelURI = URI.createFileURI(testfile);		
		
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

	
	public void initializeAndDecompositionModel(){
		assertTrue(model != null);
		
		assertTrue(model.getContainers().isEmpty());
		
		GoalImplCreateCommand create = new GoalImplCreateCommand((ModelImpl) model, "a");
		cs.execute(create);
		TaskImplCreateCommand create2 = new TaskImplCreateCommand((ModelImpl) model, "b");
		cs.execute(create2);
		TaskImplCreateCommand create3 = new TaskImplCreateCommand((ModelImpl) model, "c");
		cs.execute(create3);
		Command create4 = new AndDecompositionImplCreateCommand((ModelImpl) model, create2.getTaskImpl(), create.getGoalImpl());
		cs.execute(create4);
		create4 = new AndDecompositionImplCreateCommand((ModelImpl) model, create3.getTaskImpl(), create.getGoalImpl());
		cs.execute(create4);
		
		ModeltoAxiomsConverter converter = new ModeltoAxiomsConverter((ModelImpl) model, cs);
		
		DualHashMap<Integer, Intention> intIndex = converter.getIntentionIndex();
		
		for (Intention intention: model.getIntentions())  {
			Integer i = (Integer) intIndex.getInverse(intention);
			intIndex.remove(i);
			if (intention.getName().equals("a")) 
				intIndex.put(new Integer(1), intention);
			if (intention.getName().equals("b"))
				intIndex.put(new Integer(7), intention);
			if (intention.getName().equals("c"))
				intIndex.put(new Integer(13), intention);
		}		
				
	}
	
	public void initializeKHPTestModel(){
		assertTrue(model != null);
		
		assertTrue(model.getContainers().isEmpty());
		
		//Counsellors
		RoleImplCreateCommand createRole = new RoleImplCreateCommand((ModelImpl) model, "Counsellors");
		cs.execute(createRole);
		SoftgoalImplCreateCommand createHappiness = new SoftgoalImplCreateCommand(createRole.getRoleImpl(), "Happiness [Counsellors]");
		cs.execute(createHappiness);
		SoftgoalImplCreateCommand createBurnout = new SoftgoalImplCreateCommand(createRole.getRoleImpl(), "Avoid Burnout");
		cs.execute(createBurnout);
		SoftgoalImplCreateCommand createHighQuality = new SoftgoalImplCreateCommand(createRole.getRoleImpl(), "High Quality Counselling");
		cs.execute(createHighQuality);
		SoftgoalImplCreateCommand createListen = new SoftgoalImplCreateCommand(createRole.getRoleImpl(), "Listen for Cues");
		cs.execute(createListen);
		SoftgoalImplCreateCommand createHelpAsMany = new SoftgoalImplCreateCommand(createRole.getRoleImpl(), "Help As Many Kids as Possible");
		cs.execute(createHelpAsMany);
		GoalImplCreateCommand createProvideOnline = new GoalImplCreateCommand(createRole.getRoleImpl(), "Provide Online Counseling Services");
		cs.execute(createProvideOnline);
		TaskImplCreateCommand createUseText = new TaskImplCreateCommand(createRole.getRoleImpl(), "Use Text Messaging");
		cs.execute(createUseText);
		TaskImplCreateCommand createUseCyber = new TaskImplCreateCommand(createRole.getRoleImpl(), "Use Cyber Cafe/Portal/Chat Room");
		cs.execute(createUseCyber);		
		
		Command createMeansEnds1 = new OrDecompositionImplCreateCommand((ModelImpl) model, createUseText.getTaskImpl(), createProvideOnline.getGoalImpl());
		cs.execute(createMeansEnds1);
		Command createMeansEnds2 = new OrDecompositionImplCreateCommand((ModelImpl) model, createUseCyber.getTaskImpl(), createProvideOnline.getGoalImpl());
		cs.execute(createMeansEnds2);
		Command createHelp1 = new HelpImplCreateCommand((ModelImpl) model, createProvideOnline.getGoalImpl(), createHelpAsMany.getSoftgoalImpl());
		cs.execute(createHelp1);
		Command createHelp2 = new HelpImplCreateCommand((ModelImpl) model, createHelpAsMany.getSoftgoalImpl(), createHappiness.getSoftgoalImpl());
		cs.execute(createHelp2);
		Command createHelp3 = new HelpImplCreateCommand((ModelImpl) model, createBurnout.getSoftgoalImpl(), createHappiness.getSoftgoalImpl());
		cs.execute(createHelp3);
		Command createHelp4 = new HelpImplCreateCommand((ModelImpl) model, createHighQuality.getSoftgoalImpl(), createHappiness.getSoftgoalImpl());
		cs.execute(createHelp4);
		Command createHelp5 = new HelpImplCreateCommand((ModelImpl) model, createHappiness.getSoftgoalImpl(), createHighQuality.getSoftgoalImpl());
		cs.execute(createHelp5);
		Command createHelp6 = new HelpImplCreateCommand((ModelImpl) model, createListen.getSoftgoalImpl(), createHighQuality.getSoftgoalImpl());
		cs.execute(createHelp6);
		Command createHurt1 = new HurtImplCreateCommand((ModelImpl) model, createHelpAsMany.getSoftgoalImpl(), createBurnout.getSoftgoalImpl());
		cs.execute(createHurt1);
		Command createHurt2 = new HurtImplCreateCommand((ModelImpl) model, createHelpAsMany.getSoftgoalImpl(), createHighQuality.getSoftgoalImpl());
		cs.execute(createHurt2);
		Command createHurt3 = new HurtImplCreateCommand((ModelImpl) model, createUseCyber.getTaskImpl(), createListen.getSoftgoalImpl());
		cs.execute(createHurt3);
		Command createBreak1 = new BreakImplCreateCommand((ModelImpl) model, createUseText.getTaskImpl(), createListen.getSoftgoalImpl());
		cs.execute(createBreak1);	
		
		//Organization
		AgentImplCreateCommand createAgent = new AgentImplCreateCommand((ModelImpl) model, "Organization");
		cs.execute(createAgent);
		SoftgoalImplCreateCommand createAnonymity = new SoftgoalImplCreateCommand(createAgent.getAgentImpl(), "Anonymity [Services]");
		cs.execute(createAnonymity);
		SoftgoalImplCreateCommand createScandal = new SoftgoalImplCreateCommand(createAgent.getAgentImpl(), "Avoid Scandal");
		cs.execute(createScandal);
		SoftgoalImplCreateCommand createFunds = new SoftgoalImplCreateCommand(createAgent.getAgentImpl(), "Increase Funds");
		cs.execute(createFunds);
		SoftgoalImplCreateCommand createHelpAsMany2 = new SoftgoalImplCreateCommand(createAgent.getAgentImpl(), "Help as Many Kids as Possible 2");
		cs.execute(createHelpAsMany2);
		SoftgoalImplCreateCommand createHelp = new SoftgoalImplCreateCommand(createAgent.getAgentImpl(), "Help Kids");
		cs.execute(createHelp);
		SoftgoalImplCreateCommand createHighQuality2 = new SoftgoalImplCreateCommand(createAgent.getAgentImpl(), "High Quality Counseling 2");
		cs.execute(createHighQuality2);
		SoftgoalImplCreateCommand createImmediacy = new SoftgoalImplCreateCommand(createAgent.getAgentImpl(), "Immediacy [Services]");
		cs.execute(createImmediacy);
		GoalImplCreateCommand createProvideOnline2 = new GoalImplCreateCommand(createAgent.getAgentImpl(), "Provide Online Counseling Services 2");
		cs.execute(createProvideOnline2);
		TaskImplCreateCommand createUseText2 = new TaskImplCreateCommand(createAgent.getAgentImpl(), "Use Text Messaging 2");
		cs.execute(createUseText2);
		TaskImplCreateCommand createUseCyber2 = new TaskImplCreateCommand(createAgent.getAgentImpl(), "Use Cyber Cafe/Portal/Chat Room 2");
		cs.execute(createUseCyber2);
		
		Command createMeansEnds3 = new OrDecompositionImplCreateCommand((ModelImpl) model, createUseText2.getTaskImpl(), createProvideOnline2.getGoalImpl());
		cs.execute(createMeansEnds3);
		Command createMeansEnds4 = new OrDecompositionImplCreateCommand((ModelImpl) model, createUseCyber2.getTaskImpl(), createProvideOnline2.getGoalImpl());
		cs.execute(createMeansEnds4);
		Command createHelp7 = new HelpImplCreateCommand((ModelImpl) model, createUseText2.getTaskImpl(), createAnonymity.getSoftgoalImpl());
		cs.execute(createHelp7);
		Command createHelp8 = new HelpImplCreateCommand((ModelImpl) model, createAnonymity.getSoftgoalImpl(), createScandal.getSoftgoalImpl());
		cs.execute(createHelp8);
		Command createHelp9 = new HelpImplCreateCommand((ModelImpl) model, createScandal.getSoftgoalImpl(), createFunds.getSoftgoalImpl());
		cs.execute(createHelp9);
		Command createHelp10 = new HelpImplCreateCommand((ModelImpl) model, createFunds.getSoftgoalImpl(), createHelpAsMany2.getSoftgoalImpl());
		cs.execute(createHelp10);
		Command createHelp11 = new HelpImplCreateCommand((ModelImpl) model, createHelpAsMany2.getSoftgoalImpl(), createHelp.getSoftgoalImpl());
		cs.execute(createHelp11);
		Command createHelp12 = new HelpImplCreateCommand((ModelImpl) model, createHighQuality2.getSoftgoalImpl(), createHelp.getSoftgoalImpl());
		cs.execute(createHelp12);
		Command createHelp13 = new HelpImplCreateCommand((ModelImpl) model, createHighQuality2.getSoftgoalImpl(), createFunds.getSoftgoalImpl());
		cs.execute(createHelp13);
		Command createHelp14 = new HelpImplCreateCommand((ModelImpl) model, createImmediacy.getSoftgoalImpl(), createHighQuality2.getSoftgoalImpl());
		cs.execute(createHelp14);
		Command createHelp15 = new HelpImplCreateCommand((ModelImpl) model, createUseCyber2.getTaskImpl(), createImmediacy.getSoftgoalImpl());
		cs.execute(createHelp15);
		Command createHurt4 = new HurtImplCreateCommand((ModelImpl) model, createUseCyber2.getTaskImpl(), createAnonymity.getSoftgoalImpl());
		cs.execute(createHurt4);
		Command createHurt5 = new HurtImplCreateCommand((ModelImpl) model, createUseText2.getTaskImpl(), createImmediacy.getSoftgoalImpl());
		cs.execute(createHurt5);
		Command createHurt6 = new HurtImplCreateCommand((ModelImpl) model, createUseCyber2.getTaskImpl(), createHighQuality2.getSoftgoalImpl());
		cs.execute(createHurt6);
		Command createBreak2 = new BreakImplCreateCommand((ModelImpl) model, createUseText2.getTaskImpl(), createHighQuality2.getSoftgoalImpl());
		cs.execute(createBreak2);

		//Dependums
		SoftgoalImplCreateCommand createHelpAsMany3 = new SoftgoalImplCreateCommand((ModelImpl) model, "Help as Many Kids as Possible 3");
		cs.execute(createHelpAsMany3);
		SoftgoalImplCreateCommand createHighQuality3 = new SoftgoalImplCreateCommand((ModelImpl) model, "High Quality Counseling 3");
		cs.execute(createHighQuality3);
		TaskImplCreateCommand createProvideText = new TaskImplCreateCommand((ModelImpl) model, "Provide Counseling via Text Messaging");
		cs.execute(createProvideText);
		TaskImplCreateCommand createProvideCyber = new TaskImplCreateCommand((ModelImpl) model, "Provide Counseling via Cyber Cafe/Portal/Chat Room");
		cs.execute(createProvideCyber);
		
		//Dependencies
		//model, source, target
		DependencyImplCreateCommand createDep1 = new DependencyImplCreateCommand((ModelImpl) model, createHelpAsMany3.getSoftgoalImpl(), createHelpAsMany2.getSoftgoalImpl());
		cs.execute(createDep1);
		DependencyImplCreateCommand createDep2 = new DependencyImplCreateCommand((ModelImpl) model, createHelpAsMany.getSoftgoalImpl(), createHelpAsMany3.getSoftgoalImpl());
		cs.execute(createDep2);
		
		DependencyImplCreateCommand createDep3 = new DependencyImplCreateCommand((ModelImpl) model, createHighQuality3.getSoftgoalImpl(), createHighQuality2.getSoftgoalImpl());
		cs.execute(createDep3);
		DependencyImplCreateCommand createDep4 = new DependencyImplCreateCommand((ModelImpl) model, createHighQuality.getSoftgoalImpl(), createHighQuality3.getSoftgoalImpl());
		cs.execute(createDep4);
		
		DependencyImplCreateCommand createDep5 = new DependencyImplCreateCommand((ModelImpl) model, createProvideText.getTaskImpl(), createUseText2.getTaskImpl());
		cs.execute(createDep5);
		DependencyImplCreateCommand createDep6 = new DependencyImplCreateCommand((ModelImpl) model, createUseText.getTaskImpl(), createProvideText.getTaskImpl());
		cs.execute(createDep6);
		
		DependencyImplCreateCommand createDep7 = new DependencyImplCreateCommand((ModelImpl) model, createProvideCyber.getTaskImpl(), createUseCyber2.getTaskImpl());
		cs.execute(createDep7);
		DependencyImplCreateCommand createDep8 = new DependencyImplCreateCommand((ModelImpl) model, createUseCyber.getTaskImpl(), createProvideCyber.getTaskImpl());
		cs.execute(createDep8);		
	}

}

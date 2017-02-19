/**
 * 
 */
package edu.toronto.cs.openome.evaluation.testing;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionChangeRecorder;
import org.eclipse.gmf.runtime.emf.core.resources.GMFResource;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.junit.Before;
import org.junit.Test;

import edu.toronto.cs.openome.evaluation.SATSolver.DualHashMap;
import edu.toronto.cs.openome.evaluation.SATSolver.ModeltoAxiomsConverter;
import edu.toronto.cs.openome.testing.ActorImplCreateCommand;
import edu.toronto.cs.openome.testing.AndDecompositionImplCreateCommand;
import edu.toronto.cs.openome.testing.BreakImplCreateCommand;
import edu.toronto.cs.openome.testing.DependencyImplCreateCommand;
import edu.toronto.cs.openome.testing.GoalImplCreateCommand;
import edu.toronto.cs.openome.testing.HelpImplCreateCommand;
import edu.toronto.cs.openome.testing.HurtImplCreateCommand;
import edu.toronto.cs.openome.testing.MakeImplCreateCommand;
import edu.toronto.cs.openome.testing.OrDecompositionImplCreateCommand;
import edu.toronto.cs.openome.testing.ResourceImplCreateCommand;
import edu.toronto.cs.openome.testing.SoftgoalImplCreateCommand;
import edu.toronto.cs.openome.testing.SomeMinusImplCreateCommand;
import edu.toronto.cs.openome.testing.SomePlusImplCreateCommand;
import edu.toronto.cs.openome.testing.TaskImplCreateCommand;
import edu.toronto.cs.openome.testing.UnknownImplCreateCommand;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Model;
import edu.toronto.cs.openome_model.openome_modelFactory;
import edu.toronto.cs.openome_model.openome_modelPackage;
import edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorUtil;
import edu.toronto.cs.openome_model.impl.GoalImpl;
import edu.toronto.cs.openome_model.impl.ModelImpl;
import edu.toronto.cs.openome_model.impl.ResourceImpl;


/**
 * @author jenhork
 *
 */
public class BackwardEvaluationTest {
	
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
		
		//initializeModel();
		
//		ResourceSet resourceSet = new ResourceSetImpl();
//		File file = new File("C:/OpenOMEExamples/KHPExample.oom");
//		URI uri = URI.createFileURI(file.getAbsolutePath());
//		
//		openome_modelPackage e = openome_modelPackage.eINSTANCE;
//		openome_modelFactory f = e.getopenome_modelFactory();
//		
//		Resource resource = resourceSet.createResource(uri); //uri determines where file is stored
//		for (Object o: resource.getContents()) {
//			System.out.println(o.toString());
//		}
//		System.out.println(resource.toString());
//		Model m = f.createModel();
//		
//		XMIResourceImpl xmires = null;
//		GMFResource gr = null;
//		
//		for(Resource tmp: resourceSet.getResources()) {
//			System.out.println(tmp.toString());
//			if (tmp instanceof XMIResourceImpl) {
//				xmires = (XMIResourceImpl) tmp;
//			}
//			
//		}
//		
//			
//		ModelImpl model = null;
//						
//		for(EObject tmp2: xmires.getContents()){ 
//			System.out.println(tmp2.toString());
//			if (tmp2 instanceof ModelImpl) 
//				model = (ModelImpl) tmp2; 
//		}
	}

	//@Before
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
	
	public void initializeAndDecompositionModel2(){
		assertTrue(model != null);
		
		assertTrue(model.getContainers().isEmpty());
		
		GoalImplCreateCommand create = new GoalImplCreateCommand((ModelImpl) model, "a");
		cs.execute(create);
		TaskImplCreateCommand create2 = new TaskImplCreateCommand((ModelImpl) model, "b");
		cs.execute(create2);
		TaskImplCreateCommand create3 = new TaskImplCreateCommand((ModelImpl) model, "c");
		cs.execute(create3);
		TaskImplCreateCommand create5 = new TaskImplCreateCommand((ModelImpl) model, "d");
		cs.execute(create5);
		Command create4 = new AndDecompositionImplCreateCommand((ModelImpl) model, create2.getTaskImpl(), create.getGoalImpl());
		cs.execute(create4);
		create4 = new AndDecompositionImplCreateCommand((ModelImpl) model, create3.getTaskImpl(), create.getGoalImpl());
		cs.execute(create4);
		create4 = new AndDecompositionImplCreateCommand((ModelImpl) model, create5.getTaskImpl(), create.getGoalImpl());
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
			if (intention.getName().equals("d"))
				intIndex.put(new Integer(19), intention);
		}		
				
	}
	
	public void initializeDependencyModel(){
		assertTrue(model != null);
		
		assertTrue(model.getContainers().isEmpty());
		
		GoalImplCreateCommand create = new GoalImplCreateCommand((ModelImpl) model, "a");
		cs.execute(create);
		GoalImpl goal = create.getGoalImpl();
		ResourceImplCreateCommand create2 = new ResourceImplCreateCommand((ModelImpl) model, "b");
		cs.execute(create2);
		ResourceImpl resource = create2.getResourceImpl();
		DependencyImplCreateCommand create3 = new DependencyImplCreateCommand((ModelImpl) model, resource, goal);
		cs.execute(create3);
		
		ModeltoAxiomsConverter converter = new ModeltoAxiomsConverter((ModelImpl) model, cs);
		
		DualHashMap<Integer, Intention> intIndex = converter.getIntentionIndex();
		
		for (Intention intention: model.getIntentions())  {
			Integer i = (Integer) intIndex.getInverse(intention);
			intIndex.remove(i);
			if (intention.getName().equals("a")) 
				intIndex.put(new Integer(1), intention);
			if (intention.getName().equals("b"))
				intIndex.put(new Integer(7), intention);
		}
				
	}
	
	public void initializeOrDecompositionModel(){
		assertTrue(model != null);
		
		assertTrue(model.getContainers().isEmpty());
		
		GoalImplCreateCommand create = new GoalImplCreateCommand((ModelImpl) model, "a");
		cs.execute(create);
		TaskImplCreateCommand create2 = new TaskImplCreateCommand((ModelImpl) model, "b");
		cs.execute(create2);
		TaskImplCreateCommand create3 = new TaskImplCreateCommand((ModelImpl) model, "c");
		cs.execute(create3);
		Command create4 = new OrDecompositionImplCreateCommand((ModelImpl) model, create2.getTaskImpl(), create.getGoalImpl());
		cs.execute(create4);
		create4 = new OrDecompositionImplCreateCommand((ModelImpl) model, create3.getTaskImpl(), create.getGoalImpl());
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
	
	public void initializeOrDecompositionModel2(){
		assertTrue(model != null);
		
		assertTrue(model.getContainers().isEmpty());
		
		GoalImplCreateCommand create = new GoalImplCreateCommand((ModelImpl) model, "a");
		cs.execute(create);
		TaskImplCreateCommand create2 = new TaskImplCreateCommand((ModelImpl) model, "b");
		cs.execute(create2);
		TaskImplCreateCommand create3 = new TaskImplCreateCommand((ModelImpl) model, "c");
		cs.execute(create3);
		TaskImplCreateCommand create5 = new TaskImplCreateCommand((ModelImpl) model, "d");
		cs.execute(create5);
		Command create4 = new OrDecompositionImplCreateCommand((ModelImpl) model, create2.getTaskImpl(), create.getGoalImpl());
		cs.execute(create4);
		create4 = new OrDecompositionImplCreateCommand((ModelImpl) model, create3.getTaskImpl(), create.getGoalImpl());
		cs.execute(create4);
		create4 = new OrDecompositionImplCreateCommand((ModelImpl) model, create5.getTaskImpl(), create.getGoalImpl());
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
			if (intention.getName().equals("d"))
				intIndex.put(new Integer(19), intention);
		}		
				
	}
	
	public void initializeContributionModel(){
		assertTrue(model != null);
		
		assertTrue(model.getContainers().isEmpty());
		
		SoftgoalImplCreateCommand create = new SoftgoalImplCreateCommand((ModelImpl) model, "a");
		cs.execute(create);
		SoftgoalImplCreateCommand create2 = new SoftgoalImplCreateCommand((ModelImpl) model, "b");
		cs.execute(create2);
		Command createCont;
		createCont = new MakeImplCreateCommand((ModelImpl) model, create2.getSoftgoalImpl(), create.getSoftgoalImpl());
		cs.execute(createCont);
		
		createCont = new HelpImplCreateCommand((ModelImpl) model, create2.getSoftgoalImpl(), create.getSoftgoalImpl());
		cs.execute(createCont);
		
		createCont = new SomePlusImplCreateCommand((ModelImpl) model, create2.getSoftgoalImpl(), create.getSoftgoalImpl());
		cs.execute(createCont);
		
		createCont = new UnknownImplCreateCommand((ModelImpl) model, create2.getSoftgoalImpl(), create.getSoftgoalImpl());
		cs.execute(createCont);
		
		createCont = new SomeMinusImplCreateCommand((ModelImpl) model, create2.getSoftgoalImpl(), create.getSoftgoalImpl());
		cs.execute(createCont);
		
		createCont = new HurtImplCreateCommand((ModelImpl) model, create2.getSoftgoalImpl(), create.getSoftgoalImpl());
		cs.execute(createCont);
		
		createCont = new BreakImplCreateCommand((ModelImpl) model, create2.getSoftgoalImpl(), create.getSoftgoalImpl());
		cs.execute(createCont);
		
		ModeltoAxiomsConverter converter = new ModeltoAxiomsConverter((ModelImpl) model, cs);
		
		DualHashMap<Integer, Intention> intIndex = converter.getIntentionIndex();
		
		for (Intention intention: model.getIntentions())  {
			Integer i = (Integer) intIndex.getInverse(intention);
			intIndex.remove(i);
			if (intention.getName().equals("a")) 
				intIndex.put(new Integer(1), intention);
			if (intention.getName().equals("b"))
				intIndex.put(new Integer(7), intention);
			
		}		
				
	}
	
	public void initializeContributionModel2(){
		assertTrue(model != null);
		
		assertTrue(model.getContainers().isEmpty());
		
		SoftgoalImplCreateCommand create = new SoftgoalImplCreateCommand((ModelImpl) model, "a");
		cs.execute(create);
		SoftgoalImplCreateCommand create2 = new SoftgoalImplCreateCommand((ModelImpl) model, "b");
		cs.execute(create2);
		Command createCont;
		createCont = new MakeImplCreateCommand((ModelImpl) model, create2.getSoftgoalImpl(), create.getSoftgoalImpl());
		cs.execute(createCont);
		SoftgoalImplCreateCommand create3 = new SoftgoalImplCreateCommand((ModelImpl) model, "c");
		cs.execute(create3);
		createCont = new HelpImplCreateCommand((ModelImpl) model, create3.getSoftgoalImpl(), create.getSoftgoalImpl());
		cs.execute(createCont);		
		SoftgoalImplCreateCommand create4 = new SoftgoalImplCreateCommand((ModelImpl) model, "d");
		cs.execute(create4);
		createCont = new SomePlusImplCreateCommand((ModelImpl) model, create4.getSoftgoalImpl(), create.getSoftgoalImpl());
		cs.execute(createCont);
		SoftgoalImplCreateCommand create5 = new SoftgoalImplCreateCommand((ModelImpl) model, "e");
		cs.execute(create5);
		createCont = new UnknownImplCreateCommand((ModelImpl) model, create5.getSoftgoalImpl(), create.getSoftgoalImpl());
		cs.execute(createCont);
		SoftgoalImplCreateCommand create6 = new SoftgoalImplCreateCommand((ModelImpl) model, "f");
		cs.execute(create6);
		createCont = new SomeMinusImplCreateCommand((ModelImpl) model, create6.getSoftgoalImpl(), create.getSoftgoalImpl());
		cs.execute(createCont);
		SoftgoalImplCreateCommand create7 = new SoftgoalImplCreateCommand((ModelImpl) model, "g");
		cs.execute(create7);
		createCont = new HurtImplCreateCommand((ModelImpl) model, create7.getSoftgoalImpl(), create.getSoftgoalImpl());
		cs.execute(createCont);
		SoftgoalImplCreateCommand create8 = new SoftgoalImplCreateCommand((ModelImpl) model, "h");
		cs.execute(create8);
		createCont = new BreakImplCreateCommand((ModelImpl) model, create8.getSoftgoalImpl(), create.getSoftgoalImpl());
		cs.execute(createCont);
		
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
			if (intention.getName().equals("d"))
				intIndex.put(new Integer(19), intention);
			if (intention.getName().equals("e")) 
				intIndex.put(new Integer(25), intention);
			if (intention.getName().equals("f"))
				intIndex.put(new Integer(31), intention);
			if (intention.getName().equals("g")) 
				intIndex.put(new Integer(37), intention);
			if (intention.getName().equals("h"))
				intIndex.put(new Integer(43), intention);
		}		
				
	}

}

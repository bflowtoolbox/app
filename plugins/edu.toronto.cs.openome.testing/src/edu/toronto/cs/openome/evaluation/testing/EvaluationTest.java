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

import edu.toronto.cs.openome.testing.ActorImplCreateCommand;
import edu.toronto.cs.openome.testing.HelpImplCreateCommand;
import edu.toronto.cs.openome.testing.SoftgoalImplCreateCommand;
import edu.toronto.cs.openome_model.Model;
import edu.toronto.cs.openome_model.openome_modelFactory;
import edu.toronto.cs.openome_model.openome_modelPackage;
import edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorUtil;
import edu.toronto.cs.openome_model.impl.ModelImpl;


/**
 * @author jenhork
 *
 */
public class EvaluationTest {
	
	protected static Diagram diagram;
	protected static edu.toronto.cs.openome_model.Model model;
	protected static TransactionalEditingDomain domain;
	protected static CommandStack cs;
	protected static String testfile = "";
	//protected static String testfileood = "file:///C:/OpenOMEExamples/KHPExample.ood";
	//protected static String testfileoom = "file:///C:/OpenOMEExamples/KHPExample.oom";
	
	
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
		
		initializeModel();
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
	public void initializeModel(){
		assertTrue(model != null);
		
		assertTrue(model.getContainers().isEmpty());
		
		SoftgoalImplCreateCommand create1 = new SoftgoalImplCreateCommand((ModelImpl) model, "Softgoal a");
		cs.execute(create1);
		SoftgoalImplCreateCommand create2 = new SoftgoalImplCreateCommand((ModelImpl) model, "Softgoal b");
		cs.execute(create2);
		SoftgoalImplCreateCommand create3 = new SoftgoalImplCreateCommand((ModelImpl) model, "Softgoal c");
		cs.execute(create3);
		SoftgoalImplCreateCommand create4 = new SoftgoalImplCreateCommand((ModelImpl) model, "Softgoal d");
		cs.execute(create4);
		Command create = new SoftgoalImplCreateCommand((ModelImpl) model, "Softgoal e");
		cs.execute(create);
		create = new SoftgoalImplCreateCommand((ModelImpl) model, "Softgoal f");
		cs.execute(create);
		Command createHelp1 = new HelpImplCreateCommand((ModelImpl) model, create2.getSoftgoalImpl(), create1.getSoftgoalImpl());
		cs.execute(createHelp1);
		Command createHelp2 = new HelpImplCreateCommand((ModelImpl) model, create3.getSoftgoalImpl(), create1.getSoftgoalImpl());
		cs.execute(createHelp2);
		Command createHelp3 = new HelpImplCreateCommand((ModelImpl) model, create2.getSoftgoalImpl(), create4.getSoftgoalImpl());
		cs.execute(createHelp3);
		Command createHelp4 = new HelpImplCreateCommand((ModelImpl) model, create3.getSoftgoalImpl(), create4.getSoftgoalImpl());
		cs.execute(createHelp4);
	}

}

/**
 * 
 */
package edu.toronto.cs.openome.conversion.codegen;

import java.io.StringBufferInputStream;

import org.eclipse.emf.common.util.URI;

import model.IStarElement;

import parser.Q7;
import util.Computing;
import edu.toronto.cs.openome_model.Container;
import edu.toronto.cs.openome_model.Model;
import edu.toronto.cs.openome_model.Task;
import edu.toronto.cs.openome_model.openome_modelFactory;
import edu.toronto.cs.openome_model.openome_modelPackage;
import junit.framework.TestCase;

/**
 * @author yy66
 *
 */
public class GoalModelTest extends TestCase {
	protected void setUp() throws Exception {
		super.setUp();
	}
	@SuppressWarnings("deprecation")
	private GoalModel parseQ7(String q7) {
		Q7.main(new StringBufferInputStream(q7));
		GoalModel cg = new GoalModel(Q7.a);
		cg.generateGoalModelContent();
		return cg;
	}
	openome_modelPackage e = openome_modelPackage.eINSTANCE;
	openome_modelFactory f = e.getopenome_modelFactory();
	private Model loadQ7(String q7) {
		GoalModel cg = parseQ7(q7);
		Model m = f.createModel();
		m.setName("loadQ7test");
		cg.generateGoalModel(m);
		return m;
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	public void testStrip_quote() {
		GoalModel cg = parseQ7("\"Do a\"");
//		System.out.println(cg.goals.values());
		IStarElement g = cg.get_goal(null, "\"Do a\"", null);
		assertNotNull(g);
		assertEquals(g.name, "\"Do a\"");
		assertEquals("IStarTaskElement", g.getType("Do a"));
		assertEquals(true, g.isTask);		
		Model m = loadQ7("\"Do a\"");		
		assertEquals(1, m.getIntentions().size());
		assertEquals(true, m.getIntentions().get(0) instanceof Task);		
	}


	/**
	 * Test method for {@link edu.toronto.cs.openome.conversion.codegen.GoalModel#create_agent_for_new_who(model.Advice, model.Effect)}.
	 */
	public void testCreate_agent_for_new_who() {
		GoalModel cg = parseQ7("<A>::a");		
		assertEquals(1, cg.advices.size());
		assertEquals(1, cg.agents.size());
		assertEquals(1, cg.intentions.size());
		Model m = loadQ7("<A>::a");		
		assertEquals(0, m.getIntentions().size());
		assertEquals(1, m.getContainers().size());
	}

	/**
	 * Test method for {@link edu.toronto.cs.openome.conversion.codegen.GoalModel#create_aspect()}.
	 */
	public void testCreate_aspect() {
		if (Computing.propertyHolds("q7.codegen.istar.create_aspects")) {
//			System.out.println("Testing aspect");
			String advice0 = "<A>::a => + b";
			GoalModel cg = parseQ7(advice0);		
			assertEquals(1, cg.advices.size());
			assertEquals(2, cg.intentions.size());
			assertEquals(2, cg.agents.size());
			assertEquals(1, cg.links.size());
			Model m = loadQ7(advice0);		
			assertEquals(0, m.getIntentions().size());
			assertEquals(2, m.getContainers().size());
			assertEquals(1, count_aspect(m));
			String advice1 = "<HealthWatcher>::\"Manage [health service]\" " +
			 		"=> " +
			 		"+ Performance [system]," +
			 		"+ Usability [system]," +
			 		"+ \"Handling [Error and Exception]\"," +
			 		"++ Persistence, " +
			 		"+ Security";
			String advice2 = 
			  "Persistence { AND " + 
				"Persistence [DB] { OR "+
				   "\"Do Persistence [Microsoft Access]\" " +
				   "\"Do Persistence [Oracle]\"" +
				   "\"Do Persistence [MySQL]\"" + 
				"}" + 
			  "}";
			cg = parseQ7(advice1 + " " + advice2);
			assertEquals(2, cg.advices.size());
			assertEquals(10, cg.intentions.size());
			assertEquals(6, cg.agents.size());
			assertEquals(9, cg.links.size());
			m = loadQ7(advice1 + " " + advice2);
			assertEquals(0, m.getIntentions().size());
			assertEquals(6, m.getContainers().size());
			assertEquals(5, count_aspect(m));
			String advice3 = 
			   "\"Handling [Error and Exception]\"{ AND " +
				"\"Do Detect [exception]\" " + 
				"\"Do Apply [handler]\"" +
			   "}";	
			cg = parseQ7(advice1 + " " + advice2 + " " + advice3);
			assertEquals(3, cg.advices.size());
			assertEquals(12, cg.intentions.size());
			assertEquals(6, cg.agents.size());
			assertEquals(11, cg.links.size());
			m = loadQ7(advice1 + " " + advice2 + " " + advice3);
			assertEquals(0, m.getIntentions().size());
			assertEquals(6, m.getContainers().size());
			assertEquals(5, count_aspect(m));
			
		}
	}

	public void testDo_weave() {
		if (Computing.propertyHolds("q7.codegen.istar.create_aspects")
				&& Computing.propertyHolds("q7.codegen.istar.do_weave")) {
			String advice0 = "<A>::a { AND a [system] a [environment] }" +
					"b <= ++ * [system] { AND c }";
			GoalModel cg = parseQ7(advice0);		
			assertEquals(2, cg.advices.size());
			assertEquals(5, cg.intentions.size());
			assertEquals(2, cg.agents.size());
			assertEquals(4, cg.links.size());
			Model m = loadQ7(advice0);		
			assertEquals(0, m.getIntentions().size());
			assertEquals(2, m.getContainers().size());
			assertEquals(1, count_aspect(m));
		}	
	}
	private int count_aspect(Model m) {
		int n = 0;
		for (Object o: m.getContainers()) {
			Container a = (Container) o; 
			if (a.getName().startsWith("Aspect ")) {
				n ++;
			}
		}
		return n;
	}
	public void testFetchURI() {
		URI uri = GoalModel.fetchURI("C:\\work\\project\\ome\\runtime-openome.product\\Examples\\q7\\aspects\\t.oom"); 		
	}
}

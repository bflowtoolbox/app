package edu.toronto.cs.openome.swtbottesting;

import static org.junit.Assert.*;

import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.toronto.cs.openome_model.Association;
import edu.toronto.cs.openome_model.Contribution;
import edu.toronto.cs.openome_model.Decomposition;
import edu.toronto.cs.openome_model.Dependency;
import edu.toronto.cs.openome_model.Model;
import edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart;
import edu.toronto.cs.openome_model.impl.ActorImpl;
import edu.toronto.cs.openome_model.impl.AgentImpl;
import edu.toronto.cs.openome_model.impl.GoalImpl;
import edu.toronto.cs.openome_model.impl.PositionImpl;
import edu.toronto.cs.openome_model.impl.ResourceImpl;
import edu.toronto.cs.openome_model.impl.RoleImpl;
import edu.toronto.cs.openome_model.impl.SoftgoalImpl;
import edu.toronto.cs.openome_model.impl.TaskImpl;

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestAddSWT{
	
	private static SWTBotGefEditor editor;
	
	@BeforeClass
    public static void setUpBeforeClass() throws Exception {
        TestUtil.initializeWorkspace();
    }

    @Before
    public void setUpBeforeTest() throws Exception {
        TestUtil.createAndOpenFile();
        editor = new SWTGefBot().gefEditor("test.ood");
        TestUtil.bot.editorByTitle(TestUtil.diagramName).setFocus();
		TestUtil.bot.menu("Window").menu("Navigation").menu("Maximize Active View or Editor").click();
    }

    @After
    public void tearDownAfterTest() throws Exception {
        TestUtil.closeAndDeleteFile();
    }
    
	@Test
	public void TestcanAddActor() throws Exception {
		try {
			Model model = TestUtil.getModel(editor);
			String name = "TestActor";
			// Draw an actor
			assertTrue("Model is empty", model.getContainers().isEmpty());
			editor.activateTool("Actor");
			editor.click(0, 0);
			editor.directEditType(name);
			SWTBotGefEditPart actor = editor.getEditPart(name);
			
			assertTrue("EditPart instance of ActorEditPart", actor.part().getParent() instanceof ActorEditPart);
			assertTrue("Impl instance of ActorImpl", model.getContainers().get(0) instanceof ActorImpl);
			
            editor.clickContextMenu("Delete from Model");
		} catch (WidgetNotFoundException e) {
			fail("Could not add a new actor.");
		}
	}
	
	@AfterClass
	public static void afterClass() {
		editor.clear();
	}
	
	@Test
	public void TestcanAddAgent() throws Exception {
		try {
			Model model = TestUtil.getModel(editor);
			String name = "TestAgent";
			// Draw an actor
			assertTrue("Model is empty", model.getContainers().isEmpty());
			editor.activateTool("Agent");
			editor.click(0, 0);
			editor.directEditType(name);
			SWTBotGefEditPart agent = editor.getEditPart(name);
			
			assertTrue("EditPart instance of AgentEditPart", agent.part().getParent() instanceof AgentEditPart);
			assertTrue("Impl instance of AgentImpl", model.getContainers().get(0) instanceof AgentImpl);
            editor.clickContextMenu("Delete from Model");
		} catch (WidgetNotFoundException e) {
			fail("Could not add a new actor.");
		}
	}
	
	@Test
	public void TestcanAddRole() throws Exception {
		try {
			Model model = TestUtil.getModel(editor);
			String name = "TestRole";
			// Draw an actor
			assertTrue("Model is empty", model.getContainers().isEmpty());
			editor.activateTool("Role");
			editor.click(0, 0);
			editor.directEditType(name);
			SWTBotGefEditPart role = editor.getEditPart(name);
			
			assertTrue("EditPart instance of RoleEditPart", role.part().getParent() instanceof RoleEditPart);
			assertTrue("Impl instance of RoleImpl", model.getContainers().get(0) instanceof RoleImpl);
            editor.clickContextMenu("Delete from Model");
		} catch (WidgetNotFoundException e) {
			fail("Could not add a new actor.");
		}
	}
	
	@Test
	public void TestcanAddPosition() throws Exception {
		try {
			Model model = TestUtil.getModel(editor);
			String name = "TestPosition";
			// Draw an actor
			assertTrue("Model is empty", model.getContainers().isEmpty());
			editor.activateTool("Position");
			editor.click(0, 0);
			editor.directEditType(name);
			SWTBotGefEditPart position = editor.getEditPart(name);
			
			assertTrue("EditPart instance of PositionEditPart", position.part().getParent() instanceof PositionEditPart);
			assertTrue("Impl instance of PositionImpl", model.getContainers().get(0) instanceof PositionImpl);
			
            editor.clickContextMenu("Delete from Model");
		} catch (WidgetNotFoundException e) {
			fail("Could not adda a new position.");
		}
	}
	
	
	@Test
	public void TestcanAddHardgoal() throws Exception {
		try {
			Model model = TestUtil.getModel(editor);
			String name = "TestHardgoal";
			// Draw an actor
			assertTrue("Model is empty", model.getContainers().isEmpty());
			editor.activateTool("Hardgoal");
			editor.click(0, 0);
			editor.directEditType(name);
			SWTBotGefEditPart hardgoal = editor.getEditPart(name);
			
			assertTrue("EditPart instance of GoalEditPart", hardgoal.part().getParent() instanceof GoalEditPart);
			assertTrue("Impl instance of GoalImpl", model.getIntentions().get(0) instanceof GoalImpl);
			
            editor.clickContextMenu("Delete from Model");
		} catch (WidgetNotFoundException e) {
			fail("Could not adda a new Hardgoal.");
		}
	}
	
	@Test
	public void TestcanAddSoftgoal() throws Exception {
		try {
			Model model = TestUtil.getModel(editor);
			String name = "TestSoftgoal";
			// Draw an actor
			assertTrue("Model is empty", model.getContainers().isEmpty());
			editor.activateTool("Softgoal");
			editor.click(0, 0);
			editor.directEditType(name);
			SWTBotGefEditPart softgoal = editor.getEditPart(name);
			
			assertTrue("EditPart instance of SoftgoalEditPart", softgoal.part().getParent() instanceof SoftgoalEditPart);
			assertTrue("Impl instance of SoftgoalImpl", model.getIntentions().get(0) instanceof SoftgoalImpl);

            editor.clickContextMenu("Delete from Model");
		} catch (WidgetNotFoundException e) {
			fail("Could not adda a new Softgoal.");
		}
	}
	
	@Test
	public void TestcanAddTask() throws Exception {
		try {
			Model model = TestUtil.getModel(editor);
			String name = "TestTask";
			// Draw an actor
			assertTrue("Model is empty", model.getContainers().isEmpty());
			editor.activateTool("Task");
			editor.click(0, 0);
			editor.directEditType(name);
			SWTBotGefEditPart task = editor.getEditPart(name);

			assertTrue("EditPart instance of TaskEditPart", task.part().getParent() instanceof TaskEditPart);
			assertTrue("Impl instance of TaskImpl", model.getIntentions().get(0) instanceof TaskImpl);
			
            editor.clickContextMenu("Delete from Model");
		} catch (WidgetNotFoundException e) {
			fail("Could not adda a new Task.");
		}
	}
	
	@Test
	public void TestcanAddResource() throws Exception {
		try {
			Model model = TestUtil.getModel(editor);
			String name = "TestResource";
			// Draw an actor
			assertTrue("Model is empty", model.getContainers().isEmpty());
			editor.activateTool("Resource");
			editor.click(0, 0);
			editor.directEditType(name);
			SWTBotGefEditPart resource = editor.getEditPart(name);
			
			assertTrue("EditPart instance of ResourceEditPart", resource.part().getParent() instanceof ResourceEditPart);
			assertTrue("Impl instance of ResourceImpl", model.getIntentions().get(0) instanceof ResourceImpl);
			
            editor.clickContextMenu("Delete from Model");
		} catch (WidgetNotFoundException e) {
			fail("Could not adda a new Resource.");
		}
	}
	
	@Test
	public void TestcanAddHardLinks() throws Exception {
		String l = "";
		try {
			Model model = TestUtil.getModel(editor);
			String name = "TestAddHardLinks";
			// Draw an actor
			assertTrue("Model is empty", model.getContainers().isEmpty());
			editor.activateTool("Resource");
			editor.click(0, 0);
			editor.directEditType(name);
			SWTBotGefEditPart resource = editor.getEditPart(name);
			
			for (String link : TestUtil.hardlinks){
				l = link;
				//assertTrue(("Resource has no Hardlinks"), model.getDecompositions().isEmpty());
				editor.activateTool(link);
				editor.click(5,5);
				Thread.sleep(500);
				if (link.equals("Decomposition")){
					assertFalse(("Resource has instance of " + l), model.getDecompositions().isEmpty());
					assertTrue("", ((ResourceEditPart) resource.part().getParent()).getSourceConnections().get(0) instanceof AndDecompositionEditPart);
					assertTrue("Link is instance of Decomposition", model.getDecompositions().get(0) instanceof Decomposition);
				}else if (link.equals("Dependency")){
					assertFalse(("Resource has an instance of " + l), model.getDependencies().isEmpty());
					assertTrue("", ((ResourceEditPart) resource.part().getParent()).getSourceConnections().get(0) instanceof DependencyEditPart);
					assertTrue("Link is instance of Decomposition", model.getDependencies().get(0) instanceof Dependency);
				}else if (link.equals("Means-ends")){
					assertFalse(("Resource has an instance of " + l), model.getDecompositions().isEmpty());
					assertTrue("", ((ResourceEditPart) resource.part().getParent()).getSourceConnections().get(0) instanceof OrDecompositionEditPart);
					assertTrue("Link is instance of Decomposition", model.getDecompositions().get(0) instanceof Decomposition);
				}
				editor.clickContextMenu("Delete from Model");
			}
			editor.click("TestAddHardLinks");
            editor.clickContextMenu("Delete from Model");
		} catch (WidgetNotFoundException e) {
			fail(l + " could not be added");
		}
	}
	
	@Test
	public void TestcanAddContributions() throws Exception {
		String l = "";
		try {
			Model model = TestUtil.getModel(editor);
			String name = "TestAddContribution";
			// Draw an actor
			assertTrue("Model is empty", model.getContainers().isEmpty());
			editor.activateTool("Resource");
			editor.click(0, 0);
			editor.directEditType(name);
			SWTBotGefEditPart resource = editor.getEditPart(name);
			
			for (String link : TestUtil.contributions){
				assertTrue(("Resource has an instance of " + l), model.getContributions().isEmpty());
				l = link;
				editor.activateTool(link);
				editor.click(5,5);
				Thread.sleep(500);
				assertFalse(("Resource has no instance of a contribution"), model.getContributions().isEmpty());
				assertTrue("Link is not instance of " + link, model.getContributions().get(0) instanceof Contribution);
				assertTrue("", ((ResourceEditPart) resource.part().getParent()).getSourceConnections().get(0) instanceof ConnectionEditPart);
				editor.clickContextMenu("Delete from Model");
			}
			editor.click("TestAddContribution");
            editor.clickContextMenu("Delete from Model");
		} catch (WidgetNotFoundException e) {
			fail(l + " could not be added");
		}
	}
	
	@Test
	public void TestcanAddAssociations() throws Exception {
		String l = "";
		try {
			Model model = TestUtil.getModel(editor);
			String name = "TestAddAssociation";
			// Draw an actor
			assertTrue("Model is empty", model.getContainers().isEmpty());
			editor.activateTool("Actor");
			editor.click(0, 0);
			editor.directEditType(name);
			SWTBotGefEditPart actor = editor.getEditPart(name);
			
			for (String link : TestUtil.associations){
				assertTrue(("Resource has an instance of an association"), model.getAssociations().isEmpty());
				l = link;
				editor.activateTool(link);
				editor.click(5,5);
				Thread.sleep(500);
				assertFalse(("Resource has no instance of an association"), model.getAssociations().isEmpty());
				assertTrue("Link is not instance of " + link, model.getAssociations().get(0) instanceof Association);
				assertTrue("", ((ActorEditPart) actor.part().getParent()).getSourceConnections().get(0) instanceof ConnectionEditPart);
				editor.clickContextMenu("Delete from Model");
			}
			editor.click("TestAddAssociation");
            editor.clickContextMenu("Delete from Model");
		} catch (WidgetNotFoundException e) {
			fail(l + " could not be added");
		}
	}
	
	@Test
	public void TestcannotAddLink() throws Exception {
		try {
			Model model = TestUtil.getModel(editor);
			
			for (String link : TestUtil.associations){
				assertTrue(("Model has an instance of a link"), model.getAssociations().isEmpty());
				editor.activateTool(link);
				editor.click(5,5);
				Thread.sleep(500);
				assertTrue(("Model has an instance of an association"), model.getAssociations().isEmpty());
			}
			
			for (String link : TestUtil.contributions){
				assertTrue("Model has an instance of a link", model.getDecompositions().isEmpty());
				editor.activateTool(link);
				editor.click(5,5);
				Thread.sleep(500);
				assertTrue(("Model has an instance of a contribution"), model.getContributions().isEmpty());
			}
			
			for (String link : TestUtil.hardlinks){
				assertTrue("Model has an instance of a link", model.getDecompositions().isEmpty());
				editor.activateTool(link);
				editor.click(5,5);
				Thread.sleep(500);
				assertTrue("Model has an instance of a HardLink", model.getDecompositions().isEmpty());
				}
		} catch (WidgetNotFoundException e) {
			fail("Something happened");
		}
	}
}
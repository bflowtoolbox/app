package edu.toronto.cs.openome.swtbottesting;

import static org.junit.Assert.*;

import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.keyboard.KeyboardFactory;
import org.eclipse.ui.keys.KeyStroke;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import edu.toronto.cs.openome_model.Model;

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestChangeText {
	
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
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void changeTextDoubleClick() throws Exception{
		Model model = TestUtil.getModel(editor);
		String name = "TestDoubleNameChange";
		String change = "C";
		// Draw an actor
		assertTrue("Model is empty", model.getContainers().isEmpty());
		
		for (String intent : TestUtil.intentions){
			changeTextDouble(intent, name, change);
			assertTrue("Intention: " + intent +"'s name does not match changed name", model.getIntentions().get(0).getName().equals(change));
			editor.click(change);
			editor.clickContextMenu("Delete from Model");
		}
		
		for (String actor : TestUtil.actors){
			changeTextDouble(actor, name, change);
			assertTrue("Container: " + actor + "'s name does not match changed name", model.getContainers().get(0).getName().equals(change));
			editor.click(change);
			editor.clickContextMenu("Delete from Model");
		}
	}
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void changeTextSingleClick() throws Exception{
		Model model = TestUtil.getModel(editor);
		String name = "TestSingleNameChange";
		String change = "C";
		// Draw an actor
		assertTrue("Model is empty", model.getContainers().isEmpty());
		
		for (String intent : TestUtil.intentions){
			changeTextSingle(intent, name, change);
			assertFalse("Model has no intentions", model.getIntentions().isEmpty());
			assertTrue("Intention: " + intent +"'s name does not match changed name", model.getIntentions().get(0).getName().equals(change));
			editor.click(change);
			editor.clickContextMenu("Delete from Model");
		}
		
		for (String actor : TestUtil.actors){
			changeTextSingle(actor, name, change);
			assertFalse("Model has no actors", model.getContainers().isEmpty());
			assertTrue("Container: " + actor + "'s name does not match changed name", model.getContainers().get(0).getName().equals(change));
			editor.click(change);
			editor.clickContextMenu("Delete from Model");
		}
	}
	/**
	 * 
	 * @param actor
	 * @param name
	 * @param change
	 * @throws InterruptedException
	 */
	private void changeTextSingle(String actor, String name, String change) throws InterruptedException{
		editor.activateTool(actor);
		editor.click(0, 0);
		editor.directEditType(name);
		
		editor.click(name);
		Thread.sleep(500);
		//This test will not pass if your keyboard language is not set to English (US)
		KeyboardFactory.getSWTKeyboard().typeText(change);
		Thread.sleep(500);
		editor.click(1000, 1000);
		Thread.sleep(500);
	}
	
	private void changeTextDouble(String actor, String name, String change){
		editor.activateTool(actor);
		editor.click(0, 0);
		editor.directEditType(name);
		
		editor.doubleClick(name);
		editor.directEditType(change);
		editor.click(1000, 1000);
	}
	
	@AfterClass
	public static void afterClass() {
		editor.clear();
	}
}

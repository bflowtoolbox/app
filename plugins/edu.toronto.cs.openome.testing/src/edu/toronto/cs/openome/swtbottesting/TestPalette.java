package edu.toronto.cs.openome.swtbottesting;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestPalette extends SWTBotGefTestCase {
 
	private static SWTWorkbenchBot bot;
	private static SWTGefBot gefBot;
	private SWTBotGefEditor editor;
 
	
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
 
	//Some simple tests to ensure that all of the tools are appearing in the palette
 
	@Test
	public void testPaletteToolNamesActorsIntentions() throws Exception {
		try {
			//Actors
			editor.activateTool("Actor");
			editor.activateTool("Agent");
			editor.activateTool("Position");
			editor.activateTool("Role");
			
			//Intentions
			editor.activateTool("Hardgoal");
			editor.activateTool("Softgoal");
			editor.activateTool("Task");
			editor.activateTool("Resource");
			
		} catch (WidgetNotFoundException e) {
			fail();
		}
		
	}
	
	
	@Test
	public void testPaletteToolNamesLinksContribs() throws Exception {
		try {
			//Links
			editor.activateTool("Dependency");
			editor.activateTool("Means-ends");
			editor.activateTool("Task");
			editor.activateTool("Resource");
			
			//Contributions
			editor.activateTool("Make");
			editor.activateTool("Some+");
			editor.activateTool("Help");
			editor.activateTool("Unknown");
			editor.activateTool("Hurt");
			editor.activateTool("Means-ends");
			editor.activateTool("Some-");
			editor.activateTool("Break");
			editor.activateTool("AND");
			editor.activateTool("OR");
		} catch (WidgetNotFoundException e) {
			fail();
		}
		
	}
	
	@Test
	public void testPaletteToolNamesAssociations() throws Exception {
		try {
			//Associations
			editor.activateTool("ISA");
			editor.activateTool("Covers");
			editor.activateTool("Is part of");
			editor.activateTool("Occupies");
			editor.activateTool("Plays");
			editor.activateTool("INS");			
		} catch (WidgetNotFoundException e) {
			fail();
		}
		
	}
 
 
	@AfterClass
	public static void sleep() {
		
	}
 
}
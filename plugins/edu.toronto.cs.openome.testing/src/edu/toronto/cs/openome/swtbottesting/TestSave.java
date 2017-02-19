package edu.toronto.cs.openome.swtbottesting;

import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.keyboard.KeyboardFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestSave {
	
	private static SWTBotGefEditor editor;
	
	@BeforeClass
    public static void setUpBeforeClass() throws Exception {
		//System.setProperty("isTest", "true");
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
	public void SaveFile() throws Exception{
		try{
			Boolean flag = false;
			SWTWorkbenchBot bot = TestUtil.bot;
			String name = "TestActor";
			
			// Draw an actor
			editor.activateTool("Actor");
			editor.click(0, 0);
			editor.directEditType(name);
			
			bot.menu("File").menu("Save").click();
			Thread.sleep(500);
			BufferedReader br = getBuffer();
			String strLine;
			while ((strLine = br.readLine()) != null){
				if (strLine.contains("<containers") && strLine.contains("Actor") && strLine.contains("TestActor")){
					flag = true;
				}
			}
			assertTrue("The file does not contain an Actor", flag);
			editor.clickContextMenu("Delete from Model");
			br.close();
		}catch (WidgetNotFoundException e) {
			fail("Could not save file properly");
		}
	}
	
	@Test
	public void SaveFileKey() throws Exception{
		try{
			Boolean flag = false;
			String name = "TestGoal";
			
			editor.activateTool("Hardgoal");
			editor.click(0, 0);
			editor.directEditType(name);
			
			if (System.getProperty("os.name").toLowerCase().contains("mac")) {
	            KeyboardFactory.getAWTKeyboard().pressShortcut(KeyStroke.getInstance(SWT.COMMAND, 0), KeyStroke.getInstance("S"));
			} else {
	            KeyboardFactory.getAWTKeyboard().pressShortcut(KeyStroke.getInstance(SWT.CTRL, 0), KeyStroke.getInstance("S"));
			}
			Thread.sleep(500);
			BufferedReader br = getBuffer();
			String strLine;
			while ((strLine = br.readLine()) != null){
				if (strLine.contains("<intentions") && strLine.contains("Goal") && strLine.contains("TestGoal")){
					flag = true;
				}
			}
			assertTrue("The file does not contain a Goal", flag);
			editor.clickContextMenu("Delete from Model");
			br.close();
		}catch (WidgetNotFoundException e) {
			fail("Could not save file properly");
		}
	}
	
	private BufferedReader getBuffer() throws FileNotFoundException{		
		return new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(TestUtil.workspacePath.replaceAll("Hidden", "")))));
	}
}
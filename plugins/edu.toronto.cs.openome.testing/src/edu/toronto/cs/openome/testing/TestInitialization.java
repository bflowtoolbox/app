package edu.toronto.cs.openome.testing;
 
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.eclipse.swtbot.eclipse.finder.SWTEclipseBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
 
@RunWith(SWTBotJunit4ClassRunner.class)
public class TestInitialization {

 
	private static SWTEclipseBot	bot;
	private String projectName = "MyFirstProject";
	private String diagramName = "MyFirstDiagram.ood";
	private String modelName = "MyFirstDiagram.oom";
 
	@BeforeClass
	public static void beforeClass() throws Exception {
		bot = new SWTEclipseBot();
		try {
		bot.view("Welcome").close();
		} catch (WidgetNotFoundException e) {
			//Do nothing, welcome screen already close
		}
	}
 
 
	@Test
	public void canCreateANewProject() throws Exception {

//		//Create the project
//		bot.menu("File").menu("New").menu("Project...").click();
// 
//		SWTBotShell shell = bot.shell("New Project");
//		shell.activate();
//		
//		bot.tree().select("General").expandNode("General", true).select("Project");
//		
//		bot.button("Next >").click();
// 
//		bot.textWithLabel("Project name:").setText(projectName);
// 
//		bot.button("Finish").click();
//
//		//Get the project
//		SWTBotTreeItem theProject = bot.view("Project Explorer").bot().tree().getTreeItem(projectName);
//		
//		//Check the project
//		assertNotNull(theProject);
//		assert (theProject.getText().equals(projectName));
	}
	
	@Test
	public void canCreateNewDiagram() throws Exception{
		
//		bot.menu("File").menu("New").menu("Other...").click();
//		
//		SWTBotShell shell = bot.shell("New");
//		shell.activate();
//		
//		bot.tree().select("OpenOME").expandNode("OpenOME", true).select("Diagram (ood)");
//		bot.button("Next >").click();
//		
//		bot.tree().select(projectName);
//		bot.textWithLabel("File name:").setText(diagramName);
//		
//		bot.button("Finish").click();
//		
//		//Get the project
//		SWTBotTreeItem theProject = bot.view("Project Explorer").bot().tree().getTreeItem(projectName);
//		
//		//Verify them
//		List<String> projectContent = theProject.expand().getNodes();
//		assertTrue(projectContent.get(0).equals(diagramName));
//		assertTrue(projectContent.get(1).equals(modelName));
//		
	}
 
 
	@AfterClass
	public static void sleep() {
		bot.sleep(1000);
	}
 
}
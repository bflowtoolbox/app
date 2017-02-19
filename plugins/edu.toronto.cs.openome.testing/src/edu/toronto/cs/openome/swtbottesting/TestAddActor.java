package edu.toronto.cs.openome.swtbottesting;

import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.allOf;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.widgetOfType;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withRegex;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.impl.DecorationNodeImpl;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.SWTBotAssert;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.toronto.cs.openome_model.Actor;
import edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart.ActorFigure;
import edu.toronto.cs.openome_model.impl.ActorImpl;

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestAddActor{
	
	private static SWTBotGefEditor editor;
	private static Actor actModel;
	
	@BeforeClass
	public static void beforeClass() throws Exception {
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
	public void canAddActor() throws Exception {
		try {
			String name = "TestActor";
			// Draw an actor
			editor.activateTool("Actor");
			editor.click(0, 0);
			editor.directEditType(name);
			
			SWTBotGefEditPart actor = editor.getEditPart(name);
			
			// check if the model was made correctly
			DecorationNodeImpl decnode = (DecorationNodeImpl) actor.part().getModel();
			actModel = (Actor) decnode.getElement();
			assert(actor.part().getModel() instanceof Actor);
			
			assert(actModel.getIntentions().isEmpty());
			assert(actor.children().isEmpty());
			
			// Make sure no connections exist
			assert(actor.sourceConnections().isEmpty());
			assert(actor.targetConnections().isEmpty());
			editor.editParts((Matcher<? extends EditPart>) allOf(withRegex(".*")));
		} catch (WidgetNotFoundException e) {
			fail("Could not add a new actor.");
		}
	}
}
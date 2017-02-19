package edu.toronto.cs.openome.swtbottesting;

import static org.junit.Assert.*;

import java.util.Random;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gmf.runtime.notation.impl.DecorationNodeImpl;
import org.eclipse.gmf.runtime.notation.impl.ShapeImpl;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.toronto.cs.openome_model.diagram.edit.parts.CompartmentEditPart;
import edu.toronto.cs.openome_model.impl.ContainerImpl;

/**
 * Test dragging intentions in and our of actor boundary.
 * @author showzeb
 *
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class TestMovingInOutOfActorBoundary {

	private SWTBotGefEditor editor;
	String[] combineArray = TestUtil.combineArray (TestUtil.contributions, TestUtil.hardlinks);
	
    @Before
    public void runBeforeEverTest() {
    	TestUtil.initializeWorkspace();
        TestUtil.createAndOpenFile();
        TestUtil.bot.editorByTitle(TestUtil.diagramName).setFocus();
		TestUtil.bot.menu("Window").menu("Navigation").menu("Maximize Active View or Editor").click();
        editor = new SWTGefBot().gefEditor("test.ood");
    }
    
    @After
    public void runAfterEveryTest() {
    	TestUtil.closeAndDeleteFile();
    }
    
    /**
     * Test dragging intentions in out of actor boundary.
     * @throws InterruptedException 
     */
    @Test
    public void testIntentionInOutOfActorsBoundary() throws InterruptedException {
    	
    	SWTBotGefEditPart actor;
    	SWTBotGefEditPart intention;
    	ShapeImpl decnode;
    	ContainerImpl actorModel;
    	
    	for (String actorName : TestUtil.actors) {
    		
    		//Add an actor.
    		editor.clear();
    		editor.activateTool(actorName).click(0,0);
    		editor.directEditType("a");
    		actor = editor.getEditPart("a").parent();
    		
    		for (String intentionName : TestUtil.intentions) {
    			
    			//Add an intention.
    			editor.clear();
    			editor.activateTool(intentionName).click(600,200);
    			editor.directEditType("int");
    			intention = editor.getEditPart("int").parent();
    			
    			//Drag the intention inside an actor.
    			editor.clear();
    			editor.drag(intention, 200, 150);
    			editor.setFocus();
    			
    			//Test if intention inside the actor.
    			decnode = (ShapeImpl) actor.part().getModel();
    	    	actorModel = (ContainerImpl) decnode.getElement();
    	    	assertTrue(intentionName + " does not have an intention.", actorModel.getIntentions().size() == 1);
    	    	
    	    	//Drag the intention outside of the actor.
    	    	editor.clear();
    	    	intention = editor.getEditPart("int").parent();
    			editor.drag(intention, 600, 200);
    			editor.setFocus();
    			
    			//Test if intention inside the actor.
    			decnode = (ShapeImpl) actor.part().getModel();
    	    	actorModel = (ContainerImpl) decnode.getElement();
    	    	assertTrue(intentionName + " does have an intention.", actorModel.getIntentions().size() == 0);
    	    	
    	    	//Deleting intention to make room for new one.
    	    	editor.clear();
    	    	editor.select(editor.getEditPart("int").parent());
    	    	editor.clickContextMenu("Delete from Model");
    		}
    		
    		//Deleting actor to make room for new one
    		editor.clear();
    		editor.select(actor);
    		editor.clickContextMenu("Delete from Model");
    	}
    }
    
    /**
     * Test dragging two intentions connected with a link in/out of actor boundary
     * @throws InterruptedException 
     */
    @Test
    public void testDraggingIntentionsWithLinksInOutOfActorBoundary() throws InterruptedException {
    	
    	Random generator = new Random();
    	int bound = TestUtil.intentions.length;
    	SWTBotGefEditPart actor;
    	SWTBotGefEditPart intention;
    	SWTBotGefEditPart intention2;
    	SWTBotGefEditPart link;
    	ShapeImpl decnode;
    	ContainerImpl actorModel;
    	
    	for (String actorName : TestUtil.actors) {
    		
    		//Add an actor.
    		editor.clear();
    		editor.activateTool(actorName).click(0,0);
    		editor.directEditType("a");
    		actor = editor.getEditPart("a").parent();
    		
    		String intentionName = "Hardgoal";
    		String intentionNameSecond = "Hardgoal";
    		//String linkName = "Make";
    		
    		for (String linkName : combineArray) {
    			
    			//Get random intention.
    			intentionName = TestUtil.intentions[generator.nextInt(bound)];
    			intentionNameSecond = TestUtil.intentions[generator.nextInt(bound)];
    			
    			//Add an intention.
    			editor.clear();
    			editor.activateTool(intentionName).click(600,100);
    			editor.directEditType("int");
    			intention = editor.getEditPart("int").parent();
    			
    			//Add second intention.
    			editor.clear();
    			editor.activateTool(intentionNameSecond).click(600,300);
    			editor.directEditType("int2");
    			intention2 = editor.getEditPart("int2").parent();
    			
    			//Make a link between the two intention.
    			editor.clear();
    			editor.activateTool(linkName);
    			editor.drag(intention, 600, 300);
    			editor.setFocus();
    			link = editor.selectedEditParts().get(0);
    			
    			//Select the intentions and link.
    			editor.clear();
    			editor.select(intention,link,intention2);
    			
    			
    			//Drag the intentions and link inside an actor.
    			editor.clear();
    			editor.drag(intention, 200, 100);
    			editor.setFocus();
    			editor.clear();
    			editor.drag(intention2, 200, 300);
    			editor.setFocus();
    			
    			//Test if intention inside the actor.
    			decnode = (ShapeImpl) actor.part().getModel();
    	    	actorModel = (ContainerImpl) decnode.getElement();
    	    	assertTrue(intentionName + " does not have a connection.", intention.sourceConnections().size() == 1);
    	    	assertTrue(intentionNameSecond + " does not have a connection.", intention2.targetConnections().size() == 1);
    	    	assertTrue(actorName + " does not have an intention.", actorModel.getIntentions().size() == 2);
    	    	
    	    	//Drag the intention outside of the actor.
    	    	editor.clear();
    	    	intention = editor.getEditPart("int").parent();
    			editor.drag(intention, 600, 200);
    			editor.setFocus();
    			intention2 = editor.getEditPart("int2").parent();
    			editor.drag(intention2, 600, 400);
    			editor.setFocus();
    			
    			//Test if intention inside the actor.
    			decnode = (ShapeImpl) actor.part().getModel();
    	    	actorModel = (ContainerImpl) decnode.getElement();
    	    	assertTrue(intentionName + " does not have a connection.", intention.sourceConnections().size() == 1);
    	    	assertTrue(intentionNameSecond + " does not have a connection.", intention2.targetConnections().size() == 1);
    	    	assertTrue(actorName + " does have an intention.", actorModel.getIntentions().size() == 0);
    	    	
    	    	//Deleting intention to make room for new one.
    	    	editor.clear();
    	    	editor.select(editor.getEditPart("int").parent());
    	    	editor.clickContextMenu("Delete from Model");
    	    	
    	    	//Deleting intention to make room for new one.
    	    	editor.clear();
    	    	editor.select(editor.getEditPart("int2").parent());
    	    	editor.clickContextMenu("Delete from Model");
    		}
    		
    		//Deleting actor to make room for new one
    		editor.clear();
    		editor.select(actor);
    		editor.clickContextMenu("Delete from Model");
    		
    	}
    }
    
    //TODO: Complete the test case once bug of actor inside another actor is fixed. 
    /**
     * Test dragging inside another actor. It should not do that, if it does it should consider it 
     * as its child.
     */
    @Test
    public void testDraggingActorInsideAnotherActor() {
    	SWTBotGefEditPart actor;
    	SWTBotGefEditPart actor2;
    	
    	//Add an actor.
		editor.clear();
		editor.activateTool("Actor").click(0,0);
		editor.directEditType("a");
		actor = editor.getEditPart("a").parent();
		
		//Collapse this actor
		actor.click(new Point(10,10));
		
		//Add another actor.
		editor.clear();
		editor.activateTool("Actor").click(200,0);
		editor.directEditType("b");
		actor2 = editor.getEditPart("b").parent();
		
		//Drag first actor inside another actor.
		editor.clear();
		editor.drag(actor, 250, 150);
		editor.setFocus();
		
		//check if first actor child of second actor.
		System.out.println(actor2.children());
    }
}

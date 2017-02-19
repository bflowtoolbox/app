package edu.toronto.cs.openome.swtbottesting;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.impl.ConnectorImpl;
import org.eclipse.gmf.runtime.notation.impl.DecorationNodeImpl;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart;
import edu.toronto.cs.openome_model.impl.AndContributionImpl;
import edu.toronto.cs.openome_model.impl.AndDecompositionImpl;
import edu.toronto.cs.openome_model.impl.BreakContributionImpl;
import edu.toronto.cs.openome_model.impl.DependencyImpl;
import edu.toronto.cs.openome_model.impl.HelpContributionImpl;
import edu.toronto.cs.openome_model.impl.HurtContributionImpl;
import edu.toronto.cs.openome_model.impl.IntentionImpl;
import edu.toronto.cs.openome_model.impl.MakeContributionImpl;
import edu.toronto.cs.openome_model.impl.OrContributionImpl;
import edu.toronto.cs.openome_model.impl.OrDecompositionImpl;
import edu.toronto.cs.openome_model.impl.SomeMinusContributionImpl;
import edu.toronto.cs.openome_model.impl.SomePlusContributionImpl;
import edu.toronto.cs.openome_model.impl.UnknownContributionImpl;

/**
 * Testing the functionality of straightening the curved links.
 * @author showzeb
 *
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class TestStraightenLinks {

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
     * Test to straighten links between any two actors
     */
    @Test
    public void TestStraightenLinksBetweenTwoActors() {
    	Random generator = new Random();
    	int bound = TestUtil.actors.length;
    	SWTBotGefEditPart actorLeft;
    	SWTBotGefEditPart actorRight;
    	SWTBotGefEditPart linkBefore;
    	
    	DecorationNodeImpl intentionOrActorNode;
    	
    	for (String linkName : TestUtil.associations) {
    		
    		//Adding a random actor in the model.
    		actorLeft = addActor(TestUtil.actors[generator.nextInt(bound)], "a", 0, 0);
//    		
    		//Collapse the actor and waiting for it to collapse
			actorLeft.parent().select();
	    	actorLeft.parent().click(new Point(10,10));
	    	sleep(100);
	    	
	    	//Adding a random actor in the model.
    		actorRight = addActor(TestUtil.actors[generator.nextInt(bound)], "b", 150, 0);
    		//Collapse the actor and waiting for it to collapse
//			actorRight.parent().select();
//	    	actorRight.parent().click(new Point(155,5));
//	    	sleep(5000);
//	    	
//	    	editor.clear();
//	    	actorRight.parent().select();
//	    	editor.drag(actorRight.parent(), 150, 200);
	    	//editor.drag(150, 0 , 150, 200);
	    	
    		addLink(linkName, actorRight, actorLeft, 0, 0);
//    		//Collapse an actor and waiting for it to collapse
//			actorRight.parent().select();
//	    	actorRight.parent().click(new Point(5,5));
//	    	sleep(100);
    		
    		editor.clear();
    		editor.click(actorLeft);
    		editor.clickContextMenu("Delete from Model");
    		editor.click(actorRight);
    		editor.clickContextMenu("Delete from Model");
    		
    	}
    }
    /**
     * Test straighten links between any two intentions
     */
    @Test
    public void TestStraightenLinksBetweenTwoIntentions() {
    	
    	Random generator = new Random();
    	int bound = TestUtil.intentions.length;
    	SWTBotGefEditPart intentionLeft;
    	SWTBotGefEditPart intentionRight;
    	SWTBotGefEditPart linkBefore;
    	//SWTBotGefEditPart linkAfter = null;
    	DecorationNodeImpl intentionOrLinkNode;
    	
    	for (String linkName : combineArray) {
    		
    		//Adding two random intentions in the model.
    		intentionLeft = addIntention(TestUtil.intentions[generator.nextInt(bound)], "a", 0, 0);
    		intentionRight = addIntention(TestUtil.intentions[generator.nextInt(bound)], "b", 200, 0);
	    	
    		//Adding a link between two intentions.
    		linkBefore =  addLink(linkName, intentionLeft, intentionRight, 200, 0);
    		
    		//Getting the model of both intentions.
    		intentionOrLinkNode = (DecorationNodeImpl) intentionLeft.part().getModel();
	    	IntentionImpl intentionLeftModel = (IntentionImpl) intentionOrLinkNode.getElement();
	    	intentionOrLinkNode = (DecorationNodeImpl) intentionRight.part().getModel();
	    	IntentionImpl intentionRightModel = (IntentionImpl) intentionOrLinkNode.getElement();
    				
    				
			//Curve the link first
			//editor.clickContextMenu("Change Type").clickContextMenu(replaceLink);
	    	//editor.select(linkBefore);
			//editor.drag(185,26, 185, 100);
	    	ConnectionNodeEditPart linkEdit = (ConnectionNodeEditPart) linkBefore.part();
	    	ConnectorImpl linkModel = (ConnectorImpl) linkBefore.part().getModel();
	    	
	    	
	    	//System.out.println(linkBefore);
	    	//System.out.println(linkBefore.part());
	    	//System.out.println(linkBefore.part().getModel());
	    	//editor.click("a");
	    	//System.out.println("a");
	    	//sleep(5000);
	    	
//	    	editor.click("a");
//	    	editor.clear();
//			editor.click(180, 26);
//			System.out.println("b");
//			System.out.println(editor.selectedEditParts().get(0));
//			sleep(5000);
//			editor.drag(editor.selectedEditParts().get(0), 180, 100);
//			sleep(5000);
			//editor.setFocus();
			//linkAfter = editor.selectedEditParts().get(0);
			
			//Testing if link got replaced.
			if (!linkName.equals("Dependency") && !linkName.equals("Decomposition") && !linkName.equals("Means-ends")) {
				intentionOrLinkNode = (DecorationNodeImpl) linkBefore.children().get(0).part().getModel();
				softLinkTestingHelperFunction(intentionLeftModel,intentionRightModel, linkName, intentionOrLinkNode);
			} else {
				hardLinksTestingHelperFunction(linkBefore, linkName, intentionLeftModel, intentionLeft, 
						intentionRightModel, intentionRight);
			}
			
			//Straighten the link.
			editor.select(linkBefore);
			editor.clickContextMenu("Straighten Line");
//			editor.doubleClick(185, 26);
//			editor.setFocus();
//			linkBefore = editor.selectedEditParts().get(0);
			
			//Testing if link got replaced.
			if (!linkName.equals("Dependency") && !linkName.equals("Decomposition") && !linkName.equals("Means-ends")) {
				intentionOrLinkNode = (DecorationNodeImpl) linkBefore.children().get(0).part().getModel();
				softLinkTestingHelperFunction(intentionLeftModel,intentionRightModel, linkName, intentionOrLinkNode);
			} else {
				hardLinksTestingHelperFunction(linkBefore, linkName, intentionLeftModel, intentionLeft, 
						intentionRightModel, intentionRight);
			}
					
    		
    		//Deleting the intention and link from the model.
    		editor.clear();
    		editor.click(linkBefore);
    		editor.clickContextMenu("Delete from Model");
    		editor.click(intentionLeft);
    		editor.clickContextMenu("Delete from Model");
    		editor.click(intentionRight);
    		editor.clickContextMenu("Delete from Model");
    		editor.setFocus();
    		
    	}
    }
    
    /**
     * Put SWT Test on sleep.
     * @param time
     */
    public void sleep (int time) {
    	try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * Add an actor on the screen
     * @param actorName
     * @param name
     * @param x TODO
     * @param y TODO
     * @return
     */
    public SWTBotGefEditPart addActor(String actorName, String name, int x, int y) {
    	editor.activateTool(actorName);
		editor.click(x, y);
		editor.directEditType(name);
		return editor.getEditPart(name);
    }
    
    /**
     * Return the SWTBotGefEditPart of intention added at position (x,y).
     * @param intentionName
     * @param name
     * @param x
     * @param y
     * @return
     */
    public SWTBotGefEditPart addIntention(String intentionName, String name, int x, int y) {
    	editor.clear();
    	editor.activateTool(intentionName);
    	editor.click(x,y);
    	editor.directEditType(name);
    	return editor.getEditPart(name);
    }
    
    /**
     * Return the SWTBotGefEditPart of the link added at point (x,y). This doesn't return directEditPart but returns the 
     * edit part of the parent.
     * @param linkName Name of the link
     * @param source Source of link
     * @param target Target of the link
     * @param x
     * @param y
     * @return
     */
    public SWTBotGefEditPart addLink(String linkName, SWTBotGefEditPart source, SWTBotGefEditPart target, int x, int y) {
    	editor.clear();
    	editor.activateTool(linkName);
    	
    	editor.drag(source, x, y);
    	//editor.click(source);
    	editor.setFocus();
    	//return editor.selectedEditParts().get(0);
    	return editor.selectedEditParts().get(0);
    }
    
    /**
     * Helper function to test if the intention model has the specified soft links with the linkName
     * @param sourceIntention
     * @param linkName
     */
    public void softLinkTestingHelperFunction(IntentionImpl sourceIntention, IntentionImpl targetIntention, 
    		String linkName, DecorationNodeImpl link) {
    	
    	editor.clear();
    	if (linkName.equals("Make")) {
    		assertTrue("Testing if the " + sourceIntention.getName() + " has " + linkName + " link.",
    				((MakeContributionImpl)(sourceIntention.getContributesTo().get(0))).equals((MakeContributionImpl)link.getElement()));
    		assertTrue("Testing if the " + targetIntention.getName() + " has " + linkName + " link.", 
    				((MakeContributionImpl)(targetIntention.getContributesFrom().get(0))).equals((MakeContributionImpl)link.getElement()));
    	} else if (linkName.equals("Some+")) {
    		assertTrue("Testing if the " + sourceIntention.getName() + " has " + linkName + " link.", 
    				((SomePlusContributionImpl)sourceIntention.getContributesTo().get(0)).equals((SomePlusContributionImpl)link.getElement()));
    		assertTrue("Testing if the " + targetIntention.getName() + " has " + linkName + " link.", 
    				((SomePlusContributionImpl)targetIntention.getContributesFrom().get(0)).equals((SomePlusContributionImpl)link.getElement()));
    	} else if (linkName.equals("Help")) {
    		assertTrue("Testing if the " + sourceIntention.getName() + " has " + linkName + " link.", 
    				((HelpContributionImpl)sourceIntention.getContributesTo().get(0)).equals((HelpContributionImpl)link.getElement()));
    		assertTrue("Testing if the " + targetIntention.getName() + " has " + linkName + " link.", 
    				((HelpContributionImpl)targetIntention.getContributesFrom().get(0)).equals((HelpContributionImpl)link.getElement()));
    	} else if (linkName.equals("Unknown")) {
    		assertTrue("Testing if the " + sourceIntention.getName() + " has " + linkName + " link.", 
    				((UnknownContributionImpl)sourceIntention.getContributesTo().get(0)).equals((UnknownContributionImpl)link.getElement()));
    		assertTrue("Testing if the " + targetIntention.getName() + " has " + linkName + " link.", 
    				((UnknownContributionImpl)targetIntention.getContributesFrom().get(0)).equals((UnknownContributionImpl)link.getElement()));
    	} else if (linkName.equals("Hurt")) {
    		assertTrue("Testing if the " + sourceIntention.getName() + " has " + linkName + " link.", 
    				((HurtContributionImpl)sourceIntention.getContributesTo().get(0)).equals((HurtContributionImpl)link.getElement()));
    		assertTrue("Testing if the " + targetIntention.getName() + " has " + linkName + " link.", 
    				((HurtContributionImpl)targetIntention.getContributesFrom().get(0)).equals((HurtContributionImpl)link.getElement()));
    	} else if (linkName.equals("Some-")) {
    		assertTrue("Testing if the " + sourceIntention.getName() + " has " + linkName + " link.", 
    				((SomeMinusContributionImpl)sourceIntention.getContributesTo().get(0)).equals((SomeMinusContributionImpl)link.getElement()));
    		assertTrue("Testing if the " + targetIntention.getName() + " has " + linkName + " link.", 
    				((SomeMinusContributionImpl)targetIntention.getContributesFrom().get(0)).equals((SomeMinusContributionImpl)link.getElement()));
    	} else if (linkName.equals("Unknown")) {
    		assertTrue("Testing if the " + sourceIntention.getName() + " has " + linkName + " link.", 
    				((UnknownContributionImpl)sourceIntention.getContributesTo().get(0)).equals((UnknownContributionImpl)link.getElement()));
    		assertTrue("Testing if the " + targetIntention.getName() + " has " + linkName + " link.", 
    				((UnknownContributionImpl)targetIntention.getContributesFrom().get(0)).equals((UnknownContributionImpl)link.getElement()));
    	} else if (linkName.equals("Break")) {
    		assertTrue("Testing if the " + sourceIntention.getName() + " has " + linkName + " link.", 
    				((BreakContributionImpl)sourceIntention.getContributesTo().get(0)).equals((BreakContributionImpl)link.getElement()));
    		assertTrue("Testing if the " + targetIntention.getName() + " has " + linkName + " link.", 
    				((BreakContributionImpl)targetIntention.getContributesFrom().get(0)).equals((BreakContributionImpl)link.getElement()));
    	} else if (linkName.equals("AND")) {
    		assertTrue("Testing if the " + sourceIntention.getName() + " has " + linkName + " link.", 
    				((AndContributionImpl)sourceIntention.getContributesTo().get(0)).equals((AndContributionImpl)link.getElement()));
    		assertTrue("Testing if the " + targetIntention.getName() + " has " + linkName + " link.", 
    				((AndContributionImpl)targetIntention.getContributesFrom().get(0)).equals((AndContributionImpl)link.getElement()));
    	} else if (linkName.equals("OR")) {
    		assertTrue("Testing if the " + sourceIntention.getName() + " has " + linkName + " link.", 
    				((OrContributionImpl)sourceIntention.getContributesTo().get(0)).equals((OrContributionImpl)link.getElement()));
    		assertTrue("Testing if the " + targetIntention.getName() + " has " + linkName + " link.", 
    				((OrContributionImpl)targetIntention.getContributesFrom().get(0)).equals((OrContributionImpl)link.getElement()));
    	}
    }
    
    /**
     * Helper function to test hard links.
     * @param linkName
     * @param sourceIntentionModel
     */
    public void hardLinksTestingHelperFunction(SWTBotGefEditPart link, String linkName, IntentionImpl sourceIntentionModel, 
    		SWTBotGefEditPart sourceIntention, IntentionImpl targetIntentionModel, SWTBotGefEditPart targetIntention) {
    	
    	if (linkName.equals("Dependency")) {
    		((ConnectorImpl) link.part().getModel()).getElement();
    		assertTrue("", ((GraphicalEditPart) sourceIntention.part().getParent()).getSourceConnections().get(0) instanceof DependencyEditPart);
    		assertTrue(sourceIntentionModel.getDependencyFrom().size() == 1);
    		assertTrue(targetIntentionModel.getDependencyTo().size() == 1);
    		
    		assertTrue("Testing if the " + sourceIntentionModel.getName() + " has " + linkName + " link.", 
    				((DependencyImpl)sourceIntentionModel.getDependencyFrom().get(0)).equals((DependencyImpl)((ConnectorImpl) link.part().getModel()).getElement()));
    		assertTrue("Testing if the " + targetIntentionModel.getName() + " has " + linkName + " link.", 
    				((DependencyImpl)targetIntentionModel.getDependencyTo().get(0)).equals((DependencyImpl)((ConnectorImpl) link.part().getModel()).getElement()));
    	} else if (linkName.equals("Decomposition")){
    		assertTrue("", ((GraphicalEditPart) sourceIntention.part().getParent()).getSourceConnections().get(0) instanceof AndDecompositionEditPart);
    		assertTrue(sourceIntentionModel.getDecompositionsFrom().size() == 1);
    		assertTrue(targetIntentionModel.getDecompositionsTo().size() == 1);
    		
    		assertTrue("Testing if the " + sourceIntentionModel.getName() + " has " + linkName + " link.", 
    				((AndDecompositionImpl)sourceIntentionModel.getDecompositionsFrom().get(0)).equals((AndDecompositionImpl)((ConnectorImpl) link.part().getModel()).getElement()));
    		assertTrue("Testing if the " + targetIntentionModel.getName() + " has " + linkName + " link.", 
    				((AndDecompositionImpl)targetIntentionModel.getDecompositionsTo().get(0)).equals((AndDecompositionImpl)((ConnectorImpl) link.part().getModel()).getElement()));
    		
    	} else if (linkName.equals("Means-ends")) {
    		
       		assertTrue("", ((GraphicalEditPart) sourceIntention.part().getParent()).getSourceConnections().get(0) instanceof OrDecompositionEditPart);
       		assertTrue(sourceIntentionModel.getDecompositionsFrom().size() == 1);
    		assertTrue(targetIntentionModel.getDecompositionsTo().size() == 1);
    		
    		assertTrue("Testing if the " + sourceIntentionModel.getName() + " has " + linkName + " link.", 
    				((OrDecompositionImpl)sourceIntentionModel.getDecompositionsFrom().get(0)).equals((OrDecompositionImpl)((ConnectorImpl) link.part().getModel()).getElement()));
    		assertTrue("Testing if the " + targetIntentionModel.getName() + " has " + linkName + " link.", 
    				((OrDecompositionImpl)targetIntentionModel.getDecompositionsTo().get(0)).equals((OrDecompositionImpl)((ConnectorImpl) link.part().getModel()).getElement()));
    	}
    }

}

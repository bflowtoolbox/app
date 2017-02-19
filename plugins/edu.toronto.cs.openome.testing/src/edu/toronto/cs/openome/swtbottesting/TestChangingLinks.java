package edu.toronto.cs.openome.swtbottesting;

import static org.junit.Assert.assertTrue;

import java.awt.MouseInfo;
import java.util.Random;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
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

import org.eclipse.swt.widgets.Button;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.*;

import edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart;
import edu.toronto.cs.openome_model.impl.AndContributionImpl;
import edu.toronto.cs.openome_model.impl.AndDecompositionImpl;
import edu.toronto.cs.openome_model.impl.BreakContributionImpl;
import edu.toronto.cs.openome_model.impl.ContainerImpl;
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
 * Test changing links in a model
 * @author showzeb
 *
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class TestChangingLinks {
	
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
     * Test changing links between any two intentions
     */
    @Test
    public void TestChangingLinksBetweenTwoIntentions() {
    	
    	Random generator = new Random();
    	int bound = TestUtil.intentions.length;
    	SWTBotGefEditPart intentionLeft;
    	SWTBotGefEditPart intentionRight;
    	SWTBotGefEditPart linkBefore;
    	SWTBotGefEditPart linkAfter = null;
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
	    	
    		for (String replaceLink : combineArray) {
    			if (!replaceLink.equals(linkName)){
    				
    				
    				//Replacing the link
    				editor.clickContextMenu("Change Type").clickContextMenu(replaceLink);
					editor.doubleClick(185, 26);
    				editor.setFocus();
    				linkAfter = editor.selectedEditParts().get(0);
    				
    				//Testing if link got replaced.
					if (!replaceLink.equals("Dependency") && !replaceLink.equals("Decomposition") && !replaceLink.equals("Means-ends")) {
						intentionOrLinkNode = (DecorationNodeImpl) linkAfter.children().get(0).part().getModel();
						softLinkTestingHelperFunction(intentionLeftModel,intentionRightModel, replaceLink, intentionOrLinkNode);
					} else {
						hardLinksTestingHelperFunction(linkAfter, replaceLink, intentionLeftModel, intentionLeft, 
								intentionRightModel, intentionRight);
					}
					
					//Replacing the link again back to the original one.
					editor.select(linkAfter);
					editor.clickContextMenu("Change Type").clickContextMenu(linkName);
					editor.doubleClick(185, 26);
    				editor.setFocus();
    				linkBefore = editor.selectedEditParts().get(0);
					
    				//Testing if link got replaced.
					if (!linkName.equals("Dependency") && !linkName.equals("Decomposition") && !linkName.equals("Means-ends")) {
						intentionOrLinkNode = (DecorationNodeImpl) linkBefore.children().get(0).part().getModel();
						softLinkTestingHelperFunction(intentionLeftModel,intentionRightModel, linkName, intentionOrLinkNode);
					} else {
						hardLinksTestingHelperFunction(linkBefore, linkName, intentionLeftModel, intentionLeft, 
								intentionRightModel, intentionRight);
					}
					
    			}
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
     * Test changing links between any two intentions inside an Actor
     */
    @Test
    public void TestChangingLinksBetweenTwoIntentionsInAnActor() {
    	
    	Random generator = new Random();
    	int bound = TestUtil.intentions.length;
    	SWTBotGefEditPart actor;
    	SWTBotGefEditPart intentionLeft;
    	SWTBotGefEditPart intentionRight;
    	SWTBotGefEditPart linkBefore;
    	SWTBotGefEditPart linkAfter = null;
    	DecorationNodeImpl intentionOrLinkNode;
    	
    	for (String linkName : combineArray) {
    		
    		//Adding two random intentions in the model.
    		actor = addActor(TestUtil.actors[generator.nextInt(4)],"act");
    		intentionLeft = addIntention(TestUtil.intentions[generator.nextInt(bound)], "a", 100, 200);
    		intentionRight = addIntention(TestUtil.intentions[generator.nextInt(bound)], "b", 300, 200);
	    	
    		//Adding a link between two intentions.
    		linkBefore =  addLink(linkName, intentionLeft, intentionRight, 300, 200);
    		
    		//Getting the model of both intentions and actor.
    		DecorationNodeImpl decnode = (DecorationNodeImpl) actor.part().getModel();
	    	ContainerImpl actorModel = (ContainerImpl) decnode.getElement();
    		intentionOrLinkNode = (DecorationNodeImpl) intentionLeft.part().getModel();
	    	IntentionImpl intentionLeftModel = (IntentionImpl) intentionOrLinkNode.getElement();
	    	intentionOrLinkNode = (DecorationNodeImpl) intentionRight.part().getModel();
	    	IntentionImpl intentionRightModel = (IntentionImpl) intentionOrLinkNode.getElement();
	    	
    		for (String replaceLink : combineArray) {
    			if (!replaceLink.equals(linkName)){
    				
    				
    				//Replacing the link
    				editor.clickContextMenu("Change Type").clickContextMenu(replaceLink);
					editor.click(285, 226);
    				editor.setFocus();
    				linkAfter = editor.selectedEditParts().get(0);
    				
    				//Testing if link got replaced.
					if (!replaceLink.equals("Dependency") && !replaceLink.equals("Decomposition") && !replaceLink.equals("Means-ends")) {
						intentionOrLinkNode = (DecorationNodeImpl) linkAfter.children().get(0).part().getModel();
						softLinkTestingHelperFunction(intentionLeftModel,intentionRightModel, replaceLink, intentionOrLinkNode);
					} else {
						hardLinksTestingHelperFunction(linkAfter, replaceLink, intentionLeftModel, intentionLeft, 
								intentionRightModel, intentionRight);
					}
					
					//Replacing the link again back to the original one.
					editor.select(linkAfter);
					editor.clickContextMenu("Change Type").clickContextMenu(linkName);
					editor.click(285, 226);
    				editor.setFocus();
    				linkBefore = editor.selectedEditParts().get(0);
					
    				//Testing if link got replaced.
					if (!linkName.equals("Dependency") && !linkName.equals("Decomposition") && !linkName.equals("Means-ends")) {
						intentionOrLinkNode = (DecorationNodeImpl) linkBefore.children().get(0).part().getModel();
						softLinkTestingHelperFunction(intentionLeftModel,intentionRightModel, linkName, intentionOrLinkNode);
					} else {
						hardLinksTestingHelperFunction(linkBefore, linkName, intentionLeftModel, intentionLeft, 
								intentionRightModel, intentionRight);
					}
					
    			}
    		}
    		
    		//Deleting the intention and link from the model.
    		editor.clear();
    		editor.click(linkBefore);
    		editor.clickContextMenu("Delete from Model");
    		editor.click(intentionLeft);
    		editor.clickContextMenu("Delete from Model");
    		editor.click(intentionRight);
    		editor.clickContextMenu("Delete from Model");
    		editor.click(actor);
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
     * @return
     */
    public SWTBotGefEditPart addActor(String actorName, String name) {
    	editor.activateTool(actorName);
		editor.click(0, 0);
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

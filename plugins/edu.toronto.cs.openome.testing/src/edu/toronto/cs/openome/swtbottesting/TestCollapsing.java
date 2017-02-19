package edu.toronto.cs.openome.swtbottesting;

import static org.eclipse.swtbot.swt.finder.SWTBotAssert.assertVisible;
import static org.junit.Assert.*;

import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.impl.EAttributeImpl;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.impl.ConnectorImpl;
import org.eclipse.gmf.runtime.notation.impl.DecorationNodeImpl;
import org.eclipse.gmf.runtime.notation.impl.DiagramImpl;
import org.eclipse.gmf.runtime.notation.impl.DrawerStyleImpl;
import org.eclipse.gmf.runtime.notation.impl.NotationFactoryImpl;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.keyboard.Keyboard;
import org.eclipse.swtbot.swt.finder.keyboard.KeyboardFactory;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.toronto.cs.openome_model.Actor;
import edu.toronto.cs.openome_model.Model;
import edu.toronto.cs.openome_model.diagram.edit.parts.ActorActorCompartmentEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.CompartmentEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart;
import edu.toronto.cs.openome_model.impl.ActorImpl;
import edu.toronto.cs.openome_model.impl.AgentImpl;
import edu.toronto.cs.openome_model.impl.AndContributionImpl;
import edu.toronto.cs.openome_model.impl.AndDecompositionImpl;
import edu.toronto.cs.openome_model.impl.BreakContributionImpl;
import edu.toronto.cs.openome_model.impl.ContainerImpl;
import edu.toronto.cs.openome_model.impl.DependencyImpl;
import edu.toronto.cs.openome_model.impl.HelpContributionImpl;
import edu.toronto.cs.openome_model.impl.HurtContributionImpl;
import edu.toronto.cs.openome_model.impl.IntentionImpl;
import edu.toronto.cs.openome_model.impl.MakeContributionImpl;
import edu.toronto.cs.openome_model.impl.ModelImpl;
import edu.toronto.cs.openome_model.impl.OrContributionImpl;
import edu.toronto.cs.openome_model.impl.OrDecompositionImpl;
import edu.toronto.cs.openome_model.impl.PositionImpl;
import edu.toronto.cs.openome_model.impl.RoleImpl;
import edu.toronto.cs.openome_model.impl.SomeMinusContributionImpl;
import edu.toronto.cs.openome_model.impl.SomePlusContributionImpl;
import edu.toronto.cs.openome_model.impl.UnknownContributionImpl;
import edu.toronto.cs.openome_model.impl.openome_modelFactoryImpl;

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestCollapsing {

	private static SWTBotGefEditor editor;
    private static Keyboard keyboard;
    private SWTBotGefEditPart actors;
    private final String[][] LINKS = { TestUtil.contributions, TestUtil.hardlinks};
    /*
	 * A factory that can create any class Impl
	 */
	private static openome_modelFactoryImpl factory = new openome_modelFactoryImpl();
	
	
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        
//        TestUtil.createAndOpenFile();
//        editor = new SWTGefBot().gefEditor("test.ood");
//        keyboard = KeyboardFactory.getDefaultKeyboard(editor.getWidget(), null);
    }

    
    @Before
    public void runBeforeEverTest() {
    	TestUtil.initializeWorkspace();
        TestUtil.createAndOpenFile();
		TestUtil.bot.editorByTitle(TestUtil.diagramName).setFocus();
		TestUtil.bot.menu("Window").menu("Navigation").menu("Maximize Active View or Editor").click();
        editor = new SWTGefBot().gefEditor("test.ood");
        keyboard = KeyboardFactory.getDefaultKeyboard(editor.getWidget(), null);
    }
    @After
    public void runAfterEveryTest() {
    	//TestUtil.closeAndDeleteFile();
    	//editor.clickContextMenu("Delete from Model");
    	TestUtil.closeAndDeleteFile();
    	//editor.clear();
    }
    
    /**
     * Test and collapse and opening of empty actors
     */
    @Test
    public void testCollapseAndOpeningEmptyActors() {
    	
    	for (String actorFromList : TestUtil.actors) {
	    	actors = addActor(actorFromList, "test");
	    	CompartmentEditPart cmp = (CompartmentEditPart) actors.parent().children().get(1).part();
	    	DecorationNodeImpl decnode = (DecorationNodeImpl) actors.part().getModel();
	    	ContainerImpl actorModel = (ContainerImpl) decnode.getElement();
	    	
	    	
			assert(actorModel.getIntentions().isEmpty());
			assert(actors.children().isEmpty());
			
			// Make sure no connections exist
			assert(actors.sourceConnections().isEmpty());
			assert(actors.targetConnections().isEmpty());
			
			//Collapse the actor and waiting for it to collapse
			actors.parent().select();
	    	actors.parent().click(new Point(10,10));
	    	sleep(100);
			
			//for ( SWTBotGefEditPart i : actors.parent().children()) 
				//System.out.println(i);
			
			assertTrue("Testing if " + actorFromList + " compartment is collapsed",cmp.isCollapsed());
	
			assertTrue(actorModel.getIntentions().isEmpty());
	
			
			assertTrue(actorModel.getAssociationFrom().isEmpty());
			assertTrue(actorModel.getAssociationTo().isEmpty());
			assertTrue(actorModel.getDependencyFrom().isEmpty());
			assertTrue(actorModel.getDependencyTo().isEmpty());
			if (actorFromList.equals("Actor")) {
				assertTrue(((ActorImpl)actorModel).getIs_a().isEmpty());
				assertTrue(((ActorImpl)actorModel).getIs_part_of().isEmpty());
			}
			
			actors.parent().select();
	    	actors.parent().click(new Point(10,10));
			sleep(100);
			
			assertFalse("Testing if " + actorFromList + " compartment has opened",cmp.isCollapsed());
	
			assertTrue(actorModel.getIntentions().isEmpty());
	
			
			assertTrue(actorModel.getAssociationFrom().isEmpty());
			assertTrue(actorModel.getAssociationTo().isEmpty());
			assertTrue(actorModel.getDependencyFrom().isEmpty());
			assertTrue(actorModel.getDependencyTo().isEmpty());
			if (actorFromList.equals("Actor")) {
				assertTrue(((ActorImpl)actorModel).getIs_a().isEmpty());
				assertTrue(((ActorImpl)actorModel).getIs_part_of().isEmpty());
			}
			
			editor.clear();
			editor.select(actors.parent());
			editor.clickContextMenu("Delete from Model");
    	}
    }
    
    /**
     * Test and collapse and opening of all actors with all intentions
     */
    @Test
    public void testCollapseAndOpeningActorsWithIntentions() {

    	for (String actorsFromList : TestUtil.actors) {
	    	actors = addActor(actorsFromList, "test");
	    	
	    	DecorationNodeImpl decnode = (DecorationNodeImpl) actors.part().getModel();
	    	ContainerImpl actorModel = (ContainerImpl) decnode.getElement();
	    	CompartmentEditPart cmp = (CompartmentEditPart) actors.parent().children().get(1).part();
	    	
	    	assert(actorModel.getIntentions().isEmpty());
			assert(actors.children().isEmpty());
			
			// Make sure no connections exist
			assert(actors.sourceConnections().isEmpty());
			assert(actors.targetConnections().isEmpty());
			
			for (String intentionsFromList : TestUtil.intentions) {
				//Add an intention
				SWTBotGefEditPart intention = addIntention(intentionsFromList, "intention", 110, 80);
		
				actors.parent().select();
		    	actors.parent().click(new Point(10,10));
		    	sleep(100);
		    	
		    	assertTrue("Testing if " + actorsFromList + " with "  + intentionsFromList + " compartment is collapsed",
		    			cmp.isCollapsed());
				
				
				assertTrue(actorModel.getIntentions().size() == 1);
		
				
				assertTrue(actorModel.getAssociationFrom().isEmpty());
				assertTrue(actorModel.getAssociationTo().isEmpty());
				assertTrue(actorModel.getDependencyFrom().isEmpty());
				assertTrue(actorModel.getDependencyTo().isEmpty());
				
				actors.parent().click(new Point(10,10));
				sleep(100);
				
				assertFalse("Testing if " + actorsFromList + " with "  + intentionsFromList + " compartment is open",
		    			cmp.isCollapsed());
				
				
				assertTrue(actorModel.getIntentions().size() == 1);
		
				
				assertTrue(actorModel.getAssociationFrom().isEmpty());
				assertTrue(actorModel.getAssociationTo().isEmpty());
				assertTrue(actorModel.getDependencyFrom().isEmpty());
				assertTrue(actorModel.getDependencyTo().isEmpty());
	
				editor.select(intention.parent());
				editor.setFocus();
				editor.clickContextMenu("Delete from Model");
			}
			
			editor.clear();
			editor.select(actors.parent());
			editor.clickContextMenu("Delete from Model");
    	}
    }
    
    /**
     * Test Collapse and opening of all actors with one instances of all intentions
     * and all links.
     */
    @Test
    public void testCollapseAndOpeningActorsWithIntentionAndLink() {

    	for (String actorsFromList : TestUtil.actors) {
	    	actors = addActor(actorsFromList, "test");
	    	
	    	ModelImpl model = (ModelImpl) TestUtil.getModel(editor);
	    	DecorationNodeImpl decnode = (DecorationNodeImpl) actors.part().getModel();
	    	ContainerImpl actorModel = (ContainerImpl) decnode.getElement();
	    	CompartmentEditPart cmp = (CompartmentEditPart) actors.parent().children().get(1).part();
			
			for (String intentionsFromList : TestUtil.intentions) {
				//Add an intention
				SWTBotGefEditPart intentionSource = addIntention(intentionsFromList, "intention", 100, 100);
				
				DecorationNodeImpl intentionNode = (DecorationNodeImpl) intentionSource.part().getModel();
		    	IntentionImpl intentionModel = (IntentionImpl) intentionNode.getElement();
		    	DecorationNodeImpl linkNode = null;
		    	
				for (String[] linkList : LINKS ) {
					for (String links : linkList) {
						
						assertTrue(model.getContributions().size() == 0);
						assertTrue(model.getDecompositions().size() == 0);
						assertTrue(model.getDependencies().size() == 0);
		
						SWTBotGefEditPart link = addLink(links, intentionSource, null, 100, 100);
												
						if (!links.equals("Dependency") && !links.equals("Decomposition") && !links.equals("Means-ends")) {
							linkNode = (DecorationNodeImpl) link.children().get(0).part().getModel();
							softLinkTestingHelperFunction(intentionModel,links, linkNode);
						} else {
							hardLinksTestingHelperFunction(link, links, intentionModel, intentionSource);
						}
						
						
						actors.parent().select();
				    	actors.parent().click(new Point(10,10));
				    	
				    	sleep(100);
				    	
				    	assertTrue("Testing if " + actorsFromList + " compartment is collapsed" + " with "  
				    			+ intentionsFromList + " with " + links,
				    			cmp.isCollapsed());
						
				    	if (!links.equals("Dependency") && !links.equals("Decomposition") && !links.equals("Means-ends")) {
							softLinkTestingHelperFunction(intentionModel,links, linkNode);
						} else {
							hardLinksTestingHelperFunction(link, links, intentionModel, intentionSource);
						}
				    	
						assertTrue(actorModel.getIntentions().size() == 1);
						assertTrue(intentionModel.getAllConnected().size() == 1);
						
						assertTrue(actorModel.getAssociationFrom().isEmpty());
						assertTrue(actorModel.getAssociationTo().isEmpty());
						assertTrue(actorModel.getDependencyFrom().isEmpty());
						assertTrue(actorModel.getDependencyTo().isEmpty());
						
						actors.parent().click(new Point(10,10));
						sleep(100);
						
						assertFalse("Testing if " + actorsFromList + " compartment is open" + " with "  
								+ intentionsFromList + " with " + links ,
				    			cmp.isCollapsed());
						
						if (!links.equals("Dependency") && !links.equals("Decomposition") && !links.equals("Means-ends")) {
							softLinkTestingHelperFunction(intentionModel,links, linkNode);
						} else {
							hardLinksTestingHelperFunction(link, links, intentionModel, intentionSource);
						}
						editor.clear();
						editor.select(link);
						editor.select(link);
						editor.setFocus();
						editor.clickContextMenu("Delete from Model");
					}
				}
	
				editor.select(intentionSource.parent());
				editor.setFocus();
				editor.clickContextMenu("Delete from Model");
			}
			
			editor.clear();
			editor.select(actors.parent());
			editor.clickContextMenu("Delete from Model");
    	}
    }
    
    /**
     * Test Collapse and opening of all actors with one instances of all intentions
     * and all links.
     */
    @Test
    public void testCollapseAndOpeningActorsWithTwoIntentionAndLink() {

    	
    	for (String actorsFromList : TestUtil.actors) {
	
    		
    		/***Setting up the actors. Adding intentions and links.*****/
	    	actors = addActor(actorsFromList, "test");
	    	//System.out.println(MouseInfo.getPointerInfo().getLocation().x);
	    	//actors.parent().resize(PositionConstants.ALWAYS_RIGHT, 1000, 350);

	    	
	    	String[] combineArray = TestUtil.combineArray (TestUtil.contributions, TestUtil.hardlinks);
	    	
	    	
	    	ModelImpl model = (ModelImpl) TestUtil.getModel(editor);
	    	DecorationNodeImpl decnode = (DecorationNodeImpl) actors.part().getModel();
	    	ContainerImpl actorModel = (ContainerImpl) decnode.getElement();
	    	CompartmentEditPart cmp = (CompartmentEditPart) actors.parent().children().get(1).part();
	    	
	    	
	    	ArrayList<SWTBotGefEditPart> intentions = new ArrayList<SWTBotGefEditPart>();
	    	
	    	int[] pointX = {170, 20, 320, 170, 170, 170, 170, 40, 40, 40, 300, 300, 300 };
	    	int[] pointY = {10, 180, 180, 100, 170, 250, 350, 100, 250, 310, 100, 260, 320};
	    	
	    	int index = 0;
	    	Random generator = new Random();
	    	for (String intentionFromList : TestUtil.intentions) {
	    		intentions.add(addIntention(intentionFromList, "int" + index, pointX[index], pointY[index]));
	    		index++;
	    	}
			
	    	while(index < pointX.length) {
	    		intentions.add(addIntention(TestUtil.intentions[generator.nextInt(TestUtil.intentions.length)], 
	    				"int" + index, pointX[index], pointY[index]));
	    		index++;
	    	}
	    	
			ArrayList<SWTBotGefEditPart> links = new ArrayList<SWTBotGefEditPart>();
			
			index = 0;
			while (index < intentions.size()) {
				int innerIndex = 0;
				int linkIndex = 0; 
				while (innerIndex < intentions.size()) {
					if (innerIndex != index) {
						links.add(addLink(combineArray[linkIndex], intentions.get(index), 
								intentions.get(innerIndex), pointX[innerIndex], pointY[innerIndex]));
						linkIndex++;
					} 
					innerIndex++;
				}
				index++;
			}
			
			
//			/*** Testing if the links and intentions are added right before collapsing.****/
//			assertTrue("Testing if actor " + actorsFromList + " has the same amount of intentions as added",
//					actorModel.getIntentions().size() == intentions.size());
//			for (SWTBotGefEditPart intention : intentions) {
//				DecorationNodeImpl intentionNode = (DecorationNodeImpl) intention.part().getModel();
//		    	IntentionImpl intentionModel = (IntentionImpl) intentionNode.getElement();
//		    	System.out.println(intentionModel.getDecompositionsFrom().size() + " " + intentionModel.getContributesTo().size() + " " + intentionModel.getDependencyFrom().size() + " " + (intentions.size() - 1));
//		    	assertTrue("Testing if intention " + intentionModel.getName() + " has the same amount of links added"  ,
//		    			intentionModel.getDecompositionsFrom().size() + intentionModel.getContributesTo().size() + intentionModel.getDependencyFrom().size()
//		    			== intentions.size() - 1);
//			}
			
			
			/*** Collapsing the actor.***/
			actors.parent().select();
	    	actors.parent().click(new Point(10,10));
	    	sleep(100);
	    	
	    	/***Expanding the actor.****/
			actors.parent().select();
	    	actors.parent().click(new Point(10,10));
	    	sleep(100);
	    	
			editor.clear();
			editor.select(actors.parent());
			editor.clickContextMenu("Delete from Model");
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
     * Helper function to test hard links.
     * @param linkName
     * @param intentionModel
     */
    public void hardLinksTestingHelperFunction(SWTBotGefEditPart link, String linkName, IntentionImpl intentionModel, SWTBotGefEditPart intention) {
    	if (linkName.equals("Dependency")) {
    		DependencyImpl impl = (DependencyImpl) intentionModel.getDependencyFrom().get(0);
    		((ConnectorImpl) link.part().getModel()).getElement();
    		assertTrue("", ((GraphicalEditPart) intention.part().getParent()).getSourceConnections().get(0) instanceof DependencyEditPart);
    		assertTrue(intentionModel.getDependencyFrom().size() == 1);
    		assertTrue(intentionModel.getDependencyTo().size() == 1);
    		
    		assertTrue("Testing if the " + intentionModel.getName() + " has " + linkName + " link.", 
    				((DependencyImpl)intentionModel.getDependencyFrom().get(0)).equals((DependencyImpl)((ConnectorImpl) link.part().getModel()).getElement()));
    		assertTrue("Testing if the " + intentionModel.getName() + " has " + linkName + " link.", 
    				((DependencyImpl)intentionModel.getDependencyTo().get(0)).equals((DependencyImpl)((ConnectorImpl) link.part().getModel()).getElement()));
    	} else if (linkName.equals("Decomposition")){
    		assertTrue("", ((GraphicalEditPart) intention.part().getParent()).getSourceConnections().get(0) instanceof AndDecompositionEditPart);
    		assertTrue(intentionModel.getDecompositionsFrom().size() == 1);
    		assertTrue(intentionModel.getDecompositionsTo().size() == 1);
    		
    		assertTrue("Testing if the " + intentionModel.getName() + " has " + linkName + " link.", 
    				((AndDecompositionImpl)intentionModel.getDecompositionsFrom().get(0)).equals((AndDecompositionImpl)((ConnectorImpl) link.part().getModel()).getElement()));
    		assertTrue("Testing if the " + intentionModel.getName() + " has " + linkName + " link.", 
    				((AndDecompositionImpl)intentionModel.getDecompositionsTo().get(0)).equals((AndDecompositionImpl)((ConnectorImpl) link.part().getModel()).getElement()));
    		
    	} else if (linkName.equals("Means-ends")) {
    		
       		assertTrue("", ((GraphicalEditPart) intention.part().getParent()).getSourceConnections().get(0) instanceof OrDecompositionEditPart);
       		assertTrue(intentionModel.getDecompositionsFrom().size() == 1);
    		assertTrue(intentionModel.getDecompositionsTo().size() == 1);
    		
    		assertTrue("Testing if the " + intentionModel.getName() + " has " + linkName + " link.", 
    				((OrDecompositionImpl)intentionModel.getDecompositionsFrom().get(0)).equals((OrDecompositionImpl)((ConnectorImpl) link.part().getModel()).getElement()));
    		assertTrue("Testing if the " + intentionModel.getName() + " has " + linkName + " link.", 
    				((OrDecompositionImpl)intentionModel.getDecompositionsTo().get(0)).equals((OrDecompositionImpl)((ConnectorImpl) link.part().getModel()).getElement()));
    	}
    }
    
    /**
     * Helper function to test if the intention model has the specified soft links with the linkName
     * @param intentionModel
     * @param linkName
     */
    public void softLinkTestingHelperFunction(IntentionImpl intentionModel, String linkName, DecorationNodeImpl link) {
    	editor.clear();
    	if (linkName.equals("Means-ends")) {
    		assertTrue("Testing if the " + intentionModel.getName() + " has " + linkName + " link.", 
    				((OrDecompositionImpl)intentionModel.getDecompositionsFrom().get(0)).equals((OrDecompositionImpl)link.getElement()));
    		assertTrue("Testing if the " + intentionModel.getName() + " has " + linkName + " link.", 
    				((OrDecompositionImpl)intentionModel.getDecompositionsTo().get(0)).equals((OrDecompositionImpl)link.getElement()));
    	} else if (linkName.equals("Decomposition")) {
    		assertTrue("Testing if the " + intentionModel.getName() + " has " + linkName + " link.", 
    				((AndDecompositionImpl)intentionModel.getDecompositionsFrom().get(0)).equals((AndDecompositionImpl)link.getElement()));
    		assertTrue("Testing if the " + intentionModel.getName() + " has " + linkName + " link.", 
    				((AndDecompositionImpl)intentionModel.getDecompositionsTo().get(0)).equals((AndDecompositionImpl)link.getElement()));
    	} else if (linkName.equals("Make")) {
    		assertTrue("Testing if the " + intentionModel.getName() + " has " + linkName + " link.",
    				((MakeContributionImpl)(intentionModel.getContributesFrom().get(0))).equals((MakeContributionImpl)link.getElement()));
    		assertTrue("Testing if the " + intentionModel.getName() + " has " + linkName + " link.", 
    				((MakeContributionImpl)(intentionModel.getContributesTo().get(0))).equals((MakeContributionImpl)link.getElement()));
    	} else if (linkName.equals("Some+")) {
    		assertTrue("Testing if the " + intentionModel.getName() + " has " + linkName + " link.", 
    				((SomePlusContributionImpl)intentionModel.getContributesFrom().get(0)).equals((SomePlusContributionImpl)link.getElement()));
    		assertTrue("Testing if the " + intentionModel.getName() + " has " + linkName + " link.", 
    				((SomePlusContributionImpl)intentionModel.getContributesTo().get(0)).equals((SomePlusContributionImpl)link.getElement()));
    	} else if (linkName.equals("Help")) {
    		assertTrue("Testing if the " + intentionModel.getName() + " has " + linkName + " link.", 
    				((HelpContributionImpl)intentionModel.getContributesFrom().get(0)).equals((HelpContributionImpl)link.getElement()));
    		assertTrue("Testing if the " + intentionModel.getName() + " has " + linkName + " link.", 
    				((HelpContributionImpl)intentionModel.getContributesTo().get(0)).equals((HelpContributionImpl)link.getElement()));
    	} else if (linkName.equals("Unknown")) {
    		assertTrue("Testing if the " + intentionModel.getName() + " has " + linkName + " link.", 
    				((UnknownContributionImpl)intentionModel.getContributesFrom().get(0)).equals((UnknownContributionImpl)link.getElement()));
    		assertTrue("Testing if the " + intentionModel.getName() + " has " + linkName + " link.", 
    				((UnknownContributionImpl)intentionModel.getContributesTo().get(0)).equals((UnknownContributionImpl)link.getElement()));
    	} else if (linkName.equals("Hurt")) {
    		assertTrue("Testing if the " + intentionModel.getName() + " has " + linkName + " link.", 
    				((HurtContributionImpl)intentionModel.getContributesFrom().get(0)).equals((HurtContributionImpl)link.getElement()));
    		assertTrue("Testing if the " + intentionModel.getName() + " has " + linkName + " link.", 
    				((HurtContributionImpl)intentionModel.getContributesTo().get(0)).equals((HurtContributionImpl)link.getElement()));
    	} else if (linkName.equals("Some-")) {
    		assertTrue("Testing if the " + intentionModel.getName() + " has " + linkName + " link.", 
    				((SomeMinusContributionImpl)intentionModel.getContributesFrom().get(0)).equals((SomeMinusContributionImpl)link.getElement()));
    		assertTrue("Testing if the " + intentionModel.getName() + " has " + linkName + " link.", 
    				((SomeMinusContributionImpl)intentionModel.getContributesTo().get(0)).equals((SomeMinusContributionImpl)link.getElement()));
    	} else if (linkName.equals("Unknown")) {
    		assertTrue("Testing if the " + intentionModel.getName() + " has " + linkName + " link.", 
    				((UnknownContributionImpl)intentionModel.getContributesFrom().get(0)).equals((UnknownContributionImpl)link.getElement()));
    		assertTrue("Testing if the " + intentionModel.getName() + " has " + linkName + " link.", 
    				((UnknownContributionImpl)intentionModel.getContributesTo().get(0)).equals((UnknownContributionImpl)link.getElement()));
    	} else if (linkName.equals("Break")) {
    		assertTrue("Testing if the " + intentionModel.getName() + " has " + linkName + " link.", 
    				((BreakContributionImpl)intentionModel.getContributesFrom().get(0)).equals((BreakContributionImpl)link.getElement()));
    		assertTrue("Testing if the " + intentionModel.getName() + " has " + linkName + " link.", 
    				((BreakContributionImpl)intentionModel.getContributesTo().get(0)).equals((BreakContributionImpl)link.getElement()));
    	} else if (linkName.equals("AND")) {
    		assertTrue("Testing if the " + intentionModel.getName() + " has " + linkName + " link.", 
    				((AndContributionImpl)intentionModel.getContributesFrom().get(0)).equals((AndContributionImpl)link.getElement()));
    		assertTrue("Testing if the " + intentionModel.getName() + " has " + linkName + " link.", 
    				((AndContributionImpl)intentionModel.getContributesTo().get(0)).equals((AndContributionImpl)link.getElement()));
    	} else if (linkName.equals("OR")) {
    		assertTrue("Testing if the " + intentionModel.getName() + " has " + linkName + " link.", 
    				((OrContributionImpl)intentionModel.getContributesFrom().get(0)).equals((OrContributionImpl)link.getElement()));
    		assertTrue("Testing if the " + intentionModel.getName() + " has " + linkName + " link.", 
    				((OrContributionImpl)intentionModel.getContributesTo().get(0)).equals((OrContributionImpl)link.getElement()));
    	}
    }
    
}

package edu.toronto.cs.openome.swtbottesting;

import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withRegex;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withLabel;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withText;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEclipseEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.keyboard.Keyboard;
import org.eclipse.swtbot.swt.finder.keyboard.KeyboardFactory;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.utils.SWTUtils;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEclipseEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.keyboard.Keyboard;
import org.eclipse.swtbot.swt.finder.keyboard.KeyboardFactory;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.utils.SWTUtils;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.hamcrest.Matcher;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.toronto.cs.openome_model.Container;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Model;
import edu.toronto.cs.openome_model.diagram.edit.parts.ActorNameEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart.ResourceSVGFigure;


/**
 * These tests deal with the Highlighting functionality 
 * NOTE: This test suite is incomplete
 * -Test UnHighlighting
 * -Test Highlighting and UnHighlighting for intentions inside all actor types 
 * 
 * @author University of Toronto
 * 
 */

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestHighlighting extends SWTBotGefTestCase {

	/** The types of highlight actions - DO NOT ALTER **/
	private static final String LEAF = "As Leaf";
	private static final String ROOT = "As Root";
	private static final String UNHIGHLIGHT = "UnHighlight";
	private static final String HIGHLIGHT = "Highlight"; 
	
	private static SWTWorkbenchBot bot;
	private static SWTGefBot gefBot;
	private static SWTBotGefEditor editor;

	private SWTBotGefEditPart view;
	private Model model;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestUtil.initializeWorkspace();
	}

	@Before
	public void setUpBeforeTest() throws Exception {
		TestUtil.createAndOpenFile();
		editor = new SWTGefBot().gefEditor("test.ood");
		model = TestUtil.getModel(editor);
		view = editor.mainEditPart();
	}

	@After
	public void tearDownAfterTest() throws Exception {
		TestUtil.closeAndDeleteFile();
	}

	//I tried to put this into a for loop but it was causing too many problems with clicking the context menu
	
	/**
	 * Tests if highlighting as leaf and unhighlighting works for hardgoals 
	 */
	@Test
	public void testHighlightHardgoalAsLeaf() {
		
		createElement("Hardgoal", 0, 0, "Hardgoal");
		
		editor.clickContextMenu(LEAF);
		Intention leaf = model.getAllIntentions().get(0);
		assertTrue("Hardgoal could not be highlighted as a leaf", leaf.isLeaf());
		
		//editor.clickContextMenu(UNHIGHLIGHT);
		//assertTrue("Hardgoal could not be unhighlighted as a leaf", !leaf.isLeaf());	
	}
	
	/**
	 * Tests if highlighting as leaf and unhighlighting works for softgoals 
	 */
	@Test
	public void testHighlightSoftgoalAsLeaf() {
		
		createElement("Softgoal", 0, 0, "Softgoal");
		
		editor.clickContextMenu(LEAF);
		Intention leaf = model.getAllIntentions().get(0);
		assertTrue("Softgoal could not be highlighted as a leaf", leaf.isLeaf());

	}
	
	/**
	 * Tests if highlighting as leaf and unhighlighting works for tasks 
	 */
	@Test
	public void testHighlightTaskAsLeaf() {
		
		createElement("Task", 0, 0, "Task");
		
		editor.clickContextMenu(LEAF);
		Intention leaf = model.getAllIntentions().get(0);
		assertTrue("Task could not be highlighted as a leaf", leaf.isLeaf());

	}
	
	/**
	 * Tests if highlighting as leaf and unhighlighting works for resources 
	 */
	@Test
	public void testHighlightResourceAsLeaf() {
		
		createElement("Resource", 0, 0, "Resource");
		
		editor.clickContextMenu(LEAF);
		Intention leaf = model.getAllIntentions().get(0);
		assertTrue("Resource could not be highlighted as a leaf", leaf.isLeaf());

	}
	
	/**
	 * Tests if highlighting as leaf and unhighlighting works for hardgoals 
	 */
	@Test
	public void testHighlightHardgoalAsRoot() {
		
		createElement("Hardgoal", 0, 0, "Hardgoal");
		
		editor.clickContextMenu(ROOT);
		Intention root = model.getAllIntentions().get(0);
		assertTrue("Hardgoal could not be highlighted as a root", root.isLeaf());

	}
	
	/**
	 * Tests if highlighting as leaf and unhighlighting works for hardgoals 
	 */
	@Test
	public void testHighlightSoftgoalAsRoot() {
		
		createElement("Softgoal", 0, 0, "Softgoal");
		
		editor.clickContextMenu(ROOT);
		Intention root = model.getAllIntentions().get(0);
		assertTrue("Softgoal could not be highlighted as a root", root.isRoot());

	}
	
	/**
	 * Tests if highlighting as leaf and unhighlighting works for hardgoals 
	 */
	@Test
	public void testHighlightTaskAsRoot() {
		
		createElement("Task", 0, 0, "Task");
		
		editor.clickContextMenu(ROOT);
		Intention root = model.getAllIntentions().get(0);
		assertTrue("Resource could not be highlighted as a root", root.isRoot());

	}
	
	/**
	 * Tests if highlighting as leaf and unhighlighting works for resources 
	 */
	@Test
	public void testHighlightResourceAsRoot() {
		
		createElement("Resource", 0, 0, "Resource");
		
		editor.clickContextMenu(ROOT);
		Intention root = model.getAllIntentions().get(0);
		assertTrue("Resource could not be highlighted as a root", root.isRoot());

	}

	
	/**
	 * A general method for creating actors and intentions for test cases.
	 * 
	 * @param item
	 *            The type of element to be created
	 * @param x
	 *            The x coordinate to place element at
	 * @param y
	 *            The y coordinate to place element at
	 * @param name The label of the element 
	 */
	public void createElement(String item, int x, int y, String name) {


		// Create an element
		editor.activateTool(item).click(x, y);

		editor.rootEditPart().activateDirectEdit();

		try {
			editor.directEditType(name);
		} catch (Throwable t) {
			System.err.println("Unable to create '" + item + " with label " + name);
		}

		editor.clear();
		editor.getEditPart(name).parent().select();

	}
	
	/**
	 * Clean-up method to clear the canvas - used when you want to clear the
	 * canvas while wanting to be in the same test case. NOTE: This may not work
	 * when using with Cut since it is not working properly (ie. not deleting
	 * from model)
	 */
	public void cleanUp() {

		editor.select(view.children());
        editor.clickContextMenu("Delete from Model");

		assertTrue("Delete from Model not working", model.eContents().isEmpty()
				&& view.children().isEmpty());

	}

}

package edu.toronto.cs.openome.swtbottesting;

import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withRegex;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withLabel;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withText;
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

/**
 * These tests deal with edit actions such as Cut, Copy, and Paste.
 * 
 * @author University of Toronto
 * 
 */

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestEditActions extends SWTBotGefTestCase {

	/** The types of edit actions - DO NOT ALTER **/
	private static final String CUT = "Cut";
	private static final String COPY = "Copy";
	private static final String PASTE = "Paste";

	/** A place holder to store the current element being handled **/
	private static String current = null;
	// NOTE: Be careful when using this. Should be updated and cleaned up
	// meticulously - especially when dealing with links.
	// This is mainly used to give a more specific error message

	/** Represents elements in a 2D Array **/
	private static String[][] allElem = { TestUtil.intentions, TestUtil.actors };
	private static String[][] allLinks = { TestUtil.hardlinks,
			TestUtil.contributions, TestUtil.associations };

	/** The two methods used to perform edit actions **/
	private static String[] methods = { "context", "shortcut" };

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

	/**
	 * Tests if Cut functionality works on free-standing intentions and actors
	 * (ie. no intentions within an actor)
	 */
	@Test
	public void testCutElem() {

		for (String[] type : allElem) {
			for (String element : type) {
				for (String method : methods) {

					createElement(element, 0, 0);

					// Record what was created
					List<SWTBotGefEditPart> viewBefore = view.children();
					List<EObject> modelBefore = model.eContents();

					// Ensure element has been removed from model & view
					performAction(method, CUT);
					assertTrue(
							element.toString()
									+ " was not successfully removed from the view using Cut via "
									+ method, view.children().isEmpty());
					assertTrue(
							element.toString()
									+ " was not successfully removed from the model using Cut via "
									+ method, model.eContents().isEmpty());

					// Ensure the correct items were placed on the clipboard
					performAction(method, PASTE);
					assertTrue(
							element
									+ " was not successfully placed on system clipboard using Cut via "
									+ method, isViewEqual(viewBefore, view
									.children())
									&& model.eContents().equals(modelBefore));

					cleanUp();

				}
			}
		}
	}

	/**
	 * Tests if Cut functionality works on intentions that are linked by any
	 * type of link
	 * 
	 */
	@Test
	public void testCutLinkedElems() {

		for (String[] type : allLinks) {
			for (String link : type) {
				for (String method : methods) {
					current = link;

					// Set-up for testing links - create two intentions to link
					createElement("Hardgoal", 0, 0);
					createElement("Softgoal", 200, 0);
					assertTrue("Set-up for intentions to link unsuccessful",
							model.eContents().size() == 2
									&& view.children().size() == 2);

					editor.activateTool(link);
					editor.drag("Hardgoal", 200, 0);

					// Record what is currently in the canvas prior to Cut
					List<SWTBotGefEditPart> viewBefore = view.children();
					List<EObject> modelBefore = model.eContents();

					// Ensure linked elements are gone from model & view
					editor.select(view.children());
					performAction(method, CUT);

					assertTrue(
							"Elements linked by "
									+ link
									+ " were not successfully removed from the view using Cut via "
									+ method, view.children().isEmpty());
					assertTrue(
							"Elements linked by "
									+ link
									+ " were not successfully removed from the model using Cut via "
									+ method, model.eContents().isEmpty());

					// Ensure the correct items were placed on clipboard
					performAction(method, PASTE);
					assertTrue(
							"Elements linked by "
									+ link
									+ " was not successfully placed on system clipboard using Cut via "
									+ method, model.eContents().equals(
									modelBefore)
									&& isViewEqual(viewBefore, view.children()));

					cleanUp();

				}
			}
		}
	}

	/**
	 * Tests if Cut functionality works for actors with several intentions and
	 * connections inside
	 */
	@Test
	public void testCutActorWithElementsInside() {

		for (String actor : TestUtil.actors) {
			for (String method : methods) {

				createElement(actor, 0, 0);

				// Hard-code contents inside actor
				createElement("Hardgoal", 170, 10);
				createElement("Softgoal", 20, 180);
				editor.activateTool("Decomposition");
				editor.drag("Hardgoal", 20, 180);

				// Record what is currently in the canvas prior to Cut
				List<SWTBotGefEditPart> viewBefore = view.children();
				List<EObject> modelBefore = model.eContents();

				// Ensure actor was removed from model & view
				editor.doubleClick(editor.getEditPart(actor));
				/**
				 * NOTE: For some reason, CUT doesn't happen successfully with
				 * shortcut method in this test
				 **/
				performAction(method, CUT);

				assertTrue(
						actor
								+ " with elements inside was not removed from model properly using Cut via "
								+ method, model.getContainers().isEmpty());
				assertTrue(
						actor
								+ " with elements inside was not removed from view properly using Cut via "
								+ method, view.children().isEmpty());

				// Check if clipboard contents reflects Cut action
				performAction(method, PASTE);
				assertTrue(
						actor
								+ " with elements inside was not successfully placed onto system clipboard using Cut via "
								+ method, model.eContents().equals(modelBefore)
								&& isViewEqual(viewBefore, view.children()));

				cleanUp();

			}
		}
	}

	/**
	 * Tests if Cut functionality works on multiple elements
	 * @throws InterruptedException 
	 **/
	@Test
	public void testCutMultipleIntentions() throws InterruptedException {

		for (String method : methods) {

			// Set-up
			createElement("Hardgoal", 170, 10);
			createElement("Softgoal", 170, 170);
			createElement("Task", 320, 180);
			createElement("Resource", 170, 90);

			// Record what is on canvas
			List<SWTBotGefEditPart> viewBefore = view.children();
			List<Intention> modelBefore = model.getIntentions();

			editor.select(view.children());
			performAction(method, CUT);
			Thread.sleep(100);
			// Ensure intentions are gone from model & view
			assertTrue(
					"Multiple intentions were not removed from model using Cut via "
							+ method, model.eContents().isEmpty());
			assertTrue(
					"Multiple intentions were not removed from view using Cut via "
							+ method, view.children().isEmpty());

			// Ensure correct items were placed on system clipboard
			performAction(method, PASTE);
			assertTrue(
					"Multiple intentions were not placed on system clipboard properly using Cut via "
							+ method, modelBefore.equals(model.getIntentions())
							&& isViewEqual(viewBefore, view.children()));

			cleanUp();

		}
	}

	/**
	 * Tests Copy & Paste for all elements
	 */
	@Test
	public void testCopyPasteElem() {

		for (String[] type : allElem) {
			for (String element : type) {
				for (String method : methods) {

					createElement(element, 0, 0);
					assertTrue(element + " was not added onto canvas", model
							.eContents().size() == 1
							&& view.children().size() == 1);

					// Record what is on canvas
					List<SWTBotGefEditPart> beforeView = view.children();
					List<EObject> beforeModel = model.eContents();

					performAction(method, COPY);
					// Clean up canvas for Paste
					cleanUp();

					// Check if what was copied reflects what has been pasted
					performAction(method, PASTE);

					assertTrue(
							element
									+ " was not Copied and Pasted properly on model via "
									+ method, beforeModel.equals(model
									.eContents()));
					assertTrue(
							element
									+ " was not Copied and Pasted properly on view via "
									+ method, isViewEqual(beforeView, view
									.children()));

					cleanUp();

				}
			}
		}
	}

	@Test
	public void testCopyPasteActorWithElementsInside() {

		for (String actor : TestUtil.actors) {
			for (String method : methods) {

				createElement(actor, 0, 0);

				// Hard-code contents inside actor
				createElement("Hardgoal", 170, 10);
				createElement("Softgoal", 20, 180);
				editor.activateTool("Decomposition");
				editor.drag("Hardgoal", 20, 180);

				// Record what is on canvas
				List<SWTBotGefEditPart> beforeView = view.children();
				List<EObject> beforeModel = model.eContents();

				// Ensure actor was removed from model & view
				editor.doubleClick(editor.getEditPart(actor));
				performAction(method, COPY);
				
				// Clean up canvas for paste
				cleanUp();

				// Check if what was copied reflects what has been pasted
				performAction(method, PASTE);
				assertTrue(
						actor
								+ " with elements inside was not Copied and Pasted properly on model via "
								+ method, beforeModel.equals(model.eContents()));
				assertTrue(
						actor
								+ " with elements inside was not Copied and Pasted properly on view via "
								+ method, isViewEqual(beforeView, view
								.children()));

				cleanUp();

			}
		}

	}

	@Test
	public void testCopyPasteMultipleIntentions() {
		
		for (String method : methods) {

			// Set-up
			createElement("Hardgoal", 170, 10);
			createElement("Softgoal", 170, 170);
			createElement("Task", 320, 180);
			createElement("Resource", 170, 90);

			// Record what is on canvas
			List<SWTBotGefEditPart> viewBefore = view.children();
			List<Intention> modelBefore = model.getIntentions();

			editor.select(view.children());
			performAction(method, COPY);

			//Clean up canvas for paste
			cleanUp();

			// Check if what was copied reflects what has been pasted
			performAction(method, PASTE);
			assertTrue(
					"Multiple intentions were not copied and pasted properly via "
							+ method, modelBefore.equals(model.getIntentions())
							&& isViewEqual(viewBefore, view.children()));

			cleanUp();

		}
		
	}

	/**
	 * Compares two objects of type <code>List</code> and checks if the items
	 * inside are of the same class. The regular <method>isequal</method> does
	 * not function for the purpose of the test cases because it compares the
	 * <code>Impl</code> as well - which differs for the objects - but we don't
	 * need this to be the same in the tests.
	 * 
	 * @param parts
	 *            A <code>List</code> of <code>SWTBotGefEditPart</code> objects
	 * @return True if the items in the two lists have the same items it, False
	 *         otherwise
	 */
	public boolean isViewEqual(List<SWTBotGefEditPart> l1,
			List<SWTBotGefEditPart> l2) {

		if (l1.size() != l2.size() || l1 == null || l2 == null) {
			return false;
		} else if (l1.isEmpty() && l2.isEmpty()) { // Base case
			return true;
		} else { // We want the class and children (if any) to be the
			// same
			if (l1.get(0).getClass().equals(l2.get(0).getClass())
					|| l1.get(0).children().equals(l2.get(0).children())) {
				l1.remove(l1.get(0));
				l2.remove(l2.get(0));
				return isViewEqual(l1, l2);
			} else {
				return false;
			}
		}

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
	 */
	public void createElement(String item, int x, int y) {

		// Update current item being handled
		current = item;

		// Create an element
		editor.activateTool(item).click(x, y);

		editor.rootEditPart().activateDirectEdit();

		try {
			editor.directEditType(item);
		} catch (Throwable t) {
			System.err.println("Unable to create '" + item);
		}

		editor.getEditPart(item).focus();

	}

	/**
	 * A general method for performing edit actions such as Cut, Copy, and
	 * Paste. NOTE: This method assumes that the <code>SwtBotGefEditPart</code>
	 * that the action should be performed on has already been selected.
	 * 
	 * @param useMenu
	 *            Determines whether context menu or keystroke shortcut method
	 *            should be utilized
	 * @param type
	 *            The type of action that should be performed
	 */
	public void performAction(String method, String type) {

		if (method.equals("context")) {

			try {
				editor.clickContextMenu("Edit").clickContextMenu(type);
			} catch (Throwable t) {
				System.err.println("Unable to perform " + type + " on "
						+ current + ": " + t.getMessage());
			}

		} else {

			// Determine which key to use depending on action being performed
			String shortcut = null;

			if (type.equals(CUT)) {
				shortcut = "X";
			} else if (type.equals(COPY)) {
				shortcut = "C";
			} else if (type.equals(PASTE)) {
				shortcut = "V";
			} else {
				System.err.println("Invalid edit action given: " + type);
			}

			// Press the appropriate keystroke based on action type and OS
			pressKeys(shortcut);

		}
	}

	/**
	 * Determines which buttons should be pressed for the corresponding edit
	 * action.
	 * 
	 * @param actionShortcut
	 *            The shortcut for the edit action. X, C, V for Cut, Copy,
	 *            Paste, respectively.
	 */
	public void pressKeys(String actionShortcut) {

		KeyStroke key = null;

		try {
			key = KeyStroke.getInstance(actionShortcut);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Unable to find " + actionShortcut
					+ " shortcut Keystroke for: " + current);
		}

		KeyStroke command = KeyStroke.getInstance(SWT.COMMAND, 0);
		KeyStroke ctrl = KeyStroke.getInstance(SWT.CTRL, 0);

		// Check for OS to decide which keystroke would be appropriate
		String os = getOS();

		try {
			if (os.contains("mac")) {
				KeyboardFactory.getAWTKeyboard().pressShortcut(command, key);
			} else {
				KeyboardFactory.getAWTKeyboard().pressShortcut(ctrl, key);
			}
		} catch (Exception e) {
			System.err.println("Unable to perform CTRL/CMD + " + actionShortcut
					+ " on " + current + ": " + e.getCause());
		}

	}

	/**
	 * Determines computer OS name.
	 * 
	 * @return The OS name of the computer being utilized
	 */
	public String getOS() {

		String osType = null;

		try {
			osType = System.getProperty("os.name").toLowerCase();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}

		return osType;

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

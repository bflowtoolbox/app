package edu.toronto.cs.openome.swtbottesting;

import org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.finders.ContextMenuFinder;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.hamcrest.Matcher;

/**
 * A context menu wrapper to handle click correctly.
 * @author mchauvin
 */
public class SWTBotContextMenu {

	
	private Control control;
	
	/**
	 * .
	 * @param treeItem .
	 */
	public SWTBotContextMenu(final SWTBotTreeItem treeItem) {
		this.control = treeItem.widget.getParent();
		
	}
	
	/**
	 * .
	 * @param tree .
	 */
	public SWTBotContextMenu(final SWTBotTree tree) {
		this.control = tree.widget;
	}
	
	/**
	 * Click on the first menu item matching the text.
	 * 
	 * @param text the text on the context menu.
	 * @return the context menu
	 */
	@SuppressWarnings("unchecked")
	// varargs and generics doesn't mix well!
	public SWTBotContextMenu click(final String text) {
		Matcher<MenuItem> withMnemonic = WidgetMatcherFactory.withMnemonic(text);
		final Matcher<MenuItem> matcher = WidgetMatcherFactory.allOf(WidgetMatcherFactory.widgetOfType(MenuItem.class), withMnemonic);
		final ContextMenuFinder menuFinder = new FirstContextMenuFinder(control);
		
		final List<MenuItem> items = new ArrayList<MenuItem>();

		new SWTBot().waitUntil(new DefaultCondition() {
			public String getFailureMessage() {
				return "Could not find context menu with text: " + text; //$NON-NLS-1$
			}

			public boolean test() throws Exception {
				items.addAll(menuFinder.findMenus(matcher));
				return !items.isEmpty();
			}
		});
		
		MenuItem menuItem = items.get(0);
		if (!menuItem.isDisposed()) {
			 new SWTBotMenu(menuItem, matcher).click();
		} else {
			//do nothing or print something in debug
		}
		return this;
	}	
}

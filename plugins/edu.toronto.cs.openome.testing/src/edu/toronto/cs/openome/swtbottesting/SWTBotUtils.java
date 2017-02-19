
package edu.toronto.cs.openome.swtbottesting;

import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;

/**
 * Some utilities.
 * 
 * @author mchauvin
 */
public final class SWTBotUtils {
	
    /**
     * WARNING this class should move in SWTBot one day. WARNING if the text is
     * not found it will not failed this method to get disposed elements with
     * the current click on context menu SWTBot method
     * 
     * @param treeItem
     *            the current item
     * @param text
     *            the menu text
     */
    public static void clickContextMenu(final SWTBotTreeItem treeItem, final String text) {
        UIThreadRunnable.asyncExec(new VoidResult() {

            public void run() {
                final SWTBotContextMenu menu = new SWTBotContextMenu(treeItem);
                menu.click(text);
            }
        });
    }

    /**
     * WARNING this class should move in SWTBot one day. WARNING if the text is
     * not found it will not failed this method to get disposed elements with
     * the current click on context menu SWTBot method
     * 
     * @param tree
     *            the tree
     * @param text
     *            the menu text
     */
    public static void clickContextMenu(final SWTBotTree tree, final String text) {
        UIThreadRunnable.asyncExec(new VoidResult() {
            public void run() {
                final SWTBotContextMenu menu = new SWTBotContextMenu(tree);
                menu.click(text);
            }
        });
    }
}

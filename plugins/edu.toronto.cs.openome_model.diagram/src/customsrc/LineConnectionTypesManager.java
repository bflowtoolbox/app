package customsrc;

import org.eclipse.gmf.runtime.common.ui.action.ActionMenuManager;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;

public class LineConnectionTypesManager extends ActionMenuManager {

	public static final String ID = "LineConnectionTypes"; //$NON-NLS-1$
	
	
	private static class ActivityTypesManagerAction extends Action {
		public ActivityTypesManagerAction() {
			setText("Blah");
			setId(ID);
		}
		
		/**
		 * This constructor is used to build the blank action of the submenus.
		 * @param id the id of the menu
		 * @param text the text of the menu
		 */
		public ActivityTypesManagerAction(String id, String text) {
            setText(text);
            setId(id);
        }
	}
	
//	public EvalLabelTypesManager(String id, IAction actionHandler) {
//		super(id, actionHandler);
//		// TODO Auto-generated constructor stub
//	}
	
	public LineConnectionTypesManager() {
		super(ID, new ActivityTypesManagerAction(), false);
	}

}

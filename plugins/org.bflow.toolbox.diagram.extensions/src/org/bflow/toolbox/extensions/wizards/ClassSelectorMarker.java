package org.bflow.toolbox.extensions.wizards;

import org.eclipse.swt.widgets.Button;


/**
 * An <code>ClassSelectorMarker</code> is an simple memory to save
 * an class together with it's selection.
 * @author Joerg Hartmann
 * @since 0.0.7
 *
 */
public class ClassSelectorMarker {

	
	/**
	 * The class self.
	 */
	private Class<?> type;
	
	
	/**
	 * An button, which stores the selection temporary.
	 */
	private Button button;
	
	
	/**
	 * Stores the selection.
	 */
	private boolean selection;
	
	
	/**
	 * Creates this marker.
	 * @param singleClass
	 * @param button
	 */
	public ClassSelectorMarker(Class<?> singleClass, Button button){
		this.type = singleClass;
		this.button = button;
	}
	
	
	/**
	 * Returns the selection state.
	 * @return
	 */
	public boolean isSelected() {
		return selection;
	}
	
	
	/**
	 * Sets the selection state.
	 * @param state
	 */
	public void setSelection(boolean state) {
		this.selection = state;
	}
	
	
	/**
	 * Returns the type.
	 * @return
	 */
	public Class<?> getType() {
		return type;
	}
	
	
	/**
	 * Returns the button.
	 * @return
	 */
	public Button getButton() {
		return button;
	}
}

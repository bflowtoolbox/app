package org.bflow.toolbox.extensions.colorschemes;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bflow.toolbox.extensions.colorschemes.commands.ApplyColorCommand;
import org.bflow.toolbox.extensions.edit.parts.ColorChangeable;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.graphics.Color;


/**
 * An implementation of an <code>IGlobalColorSchema</code> which provides
 * element specific colors.
 * 
 * @author Joerg Hartmann
 * @since 0.0.7
 * @version 01/11/13
 */
public class OriginalColorSchema extends GlobalColorSchema {

	/**
	 * Returns an element specific background.
	 * @param type
	 * @return 
	 * 		an specific background or white if no elements matches the
	 * 		delivered class
	 * 
	 */
	public Color getBackground(Class<?> type) {
		Color background = ColorConstants.white;
		String className = getSimpleClassName(type);
		
		if(className.equals("Event")){
			background = new Color(null, 248, 0, 248);
		}
		else if(className.equals("OrganisationUnit")){
			background = new Color(null, 255, 255, 0);
		}
		else if(className.equals("BusinessObject")){
			background = ColorConstants.white;
		}
		else if(className.equals("ITSystem")){
			background = new Color(null, 223, 22, 22);
		}
		else if(className.equals("Document")){
			background = ColorConstants.white;	
		}
		else if(className.equals("Position")){
			background = new Color(null, 255, 255, 0);
		}
		else if(className.equals("Location")){
			background = new Color(null, 255, 255, 0);
		}
		else if(className.equals("TechnicalTerm")){
			background = ColorConstants.white;	
		}
		else if(className.equals("Group")){
			background = new Color(null, 255, 255, 0);
		}
		else if(className.equals("Participant")){
			background = new Color(null, 255, 255, 0);
		}
		else if(className.equals("ProcessInterface")){
			background = ColorConstants.white;	
		}
		else if(className.equals("Application")){
			background = new Color(null, 0, 255, 255);
		}
		else if(className.equals("Objective")){
			background = new Color(null, 0, 248, 0);
		}
		else if(className.equals("Product")){
			background = new Color(null, 43, 238, 135);
		}
		else if(className.equals("PersonType")){
			background = new Color(null, 255, 255, 0);
		}
		else if(className.equals("ExternalPerson")){
			background = ColorConstants.white;	
		}
		else if(className.equals("InternalPerson")){
			background = new Color(null, 255, 255, 0);
		}
		else if(className.equals("Cluster")){
			background = new Color(null, 223, 22, 22);
		}
		else if(className.equals("CardFile")){
			background = ColorConstants.white;	
		}
		else if(className.equals("File")){
			background = ColorConstants.white;	
		}
		else if(className.equals("Function")){
			background = new Color(null, 0, 248, 0);
		}
		
		return background;
	}

	
	/**
	 * Returns the foreground.
	 * Black.
	 * @param type
	 * @return
	 */
	public Color getForeground(Class<?> type) {
		return ColorConstants.black;
	}
	
	
	/**
	 * Returns an GEF command to apply this schema to all delivered edit parts.
	 * @param editParts
	 * @return
	 */
	public ApplyColorCommand getCommand(List<ColorChangeable> editParts) {		
		ApplyColorCommand cmd = new ApplyColorCommand(this);
		cmd.setElements(editParts);
		return cmd;
	}
	
	
	/**
	 * Returns an description which is used to store the schema in the 
	 * <code>DiagramStyle</code>.
	 * @return
	 */
	public String toString(){
		return "IGlobalColorSchema=o";
	}
	
	
	/**
	 * Returning the title.
	 * @return
	 */
	public String getTitle(){
		return "Original Color";
	}
	
	/**
	 * Returns a simple class name of the given class. If the class name 
	 * contains 'EditPart' this part will be removed. 
	 * <p>E.g., 'XorEditPart' --> 'Xor'</p> 
	 * 
	 * @param type Class type
	 * @return Reduced class name
	 */
	protected String getSimpleClassName(Class<?> type) {
		String className = type.getSimpleName().replace("EditPart", StringUtils.EMPTY);
		return className;
	}
}

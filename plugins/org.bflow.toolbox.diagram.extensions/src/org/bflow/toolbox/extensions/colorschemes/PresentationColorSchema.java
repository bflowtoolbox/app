package org.bflow.toolbox.extensions.colorschemes;

import java.util.List;

import org.bflow.toolbox.extensions.colorschemes.commands.ApplyColorCommand;
import org.bflow.toolbox.extensions.edit.parts.ColorChangeable;
import org.eclipse.swt.graphics.Color;

/**
 * An extension of an {@link OriginalColorSchema} which paints 
 * all elements with enhanced colors for a presentation mode.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2013-11-01
 * @version 2019-01-27 AST Brightened color about 15%
 */
public class PresentationColorSchema extends OriginalColorSchema {
	
	private static final String Title = "Presentation Mode Color";
	private static final String SchemeId = "IGlobalColorSchema=p";
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.colors.OriginialColorSchema#toString()
	 */
	@Override
	public String toString() {
		return SchemeId;
	}
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.colors.OriginialColorSchema#getTitle()
	 */
	@Override
	public String getTitle() {
		return Title;
	}
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.colors.OriginialColorSchema#getCommand(java.util.Vector)
	 */
	@Override
	public ApplyColorCommand getCommand(List<ColorChangeable> editParts) {
		ApplyColorCommand cmd = new ApplyColorCommand(this);
		cmd.setElements(editParts);
		return cmd;
	}
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.colors.OriginialColorSchema#getBackground(java.lang.Class)
	 */
	@Override
	public Color getBackground(Class<?> type) {
		Color bgColor = null;
		String className = getSimpleClassName(type);
		
		if (className.equals("Event")) {
			bgColor = ClrEvent;
		} else if(className.equals("Function") || className.equals("Objective") || className.equals("Activity1") || className.equals("Activity2")) {
			bgColor = ClrFunction;
		} else if(className.equals("Application")) {
			bgColor = ClrApplication;
		} else if(className.equals("Participant") || className.equals("Group") || className.equals("InternalPerson") ||
				className.equals("Location") || className.equals("Position") || className.equals("PersonType") ||
				className.equals("OrganisationUnit") ) {
			bgColor = ClrParticipant;
		} else if(className.equals("Cluster") || className.equals("ITSystem")) {
			bgColor = ClrCluster;
		} else if(className.equals("Product")) {
			bgColor = ClrProduct;
		}

		if (bgColor != null)
			return bgColor;
		
		return super.getBackground(type);
	}

	private static final Color ClrEvent       = new Color(null, 255, 146, 255);
	private static final Color ClrFunction    = new Color(null, 146, 255, 146);
	private static final Color ClrApplication = new Color(null, 146, 255, 255);
	private static final Color ClrParticipant = new Color(null, 255, 255, 146);
	private static final Color ClrCluster     = new Color(null, 255, 142, 142);
	private static final Color ClrProduct     = new Color(null, 121, 255, 220);

}

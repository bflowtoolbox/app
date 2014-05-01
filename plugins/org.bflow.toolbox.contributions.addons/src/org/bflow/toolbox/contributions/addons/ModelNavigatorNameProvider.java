package org.bflow.toolbox.contributions.addons;

import org.bflow.toolbox.epc.diagram.edit.parts.ANDEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.OREditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.XOREditPart;
import org.bflow.toolbox.hive.modelnavigator.INameProvider;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;

/**
 * Implements the {@link INameProvider} to support the resolving of EPC element type
 * names.
 * 
 * @author Arian Storch
 * @since 02/08/12
 *
 */
public class ModelNavigatorNameProvider implements INameProvider {

	@Override
	public String getName(IGraphicalEditPart graphicalEditPart) {
		if(graphicalEditPart instanceof XOREditPart) {
			return "XOR";
		}
		
		if(graphicalEditPart instanceof ANDEditPart) {
			return "AND";
		}
		
		if(graphicalEditPart instanceof OREditPart) {
			return "OR";
		}
		
		
		return null;
	}

}

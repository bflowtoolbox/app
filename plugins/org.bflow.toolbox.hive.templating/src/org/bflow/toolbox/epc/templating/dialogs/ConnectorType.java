package org.bflow.toolbox.epc.templating.dialogs;

import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * Helperenum for representing the bflow connectors without strings. 
 * 
 * @author Markus Schnaedelbach
 */
public enum ConnectorType {
	NOTHING, XOR, AND, OR;

	public IElementType getIElementType() {
		
		String typeId = "";
		switch (this) {
		case XOR:
			typeId = "org.bflow.toolbox.epc.diagram.XOR_2008";
			break;
		case OR:
			typeId = "org.bflow.toolbox.epc.diagram.OR_2001";
			break;
		case AND:
			typeId = "org.bflow.toolbox.epc.diagram.AND_2003";
			break;
		default:
			break;
		}
		return ElementTypeRegistry.getInstance().getType(typeId);
	}
}
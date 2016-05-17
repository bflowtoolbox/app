package org.bflow.toolbox.epc.diagram.wizards;

import org.bflow.toolbox.epc.diagram.wizards.AdditionalFunctionConditions.EpcElementTypeWrapper;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

public class AdditionalFunctionCondition {
	private EpcElementTypeWrapper type;
	private String shapeName;
	
	public AdditionalFunctionCondition(EpcElementTypeWrapper type, String shapeName) {
		this.type = type;
		this.shapeName = shapeName;
	}

	public IElementType getShapeType() {
		return type.getNodeType();
	}

	public String getShapeName() {
		return shapeName;
	}
	
	public IElementType getArcType() {
		return type.getArcType();
	}
	
	public boolean isIncoming() {
		return type.isIncoming();
	}
}
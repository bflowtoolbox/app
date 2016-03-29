package org.bflow.toolbox.epc.diagram.wizards;

import org.eclipse.gmf.runtime.emf.type.core.IElementType;

public class AdditionalFunctionCondition {
	private IElementType shapeType;
	private String shapeName;
	
	public AdditionalFunctionCondition(IElementType shapeType, String shapeName) {
		this.shapeType = shapeType;
		this.shapeName = shapeName;
	}

	public IElementType getShapeType() {
		return shapeType;
	}

	public String getShapeName() {
		return shapeName;
	}
	
}
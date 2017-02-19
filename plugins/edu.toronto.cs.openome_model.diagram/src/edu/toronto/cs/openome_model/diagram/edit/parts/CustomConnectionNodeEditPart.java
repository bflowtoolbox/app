package edu.toronto.cs.openome_model.diagram.edit.parts;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.notation.View;

public class CustomConnectionNodeEditPart extends ConnectionNodeEditPart{

	public CustomConnectionNodeEditPart(View view) {
		super(view);
	}
	
	public ConnectionAnchor getSourceConnectionAnchor(){
		return super.getSourceConnectionAnchor();
	}
	
	public ConnectionAnchor getTargetConnectionAnchor(){
		return super.getTargetConnectionAnchor();
	}

	@Override
	protected Connection createConnectionFigure() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
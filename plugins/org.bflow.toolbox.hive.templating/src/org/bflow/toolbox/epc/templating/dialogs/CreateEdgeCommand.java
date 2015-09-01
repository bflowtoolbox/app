package org.bflow.toolbox.epc.templating.dialogs;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.diagram.ui.commands.DeferredCreateConnectionViewAndElementCommand;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest;

public class CreateEdgeCommand extends DeferredCreateConnectionViewAndElementCommand {

	private String label;
	
	public CreateEdgeCommand(CreateConnectionViewAndElementRequest request, IAdaptable sourceViewAdapter, IAdaptable targetViewAdapter,
			EditPartViewer currentViewer) {
		super(request, sourceViewAdapter, targetViewAdapter, currentViewer);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getLabel() {
		return label;
	}
	
	@Override
	public void setLabel(String label) {
		this.label = label;
	}
}

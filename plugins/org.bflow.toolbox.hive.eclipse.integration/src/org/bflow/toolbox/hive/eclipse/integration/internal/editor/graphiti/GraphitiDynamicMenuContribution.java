package org.bflow.toolbox.hive.eclipse.integration.internal.editor.graphiti;

import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.widgets.Menu;

/**
 * Implements {@link ContributionItem} to add menu items to the
 * {@link GraphitiDiagramEditorProxy} dynamically at runtime.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 *
 */
public class GraphitiDynamicMenuContribution extends ContributionItem {
	
	// see org.eclipse.bpmn2.modeler.ui.commands.CreateDiagramCommand and corresponding plugin.xml
	/*
	 * Note, there may be commands that are added to an editor using an editor id 
	 * as reference (see BPMN2 Editor). Therefore we have to collect these specific 
	 * commands and have to add them on our own.
	 * 
	 * TODO Support in future releases
	 */

	/**
	 * Default constructor.
	 */
	public GraphitiDynamicMenuContribution() {
	}

	/**
	 * Creates a new instance with the given id.
	 * @param id Id to apply
	 */
	public GraphitiDynamicMenuContribution(String id) {
		super(id);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.action.ContributionItem#fill(org.eclipse.swt.widgets.Menu, int)
	 */
	@Override
	public void fill(Menu menu, int index) {
		// TODO Implement in future releases
		super.fill(menu, index);
	}

}

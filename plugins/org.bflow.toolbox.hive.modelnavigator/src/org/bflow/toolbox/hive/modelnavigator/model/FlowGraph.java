package org.bflow.toolbox.hive.modelnavigator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines some kind of graph that maps flows of processes. Each flow has an
 * edge followed by a shape. A shape can start a new graph and so one. One Graph
 * can have n flows.
 * 
 * @author Arian Storch
 * @since 03/08/12
 * 
 */
public class FlowGraph {

	private List<FlowGraphItem> flowGraphItems = new ArrayList<FlowGraphItem>();

	/**
	 * Adds a new {@link FlowGraphItem} to this graph.
	 * 
	 * @param item
	 *            New FlowGraphItem
	 */
	public void addFlowGraphItem(FlowGraphItem item) {
		flowGraphItems.add(item);
	}

	/**
	 * Returns true if this graph has no flows.
	 * 
	 * @return true if this graph has no flows
	 */
	public boolean isEmpty() {
		return flowGraphItems.isEmpty();
	}

	/**
	 * Maps this flow graph on an array of {@link FlowGraphTableRow}s.
	 * 
	 * @return Flow graph mapped on an array of FlowGraphTableRows
	 */
	public FlowGraphTableRow[] toFlowGraphTableRows() {
		return toFlowGraphTableRows(this);
	}

	/**
	 * Maps the given flow graph (and its children) on an array of
	 * FlowGraphTableRows.
	 * 
	 * @param flowGraph
	 *            Flow graph that shall be mapped
	 * @return Flow graph mapped on an array of FlowGraphTableRows
	 */
	private FlowGraphTableRow[] toFlowGraphTableRows(FlowGraph flowGraph) {
		if (flowGraph.isEmpty()) {
			return null;
		}

		List<FlowGraphTableRow> tableRows = new ArrayList<FlowGraphTableRow>();

		for (int i = 0; i < flowGraph.flowGraphItems.size(); i++) {
			FlowGraphItem flowGraphItem = flowGraph.flowGraphItems.get(i);

			FlowGraphTableRow row = new FlowGraphTableRow();

			row.setEdgeImage1(flowGraphItem.getEdgeImage());
			row.setEditPart1(flowGraphItem.getGraphicalEditPart());
			row.setName1(flowGraphItem.getName());
			row.setNodeImage1(flowGraphItem.getShapeImage());

			if (i != 0) {
				// Edge image of the next row is the same like previous one
				if (row.getEdgeImage1() == tableRows.get(i - 1).getEdgeImage1()) {
					row.setEdgeImage1(null);
				}
			}

			if (flowGraphItem.getNext() != null) {
				FlowGraphTableRow futherFlowRows[] = toFlowGraphTableRows(flowGraphItem
						.getNext());

				if (futherFlowRows != null) {
					for (int j = 0; j < futherFlowRows.length; j++) {
						FlowGraphTableRow futherRow = futherFlowRows[j];

						futherRow.forward();

						if (j != 0) {
							row = row.clone();
							row.forward();

							// Edge image of the next row is the same like
							// previous one
							if (futherRow.getEdgeImage2() == tableRows.get(
									j - 1).getEdgeImage2()) {
								futherRow.setEdgeImage2(null);
							}
						}

						row.merge(futherRow);

						tableRows.add(row);
					}
				}
			} else {
				tableRows.add(row);
			}
		}

		return tableRows.toArray(new FlowGraphTableRow[0]);
	}
}

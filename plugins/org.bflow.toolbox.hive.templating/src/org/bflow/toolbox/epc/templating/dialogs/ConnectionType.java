package org.bflow.toolbox.epc.templating.dialogs;


/**
 * This Enumeration class represents the possible connection types.
 * This is important information for the joining process of template and base model.
 * The type is dependent of the chosen base model node of the user
 * AND 
 * dependent of the chosen insert option (before/ after)! 
 * 
 * _________________________________________________________________________________
 * IF the User have chosen BEFORE-option:
 * 
 * function_0 			== User has selected a function without incoming edges.
 * function_1 			== User has selected a function with ONE incoming edges.
 * event_0 				== User has selected a event without incoming edges.
 * event_1 				== User has selected a event with ONE incoming edges.
 * function_connector_1 == User has selected a connector with ONE incoming edge AND a following function node. 
 * event_connector_1    == User has selected a connector with ONE incoming edge AND a following event node.
 * function_connector_n == User has selected a connector with more than ONE incoming edge AND a following function node.
 * event_connector_n	== User has selected a connector with more than ONE incoming edge AND a following event node.
 * _________________________________________________________________________________
 * IF the User have chosen AFTER-option:
 * 
 * function_0 			== User has selected a function without outgoing edges.
 * function_1 			== User has selected a function with ONE outgoing edges.
 * event_0 				== User has selected a event without outgoing edges.
 * event_1 				== User has selected a event with ONE outgoing edges.
 * function_connector_1 == User has selected a connector with ONE outgoing edge AND a before following function node. 
 * event_connector_1    == User has selected a connector with ONE outgoing edge AND a before following event node.
 * function_connector_n == User has selected a connector with more than ONE outgoing edge AND a before following function node.
 * event_connector_n	== User has selected a connector with more than ONE outgoing edge AND a before following event node.
 * __________________________________________________________________________________
 * Options independent types:
 * 
 * empty				== User has selected a empty place in the opened epc diagram.
 * unknown				== The user selection is not supported/ valid.
 * 
 * @author Markus Schnaedelbach
 */
public enum ConnectionType {
	function_0,
	function_1,
	event_0,
	event_1,
	function_connector_1,
	event_connector_1,
	function_connector_n,
	event_connector_n,
	empty,
	unknown;

	/**
	 * Returns true if a connector is needed for a valid joining process.
	 * @return boolean
	 */
	public boolean needsConnector() {
		return  this == ConnectionType.function_1 || this == ConnectionType.event_1 || this == ConnectionType.function_connector_1 || this == ConnectionType.event_connector_1;
	}
	
	/**
	 * Returns true if the ConnectionType indicated a dummy node in the template.
	 * @return
	 */
	public boolean isDummy() {
		return  this == ConnectionType.event_0 || this == ConnectionType.event_1 || this == ConnectionType.event_connector_1 || this == ConnectionType.event_connector_n;
	}
}

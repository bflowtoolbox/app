package org.bflow.toolbox.epc.diagram.modelwizard.utils;

import org.bflow.toolbox.epc.diagram.providers.EpcElementTypes;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

/**
 * Represents a Connector.
 * @author Arian Storch
 * @since 12/12/09
 * @version 08/02/10
 *
 */
public class Connector 
{
	private ConnectorType connectorType;
	private Connector connectedWith;
	private Color connectionColor;
	
	/**
	 * Default constructor.
	 * @param connectorType type of the connector
	 */
	public Connector(ConnectorType connectorType)
	{
		this.connectorType = connectorType;
	}
	
	/**
	 * Returns the connector type.
	 * @return connector type
	 */
	public ConnectorType getConnectorType() {
		return connectorType;
	}
	
	/**
	 * Sets the connector type.
	 * @param connectorType connector type
	 */
	public void setConnectorType(ConnectorType connectorType) {
		this.connectorType = connectorType;
	}
	
	/**
	 * Returns the connector this is connected with.
	 * @return connector or null
	 */
	public Connector getConnectedWith() {
		return connectedWith;
	}
	
	/**
	 * Sets the connector this is connected with.
	 * @param connectedWith connector or null.
	 */
	public void setConnectedWith(Connector connectedWith) {
		this.connectedWith = connectedWith;
	}
	
	/**
	 * Returns the color of the connector.
	 * @return color of the connector
	 */
	public Color getConnectionColor() {
		return connectionColor;
	}
	
	/**
	 * Sets color of the connector.
	 * @param connectionColor color of connector
	 */
	public void setConnectionColor(Color connectionColor) {
		this.connectionColor = connectionColor;
	}
	
	@Override
	public String toString() 
	{
		return connectorType.toString();
	}
	
	/**
	 * Enumeration of connector types.
	 * @author Arian Storch
	 * @since 01/12/09
	 * @version 08/02/10
	 *
	 */
	public enum ConnectorType
	{
		/**
		 * none flag
		 */
		NONE,
		
		/**
		 * single and flag
		 */
		AND_SINGLE,
		
		/**
		 * double and flag
		 */
		AND_DOUBLE,
		
		/**
		 * n and flag
		 */
		AND_N,
		
		/**
		 * single or flag
		 */
		OR_SINGLE,
		
		/**
		 * double or flag
		 */
		OR_DOUBLE,
		
		/**
		 * n or flag
		 */
		OR_N,
		
		/**
		 * single xor flag
		 */
		XOR_SINGLE,
		
		/**
		 * double xor flag
		 */
		XOR_DOUBLE,
		
		/**
		 * n xor flag
		 */
		XOR_N,
		
		/**
		 * xor iteration flag
		 */
		XOR_IT;
		
		/**
		 * Returns the image associated with the connector type.
		 * @param composite composite that holds the image.
		 * @return image associated with the connector type
		 */
		public Image getImage(Composite composite)
		{					
			String name = null;
			
			if(this == NONE)
				name = "throughput.png";
			
			if(this == AND_SINGLE || this == AND_DOUBLE || this == AND_N)
				name = "andput.png";
			
			if(this == OR_SINGLE || this == OR_DOUBLE || this == OR_N)
				name = "orput.png";
			
			if(this == XOR_SINGLE || this == XOR_DOUBLE || this == XOR_N || this == XOR_IT)
				name = "xorput.png";
		
			if(name != null)
				return new Image(composite.getDisplay(), 
						this.getClass().getResourceAsStream(Constants.IMGPACKAGE+name));
			else
				return null;
		}
		
		/**
		 * Returns the EMFIElementType represented by this connector type.
		 * @return EMF IElementType
		 */
		public IElementType getEMFIElementType()
		{			
			if(this == AND_SINGLE || this == AND_DOUBLE || this == AND_N)
				return EpcElementTypes.AND_2003;
			
			if(this == OR_SINGLE || this == OR_DOUBLE || this == OR_N)
				return EpcElementTypes.OR_2001;
			
			if(this == XOR_SINGLE || this == XOR_DOUBLE || this == XOR_N || this == XOR_IT)
				return EpcElementTypes.XOR_2008;
			
			return null;
		}
		
		/**
		 * Returns the Shortcut for the ModelWizard Connector Selection.
		 * @return EMF IElementType
		 */
		public String getShortcut() {
			switch (this) {
			case AND_SINGLE:
				return "Alt + y";
			case AND_DOUBLE:
				return "Alt + x";
			case AND_N:
				return "Alt + c";
			case OR_SINGLE:
				return "Alt + a";
			case OR_DOUBLE:
				return "Alt + s";
			case OR_N:
				return "Alt + d";
			case XOR_SINGLE:
				return "Alt + q";
			case XOR_DOUBLE:
				return "Alt + w";
			case XOR_N:
				return "Alt + c";
			case XOR_IT:
				return "Alt + r";
			case NONE:
				return "Strg + x";
			default:
				break;
			}
			return "";
		}
		
		/**
		 * Returns true if it is a SingleConnector
		 * @return
		 */
		public boolean isSingleConnector(){
			return this == AND_SINGLE || this == OR_SINGLE || this == XOR_SINGLE;
		}
	}
}

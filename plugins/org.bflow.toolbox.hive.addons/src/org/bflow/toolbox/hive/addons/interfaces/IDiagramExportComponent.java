package org.bflow.toolbox.hive.addons.interfaces;

import org.bflow.toolbox.hive.addons.core.model.IComponent;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeDescriptor;

/**
 * <p>
 * Defines the interface to the diagram export component.
 * </p>
 * <p>
 * <b>Important note:</b> You have to set an ExportDescription else you will get
 * a NullPointerException!
 * </p>
 * 
 * @author Arian Storch
 * @since 05/05/10
 * @version 07/04/13
 * 
 */
public interface IDiagramExportComponent extends IComponent {
	/**
	 * Sets the {@link IInterchangeDescriptor} used by this component.
	 * 
	 * @param exd
	 *            {@link IInterchangeDescriptor}
	 */
	public void setExportDescription(IInterchangeDescriptor exd);

	/**
	 * Returns the export description used by this component.
	 * 
	 * @return the export description
	 */
	public IInterchangeDescriptor getExportDescription();
}

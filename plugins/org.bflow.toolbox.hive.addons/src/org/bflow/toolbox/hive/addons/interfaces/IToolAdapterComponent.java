package org.bflow.toolbox.hive.addons.interfaces;

import org.bflow.toolbox.hive.addons.core.model.IComponent;

/**
 * Defines the interface for the {@link ToolAdapterInterface}.
 * @author Arian Storch
 * @since 23/10/10
 */
public interface IToolAdapterComponent extends IComponent {

	public static final int SHELL2FILE = 2;
	
	public static final int FILE2SHELL = 1;
	
	public static final int EQUAL = 0;
}

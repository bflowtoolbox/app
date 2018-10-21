package org.bflow.toolbox.hive.interchange.mif.impl;

import org.apache.velocity.tools.config.DefaultKey;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * Implements a Velocity tool for UUIDs.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2018-10-21
 *
 */
@DefaultKey("uuid")
public class VelocityUuidTool {
	/**
	 * Creates a new EMF-like UUID.
	 * @return New EMF-like UUID
	 */
	public String create() {
		return EcoreUtil.generateUUID();
	}
}

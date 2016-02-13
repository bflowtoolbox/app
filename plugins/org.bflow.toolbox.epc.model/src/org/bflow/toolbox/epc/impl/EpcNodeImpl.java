/**
 */
package org.bflow.toolbox.epc.impl;

import org.bflow.toolbox.bflow.impl.ElementImpl;
import org.bflow.toolbox.epc.EpcNode;
import org.bflow.toolbox.epc.EpcPackage;
import org.eclipse.emf.ecore.EClass;

/**
 * SuperClass for all EpcNodes
 */
public abstract class EpcNodeImpl extends ElementImpl implements EpcNode {

	protected EpcNodeImpl() {
		super();
	}

	@Override
	protected EClass eStaticClass() {
		return EpcPackage.Literals.EPC_NODE;
	}

} //AllImpl

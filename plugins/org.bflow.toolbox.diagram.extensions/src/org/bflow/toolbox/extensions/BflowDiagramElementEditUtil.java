package org.bflow.toolbox.extensions;

import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.impl.TransactionImpl;
import org.eclipse.emf.transaction.util.TransactionUtil;

/**
 * Provides methods to modify Bflow diagram elements.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-01-26
 *
 */
public class BflowDiagramElementEditUtil {
	/**
	 * Applies the specified element modification within a transaction-
	 * 
	 * @param obj      Element to modify
	 * @param value    Value to propagate
	 * @param modifyer Function to apply modification
	 * @throws Exception If an error occurs
	 */
	public static <TObject extends EObject, TValue> void modifyWithTransaction(TObject obj, TValue value,
			IModifyer<TObject, TValue> modifyer) throws Exception {
		if (obj == null) return;

		TransactionImpl tx = new TransactionImpl(
				TransactionUtil.getEditingDomain(obj.eContainer()), 
				false, 
				Collections.EMPTY_MAP
				);
		
		tx.start();
		modifyer.apply(obj, value);
		tx.commit();
	}

	/**
	 * Describes an element modification.
	 * @author Arian Storch<arian.storch@bflow.org>
	 *
	 * @param <TObject> Type of element modify
	 * @param <TValue> Type of value to propagate
	 */
	public interface IModifyer<TObject, TValue> {
		/**
		 * Signature of a method that implements the element modification.
		 * 
		 * @param obj   Element to modify
		 * @param value Propagated value
		 */
		void apply(TObject obj, TValue value);
	}
}

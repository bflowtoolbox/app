package org.bflow.toolbox.hive.libs.aprogu.collections;

import java.util.Iterator;

import org.bflow.toolbox.hive.libs.aprogu.exceptions.ArgumentNullException;
import org.bflow.toolbox.hive.libs.aprogu.lang.HTypedObject;

public class HArrayIterator<TClass> extends HTypedObject<TClass> implements Iterator<TClass> {

	private TClass[] fObjectArray;
	private int fIterationIndex = 0;
	
	public HArrayIterator(Class<TClass> typedObjectClass, TClass[] objectArray) {
		super(typedObjectClass);
		if (objectArray == null) throw new ArgumentNullException("objectArray");
		fObjectArray = objectArray;
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return fIterationIndex < fObjectArray.length;
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public TClass next() {
		return fObjectArray[fIterationIndex++];
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}

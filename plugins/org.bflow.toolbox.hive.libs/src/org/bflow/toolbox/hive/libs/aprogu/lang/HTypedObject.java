package org.bflow.toolbox.hive.libs.aprogu.lang;

import org.bflow.toolbox.hive.libs.aprogu.exceptions.ArgumentNullException;

public abstract class HTypedObject<TClass> {

	private Class<TClass> fTypedClass;
	
	public Class<TClass> TypedClass() {
		return fTypedClass;
	}
	
	protected HTypedObject(Class<TClass> typedObjectClass) {
		if (typedObjectClass == null) throw new ArgumentNullException("typedObjectClass");
		fTypedClass = typedObjectClass;
	}
	
}

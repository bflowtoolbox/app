package org.bflow.toolbox.hive.libs.aprogu.lang;

/**
 * Provides methods to perform cast operations.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2018-10-07
 *
 */
public class Cast {
	/**
	 * Performs a soft cast. Returns the given {@code object} as instance of the
	 * specified {@code TObject} when it's really an instance of it. Otherwise NULL
	 * is returned.
	 * 
	 * @param cls    Desired class
	 * @param object Object to cast
	 * @return NULL or casted instance
	 */
	public static <TObject> TObject as(Class<TObject> cls, Object object) {
		if (!cls.isInstance(object)) return null;
		return cls.cast(object);
	}
	
}

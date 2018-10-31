package org.bflow.toolbox.hive.libs.aprogu.lang;

/**
 * Describes a value reference.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2018-10-31
 *
 * @param <TObject> Type of object being referenced.
 */
public class Ref<TObject> {

	/** Referenced value */
	public TObject Value;
	
	/** Creates an instance of {@link Ref} that expresses an omitted reference */
	public static <T> Ref<T> Omit() {
		return new Ref<T>();
	}
}

package org.bflow.toolbox.hive.libs.aprogu.collections;

/**
 * Defines the interface of a selector which is used 
 * to filter items out of a list. 
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 31/10/13
 * @param <T>
 */
public interface IWhereSelector<T> {

	/**
	 * Return true, if the current item does match the where 
	 * clause. 
	 * 
	 * @param item item to proof
	 * @return true or false
	 */
	public boolean where(T item);
	
}

package util;

/** This class allows for the storage of two objects together by combining thier hash-values. */

public class Pair {
	/** The first object in the pair. */
    public Object first;
    /** The second object in the pair. */
    public Object second;

   
    /** Creates a new Pair from the specified two objects.
     *
     *  @param first	the first object in the pair
     *  @param second	the second object in the pair
     */
    public Pair(Object first, Object second) {
	this.first = first;
	this.second = second;	
    }


    /** Returns the combined hash-code of the two objects. */
    public int hashCode() {
    	if (first==null || second == null)
    		return 0;
    	return first.hashCode() ^ second.hashCode();
    }


    /** Returns whether a Pair matches this Pair, based on thier hash-codes. */
    public boolean equals(Object obj) {
	Pair b = (Pair)obj;
	if (b==null) return false;
	return first.equals(b.first) && second.equals(b.second);
    }
}

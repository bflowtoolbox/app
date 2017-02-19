package edu.toronto.cs.openome.evaluation.qualitativeinteractivereasoning;

import java.util.Collection;
import java.util.HashMap;
import java.util.Queue;
import java.util.Iterator;

import edu.toronto.cs.openome_model.Intention;

/**
 * @author jenhork
 * 
 * This class holds a queue of elements with labels that need to be propagated.
 * 
 * There is no point in writing a queue from scratch so I'm heavily inspired by this implementation of an array based queue:
 * http://www.java-tips.org/java-se-tips/java.lang/array-based-queue-implementation-in-java.html
 * 
 * This uses a different interface than the default Java Queue, so I've made some adjustments. 
 *
 */
public class LabelQueue implements Queue<Intention> {
	
	private Intention [ ] theArray;
    private int        currentSize;
    private int        front;
    private int        back;
    
    private HashMap map;
    
    private final int maxProp = 3;

    private static final int DEFAULT_CAPACITY = 20;

    public LabelQueue() {
    	theArray = new Intention[ DEFAULT_CAPACITY ];
        clear( );
        map = new HashMap();
    }
    
	/**
	 * I don't really need an iterator for this class
	 */
	public Iterator<Intention> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public int size() {
		return currentSize;
	}

	/**
	 * Add an Intention, if the array is getting full, double the length
	 */
	public boolean offer(Intention arg0) {
		if (!contains(arg0)) {
			Integer count = (Integer) map.get(arg0);
			
			if (count == null)
				count = 0;
			
			if (count <= maxProp)  {
				if( currentSize == theArray.length )
		            doubleQueue( );
		        back = increment( back );
		        theArray[ back ] = arg0;
		        currentSize++;
	        	
		        count++;
		        
		        map.put(arg0, count);
	        
	        	return true;
			}
		}
        
        return false;
	}

	/**
	 * Look at the head of the queue without removing
	 */
	public Intention peek() {
		if( isEmpty( ) )
            throw new UnderflowException( "ArrayQueue getFront" );
        return theArray[ front ];
	}

	/**
	 * Return and remove the head of the queue
	 */
	public Intention poll() {
		//System.out.println(currentSize + " " + front + " " + back);
		if( isEmpty( ) )
            throw new UnderflowException( "ArrayQueue dequeue" );
        currentSize--;
        
        Intention returnValue = theArray[ front ];
        front = increment( front );
        
        //System.out.println(currentSize + " " + front + " " + back);
        
        //System.out.println(returnValue.getIntention().getName());
        
        return returnValue;
	}

	public boolean add(Intention e) {
		return offer(e);
	}

	public Intention element() {
		return peek();
	}

	public Intention remove() {
		return poll();
	}

	public boolean addAll(Collection<? extends Intention> c) {
		Iterator<Intention> it = (Iterator<Intention>) c.iterator();
		
		while (it.hasNext())  {
			offer(it.next());
		}
		
		return true;
	}

	public void clear() {
		// TODO Auto-generated method stub
		currentSize = 0;
        front = 0;
        back = -1;
	}

	public boolean contains(Object o) {
		int oldfront = front;
				
   	 	for( int i = 0; i < currentSize; i++, front = increment( front ) )  {
   	 		if (o.equals(theArray[front]))  {
   	 			front = oldfront;
   	 			return true;
   	 		}
   	 	}
   	 	front = oldfront;
		return false;   	 	
	}

	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isEmpty() {
		return currentSize == 0;
	}

	public boolean remove(Object o) {
		if( isEmpty( ) )
            throw new UnderflowException( "ArrayQueue dequeue" );
        currentSize--;

        front = increment( front );
        return true;
	}

	public boolean removeAll(Collection<?> c) {
		Iterator<Intention> it = (Iterator<Intention>) c.iterator();
		
		while (it.hasNext())  {
			remove(it.next());
		}
		
		return true;
	}

	/**
	 * This is supposed to remove everything except what is in the collection.  I'm not sure why I would ever need it, 
	 * so I won't bother with it for now.
	 */
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Don't return the actual array, return a copy.  Maybe this is done by default?  Can't remember.
	 */
	public Intention[] toArray() {
		return theArray.clone();
	}

	/**
	 * I have no idea what this is supposed to do...
	 */
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
     * Internal method to increment with wraparound.
     * @param x any index in theArray's range.
     * @return x+1, or 0 if x is at the end of theArray.
     */
    private int increment( int x )
    {
        if( ++x == theArray.length )
            x = 0;
        return x;
    }

    /**
     * Internal method to expand theArray.
     */
    private void doubleQueue( )
    {
    	Intention[ ] newArray;

        newArray = new Intention[ theArray.length * 2 ];

            // Copy elements that are logically in the queue
        for( int i = 0; i < currentSize; i++, front = increment( front ) )
            newArray[ i ] = theArray[ front ];

        theArray = newArray;
        front = 0;
        back = currentSize - 1;
        
    }
        
    public void print() {
    	int oldfront = front;
    	 for( int i = 0; i < currentSize; i++, front = increment( front ) )
             System.out.print(theArray[front].getName() + ", ");
    	 
    	 System.out.println("");
    	 
    	 front = oldfront;
    	
    }
    
    public String toString() {
    	String str = "";
    	int oldfront = front;
    	 for( int i = 0; i < currentSize; i++, front = increment( front ) )
             str += theArray[front].getName() + ", ";
    	     	 
    	front = oldfront;
    	return str;
    }

	
	
	/**
	 * Exception class for access in empty containers
	 * such as stacks, queues, and priority queues.
	 * @author Mark Allen Weiss
	 */
	public class UnderflowException extends RuntimeException
	{
	    /**
	     * Construct this exception object.
	     * @param message the error message.
	     */
	    public UnderflowException( String message )
	    {
	        super( message );
	    }
	}

}


package util;
/*public class FastVector extends Vector {
	public FastVector() {
	}
}*/
// Due to Synchronization methods in java.util.Vector, the vectors suffer
// a lot performance, thus we should use another un/*_synchronized_*/ class with the
// similar interface as the standard Vector
// see http://bonehead.sedonageo.com/~vool/articles/ibm-199907-1.html
public class FastVector {
	protected int cnt;
	protected Object set[];
	
	// constructors
	public FastVector() {
		cnt = 0;
		set = new Object[11];
	}
	
	public FastVector(FastVector src) {
		cnt = src.cnt;
		set = new Object[cnt];
		System.arraycopy(src.set, 0, set, 0, cnt);
	}
	
	// query functions
	public //synchronized 
		int size() {
		return cnt;
	}

	public //synchronized 
		Object[] toArray() {
		return set;
	}
	
	// assume array bound check is seldom occurred
	public //synchronized 
	 	Object elementAt(int i) {
	        if (i >= cnt) {
	           throw new ArrayIndexOutOfBoundsException(i+" >= "+cnt);
        	}
        	if (set[i]==null)
        	    throw new NullPointerException("set "+i);
		try {
			return set[i];
		} catch (Exception e) {
	          if (set==null)
		      throw new NullPointerException("set");
        	   else
	             throw new ArrayIndexOutOfBoundsException(i+" < 0");
		}
	}

	public //synchronized 
		Object lastElement() {
		try {
			return set[cnt-1];
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			return null;
		}
	}
		
	// modifications
	
	public //synchronized 
	void setElementAt(Object o, int i) {
		try {
			set[i] = o;
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}		
	}
	
	public //synchronized 
	void addElement(Object o) {
		try {
			set[cnt] = o;
			cnt++;
		} catch (ArrayIndexOutOfBoundsException e) {
			Object new_vector[] = new Object[1+set.length<<1];
			System.arraycopy(set, 0, new_vector, 0, set.length);
			set = new_vector;
			set[cnt++] = o;
		}
	}
		
	public //synchronized 
	void insertElementAt(Object o, int i) {
		try {
		        System.arraycopy(set, i, set, i+1, cnt-i);
		        set[i] = o;
		        cnt++;
		} catch (ArrayIndexOutOfBoundsException e) {
			Object new_vector[] = new Object[1+set.length<<1];
			System.arraycopy(set, 0, new_vector, 0, i);
			new_vector[i] = o;
			System.arraycopy(set, i, new_vector, i+1, cnt-i);
			set = null;
			set = new_vector;
			cnt++;
		}
	}
	public //synchronized 
	void removeElementAt(int i) {
		try {
	        	int j = cnt - i - 1;
		        if (j > 0)
		            System.arraycopy(set, i+1, set, i, j);
        		cnt--;
        		set[cnt] = null; // to let gc do its work 
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();			
		}
	}

	public //synchronized 
	void removeAllElements() {
		for (int i=0; i< cnt ;i++)
			set[i] = null;
		cnt = 0;
	}
	
	public //synchronized 
	void trimToSize() {
		if (cnt<set.length) {
			Object new_vector[] = new Object[cnt];
			System.arraycopy(set, 0, new_vector, 0, cnt);
			set = new_vector;
		}
	}
	
	public void swapElements(int i, int j) {
	    try {
		Object o = set[i];
		set[i] = set[j];
		set[j] = o;
	    }catch (ArrayIndexOutOfBoundsException e) {
	    	e.printStackTrace();
	    }
	}
	
	// exchange block (I..I+B1-1) with block (J..J+B2-1)
	public void swapElements(int I, int B1, int J, int B2)
	{
		int i,j,b1,b2;
		if (B1>=B2) {
			b1 = B1;
			b2 = B2;
			i = I;
			j = J;
		} else {
			b1 = B2;
			b2 = B1;
			i = J;
			j = I;
		}
		Object o[] = new Object[b1];
		System.arraycopy(set, i, o, 0, b1);
		if (i<j) {
	    	   System.arraycopy(set, j, set, i, b2);
		   System.arraycopy(set, i+b1, set, i+b2, j-i-b2);
	 	   System.arraycopy(o, 0, set, j+b2-b1, b1);
		} else {
	    	   System.arraycopy(set, j, set, i+b1-b2, b2);
		   System.arraycopy(set, j+b2, set, j+b1, i-j-b2);
	 	   System.arraycopy(o, 0, set, j, b1);
		}
	}	
} 

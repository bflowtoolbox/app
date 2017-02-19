/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/util/TestSet.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.util;

import java.util.Enumeration;

class TestSet {
  public static void main(String args[]) {
    int num = 1;
    if (args.length >= 1) {
      num = Integer.parseInt(args[0]);
    }

    final long start = System.currentTimeMillis();
    if (args.length <= 1 || args[1].equals("Set")) {
      for(int i = 0; i<num; i++)
	test(ImmutableHashOrderSet.empty,ImmutableHashOrderSet.universe);
    } else if (args[1].equals("CachedSet")) {
      for(int i = 0; i<num; i++)
	test(CachedSet.getEmpty(),CachedSet.getUniverse());
    } else if (args[1].equals("UnionLattice")) {
      for(int i = 0; i<num; i++) {
	UnionLattice l = new UnionLattice();
	test((ImmutableHashOrderSet)l.top(),(ImmutableHashOrderSet)l.bottom());
      }
    } else if (args[1].equals("IntersectionLattice")) {
      for(int i = 0; i<num; i++) {
	IntersectionLattice l = new IntersectionLattice();
	test((ImmutableHashOrderSet)l.bottom(),(ImmutableHashOrderSet)l.top());
      }
    } else {
      System.out.println("Unknown set class " + args[1]);
    }
    System.out.println("Total time (in ms) = "+
		       (System.currentTimeMillis() - start));
  }

  public static void test(ImmutableHashOrderSet empty, ImmutableHashOrderSet univ) {
    System.out.println("Testing class " + empty.getClass().getName());
    System.out.println("Testing nice hash codes...");
    test(empty,univ,new Integer(1), new Integer(2), new Integer(3),
	 new Integer(4), new Integer(5));
    System.out.println("Testing nasty hash codes...");
    test(empty,univ,new TestSet(1), new TestSet(2), new TestSet(3),
	 new TestSet(4), new TestSet(5));
    System.out.println("Test done.");
  }

  public static void test(ImmutableHashOrderSet empty, ImmutableHashOrderSet univ, Object o1, Object o2,
			  Object o3, Object o4, Object o5)
  {
    ImmutableHashOrderSet s123 = empty.addElement(o1).addElement(o2).addElement(o3);
    ImmutableHashOrderSet s321 = empty.addElement(o3).addElement(o2).addElement(o1);
    ImmutableHashOrderSet s123321 = s123.addElement(o3).addElement(o2).addElement(o1);
    ImmutableHashOrderSet s345 = empty.addElement(o3).addElement(o4).addElement(o5);
    ImmutableHashOrderSet s34215 = empty.addElement(o3).addElement(o4).
      addElement(o2).addElement(o1).addElement(o5);
    ImmutableHashOrderSet s54 = empty.addElement(o5).addElement(o4);

    ImmutableHashOrderSet i123 = s123.invert();
    ImmutableHashOrderSet i321 = s321.invert();
    ImmutableHashOrderSet i345 = s345.invert();
    ImmutableHashOrderSet i34215 = s34215.invert();
    ImmutableHashOrderSet i54 = s54.invert();

    testsetcontains(empty,  o1,o2,o3,o4,o5,false,false,false,false,false);
    testsetcontains(univ,   o1,o2,o3,o4,o5,true, true, true, true, true);
    
    testsetcontains(s123,   o1,o2,o3,o4,o5,true, true, true, false,false);
    testsetcontains(s321,   o1,o2,o3,o4,o5,true, true, true, false,false);
    testsetcontains(s345,   o1,o2,o3,o4,o5,false,false,true, true, true);
    testsetcontains(s34215, o1,o2,o3,o4,o5,true, true, true, true, true);
    testsetcontains(s54,    o1,o2,o3,o4,o5,false,false,false,true, true);
    
    testsetcontains(i123,   o1,o2,o3,o4,o5,false,false,false,true, true);
    testsetcontains(i321,   o1,o2,o3,o4,o5,false,false,false,true, true);
    testsetcontains(i345,   o1,o2,o3,o4,o5,true, true, false,false,false);
    testsetcontains(i34215, o1,o2,o3,o4,o5,false,false,false,false,false);
    testsetcontains(i54,    o1,o2,o3,o4,o5,true, true, true, false,false);

    /*          S1       S2      S1=S2  S1>=S2 S2>=S1 S1 overlaps S2 */
    testsetrels(empty,   empty,  true,  true,  true,  false);
    testsetrels(empty,   univ,   false, false, true,  false);
    testsetrels(univ,    univ,   true,  true,  true,  true);
    testsetrels(univ,    empty,  false, true,  false, false);

    testsetrels(s123,    s321,   true,  true,  true,  true);
    testsetrels(s123321, s123,   true,  true,  true,  true);
    testsetrels(s321,    s345,   false, false, false, true);
    testsetrels(s345,    s34215, false, false, true,  true);
    testsetrels(s123,    s54,    false, false, false, false);
    testsetrels(s34215,  s54,    false, true,  false, true);

    testsetrels(s123,    i321,   false, false, false, false);
    testsetrels(s321,    i345,   false, false, false, true);
    testsetrels(s345,    i34215, false, false, false, false);
    testsetrels(s123,    i54,    false, false, true,  true);
    testsetrels(s34215,  i54,    false, false, false, true);

    testsetrels(i123,    s321,   false, false, false, false);
    testsetrels(i321,    s345,   false, false, false, true);
    testsetrels(i345,    s34215, false, false, false, true);
    testsetrels(i123,    s54,    false, true,  false, true);
    testsetrels(i34215,  s54,    false, false, false, false);

    testsetrels(i123,    i321,   true,  true,  true,  true);
    testsetrels(i321,    i345,   false, false, false, true);
    testsetrels(i345,    i34215, false, true,  false, true);
    testsetrels(i123,    i54,    false, false, false, true);
    testsetrels(i34215,  i54,    false, false, true,  true);

    ImmutableHashOrderSet s3 = s123.removeElement(o2).removeElement(o1);
    ImmutableHashOrderSet i3 = i123.addElement(o2).addElement(o1);
    ImmutableHashOrderSet s12 = empty.addElement(o1).addElement(o2);
    ImmutableHashOrderSet i12 = s12.invert();

    /*         S1       S2      S1US2   S1/\S2  S1-S2,  S2-S1 */
    testsetops(empty,   empty,  empty,  empty,  empty, empty);
    testsetops(empty,   univ,   univ,   empty,  empty, univ);
    testsetops(univ,    empty,  univ,   empty,  univ,  empty);
    testsetops(univ,    univ,   univ,   univ,   empty, empty);

    testsetops(s123,    s321,   s123321,s321,   empty,  empty);
    testsetops(s321,    s345,   s34215, s3,     s12,    s54);    
    testsetops(s345,    s34215, s34215, s345,   empty,  s12);
    testsetops(s123,    s54,    s34215, empty,  s123,   s54);
    testsetops(s34215,  s54,    s34215, s54,    s321,   empty);

    testsetops(s123,    i321,   univ,   empty,  s123,   i321);
    testsetops(s321,    i345,   i54,    s12,    s3,     i34215);
    testsetops(s345,    i34215, i12,    empty,  s345,   i34215);
    testsetops(s123,    i54,    i54,    s123,   empty,  i34215);
    testsetops(s34215,  i54,    univ,   s321,   s54,    i34215);

    testsetops(i123,    s321,   univ,   empty,  i321,   s123);
    testsetops(i321,    s345,   i12,    s54,    i34215, s3);
    testsetops(i345,    s34215, univ,   s12,    i34215, s345);
    testsetops(i123,    s54,    i123,   s54,    i34215, empty);
    testsetops(i34215,  s54,    i321,   empty,  i34215, s54);

    testsetops(i123,    i321,   i123,   i321,   empty,  empty);
    testsetops(i321,    i345,   i3,     i34215, s54,    s12);
    testsetops(i345,    i34215, i345,   i34215, s12,    empty);
    testsetops(i123,    i54,    univ,   i34215, s54,    s321);
    testsetops(i34215,  i54,    i54,    i34215, empty,  s123);

    ImmutableHashOrderSet countdown = s34215;
    try {
      Enumeration e = s34215.elements();
      while (e.hasMoreElements()) {
        countdown = countdown.removeElement(e.nextElement());
      }
      if (!countdown.equals(empty))
        System.out.println("!!!! " + countdown + " should be empty.");
    } catch (Exception e) {
      System.out.println("!!!! enumeration failed with exception.");
    }

    try {
      Enumeration e = i34215.elements();
      System.out.println("!!!! infinite set can be enumerated.");
    } catch (SetException e) {
    }
    
  }

  static void testsetcontains(ImmutableHashOrderSet s, Object o1, Object o2, Object o3,
			      Object o4, Object o5, boolean in1, boolean in2,
			      boolean in3, boolean in4, boolean in5) {
    Object os[] = new Object[]{o1,o2,o3,o4,o5};
    boolean bs[] = new boolean[]{in1,in2,in3,in4,in5};
    for (int i=0; i < 5; ++i) {
      if (s.contains(os[i]) != bs[i]) {
	System.out.println("!!!! " + s +
			   (bs[i] ? " does not contain " : " contains ")
			   + os[i]);
      }
    }
  }

  static void testsetrels(ImmutableHashOrderSet s1, ImmutableHashOrderSet s2, boolean equal, boolean supset,
			  boolean subset, boolean overlap) {
    if (s1.equals(s2) != equal) {
      System.out.println("!!!! " + s1 + (equal?" != ":" = ") + s2);
    }
    if (s1.includes(s2) != supset) {
      System.out.println("!!!! " + s1 + (supset?" !>= ":" >= ") + s2);
    }
    if (s2.includes(s1) != subset) {
      System.out.println("!!!! " + s1 + (subset?" !<= ":" <= ") + s2);
    }
    if (s2.overlaps(s1) != overlap) {
      System.out.println("!!!! " + s1 + (overlap?" disj ":" not disj ") + s2);
    }
  }

  static void testsetops(ImmutableHashOrderSet s1, ImmutableHashOrderSet s2, ImmutableHashOrderSet union, ImmutableHashOrderSet intersection,
			 ImmutableHashOrderSet diff1, ImmutableHashOrderSet diff2) {
    testsetopresult("\\/",s1,s2,s1.union(s2),union);
    testsetopresult("\\/",s2,s1,s2.union(s1),union);
    testsetopresult("/\\",s1,s2,s1.intersect(s2),intersection);
    testsetopresult("/\\",s2,s1,s2.intersect(s1),intersection);
    testsetopresult("-",s1,s2,s1.difference(s2),diff1);
    testsetopresult("-",s2,s1,s2.difference(s1),diff2);
  }

  static void testsetopresult(String op, ImmutableHashOrderSet s1, ImmutableHashOrderSet s2,
			      ImmutableHashOrderSet result, ImmutableHashOrderSet expected) {
    if (!result.equals(expected)) {
      System.out.println("!!!! " + s1 + " " + op + " " + s2 + " = " +
			 result + " != " + expected);
    }
  }

  private final int val;
  TestSet(int i) { val = i; }
  /* A useless hashCode: */
  public int hashCode() { return 1; /* also try val/3 */ }
  public boolean equals(Object other) {
    return (other instanceof TestSet) && (this.val == ((TestSet)other).val);
  }
  public String toString() { return String.valueOf(val); }
}



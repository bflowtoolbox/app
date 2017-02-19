package fluid.unparse;



class Position {
  // Location of an AstLoc within its parent
  //    Used for two purposes: (1) Bracketing (middle vs edge)
  //		(2) Walking up the tree (this only partially implemented).
  // Immutable
  boolean seq;
  int pos;
  static Position THERIGHT = new Position(1, false);
  static Position THELEFT = new Position(0, false);
  static Position THEMIDDLE = new Position(-1, false);
  // Above for SomeFix

  static Position THEROOT = new Position(2, false);
  // Above for the root of the Ast Tree.

  static Position POS1 = new Position(1, true);
  static Position POS2 = new Position(2, true);
  static Position POS3 = new Position(3, true);
  // Above ad hoc for others

  // For lists: pos is the location in a list
  //	  Note: Multiple lists in a node will require revision.

  Position(int i) {
    seq = true;
    pos = i;
  }
  Position(int i, boolean seq) {
    this.seq = seq;
    pos = i;
  }	
  
  public String toString() {
    return
      ((!seq)?((pos==1)?"R":((pos==0)?"L":((pos==2)?"r":"M"))):("" + pos));
  }

  int getPos() {
    if (seq)
      return pos;
    throw new RuntimeException("fluid/unpar/Position non-sequence");
  }
  static Position mkPosition(int i) {
    return new Position(i);
  }
}

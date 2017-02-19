package fluid.tree;


/** An exception used to indicate problems with child arguments
 * in graphs and trees.  (This class has been made private to
 * prevent it being used outside the package.  We will be changing
 * uses of this exception into uses of various subclasses of
 * StructureException.
 */
class IllegalChildException extends StructureException {
  public IllegalChildException() { super(); }
  public IllegalChildException(String s) { super(s); }
}

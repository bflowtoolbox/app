/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/control/PrimitiveExceptionLabel.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.java.control;


public class PrimitiveExceptionLabel extends ExceptionLabel {
  public final String name;
  public PrimitiveExceptionLabel(String n) {
    name = n;
  }
  public String toString() {
    return name;
  }
  public static final PrimitiveExceptionLabel primitiveArithmeticException =
    new PrimitiveExceptionLabel("java.lang.ArithmeticException");
  public static final PrimitiveExceptionLabel primitiveArrayStoreException =
    new PrimitiveExceptionLabel("java.lang.ArrayStoreException");
  public static final PrimitiveExceptionLabel primitiveClassCastException =
    new PrimitiveExceptionLabel("java.lang.ClassCastException");
  public static final PrimitiveExceptionLabel
    primitiveIndexOutOfBoundsException =
    new PrimitiveExceptionLabel("java.lang.IndexOutOfBoundsException");
  public static final PrimitiveExceptionLabel
    primitiveNegativeArraySizeException =
    new PrimitiveExceptionLabel("java.lang.NegativeArraySizeException");
  public static final PrimitiveExceptionLabel primitiveNullPointerException =
    new PrimitiveExceptionLabel("java.lang.NullPointerException");
  public static final PrimitiveExceptionLabel assertionError =
    new PrimitiveExceptionLabel("java.lang.AssertionError");
}

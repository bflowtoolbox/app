/* $Header: /usr/local/refactoring/molhadoRef/src/sc/SCRuntimeException.java,v 1.1 2006/03/21 23:21:00 dig Exp $ */

package sc;

import fluid.FluidRuntimeException;

/**
 * SC Runtime Exception
 *
 * @author Satish Chandra Gupta
 */
public class SCRuntimeException extends FluidRuntimeException {
  public SCRuntimeException() { super(); }
  public SCRuntimeException(String s) { super(s); }
}

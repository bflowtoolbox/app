/* $Header: /usr/local/refactoring/molhadoRef/src/sc/SCError.java,v 1.1 2006/03/21 23:21:00 dig Exp $ */

package sc;

import fluid.FluidError;

/**
 * SC Error
 *
 * @author Satish Chandra Gupta
 */
public class SCError extends FluidError {
  // public 
  protected SCError() { super(); }
  public SCError(String s) { super(s); }
}

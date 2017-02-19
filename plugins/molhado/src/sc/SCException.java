/* $Header: /usr/local/refactoring/molhadoRef/src/sc/SCException.java,v 1.1 2006/03/21 23:21:00 dig Exp $ */

package sc;

import fluid.FluidException;

/**
 * SC Exception
 *
 * @author Satish Chandra Gupta
 */
public abstract class SCException extends FluidException {
  public SCException() { super(); }
  public SCException(String s) { super(s); }
}

/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/DisconnectedEraException.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.version;

import fluid.FluidRuntimeException;

/** This exception is throw if we attempt to start an era
 * in the "middle of nowehere" in the version tree.
 */
public class DisconnectedEraException extends FluidRuntimeException {
  public DisconnectedEraException() { super(); }
  public DisconnectedEraException(String s) { super(s); }
}

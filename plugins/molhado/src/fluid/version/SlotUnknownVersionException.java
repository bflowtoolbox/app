/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/SlotUnknownVersionException.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.version;

import fluid.ir.Slot;
import fluid.ir.SlotUnknownException;

/** A slot has an unknown value at a particular version. */
public class SlotUnknownVersionException extends SlotUnknownException {
    private final Version version;
    public SlotUnknownVersionException(String msg, Slot s, Version v) {
	super(msg,s);
	version = v;
    }

    public Version getVersion() { return version; }
}

/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/SlotUnknownEraException.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.version;

import fluid.ir.Slot;
import fluid.ir.SlotUnknownException;

/** A slot has an unknown value at a particular era. */
public class SlotUnknownEraException extends SlotUnknownException {
    private final Era era;
    public SlotUnknownEraException(String msg, Slot s, Era e) {
	super(msg,s);
	era = e;
    }

    public Era getEra() { return era; }
}

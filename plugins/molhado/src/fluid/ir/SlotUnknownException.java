/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/SlotUnknownException.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.ir;

import java.util.ArrayList;
import java.util.Iterator;

/** A slot's value is unknown because of incomplete information:
 * the value is not loaded, for example.  The problem cannot
 * be fixed by assignment.
 */
public class SlotUnknownException extends SlotUndefinedException
{
    public final Slot slot;

    public SlotUnknownException(String msg,Slot s) { super(msg); slot = s; }  

    public Slot getSlot() { return slot; }

    private static ArrayList handlers = new ArrayList();

    public static synchronized void addHandler(SlotUnknownHandler h) {
	handlers.add(h);
    }

    /** Try to handle this exception.
     * If the exception is handled and the access should be retried,
     * this function returns normally.  If the exception could not
     * be handled, it is thrown.
     */
    public void handle() throws SlotUnknownException
    {
	Iterator it;
	synchronized (SlotUnknownException.class) {
	    it = ((ArrayList)handlers.clone()).iterator();
	}
	while (it.hasNext()) {
	    if (((SlotUnknownHandler)it.next()).canHandle(this))
		return;
	}
	throw this;
    }

    /** Try to handle this slot being unknown.
     *  Calls @{link #handle()} with a newly
     *  created exception.
     */
    public static void raise(String mesg, Slot s)
	throws SlotUnknownException
    {
	new SlotUnknownException(mesg,s).handle();
    }
}

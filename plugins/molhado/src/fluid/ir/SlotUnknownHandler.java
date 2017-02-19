/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/ir/SlotUnknownHandler.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */

package fluid.ir;

/** A handler is asked to load information to handle
 * a slot unknown error.  If no registered handler
 * can handle the problem, an exception is raised.
 * @see SlotUnknownException
 */
public interface SlotUnknownHandler {
    /* Returns 'true' to
     * mean to retry the slot access, or 'false' to mean
     * that the handler was unable to help.  A handler must not
     * return true without being preared for being called again if 
     * the information didn't work.
     */
    public boolean canHandle(SlotUnknownException e);
}

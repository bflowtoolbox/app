package org.bflow.toolbox.epc.extensions.utils;

import java.util.ListIterator;
import java.util.Vector;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;

/**
 * Defines a command collector.
 * 
 * @author Arian Storch
 * @since 14/05/10
 * @version 31/08/10
 * 
 */
public class CommandCollection {
	private Vector<Command> stack = new Vector<Command>();

	/**
	 * Resturns the command stack.
	 * 
	 * @return command stack
	 */
	public Vector<Command> getStack() {
		return stack;
	}

	/**
	 * Undoes all commands on the stack.
	 */
	public void undoAll() {
		ListIterator<Command> it = stack.listIterator(stack.size());

		while (it.hasPrevious())
			try {
				it.previous().undo();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
	}

	/**
	 * Returns true if the command is contained by the this collection. 
	 * @param command
	 * @return true or false
	 */
	public boolean contains(CompositeCommand command) {
		ListIterator<?> iterator = command.listIterator();

		while (iterator.hasNext()) {
			Object ac = (Object) iterator.next();
			
			ListIterator<Command> subIt = stack.listIterator(stack.size());

			while(subIt.hasPrevious()) {
				Command c = subIt.previous();
				if (c instanceof CompoundCommand) {
					CompoundCommand cc = (CompoundCommand) c;

					for (Object o : cc.getCommands()) {
						if (o instanceof ICommandProxy) {
							ICommandProxy proxy = (ICommandProxy) o;
							if (proxy.getICommand().equals(ac))
								return true;
						}
					}
				}
			}
		}

		return false;
	}

	/**
	 * Returns the size of the collection.
	 * 
	 * @return size
	 */
	public int getSize() {
		return stack.size();
	}
}

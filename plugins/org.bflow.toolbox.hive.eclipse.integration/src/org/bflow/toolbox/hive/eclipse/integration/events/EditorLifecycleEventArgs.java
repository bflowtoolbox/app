package org.bflow.toolbox.hive.eclipse.integration.events;

import org.eclipse.ui.IEditorInput;

/**
 * Defines arguments of a editor lifecycle event.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 31.07.2015
 *
 */
public abstract class EditorLifecycleEventArgs {
	
	/**
	 * Returns the graphical editor that is affected by the event.
	 * @return Graphical editor
	 */
	public abstract org.eclipse.gef.ui.parts.GraphicalEditor GraphicalEditor();
	
	/**
	 * Returns the editor input that is affected by the event.
	 * @return Editor input
	 */
	public abstract IEditorInput EditorInput();
	
	/**
	 * Returns the transactional editing domain that is affected by the event.
	 * @return Transactional editing domain
	 */
	public abstract org.eclipse.emf.transaction.TransactionalEditingDomain TransactionalEditingDomain();

}

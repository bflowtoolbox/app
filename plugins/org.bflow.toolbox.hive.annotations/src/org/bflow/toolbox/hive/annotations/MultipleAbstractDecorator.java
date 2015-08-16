package org.bflow.toolbox.hive.annotations;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.AbstractDecorator;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.Decoration;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoration;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;

/**
 * This AnnotationDecorator is used to show annotation symbols. It extends the
 * AbstractDecorator (which only stores one decoration per decoratorTarget) to
 * store more than one decoration.
 * 
 * @author Felix Hoess
 * @since 02.06.2015
 *
 */
public abstract class MultipleAbstractDecorator extends AbstractDecorator{
	
	private List<Decoration> decorations;
	public MultipleAbstractDecorator(IDecoratorTarget decoratorTarget) {
		super(decoratorTarget);
		decorations = new ArrayList<Decoration>(5);
	
	}
	
	public List<Decoration> getDecorations(){
		return decorations;
	}
	

	/**
	 *  @return returns the first decoration
	 */
	@Override
	public Decoration getDecoration() {
		return decorations.get(0);
	}
	
	/**
	 * returns the decoration at the given index
	 * @param index
	 * @return
	 */
	public Decoration getDecoration(int index) {
		return decorations.get(index);
	}
	
	/**
	 * add this decoration
	 * @param decoration
	 */
	
	public void addDecoration(IDecoration decoration){
		Assert.isTrue(decoration instanceof Decoration);
		this.decorations.add((Decoration) decoration);
	}
	/**
	 * @param decoration
	 *            The decoration to set.
	 */
	@Override
	public void setDecoration(IDecoration decoration) {
		Assert.isTrue(decoration instanceof Decoration);
		if (!decorations.contains(decoration)){
			decorations.add((Decoration) decoration);
		}
		
	}
	
	/**
	 * removes all decorations of this decorator
	 */
	@Override
	protected void removeDecoration() {
		if (!decorations.isEmpty()) {
			for (IDecoration dec : decorations){
				getDecoratorTarget().removeDecoration(dec);

			}
					}
		decorations.clear();
		
	}
	
	/**
	 * Removes the decoration at the given index if it exists
	 */
	protected void removeDecoration(int index) {
		if (!decorations.isEmpty()) {
			getDecoratorTarget().removeDecoration(getDecoration(index));
			decorations.remove(index);
		}
	}
	

	
	
}

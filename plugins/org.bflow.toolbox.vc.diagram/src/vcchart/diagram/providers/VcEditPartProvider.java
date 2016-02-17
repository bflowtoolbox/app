/*
 * 
 */
package vcchart.diagram.providers;

import org.eclipse.gmf.tooling.runtime.providers.DefaultEditPartProvider;

import vcchart.diagram.edit.parts.ModelEditPart;
import vcchart.diagram.edit.parts.VcEditPartFactory;
import vcchart.diagram.part.VcVisualIDRegistry;

/**
 * @generated
 */
public class VcEditPartProvider extends DefaultEditPartProvider {

	/**
	 * @generated
	 */
	public VcEditPartProvider() {
		super(new VcEditPartFactory(), VcVisualIDRegistry.TYPED_INSTANCE,
				ModelEditPart.MODEL_ID);
	}

}

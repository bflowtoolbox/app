/*
 * 
 */
package orgchart.diagram.providers;

import org.eclipse.gmf.tooling.runtime.providers.DefaultEditPartProvider;
import orgchart.diagram.edit.parts.ModelEditPart;
import orgchart.diagram.edit.parts.OrgcEditPartFactory;
import orgchart.diagram.part.OrgcVisualIDRegistry;

/**
 * @generated
 */
public class OrgcEditPartProvider extends DefaultEditPartProvider {

	/**
	 * @generated
	 */
	public OrgcEditPartProvider() {
		super(new OrgcEditPartFactory(), OrgcVisualIDRegistry.TYPED_INSTANCE,
				ModelEditPart.MODEL_ID);
	}

}

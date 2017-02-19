package openome_model.figures.activator;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeTypes;
import org.osgi.framework.BundleContext;

/**
 * @generated
 */
public class PluginActivator extends Plugin {

	/**
	 * @generated
	 */
	public static final String ID = "openome_model.figures"; //\u0024NON-NLS-1\u0024 XXX FIXME DollarSign support

	/**
	 * @generated
	 */
	private static PluginActivator ourInstance;

	/**
	 * @generated
	 */
	public PluginActivator() {
	}

	/**
	 * @generated
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		ourInstance = this;
	}

	/**
	 * @generated
	 */
	public void stop(BundleContext context) throws Exception {
		ourInstance = null;
		super.stop(context);
	}

	/**
	 * @generated
	 */
	public static PluginActivator getDefault() {
		return ourInstance;
	}

	private IMapMode myMapMode = MapModeTypes.IDENTITY_MM;

	/**
	 * @generated
	 */
	public void setMapMode(IMapMode mapMode) {
		myMapMode = mapMode;
		if (myMapMode == null) {
			myMapMode = MapModeTypes.IDENTITY_MM;
		}
	}

	/**
	 * @generated
	 */
	public IMapMode getMapMode() {
		return myMapMode;
	}
}

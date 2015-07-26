package org.bflow.toolbox.hive.eclipse.integration;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

/**
 * Provides various reusable methods to support proxying of a diagram editor.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 25.07.2015
 *
 */
public class DiagramProxyUtil {
	
	static private Image fTitleImage;
	
	private IEditorPart fOriginEditorPart;
	private GraphicalEditor fOriginGraphicalEditor;
	private IAdaptable fAdapterRoot;
	
	/**
	 * Returns a specific image that can be used as editor image to signal that
	 * a custom editor is active.
	 * 
	 * @return Image
	 */
	public Image TitleImage() {
		return fTitleImage;
	}

	/**
	 * Performs a standardized initialization of the editor that is proxied.
	 * Returns the origin editor that is proxied to support additional
	 * customized initialization.
	 * 
	 * @param site
	 *            Current editor site
	 * @param input
	 *            Current editor input
	 * @return Origin editor that is proxied
	 * @throws PartInitException
	 */
	public IEditorPart initialize(IEditorSite site, IEditorInput input) throws PartInitException {
		String fileName = input.getName();
		IEditorPart originEditorPart = EclipseIntegrator.getOriginDefaultEditorInstance(fileName);
		if (originEditorPart == null) throw new PartInitException("Could not resolve origin editor part!");
		
		// Use this per default
		AdapterRoot(originEditorPart);
		
		// Save reference
		fOriginEditorPart = originEditorPart;
		
		// Support origin lifecycle
		fOriginEditorPart.init(site, input);
		
		// Apply custom image
		if (fTitleImage == null) {
			try (InputStream inputStream = getClass().getResourceAsStream("/images/Beejive-Im-flat-circle-16.png")) {
				Image image = new Image(Display.getCurrent(), inputStream);
				fTitleImage = image;
			} catch (IOException ex) {
				ex.printStackTrace();
			}	
		}
		
		return fOriginEditorPart;
	}
	
	/**
	 * Activates the diagram specific custom diagram action bar contributor for
	 * the currently proxied editor.
	 */
	public void applyCustomDiagramActionBarContributor() {
		String fileName = fOriginEditorPart.getEditorInput().getName();
		String originEditorId = EclipseIntegrator.getOriginDefaultEditorIdFor(fileName);
		Class<?> originEditorClass = fOriginEditorPart.getClass();
		DynamicDiagramActionBarContributor.Instance().updateCurrentEditor(originEditorId, originEditorClass);
	}
	
	/**
	 * Initializes the graphical editor resolved from the given editor part. If
	 * the graphical editor cannot be resolved, a {@link ClassCastException} is
	 * thrown.
	 * 
	 * @param editorPart
	 *            Editor part used to resolve the graphical editor
	 */
	public void initializeGraphicalEditor(IEditorPart editorPart) {
		GraphicalEditor graphicalEditor = (GraphicalEditor) editorPart.getAdapter(GraphicalEditor.class);
		if (graphicalEditor == null) throw new ClassCastException(String.format("Could not cast instance of '%s' to '%s'", 
				editorPart.getClass().toString(), GraphicalEditor.class.toString())); 
		
		OriginGraphicalEditor(graphicalEditor);
	}
	
	/**
	 * Returns the origin proxied editor part.
	 * 
	 * @return Origin proxied editor part
	 */
	public IEditorPart OriginEditorPart() {
		return fOriginEditorPart;
	}
	
	/**
	 * Returns the origin proxied graphical editor.
	 * 
	 * @return Origin proxied graphical editor
	 */
	public GraphicalEditor OriginGraphicalEditor() {
		return fOriginGraphicalEditor;
	}
	
	/**
	 * Sets the proxied graphical editor.
	 * 
	 * @param value
	 *            New value
	 */
	private void OriginGraphicalEditor(GraphicalEditor value) {
		fOriginGraphicalEditor = value;
	}
	
	/**
	 * Sets the adapter root reference. Will be used when call adapt().
	 * 
	 * @param value
	 *            New value
	 */
	public void AdapterRoot(IAdaptable value) {
		fAdapterRoot = value;
	}
	
	/**
	 * Returns the currently adapter root. Mostly called by adapt();
	 * 
	 * @return Currently adapter root
	 */
	public IAdaptable AdapterRoot() {
		return fAdapterRoot;
	}
	
	/**
	 * Performs the call getAdapter() on the currently set adapter root. 
	 * If the root is NULL, NULL is returned. This method may also 
	 * return NULL if the getAdapter() call returns NULL.
	 * @param cls Adaption target
	 * @return NULL or adapted reference
	 */
	public <T> T adapt(Class<T> cls) {
		IAdaptable adapterRoot = AdapterRoot();
		if (adapterRoot == null) return null;
		@SuppressWarnings("unchecked")
		T castedInstance = (T) adapterRoot.getAdapter(cls);
		return castedInstance;
	}
	
	/**
	 * Returns TRUE if the adapt() succeeded. The adapted instance 
	 * will be returned using the ref parameter.
	 * @param cls Adaption target
	 * @param ref Result of the adaption try.
	 * @return TRUE or FALSE
	 */
	public <T> boolean tryAdapt(Class<T> cls, Ref<T> ref) {
		ref.Value = null;
		T castedInstance = adapt(cls);
		if (castedInstance == null) return false;
		ref.Value = castedInstance;
		return true;
	}
	
	/**
	 * Defines a generic value reference.
	 * @author Arian Storch<arian.storch@bflow.org>
	 *
	 * @param <CValue> Class of the value reference
	 */
	public static class Ref<CValue> {
		
		/**
		 * Returns the referenced value or sets it.
		 */
		public CValue Value;
		
		/**
		 * Returns a new instance.
		 * @return New instance
		 */
		public static <K> Ref<K> _new() {
			return new Ref<K>();
		}
	}
	
//	try {
//	GraphicalEditor graphicalEditor = (GraphicalEditor) fOriginEditorPart.getAdapter(GraphicalEditor.class);
//	DefaultEditDomain editDomain = (DefaultEditDomain) MethodUtils.invokeMethod(graphicalEditor, "getDiagramEditDomain", new Object[0]);
//	setEditDomain(editDomain);
//	super.init(site, input);
//} catch (Exception e) {
//	e.printStackTrace();
//}		
	
	/**
	 * Invokes the method with the given name on the given object. The method is
	 * resolved using the given class. This avoids problems with inheritance. If
	 * the method is protected it will be invoked anyhow.
	 * 
	 * @param cls
	 *            Class to resolve the method
	 * @param obj
	 *            Object to apply the method invocation
	 * @param methodName
	 *            Name of the method to invoke
	 * @param parameterTypes
	 *            Types of parameters of the method to invoke
	 * @param args
	 *            Invocation arguments
	 * @return Result of the method invocation
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public <TClass, TResult> TResult invokeForced(Class<TClass> cls, Object obj, String methodName, Class<?>[] parameterTypes, Object[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = cls.getDeclaredMethod(methodName, parameterTypes);
		boolean isAccessible = method.isAccessible();
		method.setAccessible(true);
		@SuppressWarnings("unchecked")
		TResult result = (TResult) method.invoke(obj, args);
		method.setAccessible(isAccessible);
		return result;
	}
}

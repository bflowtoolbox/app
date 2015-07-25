package org.bflow.toolbox.hive.eclipse.integration.internal.editor;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bflow.toolbox.hive.eclipse.integration.EclipseIntegrator;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

public class AdapterUtil {
	
	static private Image fTitleImage;
	
	private IEditorPart fOriginEditorPart;
	private GraphicalEditor fOriginGraphicalEditor;
	private IAdaptable fAdapterRoot;
	
	public Image TitleImage() {
		return fTitleImage;
	}

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
	
	public void applyCustomDiagramActionBarContributor() {
		String fileName = fOriginEditorPart.getEditorInput().getName();
		String originEditorId = EclipseIntegrator.getOriginDefaultEditorIdFor(fileName);
		Class<?> originEditorClass = fOriginEditorPart.getClass();
		DynamicDiagramActionBarContributor.Instance().updateCurrentEditor(originEditorId, originEditorClass);
	}
	
	public void initializeGraphicalEditor(IEditorPart editorPart) {
		GraphicalEditor graphicalEditor = (GraphicalEditor) editorPart.getAdapter(GraphicalEditor.class);
		if (graphicalEditor == null) throw new ClassCastException(String.format("Could not cast instance of '%s' to '%s'", 
				editorPart.getClass().toString(), GraphicalEditor.class.toString())); 
		
		OriginGraphicalEditor(graphicalEditor);
	}
	
	public IEditorPart OriginEditorPart() {
		return fOriginEditorPart;
	}
	
	public GraphicalEditor OriginGraphicalEditor() {
		return fOriginGraphicalEditor;
	}
	
	private void OriginGraphicalEditor(GraphicalEditor value) {
		fOriginGraphicalEditor = value;
	}
	
	public void AdapterRoot(IAdaptable value) {
		fAdapterRoot = value;
	}
	
	public IAdaptable AdapterRoot() {
		return fAdapterRoot;
	}
	
	public <T> T adapt(Class<T> cls) {
		IAdaptable adapterRoot = AdapterRoot();
		if (adapterRoot == null) return null;
		@SuppressWarnings("unchecked")
		T castedInstance = (T) adapterRoot.getAdapter(cls);
		return castedInstance;
	}
	
	public <T> boolean tryAdapt(Class<T> cls, Ref<T> ref) {
		ref.Value = null;
		T castedInstance = adapt(cls);
		if (castedInstance == null) return false;
		ref.Value = castedInstance;
		return true;
	}
	
	public static class Ref<T> {
		public T Value;
		
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

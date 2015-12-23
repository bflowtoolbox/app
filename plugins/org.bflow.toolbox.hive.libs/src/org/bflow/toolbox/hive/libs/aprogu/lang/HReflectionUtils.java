package org.bflow.toolbox.hive.libs.aprogu.lang;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HReflectionUtils {
	
	public static <TClass> Object invokeMethode(TClass obj, String methodName, Object...args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		@SuppressWarnings("unchecked")
		Class<TClass> objClass = (Class<TClass>) obj.getClass();
		Method[] declaredMethods = objClass.getDeclaredMethods();
		
		Method method = null;
		for (int i = -1; ++i != declaredMethods.length;) {
			Method currentMethod = declaredMethods[i];
			String currentMethodName = currentMethod.getName();
			if (currentMethodName.equals(methodName)) {
				method = currentMethod;
				break;
			}
		}
		
		if (method == null) {
			throw new NoSuchMethodException(String.format("The given class '%s' doesn't have a method named '%s'", objClass.toString(), methodName));
		}
		
		boolean isAccessible = method.isAccessible();
		method.setAccessible(true);
		Object returnValue = method.invoke(obj, args);
		method.setAccessible(isAccessible);
		return returnValue;
	}

	
}

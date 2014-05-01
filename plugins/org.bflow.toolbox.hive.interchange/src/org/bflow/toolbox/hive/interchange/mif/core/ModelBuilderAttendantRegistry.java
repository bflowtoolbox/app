package org.bflow.toolbox.hive.interchange.mif.core;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Provides a registry for the model builder attendants.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 07/07/13
 * @version 12/07/13
 * @see IModelBuilderAttendant
 */
public class ModelBuilderAttendantRegistry {
	
	private static final Log logger = LogFactory.getLog(ModelBuilderAttendantRegistry.class);
	
	private static Map<String, Class<?>>  registeredModelBuildersAttendants = new HashMap<String, Class<?>>();
	private static Map<String, IModelBuilderAttendant> createdInstances = new HashMap<String, IModelBuilderAttendant>();
	
	/**
	 * Registers the model builder attendant.
	 * 
	 * @param builderClass
	 *            the model builder attendant
	 */
	public static void registerModelBuilder(String diagramType, Class<?> builderClass) {
		registeredModelBuildersAttendants.put(diagramType, builderClass);
	}
	
	/**
	 * Unregisters the model builder attendant.
	 * 
	 * @param builder
	 *            the model builder attendant
	 */
	public static void unregisterModelBuilder(IModelBuilderAttendant builder) {
		throw new NotImplementedException();
	}
	
	/**
	 * Returns the model builder attendant for the given diagram type. 
	 *
	 * @param diagramType the diagram type
	 * @return the model builder for the specified diagram type
	 */
	public static IModelBuilderAttendant getModelBuilderFor(String diagramType) {
		IModelBuilderAttendant instance = createdInstances.get(diagramType);
		
		if(instance != null)
			return instance;
		
		Class<?> clazzImpl = registeredModelBuildersAttendants.get(diagramType);
		
		if(clazzImpl == null)
			throw new NullPointerException(String.format("There is no model builder for the diagram type '%s'!", diagramType));
		
		try {
			IModelBuilderAttendant modelBuilder = (IModelBuilderAttendant) clazzImpl.newInstance();
			createdInstances.put(diagramType, modelBuilder);
			return modelBuilder;
		} catch(Exception ex) {
			String message = String.format("Could not create instance of '%s'.", clazzImpl);
			logger.error(message, ex);
			return null;
		}
	}

}

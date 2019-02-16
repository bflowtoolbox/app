package org.bflow.toolbox.hive.interchange.mif.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeDescriptor;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeProcessListener;
import org.bflow.toolbox.hive.interchange.mif.core.IScriptDescriptor;
import org.bflow.toolbox.hive.interchange.mif.core.InterchangeValidationException;
import org.bflow.toolbox.hive.interchange.mif.sdk.InterchangeDescriptorValidator;

/**
 * A factory for creating {@link IInterchangeDescriptor} instances.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2012-10-03
 * @version 2013-07-18
 */
public class InterchangeDescriptorFactory {
	
	/** The factory. */
	private static InterchangeDescriptorFactory factory = new InterchangeDescriptorFactory();
	
	/** Instantiates a new interchange descriptor factory. */
	private InterchangeDescriptorFactory() {
		// Keep private
	}
	
	/**
	 * Parses the given input stream and returns an instance of
	 * {@link IInterchangeDescriptor}. This process also contains the validation of
	 * the input stream. So if the input isn't valid an
	 * {@link InterchangeValidationException} is thrown.
	 * 
	 * @param inputStream Input stream to parse
	 * @return new instance of {@link IInterchangeDescriptor}
	 * @throws InterchangeValidationException Is thrown when validation fails
	 */
	public static IInterchangeDescriptor parse(InputStream inputStream) throws InterchangeValidationException {
		return InterchangeDescriptorValidator.validate(inputStream);
	}
	
	/**
	 * Creates a new instance of {@link IInterchangeDescriptor}.
	 * 
	 * @param applDiagramEditorTypes Applicable diagram editor types
	 * @param description            Description
	 * @param fileExtensions         File extensions
	 * @param name                   Name
	 * @param scripts                Scripts
	 * @param isPublic               Flag isPublic
	 * @return an instance of {@link IInterchangeDescriptor}
	 */
	public static IInterchangeDescriptor createExportDescriptor(List<String> applDiagramEditorTypes,
			String description, List<String> fileExtensions, String name, List<IScriptDescriptor> scripts,
			boolean isPublic) {
		
		if (applDiagramEditorTypes == null) {
			applDiagramEditorTypes = new ArrayList<String>();
		}
		
		
		
		return factory.new InterchangeDescriptorImpl(
				applDiagramEditorTypes.toArray(new String[0]), description, 
				fileExtensions.toArray(new String[0]), name, 
				scripts.toArray(new IScriptDescriptor[0]), isPublic);
	}
	
	/**
	 * Creates a new instance of {@link IScriptDescriptor}.
	 * 
	 * @param path       Path
	 * @param parameters Parameters
	 * @return the new instance of {@link IScriptDescriptor}
	 */
	public static IScriptDescriptor createScriptDescriptor(String path, Map<String, Object> parameters) {
		return factory.new ScriptDescriptorImpl(path, parameters);
	}

	/**
	 * Implements {@link IInterchangeDescriptor}.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 2012-10-03
	 */
	public class InterchangeDescriptorImpl implements IInterchangeDescriptor {
		
		/** The applicable diagram editor types. */
		private String[] applicableDiagramEditorTypes;
		
		/** The description. */
		private String description;
		
		/** The file extensions. */
		private String[] fileExtensions;
		
		/** The name. */
		private String name;
		
		/** The scripts. */
		private IScriptDescriptor[] scripts;
		
		/** The _public. */
		private boolean _public;
		
		/** The _listener. */
		private IInterchangeProcessListener _listener = IInterchangeProcessListener.DeafProcessListener;

		/**
		 * Instantiates a new interchange descriptor implementation.
		 * 
		 * @param applicableDiagramEditorTypes Applicable diagram editor types
		 * @param description                  Description
		 * @param fileExtensions               File extensions
		 * @param name                         Name
		 * @param scripts                      Scripts
		 * @param public1                      Flag public
		 */
		public InterchangeDescriptorImpl(String[] applicableDiagramEditorTypes,
				String description, String[] fileExtensions, String name,
				IScriptDescriptor[] scripts, boolean public1) {
			super();
			this.applicableDiagramEditorTypes = applicableDiagramEditorTypes;
			this.description = description;
			this.fileExtensions = fileExtensions;
			this.name = name;
			this.scripts = scripts;
			_public = public1;
		}

		/* (non-Javadoc)
		 * @see org.bflow.toolbox.interchange.mif.core.IExportDescriptor#getApplicableDiagramEditorTypes()
		 */
		@Override
		public String[] getApplicableDiagramEditorTypes() {
			return applicableDiagramEditorTypes;
		}

		/* (non-Javadoc)
		 * @see org.bflow.toolbox.interchange.mif.core.IExportDescriptor#getDescription()
		 */
		@Override
		public String getDescription() {
			return description;
		}

		/* (non-Javadoc)
		 * @see org.bflow.toolbox.interchange.mif.core.IExportDescriptor#getFileExtensions()
		 */
		@Override
		public String[] getFileExtensions() {
			return fileExtensions;
		}

		/* (non-Javadoc)
		 * @see org.bflow.toolbox.interchange.mif.core.IExportDescriptor#getName()
		 */
		@Override
		public String getName() {
			return name;
		}

		/* (non-Javadoc)
		 * @see org.bflow.toolbox.interchange.mif.core.IExportDescriptor#getScripts()
		 */
		@Override
		public IScriptDescriptor[] getScripts() {
			return scripts;
		}

		/* (non-Javadoc)
		 * @see org.bflow.toolbox.interchange.mif.core.IExportDescriptor#isPublic()
		 */
		@Override
		public boolean isPublic() {
			return _public;
		}
		
		@Override
		public IInterchangeProcessListener getProcessListener() {
			return _listener;
		}
		
		public void setProcessListener(IInterchangeProcessListener listener) {
			this._listener = listener;
		}
		
	}

	/**
	 * Implements {@link IScriptDescriptor}.
	 * 
	 * @author Arian Storch
	 * @since 2012-10-03
	 */
	private class ScriptDescriptorImpl implements IScriptDescriptor {

		/** The parameters. */
		private Map<String, Object> parameters = new HashMap<String, Object>();
		
		/** The path. */
		private String path;
		
		/**
		 * Instantiates a new script descriptor implementation.
		 * 
		 * @param path       Path
		 * @param parameters Parameters
		 */
		public ScriptDescriptorImpl(String path, Map<String, Object> parameters) {
			super();
			this.path = path;
			this.parameters = parameters;
		}

		/* (non-Javadoc)
		 * @see org.bflow.toolbox.interchange.mif.core.IScriptDescriptor#getParameters()
		 */
		@Override
		public Map<String, Object> getParameters() {
			return parameters;
		}

		/* (non-Javadoc)
		 * @see org.bflow.toolbox.interchange.mif.core.IScriptDescriptor#getPath()
		 */
		@Override
		public String getPath() {
			return path;
		}		
	}
}
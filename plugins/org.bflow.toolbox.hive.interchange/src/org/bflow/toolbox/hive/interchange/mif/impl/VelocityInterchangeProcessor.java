package org.bflow.toolbox.hive.interchange.mif.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.EscapeTool;
import org.apache.velocity.tools.generic.LoopTool;
import org.apache.velocity.tools.generic.MathTool;
import org.apache.velocity.tools.generic.NumberTool;
import org.apache.velocity.tools.generic.RenderTool;
import org.apache.velocity.tools.generic.ResourceTool;
import org.apache.velocity.tools.generic.SortTool;
import org.bflow.toolbox.hive.interchange.mif.core.IEdge;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeDescriptor;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeProcessor;
import org.bflow.toolbox.hive.interchange.mif.core.IModel;
import org.bflow.toolbox.hive.interchange.mif.core.IModelData;
import org.bflow.toolbox.hive.interchange.mif.core.IShape;
import org.bflow.toolbox.hive.interchange.mif.core.InterchangeProcessingException;
import org.bflow.toolbox.hive.interchange.store.ExportDescriptorStore;
import org.osgi.framework.Bundle;

/**
 * Implements {@link IInterchangeProcessor} to provide a template based export
 * processing.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 18/10/12
 * @version 22/04/14
 */
public class VelocityInterchangeProcessor implements IInterchangeProcessor {
	
	/** The preprocess template prefix */
	private static final String PreprocessPrefix = "@preprocess";
	
	/** The default output file encoding */
	private static final String DefaultOutputFileEncoding = "UTF-8";

	/** The export descriptor. */
	private IInterchangeDescriptor exportDescriptor;

	/** The target file. */
	private File targetFile;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bflow.toolbox.interchange.mif.core.IInterchangeProcessor#lockFile
	 * (java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void lockFile(String path, String name, String extension)
			throws InterchangeProcessingException {
		String fullPath = path.concat(name).concat(".").concat(extension);
		targetFile = new File(fullPath);
	}
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IInterchangeProcessor#process(org.bflow.toolbox.interchange.mif.core.IModelData)
	 */
	@Override
	public void process(IModelData modelData) throws InterchangeProcessingException {
		process(modelData.getModel(), modelData.getShapes(), modelData.getEdges());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bflow.toolbox.interchange.mif.core.IInterchangeProcessor#process(
	 * org.bflow.toolbox.interchange.mif.core.IModel,
	 * org.bflow.toolbox.interchange.mif.core.IShape[],
	 * org.bflow.toolbox.interchange.mif.core.IEdge[])
	 */
	@Override
	public void process(IModel model, IShape[] shapes, IEdge[] edges) throws InterchangeProcessingException {
		String path = exportDescriptor.getScripts()[0].getPath();

		String template;

		try {
			InputStream is = resolveScriptInputStream(path);
			template = IOUtils.toString(is); // TODO Check the encoding of the template input
			is.close();
		} catch (IOException ex) {
			throw new InterchangeProcessingException("Could not read template from File " + path, ex);
		}
		
		// Check for preprocessing and do it
		if(template.startsWith(PreprocessPrefix)) {
			template = StringUtils.remove(template, PreprocessPrefix);
			template = StringUtils.remove(template, CharUtils.CR);
			template = StringUtils.remove(template, CharUtils.LF);
			template = StringUtils.remove(template, '\t');
			
			template = StringUtils.replace(template, "~nl", System.lineSeparator());
			template = StringUtils.replace(template, "~t", "\t");
		}

		// Configure Velocity
		// Setting additional template path
		try {
			setVelocityTemplatePath(path);
		} catch (IOException ex) {
			throw new InterchangeProcessingException("Could not derive the template lookup path from " + path, ex);
		}
		// Setting location of log file to './eclipse/logs/Velocity.log'
		Velocity.setProperty(Velocity.RUNTIME_LOG, "./logs/Velocity.log");
//		Velocity.setProperty("input.encoding", "UTF-16"); // template file encoding; non set -> default encoding of machine; 
		Velocity.setProperty("output.encoding", DefaultOutputFileEncoding); 
		Velocity.init();

//		ToolManager velocityToolManager = new ToolManager();
//		velocityToolManager.configure("velocity-tools.xml");
		
		VelocityContext ctx = new VelocityContext();
		ctx.put("model", model);
		ctx.put("shapes", shapes);
		ctx.put("edges", edges);
//		ctx.put("params", null); TODO add support for parameters
		addToolsToContext(ctx);

		StringWriter sw = new StringWriter();
		String result = null;
		boolean isValid = false;

		try {
			isValid = Velocity.evaluate(ctx, sw, "TemplateInterchangeProcessing", template);
		} catch (Exception ex) {
			String message = String.format("Could not evaluate the given template: %s", path);
			throw new InterchangeProcessingException(message, ex);
		}

		if (isValid) {
			result = sw.toString();
		}

		try {
			sw.close();
		} catch (IOException e1) {
			// should be no problem in common
		}

		try {
			FileUtils.writeStringToFile(targetFile, result, DefaultOutputFileEncoding);
		} catch (IOException e) {
			throw new InterchangeProcessingException("Could not write to the target file", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bflow.toolbox.interchange.mif.core.IInterchangeProcessor#releaseFile
	 * ()
	 */
	@Override
	public void releaseFile() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bflow.toolbox.interchange.mif.core.IInterchangeProcessor#initialize
	 * (org.bflow.toolbox.interchange.mif.core.IExportDescriptor)
	 */
	@Override
	public void initialize(IInterchangeDescriptor exportDescriptor) throws InterchangeProcessingException {
		this.exportDescriptor = exportDescriptor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bflow.toolbox.interchange.mif.core.IInterchangeProcessor#setInputStream
	 * (java.io.InputStream)
	 */
	@Override
	public void setInputStream(InputStream inputStream)
			throws InterchangeProcessingException {

	}

	/**
	 * Resolves the input stream for the given script path.
	 * 
	 * @param path
	 *            path to a script file
	 * @return Input stream of the script file addressed by the given path
	 * @throws IOException
	 */
	private InputStream resolveScriptInputStream(String path) throws IOException {
		URL url = buildURL(path);
		if(url != null) return url.openStream();
		return null;
	}
	
	/**
	 * Builds an URL for the given script path.
	 * 
	 * @param path
	 *            Path of the script file.
	 * @return URL or NULL
	 * @throws IOException
	 */
	private URL buildURL(String path) throws IOException {
		if (path == null || path.isEmpty()) throw new IOException("Path of the script is null or empty!");

		Bundle bundle = ExportDescriptorStore.getBundleFor(exportDescriptor);
		String location = ExportDescriptorStore.getPathFor(exportDescriptor);

		if (bundle == null && (location == null || location.isEmpty())) throw new IOException("There is no bundle or location information available!");

		if (bundle != null) return bundle.getEntry(path);
		
		location = FilenameUtils.getFullPathNoEndSeparator(location);
		String scriptPath = location.concat(path);
		URL url = new File(scriptPath).toURI().toURL();
		return url;
	}
	
	/**
	 * Add a standardized set of Velocity tools to the given context.
	 * 
	 * @param ctx
	 *            Context which will get the tools
	 */
	private void addToolsToContext(VelocityContext ctx) {
		ctx.put(EscapeTool.DEFAULT_KEY, new EscapeTool());
		ctx.put("date", new DateTool());
		ctx.put("math", new MathTool());
		ctx.put("number", new NumberTool());
		ctx.put("render", new RenderTool());
		ctx.put("text", new ResourceTool());
//		ctx.put("lists", new ListTool()); // Deprecated
		ctx.put("sorter", new SortTool());
		ctx.put("loop", new LoopTool());
	}
	
	/**
	 * Sets the velocity template lookup path that is derived from the given
	 * script path.
	 * 
	 * @param scriptPath
	 *            Path to the script file
	 * @throws IOException
	 */
	private void setVelocityTemplatePath(String scriptPath) throws IOException {
		String strUrl = StringUtils.EMPTY;
		String[] splitParts = StringUtils.split(scriptPath, '/');
		String pathDir = "/" + StringUtils.join(splitParts, '/', 0, splitParts.length - 1);
		URL url = buildURL(pathDir);
		strUrl = url.toString();

		Velocity.setProperty("resource.loader", "url"); // Or file
		Velocity.setProperty("url.resource.loader.class", "org.apache.velocity.runtime.resource.loader.URLResourceLoader"); 
		Velocity.setProperty("url.resource.loader.root", strUrl);
	}
}
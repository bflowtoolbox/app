package org.bflow.toolbox.hive.interchange.mif.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.bflow.toolbox.hive.interchange.mif.aditus.AditusMetaInfo;
import org.bflow.toolbox.hive.interchange.mif.aditus.AditusProcessor;
import org.bflow.toolbox.hive.interchange.mif.core.IEdge;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeDescriptor;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeProcessor;
import org.bflow.toolbox.hive.interchange.mif.core.IModel;
import org.bflow.toolbox.hive.interchange.mif.core.IModelBuilderAttendant;
import org.bflow.toolbox.hive.interchange.mif.core.IModelData;
import org.bflow.toolbox.hive.interchange.mif.core.IScriptDescriptor;
import org.bflow.toolbox.hive.interchange.mif.core.IShape;
import org.bflow.toolbox.hive.interchange.mif.core.InterchangeProcessingException;
import org.bflow.toolbox.hive.interchange.mif.core.ModelBuilderAttendantRegistry;
import org.bflow.toolbox.hive.interchange.mif.grammar.AditusGrammarLexer;
import org.bflow.toolbox.hive.interchange.mif.grammar.AditusGrammarParser;
import org.bflow.toolbox.hive.interchange.store.ImportDescriptorStore;
import org.bflow.toolbox.hive.interchange.utils.ModelBuilderService;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.osgi.framework.Bundle;

/**
 * Implements {@link IInterchangeProcessor} to provide a template based import
 * processing.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 18/07/13
 * @version 02/08/13
 */
public class AditusInterchangeProcessor implements IInterchangeProcessor {
	
	private AditusMetaInfo aditusMetaInfo;
	private IModelData modelData;
	private File targetFile;
	private String baseFileName;
	private IInterchangeDescriptor fImportDescriptor;
	
	private AditusGrammarLexer aditusGrammarLexer;
	private CommonTokenStream commonTokenStream;
	private AditusGrammarParser aditusGrammarParser;

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.interchange.mif.core.IInterchangeProcessor#initialize(org.bflow.toolbox.hive.interchange.mif.core.IInterchangeDescriptor)
	 */
	@Override
	public void initialize(IInterchangeDescriptor descriptor) throws InterchangeProcessingException {
		if(descriptor == null) throw new InterchangeProcessingException("Interchange descriptor is NULL!");
		fImportDescriptor = descriptor;
		
		IScriptDescriptor[] scripts = descriptor.getScripts();
		if(scripts == null || scripts.length == 0) throw new InterchangeProcessingException("Import descriptor has no scripts defined!");
		
		IScriptDescriptor script = descriptor.getScripts()[0];
		String scriptPath = script.getPath();
		
		if(StringUtils.isBlank(scriptPath)) throw new InterchangeProcessingException("Path of the script defined by the import descriptor is null or empty!");
		
		InputStream importScriptStream;
		try {
			importScriptStream = resolveScriptInputStream(scriptPath);
		} catch (IOException ex) {
			throw new InterchangeProcessingException("Could not open stream to script path " + scriptPath, ex);
		}
		
		aditusMetaInfo = null;
		
		// Initialize parser instances
		if(aditusGrammarLexer == null)
			aditusGrammarLexer = new AditusGrammarLexer();
		if(commonTokenStream == null)
			commonTokenStream = new CommonTokenStream(aditusGrammarLexer);
		if(aditusGrammarParser == null)
			aditusGrammarParser = new AditusGrammarParser(commonTokenStream);
		
		try {
        	ANTLRInputStream stream = new ANTLRInputStream(importScriptStream);
        	aditusGrammarLexer.setCharStream(stream);
//    		AditusGrammarLexer lexer = new AditusGrammarLexer(stream);
        	commonTokenStream.setTokenSource(aditusGrammarLexer);
//            CommonTokenStream tokens = new CommonTokenStream(lexer);
//            AditusGrammarParser aditusParser = new AditusGrammarParser(tokens);
            aditusGrammarParser.setTokenStream(commonTokenStream);
            
            aditusMetaInfo = aditusGrammarParser.aditus();   
            IOUtils.closeQuietly(importScriptStream);
        } catch (Exception e) {
            throw new InterchangeProcessingException("Error on parsing import script!", e);
        }
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.interchange.mif.core.IInterchangeProcessor#setInputStream(java.io.InputStream)
	 */
	@Override
	public void setInputStream(InputStream inputStream) throws InterchangeProcessingException {
        try {
        	AditusProcessor aditusProcessor = new AditusProcessor(aditusMetaInfo);  
            modelData = aditusProcessor.getModelData(inputStream);
		} catch (Exception e) {
			throw new InterchangeProcessingException("Error on creating model data!", e);
		}
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.interchange.mif.core.IInterchangeProcessor#lockFile(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void lockFile(String path, String name, String extension) throws InterchangeProcessingException {
		targetFile = new File(String.format("%s/%s.%s", path, name, extension));
		baseFileName = name;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.interchange.mif.core.IInterchangeProcessor#process(org.bflow.toolbox.hive.interchange.mif.core.IModel, org.bflow.toolbox.hive.interchange.mif.core.IShape[], org.bflow.toolbox.hive.interchange.mif.core.IEdge[])
	 */
	@Override
	public void process(IModel model, IShape[] shapes, IEdge[] edges) throws InterchangeProcessingException {
        
        String diagramType = "epc";
        
//        String absSrcFilePath = srcFile.getAbsolutePath();
        String absTgtFilePath = targetFile.getParentFile().getAbsolutePath();
//        String baseFileName = FilenameUtils.getBaseName(absSrcFilePath);
        
        if(baseFileName.contains("."))
        	baseFileName = FilenameUtils.getBaseName(baseFileName);
        
        String fileExtension = "epc";
        
        IPath path = Path.fromOSString(absTgtFilePath);
        String projectName = path.lastSegment();
        
        String platformResourceURI = String.format("/%s/%s.%s", projectName, baseFileName, fileExtension);
        
        URI diagramURI = URI.createPlatformResourceURI(platformResourceURI, true);
        IModelBuilderAttendant modelBuilderAttendant = ModelBuilderAttendantRegistry.getModelBuilderFor(diagramType);
        
        if(modelBuilderAttendant == null)
        	throw new InterchangeProcessingException(
        			String.format("There has been no model builder attendant registered for the diagram type '%s'!", diagramType));
        
        ModelBuilderService.build(modelData, diagramURI, modelBuilderAttendant);

	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.interchange.mif.core.IInterchangeProcessor#process(org.bflow.toolbox.hive.interchange.mif.core.IModelData)
	 */
	@Override
	public void process(IModelData modelData) throws InterchangeProcessingException {
		process(null, null, null);
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.interchange.mif.core.IInterchangeProcessor#releaseFile()
	 */
	@Override
	public void releaseFile() {
		// Clean up
		aditusMetaInfo = null;
		modelData = null;
		targetFile = null;
		baseFileName = null;
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

		Bundle bundle = ImportDescriptorStore.getBundleFor(fImportDescriptor);
		String location = ImportDescriptorStore.getPathFor(fImportDescriptor);

		if (bundle == null && (location == null || location.isEmpty())) throw new IOException("There is no bundle or location information available!");
		if (bundle != null) return bundle.getEntry(path);
		
		location = FilenameUtils.getFullPathNoEndSeparator(location);
		String scriptPath = location.concat(path);
		URL url = new File(scriptPath).toURI().toURL();
		return url;
	}
}

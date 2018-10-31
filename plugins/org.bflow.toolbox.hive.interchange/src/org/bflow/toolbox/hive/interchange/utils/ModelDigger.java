package org.bflow.toolbox.hive.interchange.utils;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bflow.toolbox.hive.interchange.commons.CommonInterchangeUtil;
import org.bflow.toolbox.hive.interchange.mif.core.AttributeProviderRegistry;
import org.bflow.toolbox.hive.interchange.mif.core.IAttributeProvider;
import org.bflow.toolbox.hive.interchange.mif.core.IEdge;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangePropertyProvider;
import org.bflow.toolbox.hive.interchange.mif.core.IModel;
import org.bflow.toolbox.hive.interchange.mif.core.IModelData;
import org.bflow.toolbox.hive.interchange.mif.core.IShape;
import org.bflow.toolbox.hive.interchange.mif.core.PropertyProviderRegistry;
import org.bflow.toolbox.hive.interchange.mif.impl.EdgeAdapter;
import org.bflow.toolbox.hive.interchange.mif.impl.ModelAdapter;
import org.bflow.toolbox.hive.interchange.mif.impl.ShapeAdapter;
import org.bflow.toolbox.hive.libs.aprogu.lang.Cast;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.swt.widgets.Shell;

/**
 * Implements a model digger.
 * <p/>
 * This class opens the model (and its associated diagram) given by a File
 * pointer and creates an instance of {@link IModelData} from it. This object
 * provides several informations about the shapes and edges contained within the
 * model. These model informations can be used for further processing like an
 * export process.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2012-10-03
 * @version 2014-05-01
 * 			2018-10-31 Enhanced semantic element and element id resolving
 */
public class ModelDigger {
	
	/** The log instance for this class */
	private static final Log logger = LogFactory.getLog(ModelDigger.class);

	/**
	 * Resolves the model data for the given source file.
	 * 
	 * @param sourceFile Source file
	 * @return {@link IModelData}
	 */
	public static IModelData resolveModelData(File sourceFile) {

		IFile iFile = CommonInterchangeUtil.toIFile(sourceFile);		
		DiagramEditPart offscreenEditPart = null;
		
		// Try load resolve the diagram edit part off-screen
		Shell myShell = new Shell();
		try {
			offscreenEditPart = CommonInterchangeUtil.getOffscreenDiagramEditPart(myShell, iFile);
		} catch (CoreException e) {
			logger.error("Could not load the diagram via DiagramIOUtil.", e);
		}

		// Use the off-screen edit part or open the editor as fallback
		DiagramEditPart diagramEditPart = (offscreenEditPart == null ? resolveDiagramEditPart(iFile) : offscreenEditPart);
		IAttributeProvider[] attributeProviders = AttributeProviderRegistry.getProviderFor(iFile.getFileExtension());
		IInterchangePropertyProvider[] propertyProviders = PropertyProviderRegistry.getProviderFor(iFile.getFileExtension());

		List<?> listShapes = diagramEditPart.getChildren();
		List<?> listEdges = diagramEditPart.getConnections();

		final IModel model = resolveModel(diagramEditPart, attributeProviders, propertyProviders);
		final IShape[] shapes = resolveShapes(listShapes, diagramEditPart, attributeProviders, propertyProviders);
		final IEdge[] edges = resolveEdges(listEdges, shapes, diagramEditPart, attributeProviders, propertyProviders);
		
		// Dispose the shell
		myShell.dispose();

		return new IModelData() {

			@Override
			public IShape[] getShapes() {
				return shapes;
			}

			@Override
			public IModel getModel() {
				return model;
			}

			@Override
			public IEdge[] getEdges() {
				return edges;
			}
		};
	}

	/**
	 * Resolves the edges informations.
	 *
	 * @param listEdges         List of edges to process
	 * @param shapes            Shapes
	 * @param diagramEditPart   Diagram edit part
	 * @param providers         Providers
	 * @param propertyProviders Property providers
	 * @return Resolved edges
	 */
	private static IEdge[] resolveEdges(List<?> listEdges, IShape[] shapes, DiagramEditPart diagramEditPart, 
			IAttributeProvider[] providers, IInterchangePropertyProvider[] propertyProviders) {
		IEdge[] edges = new IEdge[listEdges.size()];

		for (int i = 0; i < edges.length; i++) {
			ConnectionEditPart connectionEditPart = (ConnectionEditPart) listEdges.get(i);
			EObject semanticElement = getSemanticElement(connectionEditPart);
			String elementId = getModelId(semanticElement);					
						
			Map<String, String> attributes = resolveAttributesFor(elementId, diagramEditPart, providers);
			IShape[] associatedShapes = findShapes(connectionEditPart, shapes);
			IShape sourceShape = associatedShapes[0];
			IShape targetShape = associatedShapes[1];
			IInterchangePropertyProvider propertyProviderHub = PropertyProviderHub.getInstance(propertyProviders);
			edges[i] = new EdgeAdapter(elementId, semanticElement, 
					connectionEditPart, 
					sourceShape, targetShape, 
					attributes, propertyProviderHub);
		}

		return edges;
	}

	/**
	 * Resolves the shape informations.
	 * 
	 * @param listShapes        List of shapes to process
	 * @param diagramEditPart   Diagram edit part
	 * @param providers         Providers
	 * @param propertyProviders Property providers
	 * @return Resolved shapes
	 */
	private static IShape[] resolveShapes(List<?> listShapes, DiagramEditPart diagramEditPart, 
			IAttributeProvider[] providers, IInterchangePropertyProvider[] propertyProviders) {
		LinkedList<IShape> resolvedShapes = new LinkedList<IShape>();
		boolean resolveChildren = false; // TODO Introduce global parameter; children are needed by i* (openOME) for instance
//		LinkedHashSet<IShape> resolvedShapes = new LinkedHashSet<IShape>();

		for (int i = 0; i < listShapes.size(); i++) {
			// TODO Check if is instance of
			IGraphicalEditPart shape = (IGraphicalEditPart) listShapes.get(i);
			
			// Handling embedded shapes
			List<?> shapeChildren = shape.getChildren();
			IShape[] subShapes = resolveShapes(shapeChildren, diagramEditPart, providers, propertyProviders);
			if (resolveChildren) for(IShape subShape : subShapes) resolvedShapes.add(subShape);
			
			EObject semanticElement = getSemanticElement(shape);
			String elementId = getModelId(semanticElement);
			Map<String, String> attributes = resolveAttributesFor(elementId, diagramEditPart, providers);
			IInterchangePropertyProvider propertyProviderHub = PropertyProviderHub.getInstance(propertyProviders);
			IShape shapeAdapter = new ShapeAdapter(elementId, semanticElement, shape, attributes, propertyProviderHub);
			resolvedShapes.add(shapeAdapter);
		}
		IShape[] shapes = resolvedShapes.toArray(new IShape[0]);
		return shapes;
	}

	/**
	 * Resolves the model information.
	 *
	 * @param diagramEditPart   Diagram edit part
	 * @param providers         Providers
	 * @param propertyProviders Property providers
	 * @return Resolved model
	 */
	private static IModel resolveModel(DiagramEditPart diagramEditPart,	IAttributeProvider[] providers, 
			IInterchangePropertyProvider[] propertyProviders) {
		String id = EMFCoreUtil.getProxyID(diagramEditPart.resolveSemanticElement());
		Map<String, String> attributes = resolveAttributesFor(id, diagramEditPart, providers);
		IInterchangePropertyProvider propertyProviderHub = PropertyProviderHub.getInstance(propertyProviders);
		return new ModelAdapter(diagramEditPart, attributes, propertyProviderHub);
	}

	/**
	 * Resolves the associated diagram edit part.
	 * 
	 * @param file File
	 * @return Diagram edit part
	 */
	private static DiagramEditPart resolveDiagramEditPart(IFile file) {
		/* [Arian Storch]
		 * The common process uses an off-screen factory to create the edit part. Because this is only 
		 * a fallback solution, closing of an opened diagram has been set to a minor problem at this time.
		 */
		// TODO handle editors which weren't open and so needed to be closed
		return CommonInterchangeUtil.getOnscreenDiagramEditPart(file);
	}

	/**
	 * Resolves the attributes for the given id and diagram edit part.
	 * 
	 * @param id              Id
	 * @param diagramEditPart Diagram edit part
	 * @param providers       Providers
	 * @return Attributes
	 */
	private static Map<String, String> resolveAttributesFor(String id,
			DiagramEditPart diagramEditPart, IAttributeProvider[] providers) {
		Map<String, String> attributes = new HashMap<String, String>();

		for (int i = 0; i < providers.length; i++) {
			IAttributeProvider provider = providers[i];
			Map<String, String> providedAttributes = provider.getAttributesFor(diagramEditPart, id);
			if (providedAttributes != null) {
				attributes.putAll(providedAttributes);
			}
		}

		return attributes;
	}

	/**
	 * Finds the source and target shape for the given connection.
	 * 
	 * @param connection Connection
	 * @param shapes     Set of shapes to iterate
	 * @return Resolved shapes
	 */
	private static IShape[] findShapes(ConnectionEditPart connection, IShape[] shapes) {
		IShape source = null;
		IShape target = null;

		IGraphicalEditPart srcEditPart = (IGraphicalEditPart) connection.getSource();
		IGraphicalEditPart tgtEditPart = (IGraphicalEditPart) connection.getTarget();
		
		String srcId = getModelId(srcEditPart);
		String tgtId = getModelId(tgtEditPart);

		for (int i = 0; i < shapes.length; i++) {
			IShape shape = shapes[i];

			if (shape.getId().equalsIgnoreCase(srcId)) {
				source = shape;
			}

			if (shape.getId().equalsIgnoreCase(tgtId)) {
				target = shape;
			}

			if (source != null && target != null) {
				break;
			}
		}

		return new IShape[] { source, target };
	}
	
	/**
	 * Returns the semantic element of the given {@code editPart}.
	 * 
	 * @param editPart Edit part the semantic element has to be resolved
	 * @return Semantic element or NULL
	 */
	private static EObject getSemanticElement(IGraphicalEditPart editPart) {
		EObject semanticElement = editPart.resolveSemanticElement();
		
		// It might happen that the semantic element is null, for instance NoteAttachmentEditPart
		if (semanticElement == null) {
			Object model = editPart.getModel();
			semanticElement = Cast.as(EObject.class, model);
		}
		
		return semanticElement;
	}
	
	/**
	 * Returns the model id that is assigned to the semantic element of the given
	 * {@code editPart}. Note, this method resolves the edit part via
	 * {@link #getSemanticElement(IGraphicalEditPart)}.
	 * 
	 * @param editPart Edit part the get the model id for
	 * @return Model id or NULL
	 */
	private static String getModelId(IGraphicalEditPart editPart) {		
		EObject semanticElement = getSemanticElement(editPart);
		return getModelId(semanticElement);
	}
	
	/**
	 * Returns the model id that is assigned to the {@code semanticElement}.
	 * 
	 * @param semanticElement Semantic element
	 * @return Model id or NULL
	 */
	private static String getModelId(EObject semanticElement) {
		return EMFCoreUtil.getProxyID(semanticElement);
	}	
}

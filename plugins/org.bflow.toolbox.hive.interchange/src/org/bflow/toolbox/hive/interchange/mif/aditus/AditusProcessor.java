package org.bflow.toolbox.hive.interchange.mif.aditus;

import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.StringUtils;
import org.bflow.toolbox.hive.interchange.mif.core.IEdge;
import org.bflow.toolbox.hive.interchange.mif.core.IModel;
import org.bflow.toolbox.hive.interchange.mif.core.IModelData;
import org.bflow.toolbox.hive.interchange.mif.core.IShape;
import org.bflow.toolbox.hive.interchange.utils.ModelBuilderService;
import org.bflow.toolbox.hive.libs.aprogu.collections.IntegerValue;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.InvalidXPathException;
import org.dom4j.io.SAXReader;
import org.dom4j.xpath.DefaultXPath;
import org.eclipse.swt.graphics.Image;

/**
 * Provides a processor for instances of {@link AditusMetaInfo}. This 
 * processor creates based on the given meta informations an instance of {@link IModelData} 
 * which can be used to create model files.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 11/07/13
 * @see ModelBuilderService
 */
public class AditusProcessor {
	
	private AditusMetaInfo metaInfo;
	
	/**
	 * Creates a new instance.
	 * @param metaInfo Meta informations that will be processed
	 */
	public AditusProcessor(AditusMetaInfo metaInfo) {
		if(metaInfo == null)
			throw new NullPointerException("AditusMetaInfo mustn't be null!");
		
		this.metaInfo = metaInfo;
	}

	/**
	 * Returns the model data.
	 * 
	 * @param sourceFileStream
	 *            the source file stream
	 * @return the model data
	 * @throws AditusProcessorException
	 *             the aditus processor exception
	 */
	public IModelData getModelData(InputStream sourceFileStream) throws AditusProcessorException {		
		SAXReader saxReader = new SAXReader();
		Document xmlDocument = null;
		
		try {
			xmlDocument = saxReader.read(sourceFileStream);
		} catch(Exception ex) {
			throw new AditusProcessorException("Failed on reading source file stream!", ex);
		}
		
		// Process model informations
		AditusModelMetaInfo modelInfo = metaInfo.Model;
		AditusShapeMetaInfo[] shapesInfo = metaInfo.Shapes.toArray(new AditusShapeMetaInfo[0]);
		AditusEdgeMetaInfo[] edgesInfo = metaInfo.Edges.toArray(new AditusEdgeMetaInfo[0]);
		
		final IModel model = getModelInformation(xmlDocument, modelInfo);
		final IShape[] shapes = getShapesModelInformations(xmlDocument, shapesInfo);
		final IEdge[] edges = getEdgesModelInformations(xmlDocument, shapes, edgesInfo);
		
		return new IModelData() {
			@Override
			public IModel getModel() {
				return model;
			}
			
			@Override
			public IShape[] getShapes() {
				return shapes;
			}
			
			@Override
			public IEdge[] getEdges() {
				return edges;
			}
		};
	}
	
	/**
	 * Returns the edges model informations.
	 * 
	 * @param xmlDocument
	 *            The XML document
	 * @param shapes
	 *            The shapes
	 * @param edgesInfo
	 *            The edges meta informations
	 * @return The edges model informations
	 * @throws AditusProcessorException
	 *             the aditus processor exception
	 */
	private IEdge[] getEdgesModelInformations(Document xmlDocument, IShape[] shapes, AditusEdgeMetaInfo[] edgesInfo) 
		throws AditusProcessorException {
		List<IEdge> listEdges = new LinkedList<IEdge>();
		
		for(int i = 0; i < edgesInfo.length; i++) {
			AditusEdgeMetaInfo edgeInfo = edgesInfo[i];
			List<IEdge> resolvedEges = getEdgesModelInformations(xmlDocument, shapes, edgeInfo);
			listEdges.addAll(resolvedEges);
		}
		
		IEdge[] edges = listEdges.toArray(new IEdge[0]);
		return edges;
	}
	
	/**
	 * Returns the edges model informations.
	 * 
	 * @param xmlDocument
	 *            The XML document
	 * @param shapes
	 *            The shapes
	 * @param edgeInfo
	 *            The edge meta information
	 * @return the edges model informations
	 * @throws AditusProcessorException
	 *             the aditus processor exception
	 */
	private List<IEdge> getEdgesModelInformations(Document xmlDocument, IShape[] shapes, AditusEdgeMetaInfo edgeInfo) 
		throws AditusProcessorException {
		List<IEdge> edges = new LinkedList<IEdge>();
		
		String idXpathExpr = edgeInfo.Id;
		String typeXpathExpr = edgeInfo.Type;
		String nodeXpathExpr = edgeInfo.Node;
//		String nameXpathExpr = edgeInfo.Name;
		String attributesExpr = edgeInfo.Attributes;
		String sourceExpr = edgeInfo.Source;
		String targetExpr = edgeInfo.Target;
		
		List<?> nodes = xmlDocument.selectNodes(nodeXpathExpr);
		
		for(int i = 0; i < nodes.size(); i++) {
			Element element = (Element)nodes.get(i);
			
			final String valueId = getValueOrDefault(element, idXpathExpr);
			final String valueType = getValueOrDefault(element, typeXpathExpr);
//			final String valueName = getValueOrDefault(element, nameXpathExpr);
			final Map<String,String> attributes = getAttributes(xmlDocument, attributesExpr);
			
			final IShape shapeSource = getShapeFromExpression(shapes, element, sourceExpr);
			final IShape shapeTarget = getShapeFromExpression(shapes, element, targetExpr);
			
			IEdge edge = new IEdge() {
				
				@Override
				public Object get(String propertyName) {
					throw new NotImplementedException();
				}
				
				@Override
				public Object getType() {
					return valueType;
				}
				
				@Override
				public String getId() {
					return valueId;
				}
				
				@Override
				public Map<String, String> getAttributes() {
					return attributes;
				}
				
				@Override
				public IShape getTarget() {
					return shapeTarget;
				}
				
				@Override
				public IShape getSource() {
					return shapeSource;
				}
			};
			
			if(edge.getTarget() != null && edge.getSource() != null)
				edges.add(edge);
		}
		
		return edges;
	}
	
	/**
	 * Returns the shape addressed by the given aditus expression.
	 * 
	 * @param shapes
	 *            The collection of available shapes
	 * @param element
	 *            The XML node element
	 * @param expr
	 *            The aditus expression
	 * @return The shape addressed by the given aditus expression or null
	 * @throws AditusProcessorException
	 *             the aditus processor exception
	 */
	private IShape getShapeFromExpression(IShape[] shapes, Element element, String expr) 
		throws AditusProcessorException {
		String[] exprParts = StringUtils.splitByWholeSeparator(expr, EDGE_SPLITTER_TOKEN);
		String refObject = exprParts[0];
		String condition = exprParts[1];
		
		if(refObject.equalsIgnoreCase(REFERENCE_EDGE_TOKEN))
			throw new NotImplementedException();
		
		String[] condParts = StringUtils.splitByWholeSeparator(condition, EDGE_CONDITION_TOKEN);
		String classPropertyName = condParts[0];
		String propertyName = StringUtils.split(classPropertyName, '.')[1];
		String xpathExpr = condParts[1];
		
		String valueAssignmentCondition = getValueOrDefault(element, xpathExpr);
		
		for(int i = 0; i < shapes.length; i++) {
			IShape shape = shapes[i];
			boolean doesMatch = false;
			
			if(propertyName.equalsIgnoreCase("id"))
				doesMatch = shape.getId().equalsIgnoreCase(valueAssignmentCondition);
			
			// TODO Implement the other properties
			
			if(doesMatch)
				return shape;
		}
		
		return null;
	}
	
	/**
	 * Returns the shapes model informations.
	 * 
	 * @param xmlDocument
	 *            The XML document
	 * @param shapesMetaInformations
	 *            The shapes meta informations
	 * @return The shapes model informations
	 * @throws AditusProcessorException
	 *             the aditus processor exception
	 */
	private IShape[] getShapesModelInformations(Document xmlDocument, AditusShapeMetaInfo[] shapesMetaInformations)
	 throws AditusProcessorException {
		List<IShape> listShapes = new LinkedList<IShape>();
		
		for(int i = 0; i < shapesMetaInformations.length; i++) {
			AditusShapeMetaInfo shapeInfo = shapesMetaInformations[i];
			List<IShape> resolvedShapes = getShapesModelInformations(xmlDocument, shapeInfo);
			listShapes.addAll(resolvedShapes);
		}
		
		IShape[] shapes = listShapes.toArray(new IShape[0]);
		return shapes;
	}
	
	/**
	 * Returns the shapes model informations.
	 * 
	 * @param xmlDocument
	 *            The XML document
	 * @param shapeMetaInformation
	 *            The shape meta information
	 * @return The shapes model informations
	 * @throws AditusProcessorException
	 *             the aditus processor exception
	 */
	private List<IShape> getShapesModelInformations(Document xmlDocument, AditusShapeMetaInfo shapeMetaInformation) 
		throws AditusProcessorException {
		List<IShape> listShapes = new LinkedList<IShape>();
		
		String idXpathExpr = shapeMetaInformation.Id;
		String typeXpathExpr = shapeMetaInformation.Type;
		String nodeXpathExpr = shapeMetaInformation.Node;
		String nameXpathExpr = shapeMetaInformation.Name;
		String attributesExpr = shapeMetaInformation.Attributes;
		String widthXpathExpr = shapeMetaInformation.Width;
		String heightXpathExpr = shapeMetaInformation.Height;
		String xXpathExpr = shapeMetaInformation.X;
		String yXpathExpr = shapeMetaInformation.Y;
		
		List<?> nodes = xmlDocument.selectNodes(nodeXpathExpr);
		
		for(int i = 0; i < nodes.size(); i++) {
			Element element = (Element)nodes.get(i);
			
			final String valueId = getValueOrDefault(element, idXpathExpr);
			final String valueType = getValueOrDefault(element, typeXpathExpr);
			final String valueName = getValueOrDefault(element, nameXpathExpr);
			final Map<String, String> attributes = getAttributes(xmlDocument, attributesExpr);
			
			String valueWidth = getValueOrDefault(element, widthXpathExpr);
			String valueHeight = getValueOrDefault(element, heightXpathExpr);
			String valueX = getValueOrDefault(element, xXpathExpr);
			String valueY = getValueOrDefault(element, yXpathExpr);
			
			final IntegerValue valWidth = IntegerValue.create();
			if(!IntegerValue.tryParse(valueWidth, valWidth))
				valWidth.Value = 32;
			
			final IntegerValue valHeight = IntegerValue.create();
			if(!IntegerValue.tryParse(valueHeight, valHeight))
				valHeight.Value = 32;
			
			final IntegerValue valX = IntegerValue.create();
			if(!IntegerValue.tryParse(valueX, valX))
				valX.Value = 0;
			
			final IntegerValue valY = IntegerValue.create();
			if(!IntegerValue.tryParse(valueY, valY))
				valY.Value = 0;
			
			IShape shape = new IShape() {
				@Override
				public int getY() {
					return valY.Value;
				}
				
				@Override
				public int getX() {
					return valX.Value;
				}
				
				@Override
				public int getWidth() {
					return valWidth.Value;
				}
				
				@Override
				public Object getType() {
					return valueType;
				}
				
				@Override
				public int getHeight() {
					return valHeight.Value;
				}
				
				@Override
				public Image getImage() {
					throw new NotImplementedException();
				}
				
				@Override
				public Map<String, String> getAttributes() {
					return attributes;
				}
				
				@Override
				public String getName() {
					return valueName;
				}
				
				@Override
				public String getId() {
					return valueId;
				}
				
				@Override
				public Object get(String propertyName) {
					throw new NotImplementedException();
				}
			};
			
			listShapes.add(shape);
		}
		
		return listShapes;
	}
	
	/**
	 * Returns the model information.
	 * 
	 * @param xmlDocument
	 *            The XML document
	 * @param modelMetaInformation
	 *            The model meta information
	 * @return The model information
	 */
	private IModel getModelInformation(Document xmlDocument, AditusModelMetaInfo modelMetaInformation) {
		String idXpathExpr = modelMetaInformation.Id;
		String attributesExpr = modelMetaInformation.Attributes;
//		String typeXpathExpr = modelInfo.Type;
		
		final String valueId = xmlDocument.valueOf(idXpathExpr);
		final String valueType = "org.bflow.toolbox.epc.diagram.Epc_79"; // getValueOrDefault(xmlDocument, typeXpathExpr);
		final Map<String,String> attributes = getAttributes(xmlDocument, attributesExpr);
		
		return new IModel() {
			@Override
			public String getId() {
				return valueId;
			}
			
			@Override
			public String getName() {
				throw new NotImplementedException();
			}
			
			@Override
			public Object getType() {
				return valueType;
			}
			
			@Override
			public Object get(String propertyName) {
				throw new NotImplementedException();
			}
			
			@Override
			public Map<String, String> getAttributes() {
				return attributes;
			}
		};
	}
	
	/**
	 * Returns the value expressed by given XPath query. If the query result is
	 * null or empty a default value will be used. The default value is defined
	 * by the Aditus expression which may be contained within the XPath
	 * expression.
	 * 
	 * @param element
	 *            The XML node element
	 * @param xpathExpr
	 *            XPath expression
	 * @return Value of the XPath query or maybe the default value if there is
	 *         one
	 */
	private String getValueOrDefault(Element element, String xpathExpr) {
		if(StringUtils.isBlank(xpathExpr))
			return null;
		
		// Are there any sub-queries?
		Matcher matcher = SubQueryPattern.matcher(xpathExpr);
		boolean hasSubQuery = matcher.find();
		if(hasSubQuery) {
			String subQueryExprStr = matcher.group();
			String subQueryExpr = subQueryExprStr.replace('{', ' ').replace('}', ' ').trim();
			String subQueryValue = getValueOrDefault(element, subQueryExpr);
			String quotedValue = String.format("'%s'", subQueryValue);
			xpathExpr = matcher.replaceAll(quotedValue); 
		}
		
		String[] exprParts = StringUtils.splitByWholeSeparator(xpathExpr, XPATHEXPR_SPLITTER_TOKEN);
		
		// Only a XPath or default value has been set
		if(exprParts.length == 1) {
			try {				
				// If no exception occurs then this is a valid XPath expression
				new DefaultXPath(xpathExpr);
				String value = element.valueOf(xpathExpr);
			
				if(StringUtils.isBlank(value))
					throw new InvalidXPathException(xpathExpr);
				
				return value;
			} catch(InvalidXPathException ex) {
				// No matter, this must be a default value assignment
			}
			
			return xpathExpr;
		}
		
		// XPath and default value has been set
		if(exprParts.length == 2) {
			String xpath = exprParts[0];
			String def = exprParts[1];
			
			String value = element.valueOf(xpath);
			if(StringUtils.isBlank(value))
				value = def;
			
			return value;
		}
		
		throw new NotImplementedException();
	}
	
	/**
	 * Returns all attributes that are expressed by the Aditus attributes
	 * assignment expression.
	 * 
	 * @param xmlDocument
	 *            The XML document
	 * @param attributesExpr
	 *            The Aditus attributes assignment expression
	 * @return The attributes
	 */
	private Map<String, String> getAttributes(Document xmlDocument, String attributesExpr) {		
		Map<String, String> attributes = new HashMap<String, String>();
		
		if(StringUtils.isBlank(attributesExpr))
			return attributes;
		
		String[] assignmentParts = attributesExpr.split(ATTRIBUTE_SPLITTER_TOKEN);
		String keyXpathExpr = assignmentParts[0];
		String valueXpathExpr = assignmentParts[1];
		
		List<?> listKeys = xmlDocument.selectNodes(keyXpathExpr);
		List<?> listValues = xmlDocument.selectNodes(valueXpathExpr);

        for(int i = 0; i < listKeys.size(); i++) {
        	Attribute attrKey = (Attribute)listKeys.get(i);
        	Attribute attrVal = (Attribute)listValues.get(i);
        	
        	attributes.put(attrKey.getValue(), attrVal.getValue());
        }
		
		return attributes;
	}
	
	private static final Pattern SubQueryPattern = Pattern.compile("\\{(.)*\\}");
	
	// unused
	// private static final String REFERENCE_SHAPE_TOKEN = "shape";
	private static final String REFERENCE_EDGE_TOKEN = "edge";
	
	private static final String EDGE_CONDITION_TOKEN = " == ";
	private static final String EDGE_SPLITTER_TOKEN = " <=> ";
	private static final String ATTRIBUTE_SPLITTER_TOKEN = " => ";
	private static final String XPATHEXPR_SPLITTER_TOKEN = " | ";
}

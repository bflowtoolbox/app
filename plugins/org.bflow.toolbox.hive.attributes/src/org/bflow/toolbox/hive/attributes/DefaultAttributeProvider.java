package org.bflow.toolbox.hive.attributes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.bflow.toolbox.hive.attributes.internal.AttributeViewPlugin;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.eclipse.core.resources.ResourcesPlugin;


/**
 * Defines a static attribute provider that handles default model attributes.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 19.06.10
 * @version 02.07.12
 */
public class DefaultAttributeProvider {
	
	private static boolean init = false;
	private static Vector<AttributeHeader> attributes = new Vector<AttributeHeader>();
	
	private static final File CONFIG_FILE = ResourcesPlugin.getWorkspace().getRoot().getLocation().append("/.attribute/defaults.xml").toFile();
	
	/**
	 * Initializes the provider and loads it contents.
	 */
	private static void init() {
		SAXReader reader = new SAXReader();
		
		try
		{
			Document doc = reader.read(CONFIG_FILE);
			Element root = doc.getRootElement();
			
			for(Iterator<?> it = root.elementIterator(); it.hasNext(); )
			{
				Element el = (Element) it.next();
				
				String project = (el.attributeValue("project").equalsIgnoreCase("NULL") ? null : el.attributeValue("project"));
				String diagram = (el.attributeValue("diagram").equalsIgnoreCase("NULL") ? null : el.attributeValue("diagram"));
				String type = el.attributeValue("type");
				String name = el.attributeValue("name");
				String value = el.attributeValue("value");
				
				attributes.add(new DefaultAttributeProvider().new AttributeHeader(name, value, project,
																diagram, type));
			}
		} catch(DocumentException ex) {
			
		}
		
		init = true;
	}
	
	/**
	 * Updates and saves the provider contents.
	 */
	private static void update() {
		if (!init)
			init();
		
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("defaults");
		
		for(AttributeHeader header:attributes) {
			Element el = root.addElement("attributeHeader");
			
			el.addAttribute("project", (header.getProject() == null ? "NULL" : header.getProject()));
			el.addAttribute("diagram", (header.getDiagram() == null ? "NULL" : header.getDiagram()));
			el.addAttribute("type", header.getType());
			el.addAttribute("name", header.getName());
			el.addAttribute("value", header.getValue());
		}
		
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			
			if(!CONFIG_FILE.exists()) {
				CONFIG_FILE.getParentFile().mkdirs();
				CONFIG_FILE.createNewFile();
			}
			
			XMLWriter writer = new XMLWriter(new FileWriter(CONFIG_FILE), format);
			
			writer.write(doc);
			writer.close();
		} catch(IOException ex) {
			AttributeViewPlugin.logError(ex.getMessage(), ex);
		}
	}
	
	/**
	 * Returns a list of {@link Attribute}s specified by the project name and
	 * type.
	 * 
	 * @param project
	 *            name of the project
	 * @param type
	 *            "event", "function" or "model"
	 * @return list of {@link Attribute}s
	 */
	public static List<Attribute> getAttributesByProject(String project, String type) {
		if(!init)
			init();
		
		Vector<Attribute> request = new Vector<Attribute>();
		
		for (AttributeHeader header:attributes)
			if (header.getProject() != null && header.getProject().equalsIgnoreCase(project))
				if (header.getType().equalsIgnoreCase(type))
					request.add(header);
		
		return request;
	}
	
	/**
	 * Returns a list of {@link Attribute}s specified by the diagram name and
	 * type.
	 * 
	 * @param diagram
	 *            name of the diagram
	 * @param type
	 *            "event", "function" or "model"
	 * @return list of {@link Attribute}s
	 */
	public static List<Attribute> getAttributesByDiagram(String diagram, String type) {
		if (!init)
			init();
		
		Vector<Attribute> request = new Vector<Attribute>();
		
		for (AttributeHeader header:attributes)
			if (header.getDiagram() != null && header.getDiagram().equalsIgnoreCase(diagram))
				if (header.getType().equalsIgnoreCase(type))
					request.add(header);
		
		return request;
	}
	
	/**
	 * Adds a new {@link Attribute} to the defaults.
	 * 
	 * @param diagram
	 *            name of the diagram
	 * @param name
	 *            name of the attribute
	 * @param value
	 *            value
	 * @param type
	 *            "event", "function" or "model"
	 */
	public static void addAttributeForDiagram(String diagram, String name, String value, String type) {
		if (!init)
			init();
		
		attributes.add(new DefaultAttributeProvider().new AttributeHeader(name, value, null, diagram, type));
		update();
	}
		
	/**
	 * Adds a new {@link Attribute} to the defaults.
	 * 
	 * @param project
	 *            name of the project
	 * @param name
	 *            name of the attribute
	 * @param value
	 *            value
	 * @param type
	 *            "event", "function" or "model"
	 */
	public static void addAttributeForProject(String project, String name, String value, String type) {
		if(!init)
			init();
		
		attributes.add(new DefaultAttributeProvider().new AttributeHeader(name, value, project, null, type));
		update();
	}
	
	/**
	 * Removes an {@link Attribute} from the defaults.
	 * 
	 * @param diagram
	 *            name of the diagram
	 * @param name
	 *            name of the attribute
	 * @param type
	 *            "event", "function" or "model"
	 */
	public static void removeAttributeForDiagram(String diagram, String name, String type) {
		if (!init)
			init();
		
		for (AttributeHeader header:attributes)
			if (header.getDiagram() != null && header.getDiagram().equalsIgnoreCase(diagram))
				if (header.getName().equalsIgnoreCase(name))
					if(header.getType().equalsIgnoreCase(type)) {
						attributes.remove(header);
						break;
					}
		
		update();
	}
	
	/**
	 * Defines an attribute.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 19.06.10
	 */
	public class Attribute {
		private String name;
		private String value;
		
		/**
		 * Default constructor.
		 * @param name name of the attribute
		 * @param value value
		 */
		public Attribute(String name, String value) {
			super();
			this.name = name;
			this.value = value;
		}

		/**
		 * Returns the name of the attribute.
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * Returns the value of the attribute.
		 * @return the value
		 */
		public String getValue() {
			return value;
		}	
	}
	
	/**
	 * Defines a header of an {@link Attribute} for internal handling. It's not
	 * intended to be used outside the {@link DefaultAttributeProvider}.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 19.06.10
	 *
	 */
	public class AttributeHeader extends Attribute {
		private String project;
		private String diagram;
		private String type;
		
		/**
		 * Default constructor.
		 * 
		 * @param name
		 *            name of the attribute
		 * @param value
		 *            value
		 * @param project
		 *            project name or null
		 * @param diagram
		 *            diagram of null
		 * @param type
		 *            "event", "function" or "model"
		 */
		public AttributeHeader(String name, String value, String project, String diagram, String type) {
			super(name, value);
			this.project = project;
			this.diagram = diagram;
			this.type = type;
		}
		
		/**
		 * Returns the name of the project or null.
		 * @return the project
		 */
		public String getProject() {
			return project;
		}
		
		/**
		 * Returns the name of the diagram or null.
		 * @return the diagram
		 */
		public String getDiagram() {
			return diagram;
		}
		
		/**
		 * Returns the type.
		 * @return the type
		 */
		public String getType() {
			return type;
		}
	}
}

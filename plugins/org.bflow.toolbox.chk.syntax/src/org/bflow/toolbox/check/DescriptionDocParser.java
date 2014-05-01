package org.bflow.toolbox.check;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * parses a language.epc xml file to generate rules
 * @author Arian Storch
 * @version 13/08/09
 *
 */
public class DescriptionDocParser 
{
	private InputStream stream;
	private Vector<Rule> rules;
	
	/**
	 * constructor
	 * @param stream stream to the file
	 */
	public DescriptionDocParser(InputStream stream)
	{
		this.stream = stream;
		this.rules = new Vector<Rule>();
	}
	
	/**
	 * parses the stream given by the constructor
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public void parse() throws SAXException, IOException, ParserConfigurationException
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(stream);
		
		Element docElement = document.getDocumentElement();
		NodeList list = docElement.getElementsByTagName("rule");
		
		for(int i = 0; i < list.getLength(); i++)
		{
			Element el = (Element)list.item(i);
			
			rules.add(createRule(el));
		}
	}
	
	/**
	 * returns the rules<p/>
	 * if you haven't called <code>parse()</code> first, the vector doesn't contain elements
	 * @return parsed rules
	 */
	public Vector<Rule> getRules() 
	{
		return this.rules;
	}
	
	/**
	 * creates a rule out of an element
	 * @param el xml element
	 * @return rule
	 */
	private Rule createRule(Element el)
	{
		String id = getTextValue(el, "id");
		String name = getTextValue(el, "name");
		String msg = getTextValue(el, "message");
		String clazz = getTextValue(el, "class");
		String diagram = getTextValue(el, "diagram");
		String description = getTextValue(el, "description");
		String image = getTextValue(el, "image");
		boolean dfault = Boolean.parseBoolean(getTextValue(el, "default"));
		
		return new Rule(id, name, msg, clazz, diagram, description, image, dfault);
	}
	
	/**
	 * takes a xml element and the tag name, look for the tag and get
	 * the text content
	 * @param ele element
	 * @param tagName tag name
	 * @return value of the tag name
	 */
	private String getTextValue(Element ele, String tagName) {
		String textVal = "";
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			if(el.hasChildNodes())
				textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}

}

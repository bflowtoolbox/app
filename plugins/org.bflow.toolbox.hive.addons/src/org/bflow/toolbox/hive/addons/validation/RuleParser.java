package org.bflow.toolbox.hive.addons.validation;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.osgi.framework.Bundle;

/**
 * Implements a parser for documents that are containing validation rules.
 * 
 * @author Arian Storch
 * @since 28/03/11
 * @version 18/07/11
 */
public class RuleParser {

	/**
	 * Parses the given stream and returns a list that contains validation rules.
	 * @param stream file stream to parse
	 * @return list of validation rules or null if something went wrong
	 * 
	 * @see Rule
	 */
	public static ArrayList<Rule> parseFile(InputStream stream, Bundle bundle) {
		if (stream == null)
			return null;

		SAXReader reader = new SAXReader();

		try {
			Document doc = reader.read(stream);

			ArrayList<Rule> list = new ArrayList<Rule>();

			Element root = doc.getRootElement();

			for (Iterator<?> it = root.elementIterator("rule"); it.hasNext();) {
				Element rule = (Element) it.next();

				String id = rule.elementText("id");
				String name = rule.elementText("name"); 
				String message = rule.elementText("message");
				String clazz = rule.elementText("class");
				String diagram = rule.elementText("diagram");
				String description = rule.elementText("description");
				String image = rule.elementText("image");
				boolean dfault = Boolean.parseBoolean(rule
						.elementText("default"));
				String type = rule.elementText("type");
				String url = rule.elementText("url");

				Rule r = new Rule(id, name, message, clazz, diagram, type,
						description, url, image, dfault);
				
				r.setBundle(bundle);

				list.add(r);
			}

			return list;

		} catch (DocumentException ex) {
			ex.printStackTrace();
			return null;
		}
	}

}

package org.bflow.toolbox.hive.addons.validation;

import org.osgi.framework.Bundle;

/**
 * Defines a rule for validation behavior.
 * 
 * @author Arian Storch
 * @version 18/07/11
 */
public class Rule {
	
	private String id;
	private String name;
	private String message;
	private String clazz;
	private String diagramType;
	private String type;
	private String description;
	private String image;
	private String url;
	private boolean dfault;
	
	private Bundle bundle;

	/**
	 * constructs a new rule
	 * 
	 * @param id
	 *            identifier of the rule; must be biunique
	 * @param name
	 *            name of the rule
	 * @param message
	 *            message that will shown by the rule
	 * @param clazz
	 *            class of the rule
	 * @param diagramType
	 *            diagram type
	 * @param description
	 *            rule description
	 * @param image
	 *            url of the image file
	 * @param dfault
	 *            default value of the rule
	 */
	public Rule(String id, String name, String message, String clazz,
			String diagramType, String type, String description, String url,
			String image, boolean dfault) {
		
		this.id = id;
		this.name = name;
		this.message = message;
		this.clazz = clazz;
		this.diagramType = diagramType;
		this.type = type;
		this.description = description;
		this.url = (url == null ? "" : url);
		this.image = (image == null ? "" : image);
		this.dfault = dfault;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Sets the type of the rule.
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the clazz
	 */
	public String getClazz() {
		return clazz;
	}

	/**
	 * returns the diagram type
	 * 
	 * @return diagram type
	 */
	public String getDiagramType() {
		return diagramType;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the dfault
	 */
	public boolean isDfault() {
		return dfault;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Returns the associated url.
	 * 
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	@Override
	public String toString() {
		return "[Rule] id: " + id + " name: " + name + " class: " + clazz
				+ " diagramtype: " + diagramType + " message: " + message
				+ " description: " + description + "... image: " + image
				+ " default: " + dfault;
	}
	
	/**
	 * Returns the bundle that registered this rule.
	 * @return bundle owner of this rule
	 */
	public Bundle getBundle() {
		return bundle;
	}
	
	/**
	 * Sets the bundle that registers this rule.
	 * @param bundle bundle
	 */
	public void setBundle(Bundle bundle) {
		this.bundle = bundle;
	}
}

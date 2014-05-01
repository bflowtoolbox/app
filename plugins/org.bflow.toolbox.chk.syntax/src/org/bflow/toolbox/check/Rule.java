package org.bflow.toolbox.check;

/**
 * defines a rule for validation behavior
 * @author Arian Storch
 * @version 13/08/09
 */
public class Rule 
{
	private String id;
	private String name;
	private String message;
	private String clazz;
	private String diagramType;
	private String description;
	private String image;
	private boolean dfault;
	
	/**
	 * constructs a new rule
	 * @param id identifier of the rule; must be biunique
	 * @param name name of the rule
	 * @param message message that will shown by the rule
	 * @param clazz class of the rule
	 * @param diagramType diagram type
	 * @param description rule description
	 * @param image url of the image file
	 * @param dfault default value of the rule
	 */
	public Rule(String id, String name, String message, String clazz, String diagramType, String description, String image, boolean dfault) 
	{
		this.id = id;
		this.name = name;
		this.message = message;
		this.clazz = clazz;
		this.diagramType = diagramType;
		this.description = description;
		this.image = image;
		this.dfault = dfault;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the clazz
	 */
	public String getClazz() {
		return clazz;
	}

	/**
	 * @param clazz the clazz to set
	 */
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	
	/**
	 * returns the diagram type
	 * @return diagram type
	 */
	public String getDiagramType() {
		return diagramType;
	}
	
	/**
	 * sets the diagram type
	 * @param diagramType type of the diagram
	 */
	public void setDiagram(String diagramType) {
		this.diagramType = diagramType;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the dfault
	 */
	public boolean isDfault() {
		return dfault;
	}

	/**
	 * @param dfault the dfault to set
	 */
	public void setDfault(boolean dfault) {
		this.dfault = dfault;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() 
	{
		return "[Rule] id: "+id+" name: "+name+" class: "+clazz+" diagramtype: "+diagramType+
					" message: "+message+" description: "+description+
					"... image: "+image+" default: "+dfault;
	}
}

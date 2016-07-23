package org.bflow.toolbox.hive.statement.views;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bflow.toolbox.hive.attributes.AttributeFile;


/**
 * Represents a property entry for the StatementView-TableViewer
 * 
 * @author Markus Schnädelbach
 */
public class Property {

	private static AttributeFile attrFile;
	private String templateString;
	private List<Variable> variables = new ArrayList<>();
	private String id;
	private String diagramId;
	
	/**
	 * Constructor for creating a new property for a diagram
	 * @param templateString - the name of this property
	 * @param diagramId - id of the associated diagram
	 */
	Property(String templateString, String diagramId) {
		this.templateString = templateString;
		this.variables = getVariablesFromTemplate();
		this.diagramId = diagramId;
		this.id = "property_" + UUID.randomUUID().toString();
	}

	/**
	 * Constructor for an empty property.
	 * Can used as placeholder in TableViewer or for restoring a already former existing
	 * property
	 */
	public Property() {
	}

	public String getTemplateString() {
		return templateString;
	}
	
	/**
	 * Returns true if all variables are referenced to an editpart
	 * (Referenced by ID of editpart)
	 * @return true if all variables are referenced to an editpart
	 */
	public boolean isComplete(){
		for (Variable var : variables) {
			if(var.getId().isEmpty()){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns the variable stored on the index position or null
	 * @param index
	 * @return  Variable or null 
	 */
	public Variable getVariable(int index){
		
		if (variables.size() > index) {
			return variables.get(index);
		}
		return null;
	}
	
	/**
	 * Returns the unique Id of this property
	 * @return
	 */
	protected String getId() {
		return id;
	}
	
	/**
	 * Returns the diagram id of the associated diagram.
	 * @return
	 */
	private String getDiagramId() {
		return diagramId;
	}
	
	/**
	 * Sets the id of the associated diagram.
	 * @param id - id of the associated diagram
	 */
	protected void setDiagramId(String id) {
		this.diagramId = id;
	}
	
	/**
	 * Sets the unique Id of this property.
	 * @param id - unique Id of this property
	 */
	protected void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Sets the templateString.
	 * @param templateString - the name of this property
	 */
	protected void setTemplateString(String templateString) {
		this.templateString = templateString;
	}
	
	/**
	 * Sets the list of contained variables of this property.
	 * Order of list entries is depending of the variable occurrence in the property
	 * (from left to right) 
	 * @param list with sorted variables
	 */
	protected void setVariables(List<Variable> vars) {
		this.variables = vars;
	}
	
	/**
	 * Set the associated attributfile for this property
	 * @param AttributeFile - of the associated diagram
	 */
	protected static void setAttributFile(AttributeFile af) {
		attrFile = af;
	}
		
	/**
	 * Returns always a new list with the contained variables of this property
	 * @return List with Variables of this property
	 */
	protected List<Variable> getVariablesFromTemplate() {
		ArrayList<Variable> vars = new ArrayList<>();

		if (templateString.contains("$")) { //$NON-NLS-1$
			String[] words = templateString.split("\\s"); //$NON-NLS-1$
			for (String word : words) {
				if (word.startsWith("$")) { //$NON-NLS-1$
					while (word.startsWith("$")) {
						word = word.substring(1);
						//Variable endet mit "_[0-9]"
						if (word.matches("^.+?(_)\\d$")) {
							word = word.substring(0, word.length()-2);
						}
					}
					vars.add(new Variable(word));
				}
			}
		}
		return vars;
	}
	
	/**
	 * Returns a string of the property name with variables as link
	 * @return String with linked variables
	 */
	protected String getTemplateStringWithLinks() {
		if (templateString.contains("$")) { //$NON-NLS-1$
			String[] words = templateString.split("\\s"); //$NON-NLS-1$
			int j = 0;
			for (int i = 0; i < words.length; i++) {
				String word = words[i];
				if (word.startsWith("$")) {
					while (word.startsWith("$")) {
						word = word.substring(1);
						//Variable endet mit "_[0-9]"
						if (word.matches("^.+?(_)\\d$")) {
							word = word.substring(0, word.length()-2);
						}
					}
					words[i] = "<a href=\""+ j +"\">" + "["+ word + "]" + "</a>";
					j++;
				}
			}
			StringBuilder builder = new StringBuilder();
			for(String s : words) {
			    builder.append(s);
			    builder.append(" ");
			}
			return builder.toString().trim();
		}
		return templateString;
	}
	
	/**
	 * Stores the property as attribute in the associated diagram.
	 * @param property - the property you want to save
	 */
	public static void persistAsAttribute(Property property){
		attrFile.add(property.getDiagramId(), property.getId() , getPropertyAsStringEntry(property)); //$NON-NLS-1$
	}
	
	
	/**
	 * Converts the property to string, for saving them.
	 * @param property - the property you want to save
	 * @return String - property as restorable string
	 */
	protected static String getPropertyAsStringEntry(Property property) {
		String tempString = property.getTemplateString();
		if (tempString.contains("$")) { //$NON-NLS-1$
			String[] words = tempString.split("\\s"); //$NON-NLS-1$
			int j = 0;
			for (int i = 0; i < words.length; i++) {
				String word = words[i];
				if (word.startsWith("$")) {
					words[i] = "$" + property.getVariable(j).getId();
					j++;
				}
			}
			StringBuilder builder = new StringBuilder();
			for (String s : words) {
				builder.append(s);
				builder.append(" ");
			}
			return builder.toString().trim();
		}
		return property.getTemplateString();
	}
	
	/**
	 * Represents a variable of a property.
	 * 
	 * @author Markus Schnaedelbach
	 */
	public class Variable {
		private String name;
		private String id = "";

		public Variable(String name) {
			this.name = name;
		}
		
		public Variable(String name, String id) {
			this.name = name;
			this.id = id;
		}

		public String getName() {
			return name;
		}

		/**
		 * Returns the id of the associated editpart node.
		 * @return the id of the associated editpart-node
		 */
		public String getId() {
			return id;
		}

		/**
		 * Sets the Id of an associated editPart-node.
		 * If with that setting all variables of the property are assigned to a editpart node
		 * the property will persisted as attribute for the associated diagram.
		 * @param id - id of the associated editpart-node
		 */
		public void setId(String id) {
			this.id = id;
			if (Property.this.isComplete()) {
				persistAsAttribute(Property.this);
			}
		}
	}
}

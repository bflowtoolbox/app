package org.bflow.toolbox.hive.statement.views;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bflow.toolbox.hive.attributes.AttributeFile;

/**
 * Represents a property entry for the StatmentView-TableViewer
 * 
 * @author Markus Schnädelbach
 */
public class Property {

	private static AttributeFile attrFile;
	private String templateString;
	private List<Variable> variables = new ArrayList<>();
	private String id;
	private String diagramId;

	Property(String templateString) {
		this.templateString = templateString;
		this.variables = getVariablesFromTemplate();
	}
	
	Property(String templateString, String diagramId) {
		this.templateString = templateString;
		this.variables = getVariablesFromTemplate();
		this.diagramId = diagramId;
		this.id = "property_" + UUID.randomUUID().toString();
	}

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
	
	public boolean isValid() {
		for (Variable var : variables) {
			if(var.getName().equals("unknown")){
				return false;
			}
		}
		return true;
	}
	
	public Variable getVariable(int id){
		
		if (variables.size() > id) {
			return variables.get(id);
		}
		return null;
	}
	
	protected String getId() {
		return id;
	}
	
	private String getDiagramId() {
		return diagramId;
	}
	
	protected void setDiagramId(String id) {
		this.diagramId = id;
	}
	
	protected void setId(String id) {
		this.id = id;
	}
	
	protected void setTemplateString(String templeteString) {
		this.templateString = templeteString;
	}
	
	protected void setVariables(List<Variable> vars) {
		this.variables = vars;
	}
	
	protected static void setAttributFile(AttributeFile af) {
		attrFile = af;
	}
		
	/**
	 * Returns always a new list with the contained variables of this property
	 * @return List with Variables of this property
	 */
	private List<Variable> getVariablesFromTemplate() {
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
	 * Returns a string representation of the property name with variables as link
	 * @return String with linked variables
	 */
	protected String getTemplateStringWithLinks() {
		String propertyStringRepresentation = templateString;
		if (propertyStringRepresentation.contains("$")) { //$NON-NLS-1$
			String[] words = propertyStringRepresentation.split("\\s"); //$NON-NLS-1$
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
			propertyStringRepresentation = builder.toString().trim();
		}

		String[] parts = propertyStringRepresentation.split("->\\["); //$NON-NLS-1$
		if (parts.length == 2) {
			propertyStringRepresentation = parts[0];
		}
		return propertyStringRepresentation;
	}
	
	/**
	 * Stores the property as diagram attribute.
	 * @param property
	 */
	public static void persistAsAttribute(Property property){
		attrFile.add(property.getDiagramId(), property.getId() , getPropertyAsStringEntry(property)); //$NON-NLS-1$
		attrFile.save();
	}
	
	/**
	 * Converts the property to string, for saving them.
	 * @param property
	 * @return String
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

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
			Property.this.templateString = Property.this.templateString.replace(this.getName(),this.getId() );
			if (Property.this.isComplete()) {
				persistAsAttribute(Property.this);
			}
		}
	}
}

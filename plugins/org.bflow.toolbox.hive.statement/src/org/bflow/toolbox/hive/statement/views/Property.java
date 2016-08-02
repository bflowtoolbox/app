package org.bflow.toolbox.hive.statement.views;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bflow.toolbox.hive.attributes.AttributeFile;
import org.bflow.toolbox.hive.nls.NLSupport;
import org.bflow.toolbox.hive.statement.nls.Messages;

/**
 * Represents a property entry for the StatementView-TableViewer
 * 
 * @author Markus Schnädelbach
 */
public class Property {

	private static AttributeFile attrFile;
	private String templateString;
	private String formulaString;
	private List<Variable> variables = new ArrayList<>();
	private String id;
	private String diagramId;
	
	/**
	 * Constructor for creating a new property for a diagram
	 * @param templateString the name and formula of this property
	 * @param diagramId id of the associated diagram
	 */
	Property(String templateString, String diagramId) {
		
		String[] parts = templateString.split("-->"); //$NON-NLS-1$
		if (parts.length == 2) {
			this.templateString = parts[0];
			this.formulaString = parts[1];
		}else {
			this.templateString = "unknown format"; //$NON-NLS-1$
			this.formulaString = "unknown format"; //$NON-NLS-1$
		}
		this.variables = getVariablesFromTemplate();
		this.diagramId = diagramId;
		this.id = "property_" + UUID.randomUUID().toString(); //$NON-NLS-1$
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
	
	public String getFormularString() {
		return formulaString;
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
			if(var.getName().equals(NLSupport.StatementView_ReplacementUnknownVariables2)){
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
	 * @param id id of the associated diagram
	 */
	protected void setDiagramId(String id) {
		this.diagramId = id;
	}
	
	/**
	 * Sets the unique Id of this property.
	 * @param id unique Id of this property
	 */
	protected void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Sets the templateString.
	 * @param templateString the name of this property
	 */
	protected void setTemplateString(String templateString) {
		this.templateString = templateString;
	}
	
	/**
	 * Sets the formulaString.
	 * @param formulaString the formula expression of this property
	 */
	protected void setFormulaString(String forumlaString) {
		this.formulaString = forumlaString;
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
	 * @param AttributeFile of the associated diagram
	 */
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
					while (word.startsWith("$")) { //$NON-NLS-1$
						word = word.substring(1);
//						//Variable endet mit "_[0-9]"
//						if (word.matches("^.+?(_)\\d$")) {
//							word = word.substring(0, word.length()-2);
//						}
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
				if (word.startsWith("$")) { //$NON-NLS-1$
					while (word.startsWith("$")) { //$NON-NLS-1$
						word = word.substring(1);
						//Variable endet mit "_[0-9]"
						if (word.matches("^.+?(_)\\d$")) { //$NON-NLS-1$
							word = word.substring(0, word.length()-2);
						}
					}
					words[i] = "<a href=\""+ j +"\">" + "["+ word + "]" + "</a>"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
					j++;
				}
			}
			StringBuilder builder = new StringBuilder();
			for(String s : words) {
			    builder.append(s);
			    builder.append(" "); //$NON-NLS-1$
			}
			propertyStringRepresentation = builder.toString().trim();
		}
		return propertyStringRepresentation;
	}
	
	/**
	 * Stores the property as attribute in the associated diagram.
	 * @param property the property you want to save
	 */
	public static void persistAsAttribute(Property property){
		attrFile.add(property.getDiagramId(), property.getId() , getPropertyAsStringEntry(property)); //$NON-NLS-1$
		attrFile.save();
	}
	
	/**
	 * Converts the property to string, for saving them.
	 * @param property the property you want to save
	 * @return String property as restorable string
	 */
	protected static String getPropertyAsStringEntry(Property property) {
		String tempString = property.getTemplateString();
		String storeableString = property.getTemplateString();
		if (tempString.contains("$")) { //$NON-NLS-1$
			String[] words = tempString.split("\\s"); //$NON-NLS-1$
			int j = 0;
			for (int i = 0; i < words.length; i++) {
				String word = words[i];
				if (word.startsWith("$")) { //$NON-NLS-1$
					words[i] = "$" + property.getVariable(j).getId()+property.getVariable(j).getVariableNameNumber(); //$NON-NLS-1$
					j++;
				}
			}
			StringBuilder builder = new StringBuilder();
			for (String s : words) {
				builder.append(s);
				builder.append(" "); //$NON-NLS-1$
			}
			storeableString = builder.toString().trim();
		}
		return storeableString + "-->" + property.getFormularString(); //$NON-NLS-1$
	}
	
	/**
	 * Represents a variable of a property.
	 * 
	 * @author Markus Schnaedelbach
	 */
	public class Variable {
		private String name;
		private String id = ""; //$NON-NLS-1$

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
			if (this.id.isEmpty()) {
				formulaString = formulaString.replaceAll("(" + name + ")", id + getVariableNameNumber()); //$NON-NLS-1$ //$NON-NLS-2$
			} else {
				formulaString = formulaString.replaceAll("(" + this.id + getVariableNameNumber() + ")", id + getVariableNameNumber()); //$NON-NLS-1$ //$NON-NLS-2$
			}
			this.id = id;

			if (Property.this.isComplete()) {
				persistAsAttribute(Property.this);
			}
		}

		/**
		 * Returns the variablename without index number
		 * @return the variablename without index number
		 */
		public String getClearName() {
			//Variable endet mit "_[0-9]"
			if (name.matches("^.+?(_)\\d$")) { //$NON-NLS-1$
				return name.substring(0, name.length()-2);
			}
			return name;
		}
		
		/**
		 * Returns the index number of an variable or if there is no index than an empty string
		 * @return the index number of an variable or if there is no index than an empty string
		 */
		public String getVariableNameNumber() {
			Pattern pattern = Pattern.compile("(_)\\d$"); //$NON-NLS-1$
		    Matcher matcher = pattern.matcher(this.name);
		    while (matcher.find()) {
		        return matcher.group();
		    }
			return "";
		}
	}
}

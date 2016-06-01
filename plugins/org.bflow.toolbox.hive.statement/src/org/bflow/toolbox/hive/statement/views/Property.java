package org.bflow.toolbox.hive.statement.views;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bflow.toolbox.hive.attributes.AttributeFile;
import org.bflow.toolbox.hive.attributes.AttributeFileRegistry;


public class Property {

	private static AttributeFile attrFile;
	private String templateString;
	private List<Variable> variables = new ArrayList<>();
	private String id;
	private String diagramId;

	Property(String templateString, String diagramId) {
		this.templateString = templateString;
		this.variables = getVariablesFromTemplate();
		this.diagramId = diagramId;
		this.id = "property_" + UUID.randomUUID().toString();
	}

	public Property(String templateString, String diagramId, String id) {
		super();
		this.templateString = templateString;
		this.diagramId = diagramId;
		this.id = id;
	}

	public Property() {
	}

	public String getTemplateString() {
		return templateString;
	}
	
	public boolean isComplete(){
		for (Variable var : variables) {
			if(var.getId().isEmpty()){
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
	
	protected static void setAttributFile(AttributeFile af) {
		attrFile = af;
	}
		
	//Returns always a new List
	protected List<Variable> getVariablesFromTemplate() {
		ArrayList<Variable> vars = new ArrayList<>();

		if (templateString.contains("$")) { //$NON-NLS-1$
			String[] words = templateString.split("\\s"); //$NON-NLS-1$
			for (String word : words) {
				if (word.startsWith("$")) { //$NON-NLS-1$
					while (word.startsWith("$")) {
						word = word.substring(1);
					}
					vars.add(new Variable(word));
				}
			}
		}
		return vars;
	}
	
	protected String getTemplateStringWithLinks() {
		if (templateString.contains("$")) { //$NON-NLS-1$
			String[] words = templateString.split("\\s"); //$NON-NLS-1$
			int j = 0;
			for (int i = 0; i < words.length; i++) {
				String word = words[i];
				if (word.startsWith("$")) {
					while (word.startsWith("$")) {
						word = word.substring(1);
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
	
//	public static void persist(Property property) {
//		IPath rootPath = ResourcesPlugin.getWorkspace().getRoot().getRawLocation();
//		rootPath = rootPath.append(".properties/stored_properties.txt");
//		File templateFile = rootPath.toFile();
//		if (!templateFile.exists()) {
//			try {
//				templateFile.createNewFile();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//		if (templateFile.isFile() && templateFile.canWrite()) {
//			FileWriter fw = null;
//			try {
//				fw = new FileWriter(templateFile, true);
//				fw.write(getPropertyAsStringEntry(property));
//				fw.write(System.getProperty("line.separator"));
//
//			} catch (IOException e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					if (fw != null) {
//						fw.close();
//					}
//				} catch (IOException e) {
//				}
//
//			}
//		}
//		// BufferedReader in = null;
//	}
	
	public static void persistAsAttribute(Property property){
		attrFile = AttributeFileRegistry.getInstance().getActiveAttributeFile();
		attrFile.add(property.getDiagramId(), property.getId() , getPropertyAsStringEntry(property)); //$NON-NLS-1$
	}
	
	
	
	//Returns always a new List
	protected static String getPropertyAsStringEntry(Property property) {
		String tempString = property.getTemplateString();
		if (tempString.contains("$")) { //$NON-NLS-1$
			String[] words = tempString.split("\\s"); //$NON-NLS-1$
			int j = 0;
			for (int i = 0; i < words.length; i++) {
				String word = words[i];
				if (word.startsWith("$")) {
					words[i] = property.getVariable(j).getId();
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

		public String getName() {
			return name;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
			if (Property.this.isComplete()) {
//				persist(Property.this);
				persistAsAttribute(Property.this);
			}
		}
	}
}

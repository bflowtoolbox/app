package org.bflow.toolbox.hive.statement.views;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;


public class Property {

	private String templateString;
	private List<Variable> variables;
	private String id;

	Property(String templateString, String diagramId) {
		this.templateString = templateString;
		this.variables = getVariablesFromTemplate();
		this.id = diagramId + ";" + UUID.randomUUID().toString();
	}

	public Property(String templateString, List<Variable> variables, String id) {
		super();
		this.templateString = templateString;
		this.variables = variables;
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
	
	private String getId() {
		return id;
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
	
	public static void persist(Property property) {
		IPath rootPath = ResourcesPlugin.getWorkspace().getRoot().getRawLocation();
		rootPath = rootPath.append(".properties/stored_properties.txt");
		File templateFile = rootPath.toFile();
		if (!templateFile.exists()) {
			try {
				templateFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (templateFile.isFile() && templateFile.canWrite()) {
			FileWriter fw = null;
			try {
				fw = new FileWriter(templateFile, true);
				fw.write(getPropertyAsStringEntry(property));
				fw.write(System.getProperty("line.separator"));

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (fw != null) {
						fw.close();
					}
				} catch (IOException e) {
				}

			}
		}
		// BufferedReader in = null;

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
			builder.append(property.getId()+";");
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
				persist(Property.this);
			}
		}
	}
}

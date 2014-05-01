package org.bflow.toolbox.check;

import java.util.HashMap;
import java.util.Vector;

import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * Defines a Plugin-Instance for the check plugin<br/>
 * this plugin holds neccessary references to the validation rules
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 05/09
 * @version 14/12/13
 */
public class CheckPlugin extends AbstractUIPlugin 
{
	public static final String ID = "org.bflow.toolbox.check"; //$NON-NLS-1$
	private final String LANGPREFID = "bflow.language";
	
	private static String activeDiagramType;
	
	private String activeDiagramLanguage;

	/**
	 * @generated
	 */
	public static final PreferencesHint DIAGRAM_PREFERENCES_HINT = new PreferencesHint(ID);
	
	/**
	 * instance of the CheckPlugin
	 */
	private static CheckPlugin instance;
	
	/**
	 * maps available languages
	 */
	private HashMap<String, Vector<Rule>> langMap = new HashMap<String, Vector<Rule>>();
	
	/**
	 * constructor
	 */
	public CheckPlugin() {
		
	}
	
	@Override
	public void start(BundleContext context) throws Exception {
		org.eclipse.xtend.typesystem.emf.check.CheckRegistry.getInstance();
		super.start(context);
		
		instance = this;
		PreferencesHint.registerPreferenceStore(DIAGRAM_PREFERENCES_HINT, this.getPreferenceStore());
		
		// abfragen der Spracheinstellung
		this.activeDiagramLanguage = this.getPreferenceStore().getString(LANGPREFID);
				
		// default wert
		if(activeDiagramLanguage.isEmpty())
			activeDiagramLanguage = "en"; //Platform.getNL().substring(0,2);
		
		if(LauncherConfigurator.getInstance().isIniFound()) {
			activeDiagramLanguage = LauncherConfigurator.getInstance().getPlatformNL().substring(0, 2);
		} else {
			activeDiagramLanguage = "en"; // default value
		}
	}
	
	/**
	 * returns a vector containing validation rules description for the actual diagram type<br/>
	 * @return vector containing validation rules description
	 */
	public Vector<Rule> getValidationRulesDescription()
	{
		if(activeDiagramType == null)
			return this.langMap.get(activeDiagramLanguage);
		else
		{
			Vector<Rule> requestedRules = new Vector<Rule>();
			
			for(Rule rule: this.langMap.get(activeDiagramLanguage))
				if(rule.getDiagramType().toLowerCase().equals(activeDiagramType.toLowerCase()))
					requestedRules.add(rule);
			
			return requestedRules;
		}
	}
	
	/**
	 * returns a vector containing validation rules description for the defined diagram type<br/>
	 * @param diagramType diagram type
	 * @return vector containing validation rule descriptions
	 */
	public Vector<Rule> getValidationRulesDescription(String diagramType)
	{
		if(diagramType == null)
			return this.langMap.get(activeDiagramLanguage);
		else
		{
			Vector<Rule> requestedRules = new Vector<Rule>();
			
			for(Rule rule: this.langMap.get(activeDiagramLanguage))
				if(rule.getDiagramType().toLowerCase().equals(diagramType.toLowerCase()))
					requestedRules.add(rule);
			
			return requestedRules;
		}
	}
	
	/**
	 * returns the instance of this plugin
	 * @return instance of this plugin
	 */
	public static CheckPlugin getInstance() {
		return instance;
	}
	
	/**
	 * Sets the active diagram type
	 * @param activeDiagramType active diagram type {epc, oepc}
	 */
	public static void setActiveDiagramType(String activeDiagramType) {
		CheckPlugin.activeDiagramType = activeDiagramType;
	}
	
	/**
	 * Sets the active diagram language.
	 * @param lang active diagram language {de, eng}
	 */
	public void setActiveLanguage(String lang)
	{
		this.activeDiagramLanguage = lang;
		this.getPreferenceStore().setValue(LANGPREFID, lang);
		LauncherConfigurator.getInstance().setPlatformNL(lang);
	}
	
	/**
	 * Returns the current set language.
	 * @return current set language
	 */
	public String getActiveLanguage()
	{
		return this.activeDiagramLanguage;
	}
	
	/**
	 * Returns the active diagram type.
	 * @return active diagram type
	 */
	public static String getActiveDiagramType() {
		return activeDiagramType;
	}
	
	/**
	 * Returns a vector containing installed languages.
	 * @return vector containing installed languages.
	 */
	public Vector<String> getAvailableLanguages()
	{
		Vector<String> langs = new Vector<String>();
		
		langs.add("Deutsch (de) - default");
		langs.add("English (en)");
		
		return langs;
	}
	
	/**
	 * Returns the language abbreviation according to the index.
	 * @param index index position of the available language vector
	 * @return language abbreviation
	 */
	public String getLanguageAbbreviation(int index)
	{
		if(index == 0)
			return "de";
		
		if(index == 1)
			return "en";
		
		return "";
	}
	
}

package org.bflow.toolbox.hive.addons.validation;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bflow.toolbox.hive.addons.AddonPlugin;
import org.bflow.toolbox.hive.nls.NLUtil;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.osgi.service.prefs.Preferences;

/**
 * Implements an administrative instance for the validation rules and behaviour.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 28.03.11
 * @version 13.09.13
 * 			03.07.15 Fixed issue when a rule is disabled by default
 * 
 */
public class ValidationService {
	
	/**
	 * Validation service type flag
	 */
	public static final int TYPE_SETUP = 0;
	
	/**
	 * Validation service type flag
	 */
	public static final int TYPE_DEFAULT = 1;
	
	/**
	 * Validation service type flag
	 */
	public static final int TYPE_ALL = 2;
	
	/**
	 * Validation service type flag
	 */
	public static final int TYPE_CUSTOM = 3;

	/**
	 * Extension point id.
	 */
	public static final String EXTENSION_ID_VALIDATION_RULE = AddonPlugin.EXTENSION_ID_ADDON_RULE;

	/**
	 * static instance
	 */
	private static ValidationService instance;

	/**
	 * map of rules
	 */
	private HashMap<String, ArrayList<Rule>> rulesMap = new HashMap<String, ArrayList<Rule>>();
	
	/**
	 * Preference store
	 */
	private Preferences prefStore = AddonPlugin.getInstance().getPreferencesStore();

	/**
	 * Constructor.
	 */
	private ValidationService() {
		try {
			loadRules();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loads the installed rules.
	 * 
	 * @throws IOException
	 */
	private void loadRules() throws IOException {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(EXTENSION_ID_VALIDATION_RULE);
		
		String systemAbbr = NLUtil.getActiveLanguageAbbreviation();

		for (IConfigurationElement element : config) {
			IContributor con = element.getContributor();
			Bundle bundle = Platform.getBundle(con.getName());

			String file = element.getAttribute("File");
			String abbr = element.getAttribute("Abbr");
			
			// Load no file with a non fitting language abbreviation 
			if(!abbr.equalsIgnoreCase(systemAbbr)) {
				continue;
			}
			
			InputStream stream = bundle.getEntry(file).openStream();
			
			ArrayList<Rule> rules = RuleParser.parseFile(stream, bundle);
			
			// Get already registered ones
			ArrayList<Rule> base = rulesMap.get(abbr);
			
			if (base == null) { // none has been registered yet
				base = rules;
			} else { // extend the existing ones
				base.addAll(rules);
			}
			
			rulesMap.put(abbr, base);
		}
	}

	/**
	 * Returns all rules associated with the language abbreviation.
	 * 
	 * @param abbreviation
	 *            language abbreviation
	 * @return list of rules
	 * @see Rule
	 */
	public List<Rule> getRules(String abbreviation) {
		return rulesMap.get(abbreviation);
	}

	/**
	 * Returns all rules of the specified type that are associated with the
	 * language abbreviation.
	 * 
	 * @param abbreviation
	 *            language abbreviation
	 * @param type
	 *            type of rules
	 * @return list of rules
	 */
	@Deprecated
	public List<Rule> getRulesByType(String abbreviation, String type) {
		ArrayList<Rule> filteredList = new ArrayList<Rule>();

		ArrayList<Rule> list = rulesMap.get(abbreviation);

		if (list == null)
			return null;

		for (Rule r : list) {
			if (r.getType().equalsIgnoreCase(type))
				filteredList.add(r);
		}

		return filteredList;
	}
	
	/**
	 * Returns all rules of the specified type that are associated with the
	 * language abbreviation.
	 * 
	 * @param abbr
	 *            language abbreviation
	 * @param type
	 *            see Class Constants
	 * @return Collection of rules
	 */
	public List<Rule> getRulesByType(String abbr, int type) {
		ArrayList<Rule> filteredList = new ArrayList<Rule>();
		ArrayList<Rule> list = rulesMap.get(abbr);
		
		if (list == null) return filteredList;
		if (type == ValidationService.TYPE_ALL) return list;
		
		for (Rule rule:list) {
			if (type == ValidationService.TYPE_DEFAULT) {
				if (rule.isDfault())
					filteredList.add(rule);
			}
			
			if (type == ValidationService.TYPE_SETUP) {
				if (prefStore.getBoolean(rule.getId(), rule.isDfault()))
						filteredList.add(rule);
			}
		}
		
		return filteredList;
	}
	
	/**
	 * Returns true if the rule shall be checked.
	 * <p/>
	 * If there is no rule defined, false will be returned.
	 * 
	 * @param ruleId
	 *            identifier of the rule
	 * @return true or false
	 */
	public boolean isEnabled(String ruleId) {			
		String ruleIdValue = prefStore.get(ruleId, null);
		
		// Value has been set by the user
		if (ruleIdValue != null) {
			boolean value = prefStore.getBoolean(ruleId, true);
			return value;
		}		
		
		// Use the default setting
		String langAbbr = Platform.getNL();
		ArrayList<Rule> rules = rulesMap.get(langAbbr);		
		Rule rule = null;
		
		for (Rule r : rules) {
			if (!r.getId().equalsIgnoreCase(ruleId)) continue;
			rule = r;
			break;
		}
		
		// This should never happen
		if (rule == null) return true;
		
		// Take the rule default value
		boolean dfaultValue = rule.isDfault();
		return dfaultValue;
	}
	
	/**
	 * Returns the user defined error message of a rule if there exists one. Otherwise the default message
	 * is returned.<p/>
	 * @param ruleId id of the rule
	 * @param value value that will be inserted into the error message
	 * @return error message of the rule out of the preferences store, the default or null if nothing has been found
	 */
	public String getErrorMessage(String ruleId, String value)	{		
		String prefStoreMsg = prefStore.get(ruleId+"Message", null);
		
		if (prefStoreMsg != null)
			return prefStoreMsg.replace("$value", value);
		
		String abbr = NLUtil.getActiveLanguageAbbreviation();
		
		ArrayList<Rule> rules = rulesMap.get(abbr);
		
		for (Rule rule:rules)
			if (rule.getId().equalsIgnoreCase(ruleId))				
					return rule.getMessage().replace("$value", value);
		
		return null;
	}
	
	/**
	 * Sets the enabled flag of a rule.
	 * @param ruleId id of the rule
	 * @param value true or false
	 */
	public void setEnabled(String ruleId, boolean value) {		
		prefStore.putBoolean(ruleId, value);
	}

	/**
	 * Returns the instance of the validation service.
	 * 
	 * @return instance
	 */
	public static ValidationService getInstance() {
		if (instance == null) {
			instance = new ValidationService();
		}

		return instance;
	}

}

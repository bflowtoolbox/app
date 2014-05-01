package org.bflow.toolbox.check;

import org.bflow.toolbox.hive.addons.validation.ValidationService;

/**
 * defines methods to request preferences settings
 * @author Arian Storch
 * @since 05/09
 * @version 06/07/11
 * @deprecated
 *
 */
public class PreferencesRequester 
{
	/**
	 * returns if the rule shall be checked<p/>
	 * if there is no rule defined, false will be returned
	 * @param ruleId identifier of the rule
	 * @return true or false
	 */
	public static boolean shallCheck(String ruleId)	{	
		return ValidationService.getInstance().isEnabled(ruleId);
	}
	
	/**
	 * returns the error message of the rule if there exists one in the preferences store<p/>
	 * error messages can be user set ones or delivered ones
	 * @param ruleId id of the rule
	 * @param value value that will be inserted into the error message
	 * @return error message of the rule out of the preferences store or null if nothing has been found
	 */
	public static String getErrorMessage(String ruleId, String value) {
		return ValidationService.getInstance().getErrorMessage(ruleId, value);
	}
	
	/**
	 * sets a rule in the preferences store enabled (true) or disabled (false)
	 * @param ruleId id of the rule
	 * @param value true or false
	 */
	public static void setRuleEnabled(String ruleId, boolean value)	{
		ValidationService.getInstance().setEnabled(ruleId, value);
	}
	
}

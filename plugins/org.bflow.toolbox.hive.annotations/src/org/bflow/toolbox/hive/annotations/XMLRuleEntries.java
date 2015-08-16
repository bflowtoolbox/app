package org.bflow.toolbox.hive.annotations;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Basic class to encapsulate a list of {@link RuleEntry}. It is used to read
 * and write this list to an xml file.
 * 
 * 
 * @author Felix Hoess <fhoess@users.sf.net>
 * @since 15.06.2015
 *
 */
@XmlRootElement(name = "Annotation_Rules")
public class XMLRuleEntries {


	private List<RuleEntry> annotationRules;

	public List<RuleEntry> getRule() {
		return annotationRules;
	}

	public void setRule(List<RuleEntry> rules) {
		this.annotationRules = rules;
	}
	
	public XMLRuleEntries() {

	}



}

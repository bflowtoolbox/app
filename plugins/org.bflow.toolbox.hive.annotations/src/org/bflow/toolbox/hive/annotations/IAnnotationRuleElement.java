package org.bflow.toolbox.hive.annotations;

/**
 * This interface is used for rule entries you want to internationalize.
 *
 * @author Felix Hoess <fhoess@users.sf.net>
 * @since 01.07.2015
 *
 */
public interface IAnnotationRuleElement {
 
	/**
	 * The value should be a string with a proper name of the rule and use the
	 * annotation for its field: <br>
	 * 
	 * "@XmlValue" <br>
	 * private String customName;
	 * 
	 */
	public String getValue();

	/**
	 * The value should be a string with a proper name of the rule and use the
	 * annotation for its field: <br>
	 * 
	 * "@XmlValue" <br>
	 * private String customName;
	 * 
	 */
	public void setValue(String value);

	/**
	 * 
	 * the attribute should be the language NL eg. "en_US" or "en_UK" the field
	 * should use the annotation: @XmlAttribute(name = "language", required =
	 * true)
	 * 
	 * @return
	 */
	public String getAttribute();

	/**
	 * the attribute should be the language NL eg. "en_US" or "en_UK" the field
	 * should use the annotation: @XmlAttribute(name = "language", required =
	 * true)
	 * 
	 * @param attribute
	 */
	public void setAttribute(String attribute);

	@Override
	public boolean equals(Object arg);


}

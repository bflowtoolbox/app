package org.bflow.toolbox.hive.annotations;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
public class AnnotationCategory implements IAnnotationRuleElement {

	@XmlValue
	private String categoryValue;
	@XmlAttribute(name = "language", required = true)
	//the language is required "en_US" should be default
	private String attribute;

	public AnnotationCategory() {

	}


	@Override
	public String getAttribute() {
		return attribute;
	}

	@Override
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	@Override
	public boolean equals(Object arg) {
		if (arg instanceof AnnotationCategory) {
			AnnotationCategory cat = (AnnotationCategory) arg;
			return (cat
					.getAttribute().equals(
					attribute));
		}
		return false;
	}

	@Override
	public String getValue() {
		return categoryValue;
	}

	@Override
	public void setValue(String value) {
		this.categoryValue = value;

	}

}

package org.bflow.toolbox.hive.annotations;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
public class AnnotationName implements IAnnotationRuleElement {

	@XmlValue
	private String nameValue;
	@XmlAttribute(name = "language", required = true)
	//the language is required "en_US" should be default
	private String attribute;

	public AnnotationName() {
	}

	@Override
	public String getValue() {
		return nameValue;
	}

	@Override
	public void setValue(String value) {
		this.nameValue = value;

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
		if (arg instanceof AnnotationName) {
			AnnotationName str = (AnnotationName) arg;
			return (str
					.getAttribute().equals(
					attribute));
		}
		return false;
	}



}

package org.bflow.toolbox.hive.annotations;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.core.runtime.Platform;

/**
 * Basic class to store an annotation rule entry. It consists of Strings for a
 * attribute name, operator, attribute value, a filename, category and a
 * direction for the icon). The file must be saved in the default (or custom
 * chosen) AnnotationIcons folder and should be an image file. A Rule entry
 * looks like:<br>
 * Category,attribute name,Operator,Value,Direction,Filename<br>
 * e.g. Risk, Risk probability, > , 0.02, NORTH, ExclamationMark.png
 * 
 * 
 * 
 * @author Felix Hoess <fhoess@users.sf.net>
 * @since 11.06.2015
 *
 */
@XmlRootElement(name = "Rule")
@XmlType(propOrder = { "categories", "ruleNames", "attributeName", "operator",
		"attributeValue", "filenames",
		"direction", "active" })
public class RuleEntry {

	public static final int ColumnQuantity = 7; //keep this number uptodate. Don't count the isActive Column
	public static final int ColumnCATEGORY = 0;
	public static final int ColumnATTRIBUTE_NAME = 1;
	public static final int ColumnOPERATOR = 2;
	public static final int ColumnVALUE = 3;
	public static final int ColumnDIRECTION = 4;
	public static final int ColumnFILENAME = 5;
	public static final int ColumnACTIVE = 6;
	public static final int ColumnRuleName = 7;

	public static final char SEPARATOR = ',';


	private String attributeName;
	private String operator;
	private String attributeValue;
	private String direction;

	/**
	 * holds category names of the rule (one entry each language)
	 */
	private List<AnnotationCategory> categories = new ArrayList<AnnotationCategory>();
	/**
	 * holds file names (relative path) of icons of the rule (one entry each language)
	 */
	private List<AnnotationFilename> filenames = new ArrayList<AnnotationFilename>();

	/**
	 * holds the name/description of the rule (one entry each language)
	 */
	private List<AnnotationName> ruleNames = new ArrayList<AnnotationName>();

	private boolean active = true;

	public RuleEntry() {
	}

	public RuleEntry(String category, String attributeName, String operator,
			String value, String direction, String filename, String ruleName) {
		this.attributeName = attributeName;
		this.attributeValue = value;

		switch (operator) {
		case "=":
		case "<":
		case ">":
		case "\u2260":
		case "\u2265":
		case "\u2264":
			this.operator = operator;
			break;
		default:
			this.operator = "=";
		}

		this.direction = direction;
		this.active = true;
		this.setFilename(filename);
		this.setCategory(category);
		this.setRuleName(ruleName);

	}

	public String getEntryInColumn(int index) {
		String str = "";
		switch (index) {
		case ColumnATTRIBUTE_NAME:
			str = getAttributeName();
			break;
		case ColumnOPERATOR:
			str = getOperator();
			break;
		case ColumnVALUE:
			str = getAttributeValue();
			break;
		case ColumnDIRECTION:
			str = getDirection();
			break;
		case ColumnFILENAME:
			str = getFilename();
			break;
		case ColumnCATEGORY:
			str = getCategory();
			break;
		case ColumnACTIVE:
			str = String.valueOf(isActive());
			break;
		case ColumnRuleName:
			str = getName();
			break;
		}

		return str;
	}

	public void setAttributeName(String key) {
		this.attributeName = key;
	}

	public void setAttributeValue(String value) {
		this.attributeValue = value;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}


	public String getAttributeName() {
		return attributeName;
	}

	public String getOperator() {
		return operator;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public String getDirection() {
		return direction;
	}


	//	@XmlTransient
	//	public String getFilename() {
	//		String nl = Platform.getNL();
	//		String country = nl.substring(0, 2);
	//		String defaultLanguage = "en_US"; //
	//		String defaultFilename = "";
	//		String filename = null;
	//
	//		for (AnnotationFilename filenameValue : filenames) {
	//			if (filenameValue.getAttribute().equals(nl)) {
	//				filename = filenameValue.getFilenameValue();
	//				break;
	//			}
	//			if (filenameValue.getAttribute().startsWith(country))
	//				filename = filenameValue.getFilenameValue();
	//			if (filenameValue.getAttribute().equals(defaultLanguage))
	//				defaultFilename = filenameValue.getFilenameValue();
	//		}
	//		if (filename == null)
	//			filename = defaultFilename;
	//		return filename;
	//
	//	}
	@XmlTransient
	public String getFilename() {
		return getStrForNLin(filenames);
	}

	public void setFilename(String filename) {
		AnnotationFilename fn = new AnnotationFilename();
		fn.setValue(filename);
		fn.setAttribute(Platform.getNL());
		boolean hasDefault = false;
		for (AnnotationFilename savedFn : filenames) {
			if (savedFn.getAttribute().equals("en_US")) {
				hasDefault = true;
				break;
			}
		}
		if (!filenames.contains(fn))
			filenames.add(fn);
		else {
			filenames.remove(filenames.indexOf(fn));
			filenames.add(fn);
		}


		hasDefault = (fn.getAttribute().equals("en_US"));
		if (!hasDefault) {
			AnnotationFilename def = new AnnotationFilename();
			def.setAttribute("en_US");
			def.setValue(filename);
			if (!filenames.contains(def))
			filenames.add(def);
		}
	}

	@XmlTransient
	public String getCategory() {
		return getStrForNLin(categories);
	}
	
	/**
	 * Returns the default (en_US) category name of rule or null if not existent.
	 * @return
	 */
	@XmlTransient
	public String getDefaultCategory() {
		String defCat =null;
		for (IAnnotationRuleElement nameInList : categories) {
			if (nameInList.getAttribute().equals("en_US")) {
				defCat= nameInList.getValue();
				break;
			}
		}
		return defCat;
	}



	public void setCategory(String category) {
		AnnotationCategory cat = new AnnotationCategory();
		cat.setValue(category);
		cat.setAttribute(Platform.getNL());
		//check, if entry has default values
		boolean hasDefault = false;
		for (AnnotationCategory savedCat : categories) {
			if (savedCat.getAttribute().equals("en_US")) {
				hasDefault = true;
				break;
			}
		}

		if (!categories.contains(cat)) {
			categories.add(cat);
		} else {
			categories.remove(categories.indexOf(cat));
			categories.add(cat);
		}
		hasDefault = (cat.getAttribute().equals("en_US"));
		if (!hasDefault) {
			AnnotationCategory def = new AnnotationCategory();
			def.setAttribute("en_US");
			def.setValue(category);
			if (!categories.contains(def))
				categories.add(def);
		}
	}

	@XmlTransient
	public String getName() {
		return getStrForNLin(ruleNames);

	}

	//TODO. This generic version doesnt work. feel free to do it correctly
	//	private void setStrForNLin(
	//			List<? super IAnnotationRuleElement> listOfRuleElementDest,
	//			List<? extends IAnnotationRuleElement> listOfRuleElementSrc,
	//			String string) {
	//
	//		IAnnotationRuleElement element = null;
	//
	//		if (listOfRuleElementDest.getClass().equals(AnnotationCategory.class)) {
	//
	//			element = new AnnotationCategory();
	//		} else if (listOfRuleElementDest.getClass()
	//				.equals(AnnotationName.class)) {
	//			element = new AnnotationName();
	//		} else if (listOfRuleElementDest.getClass()
	//				.equals(AnnotationFilename.class)) {
	//			element = new AnnotationFilename();
	//		}
	//		if (element == null)
	//			return;
	//
	//		element.setValue(string);
	//		element.setAttribute(Platform.getNL());
	//		//check, if entry has default values
	//		boolean hasDefault = false;
	//
	//		for (IAnnotationRuleElement savedEle : listOfRuleElementSrc) {
	//			if (savedEle.getAttribute().equals("en_US")) {
	//				hasDefault = true;
	//				break;
	//			}
	//		}
	//
	//		if (!listOfRuleElementDest.contains(element)) {
	//			listOfRuleElementDest.add(element);
	//		} else {
	//			listOfRuleElementDest
	//					.remove(listOfRuleElementDest.indexOf(element));
	//			listOfRuleElementDest.add(element);
	//		}
	//		hasDefault = (element.getAttribute().equals("en_US"));
	//		if (!hasDefault) {
	//			AnnotationCategory def = new AnnotationCategory();
	//			def.setAttribute("en_US");
	//			def.setValue(string);
	//			if (!listOfRuleElementDest.contains(def))
	//				listOfRuleElementDest.add(def);
	//		}
	//	}

	/**
	 * returns the category name (as String) for the used language in the current editor. 
	 * @param listOfRuleElement
	 * @return
	 */
	private String getStrForNLin(List<? extends IAnnotationRuleElement> listOfRuleElement) {
		String nl = Platform.getNL();
		String country = nl.substring(0, 2);
		String defaultLanguage = "en_US";
		String defaultValue = ""; //
		String strOfElement = null;

		for (IAnnotationRuleElement nameInList : listOfRuleElement) {
			if (nameInList.getAttribute().equals(nl)) {
				strOfElement = nameInList.getValue();
				break;
			}
			if (nameInList.getAttribute().startsWith(country))
				strOfElement = nameInList.getValue();
			if (nameInList.getAttribute().equals(defaultLanguage))
				defaultValue = nameInList.getValue();
		}
		if (strOfElement == null)
			strOfElement = defaultValue;
		return strOfElement;
	}

	public void setRuleName(String name) {
		AnnotationName nameOfRule = new AnnotationName();
		nameOfRule.setValue(name);
		nameOfRule.setAttribute(Platform.getNL());
		//check, if entry has default values
		boolean hasDefault = false;
		for (AnnotationName savedName : ruleNames) {
			if (savedName.getAttribute().equals("en_US")) {
				hasDefault = true;
				break;
			}
		}

		if (!ruleNames.contains(nameOfRule)) {
			ruleNames.add(nameOfRule);
		} else {
			ruleNames.remove(ruleNames.indexOf(nameOfRule));
			ruleNames.add(nameOfRule);
		}
		hasDefault = (nameOfRule.getAttribute().equals("en_US"));
		if (!hasDefault) {
			AnnotationName def = new AnnotationName();
			def.setAttribute("en_US");
			def.setValue(name);
			if (!ruleNames.contains(def))
				ruleNames.add(def);
		}
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public boolean equals(Object entry) {
		if (entry instanceof RuleEntry) {
			RuleEntry input = (RuleEntry) entry;
			return input.getAttributeName().equals(attributeName)
					&& input.getOperator().equals(operator)
					&& input.getAttributeValue().equals(attributeValue);
		}
		return false;

	}

	/**
	 * sets the operator (must be < = > \u2260 \u2265 \u2264) default is =
	 * 
	 * @param operator
	 */
	public void setOperator(String operator) {
		switch (operator) {
		case "=":
		case "<":
		case ">":
		case "\u2260":
		case "\u2265":
		case "\u2264":
			this.operator = operator;
			break;
		default:
			this.operator = "=";
		}

	}

	/**
	 * returns a String[] of the entry. Uses the given separator symbol as a
	 * split symbol (use the {@link #SEPARATOR} constant)
	 * 
	 * @param separator
	 * @return
	 */
	public String[] getEntrySeparated(char separator) {
		return (getCategory() + separator + getAttributeName() + separator
				+ getOperator() + separator + getAttributeValue() + separator
				+ getDirection() + separator + getFilename() + separator + isActive())
				.split(String
				.valueOf(separator));

	}


	public List<AnnotationCategory> getCategories() {
		return categories;
	}


	public void setCategories(List<AnnotationCategory> categories) {
		this.categories = categories;
	}

	public List<AnnotationFilename> getFilenames() {
		return filenames;
	}

	public void setFilenames(List<AnnotationFilename> filenames) {
		this.filenames = filenames;
	}

	public List<AnnotationName> getRuleNames() {
		return ruleNames;
	}

	public void setRuleNames(List<AnnotationName> ruleNames) {
		this.ruleNames = ruleNames;
	}



}

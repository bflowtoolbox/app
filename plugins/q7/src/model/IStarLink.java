/*
 * Created on Feb 22, 2005
 */
package model;

import util.Computing;

/**
 * @author Yijun Yu
 */
public class IStarLink {
	public int id;
	public String type;
	public IStarElement from;
	public IStarElement to;
	
	public IStarLink(String _op, IStarElement _from, IStarElement _to) {
		type = _op;
		from = _from;
		to = _to;
	}
	
	public boolean isOrDecomposition() {
		return type.equalsIgnoreCase("Or") || type.equalsIgnoreCase("Equal"); // Why "Equal" ???
	}
	
	public boolean isDecomposition() {
		return isAndDecomposition() || isOrDecomposition();
	}
	
	public boolean isAndDecomposition() {
		return type.equalsIgnoreCase("And");
	}
	
	/**
	 * Helper method for toString.
	 * @param linkType
	 * @return
	 */
	private String getFullLinkName(String linkType) {
		if (linkType.equalsIgnoreCase("And"))
			return "DecompositionLink";
		if (linkType.equalsIgnoreCase("Or"))
			return "MeansEndsLink";			
		if (linkType.startsWith("Dep"))
			return "DependencyLink";
		if (linkType.length() > 4 && (linkType.startsWith("Help") || linkType.startsWith("Hurt"))) 
			return linkType.substring(0, 4) + "Contribution";
		return linkType + "Contribution";
	}
	
	/**
	 * Helper method for toString.
	 * Returns the contribution link symbol (++, --, etc.) or "" if the link type is not a contribution link.
	 * @param linkType Should be "And", "Or", "Help", "Make", "Break", etc.
	 * @return
	 */
	private String getContributionLinkSymbol(String linkType) {
		if (Computing.propertyHolds("q7.model.IStarLink.op2name.ignore-label")) {
			if (linkType.equalsIgnoreCase("And"))
				return "";
			if (linkType.equalsIgnoreCase("Or"))
				return "";
		}			
		if (linkType.equalsIgnoreCase("Help"))
			return "+";
		if (linkType.equalsIgnoreCase("Hurt"))
			return "-";
		if (linkType.equalsIgnoreCase("Make"))
			return "++";
		if (linkType.equalsIgnoreCase("Break"))
			return "--";
		if (linkType.equalsIgnoreCase("Dep"))
			return "";			
		return linkType;
	}	
	
	public String toString() {
		String s;
		s = "Token Link_" + id + "\n" + "    IN IStar"  
			+ getFullLinkName(type) 
				+ "\n"
				+ "    WITH\n" + "       attribute, name\n          : \""
				+ getContributionLinkSymbol(type) + "\"\n"
				+ "       attribute, to\n              : Element_"
				+ from.id + "\n"
				+ "       attribute, from\n              : Element_"
				+ to.id + "\nEND\n";
		return s;
	}
	
}


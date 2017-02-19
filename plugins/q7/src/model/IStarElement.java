/*
 * Created on Feb 22, 2005
 */
package model;

import java.util.ArrayList;
import java.util.Iterator;

import util.Computing;
import util.Debugging;

/**
 * @author Yijun Yu
 */
public class IStarElement implements Comparable {
	public String name;
	public int id;
	public String key;
	public String decompositions;
	public boolean isSoftGoal;
	public boolean isOperationalization;
	public boolean isAgent;
	public IStarElement parent;
	public ArrayList<IStarElement> children = new ArrayList<IStarElement>(); // Elements
	public boolean isAspect;
	public String label;
	public String feature;
	public String control;
	public boolean isTask;

	public IStarElement(int _id, String _name, String _decompositions) {
		id = _id;
		name = _name;
		decompositions = _decompositions;
	}

	public IStarElement(IStarElement someElement) {
		id = someElement.id;
		name = someElement.name;
		decompositions = someElement.decompositions;
	}

	public void setSoftGoal() {
		isSoftGoal = true;
	}

	public String getType(String n) {
		/* special patterns */
		if (n == null)
			return null;
		String name = n;
		if (n.length() > 0 && n.charAt(0) == '"')
			name = n.substring(1);
		if (n.indexOf(">::") >= 0)
			name = n.substring(n.indexOf(">::") + 3);
		String type = (name.startsWith("Claim: ") ? "IStarClaimElement"
				: (name.startsWith("Provide: ") ? "IStarResourceElement"
						: (name.startsWith("Do ") ? "IStarTaskElement"
								: (name.startsWith("May ") ? "IStarSoftGoalElement"
										: (name.startsWith("Agent ") ? "IStarAgentElement"
												: (name.startsWith("Role ") ? "IStarRoleElement"
														: (name
																.startsWith("Actor ") ? "IStarActorElement"
																: (name
																		.startsWith("Position ") ? "IStarPositionElement"
																		: (name
																				.startsWith("Aspect ") ? "IStarAspectElement"
																				: (isAspect ? "IStarAspectElement"
																						: (isAgent ? "IStarAgentElement"
																								: (!isSoftGoal ? (isTask ? "IStarTaskElement"
																										: "IStarGoalElement")
																										: (isOperationalization ? "IStarTaskElement"
																												: "IStarSoftGoalElement")))))))))))));
		return type;
	}
	
	/**
	 * toString helper method.
	 * @param prefixes
	 * @param name
	 * @return
	 */
	private String stripPrefix(String prefixes[], String name) {
		for (int i = 0; i < prefixes.length; i++) {
			String prefix = prefixes[i];
			if (name.indexOf(prefix) >= 0) {
				name = name.substring(name.indexOf(prefix) + prefix.length());
			} else
				break;
		}
		return name;
	}

	public String toString() {
		String s;
		String global = "::";
		name = name.substring(name.indexOf(global) + global.length()); //FIXME: toString should not modify the name of the element!!! Find out why it's doing this and fix it.
		String[] types = { "Claim:", "Provide:", "Do", "May", "Agent", "Role",
				"Position", "Actor", "Aspect" };
		s = "Token Element_"
				+ id
				+ "\n     IN "
				+ getType(name)
				+ "\n     WITH \n        attribute, name\n             : \""
				+ Computing.strip_type_prefixes(types, name)
				+ "\"\n"
				+ parent_child()
				+ (getLabel().equals("") ? ""
						: "\n        attribute, label\n             : "
								+ labelclass(label))
				+ (getFeature().equals("") ? ""
						: "\n        attribute, feature\n             : "
								+ featureclass(feature))
				+ (getControl().equals("") ? ""
						: "\n        attribute, control\n             : "
								+ controlclass(control)) + labelquantity()
				+ decompositions + "END\n";
		return s;
	}

	public float satisfied = 0; 
	public float denied = 0; 
	public float p = 0; // performance

	private String labelquantity() {
		return "\n        attribute, sat\n             : " + this.satisfied
				+ "\n        attribute, den\n             : " + this.denied
				+ "\n        attribute, perf\n             : " + this.p + "\n";
	}

	/**
	 * @param label2
	 * @return a qualitative S/D label
	 */
	private String labelclass(String label2) {
		assert (label2 != null);
		if (label2.indexOf(",") >= 0) {
			// Yijun: i will have to change this into a list of float numbers
			// later...
			int i = label2.indexOf(",");
			String sat = label2.substring(0, i);
			String den = label2.substring(i + 1);
			int j = den.indexOf(",");
			String perf = null;
			if (j >= 0) {
				perf = den.substring(j + 1);
				den = den.substring(0, j);
			}
			satisfied = Float.parseFloat(sat);
			denied = Float.parseFloat(den);
			if (perf != null)
				p = Float.parseFloat(perf);
		} else if (label2.equalsIgnoreCase("FS")) {
			satisfied = 1;
			denied = 0;
		} else if (label2.equalsIgnoreCase("PS")) {
			satisfied = 0.5f;
			denied = 0;
		} else if (label2.equalsIgnoreCase("PD")) {
			satisfied = 0;
			denied = 0.5f;
		} else if (label2.equalsIgnoreCase("FD")) {
			satisfied = 0;
			denied = 1;
		} else if (label2.equalsIgnoreCase("CF")) {
			satisfied = 1;
			denied = 1;
		} else if (label2.equalsIgnoreCase("UN")) {
			satisfied = 0;
			denied = 0;
		}
		return Computing.SDtoLabel(satisfied, denied);
	}

	/**
	 * @param feature
	 * @return
	 */
	private String featureclass(String feature) {
		assert (feature != null);
		if (feature.equalsIgnoreCase("|"))
			return "IStarAlternativeFeatureLabel";
		if (feature.equalsIgnoreCase("/"))
			return "IStarOptionalFeatureLabel";
		return "IStarOptionalFeatureLabel"; // default
	}

	/**
	 * @param control
	 * @return
	 */
	private String controlclass(String control) {
		assert (control != null);
		if (control.equalsIgnoreCase("||"))
			return "IStarParallelControlLabel";
		if (control.equalsIgnoreCase(";"))
			return "IStarSequentialControlLabel";
		return "IStarSequentialControlLabel"; // default
	}

	/**
	 * @return
	 */
	private String parent_child() {
		String s = "";
		if (isAgent || isAspect) {
			for (Iterator i = children.iterator(); i.hasNext();) {
				IStarElement t = (IStarElement) i.next();
				s += "        attribute, children\n             : Element_"
						+ t.id + "\n";
			}
		} else {
			if (parent != null) {
				s += "        attribute, parent\n             : Element_"
						+ parent.id + "\n";
			}
		}
		return s;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object e) {
		IStarElement i = (IStarElement) e;
		return id - i.id;
	}

	/**
	 * @return Returns the label.
	 */
	public String getLabel() {
		if (label == null || label.trim().equals("")
				|| label.trim().equals("UN"))
			return "";
		return "@" + label + "@";
	}

	/**
	 * @param label
	 *            The label to set.
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return Returns the control.
	 */
	public String getControl() {
		if (control == null)
			return "";
		return control;
	}

	/**
	 * @param control
	 *            The control to set.
	 */
	public void setControl(String control) {
		this.control = control;
	}

	/**
	 * @return Returns the feature.
	 */
	public String getFeature() {
		if (feature == null)
			return "";
		return feature;
	}

	/**
	 * @param feature The feature to set.
	 */
	public void setFeature(String feature) {
		this.feature = feature;
	}
}

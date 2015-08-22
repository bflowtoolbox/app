package org.bflow.toolbox.hive.addons.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.bflow.toolbox.hive.addons.validation.Rule;
import org.bflow.toolbox.hive.libs.aprogu.collections.MapValue;

/**
 * Models a tree structure for validation rules.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 31/10/13
 */
public class RuleTree {

	/**
	 * Creates a new instance for the given rule set.
	 * 
	 * @param ruleSet
	 *            Rule set which will be used to create the tree
	 * @return New instance of a Rule Tree
	 */
	public static RuleTree create(List<Rule> ruleSet) {
		return new RuleTreeRoot(ruleSet);
	}

	/**
	 * Protected constructor.
	 */
	protected RuleTree() {
		super();
	}

	/** The rule. */
	private Rule rule;

	/** The label. */
	private String label = StringUtils.EMPTY;

	/** The children. */
	private List<RuleTree> children = new LinkedList<RuleTree>();

	/**
	 * Returns the label.
	 * 
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the label.
	 * 
	 * @param label
	 *            the new label
	 */
	void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Returns the children.
	 * 
	 * @return the children
	 */
	public RuleTree[] getChildren() {
		return children.toArray(new RuleTree[0]);
	}

	/**
	 * Adds the child.
	 * 
	 * @param ruleTree
	 *            the rule tree
	 */
	void AddChild(RuleTree ruleTree) {
		children.add(ruleTree);
	}

	/**
	 * Returns the rule.
	 * 
	 * @return the rule
	 */
	public Rule getRule() {
		return rule;
	}

	/**
	 * Sets the rule.
	 * 
	 * @param rule
	 *            the new rule
	 */
	void setRule(Rule rule) {
		this.rule = rule;
	}

	/**
	 * Checks for children.
	 * 
	 * @return true, if successful
	 */
	public boolean hasChildren() {
		return !children.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof RuleTree))
			return false;

		RuleTree ruleTree = (RuleTree) obj;
		if(rule == null)
			return label.equalsIgnoreCase(ruleTree.label);
		
		return rule == ruleTree.rule;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int retVal = 31;

		retVal = 17 * retVal + label.hashCode();
		retVal = 17 * retVal + children.hashCode();
		
		if(rule != null)
			retVal = 17 * retVal + rule.hashCode();

		return retVal;
	}
}

/**
 * Internal class to model the root of a rule tree.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 31/10/13
 */
class RuleTreeRoot extends RuleTree {

	private List<Rule> ruleSet;
	private boolean initialized;

	/**
	 * Instantiates a new rule tree root.
	 *
	 * @param ruleSet the rule set
	 */
	public RuleTreeRoot(List<Rule> ruleSet) {
		super();
		this.ruleSet = ruleSet;
		setLabel("Root");
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.utils.RuleTree#getChildren()
	 */
	@Override
	public RuleTree[] getChildren() {
		if (!initialized) {
			createChildren();
			initialized = true;
		}

		return super.getChildren();
	}

	/**
	 * Creates the children.
	 */
	private void createChildren() {
		Map<String, RuleTree> idToRuleTreeMap = new HashMap<String, RuleTree>();

		// Sort the rule set
		// XXX This kind of sort is destructive
		Collections.sort(ruleSet, new Comparator<Rule>() {

			private static final String UidFormatString = "%s/%s/%s";

			@Override
			public int compare(Rule arg0, Rule arg1) {
				String uid1 = getRuleUid(arg0);
				String uid2 = getRuleUid(arg1);
				return uid1.compareTo(uid2);
			}

			private String getRuleUid(Rule rule) {
				return String.format(UidFormatString, rule.getDiagramType(),
						rule.getClazz(), rule.getId());
			}
		});

		for (int i = -1; ++i < ruleSet.size();) {
			Rule rule = ruleSet.get(i);
			String ruleClass = rule.getClazz();

			RuleTree parent = this;

			// Handle sub trees
			int lastTokenIndex = ruleClass.lastIndexOf('/');
			if (lastTokenIndex != -1) {
				String parentPath = ruleClass.substring(0, lastTokenIndex);
				RuleTree _parent = idToRuleTreeMap.get(parentPath);
				if (_parent == null) {
					_parent = new RuleTree();
					_parent.setLabel(parentPath);
					_parent.setRule(null);
					parent.AddChild(_parent);
					idToRuleTreeMap.put(parentPath, _parent);
				}
				parent = _parent;
			}

			MapValue<RuleTree> ruleTreeItem = MapValue.createInstance();
			if (!MapValue.tryGetValue(idToRuleTreeMap, ruleClass, ruleTreeItem)) {
				RuleTree ruleTreeNode = new RuleTree();
				parent.AddChild(ruleTreeNode); // Add the node to the parent
				idToRuleTreeMap.put(ruleClass, ruleTreeNode);

				ruleTreeNode.setRule(null); // Marks the item as node
				String nodeLabel = lastTokenIndex != -1 ? ruleClass
						.substring(lastTokenIndex + 1) : ruleClass;
				ruleTreeNode.setLabel(nodeLabel);

				ruleTreeItem.Value = ruleTreeNode;
			}

			RuleTree ruleTreeNode = ruleTreeItem.Value;
			RuleTree ruleTreeNodeItem = new RuleTree();
			// Setting properties
			ruleTreeNodeItem.setLabel(rule.getName());
			ruleTreeNodeItem.setRule(rule);
			// Add child to node
			ruleTreeNode.AddChild(ruleTreeNodeItem);
		}
	}
}

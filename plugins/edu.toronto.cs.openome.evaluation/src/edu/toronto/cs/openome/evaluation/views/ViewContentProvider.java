package edu.toronto.cs.openome.evaluation.views;

import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.part.ViewPart;

import edu.toronto.cs.openome_model.Alternative;
import edu.toronto.cs.openome_model.Container;
import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.HumanJudgment;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.impl.GoalImpl;
import edu.toronto.cs.openome_model.impl.ResourceImpl;
import edu.toronto.cs.openome_model.impl.SoftgoalImpl;
import edu.toronto.cs.openome_model.impl.TaskImpl;

/*
 * The content provider class is responsible for
 * providing objects to the view. It can wrap
 * existing objects in adapters or simply return
 * objects as-is. These objects may be sensitive
 * to the current input of the view, or ignore
 * it and always show the same content 
 * (like Task List, for example).
 */

// Need this to be public in order to change the view content
public class ViewContentProvider implements IStructuredContentProvider, ITreeContentProvider {
	
	private TreeNode invisibleRoot;
	private ViewPart viewPart;
	
	public ViewContentProvider(ViewPart viewPart) {
		this.viewPart = viewPart;
	}

	public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		//
	}
	
	public void dispose() {
		//
	}
	
	public Object[] getRoot() {
		return getChildren(invisibleRoot);
	}
	
	public Object[] getElements(Object parent) {
		if(parent.equals(viewPart.getViewSite())) {
			if(invisibleRoot == null) {
				initialize();
			}
			
			return getChildren(invisibleRoot);
		}
		
		return getChildren(parent);
	}
	
	public Object getParent(Object child) {
		if(child instanceof TreeObject) {
			return ((TreeObject)child).getParent();
		}
		
		return null;
	}
	
	public Object [] getChildren(Object parent) {
		if(parent instanceof TreeNode) {
			return ((TreeNode)parent).getChildren();
		}
		
		return new Object[0];
	}
	
	public boolean hasChildren(Object parent) {
		if(parent instanceof TreeNode) {
			return ((TreeNode)parent).hasChildren();
		}
		
		return false;
	}
	
	public TreeNode addNode(Alternative alt) {
		TreeNode node = createTreeNode(alt);
		addTreeNode(node);
		
		return node;
	}
	
	public TreeNode addNode(Intention i, String type) {
		TreeNode node = createTreeNode(i, type);
		addTreeNode(node);
		
		return node;
	}
	
	private boolean addTreeNode(TreeNode child) {
		invisibleRoot.addChild(child);
		
		return true;
	}
	
	private TreeNode createTreeNode(Alternative alt) {
				
		String name = alt.getName();
		
		if(!alt.getDescription().isEmpty()) {
			name += " (" + alt.getDescription() + ")";
		}
		
		//I was getting a null pointer exception here, this is a quick fix
		if (alt.getDirection() == null) {
			alt.setDirection("unknown");
		}
		
		if(alt.getDirection().equals("forward")) {
			name += " [Forward Evaluation]";
		} else if(alt.getDirection().equals("backward")) {
			name += " [Backward Evaluation]";
		}
		
		TreeNode node = new TreeNode(name, alt, null, null);
		//System.out.println("creating alternative called " + alt.getName());
		return node;
	}
	
	private TreeNode createTreeNode(Intention i, String type) {
		
		String name = ""; 
		String actorName;
		Container con; 
		
		con = i.getContainer();
		if(con != null)	{
			actorName = " {" + con.getName() + "}";
		} else {
			actorName = "";
		}
		
		name += i.getName() + actorName;
		TreeNode node = new TreeNode(name, i, null, type);
		
		return node;
	}

	
	/**
	 * Code left in to demonstrate a dummy tree structure
	 */
	private void initialize() {
		invisibleRoot = new TreeNode("", null, null, null);
	}
	
	/**
	 * Adds child nodes to the specified node by iterating over each given intention
	 * and creating a new TreeObject for each. 
	 * @author aftabs
	 * @param map
	 * @param node
	 */
	public void addChildren(HashMap<Intention, EvaluationLabel> map, TreeNode node) {		
		TreeNode to;
		String actorName;
		Container con;
		
		Set<Intention> intentions = (Set<Intention>)map.keySet();
		
		for(Intention i : intentions) {			
			// Add each intention as a new TreeObject as a child
			
			con = i.getContainer();
			
			if(con != null)	{
				actorName = con.getName();
			} else {
				actorName = "";
			}
			
			to = new TreeNode(i.getName() + " {" + actorName + "}" , i, map.get(i), null);
			node.addChild(to);
			
			//Add the human judgments for this intention to the view
			EList<HumanJudgment> humanJudgements = i.getHumanJudgments();
			int j = 1;
			
			for (HumanJudgment judgement : humanJudgements) {
				to.addChild(new TreeNode("Judgment " + j++ + ": " + judgement.getResultLabel().toString(), 
						judgement, judgement.getResultLabel(), null));
			}
		}
	}
	
	
	/**
	 * 
	 * @param node The <code>TreeNode</code> displaying the <code>Intention</code> name 
	 * @param i The intention which has been decided via Human Judgment
	 * @param alts An <code>EList</code> containing all the alternatives in the model
	 */
	public void addJudgment(TreeNode node, Intention i, EList<Alternative> alts, String type)
	{
		
		// If both of these are true after the for loop, there is a conflict
		boolean denied = false;
		boolean satisfied = false;
		
		/* Differentiates between different kinds of intentions for icon label */
		if (i instanceof GoalImpl) {
			node.setHardgoalStatus(true);
		} else if (i instanceof SoftgoalImpl) {
			node.setSoftgoalStatus(true);
		} else if (i instanceof TaskImpl) {
			node.setTaskStatus(true);
		} else if (i instanceof ResourceImpl) {
			node.setResourceStatus(true);
		}
				
		// Retrieve the intention's Human Judgments and the associated labels from each alternative
		for(Alternative a : alts) {
			//I was getting a null pointer exception here, this is a quick fix
			if (a.getDirection() == null) {
				a.setDirection("unknown");
			}
			
			EList<HumanJudgment> allJudgments = i.getHumanJudgments();

			//Retrieve all human judgments associated with the intention 
			for (HumanJudgment h: allJudgments){

				EvaluationLabel label = h.getResultLabel();

				String name = label.getName() + " (" + a.getName() + ") ";

				if(a.getDirection().equals("forward")) {
					name += "[Forward Evaluation]";
				} else if(a.getDirection().equals("backward")) {
					name += "[Backward Evaluation]";
				}

				//Create a HashMap with the human judgment as the key and the associated alternative as the value
				HashMap<HumanJudgment, Alternative> map = new HashMap<HumanJudgment, Alternative>(1); 
				map.put(h, a);

				TreeNode subnode = new TreeNode(name, map, label, type);
				node.addChild(subnode);

				//Check 
				if(label == EvaluationLabel.DENIED || label == EvaluationLabel.PARTIALLY_DENIED) {
					denied = true;
				} else if(label == EvaluationLabel.SATISFIED || label == EvaluationLabel.PARTIALLY_SATISFIED) {
					satisfied = true;
				}
			}
			
		}
		
		// set the conflict flag
		node.setConflict(denied && satisfied); 

	}
	
	public void removeAllNodes() {
		invisibleRoot.clear();
	}
}

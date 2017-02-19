package edu.toronto.cs.openome_model.diagram.views.tree;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.part.ViewPart;

import edu.toronto.cs.openome_model.AndContribution;
import edu.toronto.cs.openome_model.AndDecomposition;
import edu.toronto.cs.openome_model.BreakContribution;
import edu.toronto.cs.openome_model.Container;
import edu.toronto.cs.openome_model.Contribution;
import edu.toronto.cs.openome_model.Decomposition;
import edu.toronto.cs.openome_model.Dependency;
import edu.toronto.cs.openome_model.HelpContribution;
import edu.toronto.cs.openome_model.HurtContribution;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Link;
import edu.toronto.cs.openome_model.MakeContribution;
import edu.toronto.cs.openome_model.Model;
import edu.toronto.cs.openome_model.OrContribution;
import edu.toronto.cs.openome_model.OrDecomposition;
import edu.toronto.cs.openome_model.SomeMinusContribution;
import edu.toronto.cs.openome_model.SomePlusContribution;
import edu.toronto.cs.openome_model.UnknownContribution;
import edu.toronto.cs.openome_model.impl.ModelImpl;
import edu.toronto.cs.openome_model.provider.DecompositionItemProvider;


class ViewContentProvider implements IStructuredContentProvider,
		ITreeContentProvider {
	private Node invisibleRoot;
	private ViewPart viewPart;
	
	public ViewContentProvider(ViewPart viewPart) {
		this.viewPart = viewPart;
	}

	public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		
	}

	public void dispose() {
	}

	public Object[] getElements(Object parent) {
		if (parent.equals(viewPart.getViewSite())) {
			if (invisibleRoot == null)
				invisibleRoot = new Node("");
			return getChildren(invisibleRoot);
		}
		return getChildren(parent);
	}

	public Object getParent(Object child) {
		if (child instanceof Node) {
			return ((Node) child).getParent();
		}
		return null;
	}

	public Object[] getChildren(Object parent) {
		if (parent instanceof Node) {
			return ((Node) parent).getChildren();
		}
		return new Object[0];
	}

	public boolean hasChildren(Object parent) {
		if (parent instanceof Node)
			return ((Node) parent).hasChildren();
		return false;
	}
	
	public void removeAllNodes() {
		invisibleRoot.clear();
	}
	
	public void refresh() {
		ModelImpl model = ModelInstance.getModelImpl();
		if (model != null) {
			buildTree(model);
		}
		
	}

	/*
	 * Create a tree representation of the current model.
	 */
	private void buildTree(Model model) {
		invisibleRoot = new Node("");
		DependumNode dependums = new DependumNode();
		
		// Create All Actors
		for (Container actor : model.getContainers()) {
			ActorNode parent = new ActorNode(actor);
			invisibleRoot.addChild(parent);
		}
		
		/*  Create All Intentions and Dependums from nodes at the root of the model */
		for (Intention intention : model.getIntentions()) {
			Node child = new IntentionNode(intention);
			
			if (!intention.getDependencyTo().isEmpty()) {
				dependums.addChild(child);
			} else {
				invisibleRoot.addChild(child);
			}
		}
		if (dependums.hasChildren()) {
			invisibleRoot.addChild(dependums);
		}
		
	}
	
	
}
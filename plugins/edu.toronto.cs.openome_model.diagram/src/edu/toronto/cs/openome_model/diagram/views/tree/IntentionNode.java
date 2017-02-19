package edu.toronto.cs.openome_model.diagram.views.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.registry.EditorRegistry;
import org.eclipse.ui.views.IViewDescriptor;

import edu.toronto.cs.openome_model.AndContribution;
import edu.toronto.cs.openome_model.AndDecomposition;
import edu.toronto.cs.openome_model.BreakContribution;
import edu.toronto.cs.openome_model.Container;
import edu.toronto.cs.openome_model.Contribution;
import edu.toronto.cs.openome_model.Decomposition;
import edu.toronto.cs.openome_model.Dependency;
import edu.toronto.cs.openome_model.Goal;
import edu.toronto.cs.openome_model.HelpContribution;
import edu.toronto.cs.openome_model.HurtContribution;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Link;
import edu.toronto.cs.openome_model.MakeContribution;
import edu.toronto.cs.openome_model.OrContribution;
import edu.toronto.cs.openome_model.OrDecomposition;
import edu.toronto.cs.openome_model.Resource;
import edu.toronto.cs.openome_model.Softgoal;
import edu.toronto.cs.openome_model.SomeMinusContribution;
import edu.toronto.cs.openome_model.SomePlusContribution;
import edu.toronto.cs.openome_model.Task;
import edu.toronto.cs.openome_model.UnknownContribution;
import edu.toronto.cs.openome_model.diagram.edit.parts.ModelEditPart;
import edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditor;
import edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin;
import edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorUtil;
import edu.toronto.cs.openome_model.diagram.providers.Openome_modelEditPartProvider;
import edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes;
import edu.toronto.cs.openome_model.diagram.providers.Openome_modelViewProvider;


/**
 * @author meatcar
 *
 */
class IntentionNode extends Node {
	private int usages; //TODO: Somehow count this...
	
	public IntentionNode(Intention intention) {
		this(intention, "", 3);
	}
	
	/**
	* Create a tree of a given height, with the given intention as the root, 
	* and all intentions that influence it as the nodes. 
	*/
	private IntentionNode(Intention intention, String linkName, int height) {
		super(intention.getName());
		setModel(intention);
		setLink(linkName);
		
		usages = intention.getContributesTo().size() + 
				intention.getDecompositionsTo().size() +
				intention.getDecompositionsTo().size();

		if (height > 0) {
			height--;
			List<Object> list;
			
			/* Iterate over all Decompositions */
			for (Decomposition link : intention.getDecompositionsTo()) {
				list = getIntentionInfluence(link);
				addChild(new IntentionNode((Intention) list.get(0), 
						(String) list.get(1), height));
			}
			/* Iterate over all Contributions */
			for (Contribution link: intention.getContributesFrom()) {
				list = getIntentionInfluence(link);
				addChild(new IntentionNode((Intention) list.get(0), 
						(String) list.get(1), height));
			}
			/* Iterate over all Dependencies */
			for (Dependency link : intention.getDependencyTo()) {
				list = getIntentionInfluence(link);
				addChild(new IntentionNode((Intention) list.get(0), 
						(String) list.get(1), height));
			}
		}
	}
	
	/**
	 * Return a list that describes the source intention and the link.
	 * @param link connects two intentions
	 * @return a list with the first item being the source intention, 
	 * 		   and the second item a string describing the link.
	 */
	private List<Object> getIntentionInfluence(Link link) {
		List<Object> list = new ArrayList<Object>();
		
		if (link instanceof AndDecomposition) {
			list.add(((AndDecomposition) link).getSource());
			list.add("Decomposition");
			
		} else if (link instanceof OrDecomposition) {
			list.add(((OrDecomposition) link).getSource());
			list.add("Means-Ends");
			
		} else if (link instanceof MakeContribution) {
			list.add(((MakeContribution) link).getSource());
			list.add("Make");
			
		} else if (link instanceof SomePlusContribution) {
			list.add(((SomePlusContribution) link).getSource());
			list.add("Some+");
			
		} else if (link instanceof SomeMinusContribution) {
			list.add(((SomeMinusContribution) link).getSource());
			list.add("Some-");
			
		} else if (link instanceof HelpContribution) {
			list.add(((HelpContribution) link).getSource());
			list.add("Help");
			
		} else if (link instanceof UnknownContribution) {
			list.add(((UnknownContribution) link).getSource());
			list.add("Unkwnown");
			
		} else if (link instanceof HurtContribution) {
			list.add(((HurtContribution) link).getSource());
			list.add("Hurt");
			
		} else if (link instanceof BreakContribution) {
			list.add(((BreakContribution) link).getSource());
			list.add("Break");
			
		} else if (link instanceof AndContribution) {
			list.add(((AndContribution) link).getSource());
			list.add("AND");
			
		} else if (link instanceof OrContribution) {
			list.add(((OrContribution) link).getSource());
			list.add("OR");
			
		} else if (link instanceof Dependency) {
			list.add(((Intention) ((Dependency) link).getDependencyTo()));
			list.add("Dependency");
		}
		
		return list;
	}
	
	@Override
	public Image getImage() {
		Intention intention = (Intention) getModel();
		Image image = null;
		
		if(intention instanceof Goal) {
			image = Openome_modelDiagramEditorPlugin.findImageDescriptor("/openome_model/icons/hardgoal.gif").createImage();
		} else if (intention instanceof Softgoal) {
			image = Openome_modelDiagramEditorPlugin.findImageDescriptor("/openome_model/icons/softgoal.gif").createImage();
		} else if (intention instanceof Task) {
			image = Openome_modelDiagramEditorPlugin.findImageDescriptor("/openome_model/icons/task.gif").createImage();
		} else if (intention instanceof Resource) {
			image = Openome_modelDiagramEditorPlugin.findImageDescriptor("/openome_model/icons/resource.gif").createImage();
		}
		return image;
	}
	
	public String getUsages() {
		return String.valueOf(Math.max(usages - 1, 0));
	}
	
}
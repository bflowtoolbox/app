package edu.toronto.cs.openome_model.diagram.views.tree;

import java.util.ArrayList;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.IViewDescriptor;

import edu.toronto.cs.openome_model.diagram.edit.parts.ModelEditPart;
import edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditor;
import edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes;

class Node implements IAdaptable {
	private String name = "";
	private String link = "";
	private Node parent;
	private ArrayList children;
	private EObject model;
	
	public Node(String name) {
		this.name = name;
		children = new ArrayList();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public String toString() {
		return getName();
	}
	
	public void addChild(Node child) {
		children.add(child);
		child.setParent(this);
	}
	
	public void removeChild(Node child) {
		children.remove(child);
		child.setParent(null);
	}
	
	public Node [] getChildren() {
		return (Node [])children.toArray(new Node[children.size()]);
	}
	
	public boolean hasChildren() {
		return children.size()>0;
	}
	
	public void clear() {
		children.clear();
	}
	
	public Object getAdapter(Class key) {
		return null;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLink() {
		if (link == null) {
			return "";
		}
		return link;
	}
	
	/**
	 * Return an image representing the shape of the node in the diagram
	 */
	public Image getImage() {
		return null;
	}
	
	/**
	 * Return a string representation of the number of paths affected by this node
	 * other than the one that this node is currently part of.
	 */
	public String getUsages() {
		return "";
	}
	
	protected void setModel(EObject model) {
		this.model = model;
	}

	public EObject getModel() {
		return model;
	}
	/**
	 * Select the node in the diagram
	 */
	public void select() {
		if (this.getModel() == null) {
			return;
		}
		IWorkbenchPage page = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();

		if (page != null) {
			IEditorPart editor = page.getActiveEditor();
			if (editor instanceof Openome_modelDiagramEditor) {
				Openome_modelDiagramEditor diagram = (Openome_modelDiagramEditor) editor;
				ModelEditPart modelPart = (ModelEditPart) diagram.getDiagramEditPart();
				EditPart part = modelPart.findEditPart(modelPart, this.getModel());
				if (part != null) {
					((Openome_modelDiagramEditor) editor).getDiagramGraphicalViewer().deselectAll();
					((Openome_modelDiagramEditor) editor).getDiagramGraphicalViewer().select(part);
				}
			}
		}
	}
	
}
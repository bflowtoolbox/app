package edu.toronto.cs.openome.evaluation.views;

import org.eclipse.core.runtime.IAdaptable;

// This class associates each node in the tree with an object
// Possible objects: Alternative, Intention, EvaluationLabel 
public class TreeObject implements IAdaptable {
	private String name;
	private TreeNode parent;
	
	// the object this TreeObject represents
	protected Object obj;
	protected Object img;
	
	public TreeObject(String name, Object obj, Object img) {
		this.name = name;
		this.obj = obj;
		this.img = img;
	}
	
	public String getName() {
		return name;
	}
	
	public void setParent(TreeNode parent) {
		this.parent = parent;
	}
	
	public TreeNode getParent() {
		return parent;
	}
	
	public String toString() {
		return getName();
	}
	
	public Object getAdapter(Class key) {
		return null;
	}
	
	public void setObject(Object obj){
		this.obj = obj;
	}
	
	public Object getObject(){
		return this.obj;
	}
	
	public Object getImg() {
		return this.img;
	}
	
	public boolean equals(TreeObject to){
		return ((this.name == to.getName()) 
					&& (this.obj).equals(to.getObject()));
	}
	
	public boolean equalObject(Object o){
		return ((this.obj).equals(o));
	}
}

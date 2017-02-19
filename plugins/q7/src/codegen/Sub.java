package codegen;

import java.util.Vector;

import model.IStarElement;

/**
 * The class is used to store informaiton about a subgraph.--Xiao X.Deng
 */
public class Sub {
	public String name;

	public Vector<IStarElement> children;

	public String parentName;

	public Vector<String> subFrom;

	public Vector<String> subTo;

	public Vector<String> subLinkNames;

	public Sub() {
		name = "";
		children = new Vector<IStarElement>();
		parentName = "";
		subFrom = new Vector<String>();
		subTo = new Vector<String>();
		subLinkNames = new Vector<String>();
	}
}
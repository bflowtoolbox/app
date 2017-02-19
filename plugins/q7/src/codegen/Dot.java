package codegen;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import model.Advice;
import model.IStarElement;
import model.IStarLink;
import util.Computing;
import util.SortArrayList;

public class Dot extends IStar {

	public Dot(ArrayList<Advice> a) {
		super(a);
	}
	public void generateGoalModel(String out_file) {
		process_q7_model(out_file);
	}
	/**
	 * Saving to .dot file -- Xiao X.Deng
	 *   for the visible elements -- Yijun
	 * @param propositions
	 *            individuals in a model
	 * @return String the content of the .dot file
	 */
	public void generateDot(String out_file) {
		process_q7_model(out_file);
		boolean neato = System.getProperty("Using neato")!=null; 
		String text = "";
		text = init_graph(text, neato);
		Vector<Sub> subs = new Vector<Sub>();
		text = output_nodes(text, subs);		
		Hashtable<String, SortArrayList> s = new Hashtable<String, SortArrayList>();
		text = output_links(text, neato, subs, s);
		text = output_hidden_links(text, s);
		text = output_subgraphs(text, neato, subs);
		text = end_graph(text);
		out.print(text);
		return;
	}
	private String end_graph(String text) {
		text += "}\n";
		return text;
	}
	private String init_graph(String text, boolean neato) {
		if (!neato)
			text += "digraph G {\n";
		else
			text += "graph G {\n";
		text += "size=\"7.5, 10\";\n";
		return text;
	}
	private String output_nodes(String text, Vector<Sub> subs) {
		for (Enumeration<String> i = intentions.keys(); i.hasMoreElements(); ) {
			Advice a = intentions.get(i.nextElement());
			IStarElement g = elements.get(a);
			String nodeName = "";
			Sub newSub = new Sub();
			if (g.children.size()>0) {
				newSub.name = nodeName;
				newSub.parentName = g.name;
				newSub.children.addAll(g.children);
				subs.add(newSub);
			} else {
				text += (String) ""+g.id + " [label=\"" + Computing.strip_quote(g.name) + "\" ];\n";
			}
		}
		return text;
	}
	private String output_links(String text, boolean neato, Vector<Sub> subs, Hashtable<String, SortArrayList> s) {
		for (Enumeration<String> i = links.keys(); i.hasMoreElements(); ) {
			IStarLink l = links.get(i.nextElement());
			if (l==null) continue;
			if (!l.isDecomposition()) {
				String from = "" + l.to.id;
				String to = "" + l.from.id;
				text = output_link(text, neato, subs, l, from, to);
			} else {
				String from = "" + l.from.id;
				String to = "" + l.to.id;
				text = output_link(text, neato, subs, l, from, to);
				SortArrayList lst = s.get(from);
				if (lst==null) {
					lst = new SortArrayList();
					s.put(from, lst);
				}
				boolean already = false;
				int m = l.to.id;
				for (int j =0; j<lst.size(); j++) {
					Integer n = (Integer) lst.get(j);
					if (n.intValue() == m) {
						already = true; break;
					}
				}
				if (!already)
					lst.add((Comparable) new Integer(m));
				s.put(from, lst);
			}
		}
		return text;
	}
	private String output_subgraphs(String text, boolean neato, Vector<Sub> subs) {
		for (int h = 0; h < subs.size(); h++) {
			Sub aSub = subs.elementAt(h);
			if (neato)
				text += "{\n";
			else {
				text += "subgraph cluster" + h + " {\n";
				text += aSub.parentName	+ ";\n";
			}
			for (int h1 = 0; h1 < aSub.children.size(); h1++) {
				text += ((IStarElement)aSub.children.elementAt(h1)).id + ";\n";
			}
			for (int h2 = 0; h2 < aSub.subFrom.size(); h2++) {
				text += aSub.subFrom.elementAt(h2) + (neato?"--":"->")
						+ aSub.subTo.elementAt(h2) + "[label="
						+ (String) aSub.subLinkNames.elementAt(h2) + "];\n";
			}
			text += "}\n";
		}
		return text;
	}
	private String output_hidden_links(String text, Hashtable<String, SortArrayList> s) {
		for (Enumeration<String> j = s.keys(); j.hasMoreElements();) {
			String t = j.nextElement();
			SortArrayList l = s.get(t);
			if (l.size()>1) {
				text += "{";
				text += "rank = same\n";
				text += (Integer) l.get(0); 
				for (int k = 1; k < l.size(); k++) {
					text += " -> " + ((Integer) l.get(k)); 
				}
				text += "[weight=1000, style=\"invis\"" + "];\n";
				text += "}\n";
			}
		}
		return text;
	}
	private String output_link(String text, boolean neato, Vector<Sub> subs, IStarLink l, String from, String to) {
		boolean inside = false;
		for (int h = 0; h < subs.size(); h++) {
			Sub aSub = subs.elementAt(h);
			if (isInaSub(aSub, from) && isInaSub(aSub, to)) {
				aSub.subFrom.add(from);
				aSub.subTo.add(to);
				aSub.subLinkNames.add(""+l.id);
				inside = true;
				break;
			}
		}
		if (!inside)
			text += ""+ from + (neato?"--":"->")
				 + to + "[label=" + GraphLabel(l.type) + "];\n";
		return text;
	}

	private String GraphLabel(String op) {
		if (op.equals("&"))
			op = "AND";
		else if (op.equals("|")) 
			op = "OR";
		else if (op.equals("+"))
			op = "HELP";
		else if (op.equals("-"))
			op = "HURT";
		else if (op.equals("++"))
			op = "MAKE";
		else if (op.equals("--"))
			op = "BREAK";
		else if (op.equals("~"))
			op = "DEPENDS";
		return op;
	}
	/**
	 * Tell if a node is in the subgraph--Xiao X.Deng
	 * 
	 * @param aSub
	 *            the subgraph
	 * @param s
	 *            the node'id
	 * @return boolean true if the node is in the subgraph
	 */
	public static boolean isInaSub(Sub aSub, String s) {
		if (s.equals(aSub.parentName)) {
			return true;
		} else {
			for (IStarElement e: aSub.children) {
				if (s.equals(e.name)) {
					return true;
				}
			}
			return false;
		}
	}
}

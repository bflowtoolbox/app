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

public class RSF extends IStar {

	public RSF(ArrayList<Advice> a) {
		super(a);
	}
	public void generateGoalModel(String out_file) {
		process_q7_model(out_file);
	}
	/**
	 * @param propositions
	 *            individuals in a model
	 * @return String the content of the .dot file
	 */
	public void generateRSF(String out_file) {
		process_q7_model(out_file);
		String text = "";
		Vector<Sub> subs = new Vector<Sub>();
		text = output_nodes(text, subs);		
		Hashtable<String, SortArrayList> s = new Hashtable<String, SortArrayList>();
		text = output_links(text, subs, s);
		text = output_hidden_links(text, s);
		text = output_subgraphs(text, subs);
		out.print(text);
		return;
	}
	private String output_nodes(String text, Vector<Sub> subs) {
		for (Enumeration<Advice> i = elements.keys(); i.hasMoreElements(); ) {
			Advice a = i.nextElement();
			IStarElement g = elements.get(a);
			text += "type\t" + g.id + "\t\"" + g.getType(Computing.strip_quote(g.name)) + "\"\n";
			text += "name\t" + g.id + "\t\"" + Computing.strip_quote(g.name) + "\"\n";
			String nodeName = "";
			Sub newSub = new Sub();
			if (g.children.size()>0) {
				newSub.name = nodeName;
				newSub.parentName = ""+ g.id;
				newSub.children.addAll(g.children);
				subs.add(newSub);
			} else {
				if (g.parent!=null)
					text += "actor\t" + g.id + "\t" + g.parent.id + "\n";
			}
		}
		return text;
	}
	private String output_links(String text, Vector<Sub> subs, Hashtable<String, SortArrayList> s) {
		for (Enumeration<String> i = links.keys(); i.hasMoreElements(); ) {
			IStarLink l = links.get(i.nextElement());
			if (l==null) continue;
			if (!l.isDecomposition()) {
				String from = "" + l.to.id;
				String to = "" + l.from.id;
				text = output_link(text, subs, l, from, to);
			} else {
				String from = "" + l.from.id;
				String to = "" + l.to.id;
				text = output_link(text, subs, l, from, to);
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
	private String output_subgraphs(String text, Vector<Sub> subs) {
		for (int h = 0; h < subs.size(); h++) {
			Sub aSub = subs.elementAt(h);
			for (int h1 = 0; h1 < aSub.children.size(); h1++) {
				text += "contains" + "\t" + aSub.parentName + "\t" + ((IStarElement)aSub.children.elementAt(h1)).id + "\n";
			}
			for (int h2 = 0; h2 < aSub.subFrom.size(); h2++) {
				text += ((String) aSub.subLinkNames.elementAt(h2)) + "\t" 
				+ aSub.subFrom.elementAt(h2) + "\t" 
                + aSub.subTo.elementAt(h2) + "\n";
			}
		}
		return text;
	}
	private String output_hidden_links(String text, Hashtable<String, SortArrayList> s) {
		for (Enumeration<String> j = s.keys(); j.hasMoreElements();) {
			String t = j.nextElement();
			SortArrayList l = s.get(t);
			if (l.size()>1) {
				for (int k = 1; k < l.size(); k++) {
					text += "order\t" + (Integer) l.get(k-1) + "\t" + ((Integer) l.get(k)) + "\n"; 
				}
			}
		}
		return text;
	}
	private String output_link(String text, Vector<Sub> subs, IStarLink l, String from, String to) {
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
			text += GraphLabel(l.type) + "\t" + from + "\t" + to + "\n";
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

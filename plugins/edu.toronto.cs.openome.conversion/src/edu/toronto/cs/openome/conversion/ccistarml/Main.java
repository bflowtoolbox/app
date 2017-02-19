/*
 * Main.java
 * Sample of using ccistarml Java Package
 *
 * it is part of the ccistarml Java Package
 * version 0.6
 * Created on July 4 of 2007, By Carlos Cares
 * Updated to v0.6.1  on September 20 of 2007 By Carlos Cares
 */
package edu.toronto.cs.openome.conversion.ccistarml;

import java.lang.String;
import java.util.*;
import java.io.*;

public class Main {

	public Main() {
	}

	public static void main(String[] args) {
		ERelementList erL;
		ERelement erle;
		ccistarmlFile f = new ccistarmlFile();
		f.loadFile("/home/showzeb/Desktop/exports/a/TCExample.istarml"); // put your file name here !
		f.xmlParser();
		f.istarmlParser();
		//System.out.println("Cantidad de errores: " + f.errors());
		//f.displayErrors();
		//f.displayXML();
		System.out.println(f.buildXML());
		


//		ccistarmlFile f = new ccistarmlFile();
//		ccistarmlContent s = f.xmlStructure();
//		
//		ccistarmlContent d1,a,b,c,d,e,g,h,t,p,q, st1, st2, st3, f1,f2,f3,f4,f5,f6;
//		d1 = f.add_diagram("Tropos's decomposition example");

// ------------------------------------------------------------------------
//		a = d1.add_task("a");
//		b = d1.add_goal("b");
//		c = d1.add_goal("c");
//		b.add_decomposition(a);
//		c.add_decomposition(a);
//		a = d1.add_actor("My organizational unit");
//		b = d1.add_agent("Carlos");
//		c = d1.add_role("Finance Supervisor");
//		d = d1.add_position("General Manager");
//		d.add_covers(c);
//		b.add_occupies(d);
//		
//		e = d1.add_goal("To provide software support");
//		t = d1.add_task("To implement a metric bases quality");
//		g = b.add_resource("Viruses behaviour databse");
//		h = b.add_goal("A support job allows knowing organizations");
//		
//		st1 = d.add_task("To elicitate quality metrics");
//		st2 = d.add_task("To configure BSC system");
//		st1.add_decomposition(t, "and");
//		st2.add_decomposition(t, "and");
//		g.add_means_end(st2);
//		t.add_means_end(e);
		
//-------------------------------------------------------------------------
//		f1 = d1.add_softgoal("Help as Many Kids as possible");
//		f2 = d1.add_softgoal("High Quality Counselling");
//		f3 = d1.add_task("Provide Counselling via text message");
//		f4 = d1.add_task("Provide Counselling via Cyber Cafe/Portal/Chat Room");
//		f5 = d1.add_resource("Text Messaging Service");
//		f6 = d1.add_resource("Cyber Cafe/Portal/Chat Room Service");
//		
//		
//		st1 = d1.add_agent("Kids and youth");
//		a = st1.add_task("Kids use Cyber Cafe/Portal/Chat Room");
//		b = st1.add_task("Use Text Messaging");
//		c = st1.add_goal("Help be acquired");
//		d = st1.add_goal("Anonymity");
//		e = st1.add_softgoal("Immediacy [Service]");
//		g = st1.add_softgoal("Comfortableness with Service");
//		h = st1.add_softgoal("Get Effective Help");
//		a.add_dependee(f6);
//		a.add_contribution(e, "make");
//		a.add_contribution(d, "hurt");
//		a.add_means_end(c);
//		a.add_contribution(g, "help");
//		b.add_dependee(f5);
//		b.add_contribution(c, "hurt");
//		b.add_contribution(d, "help");
//		b.add_contribution(e, "hurt");
//		b.add_contribution(g, "help");
//		d.add_contribution(g, "help");
//		d.add_contribution(h, "help");
//		e.add_contribution(h, "help");
//		g.add_contribution(h, "help");
//		
//		st2 = d1.add_agent("Organization");
//		a = st2.add_task("Use Text Messaging");
//		b = st2.add_task("Use Cyber Cafe/Portal/Chat Room");
//		c = st2.add_softgoal("Anonymity [Services]");
//		d = st2.add_goal("Provide Online Counselling Service");
//		e = st2.add_softgoal("Immediacy [Services]");
//		g = st2.add_softgoal("Avoid Scandal");
//		h = st2.add_softgoal("High Quality Counselling");
//		t = st2.add_softgoal("Increase Funds");
//		p = st2.add_softgoal("Help as many kids as possible");
//		q = st2.add_softgoal("Help Kids");
//		a.add_dependee(f3);
//		f5.add_dependee(a);
//		a.add_contribution(c, "help");
//		a.add_contribution(h, "break");
//		a.add_means_end(d);
//		a.add_contribution(e, "hurt");
//		b.add_dependee(f4);
//		f6.add_dependee(b);
//		b.add_contribution(c, "hurt");
//		b.add_means_end(d);
//		b.add_contribution(e, "help");
//		b.add_contribution(h, "hurt");
//		c.add_contribution(g, "help");
//		e.add_contribution(h, "help");
//		g.add_contribution(t, "help");
//		h.add_dependee(f2);
//		h.add_contribution(t, "help");
//		h.add_contribution(q, "help");
//		t.add_contribution(p, "help");
//		p.add_dependee(f1);
//		p.add_contribution(q, "help");
//		
//		st3 = d1.add_role("Counsellors");
//		a = st3.add_task("Use Cyber Cafe/Portal/Chat Room");
//		b = st3.add_task("Use Text Messaging");
//		c = st3.add_softgoal("Listen for cues");
//		d = st3.add_goal("Provide Online Counselling Service");
//		e = st3.add_softgoal("Help as many kids as possible");
//		g = st3.add_softgoal("High Quality Counselling");
//		h = st3.add_softgoal("Avoid Burnout");
//		t = st3.add_softgoal("Happiness [Counsellors]");
//		f4.add_dependee(a);
//		a.add_contribution(c, "hurt");
//		a.add_means_end(d);
//		f3.add_dependee(b);
//		b.add_contribution(c, "break");
//		b.add_means_end(d);
//		c.add_contribution(g, "help");
//		d.add_contribution(e, "help");
//		f1.add_dependee(e);
//		e.add_contribution(g, "hurt");
//		e.add_contribution(h, "hurt");
//		e.add_contribution(t, "help");
//		f2.add_dependee(g);
//		g.add_contribution(t, "help");
//		h.add_contribution(t, "help");
//		t.add_contribution(g, "help");
		
// --------------------------------------------
		
//		a = d1.add_goal("a");
//		b = d1.add_resource("b");
//		//st1 = d1.add_agent("agent");
//		c = d1.add_goal("c");
//		a.add_dependee(b);
//		b.add_dependee(c);
//		
//		//System.out.println(f.buildXML());
//		f.saveFile("/home/showzeb/Desktop/test.istarml");
//		
//		
//		ERelementList erL;
//		ERelement erle;
//		f = new ccistarmlFile();
//		f.loadFile("/home/showzeb/Desktop/test.istarml");
//		f.xmlParser();
//		erL = new ERelementList(f.xmlStructure());
//		erL.display();
//		
//		Iterator it = erL.list().iterator();
//		System.out.println("Intentional Elements");
//		while(it.hasNext()) {
//			erle = (ERelement)it.next();
////			if (erle.name.equals("ielement"))
////				System.out.println("Code:"+erle.ID+" " +
////						erle.attribute + " is on diagram " + erle.diagram);
//		}
	}
}

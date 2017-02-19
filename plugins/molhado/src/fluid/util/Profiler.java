package fluid.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Vector;

class Stats {
  String method;
  Stats(String m) {
    method = m;
  }
  int count = 0;    // number of times called
  int timeIn = 0;   // cumulative time spent in the function
  int timeOut = 0;  // cumulative time spent calling other functions
  int timeHere = 0; // actual time in the function is timeIn - timeOut
  Vector others = new Vector();
}

// assuming same time per call
// could assume time proportional to "time spent"
class Call {
  int count;
  Stats callee;

  Call(int count, Stats callee) {
    this.count = count;
    this.callee = callee;
  }
}

public class Profiler { 
  public static void main(String[] args) throws IOException {
    InputStream in = (args.length > 0) ? 
      new FileInputStream(args[0]) : System.in;
    BufferedReader prof = new BufferedReader(new InputStreamReader(in));

    String unknown = "<unknown_caller>".intern();
    Hashtable times = new Hashtable();
    // Hashtable memory = new Hashtable();
    boolean callcount = true;

    String line = prof.readLine(); // skip the first line
    line = prof.readLine();
    while (line != null) {
      // System.out.println(line);
      StringTokenizer st = new StringTokenizer(line);
      try {
	if (callcount) {
	  String count = st.nextToken();
	  if (count.startsWith("handles")) {
	    System.out.println("Bytes\tCount\tKind of object");
	    line = prof.readLine(); // skip the sig/count/bytes line
	    line = prof.readLine();
	    callcount = false; 
	    continue;
	  }
	  String callee = st.nextToken().intern();
	  String caller = st.nextToken().intern();
	  String time = st.nextToken(); // cumulative
	  
	  int count0 = Integer.valueOf(count).intValue();
	  int time0 = Integer.valueOf(time).intValue();
	  
	  Stats s = getFromTable(times, callee);
	  s.count  += count0;
	  s.timeIn += time0;

	  Stats s2 = getFromTable(times, caller);
	  if (caller != unknown) {
	    s2.timeOut += time0;
	  }
	  s2.others.addElement(new Call(count0, s));

	  // System.out.println(count0+" "+callee+" "+caller+" "+time0);
	}
	else {
	  String name = st.nextToken();
	  if (!name.equals("***")) {
	    String count = st.nextToken();
	    String bytes = st.nextToken();
	    // int count0 = Integer.valueOf(count).intValue();
	    // int bytes0 = Integer.valueOf(bytes).intValue();
	    
	    System.out.println(bytes + "\t" + count + "\t" + name);
	  }
	}
      }
      catch(NoSuchElementException e) {}
      
      line = prof.readLine();
    }
    Enumeration en = times.elements();
    int totalTime = 0;

    try {
      while(true) {
	Stats s = (Stats) en.nextElement();
	s.timeHere = (s.timeIn - s.timeOut);
	s.timeIn = -1;          // to be recomputed below
	totalTime += s.timeHere;
      }
    }
    catch(NoSuchElementException e) {}

    Stats us = getFromTable(times, unknown);
    us.timeHere = 0;
    us.count = 1;
    computeCumulativeTime(us, 1);

    Vector topTimes = new Vector();
    en = times.elements();

    try {
      while(true) {
	Stats s = (Stats) en.nextElement();
	insertIntoTopVector(topTimes, s);
      }
    }
    catch(NoSuchElementException e) {
      System.out.println("\nTotal time = "+totalTime+" ms");
      System.out.println("\nCumul.\tTime\tCalls\tName of method/constructor");
      System.out.println("(as a percentage * 100)");
    }
    double total = (double) totalTime / 10000.0;
    int size = topTimes.size();
    for(int i = 0; i<size; i++) {
      Stats s = (Stats) topTimes.elementAt(i); 
      System.out.println(((int)(s.timeIn / total)) + "\t" +
			 ((int)(s.timeHere / total)) + "\t" +
			 s.count + "\t" + s.method);
      /*
      System.out.println(s.timeIn + "\t" +
			 s.timeOut + "\t" +
			 s.timeHere + "\t" +
			 s.count + "\t" + s.method);
			 */
    }
  }

  static Stats getFromTable(Hashtable table, String name) {
    Stats s; 
    Object o = table.get(name);
    if (o != null) {
      s = (Stats) o;
    }
    else {
      s = new Stats(name);
      if (table.put(name, s) != null) {
	throw new Error();
      }
    }
    return s;
  }

  static int computeCumulativeTime(Stats s, int calls) {
    if (s.timeIn < 0) { // not visited yet
      s.timeIn = 0;

      Enumeration en = s.others.elements();
      int total = 0;
      try {
	while(true) {
	  Call c = (Call) en.nextElement();
	  total += computeCumulativeTime(c.callee, c.count);
	}
      }
      catch(NoSuchElementException e) {}

      s.timeOut = total;
      s.timeIn = total + s.timeHere;
    }
    return (int) (s.timeIn * (calls / (double) s.count));
  }

  static void insertIntoTopVector(Vector v, Stats s) {
    int size = v.size();
    if (size <= 0) {
      v.addElement(s);
      return;
    }
    if (size > 99) {
      Stats last = (Stats) v.elementAt(size-1);
      if (last.timeIn > s.timeIn) {
	return;
      }
    }
    int i = 0;
    int offset = (v.size() >>> 1); // roughly half
    while (offset != 0) {
      Stats elt = (Stats) v.elementAt(i + offset);
      if (elt.timeIn >= s.timeIn) {
	i += offset;
      }
      offset = (offset >>> 1);
    }
    for(; i < size; i++) {
      Stats elt = (Stats) v.elementAt(i);
      if (elt.timeIn < s.timeIn) {
	break;
      }
    }
    v.insertElementAt(s, i);
  }
}

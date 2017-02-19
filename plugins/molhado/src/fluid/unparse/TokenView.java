// TO DO (12/28)

//    1. [DONE] Allow case of two consecutive BPs (mod openclose): 
//         Need only to eliminate the extra blank line in output.
//         Best to do this in viewer, unfortunately.  
//    1a. But also allow for breaks that put out extra lines (eg between 
//         class dfs).
//    2. With NoBP, a level can start on a line that begins at a lower 
//         level.  This means tab stops are needed to record when levels 
//         begin.  


package fluid.unparse;

import fluid.display.TextCoord;
import fluid.display.TextRegion;
import fluid.ir.IRNode;

public class TokenView {
  int width = 10;  // Width of text window -- a parameter.
  static int LINECAPACITY = 10000;  // Size of line/breakpoint array
  static int LEVELCAPACITY = 3000;  // Depth of AST nesting

  // These are indexed by tokarrry pointers
  TokenArray toks;        // The token array (static w.r.t. View processing)
  boolean[] tokVisible;   // Visibility bits for corresponding TokenArray tokens.

  // These are indexed by BP stack pointers
  int[] lineBP;           // [For all]    Tokarray pointer for BP
  Glue[] lineIndent;      // [For broken] Indent basis for this line (w/o indent)
  int[] lineLevel;        // [For all]    Level of this BP
  int[] linePrev;         // [For all]    Line on which indent is based.

  // Two stack pointers
  int nextLineToBreak;    // Next unbroken BP in line array
  int nextLineToPush;     // Next place to push a BP in line array

  int size;               // Current line size
  int currentLevel;       // Current level 
  int lastLevel;          // Level of last broken BP.
  int tokptr;             // Current token in tokarray processing.

  boolean debug;          // Main debugging
  boolean debugbp;        // Debugging BPs

  void debout(String s) {
    System.out.println(s);
  }
	
  public TokenView(int width, TokenArray toks, boolean debug) {
    this.debug = debug;
    debugbp = false;
    if (width!=0) this.width = width;
    this.toks = toks;
    if (debug) debout("Width: " + this.width + ", Toks: " + toks.top);
    tokVisible = new boolean[TokenArray.TOKCAPACITY];
    lineBP = new int[LINECAPACITY];
    lineIndent = new Glue[LINECAPACITY];
    lineLevel = new int[LINECAPACITY];
    //  levelIndent = new Glue[LEVELCAPACITY];
    nextLineToBreak = 1;   // Zero is for the initial trivial line
    nextLineToPush = 1;
    currentLevel = 1;

    lineIndent[nextLineToBreak] = Glue.JUXT;  // Initial trivial line
    lineIndent[nextLineToBreak-1] = Glue.JUXT;  // Initial trivial line
    lineLevel[nextLineToBreak] = 1;
    // levelIndent[currentLevel - 1] = Glue.JUXT;  // Trivial indent
    lineBP[nextLineToBreak] = 0;

  }
  public TokenView(TokenArray toks) {
    this(0, toks, true);
  }

  public void init () {
    for (int i=0; i < toks.top; i++) {
      // Assign tokVisible by looking in AST.
      // But for now:
      tokVisible[i] = true;
    }
    if (debug) debout("Processing...");
    for (tokptr=0; tokptr < toks.top; tokptr++) {
      if (tokVisible[tokptr]) {
	process();
      }
    }
  }

  void process() {
    // Process the token at tokenarray location tokptr: 
    //   -- Tok: no action
    //   -- BP: Push onto line stack
    //   -- OpenClose: Set level of the line
    Token tok = toks.tokens[tokptr];
    if (tok instanceof OpenClose) {
       if (((OpenClose)tok).getOpen()) 
	currentLevel++;
       else currentLevel--;
       // if (debugbp) debout("Level: " + currentLevel);
    }
    else if (tok instanceof Breakpoint) {
      if (debugbp) debout("BP: " + tokptr + " at level " + currentLevel 
			  + " Prio: " + ((Breakpoint)tok).getPrio()
			  + " Indent: " + ((Breakpoint)tok).indentGlue.getLength());
      processBP((Breakpoint)tok);
    }
    else {
      size = size + tok.getLength();
      if (debugbp) debout("Size: " + size + ", Token: " + tok.toString());
      while ((size > width) && (nextLineToBreak < nextLineToPush)) {
	breaknext();
      }
      if ((size > width) && debug) debout("OVERFLOW!");
    }
  }

  void processBP(Breakpoint bp) {
    // TOKPTR is tokenarray location of new BP to process
    if (debugbp) debout("  Top at: " + nextLineToPush + " (" + lineBP[nextLineToPush]
			+ "), Last broken at: " + (nextLineToBreak-1)
			+ " (" + lineBP[nextLineToBreak] + ")");
    // showstack();
    //   1. Increment size
    size = size + bp.getLength(); // Only matters if a token is pushed after
    //   2. No action if a NoBP.
    if (bp instanceof NoBP) return;
    //   3. Pop off superseded BPs.
    while ((nextLineToPush > nextLineToBreak) &&
	   popTheTop()) {
      nextLineToPush--;
      // if (debugbp) debout("   popping");
    }
    //   4. Push the new one onto the reduced stack.
    lineBP[nextLineToPush] = tokptr;
    lineLevel[nextLineToPush] = currentLevel;
    nextLineToPush++;
    if (debugbp) debout("  Pushed at: " + (nextLineToPush-1));

    //   5. Now dispatch on BP kind.
    //  (Indep: Break these only on line overflow, not here.)
    if (bp instanceof MandatoryBP) {
      if (debugbp) debout("  Mandatory");
      while (nextLineToPush > nextLineToBreak) {
	if (debugbp) debout("    Breaking at: " + nextLineToBreak);
	breaknext();
      }
      return;
    }
    if (bp instanceof UnitedBP) {
      //  Break now if a related is already broken.  Look on stack.
      if (debugbp) debout("  United at toks:" + tokptr);
      for (int i=nextLineToBreak-1; i >= 0; i--) {
	// Broken part of stack not ordered so search all the way back.
	if (lineLevel[i] < currentLevel) break; // !! was level
	if ((lineLevel[i] == currentLevel) &&   // !! ditto
	    linkedBP(bp, (Breakpoint)(toks.tokens[lineBP[i]]))) {
	  if (debugbp) debout("  Forced break united.");
	  breaknext(); 
	  break;
	}
      }
    }
  }

  void showstack() { 
    // For debugging only
    int i = 0;
    while (i < nextLineToPush) {
      Breakpoint bp = ((Breakpoint) toks.getToken(lineBP[i]));
      debout("  Index " + i + ((i<nextLineToBreak)?"(broken)":"(above)")
	     + ", Toks " + (lineBP[i])
	     + ", Level " + (lineLevel[i]) + ", Prio " + bp.getPrio() 
	     + ", United " + (bp instanceof UnitedBP));
      i++;
    }
  }

  void breaknext() {
    if(debugbp) {
      // debout("Breaking.  Next to break: " + nextLineToBreak); showstack();
    }
    breakhere();
    resetsize();
    breakmore();
  }

  void breakhere() {
    // Break here (ie at nextLineToBreak)
    if (nextLineToPush == nextLineToBreak) {
      if (debug) debout(" NOTHING TO BREAK.");
      return;
    }
    int thisBPloc = lineBP[nextLineToBreak];
    int thisLevel = lineLevel[nextLineToBreak];
    Breakpoint thisBP = (Breakpoint)(toks.tokens[thisBPloc]);
    int thisPrio = thisBP.getPrio();
    if (debugbp) debout("  Breaking: " + nextLineToBreak + ", this tok: " + thisBPloc);

    // Indent glue: 
    //   lineIndent[here] should have indent for this line, including current BP.
    //      So walk back to the first lineBP of lower level or (equal level and less 
    //        prio) and set our lineIndent to that value plus current BP.
    //      If lastBP is equal, keep going back!
    //      If lastBP is less, use its lineIndent as basis.  
    // NOTE: Need to adapt this to NoBP lines: our basis could be a mid-line position.
    //    NOT YET DONE in code below. Needs token line positions parallel to tokenarray.
    //      (This is an issue only at the start of a LEVEL, since internal NoBPs can
    //       be managed thru BP prios and glue.)  
    //      So: If we search back and the BP we find is at a lower (not same) level
    //          *AND* on that line our production starts, we need to revise the
    //          starting point. 
    //      We do this by seaching the line for two consecutive tokens t1, t2
    //          where level[t1] > level[t2] = our level.
    //      Use the position of the t2 token as our starting point.

    int u = nextLineToBreak-1;  // The BP broken prior to this one
    int uLevel = lineLevel[u];  // Do this so we remember the linelevel.
    while (u >= 0) {
      if (uLevel < thisLevel) break;
      if (uLevel == thisLevel) {
	int uPrio = ((Breakpoint)(toks.tokens[lineBP[u]])).getPrio();
	if (uPrio < thisPrio) break;
      }
      u--;
      uLevel = lineLevel[u];
    }
    Glue g = Glue.addGlue(lineIndent[u], thisBP.indentGlue);

    if (debug) {
      // Test for NoBP offset: Right now this is a no-op.
      if (uLevel < thisLevel) {
	IRNode al1 = toks.tokAsts[thisBPloc];
	IRNode al2 = toks.tokAsts[lineBP[u+1]];
	// Now walk back thru the previous line to find the first token
	//   at this level.  ((Line position of that token)-g) is offset.
	if (al1 != al2) {
	  debout("DIFFERENT" + al1 + "," + al2);
	}
      }
    }

    // This is place to augment the glue.
    lineIndent[nextLineToBreak] = g;
    if(debugbp) debout("   Glue.  This loc: " + nextLineToBreak + ", Basis loc: " + u 
		       + ", basis glue: " + (lineIndent[u].getLength())
		       + ", total glue: " + (g.getLength()));
    nextLineToBreak++;
  }

  void breakmore() {
    // If we broke a unitedBP and the next is a mate, break it too, and so on.
    boolean answer = 
      ((nextLineToPush > nextLineToBreak) &&
       (lineLevel[nextLineToBreak-1] == lineLevel[nextLineToBreak])); 
    if (!answer) return;
    Breakpoint thisBP = (Breakpoint)toks.tokens[lineBP[nextLineToBreak-1]];
    Breakpoint nextBP = (Breakpoint)toks.tokens[lineBP[nextLineToBreak]]; 
    answer = answer && linkedBP(thisBP, nextBP);
    if (answer) {
      if (debugbp) debout("  Breaking a matching united:" + nextLineToBreak);
      breaknext();
    } 
  }

  void resetsize() {
    size = lineIndent[nextLineToBreak-1].getLength();
    if (nextLineToBreak < nextLineToPush) {
      for(int k = lineBP[nextLineToBreak-1]+1; k <= tokptr ; k++) {
	size = size + toks.tokens[k].getLength();
	// Note the inexactness of this predictive calculation
      }
    }
    if (debugbp) debout(" Breaking done.  Next break: " + nextLineToBreak
			+ ", Starting size: " + size);
  }

  boolean linkedBP (Breakpoint bp1, Breakpoint bp2) {
    // Symmetrical relation
    // Breakpoint b1 = (Breakpoint)(toks.tokens[lineBP[bp1]]);
    // Breakpoint b2 = (Breakpoint)(toks.tokens[lineBP[bp2]]);
    // (lineLevel[bp1] == lineLevel[bp2]) &&
    return ((bp1.getPrio() == bp2.getPrio()) &&
	    (bp1 instanceof UnitedBP) &&
	    (bp2 instanceof UnitedBP) &&
	    ((bp1.getName()).equals(bp2.getName())));
  }

  boolean popTheTop () {
    // Should (nextLineToPush-1) (already processed) still be on stack?
    // (When complete, this TOKPTR bp will go on stack.)
    // Lower level means higher up the AST, so higher precedence.
    // Note: bold of *equal* precedence gets removed, so most recent breaks.
    Breakpoint bnew = (Breakpoint) toks.tokens[tokptr];
    Breakpoint bold = (Breakpoint) toks.tokens[lineBP[nextLineToPush-1]];
    int oldLevel = lineLevel[nextLineToPush-1];
    // if (debugbp) debout("  Testing link: "+tokptr+", "+lineBP[nextLineToPush-1]);
    return ((oldLevel > currentLevel) ||
	    ((oldLevel == currentLevel) &&
	     ((bold.getPrio() > bnew.getPrio()) ||
	      ((bold.getPrio() == bnew.getPrio()) && !linkedBP(bold, bnew)) )));
  }

  void drawTV () {
    StringBuffer buf;
    for (int i = 1; i <= nextLineToBreak; i++) {
      buf = new StringBuffer(width);
      if (debugbp) debout("  Line: " + i + ", nextLineToBreak: " + nextLineToBreak
			  + "  From: " + lineBP[i] + " to: " + lineBP[i+1]);
      int first = lineBP[i];
      boolean notfirst = false;
      int last = lineBP[i+1];
      if (first > last) break; // Last line (anomaly)
      Glue ind = lineIndent[i];
      buf.append(ind.toString());
      int gluelen = buf.length();
      //  debout("  From " + first + ", " + last + "  Indent: " + ind.getLength());
      while(first < last) {
	Token tok = toks.tokens[first];
	if (tok instanceof Breakpoint) {
	  if (notfirst) {
	    String gl = ((Breakpoint)tok).horzGlue.toString();
	    buf.append(gl);
	    // debout("    Next(" + first + ") gl: [" + gl + "]");
	  }
	}
	else {
	  if (tok instanceof OpenClose) {
	    //  debout("    Next(" + first + ") openclose.");
	  }
	  else {  // Printable.
	    buf.append(tok.toString());
	    //	    tokChar[first] = buf.length();
	    // if (debugbp) debout("    Next(" + first + ") str: " + tok.toString());
	  }
	}
	notfirst = true;
	first++;
      }
      if (debug) {
	if (buf.length() > gluelen) {
	  debout("LineOut (pos " + i + ", len " + buf.length() + ")" 
		 + (buf.length()<10 ? " ":"") + (i<10 ? " ":"") 
		 + ":" + buf.toString());
	}
      }
    }
  }

  /** Return IRNode displayed at a text coordinate. */
  public IRNode nodeAt(TextCoord tc) {
    return toks.tokAsts[tokenAt(tc)];
  }

  /** Return token pointer of a nonempty token that includes
   * the text coordinate given.  If the text coordinate is
   * before or after all nonempty tokens, then a pointer to
   * the first or last token (respectively) will be returned.
   */
  public int tokenAt(TextCoord tc) {
    int i = tc.getLine();
    int j = tc.getChar();
    // Check that we're within bounds:
    if (i < 1 || i >= nextLineToBreak)
      throw new IllegalArgumentException("text coordinate illegal");
    int first = lineBP[i];
    boolean notfirst = false;
    int last = lineBP[i+1];
    Glue ind = lineIndent[i];
    int sofar = ind.getLength();
    // debout("Looking for token at column " + j);
    while (first < last && sofar <= j) {
      Token tok = toks.tokens[first];
      // debout("At " + sofar + ": " + tok + " " +
      //        fluid.parse.JJNode.tree.getOperator(toks.tokAsts[first]));
      if (tok instanceof Breakpoint) {
	if (notfirst) {
	  sofar += ((Breakpoint)tok).horzGlue.getLength();
	}
      } else {
	sofar += tok.getLength();
      }
      if (sofar > j) return first; // normal exit
      notfirst = true;
      first++;
    }
    return first;
  }

  protected TextCoord tokenCoord(int tokenIndex) {
    //! stupid linear search to find line.
    int i=1;
    while (lineBP[i] < lineBP[i+1] && lineBP[i+1] < tokenIndex) ++i;
    // now search within line
    int sofar = lineIndent[i].getLength();
    boolean notfirst = false;
    int index = lineBP[i];
    while (index < tokenIndex) {
      Token tok = toks.tokens[index];
      if (tok instanceof Breakpoint) {
	if (notfirst) {
	  sofar += ((Breakpoint)tok).horzGlue.getLength();
	}
      } else {
	sofar += tok.getLength();
      }
      notfirst = true;
      index++;
    }
    return new TextCoord(i,sofar);
  }

  /** Find the text coordinates for the start of a node. */
  public TextCoord nodeStart(IRNode node) {
    int index = toks.getNodeOpen(node);
    return tokenCoord(index);
  }

  /** Find the text coordinates for the end of a node. */
  public TextCoord nodeEnd(IRNode node) {
    int index = toks.getNodeClose(node);
    return tokenCoord(index);
  }


  /** Return the region for the node. */
  public TextRegion getNodeRegion(IRNode node, String[] text) {
    int start = toks.getNodeOpen(node);
    int end = toks.getNodeClose(node);
    return getTokenRegion(start,end,text);
  }
  /** Return the region between two tokens (inclusive) */
  public TextRegion getTokenRegion(int start, int end, String[] text) {
    TextCoord startCoord = tokenCoord(start);
    TextCoord endCoord = tokenCoord(end);

    // now find region of text of interest.
    //! stupid simple linear search:
    int startline;
    for (startline=1; lineBP[startline+1] < start; ++startline)
      ;
    int endline;
    for (endline = startline; lineBP[endline+1] < end; ++endline)
      ;

    // determine min and max (at least start and end)
    int max = endCoord.getChar();
    int min = startCoord.getChar();
    for (int i = startline+1; i <= endline; ++i) {
      int indent = lineIndent[i].getLength();
      if (min > indent) min = indent;
    }
    for (int i = startline; i < endline; ++i) {
      int extent = text[i].length();
      if (max < extent) max = extent;
    }

    return new TextRegion(startCoord,endCoord,min,max);
  }


  // Note first and last lines are blank (for breakpoints)
  // This needs repair.

  public String[] strTV () {
    String[] bufs = new String[nextLineToBreak];
    StringBuffer buf = new StringBuffer(width);
    for (int i = 0; i < nextLineToBreak; i++) {
      buf.setLength(0);
      int first = lineBP[i];
      boolean notfirst = false;
      int last = lineBP[i+1];
      //      if (first > last) {
      //	bufs[i] = buf.toString();
      //	break; // Last line (anomaly -- FIX THIS)
      //      }
      Glue ind = lineIndent[i];
      buf.append(ind.toString());
      int gluelen = buf.length();
      while(first < last) {
	Token tok = toks.tokens[first];
	if (tok instanceof Breakpoint) {
	  if (notfirst) {
	    String gl = ((Breakpoint)tok).horzGlue.toString();
	    buf.append(gl);
	  }
	}
	else {
	  if (!(tok instanceof OpenClose)) {
	    buf.append(tok.toString());
	  }
	}
	notfirst = true;
	first++;
      }
      bufs[i] = buf.toString();
    }
    return bufs;
  }
}

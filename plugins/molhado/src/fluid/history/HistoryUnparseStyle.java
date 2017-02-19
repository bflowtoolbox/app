// $Header: /usr/local/refactoring/molhadoRef/src/fluid/history/HistoryUnparseStyle.java,v 1.1 2006/03/21 23:20:55 dig Exp $
package fluid.history;

import fluid.unparse.Breakpoint;
import fluid.unparse.Glue;
import fluid.unparse.IndepBP;
import fluid.unparse.MandatoryBP;
import fluid.unparse.NoToken;
import fluid.unparse.Token;
import fluid.unparse.UnitedBP;

/** This class defines the interface for History unparse styles,
 * and also provides a default implementation.  To add a new style, 
 * define four things: <pre>
 *   private static Token defaultNAME = ...;
 *   private Token TKNAME = defaultNAME;
 *   public Token getNAME() { return TKNAME; }
 *   public void setNAME(Token tk) { TKNAME = tk; }
 * </pre>
 * where <tt>NAME</tt> is the uppercase of the format in angle brackets
 * in the operator file.  If the style is </name>, use ENDNAME.
 */
public class HistoryUnparseStyle {
  /* define an access routine for each kind of named breakpoint or token */

  /* <block> */
  private static Token defaultBLOCK =
    new UnitedBP(1, "", Glue.UNIT, Glue.INDENT);
  private Token TKBLOCK = defaultBLOCK;
  public Token getBLOCK() {
    return TKBLOCK;
  }
  public void setBLOCK(Token tk) {
    TKBLOCK = tk;
  }

  /* </block> */
  private static Token defaultENDBLOCK =
    new UnitedBP(1, "", Glue.UNIT, Glue.JUXT);
  private Token TKENDBLOCK = defaultENDBLOCK;
  public Token getENDBLOCK() {
    return TKENDBLOCK;
  }
  public void setENDBLOCK(Token tk) {
    TKENDBLOCK = tk;
  }

  /* <li> */
  private static Breakpoint defaultLI =
    new UnitedBP(2, "", Glue.UNIT, Glue.JUXT);
  private Breakpoint BPLI = defaultLI;
  public Breakpoint getLI() {
    return BPLI;
  }
  public void setLI(Breakpoint bp) {
    BPLI = bp;
  }

  /* <paren> */
  private static Token defaultPAREN = IndepBP.JUXTBP;
  private Token TKPAREN = defaultPAREN;
  public Token getPAREN() {
    return TKPAREN;
  }
  public void setPAREN(Token tk) {
    TKPAREN = tk;
  }

  /* </paren> */
  private static Token defaultENDPAREN = IndepBP.JUXTBP;
  private Token TKENDPAREN = defaultENDPAREN;
  public Token getENDPAREN() {
    return TKENDPAREN;
  }
  public void setENDPAREN(Token tk) {
    TKENDPAREN = tk;
  }

  /* <comma> */
  private static Token defaultCOMMA = IndepBP.DEFAULTBP;
  private Token TKCOMMA = defaultCOMMA;
  public Token getCOMMA() {
    return TKCOMMA;
  }
  public void setCOMMA(Token tk) {
    TKCOMMA = tk;
  }

  /* <then> */
  private static Token defaultTHEN = defaultBLOCK;
  private Token TKTHEN = defaultTHEN;
  public Token getTHEN() {
    return TKTHEN;
  }
  public void setTHEN(Token tk) {
    TKTHEN = tk;
  }

  /* </then> */
  private static Token defaultENDTHEN = defaultENDBLOCK;
  private Token TKENDTHEN = defaultENDTHEN;
  public Token getENDTHEN() {
    return TKENDTHEN;
  }
  public void setENDTHEN(Token tk) {
    TKENDTHEN = tk;
  }

  /* <else> */
  private static Token defaultELSE = defaultBLOCK;
  private Token TKELSE = defaultELSE;
  public Token getELSE() {
    return TKELSE;
  }
  public void setELSE(Token tk) {
    TKELSE = tk;
  }

  /* </else> */
  private static Token defaultENDELSE = defaultENDBLOCK;
  private Token TKENDELSE = defaultENDELSE;
  public Token getENDELSE() {
    return TKENDELSE;
  }
  public void setENDELSE(Token tk) {
    TKENDELSE = tk;
  }

  /* <while> */
  private static Token defaultWHILE = NoToken.prototype;
  private Token TKWHILE = defaultWHILE;
  public Token getWHILE() {
    return TKWHILE;
  }
  public void setWHILE(Token tk) {
    TKWHILE = tk;
  }

  /* </while> */
  private static Token defaultENDWHILE = NoToken.prototype;
  private Token TKENDWHILE = defaultENDWHILE;
  public Token getENDWHILE() {
    return TKENDWHILE;
  }
  public void setENDWHILE(Token tk) {
    TKENDWHILE = tk;
  }

  /* <catch> */
  private static Token defaultCATCH = NoToken.prototype;
  private Token TKCATCH = defaultCATCH;
  public Token getCATCH() {
    return TKCATCH;
  }
  public void setCATCH(Token tk) {
    TKCATCH = tk;
  }

  /* </catch> */
  private static Token defaultENDCATCH = NoToken.prototype;
  private Token TKENDCATCH = defaultENDCATCH;
  public Token getENDCATCH() {
    return TKENDCATCH;
  }
  public void setENDCATCH(Token tk) {
    TKENDCATCH = tk;
  }

  /* <>  or <none> */
  private static Breakpoint defaultNONE = IndepBP.JUXTBP;
  private Breakpoint BPNONE = defaultNONE;
  public Breakpoint getNONE() {
    return BPNONE;
  }
  public void setNONE(Breakpoint bp) {
    BPNONE = bp;
  }

  /* <br> */
  private static Breakpoint defaultBR = MandatoryBP.BREAKBP;
  private Breakpoint BPBR = defaultBR;
  public Breakpoint getBR() {
    return BPBR;
  }
  public void setBR(Breakpoint bp) {
    BPBR = bp;
  }

  public static final HistoryUnparseStyle prototype = new HistoryUnparseStyle();
}

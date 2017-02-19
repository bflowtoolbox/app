/* $Header: /usr/local/refactoring/molhadoRef/src/sc/xml/IRDTD.java,v 1.1 2006/03/21 23:21:00 dig Exp $ */

package sc.xml;

import fluid.ir.IRNode;
import fluid.tree.Operator;

/** An internal representation of an XML DTD, that is a
 * Document Type Definition.  It uses Operators to enforce
 * type constraints.
 */
public abstract class IRDTD {

  protected String name;
	
  // Constructors

  public IRDTD(String n) { name = n;}

  /** get DTD name
   *
   * @return name of DTD
   */
  public String getName() { return name; }

  /** find an Operator in DTD with given name
   *
   * Determine {@link fluid.tree.Operator} for the given XML tag.
   * Returns null if this tag is not legal for this DTD.
   *
   * @param  name name of the {@link fluid.tree.Operator}
   * @return {@link fluid.tree.Operator} with given name, null if not found
   */
  public abstract Operator findOperator(String name);

  /** get the {@link fluid.tree.Operator} with which a given IRNode is
   * associated 
   *
   * @param  ir IRNode
   * @return {@link fluid.tree.Operator} to which IRNode is associated
   */
  public abstract Operator getOperator(IRNode ir);

  /** check whether given {@link fluid.tree.Operator} belongs to this DTD
   *
   * @param  o {@link fluid.tree.Operator}
   * @return true if o is a valid {@link fluid.tree.Operator} for this DTD,
   *         false otherwise
   */
  public abstract boolean  isOperatorOK(Operator o);

  /** check whether given {@link fluid.tree.Operator} belongs to this DTD
   * and are meant for non-terminals.
   *
   * @param  o {@link fluid.tree.Operator}
   * @return true if o is a valid {@link fluid.tree.Operator} for this DTD
   *         and meant for non-terminals, false otherwise
   */
  public abstract boolean  isIntermediateOperator(Operator o);

  /** check whether given {@link fluid.tree.Operator} belongs to this DTD
   * and are meant for terminals.
   *
   * @param  o {@link fluid.tree.Operator}
   * @return true if o is a valid {@link fluid.tree.Operator} for this DTD
   *         and meant for raw data, false otherwise
   */
  public abstract boolean  isRawOperator(Operator o);

  /** get the {@link fluid.tree.Operator} for Raw Text in XML document.
   *
   * @return {@link fluid.tree.Operator} for Raw Text
   */
  public abstract Operator getRawTextOperator();

  /** get the attribute name which has the raw text in a RawText node in XML
   *
   * To get raw text from a node, the node needs to be asked for value of
   * the attribute returned by this method.
   *
   * @return name of the attribute
   */
  public abstract String   getRawTextOperatorAttrName();
}

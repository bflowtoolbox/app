/* $Header: /usr/local/refactoring/molhadoRef/src/sc/xml/NullDTD.java,v 1.1 2006/03/21 23:21:00 dig Exp $ */

package sc.xml;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import sc.document.DocTreeBundle;
import sc.document.XmlAttrBundle;
import fluid.ir.Bundle;
import fluid.ir.IRInput;
import fluid.ir.IRNode;
import fluid.ir.IROutput;
import fluid.ir.PlainIRNode;
import fluid.ir.SlotInfo;
import fluid.tree.Operator;
import fluid.tree.SyntaxTree;
import fluid.tree.SyntaxTreeInterface;

/** A "DTD" that accepts anything whatsoever (structurally).
 *
 */
public class NullDTD extends IRDTD {

  public static final NullDTD prototype = new NullDTD();
  
  public static final SyntaxTree tree = DocTreeBundle.tree;
  
  private static Bundle  xmlAttrBundle = XmlAttrBundle.getBundle();
  
  protected static final NullOperator baseOperator = new NullOperator("*base*");
  protected final Hashtable operators = new Hashtable();

  private NullDTD() {
    super("NullDTD");
    addRawTextOperator();
  }

	private void addRawTextOperator() {
		operators.put(getRawTextOperator().name(), getRawTextOperator());
	}
	
  public Operator getRawTextOperator() {
    return RawTextOperator.prototype;
  }

  public String getRawTextOperatorAttrName() {
    return RawTextOperator.rawTextAttrName;
  }

  public Operator findOperator(String n) {
    String name = n.toLowerCase();
    Operator op = (Operator)operators.get(name);
    if (op == null) {
      op = new NullOperator(name);
      operators.put(name,op);
    }
    return op;
  }

  public Operator getOperator(IRNode ir) {
    Operator o = null;

    if (ir != null) {
      o = tree.getOperator(ir);
      if (!isOperatorOK(o))
        o = null;
    }

    return o;
  }

  public boolean  isOperatorOK(Operator o) {
    return (o!=null && (o instanceof NullDTDOperator));
  }

  public boolean  isIntermediateOperator(Operator o) {
    return (isOperatorOK(o) && !getRawTextOperator().equals(o));
  }

  public boolean  isRawOperator(Operator o) {
    return (isOperatorOK(o) && getRawTextOperator().equals(o));
  }


  /** Abstract Operator class for Null DTD Operators.
   * All Null DTD Operators should directly or indirectly inherit from
   * this class.
   */
  private static abstract class NullDTDOperator extends Operator {
    protected static final Set attrs = new HashSet();

    public NullDTDOperator() { }

    public SyntaxTreeInterface tree() { return tree; }

    
    public IRNode createNode(SyntaxTreeInterface t) {
      IRNode nd = new PlainIRNode();
      t.initNode(nd, this);
      return nd;
    }

    public IRNode createNode() {
      return createNode(tree);
    }
    

    public Operator getParent() {
      if (this == baseOperator) return null; else return baseOperator;
    }
  }; /* End of NullDTDOperator class */


  /** Operator for Raw Text in XML.
   */
  private static class RawTextOperator extends NullDTDOperator {
    public static final RawTextOperator prototype = new RawTextOperator();
    protected static final String rawTextAttrName = "info";
    protected static SlotInfo rawTextAttr;

		/* RawTextAttr was already loaded in XmlAttrBundle.getBundle()
    static {
	      // Create Slot for rawTextAttrName, and add it to list of attributes.
	      try {
	        	rawTextAttr = VersionedSlotFactory.prototype.makeSlotInfo(
	                                rawTextAttrName, IRStringType.prototype);
	        	attrs.add(rawTextAttr); // Add to all Null DTD Operator's attrs.
	        // Save to bundle in IRDTD
	        	bundle.saveAttribute(rawTextAttr);
	      } catch(SlotAlreadyRegisteredException e) {
	        throw new FluidError("'" + rawTextAttrName
	                             + "' slot already registered.");
	      }
    }
		*/
				
    /* No public/protected constructor. Singleton object.
     * This will be called maximum once during execution of the program.
     */
    private RawTextOperator() {
    	if (XmlAttrBundle.rawTextAttrSlotInfo != null)
    		rawTextAttr = XmlAttrBundle.rawTextAttrSlotInfo;
      else {
        System.out.println("The file docattr has not been loaded !");
        System.exit(1);
      }
    }

    // Name of the Operator
    public String name() { return "RawTextOperator"; }

    // All Raw Text Attributes
    public Set getAttributes() {
      Set tmp = new HashSet();
      tmp.add(rawTextAttr);
      return tmp;
    }

    // Get Raw Text Attribute
    public SlotInfo getAttribute(String name) {
      if (name.equalsIgnoreCase(rawTextAttrName))
        return rawTextAttr;
      else
        return null;
    }
    

    public boolean isComplete(IRNode node) {
      return node.valueExists(getAttribute(rawTextAttrName));
    }
    
    public void writeInstance(IROutput out)
  	{
  	}
  	
  	public Operator readInstance(IRInput in)
  	{
  		System.out.println("readInstance of RawTextOperator");
    	return this;
  	}
  }; /* End of RawTextOperator class */


  /** Operator for any node other than Raw Text in XML.
   */
  private static class NullOperator extends NullDTDOperator {
    private String opname;

    // Constructor
    public NullOperator(String n) { opname = n; }

    // Name
    public String name() { return opname; }

    // All Attributes
    public Set getAttributes() { return attrs; }

		/*: DO NOT CREATE SLOTINFO on demand ANYMORE
    public SlotInfo getAttribute(String name) {
      SlotInfo attr = super.getAttribute(name);
      if (attr == null) {
        // Since there is no such slot, create one
        try {
          attr = VersionedSlotFactory.prototype.makeSlotInfo(name,
                     IRStringType.prototype);
          attrs.add(attr); // Add to all Null DTD Operator's attrs.	
        } catch(SlotAlreadyRegisteredException e) {
          throw new FluidError("'" + name
                               + "' slot already registered. potential bug."
					                               + e);
        } finally {
        }
      }
      return attr;
    }
		*/
		
		
		public SlotInfo getAttribute(String name) {
      SlotInfo attr = super.getAttribute(name);
      if (attr == null) {
        for (int i = 0; i < xmlAttrBundle.getNumAttributes(); i++)
        {
        	SlotInfo temp = xmlAttrBundle.getAttribute(i+1);
        	attrs.add(temp);
        	if (temp.name().equals(name)) attr = temp;
        }
      }
      return attr;
    }
		
				
    public boolean isComplete(IRNode node) { return true; }

    public int numChildren() { return -1; }
    public Operator childOperator(int i) { return baseOperator; }
    public Operator variableOperator() { return baseOperator; }

    public boolean includes(Operator other) {
      return ((other instanceof NullOperator)
              || (other instanceof RawTextOperator));
    }
    
    public void writeInstance(IROutput out)
  	{
  		try {
  			out.writeUTF(opname);
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  	}
  	
  	public Operator readInstance(IRInput in)
  	{
  		String name="";
  		try {
    		name = in.readUTF();
    		System.out.println("readInstance of NullOperator " + name);
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  		
  		NullOperator op = new NullOperator(name);
  		Enumeration attrlist = xmlAttrBundle.attributes();
			while (attrlist.hasMoreElements()) {
				SlotInfo slotinfo = (SlotInfo) attrlist.nextElement();
				op.attrs.add(slotinfo);			
			}
  		return op;
  	}
  }; /* End of NullOperator class */
  
}
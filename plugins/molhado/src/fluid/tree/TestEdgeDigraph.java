/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/tree/TestEdgeDigraph.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.tree;

import fluid.ir.ConstantSlotFactory;
import fluid.ir.IRNode;
import fluid.ir.IRSequenceException;
import fluid.ir.PlainIRNode;
import fluid.ir.SimpleSlotFactory;
import fluid.ir.SlotAlreadyRegisteredException;
import fluid.ir.SlotFactory;
import fluid.ir.SlotImmutableException;
import fluid.ir.SlotUndefinedException;

class TestEdgeDigraph {
  public static void main(String args[]) {
    new TestEdgeDigraph().test(args);
  }
  EdgeDigraph createStored(SlotFactory sf) {
    try {
      return new EdgeDigraph(null,sf);
    } catch (SlotAlreadyRegisteredException ex) {
      System.out.println("panic: " + ex);
      ex.printStackTrace();
      System.exit(1);
      return null;
    }
  }
  EdgeDigraph createCopy(EdgeDigraph dig) {
    return new EdgeDigraph(dig.getAttribute(EdgeDigraph.CHILD_EDGES),
			   dig.getAttribute(EdgeDigraph.SINKS),
			   dig.getAttribute(EdgeDigraph.IS_EDGE));
  }

  protected boolean verbose = false;

  protected class AsDigraph extends TestDigraph {
    EdgeDigraph ed;
    Digraph createStored(SlotFactory sf) {
      ed = TestEdgeDigraph.this.createStored(sf);
      return ed.getAsDigraph();
    }
    Digraph createCopy(Digraph d) {
      return TestEdgeDigraph.this.createCopy(ed).getAsDigraph();
    }
  }

  public void test(String[] args) {
    new AsDigraph().test(args);
    verbose = args.length != 0;
    EdgeDigraph mutable = createStored(SimpleSlotFactory.prototype);
    EdgeDigraph mutable_copy = createCopy(mutable);
    EdgeDigraph immutable = createStored(ConstantSlotFactory.prototype);
    EdgeDigraph immutable_copy = createCopy(immutable);
    test("mutableED",mutable,mutable_copy,true,getDagOK());
    test("immutableED",immutable,immutable_copy,false,getDagOK());
  }

  protected boolean getDagOK() { return true; }

  public class SelfDocumentingIRNode extends PlainIRNode {
    String name;
    public SelfDocumentingIRNode(String n) {
      super();
      name = n;
    }
    public String toString() { return name + ":IRNode"; }
  }

  protected void test(String name, EdgeDigraph dig, EdgeDigraph copy,
		      boolean mutable, boolean dagOK) {
    IRNode root = new SelfDocumentingIRNode("root");
    IRNode c0 = new SelfDocumentingIRNode("c0");
    IRNode c1 = new SelfDocumentingIRNode("c1");
    IRNode c2 = new SelfDocumentingIRNode("c2");
    IRNode e1 = new SelfDocumentingIRNode("e1");
    IRNode e2 = new SelfDocumentingIRNode("e2");
    IRNode x = new SelfDocumentingIRNode("x");

    test_init_node(name+".initNode(root,3)",dig,root,3,true);
    test_init_node(name+".initNode(c0,0)",dig,c0,0,true);
    test_init_node(name+".initNode(c1,~0)",dig,c1,~0,true);
    test_init_node(name+"_copy.initNode(c2,~1)",copy,c2,~1,true);
    test_init_node(name+"_copy.initNode(root,3)",copy,root,3,mutable);

    test_init_edge(name+".initEdge(e1)",dig,e1,true);
    test_init_edge(name+"_copy.initEdge(e2)",copy,e2,true);
    test_init_edge(name+"_copy.initEdge(c0)",copy,c0,mutable);
    test_init_node(name+".initNode(c0,0)",dig,c0,0,mutable);
    test_init_node(name+".initNode(e2)",dig,e2,1,mutable);
    test_init_edge(name+"_copy.initEdge(e2)",copy,e2,mutable);
    
    test_num_children(name+"_copy.numChildren(root)",copy,root,3,true,true);
    test_num_children(name+".numChildren(c0)",dig,c0,0,true,true);
    test_num_children(name+"_copy.numChildren(c0)",copy,c0,0,true,true);
    test_num_children(name+".numChildren(c1)",dig,c1,0,true,true);
    test_num_children(name+"_copy.numChildren(c1)",copy,c1,0,true,true);
    test_num_children(name+".numChildren(c2)",dig,c2,1,true,true);
    test_num_children(name+".numChildren(e1)",dig,e1,1,true,false);
    test_num_children(name+"_copy.numChildren(e1)",copy,e1,1,true,false);
    test_num_children(name+".numChildren(e2)",dig,e2,1,true,false);
    test_num_children(name+"_copy.numChildren(e2)",copy,e2,1,true,false);
    test_num_children(name+".numChildren(x)",dig,x,0,false,false);

    test_set_child_edge(name+".setChildEdge(root,0,e1)",
			dig,root,0,e1,true,true,true,true);
    test_set_child_edge(name+"_copy.setChildEdge(root,2,e2)",
			copy,root,2,e2,true,true,true,true);
    test_get_child_edge(name+".getChildEdge(root,0)",
			dig,root,0,e1,true,true,true);
    /*!!
     * For backward copied SEDs, null is left behind.
     * We cannot avoid it without more thread globals.
     * Ick!
     *
     * test_get_child_edge(name+"_copy.getChildEdge(root,1)",
     *			copy,root,1,null,false,true,true);
     * test_get_child_edge(name+".getChildEdge(root,1)",
     *			dig,root,1,null,false,true,true);
     */
    test_get_child_edge(name+"_copy.getChildEdge(root,2)",
			copy,root,2,e2,true,true,true);
    test_set_sink(name+"_copy.setSink(e1,c1)",
		  copy,e1,c1,true,true,true);
    test_get_child_edge(name+".getChildEdge(e1,0)",
			dig,e1,0,null,true,true,false);
    test_get_sink(name+".getSink(e1)",dig,e1,c1,true,true);
    test_get_sink(name+"_copy.getSink(e2)",copy,e2,null,false,true);
    test_get_sink(name+"_copy.getSink(root)",copy,root,e1,true,false);
    test_get_sink(name+".getSink(root)",copy,root,e1,true,false);
    test_set_child_edge(name+".setChildEdge(root,1,c1)",
			dig,root,1,c1,true,true,true,false);
    test_set_sink(name+".setSink(root,c1)",dig,root,c1,mutable,true,false);
		  

    test_get_child_edge(name+".getChild(root,3)",
			dig,root,3,null,true,false,true);
    test_set_child_edge(name+"_copy.setChild(root,1,null)",
		   copy,root,1,null,true,true,true,true);    
    test_set_sink(name+".setSink(e2,c2)",dig,e2,c2,true,true,true);

    // now try mutations:
    test_replace_child_edge(name+"_copy.replaceChildEdge(root,e2,null)",
			    copy,root,e2,null,
			    mutable,true,true);
    test_replace_child_edge(name+".replaceChildEdge(root,e1,e2)",
			    dig,root,e1,e2,
			    mutable,true,true);
    test_replace_child_edge(name+".replaceChildEdge(root,null,e1)",
			    dig,root,null,e1,
			    mutable,true,true);

    test_set_sink(name+".setSink(e2,c1)",dig,e2,c1,mutable,dagOK,true);

  }

  public static void reportError(String test, String message) {
    System.out.println("!!!" + test + message);
  }

  public void test_init_node(String test,
			     EdgeDigraph dig, IRNode node, int numChildren,
			     boolean mutationOK)
  {
    try {
      dig.initNode(node,numChildren);
      if (!mutationOK)
	reportError(test, " should not have succeeded");
    } catch (SlotImmutableException ex) {
      if (mutationOK)
	reportError(test, " should have succeeded");
    } catch (Throwable ex) {
      reportError(test, " should not have failed: " + ex);
      ex.printStackTrace();
      System.exit(1);
    }
  }

  public void test_init_edge(String test,
			     EdgeDigraph dig, IRNode edge,
			     boolean mutationOK)
  {
    try {
      dig.initEdge(edge);
      if (!mutationOK)
	reportError(test, " should not have succeeded");
    } catch (SlotImmutableException ex) {
      if (mutationOK)
	reportError(test, " should have succeeded");
    } catch (Throwable ex) {
      reportError(test, " should not have failed: " + ex);
      ex.printStackTrace();
      System.exit(1);
    }
  }

  public void test_num_children(String test,
				EdgeDigraph dig, IRNode node, int num, 
				boolean defined, boolean isEdgeOK)
  {
    try {
      int count = dig.numChildren(node);
      if (!defined)
	reportError(test, " should not have returned.");
      if (!isEdgeOK)
	reportError(test, " should have caused EdgeDigraph exception");
      if (num != count)
	reportError(test, " returned wrong result");
    } catch (SlotUndefinedException ex) {
      if (defined)
	reportError(test, " should have returned");
    } catch (EdgeDigraphException ex) {
      if (isEdgeOK) {
	reportError(test, " should not have had node/edge problems");
	if (verbose) ex.printStackTrace();
      }
    } catch (Throwable ex) {
      reportError(test, " should not have failed: " + ex);
      ex.printStackTrace();
    }
  }

  public void test_set_child_edge(String test,EdgeDigraph dig,
				  IRNode node, int i, IRNode newChild,
				  boolean mutationOK,
				  boolean locationOK,
				  boolean childOK,
				  boolean isEdgeOK)
  {
    try {
      dig.setChildEdge(node,i,newChild);
      if (!mutationOK)
	reportError(test, " should have rejected mutation.");
      if (!locationOK)
	reportError(test, " should have rejected location");
      if (!childOK)
	reportError(test, " should have rejected child");
      if (!isEdgeOK)
	reportError(test, " should have caused EdgeDigraph exception");
    } catch (SlotImmutableException ex) {
      if (mutationOK) {
	reportError(test, " should not have rejected mutation");
	if (verbose) ex.printStackTrace();
      }
    } catch (IRSequenceException ex) {
      if (locationOK) {
	reportError(test, " should not have rejected location");
	if (verbose) ex.printStackTrace();
      }
    } catch (EdgeDigraphException ex) {
      if (isEdgeOK) {
	reportError(test, " should not have had node/edge problems");
	if (verbose) ex.printStackTrace();
      }
    } catch (StructureException ex) {
      if (childOK) {
	reportError(test, " should not have rejected child");
	if (verbose) ex.printStackTrace();
      }
    } catch (Throwable ex) {
      reportError(test, " should not have failed: " + ex);
      ex.printStackTrace();
      System.exit(1);
    }
  }

  public void test_get_child_edge(String test, EdgeDigraph dig,
				  IRNode node, int i, IRNode result,
				  boolean defined,
				  boolean locationOK,
				  boolean isEdgeOK)
  {
    try {
      IRNode value = dig.getChildEdge(node,i);
      if (!defined)
	reportError(test, " should not have returned. (value was " +
		    value +")");
      if (!locationOK)
	reportError(test, " should have rejected location");
      if (!isEdgeOK)
	reportError(test, " should have had node/edge problems");
      if (value != result)
	if (value == null || !value.equals(result))
	  reportError(test, " returned wrong result");
    } catch (SlotUndefinedException ex) {
      if (defined)
	reportError(test, " should have returned");
    } catch (IRSequenceException ex) {
      if (locationOK) {
	reportError(test, " should not have rejected location");
	//if (verbose) 
	ex.printStackTrace();
      }
    } catch (EdgeDigraphException ex) {
      if (isEdgeOK) {
	reportError(test, " should not have had node/edge problems");
	if (verbose) ex.printStackTrace();
      }
    } catch (Throwable ex) {
      reportError(test, " should not have failed: " + ex);
      ex.printStackTrace();
    }
  }

  public void test_set_sink(String test, EdgeDigraph dig,
			    IRNode edge, IRNode sink,
			    boolean mutationOK,
			    boolean childOK,
			    boolean isEdgeOK)
  {
    try {
      dig.setSink(edge,sink);
      if (!mutationOK)
	reportError(test, " should have rejected mutation.");
      if (!childOK)
	reportError(test, " should have rejected child");
      if (!isEdgeOK)
	reportError(test, " should have caused EdgeDigraph exception");
    } catch (SlotImmutableException ex) {
      if (mutationOK) {
	reportError(test, " should not have rejected mutation");
	if (verbose) ex.printStackTrace();
      }
    } catch (EdgeDigraphException ex) {
      if (isEdgeOK) {
	reportError(test, " should not have had node/edge problems");
	if (verbose) ex.printStackTrace();
      }
    } catch (StructureException ex) {
      if (childOK) {
	reportError(test, " should not have rejected child");
	if (verbose) ex.printStackTrace();
      }
    } catch (Throwable ex) {
      reportError(test, " should not have failed: " + ex);
      ex.printStackTrace();
      System.exit(1);
    }
  }

  public void test_get_sink(String test, EdgeDigraph dig,
			    IRNode edge, IRNode result,
			    boolean defined,
			    boolean isEdgeOK)
  {
    try {
      IRNode value = dig.getSink(edge);
      if (!defined)
	reportError(test, " should not have returned.");
      if (!isEdgeOK)
	reportError(test, " should have had node/edge problems");
      if (value != result)
	if (value == null || !value.equals(result))
	  reportError(test, " returned wrong result");
    } catch (SlotUndefinedException ex) {
      if (defined)
	reportError(test, " should have returned");
    } catch (EdgeDigraphException ex) {
      if (isEdgeOK) {
	reportError(test, " should not have had node/edge problems");
	if (verbose) ex.printStackTrace();
      }
    } catch (Throwable ex) {
      reportError(test, " should not have failed: " + ex);
      ex.printStackTrace();
    }
  }

  public void test_replace_child_edge(String test,
				      EdgeDigraph dig, IRNode node,
				      IRNode oldChildEdge, IRNode newChildEdge,
				      boolean mutationOK,
				      boolean childOK,
				      boolean isEdgeOK)
  {
    try {
      dig.replaceChildEdge(node,oldChildEdge,newChildEdge);
      if (!mutationOK)
	reportError(test, " should have rejected mutation.");
      if (!childOK)
	reportError(test, " should have rejected child");
      if (!isEdgeOK)
	reportError(test, " should have had node/edge problems");
    } catch (SlotImmutableException ex) {
      if (mutationOK) {
	reportError(test, " should not have rejected mutation");
	if (verbose) ex.printStackTrace();
      }
    } catch (EdgeDigraphException ex) {
      if (isEdgeOK) {
	reportError(test, " should not have had node/edge problems");
	if (verbose) ex.printStackTrace();
      }
    } catch (StructureException ex) {
      if (childOK) {
	reportError(test, " should not have rejected child");
	if (verbose) ex.printStackTrace();
      }
    } catch (Throwable ex) {
      reportError(test, " should not have failed: " + ex);
      ex.printStackTrace();
      System.exit(1);
    }
  }
}

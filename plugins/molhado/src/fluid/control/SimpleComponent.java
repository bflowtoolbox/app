package fluid.control;

import fluid.ir.IRLocation;
import fluid.ir.IRNode;

/** A component in which all children are executed in sequence.
 * This class only handles fixed numbers of children.
 * @see SequenceComponent
 */
public class SimpleComponent extends Component {

  public SimpleComponent(IRNode node) {
    super(node);
    init(node,allIndices(node));
  }

  /** Create a component which executed all children in
   * order and then performs some language-specific action.
   */
  public SimpleComponent(IRNode node, ComponentFlow cf) {
    super(node);
    init(node,allIndices(node),new ComponentFlow(this,cf.getInfo()));
  }

  // useless as it stands: choice nodes cannot directly connect
  // to abrupt exit ports.
  private SimpleComponent(IRNode node, ComponentChoice cc) {
    super(node);
    init(node,allIndices(node),new ComponentChoice(this,cc.getInfo()));
  }

  private int[] allIndices(IRNode node) {
    int n = tree.numChildren(node);
    int[] indices = new int[n];
    for (int i=0; i < n; ++i) indices[i] = i;
    return indices;
  }

  /** Execute some children as specified in the indices array. */
  public SimpleComponent(IRNode node, int[] indices) {
    super(node);
    init(node,indices);
  }

  /** Execute some children as specified in the indices array
   * and then perform some language-specific action.
   */
  public SimpleComponent(IRNode node, int[] indices, ComponentFlow cf) {
    super(node);
    init(node,indices,cf);
  }

  private void init(IRNode node, int[] indices, ComponentFlow cf) {
    int n = indices.length;
    // allocate exit ports:
    Port p2;
    if (n == 0) {
      // no children, i.e. no possibility for abrupt exit:
      p2 = new ComponentBlankAbruptExitPort(this);
    } else {
      p2 = new ComponentAbruptExitPort(this);
    }
    Port p3 = new ComponentNormalExitPort(this);
    ControlEdge.connect(cf,p3);
    init(node,indices,p2,cf);
  }

  private void init(IRNode node, int[] indices, ComponentChoice cc) {
    int n = indices.length;
    // allocate exit ports:
    Port p2 = new ComponentAbruptExitPort(this);
    Port p3 = new ComponentNormalExitPort(this);
    ControlEdge.connect(cc,p3);
    Merge m;
    if (n == 0) {
      m = null;
      ControlEdge.connect(cc,p2);
    } else {
      m = new Merge();
      ControlEdge.connect(cc,m);
      ControlEdge.connect(m,p2);
    }
    init(node,indices,m,cc);
  }
    
  private void init(IRNode node, int[] indices) {
    int n = indices.length;
    // allocate exit ports
    Port p2;
    if (n == 0) {
      // no children, i.e. no possibility for abrupt exit:
      p2 = new ComponentBlankAbruptExitPort(this);
    } else {
      p2 = new ComponentAbruptExitPort(this);
    }
    Port p3 = new ComponentNormalExitPort(this);
    init(node,indices,p2,p3);
  }

  private void init(IRNode node, int[] indices,
		    ControlNode abruptExit, ControlNode normalExit) {
    int n = indices.length;
    Port p1 = new ComponentEntryPort(this);
    // allocate and connect subcomponents
    for (int i=n-1; i >=0 ; --i) {
      IRLocation loc = tree.childLocation(node,indices[i]);
      Subcomponent sub = new Subcomponent(this,loc);
      Port sp1 = new SubcomponentEntryPort(sub);
      Port sp2 = new SubcomponentAbruptExitPort(sub);
      Port sp3 = new SubcomponentNormalExitPort(sub);
      // attach the straight-through normal control-flow
      ControlEdge e1 = new SimpleControlEdge(sp3,normalExit);
      normalExit = sp1;
      if (i != 0) { // allocate a MergeNode for abrupt exits
        ControlNode newAbruptExit = new Merge();
	ControlEdge e = new SimpleControlEdge(newAbruptExit,abruptExit);
	abruptExit = newAbruptExit;
      }
      ControlEdge e2 = new SimpleControlEdge(sp2,abruptExit);
    }
    ControlEdge elast = new SimpleControlEdge(p1,normalExit);
  }
}

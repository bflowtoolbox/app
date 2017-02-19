package fluid.control;

import fluid.ir.IRLocation;

/** A subcomponent associated with a sequence of expressions
 * or statements executed in simple sequence.  Each subcomponent
 * has two control edges: one for normal and one for abrupt 
 * termination.
 */
public class SequenceSubcomponent extends VariableSubcomponent {
  public SequenceSubcomponent(Component comp, IRLocation loc) {
    super(comp,loc,2);
    entryPort = new SubcomponentEntryPort(this);
    normalExitPort = new SubcomponentNormalExitPort(this);
    abruptExitPort = new SubcomponentAbruptExitPort(this);
    ControlNode merge = new Merge();
    ControlEdge temp = new SimpleControlEdge(abruptExitPort,merge);
    ControlEdge normalEntry = 
      new VariableSubcomponentControlEdge(this,0,true,entryPort);
    ControlEdge abruptEntry =
      new VariableSubcomponentControlEdge(this,1,true,merge);
    ControlEdge normalExit =
      new VariableSubcomponentControlEdge(this,0,false,normalExitPort);
    ControlEdge abruptExit =
      new VariableSubcomponentControlEdge(this,1,false,merge);
  }
}

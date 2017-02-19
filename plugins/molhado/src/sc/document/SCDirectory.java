package sc.document;

import java.io.DataInput;
import java.io.IOException;

import fluid.util.UniqueID;
import fluid.version.Era;
import fluid.version.Version;
/**
 * @author Tien N. Nguyen
 */
public class SCDirectory extends Component {
  private static final int           magic = 0x44495200; // 'DIR\0'
  // Constructors
  
  public SCDirectory(UniqueID id) {
    super(magic, id);
  }
  
  /** Total new one */
  public SCDirectory() {
    super (magic, true);
  }
  
  public ComponentFactory getFactory() {
    return Factory.prototype;
  }

  public String getFileName() {
    return this.getID()
            + ".dir";
  }

  /** save/loadDelta and save/loadSnapshot for VersionedChunkDelta */
  public void saveDelta(Era era,fluid.util.FileLocator floc) 
    throws IOException {
  }

  public void loadDelta(Era era, fluid.util.FileLocator floc) 
    throws IOException {
  }

  public void saveSnapshot(Version v, fluid.util.FileLocator floc) 
    throws IOException {
  }

  public void loadSnapshot(Version v, fluid.util.FileLocator floc) 
    throws IOException {
  }
  
  private static class Factory extends ComponentFactory 
  {
    public static final Factory prototype = new Factory();
    private Factory() {
      super();
    }

    /** Create an SCDirectory based on input in */
    public Component create(UniqueID id, DataInput in)
    {
      SCDirectory dir = new SCDirectory(id);
      return dir;
    }
  }
  
}

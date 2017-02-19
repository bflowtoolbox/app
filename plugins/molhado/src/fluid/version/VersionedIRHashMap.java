package fluid.version;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

import fluid.FluidError;

/**
 * 
 */
public class VersionedIRHashMap extends AbstractMap implements Map {

  /**
   * Usually implemented using AbstractSet
   * set Iterator must implement remove
   * @see java.util.Map#entrySet()
   */
  public Set entrySet() {
    return null;
  }
  
  /** 
   * @param key Should be an IRNode
   */
  public Object put(Object key, Object val) {
  	return null;
  }
  
  public int hashCode() {
  	throw new FluidError("This doesn't make sense on a versioned map");
  }
}

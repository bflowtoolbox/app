package sc.document;

import java.io.DataInput;
import java.util.Hashtable;

import fluid.util.UniqueID;

/**
 * @author Tien N. Nguyen
 */
public abstract class ComponentFactory {
  private static final Hashtable allFactories = new Hashtable();

  public ComponentFactory() 
  {
    allFactories.put(getName(),this);
  }
  
  
  public String getName() {
    return this.getClass().getName();
  }

  public static ComponentFactory getComponentFactory(String factName) {
    ComponentFactory factory = (ComponentFactory) allFactories.get(factName);
    if (factory == null) {
      try {
        Class.forName(factName);
        factory = (ComponentFactory) allFactories.get(factName);
        if (factory == null) {
          System.out.println("ComponentFactory class " + factName + " does not define a prototype !");
          return null;
        }
      } catch (Exception e) {
         e.printStackTrace();
         return null;
      }
    }
    return factory;
  }

  public abstract Component create(UniqueID id, DataInput in);
}

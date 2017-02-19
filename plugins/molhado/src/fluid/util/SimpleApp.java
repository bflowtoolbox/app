// $Header: /usr/local/refactoring/molhadoRef/src/fluid/util/SimpleApp.java,v 1.1 2006/03/21 23:20:54 dig Exp $
package fluid.util;

/**
 * 
 */
public class SimpleApp {
  /**
   * Log4j logger for this class
   */
  private static final org.apache.log4j.Logger LOG =
    org.apache.log4j.Logger.getLogger("FLUID.simpleApp");
      
  
  public static org.apache.log4j.Logger thisApp = null;

  /**
   * Runs various setup routines and configures 'thisApp' to be the category
   * for this application
   */
  public static QuickProperties configure(String appName) {
    if (thisApp != null) {
      thisApp.info("Already configured for "+appName);
    }

    /* Init Log4j
     */
    QuickProperties qp = QuickProperties.getInstance();
    qp.loadPropertyFile(appName);
    org.apache.log4j.PropertyConfigurator.configure(qp.getProperties());

    thisApp = org.apache.log4j.Logger.getLogger(appName);

    return qp;
  }
  
  public static Object logInit(org.apache.log4j.Logger log, String init) {
    QuickProperties.getInstance();
    log.info("Initializing "+init);
    return null;
  }  

  public static Object logInit(org.apache.log4j.Logger log, String init,
                                 Throwable t) {
    QuickProperties.getInstance();
    log.info("Initializing "+init, t);
    return null;
  }    
}

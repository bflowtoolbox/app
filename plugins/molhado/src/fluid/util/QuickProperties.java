/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/util/QuickProperties.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */

package fluid.util;

public final class QuickProperties {

  private static final QuickProperties INSTANCE = new QuickProperties();

  private String propertyHomeDir;
  private java.util.Properties quickProperties =
    new java.util.Properties(System.getProperties());
  ;

  /**
   * A static factory method for this singleton class that returns
   * the only instance of this class
   */
  public static QuickProperties getInstance() {
    return INSTANCE;
  }

  /**
   * Returns a normal java.util.Properties object for use.  This object
   * contains the merge of system properties, properties loaded from
   * "fluid-system.properties", and any property files loaded by calls
   * to loadPropertyFile().
   */
  public java.util.Properties getProperties() {
    return quickProperties;
  }

  /**
   * Merges a property file into this object.  The suffix ".properties" is
   * appended to the given fileName.  The file is searched for in either the
   * user's home directory or the directory specified in the
   * "fluid.property.home" property in the "fluid-property-home.properties"
   * file (which must always be in the user's home directory).  If the given
   * file does not exist then the same file with a "." prefix is searched for
   * instead (to allow Unix users to follow typical Unix conventions).
   *
   * A warning that the property files does not exist is ONLY given if the
   * property "fluid.missing.properties" is defined  This is since several
   * valid uses of this class would allow a specific properties file not to
   * exist.
   */
  public void loadPropertyFile(String fileName) {
    fileName = fileName + ".properties";

    java.io.File newFile = new java.io.File(propertyHomeDir, fileName);
    java.io.File newDotFile = new java.io.File(propertyHomeDir, "." + fileName);
    java.net.URL newURL = QuickProperties.class.getResource(fileName);
    java.io.File newURLFile =
      (newURL != null) ? new java.io.File(newURL.getFile()) : null;

    if (newFile.exists()) {
      loadPropertyFile(newFile);
    } else if (newDotFile.exists()) {
      loadPropertyFile(newDotFile);
    } else if (newURLFile != null && newURLFile.exists()) {
      loadPropertyFile(newURLFile);
    } else {
      // Output a warning only if requested to do so
      boolean warnWhenFileDoesNotExist =
        "-" != quickProperties.getProperty("fluid.missing.properties", "-");
      if (warnWhenFileDoesNotExist) {
        System.err.println(
          "WARNING: fluid.util.QuickProperties could not"
            + " find the properties file "
            + newFile.getPath()
            + " (or "
            + newDotFile.getName()
            + " or "
            + ((newURLFile != null) ? newURLFile.getName() : "no_such_resource")
            + ")");
      }
      // otherwise silence is golden
    }
  }

  private void loadPropertyFile(java.io.File propertyFile) {
    try {
      java.io.InputStream in = new java.io.FileInputStream(propertyFile);
      quickProperties.load(in);
      in.close();
    } catch (java.io.FileNotFoundException e) {
      System.err.println(
        "Strange filesystem modification during property"
          + " file open (QuickProperites singleton)");
    } catch (java.io.IOException e) {
      System.err.println(
        "Strange filesystem modification while reading"
          + " property file (QuickProperties singleton)");
    }
  }

  private QuickProperties() {
    // The default location of all property files is "user.home"
    propertyHomeDir = System.getProperty("user.home");

    // This location can be redirected via a "fluid-property-home.properties"
    java.io.File redirectFile =
      new java.io.File(propertyHomeDir, "fluid-property-home.properties");
    if (redirectFile.exists()) {
      loadPropertyFile(redirectFile);
      propertyHomeDir =
        quickProperties.getProperty("fluid.property.home", propertyHomeDir);
    }

    // Load "fluid-system.properties" right away (for all programs!)
    loadPropertyFile("fluid-system");

    // HACK to get rt.jar in by putting it in fluid.rt.jar property
    String rtjar = quickProperties.getProperty("fluid.rt.jar", "");
    System.setProperty(
      "java.class.path",
      (rtjar.equals("") ? "" : rtjar + java.io.File.pathSeparator)
        + System.getProperty("java.class.path", "."));

    // Do a default configuration of log4j
    org.apache.log4j.BasicConfigurator.configure();
    org.apache.log4j.Logger.getRootLogger().setLevel(
      org.apache.log4j.Level.ERROR);
    // avoid too much message clutter
    org.apache.log4j.PropertyConfigurator.configure(quickProperties);
  }
}

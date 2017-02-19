/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/util/PathFileLocator.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.StringTokenizer;
import java.util.Vector;

/** A locator that has a list of directories to try first.
 * If none fit, the current directory is used.
 */
public class PathFileLocator implements FileLocator {
  private final File[] directories;

  public PathFileLocator(File[] ds) {
    directories = (File[]) ds.clone();
  }

  public PathFileLocator(String path) {
    String sep = System.getProperty("path.separator");
    Vector ds = new Vector();
    StringTokenizer toks = new StringTokenizer(path, sep);
    while (toks.hasMoreTokens()) {
      String dirString = toks.nextToken();
      File dir = new File(dirString);
      if (dir.isDirectory()) {
        ds.addElement(dir);
      }
    }
    directories = new File[ds.size()];
    ds.copyInto(directories);
  }

  public File locate(String name, boolean mustExist, boolean dir)
    throws IOException {
    for (int att = 0; att < (mustExist ? 1 : 2); ++att) {
      for (int i = 0; i < directories.length; ++i) {
        File test;
        test = new File(directories[i], name);
        if (att == 0
          && (dir
            ? (test.isDirectory() && (mustExist || test.canWrite()))
            : test.isFile())
          || att == 1
          && canCreateOrWrite(test, dir))
          return test;
      }
    }
    if (mustExist)
      throw new FileNotFoundException(
        "current path is " + System.getProperty("user.dir") + " no such " + (dir ? "directory " : "file ") + name);
    else
      throw new FileNotFoundException("could not find writeable " + name);
  }

  public File locateFile(String name, boolean mustExist) throws IOException {
    // System.out.println("Locating file " + name +
    //		       (mustExist ? " for read" : " for write"));
    return locate(name, mustExist, false);
  }

  public File locateDirectory(String name, boolean mustExist)
    throws IOException {
    return locate(name, mustExist, true);
  }

  public static boolean canRead(File f, boolean dir) {
    return f.exists() && (dir == f.isDirectory());
  }

  public static boolean canCreateOrWrite(File f, boolean dir) {
    if (f.exists())
      return (dir == f.isDirectory()) && f.canWrite();
    String p = new File(f.getAbsolutePath()).getParent();
    if (p == null)
      return false;
    // System.out.println("Looking at " + p);
    File pdir = new File(p);
    boolean possible = canCreateOrWrite(pdir, true);
    if (possible) {
      if (!pdir.exists())
        pdir.mkdir();
    }
    return possible;
  }

  public static void main(String args[]) {
    String s = System.getProperty(args[0], ".");
    System.out.println("Property of " + args[0] + " is " + s);
    FileLocator floc = new PathFileLocator(s);
    for (int i = 1; i < args.length; ++i) {
      try {
        System.out.println(floc.locateFile(args[i], true).toString());
      } catch (IOException ex) {
        System.out.println(ex.toString());
      }
    }
  }

  public OutputStream openFileWrite(String name) throws IOException {
    File file = this.locateFile(name, false);
    return new BufferedOutputStream(new FileOutputStream(file));
  }

  public InputStream openFileRead(String name) throws IOException {
    File file = this.locateFile(name, true);
    return new BufferedInputStream(new FileInputStream(file));
  }

  public void commit() {
  }
}
// $Header: /usr/local/refactoring/molhadoRef/src/fluid/util/ZipFileLocator.java,v 1.1 2006/03/21 23:20:54 dig Exp $
package fluid.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipFileLocator implements FileLocator {
  private final FileLocator floc; // for finding the zip files
  private final String index;     // "   "
  private final String zipExt;    // "   "
  private ZipOutputStream outZip = null;  
  private String outZipName = null;

  public ZipFileLocator(FileLocator floc) {
    this.floc   = floc;
    this.index  = "ZipIndex.zi";
    this.zipExt = ".zfl";

    try {
      initZipList();
    } 
    catch(IOException e) {}
  }

  public File locateFile(String name, boolean mustExist)
    throws IOException {
    throw new IOException("Cannot be implemented");
  }

  public OutputStream openFileWrite(String name)
    throws IOException {
    ZipEntry ze = new ZipEntry(name);
    openOut();
    outZip.putNextEntry(ze);
    return outZip; 
  }

  public InputStream openFileRead(String name)
    throws IOException {
    // search existing zip list
    InputStream is = searchZipList(name);

    // Otherwise 
    if ((is == null) && (outZip != null)) {
      String outZipName = this.outZipName;
      commit();
      is = searchZipFile(outZipName, name);
    }
    if (is == null) {
      throw new IOException("Couldn't find "+name);
    }
    return new BufferedInputStream(is);
  }

  public void commit() throws IOException {
    if (outZip != null) {
      outZip.close();
      updateZipList(outZipName);
      outZip = null;
    }
  }

  private void openOut() throws IOException {
    if (outZip != null) {
      // check if close()'d
      try {
	outZip.closeEntry();
	return;
      } 
      catch(IOException e) {
	commit();
	// as if outZip was null
      }
    }
    UniqueID id = new UniqueID();
    outZipName  = id.toString()+zipExt;
    
    OutputStream fos = floc.openFileWrite(outZipName);
    outZip = new ZipOutputStream(new BufferedOutputStream(fos));
    // outZip.setMethod(ZipOutputStream.STORED);
    // outZip.setLevel(0);
  }
  
  private InputStream searchZipFile(String zip, String name) 
    throws IOException {
    return searchZipFile(getZipFile(zip), name); // why not use zipList?
  }

  private InputStream searchZipFile(ZipFile zf, String name) 
    throws IOException {
    return zf.getInputStream(zf.getEntry(name));
  }

  private void initZipList() throws IOException {
    Reader isr       = new InputStreamReader(floc.openFileRead(index));
    BufferedReader r = new BufferedReader(isr);
    for(String line = r.readLine(); (line != null); line = r.readLine()) {
      zipList.addElement(getZipFile(line));      
    }
  }

  // append outZipName to index file (and zip list)
  private void updateZipList(String zipName) throws IOException {
    zipList.addElement(getZipFile(zipName));
    /*
    // contents of zipList should be correct, but w/ absolute paths
    PrintWriter w = new PrintWriter(floc.openFileWrite(index));
    // FileWriter fw = new FileWriter(index, true); // append to end
    for
    for(int i = zipList.size()-1; i>=0; i--) {
      ZipFile zip    = (ZipFile) zipList.elementAt(i);
    w.println(zipName);
    w.close();
    */

    // writing the names from the zipCache - randomized order
    PrintWriter w = new PrintWriter(floc.openFileWrite(index));
    Enumeration en = zipCache.keys();
    while (en.hasMoreElements()) {
      String name = (String) en.nextElement();
      w.println(name);
    }
    w.close();
  }

  private InputStream searchZipList(String name) throws IOException {    
    // searched backwards to simulate overwrite?
    for(int i = zipList.size()-1; i>=0; i--) {
      ZipFile zip    = (ZipFile) zipList.elementAt(i);
      InputStream is = searchZipFile(zip, name);
      if (is != null) {
	return is;
      }
    }
    return null;
  }
  private final Vector zipList = new Vector();

  private ZipFile getZipFile(String name) throws IOException {
    // not necessary
    String n = name.intern();
    Object o = zipCache.get(n);
    if (o == null) {
      o = new ZipFile(floc.locateFile(name, true));
      zipCache.put(n, o);
    }
    return (ZipFile) o;
  }
  private final Hashtable zipCache = new Hashtable();
}

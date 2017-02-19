/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/util/FileLocator.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */
package fluid.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface FileLocator {
  /** Return a File for the following name.
   * @param mustExist if true then the file must exist and be
   * a normal file, otherwise it must be writeable.
   * @throws IOException if does not exist or is not writeable.
   */
  public File locateFile(String name, boolean mustExist)
       throws IOException;

  /** Return a directory for the following name.
   * @param mustExist if true then the file must exist and be
   * a directory, otherwise it must be createable or writeable.
   * @throws IOException if does not exist or is not writeable.

  public File locateDirectory(String name, boolean mustExist)
       throws IOException;
   */

  /** Return a OutputStream for the following name.
   * @throws IOException if it is not createable or not writeable.
   */
  public OutputStream openFileWrite(String name)
       throws IOException;

  /** Return a InputStream for the following name.
   * @throws IOException if it is not readable
   */
  public InputStream openFileRead(String name)
       throws IOException;

  public void commit() throws IOException;
}

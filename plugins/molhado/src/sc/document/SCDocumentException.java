/* $Header: /usr/local/refactoring/molhadoRef/src/sc/document/SCDocumentException.java,v 1.1 2006/03/21 23:20:57 dig Exp $ */

package sc.document;

import sc.SCRuntimeException;

/**
 * SC Document Exception
 *
 * @author Satish Chandra Gupta
 */
public class SCDocumentException extends SCRuntimeException {
  public SCDocumentException() { super(); }
  public SCDocumentException(String s) { super(s); }
}

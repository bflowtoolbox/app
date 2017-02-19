/* $Header: /usr/local/refactoring/molhadoRef/src/sc/document/SCDocumentNodeAttributeUnknownException.java,v 1.1 2006/03/21 23:20:57 dig Exp $ */

package sc.document;

/**
 * SC Document Node Attribute Unknown Exception
 *
 * Exception for using string for attribute name when no such attribute
 * is known. To be distinguished from fluid.ir.SlotNotRegisteredException.
 *
 * @author Satish Chandra Gupta
 */
public class SCDocumentNodeAttributeUnknownException extends SCDocumentException {
  public SCDocumentNodeAttributeUnknownException() { super(); }
  public SCDocumentNodeAttributeUnknownException(String s) { super(s); }
}

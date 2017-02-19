package sc.document;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import sc.xml.NullDTD;
import fluid.ir.IRNode;
import fluid.ir.PlainIRNode;
import fluid.ir.SlotInfo;
import fluid.tree.Operator;

/**
 * @author Tien
 */
public class Dom2SC {
    //
    // Data
    //

    /** Print writer. */
    protected PrintWriter fOut;

    /** Canonical output. */
    protected boolean fCanonical;

    private final NullDTD dtd;

    private SCDocument doc;
    //
    // Constructors
    //

    /** Default constructor. */
    public Dom2SC(SCDocument d) {
      dtd = NullDTD.prototype;
      PlainIRNode.setCurrentRegion(d.getRegion());
      doc = d;
    } // <init>()
    

    //
    // Public methods
    //

    /** Sets whether output is canonical. */
    public void setCanonical(boolean canonical) {
        fCanonical = canonical;
    } // setCanonical(boolean)

    /** Sets the output stream for printing. */
    public void setOutput(OutputStream stream, String encoding)
        throws UnsupportedEncodingException {

        if (encoding == null) {
            encoding = "UTF8";
        }

        java.io.Writer writer = new OutputStreamWriter(stream, encoding);
        fOut = new PrintWriter(writer);

    } // setOutput(OutputStream,String)

    /** Sets the output writer. */
    public void setOutput(java.io.Writer writer) {

        fOut = writer instanceof PrintWriter
             ? (PrintWriter)writer : new PrintWriter(writer);

    } // setOutput(java.io.Writer)

    /** Writes the specified node, recursively. */
    public void write(Node node) {

        // is there anything to do?
        if (node == null) {
            return;
        }

        short type = node.getNodeType();
        switch (type) {
            case Node.DOCUMENT_NODE: {
                Document document = (Document)node;
                if (!fCanonical) {
                    fOut.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                    fOut.flush();
                    write(document.getDoctype());
                }
                write(document.getDocumentElement());
                break;
            }

            case Node.DOCUMENT_TYPE_NODE: {
                DocumentType doctype = (DocumentType)node;
                fOut.print("<!DOCTYPE ");
                fOut.print(doctype.getName());
                String publicId = doctype.getPublicId();
                String systemId = doctype.getSystemId();
                if (publicId != null) {
                    fOut.print(" PUBLIC '");
                    fOut.print(publicId);
                    fOut.print("' '");
                    fOut.print(systemId);
                    fOut.print('\'');
                }
                else {
                    fOut.print(" SYSTEM '");
                    fOut.print(systemId);
                    fOut.print('\'');
                }
                String internalSubset = doctype.getInternalSubset();
                if (internalSubset != null) {
                    fOut.println(" [");
                    fOut.print(internalSubset);
                    fOut.print(']');
                }
                fOut.println('>');
                break;
            }

            case Node.ELEMENT_NODE: {
                fOut.print('<');
                fOut.print(node.getNodeName());
                Attr attrs[] = sortAttributes(node.getAttributes());
                for (int i = 0; i < attrs.length; i++) {
                    Attr attr = attrs[i];
                    fOut.print(' ');
                    fOut.print(attr.getNodeName());
                    fOut.print("=\"");
                    normalizeAndPrint(attr.getNodeValue());
                    fOut.print('"');
                }
                fOut.print('>');
                fOut.flush();

                Node child = node.getFirstChild();
                while (child != null) {
                    write(child);
                    child = child.getNextSibling();
                }
                break;
            }

            case Node.ENTITY_REFERENCE_NODE: {
                if (fCanonical) {
                    Node child = node.getFirstChild();
                    while (child != null) {
                        write(child);
                        child = child.getNextSibling();
                    }
                }
                else {
                    fOut.print('&');
                    fOut.print(node.getNodeName());
                    fOut.print(';');
                    fOut.flush();
                }
                break;
            }

            case Node.CDATA_SECTION_NODE: {
                if (fCanonical) {
                    normalizeAndPrint(node.getNodeValue());
                }
                else {
                    fOut.print("<![CDATA[");
                    fOut.print(node.getNodeValue());
                    fOut.print("]]>");
                }
                fOut.flush();
                break;
            }

            case Node.TEXT_NODE: {
                normalizeAndPrint(node.getNodeValue());
                fOut.flush();
                break;
            }

            case Node.PROCESSING_INSTRUCTION_NODE: {
                fOut.print("<?");
                fOut.print(node.getNodeName());
                String data = node.getNodeValue();
                if (data != null && data.length() > 0) {
                    fOut.print(' ');
                    fOut.print(data);
                }
                fOut.println("?>");
                fOut.flush();
                break;
            }
        }

        if (type == Node.ELEMENT_NODE) {
            fOut.print("</");
            fOut.print(node.getNodeName());
            fOut.print('>');
            fOut.flush();
        }

    } // write(Node)

    /** Returns a sorted list of attributes. */
    protected Attr[] sortAttributes(NamedNodeMap attrs) {

        int len = (attrs != null) ? attrs.getLength() : 0;
        Attr array[] = new Attr[len];
        for (int i = 0; i < len; i++) {
            array[i] = (Attr)attrs.item(i);
        }
        for (int i = 0; i < len - 1; i++) {
            String name = array[i].getNodeName();
            int index = i;
            for (int j = i + 1; j < len; j++) {
                String curName = array[j].getNodeName();
                if (curName.compareTo(name) < 0) {
                    name = curName;
                    index = j;
                }
            }
            if (index != i) {
                Attr temp = array[i];
                array[i] = array[index];
                array[index] = temp;
            }
        }

        return array;

    } // sortAttributes(NamedNodeMap):Attr[]

    //
    // Protected methods
    //

    /** Normalizes and prints the given string. */
    protected void normalizeAndPrint(String s) {

        int len = (s != null) ? s.length() : 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            normalizeAndPrint(c);
        }

    } // normalizeAndPrint(String)

    /** Normalizes and print the given character. */
    protected void normalizeAndPrint(char c) {

        switch (c) {
            case '<': {
                fOut.print("&lt;");
                break;
            }
            case '>': {
                fOut.print("&gt;");
                break;
            }
            case '&': {
                fOut.print("&amp;");
                break;
            }
            case '"': {
                fOut.print("&quot;");
                break;
            }
            case '\r':
            case '\n': {
                if (fCanonical) {
                    fOut.print("&#");
                    fOut.print(Integer.toString(c));
                    fOut.print(';');
                    break;
                }
                // else, default print char
            }
            default: {
                fOut.print(c);
            }
        }

    } // normalizeAndPrint(char)

    // -------------------------------------------------------------------------
    /** Conversion function */
    // -------------------------------------------------------------------------
    public IRNode convert(Node node) {

        // is there anything to do?
        if (node == null) {
            return null;
        }
        
        short type = node.getNodeType();
        switch (type) {
            case Node.DOCUMENT_NODE: {
                Document document = (Document)node;                
                IRNode root = convert(document.getDocumentElement());
                return root;
            }            

            case Node.ELEMENT_NODE: {
                Operator thisOp = null;
                IRNode ir_node = null;
                try {
                  thisOp = dtd.findOperator(node.getNodeName());
                  ir_node = thisOp.createNode();
                } catch (Exception e) {
                  e.printStackTrace();
                  return null;
                }
                
                // Add to the mapping in SVGDocument
                // if (doc instanceof SCSVGDocument) {
                //  SCSVGDocument d = (SCSVGDocument) doc;
                //  d.node_map.put(node,ir_node);
                //}
                                
                // Traverse all attributes
                Attr attrs[] = sortAttributes(node.getAttributes());
                for (int i = 0; i < attrs.length; i++) {
                    Attr attr = attrs[i];
                    String attrName = attr.getNodeName();                    
                    SlotInfo thisAttr = thisOp.getAttribute(attrName);
                    if (thisAttr == null) {
                      System.out.println("Could not find " + attrName);
                      return null;
                    }                                                            
                    String attrVal = normalize1(attr.getNodeValue());
                    ir_node.setSlotValue(thisAttr, thisAttr.type().fromString(attrVal));                    
                }
                // RECURSIVE CALL
                Node child = node.getFirstChild();
                while (child != null) {
                    IRNode child_node = convert(child);
                    if (child_node != null) 
                      thisOp.tree().addChild(ir_node,child_node);
                    child = child.getNextSibling();
                }
                return ir_node;
            }

            
            case Node.ENTITY_REFERENCE_NODE: {
                return null;
            }
                        
            case Node.CDATA_SECTION_NODE: {
                return null;
            }

            case Node.TEXT_NODE: {
                Operator thisOp = dtd.getRawTextOperator();
                IRNode ir_node = thisOp.createNode();
                /* check whether this attribute is applicable */
                SlotInfo thisAttr = thisOp.getAttribute(dtd.getRawTextOperatorAttrName());
                if (thisAttr == null) {
                  return null;                                                
                }
                String text = normalize1(node.getNodeValue());
                ir_node.setSlotValue(thisAttr, thisAttr.type().fromString(text));
                return ir_node;
            }

            case Node.PROCESSING_INSTRUCTION_NODE: {
                return null;
            }
            
            default: return null;
        }        

    } // convert(Node)
    
    /** Normalizes and prints the given string. */
    protected String normalize1(String s) {
        String result = "";
        int len = (s != null) ? s.length() : 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            String t = normalize2(c);
            result = result + t;
        }
        return result;

    } // normalize1(String)

    /** Normalizes and print the given character. */
    protected String normalize2(char c) {
        String result = "";
        switch (c) {
            case '<': {
                result += "&lt;";
                break;
            }
            case '>': {
                result += "&gt;";
                break;
            }
            case '&': {
                result += "&amp;";
                break;
            }
            case '"': {
                result += "&quot;";
                break;
            }
            case '\r':
            case '\n': {
                // result = result + "&#" + Integer.toString(c) + ';';
                result = result + '\n';
                break;
            }
            default: {
                result += c;
            }
       }
       return result; 
    } // normalize2(char)
}

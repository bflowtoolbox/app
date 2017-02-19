/* $Header: /usr/local/refactoring/molhadoRef/src/sc/xml/XMLParser.java,v 1.1 2006/03/21 23:21:00 dig Exp $ */

package sc.xml;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

import sc.document.SCDocument;
import fluid.FluidRuntimeException;
import fluid.NotImplemented;
import fluid.ir.IRNode;
import fluid.ir.IRType;
import fluid.ir.PlainIRNode;
import fluid.ir.SlotInfo;
import fluid.parse.ParseException;
import fluid.tree.Operator;
import fluid.util.StringFilter;

/**
 * To parse XML document to IRNode, and to dump IRNode in XML
 *
 * @author Satish Chandra Gupta
 */
public class XMLParser {

  /** tokenizer & DTD
   */
  private StreamTokenizer st = null;
  private final IRDTD dtd;
  /** The document that this XML parser will create nodes for */
  private SCDocument doc;

  /** set the tokenizer to XML mode or in basic mode. In basic mode
   * tokenizer reads char by char, and returns char value as token.
   */
  private void xmlTokenizerMode(boolean flag) {
    if (st != null) {
      if (flag) {
        st.wordChars('a', 'z');
        st.wordChars('A', 'Z');
        st.wordChars(128 + 32, 255);
        // Handle attributes
        st.wordChars('-','-');
        st.wordChars('_','_');
        st.wordChars('.','.');
        st.whitespaceChars(0, ' ');
        st.quoteChar('"');
        //st.quoteChar('\'');
        st.parseNumbers();
      } else {
        st.resetSyntax();
      }
    }
  }

  /** @param document is the document that this parser will create
   */
  public XMLParser(Reader in, SCDocument document, IRDTD d) {
    ReInit(in);
    dtd = d;
    doc = document;
    PlainIRNode.setCurrentRegion(doc.getRegion());
  }

  public void ReInit(Reader in) {
    st = new StreamTokenizer(in);
    st.resetSyntax();
    xmlTokenizerMode(true);
  }

  /** to parse DTD using the given input stream
   * <b> NOT implemented </b> yet, returns null right now.
   */
  public static IRDTD parseDTD(Reader in) {
    throw new NotImplemented("XMLParser::parseDTD()");
  }


  /** for indentation
   */
  public static void doindent(Writer w, int i) throws IOException {
    for (; i > 0; --i) {
      w.write("  ");
    }
  }

  /** This function does the parsing job
   */
  public IRNode parse() throws XMLParserException {
    IRNode n;

    try {
      n = parseOneXMLNode();

      /* there should be nothing left in the input after this */
      st.nextToken();
      if (st.ttype != StreamTokenizer.TT_EOF) {
        throw new XMLParserException(" unexpected " + st);
      }
    } catch (IOException e) {
      throw new XMLParserException("IOException : " + e.getMessage());
    }

    return n;
  }

  /** This function peeks to find whether the coming node is an XML
   * node starting with '<', or a raw text node. and call appropriate
   * methods
   */
  private IRNode parseOneNode() throws XMLParserException {
    IRNode n = null;

    try {
      st.nextToken();
      if (st.ttype == '<') {
        st.pushBack();
        n = parseOneXMLNode();
      } else { // raw text, so TextNode
        xmlTokenizerMode(false); // change the mode
        n = parseOneRawTextNode();
        xmlTokenizerMode(true);  // restore the mode
      }
    } catch (IOException e) {
      throw new XMLParserException("IOException : " + e.getMessage());
    }

    return n;
  }

  /**
   * Raw text is kept in special RawTextNode. So the DTD should be built
   * such that it has an operator with name "RawTextOperator" and the
   * raw text is kept as "info" attribute;
   */
  private IRNode parseOneRawTextNode() throws XMLParserException {
    Operator thisOp = dtd.getRawTextOperator();
    IRNode   n = thisOp.createNode();

    /* check whether this attribute is applicable */
    SlotInfo thisAttr = thisOp.getAttribute(dtd.getRawTextOperatorAttrName());
    if (thisAttr == null) {
      throw new XMLParserException("'" + dtd.getRawTextOperatorAttrName()
                                       + "' is not an attribute of '"
                                       + thisOp.name() + "' : " + st);
    }

    StringBuffer attrVal = new StringBuffer();

    try {
      // One token was already read, so need to take care of that.
      // It is needed because the mode is changed from XML.
      if (st.ttype == StreamTokenizer.TT_WORD) {
        attrVal.append(st.sval);
      } else if (st.ttype == StreamTokenizer.TT_NUMBER) {
        if (st.nval == ((int) st.nval)) {  // bad hack to avoid .0 at the end
          attrVal.append((int) st.nval);
        } else {
          attrVal.append(st.nval);
        }
      } else if (st.ttype != StreamTokenizer.TT_EOF) {
        attrVal.append((char) st.ttype);
      }

      while (st.nextToken() != StreamTokenizer.TT_EOF && st.ttype != '<') {
        attrVal.append((char) st.ttype);
      }

      // delete the trailing while spaces
      int last = attrVal.length();

      int i = last - 1;
      while (Character.isWhitespace(attrVal.charAt(i)))
        i--;

      attrVal.delete(i+1, last);

      st.pushBack();
    } catch (IOException e) {
      throw new XMLParserException("IOException : " + e.getMessage());
    }

    n.setSlotValue(thisAttr, thisAttr.type().fromString(attrVal.toString()));

    if (!thisOp.isComplete(n)) {
      throw new XMLParserException(dtd.getRawTextOperatorAttrName()
                                   + "is not provided for " + thisOp.name()
                                   + " : " + st);
    }

    return n;
  }

  /** This function parses one XML node completely, which means this function
   * builds the tree having current node and all its children (recursively).
   * Returns null only if it hits a closing tag, otherwise raises exception.
   */
  private IRNode parseOneXMLNode() throws XMLParserException {
    IRNode   n = null;
    String   thisNodeName = null;
    Operator thisOp = null;

    try {
      /* get a token and check whether it is '<' */
      st.nextToken();
      if (st.ttype != '<')
      {
        throw new XMLParserException("'<' expected instead of " + st);
      }

      /* at this point, it may be starting of a new tag, if it is so it
       * will be followed by a TT_WORD token. It may also be a closing
       * tag, in that case it will be followed by '/' token. If it is so
       * then it should return null, it will be taken care by Callee
       * instance of this function
       */
      st.nextToken();
      if (st.ttype == '/') { /* it is a closing tag, return */
        /* push back '/'. needed to detect error if file contains just "</"
         * but remember that '<' has been read, can't be pushed back.
         */
        st.pushBack();
        return null;
      } else if (st.ttype == StreamTokenizer.TT_WORD) {
        /* new sub tree - a Node having name specified by sval */
        thisNodeName = st.sval;
        try {
          thisOp = dtd.findOperator(thisNodeName);
          n = thisOp.createNode();
        } catch (FluidRuntimeException e) {
          throw e;
          //throw new XMLParserException(thisNodeName + " " + e.getMessage());
        }

        /* parse all attributes */
        while (st.nextToken() == StreamTokenizer.TT_WORD) {
          /* check whether this attribute is applicable */
          String attrName = st.sval;
          SlotInfo thisAttr = thisOp.getAttribute(attrName);
          if (thisAttr == null) {
            throw new XMLParserException("'" + attrName + "' is not an attribute of '" + thisOp.name() + "' : " + st);
          }

          /* attribute name must be followed by '=' */
          if (st.nextToken() != '=') {
            throw new XMLParserException("'=' expected instead of " + st);
          }

          String attrVal = null;

          st.nextToken();
          if (st.ttype == '"' || st.ttype == '\'') {
            attrVal = StringFilter.destructEscape(st.sval, '"', '\\');
          } else if (st.ttype == StreamTokenizer.TT_WORD) {
            attrVal = st.sval;
          } else if (st.ttype == StreamTokenizer.TT_NUMBER) {
            attrVal = "" + st.nval;
          } else {
            throw new XMLParserException("attr value expected instead of " +st);
          }

          n.setSlotValue(thisAttr, thisAttr.type().fromString(attrVal));
        } /* end of while - read all attributes */

        /* if this node has children, then this tag will end with '>'
         * otherwise it will end with '/>'
         */

        if (st.ttype == '>') {

          /* keep on reading nodes will you get null node,
           * when you get null node, check closing tag, if that is same
           * as the opening tag of current node then you are done,
           * else raise exception.
           */
          for (IRNode child=parseOneNode(); child != null; child=parseOneNode()) {
            /* add child to this node */
            thisOp.tree().addChild(n, child);
            // System.out.println(thisOp.tree());
            /* why NOT using jjtAddChild(Node n) ??? */
            /* Is it checking validity of child? */
            /* why IllegalChildException is NOT public? */
          }

          /* remember that '<' of closing tag will be consumed by 
           * last parseOneXMLNode() of the loop expecting it start
           * of another XML node
           */
          if (st.nextToken() != '/') {
            throw new XMLParserException("'/' expected instead of " + st);
          }

          if ((st.nextToken() != StreamTokenizer.TT_WORD)
              || (!st.sval.equals(thisNodeName)) ) {
            throw new XMLParserException("Closing tag " + st.sval + " is not same as the corresponsing opening tag " + thisNodeName + " at line " + st.lineno());
          }

          if (st.nextToken() != '>') {
            throw new XMLParserException("'>' expected instead of " + st);
          }

          /* node n has to be returned, control should reach to return
           * statement at the end of this function
           */
        } else if (st.ttype == '/') {
          /* no child, you are done. it should be followed by '>' */
          if (st.nextToken() != '>') {
            throw new XMLParserException("'>' expected instead of " + st);
          }

          /* node n has to be returned, control should reach to return
           * statement at the end of this function for testing completeness
           */
        } else {
          /* ooops, it was not expected */
          throw new XMLParserException("unexpected " + st);
        }
      } else { /* it is an error */
        throw new XMLParserException("unexpected " + st);
      }
    } catch (IOException e) {
      throw new XMLParserException("IOException : " + e.getMessage());
    }

    if (!thisOp.isComplete(n)) {
      throw new XMLParserException("all required attributes were not provided for " + thisOp.name() + " : " + st);
    }

    return n;
  }

  /** method to dump the tree in XML form
   */
  public static void dumpXmlTree(Writer w, IRNode root,
                                 IRDTD dtd, int indent)
  throws XMLParserException, IOException {

    doindent(w,indent);
    if (root == null) {
      w.write("<null/>\n");
      // Test
      // System.out.print("<null/>\n");
    } else {
      Operator op = dtd.getOperator(root);
      if (dtd.getRawTextOperator() != null
          && dtd.getRawTextOperator().includes(op)) {
        SlotInfo attr = op.getAttribute(dtd.getRawTextOperatorAttrName());
        if (root.valueExists(attr)) {
          String rawText = attr.type().toString(root.getSlotValue(attr));
          w.write(rawText + "\n");
          // Test
          // System.out.println(rawText);
        } else {
          throw new XMLParserException(dtd.getRawTextOperatorAttrName()
                                       + " must be set for " + op.name());
        }
      } else {
        w.write("<"+op.name());
        // Test
				// System.out.print("<"+op.name());	
        char[] escaChA = { '"', '\n' };

        Iterator attrItr = op.getAttributes().iterator();
        while (attrItr.hasNext()) {
          SlotInfo nextAttr = (SlotInfo) attrItr.next();
          if (root.valueExists(nextAttr)) {
            IRType attrType = nextAttr.type();
            String attrVal  = StringFilter.constructEscape(attrType.toString(root.getSlotValue(nextAttr)), escaChA , '\\');
            w.write(" " + nextAttr.name() + "=\"" + attrVal + "\"");
            // Test
            // System.out.print(" " + nextAttr.name() + "=\"" + attrVal + "\"");
          }
        }

        if (op.numChildren() == 0) {
          w.write("/>\n");
          // Test
          // System.out.println("/>");
        } else {
          w.write(">\n");
          // Test
          // System.out.println(">");
          Enumeration children = op.tree().children(root);
          while (children.hasMoreElements()) {
            IRNode child = (IRNode)children.nextElement();
            dumpXmlTree(w,child,dtd,indent+1);
          }
          doindent(w,indent);
          w.write("</" + op.name() + ">\n");
          // Test
          // System.out.println("</" + op.name() + ">\n");
        }
      }
    }
    w.flush();
  }

  public IRNode[] parseNodes(Operator o) throws ParseException, IOException {
    if (o!=null && !dtd.isOperatorOK(o)) {
      throw new XMLParserException(o.name() + " is not a valid operator for "
                                   + dtd.getName());
    }

    IRNode[] nodes = null;
    IRNode   n = null;

    if (o==null) {
      xmlTokenizerMode(true);
      n = parseOneXMLNode();

      st.nextToken();
      if (st.ttype!=StreamTokenizer.TT_EOF)
        throw new XMLParserException("Error near "
                                     + st.toString());

      nodes = new IRNode[1];
      nodes[0] = n;
    } else if (dtd.isIntermediateOperator(o)) {
      xmlTokenizerMode(true);
      Vector v = new Vector();
      for (st.nextToken(); st.ttype!=StreamTokenizer.TT_EOF; st.nextToken()) {
        st.pushBack();
        n = parseOneXMLNode();
        v.add(n);
      }
      nodes = new IRNode[v.size()];
      nodes = (IRNode[]) v.toArray(nodes);
    } else if (dtd.getRawTextOperator().equals(o)) {
      st.nextToken(); // raw text parser expects one token to be read
      xmlTokenizerMode(false); // change the mode
      n = parseOneRawTextNode();
      xmlTokenizerMode(true);  // restore the mode

      st.nextToken();
      if (st.ttype!=StreamTokenizer.TT_EOF)
        throw new XMLParserException("Error at " + st.lineno() + " near "
                                     + st.toString());

      nodes = new IRNode[1];
      nodes[0] = n;
    } else {
      throw new XMLParserException(o.name() + " is not supported yet.");
    }

    return nodes;
  }

}
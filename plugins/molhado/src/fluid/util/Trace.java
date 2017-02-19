/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/util/Trace.java,v 1.1 2006/03/21 23:20:53 dig Exp $ */
package fluid.util;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.Stack;

/** A class to trace the output of structures.
 * This class can be used to provide readable output at the same time that
 * we are reading or writing a DataOutput or DataInput stream.
 */
public class Trace {
  private final PrintWriter trace;

  /** Create a tracer that outputs to the given writer. */
  public Trace(Writer w) {
    trace = new PrintWriter(w);
  }

  private final Stack begun = new Stack();

  private boolean hasFreshLine = true;

  protected void freshLine() {
    if (!hasFreshLine) {
      trace.println();
      hasFreshLine = true;
    }
  }

  private int indentSize = 0;

  protected void indent() {
    freshLine();
    for (int i=0; i<indentSize; ++i) trace.print("  ");
    if (indentSize > 0) hasFreshLine = false;
  }

  private boolean currentContextBroken = false;

  /** Trace the reading or writing of data using a string. */
  public void traceString(String s) {
    if (currentContextBroken) {
      indent();
    } else {
      trace.print(" ");
    }
    trace.print(s);
    hasFreshLine = false;
  }

  /** Trace the reading or writing of binary data.
   * Use hexadecimal output format.
   */
  public void traceHex(long i, int bytes) {
    int shift = (bytes-1)*8;
    StringBuffer sb = new StringBuffer("0x");
    while (bytes > 0) {
      byte b = (byte)((i >>> shift) & 255);
      byte u = (byte)((b >> 4) & 15);
      if (u > 9) u += 'a'-10;
      else u += '0';
      b &= 15;
      if (b > 9) b += 'a'-10;
      else b += '0';
      sb.append((char)u);
      sb.append((char)b);
      --bytes;
      shift -= 8;
    }
    traceString(sb.toString()); 
  }

  /** Trace an I/O of a 64 bits integer using our UniqueID translation */
  public void trace32(long l) {
    if (l < 0)
      traceString("#w" + Long.toString(-l,32));
    else
      traceString("#" + Long.toString(l,32));
  }

  /** Indicate that a substructure is starting to be read/written. */
  public void traceBegin(String s) {
    currentContextBroken = true;
    traceString(s);
    begun.push(s);
    ++indentSize;
    currentContextBroken = false;
  }

  /** Indicate that a substructure is completed. */
  public void traceEnd(String s) {
    --indentSize;
    if (!s.equals(begun.pop())) {
      traceString("end " + s);
    }
    currentContextBroken = true;
  }

  /** Flush the underlying Writer. */
  public void flush() {
    trace.flush();
  }

  /** Close the underlying Writer. */
  public void close() {
    trace.close();
  }
}

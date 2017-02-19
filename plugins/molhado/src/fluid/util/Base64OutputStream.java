package fluid.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import fluid.FluidError;

/** A class to convert binary data into regular textual information
 * that can be written to a Writer. This is a legal way to
 * convert a Writer into an OutputSTream. (The reverse being trivial.)
 * @see Base64InputStream
 */
public class Base64OutputStream extends OutputStream
{
  Writer writer;
  private int state;
  private int bits;
  private int chars;

  public static char PAD = '=';

  /** Create an output binary stream that writes in base 64
   * characters to the writer.
   */
  public Base64OutputStream(Writer w) {
    writer = w;
    state = 0;
    bits = 0;
    chars = 0;
  }

 // @Override
  public void write(int b) throws IOException
  {
    bits = bits | ((b&255) << state);
    state += 8;
    while (state >= 6) {
      int somebits = bits & 63;
      bits >>>= 6;
      state -= 6;
      writer.write(base64(somebits));
      ++chars;
    }
    if (chars >= 76) {
      writeCRLF();
    }
  }

 // @Override
  public void flush() throws IOException
  {
    // two cases:
    // we have 2 bits remaining (one byte read, one 6 bit char written)
    if (state == 2) {
      writer.write(base64(bits));
      writer.write(PAD);
      writer.write(PAD);
    } else if (state == 4) {
      // 2 bytes read and two 6 bit chars written
      writer.write(base64(bits));
      writer.write(PAD);
    } else if (state != 0) {
      throw new FluidError("Got out of sync somehow");
    }
    bits = 0;
    state = 0;
    writeCRLF();
    writer.flush();
  }

 // @Override
  public void close() throws IOException
  {
    flush();
    writer.close();
  }

  void writeCRLF() throws IOException {
    writer.write("\n");
    chars = 0;
  }

  int base64(int small) {
    if (small < 26) return 'A' + small;
    if (small < 52) return 'a' + (small - 26);
    if (small < 62) return '0' + (small - 52);
    switch (small) {
    case 62: return '+';
    case 63: return '/';
    default:
      throw new FluidError("Bug in Base64OutputStream");
    }
  }
}


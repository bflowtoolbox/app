package fluid.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * A stream which reads from an ASCII base 64 encoding of input. It follows RFC
 * 1341 and assumes no prologue or epilogue. If illegal characters are found,
 * or the reader does not consist of a multiple of 4 non-space bytes, a
 * {@link Base64StreamCorruptedException}is thrown.
 * 
 * @see Base64OutputStream
 */
public class Base64InputStream extends InputStream {
  private Reader reader;
  private int state;
  private int bits;
  //private static final Logger LOG = Logger.getLogger("FLUID.util");

  /**
	 * Create a binary input stream reading from base64 data from a reader.
	 */
  public Base64InputStream(Reader r) {
    reader = r;
    state = 0;
    bits = 0;
  }

 // @Override
  public int read() throws IOException {
    if (state < 8) {
      int pad = 0;
      for (int i = 0; i < 4; ++i) {
        int b64 = reader.read();
        if (b64 == -1) {
          if (i == 0)
            return -1;
          throw new Base64StreamCorruptedException("Stream ended abruptly");
        }
        if (b64 == Base64OutputStream.PAD)
          ++pad;
        int newbits = from64(b64);
        if (newbits < 0) {
          --i; // we didn't really read anything
          continue;
        }
        bits |= (newbits << state);
        state += 6;
      }
      switch (pad) {
        case 0 :
          break;
        case 1 :
          state -= 8;
          break;
        case 2 :
          state -= 16;
          break;
        default :
          throw new Base64StreamCorruptedException("too much padding");
      }
    }
    int b = bits & 255;
    bits >>= 8;
    state -= 8;
    return b;
  }

  //@Override
  public int available() {
    return state / 8;
  }

  int from64(int b64) throws IOException {
    if ('A' <= b64 && b64 <= 'Z')
      return b64 - 'A';
    if ('a' <= b64 && b64 <= 'z')
      return b64 - 'a' + 26;
    if ('0' <= b64 && b64 <= '9')
      return b64 - '0' + 52;
    switch (b64) {
      case '+' :
        return 62;
      case '/' :
        return 63;
      case '=' :
        return 0;
        // whitescape:
      case ' ' :
      case '\t' :
      case '\b' :
      case '\n' :
      case '\r' :
      case '\f' :
        return -1;
      default :
        throw new Base64StreamCorruptedException(
          "Unknown character: " + (char) b64);
    }
  }
}

class TestBase64 {
  public static String TEST_PHRASE = "squeamish ossifrage";

  public static void main(String[] args) throws IOException {
    QuickProperties.getInstance();
    if (args.length > 1) {
      System.err.println("usage: java TestBase64 [test-input]");
    }

    // first some internal sanity checks:

    int len = TEST_PHRASE.length();
    test_read(TEST_PHRASE);
    for (int i = 0; i < len; ++i) {
      test_skip_read(i, TEST_PHRASE);
    }

    if (args.length == 1) {
      if (args[0].equals("--encode")) {
        encode(System.in, System.out);
      } else if (args[0].equals("--decode")) {
        decode(System.in, System.out);
      } else {
        System.err.println("!! Error: do not understand flag: " + args[0]);
      }
    }
  }

  static void test_read(String test) throws IOException {
    StringWriter sw = new StringWriter();
    OutputStream os = new Base64OutputStream(sw);
    os.write(test.getBytes());
    os.close();
    String converted = sw.toString();
    // System.out.print("Converted " + test + " to " + converted);
    InputStream is = new Base64InputStream(new StringReader(converted));
    check_stream(is, test);
  }

  static void test_skip_read(int s, String test) throws IOException {
    byte[] bytes = test.getBytes();
    StringWriter sw = new StringWriter();
    OutputStream os = new Base64OutputStream(sw);
    if (s != bytes.length)
      os.write(bytes[0]);
    os.write(bytes, (s == bytes.length) ? 0 : 1, s);
    os.flush(); // just to cause problems
    for (int i = bytes.length - s - 1; i > 0; --i) {
      os.write(bytes[bytes.length - i]);
    }
    os.close();
    String converted = sw.toString();
    // System.out.print("Converted " + test + " to " + converted);
    // check it with simple reading:
    InputStream is = new Base64InputStream(new StringReader(converted));
    check_stream(is, test);
    //now check with input:
    is = new Base64InputStream(new StringReader(converted));
    long n = is.skip(2 * s);
    if (2 * s < test.length()) {
      int by = bytes[2 * s];
      by &= 255;
      int read = is.read();
      if (read != by) {
        System.err.println(
          "!! Error after skip("
            + 2 * s
            + ") = "
            + n
            + ": expected "
            + (char) by
            + " but read "
            + (char) read);
      }
    }
  }

  static void check_stream(InputStream is, String test) throws IOException {
    byte[] bytes = test.getBytes();
    // System.out.print("Reading: ");
    byte[] newbytes = new byte[bytes.length];
    for (int i = 0; i < bytes.length; ++i) {
      int by = is.read();
      if (by < 0) {
        // System.out.println();
        System.err.print("!! Error: input ended too soon");
        return;
      } else {
        newbytes[i] = (byte) by;
        // System.out.print((char)by);
      }
    }
    // System.out.println();
    String newString = new String(newbytes);
    if (!newString.equals(test))
      System.err.println(
        "!! Error: expected " + test + " but read " + newString);
  }

  static void encode(InputStream in, OutputStream out) throws IOException {
    InputStream is = new BufferedInputStream(in);
    OutputStream os =
      new Base64OutputStream(new BufferedWriter(new OutputStreamWriter(out)));
    copy(is, os);
  }

  static void decode(InputStream in, OutputStream out) throws IOException {
    InputStream is =
      new Base64InputStream(new BufferedReader(new InputStreamReader(in)));
    OutputStream os = new BufferedOutputStream(out);
    copy(is, os);
  }

  static void copy(InputStream is, OutputStream os) throws IOException {
    int by;
    while ((by = is.read()) >= 0)
      os.write(by);
    is.close();
    os.close();
  }
}


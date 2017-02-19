package fluid.util;

import java.io.IOException;

public class Base64StreamCorruptedException extends IOException
{
  public Base64StreamCorruptedException(String s) {
    super(s);
  }
}

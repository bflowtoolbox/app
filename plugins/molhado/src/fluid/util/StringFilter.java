/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/util/StringFilter.java,v 1.1 2006/03/21 23:20:54 dig Exp $ */

package fluid.util;

import java.io.PrintStream;

/**
 * Filters to manipulate String.
 *
 * @author Satish Chandra Gupta
 */
public class StringFilter {
  /**
   * Inserts esacpe character before each occurance of a char in given
   * string. A call like insertEscape("I am king", 'k', 'I') will return
   * "I am Iking".
   *
   * @param str         The string to be processed.
   * @param ch          The char to be escaped
   * @param escapeChar  The escape character
   * @return The string after inserting escape character
   * @see #removeEscape(java.lang.String, char, char)
   * @see #constructEscape(java.lang.String, char, char)
   */
  public static String insertEscape(final String str, char ch, char escapeCh) {
    StringBuffer result = new StringBuffer(str.length());
    int old_i, new_i;

    for (old_i=0, new_i=str.indexOf(ch);
         new_i>=0 && new_i<str.length();
         old_i=new_i+1, new_i=str.indexOf(ch,old_i)) {
      result.append(str.substring(old_i, new_i));
      result.append(escapeCh);
      result.append(ch);
    }
    result.append(str.substring(old_i));

    return result.toString();
  }

  /**
   * Removes each occurance of esacpe character before a char in given
   * string. Inverse of
   * {@link #insertEscape(java.lang.String, char, char) constructEscape}
   *
   * @param str         The string to be processed.
   * @param ch          The char to be escaped
   * @param escapeChar  The escape character
   * @return The string after removing escape characters
   * @see #insertEscape(java.lang.String, char, char)
   * @see #destructEscape(java.lang.String, char, char)
   */
  public static String removeEscape(final String str, char ch, char escapeCh) {
    final String escapeStr = new String() + escapeCh + ch;
    StringBuffer result = new StringBuffer(str.length());
    int old_i, new_i;

    for (old_i=0, new_i=str.indexOf(escapeStr);
         new_i>=0 && new_i<str.length();
         old_i=new_i+escapeStr.length(), new_i=str.indexOf(escapeStr,old_i)) {
      result.append(str.substring(old_i, new_i));
      result.append(ch);
    }
    result.append(str.substring(old_i));

    return result.toString();
  }

  /**
   * Constructs an esacped string by inserting escape character before each
   * occurance of given char in the string, and also escapes the escape 
   * charater. Therefore a call like constructEscape("I am king", 'k', 'I')
   * will return "II am Iking". Notice 'I' getting converted to "II".
   *
   * @param str         The string to be processed.
   * @param ch          The char to be escaped
   * @param escapeChar  The escape character
   * @return The escaped string
   * @see #destructEscape(java.lang.String, char, char)
   * @see #insertEscape(java.lang.String, char, char)
   */
  public static String constructEscape(final String str, char ch, char escapeCh) {
    String result = null;

    result = insertEscape(str, escapeCh, escapeCh);

    if (ch != escapeCh) // else already taken care of
      result = insertEscape(result, ch, escapeCh);

    return result;
  }

  public static String constructEscape(final String str, char[] chA, char escapeCh) {
    String result = null;

    result = insertEscape(str, escapeCh, escapeCh);

    for (int i=0; i < chA.length; i++)
      if (chA[i] != escapeCh) // else already taken care of
        result = insertEscape(result, chA[i], escapeCh);

    return result;
  }

  /**
   * Strips each occurance of esacpe character before a given char or escape
   * char itself in the given string. Inverse of
   * {@link #constructEscape(java.lang.String, char, char) constructEscape}
   *
   * @param str         The string to be processed.
   * @param ch          The char to be escaped
   * @param escapeChar  The escape character
   * @return The string after striping escape characters
   * @see #constructEscape(java.lang.String, char, char)
   * @see #removeEscape(java.lang.String, char, char)
   */
  public static String destructEscape(final String str, char ch, char escapeCh) {
    String result = str;

    if (ch != escapeCh) // else next call will take care of it.
      result = removeEscape(result, ch, escapeCh);

    result = removeEscape(result, escapeCh, escapeCh);

    return result;
  }

  public static String destructEscape(final String str, char[] chA, char escapeCh) {
    String result = str;

    for (int i=0; i < chA.length; i++)
      if (chA[i] != escapeCh) // else last call will take care of it.
        result = removeEscape(result, chA[i], escapeCh);

    result = removeEscape(result, escapeCh, escapeCh);

    return result;
  }

  /**
   * Tester
   */
  private static void tester(PrintStream s, String str,
                             char ch, char escapeCh) {
    String resStr = null;
    s.println("Source String  : " + str);

    resStr = insertEscape(str, ch, escapeCh);
    s.println("insertEscape   : " + resStr);
    resStr = removeEscape(resStr, ch, escapeCh);
    s.println("removeEscape   : " + resStr);
    if (str.compareTo(resStr) != 0) {
      s.println("ERROR ERROR");
    }

    resStr = constructEscape(str, ch, escapeCh);
    s.println("constructEscape: " + resStr);
    resStr = destructEscape(resStr, ch, escapeCh);
    s.println("destructEscape : " + resStr);
    if (str.compareTo(resStr) != 0) {
      s.println("ERROR ERROR");
    }
  }

  public static void main(String[] args) {
    char[] data = {'\\', '"'};
    String str = null;

    System.out.println("Char to be escaped  : '" + data[1] +"'");
    System.out.println("Escape char         : '" + data[0] +"'");
    System.out.println("--------------------------------------------------");

    System.out.println("Test Case #01  : string doesn't contain the char '" + data[1] + "'");
    str = "This string should not get changed after calls";
    tester(System.out, str, data[1], data[0]);
    System.out.println("--------------------------------------------------");

    System.out.println("Test Case #02  : string has the char '" + data[1]
                       + "' at begining and at the end");
    str = data[1] + "in between" + data[1];
    tester(System.out, str, data[1], data[0]);
    System.out.println("--------------------------------------------------");

    System.out.println("Test Case #03  : string has the char '" + data[1]
                       + "' and escape char '" + data[0] + "'also");
    str = data[1] + "escape char also " + data[0] + " end" + data[1];
    tester(System.out, str, data[1], data[0]);
    System.out.println("--------------------------------------------------");

    System.out.println("Test Case #04  : same as Test Case #03, except the escape char is at the begining and end");
    str = data[0] + "escape char also " + data[1] + " end" + data[0];
    tester(System.out, str, data[1], data[0]);
    System.out.println("--------------------------------------------------");
  }
}

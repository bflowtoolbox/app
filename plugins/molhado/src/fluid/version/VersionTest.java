// Kwan and Tien 's comment
package fluid.version;

import fluid.ir.Slot;
import fluid.ir.SlotUndefinedException;

/** Test versioning code.
 * @author John Tang Boyland
 */
public class VersionTest {

  static boolean verbose = false;

  public static void main(String[] args) {
    VersionedSlot.debugOn();
    Integer zero = new Integer(0);
    Integer one = new Integer(1);
    Integer two = new Integer(2);
    Integer three = new Integer(3);

    verbose = (args.length >= 1 && args[0].equals("--verbose"));

    Slot w = VersionedSlotFactory.prototype.predefinedSlot(null);
    Slot x = w;
    Slot y = w;
    Slot z = w;

    y = y.setValue(zero);

    Version v0 = Version.getVersion();

    x = x.setValue(one);
    w = w.setValue(zero);
    Version v1 = Version.getVersion();

    w = w.setValue(one);
    Version v11 = Version.getVersion();

    if (verbose)
      System.out.println("Early version tests:");
    Slot[] slots = new Slot[] { w, x, y, z };

    test("v0", v0, slots, new Object[] { null, null, zero, null });
    test("v1", v1, slots, new Object[] { zero, one, zero, null });
    test("v11", v11, slots, new Object[] { one, one, zero, null });

    if (verbose)
      System.out.println("Early testing done.");

    Version.setVersion(v1);
    w = w.setValue(two);
    Version v12 = Version.getVersion();

    y = y.setValue(one);
    Version v121 = Version.getVersion();

    Version.setVersion(v12);
    x = x.setValue(two);
    Version v122 = Version.getVersion();

    if (verbose)
      System.out.println("Mid-construction tests:");
    slots = new Slot[] { w, x, y, z };

    test("v0", v0, slots, new Object[] { null, null, zero, null });
    test("v1", v1, slots, new Object[] { zero, one, zero, null });
    test("v11", v11, slots, new Object[] { one, one, zero, null });
    test("v12", v12, slots, new Object[] { two, one, zero, null });
    test("v121", v121, slots, new Object[] { two, one, one, null });
    test("v122", v122, slots, new Object[] { two, two, zero, null });

    if (verbose)
      System.out.println("Mid-construction testing done.");

    Version.setVersion(v0);
    y = y.setValue(two);
    z = z.setValue(zero);
    Version v2 = Version.getVersion();

    y = y.setValue(three);
    Version v21 = Version.getVersion();

    Version.setVersion(v2);
    z = z.setValue(one);
    Version v22 = Version.getVersion();

    z = z.setValue(two);
    Version v221 = Version.getVersion();

    Version.setVersion(v22);
    x = x.setValue(three);
    Version v222 = Version.getVersion();

    if (verbose) {
      System.out.println("Version tree constructed.");
      Version.printVersionTree();
    }

    if (verbose)
      System.out.println("Final version tests:");

    slots = new Slot[] { w, x, y, z };

    test("v0", v0, slots, new Object[] { null, null, zero, null });
    test("v1", v1, slots, new Object[] { zero, one, zero, null });
    test("v2", v2, slots, new Object[] { null, null, two, zero });
    test("v11", v11, slots, new Object[] { one, one, zero, null });
    test("v12", v12, slots, new Object[] { two, one, zero, null });
    test("v21", v21, slots, new Object[] { null, null, three, zero });
    test("v22", v22, slots, new Object[] { null, null, two, one });
    test("v121", v121, slots, new Object[] { two, one, one, null });
    test("v122", v122, slots, new Object[] { two, two, zero, null });
    test("v221", v221, slots, new Object[] { null, null, two, two });
    test("v222", v222, slots, new Object[] { null, three, two, one });

    testlca(
      new String[] {
        "0",
        "1",
        "11",
        "12",
        "121",
        "122",
        "2",
        "21",
        "22",
        "221",
        "222" },
      new Version[] { v0, v1, v11, v12, v121, v122, v2, v21, v22, v221, v222 },
      new Version[][] {
        new Version[] { v0, v0, v0, v0, v0, v0, v0, v0, v0, v0, v0 },
        new Version[] { v0, v1, v1, v1, v1, v1, v0, v0, v0, v0, v0 },
        new Version[] { v0, v1, v11, v1, v1, v1, v0, v0, v0, v0, v0 },
        new Version[] { v0, v1, v1, v12, v12, v12, v0, v0, v0, v0, v0 },
        new Version[] { v0, v1, v1, v12, v121, v12, v0, v0, v0, v0, v0 },
        new Version[] { v0, v1, v1, v12, v12, v122, v0, v0, v0, v0, v0 },
        new Version[] { v0, v0, v0, v0, v0, v0, v2, v2, v2, v2, v2 },
        new Version[] { v0, v0, v0, v0, v0, v0, v2, v21, v2, v2, v2 },
        new Version[] { v0, v0, v0, v0, v0, v0, v2, v2, v22, v22, v22 },
        new Version[] { v0, v0, v0, v0, v0, v0, v2, v2, v22, v221, v22 },
        new Version[] { v0, v0, v0, v0, v0, v0, v2, v2, v22, v22, v222 }
    });

    testPrecedes(
      new String[] {
        "0",
        "2",
        "22",
        "222",
        "221",
        "21",
        "1",
        "12",
        "122",
        "121",
        "11" },
      new Version[] { v0, v2, v22, v222, v221, v21, v1, v12, v122, v121, v11 });

    if (verbose)
      System.out.println("Version testing done.");
    testGetNextInPreorder(
      new String[] {
        "0",
        "2",
        "22",
        "222",
        "221",
        "21",
        "1",
        "12",
        "122",
        "121",
        "11" },
      new Version[] { v0, v2, v22, v222, v221, v21, v1, v12, v122, v121, v11 });

    if (verbose)
      VersionedSlot.listing(100);
  }

  public static void test(
    String testname,
    Version v,
    Slot[] slot,
    Object[] value) {
    Version.setVersion(v);
    for (int i = 0; i < slot.length; ++i) {
      try {
        Object val = slot[i].getValue();
        if (val == null && value[i] != null) {
          System.out.println(
            "!! Null returned for test " + testname + " for slot #" + i);
        } else if (value[i] == null && val != null) {
          System.out.println(
            "!! Value returned for test " + testname + " for slot #" + i);
        } else if (val != value[i]) {
          System.out.println(
            "!! Wrong value for test " + testname + " for slot #" + i);
        }
      } catch (SlotUndefinedException e) {
        if (value[i] != null) {
          System.out.println(
            "!! No value for test " + testname + " for slot #" + i);
          if (slot[i] instanceof VersionedSlot) {
            ((VersionedSlot) slot[i]).describe(System.out);
          }
          e.printStackTrace();
        }
      }
    }
  }

  public static void testlca(
    String[] names,
    Version[] versions,
    Version[][] lca) {
    int n = names.length;
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; ++j) {
        if (i != j && versions[i] == versions[j])
          System.out.println(
            "!! Versions are the same: v" + names[i] + " and v" + names[j]);
      }
    }
    if (verbose)
      System.out.println("Testing latestCommonAncestor:");
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; ++j) {
        // remove the following printstatement when lca terminates:
        // System.out.println("Testing latestCommonAncestor(v" + names[i] +
        //		   ",v" + names[j] + ") ...");
        Version v = Version.latestCommonAncestor(versions[i], versions[j]);
        int k = findVersion(v, versions);
        if (v != lca[i][j])
          System.out.println(
            "!! latestCommonAncestor(v"
              + names[i]
              + ",v"
              + names[j]
              + ") is wrong"
              + ((k < 0) ? "" : (" (v" + names[k] + ")")));
      }
    }
  }

  public static void testPrecedes(String[] names, Version[] versions) {
    int n = names.length;
    if (verbose)
      System.out.println("Testing Precedes:");
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; ++j) {
        boolean b = versions[i].precedes(versions[j]);
        if ((b && i >= j) || (!b && i < j)) {
          System.out.println(
            "!! v"
              + names[i]
              + "Precedes(v"
              + names[j]
              + ") is wrong:"
              + (b ? "false" : "true"));
        }
      }
    }
  }

  public static void testGetNextInPreorder(
    String[] names,
    Version[] versions) {
    int n = names.length;
    if (verbose)
      System.out.println("Testing getNextInPreorder:");
    for (int i = 0; i < n; i++) {
      Version v = versions[i].getNextInPreorder();
      if ((i == n - 1 && v != null)) {
        System.out.println(
          "!! v" + names[i] + ".getNextInPreorder()" + " is wrong:NonNull");
      }
      if ((i < n - 1) && !v.equals(versions[i + 1])) {
        int j = findVersion(v, versions);
        System.out.println(
          "!! v" + names[i] + ".getNextInPreorder()" + " is wrong:" + names[j]);
      }
    }
  }

  public static int findVersion(Version v, Version[] versions) {
    for (int i = versions.length - 1; i >= 0; --i)
      if (versions[i] == v)
        return i;
    return -1;
  }
}

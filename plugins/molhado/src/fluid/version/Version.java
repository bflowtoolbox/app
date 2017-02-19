/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/version/Version.java,v 1.2 2006/06/01 18:44:26 dig Exp $ */
package fluid.version;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

import fluid.FluidError;
import fluid.FluidRuntimeException;
import fluid.ir.IRNode;
import fluid.ir.IRPersistent;
import fluid.ir.IRPersistentObserver;
import fluid.ir.IRRegion;
import fluid.ir.MarkedIRNode;
import fluid.ir.SimpleSlotFactory;
import fluid.ir.SimpleSlotInfo;
import fluid.ir.SlotAlreadyRegisteredException;
import fluid.ir.SlotInfo;
import fluid.tree.Tree;
import fluid.tree.TreeInterface;
import fluid.util.ThreadGlobal;

/**
 * The representation of nodes in the global version tree. Also the arbiter of
 * the current version. IRPersistentObservers are informed when the version is
 * an assigned an era.
 */

public class Version implements Serializable {
	/**
	 * Log4j logger for this class
	 */
	protected static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
			.getLogger("IR.version");

	private static final Tree versionShadowTree;

	private static final SlotInfo versionShadowVersionAttribute;

	private/* final */
	IRNode shadowNode;
	static {
		Tree vst = null;
		SlotInfo vsva = null;
		try {
			vst = new Tree("fluid.version", SimpleSlotFactory.prototype);
			vsva = new SimpleSlotInfo("fluid.version.Version", null);
		} catch (SlotAlreadyRegisteredException ex) {
		}
		versionShadowTree = vst;
		versionShadowVersionAttribute = vsva;
	}

	private void addShadow(Version v) {
		// LOG.info("Creating shadow for version "+v, new Throwable())'
		LOG.debug("Creating shadow for version " + v + " as child of "
				+ v.parent);
		/*
		 * if (v.parent == null) { LOG.warn("parent is null at:", new
		 * Throwable()); }
		 */
		IRNode n = new MarkedIRNode(null, "versionShadow " + v);
		v.shadowNode = n;
		n.setSlotValue(versionShadowVersionAttribute, v);
		versionShadowTree.initNode(n, -1); // add after shadow connection made
		Version p = v.parent;
		if (p != null) {
			versionShadowTree.appendChild(p.shadowNode, n);
		}
	}

	public static TreeInterface getShadowTree() {
		return versionShadowTree;
	}

	public IRNode getShadowNode() {
		return shadowNode;
	}

	public static Version getShadowVersion(IRNode node) {
		Version v = (Version) node.getSlotValue(versionShadowVersionAttribute);
		++v.refCount;
		return v;
	}

	private static int totalVersions = 0;

	private static final Version firstVersion = new Version(null);

	private static ThreadGlobal current = new ThreadGlobal(firstVersion);

	private static ThreadGlobal defaultEra = new ThreadGlobal(null);

	private final int id; // Unique Identifier for each version

	private final int depth; // Absolute depth in version tree

	private final Version parent; // Prior version

	private int refCount; // if zero then can modify

	private Vector children = new Vector(); // Next versions

	private int childrank; // position in parents children

	// array
	private final Version DynastyFounder; // Groups versions by first

	// descendant Dynasties
	private Era era = null;

	private int eraOffset = 0;

	private Vector cursors; // null or cursors tracking this

	private int clamps; // number of outstanding

	// clamps forbidding changes
	private ArrayList associated; // associated persistent objects

	/**
	 * Creates a version with prior as its parent
	 */
	private Version(Version prior) {
		parent = prior;
		id = totalVersions;
		totalVersions++;
		addShadow(this);

		if (prior == null) {
			DynastyFounder = this;
			depth = 0;
			childrank = 0;
			refCount = 1;
		} else
			synchronized (prior) {
				if (prior.children.size() > 0) {
					DynastyFounder = this;
				} else {
					DynastyFounder = prior.DynastyFounder;
				}
				childrank = prior.children.size();
				prior.children.addElement(this);
				depth = prior.depth + 1;
				if (prior.cursors != null) {
					cursors = prior.cursors;
					prior.cursors = null;
					for (Enumeration en = cursors.elements(); en
							.hasMoreElements();) {
						VersionCursor vc = (VersionCursor) en.nextElement();
						vc.moveCursor(this);
					}
				}
				Era def = (Era) defaultEra.getValue();
				if (def != null && def.isNew() && !def.isComplete()
						&& (prior == def.getRoot() || prior.era == def)) { // OK
																			// to
																			// use
																			// default
					try {
						def.addVersion(this);
					} catch (OverlappingEraException willNotHappen) {
						throw new RuntimeException(
								"assertion failure in Version");
					}
				}
			}
	}

	static Version createVersion(Version parent) {
		Version v = new Version(parent);
		v.freeze();
		return v;
	}

	public Object writeReplace() {
		return new VersionWrapper(this);
	}

	public static void setDefaultEra(Era e) {
		defaultEra.setValue(e);
	}

	public static Era getDefaultEra() {
		return (Era) defaultEra.getValue();
	}

	public static void pushDefaultEra(Era e) {
		defaultEra.pushValue(e);
	}

	public static Era popDefaultEra() {
		return (Era) defaultEra.popValue();
	}

	synchronized void setEra(Era e, int offset) throws OverlappingEraException {
		if (era != null)
			throw new OverlappingEraException(this + " already has era");
		era = e;
		eraOffset = offset;
		era.getShadowRegion().saveNode(shadowNode);
		if (IRRegion.getOwnerIndex(shadowNode) != offset) {
			System.err.println("For " + this + " era offset=" + offset
					+ ", but index = " + IRRegion.getOwnerIndex(shadowNode));
			throw new FluidError("assertion failed: era offset != shadow index");
		}
		if (associated != null) {
			for (Iterator it = associated.iterator(); it.hasNext();)
				era.addAssociated((IRPersistent) it.next());
			associated = null;
		}
		if (observers != null) {
			for (Iterator it = observers.iterator(); it.hasNext();)
				((IRPersistentObserver) it.next()).updatePersistent(era, this);
			observers = null;
		}
	}

	ArrayList observers = new ArrayList();

	private long checkInTime;

	public synchronized void addPersistentObserver(IRPersistentObserver o) {
		if (era != null)
			o.updatePersistent(era, this);
		else if (!observers.contains(o))
			observers.add(o);
	}

	public Era getEra() {
		if (era == null && this == firstVersion)
			return Era.getInitialEra();
		return era;
	}

	public int getEraOffset() {
		return eraOffset;
	}

	public void addAssociated(IRPersistent p) {
		if (era != null)
			era.addAssociated(p);
		if (associated == null)
			associated = new ArrayList();
		if (!associated.contains(p))
			associated.add(p);
	}

	/** Write version to file. Always relative to an era. */
	public void write(DataOutput out) throws IOException {
		out.writeShort(eraOffset);
	}

	/**
	 * Write a marker to the file that when read will be the root version of the
	 * current era.
	 */
	public static void writeRootVersion(DataOutput out) throws IOException {
		out.writeShort(0);
	}

	/** Read version from file. Always relative to an era. */
	public static Version read(DataInput in, Era era) throws IOException {
		int i = in.readShort();
		return era.getVersion(i);
	}

	public void print() {
		System.out.println(id);
	}

	public String toString() {
		if (getEra() != null) {
			return getEra().toString() + " v" + getEraOffset();
		} else {
			return "V" + id;
		}
	}

	public void printNoCr() {
		System.out.print(id);
	}

	static void printCurrent() {
		getVersionLocal().print();
	}

	public static void printVersionTree() {
		printVersionTree(0, firstVersion);
	}

	public static void printVersionTree(int indent, Version toPrint) {
		for (int i = indent; i > 0; i--)
			System.out.print(" ");

		toPrint.print();

		for (int i = 0; i < toPrint.children.size(); i++)
			printVersionTree(indent + 1, (Version) (toPrint.children
					.elementAt(i)));
	}

	/**
	 * Return the parent version of this version. Should be used only to
	 * traverse version tree, not to make some conclusions about the
	 * existence/non-existence of versions in a line of changes.
	 */
	public Version parent() {
		return parent;
	}

	int depth() {
		return depth;
	}

	void addCursor(VersionCursor vc) {
		if (cursors == null)
			cursors = new Vector();
		cursors.addElement(vc);
	}

	void removeCursor(VersionCursor vc) {
		cursors.removeElement(vc);
	}

	public static void printVersionStack() {
		System.out.println(current);
	}

	public static void printDebuggingInfo() {
		VersionedSlot.listing(100);
	}

	public static int getTotalVersions() {
		return totalVersions;
	}

	static Version getVersionLocal() {
		return (Version) current.getValue();
	}

	void freeze() {
		if (clamps > 0 && refCount == 0) {
			throw new FluidRuntimeException(
					"clamped liquid version is being frozen");
		}
		refCount |= 65536; // allow up to 64K mark and releases
	}

	boolean isFrozen() {
		return refCount > 0;
	}

	public static Version getVersion() {
		Version cv = getVersionLocal();
		cv.freeze();
		return cv;
	}

	public boolean isCurrent() {
		return this.equals(getVersionLocal());
	}

	void mark() {
		if (clamps > 0 && refCount < 10)
			System.err.println("Marking clamped version");
		++refCount;
	}

	/**
	 * Decrement reference count. Safe only in a few situations: for instance
	 * when an enumeration reaches the end. Such a version cannot have any more
	 * than one child, or else someone snuck out a version. This method is not
	 * public because otherwise it would be abused.
	 */
	void release() {
		if (refCount != 65536)
			--refCount; // never clear frozen bit
		if (clamps > 0 && refCount < 10)
			System.err.println("Releasing clamped version");
	}

	public static Version getInitialVersion() {
		return firstVersion;
	}

	public static void setVersion(Version v) {
		current.setValue(v);
	}

	/**
	 * Save the current version, so that it can be restored after a version
	 * change. A save/restore in client code differs from code which gets and
	 * then sets because it doesn't freeze the current version.
	 * 
	 * @see #restoreVersion()
	 */
	public static void saveVersion() {
		current.pushValue(current.getValue());
	}

	public static void saveVersion(Version v) {
		current.pushValue(current.getValue());
		current.setValue(v);
	}

	/**
	 * Restore a previously saved version.
	 * 
	 * @see #saveVersion()
	 */
	public static void restoreVersion() {
		current.popValue();
	}

	/**
	 * Forbid modifications to values of the version. Used for debugging.
	 */
	public void clamp() {
		++clamps;
	}

	/**
	 * Permit modifications to values of the version. Used for debugging.
	 */
	public void unclamp() {
		--clamps;
	}

	public static void clampCurrent() {
		getVersionLocal().clamp();
	}

	public static void unclampCurrent() {
		getVersionLocal().unclamp();
	}

	/**
	 * Notify the versioning system that a change has happened, potentially
	 * requiring a new version. If the current version is not visible, it can be
	 * reused.
	 */
	public static void bumpVersion() {
		Version cv = (Version) current.getValue();
		if (!cv.isFrozen()) {
			if (cv.cursors != null) {
				int n = cv.cursors.size();
				for (int i = 0; i < n; ++i) {
					((VersionCursor) cv.cursors.elementAt(i)).notifyObservers();
				}
			}
			return;
		}
		/* permit us to debug things that are not supposed to change version */
		if (cv.clamps > 0)
			throw new FluidRuntimeException("no modifications allowed");
		current.setValue(new Version(cv));
	}

	/**
	 * A total order on versions that is consistent with ancestor ordering. A
	 * version is always less than any of its descendants. This ordering is not
	 * preserved peristently.
	 */
	boolean lessThanEq(Version other) {
		return (other.id <= this.id);
	}

	/** True if the argument is an ancestor or equal to this version. */
	public boolean comesFrom(Version ancestor) {
		Version lca = latestCommonAncestor(this, ancestor);
		if (ancestor.equals(lca)) {
			return true;
		}
		return false;
	}

	/**
	 * Compute a slot for a specific version.
	 * 
	 * @precondition nonNull(node) && nonNull(si)
	 */
	public Object fetchSlotValue(IRNode node, SlotInfo si) {
		Version v = getVersionLocal();
		try {
			setVersion(this);
			return node.getSlotValue(si);
		} finally {
			setVersion(v);
		}
	}

	/**
	 * Find the common ancestor which is deepest in the version tree for two
	 * versions. Ie, find the root of the minimum version subtree containing
	 * both versions.
	 * 
	 * @precondition nonNull(first) && nonNULL(second)
	 */
	static public Version latestCommonAncestor(Version first, Version second) {
		while (first.DynastyFounder != second.DynastyFounder) {
			if (first.DynastyFounder.depth > second.DynastyFounder.depth) {
				first = first.DynastyFounder.parent;
			} else {
				second = second.DynastyFounder.parent;
			}
		}
		if (first.depth > second.depth) {
			return second;
		} else {
			return first;
		}
	}

	/**
	 * Return true if this version is "between" two other versions, that is
	 * represents changes that potentially cause two versions to differ.
	 * Mathematically, it is between if it is the ancestor of one but not both
	 * versions.
	 */
	public boolean isBetween(Version v1, Version v2) {
		return v1.comesFrom(this) ^ v2.comesFrom(this);
	}

	Version getNextInPreorderNoKids() {
		return DynastyFounder.getNextOlderSibling();
	}

	Version getNextInPreorder() {
		if (children.size() > 0) {
			return (Version) children.elementAt(children.size() - 1);
		}

		return getNextInPreorderNoKids();
	}

	private Version getNextOlderSibling() {
		if (childrank < 1) {
			return null;
		}
		return (Version) parent.children.elementAt(childrank - 1);
	}

	boolean precedes(Version second) {
		return precedes(this, second);
	}

	/**
	 * Determine which version precedes the over in a preorder of the global
	 * version tree
	 * 
	 * @precondition nonNull(first) && nonNull(second)
	 */

	static boolean precedes(Version first, Version second) {
		if (first.equals(second)) {
			return false;
		}
		if (first.DynastyFounder.equals(second.DynastyFounder)) {
			if (first.depth < second.depth) {
				return true;
			} else {
				return false;
			}
		}
		Version temp1 = first;
		Version trace1 = first;
		Version temp2 = second;
		Version trace2 = second;

		while (temp1.DynastyFounder != temp2.DynastyFounder) {
			if (temp1.DynastyFounder.depth > temp2.DynastyFounder.depth) {
				trace1 = temp1.DynastyFounder;
				temp1 = trace1.parent;
			} else {
				trace2 = temp2.DynastyFounder;
				temp2 = trace2.parent;
			}
		}

		if (temp1.depth < temp2.depth) {
			trace2 = temp2;
			temp2 = temp1;
		}
		if (temp1.depth > temp2.depth) {
			trace1 = temp1;
			temp1 = temp2;
		}
		Version commonAncestor = temp1;

		if (first == commonAncestor) {
			return true;
		}
		if (second == commonAncestor) {
			return false;
		}

		if (trace2.DynastyFounder == commonAncestor.DynastyFounder) {
			return true;
		}
		if (trace1.DynastyFounder == commonAncestor.DynastyFounder) {
			return false;
		}

		if (trace1.childrank > trace2.childrank) {
			return true;
		} else {
			return false;
		}
	}

	// controversial:

	// we may want some way to package compound mutations into
	// units and (possibly) simplify the version space.
	// This code also presumes some things about versions.

	// It may be cleaner to have m.getTransaction return t, rather
	// that having a second parameter.

	// I'm completely out of my depth in this... (John)
	/*
	 * public static void performMutation(Mutation m, Transaction t) { Version v =
	 * getVersionLocal(); t.record_first_version(bumpVersion()); try {
	 * m.activate(); } catch (Throwable e) { setVersion(v); throw e; }
	 * t.record_last_version(getVersionLocal()); bumpVersion();
	 * getVersionLocal().setPrevVersion(v,t); }
	 */
	// gobs more versioning stuff.
	public static void ensureLoaded() {
		IRVersionType.ensureLoaded();
	}

	public long getCheckInTime() {
		return checkInTime;
	}
	
	public void setCheckInTime(long time) {
		 checkInTime= time;
	}
}

class VersionWrapper implements Serializable {
	private transient Version version;

	VersionWrapper(Version v) {
		version = v;
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		if (version == Version.getInitialVersion()) {
			out.writeInt(0);
		} else {
			Era era = version.getEra();
			if (era == null)
				throw new NotSerializableException(
						"versions must have eras to be serialized");
			out.writeInt(version.getEraOffset());
			era.writeReference(out);
		}
	}

	private void readObject(ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		int offset = in.readInt();
		if (offset == 0)
			version = Version.getInitialVersion();
		else {
			Era era = (Era) IRPersistent.readReference(in);
			version = era.getVersion(offset);
		}
	}

	public Object readResolve() {
		return version;
	}
}

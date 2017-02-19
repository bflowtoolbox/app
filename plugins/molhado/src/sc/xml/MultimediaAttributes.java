/* $Header: /usr/local/refactoring/molhadoRef/src/sc/xml/MultimediaAttributes.java,v 1.1 2006/03/21 23:21:00 dig Exp $ */

package sc.xml;

import java.io.File;
import java.io.IOException;

import sc.document.MultimediaChanged;
import fluid.FluidError;
import fluid.ir.Bundle;
import fluid.ir.IRNode;
import fluid.ir.IRPersistent;
import fluid.ir.IRStringType;
import fluid.ir.SlotAlreadyRegisteredException;
import fluid.ir.SlotInfo;
import fluid.java.JavaNode;
import fluid.util.FileLocator;
import fluid.util.UniqueID;
import fluid.version.VersionedSlotFactory;

public class MultimediaAttributes {
  private static Bundle multimediabundle;
  static {
    MultimediaAttributesBoot.ensureLoaded();
    try {
      multimediabundle
          = Bundle.loadBundle(UniqueID.parseUniqueID("multimedia"),
                              IRPersistent.fluidFileLocator);
    } catch (IOException ex) {
      System.out.println(ex.toString());
    }
  }

  public static Bundle getBundle() {
    return multimediabundle;
  }


  public static SlotInfo[] getAttributeSlotInfos() {
    return MultimediaAttributesBoot.multimediaAttrs;
  }

  // Image

  public static SlotInfo getImageAttributeSlotInfo() {
    return MultimediaAttributesBoot.multimediaAttrs[0];
  }

  public static String getImage(IRNode n) {
    return (String) n.getSlotValue(getImageAttributeSlotInfo());
  }

  public static void setImage(IRNode n, String s) {
    n.setSlotValue(getImageAttributeSlotInfo(), s);
  }

  // Audio

  public static SlotInfo getAudioAttributeSlotInfo() {
    return MultimediaAttributesBoot.multimediaAttrs[1];
  }

  public static String getAudio(IRNode n) {
    return (String) n.getSlotValue(getAudioAttributeSlotInfo());
  }

  public static void setAudio(IRNode n, String s) {
    n.setSlotValue(getAudioAttributeSlotInfo(), s);
  }

  // Graphics

  public static SlotInfo getGraphicsAttributeSlotInfo() {
    return MultimediaAttributesBoot.multimediaAttrs[2];
  }

  public static String getGraphics(IRNode n) {
    return (String) n.getSlotValue(getGraphicsAttributeSlotInfo());
  }

  public static void setGraphics(IRNode n, String s) {
    n.setSlotValue(getGraphicsAttributeSlotInfo(), s);
  }

  // Hyperlink

  public static SlotInfo getTargetAttributeSlotInfo() {
      return MultimediaAttributesBoot.multimediaAttrs[3];
  }

  public static String getTarget(IRNode n) {
    return (String) n.getSlotValue(getTargetAttributeSlotInfo());
  }

  public static void setTarget(IRNode n, String s) {
    n.setSlotValue(getTargetAttributeSlotInfo(), s);
  }

  public static SlotInfo getAnchorAttributeSlotInfo() {
      return MultimediaAttributesBoot.multimediaAttrs[4];
  }

  public static String getAnchor(IRNode n) {
    return (String) n.getSlotValue(getAnchorAttributeSlotInfo());
  }

  public static void setAnchor(IRNode n, String s) {
    n.setSlotValue(getAnchorAttributeSlotInfo(), s);
  }
 
  public static MultimediaChanged getMultimediaChanged() {
  	return MultimediaAttributesBoot.multimediaChanged;
  }

  // Test driver

  public static void main(String args[]) {
    Bundle b = getBundle();

    if (b != null)
      b.describe(System.out);
    else
      System.err.println("Multimedia bundle is NULL.");
  }
}


class MultimediaAttributesBoot {

  // Multimedia Attributes

  static final SlotInfo[] multimediaAttrs;
  static MultimediaChanged multimediaChanged;
  static {
    try {
      // Multimedia attributes
      multimediaAttrs = new SlotInfo[5];
      multimediaAttrs[0] = VersionedSlotFactory.makeSlotInfo("scdoc.image",
                                                   IRStringType.prototype, "");
      multimediaAttrs[1] = VersionedSlotFactory.makeSlotInfo("scdoc.audio",
                                                   IRStringType.prototype, "");
      multimediaAttrs[2] = VersionedSlotFactory.makeSlotInfo("scdoc.graphics",
                                                   IRStringType.prototype, "");
      multimediaAttrs[3] = VersionedSlotFactory.makeSlotInfo("scdoc.target",
                                                   IRStringType.prototype, "");
      multimediaAttrs[4] = VersionedSlotFactory.makeSlotInfo("scdoc.anchor",
                                                   IRStringType.prototype, "");
      
      multimediaChanged = new MultimediaChanged("scdoc.multimedia", fluid.java.JavaNode.tree);
			for (int i = 0; i < multimediaAttrs.length; i++)
				multimediaAttrs[i].addDefineObserver(multimediaChanged);
			
			JavaNode.commentSlotInfo.addDefineObserver(multimediaChanged);
			
    } catch (SlotAlreadyRegisteredException e) {
      throw new FluidError("Multimedia slot already registered " + e);
    }
  }

  static void saveMultimediaAttributes(Bundle b) {
    for (int i=0; i<multimediaAttrs.length; i++)
      b.saveAttribute(multimediaAttrs[i]);
  }

  public static void ensureLoaded() {
    // Don't you think that it does nothing? 
    // well, it ensures that 'static' block get executed.
  }

  public static void main(String args[]) {
    Bundle b = new Bundle();
    FileLocator floc = IRPersistent.fluidFileLocator;
    saveMultimediaAttributes(b);
		b.saveAttribute(multimediaChanged);
		
    try {
      b.store(floc);
    } catch (IOException ex) {
      System.out.println(ex.toString());
      System.out.println("Please press return to try again");
      try {
        System.in.read();
        b.store(floc);
      } catch (IOException ex2) {
        System.out.println(ex2.toString());
        return;
      }
    }

    System.err.println("Creating multimedia bundle....");
    new File(b.getID().toString()+".ab").renameTo(new File("multimedia.ab"));
  }
}

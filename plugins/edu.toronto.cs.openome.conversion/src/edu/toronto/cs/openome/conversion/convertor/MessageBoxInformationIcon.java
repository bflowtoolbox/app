package edu.toronto.cs.openome.conversion.convertor;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import edu.toronto.cs.openome.conversion.ccistarml.ERelement;
import edu.toronto.cs.openome.conversion.ccistarml.ERelementList;
import edu.toronto.cs.openome.conversion.ccistarml.ccistarmlFile;
import edu.toronto.cs.openome_model.Container;
import edu.toronto.cs.openome_model.Contribution;
import edu.toronto.cs.openome_model.Decomposition;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Link;
import edu.toronto.cs.openome_model.Model;
import edu.toronto.cs.openome_model.openome_modelFactory;
import edu.toronto.cs.openome_model.openome_modelPackage;
import edu.toronto.cs.openome_model.impl.ContainerImpl;
import edu.toronto.cs.openome_model.impl.IntentionImpl;

public class MessageBoxInformationIcon {

	public MessageBoxInformationIcon(String info) {
	    Shell shell = new Shell(Display.getCurrent());

	    int style = SWT.ICON_INFORMATION;
	    
	   
	    MessageBox messageBox = new MessageBox(shell, style);
	    messageBox.setMessage(info);
	    int rc = messageBox.open();

	    switch (rc) {
	    case SWT.OK:
	      System.out.println("SWT.OK");
	      break;
	    case SWT.CANCEL:
	      System.out.println("SWT.CANCEL");
	      break;
	    case SWT.YES:
	      System.out.println("SWT.YES");
	      break;
	    case SWT.NO:
	      System.out.println("SWT.NO");
	      break;
	    case SWT.RETRY:
	      System.out.println("SWT.RETRY");
	      break;
	    case SWT.ABORT:
	      System.out.println("SWT.ABORT");
	      break;
	    case SWT.IGNORE:
	      System.out.println("SWT.IGNORE");
	      break;
	    }
	    
	  }
	
}

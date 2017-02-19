package action;

import java.util.Vector;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.osgi.framework.debug.Debug;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ISetSelectionTarget;

import edu.toronto.cs.openome.core.action.ExtensionReader;
import edu.toronto.cs.openome.core.convertor.IConvertor;
import edu.toronto.cs.openome_model.diagram.part.Openome_modelInitDiagramFileAction;

public class ConvertAction extends ExtensionReader implements IObjectActionDelegate {

	String from;
	String to;
	IWorkbenchPart targetPart;
	
	/**
	 * Constructor for XMIAction.
	 */
	public ConvertAction(String from, String to) {
		super();
		this.from = from;
		this.to = to;
	}
	
	protected void performConversion(IConfigurationElement convertor, String[] args) {
		String input = convertor.getAttribute("input");
		String output = convertor.getAttribute("output");
		//String classname = convertor.getAttribute("class");
		if (args[0].endsWith(input) && args[1].endsWith(output)) {
			try {
//			  System.out.println(classname);
//			  Debug.DEBUG_LOADER = true;
			  IConvertor cv = (IConvertor) convertor.createExecutableExtension("class");
			  Debug.DEBUG_LOADER = false;
		      cv.convert(args[0], args[1]);
			} catch (ClassCastException x) {
				x.printStackTrace();
			} catch (CoreException e) {
				e.printStackTrace();
			} catch (Exception a) {
				a.printStackTrace();
			}
		}
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.targetPart = targetPart;
	}
    /**
     * @see the following for how to write extension points in an Eclipse plugin
     * http://help.eclipse.org/help21/index.jsp?topic=/org.eclipse.platform.doc.isv/guide/arch_extension_points.htm
     * http://udig.refractions.net/confluence/display/DEV/1+Creating+and+Using+Extension+Points
     * http://franksauer.blogspot.com/2005_03_01_franksauer_archive.html
     */
	protected Vector<IConfigurationElement> convertors = null;
	StructuredSelection ts = null;
	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		if (ts == null) {
			return;
		}
		Object tp [] = ts.toArray();
		for (int i=0; i< tp.length; i++) {
			IFile f = (IFile) tp[i];
			String args [] = new String[2];
			if (f.exists()) {
				String stem = f.getLocation().removeFileExtension().toOSString();
				args[0] = stem + from;
				args[1] = stem + to;
				convertors = new Vector<IConfigurationElement>();
				readExtensions("core.convertor", "convertor", convertors);
				if (args.length<2) {
					System.exit(1);
				}
				for (IConfigurationElement convertor: convertors)
					performConversion(convertor, args);
			}
			try {
				f.getProject().refreshLocal(2, null);
			} catch (CoreException e) {
				e.printStackTrace();
			}
			String name = f.getProjectRelativePath().toString();
			String stem1 = name.substring(0, name.lastIndexOf("."));
			String new_name = stem1 + to;
			IFile f2 = f.getProject().getFile(new_name);
			if (f2.exists()) {
				if (to.equals(".oom"))
						toModel(f2);
				//openTheEditor(f2);
			}
		}
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		ts = (StructuredSelection) selection;
	}

	/**
	 * Select the converted file resource in the current view.
	 */ 
	private void openTheEditor(IFile f2) {
		IWorkbenchWindow workbenchWindow = Workbench.getInstance().getActiveWorkbenchWindow();
		IWorkbenchPage page = workbenchWindow.getActivePage();
		final IWorkbenchPart activePart = page.getActivePart();
		if (activePart instanceof ISetSelectionTarget) {
			final ISelection targetSelection = new StructuredSelection(f2);
			workbenchWindow.getShell().getDisplay().asyncExec
				(new Runnable() {
					 public void run() {
						 ((ISetSelectionTarget)activePart).selectReveal(targetSelection);
					 }
				 });
			FileEditorInput i = null;
			try {
				i = new FileEditorInput(f2);
				page.openEditor(i,
						Workbench.getInstance().getEditorRegistry().getDefaultEditor(f2.getFullPath().toString()).getId());
			} catch (Exception e) {
				System.out.println("No editor exists for the converted file" + f2.getFullPath());
			}
			if (i!=null) {
				//i.saveState(null);
			}
		}
	}
	
	public void toModel(IFile file) {
		Openome_modelInitDiagramFileAction model = new Openome_modelInitDiagramFileAction();
		model.setActivePart(null, targetPart);
		model.setDomainModelURI(file);
		model.run(null);
		try {
			file.delete(true, null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
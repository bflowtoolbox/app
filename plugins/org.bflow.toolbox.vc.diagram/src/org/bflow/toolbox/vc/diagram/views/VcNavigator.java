package org.bflow.toolbox.vc.diagram.views;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JTextField;

import org.bflow.toolbox.epc.Function;
import org.bflow.toolbox.epc.ProcessInterface;
import org.bflow.toolbox.epc.diagram.edit.parts.FunctionEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ProcessInterfaceEditPart;
import org.bflow.toolbox.epc.diagram.views.Workspace;
import org.bflow.toolbox.vc.ValueChain;
import org.bflow.toolbox.vc.ValueChain2;
import org.bflow.toolbox.vc.diagram.edit.parts.ValueChain2EditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.ValueChainEditPart;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.gmf.runtime.emf.core.resources.GMFResource;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;


public class VcNavigator extends ViewPart {
	private TreeViewer viewer;
	private DrillDownAdapter drillDownAdapter;
	private Action doubleClickAction;
	private Action newFile;
	private Action newVCFile;
	private Action newFolder;
	private Action deleteObj;
	private ViewContentProvider vcp;
	private String myFile = "";
	private JDialog getFilename;
	private Shell myShell;
	private JTextField JTFile;
	private Object[] myExEl;
	private Text File;
	private Shell getFile;
	private JDialog Test;
	private Shell shell;
	private int nummer;
	private String path;

	class TreeObject implements IAdaptable {
		private String name;
		private TreeParent parent;

		public TreeObject(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setParent(TreeParent parent) {
			this.parent = parent;
		}

		public TreeParent getParent() {
			return parent;
		}

		public String toString() {
			return getName();
		}

		public Object getAdapter(Class key) {
			return null;
		}
	}

	class TreeParent extends TreeObject {
		private ArrayList children;

		public TreeParent(String name) {
			super(name);
			children = new ArrayList();
		}

		public void addChild(TreeObject child) {
			children.add(child);
			child.setParent(this);
		}

		public void removeChild(TreeObject child) {
			children.remove(child);
			child.setParent(null);
		}

		public TreeObject[] getChildren() {
			return (TreeObject[]) children.toArray(new TreeObject[children
					.size()]);
		}

		public boolean hasChildren() {
			return children.size() > 0;
		}
	}

	class ViewContentProvider implements IStructuredContentProvider,
			ITreeContentProvider {
		private TreeParent invisibleRoot;

		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		public void dispose() {
		}

		public Object[] getElements(Object parent) {
			if (parent.equals(getViewSite())) {
				if (invisibleRoot == null)
					initialize();
				return getChildren(invisibleRoot);
			}
			return getChildren(parent);
		}

		public Object getParent(Object child) {
			if (child instanceof TreeObject) {
				return ((TreeObject) child).getParent();
			}
			return null;
		}

		public Object[] getChildren(Object parent) {
			if (parent instanceof TreeParent) {
				return ((TreeParent) parent).getChildren();
			}
			return new Object[0];
		}

		public boolean hasChildren(Object parent) {
			if (parent instanceof TreeParent)
				return ((TreeParent) parent).hasChildren();
			return false;
		}

		/*
		 * Hier kommt der Startordner rein
		 */
		private void initialize() {
			String home = Workspace.workspaceDirectory;
			File workspace = new File(home);
			String[] files = workspace.list();
			TreeParent p1 = new TreeParent(workspace.getName());
			for (int i = 0; i < workspace.list().length; i++) {
				File temp = new File(workspace.getAbsolutePath() + "/"
						+ files[i]);
				if (temp.isFile()) {
					if (files[i].endsWith(".epc") || files[i].endsWith(".vc")) {
						TreeObject to = new TreeObject(temp.getName());
						p1.addChild(to);
					}
				} else if (temp.isDirectory()) {
					if (!files[i].startsWith(".")) {
						p1.addChild(getUnterordner(temp.getAbsoluteFile()));
					}
				}
			}
			TreeParent root = p1;
			invisibleRoot = new TreeParent("");
			invisibleRoot.addChild(root);

		}

		public TreeParent getUnterordner(File workspace) {
			String[] files = workspace.list();
			TreeParent p1 = new TreeParent(workspace.getName());
			if (workspace.list() != null) {
				for (int i = 0; i < workspace.list().length; i++) {
					File temp = new File(workspace.getAbsolutePath() + "/"
							+ files[i]);
					if (temp.isFile()) {
						if (files[i].endsWith(".epc") || files[i].endsWith(".vc")) {
							TreeObject to = new TreeObject(temp.getName());
							p1.addChild(to);
						}
					}
					if (temp.isDirectory()) {
						if (!files[i].startsWith(".")) {
							p1.addChild(getUnterordner(temp.getAbsoluteFile()));
						}
					}
				}
			}
			return p1;
		}
	}

	class ViewLabelProvider extends LabelProvider {

		public String getText(Object obj) {
			return obj.toString();
		}

		public Image getImage(Object obj) {
			String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
			if (obj instanceof TreeParent)
				imageKey = ISharedImages.IMG_OBJ_FOLDER;
			return PlatformUI.getWorkbench().getSharedImages().getImage(
					imageKey);
		}
	}

	class NameSorter extends ViewerSorter {
	}

	/**
	 * The constructor.
	 */
	public VcNavigator() {
		Shell myShell = new Shell();
		this.myShell = myShell;
		selectWorkspace();
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		drillDownAdapter = new DrillDownAdapter(viewer);
		vcp = new ViewContentProvider();
		viewer.setContentProvider(vcp);
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setSorter(new NameSorter());
		viewer.setInput(getViewSite());
		dragSupport();
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				VcNavigator.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(newFile);
		manager.add(newVCFile);
		manager.add(newFolder);
		manager.add(new Separator());
		manager.add(deleteObj);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(newFile);
		manager.add(newVCFile);
		manager.add(newFolder);
		manager.add(new Separator());
		manager.add(deleteObj);
		drillDownAdapter.addNavigationActions(manager);
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		drillDownAdapter.addNavigationActions(manager);
	}

	private void makeActions() {
		newFile = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection)
						.getFirstElement();
				String test = obj.getClass().toString();
				if (test
						.equals("class org.bflow.toolbox.epc.diagram.views.EpcNavigator$TreeParent")){
					TreeParent tp = (TreeParent) obj;
					String Filename = tp.getName();
					ArrayList<String> temp = new ArrayList<String>();
					while (tp.getParent() != null) {
						temp.add(tp.getParent().getName());
						tp = tp.getParent();
					}
					try {
						temp.remove(temp.size() - 1);
						if (temp.size() == 0) {
							Filename = "";
						} else
							temp.remove(temp.size() - 1);
						temp.add(Workspace.workspaceDirectory);
						for (int i = 0; i < temp.size(); i++) {
							Filename = temp.get(i) + "/" + Filename;
						}
						File folder = new File(Filename);
						if (folder.isDirectory()) {
							// Create new EPC into folder
							showMessage(
									"Please enter the Name of the EPC-File to create:",
									"default.epc", "Filename?", 0, Filename);
						}
					} catch (Exception ex) {
						System.out.println(ex);
					}
				} else {// if(test.equals("class
						// epc.diagram.views.EpcNavigator$TreeObject")){
					TreeObject to = (TreeObject) obj;
					String Filename = "";
					ArrayList<String> temp = new ArrayList<String>();
					while (to.getParent() != null) {
						temp.add(to.getParent().getName());
						to = to.getParent();
					}
					try {
						if (temp.size() != 1) temp.remove(temp.size() - 1);
						temp.remove(temp.size() - 1);
						temp.add(Workspace.workspaceDirectory);
						for (int i = 0; i < temp.size(); i++) {
							Filename = temp.get(i) + "/" + Filename;
						}
						File folder = new File(Filename);
						if (folder.isDirectory()) {
							// Create new EPC into folder
							showMessage(
									"Please enter the Name of the EPC-File to create:",
									"default.epc", "Filename?", 0, Filename);
						}
					} catch (Exception ex) {
						System.out.println(ex);
					}
				}
			}
		};
		newFile.setText("New Epc-Diagram");
		newFile.setToolTipText("Create a new Epc-Diagram");
		newFile.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		
		newVCFile = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection)
						.getFirstElement();
				String test = obj.getClass().toString();
				if (test
						.equals("class org.bflow.toolbox.vc.diagram.views.VcNavigator$TreeParent")) {
					TreeParent tp = (TreeParent) obj;
					String Filename = tp.getName();
					ArrayList<String> temp = new ArrayList<String>();
					while (tp.getParent() != null) {
						temp.add(tp.getParent().getName());
						tp = tp.getParent();
					}
					try {
						temp.remove(temp.size() - 1);
						if (temp.size() == 0) {
							Filename = "";
						} else
							temp.remove(temp.size() - 1);
						temp.add(Workspace.workspaceDirectory);
						for (int i = 0; i < temp.size(); i++) {
							Filename = temp.get(i) + "/" + Filename;
						}
						File folder = new File(Filename);
						if (folder.isDirectory()) {
							// Create new EPC into folder
							showMessage(
									"Please enter the Name of the VC-File to create:",
									"default.vc", "Filename?", 0, Filename);
						}
					} catch (Exception ex) {
						System.out.println(ex);
					}
				} else {// if(test.equals("class
						// epc.diagram.views.EpcNavigator$TreeObject")){
					TreeObject to = (TreeObject) obj;
					String Filename = "";
					ArrayList<String> temp = new ArrayList<String>();
					while (to.getParent() != null) {
						temp.add(to.getParent().getName());
						to = to.getParent();
					}
					try {
						temp.remove(temp.size() - 1);
						temp.remove(temp.size() - 1);
						temp.add(Workspace.workspaceDirectory);
						for (int i = 0; i < temp.size(); i++) {
							Filename = temp.get(i) + "/" + Filename;
						}
						File folder = new File(Filename);
						if (folder.isDirectory()) {
							// Create new EPC into folder
							showMessage(
									"Please enter the Name of the VC-File to create:",
									"default.vc", "Filename?", 0, Filename);
						}
					} catch (Exception ex) {
						System.out.println(ex);
					}
				}
			}
		};
		newVCFile.setText("New VC-Diagram");
		newVCFile.setToolTipText("Create a new VC-Diagram");
		newVCFile.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));

		newFolder = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection)
						.getFirstElement();
				String test = obj.getClass().toString();
				if (test
						.equals("class org.bflow.toolbox.epc.diagram.views.EpcNavigator$TreeParent") || 
						test
						.equals("class org.bflow.toolbox.vc.diagram.views.VcNavigator$TreeParent")) {
					TreeParent tp = (TreeParent) obj;
					String Filename = tp.getName();
					ArrayList<String> temp = new ArrayList<String>();
					while (tp.getParent() != null) {
						temp.add(tp.getParent().getName());
						tp = tp.getParent();
					}
					try {
						temp.remove(temp.size() - 1);
						if (temp.size() == 0) {
							Filename = "";
						} else
							temp.remove(temp.size() - 1);
						temp.add(Workspace.workspaceDirectory);
						for (int i = 0; i < temp.size(); i++) {
							Filename = temp.get(i) + "/" + Filename;
						}
						File folder = new File(Filename);
						if (folder.isDirectory()) {
							// Create new folder into folder
							showMessage(
									"Please enter the Name of the Folder to create:",
									"default", "Filename?", 1, Filename);
						}
					} catch (Exception ex) {
						System.out.println(ex);
					}
				} else {// if(test.equals("class
						// epc.diagram.views.EpcNavigator$TreeObject")){
					TreeObject to = (TreeObject) obj;
					String Filename = "";// to.getName();
					ArrayList<String> temp = new ArrayList<String>();
					while (to.getParent() != null) {
						temp.add(to.getParent().getName());
						to = to.getParent();
					}
					try {
						temp.remove(temp.size() - 1);
						temp.remove(temp.size() - 1);
						temp.add(Workspace.workspaceDirectory);
						for (int i = 0; i < temp.size(); i++) {
							Filename = temp.get(i) + "/" + Filename;
						}
						File folder = new File(Filename);
						if (folder.isDirectory()) {
							// Create new folder into folder
							showMessage(
									"Please enter the Name of the Folder to create:",
									"default", "Filename?", 1, Filename);
						}
					} catch (Exception ex) {
						System.out.println(ex);
					}
				}
			}
		};
		newFolder.setText("New Folder");
		newFolder.setToolTipText("Create a new Folder");
		newFolder.setImageDescriptor(PlatformUI.getWorkbench()
				.getSharedImages().getImageDescriptor(
						ISharedImages.IMG_OBJS_INFO_TSK));

		deleteObj = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection)
						.getFirstElement();
				TreeObject to = (TreeObject) obj;
				String Filename = to.getName();
				ArrayList<String> temp = new ArrayList<String>();
				while (to.getParent() != null) {
					temp.add(to.getParent().getName());
					to = to.getParent();
				}
				try {
					temp.remove(temp.size() - 1);
					if (temp.size() == 0) {
						Filename = "";
					} else
						temp.remove(temp.size() - 1);
					temp.add(Workspace.workspaceDirectory);
					for (int i = 0; i < temp.size(); i++) {
						Filename = temp.get(i) + "/" + Filename;
					}
					File folder = new File(Filename);
					if (folder.isDirectory()) {
						if (!folder.delete()) {
							showMessage("Folder is not empty!\nPlease delete all Objects in this Folder and try it again!");
						} else {
							myExEl = viewer.getExpandedElements();
							viewer
									.setContentProvider(new ViewContentProvider());
							setExpands(viewer.getTree().getItems());
							viewer.refresh(true);
						}
					} else {
						if (!folder.delete()) {
							showMessage("File couldn´t be deleted!");
						} else {
							myExEl = viewer.getExpandedElements();
							viewer
									.setContentProvider(new ViewContentProvider());
							setExpands(viewer.getTree().getItems());
							viewer.refresh(true);
						}
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		};
		deleteObj.setText("Delete Object");
		deleteObj.setToolTipText("Delete the selected Object");
		deleteObj.setImageDescriptor(PlatformUI.getWorkbench()
				.getSharedImages().getImageDescriptor(
						ISharedImages.IMG_OBJS_INFO_TSK));

		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection)
						.getFirstElement();
				if (obj.getClass().toString().equals(
						"class org.bflow.toolbox.epc.diagram.views.EpcNavigator$TreeObject") ||
						obj.getClass().toString().equals(
						"class org.bflow.toolbox.vc.diagram.views.VcNavigator$TreeObject")) {
					TreeObject to = (TreeObject) obj;
					String Filename = to.getName();
					ArrayList<String> temp = new ArrayList<String>();
					while (to.getParent() != null) {
						temp.add(to.getParent().getName());
						to = to.getParent();
					}
					try {
						temp.remove(temp.size() - 1);
						if (temp.size() == 0) {
							Filename = "";
						} else
							temp.remove(temp.size() - 1);
						temp.add(Workspace.workspaceDirectory);
						for (int i = 0; i < temp.size(); i++) {
							Filename = temp.get(i) + "/" + Filename;
						}

						File datei = new File(Filename);
						if (datei.isFile() && datei.toURI().getPath().endsWith(".epc")) {
							// Open EPC
							Resource res = new GMFResource(URI
									.createFileURI(Filename));
							org.bflow.toolbox.epc.diagram.part.EpcDiagramEditorUtil
									.openDiagram(res);
						} else {	
							// Open VC
							Resource resVC = new GMFResource(URI
									.createFileURI(Filename));
							org.bflow.toolbox.vc.diagram.part.VcDiagramEditorUtil
									.openDiagram(resVC);
						}
					} catch (Exception ex) {
						System.out.println(ex);
					}
				}
			}
		};
	}

	private void newFile(File datei) {
		try {
			FileWriter fw = new FileWriter(datei);
			// Den FileWriter in einem BufferedWriter verpacken.
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			bw.newLine();
			bw
					.write("<xmi:XMI xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:epc=\"org.bflow.toolbox.epc\" xmlns:notation=\"http://www.eclipse.org/gmf/runtime/1.0.1/notation\">");
			bw.newLine();
			bw.write("<epc:Epc xmi:id=\"_xpddYAVkEd2zmcwJ2MNmpA\"/>");
			bw.newLine();
			bw
					.write("<notation:Diagram xmi:id=\"_x2ddYAVkEd2zmcwJ2MNmpA\" type=\"Epc\" element=\"_xpddYAVkEd2zmcwJ2MNmpA\" name=\""
							+ datei.getName() + "\" measurementUnit=\"Pixel\">");
			bw.newLine();
			bw
					.write("<styles xmi:type=\"notation:DiagramStyle\" xmi:id=\"_x2ddYAVkEd2zmcwJ2MNmpA\"/>");
			bw.newLine();
			bw.write("</notation:Diagram>");
			bw.newLine();
			bw.write("</xmi:XMI>");
			bw.close();
			fw.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	
	private void newVCFile(File datei) {
		try {
			FileWriter fw = new FileWriter(datei);
			// Den FileWriter in einem BufferedWriter verpacken.
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			bw.newLine();
			bw
					.write("<xmi:XMI xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:vc=\"org.bflow.toolbox.vc\" xmlns:notation=\"http://www.eclipse.org/gmf/runtime/1.0.1/notation\">");
			bw.newLine();
			bw.write("<vc:Vc xmi:id=\"_xpddYAVkEd2zmcwJ2MNmpA\"/>");
			bw.newLine();
			bw
					.write("<notation:Diagram xmi:id=\"_x2ddYAVkEd2zmcwJ2MNmpA\" type=\"VC\" element=\"_xpddYAVkEd2zmcwJ2MNmpA\" name=\""
							+ datei.getName() + "\" measurementUnit=\"Pixel\">");
			bw.newLine();
			bw
					.write("<styles xmi:type=\"notation:DiagramStyle\" xmi:id=\"_x2ddYAVkEd2zmcwJ2MNmpA\"/>");
			bw.newLine();
			bw.write("</notation:Diagram>");
			bw.newLine();
			bw.write("</xmi:XMI>");
			bw.close();
			fw.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	private void setExpands(TreeItem[] ti) {
		try {
			for (int i = 0; i < ti.length; i++) {
				for (Object o : myExEl) {
					if (ti[i].getData().toString().equals(o.toString())) {
						ti[i].setExpanded(true);
						viewer.refresh(true);
					}
				}
				if (ti[i].getItems() != null) {
					setExpands(ti[i].getItems());
				}
			}
			viewer.refresh(true);
		} catch (Exception e) {
		}
	}

	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}

	private void showMessage(String message) {
		MessageBox messageBox = new MessageBox(myShell);
		messageBox.setMessage(message);
		messageBox.setText("BflowNavigatorView");
		messageBox.open();
	}

	private void showMessage(String message, String filename, String title,
		int number, String pfad) {
		this.path = pfad;
		this.nummer = number;
		getFile = new Shell();
		getFile.setText(title);
		getFile.setLayout(null);
		Test = new JDialog();
		Test.setLocationRelativeTo(null);
		getFile.setLocation(Test.getLocation().x - 180,
				Test.getLocation().y - 75);
		getFile.setSize(380, 150);
		Label label = new Label(getFile, SWT.NULL);
		label.setText(message);
		label.setLocation(30, 20);
		label.setSize(320, 20);
		File = new Text(getFile, SWT.SINGLE | SWT.BORDER);
		File.setText(filename);
		File.setLocation(30, 40);
		File.setSize(300, 30);
		Button button = new Button(getFile, SWT.PUSH);
		button.setText("OK");
		button.setLocation(30, 80);
		button.setSize(100, 30);
		button.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {
				myFile = File.getText();
				if (nummer == 0) {
					function0(path);
				} else if (nummer == 1) {
					function1(path);
				}
				getFile.close();
			}

			public void widgetSelected(SelectionEvent arg0) {
				myFile = File.getText();
				if (nummer == 0) {
					function0(path);
				} else if (nummer == 1) {
					function1(path);
				}
				getFile.close();
			}
		});
		getFile.setDefaultButton(button);
		getFile.pack();
		getFile.open();

	}

	private void function1(String Filename) {
		if (myFile.equals("default")) {
			File Directory = new File(Filename + "/default");
			int n = 0;
			while (!Directory.mkdir()) {
				Directory = new File(Filename + "/default" + n);
				n++;
			}
			myExEl = viewer.getExpandedElements();
			viewer.setContentProvider(new ViewContentProvider());
			setExpands(viewer.getTree().getItems());
			viewer.refresh(true);
		} else {
			File Directory = new File(Filename + "/" + myFile);
			if (!Directory.mkdir()) {
				showMessage("Please enter a valid Filename!");
			} else {
				myExEl = viewer.getExpandedElements();
				viewer.setContentProvider(new ViewContentProvider());
				setExpands(viewer.getTree().getItems());
				viewer.refresh(true);
			}
		}
	}

	private void function0(String Filename) {
		try {
			if (myFile.equals("default.epc")) {
				File Datei = new File(Filename + "/default.epc");
				int n = 0;
				while (!Datei.createNewFile()) {
					Datei = new File(Filename + "/default" + n + ".epc");
					n++;
				}
				newFile(Datei);
				myExEl = viewer.getExpandedElements();
				viewer.setContentProvider(new ViewContentProvider());
				setExpands(viewer.getTree().getItems());
				viewer.refresh(true);
				Resource res = new GMFResource(URI.createFileURI(Datei
						.getAbsolutePath()));
				org.bflow.toolbox.epc.diagram.part.EpcDiagramEditorUtil.openDiagram(res);
			} else if (myFile.equals("default.vc")) {
				File Datei = new File(Filename + "/default.vc");
				int n = 0;
				while (!Datei.createNewFile()) {
					Datei = new File(Filename + "/default" + n + ".vc");
					n++;
				}
				newVCFile(Datei);
				myExEl = viewer.getExpandedElements();
				viewer.setContentProvider(new ViewContentProvider());
				setExpands(viewer.getTree().getItems());
				viewer.refresh(true);
				Resource res = new GMFResource(URI.createFileURI(Datei
						.getAbsolutePath()));
				org.bflow.toolbox.vc.diagram.part.VcDiagramEditorUtil.openDiagram(res);
		} else {
			File Datei = new File(Filename + "/" + myFile);
			if (Datei.getName().endsWith(".epc")) {
				Datei = new File(Filename + "/" + myFile);
			} else Datei = new File(Filename + "/" + myFile);
			if (!Datei.createNewFile()) {
				showMessage("Please enter a valid Filename!");
			} else {
				if (Datei.getName().endsWith(".epc")) newFile(Datei);
				else newVCFile(Datei);
				myExEl = viewer.getExpandedElements();
				viewer.setContentProvider(new ViewContentProvider());
				setExpands(viewer.getTree().getItems());
				viewer.refresh(true);
				if (Datei.getName().endsWith(".epc")) {
					Resource res = new GMFResource(URI.createFileURI(Datei
							.getAbsolutePath()));
					org.bflow.toolbox.epc.diagram.part.EpcDiagramEditorUtil.openDiagram(res);
				} else {
					Resource resVC = new GMFResource(URI.createFileURI(Datei
							.getAbsolutePath()));
					org.bflow.toolbox.vc.diagram.part.VcDiagramEditorUtil.openDiagram(resVC);
				}
				
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*private void showMessageOld(String message, String filename, String title) {
		getFilename = new JDialog();
		getFilename.setTitle(title);
		getFilename.setModal(true);
		getFilename.setLayout(null);
		getFilename.setLocationRelativeTo(null);
		getFilename.setLocation(getFilename.getX() - 180,
				getFilename.getY() - 75);
		getFilename.setSize(360, 150);
		JLabel JLMessage = new JLabel(message);
		JLMessage.setLocation(30, 20);
		JLMessage.setSize(300, 20);
		getFilename.add(JLMessage);
		JTFile = new JTextField(filename);
		JTFile.setLocation(30, 50);
		JTFile.setSize(300, 20);
		getFilename.add(JTFile);
		JButton JBOk = new JButton("OK");
		JBOk.setLocation(30, 80);
		JBOk.setSize(100, 30);
		JBOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myFile = JTFile.getText();
				getFilename.setVisible(false);
			}
		});
		getFilename.add(JBOk);
		getFilename.setVisible(true);
	}*/

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	// Drag and Drop
	public void dragSupport() {
		viewer.addDragSupport(DND.DROP_COPY, new Transfer[] { LocalTransfer
				.getInstance() }, new DragSourceListener() {

			public void dragFinished(DragSourceEvent event) {
				TreeObject to = ((TreeObject) ((StructuredSelection) viewer
						.getSelection()).getFirstElement());
				String Filename = to.getName();
				ArrayList<String> temp = new ArrayList<String>();
				while (to.getParent() != null) {
					temp.add(to.getParent().getName());
					to = to.getParent();
				}
				try {
					temp.remove(temp.size() - 1); // entfernen der beiden
													// letzten Angaben
					temp.remove(temp.size() - 1); // bei Ordner
					for (int i = 0; i < temp.size(); i++) {
						Filename = temp.get(i) + "/" + Filename;
					}
					Filename = "/" + Filename;
					DragHelper.data = Filename;
				} catch (Exception ex) {
					System.out.println(ex);
				}
				DragHelper.sameObject = true;
				String drop = DragHelper.dropTarget.getClass().toString();
				if (drop.equals("class org.bflow.toolbox.epc.diagram.edit.parts.FunctionEditPart") ||
				    drop.equals("class org.bflow.toolbox.epc.diagram.edit.parts.FunctionNameEditPart")) 
				{
					FunctionEditPart funcEdit = ((FunctionEditPart) DragHelper.dropTarget);
					Function func = (Function) funcEdit.getPrimaryView2().getElement();
					//modified by Christian
					func.getSubdiagram().add(DragHelper.data);
				} else if (drop.equals("class org.bflow.toolbox.epc.diagram.edit.parts.ProcessInterfacesEditPart") ||
						   drop.equals("class org.bflow.toolbox.epc.diagram.edit.parts.ProcessInterfaceNameEditPart")) 
				{
					ProcessInterfaceEditPart funcEdit = ((ProcessInterfaceEditPart) DragHelper.dropTarget);
					ProcessInterface func = (ProcessInterface) funcEdit
							.getPrimaryView2().getElement();
					func.setSubdiagram(DragHelper.data);
				} else if (drop.equals("class org.bflow.toolbox.vcc.diagram.edit.parts.ValueChainEditPart") ||
						   drop.equals("class org.bflow.toolbox.vc.diagram.edit.parts.ValueChainNameEditPart")) 
				{
					ValueChainEditPart funcEdit = ((ValueChainEditPart) DragHelper.dropTarget);
					ValueChain func = (ValueChain) funcEdit
							.getPrimaryView().getElement();
					func.setSubdiagram(DragHelper.data);
				} else if (drop.equals("class org.bflow.toolbox.vc.diagram.edit.parts.ValueChain2EditPart") ||
						  drop.equals("class org.bflow.toolbox.vc.diagram.edit.parts.ValueChain2NameEditPart")) 
				{
					ValueChain2EditPart funcEdit = ((ValueChain2EditPart) DragHelper.dropTarget);
					ValueChain2 func = (ValueChain2) funcEdit
							.getPrimaryView().getElement();
					func.setSubdiagram(DragHelper.data);
				}
			}

			public void dragSetData(DragSourceEvent event) {

			}

			public void dragStart(DragSourceEvent event) {
				if (!((StructuredSelection) viewer.getSelection())
						.getFirstElement()
						.getClass()
						.toString()
						.equals(
								"class org.bflow.toolbox.vc.diagram.views.VcNavigator$TreeObject")) {
					event.doit = false;
				}
				DragHelper.sameObject = false;
			}
		});
	}

	public void selectWorkspace() {
		File savings = new File(System.getProperty("user.home")
				+ "/epc/configWorkspace.cfg");
		if (savings.exists()) {
			try {
				FileReader fr = new FileReader(savings);
				// Den FileWriter in einem BufferedWriter verpacken.
				BufferedReader br = new BufferedReader(fr);
				String WorkspaceFile = br.readLine();
				Workspace.workspaceDirectory = WorkspaceFile;
				br.close();
				fr.close();
			} catch (Exception ex) {
				showMessage("Cannot open workspace! Please Choose a workspace from the File Dialog!");
			}
		} else {
			Workspace.workspaceDirectory = null;
			try {
				File saveDir = new File(System.getProperty("user.home")
						+ "/epc");
				saveDir.mkdir();
				savings.createNewFile();
			} catch (Exception ex) {
				showMessage("Cannot create workspace location file! Path: "
						+ savings.getAbsolutePath());
			}

		}
		DirectoryDialog fd = new DirectoryDialog(myShell);
		File dir = new File(System.getProperty("user.home"));
		if (Workspace.workspaceDirectory != null)
			dir = new File(Workspace.workspaceDirectory);
		fd.setFilterPath(dir.getAbsolutePath());
		fd.setText("Please choose a working directory:");
		Workspace.workspaceDirectory = fd.open();
		try {
			FileWriter fw2 = new FileWriter(savings);
			BufferedWriter bw2 = new BufferedWriter(fw2);
			String WorkspaceFile = Workspace.workspaceDirectory;
			bw2.write(WorkspaceFile);
			bw2.close();
			fw2.close();
		} catch (Exception ex) {
			showMessage("Cannot open selected workspace, default folder is used!");
			try {
				Workspace.workspaceDirectory = System.getProperty("user.home");
				FileWriter fw2 = new FileWriter(savings);
				BufferedWriter bw2 = new BufferedWriter(fw2);
				String WorkspaceFile = Workspace.workspaceDirectory;
				bw2.write(WorkspaceFile);
				bw2.close();
				fw2.close();
			} catch (Exception ex2) {
				showMessage("Systemfehler: " + ex2);
				System.exit(1);
			}

		}
	}
}
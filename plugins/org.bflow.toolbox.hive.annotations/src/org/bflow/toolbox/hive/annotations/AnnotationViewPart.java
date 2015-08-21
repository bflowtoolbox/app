package org.bflow.toolbox.hive.annotations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bflow.toolbox.hive.attributes.AttributeFileRegistry;
import org.bflow.toolbox.hive.attributes.AttributeViewPart;
import org.bflow.toolbox.hive.attributes.IAttributableDocumentEditor;
import org.bflow.toolbox.hive.attributes.utils.EMFUtility;
import org.bflow.toolbox.hive.gmfbridge.HiveGmfBridge;
import org.bflow.toolbox.hive.nls.NLSupport;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.part.ViewPart;

/**
 * Implements the view part to support the add-ons annotation view.
 */
public class AnnotationViewPart extends ViewPart implements ISelectionListener, IAnnotationRuleListener {

	/**
	 * Extension view id
	 */
	public static final String VIEW_ID = "org.bflow.toolbox.annotations.view"; //$NON-NLS-1$
	
	private static Log log = LogFactory.getLog(AnnotationViewPart.class);

	private IGraphicalEditPart selectedEditPart;

	private IStructuredSelection selection;

	private static AnnotationViewPart instance;

	private DiagramEditor diagramEditor;

	private boolean isViewEnabled;

	private ArrayList<Button> buttons = new ArrayList<Button>();

	/**
	 * True, if all annotations should be shown in an
	 * {@link IFilterableAttributes}, false otherwise
	 */
	private static boolean annotationsVisible = true;

	private Button btnToggleAnnotations;

	private Composite parent;

	private Composite superParent;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.ViewPart#init(org.eclipse.ui.IViewSite)
	 */
	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		site.getPage().addSelectionListener(this);

		instance = this;
		AnnotationRuleController.getInstance().register(this);

		annotationsVisible = true;
	}

	public static boolean areAnnotationsVisible() {
		return annotationsVisible;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.WorkbenchPart#dispose()
	 */
	@Override
	public void dispose() {
		AnnotationRuleController.getInstance().unregister(this);
		getSite().getPage().removeSelectionListener(this);
		instance = null;
		super.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	@Override
	public void createPartControl(Composite container) {

		Composite mainPane = new Composite(container, SWT.BORDER | SWT.FILL);
		parent = mainPane;
		superParent = container;
		setUpView(container, mainPane);

	}

	/**
	 * sets the composite of all available rules (for each rule one button with
	 * image, attribute name and value sorted in their categories is created)
	 * 
	 * @param container
	 *            the parent view
	 * @param mainPane
	 *            the composite (encapsulated in the parent), which should
	 *            include all the buttons
	 */
	private void setUpView(Composite container, Composite mainPane) {

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;

		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.minimumWidth = 200;


		mainPane.setLayout(gridLayout);
		mainPane.setLayoutData(gridData);


		btnToggleAnnotations = new Button(mainPane, SWT.NONE);
		btnToggleAnnotations.setLayoutData(gridData);
		btnToggleAnnotations.setImage(new Image(mainPane.getDisplay(), this
				.getClass().getResourceAsStream("/icons/Eye.png")));
		btnToggleAnnotations
				.setToolTipText(NLSupport.AnnotationViewPart_Annotation_Button_VisibilityToggle_Tooltip);
		if (annotationsVisible)
			btnToggleAnnotations
					.setText(NLSupport.AnnotationViewPart_Annotation_Button_VisibilityToggle_Text_Hide);
		else
			btnToggleAnnotations
					.setText(NLSupport.AnnotationViewPart_Annotation_Button_VisibilityToggle_Text_Show);

		btnToggleAnnotations.setEnabled(isViewEnabled);

		btnToggleAnnotations.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {


				annotationsVisible = !areAnnotationsVisible();
				if (annotationsVisible)
					btnToggleAnnotations
							.setText(NLSupport.AnnotationViewPart_Annotation_Button_VisibilityToggle_Text_Hide);
				else
					btnToggleAnnotations
							.setText(NLSupport.AnnotationViewPart_Annotation_Button_VisibilityToggle_Text_Show);

				AnnotationDecoratorProvider.noticeToggleChange(annotationsVisible);


			}
		});


		ExpandBar bar = new ExpandBar(mainPane, SWT.V_SCROLL | SWT.FILL);
		bar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		HashMap<String, Composite> categories = new HashMap<String, Composite>();
		buttons.clear();
		Composite composite ;
		String cat = "";
		GridLayout layout = new GridLayout();
		layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;
		layout.verticalSpacing = 10;
		List<RuleEntry> allRules = AnnotationRuleController.getInstance()
				.getRules();
		//split all rules in sets of their category for sorting
		HashMap<String, List<RuleEntry>> rulesInCategory = new HashMap<String, List<RuleEntry>>();
		for (RuleEntry r : allRules) {
			cat = r.getCategory();
			if (!rulesInCategory.containsKey(cat)) {
				rulesInCategory.put(cat, new ArrayList<RuleEntry>());
			}
			rulesInCategory.get(cat).add(r);
		}

		//sort all sets separately, but only if at least one rule name is not empty
		for (String categoryName : rulesInCategory.keySet()) {
			List<RuleEntry> categoryRules = rulesInCategory.get(categoryName);
			boolean sort = false;
			for (RuleEntry r : categoryRules) {
				if (!r.getAttributeName().isEmpty()) {
					sort = true;
					break;
				}
			}
			if (sort && AnnotationRuleController.getInstance().getSortingOrderForCategory(categoryName))
				Collections.sort(categoryRules, new AnnotationRuleComparator());
		}
		//create the buttons for all rules

		for (String categoryName : rulesInCategory.keySet()) {
			List<RuleEntry> categoryRules = rulesInCategory.get(categoryName);
			for (RuleEntry r : categoryRules) {
			cat = r.getCategory();
			if (!categories.containsKey(cat))
			{
				categories.put(cat, new Composite(bar, SWT.NONE));

			}
			composite =categories.get(cat);
			composite.setLayout(layout);
			Button button = new Button(composite, SWT.PUSH);
			button.setText(r.getName());
			button.setToolTipText(String.format(
					NLSupport.AnnotationViewPart_Annotation_ButtonAddTooltip,
					r.getAttributeName(), r.getAttributeValue()));
			button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

			//catch the icon 
			String completePath = AnnotationLauncherConfigurator
					.getANNOTATIONLOGIC_FOLDER_PATH()
					+ r.getFilename();
			try {
				button.setImage(new Image(mainPane.getShell().getDisplay(),
						new FileInputStream(completePath)));

			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			buttons.add(button);

			//add listener which sets the attribute name/value pair (is used to add the attribute with the given value/attribute name)
			final String valueFinal = r.getAttributeValue();
			final String keyFinal = r.getAttributeName();
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					if (selectedEditPart == null)
						return;
					EObject eObj = EMFUtility.getEObject(selectedEditPart);
					final String id = EMFCoreUtil.getProxyID(eObj);


					if (AttributeViewPart.getInstance().getAttributeFile()
							.contains(id, keyFinal, valueFinal)) {
						AttributeViewPart.getInstance().getAttributeFile()
								.remove(id, keyFinal);
					} else {
						AttributeViewPart.getInstance().getAttributeFile()
								.add(id, keyFinal, valueFinal);
					}

					AttributeFileRegistry.getInstance().dispatchAttributeFileChangedEvent();

					if (diagramEditor instanceof IAttributableDocumentEditor) {
						((IAttributableDocumentEditor) diagramEditor).firePropertyChanged();
					}

					return;
				}
			});

		}
		}
		
		for (String key : categories.keySet()) {
			composite = categories.get(key);
			ExpandItem item0 = new ExpandItem(bar, SWT.NONE, 0);
			item0.setText(key);
			item0.setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
			item0.setControl(composite);
		}
		bar.setSpacing(8);
		mainPane.layout();
	}



	/**
	 * Updates the view content.
	 */
	private void updateView() {
		for (Control control : parent.getChildren()) {
			control.dispose();
		}
		setUpView(superParent, parent);
		parent.layout();

	}


	/**
	 * Deactivates the usage of the view by disabling and clearing the controls.
	 */
	private void disableView() {
		setUpControls(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.
	 * IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// Check if the active editor is a diagram editor. If not deactivate the
		// view
		IEditorPart activeEditorPart = part.getSite().getPage()
				.getActiveEditor();

		// Handling MultiPageEditorPart
		if (activeEditorPart instanceof MultiPageEditorPart) {
			MultiPageEditorPart multiPageEditorPart = (MultiPageEditorPart) activeEditorPart;
			activeEditorPart = (IEditorPart) multiPageEditorPart
					.getSelectedPage();
		}

		// Ensure that is a graphical editor
		if (!(activeEditorPart instanceof GraphicalEditor)) {
			disableView();
			return;
		}

		// Handling MultiPageEditorPart
		if (part instanceof MultiPageEditorPart) {
			MultiPageEditorPart multiPageEditorPart = (MultiPageEditorPart) part;
			part = (IEditorPart) multiPageEditorPart.getSelectedPage();
		}

		if (!(part instanceof IEditorPart))
			return;
		part = HiveGmfBridge.adapt((IEditorPart) part);

		// Check if the part which is affected by the selection is a diagram
		// editor. If not deactivate the view.
		if (!(part instanceof DiagramEditor)) {
			disableView();
			return;
		}

		// Now we are sure that the selection occurred within the current opened
		// editor
		// selectedEditPart = null;
		boolean isAssignable = (selection instanceof IStructuredSelection);
		if (!isAssignable) {
			disableView();
			return;
		}

		// Adapt selected objects if necessary
		IStructuredSelection structuredSelection = (IStructuredSelection) selection;
		Object[] selectedObjectsArray = structuredSelection.toArray();
		Object[] adaptedObjectsArray = HiveGmfBridge
				.adaptSelection(selectedObjectsArray);

		this.selection = new StructuredSelection(adaptedObjectsArray);
		Object selectedObject = ((StructuredSelection) this.selection)
				.getFirstElement();


		setUpControls(isAssignable);

		//TODO enable just that buttons which fits to a certain rule entry??????
		if (isAssignable) {
			selectedEditPart = (IGraphicalEditPart) selectedObject;
			if (selectedObject instanceof ShapeNodeEditPart
					|| selectedObject instanceof ConnectionNodeEditPart) {

			}

		} else {
			selectedEditPart = null;
		}

	}




	/**
	 * Sets up the enabled state of the controls.
	 * 
	 * @param value
	 *            true or false
	 */
	private void setUpControls(boolean value) {

		btnToggleAnnotations.setEnabled(value);
		for (Button b : buttons) {
			b.setEnabled(value);
		}

		isViewEnabled = value;
	}





	/**
	 * Returns the instance of this view part.
	 * 
	 * @return instance of this view part
	 */
	public static AnnotationViewPart getInstance() {
		if (instance == null) {
			IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow();
			IWorkbenchPage workbenchPage = workbenchWindow.getActivePage();

			if (workbenchPage != null) {
				try {
					workbenchPage.showView(VIEW_ID);
				} catch (PartInitException e) {
					log.error(e.getMessage(), e);
				}
			}
		}

		return instance;
	}


	@Override
	public void setFocus() {

	}

	@Override
	public void noticeAnnotationFileChange(AnnotationRuleEvent event) {
		//		if (event.diagramEditor == null)
		//			return;

		diagramEditor = event.diagramEditor;
		updateView();
	}

	/**
	 * helper class for sorting RuleEntries
	 */
	private class AnnotationRuleComparator implements Comparator<RuleEntry> {

		@Override
		public int compare(RuleEntry o1, RuleEntry o2) {
			return o1.getName().compareToIgnoreCase(o2.getName());
		}
	}


}

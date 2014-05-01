package org.bflow.toolbox.extensions.wizards;

import java.text.ParseException;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.internal.dialogs.PageSetupControlType;
import org.bflow.toolbox.extensions.edit.parts.BflowDiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IDiagramPreferenceSupport;
import org.eclipse.gmf.runtime.diagram.ui.internal.pagesetup.DefaultValues;
import org.eclipse.gmf.runtime.diagram.ui.internal.pagesetup.ILabels;
import org.eclipse.gmf.runtime.diagram.ui.internal.pagesetup.PageInfoHelper;
import org.eclipse.gmf.runtime.diagram.ui.internal.pagesetup.PageSetupPageType;
import org.eclipse.gmf.runtime.diagram.ui.internal.pagesetup.PageSetupWidgetFactory;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.WorkspaceViewerProperties;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import com.ibm.icu.text.NumberFormat;


/**
 * An <code>WizardPage</code> to enter the new diagram size.
 * @author Joerg Hartmann
 * @since 0.0.7
 * @version 22/08/13 modified by Arian Storch<arian.storch@bflow.org>
 */
@SuppressWarnings("restriction")
public class DiagramPageSetupWizardPage extends WizardPage {

	/**
	 * Stores the entered values.
	 */
	private IPreferenceStore preferences;
	
	/**
	 * An combo box to choose the type of the page.
	 * E.g. A3.
	 */
	private Combo fComboSize;
	
	/**
	 * To enter an show the page width.
	 */
	private Text fTextWidth;
	
	/**
	 * To enter and show the page height.
	 */
	private Text fTextHeight;
	
	/**
	 * Number format to round values.
	 */
	private NumberFormat fNumberFormat;
	
	/**
	 * Converts from inch to millimeters.
	 */
	private Convertor fConvertor;

	
	/**
	 * Creates this page.
	 */
	public DiagramPageSetupWizardPage() {
		super("Page Setup");
		setTitle("Page Setup");
		setDescription("Setup your diagram size.");

		preferences = getPreferenceStoreForPageSetup();
		fNumberFormat = NumberFormat.getNumberInstance();
		fConvertor = new Convertor(this);
	}

	
	/**
	 * Creates the layout for the page.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Group group = PageSetupWidgetFactory.createGroupPaperSize(parent,
				ILabels.LABEL_TITLE_GROUP_PAPER_SIZE);
		
		PageSetupWidgetFactory.createLabel(group, ILabels.LABEL_PAGE_SIZE);

		fComboSize = PageSetupWidgetFactory.createComboSize(group);
		fComboSize.select(DefaultValues.getLocaleSpecificPageType().getIndex());

		PageSetupWidgetFactory.createLabel(group, ILabels.LABEL_PAGE_WIDTH_MM);
		fTextWidth = PageSetupWidgetFactory.createTextWidth(group);

		PageSetupWidgetFactory.createLabel(group, ILabels.LABEL_PAGE_HEIGHT_MM);
		fTextHeight = PageSetupWidgetFactory.createTextHeight(group);

		int index = fComboSize.getSelectionIndex();
		fTextHeight.setText(fNumberFormat.format(fConvertor
				.convertInchesToMilim(PageSetupPageType.pages[index]
						.getHeight())));
		fTextWidth.setText(fNumberFormat
				.format(fConvertor
						.convertInchesToMilim(PageSetupPageType.pages[index]
								.getWidth())));

		fComboSize.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {

				int index = fComboSize.getSelectionIndex();

				fTextHeight.setText(fNumberFormat.format(fConvertor
						.convertInchesToMilim(PageSetupPageType.pages[index]
								.getHeight())));
				fTextWidth.setText(fNumberFormat.format(fConvertor
						.convertInchesToMilim(PageSetupPageType.pages[index]
								.getWidth())));
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		setControl(group);
	}

	
	/**
	 * Returns an control given by it's unique 
	 * <code>PageSetupControlType</code>.
	 * @param controlType
	 * @return
	 * @see PageSetupControlType
	 */
	public Control getControl(PageSetupControlType controlType) {
		if (controlType.equals(PageSetupControlType.COMBO_PAGE_SIZE))
			return fComboSize;
		else if (controlType.equals(PageSetupControlType.TEXT_PAGE_HEIGHT))
			return fTextHeight;
		else if (controlType.equals(PageSetupControlType.TEXT_PAGE_WIDTH))
			return fTextWidth;
		else
			return null;
	}

	
	/**
	 * Returns an <code>IPreferenceStore</code> for this set up.
	 * @return
	 */
	private IPreferenceStore getPreferenceStoreForPageSetup() {

		try{
			IPreferenceStore workspaceStore = 
				((DiagramGraphicalViewer) BflowDiagramEditPart
					.getCurrentViewer().getRoot().getViewer())
					.getWorkspaceViewerPreferenceStore();

			if (workspaceStore
					.getBoolean(WorkspaceViewerProperties.PREF_USE_DIAGRAM_SETTINGS)) {
				return workspaceStore;
			} else if (BflowDiagramEditPart.getCurrentViewer().getRoot() 
					instanceof IDiagramPreferenceSupport) {
				return (IPreferenceStore) 
					((IDiagramPreferenceSupport) BflowDiagramEditPart
						.getCurrentViewer().getRoot()).getPreferencesHint()
						.getPreferenceStore();
			}
		}
		catch(NullPointerException npe){
			
		}
		
		return (IPreferenceStore) PreferencesHint.USE_DEFAULTS
				.getPreferenceStore();
	}
	
	
	/**
	 * Saves the entered values and returns the correspond 
	 * <code>IPreferenceStore</code>.
	 * @return
	 */
	public IPreferenceStore save(){
		saveText(PageSetupControlType.TEXT_PAGE_HEIGHT, 
				WorkspaceViewerProperties.PREF_PAGE_HEIGHT);
		saveText(PageSetupControlType.TEXT_PAGE_WIDTH, 
				WorkspaceViewerProperties.PREF_PAGE_WIDTH);
		return preferences;
	}
	
	
	/**
	 * Applies the entered values.
	 */
	public void applySetup(){
		IPreferenceStore preferences = save();
		BflowDiagramEditPart diagramEditPart = null;
		try{
			diagramEditPart = BflowDiagramEditPart.getCurrentViewer();
		}
		catch(Exception e){
			return;
		}	
		
		if(diagramEditPart != null){
			final Point size = PageInfoHelper.getPageSize(preferences,
					diagramEditPart.getMapMode());
			
			diagramEditPart.getDiagramFormLayer().getFormHelper().setMinimumSize(
					size.x, size.y);
			
		}
	}
	
	
	/**
	 * Saves the text self.
	 * @param controlType
	 * @param key
	 */
	private void saveText(PageSetupControlType controlType, String key) {
		preferences.setValue(key, fConvertor.convertToInches(controlType));
	}

	
	/**
	 * Converts values from inches to millimeters.
	 *
	 */
	private class Convertor {

		DiagramPageSetupWizardPage fBlockPrint;

		public Convertor(DiagramPageSetupWizardPage configBlock) {
			fBlockPrint = configBlock;
		}

		public double convertToInches(PageSetupControlType type) {
			Text text = (Text) fBlockPrint.getControl(type);

			Number num = null;
			try {
				num = fNumberFormat.parse(text.getText());
				double valueInMillimetres = num.doubleValue();
				double valueInInches = convertMilimToInches(valueInMillimetres);
				return valueInInches;
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return -1;
		}

//		public double convertToMillimetres(PageSetupControlType type) {
//			Text text = (Text) fBlockPrint.getControl(type);
//
//			Number num = null;
//			try {
//				num = fNumberFormat.parse(text.getText());
//				double valueInInches = num.doubleValue();
//				double valueInMillimetres = convertInchesToMilim(valueInInches);
//				return valueInMillimetres;
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//			return -1;
//
//		}

		public double convertInchesToMilim(double inches) {
			return inches * 25.4d;
		}

		public double convertMilimToInches(double milim) {
			return milim / 25.4d;
		}
	}

}

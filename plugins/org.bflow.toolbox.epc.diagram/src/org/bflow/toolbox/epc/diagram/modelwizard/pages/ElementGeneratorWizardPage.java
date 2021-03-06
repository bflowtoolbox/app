package org.bflow.toolbox.epc.diagram.modelwizard.pages;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bflow.toolbox.epc.diagram.Messages;
import org.bflow.toolbox.epc.diagram.edit.parts.EventEditPart;
import org.bflow.toolbox.epc.diagram.modelwizard.utils.ColumnConnectorLabelProvider;
import org.bflow.toolbox.epc.diagram.modelwizard.utils.ColumnImageLabelProvider;
import org.bflow.toolbox.epc.diagram.modelwizard.utils.ColumnTextLabelProvider;
import org.bflow.toolbox.epc.diagram.modelwizard.utils.Connector;
import org.bflow.toolbox.epc.diagram.modelwizard.utils.ConnectorLabel;
import org.bflow.toolbox.epc.diagram.modelwizard.utils.Element;
import org.bflow.toolbox.epc.diagram.modelwizard.utils.KindEditingSupport;
import org.bflow.toolbox.epc.diagram.modelwizard.utils.NameEditingSupport;
import org.bflow.toolbox.epc.diagram.modelwizard.utils.ProcessStep;
import org.bflow.toolbox.epc.diagram.modelwizard.utils.Connector.ConnectorType;
import org.bflow.toolbox.epc.diagram.modelwizard.utils.Element.Kind;
import org.bflow.toolbox.epc.diagram.modelwizard.utils.listener.SelectionListenerXOR;
import org.bflow.toolbox.extensions.edit.parts.BflowNodeEditPart;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.FocusCellOwnerDrawHighlighter;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TableViewerEditor;
import org.eclipse.jface.viewers.TableViewerFocusCellManager;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;

/**
 * Implements the wizard page to generate and add numerous elements to the
 * diagram.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2009-11-24
 * @version 2010-08-28
 * 			2019-01-26 AST Add localization
 * 
 */
public class ElementGeneratorWizardPage extends WizardPage {
	private final String EmptyName = ""; //$NON-NLS-1$
	
	/** Holds the processStep steps. */
	private Vector<ProcessStep> processSteps = new Vector<ProcessStep>();

	/** Holds all installed labels and images for the connectors. */
	private Vector<ConnectorLabel> connectorLabels = new Vector<ConnectorLabel>();

	/** counts the parallel processes during the input phase */
	private int parallelProcesses = 0;

	/** counts the visible table columns */
	private int visibleColumns = 0;

	/** selected edit part that is used as anchor */
	private BflowNodeEditPart anchor;

	/** Tells if a connector is recently open. */
	private boolean connectorOpen = false;
	
	/** For storing the last visited cell by interruption of a other program */
	protected ViewerCell currentcell;

	private TableViewerFocusCellManager focusCellManager;

	private Method setFocusCell;

	/** The log instance for this class */
	private Log _log = LogFactory.getLog(ElementGeneratorWizardPage.class);

	/**
	 * Default constructor.
	 */
	public ElementGeneratorWizardPage(BflowNodeEditPart anchor) {
		super(Messages.ElementGeneratorWizardPage_Id);
		this.setTitle(Messages.ElementGeneratorWizardPage_Title);
		this.setMessage(Messages.ElementGeneratorWizardPage_Message);

		this.anchor = anchor;
		initSetFocusCellMethod();
		
	}

	private void initSetFocusCellMethod() {
		// Reflection for access to the setFocusCell-Methode of TableViewerFocusCellManager
		// Bugreport: --> https://bugs.eclipse.org/bugs/show_bug.cgi?id=198260
		try {
			setFocusCell = TableViewerFocusCellManager.class.getSuperclass().getDeclaredMethod("setFocusCell",  //$NON-NLS-1$
					new Class[] { ViewerCell.class });
			setFocusCell.setAccessible(true);
		} catch (IllegalArgumentException | NoSuchMethodException | SecurityException e1) {
			_log.error("Reflection does not work, focus to a cell cannot programmatically set.", e1); //$NON-NLS-1$
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());
		composite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL | GridData.HORIZONTAL_ALIGN_FILL));

		/*
		 * header
		 */
		Group imgGroup = new Group(composite, SWT.SHADOW_IN);
		imgGroup.setText(Messages.ElementGeneratorWizardPage_Connector);
		imgGroup.setLayout(new RowLayout(SWT.HORIZONTAL));

		for (ConnectorType type : ConnectorType.values())
			if (type != ConnectorType.NONE)
				connectorLabels.add(new ConnectorLabel(type, imgGroup,	new ImageLabelMouseListener()));

		/*
		 * table panel
		 */
		Composite tablePanel = new Composite(composite, SWT.NONE);
		tablePanel.setLayout(new GridLayout(1, false));

		int style = SWT.BORDER | SWT.HIDE_SELECTION | SWT.FULL_SELECTION;
		tableViewer = new TableViewer(tablePanel, style);
		tableViewer.setContentProvider(ArrayContentProvider.getInstance());
						
		focusCellManager = new TableViewerFocusCellManager(tableViewer,new FocusCellOwnerDrawHighlighter(tableViewer));
		ColumnViewerEditorActivationStrategy actSupport = new ColumnViewerEditorActivationStrategy(tableViewer) {

			@Override
			protected boolean isEditorActivationEvent(ColumnViewerEditorActivationEvent event) {
				if (event.stateMask == SWT.ALT){
					return false;
				}
				
				ViewerCell focusCell = (ViewerCell) event.getSource();
				int currentColumn = focusCell.getVisualIndex();
				boolean isNameColumn = currentColumn%2 == 0;
				ProcessStep currentStep = (ProcessStep) focusCell.getElement();
				int elementPosition = getProcessElementIndexByColumnIndex(currentColumn);
				if (elementPosition < currentStep.size()) {
					if (currentStep.get(elementPosition).getKind().isSingleConnector()) {
						return false;
					}
					if (event.eventType == ColumnViewerEditorActivationEvent.MOUSE_CLICK_SELECTION && currentColumn > 0) {
						return true;
					}
					
					if (event.keyCode == SWT.SPACE && currentColumn%2 == 1) {
						return true;
					}
					if (event.keyCode == SWT.CR && currentColumn%2 == 0 && currentColumn != 0) {
						return true;
					}
					if (isNameColumn && event.eventType == ColumnViewerEditorActivationEvent.KEY_PRESSED && isNotDifferentUsedKey(event.keyCode)) {
						KeyEvent ke = (KeyEvent) event.sourceEvent;
						ke.doit = false;
						return true;
					}
					
				}
				
				if (event.eventType == ColumnViewerEditorActivationEvent.PROGRAMMATIC && isNameColumn) {
					focusCellManager.getFocusCell(); //bug, dies stellt aber die Sictbarkeit des Cell-Cursors wieder her
					return false;
				}
				
				
				return false;
			}

			/**
			 * Returns true if the given keycode has no special function in the modelwizard
			 * 
			 * @param keyCode
			 * @return boolean
			 */
			private boolean isNotDifferentUsedKey(int keyCode) {
				if (keyCode == SWT.ALT) {
					return false;
				}
				if (keyCode == SWT.ARROW_DOWN) {
					return false;
				}
				if (keyCode == SWT.ARROW_UP) {
					return false;
				}
				if (keyCode == SWT.ARROW_RIGHT) {
					return false;
				}
				if (keyCode == SWT.ARROW_LEFT) {
					return false;
				}
				return true;
			}
		};
		
		int features = ColumnViewerEditor.TABBING_HORIZONTAL
				| ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR
				| ColumnViewerEditor.TABBING_VERTICAL
				| ColumnViewerEditor.KEYBOARD_ACTIVATION;

		TableViewerEditor.create(tableViewer, focusCellManager, actSupport, features);
		
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		GridData tableData = new GridData(SWT.FILL, SWT.FILL, true, true);
		tableData.heightHint = 400;
		tableData.widthHint = 600;
		table.setLayoutData(tableData);

		table.getVerticalBar().setEnabled(true);
		table.getVerticalBar().setVisible(true);

		table.addKeyListener(new TableKeyListener());
		table.addMenuDetectListener(new MyMenuDectectListener());

		//avoids the wizard finish activation
		table.addTraverseListener(new TraverseListener() {
			
			@Override
			public void keyTraversed(TraverseEvent e) {
				if (e.detail == SWT.TRAVERSE_RETURN ) {
		            e.doit = false;
		        }				
			}
		});
		
		table.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				currentcell =  focusCellManager.getFocusCell();
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				try {
					if (currentcell != null) {
						setFocusCell.invoke(focusCellManager, currentcell);
						currentcell.getItem();
					} else {
						ViewerCell thirdColumn = focusCellManager.getFocusCell().getNeighbor(ViewerCell.RIGHT,true).getNeighbor(ViewerCell.RIGHT, true);
						setFocusCell.invoke(focusCellManager, thirdColumn);
					}
				} catch ( IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
					_log.error("Something went wrong  with programmatically set the focus cell by using reflection.",e1); //$NON-NLS-1$
				}
			}
		});

		// table pop-up menu
		setUpPopUpMenu(table);

		TableViewerColumn conColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		conColumn.getColumn().setText("C"); //$NON-NLS-1$
		conColumn.getColumn().setWidth(25);
		conColumn.setLabelProvider(new ColumnConnectorLabelProvider(composite, processSteps));

		addParallelProcess(1);

		/*
		 * init values
		 */

		ProcessStep step = new ProcessStep(new Connector(ConnectorType.NONE));

		if (anchor != null) {
			step.add((anchor.getClass() == EventEditPart.class 
					? new Element(EmptyName, Kind.Function) 
					: new Element(EmptyName, Kind.Event)));
		} else {
			step.add(new Element(EmptyName, Kind.Event));
		}

		tableViewer.add(step);

		processSteps.add(step);
		
		getShell().addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				currentcell =  focusCellManager.getFocusCell();
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				table.setFocus();
			}
		});

		this.setControl(composite);
		tableViewer.editElement(step, 2);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.WizardPage#canFlipToNextPage()
	 */
	@Override
	public boolean canFlipToNextPage() {
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.DialogPage#setVisible(boolean)
	 */
	@Override
	public void setVisible(boolean value) {
		super.setVisible(value);
		table.setFocus();
	}

	/**
	 * Returns the processStep steps.
	 * 
	 * @return processStep steps
	 */
	public Vector<ProcessStep> getProcessSteps() {
		return processSteps;
	}

	/**
	 * Adds a new parallel process to the process step and configures the table
	 * viewer if necessary.
	 */
	private void addParallelProcess(int pp) {
		// if(processSteps.size() > 0 &&
		// processSteps.lastElement().getConnector().getConnectorType() ==
		// ConnectorType.NONE)
		// return ;
		if (pp > visibleColumns) {
			TableViewerColumn typColumn = new TableViewerColumn(tableViewer, SWT.NONE);
			typColumn.getColumn().setText(Messages.ElementGeneratorWizardPage_Type);
			typColumn.getColumn().setWidth(60);
			typColumn.setLabelProvider(new ColumnImageLabelProvider(composite, parallelProcesses));
			typColumn.setEditingSupport(new KindEditingSupport(tableViewer, parallelProcesses, processSteps));

			TableViewerColumn txtColumn = new TableViewerColumn(tableViewer, SWT.NONE);
			txtColumn.getColumn().setText(Messages.ElementGeneratorWizardPage_Name);
			txtColumn.getColumn().setWidth(120);
			txtColumn.setLabelProvider(new ColumnTextLabelProvider(parallelProcesses));
			txtColumn.setEditingSupport(new NameEditingSupport(tableViewer,	parallelProcesses, processSteps));

			visibleColumns++;
		}

		parallelProcesses++;

		if (processSteps.size() > 1) {
			int lastIndex = processSteps.size() - 1;
			ProcessStep step = processSteps.get(lastIndex - 1);
			boolean event = (step.get(0).getKind() == Kind.Event ? true : false);
			processSteps.lastElement().add(new Element(EmptyName, (event ? Kind.Function : Kind.Event)));
		}
	}

	/**
	 * Configurates the table viewer if the connector has been changed.
	 * 
	 * @param newConnector
	 *            new connector
	 */
	private void progressTable(Connector newConnector) {
		ConnectorType type = newConnector.getConnectorType();

		// Standard
		int n = 1;

		// n parallel processes
		if (type == ConnectorType.AND_N || type == ConnectorType.OR_N || type == ConnectorType.XOR_N) {
			InputDialog dlg = new InputDialog(null, 
					Messages.ElementGeneratorWizardPage_InputDialogTitle,
					Messages.ElementGeneratorWizardPage_InputDialogText, 
					Messages.ElementGeneratorWizardPage_InputDialogValue, 
					new InputValidator()
					);

			if (dlg.open() == InputDialog.OK) {
				n = Integer.parseInt(dlg.getValue());
			} else { // cancel
				deselectConnectors();
				return;
			}
		}

		// double
		if (type == ConnectorType.AND_DOUBLE || type == ConnectorType.OR_DOUBLE
				|| type == ConnectorType.XOR_DOUBLE
				|| type == ConnectorType.XOR_IT)
			n = 2;

		if (n > 1)
			for (int i = 1; i < n; i++)
				addParallelProcess(n);

		ProcessStep newStep = processSteps.lastElement();
		Element oldElement = newStep.get(0);
		newStep.setConnector(newConnector);

		for (int i = newStep.size(); i < n; i++)
			newStep.add(new Element(EmptyName, oldElement.getKind()));

		newStep.setSize(n);

		tableViewer.update(newStep, null);
		tableViewer.editElement(newStep, 2);
		
		ViewerCell focusCell = focusCellManager.getFocusCell();
		int index = focusCell.getVisualIndex();
		try {
			if (index > n*2) {// Cursor liegt au�erhalb des Bearbeitungbereiches, setzte auf erste zur�ck
				ViewerCell nextCell = focusCell;
				for (int i = 0; i < index - 2; i++) {
					nextCell = nextCell.getNeighbor(ViewerCell.LEFT, true);
				}
				if (nextCell != null) {
					setFocusCell.invoke(focusCellManager, nextCell);
					tableViewer.editElement(nextCell.getElement(), 2);
				}	
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			_log.error("Something went wrong  with programmatically set the foucus cell by using reflection.", e1); //$NON-NLS-1$
		}
		
	}

	/** Deselects all connectors. */
	private void deselectConnectors() {
		for (ConnectorLabel label : connectorLabels)
			label.setSelected(false);

		connectorOpen = false;

		// update der Tabelle
		tableViewer.update(processSteps.lastElement(), null);
	}

	/**
	 * Selects the connector label/image according to the selected value.
	 * 
	 * @param connector
	 *            connector to select
	 */
	private void selectConnector(Connector connector) {
		if (connector.getConnectorType() == ConnectorType.NONE)
			return;

		for (ConnectorLabel label : connectorLabels)
			if (label.getType() == connector.getConnectorType()) {
				label.setSelected(true);
				connectorOpen = true;
				return;
			}
	}

	/**
	 * Proofs if a connector is still open and if the table shouldn't be
	 * processed.
	 * @param connectorType 
	 * 
	 * @return true if all connectors are closed or the current Connector is a singleConnector
	 */
	private boolean isProcessable(ConnectorType connectorType) {
		if (connectorOpen) {
			if (connectorType == ConnectorType.AND_SINGLE || connectorType == ConnectorType.OR_SINGLE || connectorType == ConnectorType.XOR_SINGLE) {
				return true;
			}
			MessageDialog.openError(null, 
					Messages.ElementGeneratorWizardPage_ErrorDialogTitle,
					Messages.ElementGeneratorWizardPage_ErrorDialogMessage
					);

			return false;
		}
		
		return true;
	}

	/**
	 * Returns the ConnectorLabel for the opened connector
	 * 
	 * @return ConnectorLabel or null
	 */
	private ConnectorLabel getOpenConnectorLabel() {
		for (ConnectorLabel lbl : connectorLabels)
			if (lbl.isSelected())
				return lbl;

		return null;
	}

	/**
	 * Installs the table pop up menu
	 * 
	 * @param table
	 *            table to install within
	 */
	private void setUpPopUpMenu(Table table) {
		popUpMenu = new Menu(table);

		MenuItem mItemRemCon = new MenuItem(popUpMenu, SWT.NONE);
		mItemRemCon.setText(Messages.ElementGeneratorWizardPage_RemoveConnector);

		MenuItem mItemChCon = new MenuItem(popUpMenu, SWT.NONE);
		mItemChCon.setText(Messages.ElementGeneratorWizardPage_ChangeConnector);

		new MenuItem(popUpMenu, SWT.SEPARATOR);

		MenuItem mItemRedCC = new MenuItem(popUpMenu, SWT.NONE);
		mItemRedCC.setText(Messages.ElementGeneratorWizardPage_ReduceColumnCount);

		SelectionListener listener = new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageDialog.openInformation(null, 
						"Not implemented", //$NON-NLS-1$
						"Not implemented yet" //$NON-NLS-1$
						);

			}
		};

		// new MenuItem(popUpMenu, SWT.SEPARATOR);

		// mItemConnectTo = new MenuItem(popUpMenu, SWT.CASCADE);
		// mItemConnectTo.setText("Connect to...");

		mItemRemCon.addSelectionListener(listener);
		mItemChCon.addSelectionListener(listener);
		mItemRedCC.addSelectionListener(listener);

		table.setMenu(popUpMenu);
	}
	
	/**
	 * Adds a single connector to the current focused process step element
	 * @param ConnectorType
	 */
	private void addSingleConnector(ConnectorType type) {
		if (type == ConnectorType.AND_SINGLE || type == ConnectorType.OR_SINGLE || type == ConnectorType.XOR_SINGLE) {
			Connector newConn;
			ViewerCell focusCell = focusCellManager.getFocusCell();
			ProcessStep currentStep = (ProcessStep) focusCell.getElement();
			int index = focusCell.getVisualIndex();
			int elementIndex = getProcessElementIndexByColumnIndex(index);

			if (type == ConnectorType.XOR_SINGLE) {
				currentStep.set(new Element("XOR", Kind.XOR_Single), elementIndex); //$NON-NLS-1$
			}
			if (type == ConnectorType.OR_SINGLE) {
				currentStep.set(new Element("OR", Kind.OR_Single), elementIndex); //$NON-NLS-1$
			}
			if (type == ConnectorType.AND_SINGLE) {
				currentStep.set(new Element("AND", Kind.AND_Single), elementIndex); //$NON-NLS-1$
			}

			tableViewer.update(currentStep, null);

			if (processSteps.lastElement().equals(currentStep)) {
				newConn = currentStep.getConnector();
				ProcessStep newStep = new ProcessStep(newConn);
				Kind nextKind = getNextKind(currentStep, elementIndex);
				for (int i = 0; i < currentStep.size(); i++) {
					newStep.add(new Element(EmptyName, nextKind));
				}
				processSteps.add(newStep);
				tableViewer.add(newStep);

				for (ProcessStep s : processSteps)
					tableViewer.update(s, null);
			} else if (isLastElementInColumn(processSteps, currentStep, elementIndex)) {
				ProcessStep nextStep = processSteps.get(processSteps.indexOf(currentStep) + 1);
				Kind nextKind = getNextKind(currentStep, elementIndex);
				nextStep.set(new Element(EmptyName, nextKind), elementIndex);
			}
			
			try {
				if (index % 2 == 0) {// Bennenungsspalte
					ViewerCell nextCell = focusCell.getNeighbor(ViewerCell.BELOW, true);
					setFocusCell.invoke(focusCellManager, nextCell);
					tableViewer.editElement(nextCell.getElement(), 2);
				} else {// Typspalte
					ViewerCell nextCell = focusCell.getNeighbor(ViewerCell.RIGHT | ViewerCell.BELOW, true);
					setFocusCell.invoke(focusCellManager, nextCell);
					tableViewer.editElement(nextCell.getElement(), 2);
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
				_log.error("Something went wrong  with programmatically set the focus cell by using reflection.", e1); //$NON-NLS-1$
			}
		}
	}
	
	/**
	 * @param processStep
	 * @param column 
	 * @return 
	 */
	private Kind getNextKind(ProcessStep processStep, int column) {
		int currentIndex = processSteps.indexOf(processStep);
		Kind nextKind = Kind.Event;
		if (currentIndex >= 0) {
			for (int i = currentIndex; i >= 0; i--) {
				ProcessStep previousStep = processSteps.get(i);
				Element previousElement = previousStep.get(column);
				Kind previousKind = previousElement.getKind();
				if (previousKind == Kind.Event || previousKind == Kind.Function){
					nextKind = (previousKind == Kind.Event ? Kind.Function : Kind.Event);
					break;
				}
			}
		}
		return nextKind;
	}
		
	/**
	 * Returns the position of an element within of a process step
	 * 
	 * @param index of the table column
	 * @return index of the element
	 */
	private int getProcessElementIndexByColumnIndex(int index) {
		int stepPosition;
		if (index % 2 == 0) { 
			stepPosition = index - ((index/2)+1);
			if (stepPosition < 0) {
				stepPosition = 0;
			}
		} else {
			stepPosition = index - (index + 1)/2;
		}
		
		return stepPosition;
	}

	/**
	 * Implements a KeyListener for a table cursor.
	 * 
	 * @author Arian Storch
	 * @since 09/12/09
	 * @version 18/12/09
	 * 
	 */
	private class TableKeyListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			Connector newConn = null;

			if (e.stateMask == SWT.ALT) {
				if (e.keyCode == (byte) 'y')
					newConn = new Connector(ConnectorType.AND_SINGLE);

				if (e.keyCode == (byte) 'x')
					newConn = new Connector(ConnectorType.AND_DOUBLE);

				if (e.keyCode == (byte) 'c')
					newConn = new Connector(ConnectorType.AND_N);

				if (e.keyCode == (byte) 'a')
					newConn = new Connector(ConnectorType.OR_SINGLE);

				if (e.keyCode == (byte) 's')
					newConn = new Connector(ConnectorType.OR_DOUBLE);

				if (e.keyCode == (byte) 'd')
					newConn = new Connector(ConnectorType.OR_N);

				if (e.keyCode == (byte) 'q')
					newConn = new Connector(ConnectorType.XOR_SINGLE);

				if (e.keyCode == (byte) 'w')
					newConn = new Connector(ConnectorType.XOR_DOUBLE);

				if (e.keyCode == (byte) 'e')
					newConn = new Connector(ConnectorType.XOR_N);

				if (e.keyCode == (byte) 'r')
					newConn = new Connector(ConnectorType.XOR_IT);
			}

			if (e.stateMask == SWT.CTRL) {
				if (e.keyCode == (byte) 'x')
					newConn = new Connector(ConnectorType.NONE);
			}

			if (newConn == null) // keine interessante Taste wurde gedr�ckt
				return;
			
			ConnectorType type = newConn.getConnectorType();
			
			if (connectorOpen) {
				if (getOpenConnectorLabel().getType() == newConn.getConnectorType()) {// offener Konnektor wurde ausgew�hlt
					newConn = new Connector(ConnectorType.NONE);
				} else if (!isProcessable(type)) {
					return;
				}
			}

			if (type == ConnectorType.AND_SINGLE || type == ConnectorType.OR_SINGLE || type == ConnectorType.XOR_SINGLE) {
							
				addSingleConnector(type);
			}else {
				deselectConnectors();
				selectConnector(newConn);
				progressTable(newConn);
			}
		}		

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}

	/**
	 * Implements a MouseListener for labels with images.
	 * 
	 * @author Arian Storch
	 * @since 18/12/09
	 */
	private class ImageLabelMouseListener implements MouseListener {
		@Override
		public void mouseDoubleClick(MouseEvent e) {
		}

		@Override
		public void mouseDown(MouseEvent e) {
			Label lbl = (Label) e.getSource();

			ConnectorLabel conLabel = null;

			for (ConnectorLabel cLabel : connectorLabels)
				if (cLabel.getLabel() == lbl) {
					conLabel = cLabel;
					break;
				}

			if (conLabel == null)
				return;

			ConnectorType type;

			if (conLabel == getOpenConnectorLabel()) { // offener Konnektor wird
														// geschlossen
				type = ConnectorType.NONE;
			} else { // neuer Konnektor wird ausgew�hlt
				type = conLabel.getType();
				if (!isProcessable(type)) {
					return;
				}
			}

			Connector newConn = new Connector(type);
			
			if (type == ConnectorType.AND_SINGLE || type == ConnectorType.OR_SINGLE || type == ConnectorType.XOR_SINGLE) {
				addSingleConnector(type);
			}else {
				deselectConnectors();
				selectConnector(newConn);
				progressTable(newConn);
			}
		}

		@Override
		public void mouseUp(MouseEvent e) {
		}
	}

	/**
	 * Handles the input of the number of outgoing arcs dialog
	 * 
	 * @author Arian Storch
	 * @since 21/01/10
	 * @version 08/02/10
	 * 
	 */
	private class InputValidator implements IInputValidator {

		@Override
		public String isValid(String newText) {
			String msg = Messages.ElementGeneratorWizardPage_ValidationHint;

			try {
				int n = Integer.parseInt(newText);

				if (n < 2 || n > 10)
					return msg;

				return null;
			} catch (Exception ex) {
				return msg;
			}

		}
	}

	/**
	 * Implements a menu call listener for the table to adapt the context menu
	 * to the user request.
	 * 
	 * @author Arian Storch
	 * @since 08/02/10
	 * @version 08/02/10
	 * 
	 */
	private class MyMenuDectectListener implements MenuDetectListener {

		@Override
		public void menuDetected(MenuDetectEvent e) {
			/*
			 * Prepare the menu item connect to
			 */
			ProcessStep step = (ProcessStep) ((IStructuredSelection) tableViewer
					.getSelection()).getFirstElement();

			// ist immer so
			if (mItemConnectTo == null)
				return;

			mItemConnectTo.dispose();

			mItemConnectTo = new MenuItem(popUpMenu, SWT.CASCADE);
			mItemConnectTo.setText(Messages.ElementGeneratorWizardPage_ConnectTo);

			Menu targetMenu = new Menu(mItemConnectTo);
			mItemConnectTo.setMenu(targetMenu);

			if (step.getConnector().getConnectorType() == ConnectorType.XOR_SINGLE) {
				for (ProcessStep prevStep : processSteps)
					if (prevStep.getConnector().getConnectorType() == ConnectorType.XOR_SINGLE
							&& prevStep != step) {
						MenuItem item = new MenuItem(targetMenu, SWT.NONE);
						item.setText(prevStep.get(0).getName());
						item.addSelectionListener(new SelectionListenerXOR(step, prevStep, tableViewer));
					}
			}
		}
	}
	
	/**
	 * Returns true, if the current step-element is the last element in that branch BUT
	 * the current step is NOT the last processstep at all.
	 * @param processStep
	 * @param column or elementIndex
	 * @return boolean
	 */
	static public boolean isLastElementInColumn(Vector<ProcessStep> steps, ProcessStep currentStep, int column) {
		int indexCurrentStep = steps.indexOf(currentStep);
		if (indexCurrentStep != -1) { //Step existiert
			if (!steps.lastElement().equals(currentStep)) { //Step ist nicht letzter
				ProcessStep nextStep = steps.get(indexCurrentStep + 1);
				if (nextStep.size() > column) {
					Element nextElement = nextStep.get(column);
					if (nextElement.getName().isEmpty()) {
						return true;
					}
				}
			}
		}
		
		return false;
	}

	/*
	 * Layout and final datatypes
	 */
	private Table table;
	private TableViewer tableViewer;

	private Composite composite;

	private Menu popUpMenu;
	private MenuItem mItemConnectTo;
}

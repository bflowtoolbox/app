package org.bflow.toolbox.hive.modelnavigator;

import java.io.InputStream;
import java.util.List;

import org.bflow.toolbox.hive.modelnavigator.internal.AddonModelNavigatorPlugin;
import org.bflow.toolbox.hive.modelnavigator.model.FlowGraph;
import org.bflow.toolbox.hive.modelnavigator.model.FlowGraphDirection;
import org.bflow.toolbox.hive.modelnavigator.model.FlowGraphItem;
import org.bflow.toolbox.hive.modelnavigator.model.FlowGraphTableRow;
import org.bflow.toolbox.hive.modelnavigator.utils.GMFUtility;
import org.eclipse.gmf.runtime.common.ui.services.icon.IIconProvider;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

/**
 * Provides a view part that helps to navigate through a diagram model.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 26.07.12
 *
 */
public class ModelNavigatorView extends ViewPart {
	
	private static final String EMPTY = "";
	
	private DiagramSelectionListener diagramSelectionListener = new DiagramSelectionListener();
	
	private LinkGraphTableContentProvider linkGraphTableContentProvider = new LinkGraphTableContentProvider();
//	private NavigatorTableLabelProvider navigatorTableLabelProvider = new NavigatorTableLabelProvider();
	
	private Composite container;
	
	private TableViewer tableViewerOutgoing;
	private TableViewer tableViewerIncoming;
	
	private final int ROW_HEIGHT = 22;
	private final int EDGE_WIDTH = 40;
	private final int SHAPE_WIDTH = 120;
	
	private Action actionSetReduced;
	private Action actionSetExtended;
	
	/**
	 * Common View Id
	 */
	public static final String ViewId = "org.bflow.toolbox.views.navigation";

	/**
	 * Creates a new Model Navigator View.
	 */
	public ModelNavigatorView() {
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.ViewPart#init(org.eclipse.ui.IViewSite)
	 */
	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		site.getPage().addSelectionListener(diagramSelectionListener);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#dispose()
	 */
	@Override
	public void dispose() {
		getSite().getPage().removeSelectionListener(diagramSelectionListener);
		super.dispose();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		createViewActions();
		
		container = new Composite(parent, SWT.BORDER);
		container.setLayout(new FillLayout(SWT.VERTICAL));
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		Group grpIncoming = new Group(container, SWT.NONE);
		grpIncoming.setLayout(new FillLayout());
		grpIncoming.setText("In");
		
		Group grpOutgoing = new Group(container, SWT.NONE);
		grpOutgoing.setLayout(new FillLayout());
		grpOutgoing.setText("Out");
		
		// Creating table viewer for outgoing edges
		tableViewerOutgoing = new TableViewer(grpOutgoing, SWT.FULL_SELECTION);
		tableViewerOutgoing.setContentProvider(linkGraphTableContentProvider);
		tableViewerOutgoing.setLabelProvider(new NavigatorTableLabelProvider(FlowGraphDirection.OUTGOING));
		tableViewerOutgoing.getTable().addMouseListener(new TableViewerMouseListener(tableViewerOutgoing));
		tableViewerOutgoing.getTable().setHeaderVisible(true);

		// Creating table viewer for incoming edges
		tableViewerIncoming = new TableViewer(grpIncoming, SWT.FULL_SELECTION);
		tableViewerIncoming.setContentProvider(linkGraphTableContentProvider);
		tableViewerIncoming.setLabelProvider(new NavigatorTableLabelProvider(FlowGraphDirection.INCOMING));
		tableViewerIncoming.getTable().addMouseListener(new TableViewerMouseListener(tableViewerIncoming));
		tableViewerIncoming.getTable().setHeaderVisible(true);
		
		// Creating columns for outgoing viewer		
		createTableColumn(tableViewerOutgoing, EDGE_WIDTH, false);
		createTableColumn(tableViewerOutgoing, SHAPE_WIDTH, true);
		createTableColumn(tableViewerOutgoing, EDGE_WIDTH, false);
		createTableColumn(tableViewerOutgoing, SHAPE_WIDTH, true);
		
		// Creating columns for incoming viewer		
		createTableColumn(tableViewerIncoming, SHAPE_WIDTH, true);
		createTableColumn(tableViewerIncoming, EDGE_WIDTH, false);
		createTableColumn(tableViewerIncoming, SHAPE_WIDTH, true);
		createTableColumn(tableViewerIncoming, EDGE_WIDTH, false);
	}
	
	/**
	 * Depending on the given source the view mode of the navigator is changed
	 * from 2 columns (only neighbor) to 4 columns (neighbor and neighbor) and
	 * back.
	 * 
	 * @param source
	 *            Action that triggered this call
	 */
	private void changeNavigatorViewMode(Action source) {
		int colWidthEdge = 0;
		int colWidthShape = 0;
		
		// Setting mode to reduced
		if (source == actionSetReduced) {
			if (!actionSetReduced.isChecked()) {
				actionSetReduced.setChecked(true);
			}
			
			actionSetExtended.setChecked(false);
			
			colWidthEdge = 0;
			colWidthShape = 0;
		}
		
		// Setting mode to extended
		if (source == actionSetExtended) {
			if (!actionSetExtended.isChecked()) {
				actionSetExtended.setChecked(true);
			}
			
			actionSetReduced.setChecked(false);
			
			colWidthEdge = EDGE_WIDTH;
			colWidthShape = SHAPE_WIDTH;
		}
		
		tableViewerOutgoing.getTable().getColumn(2).setWidth(colWidthEdge);
		tableViewerOutgoing.getTable().getColumn(3).setWidth(colWidthShape);
		
		tableViewerIncoming.getTable().getColumn(0).setWidth(colWidthShape);
		tableViewerIncoming.getTable().getColumn(1).setWidth(colWidthEdge);
	}
	
	/**
	 * Creates the actions that are provided by this view part.
	 */
	private void createViewActions() {
		actionSetReduced = new Action() {
			@Override
			public ImageDescriptor getImageDescriptor() {
				InputStream is = ModelNavigatorView.class.getResourceAsStream("/icons/reduced_16.png");
				
				if (is != null) {
					Image img = new Image(Display.getCurrent(), is);
					
					if (img != null) {
						return ImageDescriptor.createFromImage(img);
					}
				}
				return null;
			}
			
			@Override
			public int getStyle() {
				return Action.AS_CHECK_BOX;
			}
			
			@Override
			public void run() {
				changeNavigatorViewMode(this);
			}
			
			@Override
			public String getToolTipText() {
				return "Reduced Mode";
			}
		};
		
		actionSetExtended = new Action() {
			@Override
			public ImageDescriptor getImageDescriptor() {
				InputStream is = ModelNavigatorView.class.getResourceAsStream("/icons/extended_16.png");
				
				if (is != null) {
					Image img = new Image(Display.getCurrent(), is);
					
					if (img != null) {
						return ImageDescriptor.createFromImage(img);
					}
				}
				
				return null;
			}
			
			@Override
			public int getStyle() {
				return Action.AS_CHECK_BOX;
			}
			
			@Override
			public void run() {
				changeNavigatorViewMode(this);
			}
			
			@Override
			public String getToolTipText() {
				return "Extended Mode";
			}
		};
		
		actionSetExtended.setChecked(true);
		
		IActionBars actionBars = getViewSite().getActionBars();
		IToolBarManager toolBar = actionBars.getToolBarManager();
		
		toolBar.add(actionSetReduced);
		toolBar.add(actionSetExtended);
	}
	
	/**
	 * Creates a table column with the given parameter.
	 * 
	 * @param tableViewer
	 *            table view which owns the new created column
	 * @param width
	 *            width of the column
	 * @param resize
	 *            When true the column can be resized
	 */
	private void createTableColumn(TableViewer tableViewer, int width, boolean resize) {
		TableColumn column = new TableColumn(tableViewer.getTable(), SWT.NONE);
		column.setMoveable(false);
		column.setResizable(resize);
		column.setWidth(width);
	}
	
	/**
	 * Resolves the edit part that has been selected through a mouse click within the given table
	 * viewer. If none could be found null is returned.
	 * 
	 * @param tableViewer table viewer which has been clicked
	 * @param x x coordinate of the click event
	 * @param y y coordinate of the click event
	 * @return Resolved edit part or null
	 */
	private IGraphicalEditPart resolveGraphicalEditPart(TableViewer tableViewer, int x, int y) {
		int column = resolveClickedColumn(tableViewer, x);
		
		if (column == -1) { // x wasn't valid 
			return null;
		}
		
		int row = resolveClickedRow(y);
		
		Object element = tableViewer.getElementAt(row);
		
		if (element == null) {
			return null;
		}
		
		FlowGraphTableRow flTblRow = (FlowGraphTableRow)element;
		
		IGraphicalEditPart editPart = null;
		
		if (tableViewer == tableViewerOutgoing) {
			editPart  = (column == 1 ? flTblRow.getEditPart1() : flTblRow.getEditPart2());
		} else {
			editPart  = (column == 0 ? flTblRow.getEditPart2() : flTblRow.getEditPart1());
		}
		
		return editPart;
	}
	
	/**
	 * Calculates the clicked row.
	 * 
	 * @param y
	 *            y coordinate of the click event
	 * @return Clicked row
	 */
	private int resolveClickedRow(int y) {
		return (y - 22) / ROW_HEIGHT;
	}
	
	/**
	 * Calculates the clicked column. If none column could be found -1 is
	 * returned.
	 * 
	 * @param tableViewer
	 *            TableViewer where the click event occured
	 * @param x
	 *            x coordinate of the click event
	 * @return Column index or -1
	 */
	private int resolveClickedColumn(TableViewer tableViewer, int x) {	
		TableColumn columns[] = tableViewer.getTable().getColumns();
		int lastColumnEnd = 0;
		
		for (int i = 0; i < columns.length; i++) {
			TableColumn column = columns[i];
			int columnEnd = lastColumnEnd + column.getWidth();
			
			if (x > lastColumnEnd && x < columnEnd) {
				return i;
			}
			
			lastColumnEnd = columnEnd;
		}
		
		return -1;
	}
	
	/**
	 * Handles the selection changed event. This method will find out all
	 * incoming and outgoing connections of the given selected edit part and
	 * will notify the table to update itself.
	 * 
	 * @param table
	 *            table that will be notified
	 * @param selectedEditPart
	 *            selected edit part.
	 */
	private void handleSelectionChanged(TableViewer tableViewer, IGraphicalEditPart selectedEditPart) {
		List<?> me2some = selectedEditPart.getSourceConnections(); // item is source of outgoing connections
		List<?> any2me = selectedEditPart.getTargetConnections(); // item is target of incoming connections
		
		FlowGraph flowGraphOutgoing = createFlowGraph(me2some, FlowGraphDirection.OUTGOING, 2);
		FlowGraph flowGraphIncoming = createFlowGraph(any2me, FlowGraphDirection.INCOMING, 2);
		
		tableViewerOutgoing.setInput(flowGraphOutgoing);
		tableViewerIncoming.setInput(flowGraphIncoming);
	}
	
	/**
	 * Creates a {@link FlowGraph} from the given list of connections specified
	 * by the given <code>direction</code> and <code>deep</code>.
	 * <p/>
	 * This method works recursive! E.g. calling it with deep = 2, you will get
	 * a Flow Graph that maximal has one following Flow Graph (f1 -> f2).
	 * 
	 * @param me2some List of connections
	 * @param direction Flow direction
	 * @param deep Maximal count of linked Flow Graphs
	 * @return Created Flow Graph
	 * @see FlowGraphDirection
	 */
	private FlowGraph createFlowGraph(List<?> me2some, FlowGraphDirection direction, int deep) {
		FlowGraph flowGraph = new FlowGraph();
		
		for (Object edge2other : me2some) {			
			ConnectionNodeEditPart edgeEditPart = (ConnectionNodeEditPart)edge2other;
			
			// Get an image for the edge
			Image edgeImage = resolveImageFromIconProvider(edgeEditPart);
			
			// Get the target of the egde
			IGraphicalEditPart targetEditPart = resolveConnectionAnchor(edgeEditPart, direction);
			
			// Get an image for the shape
			Image shapeImage = resolveImageFromIconProvider(targetEditPart);
			
			// Get a name for the shape
			String shapeName = resolveNameFromNameProvider(targetEditPart);
			
			// Create new FlowGraphItem
			FlowGraphItem flowGraphItem = new FlowGraphItem(edgeImage, shapeImage, shapeName, targetEditPart);
			flowGraph.addFlowGraphItem(flowGraphItem);
			
			// Need to go deeper?
			List<?> futherConnections = 
				(direction == FlowGraphDirection.OUTGOING ? 
						targetEditPart.getSourceConnections() :
							targetEditPart.getTargetConnections());
			
			if (!futherConnections.isEmpty() && deep > 1) {
				FlowGraph futherFlowGraph = createFlowGraph(futherConnections, direction, deep - 1);
				flowGraphItem.setNext(futherFlowGraph);
			}
		}
		
		return flowGraph;
	}
	
	/**
	 * Returns either the source of the given edge or the target depending of
	 * the given <code>direction</code>.
	 * 
	 * @param edgeEditPart
	 *            Connection edit part
	 * @param direction
	 *            Flow direction
	 * @return Instance of IGraphicalEditPart or null
	 * @see FlowGraphDirection
	 */
	private IGraphicalEditPart resolveConnectionAnchor(ConnectionNodeEditPart edgeEditPart, FlowGraphDirection direction) {
		return (IGraphicalEditPart) (direction == FlowGraphDirection.OUTGOING ? edgeEditPart.getTarget() : edgeEditPart.getSource());
	}
	
	/**
	 * Resolves the image for the given edit part that is provided by an
	 * installed {@link IIconProvider}. If neither an image nor an instance of
	 * an IconProvider could be found, null is returned.
	 * 
	 * @param graphicalEditPart Edit part whose image shall be found
	 * @return Image or null
	 */
	private Image resolveImageFromIconProvider(IGraphicalEditPart graphicalEditPart) {
		// Resolve the element type of edit part
		IElementType elementType = ElementTypeRegistry.getInstance().getElementType(graphicalEditPart.resolveSemanticElement());
		IIconProvider iconProvider = AddonModelNavigatorPlugin.getInstance().getIconProvider(elementType);
		
		// Resolve the icon of element type
		Image image = null;
		
		if (iconProvider != null) {
			image = iconProvider.getIcon(elementType, 0);
		}
		
		return image;
	}
	
	/**
	 * Tries to resolve the name that shall be displayed for the given edit part
	 * from any installed name provider. If none could be found, an emtpy string
	 * will be returned.
	 * 
	 * @param graphicalEditPart
	 *            edit part whose name shall be resolved
	 * @return name of the edit part or an empty string if none could be found
	 */
	private String resolveNameFromNameProvider(IGraphicalEditPart graphicalEditPart) {
		String name = EMFCoreUtil.getQualifiedName(graphicalEditPart.resolveSemanticElement(), false);
		
		if (name != null && !name.isEmpty()) {
			return name;
		}
		
		INameProvider nameProvider = AddonModelNavigatorPlugin.getInstance().getNameProvider(graphicalEditPart);
		return (nameProvider == null ? EMPTY : nameProvider.getName(graphicalEditPart));
	}

	@Override
	public void setFocus() {
		
	}

	/**
	 * Implements the {@link ISelectionListener} to listen to selection change
	 * events on the diagram editor.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 26.07.12
	 * 
	 */
	private class DiagramSelectionListener implements ISelectionListener {
		
		public TableViewer tableViewer = null;

		@Override
		public void selectionChanged(IWorkbenchPart part, ISelection selection) {
			// looking for diagram document editors
			boolean isAssignable = (part instanceof DiagramDocumentEditor && selection instanceof IStructuredSelection);
			
			IStructuredSelection structSelection = (IStructuredSelection)selection;
			
			// selection mustn't be empty
			isAssignable &= !structSelection.isEmpty();
			
			// disable table and finish
			if (!isAssignable) {
				container.setEnabled(false);
				return;
			}
			
			container.setEnabled(true);
			
			Object selectedObject = structSelection.getFirstElement();
			IGraphicalEditPart grfEditPart = (IGraphicalEditPart) selectedObject;
			
			handleSelectionChanged(tableViewer, grfEditPart);
		}		
	}
	
	/**
	 * Implements an {@link ITableLabelProvider} to provide the label of the
	 * model navigator table viewer.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 02.08.12
	 * 
	 */
	private class NavigatorTableLabelProvider implements ITableLabelProvider {
		
		private FlowGraphDirection direction;

		/**
		 * Creates a new instance.
		 * 
		 * @param direction Direction of the show graph
		 * @see FlowGraphDirection
		 */
		public NavigatorTableLabelProvider(FlowGraphDirection direction) {
			super();
			this.direction = direction;
		}

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			FlowGraphTableRow row = (FlowGraphTableRow)element;
			
			if (direction == FlowGraphDirection.OUTGOING) {

				if (columnIndex == 0) {
					return row.getEdgeImage1();
				}

				if (columnIndex == 1) {
					return row.getNodeImage1();
				}

				if (columnIndex == 2) {
					return row.getEdgeImage2();
				}

				if (columnIndex == 3) {
					return row.getNodeImage2();
				}
				
			}
			
			if (direction == FlowGraphDirection.INCOMING) {

				if (columnIndex == 3) {
					return row.getEdgeImage1();
				}

				if (columnIndex == 2) {
					return row.getNodeImage1();
				}

				if (columnIndex == 1) {
					return row.getEdgeImage2();
				}

				if (columnIndex == 0) {
					return row.getNodeImage2();
				}
				
			}
			
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			FlowGraphTableRow row = (FlowGraphTableRow)element;
			
			if (direction == FlowGraphDirection.OUTGOING) {
				if (columnIndex == 1) return row.getName1();
				if (columnIndex == 3) return row.getName2();
				
			}
			
			if (direction == FlowGraphDirection.INCOMING) {
				if (columnIndex == 2) return row.getName1();
				if (columnIndex == 0) return row.getName2();
			}
			
			return null;
		}

		@Override
		public void addListener(ILabelProviderListener listener) { }

		@Override
		public void dispose() { }

		@Override
		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		@Override
		public void removeListener(ILabelProviderListener listener) { }
		
	}
	
	/**
	 * Implements a {@link MouseAdapter} to be notified when a mouse double
	 * click occurs on the model navigator table viewer.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 03.08.12
	 * 
	 */
	private class TableViewerMouseListener extends MouseAdapter {
		private TableViewer tableViewer;

		/**
		 * Creates a new instance.
		 * 
		 * @param tableViewer Table viewer that uses this instance
		 */
		public TableViewerMouseListener(TableViewer tableViewer) {
			super();
			this.tableViewer = tableViewer;
		}
		
		@Override
		public void mouseDoubleClick(MouseEvent e) {
			int x = e.x;
			int y = e.y;
			
			IGraphicalEditPart selectedEditPart = resolveGraphicalEditPart(this.tableViewer, x, y);
			
			if(selectedEditPart != null) {
				GMFUtility.selectEditPart(null, selectedEditPart);
			}
		}
		
	}
	
	/**
	 * Implements an {@link IStructuredContentProvider} to decompose a
	 * {@link FlowGraph} into {@link FlowGraphTableRow} that is used by the
	 * model navigator table viewer.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 03.08.12
	 * 
	 */
	private class LinkGraphTableContentProvider implements IStructuredContentProvider {

		@Override
		public void dispose() { }

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {	}

		@Override
		public Object[] getElements(Object inputElement) {
			FlowGraph graph = (FlowGraph)inputElement;
			
			FlowGraphTableRow rows[] = graph.toFlowGraphTableRows();
			if (rows == null) return new FlowGraphTableRow[0];
			return rows;
		}
		
	}
}

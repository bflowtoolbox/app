package org.bflow.toolbox.hive.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;
import org.bflow.toolbox.hive.actions.commands.BestSizeCommand;
import org.bflow.toolbox.hive.nls.NLSupport;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;


/**
 * Implements an action to provide the best size function.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 16.08.13
 * @version 22.08.13
 * 			26.02.15 Added support of figures consisting of multiple children, for instance note
 * 			
 *
 */
public class BestSizeAction extends DiagramAction implements IObjectActionDelegate {
	/**
	 * Unique id for this action.
	 */
	public static final String Id = "bflow.best.size.action"; //$NON-NLS-1$
	
	/**
	 * The diagram edit part.
	 */
	private DiagramEditPart diagramEditPart;
	
	/** The selected edit parts. */
	@SuppressWarnings("rawtypes")
	private List selectedEditParts;
	
	/**
	 * Instantiates a new auto size action.
	 *
	 * @param workbenchPage the workbench page
	 */
	public BestSizeAction(IWorkbenchPage workbenchPage) {
		super(workbenchPage);
		setId(Id);
		setText(NLSupport.BestSizeAction_Name);
		setToolTipText(NLSupport.BestSizeAction_Tooltip);
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.gmf.runtime.diagram.ui.actions", "icons/elcl16/autosize.gif")); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Instantiates a new auto size action.
	 */
	public BestSizeAction() {
		// This constructor is needed by the Pop-up Menu contribution system 
		super(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage());
	}
	
	
	
	/**
	 * Runs the action by clicking the menu.
	 *
	 * @param action the action
	 */
	public void run(IAction action) {
		Map<EditPart, Dimension> editPartsDimensionMap = calculateOptimizedSize();
		Command command = new BestSizeCommand(editPartsDimensionMap, diagramEditPart);
		diagramEditPart.getDiagramEditDomain().getDiagramCommandStack().execute(command);
	}

	
	/**
	 * The selection has changed. This method will get the diagram edit part.
	 *
	 * @param action
	 *            the action
	 * @param selection
	 *            the selection
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		IStructuredSelection iSelection = (IStructuredSelection) selection;
		if(iSelection.getFirstElement() instanceof DiagramEditPart) {
			diagramEditPart = (DiagramEditPart) iSelection.getFirstElement();
			selectedEditParts = diagramEditPart.getChildren();
		}
	}
	
	
	/**
	 * Select all used element classes which implement <code>AutoSizable</code>.
	 *
	 * @return the vector
	 * @see AutoSizable
	 */
	@SuppressWarnings("unchecked")
	private Vector<Class<?>> selectClasses(){
		Vector<Class<?>> classes = new Vector<Class<?>>();
		if(diagramEditPart != null) {
			for(Iterator<EditPart> children = diagramEditPart.getChildren().iterator(); children.hasNext();) {
				EditPart editPart = children.next();
				if(editPart instanceof ShapeNodeEditPart){
					Class<?> singleClass = editPart.getClass();
					if(!classes.contains(singleClass)){
						classes.add(singleClass);
					}
				}
			}
		}
		
		return classes;
	}

	
	/**
	 * Sets the enability of the action.
	 *
	 * @param action
	 *            the action
	 * @param targetPart
	 *            the target part
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		action.setEnabled(selectClasses().size() > 0);
	}	

	/**
	 * Calculate optimized size.
	 *
	 * @return the map
	 */
	@SuppressWarnings("unchecked")
	private Map<EditPart, Dimension> calculateOptimizedSize() {
		Map<EditPart, Dimension> editPartDimMap = new HashMap<EditPart, Dimension>(50);
		for(Iterator<EditPart> editParts = selectedEditParts.iterator(); editParts.hasNext(); ) {
			EditPart nextElement = editParts.next();
			
			if(!(nextElement instanceof ShapeNodeEditPart))
				continue;
			
			ShapeNodeEditPart editPart = (ShapeNodeEditPart) nextElement;
			Dimension optimizedSize = getOptimizedSize(editPart.getFigure());
			editPartDimMap.put(editPart, optimizedSize);
		}		
		return editPartDimMap;
	}
	
	/**
	 * Returns an optimized size for the given figure.
	 * 
	 * @param figure
	 *            The figure to optimize
	 * @return Dimension for the optimized size of the figure
	 */
	private Dimension getOptimizedSize(IFigure figure) {
		List<?> children = figure.getChildren();
		
		// Go down the path until the figure is an instance of wrapping label
		if (children != null && !children.isEmpty() && !(figure instanceof WrappingLabel)) {
			// Some elements own more than one child, for instance a note
			// So calculate the optimized size of all children and use the maximum
			Dimension maxDimension = new Dimension(0, 0);
			for (Iterator<?> it = children.iterator(); it.hasNext();) {
				IFigure childFigure = (IFigure) it.next();
				Dimension childDimension = getOptimizedSize(childFigure);
				maxDimension = (childDimension.getArea() > maxDimension.getArea() ? childDimension : maxDimension);
			}
			return maxDimension;
		}
		
		// Only figures with text are interesting
		if (!(figure instanceof WrappingLabel)) return figure.getParent().getPreferredSize();
		
		// Get the text to display and the text bounds
		WrappingLabel label = (WrappingLabel)figure;
		String text = label.getText();		
		// Rectangle textBounds = label.getTextBounds();
		
		// Resolve font metrics
		Shell shell = new Shell();
		GC gc = new GC(shell);
		gc.setFont(label.getFont());
		int avgCharWidth = gc.getFontMetrics().getAverageCharWidth();
		int fontHeight = gc.getFontMetrics().getHeight();
		gc.dispose();
		shell.dispose();
		
		int standardWidth = 64; // textBounds.width
		
		// Calculate the size metrics
		int linesOfText = ((text.length() * avgCharWidth) / standardWidth) + 1;
		int charsPerLine = standardWidth / avgCharWidth;
		// int lineBreak = getLineBreakPoint(text, charsPerLine);
		
		String betterMax = getMaxSubstring(text, charsPerLine, linesOfText);
		
		double height = linesOfText * fontHeight;
		double width = betterMax.length() * avgCharWidth;
		
		IFigure parent = figure.getParent();
		Dimension optimizedSize = parent.getSize();
		
		optimizedSize.width = (int) width + 50;
		optimizedSize.height = (int) height + 10;
		
		return optimizedSize;
	}
	
	/**
	 * Returns a user-friendly line break point for the given text.
	 * User-friendly means that whitespaces are used. The lookup starts at the
	 * given start point.
	 * 
	 * @param text
	 *            Text to process
	 * @param start
	 *            Point where the lookup starts
	 * @return A user-friendly line break point
	 */
	private int getLineBreakPoint(String text, int start) {
		char ws = ' ';
		if(text.charAt(start) == ws)
			return start;
		
		int lengthForward = start;
		while((lengthForward + 1) < text.length() && text.charAt(++lengthForward) != ws)
			;
		
		int lengthBackward = start;
		while((lengthBackward - 1) >= 0 && text.charAt(--lengthBackward) != ws)
			;
		
		// Choose the nearest one 
		if((lengthForward - start) < (start - lengthBackward))
			return lengthForward;
		else
			return lengthBackward;
	}
	
	/**
	 * Returns the largest substring of the given text that yields in the given
	 * count of chars per line and lines of text.
	 * 
	 * @param text
	 *            Text to process
	 * @param charPerLine
	 *            Count of chars per line
	 * @param linesOfText
	 *            Count of lines
	 * @return Largest substring
	 */
	private String getMaxSubstring(String text, int charPerLine, int linesOfText) {
		List<Integer> breakPoints = new ArrayList<Integer>(5);
		for(int i = 1; i < linesOfText; i++) {
			int start = i * charPerLine;
			int lineBreakPoint = getLineBreakPoint(text, start);
			breakPoints.add(lineBreakPoint);
		}
		
		List<String> subStrings = new ArrayList<String>(10);
		int lastBreakPoint = 0;
		for(Integer lbp:breakPoints) {
			String substring = text.substring(lastBreakPoint, lbp);
			subStrings.add(substring);
			lastBreakPoint = lbp;
		}
		subStrings.add(text.substring(lastBreakPoint));
		
		String max = StringUtils.EMPTY;
		for(String substring:subStrings) {
			if(substring.length() > max.length())
				max = substring;
		}
		
		return max;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#createTargetRequest()
	 */
	@Override
	protected Request createTargetRequest() {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#calculateEnabled()
	 */
	@Override
	protected boolean calculateEnabled() {
		return getDiagramEditPart() != null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#isSelectionListener()
	 */
	@Override
	protected boolean isSelectionListener() {
		return true;
	}	
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#doRun(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected void doRun(IProgressMonitor progressMonitor) {
		if(getSelectedObjects().size() == 0)
			return;
		
		// Find the diagram edit part
		Object selObject = getSelectedObjects().get(0);
		if(selObject instanceof DiagramEditPart)
			diagramEditPart = (DiagramEditPart) selObject;
		
		// If only the diagram edit part is selected, then select all children
		if(selObject == diagramEditPart)
			selectedEditParts = diagramEditPart.getChildren();
		else
			selectedEditParts = getSelectedObjects();
		
		// Fallback strategy
		if(diagramEditPart == null)
			diagramEditPart = getDiagramEditPart();
		
		run((IAction)null);
	}
}

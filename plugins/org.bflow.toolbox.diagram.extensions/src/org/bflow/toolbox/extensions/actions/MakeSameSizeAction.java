package org.bflow.toolbox.extensions.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.bflow.toolbox.extensions.commands.MakeSameSizeCommand;
import org.bflow.toolbox.extensions.commands.AutoSizeMarker;
import org.bflow.toolbox.extensions.commands.AutoSizeMarker.EditPartMarker;
import org.bflow.toolbox.extensions.edit.parts.AutoSizable;
import org.bflow.toolbox.extensions.edit.parts.BflowDiagramEditPart;
import org.bflow.toolbox.extensions.wizards.ClassSelectorMarker;
import org.bflow.toolbox.extensions.wizards.ClassSelectorWizard;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;


/**
 * Implements an action to provide the make same size function.
 * 
 * @author Joerg Hartmann
 * @since 0.0.7
 * @version 15/12/13 by Arian Storch<arian.storch@bflow.org>
 *
 */
public class MakeSameSizeAction implements IObjectActionDelegate {

	
	/**
	 * The diagram edit part.
	 */
	private DiagramEditPart diagramEditPart;
	
	
	/**
	 * Runs the action by clicking the menu.
	 * @param action
	 */
	public void run(IAction action) {
		Vector<Class<?>> classes = selectClasses();
		
		if(classes.size() > 0){
			ClassSelectorWizard w = new ClassSelectorWizard(classes);
			w.init(null, null);
			
			IWorkbenchPart page =  PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActivePart();
		
			WizardDialog dialog = new WizardDialog(page.getSite().getShell(), w);
			dialog.create();
			dialog.open();
			
			if(w.isHasFinished()){
				Vector<ClassSelectorMarker> markers = w.getMarkers();
				Vector<AutoSizeMarker> autoSizeMarkers = captureEditParts(markers);
				
				Command command = new MakeSameSizeCommand(autoSizeMarkers);
				((BflowDiagramEditPart) diagramEditPart).getEditDomain().
					getCommandStack().execute(command);
			}
		}
	}

	
	/**
	 * The selection has changed.
	 * This method will get the diagram edit part.
	 * @param action
	 * @param selection
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		IStructuredSelection iSelection = (IStructuredSelection) selection;
		if(iSelection.getFirstElement() instanceof BflowDiagramEditPart
				&& iSelection.getFirstElement() instanceof DiagramEditPart){
			diagramEditPart = (DiagramEditPart) iSelection.getFirstElement();
		}
	}
	
	
	/**
	 * Select all used element classes which implement <code>AutoSizable</code>
	 * @return
	 * @see AutoSizable
	 */
	@SuppressWarnings("unchecked")
	private Vector<Class<?>> selectClasses(){
		Vector<Class<?>> classes = new Vector<Class<?>>();
		if(diagramEditPart != null){
			for(Iterator<EditPart> children = 
				diagramEditPart.getChildren().iterator(); children.hasNext();){
				
				EditPart editPart = children.next();
				if(editPart instanceof AutoSizable){
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
	 * @param action
	 * @param targetPart
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		action.setEnabled(selectClasses().size() > 0);
	}

	
	/**
	 * Gets all instances of used types.
	 * @param markers
	 * @return
	 */
	private Vector<AutoSizeMarker> captureEditParts(
			Vector<ClassSelectorMarker> markers){
		Vector<AutoSizeMarker> autoSizeMarkers = new Vector<AutoSizeMarker>();
		
		for(Iterator<ClassSelectorMarker> m = markers.iterator(); m.hasNext();){
			ClassSelectorMarker marker = m.next();
			if(marker.isSelected()){
				autoSizeMarkers.add(capture(marker.getType()));
			}
		}
		
		return autoSizeMarkers;
	}
	
	
	/**
	 * Gets all instances off the specified type and stores it in 
	 * an <code>AutoSizeMarker</code>.
	 * @param type
	 * @return
	 * @see AutoSizeMarker
	 */
	@SuppressWarnings("unchecked")
	private AutoSizeMarker capture(Class<?> type){
		AutoSizeMarker marker = new AutoSizeMarker(type);
		Vector<EditPartMarker> editPartMarkers = new Vector<EditPartMarker>();
		int maxWidth = 0;
		int maxHeight = 0;
		for(Iterator<ShapeNodeEditPart> editParts = 
			diagramEditPart.getChildren().iterator(); editParts.hasNext();){
			
			ShapeNodeEditPart editPart = editParts.next();
			
			if(editPart.getClass() == type){
				
				Dimension size = editPart.getFigure().getSize();
				editPartMarkers.add(marker.new EditPartMarker(editPart, size));
				
				Dimension result = getOptimizedSize(editPart.getFigure());
				
				maxWidth = Math.max(maxWidth, result.width); //result.width; // Math.max(maxWidth, size.width);
				maxHeight = Math.max(maxHeight, result.height); //result.height; //Math.max(maxHeight, size.height);
			}
		}
		marker.setMarkers(editPartMarkers);
		marker.setMaxSize(new Dimension(maxWidth, maxHeight));
		
		return marker;
	}
	
	/**
	 * Returns an optimized size for the given figure.
	 * 
	 * @param figure The figure to optimize
	 * @return Dimension for the optimized size of the figure
	 */
	private Dimension getOptimizedSize(IFigure figure) {
		List<?> children = figure.getChildren();
		
		// Go down the path until the figure is an instance of wrapping label
		if(children != null && !children.isEmpty() && !(figure instanceof WrappingLabel))
			return getOptimizedSize((IFigure) children.get(0));
		
		// Get the text to display and the text bounds
		WrappingLabel label = (WrappingLabel)figure;
		String text = label.getText();		
		Rectangle textBounds = label.getTextBounds();
		
		// Resolve font metrics
		Shell shell = new Shell();
		GC gc = new GC(shell);
		gc.setFont(label.getFont());
		int avgCharWidth = gc.getFontMetrics().getAverageCharWidth();
		int fontHeight = gc.getFontMetrics().getHeight();
		gc.dispose();
		shell.dispose();
		
		// Elements with no name has width = 0
		if(textBounds.width == 0)
			textBounds.width = 1;
		
		// Calculate the size metrics
		int linesOfText = (text.length() * avgCharWidth) / textBounds.width;
		int charsPerLine = textBounds.width / avgCharWidth;
		// int lineBreak = getLineBreakPoint(text, charsPerLine);
		
		String betterMax = getMaxSubstring(text, charsPerLine, linesOfText);
		
		double height = linesOfText * fontHeight;
		double width = betterMax.length() * avgCharWidth;
		
		IFigure parent = figure.getParent();
		Dimension optimizedSize = parent.getPreferredSize();
		
		if(optimizedSize.width < width)
			optimizedSize.width = (int) width;
		
		if(optimizedSize.height < height)
			optimizedSize.height = (int) height;
		
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
		
		String max = "";
		for(String substring:subStrings) {
			if(substring.length() > max.length())
				max = substring;
		}
		
		return max;
	}
}

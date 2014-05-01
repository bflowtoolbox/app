package org.bflow.toolbox.epc.extensions.idelete;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Vector;

import org.bflow.toolbox.epc.Arc;
import org.bflow.toolbox.epc.diagram.edit.parts.ANDEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ArcEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.OREditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.XOREditPart;
import org.bflow.toolbox.epc.diagram.edit.policies.ArcItemSemanticEditPolicy;
import org.bflow.toolbox.epc.diagram.edit.policies.EpcBaseItemSemanticEditPolicy;
import org.bflow.toolbox.epc.diagram.part.EpcDiagramEditor;
import org.bflow.toolbox.epc.diagram.part.EpcDiagramEditorPlugin;
import org.bflow.toolbox.epc.extensions.actions.DiagramLiveValidator;
import org.bflow.toolbox.epc.extensions.utils.EpcDiagramEditUtil;
import org.bflow.toolbox.extensions.configurator.BflowConfigurator;
import org.bflow.toolbox.extensions.edit.parts.ColoredConnectionEditPart;
import org.bflow.toolbox.extensions.edit.parts.ColoredNodeEditPart;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * Utility that integrates an intelligent delete mode. For example: If you use
 * this delete method on a single edit part, it will automatically connect the
 * remaining edit parts if there are some. And this does much more.
 * 
 * @author Arian Storch
 * @since 12/07/10
 * @version 15/07/11
 * 
 */
public class IntelligentDeleter {

	private static EpcDiagramEditor epcDiagramEditor;

	private static boolean inProgress;
	public static boolean running = false;
	private static boolean validationEnabled;

	private static boolean drawArc = true;

	private static Vector<EditPart> deletedEditParts;
	private static HashMap<EditPart, EditPart> replacedEditParts;

	/**
	 * Deletes the edit part on an intelligent way.
	 * 
	 * @param part
	 *            part to delete
	 */
	public static void delete(ColoredNodeEditPart part) throws Exception {

		PathCrawler backwardCrawler = new IntelligentDeleter().new PathCrawler(
				part, false);
		PathCrawler forwardCrawler = new IntelligentDeleter().new PathCrawler(
				part, true);

		EditPart src = null;
		EditPart tgt = null;

		if (!backwardCrawler.isFinishedByConnector())
			try {
				src = backwardCrawler.getPathItems().firstElement();
			} catch (NoSuchElementException ex) {
			}
		else
			src = backwardCrawler.getConnector();

		if (!forwardCrawler.isFinishedByConnector())
			try {
				tgt = forwardCrawler.getPathItems().firstElement();
			} catch (NoSuchElementException ex) {
			}
		else
			tgt = forwardCrawler.getConnector();

		drawArc = true;

		// konnektor-behandlung
		if (backwardCrawler.isFinishedByConnector()
				&& forwardCrawler.isFinishedByConnector()) {

			ColoredNodeEditPart con1 = backwardCrawler.getConnector();
			ColoredNodeEditPart con2 = forwardCrawler.getConnector();

			if (backwardCrawler.isConnectorOR())
				deleteOREnclosure(con1, con2);

			if (backwardCrawler.isConnectorAnd())
				deleteANDEnclosure(part, con1, con2);

			if (backwardCrawler.isConnectorXOR())
				deleteXOREnclosure(part, con1, con2);
		}

		if (tgt != null && src != null)
			if (drawArc)
				EpcDiagramEditUtil.createConnection(epcDiagramEditor, src, tgt);

	}

	/**
	 * Deletes an arc edit part on an intelligent way.
	 * 
	 * @param arcEditPart
	 */
	public static void delete(ArcEditPart arcEditPart) throws Exception {
		ColoredNodeEditPart src = (ColoredNodeEditPart) arcEditPart.getSource();
		ColoredNodeEditPart tgt = (ColoredNodeEditPart) arcEditPart.getTarget();
		
		if(src == tgt)
			return ;

		if (src instanceof XOREditPart)
			if (tgt instanceof XOREditPart)
				if (((XOREditPart) src).getSourceConnections().size() == 2) {

					XOREditPart split = (XOREditPart) src;
					XOREditPart join = (XOREditPart) tgt;

					EditPart nuSrc = ((ArcEditPart) split
							.getTargetConnections().get(0)).getSource();
					EditPart midS = null;
					EditPart midE = null;
					EditPart nuTgt = ((ArcEditPart) join.getSourceConnections()
							.get(0)).getTarget();

					if (split.getSourceConnections().get(0) == arcEditPart)
						midS = ((ArcEditPart) split.getSourceConnections().get(
								1)).getTarget();
					else
						midS = ((ArcEditPart) split.getSourceConnections().get(
								0)).getTarget();

					if (join.getTargetConnections().get(0) == arcEditPart)
						midE = ((ArcEditPart) join.getTargetConnections()
								.get(1)).getSource();
					else
						midE = ((ArcEditPart) join.getTargetConnections()
								.get(0)).getSource();

					EpcDiagramEditUtil.deleteConnector(epcDiagramEditor, split);
					EpcDiagramEditUtil.deleteConnector(epcDiagramEditor, join);

					EpcDiagramEditUtil.createConnection(epcDiagramEditor,
							nuSrc, midS);

					EpcDiagramEditUtil.createConnection(epcDiagramEditor, midE,
							nuTgt);

				}
	}

	/**
	 * Deletes an edit part on an intelligent way.
	 * 
	 * @param eobject
	 *            emf object to delete
	 */
	public static void delete(EObject eobject) {

		boolean b = Boolean.parseBoolean(BflowConfigurator.getInstance().get(
				BflowConfigurator.Key.IDELETE));

		if (b == false || epcDiagramEditor == null)
			return;

		IStructuredSelection selection = (IStructuredSelection) epcDiagramEditor
				.getEditorSite().getSelectionProvider().getSelection();

		if (selection.size() > 1) // break on multi-selection
			return;

		DiagramLiveValidator validator = EpcDiagramEditorPlugin.getInstance()
				.getDiagramLiveValidator();

		try {
			IntelligentDeleter.setInProgress(true);

			/*
			 * deactivate live validation to increase speed
			 */
			validationEnabled = validator.isEnabled();
			validator.setEnabled(false);
			epcDiagramEditor.getDiagramEditPart().refresh();

			/*
			 * get edit part and delete it
			 */
			if (eobject instanceof Arc)
				for (Object obj : epcDiagramEditor.getDiagramEditPart()
						.getConnections()) {
					ArcEditPart arc = (ArcEditPart) obj;
					if (arc.resolveSemanticElement() == eobject) {
						delete(arc);
						break;
					}
				}
			else
				for (Object obj : epcDiagramEditor.getDiagramEditPart()
						.getChildren()) {
					if (obj instanceof ColoredNodeEditPart) {
						ColoredNodeEditPart editPart = (ColoredNodeEditPart) obj;
						if (editPart.resolveSemanticElement().equals(eobject)) {
							IntelligentDeleter
									.delete((ColoredNodeEditPart) obj);
							break;
						}
					}
				}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			EpcDiagramEditUtil.flushCommandCollection();

			/*
			 * activate and run validation
			 */
			validator.setEnabled(validationEnabled);
			validator.runValidation();

			IntelligentDeleter.setInProgress(false);
			running = false;
		}
	}

	private static void deleteOREnclosure(ColoredNodeEditPart split,
			ColoredNodeEditPart join) {
		
		int countOuts = split.getSourceConnections().size();

		if (hasAlreadyDirectConnection(split, join)) {
			drawArc = false;

			if (countOuts == 3) {
				ColoredNodeEditPart nuSrc = EpcDiagramEditUtil.replace(epcDiagramEditor, split,
						XOREditPart.class, true);
				ColoredNodeEditPart nuTgt = EpcDiagramEditUtil.replace(epcDiagramEditor, join,
						XOREditPart.class, true);
				
				EpcDiagramEditUtil.createConnection(epcDiagramEditor, nuSrc, nuTgt);
			}
		} else {
			if(countOuts == 2) {
				drawArc = false;
				
				ColoredNodeEditPart nuSrc = EpcDiagramEditUtil.replace(epcDiagramEditor, split,
						XOREditPart.class, true);
				ColoredNodeEditPart nuTgt = EpcDiagramEditUtil.replace(epcDiagramEditor, join,
						XOREditPart.class, true);
				
				EpcDiagramEditUtil.createConnection(epcDiagramEditor, nuSrc, nuTgt);
			} else {
				drawArc = true;
			}
		}
	}

	private static void deleteANDEnclosure(ColoredNodeEditPart part,
			ColoredNodeEditPart split, ColoredNodeEditPart join) {
		drawArc = false;

		int countOuts = split.getSourceConnections().size();

		if (countOuts == 2) {

			EditPart src = (EditPart) ((ArcEditPart) split
					.getTargetConnections().get(0)).getSource();
			EditPart midS = null;
			EditPart midE = null;
			EditPart tgt = (EditPart) ((ArcEditPart) join
					.getSourceConnections().get(0)).getTarget();

			for (int i = 0; i < 2; i++)
				if (!contains(part, split, join, i)) {
					midS = ((ArcEditPart) (split.getSourceConnections().get(i)))
							.getTarget();
					midE = getLastEditPart(split, join, i);
					break;
				}

			EpcDiagramEditUtil.createConnection(epcDiagramEditor, src, midS);
			EpcDiagramEditUtil.createConnection(epcDiagramEditor, midE, tgt);

			EpcDiagramEditUtil.deleteConnector(epcDiagramEditor, split);
			EpcDiagramEditUtil.deleteConnector(epcDiagramEditor, join);
		}
	}

	private static void deleteXOREnclosure(EditPart part,
			ColoredNodeEditPart split, ColoredNodeEditPart join) {
		int countOuts = split.getSourceConnections().size();

		drawArc = false;

		if (countOuts == 2) {

			if (hasAlreadyDirectConnection(split, join)) {

				EpcDiagramEditUtil.deleteConnector(epcDiagramEditor, split);
				EpcDiagramEditUtil.deleteConnector(epcDiagramEditor, join);

				EditPart src = ((ArcEditPart) split.getTargetConnections().get(
						0)).getSource();
				EditPart tgt = ((ArcEditPart) join.getSourceConnections()
						.get(0)).getTarget();

				EpcDiagramEditUtil.createConnection(epcDiagramEditor, src, tgt);

				drawArc = false;
			} else
				drawArc = true;

			return;
		}

		if (!hasAlreadyDirectConnection(split, join))
			drawArc = true;
	}

	/**
	 * Undoes the last step.
	 */
	public static void undo() {
		EpcDiagramEditUtil.undo();
	}

	private static boolean contains(ColoredNodeEditPart part,
			ColoredNodeEditPart split, ColoredNodeEditPart join, int n) {
		ArcEditPart arc1 = (ArcEditPart) split.getSourceConnections().get(n);

		ColoredNodeEditPart p = (ColoredNodeEditPart) arc1.getTarget();

		while (p != part && p != join) {

			if (p.getSourceConnections().size() == 0)
				return false;

			ArcEditPart arc2 = (ArcEditPart) p.getSourceConnections().get(0);
			p = (ColoredNodeEditPart) arc2.getTarget();
		}

		if (p == part)
			return true;
		else
			return false;
	}

	private static EditPart getLastEditPart(ColoredNodeEditPart split,
			ColoredNodeEditPart join, int n) {
		ArcEditPart arc = (ArcEditPart) split.getSourceConnections().get(n);
		ColoredNodeEditPart next = (ColoredNodeEditPart) arc.getTarget();

		while (next != join) {
			//if(next.getSourceConnections().size() == 0)
				//break;
			
			arc = (ArcEditPart) next.getSourceConnections().get(0);
			next = (ColoredNodeEditPart) arc.getTarget();
		}

		return arc.getSource();
	}

	/**
	 * Sets the current epc diagram editor.
	 * 
	 * @param epcDiagramEditor
	 */
	public static void setEpcDiagramEditor(EpcDiagramEditor epcDiagramEditor) {
		IntelligentDeleter.epcDiagramEditor = epcDiagramEditor;
	}

	/**
	 * Sets the running state of the intelligent deleter.
	 * @param b true or false
	 */
	public static void setRunning(boolean b) {
		running = b;
	}

	private static void setInProgress(boolean inProgress) {

		if (inProgress) {
			deletedEditParts = new Vector<EditPart>();
			replacedEditParts = new HashMap<EditPart, EditPart>();
		} else {
			deletedEditParts = null;
			replacedEditParts = null;
		}

		IntelligentDeleter.inProgress = inProgress;
	}

	/**
	 * Returns true if the {@link IntelligentDeleter} is in progress.
	 * 
	 * @return true or false
	 */
	public static boolean isInProgress() {
		return inProgress;
	}

	/**
	 * Returns true of the {@link IntelligentDeleter} is enabled. This can be
	 * set by bflow* preferences or {@link BflowConfigurator}.
	 * 
	 * @return true or false
	 */
	public static boolean isEnabled() {
		if (BflowConfigurator.getInstance().get(BflowConfigurator.Key.IDELETE)
				.equalsIgnoreCase("false"))
			return false;
		else
			return true;
	}

	/**
	 * Proofs if the intelligent deleter is available for the EObject and its semantic edit policy.
	 * @param arc EObject to delete (mostly arc)
	 * @param policy semantic edit policy
	 * @return true or false
	 */
	public static boolean isAvailable(EObject arc,
			EpcBaseItemSemanticEditPolicy policy) {
		IStructuredSelection selection = (IStructuredSelection) epcDiagramEditor
				.getEditorSite().getSelectionProvider().getSelection();

		if (selection.size() > 1)
			return false;

		if (policy instanceof ArcItemSemanticEditPolicy)
			if (selection.getFirstElement() instanceof ArcEditPart) {
				if (((ArcEditPart) selection.getFirstElement())
						.resolveSemanticElement().equals(arc)) 
					return true;
				} else
					return false;

		return true;
	}

	/**
	 * Returns a vector containing all deleted edit parts of the current delete
	 * process.
	 * <p/>
	 * <b>Note:</b><br/>
	 * Don't use this manipulating the delete process! This could result in an
	 * unexpected behavior.
	 * 
	 * @return vector containing deleted edit parts
	 */
	public static Vector<EditPart> getDeletedEditParts() {
		return deletedEditParts;
	}

	/**
	 * Returns a HashMap containting all replaced edit parts of the current
	 * delete process.
	 * <p/>
	 * <b>Note:</b><br/>
	 * Don't use this manipulating the delete process! This could result in an
	 * unexpected behavior.
	 * 
	 * @return HashMap containting all replaced edit parts
	 */
	public static HashMap<EditPart, EditPart> getReplacedEditParts() {
		return replacedEditParts;
	}

	/**
	 * Returns true if the edit part has been deleted during the current delete
	 * process.
	 * 
	 * @param part
	 *            edit part to check
	 * @return true or false
	 */
	public static boolean isDeleted(EditPart part) {
		return deletedEditParts.contains(part);
	}

	/**
	 * Returns true if the edit part has been replaced during the current delete
	 * process.<br/>
	 * To get the replaced one use <code>getReplacedEditParts()</code>.
	 * 
	 * @param part
	 *            edit part to check
	 * @return true or false
	 */
	public static boolean isReplaced(EditPart part) {
		return replacedEditParts.containsKey(part);
	}

	private static boolean isDirectConnection(ColoredNodeEditPart split,
			ColoredNodeEditPart join, int n) {
		if (split.getSourceConnections().size() < n)
			return false;

		ArcEditPart arc = (ArcEditPart) split.getSourceConnections().get(n);

		for (Object o : join.getTargetConnections()) {
			ArcEditPart arcTgt = (ArcEditPart) o;
			if (arcTgt.equals(arc))
				return true;
		}

		return false;
	}

	private static boolean hasAlreadyDirectConnection(
			ColoredNodeEditPart split, ColoredNodeEditPart join) {
		for (int i = 0; i < split.getSourceConnections().size(); i++)
			if (isDirectConnection(split, join, i))
				return true;

		return false;
	}

	/**
	 * Defines an util class for crawling through paths.
	 * 
	 * @author Arian Storch
	 * @since 13/07/10
	 */
	private class PathCrawler {
		private boolean forward;
		private Vector<ColoredNodeEditPart> pathItems;
		private boolean finishedByConnector;
		private ColoredNodeEditPart connector;

		public PathCrawler(ColoredNodeEditPart anchor, boolean forward) {
			this.finishedByConnector = false;
			this.forward = forward;
			this.pathItems = new Vector<ColoredNodeEditPart>();

			if (anchor != null)
				this.crawl(anchor);
		}

		private void crawl(ColoredNodeEditPart anchor) {
			// System.out.println("\ncrawling...");

			// keine Verbindung weiter
			if (!hasNext(anchor))
				return;

			Object next = next(anchor);

			Vector<ColoredNodeEditPart> collector = new Vector<ColoredNodeEditPart>();

			while (true) {
				if (collector.size() > 0)
					break;

				if (isConnector(next)) {
					finishedByConnector = true;

					connector = (ColoredNodeEditPart) next;
					break;
				}

				ColoredNodeEditPart part = (ColoredNodeEditPart) next;

				// System.out.println(part.resolveSemanticElement());
				collector.add(part);

				if (hasNext(part))
					next = next(part);
				else
					break;
			}

			pathItems.addAll(collector);
			// System.out.println("...finished");
		}

		private boolean hasNext(ColoredNodeEditPart part) {
			return (forward ? part.getSourceConnections() : part
					.getTargetConnections()).size() != 0;
		}

		private Object next(ColoredNodeEditPart part) {
			// changed ArcEditPart to coloredConnectionEditPart during bugfix
			// could lead to other problems
			return (forward ? ((ColoredConnectionEditPart) part.getSourceConnections().get(0))
					.getTarget()
					: ((ColoredConnectionEditPart) part.getTargetConnections().get(0))
							.getSource());
		}

		public boolean isConnector(Object part) {
			return (part instanceof ANDEditPart || part instanceof OREditPart || part instanceof XOREditPart);
		}

		public Vector<ColoredNodeEditPart> getPathItems() {
			return pathItems;
		}

		public boolean isConnectorAnd() {
			return (connector instanceof ANDEditPart);
		}

		public boolean isConnectorOR() {
			return (connector instanceof OREditPart);
		}

		public boolean isConnectorXOR() {
			return (connector instanceof XOREditPart);
		}

		public boolean isFinishedByConnector() {
			return finishedByConnector;
		}

		@SuppressWarnings("unused")
		public boolean isForward() {
			return forward;
		}

		public ColoredNodeEditPart getConnector() {
			return connector;
		}

	}
}

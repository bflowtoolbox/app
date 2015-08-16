package org.bflow.toolbox.hive.attributefilter;

import java.text.Collator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.bflow.toolbox.hive.attributes.AttributeFileRegistry;
import org.bflow.toolbox.hive.attributes.AttributeFileRegistryEvent;
import org.bflow.toolbox.hive.attributes.IAttributeFileRegistryListener;
import org.bflow.toolbox.hive.attributes.internal.InterchangeAttributeProvider;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.impl.EdgeImpl;
import org.eclipse.gmf.runtime.notation.impl.NodeImpl;
import org.eclipse.gmf.runtime.notation.impl.ViewImpl;

/**
 * This class provides some methods to calculate which FilterRules apply to
 * which elements and a method which uses the calculated results to update the
 * view of the editor (currently just epc is supported)
 * 
 * @author Felix Hoess <fhoess@users.sf.net>
 * @since 11.06.2015
 *
 */
public class FilterController implements IAttributeFileRegistryListener {

	private static FilterController instance;

	private FilterController() {
		AttributeFileRegistry.getInstance().addRegistryListener(this);

	}

	public static FilterController getInstance() {
		if (instance == null)
			instance = new FilterController();
		return instance;
	}

	/**
	 * Applies all filters to the editor. If the filter is activated (or
	 * deactivated), it makes all elements in the editor invisible (or visible)
	 * 
	 * @param editor
	 */
	private void applyFiltersToEditorView(DiagramEditor editor) {

		if (editor == null)
			return;
		Object adapter = editor.getAdapter(GraphicalViewer.class);
		if (!(adapter instanceof DiagramGraphicalViewer))
			return;
		DiagramGraphicalViewer viewer = (DiagramGraphicalViewer) adapter;


		//get all EditParts of the Model
		Map editPartRegistry = viewer.getEditPartRegistry();
		Object editPart = null;


		Set<Object> visibleElements = getElementsForFilter(editPartRegistry,
				AttributeFilterController.getInstance().getAllFilters());
	
		//show all elements if the processed result is a non-empty set (look AttributeAnalyser.getVisibleElementsForFilter())
		boolean showAll = (visibleElements != null && visibleElements.isEmpty());
		//
		boolean show = true;
		Set<EObject> visibleEObjects = new HashSet<EObject>();
		if (visibleElements != null) {
			for (Object element : visibleElements) {
				if (element instanceof EdgeImpl)
					visibleEObjects.add(((EdgeImpl) element).getElement());
				if (element instanceof NodeImpl)
					visibleEObjects.add(((NodeImpl) element).getElement());
			}
		}
	
		for (Object keyOfEditPartRegistry : editPartRegistry.keySet()) {
			if (keyOfEditPartRegistry instanceof NodeImpl) {
				editPart = editPartRegistry.get(keyOfEditPartRegistry);
	
				if (editPart instanceof GraphicalEditPart) {
					if (showAll
	
							|| visibleEObjects
									.contains(((NodeImpl) keyOfEditPartRegistry)
											.getElement())) {
						show = true;
					} else {
						show = false;
					}
					((GraphicalEditPart) editPart).getFigure().setVisible(show);
	
				}
			} else if (keyOfEditPartRegistry instanceof EdgeImpl) {
				editPart = editPartRegistry.get(keyOfEditPartRegistry);
				if (showAll
						|| visibleEObjects
								.contains(((EdgeImpl) keyOfEditPartRegistry)
										.getElement())) {
					show = true;
	
				} else {
					show = false;
				}
				if (editPart instanceof ConnectionEditPart)
					((ConnectionEditPart) editPartRegistry
							.get(keyOfEditPartRegistry)).getFigure()
							.setVisible(show);
	
			}
		}
	
	}
	//
	//	private DiagramDocumentEditor getEditor() {
	//		return (DiagramDocumentEditor) EditorService.getInstance()
	//				.getRegisteredEditors(
	//						"epc.diagram.part.BflowEpcDiagramEditorID");
	//	}

	/**
	 * Notice, if attributes are changed. If so, it delegates that all filters
	 * refresh
	 */
	@Override
	public void noticeAttributeFileChange(AttributeFileRegistryEvent event) {
		applyFiltersToEditorView(event.diagramEditor);

	}

	/**
	 * Uses the given {@link AttributeFilter} and a Map of given EditParts (
	 * {@link NodeImpl} or {@link EdgeImpl}) to calculate a Set of Elements,
	 * which includes only elements where filter rule results are true. A filter
	 * uses a lexicographic comparison for Strings and the normal <, > , !=, <=,
	 * >= and = operators for numeric comparisons of the element attribute
	 * entries and the given entries of the rule. The filter compares either
	 * Strings or Numeric. If one value of the attribute is a String and the
	 * rule entry is a numeric value, the filter dosen't apply. <br>
	 * All filters of the given AttributeFilter are concatenated with a logical
	 * OR.
	 * 
	 * @param editParts
	 *            A Map of all Elements of the model ({@link NodeImpl} or
	 *            {@link EdgeImpl})
	 * @param filters
	 *            A List of {@link FilterEntry}
	 * @return a Set of {@link NodeImpl} or {@link EdgeImpl}.<br>
	 *         Returns null, if filters are used but no element is found, which
	 *         fits to the filter. Returns an empty set, if filters don't have a
	 *         effect (e.g. they are off/inactive)<br>
	 * 
	 */
	private Set<Object> getElementsForFilter(Map editParts,
			List<FilterEntry> filters) {
	
		Set<Object> set = new HashSet<Object>();
		Set<String> idlist = new HashSet<String>();
		InterchangeAttributeProvider iap = new InterchangeAttributeProvider();
		boolean result = false;
		boolean filterUsed = false;
		Locale locale = new Locale(Platform.getNL());
		Collator col = Collator.getInstance(locale);
		for (Object obj : editParts.keySet()) {
			ViewImpl viewImpl = null;
			if (obj instanceof NodeImpl || obj instanceof EdgeImpl) {
				viewImpl = (ViewImpl) obj;
				EObject eObj = viewImpl.getElement();
				String id = EMFCoreUtil.getProxyID(eObj);
				if (idlist.contains(id) || set.contains(obj))
					continue;
				HashMap<String, String> attributes = (HashMap<String, String>) iap
						.getAttributesFor(null, id);
				if (attributes != null && !attributes.isEmpty()) {
					String value = "";
					for (String key : attributes.keySet()) {
	
						for (FilterEntry entry : filters) {
							if (!entry.isActive())
								continue;
							filterUsed = true; // activate if at least one
												// filter is active
							if (key.equals(entry.getAttributeName())) {
								value = attributes.get(key);
								Double d = 0d;
								switch (entry.getOperator()) {
	
								case ">": {
									try {
										d = Double
												.parseDouble(entry.getValue());
										result = (Double.valueOf(value) > Double
												.valueOf(d));
									} catch (NumberFormatException e) {
										result = col.compare(value,
												entry.getValue()) > 0;
									}
								}
									break;
								case "=": {
	
									try {
										d = Double
												.parseDouble(entry.getValue());
										result = (Double.valueOf(value) == Double
												.valueOf(d));
									} catch (NumberFormatException e) {
										result = col.compare(value,
												entry.getValue()) == 0;
									}
	
								}
									break;
								case "<": {
									try {
										d = Double
												.parseDouble(entry.getValue());
										result = (Double.valueOf(value) < Double
												.valueOf(d));
									} catch (NumberFormatException e) {
										result = col.compare(value,
												entry.getValue()) < 0;
									}
								}
									break;
								case "\u2260":
									try {
										d = Double
												.parseDouble(entry.getValue());
										result = Double.valueOf(value) != d;
									} catch (NumberFormatException e) {
										result = col.compare(value,
												entry.getValue()) != 0;
									}
									break;
								case "\u2265":
									try {
										d = Double
												.parseDouble(entry.getValue());
										result = Double.valueOf(value) >= d;
									} catch (NumberFormatException e) {
										result = col.compare(value,
												entry.getValue()) >= 0;
									}
									break;
								case "\u2264":
									try {
										d = Double
												.parseDouble(entry.getValue());
										result = Double.valueOf(value) <= d;
									} catch (NumberFormatException e) {
										result = col.compare(value,
												entry.getValue()) <= 0;
									}
									break;
	
								}
								if (result) {
									set.add(obj);
									idlist.add(id);
									result = false;
								}
							}
						}
	
					}
				}
	
			}
		}
		// return null if the set is empty and filters are in use with no effect.
		return (set.isEmpty() && filterUsed) ? null : set;
	
	}

}

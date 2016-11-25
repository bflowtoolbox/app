package org.bflow.toolbox.hive.statement.addons.contributions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.bflow.toolbox.hive.addons.components.DiagramExportComponent;
import org.bflow.toolbox.hive.addons.components.PrologRunComponent;
import org.bflow.toolbox.hive.addons.components.ToolAdapterComponent;
import org.bflow.toolbox.hive.addons.components.ToolRunComponent;
import org.bflow.toolbox.hive.addons.core.exceptions.ComponentException;
import org.bflow.toolbox.hive.addons.core.model.IComponent;
import org.bflow.toolbox.hive.addons.core.model.IComposableComponent;
import org.bflow.toolbox.hive.attributes.AttributeFile;
import org.bflow.toolbox.hive.attributes.AttributeViewPart;
import org.bflow.toolbox.hive.statement.views.StatementView;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.impl.DiagramImpl;
import org.eclipse.ui.PlatformUI;

/**
 * Implements <code>IComponent</code> for exporting the rule set of gmf diagram.
 * <p/>
 * The component expects a file object to the diagram as input source. If it
 * isn't a <code>ComponentException</code> is thrown.<br/>
 * The exported file is saved in the target folder chosen by a user parameter<br/>
 * The export will not change the diagram source file, so the output is the unchanged diagram.
 * 
 * @author Markus Schnädelbach
 */
public class RuleSetExportComponent implements IComponent, IComposableComponent {

	/** The source. */
	private File source;

	/** The finished. */
	private boolean finished = false;

	private String param;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.addons.core.model.IComponent#finish()
	 */
	@Override
	public void finish() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.addons.core.model.IComponent#hasFinished()
	 */
	@Override
	public boolean hasFinished() {
		return finished;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.addons.core.model.IComponent#init()
	 */
	@Override
	public void init() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.addons.core.model.IComponent#invoke()
	 */
	@Override
	public void invoke() throws ComponentException {
		File targetFile = new File(param);
		if (targetFile.isDirectory()) {
			targetFile = new File(targetFile.getAbsoluteFile() + File.separator + "properties");
		}
		if (!targetFile.isAbsolute()) {
			throw new ComponentException("Destination path not valid");
		}

		DiagramEditor diagramEditor = (DiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		DiagramEditPart dep = diagramEditor.getDiagramEditPart();
		DiagramImpl diagramImpl = (DiagramImpl) dep.getModel();
		EObject eObj = diagramImpl.getElement();
		XMLResource resource = (XMLResource) eObj.eResource();
		String diagramId = resource.getID(eObj);

		AttributeFile aF = AttributeViewPart.getInstance().getAttributeFile();
		aF.load();

		HashMap<String, String> allAttr = aF.get(diagramId);
		ArrayList<String> properties = new ArrayList<>();
		
		StatementView sv = StatementView.getInstance();
		if (allAttr != null) {
			List<String> sortedKeys=new ArrayList<String>(allAttr.keySet());
			Collections.sort(sortedKeys);
			for (String propertyId : sortedKeys) {
				if (propertyId.matches("property_[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}")) {
					String[] parts = allAttr.get(propertyId).split(">>>"); //$NON-NLS-1$
					if (parts.length == 2) {
						parts[1] = parts[1].replaceAll("(\\$_)", "");
						for (String id : sv.getAllRelatedNodeIds()) {
							parts[1] = parts[1].replaceAll("("+id+")(_)\\d", id);
						}
						properties.add(parts[1]);
					}
				}
			}
		}

		if (!properties.isEmpty()) {
			try {
				targetFile.createNewFile();
			} catch (IOException e) {
				throw new ComponentException("Could not create file " + targetFile.getAbsolutePath());
			}

			FileWriter fw = null;
			try {
				fw = new FileWriter(targetFile, false);
				
				for (String prop : properties) {
					fw.write(prop);
					fw.write(System.getProperty("line.separator"));
				}
			} catch (IOException e) {
				throw new ComponentException("Could not write in " + targetFile.getAbsolutePath());
			} finally {
				try {
					if (fw != null) {
						fw.close();
					}
				} catch (IOException e) {
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bflow.toolbox.addons.core.model.IComponent#transformInput(java.lang
	 * .Object)
	 */
	@Override
	public void transformInput(Object inputSource) throws ComponentException {
		if (inputSource == null)
			throw new ComponentException("Source is null");

		if (inputSource.getClass() != File.class)
			throw new ComponentException("input source has not expected format");

		source = (File) inputSource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.addons.core.model.IComponent#transformOutput()
	 */
	@Override
	public Object transformOutput() throws ComponentException {
		return source;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bflow.toolbox.addons.core.model.IComponent#getDescription(java.lang
	 * .String)
	 */
	@Override
	public String getDescription(String abbreviation) {

		if (abbreviation.equalsIgnoreCase("de")) {
			String str = "Exportiert das Ruleset des aktuellen Diagramms an einen beliebigen Speicherort";
			return str;
		}

		String str = "Exports the ruleset of current diagram to a desired target";
		return str;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.addons.core.model.IComponent#isValid()
	 */
	@Override
	public boolean isValid() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.addons.core.model.IComponent#getDisplayName()
	 */
	@Override
	public String getDisplayName() {
		return "Ruleset export";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bflow.toolbox.addons.core.model.IComponent#canLinkWith(org.bflow.
	 * toolbox.addons.core.model.IComponent)
	 */
	@Override
	public boolean canLinkWith(IComponent component) {
		if (component instanceof DiagramExportComponent) return true;
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.hive.addons.core.model.IComposableComponent#supportsSucceeder(org.bflow.toolbox.hive.addons.core.model.IComponent)
	 */
	@Override
	public boolean supportsSucceeder(IComponent component) {
		if (component instanceof DiagramExportComponent) return true;
		if (component instanceof ToolAdapterComponent) return true;
		if (component instanceof ToolRunComponent) return true;
		if (component instanceof PrologRunComponent) return true;
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.addons.core.model.IComponent#hasParams()
	 */
	@Override
	public boolean hasParams() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bflow.toolbox.addons.core.model.IComponent#setParams(java.lang.String
	 * )
	 */
	@Override
	public void setParams(String param) {
		this.param = param;
	}
}

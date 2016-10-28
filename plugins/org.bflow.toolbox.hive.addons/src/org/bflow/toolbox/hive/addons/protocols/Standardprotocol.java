package org.bflow.toolbox.hive.addons.protocols;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.bflow.toolbox.hive.addons.AddonsPlugin;
import org.bflow.toolbox.hive.addons.components.AttributeAdjustComponent;
import org.bflow.toolbox.hive.addons.components.EPCMetricsEvaluationComponent;
import org.bflow.toolbox.hive.addons.components.FileAnalysisComponent;
import org.bflow.toolbox.hive.addons.components.ShellAnalysisComponent;
import org.bflow.toolbox.hive.addons.core.exceptions.ProtocolException;
import org.bflow.toolbox.hive.addons.core.model.IComponent;
import org.bflow.toolbox.hive.addons.core.model.Protocol;
import org.bflow.toolbox.hive.addons.interfaces.IDiagramExportComponent;
import org.bflow.toolbox.hive.addons.store.Key;
import org.bflow.toolbox.hive.addons.utils.TemporaryFileServer;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeDescriptor;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.MessageDialog;

/**
 * Provides a default implementation of a {@link Protocol}.
 * This should cover most of the common requirements for add-ons.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 24.04.10
 * @version 05.08.13
 * 			15.03.15 Modified initialization and validation behaviour
 * 
 */
public class Standardprotocol extends Protocol {

	// class attributes
	/** The name. */
	private String name;

	/** well configured flag. */
	private boolean wellConfigurated = true;

	/** source file. */
	private IFile source;

	/** marker id. */
	private String markerId;
	
	/**
	 * Instance of running protocol; if no instance is running this is null.
	 */
	private static Standardprotocol running;

	/**
	 * Returns the name of the {@linkplain Standardprotocol}.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the {@linkplain Standardprotocol}.
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.Protocol#ReturnToSenderAfterFinish()
	 */
	@Override
	protected boolean ReturnToSenderAfterFinish() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.Protocol#continueThoughException(org.bflow.toolbox.addons.core.model.IComponent, org.bflow.toolbox.addons.core.exceptions.ProtocolException)
	 */
	@Override
	protected boolean continueThoughException(IComponent component,	ProtocolException protex) {

		String errMsg = protex.getMessage();
		
		String error = "Das Protokoll konnte nicht wie geplant ausgeführt werden! "
			+ "Bitte überprüfen Sie Ihre Einstellungen!\r\nFehlermeldung: "+errMsg;
		
		MessageDialog.openError(null, "Kritischer Fehler", error);

		return false;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.Protocol#finish()
	 */
	@Override
	protected void finish() {
		setFinished(true);
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.Protocol#handleReturn(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void handleReturn(Object object) {
		if (object == null) return;

		if ((object instanceof Vector) && !((Vector<?>) object).isEmpty()) {
			if (((Vector<?>) object).firstElement() instanceof IMarker) {
				Vector<IMarker> markers = (Vector<IMarker>) object;
				AddonsPlugin.getInstance().addMarker(markerId, markers);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.Protocol#init()
	 */
	@Override
	protected void init() {
		Standardprotocol.setLogger(Logger.getLogger(this.getName()));
		Standardprotocol.getLogger().addAppender(new ConsoleAppender(new PatternLayout()));
		
		try {
			Standardprotocol.getLogger().addAppender(new FileAppender(new PatternLayout(), Key.KEY_MITAMM_LOG_FILE.getAbsolutePath(), false));
		} catch (IOException e) {
			AddonsPlugin.getInstance().logError("Error on initializing Standardprotocol", e);
		}

		Vector<IComponent> locComp = new Vector<IComponent>();

		for (IComponent component : getComponents()) {
			if (component instanceof FileAnalysisComponent) {
				component = new FileAnalysisComponent((IFile) this.getSource());
			}

			if (component instanceof ShellAnalysisComponent) {
				component = new ShellAnalysisComponent((IFile) this.getSource());
			}

			if (component instanceof EPCMetricsEvaluationComponent) {
				component = new EPCMetricsEvaluationComponent((IFile) this.getSource());
			}
			
			if (component instanceof AttributeAdjustComponent) {
				((AttributeAdjustComponent)component).setProtocol(this);
			}				

			if (component.hasParams()) {
				String compParam = this.getComponentParam(component);
				component.setParams(compParam);
			}

			locComp.add(component);
		}

		this.getComponents().clear();
		this.getComponents().addAll(locComp);

		markerId = this.getName() + "Markers";

		AddonsPlugin.getInstance().deleteMarker(markerId, this.source);
		TemporaryFileServer.init();

		setThread(false);
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.Protocol#setSource(java.lang.Object)
	 */
	@Override
	public void setSource(Object source) {
		this.source = ResourcesPlugin.getWorkspace().getRoot().findFilesForLocationURI(((File) source).toURI())[0];
		super.setSource(source);
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.Protocol#getSource()
	 */
	@Override
	public Object getSource() {
		return this.source;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.Protocol#run()
	 */
	@Override
	public void run() {
		if (!wellConfigurated) return;
		
		running = this;
		super.run();
		running = null;
	}
	
	/**
	 * Returns the running instance of the standardprotocol. If no instance is running
	 * this is null.
	 * @return running instance or null
	 */
	public static Standardprotocol getRunning() {
		return running;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.Protocol#isValid()
	 */
	@Override
	public boolean isValid() {
		if (getComponents().size() == 0) return false;

		init();
		
		for (IComponent compontent : getComponents()) {
			if (!compontent.isValid()) return false;
		}
				
		return true;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.Protocol#isApplicableFor(java.lang.String)
	 */
	@Override
	public boolean isApplicableFor(String diagramEditorFileExtension) {		
		// Iterate over all associated components
		for (IComponent component:getComponents()) {
			
			// Only instances of IDiagramExportComponents are important
			if (component instanceof IDiagramExportComponent) {
				IDiagramExportComponent dec = (IDiagramExportComponent)component;
				IInterchangeDescriptor exd = dec.getExportDescription();
				
				// The interchange descriptor has been removed
				if (exd == null) return false;
				
				// Are the diagram editor file extensions matching?
				String applicableDiagramEditorTypes[] = exd.getApplicableDiagramEditorTypes();
				
				boolean doesMatch = false;
				
				for (int i = 0; i < applicableDiagramEditorTypes.length; i++) {
					String applicableEditorType = applicableDiagramEditorTypes[i];
					if (applicableEditorType.equalsIgnoreCase(diagramEditorFileExtension)) {
						doesMatch = true;
						break;
					}
				}
				
				if (!doesMatch) return false;
				
				// A protocol can only contain one export component, so we're finished here
				break;
			}
		}

		return true;
	}
}

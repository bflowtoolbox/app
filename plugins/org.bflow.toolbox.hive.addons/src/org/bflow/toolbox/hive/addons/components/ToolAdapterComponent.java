package org.bflow.toolbox.hive.addons.components;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.bflow.toolbox.hive.addons.core.exceptions.ComponentException;
import org.bflow.toolbox.hive.addons.core.model.IComponent;
import org.bflow.toolbox.hive.addons.interfaces.IToolAdapterComponent;
import org.bflow.toolbox.hive.addons.utils.TemporaryFileServer;

/**
 * Implements the {@link IToolAdapterComponent} interface.
 * 
 * @author Arian Storch
 * @since 23/10/10
 * @version 08/11/10
 */
public class ToolAdapterComponent implements IToolAdapterComponent {

	private boolean finished;

	private int kind;

	private Object input;

	private Object output;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bflow.toolbox.mitamm.core.model.IComponent#canLinkWith(org.bflow.
	 * toolbox.mitamm.core.model.IComponent)
	 */
	@Override
	public boolean canLinkWith(IComponent component) {
		if (component instanceof PrologRunComponent)
			return true;

		if (component instanceof ToolRunComponent)
			return true;

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.mitamm.core.model.IComponent#finish()
	 */
	@Override
	public void finish() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bflow.toolbox.mitamm.core.model.IComponent#getDescription(java.lang
	 * .String)
	 */
	@Override
	public String getDescription(String abbreviation) {

		if (abbreviation.equalsIgnoreCase("de")) {
			String str = "Schafft eine Brücke zwischen zwei Tools.";

			return str;
		}

		String str = "Creates a bridge between two tools";

		return str;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.mitamm.core.model.IComponent#getDisplayName()
	 */
	@Override
	public String getDisplayName() {
		return "Tool adapter";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.mitamm.core.model.IComponent#hasFinished()
	 */
	@Override
	public boolean hasFinished() {
		return finished;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.mitamm.core.model.IComponent#hasParams()
	 */
	@Override
	public boolean hasParams() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.mitamm.core.model.IComponent#init()
	 */
	@Override
	public void init() {
		finished = false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.mitamm.core.model.IComponent#invoke()
	 */
	@Override
	public void invoke() throws ComponentException {
		try {
			if (kind == IToolAdapterComponent.SHELL2FILE) {
				String stream = ((StringBuffer)input).toString();
				stream = stream.replace("addon:", "").replace("#FS#", "");

				File tmpFile = File.createTempFile("tooladapter", ".tmp");
				
				FileUtils.writeStringToFile(tmpFile, stream);
				
				TemporaryFileServer.registerFile(tmpFile);

				this.output = tmpFile;
			}

			if (kind == IToolAdapterComponent.EQUAL) {				
				String line = ((StringBuffer)input).toString();

				int index = line.indexOf("addon:");
				
				if(index == -1)
					throw new ComponentException("no filename for input source set");
				
				String filename = line.substring(index+6);
				
				filename = filename.replace("#FS#", "");

				this.output = new File(filename);
				
				TemporaryFileServer.registerFile((File)output);
				
				if(output == null)
					throw new ComponentException("file is null");

				/*if (!((File) output).exists())
					throw new ComponentException("input source does not exist: "
							+ output);*/
			}

			if (kind == IToolAdapterComponent.FILE2SHELL) {
				throw new ComponentException("not implemented yet");
			}

		} catch (Exception ex) {
			throw new ComponentException(ex);
		} finally {
			finished = true;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.mitamm.core.model.IComponent#isValid()
	 */
	@Override
	public boolean isValid() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bflow.toolbox.mitamm.core.model.IComponent#setParams(java.lang.String
	 * )
	 */
	@Override
	public void setParams(String param) {
		if (param.equalsIgnoreCase("equal"))
			this.kind = IToolAdapterComponent.EQUAL;

		if (param.equalsIgnoreCase("shell to file"))
			this.kind = IToolAdapterComponent.SHELL2FILE;

		if (param.equalsIgnoreCase("file to shell"))
			this.kind = IToolAdapterComponent.FILE2SHELL;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bflow.toolbox.mitamm.core.model.IComponent#transformInput(java.lang
	 * .Object)
	 */
	@Override
	public void transformInput(Object inputSource) throws ComponentException {
		if (inputSource == null)
			throw new ComponentException("Quelle ist null");

		this.input = inputSource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.mitamm.core.model.IComponent#transformOutput()
	 */
	@Override
	public Object transformOutput() throws ComponentException {
		return output;
	}

}

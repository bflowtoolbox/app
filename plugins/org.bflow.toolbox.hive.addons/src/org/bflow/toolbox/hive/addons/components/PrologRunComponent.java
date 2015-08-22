package org.bflow.toolbox.hive.addons.components;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.bflow.toolbox.hive.addons.core.exceptions.ComponentException;
import org.bflow.toolbox.hive.addons.core.model.IComponent;
import org.bflow.toolbox.hive.addons.interfaces.IPrologRunComponent;
import org.bflow.toolbox.hive.addons.interprolog.AddonsSWISubprocessEngine;
import org.bflow.toolbox.hive.addons.protocols.Standardprotocol;
import org.bflow.toolbox.hive.addons.store.PrologAdditionStore;
import org.bflow.toolbox.hive.addons.store.ToolStore;
import org.bflow.toolbox.hive.addons.utils.PrologListener;
import org.bflow.toolbox.hive.addons.utils.ToolDescriptor;
import org.bflow.toolbox.hive.addons.validation.Rule;
import org.bflow.toolbox.hive.addons.validation.ValidationService;
import org.bflow.toolbox.hive.libs.aprogu.collections.IWhereSelector;
import org.bflow.toolbox.hive.libs.aprogu.collections.ListUtils;
import org.bflow.toolbox.hive.nls.NLUtil;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Display;

import com.declarativa.interprolog.SubprocessEngine;

/**
 * Implements the {@link IPrologRunComponent} for running the SWI-Prolog.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 24/06/10
 * @version 30/10/13
 */
public class PrologRunComponent implements IPrologRunComponent {

	private String toolParam;

	private File fDiagram;
	private File facts = null;

	private SubprocessEngine prologEngine;
	private PrologListener prologEngineListener;

	private boolean finished = false;

	private List<Rule> rules;
	private Vector<String> params;

	private StringBuffer simpleStream;

	private String startCommand = null;
	private String endCommand = null;

	private boolean internal = false;

	/**
	 * Default constructor.
	 */
	public PrologRunComponent() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.mitamm.components.IPrologRunComponent#finish()
	 */
	@Override
	public void finish() {
		if (facts != null && internal)
			facts.deleteOnExit();

		// Dispose prolog engine
		prologEngine.shutdown();
		prologEngine = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bflow.toolbox.mitamm.components.IPrologRunComponent#getDescription
	 * (java.lang.String)
	 */
	@Override
	public String getDescription(String abbreviation) {

		if (abbreviation.equalsIgnoreCase("de")) {
			String str = "Startet den SWI-Prolog-Interpreter. Dieser muss zuvor installiert und bei bflow* registriert sein!";
			return str;
		}

		String str = "Starts the SWI Prolog interpreter. It must be installed and registered within bflow* before!";
		return str;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bflow.toolbox.mitamm.components.IPrologRunComponent#hasFinished()
	 */
	@Override
	public boolean hasFinished() {
		return finished;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.mitamm.components.IPrologRunComponent#init()
	 */
	@Override
	public void init() {
		this.params = new Vector<String>();

		String params[] = this.toolParam.split(" ");

		// Resolving parameters
		for (String str : params) {
			// pl
			if (str.contains("-pl:")) {
				String value = str.substring(4);

				URL url = PrologAdditionStore.getURL(value);

				if (url != null) {
					File prologBaseFile;

					try {
						prologBaseFile = File.createTempFile("epc2009", ".pl");
						prologBaseFile.deleteOnExit();
						FileUtils.copyURLToFile(url, prologBaseFile);

						facts = prologBaseFile;

						internal = true;
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				} else {
					facts = new File(value);

					if (!facts.exists())
						facts = null;
				}
			}

			// p
			if (str.contains("-p:")) {
				String value = str.substring(3);

				if (value.equalsIgnoreCase("$SETUP")
						|| value.equalsIgnoreCase("$DEFAULT")
						|| value.equalsIgnoreCase("$ALL")) {

					String abbr = NLUtil.getActiveLanguageAbbreviation();

					int type = ValidationService.TYPE_ALL;

					if (value.equalsIgnoreCase("$SETUP"))
						type = ValidationService.TYPE_SETUP;

					if (value.equalsIgnoreCase("$DEFAULT"))
						type = ValidationService.TYPE_DEFAULT;

					 // Collecting rules that shall be used 
					rules = ValidationService.getInstance().getRulesByType(abbr, type);
					
					// Only use items with type of 'prolog'
					Iterable<Rule> filteredList = ListUtils.where(rules, new IWhereSelector<Rule>() {
						public boolean where(Rule item) {
							if(StringUtils.isBlank(item.getType()))
								return false;
							return item.getType().equalsIgnoreCase("prolog");
						};
					});
					
					rules = ListUtils.toList(filteredList);

					internal = true;
				} else {
					this.params = new Vector<String>();

					for (String p : str.split(","))
						this.params.add(p);
				}

			}

			// s
			if (str.contains("-s:")) {
				String value = str.substring(3);

				if (!value.isEmpty())
					this.startCommand = value;
			}

			// e
			if (str.contains("-e:")) {
				String value = str.substring(3);

				if (!value.isEmpty())
					this.endCommand = value;
			}
		}

		// start and end command
		if (startCommand == null)
			startCommand = "addon_query";

		if (endCommand == null)
			endCommand = "#query_end#";
		
		// Is there already an engine?
		if(prologEngine == null) {
			String prologExePath = ToolStore.getTool("SWI-Prolog").getPath();
			prologEngine = new AddonsSWISubprocessEngine(prologExePath, false);

			this.simpleStream = new StringBuffer(1024);
			
			prologEngineListener = new PrologListener(simpleStream, endCommand);

			prologEngine.addPrologOutputListener(prologEngineListener);
			prologEngine.addPrologStdoutListener(prologEngineListener);
		}
		
		// Reset the listener
		prologEngineListener.reset();

		finished = false;
	}

	@Override
	public void invoke() throws ComponentException {
		try {

			if (facts == null)
				throw new ComponentException(
						"No Prolog program set or Prolog programm cannot be found. Look at the parameter for the Prolog Run component (-pl:Filename)");

			try {
				String factsStream = FileUtils.readFileToString(facts);
				
				// Build the test start query
				String testStartQueryAddition = buildTestStartQueryAddition(startCommand);
				
				// Add it to the facts file
				IOUtils.write(testStartQueryAddition, new FileOutputStream(facts, true));

				// Very simple contains test for the start command
				if (!factsStream.contains(startCommand)) {
					String message = String.format("The called Prolog program must contain a clause \"%s\"", 
									startCommand);
					throw new ComponentException(message);
				}

				// Very simple contains test for the end command
				if (!factsStream.contains(endCommand)) {
					String message = String.format("The called Prolog program must contain a clause \"%s\"", 
							endCommand);
					throw new ComponentException(message);
				}

			} catch (IOException e1) {
				throw new ComponentException(e1);
			}

			prologEngine.consultAbsolute(facts);
			prologEngine.consultAbsolute(fDiagram);
			// this.engine.waitUntilIdle();

			String ruleParam = StringUtils.EMPTY;
			List<String> ruleNames = new ArrayList<String>(0);
			
			if (internal) {
				if (rules.size() < 1)
					return;

				ruleNames = toStringList(rules);
			} else {
				if (params.size() > 0)
					ruleNames = params;
			}

			ruleParam = joinRuleNames(ruleNames);
			
			String lang = NLUtil.getActiveLanguageAbbreviation();

			// Abbreviation fix
			lang = lang.split("_")[0];
			
			// Build the execute query
			String executeQuery = String.format("%s(%s,[%s]).", 
					"test_startquery", lang, ruleParam); // test_startquery was former startCommand

			// Check support flag for prolog debug 
			if(isPrologDebug()) {
				ErrorDialog.openError(Display.getCurrent().getActiveShell(), "Prolog query", executeQuery, 
						new Status(IStatus.INFO, "addons.prolog.debug", "-"));
			}
			
			// Start the engine
			prologEngine.sendAndFlushLn(executeQuery);
			
			// Variable to count the loops
			int loopCount = 0;
			boolean abnormalTermination = false;

			// Loop and sleep until the stream is finished
			while (!prologEngineListener.isStreamFinished()) {
				try {
					Thread.sleep(1000);

					// if there has been more than 60s passed there is probably
					// something wrong
					if (++loopCount > 60) {
						abnormalTermination = true;
						break;
					}
				} catch (InterruptedException e) {
					abnormalTermination = true;
					break;
				}
			}
			
			// Process did not start
			if(prologEngineListener.hasNoStartCommand()) {
				String message = String.format("The called Prolog program must contain a clause \"%s\"", 
						startCommand);

				throw new ComponentException(message);
			}
			
			// Process ended with exception
			if(prologEngineListener.hasTerminatedByException()) {
				String message = String.format("An exception occurred! Check the following Prolog output: %n%s", 
						simpleStream.toString());
				throw new ComponentException(message);
			}

			// Process ended abnormal
			if (abnormalTermination) {
				throw new ComponentException("Process did not exit clearly!");
			}

		} catch (Exception ex) {
			throw new ComponentException(ex);
		} finally {			
			finished = true;
			Standardprotocol.getLogger().info(simpleStream.toString());
			// Hint: The prologEngine will be disposed at finish()
		}
	}

	@Override
	public void transformInput(Object inputSource) throws ComponentException {
		if (inputSource == null)
			throw new ComponentException("Input source is null!");

		if (!(inputSource instanceof File))
			throw new ComponentException(
					"input source has not expected format.");

		fDiagram = (File) inputSource;
	}

	@Override
	public Object transformOutput() throws ComponentException {
		return simpleStream;
	}

	@Override
	public boolean isValid() {
		ToolDescriptor td = ToolStore.getTool("SWI-Prolog");

		if (td != null && td.getPath() != null && !td.getPath().isEmpty())
			return true;

		return false;
	}

	@Override
	public String getDisplayName() {
		return "Prolog run";
	}

	@Override
	public boolean canLinkWith(IComponent component) {
		if (component instanceof DiagramExportComponent)
			return true;

		if (component instanceof ToolAdapterComponent)
			return true;

		return false;
	}

	@Override
	public boolean hasParams() {
		return true;
	}

	@Override
	public void setParams(String param) {
		this.toolParam = param;
	}
	
	/**
	 * Selects the IDs of the rules within the given collection and builds
	 * a new string collection with them.
	 * 
	 * @param ruleCollection Collection of rules
	 * @return Collection rule IDs
	 */
	private List<String> toStringList(List<Rule> ruleCollection) {
		List<String> stringCollection = new ArrayList<String>(ruleCollection.size());
		for(int i = -1; ++i < ruleCollection.size();)
			stringCollection.add(ruleCollection.get(i).getId());
		
		return stringCollection;
	}
	
	/**
	 * Joins the given Collection of strings. Comma is used 
	 * as separator.
	 * 
	 * @param ruleCollection Collection of rule names
	 * @return Joined string
	 */
	private String joinRuleNames(List<String> ruleCollection) {
		String result = StringUtils.EMPTY;
		for(int i = -1; ++i < ruleCollection.size();) {
			String ruleValue = ruleCollection.get(i);
			result = addRuleToString(result, ruleValue);
		}
		return result;
	}
	
	/**
	 * Adds the 'newRule' value to the 'ruleString' string. Both will be 
	 * separated with a comma. If the 'newRule' value is null or empty the 
	 * origin 'ruleString' will be returned.
	 * 
	 * @param ruleString String that will be extended
	 * @param newRule String that will be added to the other string
	 * @return Concatenated string
	 */
	private String addRuleToString(String ruleString, String newRule) {
		if(StringUtils.isBlank(newRule))
			return ruleString;
		
		// Prolog can only handle rule names that start with lower case
		String uncapitalizedRuleName = uncapitalizeRuleName(newRule);
		
		// Is this value the first?
		if(StringUtils.isBlank(ruleString))
			return uncapitalizedRuleName;
		
		String _ruleString = String.format("%s,%s", ruleString, uncapitalizedRuleName);
		return _ruleString;
	}
	
	/**
	 * Uncapitalizes the rule name.
	 * 
	 * @param ruleName Rule Name
	 * @return Uncapitalized rule name
	 */
	private String uncapitalizeRuleName(String ruleName) {
		return StringUtils.uncapitalize(ruleName);
	}
	
	/**
	 * Returns true if the platform has been started with the program argument '-prologDebug'.
	 * 
	 * @return true or false
	 */
	private boolean isPrologDebug() {
		String[] args = Platform.getCommandLineArgs();
		for(int i = -1; ++i < args.length;) {
			if(args[i].equals("-prologDebug"))
				return true;
		}
		return false;
	}
	
	private String buildTestStartQueryAddition(String startCommand) {
		String testStartQueryClause = 
			String.format(BasicTestAddonQueryClause, startCommand, startCommand);
		
		return testStartQueryClause;
	}
	
	private static final String BasicTestAddonQueryClause = 
		"%ntest_startquery(Language,Parameters) :- (clause(%s(_,_),_),%s(Language,Parameters));print('halt(1)').";
}

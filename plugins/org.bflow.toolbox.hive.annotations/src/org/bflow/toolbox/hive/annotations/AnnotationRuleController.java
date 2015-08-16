package org.bflow.toolbox.hive.annotations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.bflow.toolbox.hive.attributes.internal.InterchangeAttributeProvider;
import org.bflow.toolbox.hive.nls.NLSupport;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget.Direction;
import org.eclipse.jface.dialogs.MessageDialog;


/**
 * This class is used to control Annotation Rules, which consist of
 * {@link RuleEntry} instances. It holds all listeners, which should react on
 * changes (add,remove,edit) of the rules. It is responsible for the reading and
 * writing to and from .xml files.
 * 
 * @author Felix Hoess <fhoess@users.sf.net>
 * @since 25.06.2015
 *
 */
public class AnnotationRuleController {

	//TODO implement save and load (To many read and write operations through add and remove, file should only be written if and only if the diagram is saved)
	/**
	 * isDirty is true, if the rule (stored internal in an ArrayList) has
	 * changed
	 */
	private boolean isDirty = false;
	/**
	 * is true if the rules (stored as a ArrayList) are equals to the rule list
	 * stored in an external xml file
	 */
	private boolean isSynchronized = false;

	/**
	 * Stores the list of all annotation rules
	 */
	private HashMap<String, List<RuleEntry>> rules = new HashMap<String, List<RuleEntry>>();


	private static AnnotationRuleController instance = null;
	private ArrayList<IAnnotationRuleListener> ruleListener;

	private InterchangeAttributeProvider iap;

	/**
	 * Checks whether the attribute's names of a model element (by id as String)
	 * match with the annotation rules. Returns a Set of
	 * {@link ShapeDecorationInfo} which included the appropriate decoration
	 * position and a String for the resource file name. Returns an empty set if
	 * no attribute name matches to the convention. <br>
	 * The rules are saved in the AnnotationLogic.csv file.
	 * 
	 * 
	 * @param id
	 *            the id of the Model Element
	 * @return Set<ShapeDecorationInfo> HashSet<ShapeDecorationInfo>
	 */
	public Set<ShapeDecorationInfo> getAnnotationsForRules(String id) {
		String ruleAttributeName = "";
		String ruleValue = "";
		String ruleOperator = "";
		String ruleDirection = "CENTER"; //Center is default
		String ruleFilename = "";
		iap = new InterchangeAttributeProvider();
		boolean applyRule = false;
		double dRule = 0d;
		double dAttribute = 0d;
		boolean dAttributeIsDigit = true;
		boolean dRuleIsDigit = true;
		Direction iconDirection = null;
		Locale locale = new Locale(Platform.getNL());
		Collator col = Collator.getInstance(locale);

		Set<ShapeDecorationInfo> annotations = new HashSet<ShapeDecorationInfo>();
		HashMap<String, String> attributes = (HashMap<String, String>) iap
				.getAttributesFor(null, id);

		if (attributes != null && !attributes.isEmpty()) {
			for (RuleEntry rule : AnnotationRuleController.getInstance()
					.getRules()) {
				if (!rule.isActive())
					continue;
				ruleAttributeName = rule.getAttributeName();
				ruleValue = rule.getAttributeValue();
				ruleOperator = rule.getOperator();
				ruleDirection = rule.getDirection();
				ruleFilename = rule.getFilename();
				if (attributes.containsKey(ruleAttributeName)) {
					String value = attributes.get(ruleAttributeName);

					try {
						dAttribute = Double.parseDouble(value);
						dAttributeIsDigit = true;
					} catch (NumberFormatException e) {
						dAttributeIsDigit = false;
					}
					try {
						dRule = Double.parseDouble(ruleValue);
						dRuleIsDigit = true;
					} catch (NumberFormatException e) {
						dRuleIsDigit = false;
					}
					switch (ruleOperator) {
					case "=":

						if (dAttributeIsDigit && dRuleIsDigit) {
							applyRule = dAttribute == dRule;

						} else if (!dAttributeIsDigit && !dRuleIsDigit) {
							applyRule = col.compare(value, ruleValue) == 0;
						} else {
							applyRule = false;
						}

						break;
					case "<":
						if (dAttributeIsDigit && dRuleIsDigit) {
							applyRule = dAttribute < dRule;

						} else if (!dAttributeIsDigit && !dRuleIsDigit) {
							applyRule = col.compare(value, ruleValue) < 0;
						} else {
							applyRule = false;
						}
						break;
					case ">":
						if (dAttributeIsDigit && dRuleIsDigit) {
							applyRule = dAttribute > dRule;

						} else if (!dAttributeIsDigit && !dRuleIsDigit) {
							applyRule = col.compare(value, ruleValue) > 0;
						} else {
							applyRule = false;
						}
						break;
					case "\u2260":
						if (dAttributeIsDigit && dRuleIsDigit) {
							applyRule = dAttribute != dRule;

						} else if (!dAttributeIsDigit && !dRuleIsDigit) {
							applyRule = col.compare(value, ruleValue) != 0;
						} else {
							applyRule = false;
						}
						break;
					case "\u2265":
						if (dAttributeIsDigit && dRuleIsDigit) {
							applyRule = dAttribute >= dRule;

						} else if (!dAttributeIsDigit && !dRuleIsDigit) {
							applyRule = col.compare(value, ruleValue) >= 0;
						} else {
							applyRule = false;
						}
						break;
					case "\u2264":
						if (dAttributeIsDigit && dRuleIsDigit) {
							applyRule = dAttribute <= dRule;

						} else if (!dAttributeIsDigit && !dRuleIsDigit) {
							applyRule = col.compare(value, ruleValue) <= 0;
						} else {
							applyRule = false;
						}
						break;
					}
					if (applyRule) {
						switch (ruleDirection) {
						case "CENTER":
							iconDirection = IDecoratorTarget.Direction.CENTER;
							break;
						case "NORTH":
							iconDirection = IDecoratorTarget.Direction.NORTH;
							break;
						case "NORTH_EAST":
							iconDirection = IDecoratorTarget.Direction.NORTH_EAST;
							break;
						case "NORTH_WEST":
							iconDirection = IDecoratorTarget.Direction.NORTH_WEST;
							break;
						case "SOUTH":
							iconDirection = IDecoratorTarget.Direction.SOUTH;
							break;
						case "SOUTH_EAST":
							iconDirection = IDecoratorTarget.Direction.SOUTH_EAST;
							break;
						case "SOUTH_WEST":
							iconDirection = IDecoratorTarget.Direction.SOUTH_WEST;
							break;
						case "EAST":
							iconDirection = IDecoratorTarget.Direction.EAST;
							break;
						case "WEST":
							iconDirection = IDecoratorTarget.Direction.WEST;
							break;
						default:
							iconDirection = IDecoratorTarget.Direction.NORTH_EAST;
							break;
						}
						annotations.add(new ShapeDecorationInfo(iconDirection,
								ruleFilename));
					}
				}
			}
		}

		return annotations;
	}

	/**
	 * Sets the given values to the rule entry (if you want to edit a rule).
	 * then writes to the xml file
	 * 
	 * @param inputFilename
	 * @param inputDirection
	 * @param inputValue
	 * @param inputOperator
	 * @param inputAttributeName
	 * @param inputCategory
	 * @param entry
	 */
	public void updateRule(RuleEntry entry, String inputCategory,
			String inputAttributeName, String inputOperator, String inputValue,
			String inputDirection, String inputFilename, String inputRuleName) {
		for (String str : rules.keySet()) {
			if (rules.get(str).contains(entry)) {
		//set new values to the rule
			entry.setCategory(inputCategory);
			entry.setDirection(inputDirection);
			entry.setFilename(inputFilename);
			entry.setAttributeName(inputAttributeName);
			entry.setOperator(inputOperator);
			entry.setAttributeValue(inputValue);
				entry.setRuleName(inputRuleName);
			isDirty = true;
			isSynchronized = false;
				write(str);

			}
		}
	}

	/**
	 * Sets the isActive column of a rule Then writes all rules to the xml file
	 * 
	 * @param entry
	 * @param isActive
	 */
	public void updateRule(RuleEntry entry, boolean isActive) {
		for (String str : rules.keySet()) {
			if (rules.get(str).contains(entry)) {
		entry.setActive(isActive);
		isDirty = true;
		isSynchronized = false;
				write(str);
			}
		}

	}

	/**
	 * Returns a refreshed list of RuleEntries
	 */
	public List<RuleEntry> getRules() {
		ArrayList<RuleEntry> allRuleList = new ArrayList<RuleEntry>();
		if (!isSynchronized && !isDirty) {
			FileInputStream fis = null;
			InputStreamReader reader = null;
			rules.clear();
			for (String filename : getXMLFileNames()) {
				rules.put(filename, new ArrayList<RuleEntry>());
				ArrayList<RuleEntry> list = new ArrayList<RuleEntry>();
			try {
				fis = new FileInputStream(
							AnnotationLauncherConfigurator
									.getANNOTATIONLOGIC_FOLDER_PATH()
									+ filename
									+ AnnotationLauncherConfigurator
											.getDefaultFileExtension());
			} catch (FileNotFoundException e) {
				//file not found. create new empty xml file
				File newFile = new File(
						AnnotationLauncherConfigurator.getRULES_XML_PATH());
				try {
					newFile.createNewFile();
						rules.put(filename, new ArrayList<RuleEntry>());
					isSynchronized = true;
					showWarningDialog();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
			if (fis == null) {
				try {
					fis = new FileInputStream(
							AnnotationLauncherConfigurator.getRULES_XML_PATH());
				} catch (FileNotFoundException e1) {
					//access on file denied , create empty rule list
						rules.put(filename, new ArrayList<RuleEntry>());
					showWarningDialog();
					e1.printStackTrace();
				}
			}
			if (fis != null) {
			try {
				reader = new InputStreamReader(fis, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

				if (!isSynchronized) {
			Unmarshaller um;
			try {
				JAXBContext context = JAXBContext.newInstance(XMLRuleEntries.class);
						um = context.createUnmarshaller();
				XMLRuleEntries xmlrules = (XMLRuleEntries) um.unmarshal(reader);
				list = (ArrayList<RuleEntry>) xmlrules.getRule();
			} catch (JAXBException e) {
							showCorruptionDialog(filename);
							continue;
			}
				}
					if (list != null) {
						rules.put(filename, list);
						allRuleList.addAll(list);
					}

				}
			}
			isSynchronized = true;
			isDirty = false;
			return allRuleList;
		} else
			// in the three other cases (isSynchronized && (isDirty || !isDirty)) just return the list
		{
			for (String str : rules.keySet()) {
				allRuleList.addAll(rules.get(str));
			}
			return allRuleList;
		}
	}

	/**
	 * returns a String[] of all filenames with .xml in the (default or custom)
	 * AnnotationIcons/ folder.
	 * 
	 * @return
	 */
	public String[] getXMLFileNames() {
		File folder = new File(
				AnnotationLauncherConfigurator
				.getANNOTATIONLOGIC_FOLDER_PATH());
		folder.mkdirs();
		File[] listOfFiles = folder.listFiles();
		if (listOfFiles == null || listOfFiles.length == 0) {//In this case there is no file available

			String[] defaultXmlFileName = new String[1];
			defaultXmlFileName[0] = AnnotationLauncherConfigurator
					.getRULES_XML_PATH();
			File newFile = new File(
					AnnotationLauncherConfigurator.getRULES_XML_PATH());
			try {
				newFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return defaultXmlFileName;
		} else {
			ArrayList<String> xmlFileNames = new ArrayList<String>();
			for (int i = 0; i < listOfFiles.length; i++) {

				if (listOfFiles[i].isFile()) {
					String name = listOfFiles[i].getName();
					String extension = "." + getFileExtension(listOfFiles[i]);
					if (extension != null) {
						if (extension.equals(AnnotationLauncherConfigurator
								.getDefaultFileExtension())) {
							xmlFileNames
									.add(name.substring(0, name.length()
											- AnnotationLauncherConfigurator
													.getDefaultFileExtension()
													.length()));
						}
					}
				}
			}

			return xmlFileNames.toArray(new String[0]);
		}
	}

	/**
	 * Add a annotation rule to the internal rule set and the external xml file
	 * 
	 * @param entry
	 */
	public void addRule(RuleEntry entry, String filename) {
		if (!rules.containsKey(filename)) {
			rules.put(filename, new ArrayList<RuleEntry>());
			isSynchronized = false;
		}

		if (!rules.get(filename).contains(entry)) {
			rules.get(filename).add(entry);
			isDirty = true;
			isSynchronized = false;
			write(filename);
		}

	}

	/**
	 * Removes a RuleEntry in the internal rule set and external xml file
	 * 
	 * @param entry
	 */
	public void remove(RuleEntry entry) { 
		for (String str:rules.keySet()){
			if (rules.get(str).contains(entry)) {
				rules.get(str).remove(entry);
				isDirty = true;
				isSynchronized = false;
				write(str);
			}
		}
		

	}



	/**
	 * 
	 * writes the current rule set to a file with the given name. (extends with
	 * ".xml")
	 *
	 */
	private void write(String filename) {
		//init new xml file

		if (!isSynchronized && isDirty) {
		File xmlfile = new File(
					AnnotationLauncherConfigurator
							.getANNOTATIONLOGIC_FOLDER_PATH()
							+ filename
							+ AnnotationLauncherConfigurator
									.getDefaultFileExtension());
		xmlfile.getParentFile().mkdirs();
		try {
			xmlfile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
			// create JAXB context and instantiate marshaller
			JAXBContext context;
			XMLRuleEntries xmlRules = new XMLRuleEntries();
			xmlRules.setRule(rules.get(filename));
			try {
				context = JAXBContext.newInstance(XMLRuleEntries.class);
				Marshaller m = context.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				// Write to File
			m.marshal(xmlRules, xmlfile);

			} catch (JAXBException e1) {
				e1.printStackTrace();
			}
			isDirty = false;
			isSynchronized = true;
			//	TODO		notifyListeners(diagramEditor);
		}

	}

	/**
	 * Shows a Dialog with a warning if a file can't be found
	 */
	private void showWarningDialog() {
		MessageDialog dialog = new MessageDialog(
				null,
				NLSupport.AnnotationViewPart_Annotation_Warning_FileNotFound_Headline,
				null,
				AnnotationLauncherConfigurator.getRULES_XML_PATH()
						+ "\n\n"
						+ NLSupport.AnnotationViewPart_Annotation_Warning_FileNotFound_Message,
				MessageDialog.ERROR, "OK".split(" "), 0);
		dialog.open();
	
	}

	/**
	 * Shows a Dialog with a message that the given file is corrupted (Doesn't
	 * fit to the expected .xml format)
	 * 
	 * @param filename
	 */
	private void showCorruptionDialog(String filename) {
		MessageDialog dialog = new MessageDialog(
				null,
				NLSupport.AnnotationViewPart_Annotation_Warning_FileCorrupted_Headline,
				null,
				AnnotationLauncherConfigurator.getANNOTATIONLOGIC_FOLDER_PATH()
						+ filename
						+ AnnotationLauncherConfigurator
								.getDefaultFileExtension()
						+ "\n\n"
						+ NLSupport.AttributeFilterViewPart_Annotation_Warning_FileCorrupted_Message,
				MessageDialog.ERROR, "OK".split(" "), 0);
		dialog.open();
	
	}

	private AnnotationRuleController() {
		ruleListener = new ArrayList<IAnnotationRuleListener>();
	}

	public static AnnotationRuleController getInstance() {

		if (instance == null) {
			instance = new AnnotationRuleController();
		}
		return instance;
	}

	/**
	 * register an Listener, which should react on changes in the rule set
	 * 
	 * @param listener
	 */
	public void register(IAnnotationRuleListener listener) {
		if (!ruleListener.contains(listener))
			ruleListener.add(listener);
	}

	/**
	 * unregister the listener
	 * 
	 * @param listener
	 */
	public void unregister(IAnnotationRuleListener listener) {
		if (ruleListener.contains(listener))
			ruleListener.remove(listener);
	}

	/**
	 * Notify all listeners to a change in the rule set
	 * 
	 * @param diagramEditor
	 */
	public void notifyListeners(DiagramEditor diagramEditor) {
		for (IAnnotationRuleListener lis : ruleListener) {
			AnnotationRuleEvent event = new AnnotationRuleEvent();
			event.diagramEditor = diagramEditor;
			lis.noticeAnnotationFileChange(event);
		}
	}

	/**
	 * separates the file extension of the given file
	 * 
	 * @param file
	 * @return
	 */
	private String getFileExtension(File file) {
		String ext = null;
		String s = file.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
	}

}

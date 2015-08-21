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
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.bflow.toolbox.hive.attributes.AttributeFile;
import org.bflow.toolbox.hive.attributes.AttributeFileRegistry;
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

	/**
	 * stores whether a category should be sorted ascending by rule names or as
	 * a sequence of how they are created
	 */
	private HashMap<String, Boolean> sortingOrderForCategory = new HashMap<String, Boolean>();


	private static AnnotationRuleController instance = null;
	private ArrayList<IAnnotationRuleListener> ruleListener;

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
		
		boolean applyRule = false;
		double dRule = 0d;
		double dAttribute = 0d;
		boolean dAttributeIsDigit = true;
		boolean dRuleIsDigit = true;
		Direction iconDirection = null;
		Locale locale = new Locale(Platform.getNL());
		Collator col = Collator.getInstance(locale);

		Set<ShapeDecorationInfo> annotations = new HashSet<ShapeDecorationInfo>();
		
		HashMap<String, String> attributes = (HashMap<String, String>) getAttributesOfActiveModel(id);

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
	 * Returns the attributes collection of the model element with the given id.
	 * 
	 * @param elementId
	 *            Id of the element
	 * @return Collection of attributes
	 */
	private Map<String, String> getAttributesOfActiveModel(String elementId) {
		AttributeFile file = AttributeFileRegistry.getInstance().getActiveAttributeFile();
		if (file == null) return new HashMap<String, String>();
		return file.get(elementId);
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
	 * @param sortCategoryASC
	 *            true, if entries of the category should be sorted in ascending
	 *            order
	 */
	public void updateRule(RuleEntry entry, String inputCategory,
			String inputAttributeName, String inputOperator, String inputValue,
			String inputDirection, String inputFilename, String inputRuleName,
			Boolean sortCategoryASC) {
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
				sortingOrderForCategory.put(inputCategory, sortCategoryASC);
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
			sortingOrderForCategory.clear();
			for (String filename : getXMLFilePathNames()) {
				sortingOrderForCategory.put(filename, false);
				rules.put(filename, new ArrayList<RuleEntry>());
				ArrayList<RuleEntry> list = new ArrayList<RuleEntry>();
			try {
				fis = new FileInputStream(
							AnnotationLauncherConfigurator
									.getANNOTATIONLOGIC_FOLDER_PATH()
									+ filename);
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
							sortingOrderForCategory.put(filename,
									xmlrules.isSortASC());
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
					String extension = getFileExtension(listOfFiles[i]);
					if (extension != null) {
						if (extension.equals(AnnotationLauncherConfigurator
								.getDefaultFileExtension())) {
							xmlFileNames
									.add(name.substring(0, name.length()
													- (1 + AnnotationLauncherConfigurator
													.getDefaultFileExtension()
															.length())));
						}
					}
				}
			}

			return xmlFileNames.toArray(new String[0]);
		}
	}

	/**
	 * returns all filenames of xmls which are stored in the AnnotationIcons
	 * folder (or other default folder chosen by the applicant in the bflow.ini)
	 * and its sub folders
	 * 
	 * @return
	 */
	public String[] getXMLFilePathNames() {
		ArrayList<String> result = new ArrayList<String>(50);
		String folderPath = AnnotationLauncherConfigurator.getANNOTATIONLOGIC_FOLDER_PATH();
		File file = new File(folderPath);
		File[] listOfFiles = file.listFiles();
		result = getXMLFilePathNames("", listOfFiles);
		return result.toArray(new String[0]);
	}

	/**
	 * recursive submethod of {@link getAllAvailableImgNames()} to find rule
	 * .XML files in sub folders
	 * 
	 * @param path
	 * @param listOfFiles
	 * @return
	 */
	private ArrayList<String> getXMLFilePathNames(String path, File[] listOfFiles) {
		ArrayList<String> result = new ArrayList<String>();
		if (listOfFiles == null) return result;
		
		String name = path;
		int i = 0;
		while (i < listOfFiles.length) {

			name += listOfFiles[i].getName();

			if (listOfFiles[i].isDirectory()) {
				File file = new File(AnnotationLauncherConfigurator.getANNOTATIONLOGIC_FOLDER_PATH() + "/" + name);
				File[] listFiles = file.listFiles();
				result.addAll(getXMLFilePathNames(name + "/", listFiles));
			}
			String extension = ImageFileChooserUtils.getExtension(listOfFiles[i]);
			if (extension != null) {
				if (extension.equals(AnnotationLauncherConfigurator.getDefaultFileExtension())) {
					result.add(name);
				}
			}
			name = path;
			i++;

		}
		return result;

	}

	/**
	 * returns all filenames of images which are stored in the AnnotationIcons
	 * folder (or other default folder chosen by the applicant in the bflow.ini)
	 * and its sub folders
	 * 
	 * @return
	 */
	public String[] getAllAvailableImgNames() {
		ArrayList<String> result = new ArrayList<String>(50);
		String folderPath = AnnotationLauncherConfigurator.getANNOTATIONLOGIC_FOLDER_PATH();

		File file = new File(folderPath);
		File[] listOfFiles = file.listFiles();
		result = getAllAvailableImgNames("", listOfFiles);
		return result.toArray(new String[0]);
	}

	/**
	 * recursive submethod of {@link getAllAvailableImgNames()} to find images
	 * in sub folders
	 * 
	 * @param path
	 * @param listOfFiles
	 * @return
	 */
	private ArrayList<String> getAllAvailableImgNames(String path, File[] listOfFiles) {
		ArrayList<String> result = new ArrayList<String>();
		if (listOfFiles == null) return result;
		
		String name = path;
		int i = 0;
		while (i < listOfFiles.length) {

			name += listOfFiles[i].getName();

			if (listOfFiles[i].isDirectory()) {
				File file = new File(AnnotationLauncherConfigurator.getANNOTATIONLOGIC_FOLDER_PATH() + "/" + name);
				File[] listFiles = file.listFiles();
				result.addAll(getAllAvailableImgNames(name + "/", listFiles));
			}
			String extension = ImageFileChooserUtils.getExtension(listOfFiles[i]);
			if (extension != null) {
				if (extension.equals(ImageFileChooserUtils.tiff)
						|| extension.equals(ImageFileChooserUtils.tif)
						|| extension.equals(ImageFileChooserUtils.gif)
						|| extension.equals(ImageFileChooserUtils.jpeg)
						|| extension.equals(ImageFileChooserUtils.jpg)
						|| extension.equals(ImageFileChooserUtils.png)) {
					result.add(name);
				}
			}
			name = path;
			i++;

		}
		return result;

	}

	/**
	 * Add a annotation rule to the internal rule set and the external xml file
	 * 
	 * @param entry
	 * @param sortCategoryASC
	 *            true, if entries of its category should be sorted
	 *            alphabetically ascending
	 */
	public void addRule(RuleEntry entry, String xmlfilename,
			Boolean sortCategoryASC) {
		if (!rules.containsKey(xmlfilename)) {
			rules.put(xmlfilename, new ArrayList<RuleEntry>());
			isSynchronized = false;
		}

		if (!rules.get(xmlfilename).contains(entry)) {
			rules.get(xmlfilename).add(entry);
			sortingOrderForCategory.put(xmlfilename, sortCategoryASC);
			isDirty = true;
			isSynchronized = false;
			write(xmlfilename);
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
							+ filename);
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
			xmlRules.setSortASC(sortingOrderForCategory.get(filename));
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
				NLSupport.AnnotationViewPart_Annotation_Warning_FileNotFound_Message
				+ "\n\n"
				+ AnnotationLauncherConfigurator.getRULES_XML_PATH(),
				MessageDialog.INFORMATION, "OK".split(" "), 0);
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

	/**
	 * Returns the saved sorting order. This is either ascending by the rule
	 * name or in the order in which the entries were made.
	 * 
	 * @param categoryName
	 *            name of the category/xml file name
	 * @return true, if ascending order
	 */
	public boolean getSortingOrderForCategory(String categoryName) {

		if (sortingOrderForCategory.containsKey(categoryName))
			return sortingOrderForCategory.get(categoryName);
		else
			return false;
	}

	/**
	 * returns the path of the xml file for the given category (eg.
	 * "risk/risk.xml" for category "risk") or ("annotations.xml" for category
	 * "annotations") or ("Security/Security.xml" for category "Security")
	 * 
	 * If the category is not in use. it returns the input string and adds
	 * ".xml" (or the default file extension)
	 * 
	 * @param inputCategory
	 *            string of the name of the category
	 * @return
	 */
	public String getXMLFilePathForCategory(String inputCategory) {
		String result = "";
		boolean categoryInUse = false;
		for (String categoryWithPath : rules.keySet()) {
			if (categoryWithPath.contains(inputCategory)) {
				result = categoryWithPath;
				categoryInUse = true;
				break;
			}
		}
		if (!categoryInUse) {
			result = inputCategory + "."
					+ AnnotationLauncherConfigurator.getDefaultFileExtension();
		}
		return result;
	}

}

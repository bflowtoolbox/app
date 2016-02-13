package org.bflow.toolbox.hive.attributefilter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bflow.toolbox.hive.attributes.AttributeFile;

/**
 * This class encapsulates a bunch of {@link AttributeFile}s. For each file with
 * the extension .csv in the default filter folder an AttributeFile is created
 * and stored in a HashSet with its filename as key
 * 
 * @author Felix Hoess <fhoess@users.sf.net>
 * @since 23.06.2015
 *
 */
public class AttributeFilterController {

	private static AttributeFilterController instance;

	private HashMap<String, AttributeFilter> filterSet;

	/**
	 * Constructor.
	 * 
	 * @param diagramEditPart
	 *            diagram edit part
	 */
	private AttributeFilterController() {
		super();
		filterSet = new HashMap<String, AttributeFilter>();

		for (String str : getFilterFileNames()) {
			//for each file in the default (or custom) folder create a AttributeFilter
			filterSet.put(str, new AttributeFilter(str));
		}

	}

	/**
	 * searches the default filter folder for files with the default file
	 * extension (currently .csv) and returns a String[] with all filenames
	 * 
	 * @return
	 */
	private String[] getFilterFileNames() {
		File folder = new File(FilterLauncherConfigurator.getInstance()
				.getDefaultFilterFolderPath());
		folder.mkdirs();
		File[] listOfFiles = folder.listFiles();
		if (listOfFiles == null || listOfFiles.length == 0) {
			String[] defaultArray = new String[1];
			defaultArray[0] = FilterLauncherConfigurator.getInstance()
					.getDefaultFilename();
			File newFile = new File(FilterLauncherConfigurator.getInstance()
					.getDefaultFilterFilePath());
			try {
				newFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return defaultArray;
		} else {

			ArrayList<String> filterFilesNames = new ArrayList<String>();
		for (int i = 0; i < listOfFiles.length; i++) {

			if (listOfFiles[i].isFile()) {
				String name = listOfFiles[i].getName();
					String extension = "." + getExtension(listOfFiles[i]);
				if (extension != null) {
					if (extension.equals(FilterLauncherConfigurator
							.getInstance()
							.getFileType())) {
							filterFilesNames.add(name.substring(0,
									name.length()
									- FilterLauncherConfigurator.getInstance()
													.getFileType().length()));
					}
				}
			}
		}
			return filterFilesNames.toArray(new String[0]);
		}
	}

	/**
	 * separates the file extension of the given file
	 * 
	 * @param file
	 * @return
	 */
	private String getExtension(File file) {
		String ext = null;
		String s = file.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
	}

	public static AttributeFilterController getInstance() {
		if (instance == null)
			instance = new AttributeFilterController();

		return instance;
	}


	/**
	 * Adds a filter entry with a given key, operator (!= = < > >= <= ) and
	 * value. Makes this filter active (default). The rule is stored in the
	 * default Filter.csv file.
	 * 
	 * @param key
	 * @param operator
	 * @param value
	 * @param filename
	 */
	public void add(String key, String operator, String value, String filename) {
		if (filterSet.containsKey(filename))
		filterSet.get(filename).add(key, operator, value);
	}

	/**
	 * removes the FilterEntry (in the first occurrence in the Set of
	 * AttributeFilters). So if more than one filter file .csv are in use, the
	 * entry is removed in the file with the first occurrence.
	 * 
	 * @param entry
	 */
	public void remove(FilterEntry entry) {
		for (String filename : filterSet.keySet()) {
			if (filterSet.get(filename).remove(entry))
				return;

		}
	}

	/**
	 * Updates the given entry (if it is already contained in the list of
	 * filters) in the first occurrence of {@link AttributeFilter}s. (So in the
	 * first .csv-file which holds the given entry)
	 * 
	 * @param entry
	 */
	public void updateEntry(FilterEntry entry) {
		for (String filename : filterSet.keySet()) {
			if (filterSet.get(filename).updateEntry(entry))
				return;
		}

	}

	/**
	 * returns all FilterEntries of all .csv files
	 * 
	 * @return
	 */
	public ArrayList<FilterEntry> getAllFilters() {
		ArrayList<FilterEntry> allFilterEntries = new ArrayList<FilterEntry>();
		for (String filename : filterSet.keySet()) {
			AttributeFilter filter = filterSet.get(filename);
			for (FilterEntry entry : filter.getAllFilters()) {
				if (!allFilterEntries.contains(entry))
					allFilterEntries.add(entry);
			}

		}

		return allFilterEntries;
	}



}

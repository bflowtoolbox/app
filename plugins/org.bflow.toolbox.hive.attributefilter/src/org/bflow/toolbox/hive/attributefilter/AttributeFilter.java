package org.bflow.toolbox.hive.attributefilter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.bflow.toolbox.hive.attributes.AttributeFile;
import org.bflow.toolbox.hive.nls.NLSupport;
import org.eclipse.jface.dialogs.MessageDialog;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

/**
 * This class handles attributes filter. The code is based on
 * {@link AttributeFile}. With attribute filters you can define a filter rule
 * like 'AttributeKey OPERATOR AttributeValue' e.g. ('Risk > 0.3' or 'Time =
 * 122' or 'Layer = Risk' or 'Branch = IT'). These filters are used to hide/show
 * elements, which matches the filter
 * 
 * @author Felix Hoess <fhoess@users.sf.net>
 * @since 02.06.2015
 *
 */
public class AttributeFilter {


	private boolean dirty = true;
	private ArrayList<FilterEntry> filters = new ArrayList<FilterEntry>();
	private CSVReader csvReader;
	private CSVWriter csvWriter;
	private String[] headerLine = "AttributeName,Operator,Value,IsActive"
			.split(",");
	private String filename;
	/**
	 * this array stores a copy of the filters in a Stringlist (is used to store
	 * the data in an .csv file)
	 */
	private ArrayList<String[]> strList = new ArrayList<String[]>(50);

	/**
	 * Constructor.
	 * 
	 * @param diagramEditPart
	 *            diagram edit part
	 */
	public AttributeFilter(String filename) {
		super();
		this.setFilename(filename);
	}


	/**
	 * Returns true if this file is dirty and should be saved.
	 * 
	 * @return true if this file is dirty
	 */
	public boolean isDirty() {
		return dirty;
	}

	/**
	 * Adds a filter entry with a given key, operator (!= = < > >= <= ) and
	 * value. Makes this filter active (default)
	 * 
	 * @param key
	 * @param operator
	 * @param value
	 */
	public void add(String key, String operator, String value) {
		FilterEntry entry = new FilterEntry(key, operator, value, true);

		if (!filters.contains(entry)) {
			filters.add(entry);
			dirty = true;
			String[] entryAsString = entry
					.getEntrySeparated(FilterEntry.SEPARATOR);
			strList.add(entryAsString);
			write();
		}
	}

	/**
	 * removes the FilterEntry
	 * 
	 * @param entry
	 */
	public boolean remove(FilterEntry entry) {
		if (!filters.contains(entry))
			return false;
		filters.remove(entry);
		dirty = true;
		strList = new ArrayList<String[]>(50);
		for (FilterEntry r : filters) {
			strList.add(r.getEntrySeparated(FilterEntry.SEPARATOR));
		}

		write();
		return true;

	}

	/**
	 * Updates the given entry (if it is already contained in the list of
	 * filters)
	 * 
	 * @param entry
	 */
	public boolean updateEntry(FilterEntry entry) {
		if (filters.contains(entry)) {
			filters.set(filters.indexOf(entry), entry);
			dirty = true;
			strList = new ArrayList<String[]>(50);
			for (FilterEntry r : filters) {
				strList.add(r.getEntrySeparated(FilterEntry.SEPARATOR));
			}
			write();
			return true;
		}
		return false;
	}

	/**
	 * returns all FilterEntries
	 * 
	 * @return
	 */
	public ArrayList<FilterEntry> getAllFilters() {

		if (isDirty()) {
			refreshCSVReader();
			filters.clear();
			strList.clear();
			boolean skipfirstline = true;
			//for each entry add a button in its given group
			for (String[] line : csvReader) {
				//skip header of logic.csv file
				if (skipfirstline) {
					skipfirstline = false;
					continue;
				}
				if (line.length != FilterEntry.COLUMN_AMOUNT) {
					showCorruptionDialog();
					continue;
				}
				FilterEntry e = new FilterEntry(line[FilterEntry.ColumnATTRIBUTE_NAME],
						line[FilterEntry.ColumnOPERATOR],
						line[FilterEntry.ColumnVALUE],
						Boolean.valueOf(line[FilterEntry.ColumnACTIVE]));
				filters.add(e);
				strList.add(e.getEntrySeparated(FilterEntry.SEPARATOR));
			}
		}

		return filters;
	}

	/**
	 * refreshes the local stored csvReader with the latest data of the .csv
	 * file
	 */
	private void refreshCSVReader() {
		if (isDirty()) {
			InputStreamReader reader = null;
			FileInputStream fis = null;

			try {
				//init the Path
				fis = new FileInputStream(FilterLauncherConfigurator
						.getInstance().getDefaultFilterFolderPath()
						+ filename
						+ FilterLauncherConfigurator.getInstance()
								.getFileType());

			} catch (FileNotFoundException e) {
				File newFile = new File(FilterLauncherConfigurator
						.getInstance().getDefaultFilterFilePath());
				//				newFile.getParentFile().mkdirs();
				newFile.mkdirs();
				try {
					newFile.createNewFile();
					showWarningDialog();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				try {
					fis = new FileInputStream(newFile);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}

			try {
				reader = new InputStreamReader(fis, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if (reader != null) {
				csvReader = new CSVReader(reader, ',');
				dirty = false;
			}
		}

	}

	/**
	 * writes the current rule set to a file (currently AnnotationLogic.csv)
	 */
	private void write() {
		//TODO only write when model/file is saved (or Editor is closed) 
		if (isDirty()) {
			File newfile = new File(FilterLauncherConfigurator.getInstance()
					.getDefaultFilterFolderPath()
							+ filename
					+ FilterLauncherConfigurator.getInstance().getFileType());
			newfile.mkdirs();

			try {
				newfile.createNewFile();
				FileOutputStream fos = new FileOutputStream(newfile);

				OutputStreamWriter fw = new OutputStreamWriter(fos, "UTF-8");
				csvWriter = new CSVWriter(fw, ',');
				// feed in your array (or convert your data to an array)
				csvWriter.writeNext(headerLine);
				for (String[] line : strList) {
					csvWriter.writeNext(line);
				}
				csvWriter.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private void showWarningDialog() {
		MessageDialog dialog = new MessageDialog(
				null,
				NLSupport.AnnotationViewPart_Annotation_Warning_FileNotFound_Headline,
				null,
				FilterLauncherConfigurator.getInstance()
						.getDefaultFilterFolderPath()
						+ filename
						+ FilterLauncherConfigurator.getInstance()
								.getFileType()
						+ "\n\n"
						+ NLSupport.AttributeFilterViewPart_Annotation_Warning_FileNotFound_Message,
				MessageDialog.ERROR, "OK".split(" "), 0);
		dialog.open();

	}

	private void showCorruptionDialog() {
		MessageDialog dialog = new MessageDialog(
				null,
				NLSupport.AnnotationViewPart_Annotation_Warning_FileCorrupted_Headline,
				null,
				FilterLauncherConfigurator.getInstance()
						.getDefaultFilterFolderPath()
						+ filename
						+ FilterLauncherConfigurator.getInstance()
								.getFileType()
						+ "\n\n"
						+ NLSupport.AttributeFilterViewPart_Annotation_Warning_FileCorrupted_Message,
				MessageDialog.ERROR, "OK".split(" "), 0);
		dialog.open();

	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}

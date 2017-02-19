package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.PatternSyntaxException;



public class CleanProjects {
	public static BufferedReader in;
	private static Grep fileParserGeneratedNOT;
	private static Grep fileParserGenerated;
	private static int totalDeleted = 0;
	private static int totalCustomClasses = 0;
	private static int totalNeverGenerated = 0; //Classes that do not have @generated OR @generated NOT
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		in = new BufferedReader( new InputStreamReader( System.in ) );
		try {
			fileParserGeneratedNOT = new Grep ("@generated NOT");
			fileParserGenerated = new Grep ("@generated");
		} catch (PatternSyntaxException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		if (args.length != 1) {
			System.out.println ("Usage: java CleanProjects projectRootPath");
		}
		
		File mainFolder = new File (args[0]);
		if (!mainFolder.isDirectory()) {
			System.err.println ("" + mainFolder.getName() + " must be folder.");
		}
		
		if (!mainFolder.exists()) {
			System.err.println ("" + mainFolder.getName() + " does not exist.");
		}
		
		System.out.println ("Are you sure you want to do this?\nAll the .java files that contain the text @generated will be deleted from the folder " + getFullPath(mainFolder) + " AND FROM ALL OF IT'S SUBFOLDERS. (y/n)");
		String userInput = getUserInput();
		if (!(userInput.equals("y") || userInput.equals("yes")) ) {
			System.out.println ("Quitting.");
			System.exit(0);
		}
		processFolder (new ArrayList<File>(Arrays.asList(mainFolder.listFiles())));
		
		System.out.println ("\nTotal deleted:  " + totalDeleted);
		System.out.println ("\nTotal custom classes (not deleted):  " + totalCustomClasses);
		System.out.println ("\nTotal without @generated / @generated NOT (not deleted):  " + totalNeverGenerated);
		System.out.println ("\nDon't forget to refresh the Eclipse workspace by right-clicking the project and selecting Refresh.");
		
		System.exit(0);
		
	}
	
	private static void processFolder (ArrayList<File> fileObjectList) {
		for (File fileObject : fileObjectList) {
			if (fileObject.isDirectory()) {
				// ignore all .svn folders
				if (fileObject.getName().toLowerCase().equals(".svn")) {
					continue;
				}
					processFolder (new ArrayList<File>(Arrays.asList(fileObject.listFiles())));			
			} else if (fileObject.isFile()) {
				processFile(fileObject);
			}
		}
	}
	
	private static String getUserInput() {
		String str = "";
		try {
			str = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return str;
	}
	
	/**
	 * Pre: fileObject is a file, not a directory;
	 * @param fileObject
	 */
	private static void processFile (File fileObject) {
		
		if (fileObject.getName().toLowerCase().endsWith(".java")) {
			// Check if the file contains "generated NOT"
			boolean isCustomFile = false;
			boolean isGeneratedFile = false;
			try {
				 isCustomFile = fileParserGeneratedNOT.patternOccurrsOnce(fileObject);
				 isGeneratedFile = fileParserGenerated.patternOccurrsOnce(fileObject);
			} catch (IOException e) {
				System.err.println ("Could not search file " + getFullPath(fileObject) + ". ");
				e.printStackTrace();
				System.exit(-1);
			}
			if (isGeneratedFile) {
				// file contains @generated 
				if (!isCustomFile) {
					// but does not contain @generated NOT 
					boolean deletedSuccessfully = fileObject.delete();
					if (!deletedSuccessfully) {
						System.err.println ("Could not delete file " + getFullPath(fileObject));
					} else {
						System.out.println ("(D) " + fileObject.getName() + " Deleted file:" +  getFullPath(fileObject));
						totalDeleted++;
					}
				} else {
					System.out.println ("(CC)" + fileObject.getName() + ", " +  getFullPath(fileObject));
					totalCustomClasses++;
				}
			} else {
				System.out.println ("(NG)" + fileObject.getName() + ", " + getFullPath(fileObject));
				totalNeverGenerated++;
			}
		}
	}
	
	private static String getFullPath (File f) {
		String fullPath = f.getName();
		try {
			 fullPath = f.getCanonicalPath();
		} catch (IOException e) {
			System.err.println ("Could not get canonical path for file " +  f.getName());
			e.printStackTrace();
		}
		return fullPath;
		
		
	}

}

package oepc.diagram.views;

import java.beans.DefaultPersistenceDelegate;
import java.beans.Encoder;
import java.beans.Expression;
import java.beans.Statement;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AssociationPersistence {
	private static Log log;

	private static AssociationsPersistenceDelegate associationsPersistenceDelegate;
	private static AssociationPersistenceDelegate associationPersistenceDelegate;
	
	static {
		log = LogFactory.getLog(AssociationPersistence.class);
		
		associationsPersistenceDelegate = new AssociationsPersistenceDelegate();
		associationPersistenceDelegate = new AssociationPersistenceDelegate();
	}
	
	
	/**
	 * Writes the {@code Associations} given as parameter to disk. The destination for
	 * the file is passed by within the {@code file} parameter.
	 * @param associations The associations to be written to disk
	 * @param file The destination file
	 */
	public static void writeAssociationsToFile(Associations associations, File file) {
		XMLEncoder encoder = null;

		try {
			encoder = new XMLEncoder(new FileOutputStream(file));
			encoder.setPersistenceDelegate(Associations.class, associationsPersistenceDelegate);
			encoder.setPersistenceDelegate(Association.class, associationPersistenceDelegate);
			
			encoder.writeObject(associations);
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
		} finally {
			encoder.close();
		}
	}

	/**
	 * Tries to read all associations stored in the give {@code file}.
	 * Returns a new {@code Associations} object if the file is not present or empty.
	 * Returns NULL if {@code file} is NULL.
	 * @param file The source file
	 */
	public static Associations readAssociationsFromFile(File file) {
		if (file == null) return null;

		XMLDecoder decoder = null;
		
		try {
			decoder = new XMLDecoder(new FileInputStream(file));
			Object o = decoder.readObject();
			
			if (!(o instanceof Associations)) throw new Error("Somthing went wrong!");
			return (Associations) o;			
		} catch (ArrayIndexOutOfBoundsException aiob) {
			log.error(aiob.getMessage(), aiob);
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
		} finally {
			decoder.close();
		}
		
		return new Associations();
	}
	
	/**
	 * Gets or creates the file in which all associations will be persisted.
	 * The file will be located within the provided {@code folder}. 
	 * @param folder The parent folder of the new file
	 * @return The associations file, or NULL if {@code folder} is NULL, 
	 * or some kind of I/O related error occurs
	 */
	public static File aquireAssociationsFile(File folder) {
		if (folder == null) return null;
		
		File associationFile = new File(folder, ".associations");
		
		try {
			if (associationFile.exists()) return associationFile; 
			
			associationFile.createNewFile();
			try (XMLEncoder e = new XMLEncoder(new FileOutputStream(associationFile))) {
				e.flush();
			}
			
			return associationFile;
		} catch (Exception e) {
			return null;
		}
	}
	

	private static class AssociationsPersistenceDelegate extends DefaultPersistenceDelegate {
		@Override
		protected Expression instantiate(Object oldInstance, Encoder out) {
			Associations associations = (Associations) oldInstance;
			return new Expression(associations, Associations.class, "new", null);
		}
		
		@Override
		protected void initialize(Class<?> type, Object oldInstance, Object newInstance, Encoder out) {
			Associations associations = (Associations) oldInstance;
			
			for (Association a : associations.toArray()) {
				Statement statement = new Statement(associations, "add", new Object[] { a });
				out.writeStatement(statement);
			}
		}
	}
	
	private static class AssociationPersistenceDelegate extends DefaultPersistenceDelegate {
		@Override
		protected Expression instantiate(Object oldInstance, Encoder out) {
			Association association = (Association) oldInstance;
			return new Expression(association, Association.class, "new", 
					new Object[] { association.elementId, association.associatedURL, association.type });
		}
		
		@Override
		protected void initialize(Class<?> type, Object oldInstance, Object newInstance, Encoder out) {
			// Do nothing
		}
	}
}

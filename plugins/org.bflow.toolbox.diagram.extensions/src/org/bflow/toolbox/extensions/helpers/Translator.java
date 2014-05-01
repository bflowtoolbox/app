package org.bflow.toolbox.extensions.helpers;


/**
 * Provides some methods to translate vocabulary.
 * @author Joerg Hartmann
 * @since 0.0.7
 */
public class Translator {

	
	/**
	 * Returns an german representation, if the delivered text is an name
	 * for an element.
	 * @param toTranslate
	 * @return
	 */
	public static String translate(String toTranslate){
		String translated = "";
		if(toTranslate.equals("Event")){
			translated = "Ereignis";
		}
		else if(toTranslate.equals("OrganisationUnit")){
			translated = "Organisationseinheit";
		}
		else if(toTranslate.equals("BusinessObject")){
			translated = "Geschäftsobjekt";	
		}
		else if(toTranslate.equals("ITSystem")){
			translated = "IT-System";
		}
		else if(toTranslate.equals("Document")){
			translated = "Dokument";
		}
		else if(toTranslate.equals("Position")){
			translated = "Stelle";
		}
		else if(toTranslate.equals("Location")){
			translated = "Standort";	
		}
		else if(toTranslate.equals("TechnicalTerm")){
			translated = "Fachbegriff";
		}
		else if(toTranslate.equals("Group")){
			translated = "Gruppe";
		}
		else if(toTranslate.equals("Participant")){
			translated = "Organisationseinheit";
		}
		else if(toTranslate.equals("ProcessInterface")){
			translated = "Prozessinterface";	
		}
		else if(toTranslate.equals("Application")){
			translated = "Anwendung";
		}
		else if(toTranslate.equals("Objective")){
			translated = "Ziel";
		}
		else if(toTranslate.equals("Product")){
			translated = "Produkt";	
		}
		else if(toTranslate.equals("PersonType")){
			translated = "Personentype";
		}
		else if(toTranslate.equals("ExternalPerson")){
			translated = "Externe Person";
		}
		else if(toTranslate.equals("InternalPerson")){
			translated = "Interne Person";
		}
		else if(toTranslate.equals("Cluster")){
			translated = "Cluster";	
		}
		else if(toTranslate.equals("CardFile")){
			translated = "Kartei";
		}
		else if(toTranslate.equals("File")){
			translated = "Datei";
		}
		else if(toTranslate.equals("Function")){
			translated = "Funktion";
		}
		
		
		return translated;
	}
}

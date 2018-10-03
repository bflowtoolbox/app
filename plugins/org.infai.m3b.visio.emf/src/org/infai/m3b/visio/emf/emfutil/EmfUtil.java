package org.infai.m3b.visio.emf.emfutil;

import java.io.IOException;
import java.util.HashMap;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;


public class EmfUtil {
	
	public static String correctIdentifier(String value) {
		
		if(value == null) {
			return new String();
		}
		
		value = value.replace("ä", "ae");
		value = value.replace("ö", "oe");
		value = value.replace("ü", "ue");
		value = value.replaceAll("[^a-zA-Z0-9]", "_");
		
		if(!value.matches("[A-Za-z].*")) {
			value = "_".concat(value);
		}
			
		return value;
	}
	
	public static Resource saveAsXmi(String path, EObject rootElement, boolean schemaLocation) {

		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		URI fileUri = URI.createFileURI(path);
		Resource resource = resourceSet.createResource(fileUri);
		resource.getContents().add(rootElement);
		
		save(resource, schemaLocation);
		
		return resource;
	}
	
	public static void saveAsEcore(String path, EPackage rootElement) {

		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		URI fileUri = URI.createFileURI(path);
		Resource resource = resourceSet.createResource(fileUri);
		resource.getContents().add(rootElement);
		
		save(resource, false);
	}
	
	public static void save(Resource resource, boolean schemaLocation) {
		
		try {
			if(schemaLocation) {
				HashMap<String, Boolean> options = new HashMap<String, Boolean>();
		        options.put(XMIResource.OPTION_SCHEMA_LOCATION, true);
		        resource.save(options);
			}
			else
				resource.save(null);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	
	public static Resource loadXmi(String path) {
		
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		Resource resource = resourceSet.createResource(URI.createFileURI(path));
		
		load(resource);
		
		return resource;
	}
	

	/*
	public static Resource loadEcore(String path) {
		
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		Resource resource = resourceSet.createResource(URI.createFileURI(path));
		
		load(resource);
		
		return resource;
	}*/
	
	
	public static Resource load(Resource resource) {
		
		try {
			resource.load(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resource;
	}
	
	
	//creates anspecific EAnnotation
	public static EAnnotation createAnnotation(String source, String key, String value) {
		EAnnotation annotation = EcoreFactory.eINSTANCE.createEAnnotation();
		annotation.setSource(source);
		annotation.getDetails().put(key, value);
		return annotation;
	}
	
	public static String getVisioMasterId(EClass clazz) {
		
		String id = null;
		id = clazz.getEAnnotation("visio").getDetails().get("UID");
		return id;
	}
	
	
	
	
}

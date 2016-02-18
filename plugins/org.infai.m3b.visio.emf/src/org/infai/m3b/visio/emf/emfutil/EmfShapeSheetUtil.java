package org.infai.m3b.visio.emf.emfutil;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;


public class EmfShapeSheetUtil {
	
	EObject shape;
	
	public EmfShapeSheetUtil(EObject shape) {
		this.shape = shape;
	}

	
	public EObject getSection(EObject shape, String name) {
		
		EObject shapeSheet = (EObject)shape.eGet(shape.eClass().getEStructuralFeature("visioShapeSheet"));
		
		if(shapeSheet==null)
			return null;
		
		@SuppressWarnings("unchecked")
		EList<EObject> sections = (EList<EObject>)shapeSheet.eGet(shapeSheet.eClass().getEStructuralFeature("visioSections"));
		
		for(EObject section : sections) {
			if(section.eGet(section.eClass().getEStructuralFeature("visioName")).toString().equals(name))
				return section;
		}
		return null;	
	}
	
	public EObject getRow(EObject section, String name) {
		
		if(section==null) return null;
		
		@SuppressWarnings("unchecked")
		EList<EObject> rows = (EList<EObject>)section.eGet(section.eClass().getEStructuralFeature("visioRows"));
		
		for(EObject row : rows) {
			if(row.eGet(row.eClass().getEStructuralFeature("visioName")).toString().equals(name))
				return row;
		}
		return null;
	}
	
	public EObject getCell(EObject row, String name) {
		
		if(row==null) return null;
		
		@SuppressWarnings("unchecked")
		EList<EObject> cells = (EList<EObject>)row.eGet(row.eClass().getEStructuralFeature("visioCells"));
		
		for(EObject cell : cells) {
			if(cell.eGet(cell.eClass().getEStructuralFeature("visioName")).toString().equals(name))
				return cell;
		}
		return null;
	}
	
	public EObject getCell(EObject shape, String sectionName, String rowName, String cellName) {
			
		EObject section = getSection(shape, sectionName);
		EObject row = getRow(section, rowName);
		EObject cell = getCell(row, cellName);
		
		return cell;
	}
}

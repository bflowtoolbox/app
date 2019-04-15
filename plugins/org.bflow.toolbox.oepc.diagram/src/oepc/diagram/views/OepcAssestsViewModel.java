package oepc.diagram.views;

import java.io.File;

import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;

public class OepcAssestsViewModel extends ViewModel {
	private boolean isEnabled;
	private boolean isCopy;
	
	private String selectedElementId;
	
	private DiagramEditor diagramEditor;
	
	private File folder;
	private File associationsFile;
	
	private Associations associations;
	
	private WorkbenchModel workbenchModel;
	
	
	public OepcAssestsViewModel(WorkbenchModel workbenchModel) {
		this.isEnabled = false;
		this.isCopy = true;
		
		this.workbenchModel = workbenchModel;
		this.workbenchModel.setViewModel(this);
	}


	public void setCopy(boolean isCopy) {
		firePropertyChanged("isCopy", this.isCopy, this.isCopy = isCopy);
	}
	
	public void setSelectedElementId(String selectedElementId) {
		if (isSafeToIgnore(selectedElementId)) return;
		
		firePropertyChanged("selectedElementId", this.selectedElementId, this.selectedElementId = selectedElementId);
	}

	public void setDiagramEditor(DiagramEditor diagramEditor) {
		setEnabled(diagramEditor != null);
		firePropertyChanged("diagramEditor", this.diagramEditor, this.diagramEditor = diagramEditor);
	}
	
	public void setFolder(File folder) {
		if (isSafeToIgnore(folder)) return;
		
		firePropertyChanged("folder", this.folder, this.folder = folder);
	}
	
	public void setAssociations(Associations associations) {
		if (isSafeToIgnore(associations)) return;
		
		firePropertyChanged("associations", this.associations, this.associations = associations);
	}
	
	public void setAssociationsFile(File associationsFile) {
		if (isSafeToIgnore(associationsFile)) return;
		
		firePropertyChanged("associationsFile", this.associations, this.associationsFile = associationsFile);
	}
	
	private void setEnabled(boolean isEnabled) {
		firePropertyChanged("isEnabled", this.isEnabled, this.isEnabled = isEnabled);
	}
	
	
	public boolean isEnabled() {
		return isEnabled;
	}
	
	public boolean isCopy() {
		return isCopy;
	}
	
	public String getSelectedElementId() {
		return selectedElementId;
	}
	
	public DiagramEditor getDiagramEditor() {
		return diagramEditor;
	}
	
	public File getFolder() {
		return folder;
	}
	
	public File getAssociationsFile() {
		return associationsFile;
	}
	
	public Associations getAssociations() {
		return associations;
	}
	
	private boolean isSafeToIgnore(Object newValue) {
		return newValue == null && diagramEditor != null;
	}
}

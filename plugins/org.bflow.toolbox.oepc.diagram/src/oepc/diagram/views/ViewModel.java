package oepc.diagram.views;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class ViewModel {
	private final PropertyChangeSupport pcs;
	
	public ViewModel() {
		pcs = new PropertyChangeSupport(this);
	}
	
	
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }
    
    protected void firePropertyChanged(String propertyName, Object oldValue, Object newValue) {
    	pcs.firePropertyChange(propertyName, oldValue, newValue);
    }
}

package org.bflow.toolbox.extensions.commands;

import java.util.Vector;

import org.eclipse.draw2d.geometry.Dimension;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;


/**
 * To mark an specified type together with some instances of this type and the
 * maximal size.
 * @author Joerg Hartmann
 * @since 0.0.7 
 *
 */
public class AutoSizeMarker {

	
	/**
	 * The type.
	 */
	private Class<?> type;
	
	
	/**
	 * Stores all instances together with the current size.
	 */
	private Vector<EditPartMarker> markers;
	
	
	/**
	 * The maximum size.
	 */
	private Dimension maxSize;
	
	
	/**
	 * Inits this marker.
	 * @param type
	 */
	public AutoSizeMarker(Class<?> type){
		this.type = type;
	}
	
	
	/**
	 * Returns the maximum size.
	 * @return
	 */
	public Dimension getMaxSize() {
		return maxSize;
	}
	
	
	/**
	 * Returns the type.
	 * @return
	 */
	public Class<?> getType() {
		return type;
	}

	
	/**
	 * Returns the markers.
	 * @return
	 */
	public Vector<EditPartMarker> getMarkers() {
		return markers;
	}
	
	
	/**
	 * Sets the marker.s
	 * @param markers
	 */
	public void setMarkers(Vector<EditPartMarker> markers) {
		this.markers = markers;
	}

	
	/**
	 * Sets the maximum size.
	 * @param maxSize
	 */
	public void setMaxSize(Dimension maxSize) {
		this.maxSize = maxSize;
	}
	
	
	/**
	 * Saves an <code>ShapeNodeEditPart</code> together with the current size.
	 * @author Joerg Hartmann
	 * @since 0.0.7
	 *
	 */
	public class EditPartMarker{
		
		
		/**
		 * The edit part self.
		 */
		private ShapeNodeEditPart editPart;
		
		
		/**
		 * The current size.
		 */
		private Dimension size;
		
		
		/**
		 * Creates this marker.
		 * @param editPart
		 * @param size
		 */
		public EditPartMarker(ShapeNodeEditPart editPart, Dimension size){
			this.editPart = editPart;
			this.size = size;
		}

		
		/**
		 * Returns the edit part.
		 * @return
		 */
		public ShapeNodeEditPart getEditPart() {
			return editPart;
		}

		
		/**
		 * Returns the size.
		 * @return
		 */
		public Dimension getSize() {
			return size;
		}
	}
}

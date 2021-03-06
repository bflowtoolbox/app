package org.bflow.toolbox.epc.diagram.modelwizard.utils;

/**
 * Defines an Element within a process step.
 * @author Arian Storch
 * @since 03/12/09
 * @version 01/02/10
 *
 */
public class Element {
	private Kind _kind;
	private String _name;
		
	/**
	 * Default constructor.
	 * 
	 * @param name Name of the Element
	 * @param kind Kind of the Element
	 */
		public Element(String name, Kind kind) {
			_name = name;
			_kind = kind;
		}
		
	/**
	 * Returns the kind of the element.
	 * 
	 * @return kind of the element
	 */
	public Kind getKind() {
		return _kind;
	}
		
	/**
	 * Sets the name of the element.
	 * 
	 * @param name name of the element
	 */
	public void setName(String name) {
		_name = name;
	}
		
	/**
	 * Returns the name of the element.
	 * 
	 * @return name of the element
	 */
	public String getName() {
		return _name;
	}
		
	/**
	 * Returns true if the element has no needed information and can be ignored.
	 * 
	 * @return true if it can be ignored
	 */
	public boolean isReducable() {
		if (_kind == Kind.Null || _name.isEmpty() || _name.equalsIgnoreCase(""))
			return true;
		else
			return false;
	}
		
	/**
	 * Inner class enumeration for kinds of elements.
	 * @author Arian Storch
	 * @since 03/12/09
	 *
	 */
	public enum Kind {
		/** null flag */
		Null,
		
		/** event flag */
		Event,
		
		/** function flag */
		Function,
		
		/** xor_single flag */
		XOR_Single,
		
		/** xor_single flag */
		OR_Single,
		
		/** xor_single flag */
		AND_Single;
		
		/**
		 * Returns the position of the element within the enumeration hierarchie.
		 * 
		 * @return position of the element with the enumeration
		 */
		public int getPosition() {
			if (this == Null) return 0;

			if (this == Event) return 1;

			if (this == Function) return 2;

			return -1;
		}
		
		/**
		 * Returns the following kind.
		 * 
		 * @return following kind
		 */
		public Kind next() {
			if (this == Null) return Event;

			if (this == Event) return Function;

			if (this == Function) return Null;

			return null;
		}
		
		/**
		 * Returns the position of the following kind.
		 * 
		 * @return position of the following kind
		 */
		public int nextPosition() {
			return next().getPosition();
		}
		
		/**
		 * Returns true if it is a single connector
		 * 
		 * @return boolean
		 */
		public boolean isSingleConnector() {
			return this == AND_Single || this == XOR_Single || this == OR_Single;
		}
	}
	
	@Override
	public String toString() {
		return "[Element] kind:" + _kind + " name: " + _name;
	}
}

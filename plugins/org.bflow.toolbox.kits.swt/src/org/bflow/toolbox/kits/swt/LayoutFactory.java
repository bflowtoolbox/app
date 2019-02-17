package org.bflow.toolbox.kits.swt;

/**
 * Provides methods to create SWT-bases layouts more easily.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-02-17
 *
 */
public class LayoutFactory {
	/** Creates an instance of {@link GridData} with default properties. */
	public static GridDataBuilder createGridData() {
		return new GridDataBuilder();
	}
	
	/**
	 * Creates an instance {@link GridData} with the initial {@link hAlign} and
	 * {@code vAlign} values.
	 */
	public static GridDataBuilder createGridData(int hAlign, int vAlign) {
		return new GridDataBuilder(hAlign, vAlign);
	}
	
	/**
	 * Creates an instance of {@link GridData} with the initial values. See
	 * constructor of {@link GridData} for details.
	 */
	public static GridDataBuilder createGridData(int hAlign, int vAlign, boolean gHorSpace, boolean gVerSpace) {
		return new GridDataBuilder(hAlign, vAlign, gHorSpace, gVerSpace);
	}
}

package org.bflow.toolbox.kits.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Control;

/**
 * Provides methods to create and set up instance of {@link GridData} with a
 * fluent programming style.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-02-17
 *
 */
public class GridDataBuilder {
	private int _horizontalAlignment = SWT.DEFAULT;
	private int _verticalAlignment = SWT.DEFAULT;
	private boolean _grabHorizontalSpace = false;
	private boolean _grabVerticalSpace = false;
	private int _columnSpan = -1;
	private int _widthHint = -1;
	private int _heightHint = -1;
	
	/** Creates an instance of {@link GridData} with default values. */
	public GridDataBuilder() {
		// Default initializer
	}
	
	/**
	 * Creates an instance of {@link GridData} with the given {@code hAlign} and
	 * {@code vAlign} initial values.
	 */
	public GridDataBuilder(int hAlign, int vAlign) {
		this(hAlign, vAlign, false, false);
	}
	
	/**
	 * Creates an instance of {@link GridData} with the given initial values. See
	 * constructor of {@link GridData} for details.
	 */
	public GridDataBuilder(int hAlign, int vAlign, boolean gHorSpace, boolean gVerSpace) {
		_horizontalAlignment = hAlign;
		_verticalAlignment = vAlign;
		_grabHorizontalSpace = gHorSpace;
		_grabVerticalSpace = gVerSpace;
	}
	
	/** Adds the given {@code value} as column span. */
	public GridDataBuilder withColumnSpan(int value) {
		_columnSpan = value;
		return this;
	}
	
	/** Adds the given {@code value} as height hint. */
	public GridDataBuilder withHeight(int value) {
		_heightHint = value;
		return this;
	}
	
	/** Adds the given {@code value} as width hint */
	public GridDataBuilder withWidth(int value) {
		_widthHint = value;
		return this;
	}
	
	/** Adds the given {@code value} as left and right margin to the control width */
	public GridDataBuilder withLRMargin(Control control, int value) {
		Point pt = control.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
		_widthHint = pt.x + 2 * value;
		return this;
	}
	
	/** Builds the set up instance of {@link GridData}. */
	public GridData build() {
		GridData gd = new GridData(_horizontalAlignment, _verticalAlignment, 
								   _grabHorizontalSpace, _grabVerticalSpace);
		
		if (_columnSpan != -1)
			gd.horizontalSpan = _columnSpan;
		
		if (_widthHint != -1)
			gd.widthHint = _widthHint;
		
		if (_heightHint != -1)
			gd.heightHint = _heightHint;
		
		return gd;
	}
}

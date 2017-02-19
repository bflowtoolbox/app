package edu.toronto.cs.openome_model.diagram.view.factories;

import org.eclipse.gmf.runtime.diagram.ui.view.factories.ConnectionViewFactory;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.LineStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;

public class OpenomeConnectionViewFactory extends ConnectionViewFactory {
	
	@Override
	protected void initializeFromPreferences(View view) {
		super.initializeFromPreferences(view);
		
		LineStyle lineStyle = (LineStyle) view.getStyle(NotationPackage.Literals.LINE_STYLE);
		if (lineStyle != null) {
			// line color
			lineStyle.setLineColor(FigureUtilities.colorToInteger(ViewFactoryConstants.LINK_LINE_COLOUR).intValue());
		} else {
			System.err.println ("Error when setting the OME-style line colour");
		}
		
		FontStyle fontStyle = (FontStyle) view.getStyle(NotationPackage.Literals.FONT_STYLE);
		if (fontStyle != null) {
			// line color
			fontStyle.setFontColor(FigureUtilities.colorToInteger(ViewFactoryConstants.LINK_FONT_COLOUR).intValue());
		} else {
			System.err.println ("Error when setting the OME-style font colour");
		}
		
	}

}

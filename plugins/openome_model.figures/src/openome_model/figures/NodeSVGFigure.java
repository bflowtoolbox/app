package openome_model.figures;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gmf.runtime.draw2d.ui.render.factory.RenderedImageFactory;
import org.eclipse.gmf.runtime.draw2d.ui.render.figures.CustomScalableImageFigure;

public class NodeSVGFigure extends CustomScalableImageFigure {
	// anti alias disabled to show svg diagrams
	// http://dev.eclipse.org/newslists/news.eclipse.modeling.gmf/msg09945.html
	
	// edit: this doesn't seem to be a problem anymore
	// the SVG figures anti-alias properly without any problems
	
	public NodeSVGFigure(String name) {
			super(RenderedImageFactory.getInstance(FileLocator.find(Platform.getBundle("openome_model"), new Path("images/" + name + ".svg"), null)), 
					true, true, true);
			setMaintainAspectRatio(false);
	}	
	
	public NodeSVGFigure(String name, String color) {
			super(RenderedImageFactory.getInstance(FileLocator.find(Platform.getBundle("openome_model"), new Path("images/" + name + color + ".svg"), null)), 
				true, true, true);
		setMaintainAspectRatio(false);
	}
}
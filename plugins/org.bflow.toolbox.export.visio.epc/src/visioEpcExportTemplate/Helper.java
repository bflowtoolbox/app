package visioEpcExportTemplate;

import org.eclipse.emf.ecore.*;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;

/**
 * @author Christian Boehme
 * 
 */

public class Helper {
	
	public static Bounds getPersistedChildren(EObject e, Diagram d) {
		for (Object child : d.getPersistedChildren()) {
			Node node = (Node) child;
			//null, if node is a note or a text
			if (node.getElement() != null) {
				if (node.getElement().equals(e))
					return (Bounds) node.getLayoutConstraint();
			}
		}
		return null;
	}
	
	public static double inchConversion(Integer value) {
		
		if(value==0 || value==null) return 0;
		
		double result = value/130;
		return result;
	}
	
}

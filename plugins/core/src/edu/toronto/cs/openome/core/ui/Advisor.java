/*
 * Created on Jun 19, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package edu.toronto.cs.openome.core.ui;

import org.eclipse.ui.application.WorkbenchAdvisor;

/**
 * @author Yijun
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Advisor extends WorkbenchAdvisor {

	public static void main(String[] args) {
	}
	public String getInitialWindowPerspectiveId() {
		return "edu.toronto.cs.ome.eclipse.perspective.openome";
	}
}

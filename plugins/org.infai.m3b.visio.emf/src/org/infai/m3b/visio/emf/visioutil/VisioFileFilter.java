/*******************************************************************************
 * Copyright (c) 2009 Heiko Kern.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Heiko Kern - initial API and implementation
 *******************************************************************************/
package org.infai.m3b.visio.emf.visioutil;

import java.io.File;
import java.io.FileFilter;

public class VisioFileFilter implements FileFilter 
{
	private String end;
	
	public VisioFileFilter(String end) {
		this.end = end;
	}
	
	public boolean accept(File pathname) {
		
		return (pathname.toString().endsWith("."+end.toLowerCase()) || 
				pathname.toString().endsWith("."+end.toUpperCase()));
	}
}

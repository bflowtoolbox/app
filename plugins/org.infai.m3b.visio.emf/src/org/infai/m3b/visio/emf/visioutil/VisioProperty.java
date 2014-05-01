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

public class VisioProperty {

	private String name;
    private String id;
    private String value;
    private String type;

    public VisioProperty(String name, String id, String type) {
    	
    	this.name = name;
    	this.id = id;
    	this.value = new String();
    	this.type = type;
    }
    
    public boolean equals(VisioProperty p)
    {
        if (p.getId().equals(this.getId()) && p.getName().equals(this.getName()))
            return true;

        return false;
    }

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}
	
	public String getType() {
		return type;
	}
}
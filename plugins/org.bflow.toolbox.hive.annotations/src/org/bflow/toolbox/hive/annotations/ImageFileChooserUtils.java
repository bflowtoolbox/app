/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

package org.bflow.toolbox.hive.annotations;

import java.io.File;

import javax.swing.ImageIcon;

import org.apache.commons.logging.LogFactory;
import org.bflow.toolbox.hive.nls.NLSupport;

public class ImageFileChooserUtils {
    public final static String jpeg = "jpeg"; //$NON-NLS-1$
    public final static String jpg = "jpg"; //$NON-NLS-1$
    public final static String gif = "gif"; //$NON-NLS-1$
    public final static String tiff = "tiff"; //$NON-NLS-1$
    public final static String tif = "tif"; //$NON-NLS-1$
    public final static String png = "png"; //$NON-NLS-1$
    
    private static final String[] FileExtensions = {jpeg, jpg, tiff, tif, png, gif};
    
    public static final String[] WildcardFileExtensions = getApplicableFileExtensions();
    
    public static final String[] FilterExtensions = {"*.png;*.gif;*.jpg;*.jpeg;*.tif;*.tiff"}; //$NON-NLS-1$
    public static final String[] FilterNames = {NLSupport.ImageFileChooserUtils_FilterNames}; 
    
	/**
	 * Returns all define file extensions with wildcard prefix (for instance,
	 * *.gif)
	 * 
	 * @return Array of String
	 */
    private static String[] getApplicableFileExtensions() {
    	String[] fileExtensions = new String[FileExtensions.length];
    	for (int i = -1; ++i != fileExtensions.length;) {
    		fileExtensions[i] = String.format("*.%s", FileExtensions[i]); //$NON-NLS-1$
    	}
    	return fileExtensions;			
    }

    /** Get the extension of a file. */
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = ImageFileChooserUtils.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
        	LogFactory.getLog(ImageFileChooserUtils.class)
        			  .warn("Couldn't find file: " + path); //$NON-NLS-1$
            return null;
        }
    }
}

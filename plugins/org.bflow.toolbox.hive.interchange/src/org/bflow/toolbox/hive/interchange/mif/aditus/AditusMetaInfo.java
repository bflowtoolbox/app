package org.bflow.toolbox.hive.interchange.mif.aditus;

import java.util.LinkedList;
import java.util.List;

/**
 * Defines the meta informations needed to perform a transformation from a XML file 
 * to a model file.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 04.07.2013
 */
public class AditusMetaInfo {
	
	/** The Imports. */
	public List<String> Imports = new LinkedList<String>();
	
	/** The Model. */
	public AditusModelMetaInfo Model = new AditusModelMetaInfo();
	
	/** The Shapes. */
	public List<AditusShapeMetaInfo> Shapes = new LinkedList<AditusShapeMetaInfo>();
	
	/** The Edges. */
	public List<AditusEdgeMetaInfo> Edges = new LinkedList<AditusEdgeMetaInfo>();

}

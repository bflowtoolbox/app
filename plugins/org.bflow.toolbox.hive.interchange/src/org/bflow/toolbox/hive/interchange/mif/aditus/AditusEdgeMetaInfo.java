package org.bflow.toolbox.hive.interchange.mif.aditus;

/**
 * Extends {@link AditusTypedModelItemMetaInfo} to add informations needed 
 * by an edge.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 04.07.2013
 */
public class AditusEdgeMetaInfo extends AditusTypedModelItemMetaInfo {

	/** The Source. */
	public String Source = null;
	
	/** The Target. */
	public String Target = null;
	
}

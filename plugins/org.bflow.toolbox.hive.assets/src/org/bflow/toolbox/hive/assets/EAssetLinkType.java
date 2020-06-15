package org.bflow.toolbox.hive.assets;

/**
 * Asset link type enumeration.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-04-23
 *
 */
public enum EAssetLinkType {
	/** File link (hard) */ 
	File, 
	
	/** Symbolic link */
	Symlink, 
	
	/** URL */
	Url
}

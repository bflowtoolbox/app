package org.bflow.toolbox.hive.assets;

/**
 * Describes the link to an asset.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-04-23
 *
 */
public class AssetLink {
	private final EAssetLinkType _linkType;
	private final String _elementId;
	private final String _assetUrl;
	
	/**
	 * Initializes the new instance with the specified arguments.
	 * 
	 * @param linkType  Link type
	 * @param elementId Element id of the associated element
	 * @param assetUrl  URL of the linked asset
	 */
	public AssetLink(EAssetLinkType linkType, String elementId, String assetUrl) {
		_linkType = linkType;
		_elementId = elementId;
		_assetUrl = assetUrl;
	}
	
	/** Returns the link type. */
	public EAssetLinkType LinkType() {
		return _linkType;
	}
	
	/** Returns the id of the associated element. */
	public String ElementId() {
		return _elementId;
	}
	
	/** Returns the URL of the link asset. */
	public String AssetUrl() {
		return _assetUrl;
	}
}

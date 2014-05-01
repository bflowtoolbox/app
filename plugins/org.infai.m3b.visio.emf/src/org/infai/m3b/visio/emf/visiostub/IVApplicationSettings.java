
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVApplicationSettings
 * Description: Interface provides access  Global application settings
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVApplicationSettings extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d072d-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVApplicationSettings.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVApplicationSettings (it is required by Jawin for some internal working though).
	 */
	public IVApplicationSettings() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVApplicationSettings interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVApplicationSettings(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVApplicationSettings interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVApplicationSettings(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVApplicationSettings interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVApplicationSettings interface on.
	 */
	public IVApplicationSettings(COMPtr comObject) throws COMException {
		super(comObject);
	}

	public int getIIDToken() {
		return IID_TOKEN;
	}
	
	
    /**
    *
    
    * @return void
    **/
    /*public void QueryInterface(Object[] riid,void[] 
        [] ppvObj) throws COMException
    {
      
		invokeN("QueryInterface", new Object[] {riid, ppvObj});
        
    }*/
    /**
    *
    
    * @return int
    **/
    /*public int AddRef() throws COMException
    {
      
		return ((Integer)invokeN("AddRef", new Object[] {})).intValue();
        
    }*/
    /**
    *
    
    * @return int
    **/
    /*public int Release() throws COMException
    {
      
		return ((Integer)invokeN("Release", new Object[] {})).intValue();
        
    }*/
    /**
    *
    
    * @return void
    **/
    /*public void GetTypeInfoCount(int[] pctinfo) throws COMException
    {
      
		invokeN("GetTypeInfoCount", new Object[] {pctinfo});
        
    }*/
    /**
    *
    
    * @param itinfo
    * @param lcid
    * @return void
    **/
    /*public void GetTypeInfo(int itinfo,int lcid,void[] 
        [] pptinfo) throws COMException
    {
      
		invokeN("GetTypeInfo", new Object[] {new Integer(itinfo), new Integer(lcid), pptinfo});
        
    }*/
    /**
    *
    
    * @param cNames
    * @param lcid
    * @return void
    **/
    /*public void GetIDsOfNames(Object[] riid,int[] 
        [] rgszNames,int cNames,int lcid,int[] rgdispid) throws COMException
    {
      
		invokeN("GetIDsOfNames", new Object[] {riid, rgszNames, new Integer(cNames), new Integer(lcid), rgdispid});
        
    }*/
    /**
    *
    
    * @param dispidMember
    * @param lcid
    * @param wFlags
    * @return void
    **/
    /*public void Invoke(int dispidMember,Object[] riid,int lcid,short wFlags,Object[] pdispparams,Variant[] pvarResult,Object[] pexcepinfo,int[] puArgErr) throws COMException
    {
      
		invokeN("Invoke", new Object[] {new Integer(dispidMember), riid, new Integer(lcid), new Short(wFlags), pdispparams, pvarResult, pexcepinfo, puArgErr});
        
    }*/
    /**
    *
    * @return void
    **/
    public IVApplication getApplication() throws COMException
    {
        IVApplication res = new IVApplication();
          DispatchPtr dispPtr = (DispatchPtr)get("Application");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return int
    **/
    public int getObjectType() throws COMException
    {
        return ((Integer)get("ObjectType")).intValue();
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getDrawingAids() throws COMException
    {
        return ((Boolean)get("DrawingAids")).booleanValue();
    }
        
    /**
    *
    * @param DrawingAids
    **/
    public void setDrawingAids(boolean DrawingAids) throws COMException
    {
        put("DrawingAids", DrawingAids);
    }
        
    /**
    *
    * @return int
    **/
    public int getSnapStrengthRulerX() throws COMException
    {
        return ((Integer)get("SnapStrengthRulerX")).intValue();
    }
        
    /**
    *
    * @param SnapStrengthRulerX
    **/
    public void setSnapStrengthRulerX(int SnapStrengthRulerX) throws COMException
    {
        put("SnapStrengthRulerX", SnapStrengthRulerX);
    }
        
    /**
    *
    * @return int
    **/
    public int getSnapStrengthRulerY() throws COMException
    {
        return ((Integer)get("SnapStrengthRulerY")).intValue();
    }
        
    /**
    *
    * @param SnapStrengthRulerY
    **/
    public void setSnapStrengthRulerY(int SnapStrengthRulerY) throws COMException
    {
        put("SnapStrengthRulerY", SnapStrengthRulerY);
    }
        
    /**
    *
    * @return int
    **/
    public int getSnapStrengthGridX() throws COMException
    {
        return ((Integer)get("SnapStrengthGridX")).intValue();
    }
        
    /**
    *
    * @param SnapStrengthGridX
    **/
    public void setSnapStrengthGridX(int SnapStrengthGridX) throws COMException
    {
        put("SnapStrengthGridX", SnapStrengthGridX);
    }
        
    /**
    *
    * @return int
    **/
    public int getSnapStrengthGridY() throws COMException
    {
        return ((Integer)get("SnapStrengthGridY")).intValue();
    }
        
    /**
    *
    * @param SnapStrengthGridY
    **/
    public void setSnapStrengthGridY(int SnapStrengthGridY) throws COMException
    {
        put("SnapStrengthGridY", SnapStrengthGridY);
    }
        
    /**
    *
    * @return int
    **/
    public int getSnapStrengthGuidesX() throws COMException
    {
        return ((Integer)get("SnapStrengthGuidesX")).intValue();
    }
        
    /**
    *
    * @param SnapStrengthGuidesX
    **/
    public void setSnapStrengthGuidesX(int SnapStrengthGuidesX) throws COMException
    {
        put("SnapStrengthGuidesX", SnapStrengthGuidesX);
    }
        
    /**
    *
    * @return int
    **/
    public int getSnapStrengthGuidesY() throws COMException
    {
        return ((Integer)get("SnapStrengthGuidesY")).intValue();
    }
        
    /**
    *
    * @param SnapStrengthGuidesY
    **/
    public void setSnapStrengthGuidesY(int SnapStrengthGuidesY) throws COMException
    {
        put("SnapStrengthGuidesY", SnapStrengthGuidesY);
    }
        
    /**
    *
    * @return int
    **/
    public int getSnapStrengthPointsX() throws COMException
    {
        return ((Integer)get("SnapStrengthPointsX")).intValue();
    }
        
    /**
    *
    * @param SnapStrengthPointsX
    **/
    public void setSnapStrengthPointsX(int SnapStrengthPointsX) throws COMException
    {
        put("SnapStrengthPointsX", SnapStrengthPointsX);
    }
        
    /**
    *
    * @return int
    **/
    public int getSnapStrengthPointsY() throws COMException
    {
        return ((Integer)get("SnapStrengthPointsY")).intValue();
    }
        
    /**
    *
    * @param SnapStrengthPointsY
    **/
    public void setSnapStrengthPointsY(int SnapStrengthPointsY) throws COMException
    {
        put("SnapStrengthPointsY", SnapStrengthPointsY);
    }
        
    /**
    *
    * @return int
    **/
    public int getSnapStrengthGeometryX() throws COMException
    {
        return ((Integer)get("SnapStrengthGeometryX")).intValue();
    }
        
    /**
    *
    * @param SnapStrengthGeometryX
    **/
    public void setSnapStrengthGeometryX(int SnapStrengthGeometryX) throws COMException
    {
        put("SnapStrengthGeometryX", SnapStrengthGeometryX);
    }
        
    /**
    *
    * @return int
    **/
    public int getSnapStrengthGeometryY() throws COMException
    {
        return ((Integer)get("SnapStrengthGeometryY")).intValue();
    }
        
    /**
    *
    * @param SnapStrengthGeometryY
    **/
    public void setSnapStrengthGeometryY(int SnapStrengthGeometryY) throws COMException
    {
        put("SnapStrengthGeometryY", SnapStrengthGeometryY);
    }
        
    /**
    *
    * @return int
    **/
    public int getSnapStrengthExtensionsX() throws COMException
    {
        return ((Integer)get("SnapStrengthExtensionsX")).intValue();
    }
        
    /**
    *
    * @param SnapStrengthExtensionsX
    **/
    public void setSnapStrengthExtensionsX(int SnapStrengthExtensionsX) throws COMException
    {
        put("SnapStrengthExtensionsX", SnapStrengthExtensionsX);
    }
        
    /**
    *
    * @return int
    **/
    public int getSnapStrengthExtensionsY() throws COMException
    {
        return ((Integer)get("SnapStrengthExtensionsY")).intValue();
    }
        
    /**
    *
    * @param SnapStrengthExtensionsY
    **/
    public void setSnapStrengthExtensionsY(int SnapStrengthExtensionsY) throws COMException
    {
        put("SnapStrengthExtensionsY", SnapStrengthExtensionsY);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getShowFileSaveWarnings() throws COMException
    {
        return ((Boolean)get("ShowFileSaveWarnings")).booleanValue();
    }
        
    /**
    *
    * @param ShowFileSaveWarnings
    **/
    public void setShowFileSaveWarnings(boolean ShowFileSaveWarnings) throws COMException
    {
        put("ShowFileSaveWarnings", ShowFileSaveWarnings);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getShowFileOpenWarnings() throws COMException
    {
        return ((Boolean)get("ShowFileOpenWarnings")).booleanValue();
    }
        
    /**
    *
    * @param ShowFileOpenWarnings
    **/
    public void setShowFileOpenWarnings(boolean ShowFileOpenWarnings) throws COMException
    {
        put("ShowFileOpenWarnings", ShowFileOpenWarnings);
    }
        
    /**
    *
    * @return int
    **/
    public int getDefaultSaveFormat() throws COMException
    {
        return ((Integer)get("DefaultSaveFormat")).intValue();
    }
        
    /**
    *
    * @param DefaultSaveFormat
    **/
    public void setDefaultSaveFormat(int DefaultSaveFormat) throws COMException
    {
        put("DefaultSaveFormat", DefaultSaveFormat);
    }
        
    /**
    *
    * @return int
    **/
    public int getDrawingPageColor() throws COMException
    {
        return ((Integer)get("DrawingPageColor")).intValue();
    }
        
    /**
    *
    * @param DrawingPageColor
    **/
    public void setDrawingPageColor(int DrawingPageColor) throws COMException
    {
        put("DrawingPageColor", DrawingPageColor);
    }
        
    /**
    *
    * @return int
    **/
    public int getDrawingBackgroundColor() throws COMException
    {
        return ((Integer)get("DrawingBackgroundColor")).intValue();
    }
        
    /**
    *
    * @param DrawingBackgroundColor
    **/
    public void setDrawingBackgroundColor(int DrawingBackgroundColor) throws COMException
    {
        put("DrawingBackgroundColor", DrawingBackgroundColor);
    }
        
    /**
    *
    * @return int
    **/
    public int getDrawingBackgroundColorGradient() throws COMException
    {
        return ((Integer)get("DrawingBackgroundColorGradient")).intValue();
    }
        
    /**
    *
    * @param DrawingBackgroundColorGradient
    **/
    public void setDrawingBackgroundColorGradient(int DrawingBackgroundColorGradient) throws COMException
    {
        put("DrawingBackgroundColorGradient", DrawingBackgroundColorGradient);
    }
        
    /**
    *
    * @return int
    **/
    public int getStencilBackgroundColor() throws COMException
    {
        return ((Integer)get("StencilBackgroundColor")).intValue();
    }
        
    /**
    *
    * @param StencilBackgroundColor
    **/
    public void setStencilBackgroundColor(int StencilBackgroundColor) throws COMException
    {
        put("StencilBackgroundColor", StencilBackgroundColor);
    }
        
    /**
    *
    * @return int
    **/
    public int getStencilBackgroundColorGradient() throws COMException
    {
        return ((Integer)get("StencilBackgroundColorGradient")).intValue();
    }
        
    /**
    *
    * @param StencilBackgroundColorGradient
    **/
    public void setStencilBackgroundColorGradient(int StencilBackgroundColorGradient) throws COMException
    {
        put("StencilBackgroundColorGradient", StencilBackgroundColorGradient);
    }
        
    /**
    *
    * @return int
    **/
    public int getStencilTextColor() throws COMException
    {
        return ((Integer)get("StencilTextColor")).intValue();
    }
        
    /**
    *
    * @param StencilTextColor
    **/
    public void setStencilTextColor(int StencilTextColor) throws COMException
    {
        put("StencilTextColor", StencilTextColor);
    }
        
    /**
    *
    * @return int
    **/
    public int getPrintPreviewBackgroundColor() throws COMException
    {
        return ((Integer)get("PrintPreviewBackgroundColor")).intValue();
    }
        
    /**
    *
    * @param PrintPreviewBackgroundColor
    **/
    public void setPrintPreviewBackgroundColor(int PrintPreviewBackgroundColor) throws COMException
    {
        put("PrintPreviewBackgroundColor", PrintPreviewBackgroundColor);
    }
        
    /**
    *
    * @return int
    **/
    public int getFullScreenBackgroundColor() throws COMException
    {
        return ((Integer)get("FullScreenBackgroundColor")).intValue();
    }
        
    /**
    *
    * @param FullScreenBackgroundColor
    **/
    public void setFullScreenBackgroundColor(int FullScreenBackgroundColor) throws COMException
    {
        put("FullScreenBackgroundColor", FullScreenBackgroundColor);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getShowStartupDialog() throws COMException
    {
        return ((Boolean)get("ShowStartupDialog")).booleanValue();
    }
        
    /**
    *
    * @param ShowStartupDialog
    **/
    public void setShowStartupDialog(boolean ShowStartupDialog) throws COMException
    {
        put("ShowStartupDialog", ShowStartupDialog);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getShowSmartTags() throws COMException
    {
        return ((Boolean)get("ShowSmartTags")).booleanValue();
    }
        
    /**
    *
    * @param ShowSmartTags
    **/
    public void setShowSmartTags(boolean ShowSmartTags) throws COMException
    {
        put("ShowSmartTags", ShowSmartTags);
    }
        
    /**
    *
    * @return int
    **/
    public int getTextDisplayQuality() throws COMException
    {
        return ((Integer)get("TextDisplayQuality")).intValue();
    }
        
    /**
    *
    * @param TextDisplayQuality
    **/
    public void setTextDisplayQuality(int TextDisplayQuality) throws COMException
    {
        put("TextDisplayQuality", TextDisplayQuality);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getHigherQualityShapeDisplay() throws COMException
    {
        return ((Boolean)get("HigherQualityShapeDisplay")).booleanValue();
    }
        
    /**
    *
    * @param HigherQualityShapeDisplay
    **/
    public void setHigherQualityShapeDisplay(boolean HigherQualityShapeDisplay) throws COMException
    {
        put("HigherQualityShapeDisplay", HigherQualityShapeDisplay);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getSmoothDrawing() throws COMException
    {
        return ((Boolean)get("SmoothDrawing")).booleanValue();
    }
        
    /**
    *
    * @param SmoothDrawing
    **/
    public void setSmoothDrawing(boolean SmoothDrawing) throws COMException
    {
        put("SmoothDrawing", SmoothDrawing);
    }
        
    /**
    *
    * @return int
    **/
    public int getStencilCharactersPerLine() throws COMException
    {
        return ((Integer)get("StencilCharactersPerLine")).intValue();
    }
        
    /**
    *
    * @param StencilCharactersPerLine
    **/
    public void setStencilCharactersPerLine(int StencilCharactersPerLine) throws COMException
    {
        put("StencilCharactersPerLine", StencilCharactersPerLine);
    }
        
    /**
    *
    * @return int
    **/
    public int getStencilLinesPerMaster() throws COMException
    {
        return ((Integer)get("StencilLinesPerMaster")).intValue();
    }
        
    /**
    *
    * @param StencilLinesPerMaster
    **/
    public void setStencilLinesPerMaster(int StencilLinesPerMaster) throws COMException
    {
        put("StencilLinesPerMaster", StencilLinesPerMaster);
    }
        
    /**
    *
    * @return String
    **/
    public String getUserName() throws COMException
    {
         return (String)get("UserName");
    }
        
    /**
    *
    * @param UserName
    **/
    public void setUserName(String UserName) throws COMException
    {
        put("UserName", UserName);
    }
        
    /**
    *
    * @return String
    **/
    public String getUserInitials() throws COMException
    {
         return (String)get("UserInitials");
    }
        
    /**
    *
    * @param UserInitials
    **/
    public void setUserInitials(String UserInitials) throws COMException
    {
        put("UserInitials", UserInitials);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getZoomOnRoll() throws COMException
    {
        return ((Boolean)get("ZoomOnRoll")).booleanValue();
    }
        
    /**
    *
    * @param ZoomOnRoll
    **/
    public void setZoomOnRoll(boolean ZoomOnRoll) throws COMException
    {
        put("ZoomOnRoll", ZoomOnRoll);
    }
        
    /**
    *
    * @return int
    **/
    public int getUndoLevels() throws COMException
    {
        return ((Integer)get("UndoLevels")).intValue();
    }
        
    /**
    *
    * @param UndoLevels
    **/
    public void setUndoLevels(int UndoLevels) throws COMException
    {
        put("UndoLevels", UndoLevels);
    }
        
    /**
    *
    * @return int
    **/
    public int getRecentFilesListSize() throws COMException
    {
        return ((Integer)get("RecentFilesListSize")).intValue();
    }
        
    /**
    *
    * @param RecentFilesListSize
    **/
    public void setRecentFilesListSize(int RecentFilesListSize) throws COMException
    {
        put("RecentFilesListSize", RecentFilesListSize);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getCenterSelectionOnZoom() throws COMException
    {
        return ((Boolean)get("CenterSelectionOnZoom")).booleanValue();
    }
        
    /**
    *
    * @param CenterSelectionOnZoom
    **/
    public void setCenterSelectionOnZoom(boolean CenterSelectionOnZoom) throws COMException
    {
        put("CenterSelectionOnZoom", CenterSelectionOnZoom);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getConnectorSplittingEnabled() throws COMException
    {
        return ((Boolean)get("ConnectorSplittingEnabled")).booleanValue();
    }
        
    /**
    *
    * @param ConnectorSplittingEnabled
    **/
    public void setConnectorSplittingEnabled(boolean ConnectorSplittingEnabled) throws COMException
    {
        put("ConnectorSplittingEnabled", ConnectorSplittingEnabled);
    }
        
    /**
    *
    * @return int
    **/
    public int getAsianTextUI() throws COMException
    {
        return ((Integer)get("AsianTextUI")).intValue();
    }
        
    /**
    *
    * @param AsianTextUI
    **/
    public void setAsianTextUI(int AsianTextUI) throws COMException
    {
        put("AsianTextUI", AsianTextUI);
    }
        
    /**
    *
    * @return int
    **/
    public int getComplexTextUI() throws COMException
    {
        return ((Integer)get("ComplexTextUI")).intValue();
    }
        
    /**
    *
    * @param ComplexTextUI
    **/
    public void setComplexTextUI(int ComplexTextUI) throws COMException
    {
        put("ComplexTextUI", ComplexTextUI);
    }
        
    /**
    *
    * @return int
    **/
    public int getKanaFindAndReplace() throws COMException
    {
        return ((Integer)get("KanaFindAndReplace")).intValue();
    }
        
    /**
    *
    * @param KanaFindAndReplace
    **/
    public void setKanaFindAndReplace(int KanaFindAndReplace) throws COMException
    {
        put("KanaFindAndReplace", KanaFindAndReplace);
    }
        
    /**
    *
    * @return int
    **/
    public int getFreeformDrawingPrecision() throws COMException
    {
        return ((Integer)get("FreeformDrawingPrecision")).intValue();
    }
        
    /**
    *
    * @param FreeformDrawingPrecision
    **/
    public void setFreeformDrawingPrecision(int FreeformDrawingPrecision) throws COMException
    {
        put("FreeformDrawingPrecision", FreeformDrawingPrecision);
    }
        
    /**
    *
    * @return int
    **/
    public int getFreeformDrawingSmoothing() throws COMException
    {
        return ((Integer)get("FreeformDrawingSmoothing")).intValue();
    }
        
    /**
    *
    * @param FreeformDrawingSmoothing
    **/
    public void setFreeformDrawingSmoothing(int FreeformDrawingSmoothing) throws COMException
    {
        put("FreeformDrawingSmoothing", FreeformDrawingSmoothing);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getDeveloperMode() throws COMException
    {
        return ((Boolean)get("DeveloperMode")).booleanValue();
    }
        
    /**
    *
    * @param DeveloperMode
    **/
    public void setDeveloperMode(boolean DeveloperMode) throws COMException
    {
        put("DeveloperMode", DeveloperMode);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getShowChooseDrawingTypePane() throws COMException
    {
        return ((Boolean)get("ShowChooseDrawingTypePane")).booleanValue();
    }
        
    /**
    *
    * @param ShowChooseDrawingTypePane
    **/
    public void setShowChooseDrawingTypePane(boolean ShowChooseDrawingTypePane) throws COMException
    {
        put("ShowChooseDrawingTypePane", ShowChooseDrawingTypePane);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getShowShapeSearchPane() throws COMException
    {
        return ((Boolean)get("ShowShapeSearchPane")).booleanValue();
    }
        
    /**
    *
    * @param ShowShapeSearchPane
    **/
    public void setShowShapeSearchPane(boolean ShowShapeSearchPane) throws COMException
    {
        put("ShowShapeSearchPane", ShowShapeSearchPane);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getApplyThemesOnShapeAdd() throws COMException
    {
        return ((Boolean)get("ApplyThemesOnShapeAdd")).booleanValue();
    }
        
    /**
    *
    * @param ApplyThemesOnShapeAdd
    **/
    public void setApplyThemesOnShapeAdd(boolean ApplyThemesOnShapeAdd) throws COMException
    {
        put("ApplyThemesOnShapeAdd", ApplyThemesOnShapeAdd);
    }
        
    /**
    *
    * @return int
    **/
    public int getSATextUI() throws COMException
    {
        return ((Integer)get("SATextUI")).intValue();
    }
        
    /**
    *
    * @return int
    **/
    public int getBIDITextUI() throws COMException
    {
        return ((Integer)get("BIDITextUI")).intValue();
    }
        
    /**
    *
    * @return int
    **/
    public int getKashidaTextUI() throws COMException
    {
        return ((Integer)get("KashidaTextUI")).intValue();
    }
        
    /**
    *
    * @return short
    **/
    public short getStat() throws COMException
    {
        return ((Short)get("Stat")).shortValue();
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getShowMoreShapeHandlesOnHover() throws COMException
    {
        return ((Boolean)get("ShowMoreShapeHandlesOnHover")).booleanValue();
    }
        
    /**
    *
    * @param ShowMoreShapeHandlesOnHover
    **/
    public void setShowMoreShapeHandlesOnHover(boolean ShowMoreShapeHandlesOnHover) throws COMException
    {
        put("ShowMoreShapeHandlesOnHover", ShowMoreShapeHandlesOnHover);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getEnableAutoConnect() throws COMException
    {
        return ((Boolean)get("EnableAutoConnect")).booleanValue();
    }
        
    /**
    *
    * @param EnableAutoConnect
    **/
    public void setEnableAutoConnect(boolean EnableAutoConnect) throws COMException
    {
        put("EnableAutoConnect", EnableAutoConnect);
    }
        
}

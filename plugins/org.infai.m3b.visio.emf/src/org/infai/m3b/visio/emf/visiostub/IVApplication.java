
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVApplication
 * Description: 
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVApplication extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0700-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVApplication.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVApplication (it is required by Jawin for some internal working though).
	 */
	public IVApplication() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVApplication interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVApplication(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVApplication interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVApplication(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVApplication interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVApplication interface on.
	 */
	public IVApplication(COMPtr comObject) throws COMException {
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
    public IVDocument getActiveDocument() throws COMException
    {
        IVDocument res = new IVDocument();
          DispatchPtr dispPtr = (DispatchPtr)get("ActiveDocument");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return void
    **/
    public IVPage getActivePage() throws COMException
    {
        IVPage res = new IVPage();
          DispatchPtr dispPtr = (DispatchPtr)get("ActivePage");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return void
    **/
    public IVWindow getActiveWindow() throws COMException
    {
        IVWindow res = new IVWindow();
          DispatchPtr dispPtr = (DispatchPtr)get("ActiveWindow");
          res.stealUnknown(dispPtr);
          return res;
    }
        
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
    * @return void
    **/
    public IVDocuments getDocuments() throws COMException
    {
        IVDocuments res = new IVDocuments();
          DispatchPtr dispPtr = (DispatchPtr)get("Documents");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return short
    **/
    public short getObjectType() throws COMException
    {
        return ((Short)get("ObjectType")).shortValue();
    }
        
    /**
    *
    * @return int
    **/
    public int getOnDataChangeDelay() throws COMException
    {
        return ((Integer)get("OnDataChangeDelay")).intValue();
    }
        
    /**
    *
    * @param OnDataChangeDelay
    **/
    public void setOnDataChangeDelay(int OnDataChangeDelay) throws COMException
    {
        put("OnDataChangeDelay", OnDataChangeDelay);
    }
        
    /**
    *
    * @return int
    **/
    public int getProcessID() throws COMException
    {
        return ((Integer)get("ProcessID")).intValue();
    }
        
    /**
    *
    
    * @return void
    **/
    public void Quit() throws COMException
    {
      
		invokeN("Quit", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void Redo() throws COMException
    {
      
		invokeN("Redo", new Object[] {});
        
    }
    /**
    *
    * @return short
    **/
    public short getScreenUpdating() throws COMException
    {
        return ((Short)get("ScreenUpdating")).shortValue();
    }
        
    /**
    *
    * @param ScreenUpdating
    **/
    public void setScreenUpdating(short ScreenUpdating) throws COMException
    {
        put("ScreenUpdating", ScreenUpdating);
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
    
    * @return void
    **/
    public void Undo() throws COMException
    {
      
		invokeN("Undo", new Object[] {});
        
    }
    /**
    *
    * @return String
    **/
    public String getVersion() throws COMException
    {
         return (String)get("Version");
    }
        
    /**
    *
    * @return short
    **/
    public short getWindowHandle() throws COMException
    {
        return ((Short)get("WindowHandle")).shortValue();
    }
        
    /**
    *
    * @return void
    **/
    public IVWindows getWindows() throws COMException
    {
        IVWindows res = new IVWindows();
          DispatchPtr dispPtr = (DispatchPtr)get("Windows");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return int
    **/
    public int getLanguage() throws COMException
    {
        return ((Integer)get("Language")).intValue();
    }
        
    /**
    *
    * @return short
    **/
    public short getIsVisio16() throws COMException
    {
        return ((Short)get("IsVisio16")).shortValue();
    }
        
    /**
    *
    * @return short
    **/
    public short getIsVisio32() throws COMException
    {
        return ((Short)get("IsVisio32")).shortValue();
    }
        
    /**
    *
    * @return int
    **/
    public int getWindowHandle32() throws COMException
    {
        return ((Integer)get("WindowHandle32")).intValue();
    }
        
    /**
    *
    * @return short
    **/
    public short getInstanceHandle() throws COMException
    {
        return ((Short)get("InstanceHandle")).shortValue();
    }
        
    /**
    *
    * @return int
    **/
    public int getInstanceHandle32() throws COMException
    {
        return ((Integer)get("InstanceHandle32")).intValue();
    }
        
    /**
    *
    * @return void
    **/
    public IVUIObject getBuiltInMenus() throws COMException
    {
        IVUIObject res = new IVUIObject();
          DispatchPtr dispPtr = (DispatchPtr)get("BuiltInMenus");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return void
    **/
    public IVUIObject getBuiltInToolbars(short fIgnored) throws COMException
    {
        IVUIObject res = new IVUIObject();
          DispatchPtr dispPtr = (DispatchPtr)get("BuiltInToolbars", new Short(fIgnored));
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return void
    **/
    public IVUIObject getCustomMenus() throws COMException
    {
        IVUIObject res = new IVUIObject();
          DispatchPtr dispPtr = (DispatchPtr)get("CustomMenus");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    
    * @return void
    **/
    public void SetCustomMenus(Object[] MenusObject) throws COMException
    {
      
		invokeN("SetCustomMenus", new Object[] {MenusObject});
        
    }
    /**
    *
    * @return String
    **/
    public String getCustomMenusFile() throws COMException
    {
         return (String)get("CustomMenusFile");
    }
        
    /**
    *
    * @param CustomMenusFile
    **/
    public void setCustomMenusFile(String CustomMenusFile) throws COMException
    {
        put("CustomMenusFile", CustomMenusFile);
    }
        
    /**
    *
    
    * @return void
    **/
    public void ClearCustomMenus() throws COMException
    {
      
		invokeN("ClearCustomMenus", new Object[] {});
        
    }
    /**
    *
    * @return void
    **/
    public IVUIObject getCustomToolbars() throws COMException
    {
        IVUIObject res = new IVUIObject();
          DispatchPtr dispPtr = (DispatchPtr)get("CustomToolbars");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    
    * @return void
    **/
    public void SetCustomToolbars(Object[] ToolbarsObject) throws COMException
    {
      
		invokeN("SetCustomToolbars", new Object[] {ToolbarsObject});
        
    }
    /**
    *
    * @return String
    **/
    public String getCustomToolbarsFile() throws COMException
    {
         return (String)get("CustomToolbarsFile");
    }
        
    /**
    *
    * @param CustomToolbarsFile
    **/
    public void setCustomToolbarsFile(String CustomToolbarsFile) throws COMException
    {
        put("CustomToolbarsFile", CustomToolbarsFile);
    }
        
    /**
    *
    
    * @return void
    **/
    public void ClearCustomToolbars() throws COMException
    {
      
		invokeN("ClearCustomToolbars", new Object[] {});
        
    }
    /**
    *
    * @return String
    **/
    public String getAddonPaths() throws COMException
    {
         return (String)get("AddonPaths");
    }
        
    /**
    *
    * @param AddonPaths
    **/
    public void setAddonPaths(String AddonPaths) throws COMException
    {
        put("AddonPaths", AddonPaths);
    }
        
    /**
    *
    * @return String
    **/
    public String getDrawingPaths() throws COMException
    {
         return (String)get("DrawingPaths");
    }
        
    /**
    *
    * @param DrawingPaths
    **/
    public void setDrawingPaths(String DrawingPaths) throws COMException
    {
        put("DrawingPaths", DrawingPaths);
    }
        
    /**
    *
    * @return String
    **/
    public String getFilterPaths() throws COMException
    {
         return (String)get("FilterPaths");
    }
        
    /**
    *
    * @param FilterPaths
    **/
    public void setFilterPaths(String FilterPaths) throws COMException
    {
        put("FilterPaths", FilterPaths);
    }
        
    /**
    *
    * @return String
    **/
    public String getHelpPaths() throws COMException
    {
         return (String)get("HelpPaths");
    }
        
    /**
    *
    * @param HelpPaths
    **/
    public void setHelpPaths(String HelpPaths) throws COMException
    {
        put("HelpPaths", HelpPaths);
    }
        
    /**
    *
    * @return String
    **/
    public String getStartupPaths() throws COMException
    {
         return (String)get("StartupPaths");
    }
        
    /**
    *
    * @param StartupPaths
    **/
    public void setStartupPaths(String StartupPaths) throws COMException
    {
        put("StartupPaths", StartupPaths);
    }
        
    /**
    *
    * @return String
    **/
    public String getStencilPaths() throws COMException
    {
         return (String)get("StencilPaths");
    }
        
    /**
    *
    * @param StencilPaths
    **/
    public void setStencilPaths(String StencilPaths) throws COMException
    {
        put("StencilPaths", StencilPaths);
    }
        
    /**
    *
    * @return String
    **/
    public String getTemplatePaths() throws COMException
    {
         return (String)get("TemplatePaths");
    }
        
    /**
    *
    * @param TemplatePaths
    **/
    public void setTemplatePaths(String TemplatePaths) throws COMException
    {
        put("TemplatePaths", TemplatePaths);
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
    * @return short
    **/
    public short getPromptForSummary() throws COMException
    {
        return ((Short)get("PromptForSummary")).shortValue();
    }
        
    /**
    *
    * @param PromptForSummary
    **/
    public void setPromptForSummary(short PromptForSummary) throws COMException
    {
        put("PromptForSummary", PromptForSummary);
    }
        
    /**
    *
    * @return void
    **/
    public IVAddons getAddons() throws COMException
    {
        IVAddons res = new IVAddons();
          DispatchPtr dispPtr = (DispatchPtr)get("Addons");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    
    * @param FileName
    * @return void
    **/
    public void SaveWorkspaceAs(String FileName) throws COMException
    {
      
		invokeN("SaveWorkspaceAs", new Object[] {FileName});
        
    }
    /**
    *
    
    * @param CommandID
    * @return void
    **/
    public void DoCmd(short CommandID) throws COMException
    {
      
		invokeN("DoCmd", new Object[] {new Short(CommandID)});
        
    }
    /**
    *
    * @return String
    **/
    public String getProfileName() throws COMException
    {
         return (String)get("ProfileName");
    }
        
    /**
    *
    * @return String
    **/
    public String getEventInfo(int eventSeqNum) throws COMException
    {
         return (String)get("EventInfo", new Integer(eventSeqNum));
    }
        
    /**
    *
    * @return void
    **/
    public IVEventList getEventList() throws COMException
    {
        IVEventList res = new IVEventList();
          DispatchPtr dispPtr = (DispatchPtr)get("EventList");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return short
    **/
    public short getPersistsEvents() throws COMException
    {
        return ((Short)get("PersistsEvents")).shortValue();
    }
        
    /**
    *
    * @return short
    **/
    public short getActive() throws COMException
    {
        return ((Short)get("Active")).shortValue();
    }
        
    /**
    *
    * @return short
    **/
    public short getDeferRecalc() throws COMException
    {
        return ((Short)get("DeferRecalc")).shortValue();
    }
        
    /**
    *
    * @param DeferRecalc
    **/
    public void setDeferRecalc(short DeferRecalc) throws COMException
    {
        put("DeferRecalc", DeferRecalc);
    }
        
    /**
    *
    * @return short
    **/
    public short getAlertResponse() throws COMException
    {
        return ((Short)get("AlertResponse")).shortValue();
    }
        
    /**
    *
    * @param AlertResponse
    **/
    public void setAlertResponse(short AlertResponse) throws COMException
    {
        put("AlertResponse", AlertResponse);
    }
        
    /**
    *
    * @return short
    **/
    public short getShowProgress() throws COMException
    {
        return ((Short)get("ShowProgress")).shortValue();
    }
        
    /**
    *
    * @param ShowProgress
    **/
    public void setShowProgress(short ShowProgress) throws COMException
    {
        put("ShowProgress", ShowProgress);
    }
        
    /**
    *
    * @return DispatchPtr
    **/
    public DispatchPtr getVbe() throws COMException
    {
         return (DispatchPtr)get("Vbe");
    }
        
    /**
    *
    * @return short
    **/
    public short getShowMenus() throws COMException
    {
        return ((Short)get("ShowMenus")).shortValue();
    }
        
    /**
    *
    * @param ShowMenus
    **/
    public void setShowMenus(short ShowMenus) throws COMException
    {
        put("ShowMenus", ShowMenus);
    }
        
    /**
    *
    * @return short
    **/
    public short getToolbarStyle() throws COMException
    {
        return ((Short)get("ToolbarStyle")).shortValue();
    }
        
    /**
    *
    * @param ToolbarStyle
    **/
    public void setToolbarStyle(short ToolbarStyle) throws COMException
    {
        put("ToolbarStyle", ToolbarStyle);
    }
        
    /**
    *
    * @return short
    **/
    public short getShowStatusBar() throws COMException
    {
        return ((Short)get("ShowStatusBar")).shortValue();
    }
        
    /**
    *
    * @param ShowStatusBar
    **/
    public void setShowStatusBar(short ShowStatusBar) throws COMException
    {
        put("ShowStatusBar", ShowStatusBar);
    }
        
    /**
    *
    * @return short
    **/
    public short getEventsEnabled() throws COMException
    {
        return ((Short)get("EventsEnabled")).shortValue();
    }
        
    /**
    *
    * @param EventsEnabled
    **/
    public void setEventsEnabled(short EventsEnabled) throws COMException
    {
        put("EventsEnabled", EventsEnabled);
    }
        
    /**
    *
    
    * @param StringOrNumber
    * @param UnitsIn
    * @param UnitsOut
    * @param Format
    * @return String
    **/
    public String FormatResult(Variant StringOrNumber,Variant UnitsIn,Variant UnitsOut,String Format) throws COMException
    {
      
		return (String)invokeN("FormatResult", new Object[] {StringOrNumber, UnitsIn, UnitsOut, Format});
        
    }
    /**
    *
    
    * @param StringOrNumber
    * @param UnitsIn
    * @param UnitsOut
    * @return double
    **/
    public double ConvertResult(Variant StringOrNumber,Variant UnitsIn,Variant UnitsOut) throws COMException
    {
      
		return ((Double)invokeN("ConvertResult", new Object[] {StringOrNumber, UnitsIn, UnitsOut})).doubleValue();
        
    }
    /**
    *
    * @return String
    **/
    public String getPath() throws COMException
    {
         return (String)get("Path");
    }
        
    /**
    *
    
    * @param PathsString
    * @param NameArray
    * @return void
    **/
    public void EnumDirectories(String PathsString,Object[] NameArray) throws COMException
    {
      
		invokeN("EnumDirectories", new Object[] {PathsString, NameArray});
        
    }
    /**
    *
    * @return int
    **/
    public int getTraceFlags() throws COMException
    {
        return ((Integer)get("TraceFlags")).intValue();
    }
        
    /**
    *
    * @param TraceFlags
    **/
    public void setTraceFlags(int TraceFlags) throws COMException
    {
        put("TraceFlags", TraceFlags);
    }
        
    /**
    *
    
    * @return void
    **/
    public void PurgeUndo() throws COMException
    {
      
		invokeN("PurgeUndo", new Object[] {});
        
    }
    /**
    *
    
    * @param ContextString
    * @return int
    **/
    public int QueueMarkerEvent(String ContextString) throws COMException
    {
      
		return ((Integer)invokeN("QueueMarkerEvent", new Object[] {ContextString})).intValue();
        
    }
    /**
    *
    * @return short
    **/
    public short getShowToolbar() throws COMException
    {
        return ((Short)get("ShowToolbar")).shortValue();
    }
        
    /**
    *
    * @param ShowToolbar
    **/
    public void setShowToolbar(short ShowToolbar) throws COMException
    {
        put("ShowToolbar", ShowToolbar);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getLiveDynamics() throws COMException
    {
        return ((Boolean)get("LiveDynamics")).booleanValue();
    }
        
    /**
    *
    * @param LiveDynamics
    **/
    public void setLiveDynamics(boolean LiveDynamics) throws COMException
    {
        put("LiveDynamics", LiveDynamics);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getAutoLayout() throws COMException
    {
        return ((Boolean)get("AutoLayout")).booleanValue();
    }
        
    /**
    *
    * @param AutoLayout
    **/
    public void setAutoLayout(boolean AutoLayout) throws COMException
    {
        put("AutoLayout", AutoLayout);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getVisible() throws COMException
    {
        return ((Boolean)get("Visible")).booleanValue();
    }
        
    /**
    *
    * @param Visible
    **/
    public void setVisible(boolean Visible) throws COMException
    {
        put("Visible", Visible);
    }
        
    /**
    *
    
    * @param bstrUndoScopeName
    * @return int
    **/
    public int BeginUndoScope(String bstrUndoScopeName) throws COMException
    {
      
		return ((Integer)invokeN("BeginUndoScope", new Object[] {bstrUndoScopeName})).intValue();
        
    }
    /**
    *
    
    * @param nScopeID
    * @param bCommit
    * @return void
    **/
    public void EndUndoScope(int nScopeID,boolean bCommit) throws COMException
    {
      
		invokeN("EndUndoScope", new Object[] {new Integer(nScopeID), new Boolean(bCommit)});
        
    }
    /**
    *
    
    * @param pUndoUnit
    * @return void
    **/
    public void AddUndoUnit(IUnknown pUndoUnit) throws COMException
    {
      
		invokeN("AddUndoUnit", new Object[] {pUndoUnit});
        
    }
    /**
    *
    * @return String
    **/
    public String getCommandLine() throws COMException
    {
         return (String)get("CommandLine");
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getIsUndoingOrRedoing() throws COMException
    {
        return ((Boolean)get("IsUndoingOrRedoing")).booleanValue();
    }
        
    /**
    *
    * @return int
    **/
    public int getCurrentScope() throws COMException
    {
        return ((Integer)get("CurrentScope")).intValue();
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getIsInScope(int nCmdID) throws COMException
    {
        return ((Boolean)get("IsInScope", new Integer(nCmdID))).booleanValue();
    }
        
    /**
    *
    * @return DispatchPtr
    **/
    public DispatchPtr getold_Addins() throws COMException
    {
         return (DispatchPtr)get("old_Addins");
    }
        
    /**
    *
    * @return String
    **/
    public String getProductName() throws COMException
    {
         return (String)get("ProductName");
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getUndoEnabled() throws COMException
    {
        return ((Boolean)get("UndoEnabled")).booleanValue();
    }
        
    /**
    *
    * @param UndoEnabled
    **/
    public void setUndoEnabled(boolean UndoEnabled) throws COMException
    {
        put("UndoEnabled", UndoEnabled);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getShowChanges() throws COMException
    {
        return ((Boolean)get("ShowChanges")).booleanValue();
    }
        
    /**
    *
    * @param ShowChanges
    **/
    public void setShowChanges(boolean ShowChanges) throws COMException
    {
        put("ShowChanges", ShowChanges);
    }
        
    /**
    *
    * @return short
    **/
    public short getTypelibMajorVersion() throws COMException
    {
        return ((Short)get("TypelibMajorVersion")).shortValue();
    }
        
    /**
    *
    * @return short
    **/
    public short getTypelibMinorVersion() throws COMException
    {
        return ((Short)get("TypelibMinorVersion")).shortValue();
    }
        
    /**
    *
    * @return short
    **/
    public short getAutoRecoverInterval() throws COMException
    {
        return ((Short)get("AutoRecoverInterval")).shortValue();
    }
        
    /**
    *
    * @param AutoRecoverInterval
    **/
    public void setAutoRecoverInterval(short AutoRecoverInterval) throws COMException
    {
        put("AutoRecoverInterval", AutoRecoverInterval);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getInhibitSelectChange() throws COMException
    {
        return ((Boolean)get("InhibitSelectChange")).booleanValue();
    }
        
    /**
    *
    * @param InhibitSelectChange
    **/
    public void setInhibitSelectChange(boolean InhibitSelectChange) throws COMException
    {
        put("InhibitSelectChange", InhibitSelectChange);
    }
        
    /**
    *
    * @return String
    **/
    public String getActivePrinter() throws COMException
    {
         return (String)get("ActivePrinter");
    }
        
    /**
    *
    * @param ActivePrinter
    **/
    public void setActivePrinter(String ActivePrinter) throws COMException
    {
        put("ActivePrinter", ActivePrinter);
    }
        
    /**
    *
    * @return Object[]
    **/
    public Object[] getAvailablePrinters() throws COMException
    {
        return null; // not implementedget("AvailablePrinters");
    }
        
    /**
    *
    
    * @param bstrScopeName
    * @return void
    **/
    public void RenameCurrentScope(String bstrScopeName) throws COMException
    {
      
		invokeN("RenameCurrentScope", new Object[] {bstrScopeName});
        
    }
    /**
    *
    
    * @param bstrHelpFileName
    * @param Command
    * @param Data
    * @return void
    **/
    public void InvokeHelp(String bstrHelpFileName,int Command,int Data) throws COMException
    {
      
		invokeN("InvokeHelp", new Object[] {bstrHelpFileName, new Integer(Command), new Integer(Data)});
        
    }
    /**
    *
    * @return DispatchPtr
    **/
    public DispatchPtr getCommandBars() throws COMException
    {
         return (DispatchPtr)get("CommandBars");
    }
        
    /**
    *
    * @return int
    **/
    public int getBuild() throws COMException
    {
        return ((Integer)get("Build")).intValue();
    }
        
    /**
    *
    * @return DispatchPtr
    **/
    public DispatchPtr getCOMAddIns() throws COMException
    {
         return (DispatchPtr)get("COMAddIns");
    }
        
    /**
    *
    * @return Variant
    **/
    public Object getDefaultPageUnits() throws COMException
    {
         return get("DefaultPageUnits");
    }
        
    /**
    *
    * @param DefaultPageUnits
    **/
    public void setDefaultPageUnits(Variant DefaultPageUnits) throws COMException
    {
        put("DefaultPageUnits", DefaultPageUnits);
    }
        
    /**
    *
    * @return Variant
    **/
    public Object getDefaultTextUnits() throws COMException
    {
         return get("DefaultTextUnits");
    }
        
    /**
    *
    * @param DefaultTextUnits
    **/
    public void setDefaultTextUnits(Variant DefaultTextUnits) throws COMException
    {
        put("DefaultTextUnits", DefaultTextUnits);
    }
        
    /**
    *
    * @return Variant
    **/
    public Object getDefaultAngleUnits() throws COMException
    {
         return get("DefaultAngleUnits");
    }
        
    /**
    *
    * @param DefaultAngleUnits
    **/
    public void setDefaultAngleUnits(Variant DefaultAngleUnits) throws COMException
    {
        put("DefaultAngleUnits", DefaultAngleUnits);
    }
        
    /**
    *
    * @return Variant
    **/
    public Object getDefaultDurationUnits() throws COMException
    {
         return get("DefaultDurationUnits");
    }
        
    /**
    *
    * @param DefaultDurationUnits
    **/
    public void setDefaultDurationUnits(Variant DefaultDurationUnits) throws COMException
    {
        put("DefaultDurationUnits", DefaultDurationUnits);
    }
        
    /**
    *
    * @return int
    **/
    public int getFullBuild() throws COMException
    {
        return ((Integer)get("FullBuild")).intValue();
    }
        
    /**
    *
    
    * @param uStateID
    * @param bEnter
    * @return void
    **/
    public void OnComponentEnterState(int uStateID,boolean bEnter) throws COMException
    {
      
		invokeN("OnComponentEnterState", new Object[] {new Integer(uStateID), new Boolean(bEnter)});
        
    }
    /**
    *
    * @return boolean
    **/
    public boolean getVBAEnabled() throws COMException
    {
        return ((Boolean)get("VBAEnabled")).booleanValue();
    }
        
    /**
    *
    * @return int
    **/
    public int getDefaultZoomBehavior() throws COMException
    {
        return ((Integer)get("DefaultZoomBehavior")).intValue();
    }
        
    /**
    *
    * @param DefaultZoomBehavior
    **/
    public void setDefaultZoomBehavior(int DefaultZoomBehavior) throws COMException
    {
        put("DefaultZoomBehavior", DefaultZoomBehavior);
    }
        
    /**
    *
    
    * @param nWhichStatistic
    * @return Variant
    **/
    public Object GetUsageStatistic(int nWhichStatistic) throws COMException
    {
      
		return invokeN("GetUsageStatistic", new Object[] {new Integer(nWhichStatistic)});
        
    }
    /**
    *
    * @return void
    **/
    public void getDialogFont() throws COMException
    {
        get("DialogFont");
    }
        
    /**
    *
    * @return int
    **/
    public int getLanguageHelp() throws COMException
    {
        return ((Integer)get("LanguageHelp")).intValue();
    }
        
    /**
    *
    * @return void
    **/
    public IVWindow getWindow() throws COMException
    {
        IVWindow res = new IVWindow();
          DispatchPtr dispPtr = (DispatchPtr)get("Window");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return String
    **/
    public String getName() throws COMException
    {
         return (String)get("Name");
    }
        
    /**
    *
    * @return IUnknown
    **/
    public IUnknown getConnectorToolDataObject() throws COMException
    {
         return (IUnknown)get("ConnectorToolDataObject");
    }
        
    /**
    *
    * @return void
    **/
    public IVApplicationSettings getSettings() throws COMException
    {
        IVApplicationSettings res = new IVApplicationSettings();
          DispatchPtr dispPtr = (DispatchPtr)get("Settings");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    
    * @param StringOrNumber
    * @param UnitsIn
    * @param UnitsOut
    * @param Format
    * @param LangID
    * @param CalendarID
    * @return String
    **/
    public String FormatResultEx(Variant StringOrNumber,Variant UnitsIn,Variant UnitsOut,String Format,int LangID,int CalendarID) throws COMException
    {
      
		return (String)invokeN("FormatResultEx", new Object[] {StringOrNumber, UnitsIn, UnitsOut, Format, new Integer(LangID), new Integer(CalendarID)});
        
    }
    /**
    *
    * @return DispatchPtr
    **/
    public DispatchPtr getSaveAsWebObject() throws COMException
    {
         return (DispatchPtr)get("SaveAsWebObject");
    }
        
    /**
    *
    * @return DispatchPtr
    **/
    public DispatchPtr getMsoDebugOptions() throws COMException
    {
         return (DispatchPtr)get("MsoDebugOptions");
    }
        
    /**
    *
    * @return String
    **/
    public String getMyShapesPath() throws COMException
    {
         return (String)get("MyShapesPath");
    }
        
    /**
    *
    * @param MyShapesPath
    **/
    public void setMyShapesPath(String MyShapesPath) throws COMException
    {
        put("MyShapesPath", MyShapesPath);
    }
        
    /**
    *
    * @return IUnknown
    **/
    public IUnknown getDefaultRectangleDataObject() throws COMException
    {
         return (IUnknown)get("DefaultRectangleDataObject");
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getDataFeaturesEnabled() throws COMException
    {
        return ((Boolean)get("DataFeaturesEnabled")).booleanValue();
    }
        
    /**
    *
    * @return DispatchPtr
    **/
    public DispatchPtr getLanguageSettings() throws COMException
    {
         return (DispatchPtr)get("LanguageSettings");
    }
        
    /**
    *
    * @return DispatchPtr
    **/
    public DispatchPtr getAssistance() throws COMException
    {
         return (DispatchPtr)get("Assistance");
    }
        
}


package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVWindow
 * Description: 
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVWindow extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0710-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVWindow.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVWindow (it is required by Jawin for some internal working though).
	 */
	public IVWindow() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVWindow interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVWindow(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVWindow interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVWindow(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVWindow interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVWindow interface on.
	 */
	public IVWindow(COMPtr comObject) throws COMException {
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
    * @return short
    **/
    public short getStat() throws COMException
    {
        return ((Short)get("Stat")).shortValue();
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
    
    * @return void
    **/
    public void Activate() throws COMException
    {
      
		invokeN("Activate", new Object[] {});
        
    }
    /**
    *
    * @return short
    **/
    public short getType() throws COMException
    {
        return ((Short)get("Type")).shortValue();
    }
        
    /**
    *
    * @return void
    **/
    public IVDocument getDocument() throws COMException
    {
        IVDocument res = new IVDocument();
          DispatchPtr dispPtr = (DispatchPtr)get("Document");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return void
    **/
    public IVPage getPageAsObj() throws COMException
    {
        IVPage res = new IVPage();
          DispatchPtr dispPtr = (DispatchPtr)get("PageAsObj");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @param PageFromName
    **/
    public void setPageFromName(String PageFromName) throws COMException
    {
        put("PageFromName", PageFromName);
    }
        
    /**
    *
    * @return double
    **/
    public double getZoom() throws COMException
    {
         return ((Double)get("Zoom")).doubleValue();
    }
        
    /**
    *
    * @param Zoom
    **/
    public void setZoom(double Zoom) throws COMException
    {
        put("Zoom", Zoom);
    }
        
    /**
    *
    
    * @return void
    **/
    public void Close() throws COMException
    {
      
		invokeN("Close", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void SelectAll() throws COMException
    {
      
		invokeN("SelectAll", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void DeselectAll() throws COMException
    {
      
		invokeN("DeselectAll", new Object[] {});
        
    }
    /**
    *
    * @return void
    **/
    public IVSelection getSelection() throws COMException
    {
        IVSelection res = new IVSelection();
          DispatchPtr dispPtr = (DispatchPtr)get("Selection");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    
    * @param SelectAction
    * @return void
    **/
    public void Select(Object[] SheetObject,short SelectAction) throws COMException
    {
      
		invokeN("Select", new Object[] {SheetObject, new Short(SelectAction)});
        
    }
    /**
    *
    * @return short
    **/
    public short getIndex() throws COMException
    {
        return ((Short)get("Index")).shortValue();
    }
        
    /**
    *
    
    * @return void
    **/
    public void Cut() throws COMException
    {
      
		invokeN("Cut", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void Copy() throws COMException
    {
      
		invokeN("Copy", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void Paste() throws COMException
    {
      
		invokeN("Paste", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void Delete() throws COMException
    {
      
		invokeN("Delete", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void Duplicate() throws COMException
    {
      
		invokeN("Duplicate", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void Group() throws COMException
    {
      
		invokeN("Group", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void Union() throws COMException
    {
      
		invokeN("Union", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void Combine() throws COMException
    {
      
		invokeN("Combine", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void Fragment() throws COMException
    {
      
		invokeN("Fragment", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void AddToGroup() throws COMException
    {
      
		invokeN("AddToGroup", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void RemoveFromGroup() throws COMException
    {
      
		invokeN("RemoveFromGroup", new Object[] {});
        
    }
    /**
    *
    * @return short
    **/
    public short getSubType() throws COMException
    {
        return ((Short)get("SubType")).shortValue();
    }
        
    /**
    *
    
    * @return void
    **/
    public void Intersect() throws COMException
    {
      
		invokeN("Intersect", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void Subtract() throws COMException
    {
      
		invokeN("Subtract", new Object[] {});
        
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
    public short getWindowHandle() throws COMException
    {
        return ((Short)get("WindowHandle")).shortValue();
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
    
    * @return void
    **/
    public void Trim() throws COMException
    {
      
		invokeN("Trim", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void Join() throws COMException
    {
      
		invokeN("Join", new Object[] {});
        
    }
    /**
    *
    * @return short
    **/
    public short getShowRulers() throws COMException
    {
        return ((Short)get("ShowRulers")).shortValue();
    }
        
    /**
    *
    * @param ShowRulers
    **/
    public void setShowRulers(short ShowRulers) throws COMException
    {
        put("ShowRulers", ShowRulers);
    }
        
    /**
    *
    * @return short
    **/
    public short getShowGrid() throws COMException
    {
        return ((Short)get("ShowGrid")).shortValue();
    }
        
    /**
    *
    * @param ShowGrid
    **/
    public void setShowGrid(short ShowGrid) throws COMException
    {
        put("ShowGrid", ShowGrid);
    }
        
    /**
    *
    * @return short
    **/
    public short getShowGuides() throws COMException
    {
        return ((Short)get("ShowGuides")).shortValue();
    }
        
    /**
    *
    * @param ShowGuides
    **/
    public void setShowGuides(short ShowGuides) throws COMException
    {
        put("ShowGuides", ShowGuides);
    }
        
    /**
    *
    * @return short
    **/
    public short getShowConnectPoints() throws COMException
    {
        return ((Short)get("ShowConnectPoints")).shortValue();
    }
        
    /**
    *
    * @param ShowConnectPoints
    **/
    public void setShowConnectPoints(short ShowConnectPoints) throws COMException
    {
        put("ShowConnectPoints", ShowConnectPoints);
    }
        
    /**
    *
    * @return short
    **/
    public short getShowPageBreaks() throws COMException
    {
        return ((Short)get("ShowPageBreaks")).shortValue();
    }
        
    /**
    *
    * @param ShowPageBreaks
    **/
    public void setShowPageBreaks(short ShowPageBreaks) throws COMException
    {
        put("ShowPageBreaks", ShowPageBreaks);
    }
        
    /**
    *
    * @return Variant
    **/
    public Object getPage() throws COMException
    {
         return get("Page");
    }
        
    /**
    *
    * @param Page
    **/
    public void setPage(Variant Page) throws COMException
    {
        put("Page", Page);
    }
        
    /**
    *
    
    * @param NameArray
    * @return void
    **/
    public void DockedStencils(Object[] NameArray) throws COMException
    {
      
		invokeN("DockedStencils", new Object[] {NameArray});
        
    }
    /**
    *
    * @return Variant
    **/
    public Object getMaster() throws COMException
    {
         return get("Master");
    }
        
    /**
    *
    * @return short
    **/
    public short getShowScrollBars() throws COMException
    {
        return ((Short)get("ShowScrollBars")).shortValue();
    }
        
    /**
    *
    * @param ShowScrollBars
    **/
    public void setShowScrollBars(short ShowScrollBars) throws COMException
    {
        put("ShowScrollBars", ShowScrollBars);
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
    * @return String
    **/
    public String getCaption() throws COMException
    {
         return (String)get("Caption");
    }
        
    /**
    *
    * @param Caption
    **/
    public void setCaption(String Caption) throws COMException
    {
        put("Caption", Caption);
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
    
    * @param nxFlags
    * @param nyFlags
    * @return void
    **/
    public void Scroll(int nxFlags,int nyFlags) throws COMException
    {
      
		invokeN("Scroll", new Object[] {new Integer(nxFlags), new Integer(nyFlags)});
        
    }
    /**
    *
    
    * @param x
    * @param y
    * @return void
    **/
    public void ScrollViewTo(double x,double y) throws COMException
    {
      
		invokeN("ScrollViewTo", new Object[] {new Double(x), new Double(y)});
        
    }
    /**
    *
    
    * @return void
    **/
    public void GetViewRect(double[] pdLeft,double[] pdTop,double[] pdWidth,double[] pdHeight) throws COMException
    {
      
		invokeN("GetViewRect", new Object[] {pdLeft, pdTop, pdWidth, pdHeight});
        
    }
    /**
    *
    
    * @param dLeft
    * @param dTop
    * @param dWidth
    * @param dHeight
    * @return void
    **/
    public void SetViewRect(double dLeft,double dTop,double dWidth,double dHeight) throws COMException
    {
      
		invokeN("SetViewRect", new Object[] {new Double(dLeft), new Double(dTop), new Double(dWidth), new Double(dHeight)});
        
    }
    /**
    *
    
    * @return void
    **/
    public void GetWindowRect(int[] pnLeft,int[] pnTop,int[] pnWidth,int[] pnHeight) throws COMException
    {
      
		invokeN("GetWindowRect", new Object[] {pnLeft, pnTop, pnWidth, pnHeight});
        
    }
    /**
    *
    
    * @param nLeft
    * @param nTop
    * @param nWidth
    * @param nHeight
    * @return void
    **/
    public void SetWindowRect(int nLeft,int nTop,int nWidth,int nHeight) throws COMException
    {
      
		invokeN("SetWindowRect", new Object[] {new Integer(nLeft), new Integer(nTop), new Integer(nWidth), new Integer(nHeight)});
        
    }
    /**
    *
    * @return int
    **/
    public int getWindowState() throws COMException
    {
        return ((Integer)get("WindowState")).intValue();
    }
        
    /**
    *
    * @param WindowState
    **/
    public void setWindowState(int WindowState) throws COMException
    {
        put("WindowState", WindowState);
    }
        
    /**
    *
    * @return int
    **/
    public int getViewFit() throws COMException
    {
        return ((Integer)get("ViewFit")).intValue();
    }
        
    /**
    *
    * @param ViewFit
    **/
    public void setViewFit(int ViewFit) throws COMException
    {
        put("ViewFit", ViewFit);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getIsEditingText() throws COMException
    {
        return ((Boolean)get("IsEditingText")).booleanValue();
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getIsEditingOLE() throws COMException
    {
        return ((Boolean)get("IsEditingOLE")).booleanValue();
    }
        
    /**
    *
    * @return void
    **/
    public IVWindows getParent() throws COMException
    {
        IVWindows res = new IVWindows();
          DispatchPtr dispPtr = (DispatchPtr)get("Parent");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return void
    **/
    public IVMasterShortcut getMasterShortcut() throws COMException
    {
        IVMasterShortcut res = new IVMasterShortcut();
          DispatchPtr dispPtr = (DispatchPtr)get("MasterShortcut");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return int
    **/
    public int getID() throws COMException
    {
        return ((Integer)get("ID")).intValue();
    }
        
    /**
    *
    * @return void
    **/
    public IVWindow getParentWindow() throws COMException
    {
        IVWindow res = new IVWindow();
          DispatchPtr dispPtr = (DispatchPtr)get("ParentWindow");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return String
    **/
    public String getMergeID() throws COMException
    {
         return (String)get("MergeID");
    }
        
    /**
    *
    * @return String
    **/
    public String getMergeClass() throws COMException
    {
         return (String)get("MergeClass");
    }
        
    /**
    *
    * @return int
    **/
    public int getMergePosition() throws COMException
    {
        return ((Integer)get("MergePosition")).intValue();
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getAllowEditing() throws COMException
    {
        return ((Boolean)get("AllowEditing")).booleanValue();
    }
        
    /**
    *
    * @param AllowEditing
    **/
    public void setAllowEditing(boolean AllowEditing) throws COMException
    {
        put("AllowEditing", AllowEditing);
    }
        
    /**
    *
    * @return double
    **/
    public double getPageTabWidth() throws COMException
    {
         return ((Double)get("PageTabWidth")).doubleValue();
    }
        
    /**
    *
    * @param PageTabWidth
    **/
    public void setPageTabWidth(double PageTabWidth) throws COMException
    {
        put("PageTabWidth", PageTabWidth);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getShowPageTabs() throws COMException
    {
        return ((Boolean)get("ShowPageTabs")).booleanValue();
    }
        
    /**
    *
    * @param ShowPageTabs
    **/
    public void setShowPageTabs(boolean ShowPageTabs) throws COMException
    {
        put("ShowPageTabs", ShowPageTabs);
    }
        
    /**
    *
    * @param MergeID
    **/
    public void setMergeID(String MergeID) throws COMException
    {
        put("MergeID", MergeID);
    }
        
    /**
    *
    * @param MergeClass
    **/
    public void setMergeClass(String MergeClass) throws COMException
    {
        put("MergeClass", MergeClass);
    }
        
    /**
    *
    * @param MergePosition
    **/
    public void setMergePosition(int MergePosition) throws COMException
    {
        put("MergePosition", MergePosition);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getInPlace() throws COMException
    {
        return ((Boolean)get("InPlace")).booleanValue();
    }
        
    /**
    *
    * @return String
    **/
    public String getMergeCaption() throws COMException
    {
         return (String)get("MergeCaption");
    }
        
    /**
    *
    * @param MergeCaption
    **/
    public void setMergeCaption(String MergeCaption) throws COMException
    {
        put("MergeCaption", MergeCaption);
    }
        
    /**
    *
    * @return void
    **/
    public void getIcon() throws COMException
    {
        get("Icon");
    }
        
          //for  DISPATCH_PROPERTYPUTREF not implemented
        
    /**
    *
    * @return void
    **/
    public IVShape getShape() throws COMException
    {
        IVShape res = new IVShape();
          DispatchPtr dispPtr = (DispatchPtr)get("Shape");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return void
    **/
    public IVCell getSelectedCell() throws COMException
    {
        IVCell res = new IVCell();
          DispatchPtr dispPtr = (DispatchPtr)get("SelectedCell");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    
    * @return void
    **/
    public IVWindow NewWindow() throws COMException
    {
      IVWindow res = new IVWindow();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("NewWindow", new Object[] {});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    * @return int
    **/
    public int getBackgroundColor() throws COMException
    {
        return ((Integer)get("BackgroundColor")).intValue();
    }
        
    /**
    *
    * @param BackgroundColor
    **/
    public void setBackgroundColor(int BackgroundColor) throws COMException
    {
        put("BackgroundColor", BackgroundColor);
    }
        
    /**
    *
    * @return int
    **/
    public int getBackgroundColorGradient() throws COMException
    {
        return ((Integer)get("BackgroundColorGradient")).intValue();
    }
        
    /**
    *
    * @param BackgroundColorGradient
    **/
    public void setBackgroundColorGradient(int BackgroundColorGradient) throws COMException
    {
        put("BackgroundColorGradient", BackgroundColorGradient);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getShowPageOutline() throws COMException
    {
        return ((Boolean)get("ShowPageOutline")).booleanValue();
    }
        
    /**
    *
    * @param ShowPageOutline
    **/
    public void setShowPageOutline(boolean ShowPageOutline) throws COMException
    {
        put("ShowPageOutline", ShowPageOutline);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getScrollLock() throws COMException
    {
        return ((Boolean)get("ScrollLock")).booleanValue();
    }
        
    /**
    *
    * @param ScrollLock
    **/
    public void setScrollLock(boolean ScrollLock) throws COMException
    {
        put("ScrollLock", ScrollLock);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getZoomLock() throws COMException
    {
        return ((Boolean)get("ZoomLock")).booleanValue();
    }
        
    /**
    *
    * @param ZoomLock
    **/
    public void setZoomLock(boolean ZoomLock) throws COMException
    {
        put("ZoomLock", ZoomLock);
    }
        
    /**
    *
    * @return int
    **/
    public int getZoomBehavior() throws COMException
    {
        return ((Integer)get("ZoomBehavior")).intValue();
    }
        
    /**
    *
    * @param ZoomBehavior
    **/
    public void setZoomBehavior(int ZoomBehavior) throws COMException
    {
        put("ZoomBehavior", ZoomBehavior);
    }
        
    /**
    *
    * @return Object[]
    **/
    public Object[] getSelectedMasters() throws COMException
    {
        return null; // not implementedget("SelectedMasters");
    }
        
    /**
    *
    * @return void
    **/
    public IVCharacters getSelectedText() throws COMException
    {
        IVCharacters res = new IVCharacters();
          DispatchPtr dispPtr = (DispatchPtr)get("SelectedText");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @param SelectedText
    **/
    public void setSelectedText(Object[] SelectedText) throws COMException
    {
        put("SelectedText", SelectedText);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getReviewerMarkupVisible(int ReviewerID) throws COMException
    {
        return ((Boolean)get("ReviewerMarkupVisible", new Integer(ReviewerID))).booleanValue();
    }
        
    /**
    *
    * @param ReviewerMarkupVisible
    **/
    public void setReviewerMarkupVisible(int ReviewerID,boolean newValue2) throws COMException
    {
        put("ReviewerMarkupVisible", new Integer(ReviewerID), newValue2);
    }
        
    /**
    *
    * @param Selection
    **/
    public void setSelection(Object[] Selection) throws COMException
    {
        put("Selection", Selection);
    }
        
    /**
    *
    * @return void
    **/
    public IVDataRecordset getSelectedDataRecordset() throws COMException
    {
        IVDataRecordset res = new IVDataRecordset();
          DispatchPtr dispPtr = (DispatchPtr)get("SelectedDataRecordset");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @param SelectedDataRecordset
    **/
    public void setSelectedDataRecordset(Object[] SelectedDataRecordset) throws COMException
    {
        put("SelectedDataRecordset", SelectedDataRecordset);
    }
        
    /**
    *
    * @return int
    **/
    public int getSelectedDataRowID() throws COMException
    {
        return ((Integer)get("SelectedDataRowID")).intValue();
    }
        
    /**
    *
    * @param SelectedDataRowID
    **/
    public void setSelectedDataRowID(int SelectedDataRowID) throws COMException
    {
        put("SelectedDataRowID", SelectedDataRowID);
    }
        
}

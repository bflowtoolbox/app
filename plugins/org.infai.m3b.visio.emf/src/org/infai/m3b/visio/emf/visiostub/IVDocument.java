
package org.infai.m3b.visio.emf.visiostub;

import java.util.Date;

import org.jawin.COMException;
import org.jawin.COMPtr;
import org.jawin.DispatchPtr;
import org.jawin.GUID;
import org.jawin.IUnknown;
import org.jawin.IdentityManager;
import org.jawin.Variant;


/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVDocument
 * Description: 
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVDocument extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0705-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVDocument.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVDocument (it is required by Jawin for some internal working though).
	 */
	public IVDocument() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVDocument interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVDocument(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVDocument interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVDocument(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVDocument interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVDocument interface on.
	 */
	public IVDocument(COMPtr comObject) throws COMException {
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
    * @return short
    **/
    public short getInPlace() throws COMException
    {
        return ((Short)get("InPlace")).shortValue();
    }
        
    /**
    *
    * @return void
    **/
    public IVMasters getMasters() throws COMException
    {
        IVMasters res = new IVMasters();
          DispatchPtr dispPtr = (DispatchPtr)get("Masters");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return void
    **/
    public IVPages getPages() throws COMException
    {
        IVPages res = new IVPages();
          DispatchPtr dispPtr = (DispatchPtr)get("Pages");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return void
    **/
    public IVStyles getStyles() throws COMException
    {
        IVStyles res = new IVStyles();
          DispatchPtr dispPtr = (DispatchPtr)get("Styles");
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
    * @return String
    **/
    public String getPath() throws COMException
    {
         return (String)get("Path");
    }
        
    /**
    *
    * @return String
    **/
    public String getFullName() throws COMException
    {
         return (String)get("FullName");
    }
        
    /**
    *
    
    * @param ObjectToDrop
    * @param xPos
    * @param yPos
    * @return void
    **/
    public IVMaster Drop(IUnknown ObjectToDrop,short xPos,short yPos) throws COMException
    {
      IVMaster res = new IVMaster();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("Drop", new Object[] {ObjectToDrop, new Short(xPos), new Short(yPos)});
          res.stealUnknown(dispPtr);
          return res;
        
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
    * @return short
    **/
    public short getold_Saved() throws COMException
    {
        return ((Short)get("old_Saved")).shortValue();
    }
        
    /**
    *
    * @param old_Saved
    **/
    public void setold_Saved(short old_Saved) throws COMException
    {
        put("old_Saved", old_Saved);
    }
        
    /**
    *
    * @return short
    **/
    public short getReadOnly() throws COMException
    {
        return ((Short)get("ReadOnly")).shortValue();
    }
        
    /**
    *
    
    * @return short
    **/
    public short Save() throws COMException
    {
      
		return ((Short)invokeN("Save", new Object[] {})).shortValue();
        
    }
    /**
    *
    
    * @param FileName
    * @return short
    **/
    public short SaveAs(String FileName) throws COMException
    {
      
		return ((Short)invokeN("SaveAs", new Object[] {FileName})).shortValue();
        
    }
    /**
    *
    * @return int
    **/
    public int getold_Version() throws COMException
    {
        return ((Integer)get("old_Version")).intValue();
    }
        
    /**
    *
    * @param old_Version
    **/
    public void setold_Version(int old_Version) throws COMException
    {
        put("old_Version", old_Version);
    }
        
    /**
    *
    * @return String
    **/
    public String getTitle() throws COMException
    {
         return (String)get("Title");
    }
        
    /**
    *
    * @param Title
    **/
    public void setTitle(String Title) throws COMException
    {
        put("Title", Title);
    }
        
    /**
    *
    * @return String
    **/
    public String getSubject() throws COMException
    {
         return (String)get("Subject");
    }
        
    /**
    *
    * @param Subject
    **/
    public void setSubject(String Subject) throws COMException
    {
        put("Subject", Subject);
    }
        
    /**
    *
    * @return String
    **/
    public String getCreator() throws COMException
    {
         return (String)get("Creator");
    }
        
    /**
    *
    * @param Creator
    **/
    public void setCreator(String Creator) throws COMException
    {
        put("Creator", Creator);
    }
        
    /**
    *
    * @return String
    **/
    public String getKeywords() throws COMException
    {
         return (String)get("Keywords");
    }
        
    /**
    *
    * @param Keywords
    **/
    public void setKeywords(String Keywords) throws COMException
    {
        put("Keywords", Keywords);
    }
        
    /**
    *
    * @return String
    **/
    public String getDescription() throws COMException
    {
         return (String)get("Description");
    }
        
    /**
    *
    * @param Description
    **/
    public void setDescription(String Description) throws COMException
    {
        put("Description", Description);
    }
        
    /**
    *
    
    * @return void
    **/
    public void Print() throws COMException
    {
      
		invokeN("Print", new Object[] {});
        
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
    * @return void
    **/
    public IVFonts getFonts() throws COMException
    {
        IVFonts res = new IVFonts();
          DispatchPtr dispPtr = (DispatchPtr)get("Fonts");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return void
    **/
    public IVColors getColors() throws COMException
    {
        IVColors res = new IVColors();
          DispatchPtr dispPtr = (DispatchPtr)get("Colors");
          res.stealUnknown(dispPtr);
          return res;
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
    * @return String
    **/
    public String getTemplate() throws COMException
    {
         return (String)get("Template");
    }
        
    /**
    *
    
    * @param FileName
    * @param SaveFlags
    * @return void
    **/
    public void SaveAsEx(String FileName,short SaveFlags) throws COMException
    {
      
		invokeN("SaveAsEx", new Object[] {FileName, new Short(SaveFlags)});
        
    }
    /**
    *
    * @return short
    **/
    public short getold_SavePreviewMode() throws COMException
    {
        return ((Short)get("old_SavePreviewMode")).shortValue();
    }
        
    /**
    *
    * @param old_SavePreviewMode
    **/
    public void setold_SavePreviewMode(short old_SavePreviewMode) throws COMException
    {
        put("old_SavePreviewMode", old_SavePreviewMode);
    }
        
    /**
    *
    
    * @param ID
    * @param FileName
    * @return void
    **/
    public void GetIcon(short ID,String FileName) throws COMException
    {
      
		invokeN("GetIcon", new Object[] {new Short(ID), FileName});
        
    }
    /**
    *
    
    * @param ID
    * @param Index
    * @param FileName
    * @return void
    **/
    public void SetIcon(short ID,short Index,String FileName) throws COMException
    {
      
		invokeN("SetIcon", new Object[] {new Short(ID), new Short(Index), FileName});
        
    }
    /**
    *
    * @return double
    **/
    public double getLeftMargin(Variant UnitsNameOrCode) throws COMException
    {
         return ((Double)get("LeftMargin", UnitsNameOrCode)).doubleValue();
    }
        
    /**
    *
    * @param LeftMargin
    **/
    /*
    public void setLeftMargin(Variant UnitsNameOrCode,double newValue2) throws COMException
    {
        put("LeftMargin", LeftMargin, newValue2);
    }
    */
        
    /**
    *
    * @return double
    **/
    public double getRightMargin(Variant UnitsNameOrCode) throws COMException
    {
         return ((Double)get("RightMargin", UnitsNameOrCode)).doubleValue();
    }
        
    /**
    *
    * @param RightMargin
    **/
    /*
    public void setRightMargin(Variant UnitsNameOrCode,double newValue2) throws COMException
    {
        put("RightMargin", RightMargin, newValue2);
    }
    */
        
    /**
    *
    * @return double
    **/
    public double getTopMargin(Variant UnitsNameOrCode) throws COMException
    {
         return ((Double)get("TopMargin", UnitsNameOrCode)).doubleValue();
    }
        
    /**
    *
    * @param TopMargin
    **/
    /*
    public void setTopMargin(Variant UnitsNameOrCode,double newValue2) throws COMException
    {
        put("TopMargin", TopMargin, newValue2);
    }
    */
        
    /**
    *
    * @return double
    **/
    public double getBottomMargin(Variant UnitsNameOrCode) throws COMException
    {
         return ((Double)get("BottomMargin", UnitsNameOrCode)).doubleValue();
    }
        
    /**
    *
    * @param BottomMargin
    **/
    /*
    public void setBottomMargin(Variant UnitsNameOrCode,double newValue2) throws COMException
    {
        put("BottomMargin", BottomMargin, newValue2);
    }
    */
    
    /**
    *
    * @return short
    **/
    public short getold_PrintLandscape() throws COMException
    {
        return ((Short)get("old_PrintLandscape")).shortValue();
    }
        
    /**
    *
    * @param old_PrintLandscape
    **/
    public void setold_PrintLandscape(short old_PrintLandscape) throws COMException
    {
        put("old_PrintLandscape", old_PrintLandscape);
    }
        
    /**
    *
    * @return short
    **/
    public short getold_PrintCenteredH() throws COMException
    {
        return ((Short)get("old_PrintCenteredH")).shortValue();
    }
        
    /**
    *
    * @param old_PrintCenteredH
    **/
    public void setold_PrintCenteredH(short old_PrintCenteredH) throws COMException
    {
        put("old_PrintCenteredH", old_PrintCenteredH);
    }
        
    /**
    *
    * @return short
    **/
    public short getold_PrintCenteredV() throws COMException
    {
        return ((Short)get("old_PrintCenteredV")).shortValue();
    }
        
    /**
    *
    * @param old_PrintCenteredV
    **/
    public void setold_PrintCenteredV(short old_PrintCenteredV) throws COMException
    {
        put("old_PrintCenteredV", old_PrintCenteredV);
    }
        
    /**
    *
    * @return double
    **/
    public double getPrintScale() throws COMException
    {
         return ((Double)get("PrintScale")).doubleValue();
    }
        
    /**
    *
    * @param PrintScale
    **/
    public void setPrintScale(double PrintScale) throws COMException
    {
        put("PrintScale", PrintScale);
    }
        
    /**
    *
    * @return short
    **/
    public short getold_PrintFitOnPages() throws COMException
    {
        return ((Short)get("old_PrintFitOnPages")).shortValue();
    }
        
    /**
    *
    * @param old_PrintFitOnPages
    **/
    public void setold_PrintFitOnPages(short old_PrintFitOnPages) throws COMException
    {
        put("old_PrintFitOnPages", old_PrintFitOnPages);
    }
        
    /**
    *
    * @return short
    **/
    public short getPrintPagesAcross() throws COMException
    {
        return ((Short)get("PrintPagesAcross")).shortValue();
    }
        
    /**
    *
    * @param PrintPagesAcross
    **/
    public void setPrintPagesAcross(short PrintPagesAcross) throws COMException
    {
        put("PrintPagesAcross", PrintPagesAcross);
    }
        
    /**
    *
    * @return short
    **/
    public short getPrintPagesDown() throws COMException
    {
        return ((Short)get("PrintPagesDown")).shortValue();
    }
        
    /**
    *
    * @param PrintPagesDown
    **/
    public void setPrintPagesDown(short PrintPagesDown) throws COMException
    {
        put("PrintPagesDown", PrintPagesDown);
    }
        
    /**
    *
    * @return String
    **/
    public String getDefaultStyle() throws COMException
    {
         return (String)get("DefaultStyle");
    }
        
    /**
    *
    * @param DefaultStyle
    **/
    public void setDefaultStyle(String DefaultStyle) throws COMException
    {
        put("DefaultStyle", DefaultStyle);
    }
        
    /**
    *
    * @return String
    **/
    public String getDefaultLineStyle() throws COMException
    {
         return (String)get("DefaultLineStyle");
    }
        
    /**
    *
    * @param DefaultLineStyle
    **/
    public void setDefaultLineStyle(String DefaultLineStyle) throws COMException
    {
        put("DefaultLineStyle", DefaultLineStyle);
    }
        
    /**
    *
    * @return String
    **/
    public String getDefaultFillStyle() throws COMException
    {
         return (String)get("DefaultFillStyle");
    }
        
    /**
    *
    * @param DefaultFillStyle
    **/
    public void setDefaultFillStyle(String DefaultFillStyle) throws COMException
    {
        put("DefaultFillStyle", DefaultFillStyle);
    }
        
    /**
    *
    * @return String
    **/
    public String getDefaultTextStyle() throws COMException
    {
         return (String)get("DefaultTextStyle");
    }
        
    /**
    *
    * @param DefaultTextStyle
    **/
    public void setDefaultTextStyle(String DefaultTextStyle) throws COMException
    {
        put("DefaultTextStyle", DefaultTextStyle);
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
    
    * @return void
    **/
    public IVWindow OpenStencilWindow() throws COMException
    {
      IVWindow res = new IVWindow();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("OpenStencilWindow", new Object[] {});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    
    * @param Line
    * @return void
    **/
    public void ParseLine(String Line) throws COMException
    {
      
		invokeN("ParseLine", new Object[] {Line});
        
    }
    /**
    *
    
    * @param Line
    * @return void
    **/
    public void ExecuteLine(String Line) throws COMException
    {
      
		invokeN("ExecuteLine", new Object[] {Line});
        
    }
    /**
    *
    * @return DispatchPtr
    **/
    public DispatchPtr getVBProject() throws COMException
    {
         return (DispatchPtr)get("VBProject");
    }
        
    /**
    *
    * @return double
    **/
    public double getPaperWidth(Variant UnitsNameOrCode) throws COMException
    {
         return ((Double)get("PaperWidth", UnitsNameOrCode)).doubleValue();
    }
        
    /**
    *
    * @return double
    **/
    public double getPaperHeight(Variant UnitsNameOrCode) throws COMException
    {
         return ((Double)get("PaperHeight", UnitsNameOrCode)).doubleValue();
    }
        
    /**
    *
    * @return short
    **/
    public short getold_PaperSize() throws COMException
    {
        return ((Short)get("old_PaperSize")).shortValue();
    }
        
    /**
    *
    * @param old_PaperSize
    **/
    public void setold_PaperSize(short old_PaperSize) throws COMException
    {
        put("old_PaperSize", old_PaperSize);
    }
        
    /**
    *
    
    * @param Target
    * @param Location
    * @return void
    **/
    public void FollowHyperlink45(String Target,String Location) throws COMException
    {
      
		invokeN("FollowHyperlink45", new Object[] {Target, Location});
        
    }
    /**
    *
    * @return String
    **/
    public String getCodeName() throws COMException
    {
         return (String)get("CodeName");
    }
        
    /**
    *
    * @return short
    **/
    public short getold_Mode() throws COMException
    {
        return ((Short)get("old_Mode")).shortValue();
    }
        
    /**
    *
    * @param old_Mode
    **/
    public void setold_Mode(short old_Mode) throws COMException
    {
        put("old_Mode", old_Mode);
    }
        
    /**
    *
    * @return void
    **/
    public IVOLEObjects getOLEObjects() throws COMException
    {
        IVOLEObjects res = new IVOLEObjects();
          DispatchPtr dispPtr = (DispatchPtr)get("OLEObjects");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    
    * @param Address
    * @param SubAddress
    * @param ExtraInfo
    * @param Frame
    * @param NewWindow
    * @param res1
    * @param res2
    * @param res3
    * @return void
    **/
    public void FollowHyperlink(String Address,String SubAddress,Variant ExtraInfo,Variant Frame,Variant NewWindow,Variant res1,Variant res2,Variant res3) throws COMException
    {
      
		invokeN("FollowHyperlink", new Object[] {Address, SubAddress, ExtraInfo, Frame, NewWindow, res1, res2, res3});
        
    }
    /**
    *
    * @return String
    **/
    public String getManager() throws COMException
    {
         return (String)get("Manager");
    }
        
    /**
    *
    * @param Manager
    **/
    public void setManager(String Manager) throws COMException
    {
        put("Manager", Manager);
    }
        
    /**
    *
    * @return String
    **/
    public String getCompany() throws COMException
    {
         return (String)get("Company");
    }
        
    /**
    *
    * @param Company
    **/
    public void setCompany(String Company) throws COMException
    {
        put("Company", Company);
    }
        
    /**
    *
    * @return String
    **/
    public String getCategory() throws COMException
    {
         return (String)get("Category");
    }
        
    /**
    *
    * @param Category
    **/
    public void setCategory(String Category) throws COMException
    {
        put("Category", Category);
    }
        
    /**
    *
    * @return String
    **/
    public String getHyperlinkBase() throws COMException
    {
         return (String)get("HyperlinkBase");
    }
        
    /**
    *
    * @param HyperlinkBase
    **/
    public void setHyperlinkBase(String HyperlinkBase) throws COMException
    {
        put("HyperlinkBase", HyperlinkBase);
    }
        
    /**
    *
    * @return void
    **/
    public IVShape getDocumentSheet() throws COMException
    {
        IVShape res = new IVShape();
          DispatchPtr dispPtr = (DispatchPtr)get("DocumentSheet");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return DispatchPtr
    **/
    public DispatchPtr getContainer() throws COMException
    {
         return (DispatchPtr)get("Container");
    }
        
    /**
    *
    * @return String
    **/
    public String getClassID() throws COMException
    {
         return (String)get("ClassID");
    }
        
    /**
    *
    * @return String
    **/
    public String getProgID() throws COMException
    {
         return (String)get("ProgID");
    }
        
    /**
    *
    * @return void
    **/
    public IVMasterShortcuts getMasterShortcuts() throws COMException
    {
        IVMasterShortcuts res = new IVMasterShortcuts();
          DispatchPtr dispPtr = (DispatchPtr)get("MasterShortcuts");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return String
    **/
    public String getAlternateNames() throws COMException
    {
         return (String)get("AlternateNames");
    }
        
    /**
    *
    * @param AlternateNames
    **/
    public void setAlternateNames(String AlternateNames) throws COMException
    {
        put("AlternateNames", AlternateNames);
    }
        
    /**
    *
    * @return void
    **/
    public IVShape getGestureFormatSheet() throws COMException
    {
        IVShape res = new IVShape();
          DispatchPtr dispPtr = (DispatchPtr)get("GestureFormatSheet");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    
    * @return void
    **/
    public void ClearGestureFormatSheet() throws COMException
    {
      
		invokeN("ClearGestureFormatSheet", new Object[] {});
        
    }
    /**
    *
    * @return boolean
    **/
    public boolean getAutoRecover() throws COMException
    {
        return ((Boolean)get("AutoRecover")).booleanValue();
    }
        
    /**
    *
    * @param AutoRecover
    **/
    public void setAutoRecover(boolean AutoRecover) throws COMException
    {
        put("AutoRecover", AutoRecover);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getSaved() throws COMException
    {
        return ((Boolean)get("Saved")).booleanValue();
    }
        
    /**
    *
    * @param Saved
    **/
    public void setSaved(boolean Saved) throws COMException
    {
        put("Saved", Saved);
    }
        
    /**
    *
    * @return int
    **/
    public int getVersion() throws COMException
    {
        return ((Integer)get("Version")).intValue();
    }
        
    /**
    *
    * @param Version
    **/
    public void setVersion(int Version) throws COMException
    {
        put("Version", Version);
    }
        
    /**
    *
    * @return int
    **/
    public int getSavePreviewMode() throws COMException
    {
        return ((Integer)get("SavePreviewMode")).intValue();
    }
        
    /**
    *
    * @param SavePreviewMode
    **/
    public void setSavePreviewMode(int SavePreviewMode) throws COMException
    {
        put("SavePreviewMode", SavePreviewMode);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getPrintLandscape() throws COMException
    {
        return ((Boolean)get("PrintLandscape")).booleanValue();
    }
        
    /**
    *
    * @param PrintLandscape
    **/
    public void setPrintLandscape(boolean PrintLandscape) throws COMException
    {
        put("PrintLandscape", PrintLandscape);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getPrintCenteredH() throws COMException
    {
        return ((Boolean)get("PrintCenteredH")).booleanValue();
    }
        
    /**
    *
    * @param PrintCenteredH
    **/
    public void setPrintCenteredH(boolean PrintCenteredH) throws COMException
    {
        put("PrintCenteredH", PrintCenteredH);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getPrintCenteredV() throws COMException
    {
        return ((Boolean)get("PrintCenteredV")).booleanValue();
    }
        
    /**
    *
    * @param PrintCenteredV
    **/
    public void setPrintCenteredV(boolean PrintCenteredV) throws COMException
    {
        put("PrintCenteredV", PrintCenteredV);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getPrintFitOnPages() throws COMException
    {
        return ((Boolean)get("PrintFitOnPages")).booleanValue();
    }
        
    /**
    *
    * @param PrintFitOnPages
    **/
    public void setPrintFitOnPages(boolean PrintFitOnPages) throws COMException
    {
        put("PrintFitOnPages", PrintFitOnPages);
    }
        
    /**
    *
    * @return int
    **/
    public int getPaperSize() throws COMException
    {
        return ((Integer)get("PaperSize")).intValue();
    }
        
    /**
    *
    * @param PaperSize
    **/
    public void setPaperSize(int PaperSize) throws COMException
    {
        put("PaperSize", PaperSize);
    }
        
    /**
    *
    * @return int
    **/
    public int getMode() throws COMException
    {
        return ((Integer)get("Mode")).intValue();
    }
        
    /**
    *
    * @param Mode
    **/
    public void setMode(int Mode) throws COMException
    {
        put("Mode", Mode);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getSnapEnabled() throws COMException
    {
        return ((Boolean)get("SnapEnabled")).booleanValue();
    }
        
    /**
    *
    * @param SnapEnabled
    **/
    public void setSnapEnabled(boolean SnapEnabled) throws COMException
    {
        put("SnapEnabled", SnapEnabled);
    }
        
    /**
    *
    * @return int
    **/
    public int getSnapSettings() throws COMException
    {
        return ((Integer)get("SnapSettings")).intValue();
    }
        
    /**
    *
    * @param SnapSettings
    **/
    public void setSnapSettings(int SnapSettings) throws COMException
    {
        put("SnapSettings", SnapSettings);
    }
        
    /**
    *
    * @return int
    **/
    public int getSnapExtensions() throws COMException
    {
        return ((Integer)get("SnapExtensions")).intValue();
    }
        
    /**
    *
    * @param SnapExtensions
    **/
    public void setSnapExtensions(int SnapExtensions) throws COMException
    {
        put("SnapExtensions", SnapExtensions);
    }
        
    /**
    *
    * @return Object[]
    **/
    public Object[] getSnapAngles() throws COMException
    {
        return null; // not implementedget("SnapAngles");
    }
        
    /**
    *
    * @param SnapAngles
    **/
    public void setSnapAngles(Object[] SnapAngles) throws COMException
    {
        put("SnapAngles", SnapAngles);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getGlueEnabled() throws COMException
    {
        return ((Boolean)get("GlueEnabled")).booleanValue();
    }
        
    /**
    *
    * @param GlueEnabled
    **/
    public void setGlueEnabled(boolean GlueEnabled) throws COMException
    {
        put("GlueEnabled", GlueEnabled);
    }
        
    /**
    *
    * @return int
    **/
    public int getGlueSettings() throws COMException
    {
        return ((Integer)get("GlueSettings")).intValue();
    }
        
    /**
    *
    * @param GlueSettings
    **/
    public void setGlueSettings(int GlueSettings) throws COMException
    {
        put("GlueSettings", GlueSettings);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getDynamicGridEnabled() throws COMException
    {
        return ((Boolean)get("DynamicGridEnabled")).booleanValue();
    }
        
    /**
    *
    * @param DynamicGridEnabled
    **/
    public void setDynamicGridEnabled(boolean DynamicGridEnabled) throws COMException
    {
        put("DynamicGridEnabled", DynamicGridEnabled);
    }
        
    /**
    *
    * @return String
    **/
    public String getDefaultGuideStyle() throws COMException
    {
         return (String)get("DefaultGuideStyle");
    }
        
    /**
    *
    * @param DefaultGuideStyle
    **/
    public void setDefaultGuideStyle(String DefaultGuideStyle) throws COMException
    {
        put("DefaultGuideStyle", DefaultGuideStyle);
    }
        
    /**
    *
    * @return int
    **/
    public int getProtection(Variant bstrPassword) throws COMException
    {
        return ((Integer)get("Protection", bstrPassword)).intValue();
    }
        
    /**
    *
    * @param Protection
    **/
    /*
    public void setProtection(Variant bstrPassword,int newValue2) throws COMException
    {
        put("Protection", Protection, newValue2);
    }
    */
        
    /**
    *
    * @return String
    **/
    public String getPrinter() throws COMException
    {
         return (String)get("Printer");
    }
        
    /**
    *
    * @param Printer
    **/
    public void setPrinter(String Printer) throws COMException
    {
        put("Printer", Printer);
    }
        
    /**
    *
    * @return int
    **/
    public int getPrintCopies() throws COMException
    {
        return ((Integer)get("PrintCopies")).intValue();
    }
        
    /**
    *
    * @param PrintCopies
    **/
    public void setPrintCopies(int PrintCopies) throws COMException
    {
        put("PrintCopies", PrintCopies);
    }
        
    /**
    *
    * @return String
    **/
    public String getHeaderLeft() throws COMException
    {
         return (String)get("HeaderLeft");
    }
        
    /**
    *
    * @param HeaderLeft
    **/
    public void setHeaderLeft(String HeaderLeft) throws COMException
    {
        put("HeaderLeft", HeaderLeft);
    }
        
    /**
    *
    * @return String
    **/
    public String getHeaderCenter() throws COMException
    {
         return (String)get("HeaderCenter");
    }
        
    /**
    *
    * @param HeaderCenter
    **/
    public void setHeaderCenter(String HeaderCenter) throws COMException
    {
        put("HeaderCenter", HeaderCenter);
    }
        
    /**
    *
    * @return String
    **/
    public String getHeaderRight() throws COMException
    {
         return (String)get("HeaderRight");
    }
        
    /**
    *
    * @param HeaderRight
    **/
    public void setHeaderRight(String HeaderRight) throws COMException
    {
        put("HeaderRight", HeaderRight);
    }
        
    /**
    *
    * @return double
    **/
    public double getHeaderMargin(Variant UnitsNameOrCode) throws COMException
    {
         return ((Double)get("HeaderMargin", UnitsNameOrCode)).doubleValue();
    }
        
    /**
    *
    * @param HeaderMargin
    **/
    /*
    public void setHeaderMargin(Variant UnitsNameOrCode,double newValue2) throws COMException
    {
        put("HeaderMargin", HeaderMargin, newValue2);
    }
    */
        
    /**
    *
    * @return String
    **/
    public String getFooterLeft() throws COMException
    {
         return (String)get("FooterLeft");
    }
        
    /**
    *
    * @param FooterLeft
    **/
    public void setFooterLeft(String FooterLeft) throws COMException
    {
        put("FooterLeft", FooterLeft);
    }
        
    /**
    *
    * @return String
    **/
    public String getFooterCenter() throws COMException
    {
         return (String)get("FooterCenter");
    }
        
    /**
    *
    * @param FooterCenter
    **/
    public void setFooterCenter(String FooterCenter) throws COMException
    {
        put("FooterCenter", FooterCenter);
    }
        
    /**
    *
    * @return String
    **/
    public String getFooterRight() throws COMException
    {
         return (String)get("FooterRight");
    }
        
    /**
    *
    * @param FooterRight
    **/
    public void setFooterRight(String FooterRight) throws COMException
    {
        put("FooterRight", FooterRight);
    }
        
    /**
    *
    * @return double
    **/
    public double getFooterMargin(Variant UnitsNameOrCode) throws COMException
    {
         return ((Double)get("FooterMargin", UnitsNameOrCode)).doubleValue();
    }
        
    /**
    *
    * @param FooterMargin
    **/
    /*
    public void setFooterMargin(Variant UnitsNameOrCode,double newValue2) throws COMException
    {
        put("FooterMargin", FooterMargin, newValue2);
    }
    */
        
    /**
    *
    * @return void
    **/
    public void getHeaderFooterFont() throws COMException
    {
        get("HeaderFooterFont");
    }
        
          //for  DISPATCH_PROPERTYPUTREF not implemented
        
    /**
    *
    * @return int
    **/
    public int getHeaderFooterColor() throws COMException
    {
        return ((Integer)get("HeaderFooterColor")).intValue();
    }
        
    /**
    *
    * @param HeaderFooterColor
    **/
    public void setHeaderFooterColor(int HeaderFooterColor) throws COMException
    {
        put("HeaderFooterColor", HeaderFooterColor);
    }
        
    /**
    *
    * @param Password
    **/
    /*
    public void setPassword(Variant bstrExistingPassword,String newValue2) throws COMException
    {
        put("Password", Password, newValue2);
    }
    */
        
    /**
    *
    * @return void
    **/
    public void getPreviewPicture() throws COMException
    {
        get("PreviewPicture");
    }
        
          //for  DISPATCH_PROPERTYPUTREF not implemented
        
    /**
    *
    
    * @param nTargets
    * @param nActions
    * @param nAlerts
    * @param nFixes
    * @param bStopOnError
    * @param bLogFileName
    * @param nReserved
    * @return void
    **/
    public void Clean(Variant nTargets,Variant nActions,Variant nAlerts,Variant nFixes,Variant bStopOnError,Variant bLogFileName,Variant nReserved) throws COMException
    {
      
		invokeN("Clean", new Object[] {nTargets, nActions, nAlerts, nFixes, bStopOnError, bLogFileName, nReserved});
        
    }
    /**
    *
    * @return int
    **/
    public int getBuildNumberCreated() throws COMException
    {
        return ((Integer)get("BuildNumberCreated")).intValue();
    }
        
    /**
    *
    * @return int
    **/
    public int getBuildNumberEdited() throws COMException
    {
        return ((Integer)get("BuildNumberEdited")).intValue();
    }
        
    /**
    *
    * @return Date
    **/
    public Date getTimeCreated() throws COMException
    {
         return (Date)get("TimeCreated");
    }
        
    /**
    *
    * @return Date
    **/
    public Date getTime() throws COMException
    {
         return (Date)get("Time");
    }
        
    /**
    *
    * @return Date
    **/
    public Date getTimeEdited() throws COMException
    {
         return (Date)get("TimeEdited");
    }
        
    /**
    *
    * @return Date
    **/
    public Date getTimePrinted() throws COMException
    {
         return (Date)get("TimePrinted");
    }
        
    /**
    *
    * @return Date
    **/
    public Date getTimeSaved() throws COMException
    {
         return (Date)get("TimeSaved");
    }
        
    /**
    *
    
    * @return void
    **/
    public void CopyPreviewPicture(Object[] pSourceDoc) throws COMException
    {
      
		invokeN("CopyPreviewPicture", new Object[] {pSourceDoc});
        
    }
    /**
    *
    * @return boolean
    **/
    public boolean getContainsWorkspace() throws COMException
    {
        return ((Boolean)get("ContainsWorkspace")).booleanValue();
    }
        
    /**
    *
    * @return Object[]
    **/
    public Object[] getEmailRoutingData() throws COMException
    {
        return null; // not implementedget("EmailRoutingData");
    }
        
    /**
    *
    * @return Object[]
    **/
    public Object[] getVBProjectData() throws COMException
    {
        return null; // not implementedget("VBProjectData");
    }
        
    /**
    *
    * @return int
    **/
    public int getSolutionXMLElementCount() throws COMException
    {
        return ((Integer)get("SolutionXMLElementCount")).intValue();
    }
        
    /**
    *
    * @return String
    **/
    public String getSolutionXMLElementName(int Index) throws COMException
    {
         return (String)get("SolutionXMLElementName", new Integer(Index));
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getSolutionXMLElementExists(String ElementName) throws COMException
    {
        return ((Boolean)get("SolutionXMLElementExists", ElementName)).booleanValue();
    }
        
    /**
    *
    * @return String
    **/
    public String getSolutionXMLElement(String ElementName) throws COMException
    {
         return (String)get("SolutionXMLElement", ElementName);
    }
        
    /**
    *
    * @param SolutionXMLElement
    **/
    public void setSolutionXMLElement(String ElementName,String newValue2) throws COMException
    {
        put("SolutionXMLElement", ElementName, newValue2);
    }
        
    /**
    *
    
    * @param ElementName
    * @return void
    **/
    public void DeleteSolutionXMLElement(String ElementName) throws COMException
    {
      
		invokeN("DeleteSolutionXMLElement", new Object[] {ElementName});
        
    }
    /**
    *
    * @return int
    **/
    public int getFullBuildNumberCreated() throws COMException
    {
        return ((Integer)get("FullBuildNumberCreated")).intValue();
    }
        
    /**
    *
    * @return int
    **/
    public int getFullBuildNumberEdited() throws COMException
    {
        return ((Integer)get("FullBuildNumberEdited")).intValue();
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
    * @return boolean
    **/
    public boolean getMacrosEnabled() throws COMException
    {
        return ((Boolean)get("MacrosEnabled")).booleanValue();
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
    
    * @return boolean
    **/
    public boolean CanCheckIn() throws COMException
    {
      
		return ((Boolean)invokeN("CanCheckIn", new Object[] {})).booleanValue();
        
    }
    /**
    *
    
    * @param SaveChanges
    * @param MakePublic
    * @return void
    **/
    public void CheckIn(boolean SaveChanges,Variant[] Comments,boolean MakePublic) throws COMException
    {
      
		invokeN("CheckIn", new Object[] {new Boolean(SaveChanges), Comments, new Boolean(MakePublic)});
        
    }
    /**
    *
    * @return int
    **/
    public int getType() throws COMException
    {
        return ((Integer)get("Type")).intValue();
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
    * @param Language
    **/
    public void setLanguage(int Language) throws COMException
    {
        put("Language", Language);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getRemovePersonalInformation() throws COMException
    {
        return ((Boolean)get("RemovePersonalInformation")).booleanValue();
    }
        
    /**
    *
    * @param RemovePersonalInformation
    **/
    public void setRemovePersonalInformation(boolean RemovePersonalInformation) throws COMException
    {
        put("RemovePersonalInformation", RemovePersonalInformation);
    }
        
    /**
    *
    
    * @param PrintRange
    * @param FromPage
    * @param ToPage
    * @param ScaleCurrentViewToPaper
    * @param PrinterName
    * @param PrintToFile
    * @param OutputFileName
    * @param Copies
    * @param Collate
    * @param ColorAsBlack
    * @return void
    **/
    public void PrintOut(int PrintRange,int FromPage,int ToPage,boolean ScaleCurrentViewToPaper,String PrinterName,boolean PrintToFile,String OutputFileName,int Copies,boolean Collate,boolean ColorAsBlack) throws COMException
    {
      
		invokeN("PrintOut", new Object[] {new Integer(PrintRange), new Integer(FromPage), new Integer(ToPage), new Boolean(ScaleCurrentViewToPaper), PrinterName, new Boolean(PrintToFile), OutputFileName, new Integer(Copies), new Boolean(Collate), new Boolean(ColorAsBlack)});
        
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
    
    * @return void
    **/
    public void PurgeUndo() throws COMException
    {
      
		invokeN("PurgeUndo", new Object[] {});
        
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
    
    * @param bstrScopeName
    * @return void
    **/
    public void RenameCurrentScope(String bstrScopeName) throws COMException
    {
      
		invokeN("RenameCurrentScope", new Object[] {bstrScopeName});
        
    }
    /**
    *
    * @return DispatchPtr
    **/
    public DispatchPtr getSharedWorkspace() throws COMException
    {
         return (DispatchPtr)get("SharedWorkspace");
    }
        
    /**
    *
    * @return DispatchPtr
    **/
    public DispatchPtr getSync() throws COMException
    {
         return (DispatchPtr)get("Sync");
    }
        
    /**
    *
    
    * @param RemoveHiddenInfoItems
    * @return void
    **/
    public void RemoveHiddenInformation(int RemoveHiddenInfoItems) throws COMException
    {
      
		invokeN("RemoveHiddenInformation", new Object[] {new Integer(RemoveHiddenInfoItems)});
        
    }
    /**
    *
    * @return void
    **/
    public IVDataRecordsets getDataRecordsets() throws COMException
    {
        IVDataRecordsets res = new IVDataRecordsets();
          DispatchPtr dispPtr = (DispatchPtr)get("DataRecordsets");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    
    * @param eType
    * @param NameArray
    * @return void
    **/
    public void GetThemeNames(int eType,Object[] NameArray) throws COMException
    {
      
		invokeN("GetThemeNames", new Object[] {new Integer(eType), NameArray});
        
    }
    /**
    *
    
    * @param eType
    * @param NameArray
    * @return void
    **/
    public void GetThemeNamesU(int eType,Object[] NameArray) throws COMException
    {
      
		invokeN("GetThemeNamesU", new Object[] {new Integer(eType), NameArray});
        
    }
    /**
    *
    
    * @return boolean
    **/
    public boolean CanUndoCheckOut() throws COMException
    {
      
		return ((Boolean)invokeN("CanUndoCheckOut", new Object[] {})).booleanValue();
        
    }
    /**
    *
    
    * @return void
    **/
    public void UndoCheckOut() throws COMException
    {
      
		invokeN("UndoCheckOut", new Object[] {});
        
    }
    /**
    *
    * @return boolean
    **/
    public boolean getContainsWorkspaceEx() throws COMException
    {
        return ((Boolean)get("ContainsWorkspaceEx")).booleanValue();
    }
        
    /**
    *
    * @param ContainsWorkspaceEx
    **/
    public void setContainsWorkspaceEx(boolean ContainsWorkspaceEx) throws COMException
    {
        put("ContainsWorkspaceEx", ContainsWorkspaceEx);
    }
        
    /**
    *
    
    * @param FixedFormat
    * @param OutputFileName
    * @param Intent
    * @param PrintRange
    * @param FromPage
    * @param ToPage
    * @param ColorAsBlack
    * @param IncludeBackground
    * @param IncludeDocumentProperties
    * @param IncludeStructureTags
    * @param UseISO19005_1
    * @param FixedFormatExtClass
    * @return void
    **/
    public void ExportAsFixedFormat(int FixedFormat,String OutputFileName,int Intent,int PrintRange,int FromPage,int ToPage,boolean ColorAsBlack,boolean IncludeBackground,boolean IncludeDocumentProperties,boolean IncludeStructureTags,boolean UseISO19005_1,Variant FixedFormatExtClass) throws COMException
    {
      
		invokeN("ExportAsFixedFormat", new Object[] {new Integer(FixedFormat), OutputFileName, new Integer(Intent), new Integer(PrintRange), new Integer(FromPage), new Integer(ToPage), new Boolean(ColorAsBlack), new Boolean(IncludeBackground), new Boolean(IncludeDocumentProperties), new Boolean(IncludeStructureTags), new Boolean(UseISO19005_1), FixedFormatExtClass});
        
    }
    /**
    *
    * @return String
    **/
    public String getDefaultSavePath() throws COMException
    {
         return (String)get("DefaultSavePath");
    }
        
    /**
    *
    * @param DefaultSavePath
    **/
    public void setDefaultSavePath(String DefaultSavePath) throws COMException
    {
        put("DefaultSavePath", DefaultSavePath);
    }
        
}


package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVMaster
 * Description: 
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVMaster extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0707-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVMaster.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVMaster (it is required by Jawin for some internal working though).
	 */
	public IVMaster() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVMaster interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVMaster(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVMaster interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVMaster(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVMaster interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVMaster interface on.
	 */
	public IVMaster(COMPtr comObject) throws COMException {
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
    public IVDocument getDocument() throws COMException
    {
        IVDocument res = new IVDocument();
          DispatchPtr dispPtr = (DispatchPtr)get("Document");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return String
    **/
    public String getPrompt() throws COMException
    {
         return (String)get("Prompt");
    }
        
    /**
    *
    * @param Prompt
    **/
    public void setPrompt(String Prompt) throws COMException
    {
        put("Prompt", Prompt);
    }
        
    /**
    *
    * @return short
    **/
    public short getAlignName() throws COMException
    {
        return ((Short)get("AlignName")).shortValue();
    }
        
    /**
    *
    * @param AlignName
    **/
    public void setAlignName(short AlignName) throws COMException
    {
        put("AlignName", AlignName);
    }
        
    /**
    *
    * @return short
    **/
    public short getIconSize() throws COMException
    {
        return ((Short)get("IconSize")).shortValue();
    }
        
    /**
    *
    * @param IconSize
    **/
    public void setIconSize(short IconSize) throws COMException
    {
        put("IconSize", IconSize);
    }
        
    /**
    *
    * @return short
    **/
    public short getIconUpdate() throws COMException
    {
        return ((Short)get("IconUpdate")).shortValue();
    }
        
    /**
    *
    * @param IconUpdate
    **/
    public void setIconUpdate(short IconUpdate) throws COMException
    {
        put("IconUpdate", IconUpdate);
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
    * @return String
    **/
    public String getName() throws COMException
    {
         return (String)get("Name");
    }
        
    /**
    *
    * @param Name
    **/
    public void setName(String Name) throws COMException
    {
        put("Name", Name);
    }
        
    /**
    *
    * @return void
    **/
    public IVShapes getShapes() throws COMException
    {
        IVShapes res = new IVShapes();
          DispatchPtr dispPtr = (DispatchPtr)get("Shapes");
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
    public short getOneD() throws COMException
    {
        return ((Short)get("OneD")).shortValue();
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
    * @return String
    **/
    public String getUniqueID() throws COMException
    {
         return (String)get("UniqueID");
    }
        
    /**
    *
    * @return void
    **/
    public IVLayers getLayers() throws COMException
    {
        IVLayers res = new IVLayers();
          DispatchPtr dispPtr = (DispatchPtr)get("Layers");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return void
    **/
    public IVShape getPageSheet() throws COMException
    {
        IVShape res = new IVShape();
          DispatchPtr dispPtr = (DispatchPtr)get("PageSheet");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    
    * @param ObjectToDrop
    * @param xPos
    * @param yPos
    * @return void
    **/
    public IVShape Drop(IUnknown ObjectToDrop,double xPos,double yPos) throws COMException
    {
      IVShape res = new IVShape();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("Drop", new Object[] {ObjectToDrop, new Double(xPos), new Double(yPos)});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    
    * @return void
    **/
    public void CenterDrawing() throws COMException
    {
      
		invokeN("CenterDrawing", new Object[] {});
        
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
    
    * @param xBegin
    * @param yBegin
    * @param xEnd
    * @param yEnd
    * @return void
    **/
    public IVShape DrawLine(double xBegin,double yBegin,double xEnd,double yEnd) throws COMException
    {
      IVShape res = new IVShape();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("DrawLine", new Object[] {new Double(xBegin), new Double(yBegin), new Double(xEnd), new Double(yEnd)});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    
    * @param x1
    * @param y1
    * @param x2
    * @param y2
    * @return void
    **/
    public IVShape DrawRectangle(double x1,double y1,double x2,double y2) throws COMException
    {
      IVShape res = new IVShape();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("DrawRectangle", new Object[] {new Double(x1), new Double(y1), new Double(x2), new Double(y2)});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    
    * @param x1
    * @param y1
    * @param x2
    * @param y2
    * @return void
    **/
    public IVShape DrawOval(double x1,double y1,double x2,double y2) throws COMException
    {
      IVShape res = new IVShape();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("DrawOval", new Object[] {new Double(x1), new Double(y1), new Double(x2), new Double(y2)});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    
    * @param xyArray
    * @param Tolerance
    * @param Flags
    * @return void
    **/
    public IVShape DrawSpline(Object[] xyArray,double Tolerance,short Flags) throws COMException
    {
      IVShape res = new IVShape();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("DrawSpline", new Object[] {xyArray, new Double(Tolerance), new Short(Flags)});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    
    * @param xyArray
    * @param degree
    * @param Flags
    * @return void
    **/
    public IVShape DrawBezier(Object[] xyArray,short degree,short Flags) throws COMException
    {
      IVShape res = new IVShape();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("DrawBezier", new Object[] {xyArray, new Short(degree), new Short(Flags)});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    
    * @param xyArray
    * @param Flags
    * @return void
    **/
    public IVShape DrawPolyline(Object[] xyArray,short Flags) throws COMException
    {
      IVShape res = new IVShape();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("DrawPolyline", new Object[] {xyArray, new Short(Flags)});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    
    * @param FileName
    * @return void
    **/
    public IVShape Import(String FileName) throws COMException
    {
      IVShape res = new IVShape();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("Import", new Object[] {FileName});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    
    * @param FileName
    * @return void
    **/
    public void Export(String FileName) throws COMException
    {
      
		invokeN("Export", new Object[] {FileName});
        
    }
    /**
    *
    
    * @param FileName
    * @param Flags
    * @return void
    **/
    public IVShape InsertFromFile(String FileName,short Flags) throws COMException
    {
      IVShape res = new IVShape();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("InsertFromFile", new Object[] {FileName, new Short(Flags)});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    
    * @param ClassOrProgID
    * @param Flags
    * @return void
    **/
    public IVShape InsertObject(String ClassOrProgID,short Flags) throws COMException
    {
      IVShape res = new IVShape();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("InsertObject", new Object[] {ClassOrProgID, new Short(Flags)});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    
    * @return void
    **/
    public IVWindow OpenDrawWindow() throws COMException
    {
      IVWindow res = new IVWindow();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("OpenDrawWindow", new Object[] {});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    
    * @return void
    **/
    public IVWindow OpenIconWindow() throws COMException
    {
      IVWindow res = new IVWindow();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("OpenIconWindow", new Object[] {});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    
    * @return void
    **/
    public IVMaster Open() throws COMException
    {
      IVMaster res = new IVMaster();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("Open", new Object[] {});
          res.stealUnknown(dispPtr);
          return res;
        
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
    
    * @param ObjectsToInstance
    * @param xyArray
    * @param IDArray
    * @return short
    **/
    public short DropMany(Object[] ObjectsToInstance,Object[] xyArray,Object[] IDArray) throws COMException
    {
      
		return ((Short)invokeN("DropMany", new Object[] {ObjectsToInstance, xyArray, IDArray})).shortValue();
        
    }
    /**
    *
    
    * @param SID_SRCStream
    * @param formulaArray
    * @return void
    **/
    public void GetFormulas(Object[] SID_SRCStream,Object[] formulaArray) throws COMException
    {
      
		invokeN("GetFormulas", new Object[] {SID_SRCStream, formulaArray});
        
    }
    /**
    *
    
    * @param SID_SRCStream
    * @param Flags
    * @param UnitsNamesOrCodes
    * @param resultArray
    * @return void
    **/
    public void GetResults(Object[] SID_SRCStream,short Flags,Object[] UnitsNamesOrCodes,Object[] resultArray) throws COMException
    {
      
		invokeN("GetResults", new Object[] {SID_SRCStream, new Short(Flags), UnitsNamesOrCodes, resultArray});
        
    }
    /**
    *
    
    * @param SID_SRCStream
    * @param formulaArray
    * @param Flags
    * @return short
    **/
    public short SetFormulas(Object[] SID_SRCStream,Object[] formulaArray,short Flags) throws COMException
    {
      
		return ((Short)invokeN("SetFormulas", new Object[] {SID_SRCStream, formulaArray, new Short(Flags)})).shortValue();
        
    }
    /**
    *
    
    * @param SID_SRCStream
    * @param UnitsNamesOrCodes
    * @param resultArray
    * @param Flags
    * @return short
    **/
    public short SetResults(Object[] SID_SRCStream,Object[] UnitsNamesOrCodes,Object[] resultArray,short Flags) throws COMException
    {
      
		return ((Short)invokeN("SetResults", new Object[] {SID_SRCStream, UnitsNamesOrCodes, resultArray, new Short(Flags)})).shortValue();
        
    }
    /**
    *
    * @return void
    **/
    public IVConnects getConnects() throws COMException
    {
        IVConnects res = new IVConnects();
          DispatchPtr dispPtr = (DispatchPtr)get("Connects");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    
    * @param FileName
    * @return void
    **/
    public void ImportIcon(String FileName) throws COMException
    {
      
		invokeN("ImportIcon", new Object[] {FileName});
        
    }
    /**
    *
    
    * @param FileName
    * @param Flags
    * @return void
    **/
    public void ExportIconTransparentAsBlack(String FileName,short Flags) throws COMException
    {
      
		invokeN("ExportIconTransparentAsBlack", new Object[] {FileName, new Short(Flags)});
        
    }
    /**
    *
    
    * @return void
    **/
    public void Layout() throws COMException
    {
      
		invokeN("Layout", new Object[] {});
        
    }
    /**
    *
    
    * @param Flags
    * @return void
    **/
    public void BoundingBox(short Flags,double[] lpr8Left,double[] lpr8Bottom,double[] lpr8Right,double[] lpr8Top) throws COMException
    {
      
		invokeN("BoundingBox", new Object[] {new Short(Flags), lpr8Left, lpr8Bottom, lpr8Right, lpr8Top});
        
    }
    /**
    *
    * @return short
    **/
    public short getID16() throws COMException
    {
        return ((Short)get("ID16")).shortValue();
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
    * @return short
    **/
    public short getPatternFlags() throws COMException
    {
        return ((Short)get("PatternFlags")).shortValue();
    }
        
    /**
    *
    * @param PatternFlags
    **/
    public void setPatternFlags(short PatternFlags) throws COMException
    {
        put("PatternFlags", PatternFlags);
    }
        
    /**
    *
    * @return short
    **/
    public short getMatchByName() throws COMException
    {
        return ((Short)get("MatchByName")).shortValue();
    }
        
    /**
    *
    * @param MatchByName
    **/
    public void setMatchByName(short MatchByName) throws COMException
    {
        put("MatchByName", MatchByName);
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
    * @return short
    **/
    public short getHidden() throws COMException
    {
        return ((Short)get("Hidden")).shortValue();
    }
        
    /**
    *
    * @param Hidden
    **/
    public void setHidden(short Hidden) throws COMException
    {
        put("Hidden", Hidden);
    }
        
    /**
    *
    * @return String
    **/
    public String getBaseID() throws COMException
    {
         return (String)get("BaseID");
    }
        
    /**
    *
    * @return String
    **/
    public String getNewBaseID() throws COMException
    {
         return (String)get("NewBaseID");
    }
        
    /**
    *
    * @return void
    **/
    public IVSelection getSpatialSearch(double x,double y,short Relation,double Tolerance,short Flags) throws COMException
    {
        IVSelection res = new IVSelection();
          DispatchPtr dispPtr = (DispatchPtr)getN("SpatialSearch", new Object[] {new Double(x),new Double(y),new Short(Relation),new Double(Tolerance),new Short(Flags)});
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    
    * @return void
    **/
    public IVMasterShortcut CreateShortcut() throws COMException
    {
      IVMasterShortcut res = new IVMasterShortcut();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("CreateShortcut", new Object[] {});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    * @return String
    **/
    public String getNameU() throws COMException
    {
         return (String)get("NameU");
    }
        
    /**
    *
    * @param NameU
    **/
    public void setNameU(String NameU) throws COMException
    {
        put("NameU", NameU);
    }
        
    /**
    *
    
    * @param ObjectsToInstance
    * @param xyArray
    * @param IDArray
    * @return short
    **/
    public short DropManyU(Object[] ObjectsToInstance,Object[] xyArray,Object[] IDArray) throws COMException
    {
      
		return ((Short)invokeN("DropManyU", new Object[] {ObjectsToInstance, xyArray, IDArray})).shortValue();
        
    }
    /**
    *
    
    * @param SID_SRCStream
    * @param formulaArray
    * @return void
    **/
    public void GetFormulasU(Object[] SID_SRCStream,Object[] formulaArray) throws COMException
    {
      
		invokeN("GetFormulasU", new Object[] {SID_SRCStream, formulaArray});
        
    }
    /**
    *
    
    * @param degree
    * @param Flags
    * @param xyArray
    * @param knots
    * @param weights
    * @return void
    **/
    public IVShape DrawNURBS(short degree,short Flags,Object[] xyArray,Object[] knots,Variant weights) throws COMException
    {
      IVShape res = new IVShape();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("DrawNURBS", new Object[] {new Short(degree), new Short(Flags), xyArray, knots, weights});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    
    * @param FileName
    * @param Flags
    * @param TransparentRGB
    * @return void
    **/
    public void ExportIcon(String FileName,short Flags,Variant TransparentRGB) throws COMException
    {
      
		invokeN("ExportIcon", new Object[] {FileName, new Short(Flags), TransparentRGB});
        
    }
    /**
    *
    * @return short
    **/
    public short getIndexInStencil() throws COMException
    {
        return ((Short)get("IndexInStencil")).shortValue();
    }
        
    /**
    *
    * @return void
    **/
    public void getPicture() throws COMException
    {
        get("Picture");
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
    public IVMaster getEditCopy() throws COMException
    {
        IVMaster res = new IVMaster();
          DispatchPtr dispPtr = (DispatchPtr)get("EditCopy");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return void
    **/
    public IVMaster getOriginal() throws COMException
    {
        IVMaster res = new IVMaster();
          DispatchPtr dispPtr = (DispatchPtr)get("Original");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getIsChanged() throws COMException
    {
        return ((Boolean)get("IsChanged")).booleanValue();
    }
        
    /**
    *
    * @param IndexInStencil
    **/
    public void setIndexInStencil(short IndexInStencil) throws COMException
    {
        put("IndexInStencil", IndexInStencil);
    }
        
    /**
    *
    
    * @return void
    **/
    public void ResizeToFitContents() throws COMException
    {
      
		invokeN("ResizeToFitContents", new Object[] {});
        
    }
    /**
    *
    
    * @param Flags
    * @return void
    **/
    public void Paste(Variant Flags) throws COMException
    {
      
		invokeN("Paste", new Object[] {Flags});
        
    }
    /**
    *
    
    * @param Format
    * @param Link
    * @param DisplayAsIcon
    * @return void
    **/
    public void PasteSpecial(int Format,Variant Link,Variant DisplayAsIcon) throws COMException
    {
      
		invokeN("PasteSpecial", new Object[] {new Integer(Format), Link, DisplayAsIcon});
        
    }
    /**
    *
    
    * @param SelType
    * @param IterationMode
    * @param Data
    * @return void
    **/
    public IVSelection CreateSelection(int SelType,int IterationMode,Variant Data) throws COMException
    {
      IVSelection res = new IVSelection();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("CreateSelection", new Object[] {new Integer(SelType), new Integer(IterationMode), Data});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    
    * @param Type
    * @param xPos
    * @param yPos
    * @return void
    **/
    public IVShape AddGuide(short Type,double xPos,double yPos) throws COMException
    {
      IVShape res = new IVShape();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("AddGuide", new Object[] {new Short(Type), new Double(xPos), new Double(yPos)});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    
    * @param xBegin
    * @param yBegin
    * @param xEnd
    * @param yEnd
    * @param xControl
    * @param yControl
    * @return void
    **/
    public IVShape DrawArcByThreePoints(double xBegin,double yBegin,double xEnd,double yEnd,double xControl,double yControl) throws COMException
    {
      IVShape res = new IVShape();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("DrawArcByThreePoints", new Object[] {new Double(xBegin), new Double(yBegin), new Double(xEnd), new Double(yEnd), new Double(xControl), new Double(yControl)});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    
    * @param xBegin
    * @param yBegin
    * @param xEnd
    * @param yEnd
    * @param SweepFlag
    * @return void
    **/
    public IVShape DrawQuarterArc(double xBegin,double yBegin,double xEnd,double yEnd,int SweepFlag) throws COMException
    {
      IVShape res = new IVShape();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("DrawQuarterArc", new Object[] {new Double(xBegin), new Double(yBegin), new Double(xEnd), new Double(yEnd), new Integer(SweepFlag)});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    
    * @param xCenter
    * @param yCenter
    * @param Radius
    * @param StartAngle
    * @param EndAngle
    * @return void
    **/
    public IVShape DrawCircularArc(double xCenter,double yCenter,double Radius,double StartAngle,double EndAngle) throws COMException
    {
      IVShape res = new IVShape();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("DrawCircularArc", new Object[] {new Double(xCenter), new Double(yCenter), new Double(Radius), new Double(StartAngle), new Double(EndAngle)});
          res.stealUnknown(dispPtr);
          return res;
        
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
    * @return boolean
    **/
    public boolean getDataGraphicHidden() throws COMException
    {
        return ((Boolean)get("DataGraphicHidden")).booleanValue();
    }
        
    /**
    *
    * @param DataGraphicHidden
    **/
    public void setDataGraphicHidden(boolean DataGraphicHidden) throws COMException
    {
        put("DataGraphicHidden", DataGraphicHidden);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getDataGraphicHidesText() throws COMException
    {
        return ((Boolean)get("DataGraphicHidesText")).booleanValue();
    }
        
    /**
    *
    * @param DataGraphicHidesText
    **/
    public void setDataGraphicHidesText(boolean DataGraphicHidesText) throws COMException
    {
        put("DataGraphicHidesText", DataGraphicHidesText);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getDataGraphicShowBorder() throws COMException
    {
        return ((Boolean)get("DataGraphicShowBorder")).booleanValue();
    }
        
    /**
    *
    * @param DataGraphicShowBorder
    **/
    public void setDataGraphicShowBorder(boolean DataGraphicShowBorder) throws COMException
    {
        put("DataGraphicShowBorder", DataGraphicShowBorder);
    }
        
    /**
    *
    * @return int
    **/
    public int getDataGraphicHorizontalPosition() throws COMException
    {
        return ((Integer)get("DataGraphicHorizontalPosition")).intValue();
    }
        
    /**
    *
    * @param DataGraphicHorizontalPosition
    **/
    public void setDataGraphicHorizontalPosition(int DataGraphicHorizontalPosition) throws COMException
    {
        put("DataGraphicHorizontalPosition", DataGraphicHorizontalPosition);
    }
        
    /**
    *
    * @return int
    **/
    public int getDataGraphicVerticalPosition() throws COMException
    {
        return ((Integer)get("DataGraphicVerticalPosition")).intValue();
    }
        
    /**
    *
    * @param DataGraphicVerticalPosition
    **/
    public void setDataGraphicVerticalPosition(int DataGraphicVerticalPosition) throws COMException
    {
        put("DataGraphicVerticalPosition", DataGraphicVerticalPosition);
    }
        
    /**
    *
    * @return void
    **/
    public IVGraphicItems getGraphicItems() throws COMException
    {
        IVGraphicItems res = new IVGraphicItems();
          DispatchPtr dispPtr = (DispatchPtr)get("GraphicItems");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    
    * @return void
    **/
    public void DataGraphicDelete() throws COMException
    {
      
		invokeN("DataGraphicDelete", new Object[] {});
        
    }
}

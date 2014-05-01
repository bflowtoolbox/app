
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVPage
 * Description: 
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVPage extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0709-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVPage.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVPage (it is required by Jawin for some internal working though).
	 */
	public IVPage() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVPage interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVPage(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVPage interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVPage(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVPage interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVPage interface on.
	 */
	public IVPage(COMPtr comObject) throws COMException {
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
    public short getBackground() throws COMException
    {
        return ((Short)get("Background")).shortValue();
    }
        
    /**
    *
    * @param Background
    **/
    public void setBackground(short Background) throws COMException
    {
        put("Background", Background);
    }
        
    /**
    *
    
    * @return void
    **/
    public void old_Paste() throws COMException
    {
      
		invokeN("old_Paste", new Object[] {});
        
    }
    /**
    *
    
    * @param Format
    * @return void
    **/
    public void old_PasteSpecial(short Format) throws COMException
    {
      
		invokeN("old_PasteSpecial", new Object[] {new Short(Format)});
        
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
    * @return short
    **/
    public short getIndex() throws COMException
    {
        return ((Short)get("Index")).shortValue();
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
    * @return void
    **/
    public IVPage getBackPageAsObj() throws COMException
    {
        IVPage res = new IVPage();
          DispatchPtr dispPtr = (DispatchPtr)get("BackPageAsObj");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @param BackPageFromName
    **/
    public void setBackPageFromName(String BackPageFromName) throws COMException
    {
        put("BackPageFromName", BackPageFromName);
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
    
    * @param fRenumberPages
    * @return void
    **/
    public void Delete(short fRenumberPages) throws COMException
    {
      
		invokeN("Delete", new Object[] {new Short(fRenumberPages)});
        
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
    * @return Variant
    **/
    public Object getBackPage() throws COMException
    {
         return get("BackPage");
    }
        
    /**
    *
    * @param BackPage
    **/
    public void setBackPage(Variant BackPage) throws COMException
    {
        put("BackPage", BackPage);
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
    public IVSelection getSpatialSearch(double x,double y,short Relation,double Tolerance,short Flags) throws COMException
    {
        IVSelection res = new IVSelection();
          DispatchPtr dispPtr = (DispatchPtr)getN("SpatialSearch", new Object[] {new Double(x),new Double(y),new Short(Relation),new Double(Tolerance),new Short(Flags)});
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
    * @return void
    **/
    public void getPicture() throws COMException
    {
        get("Picture");
    }
        
    /**
    *
    * @param Index
    **/
    public void setIndex(short Index) throws COMException
    {
        put("Index", Index);
    }
        
    /**
    *
    * @return int
    **/
    public int getPrintTileCount() throws COMException
    {
        return ((Integer)get("PrintTileCount")).intValue();
    }
        
    /**
    *
    
    * @param nTile
    * @return void
    **/
    public void PrintTile(int nTile) throws COMException
    {
      
		invokeN("PrintTile", new Object[] {new Integer(nTile)});
        
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
    * @return int
    **/
    public int getType() throws COMException
    {
        return ((Integer)get("Type")).intValue();
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
    public int getReviewerID() throws COMException
    {
        return ((Integer)get("ReviewerID")).intValue();
    }
        
    /**
    *
    * @return void
    **/
    public IVPage getOriginalPage() throws COMException
    {
        IVPage res = new IVPage();
          DispatchPtr dispPtr = (DispatchPtr)get("OriginalPage");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    
    * @param DataRecordsetID
    * @param ShapeIDs
    * @return void
    **/
    public void GetShapesLinkedToData(int DataRecordsetID,Object[] ShapeIDs) throws COMException
    {
      
		invokeN("GetShapesLinkedToData", new Object[] {new Integer(DataRecordsetID), ShapeIDs});
        
    }
    /**
    *
    
    * @param DataRecordsetID
    * @param DataRowID
    * @param ShapeIDs
    * @return void
    **/
    public void GetShapesLinkedToDataRow(int DataRecordsetID,int DataRowID,Object[] ShapeIDs) throws COMException
    {
      
		invokeN("GetShapesLinkedToDataRow", new Object[] {new Integer(DataRecordsetID), new Integer(DataRowID), ShapeIDs});
        
    }
    /**
    *
    
    * @param DataRecordsetID
    * @param DataRowIDs
    * @param ShapeIDs
    * @param ApplyDataGraphicAfterLink
    * @return void
    **/
    public void LinkShapesToDataRows(int DataRecordsetID,Object[] DataRowIDs,Object[] ShapeIDs,boolean ApplyDataGraphicAfterLink) throws COMException
    {
      
		invokeN("LinkShapesToDataRows", new Object[] {new Integer(DataRecordsetID), DataRowIDs, ShapeIDs, new Boolean(ApplyDataGraphicAfterLink)});
        
    }
    /**
    *
    
    * @param ShapeIDs
    * @param UniqueIDArgs
    * @param GUIDs
    * @return void
    **/
    public void ShapeIDsToUniqueIDs(Object[] ShapeIDs,int UniqueIDArgs,Object[] GUIDs) throws COMException
    {
      
		invokeN("ShapeIDsToUniqueIDs", new Object[] {ShapeIDs, new Integer(UniqueIDArgs), GUIDs});
        
    }
    /**
    *
    
    * @param GUIDs
    * @param ShapeIDs
    * @return void
    **/
    public void UniqueIDsToShapeIDs(Object[] GUIDs,Object[] ShapeIDs) throws COMException
    {
      
		invokeN("UniqueIDsToShapeIDs", new Object[] {GUIDs, ShapeIDs});
        
    }
    /**
    *
    
    * @param ObjectToDrop
    * @param x
    * @param y
    * @param DataRecordsetID
    * @param DataRowID
    * @param ApplyDataGraphicAfterLink
    * @return void
    **/
    public IVShape DropLinked(IUnknown ObjectToDrop,double x,double y,int DataRecordsetID,int DataRowID,boolean ApplyDataGraphicAfterLink) throws COMException
    {
      IVShape res = new IVShape();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("DropLinked", new Object[] {ObjectToDrop, new Double(x), new Double(y), new Integer(DataRecordsetID), new Integer(DataRowID), new Boolean(ApplyDataGraphicAfterLink)});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    
    * @param ObjectsToInstance
    * @param XYs
    * @param DataRecordsetID
    * @param DataRowIDs
    * @param ApplyDataGraphicAfterLink
    * @param ShapeIDs
    * @return int
    **/
    public int DropManyLinkedU(Object[] ObjectsToInstance,Object[] XYs,int DataRecordsetID,Object[] DataRowIDs,boolean ApplyDataGraphicAfterLink,Object[] ShapeIDs) throws COMException
    {
      
		return ((Integer)invokeN("DropManyLinkedU", new Object[] {ObjectsToInstance, XYs, new Integer(DataRecordsetID), DataRowIDs, new Boolean(ApplyDataGraphicAfterLink), ShapeIDs})).intValue();
        
    }
    /**
    *
    * @return Variant
    **/
    public Object getThemeColors() throws COMException
    {
         return get("ThemeColors");
    }
        
    /**
    *
    * @param ThemeColors
    **/
    public void setThemeColors(Variant ThemeColors) throws COMException
    {
        put("ThemeColors", ThemeColors);
    }
        
    /**
    *
    * @return Variant
    **/
    public Object getThemeEffects() throws COMException
    {
         return get("ThemeEffects");
    }
        
    /**
    *
    * @param ThemeEffects
    **/
    public void setThemeEffects(Variant ThemeEffects) throws COMException
    {
        put("ThemeEffects", ThemeEffects);
    }
        
}

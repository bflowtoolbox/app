
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVShape
 * Description: 
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVShape extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d070c-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVShape.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVShape (it is required by Jawin for some internal working though).
	 */
	public IVShape() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVShape interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVShape(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVShape interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVShape(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVShape interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVShape interface on.
	 */
	public IVShape(COMPtr comObject) throws COMException {
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
    public void VoidGroup() throws COMException
    {
      
		invokeN("VoidGroup", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void BringForward() throws COMException
    {
      
		invokeN("BringForward", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void BringToFront() throws COMException
    {
      
		invokeN("BringToFront", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void ConvertToGroup() throws COMException
    {
      
		invokeN("ConvertToGroup", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void FlipHorizontal() throws COMException
    {
      
		invokeN("FlipHorizontal", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void FlipVertical() throws COMException
    {
      
		invokeN("FlipVertical", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void ReverseEnds() throws COMException
    {
      
		invokeN("ReverseEnds", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void SendBackward() throws COMException
    {
      
		invokeN("SendBackward", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void SendToBack() throws COMException
    {
      
		invokeN("SendToBack", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void Rotate90() throws COMException
    {
      
		invokeN("Rotate90", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void Ungroup() throws COMException
    {
      
		invokeN("Ungroup", new Object[] {});
        
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
    * @return DispatchPtr
    **/
    public DispatchPtr getParent() throws COMException
    {
         return (DispatchPtr)get("Parent");
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
    * @return void
    **/
    public IVMaster getMaster() throws COMException
    {
        IVMaster res = new IVMaster();
          DispatchPtr dispPtr = (DispatchPtr)get("Master");
          res.stealUnknown(dispPtr);
          return res;
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
    public IVCell getCells(String localeSpecificCellName) throws COMException
    {
        IVCell res = new IVCell();
          DispatchPtr dispPtr = (DispatchPtr)get("Cells", localeSpecificCellName);
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return void
    **/
    public IVCell getCellsSRC(short Section,short Row,short Column) throws COMException
    {
        IVCell res = new IVCell();
          DispatchPtr dispPtr = (DispatchPtr)getN("CellsSRC", new Object[] {new Short(Section),new Short(Row),new Short(Column)});
          res.stealUnknown(dispPtr);
          return res;
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
    * @return String
    **/
    public String getData1() throws COMException
    {
         return (String)get("Data1");
    }
        
    /**
    *
    * @param Data1
    **/
    public void setData1(String Data1) throws COMException
    {
        put("Data1", Data1);
    }
        
    /**
    *
    * @return String
    **/
    public String getData2() throws COMException
    {
         return (String)get("Data2");
    }
        
    /**
    *
    * @param Data2
    **/
    public void setData2(String Data2) throws COMException
    {
        put("Data2", Data2);
    }
        
    /**
    *
    * @return String
    **/
    public String getData3() throws COMException
    {
         return (String)get("Data3");
    }
        
    /**
    *
    * @param Data3
    **/
    public void setData3(String Data3) throws COMException
    {
        put("Data3", Data3);
    }
        
    /**
    *
    * @return String
    **/
    public String getHelp() throws COMException
    {
         return (String)get("Help");
    }
        
    /**
    *
    * @param Help
    **/
    public void setHelp(String Help) throws COMException
    {
        put("Help", Help);
    }
        
    /**
    *
    * @return String
    **/
    public String getNameID() throws COMException
    {
         return (String)get("NameID");
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
    * @return String
    **/
    public String getText() throws COMException
    {
         return (String)get("Text");
    }
        
    /**
    *
    * @param Text
    **/
    public void setText(String Text) throws COMException
    {
        put("Text", Text);
    }
        
    /**
    *
    * @return int
    **/
    public int getCharCount() throws COMException
    {
        return ((Integer)get("CharCount")).intValue();
    }
        
    /**
    *
    * @return void
    **/
    public IVCharacters getCharacters() throws COMException
    {
        IVCharacters res = new IVCharacters();
          DispatchPtr dispPtr = (DispatchPtr)get("Characters");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    
    * @return void
    **/
    public void old_Copy() throws COMException
    {
      
		invokeN("old_Copy", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void old_Cut() throws COMException
    {
      
		invokeN("old_Cut", new Object[] {});
        
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
    public void VoidDuplicate() throws COMException
    {
      
		invokeN("VoidDuplicate", new Object[] {});
        
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
    * @return short
    **/
    public short getOneD() throws COMException
    {
        return ((Short)get("OneD")).shortValue();
    }
        
    /**
    *
    * @param OneD
    **/
    public void setOneD(short OneD) throws COMException
    {
        put("OneD", OneD);
    }
        
    /**
    *
    * @return short
    **/
    public short getGeometryCount() throws COMException
    {
        return ((Short)get("GeometryCount")).shortValue();
    }
        
    /**
    *
    * @return short
    **/
    public short getRowCount(short Section) throws COMException
    {
        return ((Short)get("RowCount", new Short(Section))).shortValue();
    }
        
    /**
    *
    
    * @param Section
    * @return short
    **/
    public short AddSection(short Section) throws COMException
    {
      
		return ((Short)invokeN("AddSection", new Object[] {new Short(Section)})).shortValue();
        
    }
    /**
    *
    
    * @param Section
    * @return void
    **/
    public void DeleteSection(short Section) throws COMException
    {
      
		invokeN("DeleteSection", new Object[] {new Short(Section)});
        
    }
    /**
    *
    
    * @param Section
    * @param Row
    * @param RowTag
    * @return short
    **/
    public short AddRow(short Section,short Row,short RowTag) throws COMException
    {
      
		return ((Short)invokeN("AddRow", new Object[] {new Short(Section), new Short(Row), new Short(RowTag)})).shortValue();
        
    }
    /**
    *
    
    * @param Section
    * @param Row
    * @return void
    **/
    public void DeleteRow(short Section,short Row) throws COMException
    {
      
		invokeN("DeleteRow", new Object[] {new Short(Section), new Short(Row)});
        
    }
    /**
    *
    * @return short
    **/
    public short getRowsCellCount(short Section,short Row) throws COMException
    {
        return ((Short)getN("RowsCellCount", new Object[] {new Short(Section),new Short(Row)})).shortValue();
    }
        
    /**
    *
    * @return short
    **/
    public short getRowType(short Section,short Row) throws COMException
    {
        return ((Short)getN("RowType", new Object[] {new Short(Section),new Short(Row)})).shortValue();
    }
        
    /**
    *
    * @param RowType
    **/
    public void setRowType(short Section,short Row,short newValue3) throws COMException
    {
        putN("RowType", new Object[] {new Short(Section), new Short(Row)} , new Short(newValue3));
    }
        
    /**
    *
    
    * @param xPos
    * @param yPos
    * @return void
    **/
    public void SetCenter(double xPos,double yPos) throws COMException
    {
      
		invokeN("SetCenter", new Object[] {new Double(xPos), new Double(yPos)});
        
    }
    /**
    *
    
    * @param xPos
    * @param yPos
    * @return void
    **/
    public void SetBegin(double xPos,double yPos) throws COMException
    {
      
		invokeN("SetBegin", new Object[] {new Double(xPos), new Double(yPos)});
        
    }
    /**
    *
    
    * @param xPos
    * @param yPos
    * @return void
    **/
    public void SetEnd(double xPos,double yPos) throws COMException
    {
      
		invokeN("SetEnd", new Object[] {new Double(xPos), new Double(yPos)});
        
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
    * @return short
    **/
    public short getIndex16() throws COMException
    {
        return ((Short)get("Index16")).shortValue();
    }
        
    /**
    *
    * @return String
    **/
    public String getStyle() throws COMException
    {
         return (String)get("Style");
    }
        
    /**
    *
    * @param Style
    **/
    public void setStyle(String Style) throws COMException
    {
        put("Style", Style);
    }
        
    /**
    *
    * @param StyleKeepFmt
    **/
    public void setStyleKeepFmt(String StyleKeepFmt) throws COMException
    {
        put("StyleKeepFmt", StyleKeepFmt);
    }
        
    /**
    *
    * @return String
    **/
    public String getLineStyle() throws COMException
    {
         return (String)get("LineStyle");
    }
        
    /**
    *
    * @param LineStyle
    **/
    public void setLineStyle(String LineStyle) throws COMException
    {
        put("LineStyle", LineStyle);
    }
        
    /**
    *
    * @param LineStyleKeepFmt
    **/
    public void setLineStyleKeepFmt(String LineStyleKeepFmt) throws COMException
    {
        put("LineStyleKeepFmt", LineStyleKeepFmt);
    }
        
    /**
    *
    * @return String
    **/
    public String getFillStyle() throws COMException
    {
         return (String)get("FillStyle");
    }
        
    /**
    *
    * @param FillStyle
    **/
    public void setFillStyle(String FillStyle) throws COMException
    {
        put("FillStyle", FillStyle);
    }
        
    /**
    *
    * @param FillStyleKeepFmt
    **/
    public void setFillStyleKeepFmt(String FillStyleKeepFmt) throws COMException
    {
        put("FillStyleKeepFmt", FillStyleKeepFmt);
    }
        
    /**
    *
    * @return String
    **/
    public String getTextStyle() throws COMException
    {
         return (String)get("TextStyle");
    }
        
    /**
    *
    * @param TextStyle
    **/
    public void setTextStyle(String TextStyle) throws COMException
    {
        put("TextStyle", TextStyle);
    }
        
    /**
    *
    * @param TextStyleKeepFmt
    **/
    public void setTextStyleKeepFmt(String TextStyleKeepFmt) throws COMException
    {
        put("TextStyleKeepFmt", TextStyleKeepFmt);
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
    * @return double
    **/
    public double getold_AreaIU() throws COMException
    {
         return ((Double)get("old_AreaIU")).doubleValue();
    }
        
    /**
    *
    * @return double
    **/
    public double getold_LengthIU() throws COMException
    {
         return ((Double)get("old_LengthIU")).doubleValue();
    }
        
    /**
    *
    * @return IUnknown
    **/
    public IUnknown getGeomExIf(short fFill,double LineRes) throws COMException
    {
         return (IUnknown)getN("GeomExIf", new Object[] {new Short(fFill),new Double(LineRes)});
    }
        
    /**
    *
    * @return String
    **/
    public String getUniqueID(short fUniqueID) throws COMException
    {
         return (String)get("UniqueID", new Short(fUniqueID));
    }
        
    /**
    *
    * @return void
    **/
    public IVPage getContainingPage() throws COMException
    {
        IVPage res = new IVPage();
          DispatchPtr dispPtr = (DispatchPtr)get("ContainingPage");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return void
    **/
    public IVMaster getContainingMaster() throws COMException
    {
        IVMaster res = new IVMaster();
          DispatchPtr dispPtr = (DispatchPtr)get("ContainingMaster");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return void
    **/
    public IVShape getContainingShape() throws COMException
    {
        IVShape res = new IVShape();
          DispatchPtr dispPtr = (DispatchPtr)get("ContainingShape");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return short
    **/
    public short getSectionExists(short Section,short fExistsLocally) throws COMException
    {
        return ((Short)getN("SectionExists", new Object[] {new Short(Section),new Short(fExistsLocally)})).shortValue();
    }
        
    /**
    *
    * @return short
    **/
    public short getRowExists(short Section,short Row,short fExistsLocally) throws COMException
    {
        return ((Short)getN("RowExists", new Object[] {new Short(Section),new Short(Row),new Short(fExistsLocally)})).shortValue();
    }
        
    /**
    *
    * @return short
    **/
    public short getCellExists(String localeSpecificCellName,short fExistsLocally) throws COMException
    {
        return ((Short)getN("CellExists", new Object[] {localeSpecificCellName,new Short(fExistsLocally)})).shortValue();
    }
        
    /**
    *
    * @return short
    **/
    public short getCellsSRCExists(short Section,short Row,short Column,short fExistsLocally) throws COMException
    {
        return ((Short)getN("CellsSRCExists", new Object[] {new Short(Section),new Short(Row),new Short(Column),new Short(fExistsLocally)})).shortValue();
    }
        
    /**
    *
    * @return short
    **/
    public short getLayerCount() throws COMException
    {
        return ((Short)get("LayerCount")).shortValue();
    }
        
    /**
    *
    * @return void
    **/
    public IVLayer getLayer(short Index) throws COMException
    {
        IVLayer res = new IVLayer();
          DispatchPtr dispPtr = (DispatchPtr)get("Layer", new Short(Index));
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    
    * @param Section
    * @param RowName
    * @param RowTag
    * @return short
    **/
    public short AddNamedRow(short Section,String RowName,short RowTag) throws COMException
    {
      
		return ((Short)invokeN("AddNamedRow", new Object[] {new Short(Section), RowName, new Short(RowTag)})).shortValue();
        
    }
    /**
    *
    
    * @param Section
    * @param Row
    * @param RowTag
    * @param RowCount
    * @return short
    **/
    public short AddRows(short Section,short Row,short RowTag,short RowCount) throws COMException
    {
      
		return ((Short)invokeN("AddRows", new Object[] {new Short(Section), new Short(Row), new Short(RowTag), new Short(RowCount)})).shortValue();
        
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
    
    * @param Tolerance
    * @param Flags
    * @return void
    **/
    public void FitCurve(double Tolerance,short Flags) throws COMException
    {
      
		invokeN("FitCurve", new Object[] {new Double(Tolerance), new Short(Flags)});
        
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
    
    * @return void
    **/
    public void CenterDrawing() throws COMException
    {
      
		invokeN("CenterDrawing", new Object[] {});
        
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
    * @return String
    **/
    public String getClassID() throws COMException
    {
         return (String)get("ClassID");
    }
        
    /**
    *
    * @return short
    **/
    public short getForeignType() throws COMException
    {
        return ((Short)get("ForeignType")).shortValue();
    }
        
    /**
    *
    * @return DispatchPtr
    **/
    public DispatchPtr getObject() throws COMException
    {
         return (DispatchPtr)get("Object");
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
    public IVWindow OpenSheetWindow() throws COMException
    {
      IVWindow res = new IVWindow();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("OpenSheetWindow", new Object[] {});
          res.stealUnknown(dispPtr);
          return res;
        
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
    
    * @param SRCStream
    * @param formulaArray
    * @return void
    **/
    public void GetFormulas(Object[] SRCStream,Object[] formulaArray) throws COMException
    {
      
		invokeN("GetFormulas", new Object[] {SRCStream, formulaArray});
        
    }
    /**
    *
    
    * @param SRCStream
    * @param Flags
    * @param UnitsNamesOrCodes
    * @param resultArray
    * @return void
    **/
    public void GetResults(Object[] SRCStream,short Flags,Object[] UnitsNamesOrCodes,Object[] resultArray) throws COMException
    {
      
		invokeN("GetResults", new Object[] {SRCStream, new Short(Flags), UnitsNamesOrCodes, resultArray});
        
    }
    /**
    *
    
    * @param SRCStream
    * @param formulaArray
    * @param Flags
    * @return short
    **/
    public short SetFormulas(Object[] SRCStream,Object[] formulaArray,short Flags) throws COMException
    {
      
		return ((Short)invokeN("SetFormulas", new Object[] {SRCStream, formulaArray, new Short(Flags)})).shortValue();
        
    }
    /**
    *
    
    * @param SRCStream
    * @param UnitsNamesOrCodes
    * @param resultArray
    * @param Flags
    * @return short
    **/
    public short SetResults(Object[] SRCStream,Object[] UnitsNamesOrCodes,Object[] resultArray,short Flags) throws COMException
    {
      
		return ((Short)invokeN("SetResults", new Object[] {SRCStream, UnitsNamesOrCodes, resultArray, new Short(Flags)})).shortValue();
        
    }
    /**
    *
    * @return void
    **/
    public IVConnects getFromConnects() throws COMException
    {
        IVConnects res = new IVConnects();
          DispatchPtr dispPtr = (DispatchPtr)get("FromConnects");
          res.stealUnknown(dispPtr);
          return res;
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
    
    * @param xPos
    * @param yPos
    * @param Tolerance
    * @return short
    **/
    public short HitTest(double xPos,double yPos,double Tolerance) throws COMException
    {
      
		return ((Short)invokeN("HitTest", new Object[] {new Double(xPos), new Double(yPos), new Double(Tolerance)})).shortValue();
        
    }
    /**
    *
    * @return void
    **/
    public IVHyperlink getHyperlink() throws COMException
    {
        IVHyperlink res = new IVHyperlink();
          DispatchPtr dispPtr = (DispatchPtr)get("Hyperlink");
          res.stealUnknown(dispPtr);
          return res;
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
    * @return short
    **/
    public short getObjectIsInherited() throws COMException
    {
        return ((Short)get("ObjectIsInherited")).shortValue();
    }
        
    /**
    *
    * @return void
    **/
    public IVPaths getPaths() throws COMException
    {
        IVPaths res = new IVPaths();
          DispatchPtr dispPtr = (DispatchPtr)get("Paths");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return void
    **/
    public IVPaths getPathsLocal() throws COMException
    {
        IVPaths res = new IVPaths();
          DispatchPtr dispPtr = (DispatchPtr)get("PathsLocal");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    
    * @return void
    **/
    public IVHyperlink AddHyperlink() throws COMException
    {
      IVHyperlink res = new IVHyperlink();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("AddHyperlink", new Object[] {});
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
    * @return int
    **/
    public int getIndex() throws COMException
    {
        return ((Integer)get("Index")).intValue();
    }
        
    /**
    *
    * @return void
    **/
    public IVSection getSection(short Index) throws COMException
    {
        IVSection res = new IVSection();
          DispatchPtr dispPtr = (DispatchPtr)get("Section", new Short(Index));
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return void
    **/
    public IVHyperlinks getHyperlinks() throws COMException
    {
        IVHyperlinks res = new IVHyperlinks();
          DispatchPtr dispPtr = (DispatchPtr)get("Hyperlinks");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return short
    **/
    public short getSpatialRelation(Object[] OtherShape,double Tolerance,short Flags) throws COMException
    {
        return ((Short)getN("SpatialRelation", new Object[] {OtherShape,new Double(Tolerance),new Short(Flags)})).shortValue();
    }
        
    /**
    *
    * @return double
    **/
    public double getDistanceFrom(Object[] OtherShape,short Flags) throws COMException
    {
         return ((Double)getN("DistanceFrom", new Object[] {OtherShape,new Short(Flags)})).doubleValue();
    }
        
    /**
    *
    * @return double
    **/
    public double getDistanceFromPoint(double x,double y,short Flags,Variant[] pvPathIndex,Variant[] pvCurveIndex,Variant[] pvt) throws COMException
    {
         return ((Double)getN("DistanceFromPoint", new Object[] {new Double(x),new Double(y),new Short(Flags),pvPathIndex,pvCurveIndex,pvt})).doubleValue();
    }
        
    /**
    *
    * @return void
    **/
    public IVSelection getSpatialNeighbors(short Relation,double Tolerance,short Flags,Variant ResultRoot) throws COMException
    {
        IVSelection res = new IVSelection();
          DispatchPtr dispPtr = (DispatchPtr)getN("SpatialNeighbors", new Object[] {new Short(Relation),new Double(Tolerance),new Short(Flags),ResultRoot});
          res.stealUnknown(dispPtr);
          return res;
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
    
    * @param x
    * @param y
    * @return void
    **/
    public void TransformXYTo(Object[] OtherShape,double x,double y,double[] xprime,double[] yprime) throws COMException
    {
      
		invokeN("TransformXYTo", new Object[] {OtherShape, new Double(x), new Double(y), xprime, yprime});
        
    }
    /**
    *
    
    * @param x
    * @param y
    * @return void
    **/
    public void TransformXYFrom(Object[] OtherShape,double x,double y,double[] xprime,double[] yprime) throws COMException
    {
      
		invokeN("TransformXYFrom", new Object[] {OtherShape, new Double(x), new Double(y), xprime, yprime});
        
    }
    /**
    *
    
    * @param x
    * @param y
    * @return void
    **/
    public void XYToPage(double x,double y,double[] xprime,double[] yprime) throws COMException
    {
      
		invokeN("XYToPage", new Object[] {new Double(x), new Double(y), xprime, yprime});
        
    }
    /**
    *
    
    * @param x
    * @param y
    * @return void
    **/
    public void XYFromPage(double x,double y,double[] xprime,double[] yprime) throws COMException
    {
      
		invokeN("XYFromPage", new Object[] {new Double(x), new Double(y), xprime, yprime});
        
    }
    /**
    *
    
    * @return void
    **/
    public void UpdateAlignmentBox() throws COMException
    {
      
		invokeN("UpdateAlignmentBox", new Object[] {});
        
    }
    /**
    *
    * @return void
    **/
    public IVCell getCellsU(String localeIndependentCellName) throws COMException
    {
        IVCell res = new IVCell();
          DispatchPtr dispPtr = (DispatchPtr)get("CellsU", localeIndependentCellName);
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
    * @return short
    **/
    public short getCellExistsU(String localeIndependentCellName,short fExistsLocally) throws COMException
    {
        return ((Short)getN("CellExistsU", new Object[] {localeIndependentCellName,new Short(fExistsLocally)})).shortValue();
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
    
    * @param SRCStream
    * @param formulaArray
    * @return void
    **/
    public void GetFormulasU(Object[] SRCStream,Object[] formulaArray) throws COMException
    {
      
		invokeN("GetFormulasU", new Object[] {SRCStream, formulaArray});
        
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
    * @return short
    **/
    public short getCellsRowIndex(String localeSpecificCellName) throws COMException
    {
        return ((Short)get("CellsRowIndex", localeSpecificCellName)).shortValue();
    }
        
    /**
    *
    * @return short
    **/
    public short getCellsRowIndexU(String localeIndependentCellName) throws COMException
    {
        return ((Short)get("CellsRowIndexU", localeIndependentCellName)).shortValue();
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getIsOpenForTextEdit() throws COMException
    {
        return ((Boolean)get("IsOpenForTextEdit")).booleanValue();
    }
        
    /**
    *
    * @return void
    **/
    public IVShape getRootShape() throws COMException
    {
        IVShape res = new IVShape();
          DispatchPtr dispPtr = (DispatchPtr)get("RootShape");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return void
    **/
    public IVShape getMasterShape() throws COMException
    {
        IVShape res = new IVShape();
          DispatchPtr dispPtr = (DispatchPtr)get("MasterShape");
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
    
    * @return void
    **/
    public IVShape Group() throws COMException
    {
      IVShape res = new IVShape();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("Group", new Object[] {});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    
    * @return void
    **/
    public IVShape Duplicate() throws COMException
    {
      IVShape res = new IVShape();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("Duplicate", new Object[] {});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    
    * @return void
    **/
    public void SwapEnds() throws COMException
    {
      
		invokeN("SwapEnds", new Object[] {});
        
    }
    /**
    *
    * @param Parent
    **/
    public void setParent(DispatchPtr Parent) throws COMException
    {
        put("Parent", Parent);
    }
        
    /**
    *
    * @return Object[]
    **/
    public Object[] getForeignData() throws COMException
    {
        return null; // not implementedget("ForeignData");
    }
        
    /**
    *
    
    * @param Flags
    * @return void
    **/
    public void Copy(Variant Flags) throws COMException
    {
      
		invokeN("Copy", new Object[] {Flags});
        
    }
    /**
    *
    
    * @param Flags
    * @return void
    **/
    public void Cut(Variant Flags) throws COMException
    {
      
		invokeN("Cut", new Object[] {Flags});
        
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
    
    * @param Distance
    * @return void
    **/
    public void Offset(double Distance) throws COMException
    {
      
		invokeN("Offset", new Object[] {new Double(Distance)});
        
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
    * @return double
    **/
    public double getAreaIU(boolean fIncludeSubShapes) throws COMException
    {
         return ((Double)get("AreaIU", new Boolean(fIncludeSubShapes))).doubleValue();
    }
        
    /**
    *
    * @return double
    **/
    public double getLengthIU(boolean fIncludeSubShapes) throws COMException
    {
         return ((Double)get("LengthIU", new Boolean(fIncludeSubShapes))).doubleValue();
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
    public int getContainingPageID() throws COMException
    {
        return ((Integer)get("ContainingPageID")).intValue();
    }
        
    /**
    *
    * @return int
    **/
    public int getContainingMasterID() throws COMException
    {
        return ((Integer)get("ContainingMasterID")).intValue();
    }
        
    /**
    *
    
    * @param DataRecordsetID
    * @param RowID
    * @param ApplyDataGraphicAfterLink
    * @return void
    **/
    public void LinkToData(int DataRecordsetID,int RowID,boolean ApplyDataGraphicAfterLink) throws COMException
    {
      
		invokeN("LinkToData", new Object[] {new Integer(DataRecordsetID), new Integer(RowID), new Boolean(ApplyDataGraphicAfterLink)});
        
    }
    /**
    *
    
    * @param DataRecordsetID
    * @return void
    **/
    public void BreakLinkToData(int DataRecordsetID) throws COMException
    {
      
		invokeN("BreakLinkToData", new Object[] {new Integer(DataRecordsetID)});
        
    }
    /**
    *
    
    * @param DataRecordsetID
    * @return int
    **/
    public int GetLinkedDataRow(int DataRecordsetID) throws COMException
    {
      
		return ((Integer)invokeN("GetLinkedDataRow", new Object[] {new Integer(DataRecordsetID)})).intValue();
        
    }
    /**
    *
    
    * @param DataRecordsetIDs
    * @return void
    **/
    public void GetLinkedDataRecordsetIDs(Object[] DataRecordsetIDs) throws COMException
    {
      
		invokeN("GetLinkedDataRecordsetIDs", new Object[] {DataRecordsetIDs});
        
    }
    /**
    *
    
    * @param DataRecordsetID
    * @param CustomPropertyIndices
    * @return void
    **/
    public void GetCustomPropertiesLinkedToData(int DataRecordsetID,Object[] CustomPropertyIndices) throws COMException
    {
      
		invokeN("GetCustomPropertiesLinkedToData", new Object[] {new Integer(DataRecordsetID), CustomPropertyIndices});
        
    }
    /**
    *
    
    * @param DataRecordsetID
    * @param CustomPropertyIndex
    * @return boolean
    **/
    public boolean IsCustomPropertyLinked(int DataRecordsetID,int CustomPropertyIndex) throws COMException
    {
      
		return ((Boolean)invokeN("IsCustomPropertyLinked", new Object[] {new Integer(DataRecordsetID), new Integer(CustomPropertyIndex)})).booleanValue();
        
    }
    /**
    *
    
    * @param DataRecordsetID
    * @param CustomPropertyIndex
    * @return String
    **/
    public String GetCustomPropertyLinkedColumn(int DataRecordsetID,int CustomPropertyIndex) throws COMException
    {
      
		return (String)invokeN("GetCustomPropertyLinkedColumn", new Object[] {new Integer(DataRecordsetID), new Integer(CustomPropertyIndex)});
        
    }
    /**
    *
    
    * @param PlacementDir
    * @param Connector
    * @return void
    **/
    public void AutoConnect(Object[] ToShape,int PlacementDir,IUnknown Connector) throws COMException
    {
      
		invokeN("AutoConnect", new Object[] {ToShape, new Integer(PlacementDir), Connector});
        
    }
    /**
    *
    * @return void
    **/
    public IVMaster getDataGraphic() throws COMException
    {
        IVMaster res = new IVMaster();
          DispatchPtr dispPtr = (DispatchPtr)get("DataGraphic");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @param DataGraphic
    **/
    public void setDataGraphic(Object[] DataGraphic) throws COMException
    {
        put("DataGraphic", DataGraphic);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getIsDataGraphicCallout() throws COMException
    {
        return ((Boolean)get("IsDataGraphicCallout")).booleanValue();
    }
        
}

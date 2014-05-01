
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVExtender
 * Description: Dual interface of extensions Visio gives to a control contained in a Visio Document.
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVExtender extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0d0e-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVExtender.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVExtender (it is required by Jawin for some internal working though).
	 */
	public IVExtender() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVExtender interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVExtender(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVExtender interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVExtender(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVExtender interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVExtender interface on.
	 */
	public IVExtender(COMPtr comObject) throws COMException {
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
    * @return IUnknown
    **/
    public IUnknown getObject() throws COMException
    {
         return (IUnknown)get("Object");
    }
        
    /**
    *
    * @return IUnknown
    **/
    public IUnknown getParent() throws COMException
    {
         return (IUnknown)get("Parent");
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
    public void Index() throws COMException
    {
      
		invokeN("Index", new Object[] {});
        
    }
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
    public DispatchPtr getShapeParent() throws COMException
    {
         return (DispatchPtr)get("ShapeParent");
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
    public IVMaster getMaster() throws COMException
    {
        IVMaster res = new IVMaster();
          DispatchPtr dispPtr = (DispatchPtr)get("Master");
          res.stealUnknown(dispPtr);
          return res;
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
    
    * @return void
    **/
    public void ShapeCopy() throws COMException
    {
      
		invokeN("ShapeCopy", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void ShapeCut() throws COMException
    {
      
		invokeN("ShapeCut", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void ShapeDelete() throws COMException
    {
      
		invokeN("ShapeDelete", new Object[] {});
        
    }
    /**
    *
    
    * @return void
    **/
    public void VoidShapeDuplicate() throws COMException
    {
      
		invokeN("VoidShapeDuplicate", new Object[] {});
        
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
    public short getShapeIndex16() throws COMException
    {
        return ((Short)get("ShapeIndex16")).shortValue();
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
    
    * @param FileName
    * @return void
    **/
    public void Export(String FileName) throws COMException
    {
      
		invokeN("Export", new Object[] {FileName});
        
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
    * @return String
    **/
    public String getClassID() throws COMException
    {
         return (String)get("ClassID");
    }
        
    /**
    *
    * @return DispatchPtr
    **/
    public DispatchPtr getShapeObject() throws COMException
    {
         return (DispatchPtr)get("ShapeObject");
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
    public short getShapeID16() throws COMException
    {
        return ((Short)get("ShapeID16")).shortValue();
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
    * @return int
    **/
    public int getShapeID() throws COMException
    {
        return ((Integer)get("ShapeID")).intValue();
    }
        
    /**
    *
    * @return int
    **/
    public int getShapeIndex() throws COMException
    {
        return ((Integer)get("ShapeIndex")).intValue();
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
    public IVShape ShapeDuplicate() throws COMException
    {
      IVShape res = new IVShape();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("ShapeDuplicate", new Object[] {});
          res.stealUnknown(dispPtr);
          return res;
        
    }
}

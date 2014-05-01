
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVSelection
 * Description: 
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVSelection extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d070b-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVSelection.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVSelection (it is required by Jawin for some internal working though).
	 */
	public IVSelection() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVSelection interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVSelection(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVSelection interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVSelection(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVSelection interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVSelection interface on.
	 */
	public IVSelection(COMPtr comObject) throws COMException {
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
    public IVShape getItem16(short Index) throws COMException
    {
        IVShape res = new IVShape();
          DispatchPtr dispPtr = (DispatchPtr)get("Item16", new Short(Index));
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return short
    **/
    public short getCount16() throws COMException
    {
        return ((Short)get("Count16")).shortValue();
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
    public void Union() throws COMException
    {
      
		invokeN("Union", new Object[] {});
        
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
    public void Rotate90() throws COMException
    {
      
		invokeN("Rotate90", new Object[] {});
        
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
    public void ConvertToGroup() throws COMException
    {
      
		invokeN("ConvertToGroup", new Object[] {});
        
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
    
    * @param SelectAction
    * @return void
    **/
    public void Select(Object[] SheetObject,short SelectAction) throws COMException
    {
      
		invokeN("Select", new Object[] {SheetObject, new Short(SelectAction)});
        
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
    * @return void
    **/
    public IVShape getItem(int Index) throws COMException
    {
        IVShape res = new IVShape();
          DispatchPtr dispPtr = (DispatchPtr)get("Item", new Integer(Index));
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return int
    **/
    public int getCount() throws COMException
    {
        return ((Integer)get("Count")).intValue();
    }
        
    /**
    *
    
    * @param Tolerance
    * @param Flags
    * @param x
    * @param y
    * @param ResultsMaster
    * @return void
    **/
    public IVShape DrawRegion(double Tolerance,short Flags,Variant x,Variant y,Variant ResultsMaster) throws COMException
    {
      IVShape res = new IVShape();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("DrawRegion", new Object[] {new Double(Tolerance), new Short(Flags), x, y, ResultsMaster});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    * @return IUnknown
    **/
    public IUnknown get_NewEnum() throws COMException
    {
         return (IUnknown)get("_NewEnum");
    }
        
    /**
    *
    * @return int
    **/
    public int getIterationMode() throws COMException
    {
        return ((Integer)get("IterationMode")).intValue();
    }
        
    /**
    *
    * @param IterationMode
    **/
    public void setIterationMode(int IterationMode) throws COMException
    {
        put("IterationMode", IterationMode);
    }
        
    /**
    *
    * @return short
    **/
    public short getItemStatus(int Index) throws COMException
    {
        return ((Short)get("ItemStatus", new Integer(Index))).shortValue();
    }
        
    /**
    *
    * @return void
    **/
    public IVShape getPrimaryItem() throws COMException
    {
        IVShape res = new IVShape();
          DispatchPtr dispPtr = (DispatchPtr)get("PrimaryItem");
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
    public void SwapEnds() throws COMException
    {
      
		invokeN("SwapEnds", new Object[] {});
        
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
    
    * @return void
    **/
    public IVSelection Duplicate() throws COMException
    {
      IVSelection res = new IVSelection();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("Duplicate", new Object[] {});
          res.stealUnknown(dispPtr);
          return res;
        
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
    
    * @param dx
    * @param dy
    * @param UnitsNameOrCode
    * @return void
    **/
    public void Move(double dx,double dy,Variant UnitsNameOrCode) throws COMException
    {
      
		invokeN("Move", new Object[] {new Double(dx), new Double(dy), UnitsNameOrCode});
        
    }
    /**
    *
    
    * @param Angle
    * @param AngleUnitsNameOrCode
    * @param BlastGuards
    * @param RotationType
    * @param PinX
    * @param PinY
    * @param PinUnitsNameOrCode
    * @return void
    **/
    public void Rotate(double Angle,Variant AngleUnitsNameOrCode,boolean BlastGuards,int RotationType,double PinX,double PinY,Variant PinUnitsNameOrCode) throws COMException
    {
      
		invokeN("Rotate", new Object[] {new Double(Angle), AngleUnitsNameOrCode, new Boolean(BlastGuards), new Integer(RotationType), new Double(PinX), new Double(PinY), PinUnitsNameOrCode});
        
    }
    /**
    *
    
    * @param AlignHorizontal
    * @param AlignVertical
    * @param GlueToGuide
    * @return void
    **/
    public void Align(int AlignHorizontal,int AlignVertical,boolean GlueToGuide) throws COMException
    {
      
		invokeN("Align", new Object[] {new Integer(AlignHorizontal), new Integer(AlignVertical), new Boolean(GlueToGuide)});
        
    }
    /**
    *
    
    * @param Distribute
    * @param GlueToGuide
    * @return void
    **/
    public void Distribute(int Distribute,boolean GlueToGuide) throws COMException
    {
      
		invokeN("Distribute", new Object[] {new Integer(Distribute), new Boolean(GlueToGuide)});
        
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
    
    * @param Distance
    * @return void
    **/
    public void Offset(double Distance) throws COMException
    {
      
		invokeN("Offset", new Object[] {new Double(Distance)});
        
    }
    /**
    *
    
    * @return void
    **/
    public void ConnectShapes() throws COMException
    {
      
		invokeN("ConnectShapes", new Object[] {});
        
    }
    /**
    *
    
    * @param FlipDirection
    * @param FlipType
    * @param BlastGuards
    * @param PinX
    * @param PinY
    * @param PinUnitsNameOrCode
    * @return void
    **/
    public void Flip(int FlipDirection,int FlipType,boolean BlastGuards,double PinX,double PinY,Variant PinUnitsNameOrCode) throws COMException
    {
      
		invokeN("Flip", new Object[] {new Integer(FlipDirection), new Integer(FlipType), new Boolean(BlastGuards), new Double(PinX), new Double(PinY), PinUnitsNameOrCode});
        
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
    * @param DataRowID
    * @param ApplyDataGraphicAfterLink
    * @return void
    **/
    public void LinkToData(int DataRecordsetID,int DataRowID,boolean ApplyDataGraphicAfterLink) throws COMException
    {
      
		invokeN("LinkToData", new Object[] {new Integer(DataRecordsetID), new Integer(DataRowID), new Boolean(ApplyDataGraphicAfterLink)});
        
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
    
    * @param ShapeIDs
    * @return void
    **/
    public void GetIDs(Object[] ShapeIDs) throws COMException
    {
      
		invokeN("GetIDs", new Object[] {ShapeIDs});
        
    }
    /**
    *
    
    * @param DataRecordsetID
    * @param ColumnNames
    * @param AutoLinkFieldTypes
    * @param FieldNames
    * @param AutoLinkBehavior
    * @param ShapeIDs
    * @return int
    **/
    public int AutomaticLink(int DataRecordsetID,Object[] ColumnNames,Object[] AutoLinkFieldTypes,Object[] FieldNames,int AutoLinkBehavior,Object[] ShapeIDs) throws COMException
    {
      
		return ((Integer)invokeN("AutomaticLink", new Object[] {new Integer(DataRecordsetID), ColumnNames, AutoLinkFieldTypes, FieldNames, new Integer(AutoLinkBehavior), ShapeIDs})).intValue();
        
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
        
}

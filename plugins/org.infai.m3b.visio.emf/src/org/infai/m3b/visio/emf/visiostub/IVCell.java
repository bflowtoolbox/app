
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVCell
 * Description: 
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVCell extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0701-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVCell.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVCell (it is required by Jawin for some internal working though).
	 */
	public IVCell() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVCell interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVCell(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVCell interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVCell(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVCell interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVCell interface on.
	 */
	public IVCell(COMPtr comObject) throws COMException {
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
    public short getObjectType() throws COMException
    {
        return ((Short)get("ObjectType")).shortValue();
    }
        
    /**
    *
    * @return short
    **/
    public short getError() throws COMException
    {
        return ((Short)get("Error")).shortValue();
    }
        
    /**
    *
    * @return String
    **/
    public String getFormula() throws COMException
    {
         return (String)get("Formula");
    }
        
    /**
    *
    * @param Formula
    **/
    public void setFormula(String Formula) throws COMException
    {
        put("Formula", Formula);
    }
        
    /**
    *
    * @param FormulaForce
    **/
    public void setFormulaForce(String FormulaForce) throws COMException
    {
        put("FormulaForce", FormulaForce);
    }
        
    /**
    *
    
    * @return void
    **/
    public void GlueTo(Object[] CellObject) throws COMException
    {
      
		invokeN("GlueTo", new Object[] {CellObject});
        
    }
    /**
    *
    
    * @param xPercent
    * @param yPercent
    * @return void
    **/
    public void GlueToPos(Object[] SheetObject,double xPercent,double yPercent) throws COMException
    {
      
		invokeN("GlueToPos", new Object[] {SheetObject, new Double(xPercent), new Double(yPercent)});
        
    }
    /**
    *
    * @return double
    **/
    public double getResult(Variant UnitsNameOrCode) throws COMException
    {
         return ((Double)get("Result", UnitsNameOrCode)).doubleValue();
    }
    
    
    /**
    *
    * @param Result
    **/
    /*
    public void setResult(Variant UnitsNameOrCode,double newValue2) throws COMException
    {
        put("Result", Result, newValue2);
    }
    */
    
    /**
    *
    * @param ResultForce
    **/
    /*
    public void setResultForce(Variant UnitsNameOrCode,double newValue2) throws COMException
    {
        put("ResultForce", ResultForce, newValue2);
    }
    */
        
    /**
    *
    * @return double
    **/
    public double getResultIU() throws COMException
    {
         return ((Double)get("ResultIU")).doubleValue();
    }
        
    /**
    *
    * @param ResultIU
    **/
    public void setResultIU(double ResultIU) throws COMException
    {
        put("ResultIU", ResultIU);
    }
        
    /**
    *
    * @param ResultIUForce
    **/
    public void setResultIUForce(double ResultIUForce) throws COMException
    {
        put("ResultIUForce", ResultIUForce);
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
    public short getUnits() throws COMException
    {
        return ((Short)get("Units")).shortValue();
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
    public String getLocalName() throws COMException
    {
         return (String)get("LocalName");
    }
        
    /**
    *
    * @param RowName
    **/
    public void setRowName(String RowName) throws COMException
    {
        put("RowName", RowName);
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
    public IVStyle getStyle() throws COMException
    {
        IVStyle res = new IVStyle();
          DispatchPtr dispPtr = (DispatchPtr)get("Style");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return short
    **/
    public short getSection() throws COMException
    {
        return ((Short)get("Section")).shortValue();
    }
        
    /**
    *
    * @return short
    **/
    public short getRow() throws COMException
    {
        return ((Short)get("Row")).shortValue();
    }
        
    /**
    *
    * @return short
    **/
    public short getColumn() throws COMException
    {
        return ((Short)get("Column")).shortValue();
    }
        
    /**
    *
    * @return short
    **/
    public short getIsConstant() throws COMException
    {
        return ((Short)get("IsConstant")).shortValue();
    }
        
    /**
    *
    * @return short
    **/
    public short getIsInherited() throws COMException
    {
        return ((Short)get("IsInherited")).shortValue();
    }
        
    /**
    *
    * @return int
    **/
    public int getResultInt(Variant UnitsNameOrCode,short fRound) throws COMException
    {
        return ((Integer)getN("ResultInt", new Object[] {UnitsNameOrCode,new Short(fRound)})).intValue();
    }
        
    /**
    *
    * @param ResultFromInt
    **/
    /*
    public void setResultFromInt(Variant UnitsNameOrCode,int newValue2) throws COMException
    {
        put("ResultFromInt", ResultFromInt, newValue2);
    }
    */
    
    /**
    *
    * @param ResultFromIntForce
    **/
    /*
    public void setResultFromIntForce(Variant UnitsNameOrCode,int newValue2) throws COMException
    {
        put("ResultFromIntForce", ResultFromIntForce, newValue2);
    }
    */
    
    /**
    *
    * @return String
    **/
    public String getResultStr(Variant UnitsNameOrCode) throws COMException
    {
         return (String)get("ResultStr", UnitsNameOrCode);
    }
        
    /**
    *
    
    * @return void
    **/
    public void Trigger() throws COMException
    {
      
		invokeN("Trigger", new Object[] {});
        
    }
    /**
    *
    * @return String
    **/
    public String getRowName() throws COMException
    {
         return (String)get("RowName");
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
    public IVRow getContainingRow() throws COMException
    {
        IVRow res = new IVRow();
          DispatchPtr dispPtr = (DispatchPtr)get("ContainingRow");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return String
    **/
    public String getFormulaU() throws COMException
    {
         return (String)get("FormulaU");
    }
        
    /**
    *
    * @param FormulaU
    **/
    public void setFormulaU(String FormulaU) throws COMException
    {
        put("FormulaU", FormulaU);
    }
        
    /**
    *
    * @param FormulaForceU
    **/
    public void setFormulaForceU(String FormulaForceU) throws COMException
    {
        put("FormulaForceU", FormulaForceU);
    }
        
    /**
    *
    * @return String
    **/
    public String getRowNameU() throws COMException
    {
         return (String)get("RowNameU");
    }
        
    /**
    *
    * @param RowNameU
    **/
    public void setRowNameU(String RowNameU) throws COMException
    {
        put("RowNameU", RowNameU);
    }
        
    /**
    *
    * @return void
    **/
    public IVCell getInheritedValueSource() throws COMException
    {
        IVCell res = new IVCell();
          DispatchPtr dispPtr = (DispatchPtr)get("InheritedValueSource");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return void
    **/
    public IVCell getInheritedFormulaSource() throws COMException
    {
        IVCell res = new IVCell();
          DispatchPtr dispPtr = (DispatchPtr)get("InheritedFormulaSource");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return Object[]
    **/
    public Object[] getDependents() throws COMException
    {
        return null; // not implementedget("Dependents");
    }
        
    /**
    *
    * @return Object[]
    **/
    public Object[] getPrecedents() throws COMException
    {
        return null; // not implementedget("Precedents");
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
    * @return String
    **/
    public String getResultStrU(Variant UnitsNameOrCode) throws COMException
    {
         return (String)get("ResultStrU", UnitsNameOrCode);
    }
        
}

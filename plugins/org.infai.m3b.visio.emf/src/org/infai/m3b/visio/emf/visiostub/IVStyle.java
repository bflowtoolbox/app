
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVStyle
 * Description: 
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVStyle extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d070e-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVStyle.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVStyle (it is required by Jawin for some internal working though).
	 */
	public IVStyle() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVStyle interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVStyle(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVStyle interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVStyle(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVStyle interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVStyle interface on.
	 */
	public IVStyle(COMPtr comObject) throws COMException {
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
    * @return short
    **/
    public short getIndex16() throws COMException
    {
        return ((Short)get("Index16")).shortValue();
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
    * @return String
    **/
    public String getBasedOn() throws COMException
    {
         return (String)get("BasedOn");
    }
        
    /**
    *
    * @param BasedOn
    **/
    public void setBasedOn(String BasedOn) throws COMException
    {
        put("BasedOn", BasedOn);
    }
        
    /**
    *
    * @return String
    **/
    public String getTextBasedOn() throws COMException
    {
         return (String)get("TextBasedOn");
    }
        
    /**
    *
    * @param TextBasedOn
    **/
    public void setTextBasedOn(String TextBasedOn) throws COMException
    {
        put("TextBasedOn", TextBasedOn);
    }
        
    /**
    *
    * @return String
    **/
    public String getLineBasedOn() throws COMException
    {
         return (String)get("LineBasedOn");
    }
        
    /**
    *
    * @param LineBasedOn
    **/
    public void setLineBasedOn(String LineBasedOn) throws COMException
    {
        put("LineBasedOn", LineBasedOn);
    }
        
    /**
    *
    * @return String
    **/
    public String getFillBasedOn() throws COMException
    {
         return (String)get("FillBasedOn");
    }
        
    /**
    *
    * @param FillBasedOn
    **/
    public void setFillBasedOn(String FillBasedOn) throws COMException
    {
        put("FillBasedOn", FillBasedOn);
    }
        
    /**
    *
    * @return short
    **/
    public short getIncludesText() throws COMException
    {
        return ((Short)get("IncludesText")).shortValue();
    }
        
    /**
    *
    * @param IncludesText
    **/
    public void setIncludesText(short IncludesText) throws COMException
    {
        put("IncludesText", IncludesText);
    }
        
    /**
    *
    * @return short
    **/
    public short getIncludesLine() throws COMException
    {
        return ((Short)get("IncludesLine")).shortValue();
    }
        
    /**
    *
    * @param IncludesLine
    **/
    public void setIncludesLine(short IncludesLine) throws COMException
    {
        put("IncludesLine", IncludesLine);
    }
        
    /**
    *
    * @return short
    **/
    public short getIncludesFill() throws COMException
    {
        return ((Short)get("IncludesFill")).shortValue();
    }
        
    /**
    *
    * @param IncludesFill
    **/
    public void setIncludesFill(short IncludesFill) throws COMException
    {
        put("IncludesFill", IncludesFill);
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
    public void Delete() throws COMException
    {
      
		invokeN("Delete", new Object[] {});
        
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
    public short getID16() throws COMException
    {
        return ((Short)get("ID16")).shortValue();
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
    public short getCellExistsU(String localeIndependentCellName,short fExistsLocally) throws COMException
    {
        return ((Short)getN("CellExistsU", new Object[] {localeIndependentCellName,new Short(fExistsLocally)})).shortValue();
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
    * @return short
    **/
    public short getCellsSRCExists(short Section,short Row,short Column,short fExistsLocally) throws COMException
    {
        return ((Short)getN("CellsSRCExists", new Object[] {new Short(Section),new Short(Row),new Short(Column),new Short(fExistsLocally)})).shortValue();
    }
        
}

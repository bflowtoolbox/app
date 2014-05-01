
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVCharacters
 * Description: 
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVCharacters extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0702-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVCharacters.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVCharacters (it is required by Jawin for some internal working though).
	 */
	public IVCharacters() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVCharacters interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVCharacters(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVCharacters interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVCharacters(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVCharacters interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVCharacters interface on.
	 */
	public IVCharacters(COMPtr comObject) throws COMException {
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
    
    * @param Formula
    * @param Format
    * @return void
    **/
    public void AddCustomField(String Formula,short Format) throws COMException
    {
      
		invokeN("AddCustomField", new Object[] {Formula, new Short(Format)});
        
    }
    /**
    *
    
    * @param Category
    * @param Code
    * @param Format
    * @return void
    **/
    public void AddField(short Category,short Code,short Format) throws COMException
    {
      
		invokeN("AddField", new Object[] {new Short(Category), new Short(Code), new Short(Format)});
        
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
    * @return int
    **/
    public int getBegin() throws COMException
    {
        return ((Integer)get("Begin")).intValue();
    }
        
    /**
    *
    * @param Begin
    **/
    public void setBegin(int Begin) throws COMException
    {
        put("Begin", Begin);
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
    * @param CharProps
    **/
    public void setCharProps(short CellIndex,short newValue2) throws COMException
    {
        put("CharProps", new Short(CellIndex), newValue2);
    }
        
    /**
    *
    * @return short
    **/
    public short getCharPropsRow(short BiasLorR) throws COMException
    {
        return ((Short)get("CharPropsRow", new Short(BiasLorR))).shortValue();
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
    public void Cut() throws COMException
    {
      
		invokeN("Cut", new Object[] {});
        
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
    public int getEnd() throws COMException
    {
        return ((Integer)get("End")).intValue();
    }
        
    /**
    *
    * @param End
    **/
    public void setEnd(int End) throws COMException
    {
        put("End", End);
    }
        
    /**
    *
    * @return short
    **/
    public short getFieldCategory() throws COMException
    {
        return ((Short)get("FieldCategory")).shortValue();
    }
        
    /**
    *
    * @return short
    **/
    public short getFieldCode() throws COMException
    {
        return ((Short)get("FieldCode")).shortValue();
    }
        
    /**
    *
    * @return short
    **/
    public short getFieldFormat() throws COMException
    {
        return ((Short)get("FieldFormat")).shortValue();
    }
        
    /**
    *
    * @return String
    **/
    public String getFieldFormula() throws COMException
    {
         return (String)get("FieldFormula");
    }
        
    /**
    *
    * @return short
    **/
    public short getIsField() throws COMException
    {
        return ((Short)get("IsField")).shortValue();
    }
        
    /**
    *
    * @param ParaProps
    **/
    public void setParaProps(short CellIndex,short newValue2) throws COMException
    {
        put("ParaProps", new Short(CellIndex), newValue2);
    }
        
    /**
    *
    * @return short
    **/
    public short getParaPropsRow(short BiasLorR) throws COMException
    {
        return ((Short)get("ParaPropsRow", new Short(BiasLorR))).shortValue();
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
    * @return short
    **/
    public short getTabPropsRow(short BiasLorR) throws COMException
    {
        return ((Short)get("TabPropsRow", new Short(BiasLorR))).shortValue();
    }
        
    /**
    *
    * @return int
    **/
    public int getRunBegin(short RunType) throws COMException
    {
        return ((Integer)get("RunBegin", new Short(RunType))).intValue();
    }
        
    /**
    *
    * @return int
    **/
    public int getRunEnd(short RunType) throws COMException
    {
        return ((Integer)get("RunEnd", new Short(RunType))).intValue();
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
    * @return short
    **/
    public short getStat() throws COMException
    {
        return ((Short)get("Stat")).shortValue();
    }
        
    /**
    *
    * @return String
    **/
    public String getTextAsString() throws COMException
    {
         return (String)get("TextAsString");
    }
        
    /**
    *
    * @param Text
    **/
    public void setText(Variant Text) throws COMException
    {
        put("Text", Text);
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
    * @return Variant
    **/
    public Object getText() throws COMException
    {
         return get("Text");
    }
        
    /**
    *
    
    * @param Formula
    * @param Format
    * @return void
    **/
    public void AddCustomFieldU(String Formula,short Format) throws COMException
    {
      
		invokeN("AddCustomFieldU", new Object[] {Formula, new Short(Format)});
        
    }
    /**
    *
    * @return String
    **/
    public String getFieldFormulaU() throws COMException
    {
         return (String)get("FieldFormulaU");
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
    
    * @param Category
    * @param Code
    * @param Format
    * @param LangID
    * @param CalendarID
    * @return void
    **/
    public void AddFieldEx(int Category,int Code,int Format,int LangID,int CalendarID) throws COMException
    {
      
		invokeN("AddFieldEx", new Object[] {new Integer(Category), new Integer(Code), new Integer(Format), new Integer(LangID), new Integer(CalendarID)});
        
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
        
}

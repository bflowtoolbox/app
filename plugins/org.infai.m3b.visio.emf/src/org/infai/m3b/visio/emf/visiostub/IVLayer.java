
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVLayer
 * Description: 
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVLayer extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0712-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVLayer.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVLayer (it is required by Jawin for some internal working though).
	 */
	public IVLayer() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVLayer interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVLayer(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVLayer interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVLayer(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVLayer interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVLayer interface on.
	 */
	public IVLayer(COMPtr comObject) throws COMException {
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
    public IVPage getPage() throws COMException
    {
        IVPage res = new IVPage();
          DispatchPtr dispPtr = (DispatchPtr)get("Page");
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
    public short getRow() throws COMException
    {
        return ((Short)get("Row")).shortValue();
    }
        
    /**
    *
    
    * @param fPresMems
    * @return void
    **/
    public void Add(Object[] SheetObject,short fPresMems) throws COMException
    {
      
		invokeN("Add", new Object[] {SheetObject, new Short(fPresMems)});
        
    }
    /**
    *
    
    * @param fPresMems
    * @return void
    **/
    public void Remove(Object[] SheetObject,short fPresMems) throws COMException
    {
      
		invokeN("Remove", new Object[] {SheetObject, new Short(fPresMems)});
        
    }
    /**
    *
    
    * @param fDeleteShapes
    * @return void
    **/
    public void Delete(short fDeleteShapes) throws COMException
    {
      
		invokeN("Delete", new Object[] {new Short(fDeleteShapes)});
        
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
    public IVCell getCellsC(short Column) throws COMException
    {
        IVCell res = new IVCell();
          DispatchPtr dispPtr = (DispatchPtr)get("CellsC", new Short(Column));
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
    public short getStat() throws COMException
    {
        return ((Short)get("Stat")).shortValue();
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
        
}

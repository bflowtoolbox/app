
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVDocuments
 * Description: 
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVDocuments extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0706-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVDocuments.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVDocuments (it is required by Jawin for some internal working though).
	 */
	public IVDocuments() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVDocuments interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVDocuments(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVDocuments interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVDocuments(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVDocuments interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVDocuments interface on.
	 */
	public IVDocuments(COMPtr comObject) throws COMException {
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
    
    * @param FileName
    * @return void
    **/
    public IVDocument Add(String FileName) throws COMException
    {
      IVDocument res = new IVDocument();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("Add", new Object[] {FileName});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    
    * @param FileName
    * @return void
    **/
    public IVDocument Open(String FileName) throws COMException
    {
      IVDocument res = new IVDocument();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("Open", new Object[] {FileName});
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
    public short getObjectType() throws COMException
    {
        return ((Short)get("ObjectType")).shortValue();
    }
        
    /**
    *
    * @return void
    **/
    public IVDocument getItem(Variant NameOrIndex) throws COMException
    {
        IVDocument res = new IVDocument();
          DispatchPtr dispPtr = (DispatchPtr)get("Item", NameOrIndex);
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return short
    **/
    public short getCount() throws COMException
    {
        return ((Short)get("Count")).shortValue();
    }
        
    /**
    *
    
    * @param FileName
    * @param Flags
    * @return void
    **/
    public IVDocument OpenEx(String FileName,short Flags) throws COMException
    {
      IVDocument res = new IVDocument();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("OpenEx", new Object[] {FileName, new Short(Flags)});
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
    
    * @param NameArray
    * @return void
    **/
    public void GetNames(Object[] NameArray) throws COMException
    {
      
		invokeN("GetNames", new Object[] {NameArray});
        
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
    * @return void
    **/
    public IVDocument getItemFromID(int ObjectID) throws COMException
    {
        IVDocument res = new IVDocument();
          DispatchPtr dispPtr = (DispatchPtr)get("ItemFromID", new Integer(ObjectID));
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    
    * @param FileName
    * @return boolean
    **/
    public boolean CanCheckOut(String FileName) throws COMException
    {
      
		return ((Boolean)invokeN("CanCheckOut", new Object[] {FileName})).booleanValue();
        
    }
    /**
    *
    
    * @param FileName
    * @return void
    **/
    public void CheckOut(String FileName) throws COMException
    {
      
		invokeN("CheckOut", new Object[] {FileName});
        
    }
    /**
    *
    
    * @param FileName
    * @param MeasurementSystem
    * @param Flags
    * @param LangID
    * @return void
    **/
    public IVDocument AddEx(String FileName,int MeasurementSystem,int Flags,int LangID) throws COMException
    {
      IVDocument res = new IVDocument();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("AddEx", new Object[] {FileName, new Integer(MeasurementSystem), new Integer(Flags), new Integer(LangID)});
          res.stealUnknown(dispPtr);
          return res;
        
    }
}

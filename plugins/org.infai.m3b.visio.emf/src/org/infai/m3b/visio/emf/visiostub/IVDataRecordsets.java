
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVDataRecordsets
 * Description: 
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVDataRecordsets extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d072e-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVDataRecordsets.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVDataRecordsets (it is required by Jawin for some internal working though).
	 */
	public IVDataRecordsets() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVDataRecordsets interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVDataRecordsets(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVDataRecordsets interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVDataRecordsets(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVDataRecordsets interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVDataRecordsets interface on.
	 */
	public IVDataRecordsets(COMPtr comObject) throws COMException {
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
    public int getCount() throws COMException
    {
        return ((Integer)get("Count")).intValue();
    }
        
    /**
    *
    * @return void
    **/
    public IVDataRecordset getItem(int Index) throws COMException
    {
        IVDataRecordset res = new IVDataRecordset();
          DispatchPtr dispPtr = (DispatchPtr)get("Item", new Integer(Index));
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return void
    **/
    public IVDataRecordset getItemFromID(int ID) throws COMException
    {
        IVDataRecordset res = new IVDataRecordset();
          DispatchPtr dispPtr = (DispatchPtr)get("ItemFromID", new Integer(ID));
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
    
    * @param ConnectionIDOrString
    * @param CommandString
    * @param AddOptions
    * @param Name
    * @return void
    **/
    public IVDataRecordset Add(Variant ConnectionIDOrString,String CommandString,int AddOptions,String Name) throws COMException
    {
      IVDataRecordset res = new IVDataRecordset();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("Add", new Object[] {ConnectionIDOrString, CommandString, new Integer(AddOptions), Name});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    
    * @param XMLString
    * @param AddOptions
    * @param Name
    * @return void
    **/
    public IVDataRecordset AddFromXML(String XMLString,int AddOptions,String Name) throws COMException
    {
      IVDataRecordset res = new IVDataRecordset();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("AddFromXML", new Object[] {XMLString, new Integer(AddOptions), Name});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    
    * @param FileName
    * @param AddOptions
    * @param Name
    * @return void
    **/
    public IVDataRecordset AddFromConnectionFile(String FileName,int AddOptions,String Name) throws COMException
    {
      IVDataRecordset res = new IVDataRecordset();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("AddFromConnectionFile", new Object[] {FileName, new Integer(AddOptions), Name});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    
    * @return void
    **/
    public void GetLastDataError(int[] DataErrorCode,String[] DataErrorDescription,int[] RecordsetID) throws COMException
    {
      
		invokeN("GetLastDataError", new Object[] {DataErrorCode, DataErrorDescription, RecordsetID});
        
    }
}

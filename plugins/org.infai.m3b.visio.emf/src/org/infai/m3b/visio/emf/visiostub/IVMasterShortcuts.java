
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;



/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVMasterShortcuts
 * Description: 
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVMasterShortcuts extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0726-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVMasterShortcuts.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVMasterShortcuts (it is required by Jawin for some internal working though).
	 */
	public IVMasterShortcuts() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVMasterShortcuts interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVMasterShortcuts(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVMasterShortcuts interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVMasterShortcuts(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVMasterShortcuts interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVMasterShortcuts interface on.
	 */
	public IVMasterShortcuts(COMPtr comObject) throws COMException {
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
    public IVMasterShortcut getItem(Variant NameOrIndex) throws COMException
    {
        IVMasterShortcut res = new IVMasterShortcut();
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
    
    * @param localeSpecificNameArray
    * @return void
    **/
    public void GetNames(Object[] localeSpecificNameArray) throws COMException
    {
      
		invokeN("GetNames", new Object[] {localeSpecificNameArray});
        
    }
    /**
    *
    
    * @param ObjectToDrop
    * @param xPos
    * @param yPos
    * @return void
    **/
    public IVMasterShortcut Drop(IUnknown ObjectToDrop,short xPos,short yPos) throws COMException
    {
      IVMasterShortcut res = new IVMasterShortcut();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("Drop", new Object[] {ObjectToDrop, new Short(xPos), new Short(yPos)});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    * @return void
    **/
    public IVMasterShortcut getItemU(Variant NameOrIndex) throws COMException
    {
        IVMasterShortcut res = new IVMasterShortcut();
          DispatchPtr dispPtr = (DispatchPtr)get("ItemU", NameOrIndex);
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    
    * @param localeIndependentNameArray
    * @return void
    **/
    public void GetNamesU(Object[] localeIndependentNameArray) throws COMException
    {
      
		invokeN("GetNamesU", new Object[] {localeIndependentNameArray});
        
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
    public IVMasterShortcut getItemFromID(int nID) throws COMException
    {
        IVMasterShortcut res = new IVMasterShortcut();
          DispatchPtr dispPtr = (DispatchPtr)get("ItemFromID", new Integer(nID));
          res.stealUnknown(dispPtr);
          return res;
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
}

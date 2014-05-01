
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVStatusBar
 * Description: 
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVStatusBar extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0282-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVStatusBar.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVStatusBar (it is required by Jawin for some internal working though).
	 */
	public IVStatusBar() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVStatusBar interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVStatusBar(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVStatusBar interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVStatusBar(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVStatusBar interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVStatusBar interface on.
	 */
	public IVStatusBar(COMPtr comObject) throws COMException {
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
    public void Delete() throws COMException
    {
      
		invokeN("Delete", new Object[] {});
        
    }
    /**
    *
    * @return String
    **/
    public String getDefault() throws COMException
    {
         return (String)get("Default");
    }
        
    /**
    *
    * @param Caption
    **/
    public void setCaption(String Caption) throws COMException
    {
        put("Caption", Caption);
    }
        
    /**
    *
    * @return String
    **/
    public String getCaption() throws COMException
    {
         return (String)get("Caption");
    }
        
    /**
    *
    * @return int
    **/
    public int getSetID() throws COMException
    {
        return ((Integer)get("SetID")).intValue();
    }
        
    /**
    *
    * @return void
    **/
    public IVStatusBarItems getStatusBarItems() throws COMException
    {
        IVStatusBarItems res = new IVStatusBarItems();
          DispatchPtr dispPtr = (DispatchPtr)get("StatusBarItems");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return void
    **/
    public IVStatusBars getParent() throws COMException
    {
        IVStatusBars res = new IVStatusBars();
          DispatchPtr dispPtr = (DispatchPtr)get("Parent");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getBuiltIn() throws COMException
    {
        return ((Boolean)get("BuiltIn")).booleanValue();
    }
        
    /**
    *
    * @param Protection
    **/
    public void setProtection(short Protection) throws COMException
    {
        put("Protection", Protection);
    }
        
    /**
    *
    * @return short
    **/
    public short getProtection() throws COMException
    {
        return ((Short)get("Protection")).shortValue();
    }
        
}

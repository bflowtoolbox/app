
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVMSGWrap
 * Description: 
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVMSGWrap extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0729-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVMSGWrap.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVMSGWrap (it is required by Jawin for some internal working though).
	 */
	public IVMSGWrap() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVMSGWrap interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVMSGWrap(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVMSGWrap interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVMSGWrap(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVMSGWrap interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVMSGWrap interface on.
	 */
	public IVMSGWrap(COMPtr comObject) throws COMException {
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
    * @return int
    **/
    public int gethwnd() throws COMException
    {
        return ((Integer)get("hwnd")).intValue();
    }
        
    /**
    *
    * @param hwnd
    **/
    public void sethwnd(int hwnd) throws COMException
    {
        put("hwnd", hwnd);
    }
        
    /**
    *
    * @return int
    **/
    public int getmessage() throws COMException
    {
        return ((Integer)get("message")).intValue();
    }
        
    /**
    *
    * @param message
    **/
    public void setmessage(int message) throws COMException
    {
        put("message", message);
    }
        
    /**
    *
    * @return int
    **/
    public int getwParam() throws COMException
    {
        return ((Integer)get("wParam")).intValue();
    }
        
    /**
    *
    * @param wParam
    **/
    public void setwParam(int wParam) throws COMException
    {
        put("wParam", wParam);
    }
        
    /**
    *
    * @return int
    **/
    public int getlParam() throws COMException
    {
        return ((Integer)get("lParam")).intValue();
    }
        
    /**
    *
    * @param lParam
    **/
    public void setlParam(int lParam) throws COMException
    {
        put("lParam", lParam);
    }
        
    /**
    *
    * @return int
    **/
    public int getposttime() throws COMException
    {
        return ((Integer)get("posttime")).intValue();
    }
        
    /**
    *
    * @param posttime
    **/
    public void setposttime(int posttime) throws COMException
    {
        put("posttime", posttime);
    }
        
    /**
    *
    * @return int
    **/
    public int getptx() throws COMException
    {
        return ((Integer)get("ptx")).intValue();
    }
        
    /**
    *
    * @param ptx
    **/
    public void setptx(int ptx) throws COMException
    {
        put("ptx", ptx);
    }
        
    /**
    *
    * @return int
    **/
    public int getpty() throws COMException
    {
        return ((Integer)get("pty")).intValue();
    }
        
    /**
    *
    * @param pty
    **/
    public void setpty(int pty) throws COMException
    {
        put("pty", pty);
    }
        
}

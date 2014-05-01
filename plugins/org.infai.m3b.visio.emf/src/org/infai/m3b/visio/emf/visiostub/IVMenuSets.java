
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVMenuSets
 * Description: 
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVMenuSets extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0236-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVMenuSets.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVMenuSets (it is required by Jawin for some internal working though).
	 */
	public IVMenuSets() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVMenuSets interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVMenuSets(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVMenuSets interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVMenuSets(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVMenuSets interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVMenuSets interface on.
	 */
	public IVMenuSets(COMPtr comObject) throws COMException {
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
    public IVMenuSet getItem(int lIndex) throws COMException
    {
        IVMenuSet res = new IVMenuSet();
          DispatchPtr dispPtr = (DispatchPtr)get("Item", new Integer(lIndex));
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return void
    **/
    public IVMenuSet getItemAtID(int lID) throws COMException
    {
        IVMenuSet res = new IVMenuSet();
          DispatchPtr dispPtr = (DispatchPtr)get("ItemAtID", new Integer(lID));
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    
    * @return void
    **/
    public IVMenuSet Add() throws COMException
    {
      IVMenuSet res = new IVMenuSet();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("Add", new Object[] {});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    
    * @param lID
    * @return void
    **/
    public IVMenuSet AddAtID(int lID) throws COMException
    {
      IVMenuSet res = new IVMenuSet();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("AddAtID", new Object[] {new Integer(lID)});
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
    * @return void
    **/
    public IVUIObject getParent() throws COMException
    {
        IVUIObject res = new IVUIObject();
          DispatchPtr dispPtr = (DispatchPtr)get("Parent");
          res.stealUnknown(dispPtr);
          return res;
    }
        
}

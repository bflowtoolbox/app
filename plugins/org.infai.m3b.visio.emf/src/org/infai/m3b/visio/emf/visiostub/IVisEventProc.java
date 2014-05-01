
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVisEventProc
 * Description: VB-Implements-Capable Visio event sink interface definition. Use an object that implements this interface as the sink argument in AddAdvise calls.
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVisEventProc extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0728-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVisEventProc.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVisEventProc (it is required by Jawin for some internal working though).
	 */
	public IVisEventProc() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVisEventProc interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVisEventProc(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVisEventProc interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVisEventProc(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVisEventProc interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVisEventProc interface on.
	 */
	public IVisEventProc(COMPtr comObject) throws COMException {
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
    
    * @param nEventCode
    * @param pSourceObj
    * @param nEventID
    * @param nEventSeqNum
    * @param pSubjectObj
    * @param vMoreInfo
    * @return Variant
    **/
    public Object VisEventProc(short nEventCode,DispatchPtr pSourceObj,int nEventID,int nEventSeqNum,DispatchPtr pSubjectObj,Variant vMoreInfo) throws COMException
    {
      
		return invokeN("VisEventProc", new Object[] {new Short(nEventCode), pSourceObj, new Integer(nEventID), new Integer(nEventSeqNum), pSubjectObj, vMoreInfo});
        
    }
}

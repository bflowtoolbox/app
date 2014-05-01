
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVWindows
 * Description: 
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVWindows extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0711-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVWindows.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVWindows (it is required by Jawin for some internal working though).
	 */
	public IVWindows() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVWindows interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVWindows(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVWindows interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVWindows(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVWindows interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVWindows interface on.
	 */
	public IVWindows(COMPtr comObject) throws COMException {
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
    * @return void
    **/
    public IVWindow getItem(short Index) throws COMException
    {
        IVWindow res = new IVWindow();
          DispatchPtr dispPtr = (DispatchPtr)get("Item", new Short(Index));
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
    
    * @return void
    **/
    public void VoidArrange() throws COMException
    {
      
		invokeN("VoidArrange", new Object[] {});
        
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
    
    * @param bstrCaption
    * @param nFlags
    * @param nType
    * @param nLeft
    * @param nTop
    * @param nWidth
    * @param nHeight
    * @return void
    **/
    public IVWindow Add_WithoutMergeArgs(Variant bstrCaption,Variant nFlags,Variant nType,Variant nLeft,Variant nTop,Variant nWidth,Variant nHeight) throws COMException
    {
      IVWindow res = new IVWindow();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("Add_WithoutMergeArgs", new Object[] {bstrCaption, nFlags, nType, nLeft, nTop, nWidth, nHeight});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    
    * @param nArrangeFlags
    * @return void
    **/
    public void Arrange(Variant nArrangeFlags) throws COMException
    {
      
		invokeN("Arrange", new Object[] {nArrangeFlags});
        
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
    public IVWindow getItemFromID(int nID) throws COMException
    {
        IVWindow res = new IVWindow();
          DispatchPtr dispPtr = (DispatchPtr)get("ItemFromID", new Integer(nID));
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    
    * @param bstrCaption
    * @param nFlags
    * @param nType
    * @param nLeft
    * @param nTop
    * @param nWidth
    * @param nHeight
    * @param bstrMergeID
    * @param bstrMergeClass
    * @param nMergePosition
    * @return void
    **/
    public IVWindow Add(Variant bstrCaption,Variant nFlags,Variant nType,Variant nLeft,Variant nTop,Variant nWidth,Variant nHeight,Variant bstrMergeID,Variant bstrMergeClass,Variant nMergePosition) throws COMException
    {
      IVWindow res = new IVWindow();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("Add", new Object[] {bstrCaption, nFlags, nType, nLeft, nTop, nWidth, nHeight, bstrMergeID, bstrMergeClass, nMergePosition});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    * @return void
    **/
    public IVWindow getItemEx(Variant CaptionOrIndex) throws COMException
    {
        IVWindow res = new IVWindow();
          DispatchPtr dispPtr = (DispatchPtr)get("ItemEx", CaptionOrIndex);
          res.stealUnknown(dispPtr);
          return res;
    }
        
}

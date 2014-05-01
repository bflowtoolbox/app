
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVAccelItem
 * Description: 
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVAccelItem extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0292-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVAccelItem.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVAccelItem (it is required by Jawin for some internal working though).
	 */
	public IVAccelItem() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVAccelItem interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVAccelItem(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVAccelItem interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVAccelItem(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVAccelItem interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVAccelItem interface on.
	 */
	public IVAccelItem(COMPtr comObject) throws COMException {
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
    * @return short
    **/
    public short getDefault() throws COMException
    {
        return ((Short)get("Default")).shortValue();
    }
        
    /**
    *
    * @param CmdNum
    **/
    public void setCmdNum(short CmdNum) throws COMException
    {
        put("CmdNum", CmdNum);
    }
        
    /**
    *
    * @return short
    **/
    public short getCmdNum() throws COMException
    {
        return ((Short)get("CmdNum")).shortValue();
    }
        
    /**
    *
    * @param Key
    **/
    public void setKey(short Key) throws COMException
    {
        put("Key", Key);
    }
        
    /**
    *
    * @return short
    **/
    public short getKey() throws COMException
    {
        return ((Short)get("Key")).shortValue();
    }
        
    /**
    *
    * @param Alt
    **/
    public void setAlt(short Alt) throws COMException
    {
        put("Alt", Alt);
    }
        
    /**
    *
    * @return short
    **/
    public short getAlt() throws COMException
    {
        return ((Short)get("Alt")).shortValue();
    }
        
    /**
    *
    * @param Shift
    **/
    public void setShift(short Shift) throws COMException
    {
        put("Shift", Shift);
    }
        
    /**
    *
    * @return short
    **/
    public short getShift() throws COMException
    {
        return ((Short)get("Shift")).shortValue();
    }
        
    /**
    *
    * @param Control
    **/
    public void setControl(short Control) throws COMException
    {
        put("Control", Control);
    }
        
    /**
    *
    * @return short
    **/
    public short getControl() throws COMException
    {
        return ((Short)get("Control")).shortValue();
    }
        
    /**
    *
    * @return void
    **/
    public IVAccelItems getParent() throws COMException
    {
        IVAccelItems res = new IVAccelItems();
          DispatchPtr dispPtr = (DispatchPtr)get("Parent");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @param AddOnName
    **/
    public void setAddOnName(String AddOnName) throws COMException
    {
        put("AddOnName", AddOnName);
    }
        
    /**
    *
    * @return String
    **/
    public String getAddOnName() throws COMException
    {
         return (String)get("AddOnName");
    }
        
    /**
    *
    * @param AddOnArgs
    **/
    public void setAddOnArgs(String AddOnArgs) throws COMException
    {
        put("AddOnArgs", AddOnArgs);
    }
        
    /**
    *
    * @return String
    **/
    public String getAddOnArgs() throws COMException
    {
         return (String)get("AddOnArgs");
    }
        
}

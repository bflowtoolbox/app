
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVDataColumn
 * Description: 
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVDataColumn extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0732-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVDataColumn.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVDataColumn (it is required by Jawin for some internal working though).
	 */
	public IVDataColumn() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVDataColumn interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVDataColumn(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVDataColumn interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVDataColumn(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVDataColumn interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVDataColumn interface on.
	 */
	public IVDataColumn(COMPtr comObject) throws COMException {
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
    * @return void
    **/
    public IVDataRecordset getDataRecordset() throws COMException
    {
        IVDataRecordset res = new IVDataRecordset();
          DispatchPtr dispPtr = (DispatchPtr)get("DataRecordset");
          res.stealUnknown(dispPtr);
          return res;
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
    * @return String
    **/
    public String getDisplayName() throws COMException
    {
         return (String)get("DisplayName");
    }
        
    /**
    *
    * @param DisplayName
    **/
    public void setDisplayName(String DisplayName) throws COMException
    {
        put("DisplayName", DisplayName);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getVisible() throws COMException
    {
        return ((Boolean)get("Visible")).booleanValue();
    }
        
    /**
    *
    * @param Visible
    **/
    public void setVisible(boolean Visible) throws COMException
    {
        put("Visible", Visible);
    }
        
    /**
    *
    
    * @param Property
    * @return Variant
    **/
    public Object GetProperty(int Property) throws COMException
    {
      
		return invokeN("GetProperty", new Object[] {new Integer(Property)});
        
    }
    /**
    *
    
    * @param Property
    * @param Value
    * @return void
    **/
    public void SetProperty(int Property,Variant Value) throws COMException
    {
      
		invokeN("SetProperty", new Object[] {new Integer(Property), Value});
        
    }
    /**
    *
    * @return boolean
    **/
    public boolean getHyperlink() throws COMException
    {
        return ((Boolean)get("Hyperlink")).booleanValue();
    }
        
    /**
    *
    * @param Hyperlink
    **/
    public void setHyperlink(boolean Hyperlink) throws COMException
    {
        put("Hyperlink", Hyperlink);
    }
        
}


package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVHyperlink
 * Description: 
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVHyperlink extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d071d-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVHyperlink.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVHyperlink (it is required by Jawin for some internal working though).
	 */
	public IVHyperlink() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVHyperlink interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVHyperlink(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVHyperlink interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVHyperlink(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVHyperlink interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVHyperlink interface on.
	 */
	public IVHyperlink(COMPtr comObject) throws COMException {
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
    * @return String
    **/
    public String getDescription() throws COMException
    {
         return (String)get("Description");
    }
        
    /**
    *
    * @param Description
    **/
    public void setDescription(String Description) throws COMException
    {
        put("Description", Description);
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
    * @return void
    **/
    public IVShape getShape() throws COMException
    {
        IVShape res = new IVShape();
          DispatchPtr dispPtr = (DispatchPtr)get("Shape");
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
    * @return String
    **/
    public String getAddress() throws COMException
    {
         return (String)get("Address");
    }
        
    /**
    *
    * @param Address
    **/
    public void setAddress(String Address) throws COMException
    {
        put("Address", Address);
    }
        
    /**
    *
    * @return String
    **/
    public String getSubAddress() throws COMException
    {
         return (String)get("SubAddress");
    }
        
    /**
    *
    * @param SubAddress
    **/
    public void setSubAddress(String SubAddress) throws COMException
    {
        put("SubAddress", SubAddress);
    }
        
    /**
    *
    * @return short
    **/
    public short getNewWindow() throws COMException
    {
        return ((Short)get("NewWindow")).shortValue();
    }
        
    /**
    *
    * @param NewWindow
    **/
    public void setNewWindow(short NewWindow) throws COMException
    {
        put("NewWindow", NewWindow);
    }
        
    /**
    *
    * @return String
    **/
    public String getExtraInfo() throws COMException
    {
         return (String)get("ExtraInfo");
    }
        
    /**
    *
    * @param ExtraInfo
    **/
    public void setExtraInfo(String ExtraInfo) throws COMException
    {
        put("ExtraInfo", ExtraInfo);
    }
        
    /**
    *
    * @return String
    **/
    public String getFrame() throws COMException
    {
         return (String)get("Frame");
    }
        
    /**
    *
    * @param Frame
    **/
    public void setFrame(String Frame) throws COMException
    {
        put("Frame", Frame);
    }
        
    /**
    *
    
    * @param FavoritesTitle
    * @return void
    **/
    public void AddToFavorites(Variant FavoritesTitle) throws COMException
    {
      
		invokeN("AddToFavorites", new Object[] {FavoritesTitle});
        
    }
    /**
    *
    
    * @return void
    **/
    public void Follow() throws COMException
    {
      
		invokeN("Follow", new Object[] {});
        
    }
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
    
    * @return void
    **/
    public void Copy() throws COMException
    {
      
		invokeN("Copy", new Object[] {});
        
    }
    /**
    *
    
    * @param CanonicalForm
    * @return String
    **/
    public String CreateURL(short CanonicalForm) throws COMException
    {
      
		return (String)invokeN("CreateURL", new Object[] {new Short(CanonicalForm)});
        
    }
    /**
    *
    * @return short
    **/
    public short getRow() throws COMException
    {
        return ((Short)get("Row")).shortValue();
    }
        
    /**
    *
    * @return short
    **/
    public short getIsDefaultLink() throws COMException
    {
        return ((Short)get("IsDefaultLink")).shortValue();
    }
        
    /**
    *
    * @param IsDefaultLink
    **/
    public void setIsDefaultLink(short IsDefaultLink) throws COMException
    {
        put("IsDefaultLink", IsDefaultLink);
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
    * @param Name
    **/
    public void setName(String Name) throws COMException
    {
        put("Name", Name);
    }
        
    /**
    *
    * @return String
    **/
    public String getNameU() throws COMException
    {
         return (String)get("NameU");
    }
        
    /**
    *
    * @param NameU
    **/
    public void setNameU(String NameU) throws COMException
    {
        put("NameU", NameU);
    }
        
}

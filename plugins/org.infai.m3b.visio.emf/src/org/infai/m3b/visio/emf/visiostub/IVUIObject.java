
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVUIObject
 * Description: 
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVUIObject extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0202-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVUIObject.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVUIObject (it is required by Jawin for some internal working though).
	 */
	public IVUIObject() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVUIObject interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVUIObject(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVUIObject interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVUIObject(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVUIObject interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVUIObject interface on.
	 */
	public IVUIObject(COMPtr comObject) throws COMException {
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
    public String getName() throws COMException
    {
         return (String)get("Name");
    }
        
    /**
    *
    * @param Flavor
    **/
    public void setFlavor(short Flavor) throws COMException
    {
        put("Flavor", Flavor);
    }
        
    /**
    *
    * @return short
    **/
    public short getFlavor() throws COMException
    {
        return ((Short)get("Flavor")).shortValue();
    }
        
    /**
    *
    * @return void
    **/
    public IVMenuSets getMenuSets() throws COMException
    {
        IVMenuSets res = new IVMenuSets();
          DispatchPtr dispPtr = (DispatchPtr)get("MenuSets");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return void
    **/
    public IVToolbarSets getToolbarSets() throws COMException
    {
        IVToolbarSets res = new IVToolbarSets();
          DispatchPtr dispPtr = (DispatchPtr)get("ToolbarSets");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return void
    **/
    public IVStatusBars getStatusBars() throws COMException
    {
        IVStatusBars res = new IVStatusBars();
          DispatchPtr dispPtr = (DispatchPtr)get("StatusBars");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return void
    **/
    public IVAccelTables getAccelTables() throws COMException
    {
        IVAccelTables res = new IVAccelTables();
          DispatchPtr dispPtr = (DispatchPtr)get("AccelTables");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    
    * @param FileName
    * @return void
    **/
    public void SaveToFile(String FileName) throws COMException
    {
      
		invokeN("SaveToFile", new Object[] {FileName});
        
    }
    /**
    *
    
    * @param FileName
    * @return void
    **/
    public void LoadFromFile(String FileName) throws COMException
    {
      
		invokeN("LoadFromFile", new Object[] {FileName});
        
    }
    /**
    *
    
    * @return void
    **/
    public void UpdateUI() throws COMException
    {
      
		invokeN("UpdateUI", new Object[] {});
        
    }
    /**
    *
    * @return boolean
    **/
    public boolean getDisplayKeysInTooltips() throws COMException
    {
        return ((Boolean)get("DisplayKeysInTooltips")).booleanValue();
    }
        
    /**
    *
    * @param DisplayKeysInTooltips
    **/
    public void setDisplayKeysInTooltips(boolean DisplayKeysInTooltips) throws COMException
    {
        put("DisplayKeysInTooltips", DisplayKeysInTooltips);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getDisplayTooltips() throws COMException
    {
        return ((Boolean)get("DisplayTooltips")).booleanValue();
    }
        
    /**
    *
    * @param DisplayTooltips
    **/
    public void setDisplayTooltips(boolean DisplayTooltips) throws COMException
    {
        put("DisplayTooltips", DisplayTooltips);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getLargeButtons() throws COMException
    {
        return ((Boolean)get("LargeButtons")).booleanValue();
    }
        
    /**
    *
    * @param LargeButtons
    **/
    public void setLargeButtons(boolean LargeButtons) throws COMException
    {
        put("LargeButtons", LargeButtons);
    }
        
    /**
    *
    * @return short
    **/
    public short getMenuAnimationStyle() throws COMException
    {
        return ((Short)get("MenuAnimationStyle")).shortValue();
    }
        
    /**
    *
    * @param MenuAnimationStyle
    **/
    public void setMenuAnimationStyle(short MenuAnimationStyle) throws COMException
    {
        put("MenuAnimationStyle", MenuAnimationStyle);
    }
        
    /**
    *
    * @return void
    **/
    public IVUIObject getClone() throws COMException
    {
        IVUIObject res = new IVUIObject();
          DispatchPtr dispPtr = (DispatchPtr)get("Clone");
          res.stealUnknown(dispPtr);
          return res;
    }
        
}

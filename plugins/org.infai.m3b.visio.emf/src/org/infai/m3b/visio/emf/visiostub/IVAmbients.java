
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVAmbients
 * Description: Ambient properties a Visio control site provides to a control contained in a Visio Document.
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVAmbients extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0d10-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVAmbients.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVAmbients (it is required by Jawin for some internal working though).
	 */
	public IVAmbients() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVAmbients interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVAmbients(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVAmbients interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVAmbients(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVAmbients interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVAmbients interface on.
	 */
	public IVAmbients(COMPtr comObject) throws COMException {
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
    * @return int
    **/
    public int getBackColor() throws COMException
    {
        return ((Integer)get("BackColor")).intValue();
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
    * @return DispatchPtr
    **/
    public DispatchPtr getFont() throws COMException
    {
         return (DispatchPtr)get("Font");
    }
        
    /**
    *
    * @return int
    **/
    public int getForeColor() throws COMException
    {
        return ((Integer)get("ForeColor")).intValue();
    }
        
    /**
    *
    * @return int
    **/
    public int getLocaleID() throws COMException
    {
        return ((Integer)get("LocaleID")).intValue();
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getMessageReflect() throws COMException
    {
        return ((Boolean)get("MessageReflect")).booleanValue();
    }
        
    /**
    *
    * @return String
    **/
    public String getScaleUnits() throws COMException
    {
         return (String)get("ScaleUnits");
    }
        
    /**
    *
    * @return short
    **/
    public short getTextAlign() throws COMException
    {
        return ((Short)get("TextAlign")).shortValue();
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getUserMode() throws COMException
    {
        return ((Boolean)get("UserMode")).booleanValue();
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getUIDead() throws COMException
    {
        return ((Boolean)get("UIDead")).booleanValue();
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getShowGrabHandles() throws COMException
    {
        return ((Boolean)get("ShowGrabHandles")).booleanValue();
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getShowHatching() throws COMException
    {
        return ((Boolean)get("ShowHatching")).booleanValue();
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getDisplayAsDefault() throws COMException
    {
        return ((Boolean)get("DisplayAsDefault")).booleanValue();
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getSupportsMnemonics() throws COMException
    {
        return ((Boolean)get("SupportsMnemonics")).booleanValue();
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getAutoClip() throws COMException
    {
        return ((Boolean)get("AutoClip")).booleanValue();
    }
        
    /**
    *
    * @return int
    **/
    public int getAppearance() throws COMException
    {
        return ((Integer)get("Appearance")).intValue();
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
        
}

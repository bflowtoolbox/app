
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVColor
 * Description: 
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVColor extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0716-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVColor.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVColor (it is required by Jawin for some internal working though).
	 */
	public IVColor() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVColor interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVColor(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVColor interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVColor(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVColor interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVColor interface on.
	 */
	public IVColor(COMPtr comObject) throws COMException {
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
    public short getIndex16() throws COMException
    {
        return ((Short)get("Index16")).shortValue();
    }
        
    /**
    *
    * @return short
    **/
    public short getRed() throws COMException
    {
        return ((Short)get("Red")).shortValue();
    }
        
    /**
    *
    * @param Red
    **/
    public void setRed(short Red) throws COMException
    {
        put("Red", Red);
    }
        
    /**
    *
    * @return short
    **/
    public short getGreen() throws COMException
    {
        return ((Short)get("Green")).shortValue();
    }
        
    /**
    *
    * @param Green
    **/
    public void setGreen(short Green) throws COMException
    {
        put("Green", Green);
    }
        
    /**
    *
    * @return short
    **/
    public short getBlue() throws COMException
    {
        return ((Short)get("Blue")).shortValue();
    }
        
    /**
    *
    * @param Blue
    **/
    public void setBlue(short Blue) throws COMException
    {
        put("Blue", Blue);
    }
        
    /**
    *
    * @return short
    **/
    public short getFlags() throws COMException
    {
        return ((Short)get("Flags")).shortValue();
    }
        
    /**
    *
    * @param Flags
    **/
    public void setFlags(short Flags) throws COMException
    {
        put("Flags", Flags);
    }
        
    /**
    *
    * @return int
    **/
    public int getPaletteEntry() throws COMException
    {
        return ((Integer)get("PaletteEntry")).intValue();
    }
        
    /**
    *
    * @param PaletteEntry
    **/
    public void setPaletteEntry(int PaletteEntry) throws COMException
    {
        put("PaletteEntry", PaletteEntry);
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
    * @return int
    **/
    public int getIndex() throws COMException
    {
        return ((Integer)get("Index")).intValue();
    }
        
}

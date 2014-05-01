
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: EStyles
 * Description: Visio Styles Event Interface
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class EStyles extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0b05-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, EStyles.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * EStyles (it is required by Jawin for some internal working though).
	 */
	public EStyles() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the EStyles interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public EStyles(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the EStyles interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public EStyles(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the EStyles interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the EStyles interface on.
	 */
	public EStyles(COMPtr comObject) throws COMException {
		super(comObject);
	}

	public int getIIDToken() {
		return IID_TOKEN;
	}
	
	
    /**
    *
    
    * @return void
    **/
    public void StyleAdded(Object[] Style) throws COMException
    {
      
		invokeN("StyleAdded", new Object[] {Style});
        
    }
    /**
    *
    
    * @return void
    **/
    public void StyleChanged(Object[] Style) throws COMException
    {
      
		invokeN("StyleChanged", new Object[] {Style});
        
    }
    /**
    *
    
    * @return void
    **/
    public void BeforeStyleDelete(Object[] Style) throws COMException
    {
      
		invokeN("BeforeStyleDelete", new Object[] {Style});
        
    }
    /**
    *
    
    * @return void
    **/
    public void QueryCancelStyleDelete(Object[] Style,boolean[] lpboolRet) throws COMException
    {
      
		invokeN("QueryCancelStyleDelete", new Object[] {Style, lpboolRet});
        
    }
    /**
    *
    
    * @return void
    **/
    public void StyleDeleteCanceled(Object[] Style) throws COMException
    {
      
		invokeN("StyleDeleteCanceled", new Object[] {Style});
        
    }
}

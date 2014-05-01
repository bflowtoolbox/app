
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: ESection
 * Description: Visio Section Event Interface
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class ESection extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0b0e-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, ESection.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * ESection (it is required by Jawin for some internal working though).
	 */
	public ESection() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the ESection interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public ESection(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the ESection interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public ESection(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the ESection interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the ESection interface on.
	 */
	public ESection(COMPtr comObject) throws COMException {
		super(comObject);
	}

	public int getIIDToken() {
		return IID_TOKEN;
	}
	
	
    /**
    *
    
    * @return void
    **/
    public void CellChanged(Object[] Cell) throws COMException
    {
      
		invokeN("CellChanged", new Object[] {Cell});
        
    }
    /**
    *
    
    * @return void
    **/
    public void FormulaChanged(Object[] Cell) throws COMException
    {
      
		invokeN("FormulaChanged", new Object[] {Cell});
        
    }
}

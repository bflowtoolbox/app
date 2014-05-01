
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: ERow
 * Description: Visio Row Event Interface
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class ERow extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0b0f-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, ERow.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * ERow (it is required by Jawin for some internal working though).
	 */
	public ERow() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the ERow interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public ERow(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the ERow interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public ERow(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the ERow interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the ERow interface on.
	 */
	public ERow(COMPtr comObject) throws COMException {
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

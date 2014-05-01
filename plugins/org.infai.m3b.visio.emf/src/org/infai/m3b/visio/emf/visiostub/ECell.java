
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: ECell
 * Description: Visio Cell Event Interface
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class ECell extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0b0d-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, ECell.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * ECell (it is required by Jawin for some internal working though).
	 */
	public ECell() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the ECell interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public ECell(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the ECell interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public ECell(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the ECell interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the ECell interface on.
	 */
	public ECell(COMPtr comObject) throws COMException {
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

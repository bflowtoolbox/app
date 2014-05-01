
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: EDataRecordset
 * Description: Visio DataRecordset Event Interface
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class EDataRecordset extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0b11-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, EDataRecordset.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * EDataRecordset (it is required by Jawin for some internal working though).
	 */
	public EDataRecordset() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the EDataRecordset interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public EDataRecordset(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the EDataRecordset interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public EDataRecordset(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the EDataRecordset interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the EDataRecordset interface on.
	 */
	public EDataRecordset(COMPtr comObject) throws COMException {
		super(comObject);
	}

	public int getIIDToken() {
		return IID_TOKEN;
	}
	
	
    /**
    *
    
    * @return void
    **/
    public void DataRecordsetChanged(Object[] DataRecordsetChanged) throws COMException
    {
      
		invokeN("DataRecordsetChanged", new Object[] {DataRecordsetChanged});
        
    }
    /**
    *
    
    * @return void
    **/
    public void BeforeDataRecordsetDelete(Object[] DataRecordset) throws COMException
    {
      
		invokeN("BeforeDataRecordsetDelete", new Object[] {DataRecordset});
        
    }
}

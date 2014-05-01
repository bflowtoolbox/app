
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: EDataRecordsets
 * Description: Visio DataRecordsets Event Interface
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class EDataRecordsets extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0b10-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, EDataRecordsets.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * EDataRecordsets (it is required by Jawin for some internal working though).
	 */
	public EDataRecordsets() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the EDataRecordsets interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public EDataRecordsets(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the EDataRecordsets interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public EDataRecordsets(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the EDataRecordsets interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the EDataRecordsets interface on.
	 */
	public EDataRecordsets(COMPtr comObject) throws COMException {
		super(comObject);
	}

	public int getIIDToken() {
		return IID_TOKEN;
	}
	
	
    /**
    *
    
    * @return void
    **/
    public void DataRecordsetAdded(Object[] DataRecordset) throws COMException
    {
      
		invokeN("DataRecordsetAdded", new Object[] {DataRecordset});
        
    }
    /**
    *
    
    * @return void
    **/
    public void BeforeDataRecordsetDelete(Object[] DataRecordset) throws COMException
    {
      
		invokeN("BeforeDataRecordsetDelete", new Object[] {DataRecordset});
        
    }
    /**
    *
    
    * @return void
    **/
    public void DataRecordsetChanged(Object[] DataRecordsetChanged) throws COMException
    {
      
		invokeN("DataRecordsetChanged", new Object[] {DataRecordsetChanged});
        
    }
}

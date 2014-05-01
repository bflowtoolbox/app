
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;





/**
 * Jawin generated code please do not edit
 *
 * Interface: IEnumVMenuItem
 * Description: 
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IEnumVMenuItem extends COMPtr {
	public static final GUID IID = new GUID("{000d0213-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(IID, IEnumVMenuItem.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IEnumVMenuItem (it is required by Jawin for some internal working though).
	 */
	public IEnumVMenuItem() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IEnumVMenuItem interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IEnumVMenuItem(String progid) throws COMException {
		super(progid, IID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IEnumVMenuItem interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IEnumVMenuItem(GUID clsid) throws COMException {
		super(clsid, IID);
	}

	/**
	 * For getting the IEnumVMenuItem interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IEnumVMenuItem interface on.
	 */
	public IEnumVMenuItem(COMPtr comObject) throws COMException {
		super(comObject);
	}

	public int getIIDToken() {
		return IID_TOKEN;
	}

	
	public int Next(int celt,Object[] 
        [] rgelt,int[] pceltFetched) throws COMException
	{
            //implement your pure virtual code here.
            throw new UnsupportedOperationException();
	}
        
	public int Skip(int celt) throws COMException
	{
            //implement your pure virtual code here.
            throw new UnsupportedOperationException();
	}
        
	public int Reset() throws COMException
	{
            //implement your pure virtual code here.
            throw new UnsupportedOperationException();
	}
        
	public int Clone(Object[] 
        [] ppenm) throws COMException
	{
            //implement your pure virtual code here.
            throw new UnsupportedOperationException();
	}
        
}

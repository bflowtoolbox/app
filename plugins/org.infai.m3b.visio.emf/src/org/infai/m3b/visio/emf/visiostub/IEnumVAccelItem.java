
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;





/**
 * Jawin generated code please do not edit
 *
 * Interface: IEnumVAccelItem
 * Description: 
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IEnumVAccelItem extends COMPtr {
	public static final GUID IID = new GUID("{000d0293-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(IID, IEnumVAccelItem.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IEnumVAccelItem (it is required by Jawin for some internal working though).
	 */
	public IEnumVAccelItem() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IEnumVAccelItem interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IEnumVAccelItem(String progid) throws COMException {
		super(progid, IID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IEnumVAccelItem interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IEnumVAccelItem(GUID clsid) throws COMException {
		super(clsid, IID);
	}

	/**
	 * For getting the IEnumVAccelItem interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IEnumVAccelItem interface on.
	 */
	public IEnumVAccelItem(COMPtr comObject) throws COMException {
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


package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;





/**
 * Jawin generated code please do not edit
 *
 * Interface: IEnumVMenuSet
 * Description: 
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IEnumVMenuSet extends COMPtr {
	public static final GUID IID = new GUID("{000d0233-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(IID, IEnumVMenuSet.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IEnumVMenuSet (it is required by Jawin for some internal working though).
	 */
	public IEnumVMenuSet() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IEnumVMenuSet interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IEnumVMenuSet(String progid) throws COMException {
		super(progid, IID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IEnumVMenuSet interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IEnumVMenuSet(GUID clsid) throws COMException {
		super(clsid, IID);
	}

	/**
	 * For getting the IEnumVMenuSet interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IEnumVMenuSet interface on.
	 */
	public IEnumVMenuSet(COMPtr comObject) throws COMException {
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


package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;





/**
 * Jawin generated code please do not edit
 *
 * Interface: IVClientSite
 * Description: Interface provided to OLE embedded objects through the Visio IOleClientSite object.
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVClientSite extends COMPtr {
	public static final GUID IID = new GUID("{000d0d11-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(IID, IVClientSite.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVClientSite (it is required by Jawin for some internal working though).
	 */
	public IVClientSite() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVClientSite interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVClientSite(String progid) throws COMException {
		super(progid, IID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVClientSite interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVClientSite(GUID clsid) throws COMException {
		super(clsid, IID);
	}

	/**
	 * For getting the IVClientSite interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVClientSite interface on.
	 */
	public IVClientSite(COMPtr comObject) throws COMException {
		super(comObject);
	}

	public int getIIDToken() {
		return IID_TOKEN;
	}

	
	public int Shape(Object[] 
        [] ppShape) throws COMException
	{
            //implement your pure virtual code here.
            throw new UnsupportedOperationException();
	}
        
}

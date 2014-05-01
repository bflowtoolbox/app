
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;





/**
 * Jawin generated code please do not edit
 *
 * Interface: IVisLibOcxSupport
 * Description: Interface to enable VisOcx.DrawingControl interactions with VisLib.
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVisLibOcxSupport extends COMPtr {
	public static final GUID IID = new GUID("{000d0d21-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(IID, IVisLibOcxSupport.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVisLibOcxSupport (it is required by Jawin for some internal working though).
	 */
	public IVisLibOcxSupport() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVisLibOcxSupport interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVisLibOcxSupport(String progid) throws COMException {
		super(progid, IID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVisLibOcxSupport interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVisLibOcxSupport(GUID clsid) throws COMException {
		super(clsid, IID);
	}

	/**
	 * For getting the IVisLibOcxSupport interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVisLibOcxSupport interface on.
	 */
	public IVisLibOcxSupport(COMPtr comObject) throws COMException {
		super(comObject);
	}

	public int getIIDToken() {
		return IID_TOKEN;
	}

	
	public int HostID(String[] pbstr) throws COMException
	{
            //implement your pure virtual code here.
            throw new UnsupportedOperationException();
	}
        
	public int HostID(String pbstr) throws COMException
	{
            //implement your pure virtual code here.
            throw new UnsupportedOperationException();
	}
        
	public int Window(Object[] 
        [] ppWin) throws COMException
	{
            //implement your pure virtual code here.
            throw new UnsupportedOperationException();
	}
        
	public int Document(Object[] 
        [] ppDoc) throws COMException
	{
            //implement your pure virtual code here.
            throw new UnsupportedOperationException();
	}
        
	public int Src(String[] pbstr) throws COMException
	{
            //implement your pure virtual code here.
            throw new UnsupportedOperationException();
	}
        
	public int Src(String pbstr) throws COMException
	{
            //implement your pure virtual code here.
            throw new UnsupportedOperationException();
	}
        
	public int NegotiateMenus(boolean[] pbVal) throws COMException
	{
            //implement your pure virtual code here.
            throw new UnsupportedOperationException();
	}
        
	public int NegotiateMenus(boolean pbVal) throws COMException
	{
            //implement your pure virtual code here.
            throw new UnsupportedOperationException();
	}
        
	public int NegotiateToolbars(boolean[] pbVal) throws COMException
	{
            //implement your pure virtual code here.
            throw new UnsupportedOperationException();
	}
        
	public int NegotiateToolbars(boolean pbVal) throws COMException
	{
            //implement your pure virtual code here.
            throw new UnsupportedOperationException();
	}
        
	public int About() throws COMException
	{
            //implement your pure virtual code here.
            throw new UnsupportedOperationException();
	}
        
	public int PageSizingBehavior(int[] peVal) throws COMException
	{
            //implement your pure virtual code here.
            throw new UnsupportedOperationException();
	}
        
	public int PageSizingBehavior(int peVal) throws COMException
	{
            //implement your pure virtual code here.
            throw new UnsupportedOperationException();
	}
        
}

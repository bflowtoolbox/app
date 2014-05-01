
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;





/**
 * Jawin generated code please do not edit
 *
 * Interface: IVMouseEvent
 * Description: Interface to enable passing relevant info about mouse messages through Visio automation events.
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVMouseEvent extends COMPtr {
	public static final GUID IID = new GUID("{000d072a-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(IID, IVMouseEvent.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVMouseEvent (it is required by Jawin for some internal working though).
	 */
	public IVMouseEvent() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVMouseEvent interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVMouseEvent(String progid) throws COMException {
		super(progid, IID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVMouseEvent interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVMouseEvent(GUID clsid) throws COMException {
		super(clsid, IID);
	}

	/**
	 * For getting the IVMouseEvent interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVMouseEvent interface on.
	 */
	public IVMouseEvent(COMPtr comObject) throws COMException {
		super(comObject);
	}

	public int getIIDToken() {
		return IID_TOKEN;
	}

	
	public int ToString(String[] pbstrString) throws COMException
	{
            //implement your pure virtual code here.
            throw new UnsupportedOperationException();
	}
        
	public int Application(Object[] 
        [] lpdispRet) throws COMException
	{
            //implement your pure virtual code here.
            throw new UnsupportedOperationException();
	}
        
	public int ObjectType(int[] peType) throws COMException
	{
            //implement your pure virtual code here.
            throw new UnsupportedOperationException();
	}
        
	public int Window(Object[] 
        [] ppWindow) throws COMException
	{
            //implement your pure virtual code here.
            throw new UnsupportedOperationException();
	}
        
	public int Button(int[] pnButton) throws COMException
	{
            //implement your pure virtual code here.
            throw new UnsupportedOperationException();
	}
        
	public int KeyButtonState(int[] pnKeyButtonState) throws COMException
	{
            //implement your pure virtual code here.
            throw new UnsupportedOperationException();
	}
        
	public int x(double[] pdX) throws COMException
	{
            //implement your pure virtual code here.
            throw new UnsupportedOperationException();
	}
        
	public int y(double[] pdY) throws COMException
	{
            //implement your pure virtual code here.
            throw new UnsupportedOperationException();
	}
        
	public int DragState(int[] peDragState) throws COMException
	{
            //implement your pure virtual code here.
            throw new UnsupportedOperationException();
	}
        
	public int Stat(short[] lpi2Ret) throws COMException
	{
            //implement your pure virtual code here.
            throw new UnsupportedOperationException();
	}
        
}

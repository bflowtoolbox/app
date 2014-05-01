
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: EWindows
 * Description: Visio Windows Event Interface
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class EWindows extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0b01-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, EWindows.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * EWindows (it is required by Jawin for some internal working though).
	 */
	public EWindows() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the EWindows interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public EWindows(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the EWindows interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public EWindows(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the EWindows interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the EWindows interface on.
	 */
	public EWindows(COMPtr comObject) throws COMException {
		super(comObject);
	}

	public int getIIDToken() {
		return IID_TOKEN;
	}
	
	
    /**
    *
    
    * @return void
    **/
    public void WindowOpened(Object[] Window) throws COMException
    {
      
		invokeN("WindowOpened", new Object[] {Window});
        
    }
    /**
    *
    
    * @return void
    **/
    public void SelectionChanged(Object[] Window) throws COMException
    {
      
		invokeN("SelectionChanged", new Object[] {Window});
        
    }
    /**
    *
    
    * @return void
    **/
    public void BeforeWindowClosed(Object[] Window) throws COMException
    {
      
		invokeN("BeforeWindowClosed", new Object[] {Window});
        
    }
    /**
    *
    
    * @return void
    **/
    public void WindowActivated(Object[] Window) throws COMException
    {
      
		invokeN("WindowActivated", new Object[] {Window});
        
    }
    /**
    *
    
    * @return void
    **/
    public void BeforeWindowSelDelete(Object[] Window) throws COMException
    {
      
		invokeN("BeforeWindowSelDelete", new Object[] {Window});
        
    }
    /**
    *
    
    * @return void
    **/
    public void BeforeWindowPageTurn(Object[] Window) throws COMException
    {
      
		invokeN("BeforeWindowPageTurn", new Object[] {Window});
        
    }
    /**
    *
    
    * @return void
    **/
    public void WindowTurnedToPage(Object[] Window) throws COMException
    {
      
		invokeN("WindowTurnedToPage", new Object[] {Window});
        
    }
    /**
    *
    
    * @return void
    **/
    public void WindowChanged(Object[] Window) throws COMException
    {
      
		invokeN("WindowChanged", new Object[] {Window});
        
    }
    /**
    *
    
    * @return void
    **/
    public void ViewChanged(Object[] Window) throws COMException
    {
      
		invokeN("ViewChanged", new Object[] {Window});
        
    }
    /**
    *
    
    * @return void
    **/
    public void QueryCancelWindowClose(Object[] Window,boolean[] lpboolRet) throws COMException
    {
      
		invokeN("QueryCancelWindowClose", new Object[] {Window, lpboolRet});
        
    }
    /**
    *
    
    * @return void
    **/
    public void WindowCloseCanceled(Object[] Window) throws COMException
    {
      
		invokeN("WindowCloseCanceled", new Object[] {Window});
        
    }
    /**
    *
    
    * @return void
    **/
    public void OnKeystrokeMessageForAddon(Object[] MSG,boolean[] lpboolRet) throws COMException
    {
      
		invokeN("OnKeystrokeMessageForAddon", new Object[] {MSG, lpboolRet});
        
    }
    /**
    *
    
    * @param Button
    * @param KeyButtonState
    * @param x
    * @param y
    * @return void
    **/
    public void MouseDown(int Button,int KeyButtonState,double x,double y,boolean[] CancelDefault) throws COMException
    {
      
		invokeN("MouseDown", new Object[] {new Integer(Button), new Integer(KeyButtonState), new Double(x), new Double(y), CancelDefault});
        
    }
    /**
    *
    
    * @param Button
    * @param KeyButtonState
    * @param x
    * @param y
    * @return void
    **/
    public void MouseMove(int Button,int KeyButtonState,double x,double y,boolean[] CancelDefault) throws COMException
    {
      
		invokeN("MouseMove", new Object[] {new Integer(Button), new Integer(KeyButtonState), new Double(x), new Double(y), CancelDefault});
        
    }
    /**
    *
    
    * @param Button
    * @param KeyButtonState
    * @param x
    * @param y
    * @return void
    **/
    public void MouseUp(int Button,int KeyButtonState,double x,double y,boolean[] CancelDefault) throws COMException
    {
      
		invokeN("MouseUp", new Object[] {new Integer(Button), new Integer(KeyButtonState), new Double(x), new Double(y), CancelDefault});
        
    }
    /**
    *
    
    * @param KeyCode
    * @param KeyButtonState
    * @return void
    **/
    public void KeyDown(int KeyCode,int KeyButtonState,boolean[] CancelDefault) throws COMException
    {
      
		invokeN("KeyDown", new Object[] {new Integer(KeyCode), new Integer(KeyButtonState), CancelDefault});
        
    }
    /**
    *
    
    * @param KeyAscii
    * @return void
    **/
    public void KeyPress(int KeyAscii,boolean[] CancelDefault) throws COMException
    {
      
		invokeN("KeyPress", new Object[] {new Integer(KeyAscii), CancelDefault});
        
    }
    /**
    *
    
    * @param KeyCode
    * @param KeyButtonState
    * @return void
    **/
    public void KeyUp(int KeyCode,int KeyButtonState,boolean[] CancelDefault) throws COMException
    {
      
		invokeN("KeyUp", new Object[] {new Integer(KeyCode), new Integer(KeyButtonState), CancelDefault});
        
    }
}

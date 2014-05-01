
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: EApplication
 * Description: Visio Application Event Interface
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class EApplication extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0b00-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, EApplication.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * EApplication (it is required by Jawin for some internal working though).
	 */
	public EApplication() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the EApplication interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public EApplication(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the EApplication interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public EApplication(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the EApplication interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the EApplication interface on.
	 */
	public EApplication(COMPtr comObject) throws COMException {
		super(comObject);
	}

	public int getIIDToken() {
		return IID_TOKEN;
	}
	
	
    /**
    *
    
    * @return void
    **/
    public void AppActivated(Object[] app) throws COMException
    {
      
		invokeN("AppActivated", new Object[] {app});
        
    }
    /**
    *
    
    * @return void
    **/
    public void AppDeactivated(Object[] app) throws COMException
    {
      
		invokeN("AppDeactivated", new Object[] {app});
        
    }
    /**
    *
    
    * @return void
    **/
    public void AppObjActivated(Object[] app) throws COMException
    {
      
		invokeN("AppObjActivated", new Object[] {app});
        
    }
    /**
    *
    
    * @return void
    **/
    public void AppObjDeactivated(Object[] app) throws COMException
    {
      
		invokeN("AppObjDeactivated", new Object[] {app});
        
    }
    /**
    *
    
    * @return void
    **/
    public void BeforeQuit(Object[] app) throws COMException
    {
      
		invokeN("BeforeQuit", new Object[] {app});
        
    }
    /**
    *
    
    * @return void
    **/
    public void BeforeModal(Object[] app) throws COMException
    {
      
		invokeN("BeforeModal", new Object[] {app});
        
    }
    /**
    *
    
    * @return void
    **/
    public void AfterModal(Object[] app) throws COMException
    {
      
		invokeN("AfterModal", new Object[] {app});
        
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
    public void DocumentOpened(Object[] doc) throws COMException
    {
      
		invokeN("DocumentOpened", new Object[] {doc});
        
    }
    /**
    *
    
    * @return void
    **/
    public void DocumentCreated(Object[] doc) throws COMException
    {
      
		invokeN("DocumentCreated", new Object[] {doc});
        
    }
    /**
    *
    
    * @return void
    **/
    public void DocumentSaved(Object[] doc) throws COMException
    {
      
		invokeN("DocumentSaved", new Object[] {doc});
        
    }
    /**
    *
    
    * @return void
    **/
    public void DocumentSavedAs(Object[] doc) throws COMException
    {
      
		invokeN("DocumentSavedAs", new Object[] {doc});
        
    }
    /**
    *
    
    * @return void
    **/
    public void DocumentChanged(Object[] doc) throws COMException
    {
      
		invokeN("DocumentChanged", new Object[] {doc});
        
    }
    /**
    *
    
    * @return void
    **/
    public void BeforeDocumentClose(Object[] doc) throws COMException
    {
      
		invokeN("BeforeDocumentClose", new Object[] {doc});
        
    }
    /**
    *
    
    * @return void
    **/
    public void StyleAdded(Object[] Style) throws COMException
    {
      
		invokeN("StyleAdded", new Object[] {Style});
        
    }
    /**
    *
    
    * @return void
    **/
    public void StyleChanged(Object[] Style) throws COMException
    {
      
		invokeN("StyleChanged", new Object[] {Style});
        
    }
    /**
    *
    
    * @return void
    **/
    public void BeforeStyleDelete(Object[] Style) throws COMException
    {
      
		invokeN("BeforeStyleDelete", new Object[] {Style});
        
    }
    /**
    *
    
    * @return void
    **/
    public void MasterAdded(Object[] Master) throws COMException
    {
      
		invokeN("MasterAdded", new Object[] {Master});
        
    }
    /**
    *
    
    * @return void
    **/
    public void MasterChanged(Object[] Master) throws COMException
    {
      
		invokeN("MasterChanged", new Object[] {Master});
        
    }
    /**
    *
    
    * @return void
    **/
    public void BeforeMasterDelete(Object[] Master) throws COMException
    {
      
		invokeN("BeforeMasterDelete", new Object[] {Master});
        
    }
    /**
    *
    
    * @return void
    **/
    public void PageAdded(Object[] Page) throws COMException
    {
      
		invokeN("PageAdded", new Object[] {Page});
        
    }
    /**
    *
    
    * @return void
    **/
    public void PageChanged(Object[] Page) throws COMException
    {
      
		invokeN("PageChanged", new Object[] {Page});
        
    }
    /**
    *
    
    * @return void
    **/
    public void BeforePageDelete(Object[] Page) throws COMException
    {
      
		invokeN("BeforePageDelete", new Object[] {Page});
        
    }
    /**
    *
    
    * @return void
    **/
    public void ShapeAdded(Object[] Shape) throws COMException
    {
      
		invokeN("ShapeAdded", new Object[] {Shape});
        
    }
    /**
    *
    
    * @return void
    **/
    public void BeforeSelectionDelete(Object[] Selection) throws COMException
    {
      
		invokeN("BeforeSelectionDelete", new Object[] {Selection});
        
    }
    /**
    *
    
    * @return void
    **/
    public void ShapeChanged(Object[] Shape) throws COMException
    {
      
		invokeN("ShapeChanged", new Object[] {Shape});
        
    }
    /**
    *
    
    * @return void
    **/
    public void SelectionAdded(Object[] Selection) throws COMException
    {
      
		invokeN("SelectionAdded", new Object[] {Selection});
        
    }
    /**
    *
    
    * @return void
    **/
    public void BeforeShapeDelete(Object[] Shape) throws COMException
    {
      
		invokeN("BeforeShapeDelete", new Object[] {Shape});
        
    }
    /**
    *
    
    * @return void
    **/
    public void TextChanged(Object[] Shape) throws COMException
    {
      
		invokeN("TextChanged", new Object[] {Shape});
        
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
    
    * @param SequenceNum
    * @param ContextString
    * @return void
    **/
    public void MarkerEvent(Object[] app,int SequenceNum,String ContextString) throws COMException
    {
      
		invokeN("MarkerEvent", new Object[] {app, new Integer(SequenceNum), ContextString});
        
    }
    /**
    *
    
    * @return void
    **/
    public void NoEventsPending(Object[] app) throws COMException
    {
      
		invokeN("NoEventsPending", new Object[] {app});
        
    }
    /**
    *
    
    * @return void
    **/
    public void VisioIsIdle(Object[] app) throws COMException
    {
      
		invokeN("VisioIsIdle", new Object[] {app});
        
    }
    /**
    *
    
    * @return void
    **/
    public void MustFlushScopeBeginning(Object[] app) throws COMException
    {
      
		invokeN("MustFlushScopeBeginning", new Object[] {app});
        
    }
    /**
    *
    
    * @return void
    **/
    public void MustFlushScopeEnded(Object[] app) throws COMException
    {
      
		invokeN("MustFlushScopeEnded", new Object[] {app});
        
    }
    /**
    *
    
    * @return void
    **/
    public void RunModeEntered(Object[] doc) throws COMException
    {
      
		invokeN("RunModeEntered", new Object[] {doc});
        
    }
    /**
    *
    
    * @return void
    **/
    public void DesignModeEntered(Object[] doc) throws COMException
    {
      
		invokeN("DesignModeEntered", new Object[] {doc});
        
    }
    /**
    *
    
    * @return void
    **/
    public void BeforeDocumentSave(Object[] doc) throws COMException
    {
      
		invokeN("BeforeDocumentSave", new Object[] {doc});
        
    }
    /**
    *
    
    * @return void
    **/
    public void BeforeDocumentSaveAs(Object[] doc) throws COMException
    {
      
		invokeN("BeforeDocumentSaveAs", new Object[] {doc});
        
    }
    /**
    *
    
    * @return void
    **/
    public void FormulaChanged(Object[] Cell) throws COMException
    {
      
		invokeN("FormulaChanged", new Object[] {Cell});
        
    }
    /**
    *
    
    * @return void
    **/
    public void ConnectionsAdded(Object[] Connects) throws COMException
    {
      
		invokeN("ConnectionsAdded", new Object[] {Connects});
        
    }
    /**
    *
    
    * @return void
    **/
    public void ConnectionsDeleted(Object[] Connects) throws COMException
    {
      
		invokeN("ConnectionsDeleted", new Object[] {Connects});
        
    }
    /**
    *
    
    * @param nScopeID
    * @param bstrDescription
    * @return void
    **/
    public void EnterScope(Object[] app,int nScopeID,String bstrDescription) throws COMException
    {
      
		invokeN("EnterScope", new Object[] {app, new Integer(nScopeID), bstrDescription});
        
    }
    /**
    *
    
    * @param nScopeID
    * @param bstrDescription
    * @param bErrOrCancelled
    * @return void
    **/
    public void ExitScope(Object[] app,int nScopeID,String bstrDescription,boolean bErrOrCancelled) throws COMException
    {
      
		invokeN("ExitScope", new Object[] {app, new Integer(nScopeID), bstrDescription, new Boolean(bErrOrCancelled)});
        
    }
    /**
    *
    
    * @return void
    **/
    public void QueryCancelQuit(Object[] app,boolean[] lpboolRet) throws COMException
    {
      
		invokeN("QueryCancelQuit", new Object[] {app, lpboolRet});
        
    }
    /**
    *
    
    * @return void
    **/
    public void QuitCanceled(Object[] app) throws COMException
    {
      
		invokeN("QuitCanceled", new Object[] {app});
        
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
    public void QueryCancelDocumentClose(Object[] doc,boolean[] lpboolRet) throws COMException
    {
      
		invokeN("QueryCancelDocumentClose", new Object[] {doc, lpboolRet});
        
    }
    /**
    *
    
    * @return void
    **/
    public void DocumentCloseCanceled(Object[] doc) throws COMException
    {
      
		invokeN("DocumentCloseCanceled", new Object[] {doc});
        
    }
    /**
    *
    
    * @return void
    **/
    public void QueryCancelStyleDelete(Object[] Style,boolean[] lpboolRet) throws COMException
    {
      
		invokeN("QueryCancelStyleDelete", new Object[] {Style, lpboolRet});
        
    }
    /**
    *
    
    * @return void
    **/
    public void StyleDeleteCanceled(Object[] Style) throws COMException
    {
      
		invokeN("StyleDeleteCanceled", new Object[] {Style});
        
    }
    /**
    *
    
    * @return void
    **/
    public void QueryCancelMasterDelete(Object[] Master,boolean[] lpboolRet) throws COMException
    {
      
		invokeN("QueryCancelMasterDelete", new Object[] {Master, lpboolRet});
        
    }
    /**
    *
    
    * @return void
    **/
    public void MasterDeleteCanceled(Object[] Master) throws COMException
    {
      
		invokeN("MasterDeleteCanceled", new Object[] {Master});
        
    }
    /**
    *
    
    * @return void
    **/
    public void QueryCancelPageDelete(Object[] Page,boolean[] lpboolRet) throws COMException
    {
      
		invokeN("QueryCancelPageDelete", new Object[] {Page, lpboolRet});
        
    }
    /**
    *
    
    * @return void
    **/
    public void PageDeleteCanceled(Object[] Page) throws COMException
    {
      
		invokeN("PageDeleteCanceled", new Object[] {Page});
        
    }
    /**
    *
    
    * @return void
    **/
    public void ShapeParentChanged(Object[] Shape) throws COMException
    {
      
		invokeN("ShapeParentChanged", new Object[] {Shape});
        
    }
    /**
    *
    
    * @return void
    **/
    public void BeforeShapeTextEdit(Object[] Shape) throws COMException
    {
      
		invokeN("BeforeShapeTextEdit", new Object[] {Shape});
        
    }
    /**
    *
    
    * @return void
    **/
    public void ShapeExitedTextEdit(Object[] Shape) throws COMException
    {
      
		invokeN("ShapeExitedTextEdit", new Object[] {Shape});
        
    }
    /**
    *
    
    * @return void
    **/
    public void QueryCancelSelectionDelete(Object[] Selection,boolean[] lpboolRet) throws COMException
    {
      
		invokeN("QueryCancelSelectionDelete", new Object[] {Selection, lpboolRet});
        
    }
    /**
    *
    
    * @return void
    **/
    public void SelectionDeleteCanceled(Object[] Selection) throws COMException
    {
      
		invokeN("SelectionDeleteCanceled", new Object[] {Selection});
        
    }
    /**
    *
    
    * @return void
    **/
    public void QueryCancelUngroup(Object[] Selection,boolean[] lpboolRet) throws COMException
    {
      
		invokeN("QueryCancelUngroup", new Object[] {Selection, lpboolRet});
        
    }
    /**
    *
    
    * @return void
    **/
    public void UngroupCanceled(Object[] Selection) throws COMException
    {
      
		invokeN("UngroupCanceled", new Object[] {Selection});
        
    }
    /**
    *
    
    * @return void
    **/
    public void QueryCancelConvertToGroup(Object[] Selection,boolean[] lpboolRet) throws COMException
    {
      
		invokeN("QueryCancelConvertToGroup", new Object[] {Selection, lpboolRet});
        
    }
    /**
    *
    
    * @return void
    **/
    public void ConvertToGroupCanceled(Object[] Selection) throws COMException
    {
      
		invokeN("ConvertToGroupCanceled", new Object[] {Selection});
        
    }
    /**
    *
    
    * @return void
    **/
    public void QueryCancelSuspend(Object[] app,boolean[] lpboolRet) throws COMException
    {
      
		invokeN("QueryCancelSuspend", new Object[] {app, lpboolRet});
        
    }
    /**
    *
    
    * @return void
    **/
    public void SuspendCanceled(Object[] app) throws COMException
    {
      
		invokeN("SuspendCanceled", new Object[] {app});
        
    }
    /**
    *
    
    * @return void
    **/
    public void BeforeSuspend(Object[] app) throws COMException
    {
      
		invokeN("BeforeSuspend", new Object[] {app});
        
    }
    /**
    *
    
    * @return void
    **/
    public void AfterResume(Object[] app) throws COMException
    {
      
		invokeN("AfterResume", new Object[] {app});
        
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
    /**
    *
    
    * @return void
    **/
    public void QueryCancelSuspendEvents(Object[] app,boolean[] lpboolRet) throws COMException
    {
      
		invokeN("QueryCancelSuspendEvents", new Object[] {app, lpboolRet});
        
    }
    /**
    *
    
    * @return void
    **/
    public void SuspendEventsCanceled(Object[] app) throws COMException
    {
      
		invokeN("SuspendEventsCanceled", new Object[] {app});
        
    }
    /**
    *
    
    * @return void
    **/
    public void BeforeSuspendEvents(Object[] app) throws COMException
    {
      
		invokeN("BeforeSuspendEvents", new Object[] {app});
        
    }
    /**
    *
    
    * @return void
    **/
    public void AfterResumeEvents(Object[] app) throws COMException
    {
      
		invokeN("AfterResumeEvents", new Object[] {app});
        
    }
    /**
    *
    
    * @return void
    **/
    public void QueryCancelGroup(Object[] Selection,boolean[] lpboolRet) throws COMException
    {
      
		invokeN("QueryCancelGroup", new Object[] {Selection, lpboolRet});
        
    }
    /**
    *
    
    * @return void
    **/
    public void GroupCanceled(Object[] Selection) throws COMException
    {
      
		invokeN("GroupCanceled", new Object[] {Selection});
        
    }
    /**
    *
    
    * @return void
    **/
    public void ShapeDataGraphicChanged(Object[] Shape) throws COMException
    {
      
		invokeN("ShapeDataGraphicChanged", new Object[] {Shape});
        
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
    
    * @param DataRecordsetID
    * @param DataRowID
    * @return void
    **/
    public void ShapeLinkAdded(Object[] Shape,int DataRecordsetID,int DataRowID) throws COMException
    {
      
		invokeN("ShapeLinkAdded", new Object[] {Shape, new Integer(DataRecordsetID), new Integer(DataRowID)});
        
    }
    /**
    *
    
    * @param DataRecordsetID
    * @param DataRowID
    * @return void
    **/
    public void ShapeLinkDeleted(Object[] Shape,int DataRecordsetID,int DataRowID) throws COMException
    {
      
		invokeN("ShapeLinkDeleted", new Object[] {Shape, new Integer(DataRecordsetID), new Integer(DataRowID)});
        
    }
    /**
    *
    
    * @return void
    **/
    public void AfterRemoveHiddenInformation(Object[] doc) throws COMException
    {
      
		invokeN("AfterRemoveHiddenInformation", new Object[] {doc});
        
    }
}

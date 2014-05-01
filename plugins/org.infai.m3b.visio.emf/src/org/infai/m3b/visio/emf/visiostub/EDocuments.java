
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: EDocuments
 * Description: Visio Documents Event Interface
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class EDocuments extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0b03-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, EDocuments.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * EDocuments (it is required by Jawin for some internal working though).
	 */
	public EDocuments() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the EDocuments interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public EDocuments(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the EDocuments interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public EDocuments(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the EDocuments interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the EDocuments interface on.
	 */
	public EDocuments(COMPtr comObject) throws COMException {
		super(comObject);
	}

	public int getIIDToken() {
		return IID_TOKEN;
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

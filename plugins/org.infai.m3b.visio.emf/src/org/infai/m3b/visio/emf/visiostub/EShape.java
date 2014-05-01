
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: EShape
 * Description: Visio Shape Event Interface
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class EShape extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0b0b-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, EShape.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * EShape (it is required by Jawin for some internal working though).
	 */
	public EShape() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the EShape interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public EShape(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the EShape interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public EShape(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the EShape interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the EShape interface on.
	 */
	public EShape(COMPtr comObject) throws COMException {
		super(comObject);
	}

	public int getIIDToken() {
		return IID_TOKEN;
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
    public void FormulaChanged(Object[] Cell) throws COMException
    {
      
		invokeN("FormulaChanged", new Object[] {Cell});
        
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
}

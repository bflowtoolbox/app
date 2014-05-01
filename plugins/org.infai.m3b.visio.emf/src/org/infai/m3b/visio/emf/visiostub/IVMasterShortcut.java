
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVMasterShortcut
 * Description: 
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVMasterShortcut extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0727-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVMasterShortcut.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVMasterShortcut (it is required by Jawin for some internal working though).
	 */
	public IVMasterShortcut() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVMasterShortcut interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVMasterShortcut(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVMasterShortcut interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVMasterShortcut(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVMasterShortcut interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVMasterShortcut interface on.
	 */
	public IVMasterShortcut(COMPtr comObject) throws COMException {
		super(comObject);
	}

	public int getIIDToken() {
		return IID_TOKEN;
	}
	
	
    /**
    *
    
    * @return void
    **/
    /*public void QueryInterface(Object[] riid,void[] 
        [] ppvObj) throws COMException
    {
      
		invokeN("QueryInterface", new Object[] {riid, ppvObj});
        
    }*/
    /**
    *
    
    * @return int
    **/
    /*public int AddRef() throws COMException
    {
      
		return ((Integer)invokeN("AddRef", new Object[] {})).intValue();
        
    }*/
    /**
    *
    
    * @return int
    **/
    /*public int Release() throws COMException
    {
      
		return ((Integer)invokeN("Release", new Object[] {})).intValue();
        
    }*/
    /**
    *
    
    * @return void
    **/
    /*public void GetTypeInfoCount(int[] pctinfo) throws COMException
    {
      
		invokeN("GetTypeInfoCount", new Object[] {pctinfo});
        
    }*/
    /**
    *
    
    * @param itinfo
    * @param lcid
    * @return void
    **/
    /*public void GetTypeInfo(int itinfo,int lcid,void[] 
        [] pptinfo) throws COMException
    {
      
		invokeN("GetTypeInfo", new Object[] {new Integer(itinfo), new Integer(lcid), pptinfo});
        
    }*/
    /**
    *
    
    * @param cNames
    * @param lcid
    * @return void
    **/
    /*public void GetIDsOfNames(Object[] riid,int[] 
        [] rgszNames,int cNames,int lcid,int[] rgdispid) throws COMException
    {
      
		invokeN("GetIDsOfNames", new Object[] {riid, rgszNames, new Integer(cNames), new Integer(lcid), rgdispid});
        
    }*/
    /**
    *
    
    * @param dispidMember
    * @param lcid
    * @param wFlags
    * @return void
    **/
    /*public void Invoke(int dispidMember,Object[] riid,int lcid,short wFlags,Object[] pdispparams,Variant[] pvarResult,Object[] pexcepinfo,int[] puArgErr) throws COMException
    {
      
		invokeN("Invoke", new Object[] {new Integer(dispidMember), riid, new Integer(lcid), new Short(wFlags), pdispparams, pvarResult, pexcepinfo, puArgErr});
        
    }*/
    /**
    *
    * @return void
    **/
    public IVApplication getApplication() throws COMException
    {
        IVApplication res = new IVApplication();
          DispatchPtr dispPtr = (DispatchPtr)get("Application");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return void
    **/
    public IVDocument getDocument() throws COMException
    {
        IVDocument res = new IVDocument();
          DispatchPtr dispPtr = (DispatchPtr)get("Document");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return short
    **/
    public short getIndex() throws COMException
    {
        return ((Short)get("Index")).shortValue();
    }
        
    /**
    *
    * @return short
    **/
    public short getObjectType() throws COMException
    {
        return ((Short)get("ObjectType")).shortValue();
    }
        
    /**
    *
    * @return short
    **/
    public short getStat() throws COMException
    {
        return ((Short)get("Stat")).shortValue();
    }
        
    /**
    *
    * @return String
    **/
    public String getName() throws COMException
    {
         return (String)get("Name");
    }
        
    /**
    *
    * @param Name
    **/
    public void setName(String Name) throws COMException
    {
        put("Name", Name);
    }
        
    /**
    *
    * @return String
    **/
    public String getPrompt() throws COMException
    {
         return (String)get("Prompt");
    }
        
    /**
    *
    * @param Prompt
    **/
    public void setPrompt(String Prompt) throws COMException
    {
        put("Prompt", Prompt);
    }
        
    /**
    *
    * @return short
    **/
    public short getAlignName() throws COMException
    {
        return ((Short)get("AlignName")).shortValue();
    }
        
    /**
    *
    * @param AlignName
    **/
    public void setAlignName(short AlignName) throws COMException
    {
        put("AlignName", AlignName);
    }
        
    /**
    *
    * @return short
    **/
    public short getIconSize() throws COMException
    {
        return ((Short)get("IconSize")).shortValue();
    }
        
    /**
    *
    * @param IconSize
    **/
    public void setIconSize(short IconSize) throws COMException
    {
        put("IconSize", IconSize);
    }
        
    /**
    *
    
    * @return void
    **/
    public void Delete() throws COMException
    {
      
		invokeN("Delete", new Object[] {});
        
    }
    /**
    *
    
    * @param FileName
    * @return void
    **/
    public void ImportIcon(String FileName) throws COMException
    {
      
		invokeN("ImportIcon", new Object[] {FileName});
        
    }
    /**
    *
    
    * @param FileName
    * @param Flags
    * @param TransparentRGB
    * @return void
    **/
    public void ExportIcon(String FileName,short Flags,Variant TransparentRGB) throws COMException
    {
      
		invokeN("ExportIcon", new Object[] {FileName, new Short(Flags), TransparentRGB});
        
    }
    /**
    *
    * @return int
    **/
    public int getID() throws COMException
    {
        return ((Integer)get("ID")).intValue();
    }
        
    /**
    *
    
    * @return void
    **/
    public IVWindow OpenIconWindow() throws COMException
    {
      IVWindow res = new IVWindow();
          DispatchPtr dispPtr = (DispatchPtr)invokeN("OpenIconWindow", new Object[] {});
          res.stealUnknown(dispPtr);
          return res;
        
    }
    /**
    *
    * @return String
    **/
    public String getTargetDocumentName() throws COMException
    {
         return (String)get("TargetDocumentName");
    }
        
    /**
    *
    * @param TargetDocumentName
    **/
    public void setTargetDocumentName(String TargetDocumentName) throws COMException
    {
        put("TargetDocumentName", TargetDocumentName);
    }
        
    /**
    *
    * @return String
    **/
    public String getTargetMasterName() throws COMException
    {
         return (String)get("TargetMasterName");
    }
        
    /**
    *
    * @param TargetMasterName
    **/
    public void setTargetMasterName(String TargetMasterName) throws COMException
    {
        put("TargetMasterName", TargetMasterName);
    }
        
    /**
    *
    * @return String
    **/
    public String getTargetBaseID() throws COMException
    {
         return (String)get("TargetBaseID");
    }
        
    /**
    *
    * @param TargetBaseID
    **/
    public void setTargetBaseID(String TargetBaseID) throws COMException
    {
        put("TargetBaseID", TargetBaseID);
    }
        
    /**
    *
    * @return String
    **/
    public String getDropActions() throws COMException
    {
         return (String)get("DropActions");
    }
        
    /**
    *
    * @param DropActions
    **/
    public void setDropActions(String DropActions) throws COMException
    {
        put("DropActions", DropActions);
    }
        
    /**
    *
    * @return String
    **/
    public String getShapeHelp() throws COMException
    {
         return (String)get("ShapeHelp");
    }
        
    /**
    *
    * @param ShapeHelp
    **/
    public void setShapeHelp(String ShapeHelp) throws COMException
    {
        put("ShapeHelp", ShapeHelp);
    }
        
    /**
    *
    * @return String
    **/
    public String getNameU() throws COMException
    {
         return (String)get("NameU");
    }
        
    /**
    *
    * @param NameU
    **/
    public void setNameU(String NameU) throws COMException
    {
        put("NameU", NameU);
    }
        
    /**
    *
    * @return short
    **/
    public short getIndexInStencil() throws COMException
    {
        return ((Short)get("IndexInStencil")).shortValue();
    }
        
    /**
    *
    * @return void
    **/
    public void getIcon() throws COMException
    {
        get("Icon");
    }
        
          //for  DISPATCH_PROPERTYPUTREF not implemented
        
    /**
    *
    * @param IndexInStencil
    **/
    public void setIndexInStencil(short IndexInStencil) throws COMException
    {
        put("IndexInStencil", IndexInStencil);
    }
        
}

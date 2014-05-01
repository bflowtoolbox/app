
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVMenuItem
 * Description: 
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVMenuItem extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0212-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVMenuItem.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVMenuItem (it is required by Jawin for some internal working though).
	 */
	public IVMenuItem() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVMenuItem interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVMenuItem(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVMenuItem interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVMenuItem(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVMenuItem interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVMenuItem interface on.
	 */
	public IVMenuItem(COMPtr comObject) throws COMException {
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
    public void Delete() throws COMException
    {
      
		invokeN("Delete", new Object[] {});
        
    }
    /**
    *
    * @return String
    **/
    public String getDefault() throws COMException
    {
         return (String)get("Default");
    }
        
    /**
    *
    * @return int
    **/
    public int getIndex() throws COMException
    {
        return ((Integer)get("Index")).intValue();
    }
        
    /**
    *
    * @return void
    **/
    public IVMenuItems getMenuItems() throws COMException
    {
        IVMenuItems res = new IVMenuItems();
          DispatchPtr dispPtr = (DispatchPtr)get("MenuItems");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return void
    **/
    public IVMenuItems getParent() throws COMException
    {
        IVMenuItems res = new IVMenuItems();
          DispatchPtr dispPtr = (DispatchPtr)get("Parent");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @param CmdNum
    **/
    public void setCmdNum(short CmdNum) throws COMException
    {
        put("CmdNum", CmdNum);
    }
        
    /**
    *
    * @return short
    **/
    public short getCmdNum() throws COMException
    {
        return ((Short)get("CmdNum")).shortValue();
    }
        
    /**
    *
    * @param HelpContextID
    **/
    public void setHelpContextID(short HelpContextID) throws COMException
    {
        put("HelpContextID", HelpContextID);
    }
        
    /**
    *
    * @return short
    **/
    public short getHelpContextID() throws COMException
    {
        return ((Short)get("HelpContextID")).shortValue();
    }
        
    /**
    *
    * @return short
    **/
    public short getIsSeparator() throws COMException
    {
        return ((Short)get("IsSeparator")).shortValue();
    }
        
    /**
    *
    * @return short
    **/
    public short getIsHierarchical() throws COMException
    {
        return ((Short)get("IsHierarchical")).shortValue();
    }
        
    /**
    *
    * @param Caption
    **/
    public void setCaption(String Caption) throws COMException
    {
        put("Caption", Caption);
    }
        
    /**
    *
    * @return String
    **/
    public String getCaption() throws COMException
    {
         return (String)get("Caption");
    }
        
    /**
    *
    * @param ActionText
    **/
    public void setActionText(String ActionText) throws COMException
    {
        put("ActionText", ActionText);
    }
        
    /**
    *
    * @return String
    **/
    public String getActionText() throws COMException
    {
         return (String)get("ActionText");
    }
        
    /**
    *
    * @param MiniHelp
    **/
    public void setMiniHelp(String MiniHelp) throws COMException
    {
        put("MiniHelp", MiniHelp);
    }
        
    /**
    *
    * @return String
    **/
    public String getMiniHelp() throws COMException
    {
         return (String)get("MiniHelp");
    }
        
    /**
    *
    * @param AddOnName
    **/
    public void setAddOnName(String AddOnName) throws COMException
    {
        put("AddOnName", AddOnName);
    }
        
    /**
    *
    * @return String
    **/
    public String getAddOnName() throws COMException
    {
         return (String)get("AddOnName");
    }
        
    /**
    *
    * @param AddOnArgs
    **/
    public void setAddOnArgs(String AddOnArgs) throws COMException
    {
        put("AddOnArgs", AddOnArgs);
    }
        
    /**
    *
    * @return String
    **/
    public String getAddOnArgs() throws COMException
    {
         return (String)get("AddOnArgs");
    }
        
    /**
    *
    * @param HelpFile
    **/
    public void setHelpFile(String HelpFile) throws COMException
    {
        put("HelpFile", HelpFile);
    }
        
    /**
    *
    * @return String
    **/
    public String getHelpFile() throws COMException
    {
         return (String)get("HelpFile");
    }
        
    /**
    *
    * @param CntrlType
    **/
    public void setCntrlType(short CntrlType) throws COMException
    {
        put("CntrlType", CntrlType);
    }
        
    /**
    *
    * @return short
    **/
    public short getCntrlType() throws COMException
    {
        return ((Short)get("CntrlType")).shortValue();
    }
        
    /**
    *
    * @param CntrlID
    **/
    public void setCntrlID(short CntrlID) throws COMException
    {
        put("CntrlID", CntrlID);
    }
        
    /**
    *
    * @return short
    **/
    public short getCntrlID() throws COMException
    {
        return ((Short)get("CntrlID")).shortValue();
    }
        
    /**
    *
    * @param TypeSpecific1
    **/
    public void setTypeSpecific1(short TypeSpecific1) throws COMException
    {
        put("TypeSpecific1", TypeSpecific1);
    }
        
    /**
    *
    * @return short
    **/
    public short getTypeSpecific1() throws COMException
    {
        return ((Short)get("TypeSpecific1")).shortValue();
    }
        
    /**
    *
    * @param Priority
    **/
    public void setPriority(short Priority) throws COMException
    {
        put("Priority", Priority);
    }
        
    /**
    *
    * @return short
    **/
    public short getPriority() throws COMException
    {
        return ((Short)get("Priority")).shortValue();
    }
        
    /**
    *
    * @param Spacing
    **/
    public void setSpacing(short Spacing) throws COMException
    {
        put("Spacing", Spacing);
    }
        
    /**
    *
    * @return short
    **/
    public short getSpacing() throws COMException
    {
        return ((Short)get("Spacing")).shortValue();
    }
        
    /**
    *
    * @param TypeSpecific2
    **/
    public void setTypeSpecific2(short TypeSpecific2) throws COMException
    {
        put("TypeSpecific2", TypeSpecific2);
    }
        
    /**
    *
    * @return short
    **/
    public short getTypeSpecific2() throws COMException
    {
        return ((Short)get("TypeSpecific2")).shortValue();
    }
        
    /**
    *
    
    * @param IconFileName
    * @return void
    **/
    public void IconFileName(String IconFileName) throws COMException
    {
      
		invokeN("IconFileName", new Object[] {IconFileName});
        
    }
    /**
    *
    * @return boolean
    **/
    public boolean getBuiltIn() throws COMException
    {
        return ((Boolean)get("BuiltIn")).booleanValue();
    }
        
    /**
    *
    * @param Enabled
    **/
    public void setEnabled(boolean Enabled) throws COMException
    {
        put("Enabled", Enabled);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getEnabled() throws COMException
    {
        return ((Boolean)get("Enabled")).booleanValue();
    }
        
    /**
    *
    * @param FaceID
    **/
    public void setFaceID(short FaceID) throws COMException
    {
        put("FaceID", FaceID);
    }
        
    /**
    *
    * @return short
    **/
    public short getFaceID() throws COMException
    {
        return ((Short)get("FaceID")).shortValue();
    }
        
    /**
    *
    * @param State
    **/
    public void setState(short State) throws COMException
    {
        put("State", State);
    }
        
    /**
    *
    * @return short
    **/
    public short getState() throws COMException
    {
        return ((Short)get("State")).shortValue();
    }
        
    /**
    *
    * @param Style
    **/
    public void setStyle(short Style) throws COMException
    {
        put("Style", Style);
    }
        
    /**
    *
    * @return short
    **/
    public short getStyle() throws COMException
    {
        return ((Short)get("Style")).shortValue();
    }
        
    /**
    *
    * @param Visible
    **/
    public void setVisible(boolean Visible) throws COMException
    {
        put("Visible", Visible);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getVisible() throws COMException
    {
        return ((Boolean)get("Visible")).booleanValue();
    }
        
    /**
    *
    * @param Width
    **/
    public void setWidth(short Width) throws COMException
    {
        put("Width", Width);
    }
        
    /**
    *
    * @return short
    **/
    public short getWidth() throws COMException
    {
        return ((Short)get("Width")).shortValue();
    }
        
    /**
    *
    * @param PaletteWidth
    **/
    public void setPaletteWidth(short PaletteWidth) throws COMException
    {
        put("PaletteWidth", PaletteWidth);
    }
        
    /**
    *
    * @return short
    **/
    public short getPaletteWidth() throws COMException
    {
        return ((Short)get("PaletteWidth")).shortValue();
    }
        
    /**
    *
    * @param BeginGroup
    **/
    public void setBeginGroup(boolean BeginGroup) throws COMException
    {
        put("BeginGroup", BeginGroup);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getBeginGroup() throws COMException
    {
        return ((Boolean)get("BeginGroup")).booleanValue();
    }
        
}


package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVGraphicItem
 * Description: 
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVGraphicItem extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0735-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVGraphicItem.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVGraphicItem (it is required by Jawin for some internal working though).
	 */
	public IVGraphicItem() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVGraphicItem interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVGraphicItem(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVGraphicItem interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVGraphicItem(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVGraphicItem interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVGraphicItem interface on.
	 */
	public IVGraphicItem(COMPtr comObject) throws COMException {
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
    * @return int
    **/
    public int getObjectType() throws COMException
    {
        return ((Integer)get("ObjectType")).intValue();
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
    * @return int
    **/
    public int getIndex() throws COMException
    {
        return ((Integer)get("Index")).intValue();
    }
        
    /**
    *
    * @param Index
    **/
    public void setIndex(int Index) throws COMException
    {
        put("Index", Index);
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
    * @return String
    **/
    public String getDescription() throws COMException
    {
         return (String)get("Description");
    }
        
    /**
    *
    * @return int
    **/
    public int getType() throws COMException
    {
        return ((Integer)get("Type")).intValue();
    }
        
    /**
    *
    
    * @return void
    **/
    public void GetExpression(int[] Field,String[] Expression) throws COMException
    {
      
		invokeN("GetExpression", new Object[] {Field, Expression});
        
    }
    /**
    *
    
    * @param Field
    * @param Expression
    * @return void
    **/
    public void SetExpression(int Field,String Expression) throws COMException
    {
      
		invokeN("SetExpression", new Object[] {new Integer(Field), Expression});
        
    }
    /**
    *
    * @return void
    **/
    public IVMaster getDataGraphic() throws COMException
    {
        IVMaster res = new IVMaster();
          DispatchPtr dispPtr = (DispatchPtr)get("DataGraphic");
          res.stealUnknown(dispPtr);
          return res;
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
    public String getTag() throws COMException
    {
         return (String)get("Tag");
    }
        
    /**
    *
    * @param Tag
    **/
    public void setTag(String Tag) throws COMException
    {
        put("Tag", Tag);
    }
        
    /**
    *
    * @return int
    **/
    public int getHorizontalPosition() throws COMException
    {
        return ((Integer)get("HorizontalPosition")).intValue();
    }
        
    /**
    *
    * @param HorizontalPosition
    **/
    public void setHorizontalPosition(int HorizontalPosition) throws COMException
    {
        put("HorizontalPosition", HorizontalPosition);
    }
        
    /**
    *
    * @return int
    **/
    public int getVerticalPosition() throws COMException
    {
        return ((Integer)get("VerticalPosition")).intValue();
    }
        
    /**
    *
    * @param VerticalPosition
    **/
    public void setVerticalPosition(int VerticalPosition) throws COMException
    {
        put("VerticalPosition", VerticalPosition);
    }
        
    /**
    *
    * @return boolean
    **/
    public boolean getUseDataGraphicPosition() throws COMException
    {
        return ((Boolean)get("UseDataGraphicPosition")).booleanValue();
    }
        
    /**
    *
    * @param UseDataGraphicPosition
    **/
    public void setUseDataGraphicPosition(boolean UseDataGraphicPosition) throws COMException
    {
        put("UseDataGraphicPosition", UseDataGraphicPosition);
    }
        
}

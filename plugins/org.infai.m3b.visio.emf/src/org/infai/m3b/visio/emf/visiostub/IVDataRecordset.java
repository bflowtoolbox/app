
package org.infai.m3b.visio.emf.visiostub;

import java.util.Date;

import org.jawin.COMException;
import org.jawin.COMPtr;
import org.jawin.DispatchPtr;
import org.jawin.GUID;
import org.jawin.IdentityManager;


/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVDataRecordset
 * Description: 
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVDataRecordset extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d072f-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVDataRecordset.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVDataRecordset (it is required by Jawin for some internal working though).
	 */
	public IVDataRecordset() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVDataRecordset interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVDataRecordset(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVDataRecordset interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVDataRecordset(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVDataRecordset interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVDataRecordset interface on.
	 */
	public IVDataRecordset(COMPtr comObject) throws COMException {
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
    * @return short
    **/
    public short getStat() throws COMException
    {
        return ((Short)get("Stat")).shortValue();
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
    public short getObjectType() throws COMException
    {
        return ((Short)get("ObjectType")).shortValue();
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
    * @return int
    **/
    public int getLinkReplaceBehavior() throws COMException
    {
        return ((Integer)get("LinkReplaceBehavior")).intValue();
    }
        
    /**
    *
    * @param LinkReplaceBehavior
    **/
    public void setLinkReplaceBehavior(int LinkReplaceBehavior) throws COMException
    {
        put("LinkReplaceBehavior", LinkReplaceBehavior);
    }
        
    /**
    *
    * @return void
    **/
    public IVDataConnection getDataConnection() throws COMException
    {
        IVDataConnection res = new IVDataConnection();
          DispatchPtr dispPtr = (DispatchPtr)get("DataConnection");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    * @return void
    **/
    public IVDataColumns getDataColumns() throws COMException
    {
        IVDataColumns res = new IVDataColumns();
          DispatchPtr dispPtr = (DispatchPtr)get("DataColumns");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    
    * @param PrimaryKey
    * @return void
    **/
    public void GetPrimaryKey(int[] PrimaryKeySettings,Object[] PrimaryKey) throws COMException
    {
      
		invokeN("GetPrimaryKey", new Object[] {PrimaryKeySettings, PrimaryKey});
        
    }
    /**
    *
    
    * @param PrimaryKeySettings
    * @param PrimaryKey
    * @return void
    **/
    public void SetPrimaryKey(int PrimaryKeySettings,Object[] PrimaryKey) throws COMException
    {
      
		invokeN("SetPrimaryKey", new Object[] {new Integer(PrimaryKeySettings), PrimaryKey});
        
    }
    /**
    *
    * @return String
    **/
    public String getCommandString() throws COMException
    {
         return (String)get("CommandString");
    }
        
    /**
    *
    * @param CommandString
    **/
    public void setCommandString(String CommandString) throws COMException
    {
        put("CommandString", CommandString);
    }
        
    /**
    *
    * @return String
    **/
    public String getDataAsXML() throws COMException
    {
         return (String)get("DataAsXML");
    }
        
    /**
    *
    
    * @param CriteriaString
    * @return Object[]
    **/
    public Object[] GetDataRowIDs(String CriteriaString) throws COMException
    {
      
          return null;  //Object[] not implemented
        
    }
    /**
    *
    
    * @param DataRowID
    * @return Object[]
    **/
    public Object[] GetRowData(int DataRowID) throws COMException
    {
      
          return null;  //Object[] not implemented
        
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
    
    * @return void
    **/
    public void Refresh() throws COMException
    {
      
		invokeN("Refresh", new Object[] {});
        
    }
    /**
    *
    
    * @param NewDataAsXML
    * @return void
    **/
    public void RefreshUsingXML(String NewDataAsXML) throws COMException
    {
      
		invokeN("RefreshUsingXML", new Object[] {NewDataAsXML});
        
    }
    /**
    *
    * @return Date
    **/
    public Date getTimeRefreshed() throws COMException
    {
         return (Date)get("TimeRefreshed");
    }
        
    /**
    *
    * @return int
    **/
    public int getRefreshInterval() throws COMException
    {
        return ((Integer)get("RefreshInterval")).intValue();
    }
        
    /**
    *
    * @param RefreshInterval
    **/
    public void setRefreshInterval(int RefreshInterval) throws COMException
    {
        put("RefreshInterval", RefreshInterval);
    }
        
    /**
    *
    * @return int
    **/
    public int getRefreshSettings() throws COMException
    {
        return ((Integer)get("RefreshSettings")).intValue();
    }
        
    /**
    *
    * @param RefreshSettings
    **/
    public void setRefreshSettings(int RefreshSettings) throws COMException
    {
        put("RefreshSettings", RefreshSettings);
    }
        
    /**
    *
    * @return void
    **/
    public IVEventList getEventList() throws COMException
    {
        IVEventList res = new IVEventList();
          DispatchPtr dispPtr = (DispatchPtr)get("EventList");
          res.stealUnknown(dispPtr);
          return res;
    }
        
    /**
    *
    
    * @return Object[]
    **/
    public Object[] GetAllRefreshConflicts() throws COMException
    {
      
          return null;  //Object[] not implemented
        
    }
    /**
    *
    
    * @return void
    **/
    public void RemoveRefreshConflict(Object[] ShapeInConflict) throws COMException
    {
      
		invokeN("RemoveRefreshConflict", new Object[] {ShapeInConflict});
        
    }
    /**
    *
    
    * @return Object[]
    **/
    public Object[] GetMatchingRowsForRefreshConflict(Object[] ShapeInConflict) throws COMException
    {
      
          return null;  //Object[] not implemented
        
    }
}

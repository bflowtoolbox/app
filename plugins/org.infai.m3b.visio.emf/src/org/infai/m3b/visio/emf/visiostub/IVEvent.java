
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVEvent
 * Description: 
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVEvent extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d071a-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVEvent.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVEvent (it is required by Jawin for some internal working though).
	 */
	public IVEvent() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVEvent interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVEvent(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVEvent interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVEvent(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVEvent interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVEvent interface on.
	 */
	public IVEvent(COMPtr comObject) throws COMException {
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
    public IVEventList getEventList() throws COMException
    {
        IVEventList res = new IVEventList();
          DispatchPtr dispPtr = (DispatchPtr)get("EventList");
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
    public short getEvent() throws COMException
    {
        return ((Short)get("Event")).shortValue();
    }
        
    /**
    *
    * @param Event
    **/
    public void setEvent(short Event) throws COMException
    {
        put("Event", Event);
    }
        
    /**
    *
    * @return short
    **/
    public short getAction() throws COMException
    {
        return ((Short)get("Action")).shortValue();
    }
        
    /**
    *
    * @param Action
    **/
    public void setAction(short Action) throws COMException
    {
        put("Action", Action);
    }
        
    /**
    *
    * @return String
    **/
    public String getTarget() throws COMException
    {
         return (String)get("Target");
    }
        
    /**
    *
    * @param Target
    **/
    public void setTarget(String Target) throws COMException
    {
        put("Target", Target);
    }
        
    /**
    *
    * @return String
    **/
    public String getTargetArgs() throws COMException
    {
         return (String)get("TargetArgs");
    }
        
    /**
    *
    * @param TargetArgs
    **/
    public void setTargetArgs(String TargetArgs) throws COMException
    {
        put("TargetArgs", TargetArgs);
    }
        
    /**
    *
    
    * @param ContextString
    * @return void
    **/
    public void Trigger(String ContextString) throws COMException
    {
      
		invokeN("Trigger", new Object[] {ContextString});
        
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
    * @return short
    **/
    public short getEnabled() throws COMException
    {
        return ((Short)get("Enabled")).shortValue();
    }
        
    /**
    *
    * @param Enabled
    **/
    public void setEnabled(short Enabled) throws COMException
    {
        put("Enabled", Enabled);
    }
        
    /**
    *
    * @return short
    **/
    public short getPersistable() throws COMException
    {
        return ((Short)get("Persistable")).shortValue();
    }
        
    /**
    *
    * @return short
    **/
    public short getPersistent() throws COMException
    {
        return ((Short)get("Persistent")).shortValue();
    }
        
    /**
    *
    * @param Persistent
    **/
    public void setPersistent(short Persistent) throws COMException
    {
        put("Persistent", Persistent);
    }
        
    /**
    *
    
    * @return Object[]
    **/
    public Object[] GetFilterObjects() throws COMException
    {
      
          return null;  //Object[] not implemented
        
    }
    /**
    *
    
    * @param Objects
    * @return void
    **/
    public void SetFilterObjects(Object[] Objects) throws COMException
    {
      
		invokeN("SetFilterObjects", new Object[] {Objects});
        
    }
    /**
    *
    
    * @return Object[]
    **/
    public Object[] GetFilterCommands() throws COMException
    {
      
          return null;  //Object[] not implemented
        
    }
    /**
    *
    
    * @param Commands
    * @return void
    **/
    public void SetFilterCommands(Object[] Commands) throws COMException
    {
      
		invokeN("SetFilterCommands", new Object[] {Commands});
        
    }
    /**
    *
    
    * @return Object[]
    **/
    public Object[] GetFilterSRC() throws COMException
    {
      
          return null;  //Object[] not implemented
        
    }
    /**
    *
    
    * @param SRCStream
    * @return void
    **/
    public void SetFilterSRC(Object[] SRCStream) throws COMException
    {
      
		invokeN("SetFilterSRC", new Object[] {SRCStream});
        
    }
    /**
    *
    
    * @return Object[]
    **/
    public Object[] GetFilterActions() throws COMException
    {
      
          return null;  //Object[] not implemented
        
    }
    /**
    *
    
    * @param filterActionStream
    * @return void
    **/
    public void SetFilterActions(Object[] filterActionStream) throws COMException
    {
      
		invokeN("SetFilterActions", new Object[] {filterActionStream});
        
    }
}

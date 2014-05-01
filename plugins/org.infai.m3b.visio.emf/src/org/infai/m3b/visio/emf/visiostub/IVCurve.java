
package org.infai.m3b.visio.emf.visiostub;

import org.jawin.*;






/**
 * Jawin generated code please do not edit
 *
 * Dispatch: IVCurve
 * Description: 
 * Help file:   C:\Program Files\Microsoft Office\Office12\Vis_SDR.CHM
 *
 * @author JawinTypeBrowser
 */

public class IVCurve extends DispatchPtr {
	public static final GUID DIID = new GUID("{000d0722-0000-0000-C000-000000000046}");
	public static final int IID_TOKEN;
	static {
		IID_TOKEN = IdentityManager.registerProxy(DIID, IVCurve.class);
	}

	/**
	 * The required public no arg constructor.
	 * <br><br>
	 * <b>Important:</b>Should never be used as this creates an uninitialized
	 * IVCurve (it is required by Jawin for some internal working though).
	 */
	public IVCurve() {
		super();
	}

	/**
	 * For creating a new COM-object with the given progid and with 
	 * the IVCurve interface.
	 * 
	 * @param progid the progid of the COM-object to create.
	 */
	public IVCurve(String progid) throws COMException {
		super(progid, DIID);
	}

	/**
	 * For creating a new COM-object with the given clsid and with 
	 * the IVCurve interface.
	 * 
	 * @param clsid the GUID of the COM-object to create.
	 */
	public IVCurve(GUID clsid) throws COMException {
		super(clsid, DIID);
	}

	/**
	 * For getting the IVCurve interface on an existing COM-object.
	 * This is an alternative to calling {@link #queryInterface(Class)}
	 * on comObject.
	 * 
	 * @param comObject the COM-object to get the IVCurve interface on.
	 */
	public IVCurve(COMPtr comObject) throws COMException {
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
    public short getObjectType() throws COMException
    {
        return ((Short)get("ObjectType")).shortValue();
    }
        
    /**
    *
    * @return short
    **/
    public short getClosed() throws COMException
    {
        return ((Short)get("Closed")).shortValue();
    }
        
    /**
    *
    
    * @param Tolerance
    * @param xyArray
    * @return void
    **/
    public void Points(double Tolerance,Object[] xyArray) throws COMException
    {
      
		invokeN("Points", new Object[] {new Double(Tolerance), xyArray});
        
    }
    /**
    *
    * @return double
    **/
    public double getStart() throws COMException
    {
         return ((Double)get("Start")).doubleValue();
    }
        
    /**
    *
    * @return double
    **/
    public double getEnd() throws COMException
    {
         return ((Double)get("End")).doubleValue();
    }
        
    /**
    *
    
    * @param t
    * @return void
    **/
    public void Point(double t,double[] x,double[] y) throws COMException
    {
      
		invokeN("Point", new Object[] {new Double(t), x, y});
        
    }
    /**
    *
    
    * @param t
    * @param n
    * @return void
    **/
    public void PointAndDerivatives(double t,short n,double[] x,double[] y,double[] dxdt,double[] dydt,double[] ddxdt,double[] ddydt) throws COMException
    {
      
		invokeN("PointAndDerivatives", new Object[] {new Double(t), new Short(n), x, y, dxdt, dydt, ddxdt, ddydt});
        
    }
}

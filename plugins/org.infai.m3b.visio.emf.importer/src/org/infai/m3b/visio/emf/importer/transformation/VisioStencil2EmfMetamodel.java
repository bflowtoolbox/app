package org.infai.m3b.visio.emf.importer.transformation;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.infai.m3b.visio.emf.emfutil.EmfUtil;
import org.infai.m3b.visio.emf.emfutil.EmfVisioMetamodel;
import org.infai.m3b.visio.emf.visiostub.IVApplication;
import org.infai.m3b.visio.emf.visiostub.IVDocument;
import org.infai.m3b.visio.emf.visiostub.IVMaster;
import org.infai.m3b.visio.emf.visioutil.VisioList;
import org.infai.m3b.visio.emf.visioutil.VisioProperty;
import org.infai.m3b.visio.emf.visioutil.VisioStencilUtil;
import org.infai.m3b.visio.emf.visioutil.VisioUtil;
import org.jawin.COMException;

public class VisioStencil2EmfMetamodel {
	
	private EcoreFactory ecoreFactory;
	private EcorePackage ecorePackage;
	
	private IVApplication visioApp;
	private EmfVisioMetamodel evm;
	
	
	public VisioStencil2EmfMetamodel(IVApplication visioApp, EmfVisioMetamodel emfVisioMetamodel) {
		
		ecoreFactory = EcoreFactory.eINSTANCE;
		ecorePackage = EcorePackage.eINSTANCE;
				
		this.visioApp = visioApp;
		this.evm = emfVisioMetamodel;
	}
	
	//creates the EMF metamodel
	 public EPackage createEmfMetamodel(IVDocument visioStencil) {
		
		 EPackage emfStencilPackage = EcoreFactory.eINSTANCE.createEPackage();
		
		try {
			//String stencilTitle = EmfStringCorrector.getString(stencil.getFullName());
			String stencilTitle = EmfUtil.correctIdentifier(visioStencil.getTitle());
			
			if(stencilTitle.isEmpty())
				stencilTitle = EmfUtil.correctIdentifier(visioStencil.getName());
			
			//foreach Visio stencil an EPackage is created
			emfStencilPackage = createEmfStencil(visioStencil);
						
			//foreach Visio master (2-dimensional) an EClass is created
			VisioList<IVMaster> visioMasters = new VisioList<IVMaster>(visioStencil.getMasters(), IVMaster.class);
			for(IVMaster visioMaster : visioMasters) {
				
				EClass emfMaster = createEmfMaster(visioMaster);
				emfStencilPackage.getEClassifiers().add(emfMaster);
			}
			
		} catch (COMException e) {
			VisioUtil.closeVisioApplication(this.visioApp);
			e.printStackTrace();
		}
		
		return emfStencilPackage;
	}
	 
	protected EClass createEmfMaster(IVMaster visioMaster) {
		
		VisioStencilUtil sr = new VisioStencilUtil(this.visioApp);
		EClass emfMaster = null;
		
		try {	
			emfMaster = ecoreFactory.createEClass();
			
			emfMaster.setName(EmfUtil.correctIdentifier(visioMaster.getName()));
			
			emfMaster.getEAnnotations().add(EmfUtil.createAnnotation("visio", "UID", visioMaster.getUniqueID()));
			emfMaster.getEAnnotation("visio").getDetails().put("name", visioMaster.getName());
			emfMaster.getEAnnotation("visio").getDetails().put("nameU", visioMaster.getNameU());
			
			if(sr.isConnectionMaster(visioMaster)) {
				
				EClass conClass = this.evm.getEVisioConnectinShape();
				emfMaster.getESuperTypes().add(conClass);
			}	
			else {
				EClass shapeClass = this.evm.getEVisioShape();
				emfMaster.getESuperTypes().add(shapeClass);
			}
				
				
			//foearch Visio property an EAttribute is created
			ArrayList<VisioProperty> visioPropertyList = sr.getMasterProperty(visioMaster);
			for(VisioProperty visioProperty : visioPropertyList) {
				
				EAttribute attribute = ecoreFactory.createEAttribute();
				emfMaster.getEStructuralFeatures().add(attribute);
				attribute.setName(EmfUtil.correctIdentifier(visioProperty.getName()));
				attribute.setEType(ecorePackage.getEString());
				attribute.getEAnnotations().add(EmfUtil.createAnnotation("visio", "propertyId", visioProperty.getId()));
				
				if(!visioProperty.getValue().isEmpty()) 
					attribute.setDefaultValue(visioProperty.getValue());	
			}
		
		} catch (COMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return emfMaster;
	}
	
	protected EPackage createEmfStencil(IVDocument visioStencil) {
		
		//String stencilTitle = EmfStringCorrector.getString(stencil.getFullName());
		String stencilTitle = new String();
		EPackage emfStencilPackage = ecoreFactory.createEPackage();
		
		try {
			stencilTitle = visioStencil.getTitle();
			
			if(stencilTitle.isEmpty())
				stencilTitle = visioStencil.getName();
			
			stencilTitle = EmfUtil.correctIdentifier(stencilTitle);
			
			//foreach Visio stencil an EPackage is created
			emfStencilPackage.setName(stencilTitle);
			emfStencilPackage.setNsPrefix(stencilTitle);
			emfStencilPackage.setNsURI("http://infai.org/visio2emf/" + stencilTitle);
			emfStencilPackage.getEAnnotations().add(EmfUtil.createAnnotation("visio", "title", visioStencil.getTitle()));
			emfStencilPackage.getEAnnotation("visio").getDetails().put("name", visioStencil.getName());
			emfStencilPackage.getEAnnotation("visio").getDetails().put("path", visioStencil.getFullName());
			
		} catch (COMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return emfStencilPackage;
	}
	
}

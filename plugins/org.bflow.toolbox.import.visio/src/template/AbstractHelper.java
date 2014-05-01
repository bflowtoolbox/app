package template;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.bflow.toolbox.imports.visio.VisioActivator;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.gmf.runtime.notation.Diagram;

/**
 * @author Christian Boehme
 * 
 */
public abstract class AbstractHelper {
		
		protected static EPackage standardDocumentStencilPackage;

		public static void writeToFile(Diagram diagram, String fileName,
				String targetPath, String extension) {

			// creating an ResourceSet
			ResourceSet resourceSet = new ResourceSetImpl();
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
					.put("*", new XMIResourceFactoryImpl());

			// create new Resource for both models
			Resource modelResource = resourceSet.createResource(URI
					.createURI(fileName + "." + extension));
			modelResource.getContents().add(diagram.getElement());
			modelResource.getContents().add(diagram);

			// writing Resource to file
			try {

				File modelFile = new File(targetPath + fileName + "." + extension);
				new File(targetPath).mkdirs();
				modelFile.createNewFile();
				FileOutputStream modelOutput = new FileOutputStream(modelFile);
				modelResource.save(modelOutput, null);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public static void loadMetamodel(String metamodelPath, VisioActivator ac){
			URL url = FileLocator.find(ac.getBundle(), new Path(metamodelPath), null);
			URL metaModelURL;
			try {
				metaModelURL = FileLocator.toFileURL(url);
				File metamodel = new File(metaModelURL.getFile());
				
				//get a new ResourceSet of which the resource can be created
				ResourceSet set = new ResourceSetImpl();
				//bind the file-extension to a factory
				set.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new XMIResourceFactoryImpl());
				//create the resource out of the file
				Resource res = set.createResource(URI.createFileURI(metamodel.getAbsolutePath()));
				//trying to load the contents of the model into the resource
				res.load(null);
				
				//the package has one subpackage, the documentStencil package
				standardDocumentStencilPackage = ((EPackage)res.getContents().get(0)).getESubpackages().get(0);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public static List<EObject> selectCorrectShapes(List<EObject> allShapes, String packageName){
			
			List<EObject> correctShapes = new LinkedList<EObject>();
			if (!allShapes.isEmpty()) {
				((EPackage)allShapes.get(0).eClass().eContainer()).setName(packageName);
				for (int i = 0; i < allShapes.size(); i++) {
					if (isInOurStencil(((EObject) allShapes.get(i)))) {
						correctShapes.add((EObject) allShapes.get(i));
					}
				}
			}
			return correctShapes;
		}
		
		private static boolean isInOurStencil(EObject shapeEObject){
			EClass shapeEclass = shapeEObject.eClass();
			String shapeUniqueId = shapeEclass.getEAnnotation("visio").getDetails().get("uniqueId");
			
			for (int i = 0; i < standardDocumentStencilPackage.getEClassifiers().size(); i++) {
				EClass shapeStandardEClass = (EClass) standardDocumentStencilPackage.getEClassifiers().get(i);
				String standardId = shapeStandardEClass.getEAnnotation("visio").getDetails().get("uniqueId");
				if (shapeUniqueId.equals(standardId) && shapeEclass.getName().equals(shapeStandardEClass.getName())) {
					return true;
				}
			}
			System.out.println("EObject " + shapeEObject + " not in our standard stencil!");
			return false;
		}


	}

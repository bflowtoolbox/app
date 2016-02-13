/*
 * 
 */
package vcchart.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;

import vcchart.VcchartPackage;
import vcchart.diagram.edit.parts.Activity1NameEditPart;
import vcchart.diagram.edit.parts.Activity2NameEditPart;
import vcchart.diagram.edit.parts.ApplicationNameEditPart;
import vcchart.diagram.edit.parts.ClusterNameEditPart;
import vcchart.diagram.edit.parts.DocumentNameEditPart;
import vcchart.diagram.edit.parts.ObjectiveNameEditPart;
import vcchart.diagram.edit.parts.ParticipantNameEditPart;
import vcchart.diagram.edit.parts.ProductNameEditPart;
import vcchart.diagram.edit.parts.TechnicalTermNameEditPart;
import vcchart.diagram.parsers.MessageFormatParser;
import vcchart.diagram.part.VcVisualIDRegistry;

/**
 * @generated
 */
public class VcParserProvider extends AbstractProvider implements
		IParserProvider {

	/**
	 * @generated
	 */
	private IParser productName_5001Parser;

	/**
	 * @generated
	 */
	private IParser getProductName_5001Parser() {
		if (productName_5001Parser == null) {
			EAttribute[] features = new EAttribute[] { VcchartPackage.eINSTANCE
					.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			productName_5001Parser = parser;
		}
		return productName_5001Parser;
	}

	/**
	 * @generated
	 */
	private IParser objectiveName_5002Parser;

	/**
	 * @generated
	 */
	private IParser getObjectiveName_5002Parser() {
		if (objectiveName_5002Parser == null) {
			EAttribute[] features = new EAttribute[] { VcchartPackage.eINSTANCE
					.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			objectiveName_5002Parser = parser;
		}
		return objectiveName_5002Parser;
	}

	/**
	 * @generated
	 */
	private IParser activity1Name_5003Parser;

	/**
	 * @generated
	 */
	private IParser getActivity1Name_5003Parser() {
		if (activity1Name_5003Parser == null) {
			EAttribute[] features = new EAttribute[] { VcchartPackage.eINSTANCE
					.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			activity1Name_5003Parser = parser;
		}
		return activity1Name_5003Parser;
	}

	/**
	 * @generated
	 */
	private IParser activity2Name_5004Parser;

	/**
	 * @generated
	 */
	private IParser getActivity2Name_5004Parser() {
		if (activity2Name_5004Parser == null) {
			EAttribute[] features = new EAttribute[] { VcchartPackage.eINSTANCE
					.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			activity2Name_5004Parser = parser;
		}
		return activity2Name_5004Parser;
	}

	/**
	 * @generated
	 */
	private IParser clusterName_5005Parser;

	/**
	 * @generated
	 */
	private IParser getClusterName_5005Parser() {
		if (clusterName_5005Parser == null) {
			EAttribute[] features = new EAttribute[] { VcchartPackage.eINSTANCE
					.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			clusterName_5005Parser = parser;
		}
		return clusterName_5005Parser;
	}

	/**
	 * @generated
	 */
	private IParser technicalTermName_5006Parser;

	/**
	 * @generated
	 */
	private IParser getTechnicalTermName_5006Parser() {
		if (technicalTermName_5006Parser == null) {
			EAttribute[] features = new EAttribute[] { VcchartPackage.eINSTANCE
					.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			technicalTermName_5006Parser = parser;
		}
		return technicalTermName_5006Parser;
	}

	/**
	 * @generated
	 */
	private IParser participantName_5007Parser;

	/**
	 * @generated
	 */
	private IParser getParticipantName_5007Parser() {
		if (participantName_5007Parser == null) {
			EAttribute[] features = new EAttribute[] { VcchartPackage.eINSTANCE
					.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			participantName_5007Parser = parser;
		}
		return participantName_5007Parser;
	}

	/**
	 * @generated
	 */
	private IParser applicationName_5008Parser;

	/**
	 * @generated
	 */
	private IParser getApplicationName_5008Parser() {
		if (applicationName_5008Parser == null) {
			EAttribute[] features = new EAttribute[] { VcchartPackage.eINSTANCE
					.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			applicationName_5008Parser = parser;
		}
		return applicationName_5008Parser;
	}

	/**
	 * @generated
	 */
	private IParser documentName_5009Parser;

	/**
	 * @generated
	 */
	private IParser getDocumentName_5009Parser() {
		if (documentName_5009Parser == null) {
			EAttribute[] features = new EAttribute[] { VcchartPackage.eINSTANCE
					.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			documentName_5009Parser = parser;
		}
		return documentName_5009Parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case ProductNameEditPart.VISUAL_ID:
			return getProductName_5001Parser();
		case ObjectiveNameEditPart.VISUAL_ID:
			return getObjectiveName_5002Parser();
		case Activity1NameEditPart.VISUAL_ID:
			return getActivity1Name_5003Parser();
		case Activity2NameEditPart.VISUAL_ID:
			return getActivity2Name_5004Parser();
		case ClusterNameEditPart.VISUAL_ID:
			return getClusterName_5005Parser();
		case TechnicalTermNameEditPart.VISUAL_ID:
			return getTechnicalTermName_5006Parser();
		case ParticipantNameEditPart.VISUAL_ID:
			return getParticipantName_5007Parser();
		case ApplicationNameEditPart.VISUAL_ID:
			return getApplicationName_5008Parser();
		case DocumentNameEditPart.VISUAL_ID:
			return getDocumentName_5009Parser();
		}
		return null;
	}

	/**
	 * Utility method that consults ParserService
	 * @generated
	 */
	public static IParser getParser(IElementType type, EObject object,
			String parserHint) {
		return ParserService.getInstance().getParser(
				new HintAdapter(type, object, parserHint));
	}

	/**
	 * @generated
	 */
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(VcVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(VcVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (VcElementTypes.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	 * @generated
	 */
	private static class HintAdapter extends ParserHintAdapter {

		/**
		 * @generated
		 */
		private final IElementType elementType;

		/**
		 * @generated
		 */
		public HintAdapter(IElementType type, EObject object, String parserHint) {
			super(object, parserHint);
			assert type != null;
			elementType = type;
		}

		/**
		 * @generated
		 */
		public Object getAdapter(Class adapter) {
			if (IElementType.class.equals(adapter)) {
				return elementType;
			}
			return super.getAdapter(adapter);
		}
	}

}

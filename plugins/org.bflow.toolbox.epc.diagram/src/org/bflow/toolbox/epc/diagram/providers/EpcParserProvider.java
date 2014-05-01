package org.bflow.toolbox.epc.diagram.providers;

import org.bflow.toolbox.bflow.BflowPackage;
import org.bflow.toolbox.epc.diagram.edit.parts.ApplicationNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.CardFileNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ClusterNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.DocumentNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.EventNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ExternalPersonNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.FileNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.FunctionNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.GroupNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.InternalPersonNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.LocationNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ObjectiveNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ParticipantNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.PersonTypeNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.PositionNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ProcessInterfaceNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ProductNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.TechnicalTermNameEditPart;
import org.bflow.toolbox.epc.diagram.parsers.MessageFormatParser;
import org.bflow.toolbox.epc.diagram.part.EpcVisualIDRegistry;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class EpcParserProvider extends AbstractProvider implements
		IParserProvider {

	/**
	 * @generated
	 */
	private IParser participantName_5001Parser;

	/**
	 * @generated
	 */
	private IParser getParticipantName_5001Parser() {
		if (participantName_5001Parser == null) {
			participantName_5001Parser = createParticipantName_5001Parser();
		}
		return participantName_5001Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createParticipantName_5001Parser() {
		EAttribute[] features = new EAttribute[] { BflowPackage.eINSTANCE
				.getBflowSymbol_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser applicationName_5002Parser;

	/**
	 * @generated
	 */
	private IParser getApplicationName_5002Parser() {
		if (applicationName_5002Parser == null) {
			applicationName_5002Parser = createApplicationName_5002Parser();
		}
		return applicationName_5002Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createApplicationName_5002Parser() {
		EAttribute[] features = new EAttribute[] { BflowPackage.eINSTANCE
				.getBflowSymbol_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser processInterfaceName_5003Parser;

	/**
	 * @generated
	 */
	private IParser getProcessInterfaceName_5003Parser() {
		if (processInterfaceName_5003Parser == null) {
			processInterfaceName_5003Parser = createProcessInterfaceName_5003Parser();
		}
		return processInterfaceName_5003Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createProcessInterfaceName_5003Parser() {
		EAttribute[] features = new EAttribute[] { BflowPackage.eINSTANCE
				.getBflowSymbol_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser eventName_5004Parser;

	/**
	 * @generated
	 */
	private IParser getEventName_5004Parser() {
		if (eventName_5004Parser == null) {
			eventName_5004Parser = createEventName_5004Parser();
		}
		return eventName_5004Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createEventName_5004Parser() {
		EAttribute[] features = new EAttribute[] { BflowPackage.eINSTANCE
				.getBflowSymbol_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser functionName_5005Parser;

	/**
	 * @generated
	 */
	private IParser getFunctionName_5005Parser() {
		if (functionName_5005Parser == null) {
			functionName_5005Parser = createFunctionName_5005Parser();
		}
		return functionName_5005Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createFunctionName_5005Parser() {
		EAttribute[] features = new EAttribute[] { BflowPackage.eINSTANCE
				.getBflowSymbol_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser groupName_5006Parser;

	/**
	 * @generated
	 */
	private IParser getGroupName_5006Parser() {
		if (groupName_5006Parser == null) {
			groupName_5006Parser = createGroupName_5006Parser();
		}
		return groupName_5006Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createGroupName_5006Parser() {
		EAttribute[] features = new EAttribute[] { BflowPackage.eINSTANCE
				.getBflowSymbol_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser clusterName_5007Parser;

	/**
	 * @generated
	 */
	private IParser getClusterName_5007Parser() {
		if (clusterName_5007Parser == null) {
			clusterName_5007Parser = createClusterName_5007Parser();
		}
		return clusterName_5007Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createClusterName_5007Parser() {
		EAttribute[] features = new EAttribute[] { BflowPackage.eINSTANCE
				.getBflowSymbol_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser externalPersonName_5008Parser;

	/**
	 * @generated
	 */
	private IParser getExternalPersonName_5008Parser() {
		if (externalPersonName_5008Parser == null) {
			externalPersonName_5008Parser = createExternalPersonName_5008Parser();
		}
		return externalPersonName_5008Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createExternalPersonName_5008Parser() {
		EAttribute[] features = new EAttribute[] { BflowPackage.eINSTANCE
				.getBflowSymbol_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser internalPersonName_5009Parser;

	/**
	 * @generated
	 */
	private IParser getInternalPersonName_5009Parser() {
		if (internalPersonName_5009Parser == null) {
			internalPersonName_5009Parser = createInternalPersonName_5009Parser();
		}
		return internalPersonName_5009Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createInternalPersonName_5009Parser() {
		EAttribute[] features = new EAttribute[] { BflowPackage.eINSTANCE
				.getBflowSymbol_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser positionName_5010Parser;

	/**
	 * @generated
	 */
	private IParser getPositionName_5010Parser() {
		if (positionName_5010Parser == null) {
			positionName_5010Parser = createPositionName_5010Parser();
		}
		return positionName_5010Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createPositionName_5010Parser() {
		EAttribute[] features = new EAttribute[] { BflowPackage.eINSTANCE
				.getBflowSymbol_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser locationName_5011Parser;

	/**
	 * @generated
	 */
	private IParser getLocationName_5011Parser() {
		if (locationName_5011Parser == null) {
			locationName_5011Parser = createLocationName_5011Parser();
		}
		return locationName_5011Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createLocationName_5011Parser() {
		EAttribute[] features = new EAttribute[] { BflowPackage.eINSTANCE
				.getBflowSymbol_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser personTypeName_5012Parser;

	/**
	 * @generated
	 */
	private IParser getPersonTypeName_5012Parser() {
		if (personTypeName_5012Parser == null) {
			personTypeName_5012Parser = createPersonTypeName_5012Parser();
		}
		return personTypeName_5012Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createPersonTypeName_5012Parser() {
		EAttribute[] features = new EAttribute[] { BflowPackage.eINSTANCE
				.getBflowSymbol_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser technicalTermName_5013Parser;

	/**
	 * @generated
	 */
	private IParser getTechnicalTermName_5013Parser() {
		if (technicalTermName_5013Parser == null) {
			technicalTermName_5013Parser = createTechnicalTermName_5013Parser();
		}
		return technicalTermName_5013Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createTechnicalTermName_5013Parser() {
		EAttribute[] features = new EAttribute[] { BflowPackage.eINSTANCE
				.getBflowSymbol_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser cardFileName_5014Parser;

	/**
	 * @generated
	 */
	private IParser getCardFileName_5014Parser() {
		if (cardFileName_5014Parser == null) {
			cardFileName_5014Parser = createCardFileName_5014Parser();
		}
		return cardFileName_5014Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createCardFileName_5014Parser() {
		EAttribute[] features = new EAttribute[] { BflowPackage.eINSTANCE
				.getBflowSymbol_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser documentName_5015Parser;

	/**
	 * @generated
	 */
	private IParser getDocumentName_5015Parser() {
		if (documentName_5015Parser == null) {
			documentName_5015Parser = createDocumentName_5015Parser();
		}
		return documentName_5015Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createDocumentName_5015Parser() {
		EAttribute[] features = new EAttribute[] { BflowPackage.eINSTANCE
				.getBflowSymbol_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser fileName_5016Parser;

	/**
	 * @generated
	 */
	private IParser getFileName_5016Parser() {
		if (fileName_5016Parser == null) {
			fileName_5016Parser = createFileName_5016Parser();
		}
		return fileName_5016Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createFileName_5016Parser() {
		EAttribute[] features = new EAttribute[] { BflowPackage.eINSTANCE
				.getBflowSymbol_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser objectiveName_5017Parser;

	/**
	 * @generated
	 */
	private IParser getObjectiveName_5017Parser() {
		if (objectiveName_5017Parser == null) {
			objectiveName_5017Parser = createObjectiveName_5017Parser();
		}
		return objectiveName_5017Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createObjectiveName_5017Parser() {
		EAttribute[] features = new EAttribute[] { BflowPackage.eINSTANCE
				.getBflowSymbol_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser productName_5018Parser;

	/**
	 * @generated
	 */
	private IParser getProductName_5018Parser() {
		if (productName_5018Parser == null) {
			productName_5018Parser = createProductName_5018Parser();
		}
		return productName_5018Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createProductName_5018Parser() {
		EAttribute[] features = new EAttribute[] { BflowPackage.eINSTANCE
				.getBflowSymbol_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case ParticipantNameEditPart.VISUAL_ID:
			return getParticipantName_5001Parser();
		case ApplicationNameEditPart.VISUAL_ID:
			return getApplicationName_5002Parser();
		case ProcessInterfaceNameEditPart.VISUAL_ID:
			return getProcessInterfaceName_5003Parser();
		case EventNameEditPart.VISUAL_ID:
			return getEventName_5004Parser();
		case FunctionNameEditPart.VISUAL_ID:
			return getFunctionName_5005Parser();
		case GroupNameEditPart.VISUAL_ID:
			return getGroupName_5006Parser();
		case ClusterNameEditPart.VISUAL_ID:
			return getClusterName_5007Parser();
		case ExternalPersonNameEditPart.VISUAL_ID:
			return getExternalPersonName_5008Parser();
		case InternalPersonNameEditPart.VISUAL_ID:
			return getInternalPersonName_5009Parser();
		case PositionNameEditPart.VISUAL_ID:
			return getPositionName_5010Parser();
		case LocationNameEditPart.VISUAL_ID:
			return getLocationName_5011Parser();
		case PersonTypeNameEditPart.VISUAL_ID:
			return getPersonTypeName_5012Parser();
		case TechnicalTermNameEditPart.VISUAL_ID:
			return getTechnicalTermName_5013Parser();
		case CardFileNameEditPart.VISUAL_ID:
			return getCardFileName_5014Parser();
		case DocumentNameEditPart.VISUAL_ID:
			return getDocumentName_5015Parser();
		case FileNameEditPart.VISUAL_ID:
			return getFileName_5016Parser();
		case ObjectiveNameEditPart.VISUAL_ID:
			return getObjectiveName_5017Parser();
		case ProductNameEditPart.VISUAL_ID:
			return getProductName_5018Parser();
		}
		return null;
	}

	/**
	 * @generated
	 */
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(EpcVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(EpcVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (EpcElementTypes.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static class HintAdapter extends ParserHintAdapter {

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

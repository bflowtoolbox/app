package oepc.diagram.providers;

import oepc.OepcPackage;
import oepc.diagram.edit.parts.BusinessAttributeEditPart;
import oepc.diagram.edit.parts.BusinessMethodEditPart;
import oepc.diagram.edit.parts.BusinessObjectNameEditPart;
import oepc.diagram.edit.parts.DocumentNameEditPart;
import oepc.diagram.edit.parts.EventNameEditPart;
import oepc.diagram.edit.parts.ITSystemNameEditPart;
import oepc.diagram.edit.parts.OrganisationUnitNameEditPart;
import oepc.diagram.parsers.MessageFormatParser;
import oepc.diagram.part.OepcVisualIDRegistry;

import org.bflow.toolbox.bflow.BflowPackage;
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
public class OepcParserProvider extends AbstractProvider implements
		IParserProvider {

	/**
	 * @generated
	 */
	private IParser eventName_5001Parser;

	/**
	 * @generated
	 */
	private IParser getEventName_5001Parser() {
		if (eventName_5001Parser == null) {
			eventName_5001Parser = createEventName_5001Parser();
		}
		return eventName_5001Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createEventName_5001Parser() {
		EAttribute[] features = new EAttribute[] { BflowPackage.eINSTANCE
				.getBflowSymbol_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser iTSystemName_5002Parser;

	/**
	 * @generated
	 */
	private IParser getITSystemName_5002Parser() {
		if (iTSystemName_5002Parser == null) {
			iTSystemName_5002Parser = createITSystemName_5002Parser();
		}
		return iTSystemName_5002Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createITSystemName_5002Parser() {
		EAttribute[] features = new EAttribute[] { BflowPackage.eINSTANCE
				.getBflowSymbol_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser organisationUnitName_5003Parser;

	/**
	 * @generated
	 */
	private IParser getOrganisationUnitName_5003Parser() {
		if (organisationUnitName_5003Parser == null) {
			organisationUnitName_5003Parser = createOrganisationUnitName_5003Parser();
		}
		return organisationUnitName_5003Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createOrganisationUnitName_5003Parser() {
		EAttribute[] features = new EAttribute[] { BflowPackage.eINSTANCE
				.getBflowSymbol_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser businessObjectName_5004Parser;

	/**
	 * @generated
	 */
	private IParser getBusinessObjectName_5004Parser() {
		if (businessObjectName_5004Parser == null) {
			businessObjectName_5004Parser = createBusinessObjectName_5004Parser();
		}
		return businessObjectName_5004Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createBusinessObjectName_5004Parser() {
		EAttribute[] features = new EAttribute[] { BflowPackage.eINSTANCE
				.getBflowSymbol_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser documentName_4005Parser;

	/**
	 * @generated
	 */
	private IParser getDocumentName_4005Parser() {
		if (documentName_4005Parser == null) {
			documentName_4005Parser = createDocumentName_4005Parser();
		}
		return documentName_4005Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createDocumentName_4005Parser() {
		EAttribute[] features = new EAttribute[] { BflowPackage.eINSTANCE
				.getBflowSymbol_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser businessAttribute_3001Parser;

	/**
	 * @generated
	 */
	private IParser getBusinessAttribute_3001Parser() {
		if (businessAttribute_3001Parser == null) {
			businessAttribute_3001Parser = createBusinessAttribute_3001Parser();
		}
		return businessAttribute_3001Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createBusinessAttribute_3001Parser() {
		EAttribute[] features = new EAttribute[] { OepcPackage.eINSTANCE
				.getBusinessObjectElement_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		parser.setViewPattern("- {0}");
		parser.setEditorPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser businessMethod_3002Parser;

	/**
	 * @generated
	 */
	private IParser getBusinessMethod_3002Parser() {
		if (businessMethod_3002Parser == null) {
			businessMethod_3002Parser = createBusinessMethod_3002Parser();
		}
		return businessMethod_3002Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createBusinessMethod_3002Parser() {
		EAttribute[] features = new EAttribute[] { OepcPackage.eINSTANCE
				.getBusinessObjectElement_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		parser.setViewPattern("{0}()");
		parser.setEditorPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case EventNameEditPart.VISUAL_ID:
			return getEventName_5001Parser();
		case ITSystemNameEditPart.VISUAL_ID:
			return getITSystemName_5002Parser();
		case OrganisationUnitNameEditPart.VISUAL_ID:
			return getOrganisationUnitName_5003Parser();
		case BusinessObjectNameEditPart.VISUAL_ID:
			return getBusinessObjectName_5004Parser();
		case DocumentNameEditPart.VISUAL_ID:
			return getDocumentName_4005Parser();
		case BusinessAttributeEditPart.VISUAL_ID:
			return getBusinessAttribute_3001Parser();
		case BusinessMethodEditPart.VISUAL_ID:
			return getBusinessMethod_3002Parser();
		}
		return null;
	}

	/**
	 * @generated
	 */
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(OepcVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(OepcVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (OepcElementTypes.getElement(hint) == null) {
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

/*
 * 
 */
package orgchart.diagram.providers;

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
import orgchart.OrgchartPackage;
import orgchart.diagram.edit.parts.ExternalPersonNameEditPart;
import orgchart.diagram.edit.parts.GroupNameEditPart;
import orgchart.diagram.edit.parts.InternalPersonNameEditPart;
import orgchart.diagram.edit.parts.LocationNameEditPart;
import orgchart.diagram.edit.parts.ParticipantNameEditPart;
import orgchart.diagram.edit.parts.PersonTypeNameEditPart;
import orgchart.diagram.edit.parts.PositionNameEditPart;
import orgchart.diagram.parsers.MessageFormatParser;
import orgchart.diagram.part.OrgcVisualIDRegistry;

/**
 * @generated
 */
public class OrgcParserProvider extends AbstractProvider implements
		IParserProvider {

	/**
	 * @generated
	 */
	private IParser positionName_5001Parser;

	/**
	 * @generated
	 */
	private IParser getPositionName_5001Parser() {
		if (positionName_5001Parser == null) {
			EAttribute[] features = new EAttribute[] { OrgchartPackage.eINSTANCE
					.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			positionName_5001Parser = parser;
		}
		return positionName_5001Parser;
	}

	/**
	 * @generated
	 */
	private IParser internalPersonName_5002Parser;

	/**
	 * @generated
	 */
	private IParser getInternalPersonName_5002Parser() {
		if (internalPersonName_5002Parser == null) {
			EAttribute[] features = new EAttribute[] { OrgchartPackage.eINSTANCE
					.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			internalPersonName_5002Parser = parser;
		}
		return internalPersonName_5002Parser;
	}

	/**
	 * @generated
	 */
	private IParser externalPersonName_5003Parser;

	/**
	 * @generated
	 */
	private IParser getExternalPersonName_5003Parser() {
		if (externalPersonName_5003Parser == null) {
			EAttribute[] features = new EAttribute[] { OrgchartPackage.eINSTANCE
					.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			externalPersonName_5003Parser = parser;
		}
		return externalPersonName_5003Parser;
	}

	/**
	 * @generated
	 */
	private IParser groupName_5004Parser;

	/**
	 * @generated
	 */
	private IParser getGroupName_5004Parser() {
		if (groupName_5004Parser == null) {
			EAttribute[] features = new EAttribute[] { OrgchartPackage.eINSTANCE
					.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			groupName_5004Parser = parser;
		}
		return groupName_5004Parser;
	}

	/**
	 * @generated
	 */
	private IParser participantName_5005Parser;

	/**
	 * @generated
	 */
	private IParser getParticipantName_5005Parser() {
		if (participantName_5005Parser == null) {
			EAttribute[] features = new EAttribute[] { OrgchartPackage.eINSTANCE
					.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			participantName_5005Parser = parser;
		}
		return participantName_5005Parser;
	}

	/**
	 * @generated
	 */
	private IParser personTypeName_5006Parser;

	/**
	 * @generated
	 */
	private IParser getPersonTypeName_5006Parser() {
		if (personTypeName_5006Parser == null) {
			EAttribute[] features = new EAttribute[] { OrgchartPackage.eINSTANCE
					.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			personTypeName_5006Parser = parser;
		}
		return personTypeName_5006Parser;
	}

	/**
	 * @generated
	 */
	private IParser locationName_5007Parser;

	/**
	 * @generated
	 */
	private IParser getLocationName_5007Parser() {
		if (locationName_5007Parser == null) {
			EAttribute[] features = new EAttribute[] { OrgchartPackage.eINSTANCE
					.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			locationName_5007Parser = parser;
		}
		return locationName_5007Parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case PositionNameEditPart.VISUAL_ID:
			return getPositionName_5001Parser();
		case InternalPersonNameEditPart.VISUAL_ID:
			return getInternalPersonName_5002Parser();
		case ExternalPersonNameEditPart.VISUAL_ID:
			return getExternalPersonName_5003Parser();
		case GroupNameEditPart.VISUAL_ID:
			return getGroupName_5004Parser();
		case ParticipantNameEditPart.VISUAL_ID:
			return getParticipantName_5005Parser();
		case PersonTypeNameEditPart.VISUAL_ID:
			return getPersonTypeName_5006Parser();
		case LocationNameEditPart.VISUAL_ID:
			return getLocationName_5007Parser();
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
			return getParser(OrgcVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(OrgcVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (OrgcElementTypes.getElement(hint) == null) {
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

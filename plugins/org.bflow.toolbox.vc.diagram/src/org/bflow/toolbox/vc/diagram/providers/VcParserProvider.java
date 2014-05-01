package org.bflow.toolbox.vc.diagram.providers;

import org.bflow.toolbox.bflow.BflowPackage;
import org.bflow.toolbox.vc.diagram.edit.parts.ClusterNameEditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.ObjectiveNameEditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.ProductNameEditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.TechnicalTermNameEditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.ValueChain2NameEditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.ValueChainNameEditPart;
import org.bflow.toolbox.vc.diagram.parsers.MessageFormatParser;
import org.bflow.toolbox.vc.diagram.part.VcVisualIDRegistry;
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
public class VcParserProvider extends AbstractProvider implements
		IParserProvider {

	/**
	 * @generated
	 */
	private IParser valueChainName_5001Parser;

	/**
	 * @generated
	 */
	private IParser getValueChainName_5001Parser() {
		if (valueChainName_5001Parser == null) {
			valueChainName_5001Parser = createValueChainName_5001Parser();
		}
		return valueChainName_5001Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createValueChainName_5001Parser() {
		EAttribute[] features = new EAttribute[] { BflowPackage.eINSTANCE
				.getBflowSymbol_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser valueChain2Name_5002Parser;

	/**
	 * @generated
	 */
	private IParser getValueChain2Name_5002Parser() {
		if (valueChain2Name_5002Parser == null) {
			valueChain2Name_5002Parser = createValueChain2Name_5002Parser();
		}
		return valueChain2Name_5002Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createValueChain2Name_5002Parser() {
		EAttribute[] features = new EAttribute[] { BflowPackage.eINSTANCE
				.getBflowSymbol_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser technicalTermName_5003Parser;

	/**
	 * @generated
	 */
	private IParser getTechnicalTermName_5003Parser() {
		if (technicalTermName_5003Parser == null) {
			technicalTermName_5003Parser = createTechnicalTermName_5003Parser();
		}
		return technicalTermName_5003Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createTechnicalTermName_5003Parser() {
		EAttribute[] features = new EAttribute[] { BflowPackage.eINSTANCE
				.getBflowSymbol_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser clusterName_5004Parser;

	/**
	 * @generated
	 */
	private IParser getClusterName_5004Parser() {
		if (clusterName_5004Parser == null) {
			clusterName_5004Parser = createClusterName_5004Parser();
		}
		return clusterName_5004Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createClusterName_5004Parser() {
		EAttribute[] features = new EAttribute[] { BflowPackage.eINSTANCE
				.getBflowSymbol_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser objectiveName_5005Parser;

	/**
	 * @generated
	 */
	private IParser getObjectiveName_5005Parser() {
		if (objectiveName_5005Parser == null) {
			objectiveName_5005Parser = createObjectiveName_5005Parser();
		}
		return objectiveName_5005Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createObjectiveName_5005Parser() {
		EAttribute[] features = new EAttribute[] { BflowPackage.eINSTANCE
				.getBflowSymbol_Name(), };
		MessageFormatParser parser = new MessageFormatParser(features);
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser productName_5006Parser;

	/**
	 * @generated
	 */
	private IParser getProductName_5006Parser() {
		if (productName_5006Parser == null) {
			productName_5006Parser = createProductName_5006Parser();
		}
		return productName_5006Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createProductName_5006Parser() {
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
		case ValueChainNameEditPart.VISUAL_ID:
			return getValueChainName_5001Parser();
		case ValueChain2NameEditPart.VISUAL_ID:
			return getValueChain2Name_5002Parser();
		case TechnicalTermNameEditPart.VISUAL_ID:
			return getTechnicalTermName_5003Parser();
		case ClusterNameEditPart.VISUAL_ID:
			return getClusterName_5004Parser();
		case ObjectiveNameEditPart.VISUAL_ID:
			return getObjectiveName_5005Parser();
		case ProductNameEditPart.VISUAL_ID:
			return getProductName_5006Parser();
		}
		return null;
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

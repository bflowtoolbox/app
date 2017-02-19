package edu.toronto.cs.openome_model.diagram.providers;

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

/**
 * @generated
 */
public class Openome_modelParserProvider extends AbstractProvider implements
		IParserProvider {

	/**
	 * @generated
	 */
	private IParser actorName_5005Parser;

	/**
	 * @generated
	 */
	private IParser getActorName_5005Parser() {
		if (actorName_5005Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getContainer_Name() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			actorName_5005Parser = parser;
		}
		return actorName_5005Parser;
	}

	/**
	 * @generated
	 */
	private IParser agentName_5010Parser;

	/**
	 * @generated
	 */
	private IParser getAgentName_5010Parser() {
		if (agentName_5010Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getContainer_Name() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			agentName_5010Parser = parser;
		}
		return agentName_5010Parser;
	}

	/**
	 * @generated
	 */
	private IParser positionName_5015Parser;

	/**
	 * @generated
	 */
	private IParser getPositionName_5015Parser() {
		if (positionName_5015Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getContainer_Name() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			positionName_5015Parser = parser;
		}
		return positionName_5015Parser;
	}

	/**
	 * @generated
	 */
	private IParser roleName_5020Parser;

	/**
	 * @generated
	 */
	private IParser getRoleName_5020Parser() {
		if (roleName_5020Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getContainer_Name() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			roleName_5020Parser = parser;
		}
		return roleName_5020Parser;
	}

	/**
	 * @generated
	 */
	private IParser goalName_5021Parser;

	/**
	 * @generated
	 */
	private IParser getGoalName_5021Parser() {
		if (goalName_5021Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_Name() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			goalName_5021Parser = parser;
		}
		return goalName_5021Parser;
	}

	/**
	 * @generated
	 */
	private IParser goalQualitativeReasoningCombinedLabel_5041Parser;

	/**
	 * @generated
	 */
	private IParser getGoalQualitativeReasoningCombinedLabel_5041Parser() {
		if (goalQualitativeReasoningCombinedLabel_5041Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_QualitativeReasoningCombinedLabel() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			goalQualitativeReasoningCombinedLabel_5041Parser = parser;
		}
		return goalQualitativeReasoningCombinedLabel_5041Parser;
	}

	/**
	 * @generated
	 */
	private IParser softgoalName_5022Parser;

	/**
	 * @generated
	 */
	private IParser getSoftgoalName_5022Parser() {
		if (softgoalName_5022Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_Name() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			softgoalName_5022Parser = parser;
		}
		return softgoalName_5022Parser;
	}

	/**
	 * @generated
	 */
	private IParser softgoalQualitativeReasoningCombinedLabel_5042Parser;

	/**
	 * @generated
	 */
	private IParser getSoftgoalQualitativeReasoningCombinedLabel_5042Parser() {
		if (softgoalQualitativeReasoningCombinedLabel_5042Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_QualitativeReasoningCombinedLabel() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			softgoalQualitativeReasoningCombinedLabel_5042Parser = parser;
		}
		return softgoalQualitativeReasoningCombinedLabel_5042Parser;
	}

	/**
	 * @generated
	 */
	private IParser taskName_5023Parser;

	/**
	 * @generated
	 */
	private IParser getTaskName_5023Parser() {
		if (taskName_5023Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_Name() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			taskName_5023Parser = parser;
		}
		return taskName_5023Parser;
	}

	/**
	 * @generated
	 */
	private IParser taskQualitativeReasoningCombinedLabel_5043Parser;

	/**
	 * @generated
	 */
	private IParser getTaskQualitativeReasoningCombinedLabel_5043Parser() {
		if (taskQualitativeReasoningCombinedLabel_5043Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_QualitativeReasoningCombinedLabel() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			taskQualitativeReasoningCombinedLabel_5043Parser = parser;
		}
		return taskQualitativeReasoningCombinedLabel_5043Parser;
	}

	/**
	 * @generated
	 */
	private IParser resourceName_5024Parser;

	/**
	 * @generated
	 */
	private IParser getResourceName_5024Parser() {
		if (resourceName_5024Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_Name() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			resourceName_5024Parser = parser;
		}
		return resourceName_5024Parser;
	}

	/**
	 * @generated
	 */
	private IParser resourceQualitativeReasoningCombinedLabel_5044Parser;

	/**
	 * @generated
	 */
	private IParser getResourceQualitativeReasoningCombinedLabel_5044Parser() {
		if (resourceQualitativeReasoningCombinedLabel_5044Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_QualitativeReasoningCombinedLabel() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			resourceQualitativeReasoningCombinedLabel_5044Parser = parser;
		}
		return resourceQualitativeReasoningCombinedLabel_5044Parser;
	}

	/**
	 * @generated
	 */
	private IParser goalName_5001Parser;

	/**
	 * @generated
	 */
	private IParser getGoalName_5001Parser() {
		if (goalName_5001Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_Name() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			goalName_5001Parser = parser;
		}
		return goalName_5001Parser;
	}

	/**
	 * @generated
	 */
	private IParser goalQualitativeReasoningCombinedLabel_5025Parser;

	/**
	 * @generated
	 */
	private IParser getGoalQualitativeReasoningCombinedLabel_5025Parser() {
		if (goalQualitativeReasoningCombinedLabel_5025Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_QualitativeReasoningCombinedLabel() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			goalQualitativeReasoningCombinedLabel_5025Parser = parser;
		}
		return goalQualitativeReasoningCombinedLabel_5025Parser;
	}

	/**
	 * @generated
	 */
	private IParser softgoalName_5002Parser;

	/**
	 * @generated
	 */
	private IParser getSoftgoalName_5002Parser() {
		if (softgoalName_5002Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_Name() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			softgoalName_5002Parser = parser;
		}
		return softgoalName_5002Parser;
	}

	/**
	 * @generated
	 */
	private IParser softgoalQualitativeReasoningCombinedLabel_5026Parser;

	/**
	 * @generated
	 */
	private IParser getSoftgoalQualitativeReasoningCombinedLabel_5026Parser() {
		if (softgoalQualitativeReasoningCombinedLabel_5026Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_QualitativeReasoningCombinedLabel() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			softgoalQualitativeReasoningCombinedLabel_5026Parser = parser;
		}
		return softgoalQualitativeReasoningCombinedLabel_5026Parser;
	}

	/**
	 * @generated
	 */
	private IParser resourceName_5003Parser;

	/**
	 * @generated
	 */
	private IParser getResourceName_5003Parser() {
		if (resourceName_5003Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_Name() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			resourceName_5003Parser = parser;
		}
		return resourceName_5003Parser;
	}

	/**
	 * @generated
	 */
	private IParser resourceQualitativeReasoningCombinedLabel_5027Parser;

	/**
	 * @generated
	 */
	private IParser getResourceQualitativeReasoningCombinedLabel_5027Parser() {
		if (resourceQualitativeReasoningCombinedLabel_5027Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_QualitativeReasoningCombinedLabel() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			resourceQualitativeReasoningCombinedLabel_5027Parser = parser;
		}
		return resourceQualitativeReasoningCombinedLabel_5027Parser;
	}

	/**
	 * @generated
	 */
	private IParser taskName_5004Parser;

	/**
	 * @generated
	 */
	private IParser getTaskName_5004Parser() {
		if (taskName_5004Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_Name() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			taskName_5004Parser = parser;
		}
		return taskName_5004Parser;
	}

	/**
	 * @generated
	 */
	private IParser taskQualitativeReasoningCombinedLabel_5028Parser;

	/**
	 * @generated
	 */
	private IParser getTaskQualitativeReasoningCombinedLabel_5028Parser() {
		if (taskQualitativeReasoningCombinedLabel_5028Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_QualitativeReasoningCombinedLabel() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			taskQualitativeReasoningCombinedLabel_5028Parser = parser;
		}
		return taskQualitativeReasoningCombinedLabel_5028Parser;
	}

	/**
	 * @generated
	 */
	private IParser goalName_5006Parser;

	/**
	 * @generated
	 */
	private IParser getGoalName_5006Parser() {
		if (goalName_5006Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_Name() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			goalName_5006Parser = parser;
		}
		return goalName_5006Parser;
	}

	/**
	 * @generated
	 */
	private IParser softgoalName_5012Parser;

	/**
	 * @generated
	 */
	private IParser getSoftgoalName_5012Parser() {
		if (softgoalName_5012Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_Name() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			softgoalName_5012Parser = parser;
		}
		return softgoalName_5012Parser;
	}

	/**
	 * @generated
	 */
	private IParser softgoalQualitativeReasoningCombinedLabel_5034Parser;

	/**
	 * @generated
	 */
	private IParser getSoftgoalQualitativeReasoningCombinedLabel_5034Parser() {
		if (softgoalQualitativeReasoningCombinedLabel_5034Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_QualitativeReasoningCombinedLabel() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			softgoalQualitativeReasoningCombinedLabel_5034Parser = parser;
		}
		return softgoalQualitativeReasoningCombinedLabel_5034Parser;
	}

	/**
	 * @generated
	 */
	private IParser resourceName_5013Parser;

	/**
	 * @generated
	 */
	private IParser getResourceName_5013Parser() {
		if (resourceName_5013Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_Name() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			resourceName_5013Parser = parser;
		}
		return resourceName_5013Parser;
	}

	/**
	 * @generated
	 */
	private IParser resourceQualitativeReasoningCombinedLabel_5035Parser;

	/**
	 * @generated
	 */
	private IParser getResourceQualitativeReasoningCombinedLabel_5035Parser() {
		if (resourceQualitativeReasoningCombinedLabel_5035Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_QualitativeReasoningCombinedLabel() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			resourceQualitativeReasoningCombinedLabel_5035Parser = parser;
		}
		return resourceQualitativeReasoningCombinedLabel_5035Parser;
	}

	/**
	 * @generated
	 */
	private IParser taskName_5014Parser;

	/**
	 * @generated
	 */
	private IParser getTaskName_5014Parser() {
		if (taskName_5014Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_Name() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			taskName_5014Parser = parser;
		}
		return taskName_5014Parser;
	}

	/**
	 * @generated
	 */
	private IParser taskQualitativeReasoningCombinedLabel_5036Parser;

	/**
	 * @generated
	 */
	private IParser getTaskQualitativeReasoningCombinedLabel_5036Parser() {
		if (taskQualitativeReasoningCombinedLabel_5036Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_QualitativeReasoningCombinedLabel() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			taskQualitativeReasoningCombinedLabel_5036Parser = parser;
		}
		return taskQualitativeReasoningCombinedLabel_5036Parser;
	}

	/**
	 * @generated
	 */
	private IParser goalName_5016Parser;

	/**
	 * @generated
	 */
	private IParser getGoalName_5016Parser() {
		if (goalName_5016Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_Name() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			goalName_5016Parser = parser;
		}
		return goalName_5016Parser;
	}

	/**
	 * @generated
	 */
	private IParser goalQualitativeReasoningCombinedLabel_5037Parser;

	/**
	 * @generated
	 */
	private IParser getGoalQualitativeReasoningCombinedLabel_5037Parser() {
		if (goalQualitativeReasoningCombinedLabel_5037Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_QualitativeReasoningCombinedLabel() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			goalQualitativeReasoningCombinedLabel_5037Parser = parser;
		}
		return goalQualitativeReasoningCombinedLabel_5037Parser;
	}

	/**
	 * @generated
	 */
	private IParser softgoalName_5017Parser;

	/**
	 * @generated
	 */
	private IParser getSoftgoalName_5017Parser() {
		if (softgoalName_5017Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_Name() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			softgoalName_5017Parser = parser;
		}
		return softgoalName_5017Parser;
	}

	/**
	 * @generated
	 */
	private IParser softgoalQualitativeReasoningCombinedLabel_5038Parser;

	/**
	 * @generated
	 */
	private IParser getSoftgoalQualitativeReasoningCombinedLabel_5038Parser() {
		if (softgoalQualitativeReasoningCombinedLabel_5038Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_QualitativeReasoningCombinedLabel() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			softgoalQualitativeReasoningCombinedLabel_5038Parser = parser;
		}
		return softgoalQualitativeReasoningCombinedLabel_5038Parser;
	}

	/**
	 * @generated
	 */
	private IParser resourceName_5018Parser;

	/**
	 * @generated
	 */
	private IParser getResourceName_5018Parser() {
		if (resourceName_5018Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_Name() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			resourceName_5018Parser = parser;
		}
		return resourceName_5018Parser;
	}

	/**
	 * @generated
	 */
	private IParser resourceQualitativeReasoningCombinedLabel_5039Parser;

	/**
	 * @generated
	 */
	private IParser getResourceQualitativeReasoningCombinedLabel_5039Parser() {
		if (resourceQualitativeReasoningCombinedLabel_5039Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_QualitativeReasoningCombinedLabel() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			resourceQualitativeReasoningCombinedLabel_5039Parser = parser;
		}
		return resourceQualitativeReasoningCombinedLabel_5039Parser;
	}

	/**
	 * @generated
	 */
	private IParser taskName_5019Parser;

	/**
	 * @generated
	 */
	private IParser getTaskName_5019Parser() {
		if (taskName_5019Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_Name() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			taskName_5019Parser = parser;
		}
		return taskName_5019Parser;
	}

	/**
	 * @generated
	 */
	private IParser taskQualitativeReasoningCombinedLabel_5040Parser;

	/**
	 * @generated
	 */
	private IParser getTaskQualitativeReasoningCombinedLabel_5040Parser() {
		if (taskQualitativeReasoningCombinedLabel_5040Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_QualitativeReasoningCombinedLabel() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			taskQualitativeReasoningCombinedLabel_5040Parser = parser;
		}
		return taskQualitativeReasoningCombinedLabel_5040Parser;
	}

	/**
	 * @generated
	 */
	private IParser helpContributionContributionType_6004Parser;

	/**
	 * @generated
	 */
	private IParser getHelpContributionContributionType_6004Parser() {
		if (helpContributionContributionType_6004Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getHelpContribution_ContributionType() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			helpContributionContributionType_6004Parser = parser;
		}
		return helpContributionContributionType_6004Parser;
	}

	/**
	 * @generated
	 */
	private IParser hurtContributionContributionType_6005Parser;

	/**
	 * @generated
	 */
	private IParser getHurtContributionContributionType_6005Parser() {
		if (hurtContributionContributionType_6005Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getHurtContribution_ContributionType() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			hurtContributionContributionType_6005Parser = parser;
		}
		return hurtContributionContributionType_6005Parser;
	}

	/**
	 * @generated
	 */
	private IParser makeContributionContributionType_6006Parser;

	/**
	 * @generated
	 */
	private IParser getMakeContributionContributionType_6006Parser() {
		if (makeContributionContributionType_6006Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getMakeContribution_ContributionType() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			makeContributionContributionType_6006Parser = parser;
		}
		return makeContributionContributionType_6006Parser;
	}

	/**
	 * @generated
	 */
	private IParser breakContributionContributionType_6007Parser;

	/**
	 * @generated
	 */
	private IParser getBreakContributionContributionType_6007Parser() {
		if (breakContributionContributionType_6007Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getBreakContribution_ContributionType() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			breakContributionContributionType_6007Parser = parser;
		}
		return breakContributionContributionType_6007Parser;
	}

	/**
	 * @generated
	 */
	private IParser somePlusContributionContributionType_6008Parser;

	/**
	 * @generated
	 */
	private IParser getSomePlusContributionContributionType_6008Parser() {
		if (somePlusContributionContributionType_6008Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getSomePlusContribution_ContributionType() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			somePlusContributionContributionType_6008Parser = parser;
		}
		return somePlusContributionContributionType_6008Parser;
	}

	/**
	 * @generated
	 */
	private IParser someMinusContributionContributionType_6009Parser;

	/**
	 * @generated
	 */
	private IParser getSomeMinusContributionContributionType_6009Parser() {
		if (someMinusContributionContributionType_6009Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getSomeMinusContribution_ContributionType() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			someMinusContributionContributionType_6009Parser = parser;
		}
		return someMinusContributionContributionType_6009Parser;
	}

	/**
	 * @generated
	 */
	private IParser unknownContributionContributionType_6010Parser;

	/**
	 * @generated
	 */
	private IParser getUnknownContributionContributionType_6010Parser() {
		if (unknownContributionContributionType_6010Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getUnknownContribution_ContributionType() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			unknownContributionContributionType_6010Parser = parser;
		}
		return unknownContributionContributionType_6010Parser;
	}

	/**
	 * @generated
	 */
	private IParser andContributionContributionType_6011Parser;

	/**
	 * @generated
	 */
	private IParser getAndContributionContributionType_6011Parser() {
		if (andContributionContributionType_6011Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getAndContribution_ContributionType() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			andContributionContributionType_6011Parser = parser;
		}
		return andContributionContributionType_6011Parser;
	}

	/**
	 * @generated
	 */
	private IParser orContributionContributionType_6012Parser;

	/**
	 * @generated
	 */
	private IParser getOrContributionContributionType_6012Parser() {
		if (orContributionContributionType_6012Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getOrContribution_ContributionType() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			orContributionContributionType_6012Parser = parser;
		}
		return orContributionContributionType_6012Parser;
	}

	/**
	 * @generated
	 */
	private IParser isAAssociationLabel_6013Parser;

	/**
	 * @generated
	 */
	private IParser getIsAAssociationLabel_6013Parser() {
		if (isAAssociationLabel_6013Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIsAAssociation_Label() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			isAAssociationLabel_6013Parser = parser;
		}
		return isAAssociationLabel_6013Parser;
	}

	/**
	 * @generated
	 */
	private IParser coversAssociationLabel_6014Parser;

	/**
	 * @generated
	 */
	private IParser getCoversAssociationLabel_6014Parser() {
		if (coversAssociationLabel_6014Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getCoversAssociation_Label() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			coversAssociationLabel_6014Parser = parser;
		}
		return coversAssociationLabel_6014Parser;
	}

	/**
	 * @generated
	 */
	private IParser occupiesAssociationLabel_6015Parser;

	/**
	 * @generated
	 */
	private IParser getOccupiesAssociationLabel_6015Parser() {
		if (occupiesAssociationLabel_6015Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getOccupiesAssociation_Label() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			occupiesAssociationLabel_6015Parser = parser;
		}
		return occupiesAssociationLabel_6015Parser;
	}

	/**
	 * @generated
	 */
	private IParser isPartOfAssociationLabel_6016Parser;

	/**
	 * @generated
	 */
	private IParser getIsPartOfAssociationLabel_6016Parser() {
		if (isPartOfAssociationLabel_6016Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIsPartOfAssociation_Label() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			isPartOfAssociationLabel_6016Parser = parser;
		}
		return isPartOfAssociationLabel_6016Parser;
	}

	/**
	 * @generated
	 */
	private IParser playsAssociationLabel_6017Parser;

	/**
	 * @generated
	 */
	private IParser getPlaysAssociationLabel_6017Parser() {
		if (playsAssociationLabel_6017Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getPlaysAssociation_Label() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			playsAssociationLabel_6017Parser = parser;
		}
		return playsAssociationLabel_6017Parser;
	}

	/**
	 * @generated
	 */
	private IParser iNSAssociationLabel_6018Parser;

	/**
	 * @generated
	 */
	private IParser getINSAssociationLabel_6018Parser() {
		if (iNSAssociationLabel_6018Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getINSAssociation_Label() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			iNSAssociationLabel_6018Parser = parser;
		}
		return iNSAssociationLabel_6018Parser;
	}

	/**
	 * @generated
	 */
	private IParser goalQualitativeReasoningCombinedLabel_5029Parser;

	/**
	 * @generated
	 */
	private IParser getGoalQualitativeReasoningCombinedLabel_5029Parser() {
		if (goalQualitativeReasoningCombinedLabel_5029Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_QualitativeReasoningCombinedLabel() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			goalQualitativeReasoningCombinedLabel_5029Parser = parser;
		}
		return goalQualitativeReasoningCombinedLabel_5029Parser;
	}

	/**
	 * @generated
	 */
	private IParser softgoalName_5007Parser;

	/**
	 * @generated
	 */
	private IParser getSoftgoalName_5007Parser() {
		if (softgoalName_5007Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_Name() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			softgoalName_5007Parser = parser;
		}
		return softgoalName_5007Parser;
	}

	/**
	 * @generated
	 */
	private IParser softgoalQualitativeReasoningCombinedLabel_5030Parser;

	/**
	 * @generated
	 */
	private IParser getSoftgoalQualitativeReasoningCombinedLabel_5030Parser() {
		if (softgoalQualitativeReasoningCombinedLabel_5030Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_QualitativeReasoningCombinedLabel() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			softgoalQualitativeReasoningCombinedLabel_5030Parser = parser;
		}
		return softgoalQualitativeReasoningCombinedLabel_5030Parser;
	}

	/**
	 * @generated
	 */
	private IParser resourceName_5008Parser;

	/**
	 * @generated
	 */
	private IParser getResourceName_5008Parser() {
		if (resourceName_5008Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_Name() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			resourceName_5008Parser = parser;
		}
		return resourceName_5008Parser;
	}

	/**
	 * @generated
	 */
	private IParser resourceQualitativeReasoningCombinedLabel_5031Parser;

	/**
	 * @generated
	 */
	private IParser getResourceQualitativeReasoningCombinedLabel_5031Parser() {
		if (resourceQualitativeReasoningCombinedLabel_5031Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_QualitativeReasoningCombinedLabel() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			resourceQualitativeReasoningCombinedLabel_5031Parser = parser;
		}
		return resourceQualitativeReasoningCombinedLabel_5031Parser;
	}

	/**
	 * @generated
	 */
	private IParser taskName_5009Parser;

	/**
	 * @generated
	 */
	private IParser getTaskName_5009Parser() {
		if (taskName_5009Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_Name() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			taskName_5009Parser = parser;
		}
		return taskName_5009Parser;
	}

	/**
	 * @generated
	 */
	private IParser taskQualitativeReasoningCombinedLabel_5032Parser;

	/**
	 * @generated
	 */
	private IParser getTaskQualitativeReasoningCombinedLabel_5032Parser() {
		if (taskQualitativeReasoningCombinedLabel_5032Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_QualitativeReasoningCombinedLabel() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			taskQualitativeReasoningCombinedLabel_5032Parser = parser;
		}
		return taskQualitativeReasoningCombinedLabel_5032Parser;
	}

	/**
	 * @generated
	 */
	private IParser goalName_5011Parser;

	/**
	 * @generated
	 */
	private IParser getGoalName_5011Parser() {
		if (goalName_5011Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_Name() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			goalName_5011Parser = parser;
		}
		return goalName_5011Parser;
	}

	/**
	 * @generated
	 */
	private IParser goalQualitativeReasoningCombinedLabel_5033Parser;

	/**
	 * @generated
	 */
	private IParser getGoalQualitativeReasoningCombinedLabel_5033Parser() {
		if (goalQualitativeReasoningCombinedLabel_5033Parser == null) {
			EAttribute[] features = new EAttribute[] { edu.toronto.cs.openome_model.openome_modelPackage.eINSTANCE
					.getIntention_QualitativeReasoningCombinedLabel() };
			edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser parser = new edu.toronto.cs.openome_model.diagram.parsers.MessageFormatParser(
					features);
			goalQualitativeReasoningCombinedLabel_5033Parser = parser;
		}
		return goalQualitativeReasoningCombinedLabel_5033Parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case edu.toronto.cs.openome_model.diagram.edit.parts.ActorNameEditPart.VISUAL_ID:
			return getActorName_5005Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.AgentNameEditPart.VISUAL_ID:
			return getAgentName_5010Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.PositionNameEditPart.VISUAL_ID:
			return getPositionName_5015Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.RoleNameEditPart.VISUAL_ID:
			return getRoleName_5020Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.GoalNameEditPart.VISUAL_ID:
			return getGoalName_5021Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.GoalQualitativeReasoningComEditPart.VISUAL_ID:
			return getGoalQualitativeReasoningCombinedLabel_5041Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalNameEditPart.VISUAL_ID:
			return getSoftgoalName_5022Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalQualitativeReasoningComEditPart.VISUAL_ID:
			return getSoftgoalQualitativeReasoningCombinedLabel_5042Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.TaskNameEditPart.VISUAL_ID:
			return getTaskName_5023Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.TaskQualitativeReasoningComEditPart.VISUAL_ID:
			return getTaskQualitativeReasoningCombinedLabel_5043Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.ResourceNameEditPart.VISUAL_ID:
			return getResourceName_5024Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.ResourceQualitativeReasoningComEditPart.VISUAL_ID:
			return getResourceQualitativeReasoningCombinedLabel_5044Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.GoalName2EditPart.VISUAL_ID:
			return getGoalName_5001Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.GoalQualitativeReasoningCom2EditPart.VISUAL_ID:
			return getGoalQualitativeReasoningCombinedLabel_5025Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalName2EditPart.VISUAL_ID:
			return getSoftgoalName_5002Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalQualitativeReasoningCom2EditPart.VISUAL_ID:
			return getSoftgoalQualitativeReasoningCombinedLabel_5026Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.ResourceName2EditPart.VISUAL_ID:
			return getResourceName_5003Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.ResourceQualitativeReasoningCom2EditPart.VISUAL_ID:
			return getResourceQualitativeReasoningCombinedLabel_5027Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.TaskName2EditPart.VISUAL_ID:
			return getTaskName_5004Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.TaskQualitativeReasoningCom2EditPart.VISUAL_ID:
			return getTaskQualitativeReasoningCombinedLabel_5028Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.GoalName3EditPart.VISUAL_ID:
			return getGoalName_5006Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.GoalQualitativeReasoningCom3EditPart.VISUAL_ID:
			return getGoalQualitativeReasoningCombinedLabel_5029Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalName3EditPart.VISUAL_ID:
			return getSoftgoalName_5007Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalQualitativeReasoningCom3EditPart.VISUAL_ID:
			return getSoftgoalQualitativeReasoningCombinedLabel_5030Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.ResourceName3EditPart.VISUAL_ID:
			return getResourceName_5008Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.ResourceQualitativeReasoningCom3EditPart.VISUAL_ID:
			return getResourceQualitativeReasoningCombinedLabel_5031Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.TaskName3EditPart.VISUAL_ID:
			return getTaskName_5009Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.TaskQualitativeReasoningCom3EditPart.VISUAL_ID:
			return getTaskQualitativeReasoningCombinedLabel_5032Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.GoalName4EditPart.VISUAL_ID:
			return getGoalName_5011Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.GoalQualitativeReasoningCom4EditPart.VISUAL_ID:
			return getGoalQualitativeReasoningCombinedLabel_5033Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalName4EditPart.VISUAL_ID:
			return getSoftgoalName_5012Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalQualitativeReasoningCom4EditPart.VISUAL_ID:
			return getSoftgoalQualitativeReasoningCombinedLabel_5034Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.ResourceName4EditPart.VISUAL_ID:
			return getResourceName_5013Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.ResourceQualitativeReasoningCom4EditPart.VISUAL_ID:
			return getResourceQualitativeReasoningCombinedLabel_5035Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.TaskName4EditPart.VISUAL_ID:
			return getTaskName_5014Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.TaskQualitativeReasoningCom4EditPart.VISUAL_ID:
			return getTaskQualitativeReasoningCombinedLabel_5036Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.GoalName5EditPart.VISUAL_ID:
			return getGoalName_5016Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.GoalQualitativeReasoningCom5EditPart.VISUAL_ID:
			return getGoalQualitativeReasoningCombinedLabel_5037Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalName5EditPart.VISUAL_ID:
			return getSoftgoalName_5017Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalQualitativeReasoningCom5EditPart.VISUAL_ID:
			return getSoftgoalQualitativeReasoningCombinedLabel_5038Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.ResourceName5EditPart.VISUAL_ID:
			return getResourceName_5018Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.ResourceQualitativeReasoningCom5EditPart.VISUAL_ID:
			return getResourceQualitativeReasoningCombinedLabel_5039Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.TaskName5EditPart.VISUAL_ID:
			return getTaskName_5019Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.TaskQualitativeReasoningCom5EditPart.VISUAL_ID:
			return getTaskQualitativeReasoningCombinedLabel_5040Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionContributionTypeEditPart.VISUAL_ID:
			return getHelpContributionContributionType_6004Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionContributionTypeEditPart.VISUAL_ID:
			return getHurtContributionContributionType_6005Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionContributionTypeEditPart.VISUAL_ID:
			return getMakeContributionContributionType_6006Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionContributionTypeEditPart.VISUAL_ID:
			return getBreakContributionContributionType_6007Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionContributionTypeEditPart.VISUAL_ID:
			return getSomePlusContributionContributionType_6008Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionContributionTypeEditPart.VISUAL_ID:
			return getSomeMinusContributionContributionType_6009Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionContributionTypeEditPart.VISUAL_ID:
			return getUnknownContributionContributionType_6010Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionContributionTypeEditPart.VISUAL_ID:
			return getAndContributionContributionType_6011Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionContributionTypeEditPart.VISUAL_ID:
			return getOrContributionContributionType_6012Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.IsAAssociationLabelEditPart.VISUAL_ID:
			return getIsAAssociationLabel_6013Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.CoversAssociationLabelEditPart.VISUAL_ID:
			return getCoversAssociationLabel_6014Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.OccupiesAssociationLabelEditPart.VISUAL_ID:
			return getOccupiesAssociationLabel_6015Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.IsPartOfAssociationLabelEditPart.VISUAL_ID:
			return getIsPartOfAssociationLabel_6016Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.PlaysAssociationLabelEditPart.VISUAL_ID:
			return getPlaysAssociationLabel_6017Parser();
		case edu.toronto.cs.openome_model.diagram.edit.parts.INSAssociationLabelEditPart.VISUAL_ID:
			return getINSAssociationLabel_6018Parser();
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
			return getParser(edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
					.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
					.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes
					.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	 * @generated NOT
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

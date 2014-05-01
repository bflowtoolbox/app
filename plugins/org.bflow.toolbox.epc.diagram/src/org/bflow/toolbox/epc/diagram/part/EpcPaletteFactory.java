package org.bflow.toolbox.epc.diagram.part;

import java.util.ArrayList;
import java.util.List;

import org.bflow.toolbox.epc.diagram.providers.EpcElementTypes;
import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;

/**
 * @generated
 */
public class EpcPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createSymbols1Group());
		paletteRoot.add(createConnectors2Group());
		paletteRoot.add(createConnections3Group());
	}

	/**
	 * Creates "Symbols" palette tool group
	 * @generated
	 */
	private PaletteContainer createSymbols1Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(
				Messages.Symbols1Group_title);
		paletteContainer.add(createEvent1CreationTool());
		paletteContainer.add(createFunction2CreationTool());
		paletteContainer.add(createApplication3CreationTool());
		paletteContainer.add(createProcessInterface4CreationTool());
		paletteContainer.add(createTechnicalTerm5CreationTool());
		paletteContainer.add(createParticipant6CreationTool());
		paletteContainer.add(createGroup7CreationTool());
		paletteContainer.add(createLocation8CreationTool());
		paletteContainer.add(createPosition9CreationTool());
		paletteContainer.add(createFile10CreationTool());
		paletteContainer.add(createDocument11CreationTool());
		paletteContainer.add(createCardFile12CreationTool());
		paletteContainer.add(createCluster13CreationTool());
		paletteContainer.add(createInternalPerson14CreationTool());
		paletteContainer.add(createExternalPerson15CreationTool());
		paletteContainer.add(createPersonType16CreationTool());
		paletteContainer.add(createProduct17CreationTool());
		paletteContainer.add(createObjective18CreationTool());
		return paletteContainer;
	}

	/**
	 * Creates "Connectors" palette tool group
	 * @generated
	 */
	private PaletteContainer createConnectors2Group() {
		PaletteGroup paletteContainer = new PaletteGroup(
				Messages.Connectors2Group_title);
		paletteContainer.add(createAND1CreationTool());
		paletteContainer.add(createOR2CreationTool());
		paletteContainer.add(createXOR3CreationTool());
		return paletteContainer;
	}

	/**
	 * Creates "Connections" palette tool group
	 * @generated
	 */
	private PaletteContainer createConnections3Group() {
		PaletteGroup paletteContainer = new PaletteGroup(
				Messages.Connections3Group_title);
		paletteContainer.add(createControlFlowArc1CreationTool());
		paletteContainer.add(createInformationFlowArc2CreationTool());
		paletteContainer.add(createRelation3CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createEvent1CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(EpcElementTypes.Event_2006);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Event1CreationTool_title,
				Messages.Event1CreationTool_desc, types);
		entry.setSmallIcon(EpcDiagramEditorPlugin
				.findImageDescriptor("images/event.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createFunction2CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(EpcElementTypes.Function_2007);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Function2CreationTool_title,
				Messages.Function2CreationTool_desc, types);
		entry.setSmallIcon(EpcDiagramEditorPlugin
				.findImageDescriptor("images/function.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createApplication3CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(EpcElementTypes.Application_2004);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Application3CreationTool_title,
				Messages.Application3CreationTool_desc, types);
		entry.setSmallIcon(EpcDiagramEditorPlugin
				.findImageDescriptor("images/application.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createProcessInterface4CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(EpcElementTypes.ProcessInterface_2005);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.ProcessInterface4CreationTool_title,
				Messages.ProcessInterface4CreationTool_desc, types);
		entry.setSmallIcon(EpcDiagramEditorPlugin
				.findImageDescriptor("images/processinterface.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createTechnicalTerm5CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(EpcElementTypes.TechnicalTerm_2016);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.TechnicalTerm5CreationTool_title,
				Messages.TechnicalTerm5CreationTool_desc, types);
		entry.setSmallIcon(EpcDiagramEditorPlugin
				.findImageDescriptor("images/technicalterm.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createParticipant6CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(EpcElementTypes.Participant_2002);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Participant6CreationTool_title,
				Messages.Participant6CreationTool_desc, types);
		entry.setSmallIcon(EpcDiagramEditorPlugin
				.findImageDescriptor("images/participant.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createGroup7CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(EpcElementTypes.Group_2009);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Group7CreationTool_title,
				Messages.Group7CreationTool_desc, types);
		entry.setSmallIcon(EpcDiagramEditorPlugin
				.findImageDescriptor("images/group.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createLocation8CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(EpcElementTypes.Location_2014);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Location8CreationTool_title,
				Messages.Location8CreationTool_desc, types);
		entry.setSmallIcon(EpcDiagramEditorPlugin
				.findImageDescriptor("images/location.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createPosition9CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(EpcElementTypes.Position_2013);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Position9CreationTool_title,
				Messages.Position9CreationTool_desc, types);
		entry.setSmallIcon(EpcDiagramEditorPlugin
				.findImageDescriptor("images/position.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createFile10CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(EpcElementTypes.File_2019);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.File10CreationTool_title,
				Messages.File10CreationTool_desc, types);
		entry.setSmallIcon(EpcDiagramEditorPlugin
				.findImageDescriptor("images/file.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createDocument11CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(EpcElementTypes.Document_2018);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Document11CreationTool_title,
				Messages.Document11CreationTool_desc, types);
		entry.setSmallIcon(EpcDiagramEditorPlugin
				.findImageDescriptor("images/document.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createCardFile12CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(EpcElementTypes.CardFile_2017);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.CardFile12CreationTool_title,
				Messages.CardFile12CreationTool_desc, types);
		entry.setSmallIcon(EpcDiagramEditorPlugin
				.findImageDescriptor("images/cardfile.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createCluster13CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(EpcElementTypes.Cluster_2010);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Cluster13CreationTool_title,
				Messages.Cluster13CreationTool_desc, types);
		entry.setSmallIcon(EpcDiagramEditorPlugin
				.findImageDescriptor("images/cluster.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createInternalPerson14CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(EpcElementTypes.InternalPerson_2012);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.InternalPerson14CreationTool_title,
				Messages.InternalPerson14CreationTool_desc, types);
		entry.setSmallIcon(EpcDiagramEditorPlugin
				.findImageDescriptor("images/intperson.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createExternalPerson15CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(EpcElementTypes.ExternalPerson_2011);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.ExternalPerson15CreationTool_title,
				Messages.ExternalPerson15CreationTool_desc, types);
		entry.setSmallIcon(EpcDiagramEditorPlugin
				.findImageDescriptor("images/extperson.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createPersonType16CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(EpcElementTypes.PersonType_2015);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.PersonType16CreationTool_title,
				Messages.PersonType16CreationTool_desc, types);
		entry.setSmallIcon(EpcDiagramEditorPlugin
				.findImageDescriptor("images/persontype.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createProduct17CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(EpcElementTypes.Product_2021);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Product17CreationTool_title,
				Messages.Product17CreationTool_desc, types);
		entry.setSmallIcon(EpcDiagramEditorPlugin
				.findImageDescriptor("images/product.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createObjective18CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(EpcElementTypes.Objective_2020);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Objective18CreationTool_title,
				Messages.Objective18CreationTool_desc, types);
		entry.setSmallIcon(EpcDiagramEditorPlugin
				.findImageDescriptor("images/objective.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createAND1CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(EpcElementTypes.AND_2003);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.AND1CreationTool_title,
				Messages.AND1CreationTool_desc, types);
		entry.setSmallIcon(EpcDiagramEditorPlugin
				.findImageDescriptor("images/and.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createOR2CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(EpcElementTypes.OR_2001);
		NodeToolEntry entry = new NodeToolEntry(Messages.OR2CreationTool_title,
				Messages.OR2CreationTool_desc, types);
		entry.setSmallIcon(EpcDiagramEditorPlugin
				.findImageDescriptor("images/or.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createXOR3CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(EpcElementTypes.XOR_2008);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.XOR3CreationTool_title,
				Messages.XOR3CreationTool_desc, types);
		entry.setSmallIcon(EpcDiagramEditorPlugin
				.findImageDescriptor("images/xor.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createControlFlowArc1CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(EpcElementTypes.Arc_4001);
		LinkToolEntry entry = new LinkToolEntry(
				Messages.ControlFlowArc1CreationTool_title,
				Messages.ControlFlowArc1CreationTool_desc, types);
		entry.setSmallIcon(EpcDiagramEditorPlugin
				.findImageDescriptor("images/arc.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createInformationFlowArc2CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(EpcElementTypes.InformationArc_4003);
		LinkToolEntry entry = new LinkToolEntry(
				Messages.InformationFlowArc2CreationTool_title,
				Messages.InformationFlowArc2CreationTool_desc, types);
		entry.setSmallIcon(EpcDiagramEditorPlugin
				.findImageDescriptor("images/infarc.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createRelation3CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(EpcElementTypes.Relation_4002);
		LinkToolEntry entry = new LinkToolEntry(
				Messages.Relation3CreationTool_title,
				Messages.Relation3CreationTool_desc, types);
		entry.setSmallIcon(EpcDiagramEditorPlugin
				.findImageDescriptor("images/relation.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private static class NodeToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List elementTypes;

		/**
		 * @generated
		 */
		private NodeToolEntry(String title, String description,
				List elementTypes) {
			super(title, description, null, null);
			this.elementTypes = elementTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeCreationTool(elementTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}

	/**
	 * @generated
	 */
	private static class LinkToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List relationshipTypes;

		/**
		 * @generated
		 */
		private LinkToolEntry(String title, String description,
				List relationshipTypes) {
			super(title, description, null, null);
			this.relationshipTypes = relationshipTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeConnectionTool(relationshipTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}
}

package org.bflow.toolbox.vc.diagram.part;

import java.util.ArrayList;
import java.util.List;

import org.bflow.toolbox.vc.diagram.providers.VcElementTypes;
import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;

/**
 * @generated
 */
public class VcPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createSymbols1Group());
		paletteRoot.add(createConnections2Group());
	}

	/**
	 * Creates "Symbols" palette tool group
	 * @generated
	 */
	private PaletteContainer createSymbols1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(
				Messages.Symbols1Group_title);
		paletteContainer.add(createValueChainVariant11CreationTool());
		paletteContainer.add(createValueChainVariant22CreationTool());
		paletteContainer.add(createTechnicalTerm3CreationTool());
		paletteContainer.add(createCluster4CreationTool());
		paletteContainer.add(createObjective5CreationTool());
		paletteContainer.add(createProduct6CreationTool());
		return paletteContainer;
	}

	/**
	 * Creates "Connections" palette tool group
	 * @generated
	 */
	private PaletteContainer createConnections2Group() {
		PaletteGroup paletteContainer = new PaletteGroup(
				Messages.Connections2Group_title);
		paletteContainer.setDescription(Messages.Connections2Group_desc);
		paletteContainer.add(createIsPredecessorof1CreationTool());
		paletteContainer.add(createIsprocessorientedsuperior2CreationTool());
		paletteContainer.add(createRelation3CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createValueChainVariant11CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(VcElementTypes.ValueChain_2001);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.ValueChainVariant11CreationTool_title,
				Messages.ValueChainVariant11CreationTool_desc, types);
		entry.setSmallIcon(VcDiagramEditorPlugin
				.findImageDescriptor("images/valuechain.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createValueChainVariant22CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(VcElementTypes.ValueChain2_2002);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.ValueChainVariant22CreationTool_title,
				Messages.ValueChainVariant22CreationTool_desc, types);
		entry.setSmallIcon(VcDiagramEditorPlugin
				.findImageDescriptor("images/valuechain2.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createTechnicalTerm3CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(VcElementTypes.TechnicalTerm_2003);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.TechnicalTerm3CreationTool_title,
				Messages.TechnicalTerm3CreationTool_desc, types);
		entry.setSmallIcon(VcDiagramEditorPlugin
				.findImageDescriptor("images/technicalterm.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createCluster4CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(VcElementTypes.Cluster_2004);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Cluster4CreationTool_title,
				Messages.Cluster4CreationTool_desc, types);
		entry.setSmallIcon(VcDiagramEditorPlugin
				.findImageDescriptor("images/cluster.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createObjective5CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(VcElementTypes.Objective_2005);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Objective5CreationTool_title,
				Messages.Objective5CreationTool_desc, types);
		entry.setSmallIcon(VcDiagramEditorPlugin
				.findImageDescriptor("images/objective.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createProduct6CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(VcElementTypes.Product_2006);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Product6CreationTool_title,
				Messages.Product6CreationTool_desc, types);
		entry.setSmallIcon(VcDiagramEditorPlugin
				.findImageDescriptor("images/product.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createIsPredecessorof1CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(VcElementTypes.PredecessorConnection_4001);
		LinkToolEntry entry = new LinkToolEntry(
				Messages.IsPredecessorof1CreationTool_title,
				Messages.IsPredecessorof1CreationTool_desc, types);
		entry.setSmallIcon(VcDiagramEditorPlugin
				.findImageDescriptor("images/ispred.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createIsprocessorientedsuperior2CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(VcElementTypes.ProcessSuperiority_4002);
		LinkToolEntry entry = new LinkToolEntry(
				Messages.Isprocessorientedsuperior2CreationTool_title,
				Messages.Isprocessorientedsuperior2CreationTool_desc, types);
		entry.setSmallIcon(VcDiagramEditorPlugin
				.findImageDescriptor("images/superior.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createRelation3CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(VcElementTypes.Relation_4003);
		LinkToolEntry entry = new LinkToolEntry(
				Messages.Relation3CreationTool_title,
				Messages.Relation3CreationTool_desc, types);
		entry.setSmallIcon(VcDiagramEditorPlugin
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

/*
 * 
 */
package vcchart.diagram.part;

import java.util.Collections;

import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.tooling.runtime.part.DefaultLinkToolEntry;
import org.eclipse.gmf.tooling.runtime.part.DefaultNodeToolEntry;

import vcchart.diagram.Messages;
import vcchart.diagram.providers.VcElementTypes;

/**
 * @generated
 */
public class VcPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createObjects1Group());
		paletteRoot.add(createConnections2Group());
	}

	/**
	 * Creates "Objects" palette tool group
	 * @generated
	 */
	private PaletteContainer createObjects1Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(
				Messages.Objects1Group_title);
		paletteContainer.setId("createObjects1Group"); //$NON-NLS-1$
		paletteContainer.add(createActivity11CreationTool());
		paletteContainer.add(createActivity22CreationTool());
		paletteContainer.add(createObjective3CreationTool());
		paletteContainer.add(createProduct4CreationTool());
		paletteContainer.add(createApplication5CreationTool());
		paletteContainer.add(createCluster6CreationTool());
		paletteContainer.add(createDocument7CreationTool());
		paletteContainer.add(createParticipant8CreationTool());
		paletteContainer.add(createTechnicalTerm9CreationTool());
		return paletteContainer;
	}

	/**
	 * Creates "Connections" palette tool group
	 * @generated
	 */
	private PaletteContainer createConnections2Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(
				Messages.Connections2Group_title);
		paletteContainer.setId("createConnections2Group"); //$NON-NLS-1$
		paletteContainer.add(createRelation11CreationTool());
		paletteContainer.add(createRelation22CreationTool());
		paletteContainer.add(createRelation33CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createActivity11CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.Activity11CreationTool_title,
				Messages.Activity11CreationTool_desc,
				Collections.singletonList(VcElementTypes.Activity1_2003));
		entry.setId("createActivity11CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(VcElementTypes
				.getImageDescriptor(VcElementTypes.Activity1_2003));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createActivity22CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.Activity22CreationTool_title,
				Messages.Activity22CreationTool_desc,
				Collections.singletonList(VcElementTypes.Activity2_2004));
		entry.setId("createActivity22CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(VcElementTypes
				.getImageDescriptor(VcElementTypes.Activity2_2004));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createObjective3CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.Objective3CreationTool_title,
				Messages.Objective3CreationTool_desc,
				Collections.singletonList(VcElementTypes.Objective_2002));
		entry.setId("createObjective3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(VcElementTypes
				.getImageDescriptor(VcElementTypes.Objective_2002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createProduct4CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.Product4CreationTool_title,
				Messages.Product4CreationTool_desc,
				Collections.singletonList(VcElementTypes.Product_2001));
		entry.setId("createProduct4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(VcElementTypes
				.getImageDescriptor(VcElementTypes.Product_2001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createApplication5CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.Application5CreationTool_title,
				Messages.Application5CreationTool_desc,
				Collections.singletonList(VcElementTypes.Application_2008));
		entry.setId("createApplication5CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(VcElementTypes
				.getImageDescriptor(VcElementTypes.Application_2008));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createCluster6CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.Cluster6CreationTool_title,
				Messages.Cluster6CreationTool_desc,
				Collections.singletonList(VcElementTypes.Cluster_2005));
		entry.setId("createCluster6CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(VcElementTypes
				.getImageDescriptor(VcElementTypes.Cluster_2005));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createDocument7CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.Document7CreationTool_title,
				Messages.Document7CreationTool_desc,
				Collections.singletonList(VcElementTypes.Document_2009));
		entry.setId("createDocument7CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(VcElementTypes
				.getImageDescriptor(VcElementTypes.Document_2009));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createParticipant8CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.Participant8CreationTool_title,
				Messages.Participant8CreationTool_desc,
				Collections.singletonList(VcElementTypes.Participant_2007));
		entry.setId("createParticipant8CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(VcElementTypes
				.getImageDescriptor(VcElementTypes.Participant_2007));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createTechnicalTerm9CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.TechnicalTerm9CreationTool_title,
				Messages.TechnicalTerm9CreationTool_desc,
				Collections.singletonList(VcElementTypes.TechnicalTerm_2006));
		entry.setId("createTechnicalTerm9CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(VcElementTypes
				.getImageDescriptor(VcElementTypes.TechnicalTerm_2006));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createRelation11CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(
				Messages.Relation11CreationTool_title,
				Messages.Relation11CreationTool_desc,
				Collections.singletonList(VcElementTypes.Relation1_4001));
		entry.setId("createRelation11CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(VcElementTypes
				.getImageDescriptor(VcElementTypes.Relation1_4001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createRelation22CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(
				Messages.Relation22CreationTool_title,
				Messages.Relation22CreationTool_desc,
				Collections.singletonList(VcElementTypes.Relation2_4002));
		entry.setId("createRelation22CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(VcElementTypes
				.getImageDescriptor(VcElementTypes.Relation2_4002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createRelation33CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(
				Messages.Relation33CreationTool_title,
				Messages.Relation33CreationTool_desc,
				Collections.singletonList(VcElementTypes.Relation3_4003));
		entry.setId("createRelation33CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(VcElementTypes
				.getImageDescriptor(VcElementTypes.Relation3_4003));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

}

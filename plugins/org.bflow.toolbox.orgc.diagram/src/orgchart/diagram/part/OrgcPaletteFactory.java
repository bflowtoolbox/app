/*
 * 
 */
package orgchart.diagram.part;

import java.util.Collections;

import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.tooling.runtime.part.DefaultLinkToolEntry;
import org.eclipse.gmf.tooling.runtime.part.DefaultNodeToolEntry;
import orgchart.diagram.providers.OrgcElementTypes;

/**
 * @generated
 */
public class OrgcPaletteFactory {

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
		paletteContainer.add(createParticipant1CreationTool());
		paletteContainer.add(createGroup2CreationTool());
		paletteContainer.add(createLocation3CreationTool());
		paletteContainer.add(createPosition4CreationTool());
		paletteContainer.add(createInternalPerson5CreationTool());
		paletteContainer.add(createExternalPerson6CreationTool());
		paletteContainer.add(createPersonType7CreationTool());
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
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createParticipant1CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.Participant1CreationTool_title,
				Messages.Participant1CreationTool_desc,
				Collections.singletonList(OrgcElementTypes.Participant_2005));
		entry.setId("createParticipant1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(OrgcElementTypes
				.getImageDescriptor(OrgcElementTypes.Participant_2005));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createGroup2CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.Group2CreationTool_title,
				Messages.Group2CreationTool_desc,
				Collections.singletonList(OrgcElementTypes.Group_2004));
		entry.setId("createGroup2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(OrgcElementTypes
				.getImageDescriptor(OrgcElementTypes.Group_2004));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createLocation3CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.Location3CreationTool_title,
				Messages.Location3CreationTool_desc,
				Collections.singletonList(OrgcElementTypes.Location_2007));
		entry.setId("createLocation3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(OrgcElementTypes
				.getImageDescriptor(OrgcElementTypes.Location_2007));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createPosition4CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.Position4CreationTool_title,
				Messages.Position4CreationTool_desc,
				Collections.singletonList(OrgcElementTypes.Position_2001));
		entry.setId("createPosition4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(OrgcElementTypes
				.getImageDescriptor(OrgcElementTypes.Position_2001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createInternalPerson5CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.InternalPerson5CreationTool_title,
				Messages.InternalPerson5CreationTool_desc,
				Collections.singletonList(OrgcElementTypes.InternalPerson_2002));
		entry.setId("createInternalPerson5CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(OrgcElementTypes
				.getImageDescriptor(OrgcElementTypes.InternalPerson_2002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createExternalPerson6CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.ExternalPerson6CreationTool_title,
				Messages.ExternalPerson6CreationTool_desc,
				Collections.singletonList(OrgcElementTypes.ExternalPerson_2003));
		entry.setId("createExternalPerson6CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(OrgcElementTypes
				.getImageDescriptor(OrgcElementTypes.ExternalPerson_2003));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createPersonType7CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.PersonType7CreationTool_title,
				Messages.PersonType7CreationTool_desc,
				Collections.singletonList(OrgcElementTypes.PersonType_2006));
		entry.setId("createPersonType7CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(OrgcElementTypes
				.getImageDescriptor(OrgcElementTypes.PersonType_2006));
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
				Collections.singletonList(OrgcElementTypes.Relation1_4001));
		entry.setId("createRelation11CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(OrgcElementTypes
				.getImageDescriptor(OrgcElementTypes.Relation1_4001));
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
				Collections.singletonList(OrgcElementTypes.Relation2_4002));
		entry.setId("createRelation22CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(OrgcElementTypes
				.getImageDescriptor(OrgcElementTypes.Relation2_4002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

}

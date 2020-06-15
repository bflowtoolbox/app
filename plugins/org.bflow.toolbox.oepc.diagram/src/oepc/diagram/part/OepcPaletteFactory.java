package oepc.diagram.part;

import java.util.ArrayList;
import java.util.List;

import oepc.diagram.Messages;
import oepc.diagram.providers.OepcElementTypes;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;

/**
 * @generated
 */
public class OepcPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createElements1Group());
		paletteRoot.add(createConnections2Group());
	}

	/**
	 * Creates "Elements" palette tool group
	 * @generated
	 */
	private PaletteContainer createElements1Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(
				Messages.Elements1Group_title);
		paletteContainer.add(createEreignis1CreationTool());
		paletteContainer.add(createITSystem2CreationTool());
		paletteContainer.add(createOrganisationseinheit3CreationTool());
		paletteContainer.add(createDokument4CreationTool());
		paletteContainer.add(new PaletteSeparator());
		paletteContainer.add(createXOR6CreationTool());
		paletteContainer.add(createAND7CreationTool());
		paletteContainer.add(createOR8CreationTool());
		paletteContainer.add(new PaletteSeparator());
		paletteContainer.add(createGeschaeftsobjekt10CreationTool());
		paletteContainer.add(createAttribut11CreationTool());
		paletteContainer.add(createMethode12CreationTool());
		return paletteContainer;
	}

	/**
	 * Creates "Connections" palette tool group
	 * @generated
	 */
	private PaletteContainer createConnections2Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(
				Messages.Connections2Group_title);
		paletteContainer.add(createControlFlow1CreationTool());
		paletteContainer.add(createInformationFlow2CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createEreignis1CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(OepcElementTypes.Event_2001);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Ereignis1CreationTool_title, null, types);
		entry.setSmallIcon(OepcElementTypes
				.getImageDescriptor(OepcElementTypes.Event_2001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createITSystem2CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(OepcElementTypes.ITSystem_2002);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.ITSystem2CreationTool_title, null, types);
		entry.setSmallIcon(OepcElementTypes
				.getImageDescriptor(OepcElementTypes.ITSystem_2002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createOrganisationseinheit3CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(OepcElementTypes.OrganisationUnit_2003);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Organisationseinheit3CreationTool_title, null, types);
		entry.setSmallIcon(OepcElementTypes
				.getImageDescriptor(OepcElementTypes.OrganisationUnit_2003));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createDokument4CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(OepcElementTypes.Document_2008);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Dokument4CreationTool_title,
				Messages.Dokument4CreationTool_desc, types);
		entry.setSmallIcon(OepcElementTypes
				.getImageDescriptor(OepcElementTypes.Document_2008));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createXOR6CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(OepcElementTypes.XORConnector_2004);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.XOR6CreationTool_title, null, types);
		entry.setSmallIcon(OepcElementTypes
				.getImageDescriptor(OepcElementTypes.XORConnector_2004));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createAND7CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(OepcElementTypes.ANDConnector_2006);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.AND7CreationTool_title, null, types);
		entry.setSmallIcon(OepcElementTypes
				.getImageDescriptor(OepcElementTypes.ANDConnector_2006));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createOR8CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(OepcElementTypes.ORConnector_2007);
		NodeToolEntry entry = new NodeToolEntry(Messages.OR8CreationTool_title,
				null, types);
		entry.setSmallIcon(OepcElementTypes
				.getImageDescriptor(OepcElementTypes.ORConnector_2007));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}


	/**
	 * @generated NOT
	 */
	private ToolEntry createGeschaeftsobjekt10CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(OepcElementTypes.BusinessObject_2005);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Geschaeftsobjekt10CreationTool_title, null, types);
		entry.setSmallIcon(OepcElementTypes
				.getImageDescriptor(OepcElementTypes.BusinessObject_2005));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createAttribut11CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(OepcElementTypes.BusinessAttribute_3001);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Attribut11CreationTool_title, null, types);
		entry.setSmallIcon(OepcElementTypes
				.getImageDescriptor(OepcElementTypes.BusinessAttribute_3001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createMethode12CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(OepcElementTypes.BusinessMethod_3002);
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Methode12CreationTool_title, null, types);
		entry.setSmallIcon(OepcElementTypes
				.getImageDescriptor(OepcElementTypes.BusinessMethod_3002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createControlFlow1CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(OepcElementTypes.ControlFlowEdge_4001);
		LinkToolEntry entry = new LinkToolEntry(
				Messages.ControlFlow1CreationTool_title, null, types);
		entry.setSmallIcon(OepcElementTypes
				.getImageDescriptor(OepcElementTypes.ControlFlowEdge_4001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createInformationFlow2CreationTool() {
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(OepcElementTypes.InformationEdge_4002);
		LinkToolEntry entry = new LinkToolEntry(
				Messages.InformationFlow2CreationTool_title, null, types);
		entry.setSmallIcon(OepcElementTypes
				.getImageDescriptor(OepcElementTypes.InformationEdge_4002));
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

package org.bflow.toolbox.epc.diagram.part;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.ContributionItemService;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.providers.DiagramContextMenuProvider;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @generated
 */
public class DiagramEditorContextMenuProvider extends
		DiagramContextMenuProvider {

	/**
	 * @generated
	 */
	private IWorkbenchPart part;

	/**
	 * @generated
	 */
	private DeleteElementAction deleteAction;

	/**
	 * @generated
	 */
	public DiagramEditorContextMenuProvider(IWorkbenchPart part,
			EditPartViewer viewer) {
		super(part, viewer);
		this.part = part;
		deleteAction = new DeleteElementAction(part);
		deleteAction.init();
	}

	/**
	 * @generated
	 */
	public void dispose() {
		if (deleteAction != null) {
			deleteAction.dispose();
			deleteAction = null;
		}
		super.dispose();
	}

	/**
	 * @generated NOT
	 */
	public void buildContextMenu(final IMenuManager menu) {
		getViewer().flush();
		try {
			TransactionUtil.getEditingDomain(
					(EObject) getViewer().getContents().getModel())
					.runExclusive(new Runnable() {

						public void run() {
							ContributionItemService
									.getInstance()
									.contributeToPopupMenu(
											DiagramEditorContextMenuProvider.this,
											part);
							menu.remove(ActionIds.ACTION_DELETE_FROM_MODEL);
							menu.appendToGroup("editGroup", deleteAction);
							
							moveEditMenu(menu);
						}
					});
		} catch (Exception e) {
			EpcDiagramEditorPlugin.getInstance().logError(
					"Error building context menu", e);
		}
	}
	
	
	private void moveEditMenu(final IMenuManager iMenuManager){
		IContributionItem[] menus = iMenuManager.getItems();
		for(int i = 0; i < menus.length; i++){
			IContributionItem menu = menus[i];
			if(menu instanceof MenuManager){
				MenuManager menuManager = (MenuManager) menu;
				if(menuManager.getId() != null){
					if(menuManager.getId().equals("editMenu")){
						IContributionItem subitems[] = menuManager.getItems();
						iMenuManager.remove(menuManager);
						
						for(int j = 0; j < subitems.length; j++){
							iMenuManager.appendToGroup("editGroup", subitems[j]);
						}
					}
				}
			}
		}
	}
}

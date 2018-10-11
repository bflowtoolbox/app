package org.bflow.toolbox.application;

import org.bflow.toolbox.hive.attributes.AttributeViewPart;
import org.bflow.toolbox.hive.modelnavigator.ModelNavigatorView;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * Implements {@link IPerspectiveFactory} to define a default bflow perspective 
 * configuration with the most common features.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2018-10-11
 *
 */
public class DefaultBflowPerspective implements IPerspectiveFactory {
	
	/** Unique perspective id */
	public static final String Id = "org.bflow.toolbox.application.perspective.default";
	
	private static final String LeftTopFolderId = "bflow.perspective.default.leftTopFolder";
	private static final String LeftBottomFolderId = "bflow.perspective.default.leftBottomFolder";
	// private static final String RightTopFolderId = "bflow.perspective.default.rightTopFolder";
	private static final String BottomFolderId = "bflow.perspective.default.bottomFolder";

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
	 */
	@Override
	public void createInitialLayout(IPageLayout layout) {
		IFolderLayout leftTopFolder = layout.createFolder(LeftTopFolderId, IPageLayout.LEFT, 0.2f, layout.getEditorArea());
		// IFolderLayout rigtTopFolder = layout.createFolder(RightTopFolderId, IPageLayout.RIGHT, 0.8f, layout.getEditorArea());
		IFolderLayout leftBottomFolder = layout.createFolder(LeftBottomFolderId, IPageLayout.BOTTOM, 0.4f, LeftTopFolderId);
		IFolderLayout bottomFolder = layout.createFolder(BottomFolderId, IPageLayout.BOTTOM, 0.7f, layout.getEditorArea());
		
		// Filling the left top folder
		leftTopFolder.addView(IPageLayout.ID_PROJECT_EXPLORER);
		
		// Filling the right top folder
		
		// Filling the left bottom folder
		leftBottomFolder.addView(ModelNavigatorView.ViewId);
		
		// Filling the bottom folder
		bottomFolder.addView(IPageLayout.ID_PROBLEM_VIEW);
		bottomFolder.addView(AttributeViewPart.VIEW_ID);		
	}
}

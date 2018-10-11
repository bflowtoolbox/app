package org.bflow.toolbox.application;

import org.bflow.toolbox.hive.annotations.AnnotationRuleViewPart;
import org.bflow.toolbox.hive.annotations.AnnotationViewPart;
import org.bflow.toolbox.hive.attributefilter.AttributeFilterViewPart;
import org.bflow.toolbox.hive.attributes.AttributeViewPart;
import org.bflow.toolbox.hive.modelnavigator.ModelNavigatorView;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;

/**
 * Implements {@link IPerspectiveFactory} to provide a default
 * configuration for operating with common bflow features.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2012-02-27
 * @version 2013-12-30
 * 			2015-08-25 Changed order of view parts at the bottom
 * 			2018-10-11 Renamed from BflowPerspective to FeaturedBflowPerspective
 *
 */
public class FeaturedBflowPerspective implements IPerspectiveFactory {
	
	/** Unique perspective id */
	public static final String PerspectiveId = "org.bflow.toolbox.application.perspective.featured";
	
	private static final String LeftTopFolderId = "bflow.leftTopFolder";
	private static final String LeftBottomFolderId = "bflow.leftBottomFolder";
	private static final String RightTopFolderId = "bflow.rightTopFolder";
	private static final String BottomFolderId = "bflow.bottomFolder";

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
	 */
	@Override
	public void createInitialLayout(IPageLayout layout) {
		IFolderLayout leftTopFolder = layout.createFolder(LeftTopFolderId, IPageLayout.LEFT, 0.2f, layout.getEditorArea());
		IFolderLayout rigtTopFolder = layout.createFolder(RightTopFolderId, IPageLayout.RIGHT, 0.8f, layout.getEditorArea());
		IFolderLayout leftBottomFolder = layout.createFolder(LeftBottomFolderId, IPageLayout.BOTTOM, 0.4f, LeftTopFolderId);
		IFolderLayout bottomFolder = layout.createFolder(BottomFolderId, IPageLayout.BOTTOM, 0.7f, layout.getEditorArea());
		
		// Filling the left top folder
		leftTopFolder.addView(IPageLayout.ID_PROJECT_EXPLORER);
		
		//Filling the right top folder
		rigtTopFolder.addView(AnnotationViewPart.VIEW_ID);

		// Filling the left bottom folder
		leftBottomFolder.addView(IPageLayout.ID_OUTLINE);
		leftBottomFolder.addView(ModelNavigatorView.ViewId);
		
		// Filling the bottom folder
		bottomFolder.addView(IPageLayout.ID_PROBLEM_VIEW);
		bottomFolder.addView(IConsoleConstants.ID_CONSOLE_VIEW);
		bottomFolder.addView(AttributeViewPart.VIEW_ID);
		bottomFolder.addView(AnnotationRuleViewPart.VIEW_ID);
		bottomFolder.addView(AttributeFilterViewPart.VIEW_ID);
	}
}

package org.bflow.toolbox.hive.eclipse.integration;

import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorMatchingStrategy;
import org.eclipse.ui.ide.IEditorAssociationOverride;

/**
 * Provides an implementation of {@link IEditorAssociationOverride} to override
 * the editor that is going to be opended when the proxy feature for editor is
 * activated.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 26.07.2015
 */
public class EditorAssociationOverride implements IEditorAssociationOverride {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.ide.IEditorAssociationOverride#overrideEditors(org.eclipse.ui.IEditorInput, org.eclipse.core.runtime.content.IContentType, org.eclipse.ui.IEditorDescriptor[])
	 */
	@Override
	public IEditorDescriptor[] overrideEditors(IEditorInput editorInput, IContentType contentType, IEditorDescriptor[] editorDescriptors) {
		return editorDescriptors;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.ide.IEditorAssociationOverride#overrideEditors(java.lang.String, org.eclipse.core.runtime.content.IContentType, org.eclipse.ui.IEditorDescriptor[])
	 */
	@Override
	public IEditorDescriptor[] overrideEditors(String fileName, IContentType contentType, IEditorDescriptor[] editorDescriptors) {
		return editorDescriptors;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.ide.IEditorAssociationOverride#overrideDefaultEditor(org.eclipse.ui.IEditorInput, org.eclipse.core.runtime.content.IContentType, org.eclipse.ui.IEditorDescriptor)
	 */
	@Override
	public IEditorDescriptor overrideDefaultEditor(IEditorInput editorInput, IContentType contentType, final IEditorDescriptor editorDescriptor) {
		String fileName = editorInput.getName();
		String originEditorId = editorDescriptor.getId();
		
		// Check if this isn't already a proxy
		if (EclipseIntegrator.isProxy(originEditorId, fileName)) return editorDescriptor;
		
		// Check if this editor is proxy supported
		if (!EclipseIntegrator.isProxySupported(originEditorId, fileName)) return editorDescriptor;
		
		// Override with proxy editor
		final String proxyEditorId = DiagramEditorProxyRegistry.getEditorProxyId(originEditorId, fileName);
		return new IEditorDescriptor() {
			/* (non-Javadoc)
			 * @see org.eclipse.ui.IEditorDescriptor#getId()
			 */
			@Override
			public String getId() {
				return proxyEditorId;
			}

			/* (non-Javadoc)
			 * @see org.eclipse.ui.IEditorDescriptor#getImageDescriptor()
			 */
			@Override
			public ImageDescriptor getImageDescriptor() {
				return editorDescriptor.getImageDescriptor();
			}

			/* (non-Javadoc)
			 * @see org.eclipse.ui.IEditorDescriptor#getLabel()
			 */
			@Override
			public String getLabel() {
				return editorDescriptor.getLabel();
			}

			/* (non-Javadoc)
			 * @see org.eclipse.ui.IEditorDescriptor#isInternal()
			 */
			@Override
			public boolean isInternal() {
				return editorDescriptor.isInternal();
			}

			/* (non-Javadoc)
			 * @see org.eclipse.ui.IEditorDescriptor#isOpenInPlace()
			 */
			@Override
			public boolean isOpenInPlace() {
				return editorDescriptor.isOpenInPlace();
			}

			/* (non-Javadoc)
			 * @see org.eclipse.ui.IEditorDescriptor#isOpenExternal()
			 */
			@Override
			public boolean isOpenExternal() {
				return editorDescriptor.isOpenExternal();
			}

			/* (non-Javadoc)
			 * @see org.eclipse.ui.IEditorDescriptor#getEditorMatchingStrategy()
			 */
			@Override
			public IEditorMatchingStrategy getEditorMatchingStrategy() {
				return null; // TODO Return correct value
			}
		};
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.ide.IEditorAssociationOverride#overrideDefaultEditor(java.lang.String, org.eclipse.core.runtime.content.IContentType, org.eclipse.ui.IEditorDescriptor)
	 */
	@Override
	public IEditorDescriptor overrideDefaultEditor(String fileName, IContentType contentType, IEditorDescriptor editorDescriptor) {
		return editorDescriptor;
	}

}

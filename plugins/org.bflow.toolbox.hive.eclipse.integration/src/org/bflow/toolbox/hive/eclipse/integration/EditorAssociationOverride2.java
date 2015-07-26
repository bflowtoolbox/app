package org.bflow.toolbox.hive.eclipse.integration;

import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.ide.IEditorAssociationOverride;

public class EditorAssociationOverride2 implements IEditorAssociationOverride {

	public EditorAssociationOverride2() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.ide.IEditorAssociationOverride#overrideEditors(org.eclipse.ui.IEditorInput, org.eclipse.core.runtime.content.IContentType, org.eclipse.ui.IEditorDescriptor[])
	 */
	@Override
	public IEditorDescriptor[] overrideEditors(IEditorInput editorInput, IContentType contentType, IEditorDescriptor[] editorDescriptors) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.ide.IEditorAssociationOverride#overrideEditors(java.lang.String, org.eclipse.core.runtime.content.IContentType, org.eclipse.ui.IEditorDescriptor[])
	 */
	@Override
	public IEditorDescriptor[] overrideEditors(String fileName, IContentType contentType, IEditorDescriptor[] editorDescriptors) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.ide.IEditorAssociationOverride#overrideDefaultEditor(org.eclipse.ui.IEditorInput, org.eclipse.core.runtime.content.IContentType, org.eclipse.ui.IEditorDescriptor)
	 */
	@Override
	public IEditorDescriptor overrideDefaultEditor(IEditorInput editorInput, IContentType contentType, IEditorDescriptor editorDescriptor) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.ide.IEditorAssociationOverride#overrideDefaultEditor(java.lang.String, org.eclipse.core.runtime.content.IContentType, org.eclipse.ui.IEditorDescriptor)
	 */
	@Override
	public IEditorDescriptor overrideDefaultEditor(String fileName, IContentType contentType, IEditorDescriptor editorDescriptor) {
		// TODO Auto-generated method stub
		return null;
	}

}

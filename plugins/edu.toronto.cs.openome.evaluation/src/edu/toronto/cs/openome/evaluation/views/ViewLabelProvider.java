package edu.toronto.cs.openome.evaluation.views;

import java.awt.Toolkit;
import java.util.ArrayList;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableFontProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import edu.toronto.cs.openome.evaluation.gui.EvaluationElementTypeLabelProvider;
import edu.toronto.cs.openome_model.Alternative;
import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.HumanJudgment;
import edu.toronto.cs.openome_model.Intention;

/**
 * 
 * Label provider class 
 *
 */
public class ViewLabelProvider extends EvaluationElementTypeLabelProvider implements ITableLabelProvider, ITableColorProvider {

	public String getText(Object obj) {
		return obj.toString();
	}
	
	public Image getImage(Object obj) {
		TreeNode node = (TreeNode)obj;

		if(node.getImg() != null) {
			return super.getEvalImage((EvaluationLabel)node.getImg());
		} else if(node.isAlternative()) {
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
		} else if(node.isHardgoal()) {
			ImageDescriptor id = edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
			.findImageDescriptor("/openome_model/icons/hardgoal.gif");
			Image image = new Image(null, id.createImage(), 0);
			return image;
		} else if(node.isSoftgoal()) {
			ImageDescriptor id = edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
			.findImageDescriptor("/openome_model/icons/softgoal.gif");
			Image image = new Image(null, id.createImage(), 0);
			return image;
		} else if(node.isTask()) {
			ImageDescriptor id = edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
			.findImageDescriptor("/openome_model/icons/task.gif");
			Image image = new Image(null, id.createImage(), 0);
			return image;
		} else if(node.isResource()) {
			ImageDescriptor id = edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
			.findImageDescriptor("/openome_model/icons/resource.gif");
			Image image = new Image(null, id.createImage(), 0);
			return image;
		} else if(node.getConflict()) {
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_ERROR_TSK);
		} else {
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}

	@Override
	public Color getBackground(Object element, int columnIndex) {
		return null;
	}

	@Override
	public Color getForeground(Object element, int columnIndex) {
		
		//Check if this element has been affected by some change in the Human Judgments view 
		Object e = ((TreeNode) element).getObject();
		Color orange = new Color(null, 238, 154, 0);
		
		if (e instanceof Alternative){
			if (((Alternative) e).getAffectedStatus() == true){
				return orange;
			}
		} else if (e instanceof Intention){
			if (((Intention) e).getAffectedStatus() == true){
				return orange;
			}
		} else if (e instanceof HumanJudgment){
			if (((HumanJudgment) e).getAffectedStatus() == true){
				return orange;
			}
		}

		return null;
		
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		
		TreeNode node = (TreeNode) element;

		if(node.getImg() != null) {
			return super.getEvalImage((EvaluationLabel)node.getImg());
		} else if(node.isAlternative()) {
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
		} else if(node.isHardgoal()) {
			ImageDescriptor id = edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
			.findImageDescriptor("/openome_model/icons/hardgoal.gif");
			Image image = new Image(null, id.createImage(), 0);
			return image;
		} else if(node.isSoftgoal()) {
			ImageDescriptor id = edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
			.findImageDescriptor("/openome_model/icons/softgoal.gif");
			Image image = new Image(null, id.createImage(), 0);
			return image;
		} else if(node.isTask()) {
			ImageDescriptor id = edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
			.findImageDescriptor("/openome_model/icons/task.gif");
			Image image = new Image(null, id.createImage(), 0);
			return image;
		} else if(node.isResource()) {
			ImageDescriptor id = edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
			.findImageDescriptor("/openome_model/icons/resource.gif");
			Image image = new Image(null, id.createImage(), 0);
			return image;
		} else if(node.getConflict()) {
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_ERROR_TSK);
		} else {
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {

		return element.toString();
	}


	
}

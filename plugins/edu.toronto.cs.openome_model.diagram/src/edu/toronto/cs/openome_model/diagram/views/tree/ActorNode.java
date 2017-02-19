package edu.toronto.cs.openome_model.diagram.views.tree;

import java.util.ArrayList;

import org.eclipse.swt.graphics.Image;

import edu.toronto.cs.openome_model.Actor;
import edu.toronto.cs.openome_model.Agent;
import edu.toronto.cs.openome_model.Container;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Position;
import edu.toronto.cs.openome_model.Role;
import edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin;


class ActorNode extends Node {
	
	public ActorNode(Container actor) {
		super(actor.getName());
		setModel(actor);
		
		for (Intention intention : actor.getIntentions()) {
			IntentionNode child = new IntentionNode(intention);
			addChild(child);
		}
	}
	
	public Image getImage() {
		Container actor = (Container) getModel();
		Image image = null;
		
		if (actor instanceof Actor) {
			image = Openome_modelDiagramEditorPlugin.findImageDescriptor("/openome_model/icons/actor.gif").createImage();
		} else if (actor instanceof Agent) {
			image = Openome_modelDiagramEditorPlugin.findImageDescriptor("/openome_model/icons/agent.gif").createImage();
		} else if (actor instanceof Position) {
			image = Openome_modelDiagramEditorPlugin.findImageDescriptor("/openome_model/icons/position.gif").createImage();
		} else if (actor instanceof Role) {
			image = Openome_modelDiagramEditorPlugin.findImageDescriptor("/openome_model/icons/role.gif").createImage();
		}
		
		return image;
	}

}
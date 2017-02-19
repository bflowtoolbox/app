package edu.toronto.cs.openome.conversion.codegen;
public class GMFProjectGeneration extends edu.toronto.cs.openome.conversion.action.GMFProjectGeneration {
	public GMFProjectGeneration() {
		super("openome_model", 
			  "/model/openome_model.gmfgen", 
			  "edu.toronto.cs.openome_model",
			  new String [] {
				// These adaptations are to remove the annoying border of the compartment shape
				"edu.toronto.cs.openome_model.diagram/src/edu/toronto/cs/openome_model/diagram/edit/parts/RoleRoleCompartmentEditPart.java",
				"edu.toronto.cs.openome_model.diagram/src/edu/toronto/cs/openome_model/diagram/edit/parts/PositionPositionCompartmentEditPart.java",
				"edu.toronto.cs.openome_model.diagram/src/edu/toronto/cs/openome_model/diagram/edit/parts/ContainerCompartmentEditPart.java",
				"edu.toronto.cs.openome_model.diagram/src/edu/toronto/cs/openome_model/diagram/edit/parts/AspectAspectCompartmentEditPart.java",
				"edu.toronto.cs.openome_model.diagram/src/edu/toronto/cs/openome_model/diagram/edit/parts/AgentAgentCompartmentEditPart.java",
				"edu.toronto.cs.openome_model.diagram/src/edu/toronto/cs/openome_model/diagram/edit/parts/ActorActorCompartmentEditPart.java",
			});
	}
}

# This script is used for transforming ARIS AML files to bflow EPC files

# Define the model informations
model = {
id = $//Group/@Group.Id$
name = 'Untitled'
}

# Define the Functions
shapes = $//Group/ObjDef[@TypeNum='OT_FUNC']$ {
id = $@ObjDef.ID$
type = 'org.bflow.toolbox.epc.diagram.Function_2007'
name = $AttrDef[@AttrDef.Type='AT_NAME']//PlainText/@TextValue$
width  = $//Group/Model/ObjOcc[@ObjDef.IdRef={@ObjDef.ID}]/Size/@Size.dX$ | 64
height = $//Group/Model/ObjOcc[@ObjDef.IdRef={@ObjDef.ID}]/Size/@Size.dY$ | 64
x = $//Group/Model/ObjOcc[@ObjDef.IdRef={@ObjDef.ID}]/Position/@Pos.X$ | 0
y = $//Group/Model/ObjOcc[@ObjDef.IdRef={@ObjDef.ID}]/Position/@Pos.Y$ | 0
}

# Define the Events
shapes = $//Group/ObjDef[@TypeNum='OT_EVT']$ {
id = $@ObjDef.ID$
type = 'org.bflow.toolbox.epc.diagram.Event_2006'
name = $AttrDef[@AttrDef.Type='AT_NAME']//PlainText/@TextValue$
width  = $//Group/Model/ObjOcc[@ObjDef.IdRef={@ObjDef.ID}]/Size/@Size.dX$ | 64
height = $//Group/Model/ObjOcc[@ObjDef.IdRef={@ObjDef.ID}]/Size/@Size.dY$ | 64
x = $//Group/Model/ObjOcc[@ObjDef.IdRef={@ObjDef.ID}]/Position/@Pos.X$ | 0
y = $//Group/Model/ObjOcc[@ObjDef.IdRef={@ObjDef.ID}]/Position/@Pos.Y$ | 0
}

# Define the XORs
shapes = $//Group/ObjDef[@SymbolNum='ST_OPR_XOR_1']$ {
id = $@ObjDef.ID$
type = 'org.bflow.toolbox.epc.diagram.XOR_2008'
width = 26
height = 26
x = $//Group/Model/ObjOcc[@ObjDef.IdRef={@ObjDef.ID}]/Position/@Pos.X$ | 0
y = $//Group/Model/ObjOcc[@ObjDef.IdRef={@ObjDef.ID}]/Position/@Pos.Y$ | 0
}

# Define the ANDs
shapes = $//Group/ObjDef[@SymbolNum='ST_OPR_AND_1']$ {
id = $@ObjDef.ID$
type = 'org.bflow.toolbox.epc.diagram.AND_2003'
width = 26
height = 26
x = $//Group/Model/ObjOcc[@ObjDef.IdRef={@ObjDef.ID}]/Position/@Pos.X$ | 0
y = $//Group/Model/ObjOcc[@ObjDef.IdRef={@ObjDef.ID}]/Position/@Pos.Y$ | 0
}

# Define the ORs
shapes = $//Group/ObjDef[@SymbolNum='ST_OPR_OR_1']$ {
id = $@ObjDef.ID$
type = 'org.bflow.toolbox.epc.diagram.OR_2001'
width = 26
height = 26
x = $//Group/Model/ObjOcc[@ObjDef.IdRef={@ObjDef.ID}]/Position/@Pos.X$ | 0
y = $//Group/Model/ObjOcc[@ObjDef.IdRef={@ObjDef.ID}]/Position/@Pos.Y$ | 0
}

# Define the Edges
edges = $//Group/ObjDef/CxnDef$ {
type = 'org.bflow.toolbox.epc.diagram.Arc_4001'
source = shape <=> shape.id == $ancestor::ObjDef/@ObjDef.ID$
target = shape <=> shape.id == $@ToObjDef.IdRef$
}
# This script is used for transforming EPML files to EPC files

# Define the model informations
model = {
id = $//epc/@epcId$
name = $//epc/@name$ | ''
attributes = $//epc/attribute/@typeRef$ => $//epc/attribute/@value$
}

# Define the Events
shapes = $//epc/event$ {
id = $@id$
type = 'org.bflow.toolbox.epc.diagram.Event_2006'
name = $name/text()$ | ''
width  = $graphics/position/@width$ | 64
height = $graphics/position/@height$ | 64
x = $graphics/position/@x$ | 0
y = $graphics/position/@y$ | 0
}

# Define the Functions
shapes = $//epc/function$ {
id = $@id$
type = 'org.bflow.toolbox.epc.diagram.Function_2007'
name = $name/text()$ | ''
width  = $graphics/position/@width$ | 64
height = $graphics/position/@height$ | 64
x = $graphics/position/@x$ | 0
y = $graphics/position/@y$ | 0
}

# Define the Xors
shapes = $//epc/xor$ {
id = $@id$
type = 'org.bflow.toolbox.epc.diagram.XOR_2008'
width = 26
height = 26
x = $graphics/position/@x$ | 0
y = $graphics/position/@y$ | 0
}

# Define the Ands
shapes = $//epc/and$ {
id = $@id$
type = 'org.bflow.toolbox.epc.diagram.AND_2003'
width = 26
height = 26
x = $graphics/position/@x$ | 0
y = $graphics/position/@y$ | 0
}

# Define the Ors
shapes = $//epc/or$ {
id = $@id$
type = 'org.bflow.toolbox.epc.diagram.OR_2001'
width = 26
height = 26
x = $graphics/position/@x$ | 0
y = $graphics/position/@y$ | 0
}

# Define the Edges
edges = $//epc/arc$ {
type = 'org.bflow.toolbox.epc.diagram.Arc_4001'
source = shape <=> shape.id == $flow/@source$
target = shape <=> shape.id == $flow/@target$
}

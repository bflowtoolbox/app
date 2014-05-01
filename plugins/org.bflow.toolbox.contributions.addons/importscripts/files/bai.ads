# This script is used for transforming BPM AI JSON files to EPC files

# Define the model informations
model = {
id = '1'
name = '1'
#attributes = $//epc/attribute/@typeRef$ => $//epc/attribute/@value$
}

# Define the Events
shapes = $//childShapes/e[stencil/id/text() = 'Event']$ {
id = $resourceId/text()$
type = 'org.bflow.toolbox.epc.diagram.Event_2006'
name = $properties/title/text()$
width  = $bounds/lowerRight/x/text() - bounds/upperLeft/x/text()$ | 64
height = $bounds/lowerRight/y/text() - bounds/upperLeft/y/text()$ | 64
x = $bounds/upperLeft/x/text() + (bounds/lowerRight/x/text() - bounds/upperLeft/x/text())$ | 0
y = $bounds/upperLeft/y/text() + (bounds/lowerRight/y/text() - bounds/upperLeft/y/text())$ | 0
}

# Define the Functions
shapes = $//childShapes/e[stencil/id/text() = 'Function']$ {
id = $resourceId/text()$
type = 'org.bflow.toolbox.epc.diagram.Function_2007'
name = $properties/title/text()$
width  = $bounds/lowerRight/x/text() - bounds/upperLeft/x/text()$ | 64
height = $bounds/lowerRight/y/text() - bounds/upperLeft/y/text()$ | 64
x = $bounds/upperLeft/x/text() + (bounds/lowerRight/x/text() - bounds/upperLeft/x/text())$ | 0
y = $bounds/upperLeft/y/text() + (bounds/lowerRight/y/text() - bounds/upperLeft/y/text())$ | 0
}

# Define the Xors
shapes = $//childShapes/e[stencil/id/text() = 'XorConnector']$ {
id = $resourceId/text()$
type = 'org.bflow.toolbox.epc.diagram.XOR_2008'
width = 26
height = 26
x = $bounds/upperLeft/x/text() + (bounds/lowerRight/x/text() - bounds/upperLeft/x/text()) + 35$ | 0
y = $bounds/upperLeft/y/text() + (bounds/lowerRight/y/text() - bounds/upperLeft/y/text()) + 20$ | 0
}

# Define the Ors
shapes = $//childShapes/e[stencil/id/text() = 'OrConnector']$ {
id = $resourceId/text()$
type = 'org.bflow.toolbox.epc.diagram.OR_2001'
width = 26
height = 26
x = $bounds/upperLeft/x/text() + (bounds/lowerRight/x/text() - bounds/upperLeft/x/text()) + 35$ | 0
y = $bounds/upperLeft/y/text() + (bounds/lowerRight/y/text() - bounds/upperLeft/y/text()) + 20$ | 0
}

# Define the Ands
shapes = $//childShapes/e[stencil/id/text() = 'AndConnector']$ {
id = $resourceId/text()$
type = 'org.bflow.toolbox.epc.diagram.AND_2003'
width = 26
height = 26
x = $bounds/upperLeft/x/text() + (bounds/lowerRight/x/text() - bounds/upperLeft/x/text()) + 35$ | 0
y = $bounds/upperLeft/y/text() + (bounds/lowerRight/y/text() - bounds/upperLeft/y/text()) + 20$ | 0
}

# Define the Edges
edges = $//childShapes/e[stencil/id/text() = 'ControlFlow']$ {
type = 'org.bflow.toolbox.epc.diagram.Arc_4001'
source = shape <=> shape.id == $//childShapes/e[outgoing/e/resourceId/text() = {resourceId/text()}]/resourceId/text()$
target = shape <=> shape.id == $outgoing/e/resourceId/text()$
}
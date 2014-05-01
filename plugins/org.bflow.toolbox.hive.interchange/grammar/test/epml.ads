# This is a test script for the aditus parser
# The script is aligned for an epml import 

# Defines some classpath imports
import abc.def.ClassX
import abc.def.ClassX

# Define the model informations
model = {
id = $//directory/epc/@epcId$
name = $/go$
attributes = $//go$ => $/go$
}

# Definition der Events
shapes = $//directory/epc/event$ {
id = $/@id$
type = 'org.bflow.toolbox.epc.event'
name = $/name/text()$
width  = $/graphics/@width$ | 32
height = $/grapics/@height$ | 32
x = $/graphics/@x$ | 0
y = $/graphics/@y$ | 0
}

# Definition der Funktionen
shapes = $//directory/epc/function$ {
id = $/@id$
name = $/name/text()$
width  = $/graphics/@width$ | 32
height = $/grapics/@height$ | 32
x = $/graphics/@x$ | 0
y = $/graphics/@y$ | 0
}

# Definition der Kanten
edges = $//directory/epc/arc$ {
source = shape <=> shape.id == $/flow/@source$
target = edge <=> edge.id == $/flow/@target$
}
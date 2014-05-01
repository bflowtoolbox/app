grammar AditusGrammar;

@lexer::header {
package org.bflow.toolbox.interchange.mif.grammar;
}

@parser::header {
package org.bflow.toolbox.interchange.mif.grammar;

import org.bflow.toolbox.interchange.mif.aditus.AditusMetaInfo;
import org.bflow.toolbox.interchange.mif.aditus.AditusModelMetaInfo;
import org.bflow.toolbox.interchange.mif.aditus.AditusShapeMetaInfo;
import org.bflow.toolbox.interchange.mif.aditus.AditusEdgeMetaInfo;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
}

// Language version 04.07.2013

aditus returns [AditusMetaInfo aditusMetaInfo = new AditusMetaInfo()]
	:	(imports { aditusMetaInfo.Imports = $imports.imports; })? modelDefinition { aditusMetaInfo.Model = $modelDefinition.modelInfo; } (shapeDefinition { aditusMetaInfo.Shapes.add($shapeDefinition.shapeMetaInfo); })+ (edgeDefinition { aditusMetaInfo.Edges.add($edgeDefinition.edgeMetaInfo); })+ EOF;

imports returns [List<String> imports = new LinkedList<String>()]
	:	(importStatement { imports.add($importStatement.decl); })+;

importStatement returns [String decl]
	:	IMPORT qualifiedName {$decl = $qualifiedName.name;};
	
modelDefinition returns [AditusModelMetaInfo modelInfo = new AditusModelMetaInfo()]
	:	MODEL EQ LBRACE baseDef = baseDefinitionBlock { $modelInfo.Id = $baseDef.id; $modelInfo.Type = $baseDef.type; $modelInfo.Name = $baseDef.name; $modelInfo.Attributes = $baseDef.attributes; } RBRACE;
	
shapeDefinition returns [AditusShapeMetaInfo shapeMetaInfo = new AditusShapeMetaInfo()]
	:	SHAPES EQ valueAssignmentExpr { $shapeMetaInfo.Node = $valueAssignmentExpr.text; } LBRACE baseDef = baseDefinitionBlock { $shapeMetaInfo.Id = $baseDef.id; $shapeMetaInfo.Type = $baseDef.type; $shapeMetaInfo.Name = $baseDef.name; $shapeMetaInfo.Attributes = $baseDef.attributes; } grafDef = graphicDefinitionBlock { $shapeMetaInfo.Width = $grafDef.width; $shapeMetaInfo.Height = $grafDef.height; $shapeMetaInfo.X = $grafDef.x; $shapeMetaInfo.Y = $grafDef.y; } RBRACE;
	
edgeDefinition returns [AditusEdgeMetaInfo edgeMetaInfo = new AditusEdgeMetaInfo()]
	:	EDGES EQ valueAssignmentExpr { $edgeMetaInfo.Node = $valueAssignmentExpr.text; } LBRACE baseDef = baseDefinitionBlock { $edgeMetaInfo.Id = $baseDef.id; $edgeMetaInfo.Type = $baseDef.type; $edgeMetaInfo.Name = $baseDef.name; $edgeMetaInfo.Attributes = $baseDef.attributes; } edgeDef = edgeDefinitionBlock { $edgeMetaInfo.Source = $edgeDef.source; $edgeMetaInfo.Target = $edgeDef.target; } RBRACE;
	
edgeDefinitionBlock returns [String source = null, String target = null]
	:	sourceAssignment { $source = $sourceAssignment.value; } targetAssignment { $target = $targetAssignment.value; };
	
sourceAssignment returns [String value = null]
	:	SOURCE EQ linkExpr { $value = $linkExpr.text; };
	
targetAssignment returns [String value = null]
	:	TARGET EQ linkExpr { $value = $linkExpr.text; };
	
graphicDefinitionBlock returns [String width = null, String height = null, String x = null, String y = null]
	:	(widthAssignment { $width = $widthAssignment.value; })? (heightAssignment { $height = $heightAssignment.value; })? (xAssignment { $x = $xAssignment.value; })? (yAssignment { $y = $yAssignment.value; })?;
	
widthAssignment returns [String value = null]
	:	WIDTH assignment { $value = $assignment.value; };
	
heightAssignment returns [String value = null]
	:	HEIGHT assignment { $value = $assignment.value; };

xAssignment returns [String value = null]
	:	X assignment { $value = $assignment.value; };
	
yAssignment returns [String value = null]
	:	Y assignment { $value = $assignment.value; };
	
baseDefinitionBlock returns [String id = null, String type = null, String name = null, String attributes = null]
	:	(idAssignment { $id = $idAssignment.value; })? (typeAssignment { $type = $typeAssignment.value; })? (nameAssignment { $name = $nameAssignment.value; })? (attributesAssignment { $attributes = $attributesAssignment.value; })?;
	
idAssignment returns [String value]
	:	ID assignment { $value = $assignment.value; };
	
typeAssignment returns [String value]
	:	TYPE assignment { $value = $assignment.value; };
	
nameAssignment returns [String value]
	:	NAME assignment { $value = $assignment.value; };
	
attributesAssignment returns [String value]
	:	ATTRIBUTES EQ mapExpr { $value = $mapExpr.text; };
	
mapExpr :	valueAssignmentExpr '=>' valueAssignmentExpr;
	
assignment returns [String value = null]
	:	EQ valueAssignmentExpr { $value = $valueAssignmentExpr.text; };

qualifiedName returns [String name]
	:	IDENTIFIER { $name = $IDENTIFIER.text; }
	| 	{ String namespace = ""; } (id1 = IDENTIFIER DOT { namespace += $id1.text + "."; })+ id2 = IDENTIFIER { $name = namespace + $id2.text; }
	;	

valueAssignmentExpr 
	:	XPATH 
	|	defaultValueAssignment
	| 	XPATH '|' defaultValueAssignment
	;
	
//xpath	:	('//'|'/') (.)+ ('()')?  ('|' defValueAssignment)?;
XPATH 	:	'$' ~('$')* '$' { setText(getText().substring(1, getText().length() - 1)); } ;

defaultValueAssignment 
	:	STRING | INT | NULL;
	
linkExpr 
	:	linkIdExpr IIF linkIdExpr DOT propertyExpr DEQ valueAssignmentExpr
	|	NULL
	;
	
// Tokens
IMPORT 	:	'import';
MODEL	: 	'model';
SHAPES	:	'shapes';
EDGES	:	'edges';
SHAPE	:	'shape';
EDGE	:	'edge';
ID	: 	'id';
TYPE	:	'type';
NAME	:	'name';
WIDTH	: 	'width';
HEIGHT	:	'height';
X	:	'x';
Y	:	'y';
SOURCE	:	'source';
TARGET	:	'target';
ATTRIBUTES
	:	'attributes';
EQ	:	'=';
DEQ	:	'==';
IIF	:	'<=>';
LBRACE	:	'{';
RBRACE	:	'}';
DOT	: 	'.';
NULL	:	'null';

linkIdExpr
	:	SHAPE | EDGE;
	
propertyExpr 
	: ID | TYPE | NAME | WIDTH | HEIGHT | X | Y
	;

IDENTIFIER  :	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;
    
INT :	'0'..'9'+
    ;
    
STRING
    :  '\'' ~('\'')* '\'' { setText(getText().substring(1, getText().length() - 1)); }
    ;
    
fragment
ESC_SEQ
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    |   UNICODE_ESC
    |   OCTAL_ESC
    ;
    
fragment
OCTAL_ESC
    :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7')
    ;

fragment
UNICODE_ESC
    :   '\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
    ;
    
fragment
HEX_DIGIT : ('0'..'9'|'a'..'f'|'A'..'F') ;

COMMENT
    :   '#' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    ;

WS  :   ( ' '
        | '\t'
        | '\r'
        | '\n'
        ) {$channel=HIDDEN;}
    ;

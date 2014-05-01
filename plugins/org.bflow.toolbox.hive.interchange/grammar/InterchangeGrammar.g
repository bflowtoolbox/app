grammar InterchangeGrammar;

@header {
package org.bflow.toolbox.interchange.mif.grammar;
import org.bflow.toolbox.interchange.mif.impl.InterchangeDescriptorFactory;
import org.bflow.toolbox.interchange.mif.core.IExportDescriptor;
import org.bflow.toolbox.interchange.mif.core.IScriptDescriptor;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
}

// Language version 03.10.2012

head returns [IExportDescriptor exp]	
	:	(p=PUBLIC {$p.setText("true");})? INTERCHANGE STRING LPAREN extensions RPAREN (diagram_definition)? body_block EOF
		{
		  String name = $STRING.text;
		  boolean isPublic = Boolean.parseBoolean($PUBLIC.text);
		  List<String> lstExtension = $extensions.extensions; 
		  List<String> editorTypes = $diagram_definition.types;
		  String description = $body_block.descrx;
		  List<IScriptDescriptor> scripts = $body_block.scriptsx;
		  
		  exp = InterchangeDescriptorFactory.
		  		createExportDescriptor(editorTypes,description,lstExtension,name,scripts,isPublic);
		}
	;

extensions returns [List<String> extensions = new ArrayList<String>();]
	:	(e=ID SEPARATOR {extensions.add($e.text);})* e=ID {extensions.add($e.text);}
	;
	
diagram_definition returns [List<String> types]
	:	LRBRACKET t=diagram_type {types=t;}
	;
	
diagram_type returns [List<String> types = new ArrayList<String>();]
	:	(e=ID COMMA {$types.add($e.text);})* e=ID {$types.add($e.text);}
	;

body_block returns [String descrx, List<IScriptDescriptor> scriptsx]
	:	LBRACE body RBRACE {$descrx = $body.descrx; $scriptsx = $body.scriptsx;}
	;

body returns [String descrx, List<IScriptDescriptor> scriptsx]	
	:	(descr_def { $descrx = $descr_def.desc; } )? scriptlist { $scriptsx = $scriptlist.scripts; };

descr_def returns [String desc]
	:	DESCRIPTION COLON STRING
		{$desc = $STRING.text;}
	;

scriptlist returns [List<IScriptDescriptor> scripts = new ArrayList<IScriptDescriptor>()]
	:	(scriptaddition {$scripts.add($scriptaddition.script);})+;

scriptaddition returns [IScriptDescriptor script]
	:	script_def { $script = InterchangeDescriptorFactory.createScriptDescriptor($script_def.path, null);}
	|	script_def COMMA var_assignment { $script = InterchangeDescriptorFactory.createScriptDescriptor($script_def.path, $var_assignment.params);}
	;

script_def returns [String path]
	:	AT SCRIPT COLON STRING {$path = $STRING.text;};

var_assignment returns [Map<String, Object> params = new HashMap<String, Object>()]
	:	(kv=simple_assignment COMMA { params.put($kv.idx, $kv.valx); })* simple_assignment { params.put($kv.idx, $kv.valx); }
	;

simple_assignment returns [String idx, Object valx]
	:	ID EQ value {$idx = $ID.text; $valx = $value.value;};
	
value returns [Object value]	
	: BOOLEAN { $value = Boolean.parseBoolean($BOOLEAN.text); } 
	| INT { $value = Integer.parseInt($INT.text); }
	| FLOAT { $value = Double.parseDouble($FLOAT.text); }
	| STRING { $value = $STRING.text; }
	; 

// Value types

BOOLEAN	:	'true' {setText("true");} | 'false'{setText("false");};

// Constants and reserved words

INTERCHANGE 
	:	'interchange';

SCRIPT	:	'script';

DESCRIPTION 
	:	'description';
	
PUBLIC 	:	'public';
	
LRBRACKET
	:	'<>';

LPAREN 	:	'(';
RPAREN 	:	')';
LBRACE	:	'{';
RBRACE	:	'}';
SEPARATOR
	:	'|';
COLON	:	':';
COMMA	:	',';
AT	: 	'@';
EQ	:	'=';

// Generated Code

ID  :	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;

COMMENT
    :   '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    ;

STRING
    :  '\'' ( ESC_SEQ | ~('\\'|'\'') )* '\'' 
    	{ 
    	  setText(getText().substring(1, getText().length() - 1));
	}
    ;

fragment
HEX_DIGIT : ('0'..'9'|'a'..'f'|'A'..'F') ;

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
    
INT :	'0'..'9'+
    ;

FLOAT
    :   ('0'..'9')+ '.' ('0'..'9')* EXPONENT?
    |   '.' ('0'..'9')+ EXPONENT?
    |   ('0'..'9')+ EXPONENT
    ;

fragment
EXPONENT : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;

WS  :   ( ' '
        | '\t'
        | '\r'
        | '\n'
        ) {$channel=HIDDEN;}
    ;

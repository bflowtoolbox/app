// $ANTLR 3.4 F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g 2013-07-11 16:28:04

package org.bflow.toolbox.hive.interchange.mif.grammar;

import org.bflow.toolbox.hive.interchange.mif.aditus.AditusEdgeMetaInfo;
import org.bflow.toolbox.hive.interchange.mif.aditus.AditusMetaInfo;
import org.bflow.toolbox.hive.interchange.mif.aditus.AditusModelMetaInfo;
import org.bflow.toolbox.hive.interchange.mif.aditus.AditusShapeMetaInfo;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;



import org.antlr.runtime.*;

import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class AditusGrammarParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ATTRIBUTES", "COMMENT", "DEQ", "DOT", "EDGE", "EDGES", "EQ", "ESC_SEQ", "HEIGHT", "HEX_DIGIT", "ID", "IDENTIFIER", "IIF", "IMPORT", "INT", "LBRACE", "MODEL", "NAME", "NULL", "OCTAL_ESC", "RBRACE", "SHAPE", "SHAPES", "SOURCE", "STRING", "TARGET", "TYPE", "UNICODE_ESC", "WIDTH", "WS", "X", "XPATH", "Y", "'=>'", "'|'"
    };

    public static final int EOF=-1;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int ATTRIBUTES=4;
    public static final int COMMENT=5;
    public static final int DEQ=6;
    public static final int DOT=7;
    public static final int EDGE=8;
    public static final int EDGES=9;
    public static final int EQ=10;
    public static final int ESC_SEQ=11;
    public static final int HEIGHT=12;
    public static final int HEX_DIGIT=13;
    public static final int ID=14;
    public static final int IDENTIFIER=15;
    public static final int IIF=16;
    public static final int IMPORT=17;
    public static final int INT=18;
    public static final int LBRACE=19;
    public static final int MODEL=20;
    public static final int NAME=21;
    public static final int NULL=22;
    public static final int OCTAL_ESC=23;
    public static final int RBRACE=24;
    public static final int SHAPE=25;
    public static final int SHAPES=26;
    public static final int SOURCE=27;
    public static final int STRING=28;
    public static final int TARGET=29;
    public static final int TYPE=30;
    public static final int UNICODE_ESC=31;
    public static final int WIDTH=32;
    public static final int WS=33;
    public static final int X=34;
    public static final int XPATH=35;
    public static final int Y=36;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public AditusGrammarParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public AditusGrammarParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return AditusGrammarParser.tokenNames; }
    public String getGrammarFileName() { return "F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g"; }



    // $ANTLR start "aditus"
    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:23:1: aditus returns [AditusMetaInfo aditusMetaInfo = new AditusMetaInfo()] : ( imports )? modelDefinition ( shapeDefinition )+ ( edgeDefinition )+ EOF ;
    public final AditusMetaInfo aditus() throws RecognitionException {
        AditusMetaInfo aditusMetaInfo =  new AditusMetaInfo();


        List<String> imports1 =null;

        AditusModelMetaInfo modelDefinition2 =null;

        AditusShapeMetaInfo shapeDefinition3 =null;

        AditusEdgeMetaInfo edgeDefinition4 =null;


        try {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:24:2: ( ( imports )? modelDefinition ( shapeDefinition )+ ( edgeDefinition )+ EOF )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:24:4: ( imports )? modelDefinition ( shapeDefinition )+ ( edgeDefinition )+ EOF
            {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:24:4: ( imports )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==IMPORT) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:24:5: imports
                    {
                    pushFollow(FOLLOW_imports_in_aditus36);
                    imports1=imports();

                    state._fsp--;


                     aditusMetaInfo.Imports = imports1; 

                    }
                    break;

            }


            pushFollow(FOLLOW_modelDefinition_in_aditus42);
            modelDefinition2=modelDefinition();

            state._fsp--;


             aditusMetaInfo.Model = modelDefinition2; 

            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:24:133: ( shapeDefinition )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==SHAPES) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:24:134: shapeDefinition
            	    {
            	    pushFollow(FOLLOW_shapeDefinition_in_aditus47);
            	    shapeDefinition3=shapeDefinition();

            	    state._fsp--;


            	     aditusMetaInfo.Shapes.add(shapeDefinition3); 

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:24:215: ( edgeDefinition )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==EDGES) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:24:216: edgeDefinition
            	    {
            	    pushFollow(FOLLOW_edgeDefinition_in_aditus54);
            	    edgeDefinition4=edgeDefinition();

            	    state._fsp--;


            	     aditusMetaInfo.Edges.add(edgeDefinition4); 

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            match(input,EOF,FOLLOW_EOF_in_aditus60); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return aditusMetaInfo;
    }
    // $ANTLR end "aditus"



    // $ANTLR start "imports"
    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:26:1: imports returns [List<String> imports = new LinkedList<String>()] : ( importStatement )+ ;
    public final List<String> imports() throws RecognitionException {
        List<String> imports =  new LinkedList<String>();


        String importStatement5 =null;


        try {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:27:2: ( ( importStatement )+ )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:27:4: ( importStatement )+
            {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:27:4: ( importStatement )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==IMPORT) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:27:5: importStatement
            	    {
            	    pushFollow(FOLLOW_importStatement_in_imports74);
            	    importStatement5=importStatement();

            	    state._fsp--;


            	     imports.add(importStatement5); 

            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return imports;
    }
    // $ANTLR end "imports"



    // $ANTLR start "importStatement"
    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:29:1: importStatement returns [String decl] : IMPORT qualifiedName ;
    public final String importStatement() throws RecognitionException {
        String decl = null;


        String qualifiedName6 =null;


        try {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:30:2: ( IMPORT qualifiedName )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:30:4: IMPORT qualifiedName
            {
            match(input,IMPORT,FOLLOW_IMPORT_in_importStatement91); 

            pushFollow(FOLLOW_qualifiedName_in_importStatement93);
            qualifiedName6=qualifiedName();

            state._fsp--;


            decl = qualifiedName6;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return decl;
    }
    // $ANTLR end "importStatement"



    // $ANTLR start "modelDefinition"
    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:32:1: modelDefinition returns [AditusModelMetaInfo modelInfo = new AditusModelMetaInfo()] : MODEL EQ LBRACE baseDef= baseDefinitionBlock RBRACE ;
    public final AditusModelMetaInfo modelDefinition() throws RecognitionException {
        AditusModelMetaInfo modelInfo =  new AditusModelMetaInfo();


        AditusGrammarParser.baseDefinitionBlock_return baseDef =null;


        try {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:33:2: ( MODEL EQ LBRACE baseDef= baseDefinitionBlock RBRACE )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:33:4: MODEL EQ LBRACE baseDef= baseDefinitionBlock RBRACE
            {
            match(input,MODEL,FOLLOW_MODEL_in_modelDefinition109); 

            match(input,EQ,FOLLOW_EQ_in_modelDefinition111); 

            match(input,LBRACE,FOLLOW_LBRACE_in_modelDefinition113); 

            pushFollow(FOLLOW_baseDefinitionBlock_in_modelDefinition119);
            baseDef=baseDefinitionBlock();

            state._fsp--;


             modelInfo.Id = (baseDef!=null?baseDef.id:null); modelInfo.Type = (baseDef!=null?baseDef.type:null); modelInfo.Name = (baseDef!=null?baseDef.name:null); modelInfo.Attributes = (baseDef!=null?baseDef.attributes:null); 

            match(input,RBRACE,FOLLOW_RBRACE_in_modelDefinition123); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return modelInfo;
    }
    // $ANTLR end "modelDefinition"



    // $ANTLR start "shapeDefinition"
    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:35:1: shapeDefinition returns [AditusShapeMetaInfo shapeMetaInfo = new AditusShapeMetaInfo()] : SHAPES EQ valueAssignmentExpr LBRACE baseDef= baseDefinitionBlock grafDef= graphicDefinitionBlock RBRACE ;
    public final AditusShapeMetaInfo shapeDefinition() throws RecognitionException {
        AditusShapeMetaInfo shapeMetaInfo =  new AditusShapeMetaInfo();


        AditusGrammarParser.baseDefinitionBlock_return baseDef =null;

        AditusGrammarParser.graphicDefinitionBlock_return grafDef =null;

        AditusGrammarParser.valueAssignmentExpr_return valueAssignmentExpr7 =null;


        try {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:36:2: ( SHAPES EQ valueAssignmentExpr LBRACE baseDef= baseDefinitionBlock grafDef= graphicDefinitionBlock RBRACE )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:36:4: SHAPES EQ valueAssignmentExpr LBRACE baseDef= baseDefinitionBlock grafDef= graphicDefinitionBlock RBRACE
            {
            match(input,SHAPES,FOLLOW_SHAPES_in_shapeDefinition137); 

            match(input,EQ,FOLLOW_EQ_in_shapeDefinition139); 

            pushFollow(FOLLOW_valueAssignmentExpr_in_shapeDefinition141);
            valueAssignmentExpr7=valueAssignmentExpr();

            state._fsp--;


             shapeMetaInfo.Node = (valueAssignmentExpr7!=null?input.toString(valueAssignmentExpr7.start,valueAssignmentExpr7.stop):null); 

            match(input,LBRACE,FOLLOW_LBRACE_in_shapeDefinition145); 

            pushFollow(FOLLOW_baseDefinitionBlock_in_shapeDefinition151);
            baseDef=baseDefinitionBlock();

            state._fsp--;


             shapeMetaInfo.Id = (baseDef!=null?baseDef.id:null); shapeMetaInfo.Type = (baseDef!=null?baseDef.type:null); shapeMetaInfo.Name = (baseDef!=null?baseDef.name:null); shapeMetaInfo.Attributes = (baseDef!=null?baseDef.attributes:null); 

            pushFollow(FOLLOW_graphicDefinitionBlock_in_shapeDefinition159);
            grafDef=graphicDefinitionBlock();

            state._fsp--;


             shapeMetaInfo.Width = (grafDef!=null?grafDef.width:null); shapeMetaInfo.Height = (grafDef!=null?grafDef.height:null); shapeMetaInfo.X = (grafDef!=null?grafDef.x:null); shapeMetaInfo.Y = (grafDef!=null?grafDef.y:null); 

            match(input,RBRACE,FOLLOW_RBRACE_in_shapeDefinition163); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return shapeMetaInfo;
    }
    // $ANTLR end "shapeDefinition"



    // $ANTLR start "edgeDefinition"
    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:38:1: edgeDefinition returns [AditusEdgeMetaInfo edgeMetaInfo = new AditusEdgeMetaInfo()] : EDGES EQ valueAssignmentExpr LBRACE baseDef= baseDefinitionBlock edgeDef= edgeDefinitionBlock RBRACE ;
    public final AditusEdgeMetaInfo edgeDefinition() throws RecognitionException {
        AditusEdgeMetaInfo edgeMetaInfo =  new AditusEdgeMetaInfo();


        AditusGrammarParser.baseDefinitionBlock_return baseDef =null;

        AditusGrammarParser.edgeDefinitionBlock_return edgeDef =null;

        AditusGrammarParser.valueAssignmentExpr_return valueAssignmentExpr8 =null;


        try {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:39:2: ( EDGES EQ valueAssignmentExpr LBRACE baseDef= baseDefinitionBlock edgeDef= edgeDefinitionBlock RBRACE )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:39:4: EDGES EQ valueAssignmentExpr LBRACE baseDef= baseDefinitionBlock edgeDef= edgeDefinitionBlock RBRACE
            {
            match(input,EDGES,FOLLOW_EDGES_in_edgeDefinition177); 

            match(input,EQ,FOLLOW_EQ_in_edgeDefinition179); 

            pushFollow(FOLLOW_valueAssignmentExpr_in_edgeDefinition181);
            valueAssignmentExpr8=valueAssignmentExpr();

            state._fsp--;


             edgeMetaInfo.Node = (valueAssignmentExpr8!=null?input.toString(valueAssignmentExpr8.start,valueAssignmentExpr8.stop):null); 

            match(input,LBRACE,FOLLOW_LBRACE_in_edgeDefinition185); 

            pushFollow(FOLLOW_baseDefinitionBlock_in_edgeDefinition191);
            baseDef=baseDefinitionBlock();

            state._fsp--;


             edgeMetaInfo.Id = (baseDef!=null?baseDef.id:null); edgeMetaInfo.Type = (baseDef!=null?baseDef.type:null); edgeMetaInfo.Name = (baseDef!=null?baseDef.name:null); edgeMetaInfo.Attributes = (baseDef!=null?baseDef.attributes:null); 

            pushFollow(FOLLOW_edgeDefinitionBlock_in_edgeDefinition199);
            edgeDef=edgeDefinitionBlock();

            state._fsp--;


             edgeMetaInfo.Source = (edgeDef!=null?edgeDef.source:null); edgeMetaInfo.Target = (edgeDef!=null?edgeDef.target:null); 

            match(input,RBRACE,FOLLOW_RBRACE_in_edgeDefinition203); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return edgeMetaInfo;
    }
    // $ANTLR end "edgeDefinition"


    public static class edgeDefinitionBlock_return extends ParserRuleReturnScope {
        public String source = null;
        public String target = null;
    };


    // $ANTLR start "edgeDefinitionBlock"
    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:41:1: edgeDefinitionBlock returns [String source = null, String target = null] : sourceAssignment targetAssignment ;
    public final AditusGrammarParser.edgeDefinitionBlock_return edgeDefinitionBlock() throws RecognitionException {
        AditusGrammarParser.edgeDefinitionBlock_return retval = new AditusGrammarParser.edgeDefinitionBlock_return();
        retval.start = input.LT(1);


        String sourceAssignment9 =null;

        String targetAssignment10 =null;


        try {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:42:2: ( sourceAssignment targetAssignment )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:42:4: sourceAssignment targetAssignment
            {
            pushFollow(FOLLOW_sourceAssignment_in_edgeDefinitionBlock217);
            sourceAssignment9=sourceAssignment();

            state._fsp--;


             retval.source = sourceAssignment9; 

            pushFollow(FOLLOW_targetAssignment_in_edgeDefinitionBlock221);
            targetAssignment10=targetAssignment();

            state._fsp--;


             retval.target = targetAssignment10; 

            }

            retval.stop = input.LT(-1);


        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "edgeDefinitionBlock"



    // $ANTLR start "sourceAssignment"
    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:44:1: sourceAssignment returns [String value = null] : SOURCE EQ linkExpr ;
    public final String sourceAssignment() throws RecognitionException {
        String value =  null;


        AditusGrammarParser.linkExpr_return linkExpr11 =null;


        try {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:45:2: ( SOURCE EQ linkExpr )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:45:4: SOURCE EQ linkExpr
            {
            match(input,SOURCE,FOLLOW_SOURCE_in_sourceAssignment237); 

            match(input,EQ,FOLLOW_EQ_in_sourceAssignment239); 

            pushFollow(FOLLOW_linkExpr_in_sourceAssignment241);
            linkExpr11=linkExpr();

            state._fsp--;


             value = (linkExpr11!=null?input.toString(linkExpr11.start,linkExpr11.stop):null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return value;
    }
    // $ANTLR end "sourceAssignment"



    // $ANTLR start "targetAssignment"
    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:47:1: targetAssignment returns [String value = null] : TARGET EQ linkExpr ;
    public final String targetAssignment() throws RecognitionException {
        String value =  null;


        AditusGrammarParser.linkExpr_return linkExpr12 =null;


        try {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:48:2: ( TARGET EQ linkExpr )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:48:4: TARGET EQ linkExpr
            {
            match(input,TARGET,FOLLOW_TARGET_in_targetAssignment257); 

            match(input,EQ,FOLLOW_EQ_in_targetAssignment259); 

            pushFollow(FOLLOW_linkExpr_in_targetAssignment261);
            linkExpr12=linkExpr();

            state._fsp--;


             value = (linkExpr12!=null?input.toString(linkExpr12.start,linkExpr12.stop):null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return value;
    }
    // $ANTLR end "targetAssignment"


    public static class graphicDefinitionBlock_return extends ParserRuleReturnScope {
        public String width = null;
        public String height = null;
        public String x = null;
        public String y = null;
    };


    // $ANTLR start "graphicDefinitionBlock"
    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:50:1: graphicDefinitionBlock returns [String width = null, String height = null, String x = null, String y = null] : ( widthAssignment )? ( heightAssignment )? ( xAssignment )? ( yAssignment )? ;
    public final AditusGrammarParser.graphicDefinitionBlock_return graphicDefinitionBlock() throws RecognitionException {
        AditusGrammarParser.graphicDefinitionBlock_return retval = new AditusGrammarParser.graphicDefinitionBlock_return();
        retval.start = input.LT(1);


        String widthAssignment13 =null;

        String heightAssignment14 =null;

        String xAssignment15 =null;

        String yAssignment16 =null;


        try {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:51:2: ( ( widthAssignment )? ( heightAssignment )? ( xAssignment )? ( yAssignment )? )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:51:4: ( widthAssignment )? ( heightAssignment )? ( xAssignment )? ( yAssignment )?
            {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:51:4: ( widthAssignment )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==WIDTH) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:51:5: widthAssignment
                    {
                    pushFollow(FOLLOW_widthAssignment_in_graphicDefinitionBlock278);
                    widthAssignment13=widthAssignment();

                    state._fsp--;


                     retval.width = widthAssignment13; 

                    }
                    break;

            }


            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:51:60: ( heightAssignment )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==HEIGHT) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:51:61: heightAssignment
                    {
                    pushFollow(FOLLOW_heightAssignment_in_graphicDefinitionBlock285);
                    heightAssignment14=heightAssignment();

                    state._fsp--;


                     retval.height = heightAssignment14; 

                    }
                    break;

            }


            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:51:119: ( xAssignment )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==X) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:51:120: xAssignment
                    {
                    pushFollow(FOLLOW_xAssignment_in_graphicDefinitionBlock292);
                    xAssignment15=xAssignment();

                    state._fsp--;


                     retval.x = xAssignment15; 

                    }
                    break;

            }


            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:51:163: ( yAssignment )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==Y) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:51:164: yAssignment
                    {
                    pushFollow(FOLLOW_yAssignment_in_graphicDefinitionBlock299);
                    yAssignment16=yAssignment();

                    state._fsp--;


                     retval.y = yAssignment16; 

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "graphicDefinitionBlock"



    // $ANTLR start "widthAssignment"
    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:53:1: widthAssignment returns [String value = null] : WIDTH assignment ;
    public final String widthAssignment() throws RecognitionException {
        String value =  null;


        String assignment17 =null;


        try {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:54:2: ( WIDTH assignment )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:54:4: WIDTH assignment
            {
            match(input,WIDTH,FOLLOW_WIDTH_in_widthAssignment317); 

            pushFollow(FOLLOW_assignment_in_widthAssignment319);
            assignment17=assignment();

            state._fsp--;


             value = assignment17; 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return value;
    }
    // $ANTLR end "widthAssignment"



    // $ANTLR start "heightAssignment"
    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:56:1: heightAssignment returns [String value = null] : HEIGHT assignment ;
    public final String heightAssignment() throws RecognitionException {
        String value =  null;


        String assignment18 =null;


        try {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:57:2: ( HEIGHT assignment )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:57:4: HEIGHT assignment
            {
            match(input,HEIGHT,FOLLOW_HEIGHT_in_heightAssignment335); 

            pushFollow(FOLLOW_assignment_in_heightAssignment337);
            assignment18=assignment();

            state._fsp--;


             value = assignment18; 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return value;
    }
    // $ANTLR end "heightAssignment"



    // $ANTLR start "xAssignment"
    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:59:1: xAssignment returns [String value = null] : X assignment ;
    public final String xAssignment() throws RecognitionException {
        String value =  null;


        String assignment19 =null;


        try {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:60:2: ( X assignment )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:60:4: X assignment
            {
            match(input,X,FOLLOW_X_in_xAssignment352); 

            pushFollow(FOLLOW_assignment_in_xAssignment354);
            assignment19=assignment();

            state._fsp--;


             value = assignment19; 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return value;
    }
    // $ANTLR end "xAssignment"



    // $ANTLR start "yAssignment"
    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:62:1: yAssignment returns [String value = null] : Y assignment ;
    public final String yAssignment() throws RecognitionException {
        String value =  null;


        String assignment20 =null;


        try {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:63:2: ( Y assignment )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:63:4: Y assignment
            {
            match(input,Y,FOLLOW_Y_in_yAssignment370); 

            pushFollow(FOLLOW_assignment_in_yAssignment372);
            assignment20=assignment();

            state._fsp--;


             value = assignment20; 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return value;
    }
    // $ANTLR end "yAssignment"


    public static class baseDefinitionBlock_return extends ParserRuleReturnScope {
        public String id = null;
        public String type = null;
        public String name = null;
        public String attributes = null;
    };


    // $ANTLR start "baseDefinitionBlock"
    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:65:1: baseDefinitionBlock returns [String id = null, String type = null, String name = null, String attributes = null] : ( idAssignment )? ( typeAssignment )? ( nameAssignment )? ( attributesAssignment )? ;
    public final AditusGrammarParser.baseDefinitionBlock_return baseDefinitionBlock() throws RecognitionException {
        AditusGrammarParser.baseDefinitionBlock_return retval = new AditusGrammarParser.baseDefinitionBlock_return();
        retval.start = input.LT(1);


        String idAssignment21 =null;

        String typeAssignment22 =null;

        String nameAssignment23 =null;

        String attributesAssignment24 =null;


        try {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:66:2: ( ( idAssignment )? ( typeAssignment )? ( nameAssignment )? ( attributesAssignment )? )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:66:4: ( idAssignment )? ( typeAssignment )? ( nameAssignment )? ( attributesAssignment )?
            {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:66:4: ( idAssignment )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==ID) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:66:5: idAssignment
                    {
                    pushFollow(FOLLOW_idAssignment_in_baseDefinitionBlock389);
                    idAssignment21=idAssignment();

                    state._fsp--;


                     retval.id = idAssignment21; 

                    }
                    break;

            }


            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:66:51: ( typeAssignment )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==TYPE) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:66:52: typeAssignment
                    {
                    pushFollow(FOLLOW_typeAssignment_in_baseDefinitionBlock396);
                    typeAssignment22=typeAssignment();

                    state._fsp--;


                     retval.type = typeAssignment22; 

                    }
                    break;

            }


            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:66:104: ( nameAssignment )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==NAME) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:66:105: nameAssignment
                    {
                    pushFollow(FOLLOW_nameAssignment_in_baseDefinitionBlock403);
                    nameAssignment23=nameAssignment();

                    state._fsp--;


                     retval.name = nameAssignment23; 

                    }
                    break;

            }


            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:66:157: ( attributesAssignment )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==ATTRIBUTES) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:66:158: attributesAssignment
                    {
                    pushFollow(FOLLOW_attributesAssignment_in_baseDefinitionBlock410);
                    attributesAssignment24=attributesAssignment();

                    state._fsp--;


                     retval.attributes = attributesAssignment24; 

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "baseDefinitionBlock"



    // $ANTLR start "idAssignment"
    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:68:1: idAssignment returns [String value] : ID assignment ;
    public final String idAssignment() throws RecognitionException {
        String value = null;


        String assignment25 =null;


        try {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:69:2: ( ID assignment )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:69:4: ID assignment
            {
            match(input,ID,FOLLOW_ID_in_idAssignment428); 

            pushFollow(FOLLOW_assignment_in_idAssignment430);
            assignment25=assignment();

            state._fsp--;


             value = assignment25; 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return value;
    }
    // $ANTLR end "idAssignment"



    // $ANTLR start "typeAssignment"
    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:71:1: typeAssignment returns [String value] : TYPE assignment ;
    public final String typeAssignment() throws RecognitionException {
        String value = null;


        String assignment26 =null;


        try {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:72:2: ( TYPE assignment )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:72:4: TYPE assignment
            {
            match(input,TYPE,FOLLOW_TYPE_in_typeAssignment446); 

            pushFollow(FOLLOW_assignment_in_typeAssignment448);
            assignment26=assignment();

            state._fsp--;


             value = assignment26; 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return value;
    }
    // $ANTLR end "typeAssignment"



    // $ANTLR start "nameAssignment"
    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:74:1: nameAssignment returns [String value] : NAME assignment ;
    public final String nameAssignment() throws RecognitionException {
        String value = null;


        String assignment27 =null;


        try {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:75:2: ( NAME assignment )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:75:4: NAME assignment
            {
            match(input,NAME,FOLLOW_NAME_in_nameAssignment464); 

            pushFollow(FOLLOW_assignment_in_nameAssignment466);
            assignment27=assignment();

            state._fsp--;


             value = assignment27; 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return value;
    }
    // $ANTLR end "nameAssignment"



    // $ANTLR start "attributesAssignment"
    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:77:1: attributesAssignment returns [String value] : ATTRIBUTES EQ mapExpr ;
    public final String attributesAssignment() throws RecognitionException {
        String value = null;


        AditusGrammarParser.mapExpr_return mapExpr28 =null;


        try {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:78:2: ( ATTRIBUTES EQ mapExpr )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:78:4: ATTRIBUTES EQ mapExpr
            {
            match(input,ATTRIBUTES,FOLLOW_ATTRIBUTES_in_attributesAssignment482); 

            match(input,EQ,FOLLOW_EQ_in_attributesAssignment484); 

            pushFollow(FOLLOW_mapExpr_in_attributesAssignment486);
            mapExpr28=mapExpr();

            state._fsp--;


             value = (mapExpr28!=null?input.toString(mapExpr28.start,mapExpr28.stop):null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return value;
    }
    // $ANTLR end "attributesAssignment"


    public static class mapExpr_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "mapExpr"
    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:80:1: mapExpr : valueAssignmentExpr '=>' valueAssignmentExpr ;
    public final AditusGrammarParser.mapExpr_return mapExpr() throws RecognitionException {
        AditusGrammarParser.mapExpr_return retval = new AditusGrammarParser.mapExpr_return();
        retval.start = input.LT(1);


        try {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:80:9: ( valueAssignmentExpr '=>' valueAssignmentExpr )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:80:11: valueAssignmentExpr '=>' valueAssignmentExpr
            {
            pushFollow(FOLLOW_valueAssignmentExpr_in_mapExpr497);
            valueAssignmentExpr();

            state._fsp--;


            match(input,37,FOLLOW_37_in_mapExpr499); 

            pushFollow(FOLLOW_valueAssignmentExpr_in_mapExpr501);
            valueAssignmentExpr();

            state._fsp--;


            }

            retval.stop = input.LT(-1);


        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "mapExpr"



    // $ANTLR start "assignment"
    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:82:1: assignment returns [String value = null] : EQ valueAssignmentExpr ;
    public final String assignment() throws RecognitionException {
        String value =  null;


        AditusGrammarParser.valueAssignmentExpr_return valueAssignmentExpr29 =null;


        try {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:83:2: ( EQ valueAssignmentExpr )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:83:4: EQ valueAssignmentExpr
            {
            match(input,EQ,FOLLOW_EQ_in_assignment515); 

            pushFollow(FOLLOW_valueAssignmentExpr_in_assignment517);
            valueAssignmentExpr29=valueAssignmentExpr();

            state._fsp--;


             value = (valueAssignmentExpr29!=null?input.toString(valueAssignmentExpr29.start,valueAssignmentExpr29.stop):null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return value;
    }
    // $ANTLR end "assignment"



    // $ANTLR start "qualifiedName"
    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:85:1: qualifiedName returns [String name] : ( IDENTIFIER | (id1= IDENTIFIER DOT )+ id2= IDENTIFIER );
    public final String qualifiedName() throws RecognitionException {
        String name = null;


        Token id1=null;
        Token id2=null;
        Token IDENTIFIER30=null;

        try {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:86:2: ( IDENTIFIER | (id1= IDENTIFIER DOT )+ id2= IDENTIFIER )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==IDENTIFIER) ) {
                int LA14_1 = input.LA(2);

                if ( (LA14_1==DOT) ) {
                    alt14=2;
                }
                else if ( (LA14_1==IMPORT||LA14_1==MODEL) ) {
                    alt14=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;

            }
            switch (alt14) {
                case 1 :
                    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:86:4: IDENTIFIER
                    {
                    IDENTIFIER30=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_qualifiedName532); 

                     name = (IDENTIFIER30!=null?IDENTIFIER30.getText():null); 

                    }
                    break;
                case 2 :
                    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:87:5: (id1= IDENTIFIER DOT )+ id2= IDENTIFIER
                    {
                     String namespace = ""; 

                    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:87:32: (id1= IDENTIFIER DOT )+
                    int cnt13=0;
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==IDENTIFIER) ) {
                            int LA13_1 = input.LA(2);

                            if ( (LA13_1==DOT) ) {
                                alt13=1;
                            }


                        }


                        switch (alt13) {
                    	case 1 :
                    	    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:87:33: id1= IDENTIFIER DOT
                    	    {
                    	    id1=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_qualifiedName547); 

                    	    match(input,DOT,FOLLOW_DOT_in_qualifiedName549); 

                    	     namespace += (id1!=null?id1.getText():null) + "."; 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt13 >= 1 ) break loop13;
                                EarlyExitException eee =
                                    new EarlyExitException(13, input);
                                throw eee;
                        }
                        cnt13++;
                    } while (true);


                    id2=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_qualifiedName559); 

                     name = namespace + (id2!=null?id2.getText():null); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return name;
    }
    // $ANTLR end "qualifiedName"


    public static class valueAssignmentExpr_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "valueAssignmentExpr"
    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:90:1: valueAssignmentExpr : ( XPATH | defaultValueAssignment | XPATH '|' defaultValueAssignment );
    public final AditusGrammarParser.valueAssignmentExpr_return valueAssignmentExpr() throws RecognitionException {
        AditusGrammarParser.valueAssignmentExpr_return retval = new AditusGrammarParser.valueAssignmentExpr_return();
        retval.start = input.LT(1);


        try {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:91:2: ( XPATH | defaultValueAssignment | XPATH '|' defaultValueAssignment )
            int alt15=3;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==XPATH) ) {
                int LA15_1 = input.LA(2);

                if ( (LA15_1==38) ) {
                    alt15=3;
                }
                else if ( (LA15_1==ATTRIBUTES||LA15_1==HEIGHT||LA15_1==LBRACE||LA15_1==NAME||LA15_1==RBRACE||LA15_1==SOURCE||(LA15_1 >= TARGET && LA15_1 <= TYPE)||LA15_1==WIDTH||LA15_1==X||(LA15_1 >= Y && LA15_1 <= 37)) ) {
                    alt15=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 15, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA15_0==INT||LA15_0==NULL||LA15_0==STRING) ) {
                alt15=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;

            }
            switch (alt15) {
                case 1 :
                    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:91:4: XPATH
                    {
                    match(input,XPATH,FOLLOW_XPATH_in_valueAssignmentExpr574); 

                    }
                    break;
                case 2 :
                    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:92:4: defaultValueAssignment
                    {
                    pushFollow(FOLLOW_defaultValueAssignment_in_valueAssignmentExpr580);
                    defaultValueAssignment();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:93:5: XPATH '|' defaultValueAssignment
                    {
                    match(input,XPATH,FOLLOW_XPATH_in_valueAssignmentExpr586); 

                    match(input,38,FOLLOW_38_in_valueAssignmentExpr588); 

                    pushFollow(FOLLOW_defaultValueAssignment_in_valueAssignmentExpr590);
                    defaultValueAssignment();

                    state._fsp--;


                    }
                    break;

            }
            retval.stop = input.LT(-1);


        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "valueAssignmentExpr"



    // $ANTLR start "defaultValueAssignment"
    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:99:1: defaultValueAssignment : ( STRING | INT | NULL );
    public final void defaultValueAssignment() throws RecognitionException {
        try {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:100:2: ( STRING | INT | NULL )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:
            {
            if ( input.LA(1)==INT||input.LA(1)==NULL||input.LA(1)==STRING ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "defaultValueAssignment"


    public static class linkExpr_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "linkExpr"
    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:102:1: linkExpr : ( linkIdExpr IIF linkIdExpr DOT propertyExpr DEQ valueAssignmentExpr | NULL );
    public final AditusGrammarParser.linkExpr_return linkExpr() throws RecognitionException {
        AditusGrammarParser.linkExpr_return retval = new AditusGrammarParser.linkExpr_return();
        retval.start = input.LT(1);


        try {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:103:2: ( linkIdExpr IIF linkIdExpr DOT propertyExpr DEQ valueAssignmentExpr | NULL )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==EDGE||LA16_0==SHAPE) ) {
                alt16=1;
            }
            else if ( (LA16_0==NULL) ) {
                alt16=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;

            }
            switch (alt16) {
                case 1 :
                    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:103:4: linkIdExpr IIF linkIdExpr DOT propertyExpr DEQ valueAssignmentExpr
                    {
                    pushFollow(FOLLOW_linkIdExpr_in_linkExpr643);
                    linkIdExpr();

                    state._fsp--;


                    match(input,IIF,FOLLOW_IIF_in_linkExpr645); 

                    pushFollow(FOLLOW_linkIdExpr_in_linkExpr647);
                    linkIdExpr();

                    state._fsp--;


                    match(input,DOT,FOLLOW_DOT_in_linkExpr649); 

                    pushFollow(FOLLOW_propertyExpr_in_linkExpr651);
                    propertyExpr();

                    state._fsp--;


                    match(input,DEQ,FOLLOW_DEQ_in_linkExpr653); 

                    pushFollow(FOLLOW_valueAssignmentExpr_in_linkExpr655);
                    valueAssignmentExpr();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:104:4: NULL
                    {
                    match(input,NULL,FOLLOW_NULL_in_linkExpr660); 

                    }
                    break;

            }
            retval.stop = input.LT(-1);


        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "linkExpr"



    // $ANTLR start "linkIdExpr"
    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:133:1: linkIdExpr : ( SHAPE | EDGE );
    public final void linkIdExpr() throws RecognitionException {
        try {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:134:2: ( SHAPE | EDGE )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:
            {
            if ( input.LA(1)==EDGE||input.LA(1)==SHAPE ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "linkIdExpr"



    // $ANTLR start "propertyExpr"
    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:136:1: propertyExpr : ( ID | TYPE | NAME | WIDTH | HEIGHT | X | Y );
    public final void propertyExpr() throws RecognitionException {
        try {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:137:2: ( ID | TYPE | NAME | WIDTH | HEIGHT | X | Y )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:
            {
            if ( input.LA(1)==HEIGHT||input.LA(1)==ID||input.LA(1)==NAME||input.LA(1)==TYPE||input.LA(1)==WIDTH||input.LA(1)==X||input.LA(1)==Y ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "propertyExpr"

    // Delegated rules


 

    public static final BitSet FOLLOW_imports_in_aditus36 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_modelDefinition_in_aditus42 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_shapeDefinition_in_aditus47 = new BitSet(new long[]{0x0000000004000200L});
    public static final BitSet FOLLOW_edgeDefinition_in_aditus54 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_EOF_in_aditus60 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_importStatement_in_imports74 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_IMPORT_in_importStatement91 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_qualifiedName_in_importStatement93 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MODEL_in_modelDefinition109 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_EQ_in_modelDefinition111 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_LBRACE_in_modelDefinition113 = new BitSet(new long[]{0x0000000041204010L});
    public static final BitSet FOLLOW_baseDefinitionBlock_in_modelDefinition119 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RBRACE_in_modelDefinition123 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SHAPES_in_shapeDefinition137 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_EQ_in_shapeDefinition139 = new BitSet(new long[]{0x0000000810440000L});
    public static final BitSet FOLLOW_valueAssignmentExpr_in_shapeDefinition141 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_LBRACE_in_shapeDefinition145 = new BitSet(new long[]{0x0000001541205010L});
    public static final BitSet FOLLOW_baseDefinitionBlock_in_shapeDefinition151 = new BitSet(new long[]{0x0000001501001000L});
    public static final BitSet FOLLOW_graphicDefinitionBlock_in_shapeDefinition159 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RBRACE_in_shapeDefinition163 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EDGES_in_edgeDefinition177 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_EQ_in_edgeDefinition179 = new BitSet(new long[]{0x0000000810440000L});
    public static final BitSet FOLLOW_valueAssignmentExpr_in_edgeDefinition181 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_LBRACE_in_edgeDefinition185 = new BitSet(new long[]{0x0000000048204010L});
    public static final BitSet FOLLOW_baseDefinitionBlock_in_edgeDefinition191 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_edgeDefinitionBlock_in_edgeDefinition199 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RBRACE_in_edgeDefinition203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sourceAssignment_in_edgeDefinitionBlock217 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_targetAssignment_in_edgeDefinitionBlock221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SOURCE_in_sourceAssignment237 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_EQ_in_sourceAssignment239 = new BitSet(new long[]{0x0000000002400100L});
    public static final BitSet FOLLOW_linkExpr_in_sourceAssignment241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TARGET_in_targetAssignment257 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_EQ_in_targetAssignment259 = new BitSet(new long[]{0x0000000002400100L});
    public static final BitSet FOLLOW_linkExpr_in_targetAssignment261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_widthAssignment_in_graphicDefinitionBlock278 = new BitSet(new long[]{0x0000001400001002L});
    public static final BitSet FOLLOW_heightAssignment_in_graphicDefinitionBlock285 = new BitSet(new long[]{0x0000001400000002L});
    public static final BitSet FOLLOW_xAssignment_in_graphicDefinitionBlock292 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_yAssignment_in_graphicDefinitionBlock299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WIDTH_in_widthAssignment317 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_assignment_in_widthAssignment319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HEIGHT_in_heightAssignment335 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_assignment_in_heightAssignment337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_X_in_xAssignment352 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_assignment_in_xAssignment354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Y_in_yAssignment370 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_assignment_in_yAssignment372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_idAssignment_in_baseDefinitionBlock389 = new BitSet(new long[]{0x0000000040200012L});
    public static final BitSet FOLLOW_typeAssignment_in_baseDefinitionBlock396 = new BitSet(new long[]{0x0000000000200012L});
    public static final BitSet FOLLOW_nameAssignment_in_baseDefinitionBlock403 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_attributesAssignment_in_baseDefinitionBlock410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_idAssignment428 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_assignment_in_idAssignment430 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TYPE_in_typeAssignment446 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_assignment_in_typeAssignment448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_nameAssignment464 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_assignment_in_nameAssignment466 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ATTRIBUTES_in_attributesAssignment482 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_EQ_in_attributesAssignment484 = new BitSet(new long[]{0x0000000810440000L});
    public static final BitSet FOLLOW_mapExpr_in_attributesAssignment486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_valueAssignmentExpr_in_mapExpr497 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_37_in_mapExpr499 = new BitSet(new long[]{0x0000000810440000L});
    public static final BitSet FOLLOW_valueAssignmentExpr_in_mapExpr501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_assignment515 = new BitSet(new long[]{0x0000000810440000L});
    public static final BitSet FOLLOW_valueAssignmentExpr_in_assignment517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_qualifiedName532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_qualifiedName547 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_DOT_in_qualifiedName549 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_qualifiedName559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_XPATH_in_valueAssignmentExpr574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_defaultValueAssignment_in_valueAssignmentExpr580 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_XPATH_in_valueAssignmentExpr586 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_valueAssignmentExpr588 = new BitSet(new long[]{0x0000000010440000L});
    public static final BitSet FOLLOW_defaultValueAssignment_in_valueAssignmentExpr590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_linkIdExpr_in_linkExpr643 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_IIF_in_linkExpr645 = new BitSet(new long[]{0x0000000002000100L});
    public static final BitSet FOLLOW_linkIdExpr_in_linkExpr647 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_DOT_in_linkExpr649 = new BitSet(new long[]{0x0000001540205000L});
    public static final BitSet FOLLOW_propertyExpr_in_linkExpr651 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DEQ_in_linkExpr653 = new BitSet(new long[]{0x0000000810440000L});
    public static final BitSet FOLLOW_valueAssignmentExpr_in_linkExpr655 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_in_linkExpr660 = new BitSet(new long[]{0x0000000000000002L});

}
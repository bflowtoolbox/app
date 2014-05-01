// $ANTLR 3.4 F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g 2012-10-03 12:10:14

package org.bflow.toolbox.hive.interchange.mif.grammar;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeDescriptor;
import org.bflow.toolbox.hive.interchange.mif.core.IScriptDescriptor;
import org.bflow.toolbox.hive.interchange.mif.impl.InterchangeDescriptorFactory;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.*;

import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class InterchangeGrammarParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "AT", "BOOLEAN", "COLON", "COMMA", "COMMENT", "DESCRIPTION", "EQ", "ESC_SEQ", "EXPONENT", "FLOAT", "HEX_DIGIT", "ID", "INT", "INTERCHANGE", "LBRACE", "LPAREN", "LRBRACKET", "OCTAL_ESC", "PUBLIC", "RBRACE", "RPAREN", "SCRIPT", "SEPARATOR", "STRING", "UNICODE_ESC", "WS"
    };

    public static final int EOF=-1;
    public static final int AT=4;
    public static final int BOOLEAN=5;
    public static final int COLON=6;
    public static final int COMMA=7;
    public static final int COMMENT=8;
    public static final int DESCRIPTION=9;
    public static final int EQ=10;
    public static final int ESC_SEQ=11;
    public static final int EXPONENT=12;
    public static final int FLOAT=13;
    public static final int HEX_DIGIT=14;
    public static final int ID=15;
    public static final int INT=16;
    public static final int INTERCHANGE=17;
    public static final int LBRACE=18;
    public static final int LPAREN=19;
    public static final int LRBRACKET=20;
    public static final int OCTAL_ESC=21;
    public static final int PUBLIC=22;
    public static final int RBRACE=23;
    public static final int RPAREN=24;
    public static final int SCRIPT=25;
    public static final int SEPARATOR=26;
    public static final int STRING=27;
    public static final int UNICODE_ESC=28;
    public static final int WS=29;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public InterchangeGrammarParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public InterchangeGrammarParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return InterchangeGrammarParser.tokenNames; }
    public String getGrammarFileName() { return "F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g"; }



    // $ANTLR start "head"
    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:16:1: head returns [IExportDescriptor exp] : (p= PUBLIC )? INTERCHANGE STRING LPAREN extensions RPAREN ( diagram_definition )? body_block EOF ;
    public final IInterchangeDescriptor head() throws RecognitionException {
        IInterchangeDescriptor exp = null;


        Token p=null;
        Token STRING1=null;
        List<String> extensions2 =null;

        List<String> diagram_definition3 =null;

        InterchangeGrammarParser.body_block_return body_block4 =null;


        try {
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:17:2: ( (p= PUBLIC )? INTERCHANGE STRING LPAREN extensions RPAREN ( diagram_definition )? body_block EOF )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:17:4: (p= PUBLIC )? INTERCHANGE STRING LPAREN extensions RPAREN ( diagram_definition )? body_block EOF
            {
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:17:4: (p= PUBLIC )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==PUBLIC) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:17:5: p= PUBLIC
                    {
                    p=(Token)match(input,PUBLIC,FOLLOW_PUBLIC_in_head27); 

                    p.setText("true");

                    }
                    break;

            }


            match(input,INTERCHANGE,FOLLOW_INTERCHANGE_in_head33); 

            STRING1=(Token)match(input,STRING,FOLLOW_STRING_in_head35); 

            match(input,LPAREN,FOLLOW_LPAREN_in_head37); 

            pushFollow(FOLLOW_extensions_in_head39);
            extensions2=extensions();

            state._fsp--;


            match(input,RPAREN,FOLLOW_RPAREN_in_head41); 

            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:17:82: ( diagram_definition )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==LRBRACKET) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:17:83: diagram_definition
                    {
                    pushFollow(FOLLOW_diagram_definition_in_head44);
                    diagram_definition3=diagram_definition();

                    state._fsp--;


                    }
                    break;

            }


            pushFollow(FOLLOW_body_block_in_head48);
            body_block4=body_block();

            state._fsp--;


            match(input,EOF,FOLLOW_EOF_in_head50); 


            		  String name = (STRING1!=null?STRING1.getText():null);
            		  boolean isPublic = Boolean.parseBoolean((p!=null?p.getText():null));
            		  List<String> lstExtension = extensions2; 
            		  List<String> editorTypes = diagram_definition3;
            		  String description = (body_block4!=null?body_block4.descrx:null);
            		  List<IScriptDescriptor> scripts = (body_block4!=null?body_block4.scriptsx:null);
            		  
            		  exp = InterchangeDescriptorFactory.
            		  		createExportDescriptor(editorTypes,description,lstExtension,name,scripts,isPublic);
            		

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return exp;
    }
    // $ANTLR end "head"



    // $ANTLR start "extensions"
    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:31:1: extensions returns [List<String> extensions = new ArrayList<String>();] : (e= ID SEPARATOR )* e= ID ;
    public final List<String> extensions() throws RecognitionException {
        List<String> extensions =  new ArrayList<String>();;


        Token e=null;

        try {
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:32:2: ( (e= ID SEPARATOR )* e= ID )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:32:4: (e= ID SEPARATOR )* e= ID
            {
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:32:4: (e= ID SEPARATOR )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==ID) ) {
                    int LA3_1 = input.LA(2);

                    if ( (LA3_1==SEPARATOR) ) {
                        alt3=1;
                    }


                }


                switch (alt3) {
            	case 1 :
            	    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:32:5: e= ID SEPARATOR
            	    {
            	    e=(Token)match(input,ID,FOLLOW_ID_in_extensions72); 

            	    match(input,SEPARATOR,FOLLOW_SEPARATOR_in_extensions74); 

            	    extensions.add((e!=null?e.getText():null));

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            e=(Token)match(input,ID,FOLLOW_ID_in_extensions82); 

            extensions.add((e!=null?e.getText():null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return extensions;
    }
    // $ANTLR end "extensions"



    // $ANTLR start "diagram_definition"
    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:35:1: diagram_definition returns [List<String> types] : LRBRACKET t= diagram_type ;
    public final List<String> diagram_definition() throws RecognitionException {
        List<String> types = null;


        List<String> t =null;


        try {
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:36:2: ( LRBRACKET t= diagram_type )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:36:4: LRBRACKET t= diagram_type
            {
            match(input,LRBRACKET,FOLLOW_LRBRACKET_in_diagram_definition100); 

            pushFollow(FOLLOW_diagram_type_in_diagram_definition104);
            t=diagram_type();

            state._fsp--;


            types=t;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return types;
    }
    // $ANTLR end "diagram_definition"



    // $ANTLR start "diagram_type"
    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:39:1: diagram_type returns [List<String> types = new ArrayList<String>();] : (e= ID COMMA )* e= ID ;
    public final List<String> diagram_type() throws RecognitionException {
        List<String> types =  new ArrayList<String>();;


        Token e=null;

        try {
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:40:2: ( (e= ID COMMA )* e= ID )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:40:4: (e= ID COMMA )* e= ID
            {
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:40:4: (e= ID COMMA )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==ID) ) {
                    int LA4_1 = input.LA(2);

                    if ( (LA4_1==COMMA) ) {
                        alt4=1;
                    }


                }


                switch (alt4) {
            	case 1 :
            	    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:40:5: e= ID COMMA
            	    {
            	    e=(Token)match(input,ID,FOLLOW_ID_in_diagram_type125); 

            	    match(input,COMMA,FOLLOW_COMMA_in_diagram_type127); 

            	    types.add((e!=null?e.getText():null));

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            e=(Token)match(input,ID,FOLLOW_ID_in_diagram_type135); 

            types.add((e!=null?e.getText():null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return types;
    }
    // $ANTLR end "diagram_type"


    public static class body_block_return extends ParserRuleReturnScope {
        public String descrx;
        public List<IScriptDescriptor> scriptsx;
    };


    // $ANTLR start "body_block"
    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:43:1: body_block returns [String descrx, List<IScriptDescriptor> scriptsx] : LBRACE body RBRACE ;
    public final InterchangeGrammarParser.body_block_return body_block() throws RecognitionException {
        InterchangeGrammarParser.body_block_return retval = new InterchangeGrammarParser.body_block_return();
        retval.start = input.LT(1);


        InterchangeGrammarParser.body_return body5 =null;


        try {
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:44:2: ( LBRACE body RBRACE )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:44:4: LBRACE body RBRACE
            {
            match(input,LBRACE,FOLLOW_LBRACE_in_body_block152); 

            pushFollow(FOLLOW_body_in_body_block154);
            body5=body();

            state._fsp--;


            match(input,RBRACE,FOLLOW_RBRACE_in_body_block156); 

            retval.descrx = (body5!=null?body5.descrx:null); retval.scriptsx = (body5!=null?body5.scriptsx:null);

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
    // $ANTLR end "body_block"


    public static class body_return extends ParserRuleReturnScope {
        public String descrx;
        public List<IScriptDescriptor> scriptsx;
    };


    // $ANTLR start "body"
    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:47:1: body returns [String descrx, List<IScriptDescriptor> scriptsx] : ( descr_def )? scriptlist ;
    public final InterchangeGrammarParser.body_return body() throws RecognitionException {
        InterchangeGrammarParser.body_return retval = new InterchangeGrammarParser.body_return();
        retval.start = input.LT(1);


        String descr_def6 =null;

        List<IScriptDescriptor> scriptlist7 =null;


        try {
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:48:2: ( ( descr_def )? scriptlist )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:48:4: ( descr_def )? scriptlist
            {
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:48:4: ( descr_def )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==DESCRIPTION) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:48:5: descr_def
                    {
                    pushFollow(FOLLOW_descr_def_in_body175);
                    descr_def6=descr_def();

                    state._fsp--;


                     retval.descrx = descr_def6; 

                    }
                    break;

            }


            pushFollow(FOLLOW_scriptlist_in_body182);
            scriptlist7=scriptlist();

            state._fsp--;


             retval.scriptsx = scriptlist7; 

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
    // $ANTLR end "body"



    // $ANTLR start "descr_def"
    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:50:1: descr_def returns [String desc] : DESCRIPTION COLON STRING ;
    public final String descr_def() throws RecognitionException {
        String desc = null;


        Token STRING8=null;

        try {
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:51:2: ( DESCRIPTION COLON STRING )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:51:4: DESCRIPTION COLON STRING
            {
            match(input,DESCRIPTION,FOLLOW_DESCRIPTION_in_descr_def197); 

            match(input,COLON,FOLLOW_COLON_in_descr_def199); 

            STRING8=(Token)match(input,STRING,FOLLOW_STRING_in_descr_def201); 

            desc = (STRING8!=null?STRING8.getText():null);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return desc;
    }
    // $ANTLR end "descr_def"



    // $ANTLR start "scriptlist"
    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:55:1: scriptlist returns [List<IScriptDescriptor> scripts = new ArrayList<IScriptDescriptor>()] : ( scriptaddition )+ ;
    public final List<IScriptDescriptor> scriptlist() throws RecognitionException {
        List<IScriptDescriptor> scripts =  new ArrayList<IScriptDescriptor>();


        IScriptDescriptor scriptaddition9 =null;


        try {
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:56:2: ( ( scriptaddition )+ )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:56:4: ( scriptaddition )+
            {
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:56:4: ( scriptaddition )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==AT) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:56:5: scriptaddition
            	    {
            	    pushFollow(FOLLOW_scriptaddition_in_scriptlist221);
            	    scriptaddition9=scriptaddition();

            	    state._fsp--;


            	    scripts.add(scriptaddition9);

            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
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
        return scripts;
    }
    // $ANTLR end "scriptlist"



    // $ANTLR start "scriptaddition"
    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:58:1: scriptaddition returns [IScriptDescriptor script] : ( script_def | script_def COMMA var_assignment );
    public final IScriptDescriptor scriptaddition() throws RecognitionException {
        IScriptDescriptor script = null;


        String script_def10 =null;

        String script_def11 =null;

        Map<String, Object> var_assignment12 =null;


        try {
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:59:2: ( script_def | script_def COMMA var_assignment )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==AT) ) {
                int LA7_1 = input.LA(2);

                if ( (LA7_1==SCRIPT) ) {
                    int LA7_2 = input.LA(3);

                    if ( (LA7_2==COLON) ) {
                        int LA7_3 = input.LA(4);

                        if ( (LA7_3==STRING) ) {
                            int LA7_4 = input.LA(5);

                            if ( (LA7_4==AT||LA7_4==RBRACE) ) {
                                alt7=1;
                            }
                            else if ( (LA7_4==COMMA) ) {
                                alt7=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 7, 4, input);

                                throw nvae;

                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 7, 3, input);

                            throw nvae;

                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 7, 2, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;

            }
            switch (alt7) {
                case 1 :
                    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:59:4: script_def
                    {
                    pushFollow(FOLLOW_script_def_in_scriptaddition238);
                    script_def10=script_def();

                    state._fsp--;


                     script = InterchangeDescriptorFactory.createScriptDescriptor(script_def10, null);

                    }
                    break;
                case 2 :
                    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:60:4: script_def COMMA var_assignment
                    {
                    pushFollow(FOLLOW_script_def_in_scriptaddition245);
                    script_def11=script_def();

                    state._fsp--;


                    match(input,COMMA,FOLLOW_COMMA_in_scriptaddition247); 

                    pushFollow(FOLLOW_var_assignment_in_scriptaddition249);
                    var_assignment12=var_assignment();

                    state._fsp--;


                     script = InterchangeDescriptorFactory.createScriptDescriptor(script_def11, var_assignment12);

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
        return script;
    }
    // $ANTLR end "scriptaddition"



    // $ANTLR start "script_def"
    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:63:1: script_def returns [String path] : AT SCRIPT COLON STRING ;
    public final String script_def() throws RecognitionException {
        String path = null;


        Token STRING13=null;

        try {
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:64:2: ( AT SCRIPT COLON STRING )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:64:4: AT SCRIPT COLON STRING
            {
            match(input,AT,FOLLOW_AT_in_script_def266); 

            match(input,SCRIPT,FOLLOW_SCRIPT_in_script_def268); 

            match(input,COLON,FOLLOW_COLON_in_script_def270); 

            STRING13=(Token)match(input,STRING,FOLLOW_STRING_in_script_def272); 

            path = (STRING13!=null?STRING13.getText():null);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return path;
    }
    // $ANTLR end "script_def"



    // $ANTLR start "var_assignment"
    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:66:1: var_assignment returns [Map<String, Object> params = new HashMap<String, Object>()] : (kv= simple_assignment COMMA )* simple_assignment ;
    public final Map<String, Object> var_assignment() throws RecognitionException {
        Map<String, Object> params =  new HashMap<String, Object>();


        InterchangeGrammarParser.simple_assignment_return kv =null;


        try {
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:67:2: ( (kv= simple_assignment COMMA )* simple_assignment )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:67:4: (kv= simple_assignment COMMA )* simple_assignment
            {
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:67:4: (kv= simple_assignment COMMA )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==ID) ) {
                    int LA8_1 = input.LA(2);

                    if ( (LA8_1==EQ) ) {
                        switch ( input.LA(3) ) {
                        case BOOLEAN:
                            {
                            int LA8_3 = input.LA(4);

                            if ( (LA8_3==COMMA) ) {
                                alt8=1;
                            }


                            }
                            break;
                        case INT:
                            {
                            int LA8_4 = input.LA(4);

                            if ( (LA8_4==COMMA) ) {
                                alt8=1;
                            }


                            }
                            break;
                        case FLOAT:
                            {
                            int LA8_5 = input.LA(4);

                            if ( (LA8_5==COMMA) ) {
                                alt8=1;
                            }


                            }
                            break;
                        case STRING:
                            {
                            int LA8_6 = input.LA(4);

                            if ( (LA8_6==COMMA) ) {
                                alt8=1;
                            }


                            }
                            break;

                        }

                    }


                }


                switch (alt8) {
            	case 1 :
            	    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:67:5: kv= simple_assignment COMMA
            	    {
            	    pushFollow(FOLLOW_simple_assignment_in_var_assignment290);
            	    kv=simple_assignment();

            	    state._fsp--;


            	    match(input,COMMA,FOLLOW_COMMA_in_var_assignment292); 

            	     params.put((kv!=null?kv.idx:null), (kv!=null?kv.valx:null)); 

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            pushFollow(FOLLOW_simple_assignment_in_var_assignment298);
            simple_assignment();

            state._fsp--;


             params.put((kv!=null?kv.idx:null), (kv!=null?kv.valx:null)); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return params;
    }
    // $ANTLR end "var_assignment"


    public static class simple_assignment_return extends ParserRuleReturnScope {
        public String idx;
        public Object valx;
    };


    // $ANTLR start "simple_assignment"
    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:70:1: simple_assignment returns [String idx, Object valx] : ID EQ value ;
    public final InterchangeGrammarParser.simple_assignment_return simple_assignment() throws RecognitionException {
        InterchangeGrammarParser.simple_assignment_return retval = new InterchangeGrammarParser.simple_assignment_return();
        retval.start = input.LT(1);


        Token ID14=null;
        Object value15 =null;


        try {
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:71:2: ( ID EQ value )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:71:4: ID EQ value
            {
            ID14=(Token)match(input,ID,FOLLOW_ID_in_simple_assignment315); 

            match(input,EQ,FOLLOW_EQ_in_simple_assignment317); 

            pushFollow(FOLLOW_value_in_simple_assignment319);
            value15=value();

            state._fsp--;


            retval.idx = (ID14!=null?ID14.getText():null); retval.valx = value15;

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
    // $ANTLR end "simple_assignment"



    // $ANTLR start "value"
    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:73:1: value returns [Object value] : ( BOOLEAN | INT | FLOAT | STRING );
    public final Object value() throws RecognitionException {
        Object value = null;


        Token BOOLEAN16=null;
        Token INT17=null;
        Token FLOAT18=null;
        Token STRING19=null;

        try {
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:74:2: ( BOOLEAN | INT | FLOAT | STRING )
            int alt9=4;
            switch ( input.LA(1) ) {
            case BOOLEAN:
                {
                alt9=1;
                }
                break;
            case INT:
                {
                alt9=2;
                }
                break;
            case FLOAT:
                {
                alt9=3;
                }
                break;
            case STRING:
                {
                alt9=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;

            }

            switch (alt9) {
                case 1 :
                    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:74:4: BOOLEAN
                    {
                    BOOLEAN16=(Token)match(input,BOOLEAN,FOLLOW_BOOLEAN_in_value336); 

                     value = Boolean.parseBoolean((BOOLEAN16!=null?BOOLEAN16.getText():null)); 

                    }
                    break;
                case 2 :
                    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:75:4: INT
                    {
                    INT17=(Token)match(input,INT,FOLLOW_INT_in_value344); 

                     value = Integer.parseInt((INT17!=null?INT17.getText():null)); 

                    }
                    break;
                case 3 :
                    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:76:4: FLOAT
                    {
                    FLOAT18=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_value351); 

                     value = Double.parseDouble((FLOAT18!=null?FLOAT18.getText():null)); 

                    }
                    break;
                case 4 :
                    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:77:4: STRING
                    {
                    STRING19=(Token)match(input,STRING,FOLLOW_STRING_in_value358); 

                     value = (STRING19!=null?STRING19.getText():null); 

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
        return value;
    }
    // $ANTLR end "value"

    // Delegated rules


 

    public static final BitSet FOLLOW_PUBLIC_in_head27 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_INTERCHANGE_in_head33 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_STRING_in_head35 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_LPAREN_in_head37 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_extensions_in_head39 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RPAREN_in_head41 = new BitSet(new long[]{0x0000000000140000L});
    public static final BitSet FOLLOW_diagram_definition_in_head44 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_body_block_in_head48 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_head50 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_extensions72 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_SEPARATOR_in_extensions74 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_ID_in_extensions82 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LRBRACKET_in_diagram_definition100 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_diagram_type_in_diagram_definition104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_diagram_type125 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_COMMA_in_diagram_type127 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_ID_in_diagram_type135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACE_in_body_block152 = new BitSet(new long[]{0x0000000000000210L});
    public static final BitSet FOLLOW_body_in_body_block154 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_RBRACE_in_body_block156 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_descr_def_in_body175 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_scriptlist_in_body182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DESCRIPTION_in_descr_def197 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_COLON_in_descr_def199 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_STRING_in_descr_def201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_scriptaddition_in_scriptlist221 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_script_def_in_scriptaddition238 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_script_def_in_scriptaddition245 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_COMMA_in_scriptaddition247 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_var_assignment_in_scriptaddition249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AT_in_script_def266 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_SCRIPT_in_script_def268 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_COLON_in_script_def270 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_STRING_in_script_def272 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simple_assignment_in_var_assignment290 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_COMMA_in_var_assignment292 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_simple_assignment_in_var_assignment298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_simple_assignment315 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_EQ_in_simple_assignment317 = new BitSet(new long[]{0x0000000008012020L});
    public static final BitSet FOLLOW_value_in_simple_assignment319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_in_value336 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_value344 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FLOAT_in_value351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_value358 = new BitSet(new long[]{0x0000000000000002L});

}
package org.bflow.toolbox.hive.interchange.mif.grammar;

// $ANTLR 3.4 F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g 2012-10-03 12:05:20

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class InterchangeGrammarLexer extends Lexer {
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
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public InterchangeGrammarLexer() {} 
    public InterchangeGrammarLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InterchangeGrammarLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g"; }

    // $ANTLR start "BOOLEAN"
    public final void mBOOLEAN() throws RecognitionException {
        try {
            int _type = BOOLEAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:82:9: ( 'true' | 'false' )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='t') ) {
                alt1=1;
            }
            else if ( (LA1_0=='f') ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;

            }
            switch (alt1) {
                case 1 :
                    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:82:11: 'true'
                    {
                    match("true"); 



                    setText("true");

                    }
                    break;
                case 2 :
                    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:82:39: 'false'
                    {
                    match("false"); 



                    setText("false");

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BOOLEAN"

    // $ANTLR start "INTERCHANGE"
    public final void mINTERCHANGE() throws RecognitionException {
        try {
            int _type = INTERCHANGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:87:2: ( 'interchange' )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:87:4: 'interchange'
            {
            match("interchange"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "INTERCHANGE"

    // $ANTLR start "SCRIPT"
    public final void mSCRIPT() throws RecognitionException {
        try {
            int _type = SCRIPT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:89:8: ( 'script' )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:89:10: 'script'
            {
            match("script"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SCRIPT"

    // $ANTLR start "DESCRIPTION"
    public final void mDESCRIPTION() throws RecognitionException {
        try {
            int _type = DESCRIPTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:92:2: ( 'description' )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:92:4: 'description'
            {
            match("description"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DESCRIPTION"

    // $ANTLR start "PUBLIC"
    public final void mPUBLIC() throws RecognitionException {
        try {
            int _type = PUBLIC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:94:9: ( 'public' )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:94:11: 'public'
            {
            match("public"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PUBLIC"

    // $ANTLR start "LRBRACKET"
    public final void mLRBRACKET() throws RecognitionException {
        try {
            int _type = LRBRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:97:2: ( '<>' )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:97:4: '<>'
            {
            match("<>"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LRBRACKET"

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:99:9: ( '(' )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:99:11: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LPAREN"

    // $ANTLR start "RPAREN"
    public final void mRPAREN() throws RecognitionException {
        try {
            int _type = RPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:100:9: ( ')' )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:100:11: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RPAREN"

    // $ANTLR start "LBRACE"
    public final void mLBRACE() throws RecognitionException {
        try {
            int _type = LBRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:101:8: ( '{' )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:101:10: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LBRACE"

    // $ANTLR start "RBRACE"
    public final void mRBRACE() throws RecognitionException {
        try {
            int _type = RBRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:102:8: ( '}' )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:102:10: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RBRACE"

    // $ANTLR start "SEPARATOR"
    public final void mSEPARATOR() throws RecognitionException {
        try {
            int _type = SEPARATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:104:2: ( '|' )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:104:4: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SEPARATOR"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:105:7: ( ':' )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:105:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COLON"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:106:7: ( ',' )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:106:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "AT"
    public final void mAT() throws RecognitionException {
        try {
            int _type = AT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:107:4: ( '@' )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:107:7: '@'
            {
            match('@'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "AT"

    // $ANTLR start "EQ"
    public final void mEQ() throws RecognitionException {
        try {
            int _type = EQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:108:4: ( '=' )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:108:6: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EQ"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:112:5: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:112:7: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:112:31: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= '0' && LA2_0 <= '9')||(LA2_0 >= 'A' && LA2_0 <= 'Z')||LA2_0=='_'||(LA2_0 >= 'a' && LA2_0 <= 'z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:116:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:116:9: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
            match("//"); 



            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:116:14: (~ ( '\\n' | '\\r' ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0 >= '\u0000' && LA3_0 <= '\t')||(LA3_0 >= '\u000B' && LA3_0 <= '\f')||(LA3_0 >= '\u000E' && LA3_0 <= '\uFFFF')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:116:28: ( '\\r' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='\r') ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:116:28: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }


            match('\n'); 

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:120:5: ( '\\'' ( ESC_SEQ |~ ( '\\\\' | '\\'' ) )* '\\'' )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:120:8: '\\'' ( ESC_SEQ |~ ( '\\\\' | '\\'' ) )* '\\''
            {
            match('\''); 

            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:120:13: ( ESC_SEQ |~ ( '\\\\' | '\\'' ) )*
            loop5:
            do {
                int alt5=3;
                int LA5_0 = input.LA(1);

                if ( (LA5_0=='\\') ) {
                    alt5=1;
                }
                else if ( ((LA5_0 >= '\u0000' && LA5_0 <= '&')||(LA5_0 >= '(' && LA5_0 <= '[')||(LA5_0 >= ']' && LA5_0 <= '\uFFFF')) ) {
                    alt5=2;
                }


                switch (alt5) {
            	case 1 :
            	    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:120:15: ESC_SEQ
            	    {
            	    mESC_SEQ(); 


            	    }
            	    break;
            	case 2 :
            	    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:120:25: ~ ( '\\\\' | '\\'' )
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '&')||(input.LA(1) >= '(' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            match('\''); 

             
                	  setText(getText().substring(1, getText().length() - 1));
            	

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STRING"

    // $ANTLR start "HEX_DIGIT"
    public final void mHEX_DIGIT() throws RecognitionException {
        try {
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:128:11: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HEX_DIGIT"

    // $ANTLR start "ESC_SEQ"
    public final void mESC_SEQ() throws RecognitionException {
        try {
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:132:5: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | UNICODE_ESC | OCTAL_ESC )
            int alt6=3;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='\\') ) {
                switch ( input.LA(2) ) {
                case '\"':
                case '\'':
                case '\\':
                case 'b':
                case 'f':
                case 'n':
                case 'r':
                case 't':
                    {
                    alt6=1;
                    }
                    break;
                case 'u':
                    {
                    alt6=2;
                    }
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                    {
                    alt6=3;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 1, input);

                    throw nvae;

                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }
            switch (alt6) {
                case 1 :
                    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:132:9: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
                    {
                    match('\\'); 

                    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;
                case 2 :
                    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:133:9: UNICODE_ESC
                    {
                    mUNICODE_ESC(); 


                    }
                    break;
                case 3 :
                    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:134:9: OCTAL_ESC
                    {
                    mOCTAL_ESC(); 


                    }
                    break;

            }

        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ESC_SEQ"

    // $ANTLR start "OCTAL_ESC"
    public final void mOCTAL_ESC() throws RecognitionException {
        try {
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:139:5: ( '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) )
            int alt7=3;
            int LA7_0 = input.LA(1);

            if ( (LA7_0=='\\') ) {
                int LA7_1 = input.LA(2);

                if ( ((LA7_1 >= '0' && LA7_1 <= '3')) ) {
                    int LA7_2 = input.LA(3);

                    if ( ((LA7_2 >= '0' && LA7_2 <= '7')) ) {
                        int LA7_4 = input.LA(4);

                        if ( ((LA7_4 >= '0' && LA7_4 <= '7')) ) {
                            alt7=1;
                        }
                        else {
                            alt7=2;
                        }
                    }
                    else {
                        alt7=3;
                    }
                }
                else if ( ((LA7_1 >= '4' && LA7_1 <= '7')) ) {
                    int LA7_3 = input.LA(3);

                    if ( ((LA7_3 >= '0' && LA7_3 <= '7')) ) {
                        alt7=2;
                    }
                    else {
                        alt7=3;
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
                    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:139:9: '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 

                    if ( (input.LA(1) >= '0' && input.LA(1) <= '3') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;
                case 2 :
                    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:140:9: '\\\\' ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 

                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;
                case 3 :
                    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:141:9: '\\\\' ( '0' .. '7' )
                    {
                    match('\\'); 

                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }

        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OCTAL_ESC"

    // $ANTLR start "UNICODE_ESC"
    public final void mUNICODE_ESC() throws RecognitionException {
        try {
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:146:5: ( '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:146:9: '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
            {
            match('\\'); 

            match('u'); 

            mHEX_DIGIT(); 


            mHEX_DIGIT(); 


            mHEX_DIGIT(); 


            mHEX_DIGIT(); 


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "UNICODE_ESC"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:148:5: ( ( '0' .. '9' )+ )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:148:7: ( '0' .. '9' )+
            {
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:148:7: ( '0' .. '9' )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0 >= '0' && LA8_0 <= '9')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "FLOAT"
    public final void mFLOAT() throws RecognitionException {
        try {
            int _type = FLOAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:152:5: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )? | '.' ( '0' .. '9' )+ ( EXPONENT )? | ( '0' .. '9' )+ EXPONENT )
            int alt15=3;
            alt15 = dfa15.predict(input);
            switch (alt15) {
                case 1 :
                    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:152:9: ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )?
                    {
                    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:152:9: ( '0' .. '9' )+
                    int cnt9=0;
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( ((LA9_0 >= '0' && LA9_0 <= '9')) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt9 >= 1 ) break loop9;
                                EarlyExitException eee =
                                    new EarlyExitException(9, input);
                                throw eee;
                        }
                        cnt9++;
                    } while (true);


                    match('.'); 

                    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:152:25: ( '0' .. '9' )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( ((LA10_0 >= '0' && LA10_0 <= '9')) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);


                    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:152:37: ( EXPONENT )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0=='E'||LA11_0=='e') ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:152:37: EXPONENT
                            {
                            mEXPONENT(); 


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:153:9: '.' ( '0' .. '9' )+ ( EXPONENT )?
                    {
                    match('.'); 

                    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:153:13: ( '0' .. '9' )+
                    int cnt12=0;
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( ((LA12_0 >= '0' && LA12_0 <= '9')) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt12 >= 1 ) break loop12;
                                EarlyExitException eee =
                                    new EarlyExitException(12, input);
                                throw eee;
                        }
                        cnt12++;
                    } while (true);


                    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:153:25: ( EXPONENT )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0=='E'||LA13_0=='e') ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:153:25: EXPONENT
                            {
                            mEXPONENT(); 


                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:154:9: ( '0' .. '9' )+ EXPONENT
                    {
                    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:154:9: ( '0' .. '9' )+
                    int cnt14=0;
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( ((LA14_0 >= '0' && LA14_0 <= '9')) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt14 >= 1 ) break loop14;
                                EarlyExitException eee =
                                    new EarlyExitException(14, input);
                                throw eee;
                        }
                        cnt14++;
                    } while (true);


                    mEXPONENT(); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FLOAT"

    // $ANTLR start "EXPONENT"
    public final void mEXPONENT() throws RecognitionException {
        try {
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:159:10: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:159:12: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:159:22: ( '+' | '-' )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0=='+'||LA16_0=='-') ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:159:33: ( '0' .. '9' )+
            int cnt17=0;
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( ((LA17_0 >= '0' && LA17_0 <= '9')) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt17 >= 1 ) break loop17;
                        EarlyExitException eee =
                            new EarlyExitException(17, input);
                        throw eee;
                }
                cnt17++;
            } while (true);


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EXPONENT"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:160:5: ( ( ' ' | '\\t' | '\\r' | '\\n' ) )
            // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:160:9: ( ' ' | '\\t' | '\\r' | '\\n' )
            {
            if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    public void mTokens() throws RecognitionException {
        // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:1:8: ( BOOLEAN | INTERCHANGE | SCRIPT | DESCRIPTION | PUBLIC | LRBRACKET | LPAREN | RPAREN | LBRACE | RBRACE | SEPARATOR | COLON | COMMA | AT | EQ | ID | COMMENT | STRING | INT | FLOAT | WS )
        int alt18=21;
        alt18 = dfa18.predict(input);
        switch (alt18) {
            case 1 :
                // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:1:10: BOOLEAN
                {
                mBOOLEAN(); 


                }
                break;
            case 2 :
                // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:1:18: INTERCHANGE
                {
                mINTERCHANGE(); 


                }
                break;
            case 3 :
                // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:1:30: SCRIPT
                {
                mSCRIPT(); 


                }
                break;
            case 4 :
                // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:1:37: DESCRIPTION
                {
                mDESCRIPTION(); 


                }
                break;
            case 5 :
                // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:1:49: PUBLIC
                {
                mPUBLIC(); 


                }
                break;
            case 6 :
                // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:1:56: LRBRACKET
                {
                mLRBRACKET(); 


                }
                break;
            case 7 :
                // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:1:66: LPAREN
                {
                mLPAREN(); 


                }
                break;
            case 8 :
                // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:1:73: RPAREN
                {
                mRPAREN(); 


                }
                break;
            case 9 :
                // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:1:80: LBRACE
                {
                mLBRACE(); 


                }
                break;
            case 10 :
                // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:1:87: RBRACE
                {
                mRBRACE(); 


                }
                break;
            case 11 :
                // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:1:94: SEPARATOR
                {
                mSEPARATOR(); 


                }
                break;
            case 12 :
                // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:1:104: COLON
                {
                mCOLON(); 


                }
                break;
            case 13 :
                // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:1:110: COMMA
                {
                mCOMMA(); 


                }
                break;
            case 14 :
                // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:1:116: AT
                {
                mAT(); 


                }
                break;
            case 15 :
                // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:1:119: EQ
                {
                mEQ(); 


                }
                break;
            case 16 :
                // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:1:122: ID
                {
                mID(); 


                }
                break;
            case 17 :
                // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:1:125: COMMENT
                {
                mCOMMENT(); 


                }
                break;
            case 18 :
                // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:1:133: STRING
                {
                mSTRING(); 


                }
                break;
            case 19 :
                // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:1:140: INT
                {
                mINT(); 


                }
                break;
            case 20 :
                // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:1:144: FLOAT
                {
                mFLOAT(); 


                }
                break;
            case 21 :
                // F:\\GIT Sources\\eclipsemodeling\\org.bflow.toolbox.interchange\\grammar\\InterchangeGrammar.g:1:150: WS
                {
                mWS(); 


                }
                break;

        }

    }


    protected DFA15 dfa15 = new DFA15(this);
    protected DFA18 dfa18 = new DFA18(this);
    static final String DFA15_eotS =
        "\5\uffff";
    static final String DFA15_eofS =
        "\5\uffff";
    static final String DFA15_minS =
        "\2\56\3\uffff";
    static final String DFA15_maxS =
        "\1\71\1\145\3\uffff";
    static final String DFA15_acceptS =
        "\2\uffff\1\2\1\1\1\3";
    static final String DFA15_specialS =
        "\5\uffff}>";
    static final String[] DFA15_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\3\1\uffff\12\1\13\uffff\1\4\37\uffff\1\4",
            "",
            "",
            ""
    };

    static final short[] DFA15_eot = DFA.unpackEncodedString(DFA15_eotS);
    static final short[] DFA15_eof = DFA.unpackEncodedString(DFA15_eofS);
    static final char[] DFA15_min = DFA.unpackEncodedStringToUnsignedChars(DFA15_minS);
    static final char[] DFA15_max = DFA.unpackEncodedStringToUnsignedChars(DFA15_maxS);
    static final short[] DFA15_accept = DFA.unpackEncodedString(DFA15_acceptS);
    static final short[] DFA15_special = DFA.unpackEncodedString(DFA15_specialS);
    static final short[][] DFA15_transition;

    static {
        int numStates = DFA15_transitionS.length;
        DFA15_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA15_transition[i] = DFA.unpackEncodedString(DFA15_transitionS[i]);
        }
    }

    class DFA15 extends DFA {

        public DFA15(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 15;
            this.eot = DFA15_eot;
            this.eof = DFA15_eof;
            this.min = DFA15_min;
            this.max = DFA15_max;
            this.accept = DFA15_accept;
            this.special = DFA15_special;
            this.transition = DFA15_transition;
        }
        public String getDescription() {
            return "151:1: FLOAT : ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )? | '.' ( '0' .. '9' )+ ( EXPONENT )? | ( '0' .. '9' )+ EXPONENT );";
        }
    }
    static final String DFA18_eotS =
        "\1\uffff\6\21\15\uffff\1\35\2\uffff\6\21\1\uffff\6\21\1\52\5\21"+
        "\1\uffff\1\52\5\21\1\65\1\21\1\67\1\21\1\uffff\1\21\1\uffff\6\21"+
        "\1\100\1\101\2\uffff";
    static final String DFA18_eofS =
        "\102\uffff";
    static final String DFA18_minS =
        "\1\11\1\162\1\141\1\156\1\143\1\145\1\165\15\uffff\1\56\2\uffff"+
        "\1\165\1\154\1\164\1\162\1\163\1\142\1\uffff\1\145\1\163\1\145\1"+
        "\151\1\143\1\154\1\60\1\145\1\162\1\160\1\162\1\151\1\uffff\1\60"+
        "\1\143\1\164\1\151\1\143\1\150\1\60\1\160\1\60\1\141\1\uffff\1\164"+
        "\1\uffff\1\156\1\151\1\147\1\157\1\145\1\156\2\60\2\uffff";
    static final String DFA18_maxS =
        "\1\175\1\162\1\141\1\156\1\143\1\145\1\165\15\uffff\1\145\2\uffff"+
        "\1\165\1\154\1\164\1\162\1\163\1\142\1\uffff\1\145\1\163\1\145\1"+
        "\151\1\143\1\154\1\172\1\145\1\162\1\160\1\162\1\151\1\uffff\1\172"+
        "\1\143\1\164\1\151\1\143\1\150\1\172\1\160\1\172\1\141\1\uffff\1"+
        "\164\1\uffff\1\156\1\151\1\147\1\157\1\145\1\156\2\172\2\uffff";
    static final String DFA18_acceptS =
        "\7\uffff\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1"+
        "\21\1\22\1\uffff\1\24\1\25\6\uffff\1\23\14\uffff\1\1\12\uffff\1"+
        "\3\1\uffff\1\5\10\uffff\1\2\1\4";
    static final String DFA18_specialS =
        "\102\uffff}>";
    static final String[] DFA18_transitionS = {
            "\2\26\2\uffff\1\26\22\uffff\1\26\6\uffff\1\23\1\10\1\11\2\uffff"+
            "\1\16\1\uffff\1\25\1\22\12\24\1\15\1\uffff\1\7\1\20\2\uffff"+
            "\1\17\32\21\4\uffff\1\21\1\uffff\3\21\1\5\1\21\1\2\2\21\1\3"+
            "\6\21\1\6\2\21\1\4\1\1\6\21\1\12\1\14\1\13",
            "\1\27",
            "\1\30",
            "\1\31",
            "\1\32",
            "\1\33",
            "\1\34",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\25\1\uffff\12\24\13\uffff\1\25\37\uffff\1\25",
            "",
            "",
            "\1\36",
            "\1\37",
            "\1\40",
            "\1\41",
            "\1\42",
            "\1\43",
            "",
            "\1\44",
            "\1\45",
            "\1\46",
            "\1\47",
            "\1\50",
            "\1\51",
            "\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21",
            "\1\53",
            "\1\54",
            "\1\55",
            "\1\56",
            "\1\57",
            "",
            "\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21",
            "\1\60",
            "\1\61",
            "\1\62",
            "\1\63",
            "\1\64",
            "\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21",
            "\1\66",
            "\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21",
            "\1\70",
            "",
            "\1\71",
            "",
            "\1\72",
            "\1\73",
            "\1\74",
            "\1\75",
            "\1\76",
            "\1\77",
            "\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21",
            "\12\21\7\uffff\32\21\4\uffff\1\21\1\uffff\32\21",
            "",
            ""
    };

    static final short[] DFA18_eot = DFA.unpackEncodedString(DFA18_eotS);
    static final short[] DFA18_eof = DFA.unpackEncodedString(DFA18_eofS);
    static final char[] DFA18_min = DFA.unpackEncodedStringToUnsignedChars(DFA18_minS);
    static final char[] DFA18_max = DFA.unpackEncodedStringToUnsignedChars(DFA18_maxS);
    static final short[] DFA18_accept = DFA.unpackEncodedString(DFA18_acceptS);
    static final short[] DFA18_special = DFA.unpackEncodedString(DFA18_specialS);
    static final short[][] DFA18_transition;

    static {
        int numStates = DFA18_transitionS.length;
        DFA18_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA18_transition[i] = DFA.unpackEncodedString(DFA18_transitionS[i]);
        }
    }

    class DFA18 extends DFA {

        public DFA18(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 18;
            this.eot = DFA18_eot;
            this.eof = DFA18_eof;
            this.min = DFA18_min;
            this.max = DFA18_max;
            this.accept = DFA18_accept;
            this.special = DFA18_special;
            this.transition = DFA18_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( BOOLEAN | INTERCHANGE | SCRIPT | DESCRIPTION | PUBLIC | LRBRACKET | LPAREN | RPAREN | LBRACE | RBRACE | SEPARATOR | COLON | COMMA | AT | EQ | ID | COMMENT | STRING | INT | FLOAT | WS );";
        }
    }
 

}
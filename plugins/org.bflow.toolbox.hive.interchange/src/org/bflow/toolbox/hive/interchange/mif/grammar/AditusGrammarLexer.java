// $ANTLR 3.4 F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g 2013-07-11 16:28:05

package org.bflow.toolbox.hive.interchange.mif.grammar;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class AditusGrammarLexer extends Lexer {
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
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public AditusGrammarLexer() {} 
    public AditusGrammarLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public AditusGrammarLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g"; }

    // $ANTLR start "T__37"
    public final void mT__37() throws RecognitionException {
        try {
            int _type = T__37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:6:7: ( '=>' )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:6:9: '=>'
            {
            match("=>"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__37"

    // $ANTLR start "T__38"
    public final void mT__38() throws RecognitionException {
        try {
            int _type = T__38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:7:7: ( '|' )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:7:9: '|'
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
    // $ANTLR end "T__38"

    // $ANTLR start "XPATH"
    public final void mXPATH() throws RecognitionException {
        try {
            int _type = XPATH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:97:8: ( '$' (~ ( '$' ) )* '$' )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:97:10: '$' (~ ( '$' ) )* '$'
            {
            match('$'); 

            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:97:14: (~ ( '$' ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0 >= '\u0000' && LA1_0 <= '#')||(LA1_0 >= '%' && LA1_0 <= '\uFFFF')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '#')||(input.LA(1) >= '%' && input.LA(1) <= '\uFFFF') ) {
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
            	    break loop1;
                }
            } while (true);


            match('$'); 

             setText(getText().substring(1, getText().length() - 1)); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "XPATH"

    // $ANTLR start "IMPORT"
    public final void mIMPORT() throws RecognitionException {
        try {
            int _type = IMPORT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:108:9: ( 'import' )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:108:11: 'import'
            {
            match("import"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IMPORT"

    // $ANTLR start "MODEL"
    public final void mMODEL() throws RecognitionException {
        try {
            int _type = MODEL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:109:7: ( 'model' )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:109:10: 'model'
            {
            match("model"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MODEL"

    // $ANTLR start "SHAPES"
    public final void mSHAPES() throws RecognitionException {
        try {
            int _type = SHAPES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:110:8: ( 'shapes' )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:110:10: 'shapes'
            {
            match("shapes"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SHAPES"

    // $ANTLR start "EDGES"
    public final void mEDGES() throws RecognitionException {
        try {
            int _type = EDGES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:111:7: ( 'edges' )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:111:9: 'edges'
            {
            match("edges"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EDGES"

    // $ANTLR start "SHAPE"
    public final void mSHAPE() throws RecognitionException {
        try {
            int _type = SHAPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:112:7: ( 'shape' )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:112:9: 'shape'
            {
            match("shape"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SHAPE"

    // $ANTLR start "EDGE"
    public final void mEDGE() throws RecognitionException {
        try {
            int _type = EDGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:113:6: ( 'edge' )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:113:8: 'edge'
            {
            match("edge"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EDGE"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:114:4: ( 'id' )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:114:7: 'id'
            {
            match("id"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "TYPE"
    public final void mTYPE() throws RecognitionException {
        try {
            int _type = TYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:115:6: ( 'type' )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:115:8: 'type'
            {
            match("type"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TYPE"

    // $ANTLR start "NAME"
    public final void mNAME() throws RecognitionException {
        try {
            int _type = NAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:116:6: ( 'name' )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:116:8: 'name'
            {
            match("name"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NAME"

    // $ANTLR start "WIDTH"
    public final void mWIDTH() throws RecognitionException {
        try {
            int _type = WIDTH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:117:7: ( 'width' )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:117:10: 'width'
            {
            match("width"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WIDTH"

    // $ANTLR start "HEIGHT"
    public final void mHEIGHT() throws RecognitionException {
        try {
            int _type = HEIGHT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:118:8: ( 'height' )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:118:10: 'height'
            {
            match("height"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HEIGHT"

    // $ANTLR start "X"
    public final void mX() throws RecognitionException {
        try {
            int _type = X;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:119:3: ( 'x' )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:119:5: 'x'
            {
            match('x'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "X"

    // $ANTLR start "Y"
    public final void mY() throws RecognitionException {
        try {
            int _type = Y;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:120:3: ( 'y' )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:120:5: 'y'
            {
            match('y'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Y"

    // $ANTLR start "SOURCE"
    public final void mSOURCE() throws RecognitionException {
        try {
            int _type = SOURCE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:121:8: ( 'source' )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:121:10: 'source'
            {
            match("source"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SOURCE"

    // $ANTLR start "TARGET"
    public final void mTARGET() throws RecognitionException {
        try {
            int _type = TARGET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:122:8: ( 'target' )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:122:10: 'target'
            {
            match("target"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TARGET"

    // $ANTLR start "ATTRIBUTES"
    public final void mATTRIBUTES() throws RecognitionException {
        try {
            int _type = ATTRIBUTES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:124:2: ( 'attributes' )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:124:4: 'attributes'
            {
            match("attributes"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ATTRIBUTES"

    // $ANTLR start "EQ"
    public final void mEQ() throws RecognitionException {
        try {
            int _type = EQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:125:4: ( '=' )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:125:6: '='
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

    // $ANTLR start "DEQ"
    public final void mDEQ() throws RecognitionException {
        try {
            int _type = DEQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:126:5: ( '==' )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:126:7: '=='
            {
            match("=="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DEQ"

    // $ANTLR start "IIF"
    public final void mIIF() throws RecognitionException {
        try {
            int _type = IIF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:127:5: ( '<=>' )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:127:7: '<=>'
            {
            match("<=>"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IIF"

    // $ANTLR start "LBRACE"
    public final void mLBRACE() throws RecognitionException {
        try {
            int _type = LBRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:128:8: ( '{' )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:128:10: '{'
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
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:129:8: ( '}' )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:129:10: '}'
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

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:130:5: ( '.' )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:130:8: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DOT"

    // $ANTLR start "NULL"
    public final void mNULL() throws RecognitionException {
        try {
            int _type = NULL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:131:6: ( 'null' )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:131:8: 'null'
            {
            match("null"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NULL"

    // $ANTLR start "IDENTIFIER"
    public final void mIDENTIFIER() throws RecognitionException {
        try {
            int _type = IDENTIFIER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:140:13: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:140:15: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:140:39: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= '0' && LA2_0 <= '9')||(LA2_0 >= 'A' && LA2_0 <= 'Z')||LA2_0=='_'||(LA2_0 >= 'a' && LA2_0 <= 'z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:
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
    // $ANTLR end "IDENTIFIER"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:143:5: ( ( '0' .. '9' )+ )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:143:7: ( '0' .. '9' )+
            {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:143:7: ( '0' .. '9' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0 >= '0' && LA3_0 <= '9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:
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
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
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

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:147:5: ( '\\'' (~ ( '\\'' ) )* '\\'' )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:147:8: '\\'' (~ ( '\\'' ) )* '\\''
            {
            match('\''); 

            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:147:13: (~ ( '\\'' ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0 >= '\u0000' && LA4_0 <= '&')||(LA4_0 >= '(' && LA4_0 <= '\uFFFF')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '&')||(input.LA(1) >= '(' && input.LA(1) <= '\uFFFF') ) {
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
            	    break loop4;
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

    // $ANTLR start "ESC_SEQ"
    public final void mESC_SEQ() throws RecognitionException {
        try {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:153:5: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | UNICODE_ESC | OCTAL_ESC )
            int alt5=3;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='\\') ) {
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
                    alt5=1;
                    }
                    break;
                case 'u':
                    {
                    alt5=2;
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
                    alt5=3;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 1, input);

                    throw nvae;

                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }
            switch (alt5) {
                case 1 :
                    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:153:9: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
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
                    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:154:9: UNICODE_ESC
                    {
                    mUNICODE_ESC(); 


                    }
                    break;
                case 3 :
                    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:155:9: OCTAL_ESC
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
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:160:5: ( '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) )
            int alt6=3;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='\\') ) {
                int LA6_1 = input.LA(2);

                if ( ((LA6_1 >= '0' && LA6_1 <= '3')) ) {
                    int LA6_2 = input.LA(3);

                    if ( ((LA6_2 >= '0' && LA6_2 <= '7')) ) {
                        int LA6_4 = input.LA(4);

                        if ( ((LA6_4 >= '0' && LA6_4 <= '7')) ) {
                            alt6=1;
                        }
                        else {
                            alt6=2;
                        }
                    }
                    else {
                        alt6=3;
                    }
                }
                else if ( ((LA6_1 >= '4' && LA6_1 <= '7')) ) {
                    int LA6_3 = input.LA(3);

                    if ( ((LA6_3 >= '0' && LA6_3 <= '7')) ) {
                        alt6=2;
                    }
                    else {
                        alt6=3;
                    }
                }
                else {
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
                    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:160:9: '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' )
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
                    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:161:9: '\\\\' ( '0' .. '7' ) ( '0' .. '7' )
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
                    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:162:9: '\\\\' ( '0' .. '7' )
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
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:167:5: ( '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:167:9: '\\\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
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

    // $ANTLR start "HEX_DIGIT"
    public final void mHEX_DIGIT() throws RecognitionException {
        try {
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:171:11: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:
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

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:173:5: ( '#' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:173:9: '#' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
            match('#'); 

            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:173:13: (~ ( '\\n' | '\\r' ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0 >= '\u0000' && LA7_0 <= '\t')||(LA7_0 >= '\u000B' && LA7_0 <= '\f')||(LA7_0 >= '\u000E' && LA7_0 <= '\uFFFF')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:
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
            	    break loop7;
                }
            } while (true);


            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:173:27: ( '\\r' )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0=='\r') ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:173:27: '\\r'
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

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:176:5: ( ( ' ' | '\\t' | '\\r' | '\\n' ) )
            // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:176:9: ( ' ' | '\\t' | '\\r' | '\\n' )
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
        // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:1:8: ( T__37 | T__38 | XPATH | IMPORT | MODEL | SHAPES | EDGES | SHAPE | EDGE | ID | TYPE | NAME | WIDTH | HEIGHT | X | Y | SOURCE | TARGET | ATTRIBUTES | EQ | DEQ | IIF | LBRACE | RBRACE | DOT | NULL | IDENTIFIER | INT | STRING | COMMENT | WS )
        int alt9=31;
        alt9 = dfa9.predict(input);
        switch (alt9) {
            case 1 :
                // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:1:10: T__37
                {
                mT__37(); 


                }
                break;
            case 2 :
                // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:1:16: T__38
                {
                mT__38(); 


                }
                break;
            case 3 :
                // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:1:22: XPATH
                {
                mXPATH(); 


                }
                break;
            case 4 :
                // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:1:28: IMPORT
                {
                mIMPORT(); 


                }
                break;
            case 5 :
                // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:1:35: MODEL
                {
                mMODEL(); 


                }
                break;
            case 6 :
                // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:1:41: SHAPES
                {
                mSHAPES(); 


                }
                break;
            case 7 :
                // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:1:48: EDGES
                {
                mEDGES(); 


                }
                break;
            case 8 :
                // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:1:54: SHAPE
                {
                mSHAPE(); 


                }
                break;
            case 9 :
                // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:1:60: EDGE
                {
                mEDGE(); 


                }
                break;
            case 10 :
                // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:1:65: ID
                {
                mID(); 


                }
                break;
            case 11 :
                // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:1:68: TYPE
                {
                mTYPE(); 


                }
                break;
            case 12 :
                // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:1:73: NAME
                {
                mNAME(); 


                }
                break;
            case 13 :
                // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:1:78: WIDTH
                {
                mWIDTH(); 


                }
                break;
            case 14 :
                // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:1:84: HEIGHT
                {
                mHEIGHT(); 


                }
                break;
            case 15 :
                // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:1:91: X
                {
                mX(); 


                }
                break;
            case 16 :
                // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:1:93: Y
                {
                mY(); 


                }
                break;
            case 17 :
                // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:1:95: SOURCE
                {
                mSOURCE(); 


                }
                break;
            case 18 :
                // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:1:102: TARGET
                {
                mTARGET(); 


                }
                break;
            case 19 :
                // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:1:109: ATTRIBUTES
                {
                mATTRIBUTES(); 


                }
                break;
            case 20 :
                // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:1:120: EQ
                {
                mEQ(); 


                }
                break;
            case 21 :
                // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:1:123: DEQ
                {
                mDEQ(); 


                }
                break;
            case 22 :
                // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:1:127: IIF
                {
                mIIF(); 


                }
                break;
            case 23 :
                // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:1:131: LBRACE
                {
                mLBRACE(); 


                }
                break;
            case 24 :
                // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:1:138: RBRACE
                {
                mRBRACE(); 


                }
                break;
            case 25 :
                // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:1:145: DOT
                {
                mDOT(); 


                }
                break;
            case 26 :
                // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:1:149: NULL
                {
                mNULL(); 


                }
                break;
            case 27 :
                // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:1:154: IDENTIFIER
                {
                mIDENTIFIER(); 


                }
                break;
            case 28 :
                // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:1:165: INT
                {
                mINT(); 


                }
                break;
            case 29 :
                // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:1:169: STRING
                {
                mSTRING(); 


                }
                break;
            case 30 :
                // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:1:176: COMMENT
                {
                mCOMMENT(); 


                }
                break;
            case 31 :
                // F:\\bflow\\GIT\\emtb\\org.bflow.toolbox.interchange\\grammar\\AditusGrammar.g:1:184: WS
                {
                mWS(); 


                }
                break;

        }

    }


    protected DFA9 dfa9 = new DFA9(this);
    static final String DFA9_eotS =
        "\1\uffff\1\32\2\uffff\10\23\1\47\1\50\1\23\14\uffff\1\23\1\53\12"+
        "\23\2\uffff\2\23\1\uffff\17\23\1\110\1\111\1\23\1\113\1\114\4\23"+
        "\1\121\1\123\1\23\1\125\2\uffff\1\23\2\uffff\1\127\2\23\1\132\1"+
        "\uffff\1\133\1\uffff\1\134\1\uffff\1\135\1\uffff\1\136\1\23\5\uffff"+
        "\3\23\1\143\1\uffff";
    static final String DFA9_eofS =
        "\144\uffff";
    static final String DFA9_minS =
        "\1\11\1\75\2\uffff\1\144\1\157\1\150\1\144\2\141\1\151\1\145\2\60"+
        "\1\164\14\uffff\1\160\1\60\1\144\1\141\1\165\1\147\1\160\1\162\1"+
        "\155\1\154\1\144\1\151\2\uffff\1\164\1\157\1\uffff\1\145\1\160\1"+
        "\162\2\145\1\147\1\145\1\154\1\164\1\147\2\162\1\154\1\145\1\143"+
        "\2\60\1\145\2\60\2\150\1\151\1\164\2\60\1\145\1\60\2\uffff\1\164"+
        "\2\uffff\1\60\1\164\1\142\1\60\1\uffff\1\60\1\uffff\1\60\1\uffff"+
        "\1\60\1\uffff\1\60\1\165\5\uffff\1\164\1\145\1\163\1\60\1\uffff";
    static final String DFA9_maxS =
        "\1\175\1\76\2\uffff\1\155\2\157\1\144\1\171\1\165\1\151\1\145\2"+
        "\172\1\164\14\uffff\1\160\1\172\1\144\1\141\1\165\1\147\1\160\1"+
        "\162\1\155\1\154\1\144\1\151\2\uffff\1\164\1\157\1\uffff\1\145\1"+
        "\160\1\162\2\145\1\147\1\145\1\154\1\164\1\147\2\162\1\154\1\145"+
        "\1\143\2\172\1\145\2\172\2\150\1\151\1\164\2\172\1\145\1\172\2\uffff"+
        "\1\164\2\uffff\1\172\1\164\1\142\1\172\1\uffff\1\172\1\uffff\1\172"+
        "\1\uffff\1\172\1\uffff\1\172\1\165\5\uffff\1\164\1\145\1\163\1\172"+
        "\1\uffff";
    static final String DFA9_acceptS =
        "\2\uffff\1\2\1\3\13\uffff\1\26\1\27\1\30\1\31\1\33\1\34\1\35\1\36"+
        "\1\37\1\1\1\25\1\24\14\uffff\1\17\1\20\2\uffff\1\12\34\uffff\1\11"+
        "\1\13\1\uffff\1\14\1\32\4\uffff\1\5\1\uffff\1\10\1\uffff\1\7\1\uffff"+
        "\1\15\2\uffff\1\4\1\6\1\21\1\22\1\16\4\uffff\1\23";
    static final String DFA9_specialS =
        "\144\uffff}>";
    static final String[] DFA9_transitionS = {
            "\2\27\2\uffff\1\27\22\uffff\1\27\2\uffff\1\26\1\3\2\uffff\1"+
            "\25\6\uffff\1\22\1\uffff\12\24\2\uffff\1\17\1\1\3\uffff\32\23"+
            "\4\uffff\1\23\1\uffff\1\16\3\23\1\7\2\23\1\13\1\4\3\23\1\5\1"+
            "\11\4\23\1\6\1\10\2\23\1\12\1\14\1\15\1\23\1\20\1\2\1\21",
            "\1\31\1\30",
            "",
            "",
            "\1\34\10\uffff\1\33",
            "\1\35",
            "\1\36\6\uffff\1\37",
            "\1\40",
            "\1\42\27\uffff\1\41",
            "\1\43\23\uffff\1\44",
            "\1\45",
            "\1\46",
            "\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23",
            "\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23",
            "\1\51",
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
            "\1\52",
            "\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23",
            "\1\54",
            "\1\55",
            "\1\56",
            "\1\57",
            "\1\60",
            "\1\61",
            "\1\62",
            "\1\63",
            "\1\64",
            "\1\65",
            "",
            "",
            "\1\66",
            "\1\67",
            "",
            "\1\70",
            "\1\71",
            "\1\72",
            "\1\73",
            "\1\74",
            "\1\75",
            "\1\76",
            "\1\77",
            "\1\100",
            "\1\101",
            "\1\102",
            "\1\103",
            "\1\104",
            "\1\105",
            "\1\106",
            "\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\22\23\1\107\7\23",
            "\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23",
            "\1\112",
            "\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23",
            "\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23",
            "\1\115",
            "\1\116",
            "\1\117",
            "\1\120",
            "\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23",
            "\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\22\23\1\122\7\23",
            "\1\124",
            "\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23",
            "",
            "",
            "\1\126",
            "",
            "",
            "\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23",
            "\1\130",
            "\1\131",
            "\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23",
            "",
            "\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23",
            "",
            "\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23",
            "",
            "\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23",
            "",
            "\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23",
            "\1\137",
            "",
            "",
            "",
            "",
            "",
            "\1\140",
            "\1\141",
            "\1\142",
            "\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23",
            ""
    };

    static final short[] DFA9_eot = DFA.unpackEncodedString(DFA9_eotS);
    static final short[] DFA9_eof = DFA.unpackEncodedString(DFA9_eofS);
    static final char[] DFA9_min = DFA.unpackEncodedStringToUnsignedChars(DFA9_minS);
    static final char[] DFA9_max = DFA.unpackEncodedStringToUnsignedChars(DFA9_maxS);
    static final short[] DFA9_accept = DFA.unpackEncodedString(DFA9_acceptS);
    static final short[] DFA9_special = DFA.unpackEncodedString(DFA9_specialS);
    static final short[][] DFA9_transition;

    static {
        int numStates = DFA9_transitionS.length;
        DFA9_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA9_transition[i] = DFA.unpackEncodedString(DFA9_transitionS[i]);
        }
    }

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = DFA9_eot;
            this.eof = DFA9_eof;
            this.min = DFA9_min;
            this.max = DFA9_max;
            this.accept = DFA9_accept;
            this.special = DFA9_special;
            this.transition = DFA9_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__37 | T__38 | XPATH | IMPORT | MODEL | SHAPES | EDGES | SHAPE | EDGE | ID | TYPE | NAME | WIDTH | HEIGHT | X | Y | SOURCE | TARGET | ATTRIBUTES | EQ | DEQ | IIF | LBRACE | RBRACE | DOT | NULL | IDENTIFIER | INT | STRING | COMMENT | WS );";
        }
    }
 

}
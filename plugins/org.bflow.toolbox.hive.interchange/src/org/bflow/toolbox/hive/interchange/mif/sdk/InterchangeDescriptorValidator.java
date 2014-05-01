package org.bflow.toolbox.hive.interchange.mif.sdk;

import java.io.IOException;
import java.io.InputStream;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.apache.commons.io.IOUtils;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeDescriptor;
import org.bflow.toolbox.hive.interchange.mif.core.InterchangeValidationException;
import org.bflow.toolbox.hive.interchange.mif.grammar.InterchangeGrammarLexer;
import org.bflow.toolbox.hive.interchange.mif.grammar.InterchangeGrammarParser;

/**
 * Implements a validator for Interchange Descriptors.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 05/10/12
 */
public class InterchangeDescriptorValidator {
	
	/**
	 * Validates the given input stream.
	 * 
	 * @param inputStream
	 *            the input stream
	 * @return the validated export descriptor
	 * @throws InterchangeValidationException
	 *             Is thrown when the input stream doesn't contain a valid descriptor
	 */
	public static IInterchangeDescriptor validate(InputStream inputStream) throws InterchangeValidationException {
		if(inputStream == null) {
			throw new InterchangeValidationException("Input Stream is null!");
		}
		
		String input = null;
		
		try {
			input = IOUtils.toString(inputStream);
		} catch (IOException ex) {
			throw new InterchangeValidationException("Could not read the input stream", ex);
		}
		
		if(input == null || input.isEmpty()) {
			throw new InterchangeValidationException("Input from stream is null or empty!");
		}
		
		ANTLRStringStream stream = new ANTLRStringStream(input);
		
		InterchangeGrammarLexer lex = new InterchangeGrammarLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lex);

        InterchangeGrammarParser g = new InterchangeGrammarParser(tokens);
        IInterchangeDescriptor interchangeDescriptor = null;
        
        try {
            interchangeDescriptor = g.head();      
        } catch (RecognitionException e) {
            throw new InterchangeValidationException(e.getMessage());
        }
        
        return interchangeDescriptor;
	}

}

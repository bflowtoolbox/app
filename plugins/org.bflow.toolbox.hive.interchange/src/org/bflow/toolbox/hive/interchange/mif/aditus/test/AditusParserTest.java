package org.bflow.toolbox.hive.interchange.mif.aditus.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.bflow.toolbox.hive.interchange.mif.aditus.AditusMetaInfo;
import org.bflow.toolbox.hive.interchange.mif.aditus.AditusProcessor;
import org.bflow.toolbox.hive.interchange.mif.aditus.AditusProcessorException;
import org.bflow.toolbox.hive.interchange.mif.core.IModelData;
import org.bflow.toolbox.hive.interchange.mif.grammar.AditusGrammarLexer;
import org.bflow.toolbox.hive.interchange.mif.grammar.AditusGrammarParser;
import org.junit.Test;

public class AditusParserTest {
	
	static final String TestContent = 
			"" +
			"# This is a test script for the aditus parser\r\n" + 
			"# The script is aligned for an epml import \r\n" + 
			"\r\n" + 
			"# Defines some classpath imports\r\n" + 
			"import abc.def.ClassX\r\n" + 
			"import abc.def.ClassY\r\n" + 
			"\r\n" + 
			"# Define the model informations\r\n" + 
			"model = {\r\n" + 
			"id = $//directory/epc/@epcId$\r\n" + 
			"name = $/go$\r\n" + 
			"attributes = $//go$ => $/go$\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"# Definition der Events\r\n" + 
			"shapes = $//directory/epc/event$ {\r\n" + 
			"id = $/@id$\r\n" + 
			"type = 'org.bflow.toolbox.epc.diagram.Event_2006'\r\n" + 
			"name = $/name/text()$\r\n" + 
			"width  = $/graphics/@width$ | 32\r\n" + 
			"height = $/grapics/@height$ | 32\r\n" + 
			"x = $/graphics/@x$ | 0\r\n" + 
			"y = $/graphics/@y$ | 0\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"# Definition der Funktionen\r\n" + 
			"shapes = $//directory/epc/function$ {\r\n" + 
			"id = $/@id$\r\n" +
			"type = 'org.bflow.toolbox.epc.diagram.Function_2007'" + 
			"name = $/name/text()$\r\n" +
			"attributes = 'Version' => '1.0'\r\n" + 
			"width  = $/graphics/@width$ | 32\r\n" + 
			"height = $/grapics/@height$ | 32\r\n" + 
			"x = $/graphics/@x$ | 0\r\n" + 
			"y = $/graphics/@y$ | 0\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			"# Definition der Kanten\r\n" + 
			"edges = $//directory/epc/arc$ {\r\n" + 
			"source = shape <=> shape.id == $/flow/@source$\r\n" + 
			"target = edge <=> edge.id == $/flow/@target$\r\n" + 
			"}" +
			"";

	@Test
	public void testAditusGrammarParser() {
		String fileContent = TestContent;
		
		ANTLRStringStream stream = new ANTLRStringStream(fileContent);
		
		AditusGrammarLexer lex = new AditusGrammarLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lex);

        AditusGrammarParser g = new AditusGrammarParser(tokens);
        AditusMetaInfo aditusMetaInfo = null;
        
        try {
            aditusMetaInfo = g.aditus();   
        } catch (RecognitionException e) {
            fail(e.getMessage());
        }
		
        assertNotNull("AditusMetaInfo is null", aditusMetaInfo);
        
        // Assert imports
        assertEquals("Wrong count of import statements", 2, aditusMetaInfo.Imports.size());
        assertEquals("1st import statement is wrong", "abc.def.ClassX", aditusMetaInfo.Imports.get(0));
        assertEquals("2nd import statement is wrong", "abc.def.ClassY", aditusMetaInfo.Imports.get(1));
        
        // Assert model definition
        assertNotNull("AditusModelMetaInfo is null", aditusMetaInfo.Model);
        assertEquals("Wrong AditusModelMetaInfo.Id", "//directory/epc/@epcId", aditusMetaInfo.Model.Id);
        assertEquals("Wrong AditusModelMetaInfo.Name", "/go", aditusMetaInfo.Model.Name);
        assertEquals("Wrong AditusModelMetaInfo.Attributes", "//go => /go", aditusMetaInfo.Model.Attributes);
        
        // Assert shape definitions
        assertNotNull("AditusShapeMetaInfo is null", aditusMetaInfo.Shapes);
        assertEquals("Wrong count of shape meta infos", 2, aditusMetaInfo.Shapes.size());
        
        // Assert shape definition for events
        assertEquals("Wrong Shape[0].Id", "/@id", aditusMetaInfo.Shapes.get(0).Id);
        assertEquals("Wrong Shape[0].Node", "//directory/epc/event", aditusMetaInfo.Shapes.get(0).Node);
        assertEquals("Wrong Shape[0].Type", "org.bflow.toolbox.epc.diagram.Event_2006", aditusMetaInfo.Shapes.get(0).Type);
        assertEquals("Wrong Shape[0].Name", "/name/text()", aditusMetaInfo.Shapes.get(0).Name);
        assertEquals("Wrong Shape[0].Width", "/graphics/@width | 32", aditusMetaInfo.Shapes.get(0).Width);
        assertEquals("Wrong Shape[0].Height", "/grapics/@height | 32", aditusMetaInfo.Shapes.get(0).Height);
        assertEquals("Wrong Shape[0].X", "/graphics/@x | 0", aditusMetaInfo.Shapes.get(0).X);
        assertEquals("Wrong Shape[0].Y", "/graphics/@y | 0", aditusMetaInfo.Shapes.get(0).Y);
        
        // Assert shape definition for functions
        assertEquals("Wrong Shape[1].Id", "/@id", aditusMetaInfo.Shapes.get(1).Id);
        assertEquals("Wrong Shape[1].Node", "//directory/epc/function", aditusMetaInfo.Shapes.get(1).Node);
        assertEquals("Wrong Shape[1].Type", "org.bflow.toolbox.epc.diagram.Function_2007", aditusMetaInfo.Shapes.get(1).Type);
        assertEquals("Wrong Shape[1].Name", "/name/text()", aditusMetaInfo.Shapes.get(1).Name);
        assertEquals("Wrong Shape[1].Attributes", "Version => 1.0", aditusMetaInfo.Shapes.get(1).Attributes);
        assertEquals("Wrong Shape[1].Width", "/graphics/@width | 32", aditusMetaInfo.Shapes.get(1).Width);
        assertEquals("Wrong Shape[1].Height", "/grapics/@height | 32", aditusMetaInfo.Shapes.get(1).Height);
        assertEquals("Wrong Shape[1].X", "/graphics/@x | 0", aditusMetaInfo.Shapes.get(1).X);
        assertEquals("Wrong Shape[1].Y", "/graphics/@y | 0", aditusMetaInfo.Shapes.get(1).Y);
        
        // Assert edge definitions
        assertNotNull("AditusEdgeMetaInfo is null", aditusMetaInfo.Edges);
        assertEquals("Wrong count of edge meta infos", 1, aditusMetaInfo.Edges.size());
        
        assertEquals("Wrong Edges[0].Source", "shape <=> shape.id == /flow/@source", aditusMetaInfo.Edges.get(0).Source);
        assertEquals("Wrong Edges[0].Target", "edge <=> edge.id == /flow/@target", aditusMetaInfo.Edges.get(0).Target);
	}
	
	@Test
	public void testEPMLTransformation() {
		InputStream importScriptStream = getClass().getResourceAsStream("/org/bflow/toolbox/interchange/mif/aditus/test/epml.ads");
		InputStream sourceFileStream = getClass().getResourceAsStream("/org/bflow/toolbox/interchange/mif/aditus/test/Projektantrag_stellen.xml.epml");
		
        AditusMetaInfo aditusMetaInfo = null;
        
        try {
        	ANTLRInputStream stream = new ANTLRInputStream(importScriptStream);
    		AditusGrammarLexer lex = new AditusGrammarLexer(stream);
            CommonTokenStream tokens = new CommonTokenStream(lex);
            AditusGrammarParser g = new AditusGrammarParser(tokens);
            aditusMetaInfo = g.aditus();   
            importScriptStream.close();
        } catch (Exception e) {
            fail(e.getMessage());
        }
        
        AditusProcessor aditusProcessor = new AditusProcessor(aditusMetaInfo);        
        IModelData modelData = null;
		try {
			modelData = aditusProcessor.getModelData(sourceFileStream);
		} catch (AditusProcessorException ex) {
			fail(ex.getMessage());
		}
        try {
			sourceFileStream.close();
		} catch (IOException e) {
			fail(e.getMessage());
		}
        
        // Assertions
        assertNotNull("Model data is null", modelData);

        // Assert for IModel
        assertNotNull("IModel is null", modelData.getModel());
        assertEquals("Wrong Model.Id", "1", modelData.getModel().getId());
        assertEquals("Wrong count of Model.Attributes", 6, modelData.getModel().getAttributes().size());
        
        // Assert for IShapes
        assertNotNull("IShapes is null", modelData.getShapes());
        assertEquals("Wrong count of Shapes", 12, modelData.getShapes().length);
        
        // Assert for IEdges
        assertNotNull("IEdges is null", modelData.getEdges());
        assertEquals("Wrong count of edges", 11, modelData.getEdges().length);
        
        for(int i = 0 ; i < modelData.getEdges().length; i++) {
        	assertNotNull("Source of edge is null", modelData.getEdges()[i].getSource());
        	assertNotNull("Target of edge is null", modelData.getEdges()[i].getTarget());
        }
	}
	
	@Test
	public void testAMLTransformation() {
		InputStream importScriptStream = getClass().getResourceAsStream("/org/bflow/toolbox/interchange/mif/aditus/test/aris_aml.ads");
		InputStream sourceFileStream = getClass().getResourceAsStream("/org/bflow/toolbox/interchange/mif/aditus/test/Entangled_XOR_AND_connectors.xml.aml");
		
        AditusMetaInfo aditusMetaInfo = null;
        
        try {
        	ANTLRInputStream stream = new ANTLRInputStream(importScriptStream);
    		AditusGrammarLexer lex = new AditusGrammarLexer(stream);
            CommonTokenStream tokens = new CommonTokenStream(lex);
            AditusGrammarParser g = new AditusGrammarParser(tokens);
            aditusMetaInfo = g.aditus();   
            importScriptStream.close();
        } catch (Exception e) {
            fail(e.getMessage());
        }
        
        AditusProcessor aditusProcessor = new AditusProcessor(aditusMetaInfo);        
        IModelData modelData = null;
		try {
			modelData = aditusProcessor.getModelData(sourceFileStream);
		} catch (AditusProcessorException ex) {
			fail(ex.getMessage());
		}
        try {
			sourceFileStream.close();
		} catch (IOException e) {
			fail(e.getMessage());
		}
        
        assertNotNull(modelData);
	}

}

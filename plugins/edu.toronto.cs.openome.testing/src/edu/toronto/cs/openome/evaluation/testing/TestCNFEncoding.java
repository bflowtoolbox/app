/**
 * 
 */
package edu.toronto.cs.openome.evaluation.testing;


import static org.junit.Assert.assertTrue;

import org.junit.Test;
import edu.toronto.cs.openome.evaluation.SATSolver.Dimacs;
import edu.toronto.cs.openome.evaluation.SATSolver.ModeltoAxiomsConverter;
import edu.toronto.cs.openome.evaluation.SATSolver.zChaffSolver;
import edu.toronto.cs.openome_model.impl.ModelImpl;
import org.sat4j.core.VecInt;

/**
 * @author jenhork
 *
 */
public class TestCNFEncoding extends BackwardEvaluationTest{

	static int numConstraintClauses = 12;
	
	@Test
	public void testDependency() {
		initializeDependencyModel();
		int numInts = 2;
		int [] leaves = {7};
		
		ModeltoAxiomsConverter converter = new ModeltoAxiomsConverter((ModelImpl) model, cs);
		
		Dimacs cnf;
		cnf = converter.convertBothDirections("testDimacs.cnf");
		
		cnf.writeToFile("testingdimacs.cnf");
				
		assertTrue(cnf.getNumVariables() == 12);
		assertTrue(cnf.getNumClauses() == (12 + (numInts*2) + (leaves.length*numConstraintClauses) ));
		
		testSolvable(cnf);
		
		testInvariantAxioms(cnf, numInts);
		testConstraintAxioms(cnf, leaves);
		
		VecInt vi = new VecInt();
		vi.push(-7);
		vi.push(1);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-8);
		vi.push(2);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-9);
		vi.push(3);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-10);
		vi.push(4);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-11);
		vi.push(5);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-12);
		vi.push(6);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		
		vi = new VecInt();
		vi.push(-1);
		vi.push(7);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-2);
		vi.push(8);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-3);
		vi.push(9);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-4);
		vi.push(10);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-5);
		vi.push(11);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-6);
		vi.push(12);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		
		cnf = converter.convertForward("testDimacs.cnf");
		assertTrue(cnf.getNumVariables() == 12);
		assertTrue(cnf.getNumClauses() == (6 + (numInts*2) + (leaves.length*numConstraintClauses) ));
		
		testSolvable(cnf);
		
		testInvariantAxioms(cnf, numInts);
		testConstraintAxioms(cnf, leaves);
		
		vi = new VecInt();
		vi.push(-7);
		vi.push(1);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-8);
		vi.push(2);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-9);
		vi.push(3);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-10);
		vi.push(4);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-11);
		vi.push(5);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-12);
		vi.push(6);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		
		
		cnf = converter.convertBackward("testDimacs.cnf");
		assertTrue(cnf.getNumVariables() == 12);
		assertTrue(cnf.getNumClauses() == (6 + (numInts*2) + (leaves.length*numConstraintClauses) ));
		
		testSolvable(cnf);
		
		testInvariantAxioms(cnf, numInts);
		testConstraintAxioms(cnf, leaves);
		
		vi = new VecInt();
		vi.push(-1);
		vi.push(7);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-2);
		vi.push(8);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-3);
		vi.push(9);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-4);
		vi.push(10);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-5);
		vi.push(11);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-6);
		vi.push(12);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		
	}
	
	@Test
	public void testAndDecomposition() {
		initializeAndDecompositionModel();
		int numInts = 3;
		int [] leaves = {7, 13};
		
		ModeltoAxiomsConverter converter = new ModeltoAxiomsConverter((ModelImpl) model, cs);		
		
		Dimacs cnf;
		cnf = converter.convertForward("testDimacs.cnf");
		
		cnf.writeToFile("testingdimacs.cnf");
		
		assertTrue(cnf.getNumVariables() == 18);
		assertTrue(cnf.getNumClauses() == (12 + (numInts*2) + (leaves.length*numConstraintClauses) ));
		
		testSolvable(cnf);
		
		testInvariantAxioms(cnf, numInts);
		testConstraintAxioms(cnf, leaves);
		
		VecInt vi = new VecInt();
		vi.push(-7);
		vi.push(-13);
		vi.push(1);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-8);
		vi.push(-14);
		vi.push(2);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-9);
		vi.push(-14);
		vi.push(3);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-15);
		vi.push(-8);
		vi.push(3);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-15);
		vi.push(-9);
		vi.push(3);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-10);
		vi.push(17);
		vi.push(4);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-16);
		vi.push(11);
		vi.push(4);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-10);
		vi.push(16);
		vi.push(4);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-10);
		vi.push(16);
		vi.push(4);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-17);
		vi.push(5);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-11);
		vi.push(5);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-18);
		vi.push(6);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-12);
		vi.push(6);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));	
		
		cnf = converter.convertBackward("testDimacs.cnf");
		
		cnf.writeToFile("testingdimacs.cnf");
		
		//System.out.println(model.getIntentions().size());
		assertTrue(cnf.getNumVariables() == 18);
		assertTrue(cnf.getNumClauses() == (22+ (numInts*2) + (leaves.length*numConstraintClauses) ));
		
		testSolvable(cnf);
		
		testInvariantAxioms(cnf, numInts);
		testConstraintAxioms(cnf, leaves);
		
		vi = new VecInt();
		vi.push(-1);
		vi.push(7);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-1);
		vi.push(13);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-2);
		vi.push(8);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-2);
		vi.push(14);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-3);
		vi.push(15);
		vi.push(9);
		vi.push(15);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-3);
		vi.push(15);
		vi.push(9);
		vi.push(9);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-3);
		vi.push(15);
		vi.push(14);
		vi.push(15);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-3);
		vi.push(15);
		vi.push(14);
		vi.push(9);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-3);
		vi.push(8);
		vi.push(9);
		vi.push(15);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-3);
		vi.push(8);
		vi.push(9);
		vi.push(9);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-3);
		vi.push(8);
		vi.push(14);
		vi.push(15);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-3);
		vi.push(8);
		vi.push(14);
		vi.push(9);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-4);
		vi.push(10);
		vi.push(16);
		vi.push(10);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-4);
		vi.push(10);
		vi.push(16);
		vi.push(16);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-4);
		vi.push(10);
		vi.push(-11);
		vi.push(10);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-4);
		vi.push(-17);
		vi.push(-11);
		vi.push(16);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-4);
		vi.push(-17);
		vi.push(16);
		vi.push(10);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-4);
		vi.push(-17);
		vi.push(16);
		vi.push(16);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-4);
		vi.push(-17);
		vi.push(-11);
		vi.push(10);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-4);
		vi.push(-17);
		vi.push(-11);
		vi.push(16);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-5);
		vi.push(11);
		vi.push(17);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));		
		vi = new VecInt();
		vi.push(-6);
		vi.push(12);
		vi.push(18);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		
		cnf = converter.convertBothDirections("testDimacs.cnf");
		
		assertTrue(cnf.getNumVariables() == 18);
		assertTrue(cnf.getNumClauses() == (34 + (numInts*2) + (leaves.length*numConstraintClauses)));
		
		testSolvable(cnf);
		
		testInvariantAxioms(cnf, numInts);
		testConstraintAxioms(cnf, leaves); 
	}
	
	@Test
	public void testAndDecomposition2() {
		initializeAndDecompositionModel2();
		int numInts = 4;
		int [] leaves = {7, 13, 19};
		
		ModeltoAxiomsConverter converter = new ModeltoAxiomsConverter((ModelImpl) model, cs);		
		
		Dimacs cnf;
		cnf = converter.convertForward("testDimacs.cnf"); 
		
		cnf.writeToFile("testingdimacs.cnf");		
		
		assertTrue(cnf.getNumVariables() == 24);
		assertTrue(cnf.getNumClauses() == (22+ (numInts*2) + (leaves.length*numConstraintClauses)));
		
		testSolvable(cnf);
		
		testInvariantAxioms(cnf, numInts);
		testConstraintAxioms(cnf, leaves); 
		
		VecInt vi = new VecInt();
		vi.push(-7);
		vi.push(-13);
		vi.push(-19);
		vi.push(1);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-8);
		vi.push(-14);
		vi.push(-20);
		vi.push(2);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		
		vi = new VecInt();
		vi.push(-9);
		vi.push(-14);
		vi.push(-20);
		vi.push(3);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-9);
		vi.push(-14);
		vi.push(-21);
		vi.push(3);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-9);
		vi.push(-15);
		vi.push(-20);
		vi.push(3);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-9);
		vi.push(-15);
		vi.push(-21);
		vi.push(3);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-8);
		vi.push(-14);
		vi.push(-21);
		vi.push(3);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-8);
		vi.push(-15);
		vi.push(-20);
		vi.push(3);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-8);
		vi.push(-15);
		vi.push(-21);
		vi.push(3);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		
		vi = new VecInt();
		vi.push(-10);
		vi.push(17);
		vi.push(23);
		vi.push(4);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-10);
		vi.push(17);
		vi.push(-22);
		vi.push(4);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-10);
		vi.push(-16);
		vi.push(23);
		vi.push(4);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-10);
		vi.push(-16);
		vi.push(-22);
		vi.push(4);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(11);
		vi.push(17);
		vi.push(23);
		vi.push(4);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(11);
		vi.push(17);
		vi.push(-22);
		vi.push(4);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(11);
		vi.push(-16);
		vi.push(23);
		vi.push(4);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));		
		
		vi = new VecInt();
		vi.push(-17);
		vi.push(5);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-11);
		vi.push(5);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-23);
		vi.push(5);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-18);
		vi.push(6);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-12);
		vi.push(6);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-24);
		vi.push(6);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		
		cnf = converter.convertBackward("testDimacs.cnf"); 
		
		cnf.writeToFile("testingdimacs.cnf");
		
		
		assertTrue(cnf.getNumVariables() == 24);
		assertTrue(cnf.getNumClauses() == (4382+(numInts*2) + (leaves.length*numConstraintClauses)));
		
		testSolvable(cnf);
		
		testInvariantAxioms(cnf, numInts);
		testConstraintAxioms(cnf, leaves); 	
		
		cnf = converter.convertBothDirections("testDimacs.cnf"); 
		
		cnf.writeToFile("testingdimacs.cnf");
		
		
		assertTrue(cnf.getNumVariables() == 24);
		assertTrue(cnf.getNumClauses() == (4404 + (numInts*2) + (leaves.length*numConstraintClauses)));
		
		testSolvable(cnf);
		testInvariantAxioms(cnf, numInts);
		testConstraintAxioms(cnf, leaves); 
	}
	
	@Test
	public void testOrDecomposition() {
		initializeOrDecompositionModel();
		int numInts = 3;
		int [] leaves = {7, 13};
		
		ModeltoAxiomsConverter converter = new ModeltoAxiomsConverter((ModelImpl) model, cs);		
				
		Dimacs cnf;
		cnf = converter.convertForward("testDimacs.cnf");
		
		cnf.writeToFile("testingdimacs.cnf");		
		
		assertTrue(cnf.getNumVariables() == 18);
		assertTrue(cnf.getNumClauses() == (12 + (numInts*2) + (leaves.length*numConstraintClauses)));
		
		testSolvable(cnf);
		
		testInvariantAxioms(cnf, numInts);
		testConstraintAxioms(cnf, leaves); 
		
		VecInt vi = new VecInt();
		vi.push(-7);
		vi.push(1);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-13);
		vi.push(1);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-8);
		vi.push(2);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-14);
		vi.push(2);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-9);
		vi.push(14);
		vi.push(3);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-15);
		vi.push(8);
		vi.push(3);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-15);
		vi.push(-9);
		vi.push(3);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-10);
		vi.push(-17);
		vi.push(4);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-16);
		vi.push(-11);
		vi.push(4);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-10);
		vi.push(-16);
		vi.push(4);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-10);
		vi.push(16);
		vi.push(4);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-17);
		vi.push(-11);
		vi.push(5);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-18);
		vi.push(-12);
		vi.push(6);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
				
		cnf = converter.convertBackward("testDimacs.cnf");
		
		cnf.writeToFile("testingdimacs.cnf");
		
		assertTrue(cnf.getNumVariables() == 18);
		assertTrue(cnf.getNumClauses() == (22 + (numInts*2) + (leaves.length*numConstraintClauses)));
		
		testSolvable(cnf);
		
		testInvariantAxioms(cnf, numInts);
		testConstraintAxioms(cnf, leaves); 
		
		vi = new VecInt();
		vi.push(-1);
		vi.push(7);
		vi.push(13);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-2);
		vi.push(8);
		vi.push(14);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-3);
		vi.push(15);
		vi.push(9);
		vi.push(15);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-3);
		vi.push(15);
		vi.push(9);
		vi.push(9);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-3);
		vi.push(15);
		vi.push(-14);
		vi.push(15);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-3);
		vi.push(15);
		vi.push(-14);
		vi.push(9);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-3);
		vi.push(-8);
		vi.push(9);
		vi.push(15);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-3);
		vi.push(-8);
		vi.push(9);
		vi.push(9);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-3);
		vi.push(-8);
		vi.push(-14);
		vi.push(15);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-3);
		vi.push(-8);
		vi.push(-14);
		vi.push(9);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-4);
		vi.push(10);
		vi.push(16);
		vi.push(10);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-4);
		vi.push(10);
		vi.push(16);
		vi.push(16);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-4);
		vi.push(10);
		vi.push(11);
		vi.push(10);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-4);
		vi.push(17);
		vi.push(11);
		vi.push(16);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-4);
		vi.push(17);
		vi.push(16);
		vi.push(10);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-4);
		vi.push(17);
		vi.push(16);
		vi.push(16);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-4);
		vi.push(17);
		vi.push(11);
		vi.push(10);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-4);
		vi.push(17);
		vi.push(11);
		vi.push(16);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-5);
		vi.push(11);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));	
		vi = new VecInt();
		vi.push(-5);
		vi.push(17);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));	
		vi = new VecInt();
		vi.push(-6);
		vi.push(12);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));	
		vi = new VecInt();
		vi.push(-6);
		vi.push(18);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		
		cnf = converter.convertBothDirections("testDimacs.cnf");
		
		cnf.writeToFile("testingdimacs.cnf");
		
		assertTrue(cnf.getNumVariables() == 18);
		assertTrue(cnf.getNumClauses() == (34 + (numInts*2) + (leaves.length*numConstraintClauses)));
		
		testSolvable(cnf);
		
		testInvariantAxioms(cnf, numInts);
		testConstraintAxioms(cnf, leaves); 
		
	}
	
	@Test
	public void testOrDecomposition2() {
		initializeOrDecompositionModel2();
		int numInts = 4;
		int [] leaves = {7, 13, 19};
		
		ModeltoAxiomsConverter converter = new ModeltoAxiomsConverter((ModelImpl) model, cs);		
		
		Dimacs cnf;
		cnf = converter.convertForward("testDimacs.cnf"); 
		
		cnf.writeToFile("testingdimacs.cnf");
			
		assertTrue(cnf.getNumVariables() == 24);
		assertTrue(cnf.getNumClauses() == (22 + (numInts*2) + (leaves.length*numConstraintClauses)));
		
		testSolvable(cnf);
		
		testInvariantAxioms(cnf, numInts);
		testConstraintAxioms(cnf, leaves); 
		
		cnf = converter.convertBackward("testDimacs.cnf"); 
		
		cnf.writeToFile("testingdimacs.cnf");
		
		
		assertTrue(cnf.getNumVariables() == 24);
		assertTrue(cnf.getNumClauses() == (4382 + (numInts*2) + (leaves.length*numConstraintClauses)));
		
		testSolvable(cnf);
		
		testInvariantAxioms(cnf, numInts);
		testConstraintAxioms(cnf, leaves); 
		
		cnf = converter.convertBothDirections("testDimacs.cnf"); 
		
		cnf.writeToFile("testingdimacs.cnf");
		
		
		assertTrue(cnf.getNumVariables() == 24);
		assertTrue(cnf.getNumClauses() == (4404+(numInts*2) + (leaves.length*numConstraintClauses)));
		
		testSolvable(cnf);
		
		testInvariantAxioms(cnf, numInts);
		testConstraintAxioms(cnf, leaves); 
	}

	@Test
	public void testContributions1() {
		initializeContributionModel();
		int numInts = 2;
		int [] leaves = {7};
		
		ModeltoAxiomsConverter converter = new ModeltoAxiomsConverter((ModelImpl) model, cs);		
		
		Dimacs cnf;
		cnf = converter.convertForward("testDimacs.cnf"); 
		
		cnf.writeToFile("testingdimacs.cnf");		
		
		assertTrue(cnf.getNumVariables() == 12);
		assertTrue(cnf.getNumClauses() == (33+(numInts*2) + (leaves.length*numConstraintClauses)));
		
		testSolvable(cnf);
		
		testInvariantAxioms(cnf, numInts);
		testConstraintAxioms(cnf, leaves); 
		
		//Added by all but unknown link
		VecInt vi;
		vi = new VecInt();
		vi.push(-9);
		vi.push(3);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));	
		vi = new VecInt();
		vi.push(-10);
		vi.push(4);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));	
		//Make
		vi = new VecInt();
		vi.push(-7);
		vi.push(1);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));	
		vi = new VecInt();
		vi.push(-8);
		vi.push(2);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));	
		vi = new VecInt();
		vi.push(-11);
		vi.push(5);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));	
		vi = new VecInt();
		vi.push(-12);
		vi.push(6);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		//Help
		vi = new VecInt();
		vi.push(-8);
		vi.push(2);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));	
		vi = new VecInt();
		vi.push(-9);
		vi.push(3);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));	
		vi = new VecInt();
		vi.push(-10);
		vi.push(4);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));	
		vi = new VecInt();
		vi.push(-11);
		vi.push(5);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		//Break
		vi = new VecInt();
		vi.push(-7);
		vi.push(6);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));	
		vi = new VecInt();
		vi.push(-8);
		vi.push(5);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));	
		vi = new VecInt();
		vi.push(-11);
		vi.push(2);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		//hurt
		vi = new VecInt();
		vi.push(-8);
		vi.push(5);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));	
		vi = new VecInt();
		vi.push(-11);
		vi.push(2);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		//Unknown
		vi = new VecInt();
		vi.push(-7);
		vi.push(3);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));	
		vi = new VecInt();
		vi.push(-8);
		vi.push(3);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-9);
		vi.push(3);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));	
		vi = new VecInt();
		vi.push(-10);
		vi.push(3);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));
		vi = new VecInt();
		vi.push(-11);
		vi.push(3);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));	
		vi = new VecInt();
		vi.push(-12);
		vi.push(3);
		vi.push(0);
		assertTrue(cnf.containsForward(vi));	
		
		cnf = converter.convertBackward("testDimacs.cnf"); 
		
		cnf.writeToFile("testingdimacs.cnf");
				
		assertTrue(cnf.getNumVariables() == 12);
		//assertTrue(cnf.getNumClauses() == (19+(numInts*2) + (leaves.length*numConstraintClauses)));
		
		testSolvable(cnf);
		
		testInvariantAxioms(cnf, numInts);
		testConstraintAxioms(cnf, leaves); 
		
		//VecInt vi;
		//Added by all but unknown link
		/*vi = new VecInt();
		vi.push(-3);
		vi.push(9);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));	
		vi = new VecInt();
		vi.push(-4);
		vi.push(10);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		//Make
		vi = new VecInt();
		vi.push(-1);
		vi.push(7);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));	
		vi = new VecInt();
		vi.push(-2);
		vi.push(8);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));	
		vi = new VecInt();
		vi.push(-5);
		vi.push(11);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));	
		vi = new VecInt();
		vi.push(-6);
		vi.push(12);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		//Help
		vi = new VecInt();
		vi.push(-2);
		vi.push(7);
		vi.push(8);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));	
		vi = new VecInt();
		vi.push(-3);
		vi.push(9);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));	
		vi = new VecInt();
		vi.push(-4);
		vi.push(10);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));	
		vi = new VecInt();
		vi.push(-5);
		vi.push(11);
		vi.push(12);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		//Break
		vi = new VecInt();
		vi.push(-1);
		vi.push(11);
		vi.push(12);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));	
		vi = new VecInt();
		vi.push(-2);
		vi.push(11);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));	
		vi = new VecInt();
		vi.push(-5);
		vi.push(8);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		vi = new VecInt();
		vi.push(-6);
		vi.push(7);
		vi.push(8);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		//hurt
		vi = new VecInt();
		vi.push(-2);
		vi.push(11);
		vi.push(12);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));	
		vi = new VecInt();
		vi.push(-5);
		vi.push(7);
		vi.push(8);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));
		//Unknown
		vi = new VecInt();
		vi.push(-3);
		vi.push(7);
		vi.push(8);
		vi.push(9);
		vi.push(10);
		vi.push(11);
		vi.push(12);
		vi.push(0);
		assertTrue(cnf.containsBackward(vi));	*/
		
		cnf = converter.convertBothDirections("testDimacs.cnf"); 
		
		cnf.writeToFile("testingdimacs.cnf");		
		
		assertTrue(cnf.getNumVariables() == 12);
		//assertTrue(cnf.getNumClauses() == (42+(numInts*2) +(leaves.length*numConstraintClauses)));
		
		testSolvable(cnf);
		
		testInvariantAxioms(cnf, numInts);
		testConstraintAxioms(cnf, leaves); 
		
	}
	
	@Test
	public void testContributions2() {
		initializeContributionModel2();
		int numInts = 2;
		int [] leaves = {7, 13};//, 19, 25, 31, 37, 43};
		
		ModeltoAxiomsConverter converter = new ModeltoAxiomsConverter((ModelImpl) model, cs);		
		
		Dimacs cnf;
		cnf = converter.convertForward("testDimacs.cnf"); 
		
		cnf.writeToFile("testingdimacs.cnf");		
		
		//assertTrue(cnf.getNumVariables() == 48);
		//assertTrue(cnf.getNumClauses() == (33+(numInts*2) + (leaves.length*numConstraintClauses)));
		
		testSolvable(cnf);
		
		testInvariantAxioms(cnf, numInts);
		testConstraintAxioms(cnf, leaves); 
		
		cnf = converter.convertBackward("testDimacs.cnf"); 
		
		cnf.writeToFile("testingdimacs.cnf");		
		
		//assertTrue(cnf.getNumVariables() == 48);
		//assertTrue(cnf.getNumClauses() == (23+(numInts*2) + (leaves.length*numConstraintClauses)));
		
		testSolvable(cnf);
		
		testInvariantAxioms(cnf, numInts);
		testConstraintAxioms(cnf, leaves); 
	}
	
	public void testInvariantAxioms(Dimacs cnf, int numInts) {
		
		VecInt vi;
		
		for (int i = 1; i < numInts*6; i=i+6) {
			vi = new VecInt();
			vi.push(-i);
			vi.push(i+1);
			vi.push(0);
			assertTrue(cnf.contains(vi));
			vi = new VecInt();
			vi.push(-(i+5));
			vi.push(i+4);
			vi.push(0);
			assertTrue(cnf.contains(vi));
		}
	}
	
	public void testConstraintAxioms(Dimacs cnf, int [] leaves) {
		
		VecInt vi;
		
		for (int i = 0; i < leaves.length; i++) {
			vi = new VecInt();
			vi.push(-(leaves[i]+1));
			vi.push(-(leaves[i]+2));			
			vi.push(0);
			assertTrue(cnf.contains(vi));
			vi = new VecInt();
			vi.push(-(leaves[i]+1));
			vi.push(-(leaves[i]+3));
			vi.push(0);
			assertTrue(cnf.contains(vi));
			vi = new VecInt();
			vi.push(-(leaves[i]+1));			
			vi.push(-(leaves[i]+4));
			vi.push(0);
			assertTrue(cnf.contains(vi));
			
			vi = new VecInt();
			vi.push(-(leaves[i]+4));
			vi.push(-(leaves[i]+1));			
			vi.push(0);
			assertTrue(cnf.contains(vi));
			vi = new VecInt();
			vi.push(-(leaves[i]+4));
			vi.push(-(leaves[i]+2));
			vi.push(0);
			assertTrue(cnf.contains(vi));
			vi = new VecInt();
			vi.push(-(leaves[i]+4));
			vi.push(-(leaves[i]+3));
			vi.push(0);
			assertTrue(cnf.contains(vi));
			
			vi = new VecInt();
			vi.push(-(leaves[i]+2));
			vi.push(-(leaves[i]+1));			
			vi.push(0);
			assertTrue(cnf.contains(vi));
			vi = new VecInt();
			vi.push(-(leaves[i]+2));
			vi.push(-(leaves[i]+3));
			vi.push(0);
			assertTrue(cnf.contains(vi));
			vi = new VecInt();
			vi.push(-(leaves[i]+2));
			vi.push(-(leaves[i]+4));
			vi.push(0);
			assertTrue(cnf.contains(vi));
			
			vi = new VecInt();
			vi.push(-(leaves[i]+3));
			vi.push(-(leaves[i]+1));
			vi.push(0);
			assertTrue(cnf.contains(vi));
			vi = new VecInt();
			vi.push(-(leaves[i]+3));
			vi.push(-(leaves[i]+2));
			vi.push(0);
			assertTrue(cnf.contains(vi));
			vi = new VecInt();
			vi.push(-(leaves[i]+3));
			vi.push(-(leaves[i]+4));
			vi.push(0);
			assertTrue(cnf.contains(vi));
		}
	}
	
	public void testSolvable(Dimacs cnf) {
		zChaffSolver solver = new zChaffSolver();
		
		int result = solver.solve(cnf);
		
		assertTrue(result>=0);	
	}
}

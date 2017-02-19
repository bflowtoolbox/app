package edu.toronto.cs.openome.swtbottesting;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.gmf.runtime.notation.impl.DiagramImpl;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.toronto.cs.openome_model.Model;

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestiStarMLConversion {

	private SWTBotGefEditor editor;
	private SWTBotGefEditPart mainEditPart1;
	private SWTBotGefEditPart mainEditPart2;
	private String[] fileNames = {	"Application.ood", "ClimateIntroMacKaySR.ood", "KHPExample.ood",
									"KHPSmall.ood", "MacKayIntroSR.ood",
									"Model2_GeneralChair.ood",
									"myFirst.ood", "SDM Toronto_civil_Workers_Strike.ood"};
	@Before
	public void runBeforeEverTest() {
    	TestUtil.initializeWorkspace();
        TestUtil.createAndOpenFile();
    }
    
   @After
    public void runAfterEveryTest() {
    	TestUtil.closeAndDeleteFile();
    }
	
	/**
	 * Test IStarML Conversion by opening the diagram file first, converting it to iStarML format,
	 * then convert it back to OpenOME format and compare the models.
	 */
	@Test
    public void TestIStarMLConversion() {
    	
		for (String filename : fileNames) {
			TestUtil.openFile("iStarMLTestFiles", filename);
			TestUtil.ExportDiagramTo("istarml", filename);
			editor = new SWTGefBot().gefEditor(filename);
			mainEditPart1 = editor.mainEditPart();
			TestUtil.ConvertToDiagram("istarml", filename);
			editor = new SWTGefBot().gefEditor(filename.replace(".", "2."));
			mainEditPart2 = editor.mainEditPart();
			compareDiagramFiles();
		}
    }

    private void compareDiagramFiles() {
    	
    	Model model1 = (Model) ((DiagramImpl)mainEditPart1.part().getModel()).getElement();
    	Model model2 = (Model) ((DiagramImpl)mainEditPart2.part().getModel()).getElement();

    	//Intentions
    	assertTrue("Same number of intentions", model1.getAllIntentions().size() == model2.getAllIntentions().size());
    	
    	
    	//Actors
    	assertTrue("Same number of actors", model1.getContainers().size() == model2.getContainers().size());
    	
    	//Links
    	assertTrue("Same number of contribution links", model1.getContributions().size() == model2.getContributions().size());
    	assertTrue("Same number of dependency links", model1.getDependencies().size() == model2.getDependencies().size());
    	assertTrue("Same number of decomposition links", model1.getDecompositions().size() == model2.getDecompositions().size());
    	assertTrue("Same number of associations links", model1.getAssociations().size() == model2.getAssociations().size());
    	
    	//Roots and leaves
    	assertTrue("Same number of roots", model1.getRoots().size() == model2.getRoots().size());
    	assertTrue("Same number of leaves", model1.getLeaves().size() == model2.getLeaves().size());
    	
    }
}

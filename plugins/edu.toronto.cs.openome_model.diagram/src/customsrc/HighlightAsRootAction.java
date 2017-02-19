package customsrc;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.ui.action.AbstractActionHandler;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;

import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.diagram.edit.commands.HighlightIntentionsCommand;

public class HighlightAsRootAction extends AbstractActionHandler {
    
    private String privateID = "HighlightAsRootAction"; // this is how plugin.xml recognizes us
    private String privateCommandLabel = "As Root"; //this is what the user sees in their context menu
    
    /**
     * The constructor
     */
    protected HighlightAsRootAction (IWorkbenchPage workbenchPage){
        super(workbenchPage);
        init();
    }
    
    public String getId(){
        return privateID;
    }
    
    /**
     * Initializes values for the plugins
     */
    public void init(){
        super.init();
        setId(privateID);
        setText(privateCommandLabel);
        
        refresh();
    }
    
    /**
     * This is the backbone of the context menu
     * This method contains the code that will actually be executed when user selects the context menu
     */
    protected void doRun(IProgressMonitor progressMonitor) {
        IStructuredSelection selection = getStructuredSelection();
        
        if(selection == null) {
        	return;
        }
        
        List<Intention> intentions = new LinkedList();
        
        for(Object o : selection.toArray()) {
        	GraphicalEditPart part = (GraphicalEditPart)o;
        	Intention i = (Intention)part.resolveSemanticElement();

        	intentions.add(i);
        }
        
        HighlightIntentionsCommand highlight = new HighlightIntentionsCommand(selection.toList(), intentions, "blue");

        highlight.execute();
    }
    
    public void refresh(){
        //
    }
}

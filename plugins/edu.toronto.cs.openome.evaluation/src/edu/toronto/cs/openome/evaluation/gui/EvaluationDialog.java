package edu.toronto.cs.openome.evaluation.gui;


import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.dialogs.SelectionDialog;

/**
 * A dialog that prompts for one element out of a list of elements. Uses
 * <code>IStructuredContentProvider</code> to provide the elements and
 * <code>ILabelProvider</code> to provide their labels.
 * 
 * @since 2.1
 */
public class EvaluationDialog extends SelectionDialog {
    
	private IStructuredContentProvider fEvalLabelContentProvider;
	
	private IStructuredContentProvider fLabelBagContentProvider;

    private ILabelProvider fEvalLabelLabelProvider;
    
    private ILabelProvider fLabelBagLabelProvider;

    private Object fLabelBagInput;
    
    private Object fEvalLabelInput;

    private TableViewer fTableViewer;

    private boolean fAddCancelButton = true;

    private int widthInChars = 55;

    private int viewHeightInChars = 15;
    
    private int selectHeightInChars = 7;

    /**
     * Create a new instance of the receiver with parent shell of parent.
     * @param parent
     */
    public EvaluationDialog(Shell parent) {
        super(parent);
        setShellStyle(getShellStyle() & ~SWT.APPLICATION_MODAL ); /////Remove modality from this window
    }

    /**
     * @param input The input for the list.
     */
    public void setLabelBagInput(Object input) {    	
        fLabelBagInput = input;
    }
    
    /**
     * @param input The input for the list.
     */
    public void setEvalLabelInput(Object input) {
        fEvalLabelInput = input;
    }

    /**
     * @param sp The content provider for the list.
     */
    public void setLabelBagContentProvider(IStructuredContentProvider sp) {
        fLabelBagContentProvider = sp;
    }
    
    /**
     * @param sp The content provider for the list.
     */
    public void setEvalLabelContentProvider(IStructuredContentProvider sp) {
        fEvalLabelContentProvider = sp;
    }

    /**
     * @param lp The labelProvider for the list.
     */
    public void setEvalLabelLabelProvider(ILabelProvider lp) {
        fEvalLabelLabelProvider = lp;
    }
    
    /**
     * @param lp The labelProvider for the list.
     */
    public void setLabelBagLabelProvider(ILabelProvider lp) {
        fLabelBagLabelProvider = lp;
    }

    /**
     *@param addCancelButton if <code>true</code> there will be a cancel
     * button.
     */
    public void setAddCancelButton(boolean addCancelButton) {
        fAddCancelButton = addCancelButton;
    }

    /**
     * @return the TableViewer for the receiver.
     */
    public TableViewer getTableViewer() {
        return fTableViewer;
    }

    protected void createButtonsForButtonBar(Composite parent) {
        if (!fAddCancelButton) {
			createButton(parent, IDialogConstants.OK_ID,
                    IDialogConstants.OK_LABEL, true);
		} else {
			super.createButtonsForButtonBar(parent);
		}
    }

    protected Control createDialogArea(Composite container) {
        Composite parent = (Composite) super.createDialogArea(container);
        
        createMessageArea(parent);
        
        parent = createViewEvalDialogArea(parent);
        
        parent = createSelectionDialogArea(parent);
        
        return parent;
        
     }
    
    protected Composite createSelectionDialogArea(Composite container)  {
    	 fTableViewer = new TableViewer(container, getTableStyle());
         fTableViewer.setContentProvider(fEvalLabelContentProvider);
         fTableViewer.setLabelProvider(fEvalLabelLabelProvider);
         fTableViewer.setInput(fEvalLabelInput);
         fTableViewer.addDoubleClickListener(new IDoubleClickListener() {
             public void doubleClick(DoubleClickEvent event) {
                 if (fAddCancelButton) {
 					okPressed();
 				}
             }
         });
         List initialSelection = getInitialElementSelections();
         if (initialSelection != null) {
 			fTableViewer
                     .setSelection(new StructuredSelection(initialSelection));
 		}
         GridData gd = new GridData(GridData.FILL_BOTH);
         gd.heightHint = convertHeightInCharsToPixels(selectHeightInChars);
         gd.widthHint = convertWidthInCharsToPixels(widthInChars);
         Table table = fTableViewer.getTable();
         table.setLayoutData(gd);
         table.setFont(container.getFont());
         
         return container;
    }
    
    protected Composite createViewEvalDialogArea(Composite container)  {
    	TableViewer tv = new TableViewer(container, SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER | SWT.READ_ONLY);
    
    	tv.setContentProvider(fLabelBagContentProvider);
    	tv.setLabelProvider(fLabelBagLabelProvider);
    	tv.setInput(fLabelBagInput);
    	
       
        GridData gd = new GridData(GridData.FILL_BOTH);
        gd.heightHint = convertHeightInCharsToPixels(viewHeightInChars);
        gd.widthHint = convertWidthInCharsToPixels(widthInChars);
        Table table = tv.getTable();
        table.setLayoutData(gd);
        
       
//        table.addListener(SWT.EraseItem, new Listener() {
//
//    		public void handleEvent(Event event) {
//    			if((event.detail & SWT.SELECTED) != 0 ){
//    				event.detail &= ~SWT.SELECTED;
//    			}
//    		}
//    		
//    	});

        
        table.setFont(container.getFont());
        
        table.setEnabled(false);
        
        return container;
    }
    /**
     * Return the style flags for the table viewer.
     * @return int
     */
    protected int getTableStyle() {
        return SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER;
    }

    /*
     * Overrides method from Dialog
     */
    protected void okPressed() {
        // Build a list of selected children.
        IStructuredSelection selection = (IStructuredSelection) fTableViewer
                .getSelection();
        setResult(selection.toList());
        super.okPressed();
    }

    /**
     * Returns the initial height of the dialog in number of characters.
     * 
     * @return the initial height of the dialog in number of characters
     */
    public int getSelectionHeightInChars() {
        return selectHeightInChars;
    }

    /**
     * Returns the initial width of the dialog in number of characters.
     * 
     * @return the initial width of the dialog in number of characters
     */
    public int getWidthInChars() {
        return widthInChars;
    }

    /**
     * Sets the initial height of the dialog in number of characters.
     * 
     * @param heightInChars
     *            the initialheight of the dialog in number of characters
     */
    public void setSelectionHeightInChars(int heightInChars) {
        this.selectHeightInChars = heightInChars;
    }

    /**
     * Sets the initial width of the dialog in number of characters.
     * 
     * @param widthInChars
     *            the initial width of the dialog in number of characters
     */
    public void setWidthInChars(int widthInChars) {
        this.widthInChars = widthInChars;
    }
}

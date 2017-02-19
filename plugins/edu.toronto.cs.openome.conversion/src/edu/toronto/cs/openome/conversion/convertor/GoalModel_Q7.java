package edu.toronto.cs.openome.conversion.convertor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathFactoryConfigurationException;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import edu.toronto.cs.openome_model.Agent;
import edu.toronto.cs.openome_model.Contribution;
import edu.toronto.cs.openome_model.Decomposition;
import edu.toronto.cs.openome_model.Dependency;
import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Model;
import edu.toronto.cs.openome_model.Position;
import edu.toronto.cs.openome_model.Role;
import edu.toronto.cs.openome_model.Softgoal;
import edu.toronto.cs.openome_model.Task;
import edu.toronto.cs.openome_model.impl.AndDecompositionImpl;
import edu.toronto.cs.openome_model.impl.BreakContributionImpl;
import edu.toronto.cs.openome_model.impl.ContributionImpl;
import edu.toronto.cs.openome_model.impl.DecompositionImpl;
import edu.toronto.cs.openome_model.impl.DependencyImpl;
import edu.toronto.cs.openome_model.impl.HelpContributionImpl;
import edu.toronto.cs.openome_model.impl.HurtContributionImpl;
import edu.toronto.cs.openome_model.impl.IntentionImpl;
import edu.toronto.cs.openome_model.impl.LinkImpl;
import edu.toronto.cs.openome_model.impl.MakeContributionImpl;
import edu.toronto.cs.openome_model.impl.OrDecompositionImpl;

/**
 * Class to convert Diagram Representation of the model to Q7 syntax.
 * 
 * @author showzeb
 * 
 */
@SuppressWarnings("restriction")
public class GoalModel_Q7 {

	private Diagram diagram;
	private edu.toronto.cs.openome_model.Model model;
	private String sourceFile = "";
	private String targetFile = "";
	private BufferedWriter writer = null;
	private boolean bracketOpen = false;
	private URI uri;
	private ArrayList<Intention> alreadyVisited = new ArrayList<Intention>();
	private XPath xpath;
	private Document doc;
	private int sizeContribution;
	private int sizeDependum;
	private String warning = "";

	public GoalModel_Q7(String sourceFile, String targetFile) {
		this.sourceFile = sourceFile;
		this.targetFile = targetFile;
		initialize();
	}

	/**
	 * Initialize the setup, get the Model and Diagram from the source file.
	 */
	private void initialize() {

		// Extracting the model and the diagram from the XMI file.
		uri = URI.createFileURI(sourceFile);
		Resource resource = new XMIResourceImpl();
		resource.unload();
		resource.setURI(uri);
		try {
			resource.load(null);
			for (Object o : resource.getContents()) {
				if (o instanceof Diagram) {
					diagram = (Diagram) o;
				} else if (o instanceof Model) {
					model = (Model) o;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Checking for invalid syntax and prompting the user with warning if
		// there is invalid syntax.
		if (invalidSyntax()) {
			if (messageDialog()) {
				// Creates target file
				createFile();

				for (Intention intention : model.getRoots()) {
					if (!alreadyVisited.contains(intention))
						startProcess(intention, "", false, true);
				}

				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			// Creates target file
			createFile();

			for (Intention intention : model.getRoots()) {
				if (!alreadyVisited.contains(intention) || 
						intention.getQualitativeReasoningCombinedLabel().compareTo(EvaluationLabel.NONE) != 0)
					startProcess(intention, "", false, true);
			}

			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * Return the number of intention that the intention is contributing to and
	 * write it to the file in Q7 format
	 * 
	 * @param intention
	 * @return the number of contributions.
	 */
	public int printContributionTo(Intention intention) {
		
		ArrayList<Contribution> list = new ArrayList<Contribution>();
		for (Contribution link : intention.getContributesTo()) {
			if (	link instanceof MakeContributionImpl ||
					link instanceof HelpContributionImpl ||
					link instanceof HurtContributionImpl ||
					link instanceof BreakContributionImpl) {
				list.add(link);
			}
		}
		int size = -1;
		// Check if the intention is contributing to any goal and that goal is
		// automatically assumed to be a softgoal.
		if (list.size() > 0) {
			size = 0;
			write(" => ");
			for (Contribution link : list) {
				// System.out.println(link);
				if (link instanceof MakeContributionImpl) {
					write("++" + printName(link.getTarget(), false));
				} else if (link instanceof HelpContributionImpl) {
					write("+" + printName(link.getTarget(), false));
				} else if (link instanceof HurtContributionImpl) {
					write("-" + printName(link.getTarget(), false));
				} else if (link instanceof BreakContributionImpl) {
					write("--" + printName(link.getTarget(), false));
				}
				size++;
				if (size < list.size())
					write(", ");

			}
		}
		return size;
	}

	/**
	 * Return the number of intention that the intention is dependent upon and
	 * write it to the file
	 * 
	 * @param intention
	 * @return the number of dependum
	 */
	public int printDependencyFrom(Intention intention) {
		int size = -1;
		// Check for dependency link
		if (intention.getDependencyFrom().size() > 0) {
			size = 0;
			if (intention.getContributesTo().size() == 0)
				write(" => ");
			else {
				write(", ");
			}
			for (Dependency link : intention.getDependencyFrom()) {
				if (link instanceof DependencyImpl) {
					write("~" + printName((Intention) link.getDependencyFrom(), true));
					// System.out.println(link);
				}
				size++;
				if (size < intention.getDependencyFrom().size())
					write(", ");
			}
		}
		return size;
	}

	/**
	 * Write the model to the file.
	 * 
	 * @param intention the current intention
	 * @param fromDecomposition true => inside the brackets of means-end/decomposition, its use is that if the 
	 * parameter intention is a softgoal then we will just write its name instead of recursing to its source intention 
	 * that's contributing to it.
	 * @param root TODO
	 */
	private void startProcess(Intention intention, String recurse,
			boolean fromDecomposition, boolean root) {

		
		// Seperate decomposition and means-end link of intention from the array
		ArrayList<OrDecompositionImpl> meansEnd = new ArrayList<OrDecompositionImpl>();
		ArrayList<AndDecompositionImpl> decompose = new ArrayList<AndDecompositionImpl>();
		for (Decomposition link : intention.getDecompositionsTo()) {
			if (link instanceof OrDecompositionImpl) {
				meansEnd.add((OrDecompositionImpl) link);
			} else {
				decompose.add((AndDecompositionImpl) link);
			}
		}

		// Go through all the intentions that are connected through means-end
		// link
		if (meansEnd.size() > 0) {
			write(recurse + printName(intention, false));
			write(" { |\n");
			bracketOpen = true;
			for (OrDecompositionImpl link : meansEnd) {
				startProcess(link.getSource(), recurse + "\t", true, false);
			}
			write(recurse + "}");
			printLabel(intention);
			bracketOpen = false;
			sizeContribution = printContributionTo(intention);
			sizeDependum = printDependencyFrom(intention);
			write("\n");
		}

		// Go through all the intentions that are connected through
		// decomposition link
		if (decompose.size() > 0) {
			write(recurse + printName(intention, false));
			write(" { &\n");
			bracketOpen = true;
			for (AndDecompositionImpl link : decompose) {
				startProcess(link.getSource(), recurse + "\t", true, false);
			}
			write(recurse + "}");
			printLabel(intention);
			bracketOpen = false;
			sizeContribution = printContributionTo(intention);
			sizeDependum = printDependencyFrom(intention);
			write("\n");
		}

		// If it has no means-end and decomposition links 
		// then just print name and its contributions and dependencies
		if (meansEnd.size() == 0 && decompose.size() == 0) {
			if (!root && (fromDecomposition || !alreadyVisited.contains(intention))) {
				write(recurse + printName(intention, false));
				printLabel(intention);
				sizeContribution = printContributionTo(intention);
				sizeDependum = printDependencyFrom(intention);
				write("\n");
			} else if (root && (!(intention instanceof Softgoal) || 
					intention.getQualitativeReasoningCombinedLabel().compareTo(EvaluationLabel.NONE) != 0)){
				write(recurse + printName(intention, false));
				printLabel(intention);
				sizeContribution = printContributionTo(intention);
				sizeDependum = printDependencyFrom(intention);
				write("\n");
			}
		}

		// If it is a softgoal then just recurse to its parent goal/task unless we are inside the means-end/decomposition brackets.
		if (intention.getContributesFrom().size() > 0) {
			if (fromDecomposition && !(meansEnd.size() == 0 && decompose.size() == 0)) {
				write(recurse + printName(intention, false));
				write("\n");
			} else if (!fromDecomposition && (!alreadyVisited.contains(intention) ||
					intention.getQualitativeReasoningCombinedLabel().compareTo(EvaluationLabel.NONE) != 0)) {
				for (Contribution connectInt : intention.getContributesFrom()) {
					startProcess(connectInt.getSource(), recurse, false, false);
				}
			}
		}
		
		if (intention.getDependencyFrom().size() > 0) {
			
			for (Dependency link : intention.getDependencyFrom()) {
				if (!alreadyVisited.contains((Intention) link.getDependencyFrom()))
					startProcess((Intention) link.getDependencyFrom(), "", false, false);
			}
		}
	}
	
	/**
	 * Write the label associated with that intention in the Q7 syntax in the file.
	 * @param intention
	 */
	private void printLabel(Intention intention) {
		EvaluationLabel label = intention.getQualitativeReasoningCombinedLabel();
		if (label.compareTo(label.SATISFIED) == 0) {
			write(" @FS@ ");
		} else if (label.compareTo(label.PARTIALLY_SATISFIED) == 0) {
			write(" @PS@ ");
		} else if (label.compareTo(label.DENIED) == 0) {
			write(" @FD@ ");
		} else if (label.compareTo(label.PARTIALLY_DENIED) == 0) {
			write(" @PD@ ");
		} else if (label.compareTo(label.CONFLICT) == 0) {
			write(" @CF@ ");
		} else if (label.compareTo(label.UNKNOWN) == 0) {
			write(" @UN@ ");
		}
	}
	/**
	 * Write the name of the intention in Q7 syntax in the file.
	 * 
	 * @param intention
	 * @param isDependency TODO
	 */
	private String printName(Intention intention, boolean isDependency) {
		// Checking for name
		if (!isDependency)
			alreadyVisited.add(intention);
		String start = "";
		if (intention.getContainer() != null
				&& !intention.getContainer().getName().startsWith("Aspect")) {
			start = "<\"" + actorType(intention) + " ";
			if (intention.getContainer().getName() != null) {
				start += intention.getContainer().getName() + "\">::";
			} else {
				start += "untitled\">::";
			}
		}
		if (intention instanceof Task) {
			if (intention.getName() != null) {
				return start + "\"Do " + intention.getName() + "\"";
			} else {
				return start + "\"Do untitled\"";
			}
		} else {
			if (intention.getName() != null) {
				return start + "\"" + intention.getName() + "\"";
			} else {
				return start + "\"untitled\"";
			}
		}
	}

	/**
	 * Return the actor type that is the parent of the intention.
	 * 
	 * @param intention
	 *            Child of the actor
	 * @return Type of Actor i.e agent or actor or role
	 */
	private String actorType(Intention intention) {
		if (intention.getContainer() instanceof Agent) {
			return "Agent";
		} else if (intention.getContainer() instanceof Position) {
			return "Position";
		} else if (intention.getContainer() instanceof Role) {
			return "Role";
		} else {
			return "Actor";
		}
	}

	/**
	 * Write the text to the stream.
	 * 
	 * @param text
	 */
	private void write(String text) {
		try {
			writer.write(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the targer file with the .q7 extension. Remember to close the
	 * buffered stream after writing is done.
	 */
	private void createFile() {
		try {
			FileWriter fstream = new FileWriter(targetFile);
			writer = new BufferedWriter(fstream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Prompts the user to either continue with the Export process or to stop it.
	 * @return true if the user click's yes otherwise false.
	 */
	public boolean messageDialog() {
		Shell shell = new Shell(Display.getCurrent());

		MessageBox messageBox = new MessageBox(shell, SWT.YES | SWT.NO);
		messageBox
				.setMessage(warning + "Do you want to continue?");

		int rc = messageBox.open();

		switch (rc) {
		case SWT.YES:
			return true;
		case SWT.NO:
			return false;
		}

		return false;
	}

	/**
	 * Return true if the model contains element that are not supported by Q7
	 * syntax, otherwise false.
	 * 
	 * @return true if the model contains element that are not supported by Q7
	 *         syntax, otherwise false.
	 */
	public boolean invalidSyntax() {

		boolean syntax = false;
		for (Intention intention : model.getAllIntentions()) {
			if (intention instanceof edu.toronto.cs.openome_model.Resource) {
				syntax = true;
				warning += "- " + intention.getName() + " Resource is not recognized by Q7. It will be converted to a Goal." +
				" Accepted Intentions are \"Goal\", \"Softgoal\", and \"Task\".\n";
			}
			for (Contribution link : intention.getContributesTo()) {
				if (	!(link instanceof MakeContributionImpl ||
						link instanceof HelpContributionImpl ||
						link instanceof HurtContributionImpl ||
						link instanceof BreakContributionImpl)) {
					syntax = true;
					warning += "- " + link.getContributionType() + " is not recognized by Q7 as a contribution link. It will be removed. " +
					"Accepted contribution links are \"Make\", \"Help\", \"Hurt\", and \"Break\".\n";
				}
			}
		}

		if (model.getAssociations().size() > 0) {
			syntax = true;
			warning += "- Association links are not recognized by Q7 and they will be removed.\n";
		}
		return syntax;
	}

	private void modelContents() {

		// System.out.println(diagram.getElement());
		System.out.println(model + " " + model.getAllIntentions());
		System.out.println("Roots :" + model.getRoots());
		System.out.println("Leaves :" + model.getLeaves());
	}
}

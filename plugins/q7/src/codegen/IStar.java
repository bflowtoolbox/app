/*
 * Created on Jan 27, 2005
 * To generate i* diagram from the parsed Q7 input
 * 
 * TODO use When information to generate the "claim" softgoal 
 */
package codegen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Vector;

import model.Advice;
import model.Effect;
import model.Entity;
import model.IStarElement;
import model.IStarLink;
import model.Pointcut;
import util.Computing;
import util.SortArrayList;

/**
 * @author Yijun Yu
 */
public class IStar {
    private static ResourceBundle resources;
    static {
    	load_properties();
    }
    public static void load_properties() {
        try {
            resources = ResourceBundle.getBundle("Q7",
                                                 Locale.getDefault());
            for (Enumeration i = resources.getKeys(); i.hasMoreElements(); ) {
            	String key = (String)i.nextElement();
            	String val = resources.getString(key);
            	System.setProperty(key, val);
            }
        } catch (MissingResourceException mre) {
            System.out.println("Info: Q7.properties not found");
        }    	
    }
	public ArrayList<Advice> advices;
	public Hashtable<Advice, IStarElement> elements = new Hashtable<Advice, IStarElement>();
	public Hashtable<String, IStarLink> links = new Hashtable<String, IStarLink>();
	public ArrayList<String> serialized_tokens = new ArrayList<String>();
	public Hashtable<String, Advice> intentions = new Hashtable<String, Advice>();
	public PrintStream out = System.out;
	/**
	 * @param a -- advices
	 */
	public IStar(ArrayList<Advice> a) {
		advices = a;
	}

	Hashtable<String, IStarElement> agents = new Hashtable<String, IStarElement>(); // name -> IStarElement
	
	private String serialized_view = "";
	public static URL u;
	/**
	 * @param file
	 * @return the content of the file
	 */
	private static String get_file_contents() {
		String string = "";
		try {
			BufferedReader br = null;
			String m = System.getProperty("java.library.path");
			if (m!=null) {
				FileReader fr = new FileReader(m+File.separator + "istar.tel");
				br = new BufferedReader(fr);
			} else {
				if (u==null) {
					u = IStar.class.getResource("/edu/toronto/cs/q7/metamodel/istar.tel");
				}
				InputStream i = null;
				try {
					i = u.openStream();
					br = new BufferedReader(new InputStreamReader(i));
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
			String s = null;
			do {
				if (s!=null) {
					string += "\n";
				}
				s = br.readLine();
				if (s!=null) {
					string += s;
				}
			} while (s!=null);
		} catch (Exception e) {
			// the default Telos metamodel
			  string = "";
		      string += "Token SerializedView_0\n";
		      string += "    IN SerializedView\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarRoleElement\n";
		      string += "    IN IStarActorElementClass, OMEInstantiableClass\n";
		      string += "    ISA IStarActorElement\n";
		      string += "    WITH\n";
		      string += "        attribute, attribute, name\n";
		      string += "             : \"i* Role\"\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            height : 60\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            width : 60\n";
		      string += "        attribute, attribute, imagename\n";
		      string += "             : \"Role.gif\"\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarPositionElement\n";
		      string += "    IN IStarActorElementClass, OMEInstantiableClass\n";
		      string += "    ISA IStarActorElement\n";
		      string += "    WITH\n";
		      string += "        attribute, attribute, name\n";
		      string += "             : \"i* Position\"\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            height : 60\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            width : 60\n";
		      string += "        attribute, attribute, imagename\n";
		      string += "             : \"Position.gif\"\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarAspectElement\n";
		      string += "    IN IStarActorElementClass, OMEInstantiableClass\n";
		      string += "    ISA IStarActorElement\n";
		      string += "    WITH\n";
		      string += "        attribute, attribute, name\n";
		      string += "             : \"i* Aspect\"\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            height : 40\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            width : 40\n";
		      string += "        attribute, attribute, imagename\n";
		      string += "             : \"Annotation.gif\"\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarAgentElement\n";
		      string += "    IN IStarActorElementClass, OMEInstantiableClass\n";
		      string += "    ISA IStarActorElement\n";
		      string += "    WITH\n";
		      string += "        attribute, attribute, name\n";
		      string += "             : \"i* Agent\"\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            height : 60\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            width : 60\n";
		      string += "        attribute, attribute, imagename\n";
		      string += "             : \"Agent.gif\"\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarActorElement\n";
		      string += "    IN IStarClass, IStarActorElementClass, OMEInstantiableClass\n";
		      string += "    ISA IStarElement, OMEGrowableElement\n";
		      string += "    WITH\n";
		      string += "        attribute, attribute, name\n";
		      string += "             : \"i* Actor\"\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            height : 60\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            width : 60\n";
		      string += "        attribute, children\n";
		      string += "             : IStarIntentionalElement\n";
		      string += "        attribute, attribute, imagename\n";
		      string += "             : \"Actor.gif\"\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarGoalElement\n";
		      string += "    IN IStarIntentionalElementClass, OMEInstantiableClass\n";
		      string += "    ISA IStarIntentionalElement\n";
		      string += "    WITH\n";
		      string += "        attribute, attribute, name\n";
		      string += "             : \"i* Goal\"\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            height : 50\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            width : 75\n";
		      string += "        attribute, attribute, imagename\n";
		      string += "             : \"Goal.gif\"\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarTaskElement\n";
		      string += "    IN IStarIntentionalElementClass, OMEInstantiableClass\n";
		      string += "    ISA IStarIntentionalElement\n";
		      string += "    WITH\n";
		      string += "        attribute, attribute, name\n";
		      string += "             : \"i* Task\"\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            height : 50\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            width : 75\n";
		      string += "        attribute, attribute, imagename\n";
		      string += "             : \"Task.gif\"\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarSoftGoalElement\n";
		      string += "    IN IStarIntentionalElementClass, OMEInstantiableClass\n";
		      string += "    ISA IStarIntentionalElement\n";
		      string += "    WITH       \n";
		      string += "        attribute, attribute, name\n";
		      string += "             : \"i* Softgoal\"\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            height : 50\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            width : 75\n";
		      string += "        attribute, attribute, satisficing\n";
		      string += "            sat: 0.0\n";
		      string += "        attribute, attribute, satisficing\n";
		      string += "            den: 0.0\n";
		      string += "        attribute, attribute, imagename\n";
		      string += "             : \"Softgoal.gif\"\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarClaimElement\n";
		      string += "    IN IStarIntentionalElementClass, OMEInstantiableClass\n";
		      string += "    ISA IStarIntentionalElement, IStarSoftGoalElement\n";
		      string += "    WITH\n";
		      string += "        attribute, attribute, name\n";
		      string += "             : \"i* Claim\"\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            height : 50\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            width : 75\n";
		      string += "        attribute, attribute, imagename\n";
		      string += "             : \"Claim.gif\"\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarResourceElement\n";
		      string += "    IN IStarIntentionalElementClass, OMEInstantiableClass\n";
		      string += "    ISA IStarIntentionalElement\n";
		      string += "    WITH\n";
		      string += "        attribute, attribute, name\n";
		      string += "             : \"i* Resource\"\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            height : 50\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            width : 75\n";
		      string += "        attribute, attribute, imagename\n";
		      string += "             : \"Resource.gif\"\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarIntentionalElement\n";
		      string += "    IN IStarClass, OMEElementClass, OMEVisibleClass\n";
		      string += "    ISA IStarElement\n";
		      string += "    WITH\n";
		      string += "        attribute, imagesize, attribute\n";
		      string += "            height : 50\n";
		      string += "        attribute, imagesize, attribute\n";
		      string += "            width : 75\n";
		      string += "        attribute, parent\n";
		      string += "             : IStarActorElement\n";
		      string += "        attribute, OMEAttributes\n";
		      string += "            feature : IStarFeatureLabelClass\n";
		      string += "        attribute, OMEAttributes\n";
		      string += "            control : IStarControlLabelClass\n";
		      string += "        attribute, OMEAttributes\n";
		      string += "            label : IStarElementLabelClass\n";
		      string += "        attribute, priority\n";
		      string += "            : 0\n";
		      string += "        attribute, sat\n";
		      string += "            : 0\n";
		      string += "        attribute, den\n";
		      string += "            : 0\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarElement\n";
		      string += "    IN IStarClass\n";
		      string += "    ISA OMEElement\n";
		      string += "    WITH\n";
		      string += "        attribute, name\n";
		      string += "             : String\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarDependencyLink\n";
		      string += "    IN IStarLinkClass, OMEInstantiableClass\n";
		      string += "    ISA IStarLink\n";
		      string += "    WITH\n";
		      string += "        attribute, attribute, name\n";
		      string += "             : \"i* Dependency link\"\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            width : 20\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            height : 20\n";
		      string += "        attribute, attribute, imagename\n";
		      string += "             : \"Dependency.gif\"\n";
		      string += "        attribute, OMEAttributes\n";
		      string += "            label : IStarLinkLabelClass\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarDecompositionLink\n";
		      string += "    IN IStarLinkClass, OMEInstantiableClass\n";
		      string += "    ISA IStarLink\n";
		      string += "    WITH\n";
		      string += "        attribute, attribute, name\n";
		      string += "             : \"i* Decomposition link\"\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            width : 20\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            height : 20\n";
		      string += "        attribute, to\n";
		      string += "             : IStarTaskElement\n";
		      string += "        attribute, attribute, defaultname\n";
		      string += "             : \"And\"\n";
		      string += "        attribute, attribute, imagename\n";
		      string += "             : \"Decomposition.gif\"\n";
		      string += "        attribute, from\n";
		      string += "             : IStarIntentionalElement\n";
		      string += "        attribute, OMEAttributes\n";
		      string += "            label : IStarLinkLabelClass\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarMeansEndsLink\n";
		      string += "    IN IStarLinkClass, OMEInstantiableClass\n";
		      string += "    ISA IStarLink\n";
		      string += "    WITH\n";
		      string += "        attribute, attribute, name\n";
		      string += "             : \"i* Means-ends link\"\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            width : 20\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            height : 20\n";
		      string += "        attribute, to\n";
		      string += "             : IStarGoalElement\n";
		      string += "        attribute, attribute, defaultname\n";
		      string += "             : \"Or\"\n";
		      string += "        attribute, attribute, imagename\n";
		      string += "             : \"Means-Ends.gif\"\n";
		      string += "        attribute, from\n";
		      string += "             : IStarIntentionalElement\n";
		      string += "        attribute, OMEAttributes\n";
		      string += "            label : IStarLinkLabelClass\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarISALink\n";
		      string += "    IN IStarLinkClass, OMEInstantiableClass\n";
		      string += "    ISA IStarLink\n";
		      string += "    WITH\n";
		      string += "        attribute, attribute, name\n";
		      string += "             : \"i* ISA\"\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            width : 20\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            height : 20\n";
		      string += "        attribute, to\n";
		      string += "             : IStarActorElement\n";
		      string += "        attribute, attribute, defaultname\n";
		      string += "             : \"ISA\"\n";
		      string += "        attribute, attribute, imagename\n";
		      string += "             : \"ISA.gif\"\n";
		      string += "        attribute, from\n";
		      string += "             : IStarActorElement\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarINSLink\n";
		      string += "    IN IStarLinkClass, OMEInstantiableClass\n";
		      string += "    ISA IStarLink\n";
		      string += "    WITH\n";
		      string += "        attribute, attribute, name\n";
		      string += "             : \"i* INS\"\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            width : 20\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            height : 20\n";
		      string += "        attribute, to\n";
		      string += "             : IStarActorElement\n";
		      string += "        attribute, attribute, defaultname\n";
		      string += "             : \"INS\"\n";
		      string += "        attribute, attribute, imagename\n";
		      string += "             : \"ISA.gif\"\n";
		      string += "        attribute, from\n";
		      string += "             : IStarActorElement\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarPlaysLink\n";
		      string += "    IN IStarLinkClass, OMEInstantiableClass\n";
		      string += "    ISA IStarLink\n";
		      string += "    WITH\n";
		      string += "        attribute, attribute, name\n";
		      string += "             : \"i* Plays\"\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            width : 20\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            height : 20\n";
		      string += "        attribute, to\n";
		      string += "             : IStarRoleElement\n";
		      string += "        attribute, attribute, defaultname\n";
		      string += "             : \"PLAYS\"\n";
		      string += "        attribute, attribute, imagename\n";
		      string += "             : \"ISA.gif\"\n";
		      string += "        attribute, from\n";
		      string += "             : IStarAgentElement\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarOccupiesLink\n";
		      string += "    IN IStarLinkClass, OMEInstantiableClass\n";
		      string += "    ISA IStarLink\n";
		      string += "    WITH\n";
		      string += "        attribute, attribute, name\n";
		      string += "             : \"i* Occupies\"\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            width : 20\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            height : 20\n";
		      string += "        attribute, to\n";
		      string += "             : IStarPositionElement\n";
		      string += "        attribute, attribute, defaultname\n";
		      string += "             : \"OCCUPIES\"\n";
		      string += "        attribute, attribute, imagename\n";
		      string += "             : \"ISA.gif\"\n";
		      string += "        attribute, from\n";
		      string += "             : IStarAgentElement\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarCoversLink\n";
		      string += "    IN IStarLinkClass, OMEInstantiableClass\n";
		      string += "    ISA IStarLink\n";
		      string += "    WITH\n";
		      string += "        attribute, attribute, name\n";
		      string += "             : \"i* Covers\"\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            width : 20\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            height : 20\n";
		      string += "        attribute, to\n";
		      string += "             : IStarRoleElement\n";
		      string += "        attribute, attribute, defaultname\n";
		      string += "             : \"COVERS\"\n";
		      string += "        attribute, attribute, imagename\n";
		      string += "             : \"ISA.gif\"\n";
		      string += "        attribute, from\n";
		      string += "             : IStarPositionElement\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarDeniedElementLabel\n";
		      string += "    IN OMEVisibleClass, IStarElementLabelClass\n";
		      string += "    WITH\n";
		      string += "        attribute, imagesize, attribute, imagesize\n";
		      string += "            height : 25\n";
		      string += "        attribute, imagename, attribute, imagename\n";
		      string += "             : \"Cross.gif\"\n";
		      string += "        attribute, attribute, imageposition\n";
		      string += "            dy : 30\n";
		      string += "        attribute, imagesize, attribute, imagesize\n";
		      string += "            width : 25\n";
		      string += "        attribute, attribute, imageposition\n";
		      string += "            dx : 30\n";
		      string += "        attribute, name, attribute, name\n";
		      string += "             : \"i* Denied\"\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarWeaklyDeniedElementLabel\n";
		      string += "    IN OMEVisibleClass, IStarElementLabelClass\n";
		      string += "    WITH\n";
		      string += "        attribute, imagesize, attribute, imagesize\n";
		      string += "            height : 25\n";
		      string += "        attribute, imagename, attribute, imagename\n";
		      string += "             : \"WeaklyDenied.gif\"\n";
		      string += "        attribute, attribute, imageposition\n";
		      string += "            dy : 30\n";
		      string += "        attribute, imagesize, attribute, imagesize\n";
		      string += "            width : 25\n";
		      string += "        attribute, attribute, imageposition\n";
		      string += "            dx : 30\n";
		      string += "        attribute, name, attribute, name\n";
		      string += "             : \"i* Weakly Denied\"\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarUndecidedElementLabel\n";
		      string += "    IN OMEVisibleClass, IStarElementLabelClass\n";
		      string += "    WITH\n";
		      string += "        attribute, imagesize, attribute, imagesize\n";
		      string += "            height : 25\n";
		      string += "        attribute, imagename, attribute, imagename\n";
		      string += "             : \"Undecided.gif\"\n";
		      string += "        attribute, attribute, imageposition\n";
		      string += "            dy : 35\n";
		      string += "        attribute, imagesize, attribute, imagesize\n";
		      string += "            width : 25\n";
		      string += "        attribute, attribute, imageposition\n";
		      string += "            dx : 30\n";
		      string += "        attribute, name, attribute, name\n";
		      string += "             : \"i* Undecided\"\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarWeaklySatisficedElementLabel\n";
		      string += "    IN OMEVisibleClass, IStarElementLabelClass\n";
		      string += "    WITH\n";
		      string += "        attribute, imagesize, attribute, imagesize\n";
		      string += "            height : 25\n";
		      string += "        attribute, imagename, attribute, imagename\n";
		      string += "             : \"WeaklySatisficed.gif\"\n";
		      string += "        attribute, attribute, imageposition\n";
		      string += "            dy : 25\n";
		      string += "        attribute, imagesize, attribute, imagesize\n";
		      string += "            width : 25\n";
		      string += "        attribute, attribute, imageposition\n";
		      string += "            dx : 30\n";
		      string += "        attribute, name, attribute, name\n";
		      string += "             : \"i* Weakly Satisficed\"\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarSatisficedElementLabel\n";
		      string += "    IN OMEVisibleClass, IStarElementLabelClass\n";
		      string += "    WITH\n";
		      string += "        attribute, imagesize, attribute, imagesize\n";
		      string += "            height : 25\n";
		      string += "        attribute, imagename, attribute, imagename\n";
		      string += "             : \"Check.gif\"\n";
		      string += "        attribute, attribute, imageposition\n";
		      string += "            dy : 30\n";
		      string += "        attribute, imagesize, attribute, imagesize\n";
		      string += "            width : 25\n";
		      string += "        attribute, attribute, imageposition\n";
		      string += "            dx : 30\n";
		      string += "        attribute, name, attribute, name\n";
		      string += "             : \"i* Satisficed\"\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarConflictElementLabel\n";
		      string += "    IN OMEVisibleClass, IStarElementLabelClass\n";
		      string += "    WITH\n";
		      string += "        attribute, imagesize, attribute, imagesize\n";
		      string += "            height : 25\n";
		      string += "        attribute, imagename, attribute, imagename\n";
		      string += "             : \"Conflict.gif\"\n";
		      string += "        attribute, attribute, imageposition\n";
		      string += "            dy : 30\n";
		      string += "        attribute, imagesize, attribute, imagesize\n";
		      string += "            width : 25\n";
		      string += "        attribute, attribute, imageposition\n";
		      string += "            dx : 30\n";
		      string += "        attribute, name, attribute, name\n";
		      string += "             : \"i* Conflict\"\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarDeniedLinkLabel\n";
		      string += "    IN OMEVisibleClass, IStarLinkLabelClass\n";
		      string += "    WITH\n";
		      string += "        attribute, imagesize, attribute, imagesize\n";
		      string += "            height : 20\n";
		      string += "        attribute, imagename, attribute, imagename\n";
		      string += "             : \"Cross.gif\"\n";
		      string += "        attribute, attribute, imageposition\n";
		      string += "            dy : 10\n";
		      string += "        attribute, imagesize, attribute, imagesize\n";
		      string += "            width : 20\n";
		      string += "        attribute, attribute, imageposition\n";
		      string += "            dx : 20\n";
		      string += "        attribute, name, attribute, name\n";
		      string += "             : \"i* Denied Link\"\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarOptionalFeatureLabel\n";
		      string += "    IN OMEVisibleClass, IStarFeatureLabelClass\n";
		      string += "    WITH\n";
		      string += "        attribute, imagesize, attribute, imagesize\n";
		      string += "            height : 20\n";
		      string += "        attribute, imagename, attribute, imagename\n";
		      string += "             : \"optional.gif\"\n";
		      string += "        attribute, attribute, imageposition\n";
		      string += "            dy : -20\n";
		      string += "        attribute, imagesize, attribute, imagesize\n";
		      string += "            width : 20\n";
		      string += "        attribute, attribute, imageposition\n";
		      string += "            dx : 0\n";
		      string += "        attribute, name, attribute, name\n";
		      string += "             : \"optional\"\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarAlternativeFeatureLabel\n";
		      string += "    IN OMEVisibleClass, IStarFeatureLabelClass\n";
		      string += "    WITH\n";
		      string += "        attribute, imagesize, attribute, imagesize\n";
		      string += "            height : 20\n";
		      string += "        attribute, imagename, attribute, imagename\n";
		      string += "             : \"alternative.gif\"\n";
		      string += "        attribute, attribute, imageposition\n";
		      string += "            dy : 20\n";
		      string += "        attribute, imagesize, attribute, imagesize\n";
		      string += "            width : 20\n";
		      string += "        attribute, attribute, imageposition\n";
		      string += "            dx : 0\n";
		      string += "        attribute, name, attribute, name\n";
		      string += "             : \"alternative\"\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarSequentialControlLabel\n";
		      string += "    IN OMEVisibleClass, IStarControlLabelClass\n";
		      string += "    WITH\n";
		      string += "        attribute, imagesize, attribute, imagesize\n";
		      string += "            height : 20\n";
		      string += "        attribute, imagename, attribute, imagename\n";
		      string += "             : \"sequential.gif\"\n";
		      string += "        attribute, attribute, imageposition\n";
		      string += "            dy : 40\n";
		      string += "        attribute, imagesize, attribute, imagesize\n";
		      string += "            width : 20\n";
		      string += "        attribute, attribute, imageposition\n";
		      string += "            dx : 0\n";
		      string += "        attribute, name, attribute, name\n";
		      string += "             : \"sequential\"\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarParallelControlLabel\n";
		      string += "    IN OMEVisibleClass, IStarControlLabelClass\n";
		      string += "    WITH\n";
		      string += "        attribute, imagesize, attribute, imagesize\n";
		      string += "            height : 20\n";
		      string += "        attribute, imagename, attribute, imagename\n";
		      string += "             : \"parallel.gif\"\n";
		      string += "        attribute, attribute, imageposition\n";
		      string += "            dy : 40\n";
		      string += "        attribute, imagesize, attribute, imagesize\n";
		      string += "            width : 20\n";
		      string += "        attribute, attribute, imageposition\n";
		      string += "            dx : 0\n";
		      string += "        attribute, name, attribute, name\n";
		      string += "             : \"parallel\"\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarPartsLink\n";
		      string += "    IN IStarLinkClass, OMEInstantiableClass\n";
		      string += "    ISA IStarLink\n";
		      string += "    WITH\n";
		      string += "        attribute, attribute, name\n";
		      string += "             : \"i* Is-Part-of\"\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            width : 20\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            height : 20\n";
		      string += "        attribute, to\n";
		      string += "             : IStarActorElement\n";
		      string += "        attribute, attribute, defaultname\n";
		      string += "             : \"Is-Part-of\"\n";
		      string += "        attribute, attribute, imagename\n";
		      string += "             : \"Arrow.gif\"\n";
		      string += "        attribute, from\n";
		      string += "             : IStarActorElement\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarBreakContribution\n";
		      string += "    IN OMEInstantiableClass, OMELinkClass\n";
		      string += "    ISA IStarSoftgoalContribution\n";
		      string += "    WITH\n";
		      string += "        attribute, attribute, name\n";
		      string += "             : \"i* Break\"\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            height : 20\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            width : 20\n";
		      string += "        attribute, attribute, defaultname\n";
		      string += "             : \"Break\"\n";
		      string += "        attribute, attribute, imagename\n";
		      string += "             : \"Arrow.gif\"\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarMakeContribution\n";
		      string += "    IN OMEInstantiableClass, OMELinkClass\n";
		      string += "    ISA IStarSoftgoalContribution\n";
		      string += "    WITH\n";
		      string += "        attribute, attribute, name\n";
		      string += "             : \"i* Make\"\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            height : 20\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            width : 20\n";
		      string += "        attribute, attribute, defaultname\n";
		      string += "             : \"Make\"\n";
		      string += "        attribute, attribute, imagename\n";
		      string += "             : \"Arrow.gif\"\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarOrContribution\n";
		      string += "    IN OMEInstantiableClass, OMELinkClass\n";
		      string += "    ISA IStarSoftgoalContribution\n";
		      string += "    WITH\n";
		      string += "        attribute, attribute, name\n";
		      string += "             : \"i* Or\"\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            height : 20\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            width : 20\n";
		      string += "        attribute, attribute, defaultname\n";
		      string += "             : \"Or\"\n";
		      string += "        attribute, attribute, imagename\n";
		      string += "             : \"Arrow.gif\"\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarAndContribution\n";
		      string += "    IN OMEInstantiableClass, OMELinkClass\n";
		      string += "    ISA IStarSoftgoalContribution\n";
		      string += "    WITH\n";
		      string += "        attribute, attribute, name\n";
		      string += "             : \"i* And\"\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            height : 20\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            width : 20\n";
		      string += "        attribute, attribute, defaultname\n";
		      string += "             : \"And\"\n";
		      string += "        attribute, attribute, imagename\n";
		      string += "             : \"Arrow.gif\"\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarEqualContribution\n";
		      string += "    IN OMEInstantiableClass, OMELinkClass\n";
		      string += "    ISA IStarSoftgoalContribution\n";
		      string += "    WITH\n";
		      string += "        attribute, attribute, name\n";
		      string += "             : \"i* Equal\"\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            height : 20\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            width : 20\n";
		      string += "        attribute, attribute, defaultname\n";
		      string += "             : \"=\"\n";
		      string += "        attribute, attribute, imagename\n";
		      string += "             : \"DoubleArrow.gif\"\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarHurtContribution\n";
		      string += "    IN OMEInstantiableClass, OMELinkClass\n";
		      string += "    ISA IStarSoftgoalContribution\n";
		      string += "    WITH\n";
		      string += "        attribute, attribute, name\n";
		      string += "             : \"i* Hurt\"\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            height : 20\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            width : 20\n";
		      string += "        attribute, attribute, defaultname\n";
		      string += "             : \"Hurt\"\n";
		      string += "        attribute, attribute, imagename\n";
		      string += "             : \"Arrow.gif\"\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarHelpContribution\n";
		      string += "    IN OMEInstantiableClass, OMELinkClass\n";
		      string += "    ISA IStarSoftgoalContribution\n";
		      string += "    WITH\n";
		      string += "        attribute, attribute, name\n";
		      string += "             : \"i* Help\"\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            height : 20\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            width : 20\n";
		      string += "        attribute, attribute, defaultname\n";
		      string += "             : \"Help\"\n";
		      string += "        attribute, attribute, imagename\n";
		      string += "             : \"Arrow.gif\"\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarUnknownContribution\n";
		      string += "    IN OMEInstantiableClass, OMELinkClass\n";
		      string += "    ISA IStarSoftgoalContribution\n";
		      string += "    WITH\n";
		      string += "        attribute, attribute, name\n";
		      string += "             : \"i* Unknown\"\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            height : 20\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            width : 20\n";
		      string += "        attribute, attribute, defaultname\n";
		      string += "             : \"Unknown\"\n";
		      string += "        attribute, attribute, imagename\n";
		      string += "             : \"Arrow.gif\"\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarSomePositiveContribution\n";
		      string += "    IN OMEInstantiableClass, OMELinkClass\n";
		      string += "    ISA IStarSoftgoalContribution\n";
		      string += "    WITH\n";
		      string += "        attribute, attribute, name\n";
		      string += "             : \"i* Some +\"\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            height : 20\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            width : 20\n";
		      string += "        attribute, attribute, defaultname\n";
		      string += "             : \"Some +\"\n";
		      string += "        attribute, attribute, imagename\n";
		      string += "             : \"Arrow.gif\"\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarSomeNegativeContribution\n";
		      string += "    IN OMEInstantiableClass, OMELinkClass\n";
		      string += "    ISA IStarSoftgoalContribution\n";
		      string += "    WITH\n";
		      string += "        attribute, attribute, name\n";
		      string += "             : \"i* Some -\"\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            height : 20\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            width : 20\n";
		      string += "        attribute, attribute, defaultname\n";
		      string += "             : \"Some -\"\n";
		      string += "        attribute, attribute, imagename\n";
		      string += "             : \"Arrow.gif\"\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarSoftgoalContribution\n";
		      string += "    IN OMEVisibleClass, OMELinkClass\n";
		      string += "    ISA IStarLink\n";
		      string += "    WITH\n";
		      string += "        attribute, attribute, imagename\n";
		      string += "             : \"Contribution.gif\"\n";
		      string += "        attribute, OMEAttributes\n";
		      string += "            label : IStarLinkLabelClass\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            height : 20\n";
		      string += "        attribute, attribute, imagesize\n";
		      string += "            width : 20\n";
		      string += "        attribute, name, attribute\n";
		      string += "             : \"i* Contribution link\"\n";
		      string += "        attribute, stroke, attribute\n";
		      string += "             : \"dashed\"\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass IStarLink\n";
		      string += "    ISA OMELink\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass OMEGrowableElement\n";
		      string += "    IN OMEGrowableElementClass\n";
		      string += "    ISA OMEElement\n";
		      string += "    WITH\n";
		      string += "        attribute, children\n";
		      string += "             : OMEElement\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass OMEElement\n";
		      string += "    IN OMEElementClass\n";
		      string += "    ISA OMEObject\n";
		      string += "    WITH\n";
		      string += "        attribute, parent\n";
		      string += "             : OMEElement\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass OMELink\n";
		      string += "    IN OMELinkClass\n";
		      string += "    ISA OMEObject\n";
		      string += "    WITH\n";
		      string += "        attribute, to\n";
		      string += "             : OMEObject\n";
		      string += "        attribute, from\n";
		      string += "             : OMEObject\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass OMEObject\n";
		      string += "    IN OMEObjectClass\n";
		      string += "    WITH\n";
		      string += "        attribute, links\n";
		      string += "             : OMELink\n";
		      string += "        attribute, OMEValueAttributes\n";
		      string += "            comment : String\n";
		      string += "        attribute, name\n";
		      string += "             : String\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass SerializedView\n";
		      string += "    WITH\n";
		      string += "        attribute, view_objects\n";
		      string += "             : Token\n";
		      string += "END\n";
		      string += "\n";
		      string += "SimpleClass SerializedObject\n";
		      string += "    WITH\n";
		      string += "        attribute, type\n";
		      string += "             : String\n";
		      string += "END\n";
		      string += "\n";
		      string += "MetaClass IStarElementLabelClass\n";
		      string += "    IN OMEAttributeMetaClass\n";
		      string += "    ISA OMEAttributeClass\n";
		      string += "END\n";
		      string += "\n";
		      string += "MetaClass IStarControlLabelClass\n";
		      string += "    IN OMEAttributeMetaClass\n";
		      string += "    ISA OMEAttributeClass\n";
		      string += "END\n";
		      string += "\n";
		      string += "MetaClass IStarFeatureLabelClass\n";
		      string += "    IN OMEAttributeMetaClass\n";
		      string += "    ISA OMEAttributeClass\n";
		      string += "END\n";
		      string += "\n";
		      string += "MetaClass IStarLinkLabelClass\n";
		      string += "    IN OMEAttributeMetaClass\n";
		      string += "    ISA OMEAttributeClass\n";
		      string += "END\n";
		      string += "\n";
		      string += "MetaClass IStarLinkClass\n";
		      string += "    ISA IStarClass, OMELinkClass\n";
		      string += "END\n";
		      string += "\n";
		      string += "MetaClass IStarActorElementClass\n";
		      string += "    ISA IStarElementClass\n";
		      string += "END\n";
		      string += "\n";
		      string += "MetaClass IStarIntentionalElementClass\n";
		      string += "    ISA IStarElementClass\n";
		      string += "END\n";
		      string += "\n";
		      string += "MetaClass IStarElementClass\n";
		      string += "    ISA IStarClass, OMEElementClass\n";
		      string += "END\n";
		      string += "\n";
		      string += "MetaClass IStarClass\n";
		      string += "    ISA OMEObjectClass\n";
		      string += "END\n";
		      string += "\n";
		      string += "MetaClass IStarContributionLabelClass\n";
		      string += "    IN OMEAttributeMetaClass\n";
		      string += "    ISA OMEAttributeClass\n";
		      string += "END\n";
		      string += "\n";
		      string += "MetaClass OMEInstantiableClass\n";
		      string += "    ISA OMEClass, OMEVisibleClass\n";
		      string += "    WITH\n";
		      string += "        attribute, autogui\n";
		      string += "             : Integer\n";
		      string += "END\n";
		      string += "\n";
		      string += "MetaClass OMEVisibleClass\n";
		      string += "    ISA OMEClass\n";
		      string += "    WITH\n";
		      string += "        attribute, imagename\n";
		      string += "             : String\n";
		      string += "        attribute, imagesize\n";
		      string += "             : Integer\n";
		      string += "        attribute, name\n";
		      string += "             : String\n";
		      string += "END\n";
		      string += "\n";
		      string += "MetaClass OMEGrowableElementClass\n";
		      string += "    ISA OMEObjectClass, OMEElementClass\n";
		      string += "END\n";
		      string += "\n";
		      string += "MetaClass OMEElementClass\n";
		      string += "    ISA OMEObjectClass\n";
		      string += "END\n";
		      string += "\n";
		      string += "MetaClass OMELinkClass\n";
		      string += "    ISA OMEObjectClass\n";
		      string += "    WITH\n";
		      string += "        attribute, stroke\n";
		      string += "             : String\n";
		      string += "END\n";
		      string += "\n";
		      string += "MetaClass OMEObjectClass\n";
		      string += "    ISA OMEClass\n";
		      string += "    WITH\n";
		      string += "        attribute, OMEValueAttributes\n";
		      string += "             : SClass\n";
		      string += "        attribute, defaultname\n";
		      string += "             : String\n";
		      string += "        attribute, OMEAttributes\n";
		      string += "             : OMEAttributeMetaClass\n";
		      string += "END\n";
		      string += "\n";
		      string += "MetaClass OMEClass\n";
		      string += "END\n";
		      string += "\n";
		      string += "MetaClass OMEAttributeClass\n";
		      string += "    WITH\n";
		      string += "        attribute, imageposition\n";
		      string += "             : Integer\n";
		      string += "        attribute, imagename\n";
		      string += "             : String\n";
		      string += "        attribute, imagesize\n";
		      string += "             : Integer\n";
		      string += "        attribute, name\n";
		      string += "             : String\n";
		      string += "END\n";
		      string += "\n";
		      string += "MetaMetaClass OMEAttributeMetaClass\n";
		      string += "END\n";
		      string += "\n";			
		}
		return string;
	}

	protected void preprocessAllInheritance(Advice parent, Advice a) {
		if (a == null) {
			return;
		}		
		preprocess_abbreviations(parent, a);
		if (a.how!=null && a.how.advices!=null) {
			for (Iterator i = a.how.advices.iterator(); i.hasNext();) {
				Advice ad = (Advice) i.next();
				preprocessAllInheritance(a, ad);
			}
		}		
    }
	/**
	 * Remove the extra quotes for a string
	 * 
	 * @param s,
	 *            the input string
	 * @return
	 */
	static String strip_quote(String s) {
		if (s == null)
			return s;
		String str = "";
		for (int i=0; i<s.length(); i++)
			if (s.charAt(i) != '"')
				str += s.charAt(i);
		return str;
	}
	
	/**
	 * @param who
	 * @return
	 */
	protected static String agent_context(String who) {
		String context = strip_quote(who);
		if (context !=null 
				&& !context.startsWith("Aspect ")
				&& !context.startsWith("Actor ")
				&& !context.startsWith("Role ")
				&& !context.startsWith("Position ")
				&& !context.startsWith("Agent ")
				) {
			context = "Agent " + context;
		}
		return context;
	}	
	/**
	 * @param e
	 * @return
	 */
	protected static String get_goal_name(Effect e) {		
		return Computing.unique_goal_name(e.e.who, e.e.why, e.e.what);
	}	
	/**
	 * Recursively generating the subgoals
	 * @param a:
	 *            Advice
	 */
	protected void generateGoalModel(Advice parent, Advice a) {
		if (a == null) {
			return;
		}
		generateWhen(a);
		generateWho(a);
		IStarElement g = (IStarElement) generateWhy(parent, a);
		generateWhere(g, parent, a);
		generateHow(a);
		generateHowmuch(g, a);
	}
	/**
	 * Recursively generating the subgoals
	 * 
	 * @param a:
	 *            Advice
	 */
	protected void generateGoalModelForElements(Advice parent, Advice a) {
		if (a == null) {
			return;
		}
		generateWhoForElement(a);
		IStarElement g = (IStarElement) generateWhyForElement(parent, a);
		generateWhenForElement(g, a);
		generateWhereForElement(a);
		generateHowForElement(a);
		generateHowmuchForElement(g, a);
	}
	
	String [] types = {"Do", "May", "Agent", "Role", "Position", "Actor", "Aspect"};
	/**
	 * @param a
	 */
	protected void generateHowmuchForElement(IStarElement g, Advice a) {
		if (a.how_much == null || a.how_much.size()<= 0) {
			return;
		}
		for (int i = 0; i < a.how_much.size(); i++) {
			Effect e = (Effect) a.how_much.get(i);
			if (e.e.alias!=null) {
				e.e.why = (String) Entity.aliases.get(e.e.alias);
			}
			String op = e.op;
			boolean isAspect = (e.e.who==null);
//			System.out.println(isAspect);
			IStarElement agent = create_agent_for_new_who(a, e);
			IStarElement g1 = add_goal(e.e.who,	e.e.why, e.e.what);
//			System.out.println(g1.name + " created");
			if (!op.equals("~")) {
				if (g1!=null) {
  				    g1.setSoftGoal();
  				    if (!Computing.propertyHolds("q7.codegen.istar.create_aspects")) {
  				    	g1.parent = g.parent;
  				    	g.parent.children.add(g1);
  				    }
				}
			}
			if (agent!=null) {
//				System.out.println(agent.name + " created");
				agent.isAspect = isAspect;
				g1.parent = agent;
				agent.children.add(g1);
			}
			if (g.parent!=null && g.parent!=g1.parent) {
			  if (op.equals("~") 
					  && Computing.propertyHolds("q7.codegen.creating_dependencies")) {
				IStarElement g2 = add_goal("", e.e.why ==null? a.e.why: e.e.why, e.e.why==null? a.e.what : e.e.what);
				g2.parent = null;
				g2.isSoftGoal = g1.isSoftGoal;
			  } 
			}
		}
	}

	/**
	 * @param who, why, what
	 * @return
	 */
	protected IStarElement add_goal(String who, String why, String what) {
//		System.out.println(who + why + what);
		String rest = Computing.unique_goal_name(who, why, what);
		Advice ad = intentions.get(rest);
		if (ad == null) {
			ad = new Advice("", who, why, what, null, null, null, null, null, null);
			int m = elements.size();
			ad.setID(m);
			IStarElement g1 = new IStarElement(m, rest, "");
			elements.put(ad, g1);
			g1.isAgent = false;
			intentions.put(rest, ad);
			serialize_the_token(m, false, false);
			return g1;
		} else {
			return elements.get(ad);
		}
	}

	/**
	 * @param a
	 */
	private void generateHowForElement(Advice a) {
		if (a.how != null) {
			for (Iterator i = a.how.advices.iterator(); i.hasNext();) {
				Advice ad = (Advice) i.next();
				generateGoalModelForElements(a, ad);
			}
		}
	}

	/**
	 * @param a
	 */
	private void generateWhereForElement(Advice a) {
	}

	public IStarElement create_agent_for_new_who(Advice parent, Effect e) {
		IStarElement agent = null;
		if (e.e.who!=null && e.e.who.indexOf("Aspect ")>=0) {
			e.e.who = null;
		}
		if (e.e.who!=null && 
			(parent==null || parent.e.who==null || 
				!e.e.who.equalsIgnoreCase(parent.e.who))) {
			agent = add_agent(e.e.who);
		}				
		return agent;
	}	/**
	 * Create an agent for any new context. If the context is the same as its parent,
	 * then the routine has a side effect: update a.who with parents' who if a.who = null
	 */
	public IStarElement create_agent_for_new_who_inherit(Advice parent, Advice a) {
		IStarElement agent = null;
		if (parent!=null && parent.e.who!=null 
				&& (a.e.who == null 
				|| parent.e.who.equalsIgnoreCase(a.e.who))) 
			// inheriting the context
		{
			String pcontext = agent_context(parent.e.who);
			a.e.who = parent.e.who;
			agent = agents.get(pcontext);
		} else if (a.e.who!=null && (parent==null || parent.e.who==null || !a.e.who.equalsIgnoreCase(parent.e.who))) {
			agent = add_agent(a.e.who);
		}				
		return agent;
	}
	


	/**
	 * @param who
	 * @return
	 */
	private IStarElement add_agent(String who) {
		IStarElement agent = null;
		String context = agent_context(who);
		agent = agents.get(context);
		if (agent==null) {
			int id = elements.size();
			agent = new IStarElement(id, context, "");
			agent.isAgent = true;
			Advice x = new Advice(null, null, null, null, null, null, null, null, null, null); 
			elements.put(x, agent);
			agents.put(context, agent);
			serialize_the_token(id, false, true);
		}
		return agent;
	}

	/**
	 * @param parent
	 * @param a
	 */
	private Object generateWhyForElement(Advice parent, Advice a) {
		if (a.e.who!=null && a.e.who.indexOf("Aspect ")>=0) {
			a.e.who = null;
		}
		if (a.e.alias!=null) {
			a.e.why = (String) Entity.aliases.get(a.e.alias);
		}
		boolean is_new_who = 
			parent!=null && a!=null && a.e.who!=null && parent.e.who!=null && !parent.e.who.equalsIgnoreCase(a.e.who);
		IStarElement agent = create_agent_for_new_who_inherit(parent, a);
		IStarElement g = add_goal(a.e.who, a.e.why, a.e.what);
		if (a.label!=null) {
			g.setLabel(a.label);
		}
		if (agent!=null) {
			g.parent = agent;
			agent.children.add(g);
			if (a.e.who!=null && a.e.who.indexOf("Aspect ")>=0) {
				agent.isAspect = true;
			}
		}
		if (parent!=null && is_new_who) {
			IStarElement pg = add_goal(parent.e.who, parent.e.why, parent.e.what);
			IStarElement g2 = add_goal("", a.e.why, a.e.what);
			g2.parent = null;
			IStarElement g3 = add_goal(pg.parent.name, a.e.why, a.e.what);
			g3.parent = pg.parent;
			g3.parent.children.add(g3);					
		}
		return g;
	}

	/**
	 * @param a
	 */
	private void generateWhoForElement(Advice a) {
	}

	/**
	 * @param a
	 */
	protected void generateWhenForElement(IStarElement g, Advice a) {
		if (a.when!=null) {
			IStarElement g1 = add_goal(null, "Claim: " + a.when, null); // the claim
			add_link(g, g1, a.op);
		}
	}

	public void generateGoalModel(String out_file) {
		process_q7_model(out_file);
		output_serialized_views_and_objects();
	}

	private void mark_prefixed_subgoals() {
		for (Enumeration<String> i = intentions.keys(); i.hasMoreElements();) {
			String k = i.nextElement();
		    Advice a = intentions.get(k);
		    if (a!=null) {
		    	IStarElement g = elements.get(a);
			    if (g!=null) {
			    	if (g.name.indexOf("May ")>=0) {
			    		g.setSoftGoal();
			    	}
			    }
		    }
		}
	}

	/**
	 * If a goal has more than one parents, then it will be duplicated
	 */
	private void duplicate_high_fan_in_goals() {
		for (Enumeration<String> i = intentions.keys(); i.hasMoreElements();) {
			String k = i.nextElement();
		    Advice a = intentions.get(k);
		    if (a!=null) {
		    	IStarElement g = elements.get(a);
			    if (g!=null && ! g.isSoftGoal) {
			    	Vector<IStarLink> l = new Vector<IStarLink>(); // links
					for (Enumeration j = links.keys(); j.hasMoreElements();) {
						IStarLink s = links.get(j.nextElement());
						if (s.to.id == g.id && s.isDecomposition()) {
							l.add(s);
						}
					}
					if (l.size()>1) {
						Enumeration<IStarLink> j = l.elements();
						j.nextElement(); // skip the first link
						int cnt = 1;
						while (j.hasMoreElements()) {
//							D.o("found a high fan in goal");
							IStarLink s = j.nextElement();
							Advice ad = intentions.get(s.to.name);
							IStarElement e = 
								add_goal(ad.e.who, ad.e.why + "#" + cnt, ad.e.what);
							s.to = e;
							cnt++;
						}						
					}
			    }
		    }
		}
	}

	int cnt = 0;
	/**
	 * replace .. with parent's name 
	 */
	private void preprocess() {
		for (Iterator i = advices.iterator(); i.hasNext();) {
			Advice a = (Advice) i.next();
			preprocessAllInheritance(null, a);
		}
	}

	/**
	 * Yijun Yu: Feb 27, 2005
	 * To create the dependency elements to connect two agents A, B
	 * based on the strategic dependency patterns:
	 * remove all self-dependencies 
	 */
	protected void create_dependencies() {
		remove_self_dependencies();
	}

	/**
	 * 
	 */
	private void remove_self_dependencies() {
		// remove cyclic dependencies
		for (Enumeration i = links.keys(); i.hasMoreElements();) {
			IStarLink l = links.get(i.nextElement());
			if (l.type.equals("Dep")) {
				IStarElement a = l.to;
				IStarElement b = l.from;
				if (a.isAgent && a == b.parent
					|| b.isAgent && b==a.parent) { 
					links.remove(b.name + "Dep" + a.name);
					continue;
				}
			}
		}
	}

	/**
	 * @param out_file
	 */
	protected void create_elements_and_links(String out_file) {
		try {
			out = new PrintStream(new FileOutputStream(new File(out_file)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        // Mar 11, 2005 Yijun: to offset the elements id by one as Visio does not support a shape with
		// a zero id.
		elements.put(new Advice(null, null, null, null, null, null, null, null, null, null), 
				new IStarElement(0, null, null)); 
		serialized_view += "Token SerializedView_0\n"
				+ "    IN SerializedView\n" + "    WITH\n";
		// the first pass creates the elements only, to avoid duplications
		for (Iterator i = advices.iterator(); i.hasNext();) {
			Advice a = (Advice) i.next();
			generateGoalModelForElements(null, a);
		}
		// the second pass creates the links only, to make sure no extra links are created
		for (Iterator i = advices.iterator(); i.hasNext();) {
			Advice a = (Advice) i.next();
			generateGoalModel(null, a);
		}
	}

	/**
	 * 
	 */
	private void output_serialized_views_and_objects() {
		assign_link_labels();		
		// now output the serialized objects and serialized view
		for (Iterator i = serialized_tokens.iterator(); i.hasNext();) {
			String s = (String) i.next();
			out.println(s);
		}		
		for (Enumeration i = links.keys(); i.hasMoreElements();) {
			IStarLink s = links.get(i.nextElement());			
			out.println(s.toString());
		}
		SortArrayList sorted_keys = new SortArrayList();
		for (Enumeration<IStarElement> i = elements.elements(); i.hasMoreElements(); ) {
			IStarElement p = i.nextElement();
			sorted_keys.add((Comparable)p);
		}
		for (int i=0; i<sorted_keys.size(); i++) {
			IStarElement e = (IStarElement) sorted_keys.get(i);
			if (e.id>0) {
				out.println(e.toString());
			}
		}
		out.print(serialized_view + "END\n");
		String TELOS_CLASS_MODEL = get_file_contents();
		out.print(TELOS_CLASS_MODEL);
	}

	/**
	 * 
	 */
	private void assign_link_labels() {
		// assign_link_labels
		int id = serialized_tokens.size();
		for (Enumeration i = links.keys(); i.hasMoreElements();) {
			IStarLink l = links.get(i.nextElement());
			serialize_the_token(id, true, false);
			l.from.decompositions += "        attribute, links\n             : Link_"
				+ id + "\n";
			l.to.decompositions += "        attribute, links\n             : Link_"
				+ id + "\n";
			l.id = id;
			id++;
		}
	}

	/**
	 * all parent goals of softgoals are softgoals
	 */
	protected void mark_parent_goal_of_softgoal_as_softgoal() {
		boolean change = true;
		do {
			change = false;
			for (Enumeration i = links.keys(); i.hasMoreElements();) {
				IStarLink s = links.get(i.nextElement());
				if (s.isDecomposition()) {
					if (s.from.isSoftGoal && !s.to.isSoftGoal || s.to.isSoftGoal && !s.from.isSoftGoal) {
						s.from.setSoftGoal(); s.to.setSoftGoal();
						change = true;
					}
				}
			}
		} while (change);
	}

	/**
	 * 
	 */
	protected void create_aspect() {
		boolean change;
		// root softgoals will be used to create an aspect
		 for (Enumeration<String> i = intentions.keys(); i.hasMoreElements();) {
 			String k = i.nextElement();
		    Advice a = intentions.get(k);
		    if (a!=null) {
		    	IStarElement g = elements.get(a);
			    if (g!=null && g.isSoftGoal) {
			    	boolean root = true;
					for (Enumeration j = links.keys(); j.hasMoreElements();) {
						IStarLink s = links.get(j.nextElement());
						if (s.to.id == g.id && s.isDecomposition()) {
							root = false;
							break;
						}
					}
					if (root) { // create a new aspect
						if (g.parent==null) {
							String name = Computing.strip_type_prefixes(types, g.name);
							IStarElement asp = agents.get(agent_context(Computing.prepend("Aspect ", name)));
							if (asp == null) {
								String t = Computing.prepend("Aspect ", name);
								asp = add_agent(t);
								asp.isAspect = true;
								g.parent = asp;
								asp.children.add(g);
							}
						}
					}
			    }
		    }
		}		
		// ... and all its sub-softgoals will be moved into the aspect
		// 		as their context will be the same as their parents
		// all subgoals of softgoals are softgoals
		change = true;
		do {
			change = false;
			for (Enumeration i = links.keys(); i.hasMoreElements();) {
				IStarLink s = links.get(i.nextElement());
				if (s.isDecomposition()) { // merge subgoal into its parent goal's context (agent/aspect) 
					if (s.from.parent!=null && (s.to.parent==null /*|| s.from.parent.id != s.to.parent.id */)) {
						if (s.to.parent!=null) {
							s.to.parent.children.remove(s.to);
						}
						s.to.parent = s.from.parent;
						s.from.parent.children.add(s.to);
						change = true;
					}
				}
				if (s.isOrDecomposition()) {
					if (s.from.isSoftGoal && !s.to.isSoftGoal || s.to.isSoftGoal && !s.from.isSoftGoal) {
						s.from.setSoftGoal(); s.to.setSoftGoal();
						change = true;
					}
				}
			}
		} while (change);
	}

	/**
     * leaf goals are operationalized into tasks
	 */
	private void mark_leaf_or_ANDdecomposed_goal_as_task() {
		for (Enumeration<String> i = intentions.keys(); i.hasMoreElements();) {
			String k = i.nextElement();
		    Advice a = intentions.get(k);
		    if (a!=null) {
		    	IStarElement g = elements.get(a);
			    if (g!=null && !g.isSoftGoal && !g.isAgent && !g.isAspect) {
			    	boolean leaf = true;
			    	boolean and_decomposed = true;
					for (Enumeration j = links.keys(); j.hasMoreElements();) {
						IStarLink s = links.get(j.nextElement());
						if (s.from.id == g.id && s.isDecomposition()) {
							leaf = false;
							if (s.isOrDecomposition()) {
								and_decomposed = false;
								break;
							}
							break;
						}
					}
					if (leaf) {
						g.isOperationalization = true;
					}
					if (and_decomposed) {
						g.isTask = true;
					}
			    }
		    }
		}
	}

	/**
	 * 
	 */
	protected void mark_subgoal_of_task_as_task() {
		boolean change;
		// subgoals of tasks are also tasks 
		do {
			change = false;
			for (Enumeration<String> i = intentions.keys(); i.hasMoreElements();) {
				String k = i.nextElement();
			    Advice a = intentions.get(k);
			    if (a!=null) {
			    	IStarElement g = elements.get(a);
				    if (g!=null) {
						for (Enumeration j = links.keys(); j.hasMoreElements();) {
							IStarLink s = links.get(j.nextElement());
							if (s.to.id == g.id && s.from.isOperationalization && !g.isOperationalization && s.isDecomposition()) {
								g.isOperationalization = true;
								change = true;
								break;
							}
						}
				    }
			    }
			}
		} while (change);
	}
	/**
	 * 
	 */
	protected void mark_subgoal_of_softgoal_as_softgoal() {
		boolean change;
		// subgoals of tasks are also tasks 
		do {
			change = false;
			for (Enumeration<String> i = intentions.keys(); i.hasMoreElements();) {
				String k = i.nextElement();
			    Advice a = intentions.get(k);
			    if (a!=null) {
			    	IStarElement g = elements.get(a);
				    if (g!=null) {
						for (Enumeration j = links.keys(); j.hasMoreElements();) {
							IStarLink s = links.get(j.nextElement());
							if (s.to.id == g.id && s.from.isSoftGoal && !g.isSoftGoal&& s.isDecomposition()) {
								g.isSoftGoal= true;
								change = true;
								break;
							}
						}
				    }
			    }
			}
		} while (change);
	}

	/**
	 * @param a
	 */
	protected void generateHowmuch(IStarElement ag, Advice a) {
		if (a.how_much != null && a.how_much.size() > 0) {
			int n = a.how_much.size();
			for (int i = 0; i < n ; i++) {
				Effect e = (Effect) a.how_much.get(i);
				if (e.e.alias!=null) {
					e.e.why = (String) Entity.aliases.get(e.e.alias);
				}
				String op = e.op;
				IStarElement g1 = get_goal(e.e.who, e.e.why, e.e.what);
				if (g1!=null && (ag.parent==g1.parent || Computing.propertyHolds("q7.codegen.istar.create_aspects"))) {
					add_link(g1, ag, op);
				} else if (op.equals("~") && Computing.propertyHolds("q7.codegen.creating_dependencies")){
					if (ag.parent==null || g1.parent==null 
							|| ag.parent.name.indexOf("Aspect")>=0 
							|| g1.parent.name.indexOf("Aspect")>=0) {
						add_link(g1, ag, op);
					} else {
						IStarElement g2 = get_goal("",  e.e.why==null? a.e.why : e.e.why, e.e.why==null? 
								a.e.what: e.e.what);
						add_link(g1, g2, op);
						add_link(g2, ag, op);
					}
				} else if (ag.parent!=null && Computing.propertyHolds("q7.codegen.creating_dependencies")){
					IStarElement g2 = get_goal("", e.e.why==null? a.e.why : e.e.why, e.e.why==null? a.e.what: e.e.what);
					IStarElement g3 = get_goal(ag.parent.name,  e.e.why==null? a.e.why : e.e.why, e.e.why==null? a.e.what: e.e.what);
					if (op.startsWith("~") ) {
						add_link(ag, g3, op);
						add_link(g2, g3, op);
						add_link(g1, g2, op);
						g1.label = ag.label;
						g2.label = ag.label;
						g3.label = ag.label;
					} else {
						add_link(g3, ag, op);							
						if (Computing.propertyHolds("q7.codegen.istar.create_aspects")) {
							add_link(g3, g2, "~");
							add_link(g2, g1, "~");
						}
					}
				} else {
//					System.out.println("link added: " + g1.name + op + ag.name);
					add_link(g1, ag, op);							
				}
			}
		}
	}

	/**
	 * @param sg -- the advice goal
	 * @param pa -- the current advice
	 * @param a -- the pointcut advice
	 */
	protected void generateWhere(IStarElement sg, Advice pa, Advice a) {
		if (a.where == null || a.where.size()==0) {
			return;
		}
		if (sg == null) {
			return;
		}
		for (Enumeration<String> e = intentions.keys(); e.hasMoreElements(); ) {
			String k = e.nextElement();
			if (k!=null) {
				Advice ad = intentions.get(k);
				for (int i=0; i<a.where.size(); i++) {
					Pointcut p = (Pointcut) a.where.get(i);
					/* still quite simple */
					if (p!=null
						&& (ad.e.who!=null && (p.e.who.equals("*") || ad.e.who.equalsIgnoreCase(p.e.who))
								|| ad.e.who == null && p.e.who.equals("*"))
					    && ad.e.why!=null && (p.e.why.equals("*") || ad.e.why.equalsIgnoreCase(p.e.why))
						&& (ad.e.what!=null && (p.e.what.equals("*") || ad.e.what.equalsIgnoreCase(p.e.what)
								||ad.e.what.contains(p.e.what + ",") || ad.e.what.contains("," + p.e.what))
							    || ad.e.what == null && p.e.what.equals("*")))
					{
						// weaving when matched: create link from hg to sg
						IStarElement hg = elements.get(ad);
						if (p.op.equals("&") || p.op.equals("|")) { 
							 // aspects can be functional
							sg.isSoftGoal = hg.isSoftGoal;
							add_link(hg, sg, p.op); 
						} else { // aspects are non-functional 
							sg.setSoftGoal();
							if (hg.parent!=null && hg.parent.name.indexOf("Aspect ")<0 
									&& Computing.propertyHolds("q7.codegen.creating_dependencies")) { 
								// crosscutting a component (agent), not an aspect
								IStarElement g2 = get_goal("", a.e.why, a.e.what);
								if (g2==null || g2 == sg) {
									g2 = add_goal("", a.e.why, a.e.what);
									g2.parent = null;
									g2.setSoftGoal();
								} 
								IStarElement g3 = get_goal(hg.parent.name, a.e.why, a.e.what);
								if (g3==null) {
									g3 = add_goal(hg.parent.name, a.e.why, a.e.what);
									g3.parent = hg.parent;
									hg.parent.children.add(g3);
								}								
								g3.setSoftGoal();
								add_link(g3, hg, p.op);
								if (g2!=sg) {
									add_link(g2, sg, "~");
									add_link(g3, g2, "~");									
								} else {
									add_link(g3, sg, "~");
								}
							} else {
								add_link(sg, hg, p.op);								
							}
						}
						break;
					}					
				}
			}
		}				
	}

	/**
	 * @param a
	 */
	private void generateWho(Advice a) {
	}

	/**
	 * @param a
	 */
	private void generateWhen(Advice a) {
	}

	/**
	 * @param a
	 */
	private void generateHow(Advice a) {
		if (a.how != null) {
			for (Iterator i = a.how.advices.iterator(); i.hasNext();) {
				Advice ad = (Advice) i.next();
				generateGoalModel(a, ad);
			}
		}
	}

	
	IStarElement thegoal = null;
	/**
	 * @param a
	 */
	protected Object generateWhy(Advice parent, Advice a) {
		if (a.e.who!=null && a.e.who.indexOf("Aspect ")>=0) {
			a.e.who = null;
		}
		String alias = a.e.alias;
		while (alias!=null && Entity.aliases.get(alias)!=null) {
			alias = (String) Entity.aliases.get(alias);
		}
		if (alias!=null) {
			a.e.why = alias;
		}
		IStarElement ag = get_goal(a.e.who, a.e.why, a.e.what);
		if (a.how !=null && a.how.enrich!=null) {
			if (a.how.enrich.equals("|") || a.how.enrich.equals("/")) {
				ag.setFeature(a.how.enrich);
			}
			if (a.how.enrich.equals(";") || a.how.enrich.equals("||")) {
				ag.setControl(a.how.enrich);
			}
		}
		if (parent!=null)
		{
			IStarElement pg = get_goal(parent.e.who, parent.e.why, parent.e.what);
			if (pg.parent == null || pg.parent == ag.parent /*|| ag.parent==null */) {
				add_link(pg, ag, parent.how.rule);
				if (pg.parent==null && ag.parent!=null) {
					pg.parent = ag.parent;
					ag.parent.children.add(pg);
				}
			} else if (Computing.propertyHolds("q7.codegen.creating_dependencies")) {
				IStarElement g3 = get_goal(pg.parent.name, a.e.why, a.e.what);
				add_link(pg, g3, parent.how.rule);
//				if (Computing.propertyHolds("q7.codegen.istar.create_aspects")) {
					IStarElement g2 = get_goal("", a.e.why, a.e.what);
					if (ag!=null && ag.name!=null && g2!=null && !ag.name.equals(g2.name)) {
						add_link(ag, g2, "~");
					}
					if (g2!=null && g2.name!=null && g3!=null && !g2.name.equals(g3.name)) {
						add_link(g2, g3, "~"); 
//				}
					}
			}
		}
		return ag;
	}

	/**
	 * @param parent
	 * @param a
	 */
	public void preprocess_abbreviations(Advice parent, Advice a) {
		// preprocess the abbreviations
		try {
			if (a.e.why!=null) {
				int i = a.e.why.indexOf("..");
				boolean modified = (i>=0);
				if (modified && Computing.propertyHolds("q7.codegen.expand_placeholder_into_parent_names")) {
					if (parent!=null && parent.e.why!=null) {
						String old_why = a.e.why;
						while (i>=0) {
							String p1 = a.e.why.substring(0, i);
							String p2 = a.e.why.substring(i+2);
							a.e.why = Computing.strip_quote(p1) + 
									  Computing.strip_quote(parent.e.why) + 
									  Computing.strip_quote(p2);
							i = a.e.why.indexOf("..");
						}
						a.e.update(old_why);
					} else if (a.e.alias!=null) {
						a.e.why = (String) Entity.aliases.get(a.e.alias);
					}
				} 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param who
	 * @param why
	 * @param what
	 * @return
	 */
	protected IStarElement get_goal(String who, String why, String what) {
		String name = Computing.unique_goal_name(who, why, what);
		Advice ad = intentions.get(name);
		if (ad==null) {
			return null;
		}
		return elements.get(ad);
	}

	/**
	 * @param to
	 * @param from
	 * @param rule
	 */
	protected void add_link(IStarElement to, IStarElement from, String rule) {
		if (to == null || from==null) {
			return;
		}
		if (to.id == from.id) {
			return;
		}
		String op = "";
		if (rule.equalsIgnoreCase("&")) {
			op = "And";
		} else if (rule.equalsIgnoreCase("|")) {
			op = "Or";
		} else if (rule.equalsIgnoreCase("++")) {
			op = "Make";
		} else if (rule.equalsIgnoreCase("--")) {
			op = "Break";
		} else if (rule.equalsIgnoreCase("=")) {
			op = "Equal";
		} else if (rule.startsWith("~")) {
			op = "Dep" + (rule.length()>1?" " + rule.substring(1):"");
		} else if (rule.startsWith("+")) {
			op = "Help" + (rule.length()>1?" " + rule.substring(1):"");
		} else if (rule.startsWith("-")) {
			op = "Hurt" + (rule.length()>1?" " + rule.substring(1):"");
		} else {
			op = "Unknown";
		}
		links.put(to.name + op + from.name, new IStarLink(op, to, from));
	}	
	boolean NEED_EXPAND = Computing.propertyHolds("q7.codegen.expand_actors");
	/**
	 * @param linkid
	 */
	private void serialize_the_token(int id, boolean isLink, boolean isExpanded) {
		int n;
		n = serialized_tokens.size();
		serialized_tokens
				.add("Token SerializedViewObject_0_"
						+ n
						+ "\n"
						+ "     IN SerializedObject \n"
						+ "     WITH\n"
						+ "          attribute, ID\n            : "
						+ id
						+ "\n"
						+ "          attribute, objecttype\n            : "
						+ (isLink ? 0 : 1)
						+ "\n"
						+ "          attribute, type\n            : \"edu.toronto.cs.ome.OME.GraphicView$"
						+ (isLink ? "GVLRecord" : "GVERecord")
						+ "\"\n"
						+ "          attribute, x\n            : 0.0\n"
						+ "          attribute, y\n            : 0.0\n"
						+ "          attribute, control1x\n            : 10.0\n"
						+ "          attribute, control1y\n            : 10.0\n"
						+ "          attribute, control2x\n            : 100.0\n"
						+ "          attribute, control2y\n            : 100\n"
						+ "          attribute, start2x\n            : 20.0\n"
						+ "          attribute, start2y\n            : 20.0\n"
						+ "          attribute, end1x\n            : 80.0\n"
						+ "          attribute, end1y\n            : 80.0\n"
						+ "          attribute, expanded\n            : " 
						+ (isExpanded && NEED_EXPAND? "1": "0")  // do not expand the agents
						+ "\n"
						+ "          attribute, hidden\n            : 0\n"
						+ "          attribute, justification\n            : "
						+ (isLink ? 2 : 0) + "\n"
						+ "          attribute, highlight\n            : 0\n"
						+ "END\n");
		serialized_view += "        attribute,view_objects\n"
				+ "            : SerializedViewObject_0_" + n + "\n";
	}

	/* (non-Javadoc)
	 * @see edu.toronto.cs.q7.TelosCodeGenerator#getMetaModel()
	 */
	protected String getMetaModel() {
		return "/edu/toronto/cs/q7/metamodel/istar.tel";
	}

	protected void process_q7_model(String out_file) {
		if (advices == null) {
			System.out.println("No model parsed?");
			return;
		}
		preprocess();		
		create_elements_and_links(out_file);		
		mark_prefixed_subgoals();
		mark_parent_goal_of_softgoal_as_softgoal();
		mark_subgoal_of_softgoal_as_softgoal();
		if (Computing.propertyHolds("q7.codegen.istar.create_aspects")) {
			create_aspect();
		}
		if (Computing.propertyHolds("q7.codegen.istar.marking_istar_tasks")) {
			mark_leaf_or_ANDdecomposed_goal_as_task();
			mark_subgoal_of_task_as_task();
		}
		if (Computing.propertyHolds("q7.codegen.istar.create_new_goal_with_same_name")) {
			duplicate_high_fan_in_goals();
		}
		if (Computing.propertyHolds("q7.codegen.creating_dependencies")) {
			create_dependencies();
		}
	}
	
}

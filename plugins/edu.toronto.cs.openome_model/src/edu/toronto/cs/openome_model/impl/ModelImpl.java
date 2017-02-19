/**
 * Copyright 2001-2008 University of Toronto
 *
 * $Id$
 */
package edu.toronto.cs.openome_model.impl;

import edu.toronto.cs.openome_model.Actor;
import edu.toronto.cs.openome_model.Agent;
import edu.toronto.cs.openome_model.Alternative;
import edu.toronto.cs.openome_model.AndDecomposition;
import edu.toronto.cs.openome_model.Association;
import edu.toronto.cs.openome_model.Container;
import edu.toronto.cs.openome_model.Contribution;
import edu.toronto.cs.openome_model.Correlation;
import edu.toronto.cs.openome_model.Decomposition;
import edu.toronto.cs.openome_model.Dependency;
import edu.toronto.cs.openome_model.Goal;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Model;
import edu.toronto.cs.openome_model.OrDecomposition;
import edu.toronto.cs.openome_model.Position;
import edu.toronto.cs.openome_model.Resource;
import edu.toronto.cs.openome_model.Role;
import edu.toronto.cs.openome_model.Softgoal;
import edu.toronto.cs.openome_model.Task;
import edu.toronto.cs.openome_model.openome_modelPackage;

import java.util.Collection;
import java.util.Iterator;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.toronto.cs.openome_model.impl.ModelImpl#getName <em>Name</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.ModelImpl#getIntentions <em>Intentions</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.ModelImpl#getContributions <em>Contributions</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.ModelImpl#getDependencies <em>Dependencies</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.ModelImpl#getDecompositions <em>Decompositions</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.ModelImpl#getContainers <em>Containers</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.ModelImpl#getCorrelations <em>Correlations</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.ModelImpl#getAssociations <em>Associations</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.ModelImpl#getAlternatives <em>Alternatives</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelImpl extends EObjectImpl implements Model {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright 2001-2008 University of Toronto";

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getIntentions() <em>Intentions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIntentions()
	 * @generated
	 * @ordered
	 */
	protected EList<Intention> intentions;

	/**
	 * The cached value of the '{@link #getContributions() <em>Contributions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContributions()
	 * @generated
	 * @ordered
	 */
	protected EList<Contribution> contributions;

	/**
	 * The cached value of the '{@link #getDependencies() <em>Dependencies</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependencies()
	 * @generated
	 * @ordered
	 */
	protected EList<Dependency> dependencies;

	/**
	 * The cached value of the '{@link #getDecompositions() <em>Decompositions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDecompositions()
	 * @generated
	 * @ordered
	 */
	protected EList<Decomposition> decompositions;

	/**
	 * The cached value of the '{@link #getContainers() <em>Containers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainers()
	 * @generated
	 * @ordered
	 */
	protected EList<Container> containers;

	/**
	 * The cached value of the '{@link #getCorrelations() <em>Correlations</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCorrelations()
	 * @generated
	 * @ordered
	 */
	protected EList<Correlation> correlations;

	/**
	 * The cached value of the '{@link #getAssociations() <em>Associations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociations()
	 * @generated
	 * @ordered
	 */
	protected EList<Association> associations;

	/**
	 * The cached value of the '{@link #getAlternatives() <em>Alternatives</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlternatives()
	 * @generated
	 * @ordered
	 */
	protected EList<Alternative> alternatives;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return openome_modelPackage.Literals.MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.MODEL__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Intention> getIntentions() {
		if (intentions == null) {
			intentions = new EObjectContainmentEList<Intention>(Intention.class, this, openome_modelPackage.MODEL__INTENTIONS);
		}
		return intentions;
	}
	
	/** @author jenhork
	 * The default getIntentions method only returns dependums.  This one should return all intentions in the model.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<Intention> getAllIntentions() {
			
		//this only gets dependums, the intentions not inside an actor
		//I can't just call getIntentions because I don't want that list of intentions, I want a new one 
		EList<Intention> allints = new BasicEList<Intention>(getIntentions());			
		
		//get the model containers, which are the actors.  
		//I could just use the containers variable, but getContainers instantiates it if it doesn't exist
		EList<Container> conts = getContainers();
		
		Iterator<Container> contsit = conts.iterator();
		
		while (contsit.hasNext()) {
			Container cont = contsit.next();
			
			//each container might have some intentions in it
			EList<Intention> ints = cont.getIntentions();
			
			allints.addAll(ints);
		}
		
		return allints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Contribution> getContributions() {
		if (contributions == null) {
			contributions = new EObjectContainmentWithInverseEList<Contribution>(Contribution.class, this, openome_modelPackage.MODEL__CONTRIBUTIONS, openome_modelPackage.CONTRIBUTION__MODEL);
		}
		return contributions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Dependency> getDependencies() {
		if (dependencies == null) {
			dependencies = new EObjectContainmentWithInverseEList<Dependency>(Dependency.class, this, openome_modelPackage.MODEL__DEPENDENCIES, openome_modelPackage.DEPENDENCY__MODEL);
		}
		return dependencies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Decomposition> getDecompositions() {
		if (decompositions == null) {
			decompositions = new EObjectContainmentWithInverseEList<Decomposition>(Decomposition.class, this, openome_modelPackage.MODEL__DECOMPOSITIONS, openome_modelPackage.DECOMPOSITION__MODEL);
		}
		return decompositions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Container> getContainers() {
		if (containers == null) {
			containers = new EObjectContainmentWithInverseEList<Container>(Container.class, this, openome_modelPackage.MODEL__CONTAINERS, openome_modelPackage.CONTAINER__MODEL);
		}
		return containers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Correlation> getCorrelations() {
		if (correlations == null) {
			correlations = new EObjectResolvingEList<Correlation>(Correlation.class, this, openome_modelPackage.MODEL__CORRELATIONS);
		}
		return correlations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Association> getAssociations() {
		if (associations == null) {
			associations = new EObjectContainmentEList<Association>(Association.class, this, openome_modelPackage.MODEL__ASSOCIATIONS);
		}
		return associations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Alternative> getAlternatives() {
		if (alternatives == null) {
			alternatives = new EObjectContainmentEList<Alternative>(Alternative.class, this, openome_modelPackage.MODEL__ALTERNATIVES);
		}
		return alternatives;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case openome_modelPackage.MODEL__CONTRIBUTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getContributions()).basicAdd(otherEnd, msgs);
			case openome_modelPackage.MODEL__DEPENDENCIES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDependencies()).basicAdd(otherEnd, msgs);
			case openome_modelPackage.MODEL__DECOMPOSITIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDecompositions()).basicAdd(otherEnd, msgs);
			case openome_modelPackage.MODEL__CONTAINERS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getContainers()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case openome_modelPackage.MODEL__INTENTIONS:
				return ((InternalEList<?>)getIntentions()).basicRemove(otherEnd, msgs);
			case openome_modelPackage.MODEL__CONTRIBUTIONS:
				return ((InternalEList<?>)getContributions()).basicRemove(otherEnd, msgs);
			case openome_modelPackage.MODEL__DEPENDENCIES:
				return ((InternalEList<?>)getDependencies()).basicRemove(otherEnd, msgs);
			case openome_modelPackage.MODEL__DECOMPOSITIONS:
				return ((InternalEList<?>)getDecompositions()).basicRemove(otherEnd, msgs);
			case openome_modelPackage.MODEL__CONTAINERS:
				return ((InternalEList<?>)getContainers()).basicRemove(otherEnd, msgs);
			case openome_modelPackage.MODEL__ASSOCIATIONS:
				return ((InternalEList<?>)getAssociations()).basicRemove(otherEnd, msgs);
			case openome_modelPackage.MODEL__ALTERNATIVES:
				return ((InternalEList<?>)getAlternatives()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case openome_modelPackage.MODEL__NAME:
				return getName();
			case openome_modelPackage.MODEL__INTENTIONS:
				return getIntentions();
			case openome_modelPackage.MODEL__CONTRIBUTIONS:
				return getContributions();
			case openome_modelPackage.MODEL__DEPENDENCIES:
				return getDependencies();
			case openome_modelPackage.MODEL__DECOMPOSITIONS:
				return getDecompositions();
			case openome_modelPackage.MODEL__CONTAINERS:
				return getContainers();
			case openome_modelPackage.MODEL__CORRELATIONS:
				return getCorrelations();
			case openome_modelPackage.MODEL__ASSOCIATIONS:
				return getAssociations();
			case openome_modelPackage.MODEL__ALTERNATIVES:
				return getAlternatives();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case openome_modelPackage.MODEL__NAME:
				setName((String)newValue);
				return;
			case openome_modelPackage.MODEL__INTENTIONS:
				getIntentions().clear();
				getIntentions().addAll((Collection<? extends Intention>)newValue);
				return;
			case openome_modelPackage.MODEL__CONTRIBUTIONS:
				getContributions().clear();
				getContributions().addAll((Collection<? extends Contribution>)newValue);
				return;
			case openome_modelPackage.MODEL__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection<? extends Dependency>)newValue);
				return;
			case openome_modelPackage.MODEL__DECOMPOSITIONS:
				getDecompositions().clear();
				getDecompositions().addAll((Collection<? extends Decomposition>)newValue);
				return;
			case openome_modelPackage.MODEL__CONTAINERS:
				getContainers().clear();
				getContainers().addAll((Collection<? extends Container>)newValue);
				return;
			case openome_modelPackage.MODEL__CORRELATIONS:
				getCorrelations().clear();
				getCorrelations().addAll((Collection<? extends Correlation>)newValue);
				return;
			case openome_modelPackage.MODEL__ASSOCIATIONS:
				getAssociations().clear();
				getAssociations().addAll((Collection<? extends Association>)newValue);
				return;
			case openome_modelPackage.MODEL__ALTERNATIVES:
				getAlternatives().clear();
				getAlternatives().addAll((Collection<? extends Alternative>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case openome_modelPackage.MODEL__NAME:
				setName(NAME_EDEFAULT);
				return;
			case openome_modelPackage.MODEL__INTENTIONS:
				getIntentions().clear();
				return;
			case openome_modelPackage.MODEL__CONTRIBUTIONS:
				getContributions().clear();
				return;
			case openome_modelPackage.MODEL__DEPENDENCIES:
				getDependencies().clear();
				return;
			case openome_modelPackage.MODEL__DECOMPOSITIONS:
				getDecompositions().clear();
				return;
			case openome_modelPackage.MODEL__CONTAINERS:
				getContainers().clear();
				return;
			case openome_modelPackage.MODEL__CORRELATIONS:
				getCorrelations().clear();
				return;
			case openome_modelPackage.MODEL__ASSOCIATIONS:
				getAssociations().clear();
				return;
			case openome_modelPackage.MODEL__ALTERNATIVES:
				getAlternatives().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case openome_modelPackage.MODEL__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case openome_modelPackage.MODEL__INTENTIONS:
				return intentions != null && !intentions.isEmpty();
			case openome_modelPackage.MODEL__CONTRIBUTIONS:
				return contributions != null && !contributions.isEmpty();
			case openome_modelPackage.MODEL__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case openome_modelPackage.MODEL__DECOMPOSITIONS:
				return decompositions != null && !decompositions.isEmpty();
			case openome_modelPackage.MODEL__CONTAINERS:
				return containers != null && !containers.isEmpty();
			case openome_modelPackage.MODEL__CORRELATIONS:
				return correlations != null && !correlations.isEmpty();
			case openome_modelPackage.MODEL__ASSOCIATIONS:
				return associations != null && !associations.isEmpty();
			case openome_modelPackage.MODEL__ALTERNATIVES:
				return alternatives != null && !alternatives.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}
	
	/** @author jenhork
	 * Get leaves in the model
	 * @generated NOT
	 */
	public EList<Intention> getLeaves() {
		BasicEList<Intention> leaves = new BasicEList<Intention>();
		for (Intention i : getAllIntentions()) {
			System.out.println("Intention: " + i);
			if (i.isLeaf())
				leaves.add(i);
				
		}
		return leaves;
	}
	
	/** @author jenhork
	 * Get roots in the model
	 * @generated NOT
	 */
	public EList<Intention> getRoots() {
		BasicEList<Intention> roots = new BasicEList<Intention>();
		for (Intention i : getAllIntentions()) {
			if (i.isRoot())
				roots.add(i);				
		}
		return roots;
	}
	
	/** @author jenhork
	 * Return number of intentions in the model 
	 * @generated NOT
	 */
	public int getNumIntentions(String type) {
		if (type.equals("All"))
			return getAllIntentions().size();
		
		int count = 0;
		for (Intention i : getAllIntentions()) {
			//so annoying that you can't use strings in a switch statement
			if (type.equals("Softgoal")) {
				//also really annoying that you can't check the instance of with string 
				//describing that instance type, i.e. "Softgoal"
				if (i instanceof Softgoal) {
					count++;
				}
			}
			if (type.equals("Task")) {
				if (i instanceof Task) {
					count++;
				}
			}
			if (type.equals("Resource")) {
				if (i instanceof Resource) {
					count++;
				}
			}
			if (type.equals("Goal")) {
				if (i instanceof Goal) {
					count++;
				}
			}
			
		}
		return count;	
		
		
			
	}
		
	/** @author jenhork
	 * Return number of intentions in the model 
	 * @generated NOT
	*/
	public int getNumSoftgoals() {
			
		int count = 0;
		for (Intention i : getAllIntentions()) {
			if (i instanceof Softgoal) {
				count++;
			}
		}
		return count;		
	}
	
	/* (non-Javadoc)
	 * @see edu.toronto.cs.openome_model.Model#getNumActors(java.lang.String)
	 */
	public int getNumActors(String type) {
		if (type.equals("All"))
			return getContainers().size();
		
		int count = 0;
		for (Container c : getContainers()) {
			//so annoying that you can't use strings in a switch statement
			if (type.equals("Actor")) {
				//also really annoying that you can't check the instance of with string 
				//describing that instance type, i.e. "Softgoal"
				if (c instanceof Actor) {
					count++;
				}
			}
			if (type.equals("Role")) {
				if (c instanceof Role) {
					count++;
				}
			}
			if (type.equals("Position")) {
				if (c instanceof Position) {
					count++;
				}
			}
			if (type.equals("Agent")) {
				if (c instanceof Agent) {
					count++;
				}
			}
			
		}
		return count;	
	}

	/* (non-Javadoc)
	 * @see edu.toronto.cs.openome_model.Model#getNumLinks(java.lang.String)
	 * @generated NOT
	 */
	@Override
	public int getNumLinks(String type) {
		if (type.equals("All")) {
			return getAssociations().size() +
					getContributions().size() +
					getDependencies().size() +
					getDecompositions().size();
		}
		if (type.equals("Association")) {
			return getAssociations().size();
		} else if (type.equals("Contribution")) {
			return getContributions().size();
		} else if (type.equals("Dependency")) {
			return getDependencies().size();
		} else {
			int count = 0;
			for (Decomposition link : getDecompositions()) {
				
				if (type.equals("Decomposition")) {
					if (link instanceof AndDecomposition) {
						count++;
					}
				} else if (type.equals("Means-Ends")) {
					if (link instanceof OrDecomposition) {
						count ++;
					}
				}
			}
			return count;
		}
	}
	
	
	
	

} //ModelImpl

/**
 * <copyright>
 * </copyright>
 *
 * $Id: AdviceImpl.java 532 2007-09-03 23:04:02Z nernst $
 */
package q7dsl.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import q7dsl.Advice;
import q7dsl.How;
import q7dsl.HowMuchRules;
import q7dsl.Label;
import q7dsl.Q7dslPackage;
import q7dsl.What;
import q7dsl.When;
import q7dsl.Where;
import q7dsl.Who;
import q7dsl.Why;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Advice</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link q7dsl.impl.AdviceImpl#getHow <em>How</em>}</li>
 *   <li>{@link q7dsl.impl.AdviceImpl#getWhen <em>When</em>}</li>
 *   <li>{@link q7dsl.impl.AdviceImpl#getWhy <em>Why</em>}</li>
 *   <li>{@link q7dsl.impl.AdviceImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link q7dsl.impl.AdviceImpl#getHowmuch <em>Howmuch</em>}</li>
 *   <li>{@link q7dsl.impl.AdviceImpl#getWho <em>Who</em>}</li>
 *   <li>{@link q7dsl.impl.AdviceImpl#getWhom <em>Whom</em>}</li>
 *   <li>{@link q7dsl.impl.AdviceImpl#getWhat <em>What</em>}</li>
 *   <li>{@link q7dsl.impl.AdviceImpl#getWhere <em>Where</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AdviceImpl extends EObjectImpl implements Advice {
	/**
	 * The cached value of the '{@link #getHow() <em>How</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHow()
	 * @generated
	 * @ordered
	 */
	protected How how = null;

	/**
	 * The cached value of the '{@link #getWhen() <em>When</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWhen()
	 * @generated
	 * @ordered
	 */
	protected When when = null;

	/**
	 * The cached value of the '{@link #getWhy() <em>Why</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWhy()
	 * @generated
	 * @ordered
	 */
	protected Why why = null;

	/**
	 * The cached value of the '{@link #getLabel() <em>Label</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected Label label = null;

	/**
	 * The cached value of the '{@link #getHowmuch() <em>Howmuch</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHowmuch()
	 * @generated
	 * @ordered
	 */
	protected HowMuchRules howmuch = null;

	/**
	 * The default value of the '{@link #getWho() <em>Who</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWho()
	 * @generated
	 * @ordered
	 */
	protected static final String WHO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWho() <em>Who</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWho()
	 * @generated
	 * @ordered
	 */
	protected String who = WHO_EDEFAULT;

	/**
	 * The cached value of the '{@link #getWhom() <em>Whom</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWhom()
	 * @generated
	 * @ordered
	 */
	protected Who whom = null;

	/**
	 * The cached value of the '{@link #getWhat() <em>What</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWhat()
	 * @generated
	 * @ordered
	 */
	protected What what = null;

	/**
	 * The cached value of the '{@link #getWhere() <em>Where</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWhere()
	 * @generated
	 * @ordered
	 */
	protected Where where = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AdviceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return Q7dslPackage.Literals.ADVICE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public How getHow() {
		return how;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHow(How newHow, NotificationChain msgs) {
		How oldHow = how;
		how = newHow;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Q7dslPackage.ADVICE__HOW, oldHow, newHow);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHow(How newHow) {
		if (newHow != how) {
			NotificationChain msgs = null;
			if (how != null)
				msgs = ((InternalEObject)how).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.ADVICE__HOW, null, msgs);
			if (newHow != null)
				msgs = ((InternalEObject)newHow).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.ADVICE__HOW, null, msgs);
			msgs = basicSetHow(newHow, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Q7dslPackage.ADVICE__HOW, newHow, newHow));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public When getWhen() {
		return when;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWhen(When newWhen, NotificationChain msgs) {
		When oldWhen = when;
		when = newWhen;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Q7dslPackage.ADVICE__WHEN, oldWhen, newWhen);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWhen(When newWhen) {
		if (newWhen != when) {
			NotificationChain msgs = null;
			if (when != null)
				msgs = ((InternalEObject)when).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.ADVICE__WHEN, null, msgs);
			if (newWhen != null)
				msgs = ((InternalEObject)newWhen).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.ADVICE__WHEN, null, msgs);
			msgs = basicSetWhen(newWhen, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Q7dslPackage.ADVICE__WHEN, newWhen, newWhen));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Why getWhy() {
		return why;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWhy(Why newWhy, NotificationChain msgs) {
		Why oldWhy = why;
		why = newWhy;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Q7dslPackage.ADVICE__WHY, oldWhy, newWhy);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWhy(Why newWhy) {
		if (newWhy != why) {
			NotificationChain msgs = null;
			if (why != null)
				msgs = ((InternalEObject)why).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.ADVICE__WHY, null, msgs);
			if (newWhy != null)
				msgs = ((InternalEObject)newWhy).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.ADVICE__WHY, null, msgs);
			msgs = basicSetWhy(newWhy, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Q7dslPackage.ADVICE__WHY, newWhy, newWhy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Label getLabel() {
		return label;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLabel(Label newLabel, NotificationChain msgs) {
		Label oldLabel = label;
		label = newLabel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Q7dslPackage.ADVICE__LABEL, oldLabel, newLabel);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabel(Label newLabel) {
		if (newLabel != label) {
			NotificationChain msgs = null;
			if (label != null)
				msgs = ((InternalEObject)label).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.ADVICE__LABEL, null, msgs);
			if (newLabel != null)
				msgs = ((InternalEObject)newLabel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.ADVICE__LABEL, null, msgs);
			msgs = basicSetLabel(newLabel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Q7dslPackage.ADVICE__LABEL, newLabel, newLabel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HowMuchRules getHowmuch() {
		return howmuch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHowmuch(HowMuchRules newHowmuch, NotificationChain msgs) {
		HowMuchRules oldHowmuch = howmuch;
		howmuch = newHowmuch;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Q7dslPackage.ADVICE__HOWMUCH, oldHowmuch, newHowmuch);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHowmuch(HowMuchRules newHowmuch) {
		if (newHowmuch != howmuch) {
			NotificationChain msgs = null;
			if (howmuch != null)
				msgs = ((InternalEObject)howmuch).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.ADVICE__HOWMUCH, null, msgs);
			if (newHowmuch != null)
				msgs = ((InternalEObject)newHowmuch).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.ADVICE__HOWMUCH, null, msgs);
			msgs = basicSetHowmuch(newHowmuch, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Q7dslPackage.ADVICE__HOWMUCH, newHowmuch, newHowmuch));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getWho() {
		return who;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWho(String newWho) {
		String oldWho = who;
		who = newWho;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Q7dslPackage.ADVICE__WHO, oldWho, who));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Who getWhom() {
		return whom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWhom(Who newWhom, NotificationChain msgs) {
		Who oldWhom = whom;
		whom = newWhom;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Q7dslPackage.ADVICE__WHOM, oldWhom, newWhom);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWhom(Who newWhom) {
		if (newWhom != whom) {
			NotificationChain msgs = null;
			if (whom != null)
				msgs = ((InternalEObject)whom).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.ADVICE__WHOM, null, msgs);
			if (newWhom != null)
				msgs = ((InternalEObject)newWhom).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.ADVICE__WHOM, null, msgs);
			msgs = basicSetWhom(newWhom, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Q7dslPackage.ADVICE__WHOM, newWhom, newWhom));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public What getWhat() {
		return what;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWhat(What newWhat, NotificationChain msgs) {
		What oldWhat = what;
		what = newWhat;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Q7dslPackage.ADVICE__WHAT, oldWhat, newWhat);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWhat(What newWhat) {
		if (newWhat != what) {
			NotificationChain msgs = null;
			if (what != null)
				msgs = ((InternalEObject)what).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.ADVICE__WHAT, null, msgs);
			if (newWhat != null)
				msgs = ((InternalEObject)newWhat).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.ADVICE__WHAT, null, msgs);
			msgs = basicSetWhat(newWhat, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Q7dslPackage.ADVICE__WHAT, newWhat, newWhat));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Where getWhere() {
		return where;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWhere(Where newWhere, NotificationChain msgs) {
		Where oldWhere = where;
		where = newWhere;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Q7dslPackage.ADVICE__WHERE, oldWhere, newWhere);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWhere(Where newWhere) {
		if (newWhere != where) {
			NotificationChain msgs = null;
			if (where != null)
				msgs = ((InternalEObject)where).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.ADVICE__WHERE, null, msgs);
			if (newWhere != null)
				msgs = ((InternalEObject)newWhere).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.ADVICE__WHERE, null, msgs);
			msgs = basicSetWhere(newWhere, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Q7dslPackage.ADVICE__WHERE, newWhere, newWhere));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Q7dslPackage.ADVICE__HOW:
				return basicSetHow(null, msgs);
			case Q7dslPackage.ADVICE__WHEN:
				return basicSetWhen(null, msgs);
			case Q7dslPackage.ADVICE__WHY:
				return basicSetWhy(null, msgs);
			case Q7dslPackage.ADVICE__LABEL:
				return basicSetLabel(null, msgs);
			case Q7dslPackage.ADVICE__HOWMUCH:
				return basicSetHowmuch(null, msgs);
			case Q7dslPackage.ADVICE__WHOM:
				return basicSetWhom(null, msgs);
			case Q7dslPackage.ADVICE__WHAT:
				return basicSetWhat(null, msgs);
			case Q7dslPackage.ADVICE__WHERE:
				return basicSetWhere(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Q7dslPackage.ADVICE__HOW:
				return getHow();
			case Q7dslPackage.ADVICE__WHEN:
				return getWhen();
			case Q7dslPackage.ADVICE__WHY:
				return getWhy();
			case Q7dslPackage.ADVICE__LABEL:
				return getLabel();
			case Q7dslPackage.ADVICE__HOWMUCH:
				return getHowmuch();
			case Q7dslPackage.ADVICE__WHO:
				return getWho();
			case Q7dslPackage.ADVICE__WHOM:
				return getWhom();
			case Q7dslPackage.ADVICE__WHAT:
				return getWhat();
			case Q7dslPackage.ADVICE__WHERE:
				return getWhere();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case Q7dslPackage.ADVICE__HOW:
				setHow((How)newValue);
				return;
			case Q7dslPackage.ADVICE__WHEN:
				setWhen((When)newValue);
				return;
			case Q7dslPackage.ADVICE__WHY:
				setWhy((Why)newValue);
				return;
			case Q7dslPackage.ADVICE__LABEL:
				setLabel((Label)newValue);
				return;
			case Q7dslPackage.ADVICE__HOWMUCH:
				setHowmuch((HowMuchRules)newValue);
				return;
			case Q7dslPackage.ADVICE__WHO:
				setWho((String)newValue);
				return;
			case Q7dslPackage.ADVICE__WHOM:
				setWhom((Who)newValue);
				return;
			case Q7dslPackage.ADVICE__WHAT:
				setWhat((What)newValue);
				return;
			case Q7dslPackage.ADVICE__WHERE:
				setWhere((Where)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case Q7dslPackage.ADVICE__HOW:
				setHow((How)null);
				return;
			case Q7dslPackage.ADVICE__WHEN:
				setWhen((When)null);
				return;
			case Q7dslPackage.ADVICE__WHY:
				setWhy((Why)null);
				return;
			case Q7dslPackage.ADVICE__LABEL:
				setLabel((Label)null);
				return;
			case Q7dslPackage.ADVICE__HOWMUCH:
				setHowmuch((HowMuchRules)null);
				return;
			case Q7dslPackage.ADVICE__WHO:
				setWho(WHO_EDEFAULT);
				return;
			case Q7dslPackage.ADVICE__WHOM:
				setWhom((Who)null);
				return;
			case Q7dslPackage.ADVICE__WHAT:
				setWhat((What)null);
				return;
			case Q7dslPackage.ADVICE__WHERE:
				setWhere((Where)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case Q7dslPackage.ADVICE__HOW:
				return how != null;
			case Q7dslPackage.ADVICE__WHEN:
				return when != null;
			case Q7dslPackage.ADVICE__WHY:
				return why != null;
			case Q7dslPackage.ADVICE__LABEL:
				return label != null;
			case Q7dslPackage.ADVICE__HOWMUCH:
				return howmuch != null;
			case Q7dslPackage.ADVICE__WHO:
				return WHO_EDEFAULT == null ? who != null : !WHO_EDEFAULT.equals(who);
			case Q7dslPackage.ADVICE__WHOM:
				return whom != null;
			case Q7dslPackage.ADVICE__WHAT:
				return what != null;
			case Q7dslPackage.ADVICE__WHERE:
				return where != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (who: ");
		result.append(who);
		result.append(')');
		return result.toString();
	}

} //AdviceImpl
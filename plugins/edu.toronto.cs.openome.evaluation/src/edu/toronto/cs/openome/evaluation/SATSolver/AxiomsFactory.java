package edu.toronto.cs.openome.evaluation.SATSolver;

import java.util.Vector;

import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Link;

public class AxiomsFactory {

	public LinkAxioms createLinkAxiom(Vector<Intention> sources, Intention targ, Vector<Link> l, String type, DualHashMap<Integer, Intention> dhm, String desc) {
		if (type.equals("Dependency")) {
			DependencyLinkAxioms dla = new DependencyLinkAxioms(sources, targ, l, dhm, desc);
			return dla;
		}
		if (type.equals("Decomposition")) {
			DecompositionLinkAxioms dla = new DecompositionLinkAxioms(sources, targ, l, dhm, desc);
			return dla;
		}
		if (type.equals("Means Ends")) {
			MeansEndsLinkAxioms dla = new MeansEndsLinkAxioms(sources, targ, l, dhm, desc);
			return dla;
		}
		if (type.equals("Make")) {
			MakeContributionLinkAxioms dla = new MakeContributionLinkAxioms(sources, targ, l, dhm, desc);
			return dla;
		}
		if (type.equals("Help")) {
			HelpContributionLinkAxioms dla = new HelpContributionLinkAxioms(sources, targ, l, dhm, desc);
			return dla;
		}
		if (type.equals("Unknown")) {
			UnknownContributionLinkAxioms dla = new UnknownContributionLinkAxioms(sources, targ, l, dhm, desc);
			return dla;
		}
		if (type.equals("Hurt")) {
			HurtContributionLinkAxioms dla = new HurtContributionLinkAxioms(sources, targ, l, dhm, desc);
			return dla;
		}
		if (type.equals("Break")) {
			BreakContributionLinkAxioms dla = new BreakContributionLinkAxioms(sources, targ, l, dhm, desc);
			return dla;
		}		
		if (type.equals("Contribution")) {
			ContributionLinkAxioms dla = new ContributionLinkAxioms(sources, targ, l, dhm, desc);
			return dla;
		}
		
		return null;
	}
	
	public IntentionAxioms createIntentionAxiom(Intention targ, String type, DualHashMap<Integer, Intention> dhm, String desc) {
		if (type.equals("Invariant")) {
			InvariantAxioms ax = new InvariantAxioms(targ, dhm, desc);
			return ax;
		}
		if (type.equals("Constraint")) {
			ConstraintAxioms ax = new ConstraintAxioms(targ, dhm, desc);
			return ax;
		}
		if (type.equals("Target")) {
			TargetAxioms ax = new TargetAxioms(targ, dhm, desc);
			return ax;
		}
		
		return null;
	}
}

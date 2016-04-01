/**
 * 
 */
package org.bflow.toolbox.epc.diagram.wizards;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

import org.bflow.toolbox.epc.diagram.providers.EpcElementTypes;
import org.bflow.toolbox.epc.diagram.wizards.AdditionalFunctionCondition;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * @author Markus Schnädelbach
 *
 */
public class AdditionalFunctionConditions implements Iterable<AdditionalFunctionCondition> {
	

	private ArrayList<AdditionalFunctionCondition> addConditions = new ArrayList<>();

	public AdditionalFunctionConditions(String conditionString) {
		String[] conditions = conditionString.split(Pattern.quote("//"));
		for (String string : conditions) {
			String[] pair = string.split(Pattern.quote("="));
			if(pair.length == 2){
				EpcElementTypeWrapper epcElementWrapper = getEpcElementWrapper(pair[0]);
				if (epcElementWrapper != null) {
					addConditions.add(new AdditionalFunctionCondition(epcElementWrapper, pair[1]));
				}
			} else if (pair.length == 1) {
				EpcElementTypeWrapper epcElementWrapper = getEpcElementWrapper(pair[0]);
				if (epcElementWrapper != null) {
					addConditions.add(new AdditionalFunctionCondition(getEpcElementWrapper(pair[0]), ""));
				}
			}
		}
	}

	private EpcElementTypeWrapper getEpcElementWrapper(String typeName) {
		switch (typeName.toLowerCase()) {
		case "pos":
			return new EpcElementTypeWrapper(EpcElementTypes.Position_2013);
		case "ou":
			return new EpcElementTypeWrapper(EpcElementTypes.Participant_2002);
		case "app":
			return new EpcElementTypeWrapper(EpcElementTypes.Application_2004);
		case ">file":
			return new EpcElementTypeWrapper(EpcElementTypes.File_2019, EpcElementTypes.InformationArc_4003, false);
		case "file>":
			return new EpcElementTypeWrapper(EpcElementTypes.File_2019, EpcElementTypes.InformationArc_4003, true);
		case ">doc":
			return new EpcElementTypeWrapper(EpcElementTypes.Document_2018, EpcElementTypes.InformationArc_4003, false);
		case "doc>":
			return new EpcElementTypeWrapper(EpcElementTypes.Document_2018, EpcElementTypes.InformationArc_4003, true);
		default:
			return null;
		}
	}
	
	@Override
	public Iterator<AdditionalFunctionCondition> iterator() {
		return new MyIterator();
	}
	
	public class MyIterator implements Iterator<AdditionalFunctionCondition> {
		
		private int cursor = 0;

		@Override
		public boolean hasNext() {
			return cursor < addConditions.size();
		}

		@Override
		public AdditionalFunctionCondition next() {
			AdditionalFunctionCondition nextCondition = addConditions.get(cursor);
			cursor++;
			return nextCondition;
		}
		
		@Override
		public void remove() {
			// do nothing
		}
	}
	
	protected class EpcElementTypeWrapper {
		private IElementType nodeType;
		private IElementType arcType;
		private boolean incoming = false;
		
		public EpcElementTypeWrapper(IElementType nodeType, IElementType arcType, boolean incoming) {
			this.nodeType = nodeType;
			this.arcType = arcType;
			this.incoming = incoming;
		}
		
		public EpcElementTypeWrapper(IElementType nodeType) {
			this.nodeType = nodeType;
			this.arcType = EpcElementTypes.Relation_4002;
		}

		public IElementType getNodeType() {
			return nodeType;
		}
		
		public boolean isIncoming() {
			return incoming;
		}

		public IElementType getArcType() {
			return arcType;
		}
	}
}

/**
 * 
 */
package org.bflow.toolbox.epc.diagram.wizards;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

import org.bflow.toolbox.epc.diagram.providers.EpcElementTypes;
import org.bflow.toolbox.epc.diagram.wizards.AdditionalFunctionCondition;
import org.eclipse.epsilon.eol.parse.Eol_EolParserRules.newExpression_return;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * @author Markus Schnädelbach
 *
 */
public class AdditionalFunctionConditions implements Iterable<AdditionalFunctionCondition> {
	

	private ArrayList<AdditionalFunctionCondition> addConditions = new ArrayList<>();

	public AdditionalFunctionConditions(String conditionString) {
		String[] conditions = conditionString.split(Pattern.quote("["));
		for (String string : conditions) {
			String[] pair = string.split(Pattern.quote("="));
			if(pair.length == 2){
				addConditions.add(new AdditionalFunctionCondition(getEpcElement(pair[0]), pair[1]));
			}
		}
	}

	private IElementType getEpcElement(String typeName) {
		switch (typeName) {
		case "pos":
			return EpcElementTypes.Position_2013;
			
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
}

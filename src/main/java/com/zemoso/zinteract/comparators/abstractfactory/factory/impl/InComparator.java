package com.zemoso.zinteract.comparators.abstractfactory.factory.impl;

import com.zemoso.zinteract.comparators.util.ComparatorUtils;
import com.zemoso.zinteract.comparators.abstractfactory.factory.Comparator;
import com.zemoso.zinteract.decisiontable.condition.model.ConditionValue;
import com.zemoso.zinteract.decisiontable.condition.DecisionTableCondition;
import com.zemoso.zinteract.decisiontable.condition.GenericCondition;
import com.zemoso.zinteract.decisiontable.condition.impl.InCondition;

public class InComparator extends Comparator {


	@Override
	public Boolean satisfies(DecisionTableCondition condition, ConditionValue rhs, boolean ignoreCase) {
		InCondition con = (InCondition) condition;
		Boolean matches = false;
		for(GenericCondition gC : con.getInCondition()){
			Boolean isEqual = ComparatorUtils.isEqual(gC,rhs,ignoreCase);
			if(isEqual == null){
				matches = false;
				break;
			}
			else if(isEqual){
				matches = true;
				break;
			}
		}
		return matches;
	}
}
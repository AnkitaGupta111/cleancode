package com.zemoso.zinteract.comparators;

import com.zemoso.zinteract.decisiontable.*;

public class InComparator extends Comparator {


	@Override
	public Boolean satisfies(DtCondition condition,ConditionValue rhs, Boolean ignoreCase) {
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
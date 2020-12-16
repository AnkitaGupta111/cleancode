package com.zemoso.zinteract.services.comparators;

import com.zemoso.zinteract.models.decisiontable.ConditionValue;
import com.zemoso.zinteract.models.decisiontable.DtCondition;
import com.zemoso.zinteract.models.decisiontable.GenericCondition;
import com.zemoso.zinteract.models.decisiontable.InCondition;

public class InComparator extends Comparator {


	@Override
	public Boolean satisfies(DtCondition condition,ConditionValue rhs, boolean ignoreCase) {
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
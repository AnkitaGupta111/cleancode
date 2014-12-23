package com.zemoso.zinteract.comparators;

import com.zemoso.zinteract.decisiontable.StringConstants;

public class ComparatorFactory {

	private static LessThanComparator lessThanC = new LessThanComparator();
	private static BetweenComparator betweenC = new BetweenComparator();
	private static InComparator inC = new InComparator();
	private static NotInComparator notInC = new NotInComparator();
	private static GreaterThanComparator greaterThanC = new GreaterThanComparator();
	private static GreaterThanEqualsComparator greaterThanEqualsC = new GreaterThanEqualsComparator();
	private static LessThanEqualsComparator lessThanEqualsC = new LessThanEqualsComparator();
	private static EqualsComparator equalsC = new EqualsComparator();
	private static NotEqualsComparator notEqualsC = new NotEqualsComparator();

	private static ComparatorFactory cFactory = new ComparatorFactory();

	private ComparatorFactory() {

	}

	public static ComparatorFactory getComparatorFactory() {
		return cFactory;
	}

	public Comparator getComparator(StringConstants keyword) {
		//Find out the appropriate comparator required and return that instance
		if(StringConstants.COMPARATOR_GREATERTHAN == keyword){
			return greaterThanC;
		}
		else if(StringConstants.COMPARATOR_GREATERTHAN_EQUALS == keyword){
			return greaterThanEqualsC;
		}
		else if(StringConstants.COMPARATOR_BETWEEN == keyword){
			return betweenC;
		}
		else if(StringConstants.COMPARATOR_LESSTHAN == keyword){
			return lessThanC;
		}
		else if(StringConstants.COMPARATOR_LESSTHAN_EQUALS == keyword){
			return lessThanEqualsC;
		}
		else if(StringConstants.COMPARATOR_EQUALS == keyword){
			return equalsC;
		}
		else if(StringConstants.COMPARATOR_NOT_EQUALS == keyword){
			return notEqualsC;
		}
		else if(StringConstants.COMPARATOR_IN == keyword){
			return inC;
		}
		else if(StringConstants.COMPARATOR_NOTIN == keyword){
			return notInC;
		}
//		if (keyword) {
//			//(^[<=>]=*\s*(\d+|\d*.\d+))|(^!=+\s*(\d+|\d*.\d+))|(^[not]*\s*between\s*(\d+|\d*.\d+)\s*and\s*(\d+|\d*.\d+))|(\d+|\d*.\d+)
//			// < 100 or > 100 or >= 100 or <= 100 or =100 or != 100 or 100 or between X and Y or not between X and Y
//
//			return numericC;
//		}
//		else if(string) {
//			//(^=*\s*\w+)|(^!=+\s*\w+)|(^[not]*\s*like\s*\w+.*)
//			//hello or !=hello or =hello or like hello world or not like hello world
//
//			return stringC;
//		}
//		else if(boolean) {
//			//(^=*true|false|yes|no|y|n)
//			//  true or false or yes or no or y or n
//
//			return booleanC;
//		}
//		else if(date) {
//
//			return booleanC;
//		}
//		else if(in) {
//			//(^[not]*\s*in.*)
//			// in 1,2 or in hey,you or not in 1,2 or not in hey,you
//
//			return inC;
//		}

		return null;

	}
}
package com.zemoso.zinteract.comparators;

import com.zemoso.zinteract.decisiontable.DtCondition;
import com.zemoso.zinteract.decisiontable.GenericCondition;
import com.zemoso.zinteract.decisiontable.StringConstants;

/**
 * Created by Praveen on 24-Dec-14.
 */
public class ComparatorUtils {

    public static Boolean isEqual(DtCondition condition,String rhs){
        GenericCondition eCondition = (GenericCondition) condition;

        if(condition.getDataType() == StringConstants.DATATYPE_STRING){
            return eCondition.getConditionValue().getStringConditionValue().equals(rhs);
        }
        else if(condition.getDataType() == StringConstants.DATATYPE_LONG){
            return eCondition.getConditionValue().getLongConditionValue() == Long.valueOf(rhs).longValue();
        }
        else if(condition.getDataType() == StringConstants.DATATYPE_DOUBLE){
            return eCondition.getConditionValue().getDoubleConditionValue() == Double.valueOf(rhs).doubleValue();
        }
        else if(condition.getDataType() == StringConstants.DATATYPE_DATE){
            return false;
        }
        return null;
    }

    public static Boolean isGreaterThan(DtCondition condition,String rhs){
        GenericCondition greaterThanCondition = (GenericCondition) condition;

        if(condition.getDataType() == StringConstants.DATATYPE_LONG){
            return greaterThanCondition.getConditionValue().getLongConditionValue() < Long.valueOf(rhs).longValue();
        }
        else if(condition.getDataType() == StringConstants.DATATYPE_DOUBLE){
            return greaterThanCondition.getConditionValue().getDoubleConditionValue() < Double.valueOf(rhs).doubleValue();
        }
        if(condition.getDataType() == StringConstants.DATATYPE_DATE){
            return false;
        }
        return null;
    }

    public static Boolean isLessThan(DtCondition condition,String rhs){
        GenericCondition greaterThanCondition = (GenericCondition) condition;

        if(condition.getDataType() == StringConstants.DATATYPE_LONG){
            return greaterThanCondition.getConditionValue().getLongConditionValue() > Long.valueOf(rhs).longValue();
        }
        else if(condition.getDataType() == StringConstants.DATATYPE_DOUBLE){
            return greaterThanCondition.getConditionValue().getDoubleConditionValue() > Double.valueOf(rhs).doubleValue();
        }
        else if(condition.getDataType() == StringConstants.DATATYPE_DATE){
            return false;
        }

        return null;
    }
}

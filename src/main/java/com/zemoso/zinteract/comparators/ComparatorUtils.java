package com.zemoso.zinteract.comparators;

import com.zemoso.zinteract.decisiontable.ConditionValue;
import com.zemoso.zinteract.decisiontable.DtCondition;
import com.zemoso.zinteract.decisiontable.GenericCondition;
import com.zemoso.zinteract.decisiontable.StringConstants;

/**
 * Created by Praveen on 24-Dec-14.
 */
public class ComparatorUtils {

    public static Boolean isEqual(DtCondition condition,ConditionValue rhs, boolean ignoreCase){
        GenericCondition eCondition = (GenericCondition) condition;

        if(condition.getDataType() == StringConstants.DATATYPE_STRING){
            if(ignoreCase){
                return eCondition.getConditionValue().getStringConditionValue().equalsIgnoreCase(rhs.getStringConditionValue());
            }
            return eCondition.getConditionValue().getStringConditionValue().equals(rhs.getStringConditionValue());
        }
        else if(condition.getDataType() == StringConstants.DATATYPE_LONG){
            return eCondition.getConditionValue().getLongConditionValue() == rhs.getLongConditionValue();
        }
        else if(condition.getDataType() == StringConstants.DATATYPE_DOUBLE){
            return eCondition.getConditionValue().getDoubleConditionValue() == rhs.getDoubleConditionValue();
        }
        else if(condition.getDataType() == StringConstants.DATATYPE_BOOLEAN){
            return eCondition.getConditionValue().getBooleanConditionValue() == rhs.getBooleanConditionValue();
        }
        else if(condition.getDataType() == StringConstants.DATATYPE_DATE){
            return false;
        }
        return null;
    }

    public static Boolean isGreaterThan(DtCondition condition,ConditionValue rhs){
        GenericCondition greaterThanCondition = (GenericCondition) condition;

        if(condition.getDataType() == StringConstants.DATATYPE_LONG){
            return greaterThanCondition.getConditionValue().getLongConditionValue() < rhs.getLongConditionValue();
        }
        else if(condition.getDataType() == StringConstants.DATATYPE_DOUBLE){
            return greaterThanCondition.getConditionValue().getDoubleConditionValue() < rhs.getDoubleConditionValue();
        }
        if(condition.getDataType() == StringConstants.DATATYPE_DATE){
            return false;
        }
        return null;
    }

    public static Boolean isLessThan(DtCondition condition,ConditionValue rhs){
        GenericCondition greaterThanCondition = (GenericCondition) condition;

        if(condition.getDataType() == StringConstants.DATATYPE_LONG){
            return greaterThanCondition.getConditionValue().getLongConditionValue() > rhs.getLongConditionValue();
        }
        else if(condition.getDataType() == StringConstants.DATATYPE_DOUBLE){
            return greaterThanCondition.getConditionValue().getDoubleConditionValue() > rhs.getDoubleConditionValue();
        }
        else if(condition.getDataType() == StringConstants.DATATYPE_DATE){
            return false;
        }

        return null;
    }
}

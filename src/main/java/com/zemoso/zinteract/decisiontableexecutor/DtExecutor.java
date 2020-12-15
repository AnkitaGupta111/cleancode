package com.zemoso.zinteract.decisiontableexecutor;

import com.zemoso.zinteract.comparators.AbstractComparatorFactory;
import com.zemoso.zinteract.comparators.Comparator;
import com.zemoso.zinteract.decisiontable.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DtExecutor extends AbstractDtExecutor {

    private final String dT_json;
    private DecisionTable dT = null;

    public DtExecutor(String dt) {
        this.dT_json = dt;
    }

    public DecisionTable getDecisionTable() {
        if (dT != null) {
            return dT;
        } else {
            dT = createDT();
        }
        return dT;
    }

    public List<Map<String, Map<String, String>>> getAllActionResults(Map<String, String> value) {
        List<Map<String, Map<String, String>>> actionResults = new ArrayList<>();
        List<DtResult> results = getAllMatches(value);
        for (DtResult result : results) {
            actionResults.add(result.getActionResults());
        }
        return actionResults;
    }

    public DtResult getFirstMatch(Map<String, String> value) {
        List<DtResult> allMatches = findMatches(value, true);
        if (allMatches.size() > 0) {
            return allMatches.get(0);
        } else return null;

    }

    public List<DtResult> getAllMatches(Map<String, String> value) {
        return findMatches(value, false);
    }

    private List<DtResult> findMatches(Map<String, String> valueMap, Boolean firstOnly) {
        AbstractComparatorFactory cFactory = AbstractComparatorFactory.getComparatorFactory();
        Iterator<?> i, j;
        DtCondition dtCondition;
        Comparator comparator;
        Boolean match = false;

        List<DtResult> results = new ArrayList<>();
        Map<String, Enum<?>> headerConditions = getDecisionTable().getHeader().getConditions();
        Boolean ignoreCase = getDecisionTable().getIgnoreCase();
        Map<String, Enum<?>> dtConditions = getDecisionTable().getHeader().getConditions();
        for (DtRow row : getDecisionTable().getDt()) {
            i = dtConditions.entrySet().iterator();
            while (i.hasNext()) {
                Map.Entry<String, Enum<?>> me = (Map.Entry) i.next();
                dtCondition = row.getConditionValues().get(me.getKey());
                if (dtCondition == null) {
                    continue;
                }
                comparator = cFactory.getComparator(dtCondition.getComparatorName());
                ConditionValue cV;
                String value = valueMap.get(me.getKey());
                Enum<?> dataType = headerConditions.get(me.getKey());
                cV = getConditionValue(value, dataType);
                match = comparator.satisfies(dtCondition, cV, ignoreCase);
                if (!match) {
                    break;
                }
            }
            if (match && row.getScripts() != null) {
                j = row.getScripts().entrySet().iterator();
                while (j.hasNext()) {
                    Map.Entry me = (Map.Entry) j.next();
                    DtScript script = (DtScript) me.getValue();
                    if (!script.solve(valueMap)) {
                        match = false;
                        break;
                    }
                }
            }
            if (match) {
                DtResult result = new DtResult();
                result.setVariables(valueMap);
                result.setRow(row);
                results.add(result);
            }
            if (match && firstOnly) {
                break;
            }
        }
        return results;
    }

    private DecisionTable createDT() {
        DtCreater dtCreater = new DtCreater(dT_json);
        return dtCreater.createDtModel();
    }

    private ConditionValue getConditionValue(String value, Enum<?> dataType) {
        ConditionValue cValue;
        if (dataType == null) {
            return null;
        }
        if (dataType.equals(StringConstants.DATATYPE_STRING)) {
            cValue = new ConditionValue(value);
        } else if (dataType.equals(StringConstants.DATATYPE_LONG)) {
            cValue = new ConditionValue(Long.parseLong(value));
        } else if (dataType.equals(StringConstants.DATATYPE_DOUBLE)) {
            cValue = new ConditionValue(Double.parseDouble(value));
        } else if (dataType.equals(StringConstants.DATATYPE_BOOLEAN)) {
            cValue = new ConditionValue(Boolean.parseBoolean(value));
        } else {
            cValue = new ConditionValue(value);
        }
        return cValue;
    }
}


 
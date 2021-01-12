package com.katas.kata2.part3;

/**
 * provide common helper methods in  operations over data
 */
class DataOperationHelper {

    static int getComparatorForMinDiff(String[] row1, String[] row2) {
        int value1Index = 2;
        int value2Index = 1;

        int row1Value1 = Integer.parseInt(row1[value1Index].replaceAll("\\D", ""));
        int row1Value2 = Integer.parseInt(row1[value2Index].replaceAll("\\D", ""));
        int row2Value1 = Integer.parseInt(row2[value1Index].replaceAll("\\D", ""));
        int row2Value2 = Integer.parseInt(row2[value2Index].replaceAll("\\D", ""));

        return (Math.abs(row1Value1 - row1Value2)) - (Math.abs(row2Value1 - row2Value2));
    }
}

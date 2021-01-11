package com.cleancode.datamunging.interfaces;

/**
 * Model representing the data parameters
 */
public interface ISourceDataParams {
    int getId();

    String getName();

    Object getValue();

    void setValue(Object value);
}

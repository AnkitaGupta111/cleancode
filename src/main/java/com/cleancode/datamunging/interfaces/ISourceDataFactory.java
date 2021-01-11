package com.cleancode.datamunging.interfaces;

import java.util.List;

/**
 * Factory to create instances of various data sources like Weather data and League data
 */
public interface ISourceDataFactory {
    List<ISourceDataParams> getDataParams();
    ISourceData create(List<ISourceDataParams> params);
    String getRegex();
}

package com.cleancode.datamunging.models;

import com.cleancode.datamunging.interfaces.ISourceData;
import org.immutables.value.Value;

/**
 * Immutable model to represent Weather data
 *
 */
@Value.Immutable
public abstract class WeatherData implements ISourceData {
    public abstract int getDay();

    public abstract int getMinTemp();

    public abstract int getMaxTemp();
}

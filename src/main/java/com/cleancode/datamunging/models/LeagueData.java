package com.cleancode.datamunging.models;

import com.cleancode.datamunging.interfaces.ISourceData;
import org.immutables.value.Value;

/***
 * Immutable model to represent the League data
 */
@Value.Immutable
public abstract class LeagueData implements ISourceData {
    public abstract String getTeamName();

    public abstract int getForGoals();

    public abstract int getAgainstGoals();
}

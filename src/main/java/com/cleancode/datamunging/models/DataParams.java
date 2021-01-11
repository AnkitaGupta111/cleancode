package com.cleancode.datamunging.models;

import com.cleancode.datamunging.interfaces.ISourceDataParams;

public class DataParams implements ISourceDataParams {
    private int id;
    private String name;
    private Object value;


    public DataParams(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public DataParams(int id, String name, Object value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }
}

package com.zemoso.zinteract.decisiontable;


import java.util.ArrayList;
import java.util.List;

public class DecisionTable {

    private String options[];

    private String name;
    private String description;
    private String artifact_id;
    private Boolean ignoreCase = true;


    private DtHeader header = new DtHeader();
    //private HashMap<String,Enum> conditions = new HashMap<String, Enum>();

    private List<DtRow> dT = new ArrayList<DtRow>();

    public DecisionTable() {

    }

    public Boolean getIgnoreCase() {
        return this.ignoreCase;
    }

    public void setIgnoreCase(Boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    public void setOptions(String options[]) {
        //filter options and set default options
        options = new String[3];

    }

    public void setDT(List<DtRow> dt) {
        dT = dt;
    }

    public void setHeaderConditions(String s, Enum e) {
        this.header.addConditions(s, e);
    }

    public DtHeader getHeader() {
        return this.header;
    }

    public List<DtRow> getDt() {
        return dT;
    }

    public void setName(String s) {
        this.name = s;
    }

    public void setDescription(String s) {
        this.description = s;
    }

    public void setArtifactId(String s) {
        this.artifact_id = s;
    }
}
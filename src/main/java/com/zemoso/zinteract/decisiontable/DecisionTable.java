package com.zemoso.zinteract.decisiontable;

import java.util.ArrayList;
import java.util.HashMap;

public class DecisionTable {

	private String options[];

	private String name;
	private String description;
	private String artifact_id;

	private DtHeader header = new DtHeader();
	//private HashMap<String,Enum> conditions = new HashMap<String, Enum>();

	private ArrayList<DtRow> dT = new ArrayList<DtRow>();

	public DecisionTable() {

	};

	public void setOptions(String options[]) {
		//filter options and set default options
		options = new String[3];

	};

	public void setDT(ArrayList<DtRow> dt) {
		dT = dt;
	};

	public void setHeaderConditions(String s,Enum e) {
		this.header.addConditions(s,e);
	}

	public DtHeader getHeader() {
		return this.header;
	}

	public ArrayList<DtRow> getDt() {
		return dT;
	}

	public void setName(String s){
		this.name = s;
	}

	public void setDescription(String s) {
		this.description = s;
	}

	public void setArtifactId(String s) {
		this.artifact_id = s;
	}
}
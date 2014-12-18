package com.zemoso.zinteract.decisiontable;

import java.util.ArrayList;

public class DecisionTable {

	private String options[];

	private String name;
	private String description;
	private String artifact_id;

	private ArrayList<String> conditions = new ArrayList<String>();

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

	public void setConditions(String s) {
		this.conditions.add(s);
	}

	public ArrayList<String> getConditions() {
		return this.conditions;
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
package com.zemoso.zinteract.decisiontable.condition.model;

import com.zemoso.zinteract.util.DataType;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DecisionTable {

	private String[] options = new String[3];

	private String name;

	private String description;

	private String artifactId;

	private Boolean ignoreCase = true;

	private DecisionTableHeader header = new DecisionTableHeader();

	private List<DecisionTableRow> decisionTableRows = new ArrayList<>();

	public void setHeaderConditions(String s, DataType e) {
		this.header.addConditions(s, e);
	}




}
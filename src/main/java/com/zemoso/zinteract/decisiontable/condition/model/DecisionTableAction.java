package com.zemoso.zinteract.decisiontable.condition.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DecisionTableAction {

	private String action;

	private String type;

	private boolean isScripted = false;

	@Override
	public String toString() {
		return action;
	}

}
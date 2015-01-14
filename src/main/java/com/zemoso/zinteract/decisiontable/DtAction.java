package com.zemoso.zinteract.decisiontable;

public class DtAction{
	private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String s){
        this.action = s;
    }

    @Override
    public String toString(){

        return action;
    }
}
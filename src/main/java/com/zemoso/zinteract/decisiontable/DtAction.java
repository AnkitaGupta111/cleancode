package com.zemoso.zinteract.decisiontable;

public class DtAction{
	private String action;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;


    public boolean isScripted() {
        return isScripted;
    }

    public void setScripted(boolean scripted) {
        isScripted = scripted;
    }

    private boolean isScripted=false;


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
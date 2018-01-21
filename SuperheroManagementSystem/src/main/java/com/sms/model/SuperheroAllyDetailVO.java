package com.sms.model;

import java.io.Serializable;

public class SuperheroAllyDetailVO implements Serializable {

	private static final long serialVersionUID = 2074377663703556654L;
	
	private String name;
	private String pseudonym;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPseudonym() {
		return pseudonym;
	}
	public void setPseudonym(String pseudonym) {
		this.pseudonym = pseudonym;
	}
}

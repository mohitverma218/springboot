package com.sms.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;


public class SuperheroAllyCreateVO implements Serializable {
	
	private static final long serialVersionUID = 6648564078266963331L;

	@NotNull(message = "name can not be null.")
	private String name;

	@NotNull(message = "allies can not be null.")
	private List<String> allies;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getAllies() {
		return allies;
	}

	public void setAllies(List<String> allies) {
		this.allies = allies;
	}
}

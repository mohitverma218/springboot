package com.sms.model;

import java.util.List;

public class SuperheroDetailVO extends SuperheroCreateVO{
	
	private static final long serialVersionUID = -4196114010714851178L;
	
	private List<SuperheroAllyDetailVO> allies;
	
	public List<SuperheroAllyDetailVO> getAllies() {
		return allies;
	}
	public void setAllies(List<SuperheroAllyDetailVO> allies) {
		this.allies = allies;
	}
}

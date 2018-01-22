package com.sms.model;

import java.util.Date;
import java.util.List;

import com.sms.bean.SuperheroPublisher;

public class SuperheroDetailVO extends SuperheroCreateVO{

	private static final long serialVersionUID = -4196114010714851178L;

	private List<SuperheroAllyDetailVO> allies;

	public SuperheroDetailVO() {
		super();
	}

	public SuperheroDetailVO(String name, String pseudonym, SuperheroPublisher publisher, List<String> skills,
			Date firstAppearanceOn, List<SuperheroAllyDetailVO> allies) {
		super(name, pseudonym, publisher, skills, firstAppearanceOn);
		this.allies = allies;
	}
	public List<SuperheroAllyDetailVO> getAllies() {
		return allies;
	}
	public void setAllies(List<SuperheroAllyDetailVO> allies) {
		this.allies = allies;
	}
}

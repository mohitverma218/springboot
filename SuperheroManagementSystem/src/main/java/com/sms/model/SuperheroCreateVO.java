package com.sms.model;

import java.io.Serializable;  
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sms.bean.SuperheroPublisher;
 
public class SuperheroCreateVO implements Serializable{
	
	private static final long serialVersionUID = -4196114010714851178L;
	

    @NotNull(message = "name can not be null.")
	private String name;
   
    @NotNull(message = "pseudonym can not be null.")
	private String pseudonym;
    
    @NotNull(message = "publisher can not be null.")
	private SuperheroPublisher publisher = SuperheroPublisher.INDEPENDENT;
    
    @NotNull(message = "skills can not be null.")
    private List<String> skills;
	
    @NotNull(message = "firstAppearanceOn can not be null.")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date firstAppearanceOn;
    
    public SuperheroCreateVO() {
		super();
	}
	
	public SuperheroCreateVO(String name, String pseudonym, SuperheroPublisher publisher, List<String> skills,
			Date firstAppearanceOn) {
		super();
		this.name = name;
		this.pseudonym = pseudonym;
		this.publisher = publisher;
		this.skills = skills;
		this.firstAppearanceOn = firstAppearanceOn;
	}
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
	public SuperheroPublisher getPublisher() {
		return publisher;
	}
	public void setPublisher(SuperheroPublisher publisher) {
		this.publisher = publisher;
	}
	
	public List<String> getSkills() {
		return skills;
	}
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	public Date getFirstAppearanceOn() {
		return firstAppearanceOn;
	}
	public void setFirstAppearanceOn(Date firstAppearanceOn) {
		this.firstAppearanceOn = firstAppearanceOn;
	}
}

package com.sms.bean;

import org.hibernate.validator.constraints.NotBlank;  
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "superhero")
@EntityListeners(AuditingEntityListener.class)
public class Superhero {
  
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String pseudonym;
    
    @Enumerated(EnumType.STRING)
    private SuperheroPublisher publisher;
    
    @OneToMany(mappedBy = "superhero", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Skill> skills;
    
    @OneToMany(mappedBy = "superhero", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ally> allies;
    
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date firstAppearanceOn;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

	public Long getId() {
		return id;
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

	public Date getFirstAppearanceOn() {
		return firstAppearanceOn;
	}

	public void setFirstAppearanceOn(Date firstAppearanceOn) {
		this.firstAppearanceOn = firstAppearanceOn;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}

	public Set<Ally> getAllies() {
		return allies;
	}

	public void setAllies(Set<Ally> allies) {
		this.allies = allies;
	}
}
package com.sms.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Date;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "allies")
@EntityListeners(AuditingEntityListener.class)
public class Ally {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn (name="superhero_id")
	@JsonBackReference
	private Superhero superhero;

	@ManyToOne
	@JoinColumn (name="ally_id")
	@JsonBackReference
	private Superhero ally;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	public Ally() {
		super();
	}

	public Ally(Superhero superhero, Superhero ally) {
		super();
		this.superhero = superhero;
		this.ally = ally;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Superhero getSuperhero() {
		return superhero;
	}

	public void setSuperhero(Superhero superhero) {
		this.superhero = superhero;
	}

	public Superhero getAlly() {
		return ally;
	}

	public void setAlly(Superhero ally) {
		this.ally = ally;
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
}
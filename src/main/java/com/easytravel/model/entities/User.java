package com.easytravel.model.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="et_user")
@ApiModel(value="User", description="Model class for User")
public class User implements Serializable {

	private static final long serialVersionUID = 6826406308161965874L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
	@SequenceGenerator(name="user_gen", sequenceName="user_seq", initialValue=1, allocationSize=1)
	private Long id;
	
	private String name;
	private String email;
	
	public User() {
		super();
	}

	@ApiModelProperty(value="Row PK in database")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ApiModelProperty(value="User name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ApiModelProperty(value="User e-mail")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

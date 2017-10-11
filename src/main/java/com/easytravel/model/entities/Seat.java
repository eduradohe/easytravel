package com.easytravel.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="et_seat")
@ApiModel(value="Seat", description="Model class for Seat")
public class Seat implements Serializable {

	private static final long serialVersionUID = 4665193242261940678L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seat_gen")
	@SequenceGenerator(name="seat_gen", sequenceName="seat_seq", initialValue=1, allocationSize=1)
	private Long id;
	
	private String number;
	
	private BigDecimal price;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Seat() {
		super();
	}
	
	@ApiModelProperty(value="Row PK in database")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ApiModelProperty(value="Number of the Seat", allowableValues="1a, 1b, 1c, 1d, 2a, 2b, 2c, 2d, 3a, 3b, 3c, 3d")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@ApiModelProperty(value="Seat price")
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@ApiModelProperty(value="User to whom the Seat is booked", allowableValues="User object if the seat is booked, null if otherwise")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

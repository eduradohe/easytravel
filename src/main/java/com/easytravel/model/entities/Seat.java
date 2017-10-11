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

@Entity
@Table(name="et_seat")
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

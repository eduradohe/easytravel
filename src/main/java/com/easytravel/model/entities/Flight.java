package com.easytravel.model.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Flight implements Serializable {

	private static final long serialVersionUID = -4438826792107749981L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flight_gen")
	@SequenceGenerator(name="flight_gen", sequenceName="flight_seq", initialValue=1, allocationSize=1)
	private Long id;
	
	private String number;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "seat")
	@JsonIgnore
	private List<Seat> seats;
	
	public Flight() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}

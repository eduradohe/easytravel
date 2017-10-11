package com.easytravel.controller.services.io;

import java.io.Serializable;
import java.math.BigDecimal;

import com.easytravel.model.entities.Seat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="SeatStatus", description="Class defining the status of a Seat")
public class SeatStatus implements Serializable {
	
	private static final long serialVersionUID = 6430574461941740884L;
	
	private String number;
	private BigDecimal price;
	private String availability;
	
	public SeatStatus ( final Seat seat ) {
		if ( seat != null ) { 
			this.number = seat.getNumber();
			this.price = seat.getPrice();
			this.availability = ( seat.getUser() == null ? "Available" : "Unavailable" );
		}
	}
	
	@ApiModelProperty(value="Number of a Seat", allowableValues="1a, 1b, 1c, 1d, 2a, 2b, 2c, 2d, 3a, 3b, 3c, 3d")
	public String getNumber() {
		return number;
	}
	
	@ApiModelProperty(value="Status of the Seat", allowableValues="'Unavailable' if the Seat is booked, 'Available' otherwise")
	public String getAvailability() {
		return availability;
	}
	
	@ApiModelProperty(value="Price of the Seat")
	public BigDecimal getPrice() {
		return price;
	}
}

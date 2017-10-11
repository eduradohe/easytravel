package com.easytravel.controller.services.io;

import java.io.Serializable;

import com.easytravel.model.entities.Seat;

public class SeatStatus implements Serializable {
	
	private static final long serialVersionUID = 6430574461941740884L;
	
	private String number;
	private String availability;
	
	public SeatStatus ( final Seat seat ) {
		if ( seat != null ) { 
			this.number = seat.getNumber();
			this.availability = ( seat.getUser() == null ? "Available" : "Unavailable" );
		}
	}

	public String getNumber() {
		return number;
	}

	public String getAvailability() {
		return availability;
	}
}

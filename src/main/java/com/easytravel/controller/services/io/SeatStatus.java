package com.easytravel.controller.services.io;

import java.io.Serializable;

import com.easytravel.model.entities.Seat;

public final class SeatStatus implements Serializable {
	
	private static final long serialVersionUID = 6430574461941740884L;
	
	private final String number;
	private final String availability;
	
	public SeatStatus ( final Seat seat ) {
		this.number = seat.getNumber();
		this.availability = ( seat.getUser() == null ? "Available" : "Unavailable" );
	}

	public String getNumber() {
		return number;
	}

	public String getAvailability() {
		return availability;
	}
}

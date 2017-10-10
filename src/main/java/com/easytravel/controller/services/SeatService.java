package com.easytravel.controller.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.easytravel.controller.services.io.SeatStatus;
import com.easytravel.model.entities.Seat;
import com.easytravel.model.entities.User;
import com.easytravel.model.repositories.Seats;
import com.easytravel.model.repositories.Users;

@Path("seats")
@Produces({ MediaType.APPLICATION_JSON })
@Component
public class SeatService {
	
	@Autowired
	private Seats seatsRepository;
	@Autowired
	private Users usersRepository;
	
	@GET
	public List<Seat> listAvailableSeats () {
		
		return seatsRepository.findByUserIsNull();
	}
	
	@GET
	@Path("{number}")
	public SeatStatus getStatus ( @PathParam("number") String number ) {
		
		return new SeatStatus(seatsRepository.findByNumber(number));
	}
	
	@PUT
	@Path("book")
	public Seat book ( @QueryParam("seatNumber") String number, @QueryParam("userName") String name ) {
		
		final Seat seat = seatsRepository.findByNumber(number);
		final User user = usersRepository.findByName(name);
		
		seat.setUser(user);
		
		return seatsRepository.saveAndFlush(seat);
	}
	
	@PUT
	@Path("cancel")
	public Seat cancel ( @QueryParam("seatNumber") String number ) {
		
		final Seat seat = seatsRepository.findByNumber(number);
		seat.setUser(null);
		
		return seatsRepository.saveAndFlush(seat);
	}
}

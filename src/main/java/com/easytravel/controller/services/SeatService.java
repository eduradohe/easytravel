package com.easytravel.controller.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.easytravel.controller.services.io.ResponseUtils;
import com.easytravel.controller.services.io.SeatStatus;
import com.easytravel.controller.utils.StringUtils;
import com.easytravel.model.entities.Seat;
import com.easytravel.model.entities.User;
import com.easytravel.model.repositories.Seats;
import com.easytravel.model.repositories.Users;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/easytravel/seats")
@Produces({ MediaType.APPLICATION_JSON })
@Component
@Api(value = "Seats services", produces = "application/json")
public class SeatService {
	
	@Autowired
	private Seats seatsRepository;
	@Autowired
	private Users usersRepository;
	
	@GET
	@ApiOperation(value = "Gets the list of all available seats (i.e. seats that have not been already booked)", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Available Seats successfully retrieved"), @ApiResponse(code = 204, message = "Request fulfilled but there is no available Seats") })
	public Response listAvailableSeats () {
		
		final List<Seat> seats = seatsRepository.findByUserIsNullOrderByNumber();
		
		if ( seats == null || seats.isEmpty() ) {
			return ResponseUtils.noContentResponse();
		}
		
		return ResponseUtils.okResponse(seats);
	}
	
	@GET
	@Path("{seatNumber}")
	@ApiOperation(value = "Gets a given Seat by the number and delivers its current status (i.e. Available/Unavailable)", response = SeatStatus.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Seat data successfully retrieved - \"Available\" means the Seat wasn't booked yet"), 
			@ApiResponse(code = 204, message = "Request fulfilled but there is no Seat with the given number"), 
			@ApiResponse(code = 400, message = "The parameter 'number' is required and cannot be blank nor filled with blank spaces") 
			})
	public Response getStatus ( @ApiParam(value = "Number of the Seat that must be searched", required = true) @PathParam("seatNumber") String number ) {
		
		if ( StringUtils.isEmpty( number ) ) {
			return ResponseUtils.badRequestResponse();
		}
		
		final Seat seat = seatsRepository.findByNumber(number);
		
		if ( seat == null ) {
			// seat not found
			return ResponseUtils.noContentResponse();
		}
		
		final SeatStatus status = new SeatStatus(seat);
		
		return ResponseUtils.okResponse(status);
	}
	
	@PUT
	@Path("book")
	@ApiOperation(value = "Books a Seat identified by its number to the User identified by its name, and returns data corresponding to the booked Seat", response = Seat.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Seat successfully booked"), 
			@ApiResponse(code = 204, message = "Request fulfilled but the Seat is already booked and thus have not been modified"), 
			@ApiResponse(code = 400, message = "The parameters 'number' and 'name' are required and cannot be blank nor filled with blank spaces - also, they should correspond to existing data on database") 
			})
	public Response book ( @ApiParam(value = "Number of the Seat that must be searched", required = true) @QueryParam("seatNumber") String number, 
			@ApiParam(value = "Name of the User that must be searched", required = true) @QueryParam("userName") String name ) {
		
		if ( StringUtils.isEmpty( number ) || StringUtils.isEmpty( name ) ) {
			return ResponseUtils.badRequestResponse();
		}
		
		final Seat seat = seatsRepository.findByNumber(number);
		
		if ( seat == null ) {
			// seat not found
			return ResponseUtils.badRequestResponse();
		}
		
		if ( seat.getUser() != null ) {
			// doesn't change the seat if it is already booked
			return ResponseUtils.noContentResponse(seat);
		}
		
		final User user = usersRepository.findByName(name);
		
		if ( user == null ) {
			// user not found
			return ResponseUtils.badRequestResponse();
		}
		
		seat.setUser(user);
		
		return ResponseUtils.okResponse(seatsRepository.saveAndFlush(seat));
	}
	
	@PUT
	@Path("cancel")
	@ApiOperation(value = "Cancels the booking of a Seat identified by its number and returns data corresponding to the Seat", response = Seat.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Seat booking successfully cancelled"), 
			@ApiResponse(code = 204, message = "Request fulfilled but the Seat isn't booked and thus have not been modified"), 
			@ApiResponse(code = 400, message = "The parameter 'number' is required and cannot be blank nor filled with blank spaces - also, it should correspond to existing data on database") 
			})
	public Response cancel ( @ApiParam(value = "Number of the Seat that must be searched", required = true) @QueryParam("seatNumber") String number ) {
		
		if ( StringUtils.isEmpty( number ) ) {
			return ResponseUtils.badRequestResponse();
		}
		
		final Seat seat = seatsRepository.findByNumber(number);
		
		if ( seat == null ) {
			// seat not found
			return ResponseUtils.badRequestResponse();
		}
		
		if ( seat.getUser() == null ) {
			// doesn't change the seat if it is already available
			return ResponseUtils.noContentResponse(seat);
		}
		
		seat.setUser(null);
		
		return ResponseUtils.okResponse(seatsRepository.saveAndFlush(seat));
	}
}

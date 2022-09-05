package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.BookingDT;
import com.lti.dto.SearchFlightsDT;
import com.lti.dto.StatusDT;
import com.lti.dto.TicketDT;
import com.lti.entity.Booking;
import com.lti.entity.Flight;
import com.lti.entity.Seat;
import com.lti.entity.User;
import com.lti.service.AirlineService;

@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	private AirlineService airlineService;
	
	@PostMapping("/registerUser")
	public StatusDT registerUser(@RequestBody User user) {
		int user_id = airlineService.registerUser(user);
		StatusDT status = new StatusDT();
		status.setGeneratedId(user_id);
		status.setMessage("User Registered Successfully");
		return status;
	}

	@PostMapping("/searchFlights")
	public List<Flight> searchFlights(@RequestBody SearchFlightsDT searchFlightsDT) {
		return airlineService.searchFlights(searchFlightsDT);
	}

	@PostMapping("/addBooking")
	public StatusDT addBooking(@RequestBody BookingDT bookingDT) {
		int bookingId = airlineService.addBooking(bookingDT);
		StatusDT status = new StatusDT();
		status.setGeneratedId(bookingId);
		status.setMessage("Ticket Booking in Progress");
		return status;
	}
	@GetMapping("/updateBooking")
	public void updateBooking(@RequestParam("bookingId") int bookingId) {
		airlineService.updateBooking(bookingId);
		System.out.println(bookingId);
	}
	
	@GetMapping("/fetchBooking")
	public List<Booking> fetchBookings(@RequestParam("userId") int userId){
		return airlineService.fetchBookings(userId);
	}

	@GetMapping("/fetchTicket")
	public TicketDT fetchTicket(@RequestParam("bookingId") int bookingId) {

		TicketDT ticket = airlineService.fetchTicket(bookingId);
		return ticket;
	}
	
	@GetMapping("/cancelTicket")
	public double cancelBooking(@RequestParam("bookingId") int bookingId) {
		double refund = airlineService.cancelTicket(bookingId);
		return refund;
	}
	
	@GetMapping("/fetchSeats")
	public List<Seat> fetchSeats(@RequestParam("flightId") int flightId){
		return airlineService.fetchSeats(flightId);
	}
	
}

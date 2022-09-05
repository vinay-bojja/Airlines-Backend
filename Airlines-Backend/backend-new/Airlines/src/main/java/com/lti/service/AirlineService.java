package com.lti.service;

import java.util.List;

import com.lti.dto.BookingDT;
import com.lti.dto.SearchFlightsDT;
import com.lti.dto.TicketDT;
import com.lti.entity.Booking;
import com.lti.entity.Flight;
import com.lti.entity.Seat;
import com.lti.entity.User;

public interface AirlineService {

	public int registerUser(User user);
	public List<Flight> searchFlights(SearchFlightsDT searchFlightsDT);
	public int addBooking(BookingDT bookingDT);
	public void updateBooking(int bookinId);
	public TicketDT fetchTicket(int bookingId);
	public double cancelTicket(int bookingId);
	public List<Booking> fetchBookings(int userId);
	public List<Seat> fetchSeats(int flightId);
}

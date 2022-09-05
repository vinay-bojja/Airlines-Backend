package com.lti.service;

import java.util.List;

import com.lti.entity.Flight;

public interface AdminService {

	public int addFlight(Flight flight);
	public Flight displayFlight(int flightId);
	public void deleteFight(int flightId);
	public List<Flight> displayAllFlights();
}

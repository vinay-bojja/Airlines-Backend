package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dao.GenericDao;
import com.lti.entity.Flight;
import com.lti.entity.Seat;
import com.lti.function.MiscFunction;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	GenericDao dao;

	@Autowired
	MiscFunction miscFunction;

	@Override
	public int addFlight(Flight flight) {
		flight.setDuration(miscFunction.calcDuration(flight.getDeparture(), flight.getArrival()));
		//flight.setDuration("2 Hrs");
		//System.out.println("asi");
		int ecoSeats = flight.getEconomySeats();
		int busSeats = flight.getBusinessSeats();
		
		Flight updatedFlight = (Flight) dao.save(flight);
		
		
		for(int i = 0; i<ecoSeats; i++) {
			Seat seat = new Seat();
			seat.setTravelClass("Economy");
			seat.setFlightId(updatedFlight.getFlightId());
			seat.setIsBooked("false");
			dao.save(seat);
		}
		for(int i = 0; i<busSeats; i++) {
			Seat seat = new Seat();
			seat.setTravelClass("Business");
			seat.setFlightId(updatedFlight.getFlightId());
			seat.setIsBooked("false");
			dao.save(seat);
		}
		//System.out.println(updatedFlight);
		return updatedFlight.getFlightId();
	}

	@Override
	public Flight displayFlight(int flightId) {
		Flight flight = dao.fetchById(Flight.class, flightId);
		return flight;
	}

	@Override
	public void deleteFight(int flightId) {
		Flight flight = dao.fetchById(Flight.class, flightId);
		dao.delete(flight);
	}

	@Override
	public List<Flight> displayAllFlights() {
		List<Flight> flightList = dao.fetchAll(Flight.class);
		return flightList;
	}

}

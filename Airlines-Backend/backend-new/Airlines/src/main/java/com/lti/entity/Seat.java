package com.lti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="seat_detail")
public class Seat {

	@Id
	@GeneratedValue
	@Column(name="seat_id")
	private int seatId;
	
	@Column(name="travel_class")
	private String travelClass;
	
	@Column(name="flight_id")
	private int flightId;
	
	@Column(name="is_booked")
	private String isBooked;

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public String getTravelClass() {
		return travelClass;
	}

	public void setTravelClass(String travelClass) {
		this.travelClass = travelClass;
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public String getIsBooked() {
		return isBooked;
	}

	public void setIsBooked(String isBooked) {
		this.isBooked = isBooked;
	}
}

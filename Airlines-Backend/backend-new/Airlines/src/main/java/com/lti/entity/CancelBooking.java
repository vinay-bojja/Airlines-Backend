package com.lti.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="cancelled_booking_details")
public class CancelBooking {

	@Id
	@Column(name = "booking_id")
	private int bookingId;
	
	@Column(name="user_id_fk")
	private int userId;
	
	@Column(name="flight_id_fk")
	private int flightId;

	@Column(name = "source")
	private String source;

	@Column(name = "destination")
	private String destination;

	@Column(name = "departure_from_source")
	private String departure;

	@Column(name = "arrival_at_destination")
	private String arrival;

	@Column(name = "journey_date")
	private Date journeyDate;

	@Column(name = "no_of_passengers")
	private int noOfPassengers;

	@Column(name = "cost")
	private double cost;
	
	@Column(name="booking_date")
	private LocalDate bookingDate;
	
	@Column(name="cancelling_date")
	private LocalDate cancellingDate;
	
	@Column(name="travel_class")
	private String travelClass;
	
	@Column(name="ticket_mailing_id")
	private String ticketMailingId;
	
	@Column(name="refund")
	private double refund;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public Date getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(Date journeyDate) {
		this.journeyDate = journeyDate;
	}

	public int getNoOfPassengers() {
		return noOfPassengers;
	}

	public void setNoOfPassengers(int noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public LocalDate getCancellingDate() {
		return cancellingDate;
	}

	public void setCancellingDate(LocalDate cancellingDate) {
		this.cancellingDate = cancellingDate;
	}

	public String getTravelClass() {
		return travelClass;
	}

	public void setTravelClass(String travelClass) {
		this.travelClass = travelClass;
	}

	public String getTicketMailingId() {
		return ticketMailingId;
	}

	public void setTicketMailingId(String ticketMailingId) {
		this.ticketMailingId = ticketMailingId;
	}

	public double getRefund() {
		return refund;
	}

	public void setRefund(double refund) {
		this.refund = refund;
	}
	
}

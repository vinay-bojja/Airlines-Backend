package com.lti.dto;

import java.util.Date;

public class FlightDataTransfer {

	private String source;
	private String destination;
	private Date journeyDate;
	private String departureTime;
	private String arrivalTime;
	private double economyClassCost;
	private double businessClassCost;
	private int noOfSeats;
	private int economySeats;
	private int businessSeats;
	private int adminId;

	public FlightDataTransfer() {
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

	public Date getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(Date journeyDate) {
		this.journeyDate = journeyDate;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public double getEconomyClassCost() {
		return economyClassCost;
	}

	public void setEconomyClassCost(double economyClassCost) {
		this.economyClassCost = economyClassCost;
	}

	public double getBusinessClassCost() {
		return businessClassCost;
	}

	public void setBusinessClassCost(double businessClassCost) {
		this.businessClassCost = businessClassCost;
	}

	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public int getEconomySeats() {
		return economySeats;
	}

	public void setEconomySeats(int economySeats) {
		this.economySeats = economySeats;
	}

	public int getBusinessSeats() {
		return businessSeats;
	}

	public void setBusinessSeats(int businessSeats) {
		this.businessSeats = businessSeats;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

}

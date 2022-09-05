package com.lti.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.lti.dto.SearchFlightsDT;
import com.lti.dto.TicketDT;
import com.lti.entity.Booking;
import com.lti.entity.CancelBooking;
import com.lti.entity.Flight;
import com.lti.entity.Passenger;
import com.lti.entity.Seat;

@Repository
public class AirlineOperationDaoImpl implements AirlineOperationDao {

	@PersistenceContext
	protected EntityManager entityManager;

	@Autowired
	protected JavaMailSender mailSender;

	@Autowired
	protected GenericDao dao;
	
	@Autowired
	MailSender emailSender;

	@Override
	public List<Flight> searchFlightOperation(SearchFlightsDT searchFlightsDT) {

		if (searchFlightsDT.getTravelClass().equalsIgnoreCase("economy")) {
			String fetchedQuery = "select f from Flight f where f.source=:qsource and f.destination=:qdestination and f.journeyDate=:qdate and f.economySeats >= :qtravellers";
			Query query = entityManager.createQuery(fetchedQuery);
			query.setParameter("qsource", searchFlightsDT.getSource());
			query.setParameter("qdestination", searchFlightsDT.getDestination());
			query.setParameter("qdate", searchFlightsDT.getTravelDate());
			query.setParameter("qtravellers", searchFlightsDT.getNoOfPassengers());
			return query.getResultList();

		} else {
			String fetchedQuery = "select f from Flight f where f.source=:qsource and f.destination=:qdestination and f.journeyDate=:qdate and f.businessSeats >= :qtravellers";
			Query query = entityManager.createQuery(fetchedQuery);
			query.setParameter("qsource", searchFlightsDT.getSource());
			query.setParameter("qdestination", searchFlightsDT.getDestination());
			query.setParameter("qdate", searchFlightsDT.getTravelDate());
			query.setParameter("qtravellers", searchFlightsDT.getNoOfPassengers());
			return query.getResultList();
		}

	}

	@Override
	public void addPassenger(Passenger passenger) {
		Passenger updatedPassenger = (Passenger) dao.save(passenger);
	}

	@Override
	public List<Passenger> fetchPassenger(int bookingId) {

		String fetchedQuery = "select p from Passenger p where p.booking.bookingId=:qbookingId";
		Query query = entityManager.createQuery(fetchedQuery);
		query.setParameter("qbookingId", bookingId);
		List<Passenger> passengers = query.getResultList();
		return passengers;
	}

	@Override
	public Flight fetchFlight(Booking booking) {

		Flight bookedFlight = booking.getFlight();
		int bookedFlightId = bookedFlight.getFlightId();
		String fetchedQuery = "select f from Flight f where f.flightId=:qflightId";
		Query query = entityManager.createQuery(fetchedQuery);
		query.setParameter("qflightId", bookedFlightId);
		Flight fetchedFlight = (Flight) query.getSingleResult();
		return fetchedFlight;
	}

	@Override
	public TicketDT fetchTicket(int bookingId) {

		TicketDT ticketDT = new TicketDT();
		try {
			// fetching Flight
			Booking booking = new Booking();
			booking = dao.fetchById(Booking.class, bookingId);
			Flight bookedFlight = booking.getFlight();
			int bookedFlightId = bookedFlight.getFlightId();
			String fetchedQuery1 = "select f from Flight f where f.flightId=:qflightId";
			Query query1 = entityManager.createQuery(fetchedQuery1);
			query1.setParameter("qflightId", bookedFlightId);
			Flight fetchedFlight = (Flight) query1.getSingleResult();

			// fetching passenger
			String fetchedQuery2 = "select p from Passenger p where p.booking.bookingId=:qbookingId";
			Query query2 = entityManager.createQuery(fetchedQuery2);
			query2.setParameter("qbookingId", bookingId);
			List<Passenger> passengerList = query2.getResultList();

			// generating Ticket
			
			ticketDT.setBookingId(bookingId);
			ticketDT.setFlightId(fetchedFlight.getFlightId());
			ticketDT.setSource(fetchedFlight.getSource());
			ticketDT.setDestination(fetchedFlight.getDestination());
			ticketDT.setJourneyDate(booking.getJourneyDate());
			ticketDT.setArrivalTime(fetchedFlight.getArrival());
			ticketDT.setDepartureTime(fetchedFlight.getDeparture());
			ticketDT.setTravelClass(booking.getTravelClass());

			List<Passenger> passengerData = new ArrayList<Passenger>();
			for (Passenger p : passengerList) {
				passengerData.add(p);
			}
			ticketDT.setPassengerList(passengerData);

			// Generating pdf
			Document document = new Document();
			FileOutputStream out = new FileOutputStream("c:/SendingMail/e-ticket.pdf");
			PdfWriter writer = PdfWriter.getInstance(document, out);
			document.open();

			String pnr = "Booking Id: "+String.valueOf(ticketDT.getBookingId());
			String fId = "Flight Id: "+String.valueOf(ticketDT.getFlightId());
			String date = String.valueOf(ticketDT.getJourneyDate());
			
			
			Paragraph para1 = new Paragraph(pnr);
			Paragraph para2 = new Paragraph(fId);
			Paragraph para3 = new Paragraph("Source: "+ticketDT.getSource());
			Paragraph para4 = new Paragraph("Destination: "+ticketDT.getDestination());
			Paragraph para5 = new Paragraph("DepartureTime: "+ticketDT.getDepartureTime());
			Paragraph para6 = new Paragraph("ArrivalTime: "+ticketDT.getArrivalTime());
			Paragraph para7 = new Paragraph("Journey Date: "+date);
			Paragraph para8 = new Paragraph("Travel Class: "+ticketDT.getTravelClass());
			Paragraph para9 = new Paragraph("Passenger List: ");
			
			document.add(para1);
			document.add(para2);
			document.add(para3);
			document.add(para4);
			document.add(para5);
			document.add(para6);
			document.add(para7);
			document.add(para8);
			document.add(para9);
			
			
			for (Passenger p : passengerList) {
				String f = String.valueOf(p.getfName());
				String l = String.valueOf(p.getlName());
				String g = String.valueOf(p.getGender());
				String a = String.valueOf(p.getAge());
				String s = String.valueOf(p.getSeatId());
				Paragraph para11 = new Paragraph("First Name: "+f);
				Paragraph para21 = new Paragraph("Last Name: "+l);
				Paragraph para31 = new Paragraph("Gender: "+g);
				Paragraph para41 = new Paragraph("Age: "+a);
				Paragraph para51 = new Paragraph("Seat No: "+s);
				document.add(para11);
				document.add(para21);
				document.add(para31);
				document.add(para41);
				document.add(para51);
			}
			
			
			
			document.close();
			writer.close();

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom("sonicairlines.autogenerated@gmail.com");
			helper.setTo(booking.getTicketMailingId());
			helper.setSubject("Sonic Airlines E-Ticket");
			helper.setText("Attached file is your e-ticket");

			FileSystemResource file = new FileSystemResource(new File("c:/SendingMail/e-ticket.pdf"));

			helper.addAttachment("e-ticket.pdf", file);

			mailSender.send(message);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ticketDT;

	}

	@Override
	public double cancelTicket(int bookingId) {

		// fetching booking and flight
		Booking booking = new Booking();
		booking = dao.fetchById(Booking.class, bookingId);
		Flight bookedFlight = booking.getFlight();
		int bookedFlightId = bookedFlight.getFlightId();
		String fetchedQuery1 = "select f from Flight f where f.flightId=:qflightId";
		Query query1 = entityManager.createQuery(fetchedQuery1);
		query1.setParameter("qflightId", bookedFlightId);
		Flight fetchedFlight = (Flight) query1.getSingleResult();

		// fetching passenger
		String fetchedQuery2 = "select p from Passenger p where p.booking.bookingId=:qbookingId";
		Query query2 = entityManager.createQuery(fetchedQuery2);
		query2.setParameter("qbookingId", bookingId);
		List<Passenger> passengerList = query2.getResultList();

		for (Passenger p : passengerList) {
			p.setBookingStatus("Cancelled");
			Seat seat = dao.fetchById(Seat.class, p.getSeatId());
			seat.setIsBooked("false");
			dao.save(seat);
		}
		for (Passenger p : passengerList) {
			dao.save(p);
		}

		CancelBooking cancelBooking = new CancelBooking();
		cancelBooking.setArrival(booking.getArrival());
		cancelBooking.setBookingDate(booking.getBookingDate());
		cancelBooking.setBookingId(booking.getBookingId());
		cancelBooking.setSource(booking.getSource());
		cancelBooking.setDestination(booking.getDestination());
		cancelBooking.setDeparture(booking.getDeparture());
		cancelBooking.setUserId(booking.getUser().getUserId());
		cancelBooking.setFlightId(fetchedFlight.getFlightId());
		cancelBooking.setCancellingDate(LocalDate.now());
		cancelBooking.setTravelClass(booking.getTravelClass());
		cancelBooking.setTicketMailingId(booking.getTicketMailingId());
		cancelBooking.setJourneyDate(booking.getJourneyDate());
		cancelBooking.setNoOfPassengers(booking.getNoOfPassengers());
		cancelBooking.setCost(booking.getCost());

		double cost = booking.getCost();
		double refund = cost - (cost * 0.25);
		cancelBooking.setRefund(refund);
		int noOfPassengers = booking.getNoOfPassengers();

		if (booking.getTravelClass().equalsIgnoreCase("economy")) {
			fetchedFlight.setEconomySeats(fetchedFlight.getEconomySeats() + noOfPassengers);
		} else {
			fetchedFlight.setBusinessSeats(fetchedFlight.getBusinessSeats() + noOfPassengers);
		}
		Flight flight = (Flight) dao.save(fetchedFlight);
		dao.delete(booking);

		CancelBooking cB = (CancelBooking) dao.save(cancelBooking);
		
		SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("sonicairlines.autogenerated@gmail.com");
        message.setTo(cancelBooking.getTicketMailingId());
        message.setSubject("Ticket Cancellation");
        message.setText("Hello, "+booking.getUser().getfName()+" "+booking.getUser().getlName()+". Your Booking with Booking Id: "+String.valueOf(cancelBooking.getBookingId())+" has been cancelled Succesfully. Your refund amount is: "+String.valueOf(cancelBooking.getRefund())+". Your Amount will be credited in 2-4 Business Days. Thank you for your business");
        mailSender.send(message);

		return refund;
	}

	@Override
	public List<Booking> fetchBooking(int userId) {

		String fetchedQuery = "select b from Booking b where b.user.userId=:quserId";
		Query query = entityManager.createQuery(fetchedQuery);
		query.setParameter("quserId", userId);
		return query.getResultList();
	}

	@Override
	public List<Seat> fetchSeats(int flightId) {
		String fetchedQuery = "select s from Seat s where s.flightId=:qflightId";
		Query query = entityManager.createQuery(fetchedQuery);
		query.setParameter("qflightId", flightId);
		return query.getResultList();
	}
}

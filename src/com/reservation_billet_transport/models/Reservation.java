package com.reservation_billet_transport.models;

import java.util.Date;
import java.util.UUID;

public class Reservation {
	private UUID id;
	private Date dateReservation;
	
	
	public Reservation() {
	}


	public Reservation(UUID id, Date dateReservation) {
		super();
		this.id = id;
		this.dateReservation = dateReservation;
	}
	
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}

	public Date getDateReservation() {
		return dateReservation;
	}
	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}
}

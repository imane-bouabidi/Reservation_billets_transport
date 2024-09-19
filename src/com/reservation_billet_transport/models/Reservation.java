package com.reservation_billet_transport.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import com.reservation_billet_transport.enums.StatutReservation;

public class Reservation {
	private UUID id;
	private Date dateReservation;
	private User client;
	private StatutReservation statutReservation;
	private UUID billetId;
	
	
	public Reservation() {
	}


	public Reservation(UUID id, Date dateReservation) {
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


	public User getClient() {
		return client;
	}


	public void setClient(User client) {
		this.client = client;
	}


	public StatutReservation getStatutReservation() {
		return statutReservation;
	}


	public void setStatutReservation(StatutReservation statutReservation) {
		this.statutReservation = statutReservation;
	}


	public UUID getBillet() {
		return billetId;
	}


	public void setBillet(UUID billetId) {
		this.billetId = billetId;
	}
	
}

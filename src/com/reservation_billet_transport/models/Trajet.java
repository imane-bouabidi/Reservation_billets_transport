package com.reservation_billet_transport.models;

import java.util.Date;
import java.util.UUID;

public class Trajet {
	private UUID id;
	private Ville villeDepart;
	private Ville villeArrive;
	private Date dateDepart;
	private Date dateArrive;
	
	
	public Trajet() {
	}
	
	
	public Trajet(UUID id, Ville villeDepart, Ville villeArrive, Date dateDepart, Date dateArrive) {
		this.id = id;
		this.villeDepart = villeDepart;
		this.villeArrive = villeArrive;
		this.dateDepart = dateDepart;
		this.dateArrive = dateArrive;
	}




	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public Ville getVilleDepart() {
		return villeDepart;
	}
	public void setVilleDepart(Ville villeDepart) {
		this.villeDepart = villeDepart;
	}
	public Ville getVilleArrive() {
		return villeArrive;
	}
	public void setVilleArrive(Ville villeArrive) {
		this.villeArrive = villeArrive;
	}
	public Date getDateDepart() {
		return dateDepart;
	}
	public void setDateDepart(Date dateDepart) {
		this.dateDepart = dateDepart;
	}
	public Date getDateArrive() {
		return dateArrive;
	}
	public void setDateArrive(Date dateArrive) {
		this.dateArrive = dateArrive;
	}

	
	
}

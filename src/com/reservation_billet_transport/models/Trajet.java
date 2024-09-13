package com.reservation_billet_transport.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Trajet {
	private UUID id;
	private Ville villeDepart;
	private Ville villeArrive;
	private Date dateDepart;
	private Date dateArrive;
	private ArrayList<Billet> billets;
	
	
	public Trajet() {
	}
	
	public Trajet(UUID id, Date dateDepart, Date dateArrive) {
		this.id = id;
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
	public ArrayList<Billet> getBillets() {
		return billets;
	}
	public void setBillets(ArrayList<Billet> billets) {
		this.billets = billets;
	}
	
	
}

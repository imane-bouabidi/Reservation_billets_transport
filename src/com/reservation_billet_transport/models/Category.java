package com.reservation_billet_transport.models;

import java.util.ArrayList;

public class Category {
	private int id;
	private String name;
	private ArrayList<Partenaire> Partenaire;
	
	public Category(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Partenaire> getPartenaire() {
		return Partenaire;
	}

	public void setPartenaire(ArrayList<Partenaire> partenaire) {
		Partenaire = partenaire;
	}
	
	
}

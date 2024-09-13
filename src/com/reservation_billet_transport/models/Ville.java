package com.reservation_billet_transport.models;

import java.util.UUID;

public class Ville {
	private UUID id;
	private String name;
	
	public Ville() {
	}
	
	public Ville(UUID id, String name) {
		this.id = id;
		this.name = name;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}

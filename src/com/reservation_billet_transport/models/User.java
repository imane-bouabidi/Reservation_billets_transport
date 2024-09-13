package com.reservation_billet_transport.models;

import java.util.UUID;

public class User {
	private UUID id;
	private String name;
	private String prenom;
	private String email;
	private String phone;
	
	public User() {
	}

	public User(UUID id, String name, String prenom, String email, String phone) {
		this.id = id;
		this.name = name;
		this.prenom = prenom;
		this.email = email;
		this.phone = phone;
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
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	

}

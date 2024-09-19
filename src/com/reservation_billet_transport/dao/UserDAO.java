package com.reservation_billet_transport.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.reservation_billet_transport.database.DbConnection;
import com.reservation_billet_transport.enums.StatutReservation;
import com.reservation_billet_transport.models.*;

public class UserDAO {
	private Connection conn;
	
	public UserDAO(){
		this.conn = DbConnection.getInstance().getConnection();
	}
	
	public void AjouterUser(User user) {
		String query = "INSERT into client (name,prenom,email,phone) values(?,?,?,?)";
		try(PreparedStatement stmt = conn.prepareStatement(query)){
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getPrenom());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getPhone());
			
			stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public User getUserById(UUID id) {
		String query = "select * from client where id = ?";
		try(PreparedStatement stmt = conn.prepareStatement(query)){
			stmt.setObject(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				User user= new User(
						rs.getObject("id", UUID.class),
						rs.getString("name"),
						rs.getString("prenom"),
						rs.getString("email"),
						rs.getString("phone")
						);
				return user;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateUser(User user) {
		String query = "Update client set name = ?, prenom = ?, email = ?, phone = ? where id = ?";
		try(PreparedStatement stmt = conn.prepareStatement(query)){
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getPrenom());
			stmt.setString(3,user.getEmail());
			stmt.setString(4, user.getPhone());
			stmt.setObject(5, user.getId());
			
			stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteUser(UUID id) {
		String query = "delete from client where id = ?";
		try(PreparedStatement stmt = conn.prepareStatement(query)){
			stmt.setObject(1, id);
			
			stmt.executeQuery();
			
			System.out.println("client Supprime avec succe!");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean validerUser(String email) {
		String query = "select * from client where email = ?";
		try(PreparedStatement stmt = conn.prepareStatement(query)){
			stmt.setString(1, email);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.getObject("id",UUID.class)!=null)
				return true;
			else return false;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public List<Reservation> getReservations(UUID clientId) {
		List<Reservation> reservations = new ArrayList<>();
		String query = "select reservation.id as res_id, date_reservation, client_id, statut_reservation, billet_id from reservation join client on reservation.client_id = client.id where client.id = ?";
		try(PreparedStatement stmt = conn.prepareStatement(query)){
			stmt.setObject(1, clientId);
			ResultSet rs = stmt.executeQuery();
			Reservation r = new Reservation();
			if(rs.next()){
				r.setId(UUID.fromString(rs.getString("id")));
				r.setDateReservation(rs.getDate("date_reservation"));
				r.setClient(getUserById(UUID.fromString(rs.getString("client_id"))));
				r.setStatutReservation(StatutReservation.valueOf(rs.getString("statut_reservation")));
				r.setBillet(UUID.fromString(rs.getString("billet_id")));
				reservations.add(r);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return reservations;
	}
	
	
	
	
}

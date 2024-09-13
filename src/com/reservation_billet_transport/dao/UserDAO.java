package com.reservation_billet_transport.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.reservation_billet_transport.database.DbConnection;
import com.reservation_billet_transport.models.User;

public class UserDAO {
	private Connection conn;
	
	public UserDAO(){
		this.conn = DbConnection.getInstance().getConnection();
	}
	
	public void AjouterUser(User user) {
		String query = "INSERT into user (name,prenom,email,phone) values(?,?,?,?)";
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
	
	private User getUserById(UUID id) {
		String query = "select * from user where id = ?";
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
	
	private void updateUser(User user) {
		String query = "Update user set name = ?, prenom = ?, email = ?, phone = ? where id = ?";
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
	
	private void deleteUser(UUID id) {
		String query = "delete from user where id = ?";
		try(PreparedStatement stmt = conn.prepareStatement(query)){
			stmt.setObject(1, id);
			
			stmt.executeQuery();
			
			System.out.println("User Supprime avec succe!");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	private boolean validerUser(String email) {
		String query = "select * from user where email = ?";
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
	
	
}

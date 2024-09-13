package com.reservation_billet_transport.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import com.reservation_billet_transport.database.DbConnection;
import com.reservation_billet_transport.models.*;

public class VilleDAO {
	private Connection conn;
	
	public VilleDAO(){
		this.conn = DbConnection.getInstance().getConnection();
	}
	
	public void ajouterVille(Ville ville) {
		String query = "insert into ville (name) values (?)";
		 try (PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setString(1, ville.getName());
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	public void updateVille(Ville ville) {
		String query = "update ville set name = ? where id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, ville.getName());
			stmt.setObject(2, ville.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteVille(UUID id) {
		String query = "delete from ville where id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setObject(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

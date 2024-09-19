package com.reservation_billet_transport.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.reservation_billet_transport.database.DbConnection;
import com.reservation_billet_transport.enums.TypeTransport;
import com.reservation_billet_transport.models.Trajet;
import com.reservation_billet_transport.dao.*;

public class TrajetDAO {
	private Connection conn;
	
	public TrajetDAO() {
		this.conn = DbConnection.getInstance().getConnection();
	}
	
	VilleDAO villeDAO = new VilleDAO();

	public void ajouterTrajet(Trajet trajet) {
		String query = "insert into trajet (ville_depart, ville_arrive,date_depart,date_arrive) values (?,?,?,?)";
		 try (PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setObject(1, trajet.getVilleDepart());
	            stmt.setObject(2, trajet.getVilleArrive());
	            stmt.setDate(3, trajet.getDateDepart()!= null ? new Date(trajet.getDateDepart().getTime()) : null);
	            stmt.setDate(4, trajet.getDateArrive() != null ? new Date(trajet.getDateArrive().getTime()) : null);
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	
	public void updateTrajet(Trajet trajet) {
		String query = "update ville set ville_depart = ?, ville_arrive = ?,date_depart = ?,date_arrive = ? where id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setObject(1, trajet.getVilleDepart());
			stmt.setObject(2, trajet.getVilleArrive());
			stmt.setDate(2, trajet.getDateDepart()!= null ? new Date(trajet.getDateDepart().getTime()) : null);
			stmt.setDate(2, trajet.getDateArrive()!= null ? new Date(trajet.getDateArrive().getTime()) : null);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteTrajet(UUID id) {
		String query = "delete from trajet where id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setObject(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
    public Trajet getTrajetById(UUID id) {
        String query = "SELECT * FROM trajet WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return new Trajet(
                            rs.getObject("id", UUID.class),
                            villeDAO.getVilleById(rs.getObject("ville_depart_id", UUID.class)),
                            villeDAO.getVilleById(rs.getObject("ville_arrivee_id", UUID.class)),
                            rs.getDate("date_depart"),
                            rs.getDate("date_arrivee")
                    );
                }
        }catch (SQLException e) {
			e.printStackTrace();
		}
        return null;
    }
}

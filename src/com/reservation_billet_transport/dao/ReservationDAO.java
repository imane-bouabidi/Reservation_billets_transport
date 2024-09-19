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
import com.reservation_billet_transport.models.Reservation;
import com.reservation_billet_transport.models.User;

public class ReservationDAO {
	
	private Connection conn;
	
	public ReservationDAO(){
		this.conn = DbConnection.getInstance().getConnection();
	}
	
	UserDAO userDAO = new UserDAO();
	
	public void Reserver(UUID clientId,UUID billetId) {
		String query ="insert into reservation(client_id,statut_reservation,billet_id) values (?,?,?)";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, clientId);
            stmt.setObject(2, StatutReservation.EN_ATTENTE);
            stmt.setObject(3, billetId);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public List<Reservation> getReservationsByUserId(UUID userId) {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE user_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	User user = userDAO.getUserById(UUID.fromString(rs.getString("client_id")));
                Reservation r = new Reservation();
                    r.setId(UUID.fromString(rs.getString("id")));
                    r.setDateReservation(rs.getDate("date_reservation"));
                    r.setClient(user);
                    r.setStatutReservation(StatutReservation.valueOf(rs.getString("statut_reservation")));
                    r.setBillet(UUID.fromString(rs.getString("billet_id")));
                reservations.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

}

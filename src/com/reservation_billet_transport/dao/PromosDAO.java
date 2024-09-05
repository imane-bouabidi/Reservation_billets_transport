package com.reservation_billet_transport.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.reservation_billet_transport.database.DbConnection;
import com.reservation_billet_transport.enums.StatutOffre;
import com.reservation_billet_transport.enums.TypeReduction;
import com.reservation_billet_transport.models.Contrat;
import com.reservation_billet_transport.models.Promos;

public class PromosDAO {
    private Connection conn;

    public PromosDAO() {
        this.conn = DbConnection.getInstance().getConnection();
    }

    public void addPromo(Promos promo) {
        String query = "INSERT INTO promos (nomOffre, description, dateDebut, dateFin, typeReduction, valeurReduction, conditions, statutOffre, contratId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, promo.getNomOffre());
            stmt.setString(2, promo.getDescription());
            stmt.setDate(3, new Date(promo.getDateDebut().getTime()));
            stmt.setDate(4, promo.getDateFin() != null ? new Date(promo.getDateFin().getTime()) : null);
            stmt.setObject(5, promo.getTypeReduction(), java.sql.Types.OTHER);
            stmt.setDouble(6, promo.getValeurReduction());
            stmt.setString(7, promo.getConditions());
            stmt.setObject(8, promo.getStatutOffre(), java.sql.Types.OTHER);
            stmt.setObject(9, promo.getContrat().getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePromo(Promos promo) {
        String query = "UPDATE promos SET nomOffre = ?, description = ?, dateDebut = ?, dateFin = ?, typeReduction = ?, valeurReduction = ?, conditions = ?, statutOffre = ?, contratId = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, promo.getNomOffre());
            stmt.setString(2, promo.getDescription());
            stmt.setDate(3, new Date(promo.getDateDebut().getTime()));
            stmt.setDate(4, promo.getDateFin() != null ? new Date(promo.getDateFin().getTime()) : null);
            stmt.setObject(5, promo.getTypeReduction(), java.sql.Types.OTHER);
            stmt.setDouble(6, promo.getValeurReduction());
            stmt.setString(7, promo.getConditions());
            stmt.setObject(8, promo.getStatutOffre(), java.sql.Types.OTHER);
            stmt.setObject(9, promo.getContrat().getId());
            stmt.setObject(10, promo.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePromo(UUID id) {
        String query = "DELETE FROM promos WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Promos getPromoById(UUID id) {
        String query = "SELECT * FROM promos WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ContratDAO contratDAO = new ContratDAO();
                Contrat contrat = contratDAO.getContratById(rs.getObject("contratId", UUID.class));

                Promos promo = new Promos(
                    rs.getObject("id", UUID.class),
                    contrat,
                    rs.getString("nomOffre"),
                    rs.getString("description"),
                    rs.getDate("dateDebut"),
                    rs.getDate("dateFin"),
                    TypeReduction.valueOf(rs.getString("typeReduction")),
                    rs.getDouble("valeurReduction"),
                    rs.getString("conditions"),
                    StatutOffre.valueOf(rs.getString("statutOffre"))
                );

                return promo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

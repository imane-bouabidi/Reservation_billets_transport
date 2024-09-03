package com.reservation_billet_transport.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.reservation_billet_transport.database.DbConnection;
import com.reservation_billet_transport.enums.StatutContrat;
import com.reservation_billet_transport.models.Contrat;
import com.reservation_billet_transport.models.Partenaire;

public class ContratDAO {
    private Connection conn;

    public ContratDAO() {
        this.conn = DbConnection.getInstance().getConnection();
    }

    public void addContrat(Contrat contrat) {
        String query = "INSERT INTO contrat (id, partenaireId, dateDebut, dateFin, tarifSpecial, conditionsAccord, renouvelable, statutContrat) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, contrat.getId());
            stmt.setObject(2, contrat.getPartenaire().getId());
            stmt.setDate(3, new Date(contrat.getDateDebut().getTime()));
            stmt.setDate(4, contrat.getDateFin() != null ? new Date(contrat.getDateFin().getTime()) : null);
            stmt.setDouble(5, contrat.getTarifSpecial());
            stmt.setString(6, contrat.getConditionsAccord());
            stmt.setBoolean(7, contrat.isRenouvlable());
            stmt.setString(8, contrat.getStatutContrat().toString());

            stmt.executeUpdate();
            System.out.println("Contrat ajouté avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateContrat(Contrat contrat) {
        String query = "UPDATE contrat SET partenaireId = ?, dateDebut = ?, dateFin = ?, tarifSpecial = ?, conditionsAccord = ?, renouvelable = ?, statutContrat = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, contrat.getPartenaire().getId());
            stmt.setDate(2, new Date(contrat.getDateDebut().getTime()));
            stmt.setDate(3, contrat.getDateFin() != null ? new Date(contrat.getDateFin().getTime()) : null);
            stmt.setDouble(4, contrat.getTarifSpecial());
            stmt.setString(5, contrat.getConditionsAccord());
            stmt.setBoolean(6, contrat.isRenouvlable());
            stmt.setString(7, contrat.getStatutContrat().toString());
            stmt.setObject(8, contrat.getId());

            stmt.executeUpdate();
            System.out.println("Contrat mis à jour avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteContrat(UUID id) {
        String query = "DELETE FROM contrat WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
            System.out.println("Contrat supprimé avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Contrat getContratById(UUID id) {
        String query = "SELECT * FROM contrat WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
            	PartenaireDAO partnerDAO = new PartenaireDAO();
                Partenaire partner = partnerDAO.getPartenaireById(rs.getObject("partenaireId", UUID.class));
                
                Contrat contrat = new Contrat(
                    rs.getObject("id", UUID.class),
                    partner,
                    rs.getDate("dateDebut"),
                    rs.getDate("dateFin"),
                    rs.getDouble("tarifSpecial"),
                    rs.getString("conditionsAccord"),
                    rs.getBoolean("renouvelable"),
                    StatutContrat.valueOf(rs.getString("statutContrat"))
                );

                return contrat;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

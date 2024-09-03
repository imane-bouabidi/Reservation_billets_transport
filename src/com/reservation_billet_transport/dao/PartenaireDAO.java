package com.reservation_billet_transport.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.reservation_billet_transport.database.DbConnection;
import com.reservation_billet_transport.enums.StatutPartenaire;
import com.reservation_billet_transport.enums.TypeTransport;
import com.reservation_billet_transport.models.Partenaire;

public class PartenaireDAO {
    private Connection conn;

    public PartenaireDAO() {
        this.conn = DbConnection.getInstance().getConnection();
    }

    // Ajouter un partenaire
    public void addPartenaire(Partenaire partenaire) {
        String query = "INSERT INTO partenaire (id, nomCompagnie, contactCommercial, typeTransport, zoneGeographique, conditionsSpeciales, statutPartenaire, dateCreation) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, partenaire.getId());
            stmt.setString(2, partenaire.getNomCompagnie());
            stmt.setString(3, partenaire.getContactCommercial());
            stmt.setString(4, partenaire.getTypeTransport().toString());
            stmt.setString(5, partenaire.getZoneGeographique());
            stmt.setString(6, partenaire.getConditionsSpeciales());
            stmt.setString(7, partenaire.getStatutPartenaire().toString());
            stmt.setDate(8, new Date(partenaire.getDateCreation().getTime()));

            stmt.executeUpdate();
            System.out.println("Partenaire ajouté avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Mettre à jour un partenaire
    public void updatePartenaire(Partenaire partenaire) {
        String query = "UPDATE partenaire SET nomCompagnie = ?, contactCommercial = ?, typeTransport = ?, zoneGeographique = ?, conditionsSpeciales = ?, statutPartenaire = ?, dateCreation = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, partenaire.getNomCompagnie());
            stmt.setString(2, partenaire.getContactCommercial());
            stmt.setString(3, partenaire.getTypeTransport().toString());
            stmt.setString(4, partenaire.getZoneGeographique());
            stmt.setString(5, partenaire.getConditionsSpeciales());
            stmt.setString(6, partenaire.getStatutPartenaire().toString());
            stmt.setDate(7, new Date(partenaire.getDateCreation().getTime()));
            stmt.setObject(8, partenaire.getId());

            stmt.executeUpdate();
            System.out.println("Partenaire mis à jour avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Supprimer un partenaire
    public void deletePartenaire(UUID id) {
        String query = "DELETE FROM partenaire WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
            System.out.println("Partenaire supprimé avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Obtenir un partenaire par ID
    public Partenaire getPartenaireById(UUID id) {
        String query = "SELECT * FROM partenaire WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Partenaire(
                    rs.getObject("id", UUID.class),
                    rs.getString("nomCompagnie"),
                    rs.getString("contactCommercial"),
                    TypeTransport.valueOf(rs.getString("typeTransport")),
                    rs.getString("zoneGeographique"),
                    rs.getString("conditionsSpeciales"),
                    StatutPartenaire.valueOf(rs.getString("statutPartenaire")),
                    rs.getDate("dateCreation")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

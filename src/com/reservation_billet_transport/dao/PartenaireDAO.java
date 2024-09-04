package com.reservation_billet_transport.dao;

import java.sql.Connection;
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
        String query = "INSERT INTO partenaire (nomCompagnie, contactCommercial, typeTransport, zoneGeographique, conditionsSpeciales, statutPartenaire) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, partenaire.getNomCompagnie());
            stmt.setString(2, partenaire.getContactCommercial());
            stmt.setObject(3, partenaire.getTypeTransport(), java.sql.Types.OTHER);
            stmt.setString(4, partenaire.getZoneGeographique());
            stmt.setString(5, partenaire.getConditionsSpeciales());
            stmt.setObject(6, partenaire.getStatutPartenaire(), java.sql.Types.OTHER);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Mettre à jour un partenaire
    public void updatePartenaire(Partenaire partenaire) {    	

        String query = "UPDATE partenaire SET nomCompagnie = ?, contactCommercial = ?, typeTransport = ?, zoneGeographique = ?, conditionsSpeciales = ?, statutPartenaire = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, partenaire.getNomCompagnie());
            stmt.setString(2, partenaire.getContactCommercial());
            stmt.setObject(3, partenaire.getTypeTransport(), java.sql.Types.OTHER);
            stmt.setString(4, partenaire.getZoneGeographique());
            stmt.setString(5, partenaire.getConditionsSpeciales());
            stmt.setObject(6, partenaire.getStatutPartenaire(), java.sql.Types.OTHER);
            stmt.setObject(7, partenaire.getId());

            System.out.println(partenaire.getId());
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
            		UUID.fromString(rs.getString("id")), 
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

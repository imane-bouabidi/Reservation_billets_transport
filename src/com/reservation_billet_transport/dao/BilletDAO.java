package com.reservation_billet_transport.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.reservation_billet_transport.database.DbConnection;
import com.reservation_billet_transport.enums.StatutBillet;
import com.reservation_billet_transport.enums.TypeTransport;
import com.reservation_billet_transport.models.Billet;
import com.reservation_billet_transport.models.Contrat;

public class BilletDAO {
    private Connection conn;

    public BilletDAO() {
        this.conn = DbConnection.getInstance().getConnection();
    }
    ContratDAO contratDAO = new ContratDAO();
    TrajetDAO trajetDAO = new TrajetDAO();

    public void addBillet(Billet billet) {
        String query = "INSERT INTO billet (contratId, typeTransport, prixAchat, prixVente, dateVente, statutBillet) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, billet.getContrat().getId());
            stmt.setObject(2, billet.getTypeTransport(), java.sql.Types.OTHER);
            stmt.setDouble(3, billet.getPrixAchat());
            stmt.setDouble(4, billet.getPrixVente());
            stmt.setTimestamp(5, billet.getDateVente());
            stmt.setObject(6, billet.getStatutBillet(), java.sql.Types.OTHER);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBillet(Billet billet) {
        String query = "UPDATE billet SET contratId = ?, typeTransport = ?, prixAchat = ?, prixVente = ?, dateVente = ?, statutBillet = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, billet.getContrat().getId());
            stmt.setObject(2, billet.getTypeTransport(), java.sql.Types.OTHER);
            stmt.setDouble(3, billet.getPrixAchat());
            stmt.setDouble(4, billet.getPrixVente());
            stmt.setTimestamp(5, billet.getDateVente());
            stmt.setObject(6, billet.getStatutBillet(), java.sql.Types.OTHER);
            stmt.setObject(7, billet.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBillet(UUID id) {
        String query = "DELETE FROM billet WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
            System.out.println("Billet supprimé avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Billet getBilletById(UUID id) {
        String query = "SELECT * FROM billet WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Contrat contrat = contratDAO.getContratById(rs.getObject("contratId", UUID.class));

                return new Billet(
                    rs.getObject("id", UUID.class),
                    contrat,
                    TypeTransport.valueOf(rs.getString("typeTransport")),
                    rs.getDouble("prixAchat"),
                    rs.getDouble("prixVente"),
                    rs.getTimestamp("dateVente"),
                    StatutBillet.valueOf(rs.getString("statutBillet"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Billet> getAllBillets() {
        List<Billet> billets = new ArrayList<>();
        String query = "SELECT * FROM billet";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Contrat contrat = contratDAO.getContratById(rs.getObject("contratId", UUID.class));

                Billet billet = new Billet(
                    rs.getObject("id", UUID.class),
                    contrat,
                    TypeTransport.valueOf(rs.getString("typeTransport")),
                    rs.getDouble("prixAchat"),
                    rs.getDouble("prixVente"),
                    rs.getTimestamp("dateVente"),
                    StatutBillet.valueOf(rs.getString("statutBillet"))
                );
                billets.add(billet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return billets;
    }
    
    
    public List<Billet> findBilletsByTrajet(UUID trajetId){
        List<Billet> billets = new ArrayList<>();
        String query = "SELECT * FROM billets WHERE trajet_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setObject(1, trajetId);
            ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Contrat contrat = contratDAO.getContratById(rs.getObject("contratId", UUID.class));
                    Billet b = new Billet(
                    		rs.getObject("id", UUID.class),
                            contrat,
                            TypeTransport.valueOf(rs.getString("typeTransport")),
                            rs.getDouble("prixAchat"),
                            rs.getDouble("prixVente"),
                            rs.getTimestamp("dateVente"),
                            StatutBillet.valueOf(rs.getString("statutBillet"))
                    );
                    b.setTrajet(trajetDAO.getTrajetById(rs.getObject("trajet_id", UUID.class)));
                    billets.add(b);
                }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return billets;
    }
    
    
    
}

package com.reservation_billet_transport.dao;

import java.sql.Connection;
import com.reservation_billet_transport.models.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.reservation_billet_transport.database.DbConnection;
import com.reservation_billet_transport.enums.StatutContrat;
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
		String query = "SELECT p.id, p.nomCompagnie, p.contactCommercial, p.typeTransport, p.zoneGeographique,p.conditionsSpeciales, p.statutPartenaire, p.dateCreation, c.id AS contratid, c.partenaireid, c.datedebut, c.datefin, c.tarifspecial, c.conditionsaccord, c.renouvelable, c.statutcontrat FROM partenaire p LEFT JOIN contrat c ON c.partenaireid = p.id WHERE p.id = ?";
		List<Contrat> contrats = new ArrayList<>();
		Contrat contrat = new Contrat();
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setObject(1, id);
			ResultSet rs = stmt.executeQuery();
			Partenaire partenaire = null;
			while (rs.next()) {
				if (rs.getString("contratid") != null) {
					contrat.setId(UUID.fromString(rs.getString("contratid")));
					contrat.setDateDebut(rs.getDate("datedebut"));
					contrat.setDateFin(rs.getDate("datefin"));
					contrat.setTarifSpecial(rs.getDouble("tarifspecial"));
					contrat.setConditionsAccord(rs.getString("conditionsaccord"));
					contrat.setRenouvlable(rs.getBoolean("renouvelable"));
					contrat.setStatutContrat(StatutContrat.valueOf(rs.getString("statutcontrat")));
					contrats.add(contrat);
				}
				if (partenaire == null) {
					partenaire = new Partenaire();
					partenaire.setId(UUID.fromString(rs.getString("id")));
					partenaire.setNomCompagnie(rs.getString("nomCompagnie"));
					partenaire.setContactCommercial(rs.getString("contactCommercial"));
					partenaire.setTypeTransport(TypeTransport.valueOf(rs.getString("typeTransport"))); 
					partenaire.setZoneGeographique(rs.getString("zoneGeographique"));
					partenaire.setConditionsSpeciales(rs.getString("conditionsSpeciales"));
					partenaire.setStatutPartenaire(StatutPartenaire.valueOf(rs.getString("statutPartenaire")));
					partenaire.setDateCreation(rs.getDate("dateCreation"));
					partenaire.setContrats(contrats);
				}
			}
			return partenaire;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

//    public List<Partenaire> getAllPartners(){
//    	String query = "SELECT * from partenaire";
//    	try(PreparedStatem){
//    		
//    	}catch(){
//    		
//    	}
//    }
}

package com.reservation_billet_transport.services;

import com.reservation_billet_transport.dao.*;
import com.reservation_billet_transport.models.Partenaire;
import java.time.LocalDate;

public class PartenaireService {
	private PartenaireDAO partnerDAO;
	
	public PartenaireService(){
		this.partnerDAO = new PartenaireDAO();
	}
	
	public void addPartenaire(Partenaire partenaire) {
		LocalDate currentDate = LocalDate.now();
			if(partenaire.getDateCreation().isAfter(currentDate)) {
				
		}
		Partenaire p = new Partenaire(partenaire.getId(),partenaire.getNomCompagnie(), partenaire.getContactCommercial(),partenaire.getTypeTransport(),partenaire.getZoneGeographique(),partenaire.getConditionsSpeciales(),partenaire.getStatutPartenaire(),partenaire.getDateCreation());
		partnerDAO.addPartenaire(p);
	}
}

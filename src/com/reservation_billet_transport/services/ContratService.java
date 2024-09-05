package com.reservation_billet_transport.services;

import java.util.Scanner;
import java.util.UUID;
import com.reservation_billet_transport.dao.ContratDAO;
import com.reservation_billet_transport.enums.StatutContrat;
import com.reservation_billet_transport.enums.TypeTransport;
import com.reservation_billet_transport.models.Contrat;

public class ContratService {
    private ContratDAO contratDAO;

    public ContratService() {
        this.contratDAO = new ContratDAO();
    }

    public void addContrat(Contrat contrat) {
        contratDAO.addContrat(contrat);
    }

    public void updateContrat(Contrat contrat) {
        contratDAO.updateContrat(contrat);
    }

    public void deleteContrat(UUID id) {
        contratDAO.deleteContrat(id);
    }

    public Contrat getContratById(UUID id) {
        return contratDAO.getContratById(id);
    }
    
    public StatutContrat getStatutContrat() {
    	StatutContrat statutContrat = null;
    	int choix;
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Choisisez le statut du contrat => 1 : EN_COURS , 2 : TERMINE, 3 : SUSPENDU ");
    	choix = scanner.nextInt();
        switch (choix) {            
        case 1: 
        	statutContrat= StatutContrat.EN_COURS; 
        	break;
        case 2 :
        	statutContrat= StatutContrat.TERMINE;
        	break;
        case 3 : 
        	statutContrat= StatutContrat.SUSPENDU;
        	break;
        default :
        	System.out.println("Choix invalid!");
        	break;
        } 
        return statutContrat;
    } 
}

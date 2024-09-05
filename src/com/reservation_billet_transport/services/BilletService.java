package com.reservation_billet_transport.services;

import java.util.Scanner;
import java.util.UUID;

import com.reservation_billet_transport.dao.BilletDAO;
import com.reservation_billet_transport.dao.PartenaireDAO;
import com.reservation_billet_transport.enums.StatutBillet;
import com.reservation_billet_transport.enums.TypeTransport;
import com.reservation_billet_transport.models.Billet;
import com.reservation_billet_transport.models.Partenaire;

public class BilletService {
    private BilletDAO billetDAO;

    public BilletService() {
        this.billetDAO = new BilletDAO();
    }
    
    public boolean addBillet(Billet b) {
    	billetDAO.addBillet(b);
        return true;
    }

    public boolean updateBillet(Billet b) {
    	billetDAO.updateBillet(b);
        return true;
    }

    public boolean deleteBillet(UUID id) {
    	billetDAO.deleteBillet(id);
        return true;
    }

    public Billet getBilletById(UUID id) {
        return billetDAO.getBilletById(id);
    }
    
    public void afficherBillet(Billet b) {
        System.out.println("ID du billet : " + b.getId());
        System.out.println("Contrat avec : " + b.getContrat().getPartenaire().getNomCompagnie());
        System.out.println("Type de transport : " + b.getTypeTransport());
        System.out.println("Prix d'achat : " + b.getPrixAchat());
        System.out.println("Prix de vente : " + b.getPrixVente());
        System.out.println("Date de vente : " + b.getDateVente());
        System.out.println("Statut du billet : " + b.getStatutBillet());
    }

    public TypeTransport getTypeTransport(Billet b) {
    	TypeTransport typeTransport = null;
    	int choix;
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Choisisez le type de transport => 1 : AVION , 2 : TRAIN, 3 : BUS ");
    	choix = scanner.nextInt();
        switch (choix) {            
        case 1: 
        	typeTransport= TypeTransport.AVION; 
        	break;
        case 2 :
        	typeTransport= TypeTransport.BUS;
        	break;
        case 3 : 
        	typeTransport= TypeTransport.TRAIN;
        	break;
        case 0 : 
        	typeTransport= b.getTypeTransport();
        	break;
        default :
        	System.out.println("Choix invalid, aucun changement !");
        	break;
        } 
        return typeTransport;
    }
    
    public StatutBillet getStatutBillet() {
    	StatutBillet statutBillet = null;
    	int choix;
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Choisisez statut du billet => 1 : EN_ATTENTE , 2 : VENDU, 3 : ANNULE ");
    	choix = scanner.nextInt();
        switch (choix) {            
        case 1: 
        	statutBillet= StatutBillet.EN_ATTENTE; 
        	break;
        case 2 :
        	statutBillet= StatutBillet.VENDU;
        	break;
        case 3 : 
        	statutBillet= StatutBillet.ANNULE;
        	break;
        default :
        	System.out.println("Choix invalid, aucun changement !");
        	break;
        } 
        return statutBillet;
    }
    
    public StatutBillet getStatutBillet(Billet b) {
    	StatutBillet statutBillet = null;
    	int choix;
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Choisisez le type de transport => 1 : EN_ATTENTE , 2 : VENDU, 3 : ANNULE ");
    	choix = scanner.nextInt();
        switch (choix) {            
        case 1: 
        	statutBillet= StatutBillet.EN_ATTENTE; 
        	break;
        case 2 :
        	statutBillet= StatutBillet.VENDU;
        	break;
        case 3 : 
        	statutBillet= StatutBillet.ANNULE;
        	break;
        case 0 : 
        	statutBillet= b.getStatutBillet();
        	break;
        default :
        	System.out.println("Choix invalid, aucun changement !");
        	break;
        } 
        return statutBillet;
    }
}

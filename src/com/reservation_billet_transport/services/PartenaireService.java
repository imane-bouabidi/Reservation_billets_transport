package com.reservation_billet_transport.services;

import com.reservation_billet_transport.dao.PartenaireDAO;
import com.reservation_billet_transport.enums.StatutPartenaire;
import com.reservation_billet_transport.enums.TypeTransport;
import com.reservation_billet_transport.models.Partenaire;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class PartenaireService {
    private PartenaireDAO partenaireDAO;

    public PartenaireService() {
        this.partenaireDAO = new PartenaireDAO();
    }

    public TypeTransport getTypeTransport(Partenaire p) {
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
        	typeTransport= p.getTypeTransport();
        	break;
        default :
        	System.out.println("Choix invalid, aucun changement !");
        	break;
        } 
        return typeTransport;
    }    
    
    public TypeTransport getTypeTransport() {
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
        default :
        	System.out.println("Choix invalid, aucun changement !");
        	break;
        } 
        return typeTransport;
    } 
    
    
    public StatutPartenaire getStatutPartenaire(Partenaire p) {
    	StatutPartenaire statutPartenaire = null;
    	int choix;
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Choisisez le Statut du partenaire => 1 : actif , 2 : inactif, 3 : suspendu, 0 : aucun changement ");
    	choix = scanner.nextInt();
        switch (choix) {            
        case 1: 
        	statutPartenaire= StatutPartenaire.ACTIF; 
        	break;
        case 2 :
        	statutPartenaire= StatutPartenaire.INACTIF;
        	break;
        case 3 : 
        	statutPartenaire= StatutPartenaire.SUSPENDU;
        	break;
        case 0 : 
        	statutPartenaire= p.getStatutPartenaire();
        	break;
        default :
        	System.out.println("Choix invalid, aucun changement !");
        	break;
        } 
        return statutPartenaire;
    }
    
    public StatutPartenaire getStatutPartenaire() {
    	StatutPartenaire statutPartenaire = null;
    	int choix;
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Choisisez le Statut du partenaire => 1 : actif , 2 : inactif, 3 : suspendu ");
    	choix = scanner.nextInt();
        switch (choix) {            
        case 1: 
        	statutPartenaire= StatutPartenaire.ACTIF; 
        	break;
        case 2 :
        	statutPartenaire= StatutPartenaire.INACTIF;
        	break;
        case 3 : 
        	statutPartenaire= StatutPartenaire.SUSPENDU;
        	break;
        default :
        	System.out.println("Choix invalid, aucun changement !");
        	break;
        } 
        return statutPartenaire;
    }
    
    
    public boolean addPartenaire(Partenaire partenaire) {
        partenaireDAO.addPartenaire(partenaire);
        return true;
    }

    public boolean updatePartenaire(Partenaire partenaire) {
        partenaireDAO.updatePartenaire(partenaire);
        return true;
    }

    public boolean deletePartenaire(UUID id) {
        partenaireDAO.deletePartenaire(id);
        return true;
    }

    public Partenaire getPartenaireById(UUID id) {
        return partenaireDAO.getPartenaireById(id);
    }
//    public Partenaire getAllPartners() {
//    	return partenaireDAO.getAllPartners();
//    }
    

    
    
    
    
}

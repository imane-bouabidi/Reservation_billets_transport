package com.reservation_billet_transport.services;

import com.reservation_billet_transport.dao.PromosDAO;
import com.reservation_billet_transport.enums.StatutOffre;
import com.reservation_billet_transport.enums.TypeReduction;
import com.reservation_billet_transport.enums.TypeTransport;
import com.reservation_billet_transport.models.Promos;

import java.util.Scanner;
import java.util.UUID;

public class PromoService {
    private PromosDAO promosDAO;

    public PromoService() {
        this.promosDAO = new PromosDAO();
    }

    public void addPromo(Promos promo) {
        promosDAO.addPromo(promo);
    }

    public void updatePromo(Promos promo) {
        promosDAO.updatePromo(promo);
    }

    public void deletePromo(UUID id) {
        promosDAO.deletePromo(id);
    }

    public Promos getPromoById(UUID id) {
        return promosDAO.getPromoById(id);
    }
    
    
    public TypeReduction getTypeReduction() {
    	TypeReduction typeReduction = null;
    	int choix;
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Choisisez le type de reduction => 1 : POURCENTAGE , 2 : MONTANT");
    	choix = scanner.nextInt();
        switch (choix) {            
        case 1: 
        	typeReduction= TypeReduction.POURCENTAGE; 
        	break;
        case 2 :
        	typeReduction= TypeReduction.MONTANT_FIXE;
        	break;
        default :
        	System.out.println("Choix invalid!");
        	break;
        } 
        return typeReduction;
    } 
    
    public TypeReduction getTypeReduction(Promos p) {
    	TypeReduction typeReduction = null;
    	int choix;
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Choisisez le type de reduction => 1 : POURCENTAGE , 2 : MONTANT, 0 : aucun changement ");
    	choix = scanner.nextInt();
        switch (choix) {            
        case 1: 
        	typeReduction= TypeReduction.POURCENTAGE; 
        	break;
        case 2 :
        	typeReduction= TypeReduction.MONTANT_FIXE;
        	break;
        case 0 : 
        	typeReduction= p.getTypeReduction();
        	break;
        default :
        	System.out.println("Choix invalid!");
        	break;
        } 
        return typeReduction;
    } 
    
    public StatutOffre getStatutOffre() {
    	StatutOffre statutOffre = null;
    	int choix;
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Choisisez le statut de l'offre => 1 : ACTIVE , 2 : EXPIREE, 3 : SUSPENDUE");
    	choix = scanner.nextInt();
        switch (choix) {            
        case 1: 
        	statutOffre= StatutOffre.ACTIVE; 
        	break;
        case 2 :
        	statutOffre= StatutOffre.EXPIREE;
        	break;
        case 3 :
        	statutOffre= StatutOffre.SUSPENDUE;
        	break;
        default :
        	System.out.println("Choix invalid!");
        	break;
        } 
        return statutOffre;
    } 
    
    public StatutOffre getStatutOffre(Promos p) {
    	StatutOffre statutOffre = null;
    	int choix;
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Choisisez le statut de l'offre => 1 : ACTIVE , 2 : EXPIREE, 3 : SUSPENDUE, 0 : aucun changement");
    	choix = scanner.nextInt();
        switch (choix) {            
        case 1: 
        	statutOffre= StatutOffre.ACTIVE; 
        	break;
        case 2 :
        	statutOffre= StatutOffre.EXPIREE;
        	break;
        case 3 :
        	statutOffre= StatutOffre.SUSPENDUE;
        	break;
        case 0 : 
        	statutOffre= p.getStatutOffre();
        	break;
        default :
        	System.out.println("Choix invalid!");
        	break;
        } 
        return statutOffre;
    } 
}

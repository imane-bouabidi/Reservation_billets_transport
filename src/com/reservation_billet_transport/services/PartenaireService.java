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
    
    public void afficherPartenaire(Partenaire partenaire) {
    	System.out.println("Nom de la compagnie : " + partenaire.getNomCompagnie());
        System.out.println("Contact commercial : " + partenaire.getContactCommercial());
        System.out.println("Type de transport : " + partenaire.getTypeTransport());
        System.out.println("Zone géographique : " + partenaire.getZoneGeographique());
        System.out.println("Conditions spéciales : " + partenaire.getConditionsSpeciales());
        System.out.println("Statut partenaire : " + partenaire.getStatutPartenaire());
        System.out.println("Date de création : " + partenaire.getDateCreation());
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //Main

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        PartenaireService partenaireService = new PartenaireService();
//        boolean running = true;
//
//        while (running) {
//            System.out.println("Choisissez une option:");
//            System.out.println("1. Ajouter un partenaire");
//            System.out.println("2. Mettre à jour un partenaire");
//            System.out.println("3. Supprimer un partenaire");
//            System.out.println("4. Obtenir un partenaire par ID");
//            System.out.println("5. Quitter");
//
//            int choix;
//            try {
//                choix = scanner.nextInt();
//                scanner.nextLine();
//            } catch (InputMismatchException e) {
//                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
//                scanner.nextLine();
//                continue;
//            }
//
//            switch (choix) {
//                case 1:
//                    // Ajouter un partenaire
////                    try {
////                        System.out.println("Entrez le nom de la compagnie:");
////                        String nomCompagnie = scanner.nextLine();
////
////                        System.out.println("Entrez le contact commercial:");
////                        String contactCommercial = scanner.nextLine();
////
////                        System.out.println("Entrez le type de transport (BUS, TRAIN, AVION):");
////                        TypeTransport typeTransport = TypeTransport.valueOf(scanner.nextLine().toUpperCase());
////
////                        System.out.println("Entrez la zone géographique:");
////                        String zoneGeographique = scanner.nextLine();
////
////                        System.out.println("Entrez les conditions spéciales:");
////                        String conditionsSpeciales = scanner.nextLine();
////
////                        System.out.println("Entrez le statut du partenaire (ACTIF, INACTIF):");
////                        StatutPartenaire statutPartenaire = StatutPartenaire.valueOf(scanner.nextLine().toUpperCase());
////
////                        Partenaire partenaire = new Partenaire(nomCompagnie, contactCommercial, typeTransport, zoneGeographique, conditionsSpeciales, statutPartenaire);
////
////                        if (partenaireService.addPartenaire(partenaire)) {
////                            System.out.println("Partenaire ajouté avec succès.");
////                        }
////                    } catch (IllegalArgumentException e) {
////                        System.out.println("Erreur: " + e.getMessage());
////                    }
//                    break;
//
//                case 2:
//                    // Mettre à jour un partenaire
//                    try {
//                        System.out.println("Entrez l'ID du partenaire à mettre à jour (UUID):");
//                        UUID id = UUID.fromString(scanner.nextLine());
//
//                        Partenaire partenaire = partenaireService.getPartenaireById(id);
//                        if (partenaire == null) {
//                            System.out.println("Partenaire non trouvé.");
//                            break;
//                        }
//                        System.out.println("Entrez le nouveau nom de la compagnie (laisser vide pour ne pas modifier):");
//                        String nomCompagnie = scanner.nextLine();
//                        if (!nomCompagnie.isEmpty()) partenaire.setNomCompagnie(nomCompagnie);
//
//                        System.out.println("Entrez le nouveau contact commercial (laisser vide pour ne pas modifier):");
//                        String contactCommercial = scanner.nextLine();
//                        if (!contactCommercial.isEmpty()) partenaire.setContactCommercial(contactCommercial);
//
//                        TypeTransport validatedTypeTransport = partenaireService.getTypeTransport(partenaire);
//                        partenaire.setTypeTransport(validatedTypeTransport);
//
//                        System.out.println("Entrez la nouvelle zone geographique (laisser vide pour ne pas modifier):");
//                        String zoneGeographique = scanner.nextLine();
//                        if (!zoneGeographique.isEmpty()) partenaire.setZoneGeographique(zoneGeographique);
//
//                        System.out.println("Entrez le nouveau conditions speciales (laisser vide pour ne pas modifier):");
//                        String conditionsSpeciales = scanner.nextLine();
//                        if (!conditionsSpeciales.isEmpty()) partenaire.setConditionsSpeciales(conditionsSpeciales);
//
//                        StatutPartenaire validatedStatutPartenaire = partenaireService.getStatutPartenaire(partenaire);
//                        partenaire.setStatutPartenaire(validatedStatutPartenaire);
//                        partenaireService.updatePartenaire(partenaire);
//                        
//                    } catch (IllegalArgumentException e) {
//                        System.out.println("Erreur: " + e.getMessage());
//                    }
//                    break;
//
//                case 3:
//                    // Supprimer un partenaire
//                    try {
//                        System.out.println("Entrez l'ID du partenaire à supprimer (UUID):");
//                        UUID id = UUID.fromString(scanner.nextLine());
//
//                        if (partenaireService.deletePartenaire(id)) {
//                            System.out.println("Partenaire supprimé avec succès.");
//                        }
//                    } catch (IllegalArgumentException e) {
//                        System.out.println("Erreur: " + e.getMessage());
//                    }
//                    break;
//
//                case 4:
//                    // Obtenir un partenaire par ID
//                    try {
//                        System.out.println("Entrez l'ID du partenaire à obtenir (UUID):");
//                        UUID id = UUID.fromString(scanner.nextLine());
//
//                        Partenaire partenaire = partenaireService.getPartenaireById(id);
//                        if (partenaire != null) {
//                        partenaireService.afficherPartenaire(partenaire);
//                        } else {
//                            System.out.println("Partenaire non trouvé.");
//                        }
//                    } catch (IllegalArgumentException e) {
//                        System.out.println("Erreur: " + e.getMessage());
//                    }
//                    break;
//
//                case 5:
//                    // Quitter
//                    running = false;
//                    System.out.println("Au revoir !");
//                    break;
//
//                default:
//                    System.out.println("Choix invalide. Veuillez réessayer.");
//                    break;
//            }
//        }
//
//        scanner.close();
//    }
}

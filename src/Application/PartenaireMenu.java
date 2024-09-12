package Application;

import com.reservation_billet_transport.enums.*;
import com.reservation_billet_transport.models.Contrat;
import com.reservation_billet_transport.models.Partenaire;
import com.reservation_billet_transport.services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class PartenaireMenu {

    public static void display() {
        Scanner scanner = new Scanner(System.in);
        PartenaireService partenaireService = new PartenaireService();
        boolean back = false;

        while (!back) {
        	System.out.println("1. Afficher partenaire by Id");
            System.out.println("2. Ajouter un nouveau partenaire");
            System.out.println("3. Modifier un partenaire");
            System.out.println("4. Supprimer un partenaire");
            System.out.println("5. Quitter");
            System.out.print("Choisissez une option : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
            	case 1:
            		System.out.println("Entrez l'id du partenaire:");
					  UUID Pid = UUID.fromString(scanner.nextLine());
            		Partenaire partner = partenaireService.getPartenaireById(Pid);
            		afficherPartenaire(partner);
            		break;
                case 2:
                	try {
					System.out.println("Entrez le nom de la compagnie:");
					  String nomCompagnie = scanner.nextLine();
					
					  System.out.println("Entrez le contact commercial:");
					  String contactCommercial = scanner.nextLine();

					  TypeTransport typeTransport = partenaireService.getTypeTransport();
					
					  System.out.println("Entrez la zone géographique:");
					  String zoneGeographique = scanner.nextLine();
					
					  System.out.println("Entrez les conditions spéciales:");
					  String conditionsSpeciales = scanner.nextLine();
					  
					  StatutPartenaire statutPartenaire = partenaireService.getStatutPartenaire();
					  
					  Partenaire partenaire = new Partenaire();
					  partenaire.setNomCompagnie(nomCompagnie);
					  partenaire.setContactCommercial(contactCommercial);
					  partenaire.setZoneGeographique(zoneGeographique);
					  partenaire.setConditionsSpeciales(conditionsSpeciales);
					  partenaire.setStatutPartenaire(statutPartenaire);
					  partenaire.setTypeTransport(typeTransport);
					  
					  if (partenaireService.addPartenaire(partenaire)) {
					      System.out.println("Partenaire ajouté avec succès.");
					  }
			            } catch (IllegalArgumentException e) {
			              System.out.println("Erreur: " + e.getMessage());
			          }
                    break;
                case 3:
                	 try {
                         System.out.println("Entrez l'ID du partenaire à mettre à jour (UUID):");
                         UUID id = UUID.fromString(scanner.nextLine());

                         Partenaire partenaire = partenaireService.getPartenaireById(id);
                         if (partenaire == null) {
                             System.out.println("Partenaire non trouvé.");
                             break;
                         }
                         System.out.println("Entrez le nouveau nom de la compagnie (laisser vide pour ne pas modifier):");
                         String nomCompagnie = scanner.nextLine();
                         if (!nomCompagnie.isEmpty()) partenaire.setNomCompagnie(nomCompagnie);

                         System.out.println("Entrez le nouveau contact commercial (laisser vide pour ne pas modifier):");
                         String contactCommercial = scanner.nextLine();
                         if (!contactCommercial.isEmpty()) partenaire.setContactCommercial(contactCommercial);

                         TypeTransport validatedTypeTransport = partenaireService.getTypeTransport(partenaire);
                         partenaire.setTypeTransport(validatedTypeTransport);

                         System.out.println("Entrez la nouvelle zone geographique (laisser vide pour ne pas modifier):");
                         String zoneGeographique = scanner.nextLine();
                         if (!zoneGeographique.isEmpty()) partenaire.setZoneGeographique(zoneGeographique);

                         System.out.println("Entrez le nouveau conditions speciales (laisser vide pour ne pas modifier):");
                         String conditionsSpeciales = scanner.nextLine();
                         if (!conditionsSpeciales.isEmpty()) partenaire.setConditionsSpeciales(conditionsSpeciales);

                         StatutPartenaire validatedStatutPartenaire = partenaireService.getStatutPartenaire(partenaire);
                         partenaire.setStatutPartenaire(validatedStatutPartenaire);
                         partenaireService.updatePartenaire(partenaire);
                         
                     } catch (IllegalArgumentException e) {
                         System.out.println("Erreur: " + e.getMessage());
                     }
                    break;
                case 4:
                	try {
                        System.out.println("Entrez l'ID du partenaire à supprimer (UUID):");
                        UUID id = UUID.fromString(scanner.nextLine());

                        if (partenaireService.deletePartenaire(id)) {
                            System.out.println("Partenaire supprimé avec succès.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erreur: " + e.getMessage());
                    }
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    break;
            }
        }
    }
    public static void afficherPartenaire(Partenaire partenaire) {
    	System.out.println("Nom de la compagnie : " + partenaire.getNomCompagnie());
    	System.out.println("Contact commercial : " + partenaire.getContactCommercial());
    	System.out.println("Type de transport : " + partenaire.getTypeTransport());
    	System.out.println("Zone géographique : " + partenaire.getZoneGeographique());
    	System.out.println("Conditions spéciales : " + partenaire.getConditionsSpeciales());
    	System.out.println("Statut partenaire : " + partenaire.getStatutPartenaire());
    	System.out.println("Date de création : " + partenaire.getDateCreation());
    	System.out.println("--------------------------------------------------------------");
    	System.out.println("------------------------------- Contrats :-------------------------------");
    	List<Contrat> contrats =  new ArrayList<>();
    	contrats = partenaire.getContrats();
    	for(Contrat contrat : contrats) {    		
    		System.out.println("Id : "+contrat.getId());
    		System.out.println("Conditions : "+contrat.getConditionsAccord());
    		System.out.println("Tarifs : "+contrat.getTarifSpecial());
    		System.out.println("Date debut : "+contrat.getDateDebut());
    		System.out.println("Date fin : "+contrat.getDateFin());
    		System.out.println("Statut : "+contrat.getStatutContrat());
    	}
    }
}



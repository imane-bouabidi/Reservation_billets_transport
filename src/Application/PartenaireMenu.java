package Application;

import com.reservation_billet_transport.enums.*;
import com.reservation_billet_transport.models.Partenaire;
import com.reservation_billet_transport.services.*;
import java.util.Scanner;
import java.util.UUID;

public class PartenaireMenu {

    public static void display() {
        Scanner scanner = new Scanner(System.in);
        PartenaireService partenaireService = new PartenaireService();
        boolean back = false;

        while (!back) {
        	System.out.println("1. Afficher tous les partenaires");
            System.out.println("2. Ajouter un nouveau partenaire");
            System.out.println("3. Modifier un partenaire");
            System.out.println("4. Supprimer un partenaire");
            System.out.println("5. Quitter");
            System.out.print("Choisissez une option : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
            	case 1:
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
					  
					  Partenaire partenaire = new Partenaire(nomCompagnie, contactCommercial, typeTransport, zoneGeographique, conditionsSpeciales, statutPartenaire);					
					  
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
}


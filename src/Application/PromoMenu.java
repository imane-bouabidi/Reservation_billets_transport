package Application;

import com.reservation_billet_transport.enums.StatutContrat;

import com.reservation_billet_transport.enums.StatutOffre;
import com.reservation_billet_transport.enums.TypeReduction;
import com.reservation_billet_transport.models.Contrat;
import com.reservation_billet_transport.models.Promos;
import com.reservation_billet_transport.services.ContratService;
import com.reservation_billet_transport.services.PromoService;

import java.sql.Date;
import java.util.Scanner;
import java.util.UUID;

public class PromoMenu {

    public static void display() {
        Scanner scanner = new Scanner(System.in);
        PromoService promoService = new PromoService();
        ContratService contratService = new ContratService();
        boolean back = false;

        while (!back) {
            System.out.println("1. Afficher toutes les promotions");
            System.out.println("2. Ajouter une nouvelle promotion");
            System.out.println("3. Modifier une promotion");
            System.out.println("4. Supprimer une promotion");
            System.out.println("5. Quitter");
            System.out.print("Choisissez une option : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    //  afficher 
                    break;
                case 2:
                    try {
                        System.out.println("Entrez l'ID du contrat pour cette promotion (UUID):");
                        UUID contratId = UUID.fromString(scanner.nextLine());
                        Contrat contrat = contratService.getContratById(contratId);

                        if (contrat == null) {
                            System.out.println("Contrat non trouvé.");
                            break;
                        }

                        System.out.println("Entrez le nom de l'offre:");
                        String nomOffre = scanner.nextLine();

                        System.out.println("Entrez la description de l'offre:");
                        String description = scanner.nextLine();

                        System.out.println("Entrez la date de début de la promotion (YYYY-MM-DD):");
                        Date dateDebut = Date.valueOf(scanner.nextLine());

                        System.out.println("Entrez la date de fin de la promotion (YYYY-MM-DD, laisser vide pour aucune fin):");
                        String dateFinStr = scanner.nextLine();
                        Date dateFin = dateFinStr.isEmpty() ? null : Date.valueOf(dateFinStr);
                        
                        TypeReduction typeReduction = promoService.getTypeReduction();

                        System.out.println("Entrez la valeur de la réduction:");
                        double valeurReduction = scanner.nextDouble();
                        scanner.nextLine();

                        System.out.println("Entrez les conditions de la promotion:");
                        String conditions = scanner.nextLine();

                        StatutOffre statutOffre = promoService.getStatutOffre();

                        Promos promo = new Promos();
                        promo.setConditions(conditions);
                        promo.setContrat(contrat);
                        promo.setDateDebut(dateDebut);
                        promo.setDateFin(dateFin);
                        promo.setDescription(description);
                        promo.setNomOffre(nomOffre);
                        promo.setStatutOffre(statutOffre);
                        promo.setTypeReduction(typeReduction);
                        promo.setValeurReduction(valeurReduction);
                        promoService.addPromo(promo);
                        System.out.println("Promotion ajoutée avec succès.");

                    } catch (IllegalArgumentException e) {
                        System.out.println("Erreur: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Entrez l'ID de la promotion à mettre à jour (UUID):");
                        UUID id = UUID.fromString(scanner.nextLine());

                        Promos promo = promoService.getPromoById(id);
                        if (promo == null) {
                            System.out.println("Promotion non trouvée.");
                            break;
                        }

                        System.out.println("Entrez le nouveau nom de l'offre (laisser vide pour ne pas modifier):");
                        String nomOffre = scanner.nextLine();
                        if (!nomOffre.isEmpty()) {
                            promo.setNomOffre(nomOffre);
                        }

                        System.out.println("Entrez la nouvelle description de l'offre (laisser vide pour ne pas modifier):");
                        String description = scanner.nextLine();
                        if (!description.isEmpty()) {
                            promo.setDescription(description);
                        }

                        System.out.println("Entrez la nouvelle date de début de la promotion (laisser vide pour ne pas modifier):");
                        String dateDebutStr = scanner.nextLine();
                        if (!dateDebutStr.isEmpty()) {
                            promo.setDateDebut(Date.valueOf(dateDebutStr));
                        }

                        System.out.println("Entrez la nouvelle date de fin de la promotion (laisser vide pour ne pas modifier):");
                        String dateFinStr = scanner.nextLine();
                        if (!dateFinStr.isEmpty()) {
                            promo.setDateFin(Date.valueOf(dateFinStr));
                        }

                        TypeReduction typeReduction = promoService.getTypeReduction(promo);

                        System.out.println("Entrez la nouvelle valeur de la réduction (laisser vide pour ne pas modifier):");
                        String valeurReductionStr = scanner.nextLine();
                        if (!valeurReductionStr.isEmpty()) {
                            promo.setValeurReduction(Double.parseDouble(valeurReductionStr));
                        }

                        System.out.println("Entrez les nouvelles conditions de la promotion (laisser vide pour ne pas modifier):");
                        String conditions = scanner.nextLine();
                        if (!conditions.isEmpty()) {
                            promo.setConditions(conditions);
                        }

                        StatutOffre statutOffre = promoService.getStatutOffre(promo);

                        promoService.updatePromo(promo);
                        System.out.println("Promotion mise à jour avec succès.");

                    } catch (IllegalArgumentException e) {
                        System.out.println("Erreur: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        System.out.println("Entrez l'ID de la promotion à supprimer (UUID):");
                        UUID id = UUID.fromString(scanner.nextLine());

                        promoService.deletePromo(id);
                        System.out.println("Promotion supprimée avec succès.");

                    } catch (IllegalArgumentException e) {
                        System.out.println("Erreur: " + e.getMessage());
                    }
                    break;
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

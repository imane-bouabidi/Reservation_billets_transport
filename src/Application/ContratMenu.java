package Application;

import com.reservation_billet_transport.enums.StatutContrat;
import com.reservation_billet_transport.enums.StatutPartenaire;
import com.reservation_billet_transport.models.Contrat;
import com.reservation_billet_transport.models.Partenaire;
import com.reservation_billet_transport.services.ContratService;
import com.reservation_billet_transport.services.PartenaireService;

import java.sql.Date;
import java.util.Scanner;
import java.util.UUID;

public class ContratMenu {

    public static void display() {
        Scanner scanner = new Scanner(System.in);
        ContratService contratService = new ContratService();
        PartenaireService partenaireService = new PartenaireService();
        boolean back = false;

        while (!back) {
            System.out.println("1. Afficher tous les contrats");
            System.out.println("2. Ajouter un nouveau contrat");
            System.out.println("3. Modifier un contrat");
            System.out.println("4. Supprimer un contrat");
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
                        System.out.println("Entrez l'ID du partenaire pour ce contrat (UUID):");
                        UUID partenaireId = UUID.fromString(scanner.nextLine());
                        Partenaire partenaire = partenaireService.getPartenaireById(partenaireId);

                        if (partenaire == null) {
                            System.out.println("Partenaire non trouvé.");
                            break;
                        }

                        System.out.println("Entrez la date de début du contrat (YYYY-MM-DD):");
                        Date dateDebut = Date.valueOf(scanner.nextLine());

                        System.out.println("Entrez la date de fin du contrat (YYYY-MM-DD, laisser vide pour aucune fin):");
                        String dateFinStr = scanner.nextLine();
                        Date dateFin = dateFinStr.isEmpty() ? null : Date.valueOf(dateFinStr);

                        System.out.println("Entrez le tarif spécial en DH:");
                        double tarifSpecial = scanner.nextDouble();
                        scanner.nextLine();

                        System.out.println("Entrez les conditions de l'accord:");
                        String conditionsAccord = scanner.nextLine();

                        System.out.println("Le contrat est-il renouvelable (true/false):");
                        boolean renouvlable = scanner.nextBoolean();
                        scanner.nextLine();

                        StatutContrat statutContrat = contratService.getStatutContrat();

                        Contrat contrat = new Contrat();
                        contrat.setConditionsAccord(conditionsAccord);
                        contrat.setDateDebut(dateDebut);
                        contrat.setDateFin(dateFin);
                        contrat.setTarifSpecial(tarifSpecial);
                        contrat.setRenouvlable(renouvlable);
                        contrat.setStatutContrat(statutContrat);

                        contratService.addContrat(contrat);
                        System.out.println("Contrat ajouté avec succès.");

                    } catch (IllegalArgumentException e) {
                        System.out.println("Erreur: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Entrez l'ID du contrat à mettre à jour (UUID):");
                        UUID id = UUID.fromString(scanner.nextLine());

                        Contrat contrat = contratService.getContratById(id);
                        if (contrat == null) {
                            System.out.println("Contrat non trouvé.");
                            break;
                        }

                        System.out.println("Entrez la nouvelle date de début du contrat (laisser vide pour ne pas modifier):");
                        String dateDebutStr = scanner.nextLine();
                        if (!dateDebutStr.isEmpty()) {
                            contrat.setDateDebut(Date.valueOf(dateDebutStr));
                        }

                        System.out.println("Entrez la nouvelle date de fin du contrat (laisser vide pour ne pas modifier):");
                        String dateFinStr = scanner.nextLine();
                        if (!dateFinStr.isEmpty()) {
                            contrat.setDateFin(Date.valueOf(dateFinStr));
                        }

                        System.out.println("Entrez le nouveau tarif spécial (laisser vide pour ne pas modifier):");
                        String tarifSpecialStr = scanner.nextLine();
                        if (!tarifSpecialStr.isEmpty()) {
                            contrat.setTarifSpecial(Double.parseDouble(tarifSpecialStr));
                        }

                        System.out.println("Entrez les nouvelles conditions de l'accord (laisser vide pour ne pas modifier):");
                        String conditionsAccord = scanner.nextLine();
                        if (!conditionsAccord.isEmpty()) {
                            contrat.setConditionsAccord(conditionsAccord);
                        }

                        System.out.println("Le contrat est-il renouvelable (laisser vide pour ne pas modifier):");
                        String renouvlableStr = scanner.nextLine();
                        if (!renouvlableStr.isEmpty()) {
                            contrat.setRenouvlable(Boolean.parseBoolean(renouvlableStr));
                        }

                        System.out.println("Entrez le nouveau statut du contrat (laisser vide pour ne pas modifier):");
                        String statutContratStr = scanner.nextLine();
                        if (!statutContratStr.isEmpty()) {
                            contrat.setStatutContrat(StatutContrat.valueOf(statutContratStr.toUpperCase()));
                        }

                        contratService.updateContrat(contrat);
                        System.out.println("Contrat mis à jour avec succès.");

                    } catch (IllegalArgumentException e) {
                        System.out.println("Erreur: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        System.out.println("Entrez l'ID du contrat à supprimer (UUID):");
                        UUID id = UUID.fromString(scanner.nextLine());

                        contratService.deleteContrat(id);
                        System.out.println("Contrat supprimé avec succès.");

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

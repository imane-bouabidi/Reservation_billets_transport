package Application;

import com.reservation_billet_transport.enums.StatutBillet;
import com.reservation_billet_transport.enums.TypeTransport;
import com.reservation_billet_transport.models.Billet;
import com.reservation_billet_transport.models.Contrat;
import com.reservation_billet_transport.services.BilletService;
import com.reservation_billet_transport.services.ContratService;

import java.sql.Timestamp;
import java.util.Scanner;
import java.util.UUID;

public class BilletMenu {

    private static BilletService billetService = new BilletService();
    private static ContratService contratService = new ContratService();
    private static Scanner scanner = new Scanner(System.in);

    public static void display() {
        boolean running = true;

        while (running) {
            System.out.println("Menu Billet :");
            System.out.println("1. Ajouter un billet");
            System.out.println("2. Mettre à jour un billet");
            System.out.println("3. Supprimer un billet");
            System.out.println("4. Retour au menu principal");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    addBillet();
                    break;
                case 2:
                    updateBillet();
                    break;
                case 3:
                    deleteBillet();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    break;
            }
        }
    }

    private static void addBillet() {
        System.out.println("Ajouter un billet :");
        System.out.print("ID du contrat : ");
        UUID contratId = UUID.fromString(scanner.nextLine());

        Contrat contrat = contratService.getContratById(contratId);
        if (contrat == null) {
            System.out.println("Contrat non trouvé.");
            return;
        }

        TypeTransport typeTransport = billetService.getTypeTransport(null);

        System.out.print("Prix d'achat : ");
        double prixAchat = scanner.nextDouble();

        System.out.print("Prix de vente : ");
        double prixVente = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Date de vente (format: yyyy-MM-dd HH:mm:ss) : ");
        Timestamp dateVente = Timestamp.valueOf(scanner.nextLine().trim());

        StatutBillet statutBillet = billetService.getStatutBillet();

        Billet billet = new Billet();
        billet.setContrat(contrat);
        billet.setTypeTransport(typeTransport);
        billet.setPrixAchat(prixAchat);
        billet.setPrixVente(prixVente);
        billet.setDateVente(dateVente);
        billet.setStatutBillet(statutBillet);
        billetService.addBillet(billet);

        System.out.println("Billet ajouté avec succès.");
    }

    private static void updateBillet() {
        System.out.println("Mettre à jour un billet :");
        System.out.print("ID du billet à mettre à jour : ");
        UUID id = UUID.fromString(scanner.nextLine());

        Billet billet = billetService.getBilletById(id);
        if (billet == null) {
            System.out.println("Billet non trouvé.");
            return;
        }

        System.out.print("Nouveau prix d'achat (laisser vide pour ne pas modifier) : ");
        String prixAchatStr = scanner.nextLine();
        if (!prixAchatStr.isEmpty()) {
            billet.setPrixAchat(Double.parseDouble(prixAchatStr));
        }

        System.out.print("Nouveau prix de vente (laisser vide pour ne pas modifier) : ");
        String prixVenteStr = scanner.nextLine();
        if (!prixVenteStr.isEmpty()) {
            billet.setPrixVente(Double.parseDouble(prixVenteStr));
        }

        TypeTransport typeTransport = billetService.getTypeTransport(billet);
        billet.setTypeTransport(typeTransport);

        System.out.print("Nouvelle date de vente (format: yyyy-MM-dd HH:mm:ss, laisser vide pour ne pas modifier) : ");
        String dateVenteStr = scanner.nextLine();
        if (!dateVenteStr.isEmpty()) {
            billet.setDateVente(Timestamp.valueOf(dateVenteStr.trim()));
        }

        StatutBillet statutBillet = billetService.getStatutBillet(billet);
        billet.setStatutBillet(statutBillet);

        billetService.updateBillet(billet);
        System.out.println("Billet mis à jour avec succès.");
    }

    private static void deleteBillet() {
        System.out.println("Supprimer un billet :");
        System.out.print("ID du billet à supprimer : ");
        UUID id = UUID.fromString(scanner.nextLine());

        billetService.deleteBillet(id);
        System.out.println("Billet supprimé avec succès.");
    }
}

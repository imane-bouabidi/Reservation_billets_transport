package Application;

import com.reservation_billet_transport.dao.BilletDAO;
import com.reservation_billet_transport.dao.ContratDAO;
import com.reservation_billet_transport.models.Billet;
import com.reservation_billet_transport.models.Contrat;
import com.reservation_billet_transport.enums.StatutBillet;
import com.reservation_billet_transport.enums.TypeTransport;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class BilletMenu {

    private static BilletDAO billetDAO = new BilletDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void display() {
        boolean running = true;

        while (running) {
            System.out.println("Menu Billet :");
            System.out.println("1. Ajouter un billet");
            System.out.println("2. Afficher tous les billets");
            System.out.println("3. Mettre à jour un billet");
            System.out.println("4. Supprimer un billet");
            System.out.println("5. Retour au menu principal");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    addBillet();
                    break;
                case 2:
                    listBillets();
                    break;
                case 3:
                    updateBillet();
                    break;
                case 4:
                    deleteBillet();
                    break;
                case 5:
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
        
        System.out.print("Type de transport (EX: TRAIN, BUS) : ");
        TypeTransport typeTransport = TypeTransport.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Prix d'achat : ");
        double prixAchat = scanner.nextDouble();
        
        System.out.print("Prix de vente : ");
        double prixVente = scanner.nextDouble();
        
        System.out.print("Date de vente (format: yyyy-MM-dd HH:mm:ss) : ");
        Timestamp dateVente = Timestamp.valueOf(scanner.nextLine().trim() + " 00:00:00");
        
        System.out.print("Statut du billet (EX: VALIDE, ANNULE) : ");
        StatutBillet statutBillet = StatutBillet.valueOf(scanner.nextLine().toUpperCase());

        ContratDAO contratDAO = new ContratDAO();
        Contrat contrat = contratDAO.getContratById(contratId);

        Billet billet = new Billet(contrat, typeTransport, prixAchat, prixVente, dateVente, statutBillet);
        billetDAO.addBillet(billet);
    }

    private static void listBillets() {
        System.out.println("Liste des billets :");
        List<Billet> billets = billetDAO.getAllBillets();
        for (Billet billet : billets) {
            System.out.println(billet);
        }
    }

    private static void updateBillet() {
        System.out.println("Mettre à jour un billet :");
        System.out.print("ID du billet à mettre à jour : ");
        UUID id = UUID.fromString(scanner.nextLine());

        Billet billet = billetDAO.getBilletById(id);

        if (billet != null) {
            System.out.println("Modifier les détails du billet :");
            // Collect updated information
            System.out.print("Nouveau prix d'achat : ");
            double prixAchat = scanner.nextDouble();
            
            System.out.print("Nouveau prix de vente : ");
            double prixVente = scanner.nextDouble();
            
            System.out.print("Nouvelle date de vente (format: yyyy-MM-dd HH:mm:ss) : ");
            Timestamp dateVente = Timestamp.valueOf(scanner.nextLine().trim() + " 00:00:00");
            
            System.out.print("Nouveau statut du billet (EX: VALIDE, ANNULE) : ");
            StatutBillet statutBillet = StatutBillet.valueOf(scanner.nextLine().toUpperCase());

            billet.setPrixAchat(prixAchat);
            billet.setPrixVente(prixVente);
            billet.setDateVente(dateVente);
            billet.setStatutBillet(statutBillet);

            billetDAO.updateBillet(billet);
        } else {
            System.out.println("Billet non trouvé.");
        }
    }

    private static void deleteBillet() {
        System.out.println("Supprimer un billet :");
        System.out.print("ID du billet à supprimer : ");
        UUID id = UUID.fromString(scanner.nextLine());

        billetDAO.deleteBillet(id);
    }
}

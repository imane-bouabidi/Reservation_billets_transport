package Application;

import com.reservation_billet_transport.services.*;
import com.reservation_billet_transport.models.*;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ClientMenu {

    private UserService userService = new UserService();
    private VilleService villeService = new VilleService();
    private TrajetService trajetService = new TrajetService();
    private ReservationService reservationService = new ReservationService();
    private Scanner scanner = new Scanner(System.in);

    public void afficherMenuPrincipal() {
        while (true) {
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Enregistrer un nouveau client");
            System.out.println("2. Connexion client");
            System.out.println("3. Rechercher des billets");
            System.out.println("4. Quitter");

            int choix = scanner.nextInt();
            scanner.nextLine(); 

            switch (choix) {
                case 1:
                    enregistrerClient();
                    break;
                case 2:
                    connecterClient();
                    break;
                case 3:
                    rechercherBillets();
                    break;
                case 4:
                    System.out.println("Au revoir !");
                    return;
                default:
                    System.out.println("Choix invalide, réessayez.");
            }
        }
    }

    private void enregistrerClient() {
        System.out.println("Entrez votre nom:");
        String nom = scanner.nextLine();
        System.out.println("Entrez votre prénom:");
        String prenom = scanner.nextLine();
        System.out.println("Entrez votre email:");
        String email = scanner.nextLine();
        System.out.println("Entrez votre numéro de téléphone:");
        String phone = scanner.nextLine();

        User newUser = new User(UUID.randomUUID(), nom, prenom, email, phone);
        userService.ajouterUser(newUser);
        System.out.println("Client enregistré avec succès !");
    }

    private void connecterClient() {
        System.out.println("Entrez votre email:");
        String email = scanner.nextLine();

        if (userService.validerUser(email)) {
            System.out.println("Connexion réussie.");
            afficherMenuClient(email);
        } else {
            System.out.println("Client non trouvé.");
        }
    }

    private void afficherMenuClient(String email) {
        User user = userService.getAllUsers().stream()
            .filter(u -> u.getEmail().equals(email))
            .findFirst().orElse(null); 

        while (true) {
            System.out.println("=== Menu Client ===");
            System.out.println("1. Mettre à jour mes informations");
            System.out.println("2. Rechercher des billets");
            System.out.println("3. Consulter mes réservations");
            System.out.println("4. Déconnexion");

            int choix = scanner.nextInt();
            scanner.nextLine();  

            switch (choix) {
                case 1:
                    mettreAJourInformations(user);
                    break;
                case 2:
                    rechercherBillets();
                    break;
                case 3:
                    consulterReservations(user.getId());
                    break;
                case 4:
                    System.out.println("Déconnexion réussie.");
                    return;
                default:
                    System.out.println("Choix invalide, réessayez.");
            }
        }
    }

    private void mettreAJourInformations(User user) {
        System.out.println("Entrez votre nouveau numéro de téléphone:");
        String phone = scanner.nextLine();
        user.setPhone(phone);
        userService.updateUser(user);
        System.out.println("Informations mises à jour avec succès !");
    }

    private void rechercherBillets() {
        System.out.println("Entrez la ville de départ:");
        String villeDepart = scanner.nextLine();
        System.out.println("Entrez la ville d'arrivée:");
        String villeArrive = scanner.nextLine();
        System.out.println("Entrez la date de départ (yyyy-mm-dd):");
        String dateDepart = scanner.nextLine();

    }

    private void consulterReservations(UUID clientId) {
        List<Reservation> reservations = reservationService.getReservationsByUserId(clientId);
        if (reservations.isEmpty()) {
            System.out.println("Aucune réservation trouvée.");
        } else {
            for (Reservation res : reservations) {
                System.out.println(res);
            }
        }
    }
}

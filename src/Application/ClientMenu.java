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
//                case 3:
//                    rechercherBillets();
//                    break;
                case 4:
                    System.out.println("Au revoir !");
                    return;
                default:
                    System.out.println("Choix invalide, réessayez.");
            }
        }
    }

    public void enregistrerClient() {
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

    public void connecterClient() {
        System.out.println("Entrez votre email:");
        String email = scanner.nextLine();

        if (userService.validerUser(email)) {
            System.out.println("Connexion réussie.");
            afficherMenuClient(email);
        } else {
            System.out.println("Client non trouvé.");
        }
    }

    public void afficherMenuClient(String email) {
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
//                case 1:
//                    updateInformations(user);
//                    break;
//                case 2:
//                    rechercherBillets();
//                    break;
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
//
//    public void mettreAJourInformations(User user) {
//        System.out.println("Entrez votre nouveau numéro de téléphone:");
//        String phone = scanner.nextLine();
//        user.setPhone(phone);
//        userService.updateUser(user);
//        System.out.println("Informations mises à jour avec succès !");
//    }
//
//    public void rechercherBillets() {
//    	    System.out.println("Entrez la ville de départ:");
//    	    String nomVilleDepart = scanner.nextLine();
//    	    System.out.println("Entrez la ville d'arrivée:");
//    	    String nomVilleArrive = scanner.nextLine();
//    	    System.out.println("Entrez la date de départ (yyyy-mm-dd):");
//    	    String dateDepart = scanner.nextLine();
//
//    	    Ville villeDepart = villeService.getAllVilles().stream()
//    	                        .filter(v -> v.getNom().equalsIgnoreCase(nomVilleDepart))
//    	                        .findFirst().orElse(null);
//
//    	    Ville villeArrive = villeService.getAllVilles().stream()
//    	                        .filter(v -> v.getNom().equalsIgnoreCase(nomVilleArrive))
//    	                        .findFirst().orElse(null);
//
//    	    if (villeDepart == null || villeArrive == null) {
//    	        System.out.println("Ville de départ ou d'arrivée non trouvée.");
//    	        return;
//    	    }
//
//    	    List<Trajet> trajets = trajetService.getAllTrajets().stream()
//    	            .filter(t -> t.getVilleDepart().getId().equals(villeDepart.getId())
//    	                      && t.getVilleArrive().getId().equals(villeArrive.getId())
//    	                      && t.getDateDepart().toString().equals(dateDepart))
//    	            .toList();
//
//    	    if (trajets.isEmpty()) {
//    	        System.out.println("Aucun trajet trouvé pour ces critères.");
//    	    } else {
//    	        System.out.println("Trajets disponibles :");
//    	        for (Trajet trajet : trajets) {
//    	            System.out.println("Trajet ID : " + trajet.getId());
//    	            System.out.println("Départ : " + trajet.getVilleDepart().getNom() + " à " + trajet.getDateDepart());
//    	            System.out.println("Arrivée : " + trajet.getVilleArrive().getNom() + " à " + trajet.getDateArrivee());
//    	            System.out.println("-----------------------");
//    	        }
//    	    }
//    	}
//
//
//    }

	public void consulterReservations(UUID clientId) {
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

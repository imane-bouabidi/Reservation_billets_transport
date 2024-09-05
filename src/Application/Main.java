package Application;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Menu principal :");
            System.out.println("1. Gestion des partenaires");
            System.out.println("2. Gestion des contrats");
            System.out.println("3. Quitter");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    PartenaireMenu.display();
                    break;
                case 2:
                    ContratMenu.display();
                    break;
                case 3:
                    running = false;
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    break;
            }
        }

        scanner.close();
    
	}

}

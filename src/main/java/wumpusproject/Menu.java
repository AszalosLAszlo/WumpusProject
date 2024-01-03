package wumpusproject;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Ez az osztály felelős a Wumpus Projekt játék
 * menüjének kezeléséért és megjelenítéséért.
 * A menü két funkciót kínál: pályaszerkesztés
 * és játék indítása.
 */
public class Menu {
    /**
     * Megjeleníti a Wumpus Projekt játék menüjét a konzolon.
     */
    public static void display() {
        System.out.println("Wumpus Projekt Menu");
        System.out.println("1. Map editing");
        System.out.println("2. Game");
        System.out.println("3. Exit");
        System.out.println("4. Show High Score");
    }

    /**
     * Felhasználótól bekéri a kiválasztott menüpont sorszámát.
     *
     * @return A felhasználó által választott menüpont sorszáma.
     */
    public static int getUserChoice() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Select a menu item: ");
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            return getUserChoice();
        }
    }
}

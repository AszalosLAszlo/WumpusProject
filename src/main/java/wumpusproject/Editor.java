package wumpusproject;

import java.util.Scanner;

/**
 * Ez az osztály a pályaszerkesztésért felelős
 */
public class Editor {
    private final char[][] board;

    // (Konstansok az elemek reprezentálásához...))
    private static final char WALL = 'F';
    private static final char PIT = 'V';
    private static final char WUMPUS = 'W';
    private static final char GOLD = 'A';
    private static final char HERO = 'H';

    /**
     * Konstruktor, inicializálja a pályát egy adott méretű négyzetrácsos táblával.
     *
     * @param N A pálya mérete.
     */
    public Editor(int N) {

        board = createEmptyBoard(N);
    }

    /**
     * Pályaszerkesztő műveleteket valósít meg. Lehetőséget ad a felhasználónak
     * a pályaelemek hozzáadására és eltávolítására, valamint a pálya megjelenítésére.
     */
    public void editBoard() {
        boolean editing = true;
        Scanner scanner = new Scanner(System.in);

        while (editing) {
            printBoard(board);
            System.out.println("Track editing operations:");
            System.out.println("1. Add a wall");
            System.out.println("2. Add a stack");
            System.out.println("3. Add wumpus");
            System.out.println("4. Add gold");
            System.out.println("5. Add a hero");
            System.out.println("6. Wall removal");
            System.out.println("7. Stack removal");
            System.out.println("8. Wumpus removal");
            System.out.println("9. Gold removal");
            System.out.println("10. Remove a hero");
            System.out.println("11. Complete track editing");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addWall();
                    break;
                case 2:
                    addPit();
                    break;
                case 3:
                    addWumpus();
                    break;
                case 4:
                    addGold();
                    break;
                case 5:
                    addHero();
                    break;
                case 6:
                    removeWall();
                    break;
                case 7:
                    removePit();
                    break;
                case 8:
                    removeWumpus();
                    break;
                case 9:
                    removeGold();
                    break;
                case 10:
                    removeHero();
                    break;
                case 11:
                    editing = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    /**
     * Üres pályát hoz létre, ahol a pályaszéleken falak találhatók.
     *
     * @param N A pálya mérete.
     * @return Az inicializált pálya.
     */
    private char[][] createEmptyBoard(int N) {
        char[][] board = new char[N][N];

        // (Első és utolsó sorban falak...)
        for (int i = 0; i < N; i++) {
            board[0][i] = 'F';
            board[N - 1][i] = 'F';
        }


        // (Első és utolsó oszlopban falak...)
        for (int i = 0; i < N; i++) {
            board[i][0] = 'F';
            board[i][N - 1] = 'F';
        }

        // (Üres mezők szóközzel...)
        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < N - 1; j++) {
                board[i][j] = ' ';
            }
        }

        return board;
    }

    /**
     * Kiírja a jelenlegi pálya állapotát a konzolra.
     *
     * @param board A megjelenítendő pálya.
     */
    private void printBoard(char[][] board) {
        //
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void addElement(char element) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the " + getElementName(element) + " position (e.g. 'c4'): ");
        String positionStr = scanner.next().toLowerCase();
        Position position = parsePosition(positionStr);

        if (isValidPosition(position, board) && board[position.getRow()][position.getCol()] == ' ') {
            board[position.getRow()][position.getCol()] = element;
            System.out.println(getElementName(element) + " added to track.");
        } else {
            System.out.println("Invalid position or field already taken.");
        }
    }

    private void removeElement(char element) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the " + getElementName(element) + " position (e.g. 'c4'): ");
        String positionStr = scanner.next().toLowerCase();
        Position position = parsePosition(positionStr);

        if (board[position.getRow()][position.getCol()] == element) {
            board[position.getRow()][position.getCol()] = ' ';
            System.out.println(getElementName(element) + " removed from the track.");
        } else {
            System.out.println("Invalid position or field does not contain " + getElementName(element) + ".");
        }
    }

    private String getElementName(char element) {
        switch (element) {
            case WALL:
                return "wall";
            case PIT:
                return "pit";
            case WUMPUS:
                return "wumpus";
            case GOLD:
                return "gold";
            case HERO:
                return "hero";
            default:
                return "unknown";
        }
    }


    /**
     * Fal hozzáadása a pályához a felhasználó által megadott pozíció alapján.
     */
    private void addWall() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the position of the wall (e.g. 'c4'): ");
        String positionStr = scanner.next().toLowerCase();

        Position position = parsePosition(positionStr);

        if (isValidPosition(position, board) &&
                board[position.getRow()][position.getCol()] == ' ') {
            board[position.getRow()][position.getCol()] = 'F';
        } else {
            System.out.println("Invalid position or field already taken.");
        }
    }
    /**
     * Verem hozzáadása a pályához a felhasználó által megadott pozíció alapján.
     */
    private void addPit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the stack position (e.g. 'c4'): ");
        String positionStr = scanner.next().toLowerCase();

        Position position = parsePosition(positionStr);

        if (isValidPosition(position, board) &&
                board[position.getRow()][position.getCol()] == ' ') {
            board[position.getRow()][position.getCol()] = 'V';
        } else {
            System.out.println("Invalid position or field already taken.");
        }

    }
    /**
     * Wumpus hozzáadása a pályához a felhasználó által megadott pozíció alapján.
     */
    private void addWumpus() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the wumpus position (e.g. 'c4'): ");
        String positionStr = scanner.next().toLowerCase();

        Position position = parsePosition(positionStr);

        if (isValidPosition(position, board) &&
                board[position.getRow()][position.getCol()] == ' ') {
            board[position.getRow()][position.getCol()] = 'W';
        } else {
            System.out.println("Invalid position or field already taken.");
        }
    }
    /**
     * Arany hozzáadása a pályához a felhasználó által megadott pozíció alapján.
     */
    private void addGold() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the position of gold (e.g. 'c4'): ");
        String positionStr = scanner.next().toLowerCase();

        Position position = parsePosition(positionStr);

        if (isValidPosition(position, board) &&
                board[position.getRow()][position.getCol()] == ' ') {
            board[position.getRow()][position.getCol()] = 'A';
        } else {
            System.out.println("Invalid position or field already taken.");
        }
    }
    /**
     * Hős hozzáadása a pályához a felhasználó által megadott pozíció alapján.
     */
    private void addHero() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the hero's position (e.g. 'c4'): ");
        String positionStr = scanner.next().toLowerCase();

        Position position = parsePosition(positionStr);

        if (isValidPosition(position, board) &&
                board[position.getRow()][position.getCol()] == ' ') {
            board[position.getRow()][position.getCol()] = 'H';
        } else {
            System.out.println("Invalid position or field already taken.");
        }
    }
    /**
     * Fal eltávolítása a pályáról a felhasználó által megadott pozíció alapján.
     */
    private void removeWall() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the position of the wall (e.g. 'c4'): ");
        String positionStr = scanner.next().toLowerCase();

        Position position = parsePosition(positionStr);

        if (board[position.getRow()][position.getCol()] == 'F') {
            board[position.getRow()][position.getCol()] = ' ';
        } else {
            System.out.println("Invalid position or field blank.");
        }
    }
    /**
     * Verem eltávolítása a pályáról a felhasználó által megadott pozíció alapján.
     */
    private void removePit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the stack position (e.g. 'c4'): ");
        String positionStr = scanner.next().toLowerCase();

        Position position = parsePosition(positionStr);

        if (board[position.getRow()][position.getCol()] == 'V') {
            board[position.getRow()][position.getCol()] = ' ';
        } else {
            System.out.println("Invalid position or field blank.");
        }

    }
    /**
     * Wumpus eltávolítása a pályáról a felhasználó által megadott pozíció alapján.
     */
    private void removeWumpus() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the wumpus position (e.g. 'c4'): ");
        String positionStr = scanner.next().toLowerCase();

        Position position = parsePosition(positionStr);

        if (board[position.getRow()][position.getCol()] == 'W') {
            board[position.getRow()][position.getCol()] = ' ';
        } else {
            System.out.println("Invalid position or field blank.");
        }
    }
    /**
     * Arany eltávolítása a pályáról a felhasználó által megadott pozíció alapján.
     */
    private void removeGold() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the position of gold (e.g. 'c4'): ");
        String positionStr = scanner.next().toLowerCase();

        Position position = parsePosition(positionStr);

        if (board[position.getRow()][position.getCol()] == 'A') {
            board[position.getRow()][position.getCol()] = ' ';
        } else {
            System.out.println("Invalid position or field blank.");
        }
    }
    /**
     * Hős eltávolítása a pályáról a felhasználó által megadott pozíció alapján.
     */
    private void removeHero() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the hero's position (e.g. 'c4'): ");
        String positionStr = scanner.next().toLowerCase();

        Position position = parsePosition(positionStr);

        if (board[position.getRow()][position.getCol()] == 'H') {
            board[position.getRow()][position.getCol()] = ' ';
        } else {
            System.out.println("Invalid position or field blank.");
        }
    }

    private static Position parsePosition(String positionStr) {
        int row = positionStr.charAt(1) - '1';
        int col = positionStr.charAt(0) - 'a';
        return new Position(row, (char) col);
    }

    //(A megadott pozíció érvényes-e, nem megy-e túl a pályakereten...)
    public static boolean isValidPosition(Position position, char[][] board) {
        int N = board.length;
        int row = position.getRow();
        int col = position.getCol();
        return row >= 0 && row < N && col >= 0 && col < N;
    }

    public char[][] getBoard() {

        return board;
    }
}

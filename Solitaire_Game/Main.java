import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    // declaring all private and public variables here

    private static int moves = 0;
    private static int score = 0;
    private static final Scanner sc = new Scanner(System.in); // defining variable to save the input from the user
    static final Random rand = new Random();
    static final String[] suits = { "spade", "heart", "club", "diamond" };
    static final String[] values = { "ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen",
            "king" };
    static Card[] pack;
    public static Stack<Card> hand, waste;
    public static Stack<Stack<Card>> tab, found;
    static ArrayList<int[]> posMoves;
    private static boolean gameWon;

    public static int source, destination, n = 1;

    public static void main(String[] args) {
        boolean restart;
        do {
            // Initliazing the variables
            pack = new Card[52];
            hand = new Stack<>();
            waste = new Stack<>();
            tab = new Stack<>();
            found = new Stack<>();
            posMoves = new ArrayList<>();
            gameWon = false;
            restart = false;

            // Fill the pack with cards
            Deck.fillPack();
            // Deck.printPack();

            // Shuffle the pack
            Deck.shufflePack();
            // printPack();

            // Place the cards onto the table
            Table.DrawTable();

            Instruction();
            int posMovesC;

            // Run main game loop
            while (true) {

                posMovesC = 0;
                Hint.checkPossibleMoves();

                Table.printTable();

                // Query the player
                System.out.println();
                System.out.println("Moves = " + moves + "  " + "Score = " + score);
                System.out.println();

                boolean repeat, exitGameLoop;
                restart = true;
                exitGameLoop = true;
                do {
                    exitGameLoop = false;
                    repeat = false;
                    System.out.print("Enter your command = ");
                    String[] input = sc.nextLine().split(" ");
                    String inputMove = convertStringArrayToString(input, "");
                    System.out.println();

                    if (input.length > 0) {

                        switch (input[0]) {
                            case "t":
                            case "T":
                                Move.turnHand();
                                moves++;
                                break;

                            case "i":
                            case "I":
                                Instruction();
                                repeat = true;

                            case "z":
                            case "Z":
                                if (posMoves.isEmpty()) {
                                    System.out.println("turn");
                                } else {
                                    int[] move = posMoves.get(posMovesC);
                                    System.out.println("move " + move[1] + " card(s) form column no. " + move[0]
                                            + " to column no. " + move[2]);
                                    posMovesC++;
                                    if (posMovesC == posMoves.size()) {
                                        posMovesC = 0;
                                    }
                                }

                                repeat = true;
                                break;
                            case "q":
                            case "Q":
                                exitGameLoop = true;
                                restart = false;
                                break;

                            default:
                                repeat = true;
                        }

                    }

                    if (inputMove.length() == 3) {

                        try {
                            if (inputMove.toLowerCase().charAt(0) == 'p') {
                                source = 8;
                            } else {
                                source = Integer.parseInt(inputMove.substring(0, 1));
                            }

                            if (inputMove.toLowerCase().charAt(1) == 'd') {
                                destination = 9;
                            } else if (inputMove.charAt(1) == 'h') {
                                destination = 10;
                            } else if (inputMove.toLowerCase().charAt(1) == 'c') {
                                destination = 11;
                            }

                            else if (inputMove.toLowerCase().charAt(1) == 's') {
                                destination = 12;
                            }

                            else {
                                destination = Integer.parseInt(inputMove.substring(1, 2));
                            }

                            n = Integer.parseInt(inputMove.substring(2, 3));

                            repeat = Move.moveCard(source, destination, n);
                            if (repeat == false) {
                                moves++;
                                score += 5;
                            }
                        }

                        catch (Exception e) {
                            System.out.println("Please enter the valid command\n");
                        }
                    }

                    if (inputMove.length() == 2) {
                        try {
                            if (inputMove.toLowerCase().charAt(0) == 'p') {
                                source = 8;
                            } else {
                                source = Integer.parseInt(inputMove.substring(0, 1));
                            }

                            if (inputMove.toLowerCase().charAt(1) == 'd') {
                                destination = 9;
                            } else if (inputMove.charAt(1) == 'h') {
                                destination = 10;
                            } else if (inputMove.toLowerCase().charAt(1) == 'c') {
                                destination = 11;
                            }

                            else if (inputMove.toLowerCase().charAt(1) == 's') {
                                destination = 12;
                            }

                            else {
                                destination = Integer.parseInt(inputMove.substring(1, 2));
                            }

                            repeat = Move.moveCard(source, destination, 1);
                            if (repeat == false) {
                                moves++;
                                score += 5;
                            }
                        }

                        catch (Exception e) {
                            System.out.println("Please enter the valid command\n");
                            repeat = true;
                        }

                    }

                } while (repeat);

                checkGameWon();

                if (exitGameLoop) {
                    break;
                }
            }

            if (gameWon) {
                System.out.println("You win, congratulations!");
            } else {
                System.out.println("Exiting game");
            }
        } while (restart);
    }

    private static void checkGameWon() {
        for (int i = 0; i < 4; i++) {
            if (found.get(i).size() != 13) {
                return;
            }
        }
        gameWon = true;
    }

    public static String convertStringArrayToString(String[] strArr, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (String str : strArr)
            sb.append(str).append(delimiter);
        return sb.substring(0, sb.length());
    }

    public static void Instruction() {
        System.out.println();
        System.out.println("-----------------Instructions:-----------------");
        System.out.println("- Type 't' to turn over the hand.");
        System.out.println(
                "- Type '123' where: ");
        System.out.println("\t'1' refers to the column/area you wish to move cards from, ");
        System.out.println("\t'2' refers to the column/area you want to move them to, ");
        System.out.println("\t'3' refers to the number of cards you wish to move.");
        System.out.println(
                "- Type 'p' to refer to the pile and D, H, C, S to refer to the foundations.");

        System.out.println("- Type 'z' to recieve a hint.");
        System.out.println("- Type 'q' to exit the game.");
        System.out.println("- Press 'i' to display instruction set again.");
        System.out.println("- '[]' denotes empty \t 'XX' denotes hidden.");
        System.out.println("Only for hint command output the column 8 referes to P (pile) and column 9,10,11,12 refers to D,H,C,S foundations");
        System.out.println("- The commands are not case-sensitive.");
        System.out.println();

    }

}

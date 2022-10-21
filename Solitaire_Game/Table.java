
import java.util.Stack;

public class Table {
    // the function belows will make the table for the first time when the game
    // starts
    static void DrawTable() {
        int cardCounter = 0;

        for (int a = 0; a < 7; a++) { // the range is 7 becasue we have 7 columns
            Main.tab.push(new Stack<Card>()); // pushing cards into the stack of cards in every column

            for (int b = 0; b < a + 1; b++) {
                Main.tab.get(a).push(Main.pack[cardCounter]); // pushing cards like 1 in 1st column, 2 in 2nd columns
                                                              // and so on

                if (b != a) {
                    Main.tab.get(a).peek().setHidden(true); // setting all the cards except first as hidden
                }
                cardCounter++;
            }
        }

        // placing the remiang cards in hand
        while (cardCounter < 52) {
            Main.hand.push(Main.pack[cardCounter]);
            Main.hand.peek().setHidden(true); // checking the card in pack and hidding it
            cardCounter++;
        }

        // making foundations
        for (int a = 0; a < 4; a++) {
            Main.found.push(new Stack<Card>());
        }
    }

    static void printTable() {
        // Print the hand and foundations indexes
        System.out.println(Card.BLUE + "\t\tP\t\t D  \t H \t C \t S" + Card.RESET);

        // Print the hand
        if (Main.hand.empty()) {
            System.out.print("\t[] ");
        } else {
            System.out.print("\t" + Main.hand.peek().getCard() + " ");
        }

        // Print the waste
        if (Main.waste.empty()) {
            System.out.print("\t[]    ");
        } else {
            System.out.print("\t" + Main.waste.peek().getCard() + "    ");
        }

        // Print the foundations
        System.out.print("\t");
        for (int i = 0; i < 4; i++) {
            if (Main.found.get(i).empty()) {
                System.out.print("\t[] ");
            } else {
                System.out.print("\t" + Main.found.get(i).peek().getCard() + " ");
            }
        }
        System.out.println();
        System.out.println();

        // Print the column indexes
        System.out.println(Card.BLUE + "\t01\t02\t03\t04\t05\t06\t07" + Card.RESET);

        // Print the tableau
        for (int i = 0; i < 19; i++) {
            if (isColumnEmpty(i)) {
                break;
            }
            for (int j = 0; j < 7; j++) {
                if (i >= Main.tab.get(j).size()) {
                    System.out.print("\t   ");
                } else {
                    System.out.print("\t" + Main.tab.get(j).get(i).getCard() + " ");
                }
            }
            System.out.println();
        }
    }

    private static boolean isColumnEmpty(int index) {
        for (int a = 0; a < 7; a++) {
            if (index < Main.tab.get(a).size()) {
                return false;
            }
        }
        return true;
    }
}

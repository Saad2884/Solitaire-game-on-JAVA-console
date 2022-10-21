
// this class is additional to the task
// this will provide the user some hint of what our next would could be

import java.util.Stack;

public class Hint {
    static void checkPossibleMoves() {
        Main.posMoves.clear();
        // Check the card a the top of the waste
        if (!Main.waste.empty()) {
            considerCard(Main.waste.peek(), 7, 1);
        }
        // Check the cards in the tableau
        for (Stack<Card> col : Main.tab) {
            for (Card card : col) {
                if (!card.getHidden()) {
                    considerCard(card, Main.tab.indexOf(col), col.size() - col.indexOf(card));
                }
            }
        }
        // Check the cards at the tops of the foundations
        for (Stack<Card> col : Main.found) {
            if (!col.empty()) {
                considerCard(col.peek(), Main.found.indexOf(col) + 8, 1);
            }
        }
    }

    private static void considerCard(Card card, int source, int n) {
        if (!(source > 7) && n == 1) {
            for (int i = 0; i < 4; i++) {
                if (Write.isValidForFoundations(card, i, true)) {
                    int[] move = { source + 1, n, i + 9 };
                    Main.posMoves.add(move);
                }
            }
        }
        for (int i = 0; i < 7; i++) {
            if (Write.isValidForTableau(card, i, true)) {
                int[] move = { source + 1, n, i + 1 };
                Main.posMoves.add(move);
            }
        }
    }
}

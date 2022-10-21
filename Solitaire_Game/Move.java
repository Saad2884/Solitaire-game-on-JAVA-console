
import java.util.Stack;

// this class is made to movethe card from one place to another

public class Move {

    // method below is used to extract card from the pile
    static void turnHand() {
        if (Main.hand.empty()) {
            resetHand();
        } else {
            Main.waste.push(Main.hand.pop());
            Main.waste.peek().setHidden(false);
        }
    }

    // method below will tell if the pile is empty of still contain cards
    private static void resetHand() {
        while (!Main.waste.empty()) {
            Main.hand.push(Main.waste.pop());
            Main.hand.peek().setHidden(true);
        }
        Main.waste.clear();
    }

    // this method will card from source to destination by reading the card from the
    // source and writing the card in destination
    static boolean moveCard(int source, int destination, int number) {
        Stack<Card> cardStack;
        cardStack = Read.readCardStack(source, number);

        if (cardStack != null) {
            boolean writeFail = Write.writeCardStack(cardStack, destination);

            if (!writeFail) {
                Remove.removeCardStack(source, number);
                revealCard(source);
                return false;
            }
        }

        return true;
    }

    // this function will reveal the card after the card over it is moved to other
    // destination
    private static void revealCard(int loc) {
        if (loc >= 1 && loc <= 7) {
            int index = loc - 1;
            if (!Main.tab.get(index).empty()) {
                Main.tab.get(index).peek().setHidden(false);
            }
        }
    }

}

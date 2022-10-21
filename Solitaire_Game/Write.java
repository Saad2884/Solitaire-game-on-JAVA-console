
import java.util.Arrays;
import java.util.Stack;

// this class will write the card to the destination after reading its value from the source and removing it from there.

public class Write {
    private static boolean writeCardStackToTableau(Stack<Card> cardStack, int loc) {
        int index = loc - 1;
        if (isValidForTableau(cardStack.peek(), index, false)) {
            while (!cardStack.empty()) {
                Main.tab.get(index).push(cardStack.pop());
            }
            return false;
        }
        return true;
    }

    private static boolean writeCardStackToFoundation(Stack<Card> cardStack, int loc) {
        int index = loc - 9;
        if (isValidForFoundations(cardStack.peek(), index, false)) {
            while (!cardStack.empty()) {
                Main.found.get(index).push(cardStack.pop());
            }
            return false;
        }
        return true;
    }

    static boolean isValidForTableau(Card placee, int index, boolean silent) {
        if (Main.tab.get(index).empty()) {
            if (placee.getValue().equals("king")) {
                return true;
            }
            if (!silent) {
                System.out.println("Only kings can be placed in empty columns");
            }
            return false;
        }
        Card placed = Main.tab.get(index).peek();
        // System.out.println("placed.getValue() = " + placed.getValue());
        int valIndex = Arrays.asList(Main.values).indexOf(placed.getValue()) - 1;
        // System.out.println("valIndex = " + valIndex);
        if (valIndex >= 0 && valIndex < 13) {
            if (!placed.getColor().equals(placee.getColor())) {
                if (Main.values[valIndex].equals(placee.getValue())) {
                    return true;
                }
                if (!silent) {
                    System.out.println("Cards can only be stacked sequentially");
                }
                return false;
            }
            if (!silent) {
                System.out.println("Only cards of differing colour may be stacked on the tableau");
            }
            return false;
        }
        if (!silent) {
            System.out.println("That is not a valid movement");
        }
        return false;
    }

    static boolean isValidForFoundations(Card placee, int index, boolean silent) {
        if (Main.found.get(index).empty()) {
            if (placee.getValue().equals("ace")) {
                return true;
            }
            if (!silent) {
                System.out.println("Only aces can be placed in empty foundations");
            }
            return false;
        }
        Card placed = Main.found.get(index).peek();
        int valIndex = Arrays.asList(Main.values).indexOf(placed.getValue()) + 1;
        if (valIndex >= 0 && valIndex < 13) {
            if (placed.getSuit().equals(placee.getSuit())) {
                if (Main.values[valIndex].equals(placee.getValue())) {
                    return true;
                }
                if (!silent) {
                    System.out.println("Cards can only be stacked sequentially");
                }
                return false;
            }
            if (!silent) {
                System.out.println("Only cards of the same suit may be stacked on the foundations");
            }
            return false;
        }
        if (!silent) {
            System.out.println("That is not a valid movement");
        }
        return false;
    }

    static boolean writeCardStack(Stack<Card> cardStack, int loc) {
        switch (loc) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return writeCardStackToTableau(cardStack, loc);
            case 8:
                System.out.println("You cannot place a card in the waste");
                return true;
            case 9:
            case 10:
            case 11:
            case 12:
                if (cardStack.size() == 1) {
                    return writeCardStackToFoundation(cardStack, loc);
                } else {
                    System.out.println("You can only move one card at a time to the foundations");
                }
            default:
                System.out.println("Destination column/area not recognised");
                return true;
        }
    }
}

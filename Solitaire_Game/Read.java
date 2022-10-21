
import java.util.Stack;

// this class is used to read the card's rank, suit and value form the source to compare it with the values of the destination cards

public class Read {

    private static Stack<Card> readCardStackFromTableau(int location, int number) {
        int index = location - 1;
        if (number > Main.tab.get(index).size()) {
            System.out.println("There aren't enough cards in that column");
            return null;
        }
        Stack<Card> cardStack = new Stack<>();
        for (int i = 0; i < number; i++) {
            if (Main.tab.get(index).get(Main.tab.get(index).size() - 1 - i).getHidden()) {
                System.out.println("There aren't enough visible cards in that column");
                return null;
            }
            cardStack.push(Main.tab.get(index).get(Main.tab.get(index).size() - 1 - i));
        }
        return cardStack;
    }

    private static Stack<Card> readCardStackFromWaste() {
        if (Main.waste.empty()) {
            System.out.println("The waste is empty");
            return null;
        }
        Stack<Card> cardStack = new Stack<>();
        cardStack.push(Main.waste.peek());
        return cardStack;
    }

    private static Stack<Card> readCardStackFromFoundations(int location) {
        int index = location - 9;
        if (Main.found.get(index).empty()) {
            System.out.println("This foundation is empty");
            return null;
        }
        Stack<Card> cardStack = new Stack<>();
        cardStack.push(Main.found.get(index).peek());
        return cardStack;
    }

    static Stack<Card> readCardStack(int loc, int n) {
        // Check that n is valid
        if (n < 1 || n > 13) {
            System.out.println("You can't move that many cards at a time");
            return null;
        }

        switch (loc) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return readCardStackFromTableau(loc, n);
            case 8:
                if (n == 1) {
                    return readCardStackFromWaste();
                } else {
                    System.out.println("You can only move one card at a time from the waste");
                    return null;
                }
            case 9:
            case 10:
            case 11:
            case 12:
                if (n == 1) {
                    return readCardStackFromFoundations(loc);
                } else {
                    System.out.println("You can only move one card at a time from the foundations");
                }
            default:
                System.out.println("Source column/area not recognised");
                return null;
        }
    }
}

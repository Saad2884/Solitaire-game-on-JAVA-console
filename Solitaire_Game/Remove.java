
// after reading the value if card is valid for the destination then it will be removed from the source by the move command
// the removing of the card is done by this class

public class Remove {
    static void removeCardStack(int loc, int n) {
        switch (loc) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                removeCardStackFromTableau(loc, n);
                break;
            case 8:
                removeCardFromWaste();
                break;
            case 9:
            case 10:
            case 11:
            case 12:
                removeCardFromFoundations(loc);
                break;
            default:
                System.out.println("ERROR: Location to remove card stack from not recognised");
        }
    }

    private static void removeCardStackFromTableau(int loc, int n) {
        int index = loc - 1;
        for (int i = 0; i < n; i++) {
            Main.tab.get(index).pop();
        }
    }

    private static void removeCardFromWaste() {
        Main.waste.pop();
    }

    private static void removeCardFromFoundations(int loc) {
        int index = loc - 9;
        Main.found.get(index).pop();
    }
}

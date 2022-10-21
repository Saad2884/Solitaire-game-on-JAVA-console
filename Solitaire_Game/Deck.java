

public class Deck {

    // the function below will generate cards and put in the 'pack' of cards (in
    // series).
    static void fillPack() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                // System.out.println("Instantiating card with: suit = " + suits[i] + " value =
                // " + values[j]);
                Main.pack[i * 13 + j] = new Card(Main.suits[i], Main.values[j], false);
            }
        }
    }

    // the below function will print the cards on the console if needed
    static void printPack() {
        for (int a = 0; a < 52; a++) {
            System.out.println(Main.pack[a].getCard());
        }
    }

    // the function below will shuffle the card order
    static void shufflePack() {
        for (int a = 0; a < 52; a++) {
            int index = Main.rand.nextInt(52 - a);
            Card temp = Main.pack[index];
            Main.pack[index] = Main.pack[51 - a];
            Main.pack[51 - a] = temp;
        }
    }
}

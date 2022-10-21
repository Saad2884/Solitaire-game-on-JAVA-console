
// this class is made to make the cards with ranks, suits and color

public class Card {

    private boolean hidden;
    private final String suit, value, color;
    private final String suitChar, valueChar;
    private static final String RED = "\u001B[31m";
    private static final String BLACK = "\u001B[30m";
    static final String BLUE = "\u001B[34m";
    static final String RESET = "\u001B[0m";
    private static final String HEART = "\u0003";
    private static final String DIAMOND = "\u0004";
    private static final String SPADE = "\u0006";
    private static final String CLUB = "\u0005";
    private static final String HIDDEN = "XX";

    public Card(String suit, String value, boolean hidden) {
        this.suit = suit;
        this.value = value;
        this.hidden = hidden;

        suitChar = genSuit();
        valueChar = genValue();
        color = genColor();

    }

    private String genSuit() {
        switch (suit) {
            case "spade":
                return SPADE;
            case "heart":
                return HEART;
            case "club":
                return CLUB;
            case "diamond":
                return DIAMOND;
        }
        return "";
    }

    private String genValue() {
        switch (value) {
            case "ace":
                return "A";
            case "jack":
                return "J";
            case "king":
                return "K";
            case "queen":
                return "Q";
            case "10":
                return "10";
            case "2":
                return "2";
            case "3":
                return "3";
            case "4":
                return "4";
            case "5":
                return "5";
            case "6":
                return "6";
            case "7":
                return "7";
            case "8":
                return "8";
            case "9":
                return "9";

        }
        return "";
    }

    private String genColor() {
        switch (suit) {
            case "spade":
            case "club":
                return "black";

            case "heart":
            case "diamond":
                return "red";
        }
        return "";
    }

    String getSuit() {
        return suit;
    }

    String getColor() {
        return color;
    }

    String getValue() {
        return value;
    }

    public String getCard() {
        if (hidden == true) {
            return HIDDEN;
        } else {
            if (color.equals("black")) {
                return BLACK + suitChar + valueChar + RESET;
            } else {
                return RED + suitChar + valueChar + RESET;
            }
        }
    }

    public void setHidden(boolean condition) {
        hidden = condition;
    }

    public boolean getHidden() {
        return hidden;
    }

}

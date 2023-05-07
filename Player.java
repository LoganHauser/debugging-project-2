import java.util.ArrayList;

public class Player {
    public String name;
    public Hand hand;
    //ArrayList to store the card values this player has matched
    public ArrayList<Card.Value> matches;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand(100);
        this.matches = new ArrayList<>();
    }

    //Print all cards in this player's hand
    public void printCards() {
        this.hand.printCards();
    }

    //Return wether this player has any cards with the given value
    public boolean hasCardWithValue(Card.Value cardValue) {
        return this.hand.hasCardWithValue(cardValue);
    }

    //Return the number of cards in this player's hand with the given value
    public int countMatchingCards(Card.Value cardValue) {
        return this.hand.countMatchingCards(cardValue);
    }

    //Give all cards in this player's hand with the given value to another player
    public void giveCardsWithValueTo(Card.Value cardValue, Player otherPlayer) {
        //This print statement is important because it is useful to see which cards are being transferred to another player in case the wrong cards are being transferred.
        Main.debugPrint("Gave " + countMatchingCards(cardValue) + " cards with value " + cardValue + " from " + this.name + " to " + otherPlayer.name);
        this.hand.giveCardsWithValueTo(cardValue, otherPlayer.hand);
    }

    /*
      Check if this player has made a match of 4 cards with the same value, return the value they matched,
      and add it to this player's list of matches
    */
    public Card.Value checkForMatch() {
        Card.Value matchedValue = null;
        for (Card card : this.hand.cards) {
            if (this.countMatchingCards(card.value) == 4) {
                this.matches.add(card.value);
                matchedValue = card.value;
                break;
            }
        }
        if (matchedValue != null) {
            this.hand.removeCardsWithValue(matchedValue);
        }
        //This print statement is important because the method could not be finding matches correctly.
        Main.debugPrint("checkForMatch() returned " + matchedValue);
        return matchedValue;
    }
}

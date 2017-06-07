/*
 * Author: Jasper Lelijveld
 * Based on: Think Java: How to Think Like a Computer Scientist
 * Time spent: +- 3 hours
 * 
 *
 * First java application written by the author.
 * It simulates a deck of cards
 * with separed hands, all with some appropriate methods.
 */
package deckofcards;


/**
 *
 * @author JasperL
 */
public class DeckOfCards {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Card card1 = new Card(1, 10);
        Deck deck = new Deck();
        deck.shuffleDeck();
        Hand hand1 = new Hand(deck, 0, 4);
        Hand hand2 = new Hand(deck, 5, 9);
        Hand pack = new Hand(deck, 10, 51);
        deck.printDeck();
        hand1.printHand();
    }
    
    
    public static int findBisect(Card[] cards, Card card, int low, int high){
        System.out.println(low + ", " + high);
        
        if (high < low){
            return -1;
        }
        int mid = (high + low) / 2;
        int comp = compareCard(cards[mid], card);
        
        if (comp == 0){
            return mid;
        } else if (comp > 0){
            return findBisect(cards, card, low, mid-1);
        } else {
            return findBisect(cards, card, mid+1, high);
        }
    }
    
    public static int compareCard(Card c1, Card c2){
        if (c1.suit > c2.suit) return 1;
        if (c1.suit < c2.suit) return -1;
        if (c1.rank > c2.rank) return 1;
        if (c1.rank < c2.rank) return -1;
        return 0;
    }
    
    public static int findCard(Card[] cards, Card card){
        for (int i = 0; i < cards.length; i++){
            if (cards[i].equals(card)){
                return i;
            }
        }
        return -1;
    }
}

class Deck{
    Card[] cards;
    // constructor
    public Deck(int n){
        this.cards = new Card[n];
    }
    // constructor
    public Deck(){
        this.cards = new Card[52];
        int index = 0;
        for (int suit = 0; suit <= 3; suit++){
            for (int rank = 1; rank <= 13; rank++){
                cards[index] = new Card(suit, rank);
                index++;
            }
        }
    }
    // method for printing deck
    public void printDeck(){
        for (int i = 0; i < this.cards.length; i++){
            this.cards[i].printCard();
        }
    }
    // method for swapping two cards
    public void swapCards(int i, int j){
        Card temp = this.cards[i];
        this.cards[i] = this.cards[j];
        this.cards[j] = temp;
    }
    // method for shuffling deck
    public void shuffleDeck(){
        for (int i = 0; i < this.cards.length; i++){
            int nth = (int) (Math.random() * 52);
            swapCards(i, nth);
        }
    }
    // method for sorting: selection sort
    public void sortDeck(){
        int sorted = 0;
        Card element;
        for (int i = 0; i < this.cards.length; i++){
            for (int j = i + 1; j < this.cards.length; j++){
                element = this.cards[i];
                if (this.compareCard(this.cards[i], this.cards[j]) == 1){
                    this.cards[i] = this.cards[j];
                    this.cards[j] = element;
                }
            }
        }
    }
    // method for comparing two cards
    public int compareCard(Card c1, Card c2){
        if (c1.suit > c2.suit) return 1;
        if (c1.suit < c2.suit) return -1;
        if (c1.rank > c2.rank) return 1;
        if (c1.rank < c2.rank) return -1;
        return 0;
    }
}

// hand class
class Hand extends Deck{
    Deck sub;
    public Hand(Deck deck, int low, int high){
        sub = new Deck(high-low+1);
        for (int i = 0; i < sub.cards.length; i++){
            sub.cards[i] = deck.cards[low+i];
        }
    }
    public void printHand(){
        for (int i = 0; i < sub.cards.length; i++){
            sub.cards[i].printCard();
        }
    }
}

class Card{
    int suit, rank;
    
    static String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };
    static String[] ranks = { "narf", "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };

    // two constructors
    public Card(){
        this.suit = 0;
        this.rank = 0;
    }
    public Card(int suit, int rank){
        this.suit = suit;
        this.rank = rank;
    }
    // two methods
    public void printCard(){
         System.out.println(ranks[rank] + " of " + suits[suit]);
    }
    public boolean equals(Card that){
        return(this.suit == that.suit && this.rank == that.rank);
    }
}
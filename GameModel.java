package gee_p3;

/*
 * GEE
 * This is free and unencumbered software released into the public domain.
 */

import java.util.ArrayList; // Importing ArrayList class from java.util package
import java.util.Random; // Importing Random class from java.util package

/**
 * This class models the game logic for a simple card game.
 */
public class GameModel { // Class declaration for GameModel

    // Stack representing the deck of cards.
    private ArrayList<Integer> deckStack; // Declaring an ArrayList deckStack to represent the deck of cards

    // Stack representing the discard pile.
    private ArrayList<Integer> discardStack; // Declaring an ArrayList discardStack to represent the discard pile

    // Queue representing player 1's hand.
    private Queue<Integer> player1Queue; // Declaring a Queue player1Queue to represent player 1's hand

    // Queue representing player 2's hand.
    private Queue<Integer> player2Queue; // Declaring a Queue player2Queue to represent player 2's hand

    // ArrayList representing the deck of cards.
    private ArrayList<Integer> cards; // Declaring an ArrayList cards to represent the deck of cards

    /**
     * Constructor that initializes the game state.
     */
    public GameModel() { // Constructor definition for GameModel class

        // Creating deck of cards, shuffling it, and initializing game state
        deckStack = createDeck(); // Initializing deckStack by calling createDeck method
        shuffleDeck(deckStack); // Shuffling the deckStack
        discardStack = new ArrayList<>(); // Initializing discardStack as a new empty ArrayList
        player1Queue = new Queue<>(); // Initializing player1Queue as a new empty Queue
        player2Queue = new Queue<>(); // Initializing player2Queue as a new empty Queue
        dealCards(cards); // Dealing cards to players
        discardStack.add(deckStack.remove(deckStack.size() - 1)); // Moving a card from deckStack to discardStack
    }

    /**
     * Initializes the game state.
     */
    public void initializeGame() { // Method to initialize game state
        ArrayList<Integer> cards = generateCards(); // Generating cards
        shuffleDeck(cards); // Shuffling the deck of cards
        dealCards(cards); // Dealing cards to players
    }

    /**
     * Generates a list of cards (1-10) to be used in the game.
     *
     * @return An ArrayList containing the generated cards.
     */
    private ArrayList<Integer> generateCards() { // Method to generate cards
        ArrayList<Integer> cards = new ArrayList<>(); // Initializing cards as a new empty ArrayList
        for (int i = 1; i <= 10; i++) { // Loop to generate cards from 1 to 10
            cards.add(i); // Adding each card to the cards ArrayList
        }
        return cards; // Returning the generated cards
    }

    /**
     * Creates a deck of cards with 52 cards (including shuffling).
     *
     * @return An ArrayList containing the shuffled deck of cards.
     */
    private ArrayList<Integer> createDeck() { // Method to create a deck of cards
        ArrayList<Integer> cards = new ArrayList<>(); // Initializing cards as a new empty ArrayList

        Random rand = new Random(); // Creating a new Random object

        for (int i = 1; i <= 13; i++) { // Loop to generate cards from 1 to 13
            // Add each card value (1-13) 4 times to represent suits
            for (int j = 0; j < 4; j++) { // Loop to add each card 4 times for 4 suits
                cards.add(i); // Adding each card to the cards ArrayList
            }
        }

        shuffleDeck(cards); // Shuffling the deck of cards

        return cards; // Returning the shuffled deck of cards
    }

    /**
     * Shuffles the deck of cards using the Fisher-Yates shuffle algorithm.
     *
     * @param cards The ArrayList representing the deck to shuffle.
     */
    private void shuffleDeck(ArrayList<Integer> cards) { // Method to shuffle the deck of cards
        Random rand = new Random(); // Creating a new Random object

        for (int i = cards.size() - 1; i > 0; i--) { // Loop to iterate through the cards ArrayList
            // Generate a random index between 0 and i (inclusive)
            int j = rand.nextInt(i + 1); // Generating a random index within the range

            // Swap the card at index i with the card at index j
            int temp = cards.get(i); // Storing the card at index i in a temporary variable
            cards.set(i, cards.get(j)); // Setting the card at index i to the card at index j
            cards.set(j, temp); // Setting the card at index j to the temporary variable
        }
    }

    /**
     * Deals 7 cards each to player 1 and player 2.
     *
     * @param cards The ArrayList representing the deck of cards.
     */
    private void dealCards(ArrayList<Integer> cards) { // Method to deal cards to players
        for (int i = 0; i < 7; i++) { // Loop to deal 7 cards to each player
            player1Queue.enqueue(cards.remove(0)); // Dealing a card to player 1 and removing it from the deck
            player2Queue.enqueue(cards.remove(0)); // Dealing a card to player 2 and removing it from the deck
        }

        discardStack.add(cards.remove(0)); // Moving a card from deck to discard pile
    }

    /**
     * Moves cards from discard pile to deck stack without shuffling.
     *
     * @param deckStack The ArrayList representing the deck stack.
     * @param discardStack The ArrayList representing the discard stack.
     */
    private void flipDeck(ArrayList<Integer> deckStack, ArrayList<Integer> discardStack) { // Method to move cards from discard pile to deck stack
        int topCard = discardStack.remove(discardStack.size() - 1); // Removing the top card from the discard pile

        while (!discardStack.isEmpty()) { // Loop to move cards from discard pile to deck stack
            deckStack.add(discardStack.remove(discardStack.size() - 1)); // Moving cards from discard pile to deck stack
        }

        discardStack.add(topCard); // Adding the top card back to the discard pile
    }

    /**
     * Simulates a turn for a player.
     *
     * @param player The player number (1 or 2).
     * @return True if the player's turn ends, false otherwise.
     */
    public boolean playTurn(int player) {
        // Method to simulate a turn for a player
        Queue<Integer> hand; // Declaring a Queue to represent the player's hand

        if (player == 1) { // Checking if the player is player 1
            hand = player1Queue; // Assigning player 1's hand to the Queue hand
        } else {
            hand = player2Queue; // Assigning player 2's hand to the Queue hand
        }

        int playerCard = hand.dequeue(); // Removing the top card from the
                                        // player's hand

        // Getting the top card from the discard pile
        int topDiscard = discardStack.get(discardStack.size() - 1);

        // Checking if the player's card is greater than the top discard card
        if (playerCard > topDiscard) {

            // Returning true if the player's turn ends
            return hand.isEmpty();

            // Checking if the player's card is equal to the top discard card
        } else if (playerCard == topDiscard) {
            // Adding a penalty card to the player's hand
            hand.enqueue(deckStack.remove(deckStack.size() - 1));
        } else {
            // Adding a penalty card to the player's hand
            hand.enqueue(deckStack.remove(deckStack.size() - 1));
            // Adding a penalty card to the player's hand
            hand.enqueue(deckStack.remove(deckStack.size() - 1));
        }

        // Adding the player's card to the discard pile
        discardStack.add(playerCard);
        return hand.isEmpty(); // Returning true if the player's turn ends
    }

    /**
     * Checks if the game is over (one player has no cards left).
     *
     * @return True if the game is over, false otherwise.
     */
    public boolean isGameOver() { // Method to check if the game is over

        // Returning true if either player's hand is empty
        return player1Queue.isEmpty() || player2Queue.isEmpty();
    }

    /**
     * Determines the winner of the game (the player who has no cards left).
     *
     * @return The player number (1 or 2) who won the game.
     */
    public int getWinner() { // Method to determine the winner of the game
        if (player1Queue.isEmpty()) { // Checking if player 1's hand is empty
            return 1; // Returning 1 if player 1 won
        } else {
            return 2; // Returning 2 if player 2 won
        }
    }
}

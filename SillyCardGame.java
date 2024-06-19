package gee_p3;

/*
 * GEE
 * This is free and unencumbered software released into the public domain.
 */

import java.util.Scanner;

public class SillyCardGame {
    public static void main(String[] args) {
        System.out.println("Welcome to Silly Little Games' new Card Game!");
        System.out.println("In this game, there will be two players each " +
                "dealt 7 cards games in a random order" +
                "(Round-Robin). (Player 1 gets a card, Then " +
                "Player 2 gets a card, Then Player 1 gets a card," +
                "etc.) and their cards are placed into their " +
                "queue");

        /**
         System.out.println("The next card in the deal stack is placed " +
         "into the discard stack. ");
         System.out.println("For their turn, each player plays the next card " +
         "in his/her queue.\n If the card the player " +
         "plays is HIGHER in number than the one on the " +
         "top of the discard stack, the player's turn " +
         "is over.\n If the card the player plays is " +
         "EQUAL in number to the one on the top of the " +
         "discard stack, the player must then take one " +
         "card from the deal stack and the player's turn " +
         "is over.\n If the player's card is LOWER in " +
         "number than the one on the discard stack, the " +
         "player must take two cards from the deal stack " +
         "and the player's turn is over.");
         System.out.println("Good luck!!!!");

         */
        System.out.println("\nHere are the game instructions:");

        System.out.println("\nOn your turn, you will play the top card f" +
                "rom your hand:");
        System.out.println("- If your card is **HIGHER** than the top card " +
                "in the discard pile, your turn ends.");
        System.out.println("- If your card is **EQUAL** to the top card " +
                "in the discard pile, you draw **one** card " +
                "from the deck, and your turn ends.");
        System.out.println("- If your card is **LOWER** than the top card " +
                "in the discard pile, you draw **two** cards " +
                "from the deck, and your turn ends.");

        System.out.println("\nRemember, the goal is to be the first " +
                "player to have an empty hand!");
        System.out.println("\nGood luck!");


        Scanner scanner = new Scanner(System.in);
        char playAgain;

        do {
            GameModel game = new GameModel();
            game.initializeGame();

            playGame(game, scanner);

            System.out.println("Do you want to play again? (y/n): ");
            playAgain = scanner.next().charAt(0);
        } while (playAgain == 'y' || playAgain == 'Y');

        //close the scanner object.
        scanner.close();

    }
        private static void displayWinner(Integer winner) {
            System.out.println("Congratulations, "  + winner +
                                ", you won the game!");
        }
        private static void playGame(GameModel game, Scanner scanner) {

            boolean gameOver = false;

            while (!gameOver) {
               // displayGameState(game);

                //Player 1's Turn
                System.out.println("Player 1's turn.");
                boolean playerTurnEnds  = game.playTurn(1);

                if (game.isGameOver()) {
                    displayWinner(1);
                    gameOver = true;
                } else if (!playerTurnEnds) {

                    //Player 2's turn only if player 1's turn doesn't end/win
                    System.out.println("Player 2's turn.");
                    boolean player2TurnEnds = game.playTurn(2);

                    if (game.isGameOver()) {
                        displayWinner(2);
                        gameOver = true;
                    } else if (player2TurnEnds) {
                        //if player 2's turn ends but player 1's turn doesn't,
                        //display game over
                        System.out.println("Both players' turns ended. " +
                                            "Game over.");
                        gameOver = true;

                    }

                }

            }

        }
    }



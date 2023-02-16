package cpsc2150.ExtendedTicTacToe;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * @invariant currentPlayer != null
 */
public class GameScreen {
    static char currentPlayer;

    // main function
    public static void main(String[] args) {
        ArrayList<Character> playerArray;
        boolean correctBoardSize;
        boolean contiune;
        boolean correctInput;
        boolean playAgain = true;
        int tempRow = 0;
        int tempCol = 0;
        int tempMarkerCount = 0;
        int numberOfPlayers;
        Scanner scan = new Scanner(System.in);
        String input;

        // loop for playing the game again
        while (playAgain) {
            numberOfPlayers = 0;
            // choosing the number of players
            while (2 > numberOfPlayers || numberOfPlayers > 10) {
                System.out.println("How many players are going to play? At least 2 players and at most 10 players.");
                input = scan.nextLine();
                numberOfPlayers = Integer.parseInt(input);
            }
            playerArray = new ArrayList<Character>(numberOfPlayers);
            // each player chooses their character
            for (int i = 1; i <= numberOfPlayers; i++) {
                System.out.println("Please enter player " + i + "'s character: ");
                input = scan.nextLine();
                while (playerArray.indexOf(Character.toUpperCase(input.charAt(0))) != -1 && i != 1) {
                    System.out.println("Please enter a character that has not been picked yet");
                    input = scan.nextLine();
                }
                playerArray.add(Character.toUpperCase(input.charAt(0)));
            }
            currentPlayer = playerArray.get(0);
            correctBoardSize = false;
            // choosing the number of rows
            while (!correctBoardSize) {
                System.out.println("Please enter the number of rows this game should have. It should be 1oo or smaller.");
                input = scan.nextLine();
                tempRow = Integer.parseInt(input);
                if (tempRow <= 100 && tempRow > 2) {
                    correctBoardSize = true;
                }
            }
            correctBoardSize = false;
            // choosing the number of columns
            while (!correctBoardSize) {
                System.out.println("Please enter the number of columns this game should have. It should be 100 or smaller.");
                input = scan.nextLine();
                tempCol = Integer.parseInt(input);
                if (tempCol <= 100 && tempCol > 2) {
                    correctBoardSize = true;
                }
            }
            correctBoardSize = false;
            // choosing the number of markers in a row to win
            while (!correctBoardSize) {
                System.out.println("Please enter the number of markers in a row needed this game to win. It should be between 3 and 25.");
                input = scan.nextLine();
                tempMarkerCount = Integer.parseInt(input);
                if (tempMarkerCount <= 25 && tempMarkerCount <= tempRow && tempMarkerCount <= tempCol && tempMarkerCount > 2) {
                    correctBoardSize = true;
                }
            }
            // choosing fast or memory efficient game
            System.out.println("Would you like a fast game(F/f) or a memory efficient game(M/m)");
            input = scan.nextLine();
            while (!input.equals("F") && !input.equals("f") && !input.equals("M") && !input.equals("m")) {
                System.out.println("Please enter F or f for a fast game or M or m for a  memory efficient game");
                input = scan.nextLine();
            }
            AbsGameBoard newBoard;
            if (input == "F" || input == "f") {
                newBoard = new GameBoard(tempRow, tempCol, tempMarkerCount);
            } else {
                newBoard = new GameBoardMem(tempRow, tempCol, tempMarkerCount);
            }
            contiune = true;
            correctInput = false;
            playAgain = true;

            // loop until there is a winner or tie
            while (contiune) {
                // taking in a player's row and col
                System.out.println(newBoard.toString());
                System.out.println("Player " + currentPlayer + " Please enter your Row");
                input = scan.nextLine();
                tempRow = Integer.parseInt(input);
                System.out.println("Player " + currentPlayer + " Please enter your Column");
                input = scan.nextLine();
                tempCol = Integer.parseInt(input);
                BoardPosition pos = new BoardPosition(tempRow, tempCol);

                // checking whether space is available, if not keep asking until valid space
                while (newBoard.checkSpace(pos) == false) {
                    System.out.println(newBoard.toString());
                    System.out.println("Please enter another position that is valid");
                    System.out.println("Player " + currentPlayer + " Please enter your Row");
                    input = scan.nextLine();
                    tempRow = Integer.parseInt(input);
                    System.out.println("Player " + currentPlayer + " Please enter your Column");
                    input = scan.nextLine();
                    tempCol = Integer.parseInt(input);
                    pos = new BoardPosition(tempRow, tempCol);
                }

                // place marker
                newBoard.placeMarker(pos, currentPlayer);

                // checking for winner
                // if there is a winner, ask until they say y or n to playing again
                if (newBoard.checkForWinner(pos) == true) {
                    System.out.println(newBoard.toString());
                    System.out.println("Player " + currentPlayer + " has won!");
                    System.out.println("Do you wish to play again? Please enter only y for yes or n for no");
                    input = scan.nextLine();
                    while (!correctInput) {
                        switch (input) {
                            case "y":
                                correctInput = true;
                                contiune = false;
                                break;
                            case "n":
                                correctInput = true;
                                contiune = false;
                                playAgain = false;
                                break;
                            default:
                                System.out.println("Do you wish to play again? Please enter only y for yes or n for no");
                                input = scan.nextLine();
                                break;
                        }
                    }
                }
                // checks for draw, ask for y or n to playing again if tie
                else if (newBoard.checkForDraw()) {
                    System.out.println(newBoard.toString());
                    System.out.println("Both Players have tied");
                    System.out.println("Do you wish to play again? Please enter only y for yes or n for no");
                    input = scan.nextLine();
                    while (!correctInput) {
                        switch (input) {
                            case "y":
                                correctInput = true;
                                contiune = false;
                                break;
                            case "n":
                                correctInput = true;
                                playAgain = false;
                                contiune = false;
                                break;
                            default:
                                System.out.println("Do you wish to play again? Please enter only y for yes or n for no");
                                input = scan.nextLine();
                                break;
                        }
                    }
                }
                // switch player's turn
                if (playerArray.get(playerArray.size() - 1) == currentPlayer) {
                    currentPlayer = playerArray.get(0);
                } else {
                    int index = playerArray.indexOf(currentPlayer);
                    currentPlayer = playerArray.get(index + 1);
                }
            }
        }
    }
}
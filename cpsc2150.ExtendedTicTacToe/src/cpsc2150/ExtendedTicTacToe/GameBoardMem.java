package cpsc2150.ExtendedTicTacToe;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * @invariant 3 <= row <= 100
 * @invariant 3 <= column <= 100
 * @invariant 3 <= numForWin <= 100
 * @invariant numForWin <= row
 * @invariant numForWin <= column
 * @Correspondence NumberOfRows = numberOfRows
 * @Correspondence NumberOfColumns = numberOfColumns
 * @Correspondence MarkersToWin = numForWin
 * @Correspondence TwoD_Array = gameBoard
 */
public class GameBoardMem extends AbsGameBoard {
    private int numberOfRows, numberOfColumns, numForWin;
    Map<Character, ArrayList<BoardPosition>> gameBoard = new HashMap<>();

    /**
     *
     * @param rowInput - number of rows for the game
     * @param columnInput - number of columns for the game
     * @param numForWinInput - number of markers in a row to win
     * @post - numberOfRows = rowInput
     * @post - numberOfColumns = columnInput
     * @post - numForWin = numForWinInput
     */
    public GameBoardMem(int rowInput, int columnInput, int numForWinInput)
    {
        // getting user input
        numberOfRows = rowInput;
        numberOfColumns = columnInput;
        numForWin = numForWinInput;
    }

    public void placeMarker(BoardPosition marker, char player)
    {
        ArrayList<BoardPosition> adding = new ArrayList<BoardPosition>();
        if(!gameBoard.containsKey(player))
        {
            adding.add(marker);
        }
        else {
            adding = gameBoard.get(player);
            adding.add(marker);
        }
        gameBoard.put(player, adding);
    }

    public boolean isPlayerAtPos(BoardPosition pos, char player)
    {
        boolean returnStatement = false;
        if(!gameBoard.containsKey(player))
        {
            returnStatement = false;
        }
        else {
            for (int i = 0; i < gameBoard.get(player).size(); i++) {
                if (gameBoard.get(player).get(i).getRow() == pos.getRow() && gameBoard.get(player).get(i).getColumn() == pos.getColumn()) {
                    returnStatement = true;
                }
            }
        }
        return returnStatement;
    }

    public char whatsAtPos(BoardPosition pos)
    {
        for(Map.Entry<Character, ArrayList<BoardPosition>> entry : gameBoard.entrySet())
        {
           for(int i = 0; i< gameBoard.get(entry.getKey()).size(); i++)
           {
               if(pos.getRow() == gameBoard.get(entry.getKey()).get(i).getRow() && pos.getColumn() == gameBoard.get(entry.getKey()).get(i).getColumn())
               {
                   return entry.getKey();
               }
           }
        }
        return ' ';
    }

    public int getNumRows()
    {
        return numberOfRows;
    }

    public int getNumColumns()
    {
        return numberOfColumns;
    }

    public int getNumToWin()
    {
        return numForWin;
    }
}
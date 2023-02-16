package cpsc2150.ExtendedTicTacToe;

/**
 * @invariant 3 <= row <= 100
 * @invariant 3 <= column <= 100
 * @invariant 3 <= numForWin <= 100
 * @invariant numForWin <= row
 * @invariant numForWin <= column
 * @invariant [gameBoard length will not be changed]
 * @Correspondence NumberOfRows = numberOfRows
 * @Correspondence NumberOfColumns = numberOfColumns
 * @Correspondence MarkersToWin = numForWin
 * @Correspondence TwoD_Array = gameBoard
 */
public class GameBoard extends AbsGameBoard{
    private int numberOfRows, numberOfColumns, numForWin;
    private char[][] gameBoard;

    /**
     *
     * @param rowInput - number of rows for the game
     * @param columnInput - number of columns for the game
     * @param numForWinInput - number of markers in a row to win
     * @post - [gameBoard will be initialized with rowInput number of rows and columnInput number of columns]
     * @post - [gameBoard will be set as a blank board]
     * @post - numberOfRows = rowInput
     * @post - numberOfColumns = columnInput
     * @post - numForWin = numForWinInput
     */
    public GameBoard(int rowInput, int columnInput, int numForWinInput)
    {
        // getting user input
        numberOfRows = rowInput;
        numberOfColumns = columnInput;
        numForWin = numForWinInput;
        // setting game board to empty space (char)
        gameBoard= new char[numberOfRows][numberOfColumns];
        for (int i = 0; i < rowInput; i++)
        {
            for (int j = 0; j < columnInput; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
    }


    public void placeMarker(BoardPosition marker, char player)
    {
        gameBoard[marker.getRow()][marker.getColumn()] = player;
    }

    public char whatsAtPos(BoardPosition pos)
    {
        return gameBoard[pos.getRow()][pos.getColumn()];
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


package cpsc2150.ExtendedTicTacToe;

/**
 * IGameBoard represents the board of a tic tac toe game.
 * Initialization ensures: [a board with blank spaces will be created with row and column length according to user input]
 * Defines: NumberOfRows: Z-+ - number of rows the board will have
 *          NumberOfColumns: Z-+ - number of columns the board will have
 *          MarkersToWin: Z-+ - number of markers in a row to win
 *          TwoD_Array: chars - the game board
 * Constraints: 3 < NumberOfRows <= 100
 *              3 < NumberOfColumns <= 100
 *              3 < MarkersToWin <= NumberOfRows
 *              3 < MarkersToWin <= NumberOfColumns
 *              3 < MarkersToWin <= 100
 */
public interface IGameBoard {
    public static final int max_rows = 100;
    public static final int max_cols = 100;
    public static final int max_numToWin = 25;
    public static final int min_rows = 3;
    public static final int min_cols = 3;
    public static final int min_numToWin = 3;
    public static final int min_rows_cols = 0;
    /**
     *
     * @param pos - BoardPosition object that contains a rowCoordinate and columnCoordinate
     * @return whether there are spaces available in bounds
     * @pre pos.getRow() != NULL
     * @pre pos.getColumn() != NULL
     * @post [no changes made to TwoD_Array]
     * @post checkSpace = true || checkSpace = false
     * @post [checks whether pos is in bounds and contains a blank space]
     */
    default boolean checkSpace(BoardPosition pos)
    {
        if(pos.getRow() <getNumRows() && pos.getRow() >= min_rows_cols && pos.getColumn() >= min_rows_cols && pos.getColumn() < getNumColumns())
            if(whatsAtPos(pos) == ' ')
                return true;
        return false;
    }

    /**
     *
     * @param marker - BoardPosition object that contains a rowCoordinate and columnCoordinate
     * @param player - player's marker
     * @pre marker.getRow() != NULL
     * @pre marker.getColumn() != NULL
     * @pre checkSpace(marker) = true
     * @pre player != NULL
     * @post [player's marker will be placed at position specified in twoD_Array]
     */
    void placeMarker(BoardPosition marker, char player);

    /**
     *
     * @param lastPos - BoardPosition object that contains a rowCoordinate and columnCoordinate
     * @return whether a player won or not horizontally, vertically, or diagonally
     * @pre lastPos.getRow() != null
     * @pre lastPos.getColumn() != null
     * @post [no changes made to TwoD_Array]
     * @post checkForWinner = true || checkForWinner = false
     */
    default boolean checkForWinner(BoardPosition lastPos)
    {
        if(checkDiagonalWin(lastPos, whatsAtPos(lastPos)) || checkHorizontalWin(lastPos, whatsAtPos(lastPos)) || checkVerticalWin(lastPos, whatsAtPos(lastPos)))
            return true;
        return false;
    }

    /**
     *
     * @return whether there is a draw
     * @post [no changes made to TwoD_Array]
     * @post checkForDraw = true || checkForDraw = false
     */
    default boolean checkForDraw()
    {
        for(int r = min_rows_cols; r < getNumRows(); r++)
        {
            for(int c = min_rows_cols; c < getNumColumns(); c++)
            {
                BoardPosition pos = new BoardPosition(r, c);
                if(whatsAtPos(pos) == ' ')
                    return false;
            }
        }
        return true;
    }

    /**
     *
     * @param lastPos - BoardPosition object that contains a rowCoordinate and columnCoordinate
     * @param player - player marker
     * @return whether player has a horizontal win of specified amount of markers in a horizontal row
     * @pre lastPos.getRow() != null
     * @pre lastPos.getColumn() != null
     * @pre player != NULL
     * @post [no changes made to TwoD_Array]
     * @post checkHorizontalWin = true || checkHorizontalWin = false
     */
    default boolean checkHorizontalWin(BoardPosition lastPos, char player)
    {
        // the coordinates we are checking
        int tempRow = lastPos.getRow();
        int tempCol = lastPos.getColumn() + 1;
        // the number of player's marker in a row
        int count = 1;
        // whether the space we are checking has same marker as player
        boolean sameChar = true;

        // checks bounds and whether sameChar is true
        while(sameChar && tempRow >= min_rows_cols && tempRow < getNumRows() && tempCol >= min_rows_cols && tempCol < getNumColumns())
        {
            BoardPosition pos = new BoardPosition(tempRow, tempCol);
            // checks if the space to the right has same marker
            if(isPlayerAtPos(pos, player)) {
                count++;
                tempCol++;
            }
            else
                // if space to the right has different marker
                sameChar = false;
        }

        // reset variables for checking to the left of current position
        sameChar = true;
        tempRow = lastPos.getRow();
        tempCol = lastPos.getColumn() - 1;

        // checks bounds and whether sameChar is true
        while(sameChar && tempRow >= min_rows_cols && tempRow < getNumRows() && tempCol >= min_rows_cols && tempCol < getNumColumns())
        {
            BoardPosition pos = new BoardPosition(tempRow, tempCol);
            // checks if the space to the left has same marker
            if(isPlayerAtPos(pos, player)) {
                count++;
                tempCol--;
            }
            else
                // if space to the left has different marker
                sameChar = false;
        }

        // if there is the specified amount of player's marker in a row, then they won
        return count >= getNumToWin();
    }

    /**
     *
     * @param lastPos - BoardPosition object that contains a rowCoordinate and columnCoordinate
     * @param player - player marker
     * @return whether player has a vertical win of a specified amount of markers in a vertical column
     * @pre lastPos.getRow() != null
     * @pre lastPos.getColumn() != null
     * @pre player != NULL
     * @post [no changes made to TwoD_Array]
     * @post checkVerticalWin = true || checkVerticalWin = false
     */
    default boolean checkVerticalWin(BoardPosition lastPos, char player)
    {

        // the coordinates we are checking
        int tempRow = lastPos.getRow() + 1;
        int tempCol = lastPos.getColumn();
        // the number of player's marker in a row
        int count = 1;
        // whether the space we are checking has same marker as player
        boolean sameChar = true;

        // checks bounds and whether sameChar is true
        while(sameChar && tempRow >= min_rows_cols && tempRow < getNumRows() && tempCol >= min_rows_cols && tempCol < getNumColumns())
        {
            BoardPosition pos = new BoardPosition(tempRow, tempCol);
            // checks if the space below has same marker
            if(isPlayerAtPos(pos, player)) {
                count++;
                tempRow++;
            }
            else
                // if space below has different marker
                sameChar = false;
        }

        // reset variables for checking above of current position
        tempRow = lastPos.getRow() - 1;
        tempCol = lastPos.getColumn();
        sameChar = true;

        // checks bounds and whether sameChar is true
        while(sameChar && tempRow >= min_rows_cols && tempRow < getNumRows() && tempCol >= min_rows_cols && tempCol < getNumColumns())
        {
            BoardPosition pos = new BoardPosition(tempRow, tempCol);
            // checks if the space above has same marker
            if(isPlayerAtPos(pos, player)) {
                count++;
                tempRow--;
            }
            else
                // if space above has different marker
                sameChar = false;
        }

        // if there is the specified amount of player's marker in a column, then they won
        return count >= getNumToWin();
    }

    /**
     *
     * @param lastPos - BoardPosition object that contains a rowCoordinate and columnCoordinate
     * @param player - player marker
     * @return whether player has a vertical win of the specified amount of markers vertically
     * @pre lastPos.getRow() != null
     * @pre lastPos.getColumn() != null
     * @pre player != NULL
     * @post [no changes made to TwoD_Array]
     * @post checkDiagonalWin = true || checkDiagonalWin = false
     */
    default boolean checkDiagonalWin(BoardPosition lastPos, char player)
    {

        // the coordinates we are checking
        int tempRow = lastPos.getRow() + 1;
        int tempCol = lastPos.getColumn() + 1;
        // the number of player's marker in a diagonal from top right to bottom left
        int count = 1;
        // the number of player's marker in a diagonal from top left to bottom right
        int count2 = 1;
        // whether the space we are checking has same marker as player
        boolean sameChar = true;

        // checks bounds and whether sameChar is true
        while(sameChar && tempRow >= min_rows_cols && tempRow < getNumRows() && tempCol >= min_rows_cols && tempCol < getNumColumns())
        {
            BoardPosition pos = new BoardPosition(tempRow, tempCol);
            // checks if the space above and to the right has same marker
            if(isPlayerAtPos(pos, player)) {
                count++;
                tempCol++;
                tempRow++;
            }
            else
                // if space above and to the right has different marker
                sameChar = false;
        }

        // reset variables for checking below and to the left of current position
        sameChar = true;
        tempRow = lastPos.getRow() - 1;
        tempCol = lastPos.getColumn() - 1;

        // checks bounds and whether sameChar is true
        while(sameChar && tempRow >= min_rows_cols && tempRow < getNumRows() && tempCol >= min_rows_cols && tempCol < getNumColumns())
        {
            BoardPosition pos = new BoardPosition(tempRow, tempCol);
            // checks if the space below and to the left has same marker
            if(isPlayerAtPos(pos, player)) {
                count++;
                tempCol--;
                tempRow--;
            }
            else
                // if space below and to the left has different marker
                sameChar = false;
        }

        // reset variables for checking below and to the right of current position
        sameChar = true;
        tempRow = lastPos.getRow() + 1;
        tempCol = lastPos.getColumn() - 1;

        // checks bounds and whether sameChar is true
        while(sameChar && tempRow >= min_rows_cols && tempRow < getNumRows() && tempCol >= min_rows_cols && tempCol < getNumColumns())
        {
            BoardPosition pos = new BoardPosition(tempRow, tempCol);
            // checks if the space below and to the right has same marker
            if(isPlayerAtPos(pos, player)) {
                count2++;
                tempCol--;
                tempRow++;
            }
            else
                // if space below and to the right has different marker
                sameChar = false;
        }

        // reset variables for checking above and to the left of current position
        sameChar = true;
        tempRow = lastPos.getRow() - 1;
        tempCol = lastPos.getColumn() + 1;

        // checks bounds and whether sameChar is true
        while(sameChar && tempRow >= min_rows_cols && tempRow < getNumRows() && tempCol >= min_rows_cols && tempCol < getNumColumns())
        {
            BoardPosition pos = new BoardPosition(tempRow, tempCol);
            // checks if the space above and to the left has same marker
            if(isPlayerAtPos(pos, player)) {
                count2++;
                tempCol++;
                tempRow--;
            }
            else
                // if space  above and to the left has different marker
                sameChar = false;
        }

        // if there is 5 player's marker in a diagonal either from top left to bottom right or top right to bottom left, then they won
        return count >= getNumToWin() || count2 >= getNumToWin();
    }

    /**
     *
     * @param pos - BoardPosition object that contains a rowCoordinate and columnCoordinate
     * @return the element at pos for TwoD_Array
     * @pre pos.getRow() > NumberOfRows
     * @pre pos.getColumn() > NumberOfColumns
     * @pre pos.getRow() <= 0
     * @pre pos.getColumn() <= 0
     * @pre pos.getRow() != NULL
     * @pre pos.getColumn() != NULL
     * @post [the char at pos, if nothing then blank space.]
     */
    char whatsAtPos(BoardPosition pos);

    /**
     *
     * @param pos - BoardPosition object that contains a rowCoordinate and columnCoordinate
     * @param player - player marker
     * @return whether player is at pos in TwoD_Array
     * @pre pos.getRow() > NumberOfRows
     * @pre pos.getColumn() > NumberOfColumns
     * @pre pos.getRow() != NULL
     * @pre pos.getColumn() != NULL
     * @pre player != NULL
     * @post [no changes made to TwoD_Array]
     * @post isPlayerAtPos = true || isPlayerAtPos = false
     */
    default boolean isPlayerAtPos(BoardPosition pos, char player)
    {
        if(whatsAtPos(pos) == player)
            return true;
        return false;
    }

    /**
     *
     * @return - the number of rows the board will have
     * @post [no changes made to TwoD_Array]
     * @post getNumRows = NumberOfRows
     */
    int getNumRows();

    /**
     *
     * @return - the number of columns the board will have
     * @post [no changes made to TwoD_Array]
     * @post getNumColumns = NumberOfColumns
     */
    int getNumColumns();

    /**
     *
     * @return - the number of markers in a row to win
     * @post [no changes made to TwoD_Array]
     * @post getNumToWin = MarkersToWin
     */
    int getNumToWin();
}

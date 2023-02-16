package cpsc2150.ExtendedTicTacToe;

//import cpsc2150.ExtendedTicTacToe.src.IGameBoard;

/**
 * MaxRow = GameBoard.width()
 * MaxCol = GameBoard.length()
 * @invariant 0 <= rowCoordinate < MaxRow
 * @invariant 0 <= columnCoordinate < MaxCol
 * @invariant rowCoordinates != null
 * @invariant columnCoordinates != null
 *
 */
public class BoardPosition {
    private int rowCoordinate, columnCoordinate;

    /**
     *
     * @param rowPosition - the row number a coordinate is at
     * @param colPosition - the column number a coordinate is at
     * @post rowCoordinate = rowPosition
     * @post columnCoordinate = colPosition
     */
    public BoardPosition(int rowPosition, int colPosition)
    {
        rowCoordinate = rowPosition;
        columnCoordinate = colPosition;
    }

    /**
     *
     * @return which Row an object is on
     * @post [no changes made to rowCoordinate]
     * @post getRow = rowCoordinate
     */
    public int getRow()
    {
        return rowCoordinate;
    }

    /**
     *
     * @return which Column an object is on
     * @post [no changes made to columnCoordinate]
     * @post getColumn = columnCoordinate
     */
    public int getColumn()
    {
        return columnCoordinate;
    }

    /**
     *
     * @param obj is a "Row, Column" coordinate on the board
     * @return comparison of the coordinates of obj and this.BoardPosition object
     * @pre position.getRow() != NULL
     * @pre position.getColumn() != NULL
     * @pre 0 <= position.getRow() <= max_Size
     * @pre 0 <= position.getColumn() <= max_SizeL
     * @post [no changes made to any objects]
     * @post equals = true || equals = false
     */
    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof BoardPosition) {
            if (((BoardPosition) obj).getRow() == getRow() && ((BoardPosition) obj).getColumn() == getColumn()) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return rowCoordinate and columnCoordinate in a String in the format of "<row, column>"
     * @post toString = "<rowCoordinate>,<columnCoordinate>"
     */
    @Override
    public String toString()
    {
        String coordinates = "";
        coordinates += Integer.toString(rowCoordinate);
        coordinates += ",";
        coordinates += Integer.toString(columnCoordinate);
        return coordinates;
    }
}
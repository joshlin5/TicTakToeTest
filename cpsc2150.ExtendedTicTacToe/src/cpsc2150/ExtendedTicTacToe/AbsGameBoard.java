package cpsc2150.ExtendedTicTacToe;

abstract class AbsGameBoard implements IGameBoard{
    /**
     * @return [a visual representation of the game board]
     * @post [no changes will be made to twoD_Array]
     * @post [all elements will be returned in a String along with the row and coordinates on the side and bars to separate each tile.]
     */
    @ Override
    public String toString()
    {
        // actual coordinates in the GameBoard
        String board = "";
        int tempCol = 0;
        int tempRow = 0;

        // loop for the row for the board being printed
        for (int i = 0; i < getNumRows() + 1; i++) {
            // loop for the column for the board being printed
            for (int j = 0; j < getNumColumns() + 1; j++) {
                // prints the column number for the board
                if(i == 0) {
                    if(tempCol == 0)
                    {
                        board += "   ";
                        tempCol++;
                    }
                    else if(tempCol <= 10){
                        board += " ";
                        board += tempCol - 1;
                        board += "|";
                        tempCol++;
                    }
                    else if(tempCol == getNumColumns())
                    {
                        board += tempCol - 1;
                        board += "|";
                    }
                    else
                    {
                        board += tempCol - 1;
                        board +="|";
                        tempCol++;
                    }
                }
                // prints the row number for the board
                else if(j == 0)
                {
                    if(tempRow < 10)
                    {
                        board += " ";
                    }
                    board += tempRow;
                    board += "|";
                    tempRow++;
                }
                // prints the coordinates at their respective position on the board
                else
                {
                    board += " ";
                    BoardPosition pos = new BoardPosition(i-1,j-1);
                    System.out.println(whatsAtPos(pos));
                    board += whatsAtPos(pos);
                    board += "|";
                }
            }
            // newline for each row
            board += "\n";
        }
        return board;
    }
}

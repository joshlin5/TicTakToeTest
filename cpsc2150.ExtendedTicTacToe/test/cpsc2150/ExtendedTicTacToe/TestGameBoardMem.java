package cpsc2150.ExtendedTicTacToe;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestGameBoardMem {
    private IGameBoard makeGameBoard(int rowInput, int columnInput, int numForWinInput)
    {
        IGameBoard gb = new GameBoardMem(rowInput, columnInput, numForWinInput);
        return gb;
    }

    private String toString(char[][] gameBoard)
    {
        // actual coordinates in the GameBoard
        String board = "";
        int tempCol = 0;
        int tempRow = 0;

        // loop for the row for the board being printed
        for (int i = 0; i < gameBoard.length + 1; i++) {
            // loop for the column for the board being printed
            for (int j = 0; j < gameBoard[0].length + 1; j++) {
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
                    else if(tempCol == gameBoard[0].length)
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
                    System.out.println(gameBoard[i-1][j-1]);
                    board += gameBoard[i-1][j-1];
                    board += "|";
                }
            }
            // newline for each row
            board += "\n";
        }
        return board;
    }

    @Test
    public void testConstructor_row_3_col_3_numToWin_3()
    {
        IGameBoard gb = makeGameBoard(3,3,3);
        char[][] gameBoard= new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        assertTrue(gb.getNumRows() == 3 && gb.getNumColumns() == 3 && gb.getNumToWin() == 3);
        assertEquals(gb.toString(), toString(gameBoard));
    }

    @Test
    public void testConstructor_row_100_col_100_numToWin_25()
    {
        IGameBoard gb = makeGameBoard(100,100,25);
        char[][] gameBoard= new char[100][100];
        for (int i = 0; i < 100; i++)
        {
            for (int j = 0; j < 100; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        assertTrue(gb.getNumRows() == 100 && gb.getNumColumns() == 100 && gb.getNumToWin() == 25);
        assertEquals(toString(gameBoard), gb.toString());
    }

    @Test
    public void testConstructor_row_25_col_10_numToWin_10_routine()
    {
        IGameBoard gb = makeGameBoard(25,10,10);
        char[][] gameBoard= new char[25][10];
        for (int i = 0; i < 25; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        assertTrue(gb.getNumRows() == 25 && gb.getNumColumns() == 10 && gb.getNumToWin() == 10);
        assertEquals(toString(gameBoard), gb.toString());
    }

    @Test
    public void testCheckSpace_out_of_bounds()
    {
        IGameBoard gb = makeGameBoard(3,3,3);
        BoardPosition pos = new BoardPosition(5,5);
        char[][] gameBoard= new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        assertTrue(gb.checkSpace(pos) == false);
        assertEquals(toString(gameBoard), gb.toString());
    }

    @Test
    public void testCheckSpace_in_bounds_not_blank_space()
    {
        IGameBoard gb = makeGameBoard(3,3,3);
        BoardPosition pos = new BoardPosition(0,0);
        gb.placeMarker(pos, 'X');
        char[][] gameBoard= new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        gameBoard[0][0] = 'X';
        assertTrue(gb.checkSpace(pos) == false);
        assertEquals(toString(gameBoard), gb.toString());
    }

    @Test
    public void testCheckSpace_in_bounds_blank_space_routine()
    {
        IGameBoard gb = makeGameBoard(3,3,3);
        BoardPosition pos = new BoardPosition(0,0);
        char[][] gameBoard= new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        assertTrue(gb.checkSpace(pos) == true);
        assertEquals(toString(gameBoard), gb.toString());
    }

    @Test
    public void testHorizontalWin_check_left_of_last_position_win()
    {
        IGameBoard gb = makeGameBoard(3,3,3);
        BoardPosition pos = new BoardPosition(0,0);
        gb.placeMarker(pos, 'X');
        BoardPosition pos2 = new BoardPosition(0,1);
        gb.placeMarker(pos2, 'X');
        BoardPosition pos3 = new BoardPosition(0,2);
        gb.placeMarker(pos3, 'X');
        char[][] gameBoard= new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        gameBoard[0][0] = 'X';
        gameBoard[0][1] = 'X';
        gameBoard[0][2] = 'X';
        assertTrue(gb.checkHorizontalWin(pos3, 'X') == true);
        assertEquals(toString(gameBoard), gb.toString());
    }

    @Test
    public void testHorizontalWin_check_right_of_last_position_win()
    {
        IGameBoard gb = makeGameBoard(3,3,3);
        BoardPosition pos = new BoardPosition(0,2);
        gb.placeMarker(pos, 'X');
        BoardPosition pos2 = new BoardPosition(0,1);
        gb.placeMarker(pos2, 'X');
        BoardPosition pos3 = new BoardPosition(0,0);
        gb.placeMarker(pos3, 'X');
        char[][] gameBoard= new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        gameBoard[0][0] = 'X';
        gameBoard[0][1] = 'X';
        gameBoard[0][2] = 'X';
        assertTrue(gb.checkHorizontalWin(pos3, 'X') == true);
        assertEquals(toString(gameBoard), gb.toString());
    }

    @Test
    public void testHorizontalWin_last_pos_in_middle_win()
    {
        IGameBoard gb = makeGameBoard(3,3,3);
        BoardPosition pos = new BoardPosition(0,0);
        gb.placeMarker(pos, 'X');
        BoardPosition pos2 = new BoardPosition(0,2);
        gb.placeMarker(pos2, 'X');
        BoardPosition pos3 = new BoardPosition(0,1);
        gb.placeMarker(pos3, 'X');
        char[][] gameBoard= new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        gameBoard[0][0] = 'X';
        gameBoard[0][1] = 'X';
        gameBoard[0][2] = 'X';
        assertTrue(gb.checkHorizontalWin(pos3, 'X') == true);
        assertEquals(toString(gameBoard), gb.toString());
    }

    @Test
    public void testHorizontalWin_no_win_routine()
    {
        IGameBoard gb = makeGameBoard(3,3,3);
        BoardPosition pos = new BoardPosition(0,0);
        gb.placeMarker(pos, 'X');
        BoardPosition pos2 = new BoardPosition(1,1);
        gb.placeMarker(pos2, 'X');
        BoardPosition pos3 = new BoardPosition(2,2);
        gb.placeMarker(pos3, 'X');
        char[][] gameBoard= new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        gameBoard[0][0] = 'X';
        gameBoard[1][1] = 'X';
        gameBoard[2][2] = 'X';
        assertEquals(toString(gameBoard), gb.toString());
        assertTrue(gb.checkHorizontalWin(pos3, 'X') == false);
    }

    @Test
    public void testVerticalWin_check_upward_of_last_position_win()
    {
        IGameBoard gb = makeGameBoard(3,3,3);
        BoardPosition pos = new BoardPosition(0,0);
        gb.placeMarker(pos, 'X');
        BoardPosition pos2 = new BoardPosition(1,0);
        gb.placeMarker(pos2, 'X');
        BoardPosition pos3 = new BoardPosition(2,0);
        gb.placeMarker(pos3, 'X');
        char[][] gameBoard= new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        gameBoard[0][0] = 'X';
        gameBoard[1][0] = 'X';
        gameBoard[2][0] = 'X';
        assertEquals(toString(gameBoard), gb.toString());
        assertTrue(gb.checkVerticalWin(pos3, 'X') == true);
    }

    @Test
    public void testVerticalWin_check_downward_of_last_position_win()
    {
        IGameBoard gb = makeGameBoard(3,3,3);
        BoardPosition pos = new BoardPosition(2,0);
        gb.placeMarker(pos, 'X');
        BoardPosition pos2 = new BoardPosition(1,0);
        gb.placeMarker(pos2, 'X');
        BoardPosition pos3 = new BoardPosition(0,0);
        gb.placeMarker(pos3, 'X');
        char[][] gameBoard= new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        gameBoard[0][0] = 'X';
        gameBoard[1][0] = 'X';
        gameBoard[2][0] = 'X';
        assertEquals(toString(gameBoard), gb.toString());
        assertTrue(gb.checkVerticalWin(pos3, 'X') == true);
    }

    @Test
    public void testVerticalWin_last_pos_in_middle_win()
    {
        IGameBoard gb = makeGameBoard(3,3,3);
        BoardPosition pos = new BoardPosition(0,0);
        gb.placeMarker(pos, 'X');
        BoardPosition pos2 = new BoardPosition(1,0);
        gb.placeMarker(pos2, 'X');
        BoardPosition pos3 = new BoardPosition(2,0);
        gb.placeMarker(pos3, 'X');
        char[][] gameBoard= new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        gameBoard[0][0] = 'X';
        gameBoard[1][0] = 'X';
        gameBoard[2][0] = 'X';
        assertEquals(toString(gameBoard), gb.toString());
        assertTrue(gb.checkVerticalWin(pos3, 'X') == true);
    }

    @Test
    public void testVerticalWin_no_win_routine()
    {
        IGameBoard gb = makeGameBoard(3,3,3);
        BoardPosition pos = new BoardPosition(0,0);
        gb.placeMarker(pos, 'X');
        BoardPosition pos2 = new BoardPosition(1,1);
        gb.placeMarker(pos2, 'X');
        BoardPosition pos3 = new BoardPosition(2,2);
        gb.placeMarker(pos3, 'X');
        char[][] gameBoard= new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        gameBoard[0][0] = 'X';
        gameBoard[1][1] = 'X';
        gameBoard[2][2] = 'X';
        assertTrue(gb.checkVerticalWin(pos3, 'X') == false);
        assertEquals(toString(gameBoard), gb.toString());
    }

    @Test
    public void testDiagonalWin_last_pos_bottom_right_win()
    {
        IGameBoard gb = makeGameBoard(3,3,3);
        BoardPosition pos = new BoardPosition(0,0);
        gb.placeMarker(pos, 'X');
        BoardPosition pos2 = new BoardPosition(1,1);
        gb.placeMarker(pos2, 'X');
        BoardPosition pos3 = new BoardPosition(2,2);
        gb.placeMarker(pos3, 'X');
        char[][] gameBoard= new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        gameBoard[0][0] = 'X';
        gameBoard[1][1] = 'X';
        gameBoard[2][2] = 'X';
        assertEquals(toString(gameBoard), gb.toString());
        assertTrue(gb.checkDiagonalWin(pos3, 'X') == true);
    }

    @Test
    public void testDiagonalWin_last_pos_top_left_win()
    {
        IGameBoard gb = makeGameBoard(3,3,3);
        BoardPosition pos = new BoardPosition(2,2);
        gb.placeMarker(pos, 'X');
        BoardPosition pos2 = new BoardPosition(1,1);
        gb.placeMarker(pos2, 'X');
        BoardPosition pos3 = new BoardPosition(0,0);
        gb.placeMarker(pos3, 'X');
        char[][] gameBoard= new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        gameBoard[0][0] = 'X';
        gameBoard[1][1] = 'X';
        gameBoard[2][2] = 'X';
        assertEquals(toString(gameBoard), gb.toString());
        assertTrue(gb.checkDiagonalWin(pos3, 'X') == true);
    }

    @Test
    public void testDiagonalWin_last_pos_bottom_left_win()
    {
        IGameBoard gb = makeGameBoard(3,3,3);
        BoardPosition pos = new BoardPosition(0,2);
        gb.placeMarker(pos, 'X');
        BoardPosition pos2 = new BoardPosition(1,1);
        gb.placeMarker(pos2, 'X');
        BoardPosition pos3 = new BoardPosition(2,0);
        gb.placeMarker(pos3, 'X');
        char[][] gameBoard= new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        gameBoard[0][2] = 'X';
        gameBoard[1][1] = 'X';
        gameBoard[2][0] = 'X';
        assertEquals(toString(gameBoard), gb.toString());
        assertTrue(gb.checkDiagonalWin(pos3, 'X') == true);
    }

    @Test
    public void testDiagonalWin_last_pos_top_right_win()
    {
        IGameBoard gb = makeGameBoard(3,3,3);
        BoardPosition pos = new BoardPosition(2,0);
        gb.placeMarker(pos, 'X');
        BoardPosition pos2 = new BoardPosition(1,1);
        gb.placeMarker(pos2, 'X');
        BoardPosition pos3 = new BoardPosition(0,2);
        gb.placeMarker(pos3, 'X');
        char[][] gameBoard= new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        gameBoard[0][2] = 'X';
        gameBoard[1][1] = 'X';
        gameBoard[2][0] = 'X';
        assertEquals(toString(gameBoard), gb.toString());
        assertTrue(gb.checkDiagonalWin(pos3, 'X') == true);
    }

    @Test
    public void testDiagonalWin_top_left_to_bottom_right_last_pos_in_middle_win()
    {
        IGameBoard gb = makeGameBoard(3,3,3);
        BoardPosition pos = new BoardPosition(0,0);
        gb.placeMarker(pos, 'X');
        BoardPosition pos2 = new BoardPosition(2,2);
        gb.placeMarker(pos2, 'X');
        BoardPosition pos3 = new BoardPosition(1,1);
        gb.placeMarker(pos3, 'X');
        char[][] gameBoard= new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        gameBoard[0][0] = 'X';
        gameBoard[1][1] = 'X';
        gameBoard[2][2] = 'X';
        assertEquals(toString(gameBoard), gb.toString());
        assertTrue(gb.checkDiagonalWin(pos3, 'X') == true);
    }

    @Test
    public void testDiagonalWin_top_right_to_bottom_left_last_pos_in_middle_win()
    {
        IGameBoard gb = makeGameBoard(3,3,3);
        BoardPosition pos = new BoardPosition(2,0);
        gb.placeMarker(pos, 'X');
        BoardPosition pos2 = new BoardPosition(0,2);
        gb.placeMarker(pos2, 'X');
        BoardPosition pos3 = new BoardPosition(1,1);
        gb.placeMarker(pos3, 'X');
        char[][] gameBoard= new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        gameBoard[0][2] = 'X';
        gameBoard[1][1] = 'X';
        gameBoard[2][0] = 'X';
        assertEquals(toString(gameBoard), gb.toString());
        assertTrue(gb.checkDiagonalWin(pos3, 'X') == true);
    }

    @Test
    public void testDiagonalWin_no_win_routine()
    {
        IGameBoard gb = makeGameBoard(3,3,3);
        BoardPosition pos = new BoardPosition(0,0);
        gb.placeMarker(pos, 'X');
        BoardPosition pos2 = new BoardPosition(1,1);
        gb.placeMarker(pos2, 'X');
        BoardPosition pos3 = new BoardPosition(0,2);
        gb.placeMarker(pos3, 'X');
        char[][] gameBoard= new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        gameBoard[0][2] = 'X';
        gameBoard[1][1] = 'X';
        gameBoard[0][0] = 'X';
        assertEquals(toString(gameBoard), gb.toString());
        assertTrue(gb.checkDiagonalWin(pos3, 'X') == false);
    }

    @Test
    public void testCheckForDraw_no_draw_square_board()
    {
        IGameBoard gb = makeGameBoard(3,3,3);
        char[][] gameBoard= new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        assertEquals(toString(gameBoard), gb.toString());
        assertTrue(gb.checkForDraw() == false);
    }

    @Test
    public void testCheckForDraw_rectangle_board_no_draw()
    {
        IGameBoard gb = makeGameBoard(10,5,3);
        char[][] gameBoard= new char[10][5];
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        assertEquals(toString(gameBoard), gb.toString());
        assertTrue(gb.checkForDraw() == false);
    }
    @Test
    public void testCheckForDraw_square_board_draw()
    {
        IGameBoard gb = makeGameBoard(3,3,3);
        BoardPosition pos = new BoardPosition(0,0);
        gb.placeMarker(pos, 'X');
        BoardPosition pos1 = new BoardPosition(1,0);
        gb.placeMarker(pos1, 'X');
        BoardPosition pos2 = new BoardPosition(2,0);
        gb.placeMarker(pos2, 'X');
        BoardPosition pos3 = new BoardPosition(0,1);
        gb.placeMarker(pos3, 'X');
        BoardPosition pos4 = new BoardPosition(1,1);
        gb.placeMarker(pos4, 'X');
        BoardPosition pos5 = new BoardPosition(2,1);
        gb.placeMarker(pos5, 'X');
        BoardPosition pos6 = new BoardPosition(0,2);
        gb.placeMarker(pos6, 'X');
        BoardPosition pos7 = new BoardPosition(1,2);
        gb.placeMarker(pos7, 'X');
        BoardPosition pos8 = new BoardPosition(2,2);
        gb.placeMarker(pos8, 'X');
        char[][] gameBoard= new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        gameBoard[0][0] = 'X';
        gameBoard[1][0] = 'X';
        gameBoard[2][0] = 'X';
        gameBoard[0][1] = 'X';
        gameBoard[1][1] = 'X';
        gameBoard[2][1] = 'X';
        gameBoard[0][2] = 'X';
        gameBoard[1][2] = 'X';
        gameBoard[2][2] = 'X';
        assertEquals(toString(gameBoard), gb.toString());
        assertTrue(gb.checkForDraw() == true);
    }

    @Test
    public void testCheckForDraw_rectangular_board_draw()
    {
        IGameBoard gb = makeGameBoard(4,3,3);
        BoardPosition pos = new BoardPosition(0,0);
        gb.placeMarker(pos, 'X');
        BoardPosition pos1 = new BoardPosition(1,0);
        gb.placeMarker(pos1, 'X');
        BoardPosition pos2 = new BoardPosition(2,0);
        gb.placeMarker(pos2, 'X');
        BoardPosition pos3 = new BoardPosition(0,1);
        gb.placeMarker(pos3, 'X');
        BoardPosition pos4 = new BoardPosition(1,1);
        gb.placeMarker(pos4, 'X');
        BoardPosition pos5 = new BoardPosition(2,1);
        gb.placeMarker(pos5, 'X');
        BoardPosition pos6 = new BoardPosition(0,2);
        gb.placeMarker(pos6, 'X');
        BoardPosition pos7 = new BoardPosition(1,2);
        gb.placeMarker(pos7, 'X');
        BoardPosition pos8 = new BoardPosition(2,2);
        gb.placeMarker(pos8, 'X');
        BoardPosition pos9 = new BoardPosition(3,0);
        gb.placeMarker(pos9, 'X');
        BoardPosition pos10 = new BoardPosition(3,1);
        gb.placeMarker(pos10, 'X');
        BoardPosition pos11 = new BoardPosition(3,2);
        gb.placeMarker(pos11, 'X');
        char[][] gameBoard= new char[4][3];
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        gameBoard[0][0] = 'X';
        gameBoard[1][0] = 'X';
        gameBoard[2][0] = 'X';
        gameBoard[0][1] = 'X';
        gameBoard[1][1] = 'X';
        gameBoard[2][1] = 'X';
        gameBoard[0][2] = 'X';
        gameBoard[1][2] = 'X';
        gameBoard[2][2] = 'X';
        gameBoard[3][0] = 'X';
        gameBoard[3][1] = 'X';
        gameBoard[3][2] = 'X';
        assertEquals(toString(gameBoard), gb.toString());
        assertTrue(gb.checkForDraw() == true);
    }

    @Test
    public void testWhatsAtPos_blank_space_square_board()
    {
        IGameBoard gb = makeGameBoard(3,3,3);
        BoardPosition pos = new BoardPosition(0,0);
        char[][] gameBoard= new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        assertEquals(toString(gameBoard), gb.toString());
        assertTrue(gb.whatsAtPos(pos) == ' ');
    }

    @Test
    public void testWhatsAtPos_blank_space_rectangular_board()
    {
        IGameBoard gb = makeGameBoard(5,3,3);
        BoardPosition pos3 = new BoardPosition(0,0);
        char[][] gameBoard= new char[5][3];
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        assertEquals(toString(gameBoard), gb.toString());
        assertTrue(gb.whatsAtPos(pos3) == ' ');
    }

    @Test
    public void testWhatsAtPos_not_blank_square_board()
    {
        IGameBoard gb = makeGameBoard(3,3,3);
        BoardPosition pos2 = new BoardPosition(0,0);
        gb.placeMarker(pos2, 'Y');
        char[][] gameBoard= new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        gameBoard[0][0] = 'Y';
        assertEquals(toString(gameBoard), gb.toString());
        assertTrue(gb.whatsAtPos(pos2) == 'Y');
    }

    @Test
    public void testWhatsAtPos_not_blank_rectangular_board()
    {
        IGameBoard gb = makeGameBoard(5,3,3);
        BoardPosition pos4 = new BoardPosition(0,0);
        gb.placeMarker(pos4, 'Y');
        char[][] gameBoard= new char[5][3];
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        gameBoard[0][0] = 'Y';
        assertEquals(toString(gameBoard), gb.toString());
        assertTrue(gb.whatsAtPos(pos4) == 'Y');
    }

    @Test
    public void testWhatsAtPos_square_board_bottom_right_position_blank_space() // boundary
    {
        IGameBoard gb = makeGameBoard(3,3,3);
        BoardPosition pos5 = new BoardPosition(2,2);
        char[][] gameBoard= new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        assertEquals(toString(gameBoard), gb.toString());
        assertTrue(gb.whatsAtPos(pos5) == ' ');
    }

    @Test
    public void testIsPlayerAtPos_square_board_blank_space()
    {
        IGameBoard gb = makeGameBoard(3,3,3);
        BoardPosition pos = new BoardPosition(0,0);
        char[][] gameBoard= new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        assertEquals(toString(gameBoard), gb.toString());
        assertTrue(gb.isPlayerAtPos(pos, 'X') == false);
    }

    @Test
    public void testIsPlayerAtPos_rectangular_board_blank_space()
    {
        IGameBoard gb = makeGameBoard(5,3,3);
        BoardPosition pos2 = new BoardPosition(0,2);
        char[][] gameBoard= new char[5][3];
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        assertEquals(toString(gameBoard), gb.toString());
        assertTrue(gb.isPlayerAtPos(pos2, 'Y') == false);
    }

    @Test
    public void testIsPlayerAtPos_square_board_not_blank_space()
    {
        IGameBoard gb = makeGameBoard(3,3,3);
        BoardPosition pos3 = new BoardPosition(2,0);
        gb.placeMarker(pos3, 'X');
        char[][] gameBoard= new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        gameBoard[2][0] = 'X';
        assertEquals(toString(gameBoard), gb.toString());
        assertTrue(gb.isPlayerAtPos(pos3, 'X') == true);
    }

    @Test
    public void testIsPlayerAtPos_rectangular_board_not_blank_space()
    {
        IGameBoard gb = makeGameBoard(5,3,3);
        BoardPosition pos4 = new BoardPosition(2,2);
        gb.placeMarker(pos4, 'Y');
        char[][] gameBoard= new char[5][3];
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        gameBoard[2][2] = 'Y';
        assertEquals(toString(gameBoard), gb.toString());
        assertTrue(gb.isPlayerAtPos(pos4, 'Y') == true);
    }

    @Test
    public void testIsPlayerAtPos_square_board_marker_not_part_of_game()
    {
        IGameBoard gb = makeGameBoard(3,3,3);
        BoardPosition pos5 = new BoardPosition(1,1);
        BoardPosition pos = new BoardPosition(0,0);
        gb.placeMarker(pos, 'X');
        pos = new BoardPosition(1,0);
        gb.placeMarker(pos, 'Z');
        char[][] gameBoard= new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        gameBoard[0][0] = 'X';
        gameBoard[1][0] = 'Z';
        assertEquals(toString(gameBoard), gb.toString());
        assertTrue(gb.isPlayerAtPos(pos5, 'Y') == false);
    }

    @Test
    public void testPlaceMarker_top_left_corner()
    {
        IGameBoard gb = makeGameBoard(3,3,3);
        BoardPosition pos = new BoardPosition(0,0);
        gb.placeMarker(pos, 'X');
        char[][] gameBoard= new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        gameBoard[0][0] = 'X';
        assertEquals(toString(gameBoard), gb.toString());
    }

    @Test
    public void testPlaceMarker_top_right_corner()
    {
        IGameBoard gb = makeGameBoard(3,3,3);
        BoardPosition pos = new BoardPosition(0,2);
        gb.placeMarker(pos, 'X');
        char[][] gameBoard= new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        gameBoard[0][2] = 'X';
        assertEquals(toString(gameBoard), gb.toString());
    }

    @Test
    public void testPlaceMarker_bottom_left_corner()
    {
        IGameBoard gb = makeGameBoard(3,3,3);
        BoardPosition pos = new BoardPosition(2,0);
        gb.placeMarker(pos, 'X');
        char[][] gameBoard= new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        gameBoard[2][0] = 'X';
        assertEquals(toString(gameBoard), gb.toString());
    }

    @Test
    public void testPlaceMarker_bottom_right_corner()
    {
        IGameBoard gb = makeGameBoard(3,3,3);
        BoardPosition pos = new BoardPosition(2,2);
        gb.placeMarker(pos, 'X');
        char[][] gameBoard= new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        gameBoard[2][2] = 'X';
        assertEquals(toString(gameBoard), gb.toString());
    }

    @Test
    public void testPlaceMarker_middle_pos_special_character_routine()
    {
        IGameBoard gb = makeGameBoard(3,3,3);
        BoardPosition pos = new BoardPosition(1,1);
        gb.placeMarker(pos, ';');
        char[][] gameBoard= new char[3][3];
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = ' ';
            }
        }
        gameBoard[1][1] = ';';
        assertEquals(toString(gameBoard), gb.toString());
    }
}
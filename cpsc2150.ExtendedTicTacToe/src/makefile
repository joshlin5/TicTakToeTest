default: cpsc2150/ExtendedTicTacToe/BoardPosition.java cpsc2150/ExtendedTicTacToe/GameBoard.java cpsc2150/ExtendedTicTacToe/GameScreen.java cpsc2150/ExtendedTicTacToe/IGameBoard.java cpsc2150/ExtendedTicTacToe/AbsGameBoard.java cpsc2150/ExtendedTicTacToe/GameBoardMem.java
	javac cpsc2150/ExtendedTicTacToe/BoardPosition.java cpsc2150/ExtendedTicTacToe/GameBoard.java cpsc2150/ExtendedTicTacToe/GameScreen.java cpsc2150/ExtendedTicTacToe/IGameBoard.java cpsc2150/ExtendedTicTacToe/AbsGameBoard.java cpsc2150/ExtendedTicTacToe/GameBoardMem.java

run: cpsc2150/ExtendedTicTacToe/BoardPosition.class cpsc2150/ExtendedTicTacToe/GameBoard.class cpsc2150/ExtendedTicTacToe/GameScreen.class cpsc2150/ExtendedTicTacToe/IGameBoard.class cpsc2150/ExtendedTicTacToe/AbsGameBoard.class cpsc2150/ExtendedTicTacToe/GameBoardMem.class
	java cpsc2150.ExtendedTicTacToe.GameScreen

test: cpsc2150/ExtendedTicTacToe/*.java
	javac -cp .:/usr/share/java/junit4.jar cpsc2150/ExtendedTicTacToe/*.java

testGBmem: cpsc2150/ExtendedTicTacToe/*.class
	java  -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.ExtendedTicTacToe.TestGameBoardMem

testGB: cpsc2150/ExtendedTicTacToe/*.class
	java  -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.ExtendedTicTacToe.TestGameBoard

clean:
	rm cpsc2150/ExtendedTicTacToe/*.class

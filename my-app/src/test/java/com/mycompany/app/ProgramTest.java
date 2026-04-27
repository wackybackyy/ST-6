package com.mycompany.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.GridLayout;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class ProgramTest {

    @Test
    void gameInitialStateIsPlaying() {
        Game game = new Game();
        assertEquals(State.PLAYING, game.state);
    }

    @Test
    void gameInitialBoardIsEmpty() {
        Game game = new Game();
        for (int i = 0; i < 9; i++) {
            assertEquals(' ', game.board[i]);
        }
    }

    @Test
    void gamePlayersHaveCorrectSymbols() {
        Game game = new Game();
        assertEquals('X', game.player1.symbol);
        assertEquals('O', game.player2.symbol);
    }

    @Test
    void checkStateReturnsXWinForFirstRow() {
        Game game = new Game();
        char[] board = {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(board));
    }

    @Test
    void checkStateReturnsOWinForFirstColumn() {
        Game game = new Game();
        char[] board = {'O', ' ', ' ', 'O', ' ', ' ', 'O', ' ', ' '};
        game.symbol = 'O';
        assertEquals(State.OWIN, game.checkState(board));
    }

    @Test
    void checkStateReturnsXWinForMainDiagonal() {
        Game game = new Game();
        char[] board = {'X', ' ', ' ', ' ', 'X', ' ', ' ', ' ', 'X'};
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(board));
    }

    @Test
    void checkStateReturnsDrawForFullBoardWithoutWinner() {
        Game game = new Game();
        char[] board = {'X', 'O', 'X', 'X', 'O', 'O', 'O', 'X', 'X'};
        game.symbol = 'X';
        assertEquals(State.DRAW, game.checkState(board));
    }

    @Test
    void checkStateReturnsPlayingForUnfinishedBoard() {
        Game game = new Game();
        char[] board = {'X', 'O', 'X', ' ', 'O', ' ', 'O', 'X', ' '};
        game.symbol = 'X';
        assertEquals(State.PLAYING, game.checkState(board));
    }

    @Test
    void generateMovesReturnsAllFreeCells() {
        Game game = new Game();
        char[] board = {'X', ' ', 'X', ' ', 'O', ' ', ' ', 'X', ' '};
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(board, moves);

        assertEquals(5, moves.size());
        assertTrue(moves.contains(1));
        assertTrue(moves.contains(3));
        assertTrue(moves.contains(5));
        assertTrue(moves.contains(6));
        assertTrue(moves.contains(8));
    }

    @Test
    void generateMovesReturnsEmptyListForFullBoard() {
        Game game = new Game();
        char[] board = {'X', 'O', 'X', 'X', 'O', 'O', 'O', 'X', 'X'};
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(board, moves);

        assertEquals(0, moves.size());
    }

    @Test
    void evaluatePositionReturnsPositiveInfForWinningX() {
        Game game = new Game();
        Player player = new Player();
        player.symbol = 'X';
        char[] board = {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'X';

        assertEquals(Game.INF, game.evaluatePosition(board, player));
    }

    @Test
    void evaluatePositionReturnsNegativeInfForLosingX() {
        Game game = new Game();
        Player player = new Player();
        player.symbol = 'X';
        char[] board = {'O', 'O', 'O', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'O';

        assertEquals(-Game.INF, game.evaluatePosition(board, player));
    }

    @Test
    void evaluatePositionReturnsZeroForDraw() {
        Game game = new Game();
        Player player = new Player();
        player.symbol = 'X';
        char[] board = {'X', 'O', 'X', 'X', 'O', 'O', 'O', 'X', 'X'};
        game.symbol = 'X';

        assertEquals(0, game.evaluatePosition(board, player));
    }

    @Test
    void evaluatePositionReturnsMinusOneForPlayingPosition() {
        Game game = new Game();
        Player player = new Player();
        player.symbol = 'X';
        char[] board = {'X', 'O', 'X', ' ', 'O', ' ', 'O', 'X', ' '};
        game.symbol = 'X';

        assertEquals(-1, game.evaluatePosition(board, player));
    }

    @Test
    void minimaxReturnsValidMoveNumber() {
        Game game = new Game();
        Player player = game.player2;
        char[] board = {'X', ' ', ' ', ' ', 'O', ' ', ' ', ' ', 'X'};
        int move = game.MiniMax(board, player);

        assertTrue(move >= 1 && move <= 9);
    }

    @Test
    void minMoveReturnsTerminalValueForWinningBoard() {
        Game game = new Game();
        Player player = new Player();
        player.symbol = 'X';
        char[] board = {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'X';

        assertEquals(Game.INF, game.MinMove(board, player));
    }

    @Test
    void maxMoveReturnsTerminalValueForDrawBoard() {
        Game game = new Game();
        Player player = new Player();
        player.symbol = 'X';
        char[] board = {'X', 'O', 'X', 'X', 'O', 'O', 'O', 'X', 'X'};
        game.symbol = 'X';

        assertEquals(0, game.MaxMove(board, player));
    }

    @Test
    void ticTacToeCellInitialMarkerIsSpace() {
        TicTacToeCell cell = new TicTacToeCell(4, 1, 1);
        assertEquals(' ', cell.getMarker());
    }

    @Test
    void ticTacToeCellStoresCoordinatesAndNumber() {
        TicTacToeCell cell = new TicTacToeCell(5, 2, 1);
        assertEquals(5, cell.getNum());
        assertEquals(2, cell.getCol());
        assertEquals(1, cell.getRow());
    }

    @Test
    void ticTacToeCellSetMarkerChangesMarker() {
        TicTacToeCell cell = new TicTacToeCell(0, 0, 0);
        cell.setMarker("X");
        assertEquals('X', cell.getMarker());
        assertFalse(cell.isEnabled());
    }

    @Test
    void utilityPrintCharArrayDoesNotThrow() {
        char[] board = {'X', 'O', 'X', ' ', 'O', ' ', 'O', 'X', ' '};
        Utility.print(board);
        assertTrue(true);
    }

    @Test
    void utilityPrintIntArrayDoesNotThrow() {
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Utility.print(values);
        assertTrue(true);
    }

    @Test
    void utilityPrintMoveListDoesNotThrow() {
        ArrayList<Integer> moves = new ArrayList<>();
        moves.add(1);
        moves.add(4);
        moves.add(8);
        Utility.print(moves);
        assertTrue(true);
    }

    @Test
    void panelCanBeCreated() {
        TicTacToePanel panel = new TicTacToePanel(new GridLayout(3, 3));
        assertNotNull(panel);
    }

    @Test
    void gameCurrentPlayerCanBeAssigned() {
        Game game = new Game();
        game.cplayer = game.player1;
        assertEquals('X', game.cplayer.symbol);
    }
}
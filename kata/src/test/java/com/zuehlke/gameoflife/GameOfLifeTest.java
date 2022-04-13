package com.zuehlke.gameoflife;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameOfLifeTest {

    @Test
    void startingGrid_8x4grid() {
        String expected = """
                ........
                ........
                ........
                ........""";

        GameOfLife testee = new GameOfLife();
        assertEquals(expected, testee.getCurrentGrid());
    }

    @Test
    void populatedStartingGrid_8x4gridWithParams() {
        String expected = """
                ........
                .....*..
                ....**..
                ........""";

        GameOfLife game = new GameOfLife(expected);

        assertEquals(expected, game.getCurrentGrid());
    }

    @Test
    void NextGeneration() {
        String initial = """
                ........
                .....*..
                ....**..
                ........
                """;
        String expected = """
                ........
                ....**..
                ....**..
                ........""";

        GameOfLife game = new GameOfLife(initial);

        game.runCycle();

        assertEquals(expected, game.getCurrentGrid());
    }

    @Test
    void CountAliveNeighboursOfRow1Column4() {
        String initial = """
                ........
                .....*..
                ....**..
                ........
                """;

        GameOfLife game = new GameOfLife(initial);

        assertEquals(3, game.getGrid().countAliveNeighbours(1, 4));
    }

    @Test
    void CountAliveNeighboursOfRow1Column5() {
        String initial = """
                ........
                .....*..
                ....**..
                ........
                """;

        GameOfLife game = new GameOfLife(initial);

        assertEquals(2, game.getGrid().countAliveNeighbours(1, 5));
    }

    @Test
    void CountAliveNeighboursOfRow0Column0() {
        String initial = """
                **......
                **...*..
                ....**..
                ........
                """;

        GameOfLife game = new GameOfLife(initial);

        assertEquals(3, game.getGrid().countAliveNeighbours(0, 0));
    }

    @Test
    void dieWhenOverpopulated() {
        String initial = """
                ........
                ...***..
                ....**..
                ........
                """;
        String expected = """
                ....*...
                ...*.*..
                ...*.*..
                ........""";
        GameOfLife game = new GameOfLife(initial);

        game.runCycle();

        assertEquals(expected, game.getCurrentGrid());
    }

    @Test
    void dieWhenUnderpopulated() {
        String initial = """
                ........
                ........
                ....*...
                ........
                """;
        String expected = """
                ........
                ........
                ........
                ........""";
        GameOfLife game = new GameOfLife(initial);

        game.runCycle();

        assertEquals(expected, game.getCurrentGrid());
    }

    @Test
    void runForManyCycles() throws InterruptedException {
        String initial = """
                ....*....
                ...*.*...
                ..*.*.*..
                .*.*.*.*.
                """;
        GameOfLife game = new GameOfLife(initial);

        game.runFor(10, 100);
    }

    private void cycleAndPrint(GameOfLife game) {
        game.runCycle();
        System.out.println(game.getCurrentGrid());
        System.out.println("---");
    }
}
package com.zuehlke.gameoflife;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

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

        var grid = new Grid(initial);

        assertEquals(3, grid.countAliveNeighbours(0, 0));
    }

}
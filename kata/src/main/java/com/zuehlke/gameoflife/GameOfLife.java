package com.zuehlke.gameoflife;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameOfLife {

    private Grid grid;

    public GameOfLife() {
        this("""
                ........
                ........
                ........
                ........
                """);
    }

    public Grid getGrid() {
        return grid;
    }

    public GameOfLife(String grid) {
        this.grid = new Grid(grid);
    }

    public String getCurrentGrid() {
        return grid.toString();
    }

    public void runCycle() {
        grid = grid.applyForEachCell(
                (Integer aliveNeighbours, Boolean isAlive) -> {
            var newState = Grid.DEAD;
            if (aliveNeighbours == 3) {
                newState = Grid.ALIVE;
            } else if (aliveNeighbours == 2 && isAlive) {
                newState = Grid.ALIVE;
            }
            return newState;
        }
        );

    }


    public void runFor(int numberOfCycles, int delayBetweenCycles) throws InterruptedException {
        for (int i = 0; i < numberOfCycles; i++) {
            runCycle();
            Thread.sleep(delayBetweenCycles);
            System.out.println(getCurrentGrid());
            System.out.println();
        }
    }
}

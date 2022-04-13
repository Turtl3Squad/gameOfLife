package com.zuehlke.gameoflife;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Grid {
    public static final char DEAD = '.';
    public static final char ALIVE = '*';
    List<char[]> grid;

    public Grid(String grid) {
        String[] rows = grid.split("\n");
        this.grid = new ArrayList<>();
        for (String row : rows) {
            this.grid.add(row.toCharArray());
        }
    }

    public Grid(ArrayList<char[]> newGrid) {
        this.grid = newGrid;
    }

    public Grid applyForEachCell(BiFunction<Integer, Boolean, Character> cellContentEval) {
        ArrayList<char[]> newGrid = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < grid.size(); rowIndex++) {
            char[] row = grid.get(rowIndex);
            int rowLength = row.length;
            char[] newRow = new char[rowLength];
            for (int columnIndex = 0; columnIndex < rowLength; columnIndex++) {
                int aliveNeighbours = countAliveNeighbours(rowIndex, columnIndex);

                boolean isAlive = isAlive(rowIndex, columnIndex);
                newRow[columnIndex] = cellContentEval.apply(aliveNeighbours, isAlive);

            }
            newGrid.add(newRow);
        }
        return new Grid(newGrid);
    }

    public int countAliveNeighbours(int row, int column) {
        var columnBefore = column - 1;
        var columnAfter = column + 1;
        var rowBefore = row - 1;
        var rowAfter = row + 1;

        var aliveCells = 0;
        for (int currentColumn = columnBefore; currentColumn <= columnAfter; currentColumn++) {
            for (int currentRow = rowBefore; currentRow <= rowAfter; currentRow++) {
                if ((currentRow == row) && (currentColumn == column)) {
                    continue;
                }
                if (isAlive(currentRow, currentColumn)) {
                    aliveCells++;
                }
            }
        }

        return aliveCells;
    }

    boolean isAlive(int row, int column) {
        if (row < 0 || row >= grid.size()) {
            return false;
        }
        if (column < 0 || column >= grid.get(row).length) {
            return false;
        }
        return grid.get(row)[column] == ALIVE;
    }

    @Override
    public String toString() {
        return grid.stream()
                .map(String::new)
                .collect(Collectors.joining("\n"));
    }
}
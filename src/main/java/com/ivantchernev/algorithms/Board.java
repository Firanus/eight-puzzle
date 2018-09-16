package com.ivantchernev.algorithms;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Stack;

public class Board {
    private int dimension;
    private int[][] blocks;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        dimension = blocks.length;
        this.blocks = cloneBlocks(blocks);
    }

    // board dimension n
    public int dimension() {
        return dimension;
    }

    // number of blocks out of place
    public int hamming() {
        int count = 0;
        for(int i = 0; i < dimension; i++) {
            for(int j = 0; j < dimension; j++) {
                if (targetNumberForPosition(i, j) != 0 && blocks[i][j] != targetNumberForPosition(i, j)) count++;
            }
        }
        return count;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int distances = 0;
        for(int i = 0; i < dimension; i++) {
            for(int j = 0; j < dimension; j++) {
                if (blocks[i][j] != 0 && blocks[i][j] != targetNumberForPosition(i, j)) {
                    distances += Math.abs(xPositionForNumber(blocks[i][j]) - j);
                    distances += Math.abs(yPositionForNumber(blocks[i][j]) - i);
                }
            }
        }
        return distances;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board compareBoard = (Board) y;
        if (compareBoard.dimension() != this.dimension()) return false;

        for(int i = 0; i < dimension; i++) {
            for(int j = 0; j < dimension; j++) {
                if (blocks[i][j] != compareBoard.blocks[i][j]) return false;
            }
        }
        return true;
    }

//    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        int[][] twinLayout = swapBlocks(blocks,
                dimension - 2,
                dimension - 1,
                dimension - 1,
                dimension - 1);

        return new Board(twinLayout);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        int[] zeroPosition = positionOfNumber(0);
        int zeroX = zeroPosition[0];
        int zeroY = zeroPosition[1];

        Stack<Board> neighboursStack = new Stack<>();
        if (zeroX > 0) neighboursStack.push(new Board(swapBlocks(blocks, zeroX, zeroY, zeroX - 1, zeroY)));
        if (zeroY > 0) neighboursStack.push(new Board(swapBlocks(blocks, zeroX, zeroY, zeroX, zeroY - 1)));
        if (zeroX < dimension - 1) neighboursStack.push(new Board(swapBlocks(blocks, zeroX, zeroY, zeroX + 1, zeroY)));
        if (zeroY < dimension - 1) neighboursStack.push(new Board(swapBlocks(blocks, zeroX, zeroY, zeroX, zeroY + 1)));

        return neighboursStack;
    }

    // indices are 0 based
    private int targetNumberForPosition(int x, int y) {
        return x == (dimension - 1) && y == (dimension - 1) ? 0 : (x * dimension) + y + 1;
    }

    // returned x-position is 0-based
    private int xPositionForNumber(int num) {
        return num == 0 ? dimension - 1 : (num - 1) % dimension;
    }

    //returned y-position is 0-based
    private int yPositionForNumber(int num) {
        return num == 0 ? dimension - 1 : (num - 1) / dimension;
    }

    // Returns position in array [x, y]
    // Sub-optimal to return an int[] rather than
    // a custom type, but refactor not worth the time.
    private int[] positionOfNumber(int num) {
        int[] returnPosition = new int[2];
        for (int i = 0; i < dimension; i++ ) {
            for (int j = 0; j < dimension; j++) {
                if(blocks[i][j] == num) {
                    returnPosition[0] = j;
                    returnPosition[1] = i;
                    return returnPosition;
                }
            }
        }
        throw new NoSuchElementException();
    }

    private int[][] cloneBlocks(int[][] blocks) {
        int[][] copiedBlocks = new int[dimension][dimension];
        for(int i = 0; i < dimension; i++) {
            for(int j = 0; j < dimension; j++) {
                copiedBlocks[i][j] = blocks[i][j];
            }
        }
        return copiedBlocks;
    }

    private int[][] cloneBlocks() {
        return cloneBlocks(this.blocks);
    }

    private int[][] swapBlocks(int[][] inputBlocks, int x1, int y1, int x2, int y2) {
        int[][] copiedBlocks = cloneBlocks();
        int temp = copiedBlocks[y1][x1];
        copiedBlocks[y1][x1] = copiedBlocks[y2][x2];
        copiedBlocks[y2][x2] = temp;
        return copiedBlocks;
    }

//    public String toString()               // string representation of this board (in the output format specified below)
}

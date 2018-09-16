package com.ivantchernev.algorithms;

public class Board {
    private int dimension;
    private int[][] blocks;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        dimension = blocks.length;
        this.blocks = blocks;
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
//    public Board twin() {
//
//    }

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

//    public Iterable<Board> neighbors()     // all neighboring boards
//    public String toString()               // string representation of this board (in the output format specified below)
}

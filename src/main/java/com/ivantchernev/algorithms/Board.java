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
                if (blocks[i][j] != 0 && blocks[i][j] != (i * dimension) + j + 1) count++;
            }
        }
        return count;
    }
//    public int manhattan()                 // sum of Manhattan distances between blocks and goal
//    public boolean isGoal()                // is this board the goal board?
//    public Board twin()                    // a board that is obtained by exchanging any pair of blocks
//    public boolean equals(Object y)        // does this board equal y?
//    public Iterable<Board> neighbors()     // all neighboring boards
//    public String toString()               // string representation of this board (in the output format specified below)
}

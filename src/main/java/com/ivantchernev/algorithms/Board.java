package com.ivantchernev.algorithms;

public class Board {
    private int dimension = 0;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        dimension = blocks.length;
    }

    // board dimension n
    public int dimension() {
        return dimension;
    }
//    public int hamming()                   // number of blocks out of place
//    public int manhattan()                 // sum of Manhattan distances between blocks and goal
//    public boolean isGoal()                // is this board the goal board?
//    public Board twin()                    // a board that is obtained by exchanging any pair of blocks
//    public boolean equals(Object y)        // does this board equal y?
//    public Iterable<Board> neighbors()     // all neighboring boards
//    public String toString()               // string representation of this board (in the output format specified below)
}

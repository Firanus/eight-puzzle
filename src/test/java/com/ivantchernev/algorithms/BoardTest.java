package com.ivantchernev.algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    public void dimensionShouldReturnLengthOfBoard() {
        int[][] boardInitialiser = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };
        Board b = new Board(boardInitialiser);

        assertEquals(b.dimension(), 3);
    }
}
package com.ivantchernev.algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {

    @Test
    void SolverThrowsIllegalArgumentExceptionIfPassedNullBoard() {
        assertThrows(IllegalArgumentException.class,
                () -> new Solver(null));
    }

}
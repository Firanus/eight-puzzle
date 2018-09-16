package com.ivantchernev.algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    public void dimensionShouldReturnLengthOfBoard() {
        Board b = new Board(ExampleBoards.threeBoardGoal);

        assertEquals(3, b.dimension());
    }

    @Test
    public void hammingShouldReturnBlocksOutOfPlace() {
        assertAll("Hamming",
                () -> assertEquals(0, new Board(ExampleBoards.twoBoardGoal).hamming()),
                () -> assertEquals(0, new Board(ExampleBoards.threeBoardGoal).hamming()),
                () -> assertEquals(0, new Board(ExampleBoards.fourBoardGoal).hamming()),

                () -> assertEquals(2, new Board(ExampleBoards.twoBoard0132).hamming()),
                () -> assertEquals(1, new Board(ExampleBoards.threeBoardOneAway).hamming()),
                () -> assertEquals(4, new Board(ExampleBoards.threeBoardPuzzleFour).hamming()),
                () -> assertEquals(6, new Board(ExampleBoards.threeBoardPuzzleThreeByThreeTwenty).hamming()),
                () -> assertEquals(10, new Board(ExampleBoards.fourBoardPuzzleFourByFourEleven).hamming())
        );
    }

    @Test
    public void manhattanShouldReturnDistancesBetweenBlocksAndGoal() {
        assertAll("Manhattan",
                () -> assertEquals(0, new Board(ExampleBoards.twoBoardGoal).manhattan()),
                () -> assertEquals(0, new Board(ExampleBoards.threeBoardGoal).manhattan()),
                () -> assertEquals(0, new Board(ExampleBoards.fourBoardGoal).manhattan()),

                () -> assertEquals(2, new Board(ExampleBoards.twoBoard0132).manhattan()),
                () -> assertEquals(1, new Board(ExampleBoards.threeBoardOneAway).manhattan()),
                () -> assertEquals(4, new Board(ExampleBoards.threeBoardPuzzleFour).manhattan()),
                () -> assertEquals(12, new Board(ExampleBoards.threeBoardPuzzleThreeByThreeTwenty).manhattan()),
                () -> assertEquals(11, new Board(ExampleBoards.fourBoardPuzzleFourByFourEleven).manhattan())
        );
    }
}
package com.ivantchernev.algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    void dimensionShouldReturnLengthOfBoard() {
        Board b = new Board(ExampleBoards.threeBoardGoal);

        assertEquals(3, b.dimension());
    }

    @Test
    void hammingShouldReturnBlocksOutOfPlace() {
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
    void manhattanShouldReturnDistancesBetweenBlocksAndGoal() {
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

    @Test
    void isGoalShouldReturnTrueForGoalBoardsFalseOtherwise() {
        assertAll("isGoal",
                () -> assertTrue(new Board(ExampleBoards.twoBoardGoal).isGoal()),
                () -> assertTrue(new Board(ExampleBoards.threeBoardGoal).isGoal()),
                () -> assertTrue(new Board(ExampleBoards.fourBoardGoal).isGoal()),

                () -> assertFalse(new Board(ExampleBoards.twoBoard0132).isGoal()),
                () -> assertFalse(new Board(ExampleBoards.threeBoardOneAway).isGoal()),
                () -> assertFalse(new Board(ExampleBoards.threeBoardPuzzleFour).isGoal()),
                () -> assertFalse(new Board(ExampleBoards.threeBoardPuzzleThreeByThreeTwenty).isGoal()),
                () -> assertFalse(new Board(ExampleBoards.fourBoardPuzzleFourByFourEleven).isGoal())
        );
    }

    @Test
    void equalsReturnsTrueBetweenAnItemAndItself() {
        Board board = new Board(ExampleBoards.twoBoard0132);
        assertEquals(board, board);
    }

    @Test
    void equalsReturnsFalseBetweenAnyBoardAndNull() {
        assertNotEquals(new Board(ExampleBoards.twoBoard0132), null);
    }

    @Test
    void equalsReturnsFalseBetweenAnyBoardAndDifferentType() {
        assertNotEquals(new Board(ExampleBoards.twoBoard0132), new App());
    }

    @Test
    void equalsComparesBoards() {
        Board twoGoal = new Board(ExampleBoards.twoBoardGoal);
        Board threeGoal = new Board(ExampleBoards.threeBoardGoal);
        Board threePuzzle = new Board(ExampleBoards.threeBoardPuzzleThreeByThreeTwenty);

        assertAll("isGoal",
                () -> assertEquals(twoGoal, twoGoal),
                () -> assertEquals(threeGoal, threeGoal),
                () -> assertEquals(threePuzzle, threePuzzle),

                () -> assertNotEquals(twoGoal, threeGoal),
                () -> assertNotEquals(twoGoal, threePuzzle),
                () -> assertNotEquals(threeGoal, threePuzzle)
        );
    }

    @Test
    void twinSwapsLastTwoBlocksOfBoard() {
        int[][] initialBoardLayout = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };

        int[][] twinBoardLayout = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 8}
        };

        assertEquals(
                new Board(initialBoardLayout).twin(),
                new Board((twinBoardLayout))
        );
    }
}
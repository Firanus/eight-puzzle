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
    void changingInitialBlocksArrayShouldNotAffectBoard() {
        int[][] initialLayout = {
                {1, 2},
                {3, 0}
        };
        Board twoGoal = new Board(initialLayout);

        assertTrue(twoGoal.isGoal());

        initialLayout[1][0] = 0;
        initialLayout[1][1] = 3;

        assertTrue(twoGoal.isGoal());
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
    void twinDoesntChangeInitialBoard() {
        int[][] initialBoardLayout = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };
        Board initialBoard = new Board(initialBoardLayout);
        initialBoard.twin();

        assertEquals(
                initialBoard,
                new Board(initialBoardLayout)
        );
    }

    @Test
    void twinSwapsFirstTwoBlocksOnLastRowOfThreeBoard() {
        int[][] initialBoardLayout = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };

        int[][] twinBoardLayout = {
                {1, 2, 3},
                {4, 5, 6},
                {8, 7, 0}
        };

        assertEquals(
                new Board(initialBoardLayout).twin(),
                new Board((twinBoardLayout))
        );
    }

    @Test
    void twinSwapsSecondLastTwoBlocksOnLastRowOfFourBoard() {
        int[][] initialBoardLayout = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 0}
        };

        int[][] twinBoardLayout = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 15, 14, 0}
        };

        assertEquals(
                new Board(initialBoardLayout).twin(),
                new Board((twinBoardLayout))
        );
    }

    @Test
    void neighboursDoesntChangeInitialBoard() {
        int[][] initialBoardLayout = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };
        Board initialBoard = new Board(initialBoardLayout);
        initialBoard.neighbors();

        assertEquals(
                initialBoard,
                new Board(initialBoardLayout)
        );
    }

    @Test
    void neighboursReturnsNeighbouringBoardsWhenOpenSlotOnEdges() {
        int[][] initialBoardLayout = {
                {1, 2},
                {3, 0}
        };
        int[][] verticalNeighborLayout  = {
                {1, 0},
                {3, 2}
        };
        int[][] horizontalNeighborLayout  = {
                {1, 2},
                {0, 3}
        };

        Board board = new Board(initialBoardLayout);
        Board verticalNeighbour = new Board(verticalNeighborLayout);
        Board horizontalNeighbour = new Board(horizontalNeighborLayout);

        boolean containsHorNeighbour = false;
        boolean containsVertNeighbour = false;
        int count = 0;

        for(Board neighbour : board.neighbors()) {
            count++;
            if (neighbour.equals(verticalNeighbour)) containsVertNeighbour = true;
            if (neighbour.equals(horizontalNeighbour)) containsHorNeighbour = true;
        }

        assertEquals(2, count);
        assertTrue(containsHorNeighbour && containsVertNeighbour);
    }

    @Test
    void neighboursReturnsNeighbouringBoardsWhenOpenSlotInMiddle() {
        int[][] initialBoardLayout = {
                {1, 2, 3},
                {4, 0, 6},
                {7, 8, 5}
        };
        int[][] topNeighborLayout  = {
                {1, 0, 3},
                {4, 2, 6},
                {7, 8, 5}
        };
        int[][] bottomNeighborLayout  = {
                {1, 2, 3},
                {4, 8, 6},
                {7, 0, 5}
        };
        int[][] leftNeighborLayout  = {
                {1, 2, 3},
                {0, 4, 6},
                {7, 8, 5}
        };
        int[][] rightNeighborLayout  = {
                {1, 2, 3},
                {4, 6, 0},
                {7, 8, 5}
        };

        Board board = new Board(initialBoardLayout);
        Board topNeighbour = new Board(topNeighborLayout);
        Board bottomNeighbour = new Board(bottomNeighborLayout);
        Board leftNeighbour = new Board(leftNeighborLayout);
        Board rightNeighbour = new Board(rightNeighborLayout);

        boolean containsTopNeighbour = false;
        boolean containsBottomNeighbour = false;
        boolean containsLeftNeighbour = false;
        boolean containsRightNeighbour = false;
        int count = 0;

        for(Board neighbour : board.neighbors()) {
            count++;
            if (neighbour.equals(topNeighbour)) containsTopNeighbour = true;
            if (neighbour.equals(bottomNeighbour)) containsBottomNeighbour = true;
            if (neighbour.equals(leftNeighbour)) containsLeftNeighbour = true;
            if (neighbour.equals(rightNeighbour)) containsRightNeighbour = true;
        }

        assertEquals(4, count);
        assertTrue(containsTopNeighbour &&
                containsBottomNeighbour &&
                containsLeftNeighbour &&
                containsRightNeighbour);
    }

    @Test
    void toStringReturnsStringRepresentationInCorrectFormat() {
        assertAll("toString",
                () -> assertEquals("2\n" +
                        " 1  2 \n" +
                        " 3  0 \n",
                        new Board(ExampleBoards.twoBoardGoal).toString()),
                () -> assertEquals("3\n" +
                        " 0  1  3 \n" +
                        " 4  2  5 \n" +
                        " 7  8  6 \n",
                        new Board(ExampleBoards.threeBoardPuzzleFour).toString()),
                () -> assertEquals("4\n" +
                        " 1  2  3  4 \n" +
                        " 5  6  7  8 \n" +
                        " 9 10 11 12 \n" +
                        "13 14 15  0 \n",
                        new Board(ExampleBoards.fourBoardGoal).toString())
        );
    }
}
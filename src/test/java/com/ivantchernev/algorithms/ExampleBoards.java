package com.ivantchernev.algorithms;

public class ExampleBoards {
    static final int[][] twoBoardGoal = {
            {1, 2},
            {3, 0}
    };

    static final int[][] twoBoard0132 = {
            {0, 1},
            {3, 2}
    };

    static final int[][] threeBoardGoal = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}
    };

    static final int[][] threeBoardOneAway = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 0, 8}
    };

    static final int[][] threeBoardPuzzleFour = {
            {0, 1, 3},
            {4, 2, 5},
            {7, 8, 6}
    };

    static final int[][] threeBoardPuzzleThreeByThreeTwenty = {
            {7, 4, 3},
            {2, 8, 6},
            {0, 5, 1}
    };

    static final int[][] threeBoardUnsolvable = {
            {1, 2, 3},
            {4, 5, 6},
            {8, 7, 0}
    };

    static final int[][] fourBoardGoal = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    static final int[][] fourBoardPuzzleFourByFourEleven = {
            {5, 1, 3, 4},
            {9, 2, 7, 8},
            {13, 0, 10, 12},
            {14, 6, 11, 15}
    };

    static final int[][] getThreeBoardPuzzleFourStepOne = {
            {1, 0, 3},
            {4, 2, 5},
            {7, 8, 6}
    };

    static final int[][] getThreeBoardPuzzleFourStepTwo = {
            {1, 2, 3},
            {4, 0, 5},
            {7, 8, 6}
    };

    static final int[][] getThreeBoardPuzzleFourStepThree = {
            {1, 2, 3},
            {4, 5, 0},
            {7, 8, 6}
    };
}

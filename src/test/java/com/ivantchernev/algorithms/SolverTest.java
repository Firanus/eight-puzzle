package com.ivantchernev.algorithms;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {

    @Test
    void solverThrowsIllegalArgumentExceptionIfPassedNullBoard() {
        assertThrows(IllegalArgumentException.class,
                () -> new Solver(null));
    }

    @Test
    void solverSolvesTwoGoalBoard() {
        Board twoGoalBoard = new Board(ExampleBoards.twoBoardGoal);
        Solver s = new Solver(twoGoalBoard);

        assertTrue(s.isSolvable());
        assertEquals(0, s.moves());
        assertEquals(twoGoalBoard, ((Stack<Board>) s.solution()).pop());
    }

    @Test
    void solverSolvesThreeGoalBoard() {
        Board threeGoalBoard = new Board(ExampleBoards.threeBoardGoal);
        Solver s = new Solver(threeGoalBoard);

        assertTrue(s.isSolvable());
        assertEquals(0, s.moves());
        assertEquals(threeGoalBoard, ((Stack<Board>) s.solution()).pop());
    }

    @Test
    void solverSolvesFourGoalBoard() {
        Board fourGoalBoard = new Board(ExampleBoards.fourBoardGoal);
        Solver s = new Solver(fourGoalBoard);

        assertTrue(s.isSolvable());
        assertEquals(0, s.moves());
        assertEquals(fourGoalBoard, ((Stack<Board>) s.solution()).pop());
    }

    @Test
    void solverSolvesThreeBoardOneAway() {
        Board initialBoard = new Board(ExampleBoards.threeBoardOneAway);
        Solver s = new Solver(initialBoard);

        assertTrue(s.isSolvable());
        assertEquals(1, s.moves());

        Stack<Board> solutionBoards = (Stack<Board>) s.solution();
        assertEquals(initialBoard, solutionBoards.pop());
        assertEquals(new Board(ExampleBoards.threeBoardGoal), solutionBoards.pop());
    }

    @Test
    void solverSolvesThreeBoardPuzzleFour() {
        Board initialBoard = new Board(ExampleBoards.threeBoardPuzzleFour);
        Solver s = new Solver(initialBoard);

        assertTrue(s.isSolvable());
        assertEquals(4, s.moves());

        Stack<Board> solutionBoards = (Stack<Board>) s.solution();
        assertEquals(initialBoard, solutionBoards.pop());
        assertEquals(new Board(ExampleBoards.getThreeBoardPuzzleFourStepOne), solutionBoards.pop());
        assertEquals(new Board(ExampleBoards.getThreeBoardPuzzleFourStepTwo), solutionBoards.pop());
        assertEquals(new Board(ExampleBoards.getThreeBoardPuzzleFourStepThree), solutionBoards.pop());
        assertEquals(new Board(ExampleBoards.threeBoardGoal), solutionBoards.pop());
    }

    @Test
    void solverFailsToSolveUnsolvableThreeByThree() {
        Board initialBoard = new Board(ExampleBoards.threeBoardUnsolvable);
        Solver s = new Solver(initialBoard);

        assertFalse(s.isSolvable());
        assertEquals(-1, s.moves());
        assertEquals(null, s.solution());
    }
}
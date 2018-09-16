package com.ivantchernev.algorithms;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    private class SearchNode implements Comparable<SearchNode> {
        public final Board board;
        public final int numberOfMoves;
        public final int priority;
        public final SearchNode predecessor;

        public SearchNode(Board board, int numberOfMoves, SearchNode predecessor) {
            this.board = board;
            this.priority = board.manhattan() + numberOfMoves;
            this.numberOfMoves = numberOfMoves;
            this.predecessor = predecessor;
        }

        @Override
        public int compareTo(SearchNode that) {
            return this.priority - that.priority;
        }
    }

    private final SearchNode solution;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();

        MinPQ<SearchNode> mainPQ = new MinPQ<>();
        MinPQ<SearchNode> twinPQ = new MinPQ<>();

        mainPQ.insert(new SearchNode(initial, 0, null));
        twinPQ.insert(new SearchNode(initial.twin(), 0, null));

        boolean workingOnTwinPq = false;
        MinPQ<SearchNode> workingPQ = mainPQ;
        SearchNode latestSearchNode = workingPQ.delMin();

        while (!latestSearchNode.board.isGoal()) {
            Iterable<Board> neighbourBoards = latestSearchNode.board.neighbors();
            for (Board newBoard : neighbourBoards) {
                if (latestSearchNode.predecessor == null || !newBoard.equals(latestSearchNode.predecessor.board)) {
                    SearchNode newNode = new SearchNode(newBoard,
                            latestSearchNode.numberOfMoves + 1,
                            latestSearchNode);
                    workingPQ.insert(newNode);
                }
            }

            workingOnTwinPq = !workingOnTwinPq;
            workingPQ = workingOnTwinPq ? twinPQ : mainPQ;
            latestSearchNode = workingPQ.delMin();
        }

        solution = workingOnTwinPq ? null : latestSearchNode;
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return solution != null;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return solution == null ? -1 : solution.numberOfMoves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (solution == null) return null;

        Stack<Board> solutionBoards = new Stack<>();
        SearchNode lastMoveNode = solution;

        while (lastMoveNode != null) {
            solutionBoards.push(lastMoveNode.board);
            lastMoveNode = lastMoveNode.predecessor;
        }

        return solutionBoards;
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}

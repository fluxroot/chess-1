package com.es;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.junit.Before;
import org.junit.Test;

import com.es.ai.AlphaBetaAI;
import com.es.ai.MoveAI;
import com.es.ai.MoveNode;
import com.es.ai.NegaScoutAI;
import com.es.pieces.Piece.Color;

public class AlphaBetaAITest {

    Board board = new Board();
    AlphaBetaAI alphaBeta = new AlphaBetaAI(Color.WHITE);
    NegaScoutAI nega = new NegaScoutAI(Color.WHITE);
    MoveAI normal = new MoveAI(Color.WHITE);

    static final int DEPTH = 4;

    @Before
    public void setup() {
        LogManager.getRootLogger().setLevel(Level.DEBUG);
    }

    public void setupBoard() throws IllegalMoveException {
//        board.clearBoard();

        board.makeMove(0x13, 0x33);
/*
        board.addPiece(new Pawn(Color.BLACK), 0x63);
        board.addPiece(new Pawn(Color.BLACK), 0x64);
        board.addPiece(new Queen(Color.BLACK), 0x73);

        board.addPiece(new Pawn(Color.WHITE), 0x13);
//        board.addPiece(new Pawn(Color.WHITE), 0x14);
        board.addPiece(new Queen(Color.WHITE), 0x03);
*/
    }

    @Test
    public void testAlphabeta() throws IllegalMoveException {

        //
        // setup alpha-beta
        //
        setupBoard();
        MoveNode alphaBetaNode = new MoveNode(board, null, new int[] { Board.MAX_SQUARE, Board.MAX_SQUARE });

        long start = System.currentTimeMillis();
        int ret = alphaBeta.alphabeta(alphaBetaNode, DEPTH, -1000000, 10000000, Color.BLACK);
        alphaBetaNode.getBestChild();
        long alphaBetaTime = System.currentTimeMillis() - start;

        System.out.println("RET: " + ret);

        String from = Integer.toHexString(alphaBetaNode.getBestChild().getMove()[0]);
        String to = Integer.toHexString(alphaBetaNode.getBestChild().getMove()[1]);

        System.out.println("MOVE: " + from + " -> " + to);
/*
        //
        // setup negascout
        //
        setupBoard();
        MoveNode negaNode = new MoveNode(board, null, new int[] { Board.MAX_SQUARE, Board.MAX_SQUARE });

        start = System.currentTimeMillis();
        ret = nega.negascout(negaNode, DEPTH, -1000000, 10000000, Color.WHITE);
        negaNode.getBestChild();
        long negaTime = System.currentTimeMillis() - start;

        System.out.println("RET: " + ret);

        //
        // setup normal
        //
        setupBoard();
        MoveNode normalNode = new MoveNode(board, null, new int[] { Board.MAX_SQUARE, Board.MAX_SQUARE });

        start = System.currentTimeMillis();
        normal.computeNextMove(normalNode, Color.WHITE, DEPTH);
        long normalTime = System.currentTimeMillis() - start;
*/
        //
        // print the results
        //
        System.out.println("* ALPHA BETA: " + alphaBetaTime + " " + alphaBetaNode.getNodeCount());
        alphaBetaNode.printChildren();
        printMoves(alphaBetaNode.getBestChild());
        System.out.println();
/*
        System.out.println("* NEGA: " + negaTime);
        negaNode.printChildren();
        printMoves(negaNode.getBestChild());
        System.out.println();

        System.out.println("* NORMAL: " + normalTime + " " + normalNode.getNodeCount());
        normalNode.printChildren();
        printMoves(normalNode.getBestChild());
        System.out.println();
*/
    }

    public void printMoves(MoveNode node) {
        while(true) {
            node.getBoard().printBoard();
            System.out.println("SCORE: " + normal.computeScore(node) + "=" + alphaBeta.computeScore(node) + " DEPTH: " + node.getDepth());

            if(node.getChildCount() == 0)
                break;
            node = node.getChildren().get(0);
            System.out.println();
        }
    }

}

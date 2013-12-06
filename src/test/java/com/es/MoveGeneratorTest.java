package com.es;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import com.es.Board.State;
import com.es.ai.MoveNode;
import com.es.pieces.Piece.Color;
import com.fluxchess.jcpi.models.GenericBoard;
import com.fluxchess.jcpi.models.IllegalNotationException;

public class MoveGeneratorTest {

    private Board board;
    private int moveCount = 1;

    @Test
    public void testPerft() throws IOException {
        File file= new File("perftsuite.epd");

        if(! file.exists()) {
            file = new File("src/test/resources/perftsuite.epd");
        }

        // read in all the lines of the test file
        final List<String> lines = FileUtils.readLines(file);

        //final List<String> lines = Arrays.asList("4k3/8/8/8/8/8/8/R3K2R w Q - 0 1 ;D1 26 ;D2 112 ;D3 3068 ;D4 17945 ;D5 532933 ;D6 2788982");

        final List<String> failedBoards = new ArrayList<String>();

        // i = the depth we're searching
        for (int i = 5; i < 6; i++) {
            moveCount = 1;
            for(String line:lines) {
                String[] tokens = line.split(";");

                // Setup a new board from fen
                GenericBoard genericBoard = null;
                try {
                    genericBoard = new GenericBoard(tokens[0].trim());
                } catch (IllegalNotationException e) {
                    fail("Illegal Notation: " + e.getMessage());
                }

                // make sure we have enough tokens/depth for this run
                if(tokens.length <= i) {
                    continue;
                }

                String[] data = tokens[i].trim().split(" ");
                int depth = Integer.parseInt(data[0].substring(1));
                int numberOfNodes = Integer.parseInt(data[1]);
                Color activeColor = Color.fromGenericColor(genericBoard.getActiveColor());

                // Create a new board
                board = new Board(genericBoard);

                //System.out.println("BOARD: ");
                //System.out.println(board.toString());
                System.out.println("Testing " + tokens[0].trim() + " depth " + depth + " with nodes number " + numberOfNodes);

                // reset our move count before we start
                moveCount = 0;
                
                // compute the move tree
                miniMax(activeColor, depth);

                // Check total moves against database
                // assertEquals(tokens[0].trim(), nodesNumber, result);

                if (numberOfNodes != moveCount) {
                    System.out.println("FAILED FOUND: " + moveCount + " NEEDED: " + numberOfNodes);
                    failedBoards.add(line + " @ depth " + depth + " FOUND: " + moveCount);
                    //fail("FAILED");
                } else {
                    System.out.println("PASSED!");
                }
            }
        }

        for(String failed:failedBoards) {
            System.out.println("FAILED: " + failed);
        }
    }

    private void miniMax(Color color, int depth) {
        if (depth == 0) {
            return;
        }
        
        // generate all the moves
        final int[] allMoves = board.generateAllMoves();

        // go through all the moves, we stop when we see one that is Board.MAX_SQUARE
        for (int i = 0; i < allMoves.length && Board.getFromSquare(allMoves[i]) != Board.MAX_SQUARE; ++i) {
            State boardState = null;
            //final String from = Integer.toHexString(Board.getFromSquare(allMoves[i]));
            //final String to = Integer.toHexString(Board.getToSquare(allMoves[i]));

            try {
                board.checkBoard();
                //System.out.println(moveCount++ + " : " + color + " " + board.moveToStringWithPieces(allMoves[i]) + " (" + board.getEnPassant() + ")");
                boardState = board.makeMove(allMoves[i]);
                board.checkBoard();

                if(depth == 1) {
                    moveCount++;
                    //System.out.println(board.toFEN());
                }

                if (board.getPiece(Board.getToSquare(allMoves[i])) == null) {
                    System.out.println("Never made move");
                    System.out.println(board);
                    fail("NEVER MADE MOVE");
                }

                //System.out.println(board); // print out the board
            } catch (IllegalMoveException e) {
                System.err.println(board);
                e.printStackTrace();
                fail("Illegal Move: " + e.getMessage());
            }

            // make the recursive call
            miniMax(color.inverse(), depth - 1);

            try {
                // System.out.println("UN-MOVE: " + color + " " + from + " -> " + to + " (" + board.getEnPassant() + ")");
                board.unmakeMove(allMoves[i], boardState);
                board.checkBoard();
            } catch (IllegalMoveException e) {
                System.err.println(board);
                e.printStackTrace();
                fail("Illegal Move: " + e.getMessage());
            }
        }
    }

    @Test
    public void testBoardSetup() throws Exception {
        int depth = 2;
        int res = 2042;
        GenericBoard board = new GenericBoard("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1");
        this.board = new Board(board);
        final MoveNode rootNode = new MoveNode();

        System.out.println("BOARD: ");
        System.out.println(this.board.toString());

        // Count all moves
        miniMax(Color.fromGenericColor(board.getActiveColor()), depth);

        assertEquals(res, moveCount);
    }

}

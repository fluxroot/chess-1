package com.es.ai.search;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.math3.util.FastMath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.es.Board;
import com.es.Board.State;
import com.es.CmdConfiguration;
import com.es.IllegalMoveException;
import com.es.ai.MoveNode;
import com.es.ai.evaluate.AbstractEvaluate;
import com.es.pieces.Piece.Color;


public class NegaMaxSearch extends AbstractSearch {

    private static final Logger LOG = LoggerFactory.getLogger(NegaMaxSearch.class);
    
    public NegaMaxSearch(Color colorPlaying, Board board, Configuration configuration, AbstractEvaluate eval) {
        super(colorPlaying, board, configuration, eval);
    }

    @Override
    public MoveNode computeNextMove(MoveNode rootNode) throws IllegalMoveException {
        int ret = negamax(rootNode, configuration.getInt(CmdConfiguration.DEPTH), colorPlaying);

        LOG.debug("RET: {}", ret);
        
        return rootNode;
    }

    private int negamax(MoveNode node, int depth, Color currentPlayer) throws IllegalMoveException {
        if(depth == 0) { // when we reach our depth, evaluate the board
            final int score = -eval.evaluate(board);
            node.setScore(score);
            return score * (currentPlayer.equals(colorPlaying) ? 1 : -1);
        }

        int bestValue = Integer.MIN_VALUE;

        // generate all the moves for the current board
        final int[] moves = board.generateAllMoves();

        // add children for each move
        for(int move:moves) {
            if(move == Board.NULL_MOVE)
                break;

            node.addChild(move);
        }

        // go through the children computing scores
        for(MoveNode child:node.getChildren()) {
            final State state = board.makeMove(child.getMove()); // make this move

            // make the recursive negamax call
            final int value = -negamax(child, depth-1, currentPlayer.inverse());

            // update the best value
            bestValue = FastMath.max(bestValue, value);

            board.unmakeMove(child.getMove(), state); // unmake the move
        }

        // set the score for the node
        node.setScore(bestValue);

        return bestValue;

    }
}

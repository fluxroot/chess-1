package com.es.pieces;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.es.Board;


public class Pawn extends AbstractPiece {

    private static final Logger LOG = LoggerFactory.getLogger(Bishop.class);

    public Pawn(Color color) {
        super(color);
    }

    public String toString() {
        if(getColor().equals(Color.BLACK)) {
            return "p";
        } else {
            return "P";
        }
    }

    public int[] generateAllMoves(Board board, int curPos) {
        int[] ret = new int[4]; // can only move in 4 positions
        int retIndex = 0;

        if(getColor().equals(Color.BLACK)) {
            // straight forward moves
            if((curPos >> 4) == 6 && board.getPiece(curPos - 0x10) == null && board.getPiece(curPos - 0x20) == null) {
                ret[retIndex++] = curPos - 0x20;
            }

            if(curPos - 0x10 >= 0 && board.getPiece(curPos - 0x10) == null) {
                ret[retIndex++] = curPos - 0x10;
            }

            // capture lower-right
            int move = curPos - 0x0f;
            if(move >= 0) {
                Piece p = board.getPiece(move);
                if(p != null && p.getColor().equals(Color.WHITE)) {
                    ret[retIndex++] = move;
                }
            }
            
            // capture lower-left
            move = curPos - 0x11;
            if(move >= 0) {
                Piece p = board.getPiece(move); 
                if(p != null && p.getColor().equals(Color.WHITE)) {
                    ret[retIndex++] = move;
                }
            }
        } else {
            // straight forward moves
            if((curPos >> 4) == 1 && board.getPiece(curPos + 0x10) == null && board.getPiece(curPos + 0x20) == null) {
                ret[retIndex++] = curPos + 0x20;
            }

            if(curPos + 0x10 < Board.MAX_SQUARE && board.getPiece(curPos + 0x10) == null) {
                ret[retIndex++] = curPos + 0x10;
            }

            // capture upper-left
            int move = curPos + 0x0f;
            if(move < Board.MAX_SQUARE) {
                Piece p = board.getPiece(move); 
                if(p != null && p.getColor().equals(Color.BLACK)) {
                    ret[retIndex++] = move;
                }
            }
            
            // capture upper-right
            move = curPos + 0x11;
            if(move < Board.MAX_SQUARE) {
                Piece p = board.getPiece(move);
                if(p != null && p.getColor().equals(Color.BLACK)) {
                    ret[retIndex++] = move;
                }
            }
        }

        Arrays.fill(ret, retIndex, ret.length, Board.MAX_SQUARE);   // fill the rest with -1
        Arrays.sort(ret);   // sort the array

        return ret;
    }
}

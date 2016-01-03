package com.vladbonta.myapplication.model;

import com.vladbonta.myapplication.R;
import com.vladbonta.myapplication.activities.Game;

/**
 * @author VladBonta on 27/12/15.
 */
public class Pawn extends ChessPiece {
    public Pawn(Game game, boolean isWhite, int x, int y) {
        super(game, isWhite, x, y);
        this.setIsEmpty(false);
        setWhiteDrawableImageId(R.drawable.wpawn);
        setBlackDrawableImageId(R.drawable.bpawn);
    }

    @Override
    public boolean isMoveValid(int fromX, int fromY, int toX, int toY) {
        boolean result = false;
        result = super.isMoveValid(fromX, fromY, toX, toY);

        //Add validation for pawn
        return result;
    }
}

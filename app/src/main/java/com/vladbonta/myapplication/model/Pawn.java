package com.vladbonta.myapplication.model;

import com.vladbonta.myapplication.R;

/**
 * @author VladBonta on 27/12/15.
 */
public class Pawn extends ChessPiece {
    public Pawn(boolean isWhite, int x, int y) {
        super(isWhite, x, y);
        this.setIsEmpty(false);
    }

    @Override
    public int getWhiteDrawableImageId() {
        return R.drawable.wpawn;
    }

    @Override
    public int getBlackDrawableImageId() {
        return R.drawable.bpawn;
    }

    @Override
    public boolean isMoveValid(int fromX, int fromY, int toX, int toY) {
        boolean result = false;
        result = super.isMoveValid(fromX, fromY, toX, toY);

        //Add validation for pawn
        return result;
    }
}

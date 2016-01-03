package com.vladbonta.myapplication.model;

import com.vladbonta.myapplication.R;

/**
 * @author VladBonta on 27/12/15.
 */
public class Queen extends ChessPiece {
    public Queen(boolean isWhite, int x, int y) {
        super(isWhite, x, y);
        this.setIsEmpty(false);
    }

    @Override
    public int getWhiteDrawableImageId() {
        return R.drawable.wqueen;
    }

    @Override
    public int getBlackDrawableImageId() {
        return R.drawable.bqueen;
    }

    @Override
    public boolean isMoveValid(int fromX, int fromY, int toX, int toY) {
        boolean result = false;
        result = super.isMoveValid(fromX, fromY, toX, toY);

        //diagonal
        if(toX - fromX != toY - fromY && fromX != toX && fromY != toY)
            result = false;
        return result;
    }
}

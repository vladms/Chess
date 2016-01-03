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
    public boolean isMovePossible(int fromX, int fromY, int toX, int toY) {
        if(super.isMovePossible(fromX, fromY, toX, toY) == false)
            return false;
        //diagonal
        if(Math.abs(toX - fromX) == Math.abs(toY - fromY))
            return true;
        if(toX == fromX)
            return true;
        if(toY == fromY)
            return true;

        return false;
    }
}

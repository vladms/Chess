package com.vladbonta.myapplication.model;

import com.vladbonta.myapplication.R;

/**
 * @author VladBonta on 27/12/15.
 */
public class King extends ChessPiece {
    public King(boolean isWhite, int x, int y){
        super(isWhite, x, y);
        this.setIsEmpty(false);
    }

    @Override
    public int getWhiteDrawableImageId() {
        return R.drawable.wking;
    }

    @Override
    public int getBlackDrawableImageId() {
        return R.drawable.bking;
    }

    @Override
    public boolean isMovePossible(int fromX, int fromY, int toX, int toY){
        boolean result = false;
        result = super.isMovePossible(fromX, fromY, toX, toY);
        int dist2 = (int) (Math.pow((toX - fromX), 2) + Math.pow((toY - fromY), 2));
        if (dist2 != 1 && dist2 != 2){
            result = false;
        }
        return result;
    }
}

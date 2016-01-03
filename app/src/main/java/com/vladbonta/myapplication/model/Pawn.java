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
    public boolean isMovePossible(int fromX, int fromY, int toX, int toY) {
        if (super.isMovePossible(fromX, fromY, toX, toY) == false)
            return false;

        if (fromY == toY && Math.abs(fromX - toX) <= 2) {
            if (this.isWhite()){
                if (fromX > toX)
                    return true;
                else
                    return false;
            } else {
                if (fromX < toX)
                    return true;
                else
                    return false;
            }
        }
        if (Math.abs(fromY - toY) == 1 && Math.abs(fromX - toX) == 1){
            if (this.isWhite()){
                if (fromX > toX)
                    return true;
                else
                    return false;
            } else {
                if (fromX < toX)
                    return true;
                else
                    return false;
            }
        }
        return false;
    }
}

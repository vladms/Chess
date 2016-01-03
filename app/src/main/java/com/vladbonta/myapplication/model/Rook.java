package com.vladbonta.myapplication.model;

import android.util.Log;

import com.vladbonta.myapplication.R;

/**
 * @author VladBonta on 27/12/15.
 */
public class Rook extends ChessPiece {
    public Rook(boolean isWhite, int x, int y) {
        super(isWhite, x, y);
        this.setIsEmpty(false);
    }

    @Override
    public int getWhiteDrawableImageId() {
        return R.drawable.wrook;
    }

    @Override
    public int getBlackDrawableImageId() {
        return R.drawable.brook;
    }

    @Override
    public boolean isMoveValid(int fromX, int fromY, int toX, int toY) {
        boolean result = false;
        result = super.isMoveValid(fromX, fromY, toX, toY);
        Log.d("myTag", String.valueOf(fromX) + " " + String.valueOf(fromY) + " " + String.valueOf(toX) + " " + String.valueOf(toY));

        if(fromX != toX && fromY != toY)
            result = false;
        return result;
    }
}

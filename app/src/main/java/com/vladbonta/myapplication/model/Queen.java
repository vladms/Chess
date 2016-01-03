package com.vladbonta.myapplication.model;

import com.vladbonta.myapplication.R;
import com.vladbonta.myapplication.activities.Game;

/**
 * @author VladBonta on 27/12/15.
 */
public class Queen extends ChessPiece {
    public Queen(Game game, boolean isWhite, int x, int y) {
        super(game, isWhite, x, y);
        this.setIsEmpty(false);
        setWhiteDrawableImageId(R.drawable.wqueen);
        setBlackDrawableImageId(R.drawable.bqueen);
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

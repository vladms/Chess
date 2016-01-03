package com.vladbonta.myapplication.model;

import com.vladbonta.myapplication.R;
import com.vladbonta.myapplication.activities.Game;

/**
 * @author VladBonta on 27/12/15.
 */
public class Knight extends ChessPiece {
    public Knight(Game game, boolean isWhite, int x, int y){
        super(game, isWhite, x, y);
        this.setIsEmpty(false);
        setWhiteDrawableImageId(R.drawable.wknight);
        setBlackDrawableImageId(R.drawable.bknight);
    }

    @Override
    public boolean isMoveValid(int fromX, int fromY, int toX, int toY) {
        boolean result = false;
        result = super.isMoveValid(fromX, fromY, toX, toY);
        if(toX != fromX - 1 && toX != fromX + 1 && toX != fromX + 2 && toX != fromX - 2)
            result = false;
        if(toY != fromY - 2 && toY != fromY + 2 && toY != fromY - 1 && toY != fromY + 1)
            result = false;
        return result;
    }
}

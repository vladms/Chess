package com.vladbonta.myapplication.model;

import com.vladbonta.myapplication.R;
import com.vladbonta.myapplication.activities.Game;

/**
 * @author VladBonta on 27/12/15.
 */
public class Bishop extends ChessPiece {


    public Bishop(Game game, boolean isWhite, int x, int y){
        super(game, isWhite, x, y);

        setWhiteDrawableImageId(R.drawable.wbishop);
        setBlackDrawableImageId(R.drawable.bbishop);
    }

    @Override
    public boolean isMoveValid(int fromX, int fromY, int toX, int toY) {
        boolean result = false;
        result = super.isMoveValid(fromX, fromY, toX, toY);

        if(toX - fromX != toY - fromY)
            result = false;
        return result;
    }
}

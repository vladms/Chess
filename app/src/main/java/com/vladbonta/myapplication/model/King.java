package com.vladbonta.myapplication.model;

import com.vladbonta.myapplication.R;
import com.vladbonta.myapplication.activities.Game;

/**
 * @author VladBonta on 27/12/15.
 */
public class King extends ChessPiece {
    public King(Game game, boolean isWhite, int x, int y){
        super(game, isWhite, x, y);
        this.setIsEmpty(false);
        setWhiteDrawableImageId(R.drawable.wking);
        setBlackDrawableImageId(R.drawable.bking);
    }
    @Override
    public boolean isMoveValid(int fromX, int fromY, int toX, int toY){
        boolean result = false;
        result = super.isMoveValid(fromX, fromY, toX, toY);
        int dist2 = (int) (Math.pow((toX - fromX), 2) + Math.pow((toY - fromY), 2));
        if (dist2 != 1 && dist2 != 2){
            result = false;
        }
        return result;
    }
}

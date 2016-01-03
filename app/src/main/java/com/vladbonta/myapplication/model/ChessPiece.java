package com.vladbonta.myapplication.model;

/**
 * @author VladBonta on 23/12/15.
 */

public abstract class ChessPiece {
    private int x;
    private int y;
    private boolean isWhite;
    private boolean isEmpty;
    private boolean isSelected;

    public ChessPiece(boolean isWhite, int x, int y){
        super();
        this.isEmpty = true;
        this.isWhite = isWhite;
        this.x = x;
        this.y = y;
    }

    public boolean isMoveValid(int fromX, int fromY, int toX, int toY){
        boolean result = true;
        if(toX == fromX && toY == fromY)
            result = false;
        if(toX <= 0 || toX > 8 || fromX <= 0 || fromX > 8 || toY <= 0 || toY > 8 || fromY <= 0 || fromY > 8)
            result =  false;
        return result;

    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setIsWhite(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public abstract int getWhiteDrawableImageId();

    public abstract int getBlackDrawableImageId();

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

}

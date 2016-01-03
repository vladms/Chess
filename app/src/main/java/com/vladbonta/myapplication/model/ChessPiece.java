package com.vladbonta.myapplication.model;

import android.util.Log;
import android.view.View;

import com.vladbonta.myapplication.activities.Game;

/**
 * @author VladBonta on 23/12/15.
 */

public class ChessPiece implements View.OnClickListener{
    private int x;
    private int y;
    private boolean isWhite;
    private boolean isEmpty;
    private Game game;
    private int whiteDrawableImageId;
    private int blackDrawableImageId;
    private boolean isSelected;


    /*public ChessPiece(Game game) {
        super();
        this.game = game;
        this.isEmpty = true;
        this.isWhite = false;
        this.x = 0;
        this.y = 0;

    }*/

    public ChessPiece(Game game, boolean isWhite, int x, int y){
        super();
        this.isEmpty = true;
        this.game = game;
        this.isWhite = isWhite;
        this.x = x;
        this.y = y;
    }

    @Override
    public void onClick(View view) {
        Log.d("myTag", String.valueOf(view));

        Log.d("myTag", String.valueOf(getX()) + " " + String.valueOf(getY()));
        this.game.handlePieceTouch(this);
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

    public int getWhiteDrawableImageId() {
        return whiteDrawableImageId;
    }

    public void setWhiteDrawableImageId(int whiteDrawableImageId) {
        this.whiteDrawableImageId = whiteDrawableImageId;
    }

    public int getBlackDrawableImageId() {
        return blackDrawableImageId;
    }

    public void setBlackDrawableImageId(int blackDrawableImageId) {
        this.blackDrawableImageId = blackDrawableImageId;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

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

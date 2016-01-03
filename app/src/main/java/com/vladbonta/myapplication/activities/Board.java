package com.vladbonta.myapplication.activities;

import com.vladbonta.myapplication.model.ChessPiece;

import java.util.ArrayList;

/**
 * @author VladBonta on 27/12/15.
 */
public class Board {
    private ChessPiece emptyChessPiece;
    private ArrayList<ChessPiece> pieces;

    public Board() {
        super();
    }

    public void clearPieceAtPosition(int x, int y){
        emptyChessPiece.setX(x);
        emptyChessPiece.setY(y);
        int pieceIndex = (x - 1) * 8 + (y - 1);
        pieces.remove(pieceIndex);
        pieces.add(pieceIndex, emptyChessPiece);
    }

    public void changePieceAtPosition(int x, int y, ChessPiece chessPiece){
        int pieceIndex = (x - 1) * 8 + (y - 1);
        pieces.remove(pieceIndex);
        chessPiece.setX(x);
        chessPiece.setY(y);
        pieces.add(pieceIndex, chessPiece);
    }

    public ArrayList<ChessPiece> getPieces(){
        return pieces;
    }

}
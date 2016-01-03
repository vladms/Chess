package com.vladbonta.myapplication.activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.vladbonta.myapplication.model.Bishop;
import com.vladbonta.myapplication.model.ChessPiece;
import com.vladbonta.myapplication.model.King;
import com.vladbonta.myapplication.model.Knight;
import com.vladbonta.myapplication.model.Pawn;
import com.vladbonta.myapplication.model.Queen;
import com.vladbonta.myapplication.model.Rook;

import java.util.ArrayList;

/**
 * @author VladBonta on 27/12/15.
 */
public class Game {
    private Board board = new Board();
    private TilesAdapter mTilesAdapter;
    private RecyclerView mRecyclerView;
    private ChessPiece lastPressedPiece;
    private ChessPiece emptyChessPiece;
    ArrayList<ChessPiece> pieces;
    public Game(RecyclerView recyclerView, Context context) {
        lastPressedPiece = null;
        emptyChessPiece = new ChessPiece(this, false, 0, 0);
        mRecyclerView = recyclerView;
        mTilesAdapter = new TilesAdapter(context);
        addPiecesOnBoard();
        updateBoard();

    }

    public void addPiecesOnBoard() {
        pieces = new ArrayList<>();
        ChessPiece piece = new ChessPiece(this, false, 0, 0);
        pieces.add(new Rook(this, false, 1, 1));
        pieces.add(new Knight(this, false, 1, 2));
        pieces.add(new Bishop(this, false, 1, 3));
        pieces.add(new Queen(this, false, 1, 4));
        pieces.add(new King(this, false, 1, 5));
        pieces.add(new Bishop(this, false, 1, 6));
        pieces.add(new Knight(this, false, 1, 7));
        pieces.add(new Rook(this, false, 1, 8));
        for(int i = 1;i <= 8; i++){
            pieces.add(new Pawn(this, false, 2, i));
        }
        for(int i = 0;i < 32; i++){
            pieces.add(new ChessPiece(this, false, 3 + i / 8, 1 + i % 8));
        }
        for(int i = 1;i <= 8; i++){
            pieces.add(new Pawn(this, true, 7, i));
        }
        pieces.add(new Rook(this, true, 8, 1));
        pieces.add(new Knight(this, true, 8, 2));
        pieces.add(new Bishop(this, true, 8, 3));
        pieces.add(new Queen(this, true, 8, 4));
        pieces.add(new King(this, true, 8, 5));
        pieces.add(new Bishop(this, true, 8, 6));
        pieces.add(new Knight(this, true, 8, 7));
        pieces.add(new Rook(this, true, 8, 8));
    }

    public void updateBoard(){
        mTilesAdapter.setChessPieces(pieces);
        mRecyclerView.setAdapter(mTilesAdapter);

    }

    public void handlePieceTouch(ChessPiece chessPiece) {
        Log.d("myTag", String.valueOf(chessPiece));




        if (lastPressedPiece == null){
            if (!chessPiece.isEmpty()){
                chessPiece.setIsSelected(true);
                lastPressedPiece = chessPiece;
                Log.d("myTag", "Prima piessa atinsa");
            }
        } else if (lastPressedPiece.equals(chessPiece)){
            Log.d("myTag", "Aceeasi piesa");
            chessPiece.setIsSelected(false);
            lastPressedPiece = null;
        } else {
            if (chessPiece.isWhite() != lastPressedPiece.isWhite()) {
                if (lastPressedPiece.isMoveValid(lastPressedPiece.getX(), lastPressedPiece.getY(), chessPiece.getX(), chessPiece.getY())) {
                    lastPressedPiece.setIsSelected(false);
                    Log.d("lastPressedPiece", String.valueOf(lastPressedPiece.getX()) + " " + String.valueOf(lastPressedPiece.getY()));
                    Log.d("chessPiece", String.valueOf(chessPiece.getX()) + " " + String.valueOf(chessPiece.getY()));
                    clearPieceAtPosition(lastPressedPiece.getX(), lastPressedPiece.getY());
                    Log.d("lastPressedPiece", String.valueOf(lastPressedPiece.getX()) + " " + String.valueOf(lastPressedPiece.getY()));
                    Log.d("chessPiece", String.valueOf(chessPiece.getX()) + " " + String.valueOf(chessPiece.getY()));

                    changePieceAtPosition(chessPiece.getX(), chessPiece.getY(), lastPressedPiece);
                    Log.d("lastPressedPiece", String.valueOf(lastPressedPiece.getX()) + " " + String.valueOf(lastPressedPiece.getY()));
                    Log.d("chessPiece", String.valueOf(chessPiece.getX()) + " " + String.valueOf(chessPiece.getY()));

                    lastPressedPiece = null;
                } else {
                }
            }
            Log.d("myTag", "piese diferite");

        }
        updateBoard();
    }

    private void clearPieceAtPosition(int x, int y){
        emptyChessPiece = new ChessPiece(this, false, x, y);


        int pieceIndex = (x - 1) * 8 + (y - 1);
        Log.d("myTag", String.valueOf(pieces));

        pieces.remove(pieceIndex);
        pieces.add(pieceIndex, emptyChessPiece);
    }

    private void changePieceAtPosition(int x, int y, ChessPiece chessPiece){
        int pieceIndex = (x - 1) * 8 + (y - 1);
        chessPiece.setX(x);
        chessPiece.setY(y);
        pieces.remove(pieceIndex);
        pieces.add(pieceIndex, chessPiece);
    }

        public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}

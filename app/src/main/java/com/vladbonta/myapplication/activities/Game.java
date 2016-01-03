package com.vladbonta.myapplication.activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.vladbonta.myapplication.model.ChessPiece;

import java.util.ArrayList;

/**
 * @author VladBonta on 27/12/15.
 */
public class Game {
    private Board board = new Board(this);
    private TilesAdapter mTilesAdapter;
    private RecyclerView mRecyclerView;
    private ChessPiece lastPressedPiece;
    private boolean whitePlayerTurn;
    ArrayList<ChessPiece> pieces;
    public Game(RecyclerView recyclerView, Context context) {
        lastPressedPiece = null;
        whitePlayerTurn = true;
        mRecyclerView = recyclerView;
        mTilesAdapter = new TilesAdapter(context, board);
        mRecyclerView.setAdapter(mTilesAdapter);
        updateBoard();
    }


    public void updateBoard(){
        mTilesAdapter.setChessPieces(board.getPieces());
        mTilesAdapter.notifyDataSetChanged();
    }

    public void handlePieceTouch(ChessPiece chessPiece) {

        Log.d("myTag", String.valueOf(chessPiece) + "handlePieceTouch");

        if (lastPressedPiece == null){
            Log.d("myTag", "lastPressedPiece == null");
            if (!chessPiece.isEmpty() && selectedPieceBelongsToPlayingUser(chessPiece)){
                chessPiece.setIsSelected(true);
                lastPressedPiece = chessPiece;
                Log.d("myTag", "Prima piessa atinsa");
            }
        } else if (lastPressedPiece.equals(chessPiece)){
            Log.d("myTag", "Aceeasi piesa");
            chessPiece.setIsSelected(false);
            lastPressedPiece = null;
        } else {
            Log.d("myTag", "piese diferite");
            if (chessPiece.isWhite() != lastPressedPiece.isWhite() || chessPiece.isEmpty()) {
                Log.d("myTag", "Here1");

                if (lastPressedPiece.isMovePossible(lastPressedPiece.getX(), lastPressedPiece.getY(), chessPiece.getX(), chessPiece.getY())) {
                    Log.d("myTag", "here2");
                    int xChessPiece = chessPiece.getX();
                    int yChessPiece = chessPiece.getY();
                    lastPressedPiece.setIsSelected(false);
                   // Log.d("lastPressedPiece", String.valueOf(lastPressedPiece.getX()) + " " + String.valueOf(lastPressedPiece.getY()));
                  //  Log.d("chessPiece", String.valueOf(chessPiece.getX()) + " " + String.valueOf(chessPiece.getY()));
                    board.clearPieceAtPosition(lastPressedPiece.getX(), lastPressedPiece.getY());
                   // Log.d("lastPressedPiece", String.valueOf(lastPressedPiece.getX()) + " " + String.valueOf(lastPressedPiece.getY()));
                  //  Log.d("chessPiece", String.valueOf(chessPiece.getX()) + " " + String.valueOf(chessPiece.getY()));
                   // Log.d("lastPressedPiece", String.valueOf(lastPressedPiece.getX()) + " " + String.valueOf(lastPressedPiece.getY()));
                  //  Log.d("coordinates chessPiece", String.valueOf(xChessPiece) + " " + String.valueOf(yChessPiece));
                    board.changePieceAtPosition(xChessPiece, yChessPiece, lastPressedPiece);
                  //  Log.d("lastPressedPiece", String.valueOf(lastPressedPiece.getX()) + " " + String.valueOf(lastPressedPiece.getY()));
                  //  Log.d("chessPiece", String.valueOf(chessPiece.getX()) + " " + String.valueOf(chessPiece.getY()));
                    whitePlayerTurn = !whitePlayerTurn;
                    lastPressedPiece = null;
                } else {
                    Log.d("myTag", "here3");
                }
            } else {
                Log.d("myTag", "here4");

            }

        }
        updateBoard();
    }


    private boolean selectedPieceIsValid(ChessPiece chessPiece){
        boolean result = false;

        return result;
    }
    private boolean selectedPieceBelongsToPlayingUser(ChessPiece pressedChessPiece){
        boolean result = false;
        Log.d("myTag", "belongsToPlayinguser");
        Log.d("myTag", String.valueOf(whitePlayerTurn));


        if (pressedChessPiece.isWhite() == whitePlayerTurn){
            result = true;
        }
        Log.d("myTag", "result == " + String.valueOf(result));

        return result;
    }


    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}

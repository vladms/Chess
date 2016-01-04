package com.vladbonta.myapplication.activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.vladbonta.myapplication.model.ChessPiece;
import com.vladbonta.myapplication.model.King;
import com.vladbonta.myapplication.model.Rook;

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

       // Log.d("handlePieceTouch", String.valueOf(chessPiece) + "handlePieceTouch");

        if (lastPressedPiece == null){
            //validate selected piece to move
            Log.d("handlePieceTouch", "Validate selected piece to move");

            if (!chessPiece.isEmpty() && selectedPieceBelongsToPlayingUser(chessPiece)){
                chessPiece.setIsSelected(true);
                lastPressedPiece = chessPiece;
                Log.d("handlePieceTouch", "Moving piece selected");
            }
        } else if (lastPressedPiece.equals(chessPiece)){
            Log.d("handlePieceTouch", "Pressed the same piece");
            chessPiece.setIsSelected(false);
            lastPressedPiece = null;
        } else {
            //Validate the next position for selected piece

            //Next position is valid if it is an empty place or a different color piece
            if (chessPiece.isWhite() != lastPressedPiece.isWhite() || chessPiece.isEmpty()) {
                Log.d("handlePieceTouch", "Next position is empty or the other player piece");
                //Verify it is a possible move for given chessPiece
                if (lastPressedPiece.isMovePossible(lastPressedPiece.getX(), lastPressedPiece.getY(), chessPiece.getX(), chessPiece.getY())) {
                    Log.d("handlePieceTouch", "Possible move for chessPiece: " + String.valueOf(chessPiece));
                    if (chessPiece.getClass() == King.class){
                        chessPiece.setMoved(true);
                    }
                    //Check for
                    //Validate movement for chess cases
                    movePieceAtPosition(lastPressedPiece, chessPiece.getX(), chessPiece.getY());
                    whitePlayerTurn = !whitePlayerTurn;
                } else {
                    Log.d("handlePieceTouch", "Piece move is not possible");
                    //Check for king "rocada" movement
                    if (lastPressedPiece.getClass() == King.class && !lastPressedPiece.isMoved()){
                        checkForRocada(chessPiece.getX(), chessPiece.getY());
                    }
                }
            } else {
                Log.d("handlePieceTouch", "Next position is not valid: not empty or not the other player piece");
            }

        }
        updateBoard();
    }

    public void movePieceAtPosition(ChessPiece chessPiece, int xNextPosition, int yNextPosition){
        lastPressedPiece.setIsSelected(false);
        board.clearPieceAtPosition(chessPiece.getX(), chessPiece.getY());
        board.changePieceAtPosition(xNextPosition, yNextPosition, chessPiece);
        lastPressedPiece = null;
        // Log.d("lastPressedPiece", String.valueOf(lastPressedPiece.getX()) + " " + String.valueOf(lastPressedPiece.getY()));
        //  Log.d("coordinates chessPiece", String.valueOf(xChessPiece) + " " + String.valueOf(yChessPiece));
    }

    private void checkForRocada(int x, int y){
        if (x == lastPressedPiece.getX() && y == lastPressedPiece.getY() + 2){
            int index = (lastPressedPiece.getX() - 1) * 8 + (lastPressedPiece.getY() - 1);
            if (board.getPieces().get(index + 1).isEmpty() && board.getPieces().get(index + 2). isEmpty()) {
                if (board.getPieces().get(index + 3).getClass() == Rook.class) {
                    makeRightRocada(index + 3);
                }
            }
        } else if (x == lastPressedPiece.getX() && y == lastPressedPiece.getY() - 2){
            int index = (lastPressedPiece.getX() - 1) * 8 + (lastPressedPiece.getY() - 1);
            if (board.getPieces().get(index  - 1).isEmpty() && board.getPieces().get(index - 2). isEmpty()  && board.getPieces().get(index - 3). isEmpty()) {
                if (board.getPieces().get(index - 4).getClass() == Rook.class) {
                    makeLeftRocada(index - 4);
                }
            }
        }
    }

    private void makeRightRocada(int rookIndex){
        lastPressedPiece.setMoved(true);
        movePieceAtPosition(lastPressedPiece, lastPressedPiece.getX(), lastPressedPiece.getY() + 2);
        //get ROOK piece
        lastPressedPiece = board.getPieces().get(rookIndex);
        movePieceAtPosition(lastPressedPiece, lastPressedPiece.getX(), lastPressedPiece.getY() - 2);
        whitePlayerTurn = !whitePlayerTurn;
    }

    private void makeLeftRocada(int rookIndex){
        lastPressedPiece.setMoved(true);
        movePieceAtPosition(lastPressedPiece, lastPressedPiece.getX(), lastPressedPiece.getY() - 2);
        //get ROOK piece
        lastPressedPiece = board.getPieces().get(rookIndex);
        movePieceAtPosition(lastPressedPiece, lastPressedPiece.getX(), lastPressedPiece.getY()  + 3);
        whitePlayerTurn = !whitePlayerTurn;
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

package com.vladbonta.myapplication.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.vladbonta.myapplication.model.ChessPiece;
import com.vladbonta.myapplication.model.King;
import com.vladbonta.myapplication.model.Rook;

import java.util.ArrayList;

/**
 * @author VladBonta on 27/12/15.
 */
//TO DO - Add logic for pawn attack, paawn transformation, checkmate, structure validation in classes, structure project in packages
public class Game {
    private Board board = new Board(this);
    private Board testBoard = new Board();

    private TilesAdapter mTilesAdapter;
    private RecyclerView mRecyclerView;
    private ChessPiece lastPressedPiece;
    private boolean whitePlayerTurn;
    private Context mContext;


    ArrayList<ChessPiece> pieces;

    public Game(RecyclerView recyclerView, Context context) {
        mContext =  context;
        lastPressedPiece = null;
        whitePlayerTurn = true;
        mRecyclerView = recyclerView;
        mTilesAdapter = new TilesAdapter(context, board);
        mRecyclerView.setAdapter(mTilesAdapter);
        updateBoard();
    }

    public void updateBoard(){
        copyTestToGoodPieces();
        mTilesAdapter.setChessPieces(board.getPieces());
        mTilesAdapter.notifyDataSetChanged();
    }

    private void copyGoodToTestPieces(){
        ArrayList<ChessPiece> pieces = board.getPieces();
        ArrayList<ChessPiece> tempPieces = new ArrayList<ChessPiece>();
        for (int i = 0;i < pieces.size();i++){
            ChessPiece chessPiece = pieces.get(i);
            tempPieces.add(chessPiece);
        }
        testBoard.setPieces(tempPieces);

    }

    private void copyTestToGoodPieces(){
        ArrayList<ChessPiece> pieces = testBoard.getPieces();
        ArrayList<ChessPiece> tempPieces = new ArrayList<ChessPiece>();
        for (int i = 0;i < pieces.size();i++){
            ChessPiece chessPiece = pieces.get(i);
            tempPieces.add(chessPiece);
        }
        board.setPieces(tempPieces);
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
                updateBoard();
            }
        } else if (lastPressedPiece.equals(chessPiece)){
            Log.d("handlePieceTouch", "Pressed the same piece");
            chessPiece.setIsSelected(false);
            lastPressedPiece = null;
            updateBoard();
        } else {
            //Validate the next position for selected piece
            copyGoodToTestPieces();
           // testBoard.setPieces(board.getPieces());
            boolean clearPathForSelectedPiece = true;
            ArrayList<Integer> possiblePositions = lastPressedPiece.getPossibleMovesList(lastPressedPiece.getX(), lastPressedPiece.getY(), chessPiece.getX(), chessPiece.getY());
            for (int index = 0; index < possiblePositions.size(); index++){
                Integer positionIndex = possiblePositions.get(index);
                ChessPiece piece = testBoard.getPieces().get(positionIndex);
                if (!piece.isEmpty()){
                    clearPathForSelectedPiece = false;
                }
            }
            if (clearPathForSelectedPiece) {
                //Next position is valid if it is an empty place or a different color piece
                if (chessPiece.isWhite() != lastPressedPiece.isWhite() || chessPiece.isEmpty()) {
                    Log.e("Check", "8");
                    Log.d("handlePieceTouch", "Next position is empty or the other player piece");
                    //Verify it is a possible move for given chessPiece
                    if (lastPressedPiece.isMovePossible(lastPressedPiece.getX(), lastPressedPiece.getY(), chessPiece.getX(), chessPiece.getY())) {
                        Log.d("handlePieceTouch", "Possible move for chessPiece: " + String.valueOf(chessPiece));
                        //Validate movement for chess cases
                        movePieceAtPosition(false, lastPressedPiece, chessPiece.getX(), chessPiece.getY());
                        Log.d("handlePieceTouch", "Check at black: " + String.valueOf(isCheckAtBlack()) + "Check At white: " + String.valueOf(isCheckAtWhite()) + "lastpressedpieceisWhite: " + String.valueOf(lastPressedPiece.isWhite()));
                        Boolean isCheckAtWhite = isCheckAtWhite();
                        Boolean isCheckAtBlack = isCheckAtBlack();
                        if (whitePlayerTurn){
                            if (isCheckAtWhite){
                                showDialog("Check", "YOUR KING is in check!");
                                lastPressedPiece = null;
                                copyGoodToTestPieces();
                                // testBoard.setPieces(board.getPieces());
                                updateBoard();
                            } else if (isCheckAtBlack) {
                                showDialog("Check", "White made a check!");
                                chessPiece.setMoved(true);
                                whitePlayerTurn = !whitePlayerTurn;
                                lastPressedPiece = null;
                                updateBoard();
                            } else {
                                chessPiece.setMoved(true);
                                whitePlayerTurn = !whitePlayerTurn;
                                lastPressedPiece = null;
                                updateBoard();
                            }
                        } else {
                            if (isCheckAtBlack){
                                showDialog("Check", "YOUR KING is in check!");
                                lastPressedPiece = null;
                                copyGoodToTestPieces();
                                // testBoard.setPieces(board.getPieces());
                                updateBoard();
                            } else if (isCheckAtWhite) {
                                showDialog("Check", "Black made a check!");
                                chessPiece.setMoved(true);
                                whitePlayerTurn = !whitePlayerTurn;
                                lastPressedPiece = null;
                                updateBoard();
                            } else {
                                chessPiece.setMoved(true);
                                whitePlayerTurn = !whitePlayerTurn;
                                lastPressedPiece = null;
                                updateBoard();
                            }
                        }
                    } else {
                        Log.d("handlePieceTouch", "Piece move is not possible");
                        //Check for king "rocada" movement
                        if (lastPressedPiece.getClass() == King.class && !lastPressedPiece.isMoved()) {
                            checkForRocada(chessPiece.getX(), chessPiece.getY());
                            if ((whitePlayerTurn && isCheckAtWhite()) || (!whitePlayerTurn && isCheckAtBlack())) {
                                showDialog("Check", "King is in check!");
                                copyGoodToTestPieces();
                            } else {
                                lastPressedPiece = null;
                                updateBoard();
                            }
                        }
                    }
                } else {
                    Log.d("handlePieceTouch", "Next position is not valid: not empty or not the other player piece");
                }
            } else {
                Log.d("handlePieceTouch", "Path is not clear");
            }

        }
    }

    public boolean isCheckAtWhite(){
        ArrayList<ChessPiece> pieces = testBoard.getPieces();
        boolean result = false;
        ChessPiece whiteKing = null;
        for (int index = 0; index < pieces.size(); index++) {
            ChessPiece chessPiece = pieces.get(index);
            if (chessPiece.isWhite() && chessPiece.getClass() == King.class){
                whiteKing = chessPiece;
            }
        }
        for (int index = 0; index < pieces.size(); index++){
            ChessPiece chessPiece = pieces.get(index);
            if (!chessPiece.isWhite()){
                boolean clearPathForSelectedPiece = true;
                ArrayList<Integer> possiblePositions = chessPiece.getPossibleMovesList(chessPiece.getX(), chessPiece.getY(), whiteKing.getX(), whiteKing.getY());
                for (int index2 = 0; index2 < possiblePositions.size(); index2++){
                    Integer positionIndex = possiblePositions.get(index2);
                    ChessPiece piece = testBoard.getPieces().get(positionIndex);
                    if (!piece.isEmpty()){
                        clearPathForSelectedPiece = false;
                    }
                }
                //Check not to jump over pieces and pawn cases and king cases
                if (clearPathForSelectedPiece && chessPiece.isMovePossible(chessPiece.getX(), chessPiece.getY(), whiteKing.getX(), whiteKing.getY())){
                    result = true;
                }
            }
        }
        return result;
    }

    public boolean isCheckAtBlack(){
        ArrayList<ChessPiece> pieces = testBoard.getPieces();
        boolean result = false;
        ChessPiece blackKing = null;
        for (int index = 0; index < pieces.size(); index++) {
            ChessPiece chessPiece = pieces.get(index);
            if (!chessPiece.isWhite() && chessPiece.getClass() == King.class){
                blackKing = chessPiece;
            }
        }
        for (int index = 0; index < pieces.size(); index++){
            ChessPiece chessPiece = pieces.get(index);
            if (chessPiece.isWhite()){
                boolean clearPathForSelectedPiece = true;
                ArrayList<Integer> possiblePositions = chessPiece.getPossibleMovesList(chessPiece.getX(), chessPiece.getY(), blackKing.getX(), blackKing.getY());
                for (int index2 = 0; index2 < possiblePositions.size(); index2++){
                    Integer positionIndex = possiblePositions.get(index2);
                    ChessPiece piece = testBoard.getPieces().get(positionIndex);
                    if (!piece.isEmpty()){
                        clearPathForSelectedPiece = false;
                    }
                }
                //Check not to jump over pieces and pawn cases and king cases
                if (clearPathForSelectedPiece && chessPiece.isMovePossible(chessPiece.getX(), chessPiece.getY(), blackKing.getX(), blackKing.getY())){
                    result = true;
                }
            }
        }
        return result;
    }

    public boolean checkForCheckmate(){
        return false;
    }

    public void movePieceAtPosition(boolean deleteLastPressedPiece,ChessPiece chessPiece, int xNextPosition, int yNextPosition){
        Log.e("NextPosition", "x:"+ xNextPosition + "y:"+yNextPosition);
        Log.e("lastPressedPiece", "x:"+ lastPressedPiece.getX()+ "y:"+lastPressedPiece.getY());
        lastPressedPiece.setIsSelected(false);
        testBoard.clearPieceAtPosition(chessPiece.getX(), chessPiece.getY());
        testBoard.changePieceAtPosition(xNextPosition, yNextPosition, chessPiece);
        if (deleteLastPressedPiece)
            lastPressedPiece = null;
    }

    private void checkForRocada(int x, int y){
        //Check if king will not be in check on rocada moves
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
        movePieceAtPosition(false, lastPressedPiece, lastPressedPiece.getX(), lastPressedPiece.getY() + 2);
        //get ROOK piece
        lastPressedPiece = board.getPieces().get(rookIndex);
        movePieceAtPosition(false, lastPressedPiece, lastPressedPiece.getX(), lastPressedPiece.getY() - 2);
    }

    private void makeLeftRocada(int rookIndex){
        lastPressedPiece.setMoved(true);
        movePieceAtPosition(false, lastPressedPiece, lastPressedPiece.getX(), lastPressedPiece.getY() - 2);
        //get ROOK piece
        lastPressedPiece = board.getPieces().get(rookIndex);
        movePieceAtPosition(false, lastPressedPiece, lastPressedPiece.getX(), lastPressedPiece.getY()  + 3);
    }

    private boolean selectedPieceIsValid(ChessPiece chessPiece){
        boolean result = false;

        return result;
    }

    public void showDialog(String title, String content){
        AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(content);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private boolean selectedPieceBelongsToPlayingUser(ChessPiece pressedChessPiece){
        boolean result = false;
        Log.d("myTag", "belongsToPlayinguser");
        if (pressedChessPiece.isWhite() == whitePlayerTurn){
            result = true;
        }
        return result;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}

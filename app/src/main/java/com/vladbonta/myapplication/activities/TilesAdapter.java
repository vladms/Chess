package com.vladbonta.myapplication.activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.vladbonta.myapplication.R;
import com.vladbonta.myapplication.model.ChessPiece;

import java.util.ArrayList;

/**
 * @author VladBonta on 23/12/15.
 */
public class TilesAdapter extends RecyclerView.Adapter<TilesAdapter.BaseViewHolder>{

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<ChessPiece> mChessPieces;
    private int S_MAX_COL = 8;
    private int S_MAX_ROW = 8;
    private int S_PIECE_VIEW_TYPE = 111;
    private ArrayList<Integer> S_BLACKS;
    private Board board;

    public TilesAdapter(Context context, Board board) {
        this.board = board;
        mContext = context;
        S_BLACKS = new ArrayList<>();
        S_BLACKS.add(1);
        S_BLACKS.add(3);
        S_BLACKS.add(5);
        S_BLACKS.add(7);
        S_BLACKS.add(8);
        S_BLACKS.add(10);
        S_BLACKS.add(12);
        S_BLACKS.add(14);

    }

    @Override
    public int getItemCount() {
        return S_MAX_COL * S_MAX_ROW;
    }

    @Override
    public int getItemViewType(int position) {
        return S_PIECE_VIEW_TYPE;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == S_PIECE_VIEW_TYPE){
            View convertView = getInflater(parent.getContext()).inflate(R.layout.tile_layout, parent, false);
            return new ChessPieceViewHolder(convertView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if(holder instanceof ChessPieceViewHolder){
            setupPieceView((ChessPieceViewHolder) holder, position);
        }
    }

    private void setupPieceView(ChessPieceViewHolder holder, int position){
        ChessPiece chessPiece = getItem(position);
        if(chessPiece!=null) {
            if (S_BLACKS.contains(position % 16)) {
                holder.mBackgroundView.setBackgroundColor(mContext.getResources().getColor(android.R.color.black));
            } else {
                holder.mBackgroundView.setBackgroundColor(mContext.getResources().getColor(android.R.color.white));
            }
            if (chessPiece.isSelected()){
                holder.mPieceImageView.setBackgroundResource(R.drawable.selected);
            }
            if (chessPiece.isWhite()){
                holder.mPieceImageView.setImageResource(chessPiece.getWhiteDrawableImageId());
            } else {
                holder.mPieceImageView.setImageResource(chessPiece.getBlackDrawableImageId());
            }
            holder.mBackgroundView.setOnClickListener(board);

        }
    }


    private LayoutInflater getInflater(Context context) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(context);
        }
        return mInflater;
    }

    private ChessPiece getItem(int position){
        return mChessPieces.get(position);
    }

    public ArrayList<ChessPiece> getChessPieces() {
        return mChessPieces;
    }

    public void setChessPieces(ArrayList<ChessPiece> chessPieces) {
        mChessPieces = chessPieces;
    }

    protected static class BaseViewHolder extends RecyclerView.ViewHolder{

        public BaseViewHolder(View itemView) {
            super(itemView);
        }
    }

    private static class ChessPieceViewHolder extends BaseViewHolder{

        private View mBackgroundView;
        private ImageView mPieceImageView;

        public ChessPieceViewHolder(View itemView) {
            super(itemView);
            mBackgroundView = itemView.findViewById(R.id.whiteOrBlackView);
            mPieceImageView = (ImageView) itemView.findViewById(R.id.pieceImageView);
        }
    }

}

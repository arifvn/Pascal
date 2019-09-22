package com.squareIT.belajarpascal.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareIT.belajarpascal.R;
import com.squareIT.belajarpascal.model.MateriItem;

import java.util.ArrayList;

public class MateriAdapter extends RecyclerView.Adapter<MateriAdapter.MateriItemViewHolder> {

    private static Context context;

    private ArrayList<MateriItem> materiItemArrayList;

    //Click Handling
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    //Constructor
    public MateriAdapter(ArrayList<MateriItem> materiItemArrayList, Context context) {
        this.materiItemArrayList = materiItemArrayList;
        this.context = context;
    }

    //InnerClass viewholder
    public static class MateriItemViewHolder extends RecyclerView.ViewHolder {
        private TextView number, title, caption;

        public MateriItemViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            number = (TextView) itemView.findViewById(R.id.tv_materi_number);
            title = (TextView) itemView.findViewById(R.id.tv_materi_title);
            caption = (TextView) itemView.findViewById(R.id.tv_materi_caption);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public MateriItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.materi_item, viewGroup, false);

        MateriItemViewHolder materiItemViewHolder = new MateriItemViewHolder(view, onItemClickListener);
        return materiItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MateriItemViewHolder viewHolder, int i) {
        MateriItem materiItem = materiItemArrayList.get(i);
        viewHolder.number.setText(materiItem.getId());
        viewHolder.title.setText(materiItem.getTitle());
        viewHolder.caption.setText(materiItem.getCaption());
    }

    @Override
    public int getItemCount() {
        return materiItemArrayList.size();
    }

}


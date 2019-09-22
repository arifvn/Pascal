package com.squareIT.belajarpascal.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareIT.belajarpascal.R;
import com.squareIT.belajarpascal.model.RPPItem;

import java.util.ArrayList;

public class RPPAdapter extends RecyclerView.Adapter<RPPAdapter.RPPItemViewHolder> {

    private static Context context;

    private ArrayList<RPPItem> rppItemArrayList;

    //Click Handling
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    //Constructor
    public RPPAdapter(ArrayList<RPPItem> rppItemArrayList, Context context) {
        this.rppItemArrayList = rppItemArrayList;
        this.context = context;
    }

    //InnerClass viewholder
    public static class RPPItemViewHolder extends RecyclerView.ViewHolder {
        private TextView kd, title;

        public RPPItemViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            kd = (TextView) itemView.findViewById(R.id.tv_rpp_kd);
            title = (TextView) itemView.findViewById(R.id.tv_rpp_title);

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
    public RPPItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.rpp_item, viewGroup, false);

        RPPItemViewHolder rppItemViewHolder = new RPPItemViewHolder(view, onItemClickListener);
        return rppItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RPPItemViewHolder viewHolder, int i) {
        RPPItem rppItem = rppItemArrayList.get(i);
        viewHolder.kd.setText(rppItem.getKd());
        viewHolder.title.setText(rppItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return rppItemArrayList.size();
    }

}

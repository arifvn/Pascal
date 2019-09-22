package com.squareIT.belajarpascal.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareIT.belajarpascal.R;
import com.squareIT.belajarpascal.model.KDItem;

import java.util.ArrayList;

public class KDAdapter extends RecyclerView.Adapter<KDAdapter.KDItemViewHolder> {

    private static Context context;

    private ArrayList<KDItem> KDItemArrayList;

    //Constructor
    public KDAdapter(ArrayList<KDItem> KDItemArrayList, Context context) {
        this.KDItemArrayList = KDItemArrayList;
        this.context = context;
    }

    //InnerClass viewholder
    public static class KDItemViewHolder extends RecyclerView.ViewHolder {
        private TextView kd, caption, kd2, caption2;

        public KDItemViewHolder(@NonNull View itemView) {
            super(itemView);
            kd = (TextView) itemView.findViewById(R.id.tv_kd);
            kd2 = (TextView) itemView.findViewById(R.id.tv_kd_2);
            caption = (TextView) itemView.findViewById(R.id.tv_kd_caption);
            caption2 = (TextView) itemView.findViewById(R.id.tv_kd_caption_2);
        }
    }

    @NonNull
    @Override
    public KDAdapter.KDItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.kd_item, viewGroup, false);

        KDAdapter.KDItemViewHolder kdItemViewHolder = new KDAdapter.KDItemViewHolder(view);
        return kdItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull KDAdapter.KDItemViewHolder viewHolder, int i) {
        KDItem kdItem = KDItemArrayList.get(i);
        viewHolder.kd.setText(kdItem.getKd());
        viewHolder.kd2.setText(kdItem.getKd2());
        viewHolder.caption.setText(kdItem.getCaption());
        viewHolder.caption2.setText(kdItem.getCaption2());
    }

    @Override
    public int getItemCount() {
        return KDItemArrayList.size();
    }

}

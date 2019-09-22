package com.squareIT.belajarpascal.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareIT.belajarpascal.model.DashboardItem;
import com.squareIT.belajarpascal.R;

import java.util.ArrayList;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.DashboardItemViewHolder> {

    private static Context context;

    private ArrayList<DashboardItem> dashboardItemArrayList;

    //Click Handling
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    //Constructor
    public DashboardAdapter(ArrayList<DashboardItem> dashboardItemArrayList, Context context) {
        this.dashboardItemArrayList = dashboardItemArrayList;
        this.context = context;
    }

    //InnerClass viewholder
    public static class DashboardItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageIcon;
        private TextView title;

        public DashboardItemViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            imageIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
            title = (TextView) itemView.findViewById(R.id.tv_title);

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
    public DashboardItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dashboard_item, viewGroup, false);

        DashboardItemViewHolder dashboardItemViewHolder = new DashboardItemViewHolder(view, onItemClickListener);
        return dashboardItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardItemViewHolder viewHolder, int i) {
        DashboardItem dashboardItem = dashboardItemArrayList.get(i);
        viewHolder.imageIcon.setImageResource(dashboardItem.getImageIcon());
        viewHolder.title.setText(dashboardItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return dashboardItemArrayList.size();
    }

}

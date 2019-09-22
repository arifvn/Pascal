package com.squareIT.belajarpascal.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareIT.belajarpascal.R;
import com.squareIT.belajarpascal.model.VideoItem;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoItemViewHolder> {

    private static Context context;

    private ArrayList<VideoItem> videoItemArrayList;

    //Click Handling
    private VideoAdapter.OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(VideoAdapter.OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    //Constructor
    public VideoAdapter(ArrayList<VideoItem> videoItemArrayList, Context context) {
        this.videoItemArrayList = videoItemArrayList;
        this.context = context;
    }

    //InnerClass viewholder
    public static class VideoItemViewHolder extends RecyclerView.ViewHolder {
        private TextView videoTitle, videoCaption;
        private RelativeLayout relativeLayout;
        private Button buttonPlay;

        public VideoItemViewHolder(@NonNull View itemView, final VideoAdapter.OnItemClickListener listener) {
            super(itemView);
            videoTitle = (TextView) itemView.findViewById(R.id.tv_video_title);
            videoCaption = (TextView) itemView.findViewById(R.id.tv_video_caption);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relative_video);
            buttonPlay = (Button) itemView.findViewById(R.id.btn_video_play);

            buttonPlay.setOnClickListener(new View.OnClickListener() {
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
    public VideoAdapter.VideoItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.video_item, viewGroup, false);

        VideoAdapter.VideoItemViewHolder videoItemViewHolder = new VideoItemViewHolder(view, onItemClickListener);
        return videoItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.VideoItemViewHolder viewHolder, int i) {
        VideoItem videoItem = videoItemArrayList.get(i);
        viewHolder.videoTitle.setText(videoItem.getVideoTitle());
        viewHolder.videoCaption.setText(videoItem.getVideoCaption());
        viewHolder.relativeLayout.setBackgroundColor(videoItem.getColorBg());
    }

    @Override
    public int getItemCount() {
        return videoItemArrayList.size();
    }

}


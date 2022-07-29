package com.example.firstphotoalbumapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstphotoalbumapp.Controller.VideoController;
import com.example.firstphotoalbumapp.Model.MyVideo;
import com.example.firstphotoalbumapp.R;

import java.util.List;

public class MyVideoAdapter extends RecyclerView.Adapter<MyVideoAdapter.ViewHolder> {

    private List<MyVideo> myVideoList;

    public MyVideoAdapter(List<MyVideo> myVideoList) {
        this.myVideoList = myVideoList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView id;
        ImageView imageView;
        TextView textView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.video_id);
            imageView = (ImageView) itemView.findViewById(R.id.video_image_title);
            textView = (TextView) itemView.findViewById(R.id.video_path_title);
        }

    }

    @NonNull
    @Override
    public MyVideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        final RecyclerView.ViewHolder holder = new MyVideoAdapter.ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyVideo video = myVideoList.get(holder.getAdapterPosition());
                VideoController.actionStart(v.getContext(), video.getPath(),video.getId());

            }
        });
        return (MyVideoAdapter.ViewHolder) holder;
    }


    @Override
    public void onViewRecycled(@NonNull MyVideoAdapter.ViewHolder holder) {
        super.onViewRecycled(holder);
    }


    @Override
    public void onBindViewHolder(@NonNull MyVideoAdapter.ViewHolder holder, int position) {

        MyVideo video = myVideoList.get(position);
        holder.id.setText(String.valueOf(video.getId()));
        holder.imageView.setImageBitmap(video.getThumbnail());
        holder.textView.setText(video.getPath());


    }

    @Override
    public int getItemCount() {
        return myVideoList.size();
    }

}


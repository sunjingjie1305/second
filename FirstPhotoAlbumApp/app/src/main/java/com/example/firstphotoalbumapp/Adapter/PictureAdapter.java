package com.example.firstphotoalbumapp.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.firstphotoalbumapp.Controller.PictureController;
import com.example.firstphotoalbumapp.Model.Picture;
import com.example.firstphotoalbumapp.R;

import java.util.List;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {

    private List<Picture> pictureList;

    public PictureAdapter(List<Picture> pictureList) {

        Log.d("Sanbin","adapter create");
        this.pictureList = pictureList;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView id;
        ImageView imageView;
        TextView textView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id=(TextView)itemView.findViewById(R.id.picture_id);
            imageView=(ImageView) itemView.findViewById(R.id.picture_title);
            textView=(TextView) itemView.findViewById(R.id.path_title);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_item,parent,false);
        final RecyclerView.ViewHolder holder= new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picture picture =pictureList.get(holder.getAdapterPosition());
                PictureController.actionStart(v.getContext(), picture.getPath());

            }
        });
        return (ViewHolder) holder;
    }



    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        super.onViewRecycled(holder);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("Sanbin","onBindViewHolder in PicAdapter");
        Picture picture= pictureList.get(position);
        holder.id.setText(String.valueOf(picture.getId()));
        holder.imageView.setImageBitmap(picture.getPicture());
        holder.textView.setText(picture.getPath());


    }

    @Override
    public int getItemCount() {

        Log.d("Sanbin","oictureList.size("+pictureList.size());
        return pictureList.size();
    }

}

package com.example.firstphotoalbumapp.View;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.firstphotoalbumapp.R;

public class PictureContentFragment extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.picture_content_frag,container,false);
        return view;
    }

    public void refresh(Bitmap picture, String content){
        View visLay =view.findViewById(R.id.vis_layout);
        visLay.setVisibility(View.VISIBLE);
        ImageView imageView =(ImageView) view.findViewById(R.id.picture_title);
        TextView contentText =(TextView) view.findViewById(R.id.path);
        imageView.setImageBitmap(picture);
        contentText.setText(content);
    }
}

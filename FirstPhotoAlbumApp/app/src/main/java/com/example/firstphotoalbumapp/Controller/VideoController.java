package com.example.firstphotoalbumapp.Controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firstphotoalbumapp.R;

import com.example.firstphotoalbumapp.View.VideoContentFragment;

public class VideoController extends AppCompatActivity {

    public static void actionStart(Context context,String path,int id){
        Intent intent = new Intent(context, VideoController.class);
        intent.putExtra("video",path);
        intent.putExtra("id",id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_content);
        String path = getIntent().getStringExtra("video");
        int id=getIntent().getIntExtra("id",0);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = false;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = MediaStore.Video.Thumbnails.getThumbnail(getContentResolver(), id, MediaStore.Images.Thumbnails.MICRO_KIND, options);
       VideoContentFragment videoContentFragment =
                (VideoContentFragment) getSupportFragmentManager().findFragmentById(R.id.video_content_fragment);
        videoContentFragment.refresh(bitmap,path);
    }
}

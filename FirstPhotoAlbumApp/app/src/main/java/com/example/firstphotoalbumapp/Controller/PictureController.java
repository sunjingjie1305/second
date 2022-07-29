package com.example.firstphotoalbumapp.Controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firstphotoalbumapp.Model.Picture;
import com.example.firstphotoalbumapp.R;
import com.example.firstphotoalbumapp.View.PictureContentFragment;

public class PictureController extends AppCompatActivity {

    public static void actionStart(Context context,String path){
        Intent intent = new Intent(context,PictureController.class);
        intent.putExtra("picture",path);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_content);
        String path = getIntent().getStringExtra("picture");
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        PictureContentFragment pictureContentFragment =
                (PictureContentFragment) getSupportFragmentManager().findFragmentById(R.id.picture_content_fragment);
        pictureContentFragment.refresh(bitmap,path);
    }
}

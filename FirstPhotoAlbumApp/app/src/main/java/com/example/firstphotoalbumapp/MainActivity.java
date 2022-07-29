package com.example.firstphotoalbumapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.firstphotoalbumapp.View.PictureTitleFragment;
import com.example.firstphotoalbumapp.View.VideoTitleFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean pictureFlag = true;
    private boolean videoFlag = true;


    private PictureTitleFragment pictureTitleFragment;
    private VideoTitleFragment videoFragment;
    private NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //申请权限
        if ((ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) ||
                (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) ||
                (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)) {
            //如果没有授权，则请求授权
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 2);
        }
        if (savedInstanceState==null){
            Log.d("fragment","savedInstanceState :null  ");
        }
        Button picture = (Button) findViewById(R.id.picture_button);
        Button video = (Button) findViewById(R.id.video_button);
        picture.setOnClickListener(this);
        video.setOnClickListener(this);
       navController =Navigation.findNavController(MainActivity.this,R.id.nav_host_fragment);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.picture_button: {
                if (navController.getCurrentDestination().getId()!=R.id.mainFragment) {
                    navController.navigate(R.id.mainFragment);
                }break;}
            case R.id.video_button: {
                if (navController.getCurrentDestination().getId()!=R.id.secondFragment){
                    navController.navigate(R.id.secondFragment);
                }break;}
            default:
                break;
        }
    }


}
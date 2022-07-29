package com.example.firstphotoalbumapp.View;

import static android.content.Context.SENSOR_SERVICE;

import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorManager;
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
import com.squareup.picasso.Picasso;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoContentFragment extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.video_content_frag,container,false);
        return view;
    }

    private JCVideoPlayer.JCAutoFullscreenListener mSensorEventListener;
    //传感器
    private SensorManager mSensorManage;
    //传感器

    public void refresh(Bitmap bitmap,String content){
        View visLay =view.findViewById(R.id.vis_layout_video);
        visLay.setVisibility(View.VISIBLE);
        JCVideoPlayerStandard videoPlayer =(JCVideoPlayerStandard) view.findViewById(R.id.video_title);
        TextView contentText =(TextView) view.findViewById(R.id.video_path);
        contentText.setText(content);
        videoPlayer.setUp(content, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,"simpleVideo");
        mSensorManage = (SensorManager) getContext().getSystemService(SENSOR_SERVICE);
        mSensorEventListener = new JCVideoPlayer.JCAutoFullscreenListener();
        videoPlayer.thumbImageView.setImageBitmap(bitmap);
        //Picasso.with(getContext())
    }

    @Override
    public void onResume() {
        super.onResume();
        //注册传感器
        Sensor sensor = mSensorManage.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManage.registerListener(mSensorEventListener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        //取消注册传感器
        mSensorManage.unregisterListener(mSensorEventListener);
        JCVideoPlayer.releaseAllVideos();
    }





}

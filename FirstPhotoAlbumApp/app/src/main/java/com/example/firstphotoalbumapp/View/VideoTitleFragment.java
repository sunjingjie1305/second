package com.example.firstphotoalbumapp.View;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstphotoalbumapp.Adapter.MyVideoAdapter;
import com.example.firstphotoalbumapp.Adapter.PictureAdapter;
import com.example.firstphotoalbumapp.MainActivity;
import com.example.firstphotoalbumapp.Model.MyVideo;
import com.example.firstphotoalbumapp.R;

import java.util.ArrayList;
import java.util.List;

public class VideoTitleFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView2;
    private List<MyVideo> videos = new ArrayList<MyVideo>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        MainActivity activity =(MainActivity)getActivity();
        view = inflater.inflate(R.layout.video_title_frag, container, false);
        recyclerView2 = (RecyclerView) view.findViewById(R.id.recycler_view_video);
        recyclerView2.setLayoutManager(new LinearLayoutManager(activity));
        getVideos();
        MyVideoAdapter myVideoAdapter = new MyVideoAdapter(videos);
        recyclerView2.setAdapter(myVideoAdapter);
        return view;
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d("fragment","video +  "+hidden);
    }



    /**
     * 获取本机视频列表
     *
     * @return
     */
    public void getVideos() {

        if (videos.size()==0){
            Cursor c = null;
            try {
                c = getContext().getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Video.Media.DEFAULT_SORT_ORDER);
                while (c.moveToNext()) {
                    String path = c.getString(c.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));// 路径

                    int id = c.getInt(c.getColumnIndexOrThrow(MediaStore.Video.Media._ID));// 视频的id
                    String name = c.getString(c.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)); // 视频名称
                    String resolution = c.getString(c.getColumnIndexOrThrow(MediaStore.Video.Media.RESOLUTION)); //分辨率
                    long size = c.getLong(c.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));// 大小
                    long duration = c.getLong(c.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));// 时长
                    long date = c.getLong(c.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_MODIFIED));//修改时间

                    Bitmap bitmap = getVideoThumbnail(id);
                    MyVideo video = new MyVideo(id, path, name, resolution, size, date, duration, bitmap);

                    videos.add(video);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (c != null) {
                    c.close();
                }
            }
        }





    }

    // 获取视频缩略图
    public Bitmap getVideoThumbnail(int id) {
        Bitmap bitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = false;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        bitmap = MediaStore.Video.Thumbnails.getThumbnail(getContext().getContentResolver(), id, MediaStore.Images.Thumbnails.MICRO_KIND, options);
        return bitmap;
    }
}

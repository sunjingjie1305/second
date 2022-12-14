package com.example.firstphotoalbumapp.View;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstphotoalbumapp.Adapter.PictureAdapter;
import com.example.firstphotoalbumapp.MainActivity;
import com.example.firstphotoalbumapp.Model.Picture;
import com.example.firstphotoalbumapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PictureTitleFragment extends Fragment {

    private View view;
    private List<Picture> pictureList = new ArrayList<>();
    private RecyclerView recyclerView;
    List<String> allPath = new ArrayList<>();
    BitmapFactory.Options options = new BitmapFactory.Options();
    private int position = -1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        MainActivity activity = (MainActivity) getActivity();
        view = LayoutInflater.from(activity).inflate(R.layout.picture_title_frag, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        getPictureFromPhoto();
        PictureAdapter pictureAdapter = new PictureAdapter(pictureList);
        recyclerView.setAdapter(pictureAdapter);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("ssss", "onScrolled" + dx + "   " + dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                int lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                int firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                //?????????????????????fragment
                if (lastVisibleItemPosition + 1 > position) {
                    Log.d("ssss", "is last");
                    updatePicture();
                    MoveToPosition(new LinearLayoutManager(activity),recyclerView,lastVisibleItemPosition-1);
                    recyclerView.smoothScrollToPosition(lastVisibleItemPosition);
                }
                Log.d("ssss", "ecyclerView.getTop();" + recyclerView.getTop() + "last " + lastVisibleItemPosition + " first " + firstVisibleItemPosition);
            }
        });
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d("fragment", "picture +  " + hidden);
    }


    //??????????????????
    public void getPictureFromPhoto() {
        if (allPath.size() == 0) {
            allPath = getAllPcturePath(getContext().getContentResolver());//????????????
            options.inSampleSize = 5;
            options.inPreferredConfig = null;  /*???????????????????????????????????????*/
            options.inJustDecodeBounds = false;

            //?????????9???
            for (int i = 0; i < 9; i++) {
                position++;
                String path = allPath.get(i);
                Picture picture = new Picture();
                Bitmap bitmap = BitmapFactory.decodeFile(path, options);
                picture.setId(position);
                picture.setPicture(bitmap);
                picture.setPath(path);
                pictureList.add(picture);
            }
        }
        Log.d("Sanbin", "pictureList" + pictureList.size());


    }


    private void updatePicture() {
        if (allPath.size() == 0) return;
        if (position <= allPath.size()) {
            for (int i = 0; i < 9; i++) {
                if (position < allPath.size()) {
                    String path = allPath.get(position - 1);
                    Picture picture = new Picture();
                    Bitmap bitmap = BitmapFactory.decodeFile(path, options);
                    picture.setId(position);
                    picture.setPicture(bitmap);
                    picture.setPath(path);
                    pictureList.add(picture);
                    position++;
                } else return;
            }
            PictureAdapter pictureAdapter = new PictureAdapter(pictureList);
            recyclerView.setAdapter(pictureAdapter);
        } else return;
    }

    //??????????????????
    private List<String> getAllPcturePath(ContentResolver contentResolver) {
        List<String> allPath = new ArrayList<>();
        //???????????????????????????id
        final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
        //??????id??????
        final String orderBy = MediaStore.Images.Media._ID;
        //?????????sql????????????????????????orderBy??????????????????????????????????????????orderBy+" desc "
        Cursor imagecursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, orderBy);
        if (imagecursor != null && imagecursor.getCount() > 0) {
            while (imagecursor.moveToNext()) {
                int dataColumnIndex = imagecursor.getColumnIndex(MediaStore.Images.Media.DATA);
                String path = imagecursor.getString(dataColumnIndex);
                Log.d("tgw5", "getGalleryPhotos: " + path);
                allPath.add(path);
            }
        }
        //???????????????????????????????????????????????????
        Collections.reverse(allPath);
        return allPath;
    }

    /**
     * RecyclerView ????????????????????????
     *
     * @param manager   ??????RecyclerView?????????manager
     * @param mRecyclerView  ?????????RecyclerView
     * @param n  ??????????????????
     */
    public static void MoveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {


        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(n);
        }

    }
}

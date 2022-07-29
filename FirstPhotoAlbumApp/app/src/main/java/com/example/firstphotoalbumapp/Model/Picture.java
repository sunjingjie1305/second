package com.example.firstphotoalbumapp.Model;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Picture  {
    private int id;
    private String path;
    private Bitmap picture;

    public Picture() {
    }

    public Picture(String path, Bitmap picture) {
        this.path = path;
        this.picture = picture;
    }

    public Picture(int id, String path, Bitmap picture) {
        this.id = id;
        this.path = path;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }
}

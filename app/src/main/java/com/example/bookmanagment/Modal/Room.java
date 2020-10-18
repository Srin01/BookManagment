package com.example.bookmanagment.Modal;

import android.graphics.Bitmap;

public class Room
{
    private int id;
    private String roomName;
    private Bitmap bitmapImage;

    public Room(int id, String roomName, Bitmap bitmap)
    {
        this.id = id;
        this.roomName = roomName;
        this.bitmapImage = bitmap;
    }
    public Room()
    {
        
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Bitmap getBitmapImage()
    {
        return bitmapImage;
    }
}

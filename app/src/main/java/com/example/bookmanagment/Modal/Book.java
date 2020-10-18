package com.example.bookmanagment.Modal;

import android.graphics.Bitmap;

public class Book
{
    private int id;
    private String bookAuthor;
    private String bookName;
    private int roomID;
    private int rowNumber;
    private int bookPositionInRow;
    private Bitmap bitmap;

    public Book(int id, String bookName, int roomID,  int rowNumber, int bookPositionInRow, String summary, Bitmap bitmap)
    {
        this.id = id;
        this.bookName = bookName;
        this.roomID = roomID;
        this.rowNumber = rowNumber;
        this.bookPositionInRow = bookPositionInRow;
        this.bookAuthor = summary;
        this.bitmap = bitmap;
    }

    public Book(int id, String bookName, int roomID, int rowNumber, int bookPositionInRow, String summary)
    {
        this.id = id;
        this.bookName = bookName;
        this.roomID = roomID;
        this.rowNumber = rowNumber;
        this.bookPositionInRow = bookPositionInRow;
        this.bookAuthor = summary;
    }

    public Book(String bookName, int rowNumber, int roomID, Bitmap bitmap)
    {
        this.bookName = bookName;
        this.rowNumber = rowNumber;
        this.roomID = roomID;
        this.bitmap = bitmap;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getRoomID()
    {
        return roomID;
    }

    public int getRowNumber()
    {
        return rowNumber;
    }

    public int getBookPositionInRow()
    {
        return bookPositionInRow;
    }

    public String getBookAuthor()
    {
        return bookAuthor;
    }

    public String getBookName() {
        return bookName;
    }

    public Bitmap getBitmapImage(){ return bitmap; }
}

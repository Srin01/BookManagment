package com.example.bookmanagment.Modal;

public class Book
{
    private int id;
    private String summary;
    private String bookName;
    private int roomID;
    private int rowNumber;
    private int bookPositionInRow;
    private int shelfID;

    public Book(int id, String bookName, int roomID, int shelfID , int rowNumber, int bookPositionInRow, String summary)
    {
        this.id = id;
        this.bookName = bookName;
        this.shelfID = shelfID;
        this.roomID = roomID;
        this.rowNumber = rowNumber;
        this.bookPositionInRow = bookPositionInRow;
        this.summary = summary;
    }

    public Book(String bookName, int rowNumber)
    {
        this.bookName = bookName;
        this.rowNumber = rowNumber;
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

    public int getShelfID() {
        return shelfID;
    }

    public int getRowNumber()
    {
        return rowNumber;
    }

    public int getBookPositionInRow()
    {
        return bookPositionInRow;
    }

    public String getSummary()
    {
        return summary;
    }

    public String getBookName() {
        return bookName;
    }
}

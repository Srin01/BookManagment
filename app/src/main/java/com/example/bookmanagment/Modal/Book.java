package com.example.bookmanagment.Modal;

public class Book
{
    private int id;
    private String summary;
    private int roomID;
    private int rowNumber;
    private int bookPositionInRow;
    private int shelfID;

    public Book(int id, int roomID, int shelfID , int rowNumber, int bookPositionInRow, String summary)
    {
        this.id = id;
        this.shelfID = shelfID;
        this.roomID = roomID;
        this.rowNumber = rowNumber;
        this.bookPositionInRow = bookPositionInRow;
        this.summary = summary;
    }

    public Book()
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

    public int getRoomID()
    {
        return roomID;
    }

    public void setRoomID(int roomID)
    {
        this.roomID = roomID;
    }

    public int getShelfID() {
        return shelfID;
    }

    public void setShelfID(int shelfID) {
        this.shelfID = shelfID;
    }


    public int getRowNumber()
    {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber)
    {
        this.rowNumber = rowNumber;
    }

    public int getBookPositionInRow()
    {
        return bookPositionInRow;
    }

    public void setBookPositionInRow(int bookPositionInRow)
    {
        this.bookPositionInRow = bookPositionInRow;
    }

    public String getSummary()
    {
        return summary;
    }

    public void setSummary(String summary)
    {
        this.summary = summary;
    }
}

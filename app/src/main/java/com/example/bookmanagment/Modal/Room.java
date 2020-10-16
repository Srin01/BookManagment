package com.example.bookmanagment.Modal;

public class Room
{
    private int id;

    public Room(int id, int numberOfShelf, String roomName)
    {
        this.id = id;
        NumberOfShelf = numberOfShelf;
        this.roomName = roomName;
    }
    public Room()
    {
        
    }

    private int NumberOfShelf;
    private String roomName;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getShelfNumber()
    {
        return NumberOfShelf;
    }

    public void setShelfNumber(int shelf)
    {
        NumberOfShelf = shelf;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}

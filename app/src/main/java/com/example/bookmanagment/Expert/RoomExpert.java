package com.example.bookmanagment.Expert;

import com.example.bookmanagment.Driver.BookDatabaseDriver;
import com.example.bookmanagment.Driver.RoomDatabaseDriver;
import com.example.bookmanagment.Modal.Room;

import java.util.ArrayList;

public class RoomExpert
{
    RoomDatabaseDriver roomDatabaseDriver;
    ArrayList<Room> roomArrayList;
    public RoomExpert(RoomDatabaseDriver roomDatabaseDriver)
    {
        this.roomDatabaseDriver = roomDatabaseDriver;
        roomArrayList = roomDatabaseDriver.getAllRoomList();
    }

    public int getRoomID(int bookPosition)
    {
        return roomArrayList.get(bookPosition).getId();
    }

    public int getShelfNumber(int bookPosition)
    {
        return roomArrayList.get(bookPosition).getShelfNumber();
    }

    public String getRoomName(int bookPosition)
    {
        return roomArrayList.get(bookPosition).getRoomName();
    }

    public void addNewRoom(Room room)
    {
        roomDatabaseDriver.insertNewRoom(room);
        roomArrayList.add(room);
    }

    public int getTotalRooms()
    {
        return roomArrayList.size();

    }
}

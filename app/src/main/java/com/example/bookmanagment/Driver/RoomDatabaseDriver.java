package com.example.bookmanagment.Driver;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bookmanagment.Modal.Room;
import com.example.bookmanagment.SQL.RoomSqlHelper;
import com.example.bookmanagment.Schema.RoomSchema;

import java.util.ArrayList;

public class RoomDatabaseDriver
{
    private RoomSqlHelper roomSqlHelper;
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public RoomDatabaseDriver(Context context)
    {
        this.context = context;
        roomSqlHelper = new RoomSqlHelper(context);
        sqLiteDatabase = roomSqlHelper.getWritableDatabase();
    }

    public ArrayList<Room> getAllRoomList()
    {
        ArrayList<Room> roomList = new ArrayList<>();

        String[] columns = {RoomSchema._roomId, RoomSchema._numberOfShelf};
        Cursor cursor = sqLiteDatabase.query(RoomSchema._tableName, columns, null, null, null, null, null);

        if(cursor != null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            do {
                int id = cursor.getInt(cursor.getColumnIndex(RoomSchema._roomId));
                int numberOfShelf = cursor.getInt(cursor.getColumnIndex(RoomSchema._numberOfShelf));

                 Room room = setRoomDetails(id, numberOfShelf);
                roomList.add(room);

            }while (cursor.moveToNext());
        }

        return roomList;
    }

    private Room setRoomDetails(int id, int numberOfShelf)
    {
        Room room = new Room();
        room.setId(id);
        room.setShelfNumber(numberOfShelf);
        return room;
    }

    public void insertNewRoom(Room room)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(RoomSchema._roomId, room.getId());
        contentValues.put(RoomSchema._numberOfShelf, room.getShelfNumber());
    }
}

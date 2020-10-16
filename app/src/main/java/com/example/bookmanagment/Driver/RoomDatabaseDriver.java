package com.example.bookmanagment.Driver;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bookmanagment.Modal.Room;
import com.example.bookmanagment.SQL.BookSqlHelper;
import com.example.bookmanagment.Schema.RoomSchema;

import java.util.ArrayList;

public class RoomDatabaseDriver
{
     BookSqlHelper roomSqlHelper;
     Context context;
     SQLiteDatabase sqLiteDatabase;

    public RoomDatabaseDriver(Context context)
    {
        this.context = context;
        roomSqlHelper = new BookSqlHelper(context);
        sqLiteDatabase = roomSqlHelper.getWritableDatabase();
    }

    public ArrayList<Room> getAllRoomList()
    {
        ArrayList<Room> roomList = new ArrayList<>();

        String[] columns = {RoomSchema._roomId, RoomSchema._roomName,  RoomSchema._numberOfShelf};
        Cursor cursor = sqLiteDatabase.query(RoomSchema._tableName, columns, null, null, null, null, null);

        if(cursor != null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            do {
                int id = cursor.getInt(cursor.getColumnIndex(RoomSchema._roomId));
                String roomName = cursor.getString(cursor.getColumnIndex(RoomSchema._roomName));
                int numberOfShelf = cursor.getInt(cursor.getColumnIndex(RoomSchema._numberOfShelf));

                Room room = new Room(id,  numberOfShelf, roomName);
                roomList.add(room);

            }while (cursor.moveToNext());
        }

        return roomList;
    }

    public void insertNewRoom(Room room)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(RoomSchema._roomName, room.getRoomName());
        contentValues.put(RoomSchema._numberOfShelf, room.getShelfNumber());

        long id = sqLiteDatabase.insert(RoomSchema._tableName, null, contentValues);
    }
}

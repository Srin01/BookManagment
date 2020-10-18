package com.example.bookmanagment.Driver;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.bookmanagment.Modal.Book;
import com.example.bookmanagment.Modal.Room;
import com.example.bookmanagment.SQL.BookSqlHelper;
import com.example.bookmanagment.Schema.BookSchema;
import com.example.bookmanagment.Schema.RoomSchema;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import static com.example.bookmanagment.BookViewerActivity.TAG;

public class RoomDatabaseDriver
{
     BookSqlHelper roomSqlHelper;
     Context context;
     private SQLiteDatabase sqLiteDatabase;
     private ArrayList<Room> roomList;

    public RoomDatabaseDriver(Context context)
    {
        this.context = context;
        roomSqlHelper = new BookSqlHelper(context);
        sqLiteDatabase = roomSqlHelper.getWritableDatabase();
        createLivingRoom();
    }

    private void createLivingRoom()
    {
        if(!isLivingRoomExist()) {
            ContentValues contentValues = insertContentValues(new Room("Living Room"));
            long id = sqLiteDatabase.insert(RoomSchema._tableName, null, contentValues);
            Log.d(TAG, "createLivingRoom: living room is created ");
        }
    }

    public boolean isLivingRoomExist()
    {
        String selection = RoomSchema._roomName + " = ?";
        String[] selectionArgs = {"Living Room"};

        String[] columns = {RoomSchema._roomId};
        Cursor cursor = sqLiteDatabase.query(RoomSchema._tableName, columns, selection, selectionArgs,null,null, null);
        return cursor != null && cursor.getCount() > 0 && cursor.moveToFirst();
    }
    
    public ArrayList<Room> getAllRoomList()
    {
        roomList = new ArrayList<>();

        String[] columns = {RoomSchema._roomId, RoomSchema._roomName};
        Cursor cursor = sqLiteDatabase.query(RoomSchema._tableName, columns, null, null, null, null, null);

        return getListOfBooksFromDb(cursor);
    }

    private ArrayList<Room> getListOfBooksFromDb(Cursor cursor)
    {
        if(cursor != null && cursor.getCount() > 0 && cursor.moveToFirst())
        {
            cursor.moveToFirst();
            do {
                int id = cursor.getInt(cursor.getColumnIndex(RoomSchema._roomId));
                String roomName = cursor.getString(cursor.getColumnIndex(RoomSchema._roomName));
                Room room = new Room(id, roomName);
                roomList.add(room);

            }while (cursor.moveToNext());
        }

        return roomList;
    }

    public int getIdRoomSpeificname(String name)
    {
        roomList = new ArrayList<>();
        String selection = RoomSchema._roomName + " = ?";
        String[] selectionArgs = {name};

        String[] columns = {RoomSchema._roomId};
        Cursor cursor = sqLiteDatabase.query(RoomSchema._tableName, columns, selection, selectionArgs,null,null, null);

        if(cursor != null && cursor.getCount() > 0 && cursor.moveToFirst())
        {
            cursor.moveToFirst();
            do {
                return cursor.getInt(cursor.getColumnIndex(RoomSchema._roomId));

            }while (cursor.moveToNext());
        }
        return 1;
    }

    public void insertNewRoom(Room room)
    {
        ContentValues contentValues = insertContentValues(room);
        long id = sqLiteDatabase.insert(RoomSchema._tableName, null, contentValues);
        Log.d(TAG, "insertNewRoom: new Room of id " + id + " inserted into db");
    }

    private ContentValues insertContentValues(Room room)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(RoomSchema._roomName, room.getRoomName());
        return contentValues;
    }
}

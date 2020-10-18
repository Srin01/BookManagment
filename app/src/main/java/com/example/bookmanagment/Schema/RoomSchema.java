package com.example.bookmanagment.Schema;

public class RoomSchema implements DatabaseSchema
{
    public static final String _tableName = "room";
    public static final String _roomId = "id";
    public static final String _roomName = "room_name";
    public static final String _roomImage = "room_image";

    public static String createTableRoom = "CREATE TABLE "+ _tableName +" (" +_roomId+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + _roomName+ " VARCHAR(45), " +_roomImage + " BLOB);";

}

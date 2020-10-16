package com.example.bookmanagment.Schema;

public class RoomSchema implements DatabaseSchema
{
    public static final String _tableName = "room";
    public static final String _roomId = "id";
    public static final String _numberOfShelf = "shelf_number";

    public static String createTableRoom = "CREATE TABLE "+ _tableName +" (" +_roomId+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +_numberOfShelf +" INTEGER );";

}

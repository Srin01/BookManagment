package com.example.bookmanagment.Schema;

public class BookSchema implements DatabaseSchema
{
    public static final String _tableName = "book";
    public static final String _bookId = "id";
    public static final String _roomID = "room_id";
    public static final String _shelfID = "shelf_id";
    public static final String _summary = "summary";
    public static final String _rowNumber = "row_number";
    public static final String _bookPosition = "book_position";
    public static String _bookName = "book_name";

    public static String createBookTable = "CREATE TABLE "+ _tableName +" (" +_bookId+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + _bookName+ " VARCHAR(45), " +_roomID+" INTEGER, "+_shelfID +" INTEGER, " + _rowNumber+" INTEGER, " + _bookPosition+ " INTEGER, " + _summary+ " VARCHAR(300));";

}

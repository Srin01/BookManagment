package com.example.bookmanagment.Schema;

public class UserSchema implements DatabaseSchema
{
    public static final String _userName = "user_name";
    public static final String _tableName = "user";
    public static final String _userId = "id";
    public static final String _userPassword = "password";
    public static final String _userQuestion = "question";
    public static final String _questionAnswer = "answer";
    public static final String _roomId = "room_id";

    public static final String _createTableUser = "CREATE TABLE "+ _tableName +" (" +_userId+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + _userName+ " VARCHAR(45), " +_userPassword+" VARCHAR(45), " + _userQuestion+" VARCHAR(45), " + _questionAnswer+ " VARCHAR(45), "+ _roomId +" INTEGER );" ;
}

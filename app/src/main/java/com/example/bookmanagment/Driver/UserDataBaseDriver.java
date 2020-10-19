package com.example.bookmanagment.Driver;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bookmanagment.Modal.User;
import com.example.bookmanagment.SQL.BookSqlHelper;
import com.example.bookmanagment.Schema.UserSchema;

import java.util.ArrayList;

import static com.example.bookmanagment.Activities.BookViewerActivity.TAG;

public class UserDataBaseDriver
{
    private SQLiteDatabase sqLiteDatabase;
    private ArrayList<User> userList;
    private int id = 1;
    private String username;
    private String user_pwd ;
    private String question;
    private String answer ;
    private String[] columns = {UserSchema._userId, UserSchema._userName, UserSchema._userPassword, UserSchema._userQuestion, UserSchema._questionAnswer, UserSchema._roomId};
    int rid ;

    private void addUserValues()
    {
        User user = new User(id, username, user_pwd, question, answer, rid);
        userList.add(user);
    }

    private void extractUserValues(Cursor cursor)
    {
        id = cursor.getInt(cursor.getColumnIndex(UserSchema._userId));
        username = cursor.getString(cursor.getColumnIndex(UserSchema._userName));
        user_pwd = cursor.getString(cursor.getColumnIndex(UserSchema._userPassword));
        question = cursor.getString(cursor.getColumnIndex(UserSchema._userQuestion));
        answer = cursor.getString(cursor.getColumnIndex(UserSchema._questionAnswer));
        rid = cursor.getInt(cursor.getColumnIndex(UserSchema._roomId));
    }

    private ArrayList<User> getAllUserList(Cursor cursor)
    {
        if(cursor != null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            do {
                extractUserValues(cursor);
                addUserValues();
            }while (cursor.moveToNext());
        }
        return userList;
    }

    public ArrayList<User> getUserFromSpecifRoom(int roomId)
    {
        String selection = UserSchema._roomId + " = ?" ;
        String[] selectionArgs = {String.valueOf(roomId)};
        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.query(UserSchema._tableName, columns, selection, selectionArgs, null, null, null);

        if(cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                extractUserValues(cursor);
                addUserValues();
            } while (cursor.moveToNext());
        }
        return userList;
    }

    public ArrayList<User> getUserList()
    {
        userList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(UserSchema._tableName, columns, null, null, null, null, null);

        return getAllUserList(cursor);
    }

    public void insertNewUser(User user)
    {
        ContentValues contentValues = insertContentValues(user);
        long id = sqLiteDatabase.insert(UserSchema._tableName, null, contentValues);
        Log.d(TAG, "insertNewUser: new User of id " + id + " inserted into db");
    }

    private ContentValues insertContentValues(User user)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserSchema._userId, id++);
        contentValues.put(UserSchema._userName, user.getUserName());
        contentValues.put(UserSchema._userPassword, user.getUserPassword());
        contentValues.put(UserSchema._userQuestion, user.getQuestion());
        contentValues.put(UserSchema._questionAnswer, user.getAnswer());
        contentValues.put(UserSchema._roomId, user.getRoomId());
        return contentValues;
    }

    public UserDataBaseDriver(Context context)
    {
        BookSqlHelper userSqlHelper = new BookSqlHelper(context);
        sqLiteDatabase = userSqlHelper.getWritableDatabase();
    }

}

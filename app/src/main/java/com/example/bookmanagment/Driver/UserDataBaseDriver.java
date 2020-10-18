package com.example.bookmanagment.Driver;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bookmanagment.Modal.User;
import com.example.bookmanagment.SQL.BookSqlHelper;
import com.example.bookmanagment.Schema.UserSchema;

import java.util.ArrayList;

import static com.example.bookmanagment.BookViewerActivity.TAG;

public class UserDataBaseDriver
{
    BookSqlHelper userSqlHelper;
    Context context;
    private SQLiteDatabase sqLiteDatabase;
    private ArrayList<User> userList;

    public UserDataBaseDriver(Context context)
    {
        this.context = context;
        userSqlHelper = new BookSqlHelper(context);
        sqLiteDatabase = userSqlHelper.getWritableDatabase();
    }

    public ArrayList<User> getUserList()
    {
        userList = new ArrayList<>();

        String[] columns = {UserSchema._userId, UserSchema._userName, UserSchema._userPassword, UserSchema._userQuestion, UserSchema._questionAnswer};
        Cursor cursor = sqLiteDatabase.query(UserSchema._tableName, columns, null, null, null, null, null);

        return getAllUserList(cursor);
    }

    private ArrayList<User> getAllUserList(Cursor cursor)
    {
        if(cursor != null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            do {
                int id = cursor.getInt(cursor.getColumnIndex(UserSchema._userId));
                String username = cursor.getString(cursor.getColumnIndex(UserSchema._userName));
                String user_pwd = cursor.getString(cursor.getColumnIndex(UserSchema._userPassword));
                String question = cursor.getString(cursor.getColumnIndex(UserSchema._userQuestion));
                String answer = cursor.getString(cursor.getColumnIndex(UserSchema._questionAnswer));
                User user = new User(id, username, user_pwd, question, answer );
                userList.add(user);

            }while (cursor.moveToNext());
        }
        return userList;
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
        contentValues.put(UserSchema._userId, user.getId());
        contentValues.put(UserSchema._userName, user.getUserName());
        contentValues.put(UserSchema._userPassword, user.getUserPassword());
        contentValues.put(UserSchema._userQuestion, user.getQuestion());
        contentValues.put(UserSchema._questionAnswer, user.getAnswer());
        return contentValues;
    }
}

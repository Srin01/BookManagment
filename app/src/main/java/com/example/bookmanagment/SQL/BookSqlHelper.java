package com.example.bookmanagment.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import com.example.bookmanagment.Schema.BookSchema;
import com.example.bookmanagment.Schema.RoomSchema;

public class BookSqlHelper extends SQLiteOpenHelper
{

    public BookSqlHelper(@Nullable Context context)
    {
        super(context,BookSchema.dataBaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(BookSchema.createBookTable);
        sqLiteDatabase.execSQL(RoomSchema.createTableRoom);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

package com.example.bookmanagment.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import com.example.bookmanagment.Schema.BookSchema;

public class BookSqlHelper extends SQLiteOpenHelper
{

    public BookSqlHelper(@Nullable Context context)
    {
        super(context,BookSchema.Book._tableName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL("CREATE TABLE "+BookSchema.Book._tableName +" (" +BookSchema.Book._id+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +BookSchema.Book._roomID+" INTEGER, "+BookSchema.Book._shelfID +" INTEGER, " + BookSchema.Book._rowNumber+" INTEGER, " + BookSchema.Book._bookPosition+ " INTEGER, " + BookSchema.Book._summary+ " VARCHAR(300));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

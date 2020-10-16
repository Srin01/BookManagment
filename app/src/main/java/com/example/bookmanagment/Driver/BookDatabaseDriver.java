package com.example.bookmanagment.Driver;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bookmanagment.Modal.Book;
import com.example.bookmanagment.SQL.BookSqlHelper;
import com.example.bookmanagment.Schema.BookSchema;

import java.util.ArrayList;

public class BookDatabaseDriver
{
    private BookSqlHelper bookSqlHelper;
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public BookDatabaseDriver(Context context)
    {
        bookSqlHelper = new BookSqlHelper(context);
        this.context = context;
        sqLiteDatabase = bookSqlHelper.getWritableDatabase();
    }

    public ArrayList<Book> getAllBooks()
    {
        ArrayList<Book> bookList = new ArrayList<>();

        String[] columns = {BookSchema.Book._id, BookSchema.Book._roomID, BookSchema.Book._shelfID, BookSchema.Book._rowNumber, BookSchema.Book._bookPosition, BookSchema.Book._summary};
        Cursor cursor = sqLiteDatabase.query(BookSchema.Book._tableName, columns, null, null, null, null, null);

        if(cursor != null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            do {
                int id = cursor.getInt(cursor.getColumnIndex(BookSchema.Book._id));
                int roomID = cursor.getInt(cursor.getColumnIndex(BookSchema.Book._roomID));
                int shelfID = cursor.getInt(cursor.getColumnIndex(BookSchema.Book._shelfID));
                int rowNumber = cursor.getInt(cursor.getColumnIndex(BookSchema.Book._rowNumber));
                int bookPosition = cursor.getInt(cursor.getColumnIndex(BookSchema.Book._bookPosition));
                String summary = cursor.getString(cursor.getColumnIndex(BookSchema.Book._summary));

                Book book = setBookDetails(id, roomID, shelfID, rowNumber, bookPosition, summary);
                bookList.add(book);

            }while (cursor.moveToNext());
        }

        return bookList;
    }

    private Book setBookDetails(int id, int roomID, int shelfID, int rowNumber, int bookPosition, String summary)
    {
        Book book = new Book();
        book.setId(id);
        book.setRoomID(roomID);
        book.setShelfID(shelfID);
        book.setRowNumber(rowNumber);
        book.setBookPositionInRow(bookPosition);
        book.setSummary(summary);

        return book;
    }

    public void insertNewBook(Book book)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(BookSchema.Book._roomID, book.getRoomID());
        contentValues.put(BookSchema.Book._shelfID, book.getShelfID());
        contentValues.put(BookSchema.Book._rowNumber, book.getRowNumber());
        contentValues.put(BookSchema.Book._bookPosition, book.getBookPositionInRow());

        long id =sqLiteDatabase.insert(BookSchema.Book._tableName, null, contentValues);
    }
}

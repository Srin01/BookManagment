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
        this.context = context;
        bookSqlHelper = new BookSqlHelper(context);
        sqLiteDatabase = bookSqlHelper.getWritableDatabase();
    }

    public ArrayList<Book> getAllBooks()
    {
        ArrayList<Book> bookList = new ArrayList<>();

        String[] columns = {BookSchema._bookId, BookSchema._roomID, BookSchema._shelfID, BookSchema._rowNumber, BookSchema._bookPosition, BookSchema._summary};
        Cursor cursor = sqLiteDatabase.query(BookSchema._tableName, columns, null, null, null, null, null);

        if(cursor != null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            do {
                int id = cursor.getInt(cursor.getColumnIndex(BookSchema._bookId));
                int roomID = cursor.getInt(cursor.getColumnIndex(BookSchema._roomID));
                int shelfID = cursor.getInt(cursor.getColumnIndex(BookSchema._shelfID));
                int rowNumber = cursor.getInt(cursor.getColumnIndex(BookSchema._rowNumber));
                int bookPosition = cursor.getInt(cursor.getColumnIndex(BookSchema._bookPosition));
                String summary = cursor.getString(cursor.getColumnIndex(BookSchema._summary));

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
        contentValues.put(BookSchema._roomID, book.getRoomID());
        contentValues.put(BookSchema._shelfID, book.getShelfID());
        contentValues.put(BookSchema._rowNumber, book.getRowNumber());
        contentValues.put(BookSchema._bookPosition, book.getBookPositionInRow());

        long id =sqLiteDatabase.insert(BookSchema._tableName, null, contentValues);
    }
}

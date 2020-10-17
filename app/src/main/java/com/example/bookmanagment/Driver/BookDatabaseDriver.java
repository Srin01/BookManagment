package com.example.bookmanagment.Driver;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bookmanagment.Modal.Book;
import com.example.bookmanagment.SQL.BookSqlHelper;
import com.example.bookmanagment.Schema.BookSchema;

import java.util.ArrayList;

import static com.example.bookmanagment.MainActivity.TAG;

public class BookDatabaseDriver
{
    SQLiteDatabase booksqLiteDatabase;
    BookSqlHelper bookSqlHelper;
    Context context;
    ArrayList<Book> bookList;
    ArrayList<Book> spclBookList;
    int id ;
    int roomID ;
    int shelfID;
    int rowNumber;
    int bookPosition ;
    String summary;
    String bookName;

    public BookDatabaseDriver(Context context)
    {
        this.context = context;
        bookSqlHelper = new BookSqlHelper(context);
        booksqLiteDatabase = bookSqlHelper.getWritableDatabase();
    }

    public ArrayList<Book> getAllBooks()
    {
        bookList = new ArrayList<>();

        String[] columns = {BookSchema._bookId, BookSchema._bookName ,BookSchema._roomID, BookSchema._shelfID, BookSchema._rowNumber, BookSchema._bookPosition, BookSchema._summary};
        Cursor cursor = booksqLiteDatabase.query(BookSchema._tableName, columns, null, null, null, null, null);

        if(cursor != null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            do {
                bindValues(cursor);
                Log.d(TAG, "getAllBooks: got book " +bookName + " from db " + roomID+ " ");
                Book book = new Book(id, bookName ,roomID, shelfID, rowNumber, bookPosition, summary);
                bookList.add(book);

            }while (cursor.moveToNext());
            cursor.close();
        }

        return bookList;
    }

    public ArrayList<Book> getBooksOfSpecificRoom(int roomIDFromIntent)
    {
        spclBookList = new ArrayList<>();
        Log.d(TAG, "getBooksOfSpecificRoom: got room id " + roomIDFromIntent);
        Log.d(TAG, "getBooksOfSpecificRoom: retrieving books from id  " + roomIDFromIntent);
        String[] columns = {BookSchema._bookId, BookSchema._bookName ,BookSchema._roomID, BookSchema._shelfID, BookSchema._rowNumber, BookSchema._bookPosition, BookSchema._summary};
        Cursor cursor = booksqLiteDatabase.rawQuery("SELECT * FROM book WHERE TRIM(room_id) = '"+roomIDFromIntent+"'", null);
        if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst())
        {
            do {
                bindValues(cursor);
                Log.d(TAG, "getAllBooks: got special book " +bookName + " from db ");
                Book book = new Book(id, bookName, roomID, shelfID, rowNumber, bookPosition, summary);
                spclBookList.add(book);

            }while (cursor.moveToNext());
            cursor.close();
        }
        return spclBookList;
    }

    private void bindValues(Cursor cursor)
    {
        id = cursor.getInt(cursor.getColumnIndex(BookSchema._bookId));
        bookName = cursor.getString(cursor.getColumnIndex(BookSchema._bookName));
        roomID = cursor.getInt(cursor.getColumnIndex(BookSchema._roomID));
        shelfID = cursor.getInt(cursor.getColumnIndex(BookSchema._shelfID));
        rowNumber = cursor.getInt(cursor.getColumnIndex(BookSchema._rowNumber));
        bookPosition = cursor.getInt(cursor.getColumnIndex(BookSchema._bookPosition));
        summary = cursor.getString(cursor.getColumnIndex(BookSchema._summary));
    }

    public void insertNewBook(Book book)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(BookSchema._roomID, book.getRoomID());
        contentValues.put(BookSchema._shelfID, book.getShelfID());
        contentValues.put(BookSchema._bookName, book.getBookName());
        contentValues.put(BookSchema._rowNumber, book.getRowNumber());
        contentValues.put(BookSchema._bookPosition, book.getBookPositionInRow());

        long id = booksqLiteDatabase.insert(BookSchema._tableName, null, contentValues);
        Log.d(TAG, "insertNewBook: " + id +" added to db");
    }


}

package com.example.bookmanagment.Driver;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.bookmanagment.Modal.Book;
import com.example.bookmanagment.SQL.BookSqlHelper;
import com.example.bookmanagment.Schema.BookSchema;

import java.io.ByteArrayOutputStream;
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
    int rowNumber;
    Bitmap bitmap;
    int bookPosition ;
    String summary;
    String bookName;
    byte[] image;

    public BookDatabaseDriver(Context context)
    {
        this.context = context;
        bookSqlHelper = new BookSqlHelper(context);
        booksqLiteDatabase = bookSqlHelper.getWritableDatabase();
    }

    public ArrayList<Book> getAllBooks()
    {
        bookList = new ArrayList<>();

        String[] columns = {BookSchema._bookId, BookSchema._bookName ,BookSchema._roomID,  BookSchema._rowNumber, BookSchema._bookPosition, BookSchema._summary, BookSchema._bookImage};
        Cursor cursor = booksqLiteDatabase.query(BookSchema._tableName, columns, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst() )
        {
            do {
                bindValues(cursor);
                bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
                Book book = new Book(id, bookName, roomID, rowNumber, bookPosition, summary, bitmap);
                bookList.add(book);

            }while (cursor.moveToNext());
            cursor.close();
        }
            return bookList;
    }

    public ArrayList<Book> getBooksOfSpecificRoomAndRow(int roomIDFromIntent, int rowId)
    {
        String selection = BookSchema._roomID + " = ? " + " AND " + BookSchema._rowNumber + " = ?";
        String[] selectionArgs = {String.valueOf(roomIDFromIntent), String.valueOf(rowId)};
        spclBookList = new ArrayList<>();
        String[] columns = {BookSchema._bookId, BookSchema._bookName ,BookSchema._roomID,  BookSchema._rowNumber, BookSchema._bookPosition, BookSchema._summary, BookSchema._bookImage};
        Cursor cursor = booksqLiteDatabase.query(BookSchema._tableName, columns, selection, selectionArgs,null,null, null);

        if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst())
        {
            do {
                bindValues(cursor);
                bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
                Book book = new Book(id, bookName, roomID, rowNumber, bookPosition, summary, bitmap);
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
        rowNumber = cursor.getInt(cursor.getColumnIndex(BookSchema._rowNumber));
        bookPosition = cursor.getInt(cursor.getColumnIndex(BookSchema._bookPosition));
        summary = cursor.getString(cursor.getColumnIndex(BookSchema._summary));
        image = cursor.getBlob(cursor.getColumnIndex(BookSchema._bookImage));
    }

    public void insertNewBook(Book book)
    {
        ContentValues contentValues = insertContentValues(book);
        long id = booksqLiteDatabase.insert(BookSchema._tableName, null, contentValues);
        Log.d(TAG, "insertNewBook: " + id +" added to db");
    }

    private ContentValues insertContentValues(Book book)
    {
        ContentValues contentValues = new ContentValues();
        Bitmap bitmap = book.getBitmapImage();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] bytes = stream.toByteArray();
        contentValues.put(BookSchema._roomID, book.getRoomID());
        contentValues.put(BookSchema._bookName, book.getBookName());
        contentValues.put(BookSchema._rowNumber, book.getRowNumber());
        contentValues.put(BookSchema._bookPosition, book.getBookPositionInRow());
        contentValues.put(BookSchema._bookImage,bytes);
        return contentValues;
    }
}

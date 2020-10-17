package com.example.bookmanagment.Expert;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.bookmanagment.Driver.BookDatabaseDriver;
import com.example.bookmanagment.Modal.Book;

import java.util.ArrayList;

import static com.example.bookmanagment.MainActivity.TAG;

public class BooksForRoomExpert
{
    BookDatabaseDriver bookDatabaseDriver;
    ArrayList<Book> bookList ;
    int roomId;
    public BooksForRoomExpert(int roomID, BookDatabaseDriver bookdatabaseDriver)
    {
        this.bookDatabaseDriver = bookdatabaseDriver;
        this.roomId = roomID;
        bookList = bookdatabaseDriver.getBooksOfSpecificRoom(roomID);
        Log.d(TAG, "BooksForRoomExpert: bookslist of special room number obtained");
    }

    public int getBookId(int bookPosition)
    {
        return bookList.get(bookPosition).getId();
    }

    public int getBookRoomId(int bookPosition)
    {
        return bookList.get(bookPosition).getRoomID();
    }

    public int getBookShelfNumber(int bookPosition)
    {
        return bookList.get(bookPosition).getShelfID();
    }

    public int getRowNumber(int bookPosition)
    {
        return bookList.get(bookPosition).getRowNumber();
    }

    public int getBookPosition(int bookPosition)
    {
        return bookList.get(bookPosition).getBookPositionInRow();
    }

    public String getSummary(int bookPosition)
    {
        return bookList.get(bookPosition).getSummary();
    }

    public int getTotalBooks()
    {

        return bookDatabaseDriver.getBooksOfSpecificRoom(roomId).size();
    }

    public void addNewBook(Book book)
    {
        bookDatabaseDriver.insertNewBook(book);
        bookList.add(book);
        Log.d(TAG, "addNewBook: Book " + book.getBookName() +"Added to db");
    }

    public String getBookName(int position)
    {
        return bookList.get(position).getBookName();
    }
    public Book getBookOfSpecificId(int id)
    {
        for (int i = 0; i < bookList.size(); i++)
        {
            if(bookList.get(i).getId() == id)
            {
                return bookList.get(i);
            }
        }
        return null;
    }
    public Bitmap getBitmapImage(int position)
    {
        return bookList.get(position).getBitmapImage();
    }
}

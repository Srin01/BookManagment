package com.example.bookmanagment.Expert;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.bookmanagment.Driver.BookDatabaseDriver;
import com.example.bookmanagment.Modal.Book;

import java.util.ArrayList;

import static com.example.bookmanagment.MainActivity.TAG;

public class BookExpert2
{
    BookDatabaseDriver bookDatabaseDriver;
    ArrayList<Book> bookList ;
    int roomId;

    public BookExpert2(int roomID, BookDatabaseDriver bookdatabaseDriver)
    {
        this.bookDatabaseDriver = bookdatabaseDriver;
        this.roomId = roomID;
        bookList = bookdatabaseDriver.getBooksOfSpecificRoomAndRow(roomID, 2);
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

    public int getRowNumber(int bookPosition)
    {
        return bookList.get(bookPosition).getRowNumber();
    }

    public int getBookPosition(int bookPosition)
    {
        return bookList.get(bookPosition).getBookPositionInRow();
    }

    public String getBookAuthor(int bookPosition)
    {
        return bookList.get(bookPosition).getBookAuthor();
    }

    public int getTotalBooks()
    {

        return bookDatabaseDriver.getBooksOfSpecificRoomAndRow(roomId, 2).size();
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


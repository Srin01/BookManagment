package com.example.bookmanagment.Expert;

import android.graphics.Bitmap;

import com.example.bookmanagment.Driver.BookDatabaseDriver;
import com.example.bookmanagment.Modal.Book;

import java.util.ArrayList;

public class BookExpertForSearch
{
    BookDatabaseDriver bookDatabaseDriver;
    ArrayList<Book> bookList ;

    public BookExpertForSearch(BookDatabaseDriver bookdatabaseDriver)
    {
        this.bookDatabaseDriver = bookdatabaseDriver;
        bookList = bookdatabaseDriver.getAllBooks();
    }

    public ArrayList<Book> getAllBooks()
    {
        return bookList;
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
        return bookList.size();
    }

    public void addNewBook(Book book)
    {
        bookDatabaseDriver.insertNewBook(book);
        bookList.add(book);
    }
}

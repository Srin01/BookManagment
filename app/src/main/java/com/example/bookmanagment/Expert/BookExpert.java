package com.example.bookmanagment.Expert;

import com.example.bookmanagment.Driver.BookDatabaseDriver;
import com.example.bookmanagment.Modal.Book;

import java.util.ArrayList;

public class BookExpert
{
    BookDatabaseDriver bookDatabaseDriver;
    ArrayList<Book> bookList ;
    public BookExpert(BookDatabaseDriver bookdatabaseDriver)
    {
        this.bookDatabaseDriver = bookdatabaseDriver;
        bookList = bookdatabaseDriver.getAllBooks();
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
        return bookList.size();
    }

    public void addNewBook(Book book)
    {
        bookDatabaseDriver.insertNewBook(book);
        bookList.add(book);
    }
}

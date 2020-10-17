package com.example.bookmanagment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.bookmanagment.Adapter.BookAdapter;
import com.example.bookmanagment.Driver.BookDatabaseDriver;
import com.example.bookmanagment.Expert.BooksForRoomExpert;
import com.example.bookmanagment.Modal.Book;

import java.util.ArrayList;

public class ShelfBookActivity extends AppCompatActivity
{
    RecyclerView booksListRecyclerView;
    BookDatabaseDriver bookDatabaseDriver;
    BookAdapter bookAdapter;
    BooksForRoomExpert booksForRoomExpert ;
    String TAG = "myTag";
    int roomId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf_book);
        bindViews();
        getDataFromIntent();
        setAdapter();
        Log.d(TAG, "onCreate: ShelfBookActivity Started");
        printDetails();
    }

    void setAdapter()
    {
        booksForRoomExpert = new BooksForRoomExpert(roomId, bookDatabaseDriver);
        bookAdapter = new BookAdapter(this, booksForRoomExpert);
        booksListRecyclerView.setAdapter(bookAdapter);
    }

    private void bindViews()
    {
        booksListRecyclerView = findViewById(R.id.books_recyclerView);
        booksListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookDatabaseDriver = new BookDatabaseDriver(this);
    }

    private void getDataFromIntent()
    {
        Intent intent = getIntent();
        if(intent.hasExtra(MainActivity.ROOM_NAME) && intent.hasExtra(MainActivity.ROOM_ID) && intent.hasExtra(MainActivity.SHELF_NUMBER))
        {
            roomId = intent.getIntExtra(MainActivity.ROOM_ID, 0);
        }
    }

    public void printDetails()
    {
        ArrayList<Book> Books = bookDatabaseDriver.getAllBooks();

        Log.d(TAG, "Your database has " + Books.size() + " books ");
        for (int i = 0; i < Books.size(); i++)
        {
            Log.d(TAG, Books.get(i).getId() + " " +Books.get(i).getBookName() + " " + Books.get(i).getRowNumber());
        }
    }


    public void OnclickAddExtraBookByOpeningActitvity(View view)
    {
        Intent addRoomIntent = new Intent(this,AddExtraBookActivity.class);
        startActivityForResult(addRoomIntent, 1);
        bookAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1)
        {
            if(resultCode == RESULT_OK)
            {
                assert data != null;
                String bookNameValue = data.getStringExtra("bookName");
                int rowNumberValue = data.getIntExtra("RowNumber", 1);
                Book book = new Book(bookNameValue, rowNumberValue);
                booksForRoomExpert.addNewBook(book);
                bookAdapter.notifyDataSetChanged();
            }
        }
    }
}
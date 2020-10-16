package com.example.bookmanagment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bookmanagment.Adapter.BookAdapter;
import com.example.bookmanagment.Driver.BookDatabaseDriver;
import com.example.bookmanagment.Expert.BookExpert;
import com.example.bookmanagment.Modal.Book;
import com.example.bookmanagment.Modal.Room;

public class ShelfBookActivity extends AppCompatActivity
{
    RecyclerView booksListRecyclerView;
    BookDatabaseDriver bookDatabaseDriver;
    BookAdapter bookAdapter;
    BookExpert bookExpert;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf_book);
        bindViews();
    }

    private void bindViews()
    {
        booksListRecyclerView = findViewById(R.id.books_recyclerView);
        booksListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookDatabaseDriver = new BookDatabaseDriver(this);
        bookExpert = new BookExpert(bookDatabaseDriver);
        bookAdapter = new BookAdapter(this, bookExpert);
        booksListRecyclerView.setAdapter(bookAdapter);
    }


    public void OnclickAddExtraBook(View view)
    {
        Intent addRoomIntent = new Intent(this,AddExtraRoom.class);
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
                String roomName = data.getStringExtra("bookName");
                int numberOfShelves = data.getIntExtra("RowNumber", 1);
                Book book = new Book(roomName, numberOfShelves);
                bookExpert.addNewBook(book);
                bookAdapter.notifyDataSetChanged();
            }
        }
    }
}
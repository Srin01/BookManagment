package com.example.bookmanagment;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmanagment.Adapter.SearchAdapter;
import com.example.bookmanagment.Driver.BookDatabaseDriver;
import com.example.bookmanagment.Modal.Book;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity
{
    SearchView searchView;
    ListView listView;
    ArrayAdapter<String> adapter;
    BookDatabaseDriver bookDatabaseDriver = new BookDatabaseDriver(this);

    ArrayList<Book> books = bookDatabaseDriver.getAllBooks();
    ArrayList<String> bookNames = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        bindViews();

        for(int i = 0; i < books.size(); i++)
        {
            bookNames.add(books.get(i).getBookName());
        }

        adapter = new ArrayAdapter<>(this, R.layout.search_list_item, bookNames);
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                adapter.getFilter().filter(s);
                return false;
            }
        });
    }

    private void bindViews()
    {
        searchView = findViewById(R.id.searchView);
        listView = findViewById(R.id.list_view);
    }
}

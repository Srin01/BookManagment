package com.example.bookmanagment.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmanagment.Adapter.SearchAdapter;
import com.example.bookmanagment.Driver.BookDatabaseDriver;
import com.example.bookmanagment.Expert.BookExpertForSearch;
import com.example.bookmanagment.Modal.Book;
import com.example.bookmanagment.R;

import java.util.ArrayList;

import static com.example.bookmanagment.Activities.BookViewerActivity.ROW_ID;
import static com.example.bookmanagment.Activities.ShelfBookActivity.BOOK_ID;
import static com.example.bookmanagment.Activities.ShelfBookActivity.BOOK_POS;
import static com.example.bookmanagment.Activities.ShelfBookActivity.BOOK_ROOM_ID;

public class SearchActivity extends AppCompatActivity implements SearchAdapter.OnBookSearchListener
{
    private SearchView searchView;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private BookExpertForSearch bookExpertForSearch;

    private ArrayList<Book> books ;
    private ArrayList<String> bookNames = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        bindViews();
        addBooks();
        adapter = new ArrayAdapter<>(this, R.layout.search_list_item,R.id.book_name, bookNames);
        listView.setAdapter(adapter);
        implementSearchView();
    }

    private void addBooks()
    {
        for(int i = 0; i < books.size(); i++)
        {
            Log.d(MainActivity.TAG, "onCreate: book created for search "+ books.get(i).getBookName());
            bookNames.add(books.get(i).getBookName());
        }
    }

    private void implementSearchView()
    {
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
        BookDatabaseDriver bookDatabaseDriver = new BookDatabaseDriver(this);
        bookExpertForSearch = new BookExpertForSearch(bookDatabaseDriver);
        books = bookExpertForSearch.getAllBooks();
    }

    @Override
    public void onBookClick(int position) {
        Log.d(MainActivity.TAG, "onBookClick: clicked on Book " + bookExpertForSearch.getBookName(position));
        Intent intent = addIntentValues(position);
        Log.d(MainActivity.TAG, "onBookClick: position " + position + " passed");
        startActivity(intent);
    }

    private Intent addIntentValues(int position)
    {
        Intent intent = new Intent(this, BookViewerActivity.class);
        intent.putExtra(BOOK_ID, bookExpertForSearch.getBookId(position));
        intent.putExtra(BOOK_ROOM_ID, bookExpertForSearch.getBookRoomId(position));
        intent.putExtra(BOOK_POS, position);
        intent.putExtra(ROW_ID, bookExpertForSearch.getRowNumber(position));
        return intent;
    }
}

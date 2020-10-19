package com.example.bookmanagment.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.bookmanagment.Adapter.BookAdapter;
import com.example.bookmanagment.Adapter.BookAdapter2;
import com.example.bookmanagment.Adapter.BookAdapter3;
import com.example.bookmanagment.Driver.BookDatabaseDriver;
import com.example.bookmanagment.Expert.BookExpert2;
import com.example.bookmanagment.Expert.BookExpert3;
import com.example.bookmanagment.Expert.BookExpertForRoomAndRow;
import com.example.bookmanagment.Modal.Book;
import com.example.bookmanagment.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import static com.example.bookmanagment.Activities.BookViewerActivity.ROW_ID;

public class ShelfBookActivity extends AppCompatActivity implements BookAdapter.OnBookListerner,BookAdapter3.OnBookListerner3,BookAdapter2.OnBookListerner2
{
    public static final String BOOK_ID = "book_id" ;
    public static final String BOOK_POS ="book_pos" ;
    public static final String BOOK_ROOM_ID = "book_room_id";
    public static final String TAG = "myTag";
    private RecyclerView booksListRecyclerViewrow1;
    private RecyclerView booksListRecyclerViewrow2;
    private RecyclerView booksListRecyclerViewrow3;
    private BookDatabaseDriver bookDatabaseDriver;
    private BookAdapter bookAdapter1;
    private BookAdapter2 bookAdapter2;
    private BookAdapter3 bookAdapter3;
    private BookExpertForRoomAndRow bookExpertForRoomAndRow1;
    private BookExpert2 bookExpertForRoomAndRow2;
    private BookExpert3 bookExpertForRoomAndRow3;
    private Bitmap bitmap;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private String getBookPosTAG = "myTag";
    private int roomId;
    private String roomName;
    private String bookNameValue;
    int rowId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf_book);
        bindViews();
        setUpToolbar();
        setUpNavigationDrawerIcon();
        setUpListeners();

        Log.d(TAG, "onCreate: ShelfBookActivity Started");
        printDetails();
    }

    void setAdapter()
    {
        getDataFromIntent();
        createNewExperts();
        Log.d(TAG, "setAdapter: Adapter with room ID set and row Id = "+ rowId );
        bookAdapter1 = new BookAdapter(this, bookExpertForRoomAndRow1,this);
        bookAdapter2 = new BookAdapter2(this, bookExpertForRoomAndRow2,this);
        bookAdapter3 = new BookAdapter3(this, bookExpertForRoomAndRow3,this);
        booksListRecyclerViewrow1.setAdapter(bookAdapter1);
        booksListRecyclerViewrow2.setAdapter(bookAdapter2);
        booksListRecyclerViewrow3.setAdapter(bookAdapter3);
    }

    private void createNewExperts()
    {
        bookExpertForRoomAndRow1 = new BookExpertForRoomAndRow(roomId, bookDatabaseDriver);
        bookExpertForRoomAndRow2 = new BookExpert2(roomId, bookDatabaseDriver);
        bookExpertForRoomAndRow3 = new BookExpert3(roomId, bookDatabaseDriver);
    }

    private void bindViews()
    {
        booksListRecyclerViewrow1 = findViewById(R.id.books_recyclerView_row1);
        booksListRecyclerViewrow2 = findViewById(R.id.books_recyclerView_row2);
        booksListRecyclerViewrow3 = findViewById(R.id.books_recyclerView_row3);
        setRecyclerView();
        bookDatabaseDriver = new BookDatabaseDriver(this);
        setAdapter();
    }

    private void setRecyclerView()
    {
        booksListRecyclerViewrow1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        booksListRecyclerViewrow2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        booksListRecyclerViewrow3.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void getDataFromIntent()
    {
        Intent intent = getIntent();
        if(intent.hasExtra(MainActivity.ROOM_NAME) && intent.hasExtra(MainActivity.ROOM_ID))
        {
            roomId = intent.getIntExtra(MainActivity.ROOM_ID, 0);
            roomName = intent.getStringExtra(MainActivity.ROOM_NAME);
        }
        Log.d(TAG, "getDataFromIntent: got "+ roomId + " " +roomName);
    }

    public void printDetails()
    {
        ArrayList<Book> Books = bookDatabaseDriver.getAllBooks();

        Log.d(TAG, "Your database has " + Books.size() + " books ");
        for (int i = 0; i < Books.size(); i++)
        {
            Log.d(TAG, Books.get(i).getId() + " " +Books.get(i).getBookName() + " " + Books.get(i).getRowNumber() + " " + Books.get(i).getRoomID());
        }
    }


    public void OnclickAddExtraBookByOpeningActitvity(View view)
    {
        Intent addRoomIntent = new Intent(this, AddExtraBookActivity.class);
        startActivityForResult(addRoomIntent, 1);
        Log.d(TAG, "OnclickAddExtraBookByOpeningActitvity: Activity intent clicked");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1)
        {
            if(resultCode == RESULT_OK)
            {
                extractValuesFromIntent(data);
                Book book = new Book(bookNameValue, rowId, roomId, bitmap);
                Log.d(TAG, "onActivityResult: new book of room id " + roomId + " is added");
                try {
                    if (rowId == 1) {
                        bookExpertForRoomAndRow1.addNewBook(book);
                        bookAdapter1.notifyDataSetChanged();
                    } else if (rowId == 2) {
                        bookExpertForRoomAndRow2.addNewBook(book);
                        bookAdapter2.notifyDataSetChanged();
                    } else {
                        bookExpertForRoomAndRow3.addNewBook(book);
                        bookAdapter3.notifyDataSetChanged();
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                    Toast.makeText(this, "Please provide a book cover photo", Toast.LENGTH_SHORT).show();
                }
                Log.d(TAG, "onActivityResult: got data" + bookNameValue + " " + rowId);
                Log.d(TAG, "onActivityResult: Book" + bookNameValue +" sent to booksRoomExpert");
            }
        }
    }

    private void extractValuesFromIntent(Intent data)
    {
        assert data != null;
        bookNameValue = data.getStringExtra("bookName");
        rowId = data.getIntExtra("RowNumber", 1);
        bitmap = data.getParcelableExtra("bookImage");
    }

    @Override
    public void onBookClick1(int position) {
        Log.d(TAG, "onBookClick: clicked on Book " + bookExpertForRoomAndRow1.getBookName(position));
        Intent intent = new Intent(this, BookViewerActivity.class);
        intent.putExtra(BOOK_ID, bookExpertForRoomAndRow1.getBookId(position));
        intent.putExtra(BOOK_ROOM_ID, bookExpertForRoomAndRow1.getBookRoomId(position));
        intent.putExtra(BOOK_POS, position);
        intent.putExtra(ROW_ID, bookExpertForRoomAndRow1.getRowNumber(position));
        Log.d(TAG, "onBookClick: position " + position + " passed");
        startActivity(intent);
    }

    @Override
    public void onBookClick2(int position) {
        Log.d(TAG, "onBookClick: clicked on Book " + bookExpertForRoomAndRow2.getBookName(position));
        Intent intent = new Intent(this, BookViewerActivity.class);
        intent.putExtra(BOOK_ID, bookExpertForRoomAndRow2.getBookId(position));
        intent.putExtra(BOOK_ROOM_ID, bookExpertForRoomAndRow2.getBookRoomId(position));
        intent.putExtra(BOOK_POS, position);
        intent.putExtra(ROW_ID, bookExpertForRoomAndRow2.getRowNumber(position));
        Log.d(TAG, "onBookClick: position " + position + " passed");
        startActivity(intent);
    }

    @Override
    public void onBookClick3(int position) {
        Log.d(TAG, "onBookClick: clicked on Book " + bookExpertForRoomAndRow3.getBookName(position));
        Intent intent = new Intent(this, BookViewerActivity.class);
        intent.putExtra(BOOK_ID, bookExpertForRoomAndRow3.getBookId(position));
        intent.putExtra(BOOK_ROOM_ID, bookExpertForRoomAndRow3.getBookRoomId(position));
        intent.putExtra(BOOK_POS, position);
        intent.putExtra(ROW_ID, bookExpertForRoomAndRow3.getRowNumber(position));
        Log.d(TAG, "onBookClick: position " + position + " passed");
        startActivity(intent);
    }

    private void setUpNavigationDrawerIcon()
    {
        drawerLayout = findViewById(R.id.room_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.closed);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
    }

    private void setUpToolbar()
    {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setUpListeners()
    {
        NavigationView navigationView = findViewById(R.id.room_navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.item_search:
                        Intent searchIntent = new Intent(ShelfBookActivity.this, SearchActivity.class);
                        searchIntent.putExtra("roomId", roomId);
                        startActivity(searchIntent);
                        return true;
                    case R.id.item_home:
                        Intent mainIntent = new Intent(ShelfBookActivity.this, MainActivity.class);
                        startActivity(mainIntent);
                        return true;
                }
                return false;
            }
        });
    }
}
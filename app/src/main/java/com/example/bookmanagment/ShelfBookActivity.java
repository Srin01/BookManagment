package com.example.bookmanagment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.bookmanagment.Adapter.BookAdapter;
import com.example.bookmanagment.Driver.BookDatabaseDriver;
import com.example.bookmanagment.Expert.BooksForRoomExpert;
import com.example.bookmanagment.Modal.Book;

import java.util.ArrayList;

public class ShelfBookActivity extends AppCompatActivity implements BookAdapter.OnBookListerner
{
    public static final String BOOK_ID = "book_id" ;
    public static final String BOOK_POS ="book_pos" ;
    public static final String BOOK_ROOM_ID = "book_room_id";
    RecyclerView booksListRecyclerView;
    BookDatabaseDriver bookDatabaseDriver;
    BookAdapter bookAdapter;
    BooksForRoomExpert booksForRoomExpert ;
    Bitmap bitmap;
    String TAG = "myTag";
    int roomId;
    String roomName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf_book);
        bindViews();
        Log.d(TAG, "onCreate: ShelfBookActivity Started");
        printDetails();
    }

    void setAdapter()
    {
        getDataFromIntent();
        booksForRoomExpert = new BooksForRoomExpert(roomId, bookDatabaseDriver);
        Log.d(TAG, "setAdapter: Adapter with room ID set");
        bookAdapter = new BookAdapter(this, booksForRoomExpert,this);
        booksListRecyclerView.setAdapter(bookAdapter);
    }

    private void bindViews()
    {
        booksListRecyclerView = findViewById(R.id.books_recyclerView);
        booksListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookDatabaseDriver = new BookDatabaseDriver(this);
        setAdapter();
    }

    private void getDataFromIntent()
    {
        Intent intent = getIntent();
        if(intent.hasExtra(MainActivity.ROOM_NAME) && intent.hasExtra(MainActivity.ROOM_ID) && intent.hasExtra(MainActivity.SHELF_NUMBER))
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
        Intent addRoomIntent = new Intent(this,AddExtraBookActivity.class);
        startActivityForResult(addRoomIntent, 1);
        bookAdapter.notifyDataSetChanged();
        Log.d(TAG, "OnclickAddExtraBookByOpeningActitvity: Activity intent clicked");
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
                bitmap = data.getParcelableExtra("bookImage");
                Book book = new Book(bookNameValue, rowNumberValue, roomId, bitmap);
                Log.d(TAG, "onActivityResult: new book of room id " + roomId + " is added");
                booksForRoomExpert.addNewBook(book);
                bookAdapter.notifyDataSetChanged();
                Log.d(TAG, "onActivityResult: got data" + bookNameValue + " " + rowNumberValue);
                Log.d(TAG, "onActivityResult: Book" + bookNameValue +" sent to booksRoomExpert");
            }
        }
    }

    @Override
    public void onBookClick(int position)
    {
        Log.d(TAG, "onBookClick: clicked on Book " + booksForRoomExpert.getBookName(position));
        Intent intent = new Intent(this, BookViewerActivity.class);
        intent.putExtra(BOOK_ID, booksForRoomExpert.getBookId(position));
        intent.putExtra(BOOK_ROOM_ID, booksForRoomExpert.getBookRoomId(position));
        intent.putExtra(BOOK_POS, position);
        Log.d(TAG, "onBookClick: position " + position + " passed");
        startActivity(intent);
    }
}
package com.example.bookmanagment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookmanagment.Driver.BookDatabaseDriver;
import com.example.bookmanagment.Expert.BooksForRoomExpert;

import static com.example.bookmanagment.ShelfBookActivity.BOOK_ID;
import static com.example.bookmanagment.ShelfBookActivity.BOOK_POS;
import static com.example.bookmanagment.ShelfBookActivity.BOOK_ROOM_ID;

public class BookViewerActivity extends AppCompatActivity
{
    TextView bookName;
    TextView bookAuthor;
    TextView bookLocation;
    TextView Summary;
    ImageView imageView;
    BooksForRoomExpert booksForRoomExpert;
    int room_id;
    int position;
    public final static String TAG = "myTag";
    BookDatabaseDriver bookDatabaseDriver;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_viewer);
        Log.d(TAG, "onCreate: started for bookViewer Activity");
        bindViews();
        SetViews();
    }

    private void bindViews() {
        bookName = findViewById(R.id.textView_bookName);
        bookAuthor = findViewById(R.id.textView_bookAuthor);
        bookLocation = findViewById(R.id.textView_Location);
        Summary = findViewById(R.id.textView_bookSummary);
        imageView = findViewById(R.id.imageViewSpecial);
        bookDatabaseDriver = new BookDatabaseDriver(this);
        getDataFromShelfIntent();
        booksForRoomExpert = new BooksForRoomExpert(room_id,bookDatabaseDriver);
    }

    private void getDataFromShelfIntent()
    {
        Log.d(TAG, "getDataFromShelfIntent: revived data from shelf intent position " + getIntent().getIntExtra(BOOK_POS,0));
        Log.d(TAG, "getDataFromShelfIntent: revived data from shelf intent book name " + getIntent().getIntExtra(BOOK_ID,0));
        position = getIntent().getIntExtra(BOOK_ID,0);
        room_id = getIntent().getIntExtra(BOOK_ROOM_ID,0);
        id = getIntent().getIntExtra(BOOK_ID, 0);
    }

    private void SetViews()
    {
        bookName.setText(booksForRoomExpert.getBookOfSpecificId(id).getBookName());
        Summary.setText(booksForRoomExpert.getBookOfSpecificId(id).getSummary());
        imageView.setImageBitmap(booksForRoomExpert.getBookOfSpecificId(id).getBitmapImage());
    }

    public void onClickOpenPdf(View view) {
    }
}
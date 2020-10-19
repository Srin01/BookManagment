package com.example.bookmanagment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookmanagment.Driver.BookDatabaseDriver;
import com.example.bookmanagment.Expert.BookExpert2;
import com.example.bookmanagment.Expert.BookExpert3;
import com.example.bookmanagment.Expert.BookExpertForRoomAndRow;

import static com.example.bookmanagment.ShelfBookActivity.BOOK_ID;
import static com.example.bookmanagment.ShelfBookActivity.BOOK_POS;
import static com.example.bookmanagment.ShelfBookActivity.BOOK_ROOM_ID;

public class BookViewerActivity extends AppCompatActivity
{
    public static final String ROW_ID = "row_Id";
    TextView bookName;
    TextView bookAuthor;
    TextView bookLocation;
    ImageView imageView;
    BookExpertForRoomAndRow bookExpertForRoomAndRow;
    BookExpert2 bookExpert2;
    BookExpert3 bookExpert3;
    int room_id;
    int position;
    public final static String TAG = "myTag";
    BookDatabaseDriver bookDatabaseDriver;
    private int id;
    private int row_Id;

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
        bookAuthor = findViewById(R.id.textView_authorName);
        bookLocation = findViewById(R.id.textView_Location);
        imageView = findViewById(R.id.imageViewSpecial);
        bookDatabaseDriver = new BookDatabaseDriver(this);
        getDataFromShelfIntent();
        bookExpertForRoomAndRow = new BookExpertForRoomAndRow(room_id,bookDatabaseDriver);
        bookExpert2 = new BookExpert2(room_id, bookDatabaseDriver);
        bookExpert3 = new BookExpert3(room_id, bookDatabaseDriver);
    }

    private void getDataFromShelfIntent()
    {
        Log.d(TAG, "getDataFromShelfIntent: revived data from shelf intent position " + getIntent().getIntExtra(BOOK_POS,0));
        Log.d(TAG, "getDataFromShelfIntent: revived data from shelf intent book name " + getIntent().getIntExtra(BOOK_ID,0));
        position = getIntent().getIntExtra(BOOK_ID,0);
        room_id = getIntent().getIntExtra(BOOK_ROOM_ID,0);
        id = getIntent().getIntExtra(BOOK_ID, 0);
        row_Id = getIntent().getIntExtra(ROW_ID, 0);
    }

    private void SetViews()
    {
        if(row_Id == 1) {
            bookName.setText(bookExpertForRoomAndRow.getBookOfSpecificId(id).getBookName());
            bookAuthor.setText(bookExpertForRoomAndRow.getBookOfSpecificId(id).getBookAuthor());
            imageView.setImageBitmap(bookExpertForRoomAndRow.getBookOfSpecificId(id).getBitmapImage());
        }
        else if(row_Id == 2)
        {
            bookName.setText(bookExpert2.getBookOfSpecificId(id).getBookName());
            bookAuthor.setText(bookExpert2.getBookOfSpecificId(id).getBookAuthor());
            imageView.setImageBitmap(bookExpert2.getBookOfSpecificId(id).getBitmapImage());
        }
        else
        {
            bookName.setText(bookExpert3.getBookOfSpecificId(id).getBookName());
            bookAuthor.setText(bookExpert3.getBookOfSpecificId(id).getBookAuthor());
            imageView.setImageBitmap(bookExpert3.getBookOfSpecificId(id).getBitmapImage());
        }
    }

    public void onClickOpenPdf(View view)
    {
        Intent intent = new Intent(this, PDFActivity.class);
        startActivity(intent);
    }
}
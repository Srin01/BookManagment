package com.example.bookmanagment.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmanagment.Driver.BookDatabaseDriver;
import com.example.bookmanagment.Expert.BookExpert2;
import com.example.bookmanagment.Expert.BookExpert3;
import com.example.bookmanagment.Expert.BookExpertForRoomAndRow;
import com.example.bookmanagment.R;

import static com.example.bookmanagment.Activities.ShelfBookActivity.BOOK_ID;
import static com.example.bookmanagment.Activities.ShelfBookActivity.BOOK_POS;
import static com.example.bookmanagment.Activities.ShelfBookActivity.BOOK_ROOM_ID;

public class BookViewerActivity extends AppCompatActivity
{
    public static final String ROW_ID = "row_Id";
    private TextView bookName;
    private TextView bookLocation;
    private ImageView imageView;
    private BookExpertForRoomAndRow bookExpertForRoomAndRow;
    private BookExpert2 bookExpert2;
    private BookExpert3 bookExpert3;
    int room_id;
    int position;
    public final static String TAG = "myTag";
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
        bookLocation = findViewById(R.id.textView_Location);
        imageView = findViewById(R.id.imageViewSpecial);
        BookDatabaseDriver bookDatabaseDriver = new BookDatabaseDriver(this);
        getDataFromShelfIntent();
        bookExpertForRoomAndRow = new BookExpertForRoomAndRow(room_id, bookDatabaseDriver);
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

    @SuppressLint("SetTextI18n")
    private void SetViews()
    {
        try {
            if (row_Id == 1) {
                bookName.setText(bookExpertForRoomAndRow.getBookOfSpecificId(id).getBookName());
                imageView.setImageBitmap(bookExpertForRoomAndRow.getBookOfSpecificId(id).getBitmapImage());
                bookLocation.setText("Location : "+ bookExpertForRoomAndRow.getBookOfSpecificId(id).getRowNumber() + " row " + bookExpertForRoomAndRow.getBookOfSpecificId(id).getBookPositionInRow() + " position");
            } else if (row_Id == 2) {
                bookName.setText(bookExpert2.getBookOfSpecificId(id).getBookName());
                imageView.setImageBitmap(bookExpert2.getBookOfSpecificId(id).getBitmapImage());
                bookLocation.setText("Location : "+ bookExpert2.getBookOfSpecificId(id).getRowNumber() + " row " + bookExpert2.getBookOfSpecificId(id).getBookPositionInRow() + " position");
            } else {
                bookName.setText(bookExpert3.getBookOfSpecificId(id).getBookName());
                imageView.setImageBitmap(bookExpert3.getBookOfSpecificId(id).getBitmapImage());
                bookLocation.setText("Location : "+ bookExpert3.getBookOfSpecificId(id).getRowNumber() + " row " + bookExpert3.getBookOfSpecificId(id).getBookPositionInRow() + " position");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Intent intent = new Intent(this, MainActivity.class);
            Toast.makeText(this, "Login Again", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
    }

    public void onClickOpenPdf(View view)
    {
        Intent intent = new Intent(this, PDFActivity.class);
        startActivity(intent);
    }
}
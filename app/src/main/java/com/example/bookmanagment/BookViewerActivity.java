package com.example.bookmanagment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class BookViewerActivity extends AppCompatActivity
{
    TextView bookName;
    TextView bookAuthor;
    TextView bookLocation;
    TextView Summary;
    public final static String TAG = "myTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_viewer);
        Log.d(TAG, "onCreate: started for bookViewer Activity");
        bindViews();
    }

    private void bindViews() {
        bookName = findViewById(R.id.textView_bookName);
        bookAuthor = findViewById(R.id.textView_bookAuthor);
        bookLocation = findViewById(R.id.textView_Location);
        Summary = findViewById(R.id.textView_bookSummary);
    }
}
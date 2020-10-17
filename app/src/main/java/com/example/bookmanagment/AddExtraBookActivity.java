package com.example.bookmanagment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.bookmanagment.Adapter.BookAdapter;

public class AddExtraBookActivity extends AppCompatActivity
{
    TextView bookName;
    TextView rowNumber;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_extra_book);
        bindViews();
    }

    private void bindViews()
    {
        bookName = findViewById(R.id.textInputBookName);
        rowNumber = findViewById(R.id.textInputRowNumber);
        submitButton = findViewById(R.id.submitButton);
    }

    public void onClickAddExtraBook(View view)
    {
        String bookNameValue = bookName.getText().toString();
        int rowNumberValue = Integer.parseInt(rowNumber.getText().toString());
        Intent intent = getIntent();
        intent.putExtra("bookName", bookNameValue);
        intent.putExtra("rowNumber", rowNumberValue);
        setResult(RESULT_OK, intent);
        finish();
    }
}
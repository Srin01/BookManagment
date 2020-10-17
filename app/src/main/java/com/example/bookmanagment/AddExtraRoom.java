package com.example.bookmanagment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddExtraRoom extends AppCompatActivity
{
    TextView roomName;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_extra_room);
        bindViews();
    }

    private void bindViews()
    {
        roomName = findViewById(R.id.textinputRoomName);
        submitButton = findViewById(R.id.submitButton);
    }

    public void onClickAddExtraRoom(View view)
    {
        String roomNameValue = roomName.getText().toString();
        Intent intent = getIntent();
        intent.putExtra("roomName", roomNameValue);
        setResult(RESULT_OK, intent);
        finish();
    }
}
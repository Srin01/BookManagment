package com.example.bookmanagment.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bookmanagment.R;

public class AddExtraRoomActivity extends AppCompatActivity
{
    private TextView roomName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_extra_room);
        bindViews();
    }

    private void bindViews()
    {
        roomName = findViewById(R.id.textinputRoomName);
    }

    public void onClickAddExtraRoom(View view)
    {
        String roomNameValue = roomName.getText().toString();
        addIntentValues(getIntent(), roomNameValue);
        finish();
    }

    private void addIntentValues(Intent intent, String roomNameValue)
    {
        intent.putExtra("roomName", roomNameValue);
        setResult(RESULT_OK, intent);
    }
}
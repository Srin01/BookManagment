package com.example.bookmanagment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookmanagment.Adapter.RoomAdapter;
import com.example.bookmanagment.Driver.RoomDatabaseDriver;
import com.example.bookmanagment.Expert.RoomExpert;
import com.example.bookmanagment.Modal.Room;

public class AddExtraRoomActivity extends AppCompatActivity
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
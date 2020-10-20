package com.example.bookmanagment.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmanagment.R;
import com.google.android.material.textfield.TextInputLayout;

public class AddExtraRoomActivity extends AppCompatActivity
{
    private TextView roomName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_extra_room);
        bindViews();
        setUpListener();
    }

    private void setUpListener()
    {
        roomName.addTextChangedListener(new TextWatcher() {
            TextInputLayout roomNameLayout = findViewById(R.id.textInputLayout_RoomName);
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equals(""))
                {
                    roomNameLayout.setError("Please Enter A Valid Room Name");
                    roomNameLayout.setErrorEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void bindViews()
    {
        roomName = findViewById(R.id.textinputRoomName);
    }

    public void onClickAddExtraRoom(View view)
    {
        if(roomName.getText().toString().equals(""))
        {
            Toast.makeText(this, "Please Enter A Valid RoomName", Toast.LENGTH_SHORT).show();
        }
        else {
            String roomNameValue = roomName.getText().toString();
            addIntentValues(getIntent(), roomNameValue);
            finish();
        }
    }

    private void addIntentValues(Intent intent, String roomNameValue)
    {
        intent.putExtra("roomName", roomNameValue);
        setResult(RESULT_OK, intent);
    }
}
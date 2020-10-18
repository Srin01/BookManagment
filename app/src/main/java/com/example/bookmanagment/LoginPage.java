package com.example.bookmanagment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmanagment.Driver.UserDataBaseDriver;
import com.example.bookmanagment.Expert.UserExpert;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import static com.example.bookmanagment.MainActivity.ROOM_ID;
import static com.example.bookmanagment.MainActivity.ROOM_NAME;

public class LoginPage extends AppCompatActivity
{
    private TextInputEditText username;
    private TextInputEditText password;
    UserExpert userExpert;
    UserDataBaseDriver userDataBaseDriver;
    public static final String TAG = "myTag";
    public static final String USER_NAME = "userName";
    String roomName;
    String usernameValue ;
    String userPasswordValue;
    int roomId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        bindViews();
    }

    private void bindViews()
    {
        username = findViewById(R.id.textInputUsername);
        password = findViewById(R.id.textInputPassword);
        userDataBaseDriver = new UserDataBaseDriver(this);
        userExpert = new UserExpert(userDataBaseDriver);
    }

    private void getIntentdata()
    {
        Intent intent = getIntent();
        roomName = intent.getStringExtra(ROOM_NAME);
        roomId = intent.getIntExtra(ROOM_ID, 1);
    }

    public void onClickLogin(View view)
    {
        getIntentdata();
         usernameValue = Objects.requireNonNull(username.getText()).toString();
         userPasswordValue = Objects.requireNonNull(password.getText()).toString();
        //code to check if the entered username and password are matching the correct credentials or not

        if(!userExpert.ifUserExistForSpecifiRoom(usernameValue, roomId))
        {
            Toast.makeText(this, "User Name Invalid", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(userExpert.validatePassword(usernameValue, userPasswordValue))
            {
                Toast.makeText(this, "Login Successful welcome back  " + usernameValue, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onClickLogin: login successful");
                startActivityAfterLogin();
            }
        }
    }

    private void startActivityAfterLogin()
    {
        Intent intent = new Intent(this, ShelfBookActivity.class);
        intent.putExtra(ROOM_NAME, roomName);
        intent.putExtra(ROOM_ID, roomId);
        intent.putExtra(USER_NAME, usernameValue);
    }
}

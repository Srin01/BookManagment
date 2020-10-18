package com.example.bookmanagment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class LoginPage extends AppCompatActivity
{
    private TextInputEditText username;
    private TextInputEditText password;

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
    }

    public void onClickLogin(View view)
    {
        //code to check if the entered username and password are matching the correct credentials or not
    }
}

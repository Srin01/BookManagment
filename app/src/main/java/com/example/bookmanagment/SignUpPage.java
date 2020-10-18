package com.example.bookmanagment;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class SignUpPage extends AppCompatActivity
{
    private TextInputEditText username;
    private TextInputEditText setPassword;
    private TextInputEditText confirmedPassword;
    private TextInputEditText securityAnswer;
    private Spinner securityQuestion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_page);

        bindViews();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.questions));
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        securityQuestion.setAdapter(adapter);
    }

    private void bindViews()
    {
        username = findViewById(R.id.textInputUsername);
        setPassword = findViewById(R.id.textInputSetPassword);
        confirmedPassword = findViewById(R.id.textInputConfirmPassword);
        securityAnswer = findViewById(R.id.textInputSecurityAnswer);
        securityQuestion = findViewById(R.id.security_question);
    }
}

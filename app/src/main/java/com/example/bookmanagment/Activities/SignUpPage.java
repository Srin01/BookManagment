package com.example.bookmanagment.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmanagment.Driver.UserDataBaseDriver;
import com.example.bookmanagment.Expert.UserExpert;
import com.example.bookmanagment.Modal.User;
import com.example.bookmanagment.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpPage extends AppCompatActivity
{
    private TextInputEditText username;
    private TextInputEditText setPassword;
    private TextInputEditText confirmedPassword;
    private TextInputEditText securityAnswer;
    private Spinner securityQuestion;
    private UserExpert userExpert;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_page);

        bindViews();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.questions));
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        securityQuestion.setAdapter(adapter);

        setUpListeners();
    }


    private void bindViews()
    {
        username = findViewById(R.id.textInputUsername);
        setPassword = findViewById(R.id.textInputSetPassword);
        confirmedPassword = findViewById(R.id.textInputConfirmPassword);
        securityAnswer = findViewById(R.id.textInputSecurityAnswer);
        securityQuestion = findViewById(R.id.security_question);
        UserDataBaseDriver userDataBaseDriver = new UserDataBaseDriver(this);
        userExpert = new UserExpert(userDataBaseDriver);
    }

    private void setUpListeners()
    {
        username.addTextChangedListener(new TextWatcher() {

            TextInputLayout usernameLayout = findViewById(R.id.textInputLayout_Username);
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if(charSequence.toString().equals(""))
                {
                    usernameLayout.setError("Please enter a username.");
                    usernameLayout.setErrorEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });

        setPassword.addTextChangedListener(new TextWatcher() {

            TextInputLayout setPasswordLayout = findViewById(R.id.textInputLayout_setPassword);

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if(!calculateStrength(charSequence.toString()).equals("Password is Strong"))
                {
                    setPasswordLayout.setError(calculateStrength(charSequence.toString()));
                    setPasswordLayout.setErrorEnabled(true);
                    return;
                }
                else
                {
                    setPasswordLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private static String calculateStrength(String password)
    {
        //total score of password
        int iPasswordScore = 0;

        if(password.length() < 8)
            return "Password must have at least 8 characters";
        else if(password.length() >= 10)
            iPasswordScore += 2;
        else
            iPasswordScore += 1;

        //if it contains one digit, add 2 to total score
        if( password.matches("(?=.*[0-9]).*") )
            iPasswordScore += 2;

        //if it contains one lower case letter, add 2 to total score
        if( password.matches("(?=.*[a-z]).*") )
            iPasswordScore += 2;

        //if it contains one upper case letter, add 2 to total score
        if( password.matches("(?=.*[A-Z]).*") )
            iPasswordScore += 2;

        //if it contains one special character, add 2 to total score
        if( password.matches("(?=.*[~!@#$%^&*()_-]).*") )
            iPasswordScore += 2;

        if(iPasswordScore < 4)
        {
            return "Password is weak";
        }
        else if(iPasswordScore < 8)
        {
            return "Password Strength is medium";
        }

        return "Password is Strong";
    }

    public void onClickSignUp(View view)
    {
        if(isNull((username.getText()).toString()) || isNull((setPassword.getText()).toString()) || isNull(confirmedPassword.getText().toString()) || isNull(securityAnswer.getText().toString()))
        {
            Toast.makeText(this, "Please enter all the fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(userExpert.ifUserExist(username.getText().toString()))
        {
            Toast.makeText(this, "Username already exists. Please enter a different username.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!setPassword.getText().toString().equals(confirmedPassword.getText().toString()))
        {
            Toast.makeText(this, "Passwords are not matching.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = getIntent();
        int id = intent.getIntExtra("roomId", 1);
        User user = new User(username.getText().toString(), confirmedPassword.getText().toString(), securityQuestion.getSelectedItem().toString() ,securityAnswer.getText().toString(), id);
        userExpert.AddNewUser(user);
        finish();
    }

    private boolean isNull(String s)
    {
        return s.equals("");
    }
}

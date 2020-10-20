package com.example.bookmanagment.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmanagment.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import static com.example.bookmanagment.Activities.MainActivity.OPEN_CAMERA_CODE;

public class AddExtraBookActivity extends AppCompatActivity
{
    private TextView bookName;
    private TextView rowNumber;
    private Button cameraButton;
    private ImageView sampleImage;
    private int rowNumberValue = 0;
    private Bitmap bitmap = null;
    static int pos = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_extra_book);
        bindViews();
        setUpListener();
    }

    private void setUpListener()
    {
        rowNumber.addTextChangedListener(new TextWatcher() {
            TextInputLayout textInputLayout = findViewById(R.id.textInputLayout_RowNumber);
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equals(""))
                {
                    textInputLayout.setError("Please enter a row number.");
                    textInputLayout.setErrorEnabled(true);
                }
                else
                {
                    textInputLayout.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void bindViews()
    {
        bookName = findViewById(R.id.textInputBookName);
        rowNumber = findViewById(R.id.textInputRowNumber);
        cameraButton = findViewById(R.id.cameraButton);
        sampleImage = findViewById(R.id.imageSampleView);
    }

    public void onClickAddExtraBook(View view)
    {
        if(rowNumber.getText() == null || bookName.getText() == null || rowNumber.getText().toString().equals("") || bookName.getText().toString().equals("") || Integer.parseInt(rowNumber.getText().toString()) > 3 || Integer.parseInt(rowNumber.getText().toString()) < 0)
        {
            Toast.makeText(this, "Please enter proper book data", Toast.LENGTH_SHORT).show();
        }
        else {
            String bookNameValue = bookName.getText().toString();
            Log.d(MainActivity.TAG, "onClickAddExtraBook: rowNumber = " + rowNumber.getText().toString());
            rowNumberValue = Integer.parseInt(rowNumber.getText().toString());
            addExtraValue(getIntent(), bookNameValue, rowNumberValue, pos++);
            finish();
        }
    }

    private void addExtraValue(Intent intent, String bookNameValue, int rowNumberValue, int pos)
    {
        intent.putExtra("bookName", bookNameValue);
        intent.putExtra("RowNumber", rowNumberValue);
        intent.putExtra("bookImage", bitmap);
        intent.putExtra("bookPos", pos);
        setResult(RESULT_OK, intent);
    }

    public void onClickOpenCameraIntent(View view)
    {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED)
        {
            cameraButton.setEnabled(false);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, OPEN_CAMERA_CODE);
        }
        startActivity();
    }

    private void startActivity()
    {
        Intent takePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePhoto, OPEN_CAMERA_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == OPEN_CAMERA_CODE)
        {
            if(resultCode == RESULT_OK)
            {
                bitmap = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
                sampleImage.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == OPEN_CAMERA_CODE)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Permission Granted! to take photo", Toast.LENGTH_SHORT).show();
                cameraButton.setEnabled(true);
            }
        }
    }
}
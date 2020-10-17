package com.example.bookmanagment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookmanagment.Adapter.BookAdapter;

import static com.example.bookmanagment.MainActivity.OPEN_CAMERA_CODE;

public class AddExtraBookActivity extends AppCompatActivity
{
    TextView bookName;
    TextView rowNumber;
    Button submitButton;
    Button cameraButton;
    ImageView sampleImage;
    Bitmap bitmap = null;

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
        cameraButton = findViewById(R.id.cameraButton);
        sampleImage = findViewById(R.id.imageSampleView);
    }

    public void onClickAddExtraBook(View view)
    {
        String bookNameValue = bookName.getText().toString();
        int rowNumberValue = Integer.parseInt(rowNumber.getText().toString());
        Intent intent = getIntent();
        intent.putExtra("bookName", bookNameValue);
        intent.putExtra("rowNumber", rowNumberValue);
        intent.putExtra("bookImage", bitmap);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onClickOpenCameraIntent(View view)
    {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED)
        {
            cameraButton.setEnabled(false);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, OPEN_CAMERA_CODE);
        }
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
                bitmap = (Bitmap) data.getExtras().get("data");
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
package com.example.bookmanagment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import static com.example.bookmanagment.MainActivity.OPEN_CAMERA_CODE;

public class AddExtraRoom extends AppCompatActivity
{
    TextView roomName;
    Button submitButton;
    Button cameraButton;
    Bitmap bitmap = null;
    ImageView sampleImage;

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
        sampleImage = findViewById(R.id.imageView_roomTaken);
        cameraButton = findViewById(R.id.takePhoto_button);
        bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.living_room);
    }

    public void onClickAddExtraRoom(View view)
    {
        String roomNameValue = roomName.getText().toString();
        Intent intent = getIntent();
        intent.putExtra("roomName", roomNameValue);
        intent.putExtra("roomImage", bitmap);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onClickOpenCameraIntent(View view) {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED)
        {
            cameraButton.setEnabled(false);
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
                if(data.hasExtra("data"))
                {
                    bitmap = (Bitmap) (data.getExtras()).get("data");
                    sampleImage.setImageBitmap(bitmap);
                    return;
                }
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
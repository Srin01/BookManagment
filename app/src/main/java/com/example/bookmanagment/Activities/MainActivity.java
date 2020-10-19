package com.example.bookmanagment.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.bookmanagment.Adapter.RoomAdapter;
import com.example.bookmanagment.Driver.RoomDatabaseDriver;
import com.example.bookmanagment.Expert.RoomExpert;
import com.example.bookmanagment.Modal.Room;
import com.example.bookmanagment.R;
import com.google.android.material.navigation.NavigationView;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RoomAdapter.OnRoomListerner
{
    public static final String TAG = "myTag";
    public static final String ROOM_NAME = "roomName";
    public static final String ROOM_ID = "roomID";
    public static final int OPEN_CAMERA_CODE = 1234;
    private RoomDatabaseDriver roomDatabaseDriver;
    private RoomAdapter roomAdapter;
    private CarouselView carouselView;
    private RoomExpert roomExpert;
    private Toolbar toolbar;
    private ImageListener imageListener;
    private SwipeRefreshLayout swipeRefreshLayout;

    private int[] sampleImages = {R.drawable.shelf_messy, R.drawable.organised_shelf, R.drawable.shelf3, R.drawable.online_reading};


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, OPEN_CAMERA_CODE);
        setValues();
        setUpImageLisertener();
        bindViews();
        setUpToolbar();
        setUpNavigationDrawerIcon();
        setUpListeners();
        onReferesh();
    }

    private void onReferesh()
    {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                roomAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void setUpImageLisertener()
    {
        imageListener = new ImageListener()
        {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(sampleImages[position]);
            }
        };
    }

    private void setValues()
    {
        roomDatabaseDriver = new RoomDatabaseDriver(this);
        roomExpert = new RoomExpert(roomDatabaseDriver);
        roomAdapter = new RoomAdapter(this, roomExpert,this);
    }

    private void bindViews()
    {
        RecyclerView roomsViewRecycler = findViewById(R.id.shelves_recyclerView);
        roomsViewRecycler.setLayoutManager(new LinearLayoutManager(this));
        roomsViewRecycler.setAdapter(roomAdapter);
        carouselView = findViewById(R.id.carouselView);
        swipeRefreshLayout = findViewById(R.id.swipeRefereshLayout);
        setCarouselViews();
        printDetails();
    }

    private void setCarouselViews()
    {
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
    }

    private void setUpListeners()
    {
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.item1:
                        Intent searchIntent = new Intent(MainActivity.this, SearchActivity.class);
                        startActivity(searchIntent);
                        return false;
                    case R.id.item3:
                        Intent instructionIntent = new Intent(MainActivity.this, InstructionsActivity.class);
                        startActivity(instructionIntent);
                        return true;
                }
                return false;
            }
        });
    }

    private void setUpNavigationDrawerIcon()
    {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.closed);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
    }

    private void setUpToolbar()
    {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    public void printDetails()
    {
        ArrayList<Room> Rooms = roomDatabaseDriver.getAllRoomList();

        Log.d(TAG, "printDetails: Your database has " + Rooms.size() + " rooms ");
        for (int i = 0; i < Rooms.size(); i++)
        {
            Log.d(TAG,Rooms.get(i).getId()  + " " + Rooms.get(i).getRoomName());
        }
    }

    public void OnclickAddExtraRoom(View view)
    {
        Intent addRoomIntent = new Intent(this, AddExtraRoomActivity.class);
        startActivityForResult(addRoomIntent, 1);
        roomAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK)
            {
                assert data != null;
                String roomName = data.getStringExtra("roomName");
                Room room = new Room();
                room.setRoomName(roomName);
                roomExpert.addNewRoom(room);
                int roomId = roomExpert.getSpecifcId(roomName);
                Log.d(TAG, "onActivityResult: roomId is " + roomId);
                roomAdapter.notifyDataSetChanged();
                startSignUpActivity(roomId);
            }
            printDetails();
        }
    }

    @Override
    public void onRoomClick(int position)
    {
        if(position != 0) {
            Log.d(TAG, "onRoomClick: " + roomExpert.getRoomName(position) + " Login to open this room ");
            startLoginActivity(position);
        }
        else {
            Log.d(TAG, "onRoomClick: opened Living room Activity without login ");
            startLivingRoomActivity(position);
        }
    }

    private void startLoginActivity(int position)
    {
        Intent intent = new Intent(this, LoginPage.class);
        intent.putExtra(ROOM_NAME, roomExpert.getRoomName(position));
        intent.putExtra(ROOM_ID, roomExpert.getRoomID(position));
        startActivity(intent);
    }

    private void startLivingRoomActivity(int position)
    {
        Intent intent = new Intent(this, ShelfBookActivity.class);
        intent.putExtra(ROOM_NAME,roomExpert.getRoomName(position));
        intent.putExtra(ROOM_ID,roomExpert.getRoomID(position));
        startActivity(intent);
    }

    private void startSignUpActivity(int roomId)
    {
        Intent intent1 = new Intent(this, SignUpPage.class);
        intent1.putExtra("roomId", roomId);
        startActivity(intent1);
    }
}
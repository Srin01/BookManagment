package com.example.bookmanagment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bookmanagment.Adapter.BookAdapter;
import com.example.bookmanagment.Adapter.RoomAdapter;
import com.example.bookmanagment.Driver.BookDatabaseDriver;
import com.example.bookmanagment.Driver.RoomDatabaseDriver;
import com.example.bookmanagment.Expert.BookExpert;
import com.example.bookmanagment.Expert.RoomExpert;
import com.example.bookmanagment.Modal.Book;
import com.example.bookmanagment.Modal.Room;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    BookDatabaseDriver bookdatabaseDriver;
    RoomDatabaseDriver roomDatabaseDriver;
    RoomAdapter roomAdapter;
    RoomExpert roomExpert;
    RecyclerView roomsViewRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roomDatabaseDriver = new RoomDatabaseDriver(this);
        roomExpert = new RoomExpert(roomDatabaseDriver);
        roomAdapter = new RoomAdapter(this, roomExpert);
        bindViews();
    }

    private void bindViews()
    {
        roomsViewRecycler = findViewById(R.id.shelves_recyclerView);
        roomsViewRecycler.setLayoutManager(new LinearLayoutManager(this));
        roomsViewRecycler.setAdapter(roomAdapter);
        printDetails();
    }


    public void printDetails()
    {
        ArrayList<Room> Rooms = roomDatabaseDriver.getAllRoomList();
        
        System.out.println("Your database has " + Rooms.size() + " rooms ");
        for (int i = 0; i < Rooms.size(); i++)
        {
            System.out.println(Rooms.get(i).getId() + " " +Rooms.get(i).getShelfNumber() + " " + Rooms.get(i).getRoomName());
        }
    }

    public void OnclickAddExtraRoom(View view)
    {
        Intent addRoomIntent = new Intent(this,AddExtraRoom.class);
        startActivityForResult(addRoomIntent, 1);
        roomAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1)
        {
            if(resultCode == RESULT_OK)
            {
                String roomName = data.getStringExtra("roomName");
                int numberOfShelves = data.getIntExtra("numberOfShelves", 1);
                Room room = new Room();
                room.setShelfNumber(numberOfShelves);
                room.setRoomName(roomName);
                roomExpert.addNewRoom(room);
                roomAdapter.notifyDataSetChanged();
            }
        }
        printDetails();
    }
}
package com.example.bookmanagment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmanagment.Driver.BookDatabaseDriver;
import com.example.bookmanagment.Expert.RoomExpert;
import com.example.bookmanagment.R;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder>
{
    private RoomExpert roomExpert;
    private Context context;

    public RoomAdapter(Context context, RoomExpert roomExpert)
    {
        this.context = context;
        this.roomExpert = roomExpert;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_room, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position)
    {
        View view = holder.itemView;
        TextView roomId = view.findViewById(R.id.room_id);
        TextView roomName = view.findViewById(R.id.room_name);
        TextView numberOfShelves = view.findViewById(R.id.number_of_shelves);

        roomId.setText(roomExpert.getRoomID(position) + "");
        roomName.setText(roomExpert.getRoomName(position));
        numberOfShelves.setText(roomExpert.getShelfNumber(position) + "");
    }

    @Override
    public int getItemCount() {
        return roomExpert.getTotalRooms();
    }

    public static class RoomViewHolder extends RecyclerView.ViewHolder
    {
        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

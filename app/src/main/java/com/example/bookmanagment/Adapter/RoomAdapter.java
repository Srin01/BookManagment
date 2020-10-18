package com.example.bookmanagment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmanagment.Driver.BookDatabaseDriver;
import com.example.bookmanagment.Expert.RoomExpert;
import com.example.bookmanagment.R;
import com.example.bookmanagment.ShelfBookActivity;

import static com.example.bookmanagment.BookViewerActivity.TAG;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder>
{
    private RoomExpert roomExpert;
    private Context context;
    private OnRoomListerner onRoomListerner;

    public RoomAdapter(Context context, RoomExpert roomExpert, OnRoomListerner onRoomListerner)
    {
        this.context = context;
        this.roomExpert = roomExpert;
        this.onRoomListerner = onRoomListerner;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_shelf, parent, false);
        return new RoomViewHolder(view, onRoomListerner);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, final int position)
    {
        View view = holder.itemView;
        final TextView roomName = view.findViewById(R.id.room_name1);
        ImageView roomImage = view.findViewById(R.id.room_image1);
        roomName.setText(roomExpert.getRoomName(position));
    }

    @Override
    public int getItemCount() {
        return roomExpert.getTotalRooms();
    }

    public static class RoomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        View view;
        OnRoomListerner onRoomListerner;
        public RoomViewHolder(@NonNull View itemView, OnRoomListerner onRoomListerner)
        {
            super(itemView);
            this.view = itemView;
            this.onRoomListerner = onRoomListerner;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onRoomListerner.onRoomClick(getAdapterPosition());
        }
    }

    public interface OnRoomListerner
    {
        void onRoomClick(int position);
    }
}

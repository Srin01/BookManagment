package com.example.bookmanagment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmanagment.Expert.BooksForRoomExpert;
import com.example.bookmanagment.R;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyBookViewHolder>
{
    private Context context;
    private BooksForRoomExpert bookExpert;
    TextView bookNumber ;
    TextView Summary ;
    TextView roomId ;
    TextView shelfId ;
    TextView rowNumber;
    TextView positionInRow ;

    public BookAdapter(Context context, BooksForRoomExpert bookExpert)
    {
        this.context = context;
        this.bookExpert = bookExpert;
    }


    @NonNull
    @Override
    public MyBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_book, parent, false);
        return new MyBookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyBookViewHolder holder, int position)
    {
        View view = holder.view;
        bindViews(view);

        bookNumber.setText(bookExpert.getBookId(position) + "");
        Summary.setText(bookExpert.getSummary(position));
        roomId.setText(bookExpert.getBookRoomId(position) + "");
        shelfId.setText(bookExpert.getBookShelfNumber(position) + "");
        rowNumber.setText(bookExpert.getRowNumber(position) + "");
        positionInRow.setText(bookExpert.getBookPosition(position) + "");
    }

    private void bindViews(View view)
    {
        bookNumber = view.findViewById(R.id.book_number);
        Summary = view.findViewById(R.id.summary);
        roomId = view.findViewById(R.id.room_number);
        shelfId = view.findViewById(R.id.shelf_number);
        rowNumber = view.findViewById(R.id.row_number);
        positionInRow = view.findViewById(R.id.position_in_row);
    }

    @Override
    public int getItemCount()
    {
        return bookExpert.getTotalBooks();
    }

    public static class MyBookViewHolder extends RecyclerView.ViewHolder
    {
        View view;
        public MyBookViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
        }
    }
}

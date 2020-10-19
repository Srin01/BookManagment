package com.example.bookmanagment.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmanagment.Expert.BookExpertForRoomAndRow;
import com.example.bookmanagment.R;

import static com.example.bookmanagment.Activities.MainActivity.TAG;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyBookViewHolder>
{
    private Context context;
    private BookExpertForRoomAndRow bookExpertForRoomAndRow;
    TextView bookName;
    ImageView imageView;
    OnBookListerner onBookListerner;

    private void bindViews(View view)
    {
        bookName = view.findViewById(R.id.book_name);
        imageView = view.findViewById(R.id.imageView_book);
    }

    public BookAdapter(Context context, BookExpertForRoomAndRow bookExpertForRoomAndRow, OnBookListerner onBookListerner)
    {
        this.context = context;
        this.bookExpertForRoomAndRow = bookExpertForRoomAndRow;
        this.onBookListerner = onBookListerner;
    }

    @Override
    public int getItemCount()
    {
        Log.d(TAG, "getItemCount: there are " + bookExpertForRoomAndRow.getTotalBooks() + " no of books for adapter view");
        return bookExpertForRoomAndRow.getTotalBooks();
    }

    public interface OnBookListerner
    {
        void onBookClick1(int position);
    }

    @Override
    public void onBindViewHolder(@NonNull MyBookViewHolder holder, int position)
    {
        View view = holder.view;
        bindViews(view);
        Log.d(TAG, "onBindViewHolder: setting text as " + bookExpertForRoomAndRow.getBookName(position));
        bookName.setText(bookExpertForRoomAndRow.getBookName(position));
        imageView.setImageBitmap(bookExpertForRoomAndRow.getBitmapImage(position));
    }

    @NonNull
    @Override
    public MyBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_book, parent, false);
        return new MyBookViewHolder(view, onBookListerner);
    }

    public static class MyBookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        View view;
        OnBookListerner onBookListerner;
        public MyBookViewHolder(@NonNull View itemView, OnBookListerner onBookListerner) {
            super(itemView);
            view = itemView;
            this.onBookListerner = onBookListerner;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onBookListerner.onBookClick1(getAdapterPosition());
        }
    }
}

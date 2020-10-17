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

import com.example.bookmanagment.Expert.BookExpert3;
import com.example.bookmanagment.R;

import static com.example.bookmanagment.MainActivity.TAG;

public class BookAdapter3 extends RecyclerView.Adapter<BookAdapter3.MyBookViewHolder>
{
    private Context context;
    BookExpert3 bookExpert3;
    TextView bookName;
    ImageView imageView;
    OnBookListerner3 onBookListerner;

    public BookAdapter3(Context context, BookExpert3 bookExpert3, OnBookListerner3 onBookListerner)
    {
        this.context = context;
        this.bookExpert3 = bookExpert3;
        this.onBookListerner = onBookListerner;
    }


    @NonNull
    @Override
    public MyBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_book, parent, false);
        return new MyBookViewHolder(view, onBookListerner);
    }

    @Override
    public void onBindViewHolder(@NonNull MyBookViewHolder holder, int position)
    {
        View view = holder.view;
        bindViews(view);
        Log.d(TAG, "onBindViewHolder: setting text as " + bookExpert3.getBookName(position));
        bookName.setText(bookExpert3.getBookName(position));
        imageView.setImageBitmap(bookExpert3.getBitmapImage(position));
    }

    private void bindViews(View view)
    {
        bookName = view.findViewById(R.id.book_name);
        imageView = view.findViewById(R.id.imageView_book);
    }

    @Override
    public int getItemCount()
    {
        Log.d(TAG, "getItemCount: there are " + bookExpert3.getTotalBooks() + " no of books for adapter view");
        return bookExpert3.getTotalBooks();
    }

    public static class MyBookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        View view;
        OnBookListerner3 onBookListerner;
        public MyBookViewHolder(@NonNull View itemView, OnBookListerner3 onBookListerner) {
            super(itemView);
            view = itemView;
            this.onBookListerner = onBookListerner;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onBookListerner.onBookClick3(getAdapterPosition());
        }
    }

    public interface OnBookListerner3
    {
        void onBookClick3(int position);
    }
}




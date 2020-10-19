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

import com.example.bookmanagment.Expert.BookExpert2;
import com.example.bookmanagment.R;

import static com.example.bookmanagment.Activities.MainActivity.TAG;

public class BookAdapter2 extends RecyclerView.Adapter<BookAdapter2.MyBookViewHolder>
{
    private Context context;
    BookExpert2 bookExpert2;
    TextView bookName;
    ImageView imageView;
    OnBookListerner2 onBookListerner;

    public BookAdapter2(Context context, BookExpert2 bookExpert2, OnBookListerner2 onBookListerner)
    {
        this.context = context;
        this.bookExpert2 = bookExpert2;
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
        Log.d(TAG, "onBindViewHolder: setting text as " + bookExpert2.getBookName(position));
        bookName.setText(bookExpert2.getBookName(position));
        imageView.setImageBitmap(bookExpert2.getBitmapImage(position));
    }

    private void bindViews(View view)
    {
        bookName = view.findViewById(R.id.book_name);
        imageView = view.findViewById(R.id.imageView_book);
    }

    @Override
    public int getItemCount()
    {
        Log.d(TAG, "getItemCount: there are " + bookExpert2.getTotalBooks() + " no of books for adapter view");
        return bookExpert2.getTotalBooks();
    }

    public static class MyBookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        View view;
        OnBookListerner2 onBookListerner;
        public MyBookViewHolder(@NonNull View itemView, OnBookListerner2 onBookListerner) {
            super(itemView);
            view = itemView;
            this.onBookListerner = onBookListerner;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onBookListerner.onBookClick2(getAdapterPosition());
        }
    }

    public interface OnBookListerner2
    {
        void onBookClick2(int position);
    }
}


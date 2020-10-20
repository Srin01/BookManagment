package com.example.bookmanagment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmanagment.Modal.Book;
import com.example.bookmanagment.R;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> implements Filterable
{
    private Context context;
    private ArrayList<Book> books;
    private OnBookSearchListener onBookSearchListener;

    public SearchAdapter(Context context, ArrayList<Book> books, OnBookSearchListener onBookSearchListener)
    {
        this.context = context;
        this.books = books;
        this.onBookSearchListener = onBookSearchListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.search_list_item, parent, false);
        return new ViewHolder(view, onBookSearchListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        View view = holder.view;
        TextView bookName = view.findViewById(R.id.book_name);
        bookName.setText(books.get(position).getBookName());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        View view;
        OnBookSearchListener onBookListener;
        public ViewHolder(@NonNull View itemView, OnBookSearchListener onBookSearchListener)
        {
            super(itemView);
            this.view = itemView;
            this.onBookListener = onBookSearchListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onBookListener.onBookClick(getAdapterPosition());
        }
    }

    public interface OnBookSearchListener
    {
        void onBookClick(int position);
    }
}

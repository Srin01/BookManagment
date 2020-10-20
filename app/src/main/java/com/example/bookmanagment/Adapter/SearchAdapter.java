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
    private OnBookSearchListerner onBookSearchListerner;

    public SearchAdapter(Context context, ArrayList<Book> books, OnBookSearchListerner onBookSearchListerner)
    {
        this.context = context;
        this.books = books;
        this.onBookSearchListerner = onBookSearchListerner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.search_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, onBookSearchListerner);
        return viewHolder;
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
        OnBookSearchListerner onBookListerner;
        public ViewHolder(@NonNull View itemView, OnBookSearchListerner onBookSearchListerner)
        {
            super(itemView);
            this.view = itemView;
            this.onBookListerner = onBookSearchListerner;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onBookListerner.onBookClick(getAdapterPosition());
        }
    }

    public interface OnBookSearchListerner
    {
        void onBookClick(int position);
    }
}

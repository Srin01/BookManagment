package com.example.bookmanagment.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmanagment.Expert.BooksForRoomExpert;
import com.example.bookmanagment.R;

import static com.example.bookmanagment.MainActivity.TAG;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyBookViewHolder>
{
    private Context context;
    private BooksForRoomExpert booksForRoomExpert;
    TextView bookNumber ;
    TextView Summary ;
    TextView roomId ;
    TextView shelfId ;
    TextView rowNumber;
    TextView positionInRow ;
    TextView bookName;
    OnBookListerner onBookListerner;

    public BookAdapter(Context context, BooksForRoomExpert booksForRoomExpert, OnBookListerner onBookListerner)
    {
        this.context = context;
        this.booksForRoomExpert = booksForRoomExpert;
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
        Log.d(TAG, "onBindViewHolder: setting text as " + booksForRoomExpert.getBookName(position));
        bookName.setText(booksForRoomExpert.getBookName(position));
        bookNumber.setText(String.valueOf(booksForRoomExpert.getBookId(position)));
        Summary.setText(booksForRoomExpert.getSummary(position));
        roomId.setText(String.valueOf(booksForRoomExpert.getBookRoomId(position)));
        shelfId.setText(String.valueOf(booksForRoomExpert.getBookShelfNumber(position)));
        rowNumber.setText(String.valueOf(booksForRoomExpert.getRowNumber(position)));
        positionInRow.setText(String.valueOf(booksForRoomExpert.getBookPosition(position)));
    }

    private void bindViews(View view)
    {
        bookNumber = view.findViewById(R.id.book_number);
        bookName = view.findViewById(R.id.book_name);
        Summary = view.findViewById(R.id.summary);
        roomId = view.findViewById(R.id.room_number);
        shelfId = view.findViewById(R.id.shelf_number);
        rowNumber = view.findViewById(R.id.row_number);
        positionInRow = view.findViewById(R.id.position_in_row);
    }

    @Override
    public int getItemCount()
    {
        Log.d(TAG, "getItemCount: there are " + booksForRoomExpert.getTotalBooks() + " no of books for adapter view");
        return booksForRoomExpert.getTotalBooks();
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
            onBookListerner.onBookClick(getAdapterPosition());
        }
    }

    public interface OnBookListerner
    {
        void onBookClick(int position);
    }
}

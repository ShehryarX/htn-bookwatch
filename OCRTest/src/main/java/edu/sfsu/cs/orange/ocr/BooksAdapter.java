package edu.sfsu.cs.orange.ocr;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import edu.sfsu.cs.orange.ocr.Book;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookHolder> {

    public class BookHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final Activity activity;
        private final CardView bookCard;
        private Book book;
        private TextView bookTitleText;
        private TextView authorText;

        public BookHolder(Activity activity, View bookCard) {
            super(bookCard);

            this.activity = activity;
            this.bookCard = (CardView) bookCard;
            bookTitleText = (TextView) this.bookCard.findViewById(R.id.bookTitle);
            authorText = (TextView) this.bookCard.findViewById(R.id.authorName);

            this.bookCard.setOnClickListener(this);
        }

        public void bindBook(Book book) {
            this.book = book;
            bookTitleText.setText(book.bookName);
            authorText.setText(book.authorName);
        }

        @Override
        public void onClick(View view) {
            goToConnectedArticle();
        }

        private void goToConnectedArticle() {
            Intent goToArticleIntent = new Intent(activity, Book.class);
            goToArticleIntent.putExtra("book", book);
            activity.startActivity(goToArticleIntent);
        }
    }

    private final ArrayList<Book> books;
    private final Activity activity;

    public BooksAdapter(Activity activity, ArrayList<Book> books) {
        this.activity = activity;
        this.books = books;
    }

    @Override
    public BookHolder onCreateViewHolder(ViewGroup parent, int pos) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_card, parent, false);
        return new BookHolder(activity, view);
    }

    @Override
    public void onBindViewHolder(BookHolder holder, int pos) {
        holder.bindBook(books.get(pos));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

}

package edu.sfsu.cs.orange.ocr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by mark on 18/09/16.
 */
public class BookDetailsActivity extends AppCompatActivity {
    private Book book;
    private TextView titleText;
    private TextView authorText;

    @Override
    protected void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_book_details);

        titleText = ((TextView) findViewById(R.id.titleText));
        authorText = ((TextView) findViewById(R.id.authorText));

        book = ((Book) getIntent().getSerializableExtra("book"));
        displayBookDetails();
    }

    private void displayBookDetails() {
        titleText.setText(book.bookName);
        authorText.setText(book.authorName);
    }

}

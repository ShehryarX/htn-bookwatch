package edu.sfsu.cs.orange.ocr;

import java.io.Serializable;

public class Book implements Serializable {

    public String bookName;
    public String authorName;
    public int publicationDay;
    public int publicationMonth;
    public int publicationYear;
    public double ratingAverage;

    public Book(String bookName, String authorName, int publicationDay, int publicationMonth, int publicationYear, double ratingAverage) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.publicationDay = publicationDay;
        this.publicationMonth = publicationMonth;
        this.publicationYear = publicationYear;
        this.ratingAverage = ratingAverage;
    }

    public static Book createBook(String bookName, String authorName, int publicationDay, int publicationMonth, int publicationYear, double ratingAverage) {
        return new Book(bookName, authorName, publicationDay, publicationMonth, publicationYear, ratingAverage);
    }

}

package ru.itvitality.sbrf.cu.library.dto;

import ru.itvitality.sbrf.cu.library.entities.Book;

import java.util.Objects;

public class BookDescription {
    private String isbn;
    private String author;
    private String name;

    public String getIsbn() {
        return isbn;
    }

    public BookDescription setIsbn( String isbn ) {
        this.isbn = isbn;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public BookDescription setAuthor( String author ) {
        this.author = author;
        return this;
    }

    public String getName() {
        return name;
    }

    public BookDescription setName( String name ) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        BookDescription that = (BookDescription) o;
        return isbn.equals( that.isbn );
    }

    @Override
    public int hashCode() {
        return Objects.hash( isbn );
    }

    public static BookDescription build( Book book ) {
        BookDescription bookDescription = new BookDescription();
        bookDescription.setIsbn( book.getIsbn() );
        bookDescription.setAuthor( book.getAuthor() );
        bookDescription.setName( book.getName() );
        return bookDescription;
    }
}

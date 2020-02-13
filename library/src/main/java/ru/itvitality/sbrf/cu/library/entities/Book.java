package ru.itvitality.sbrf.cu.library.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private Integer bookId;

    private String bookName;
    private String author;
    public Integer getBookNumber() {return bookId;}
    public void setBookName(String bookName) {this.bookName = bookName;}
    public String getBookName(){return this.bookName;}
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getAuthor(){return this.author;}

    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Book book = (Book)o;
        return Objects.equals( bookId, book.bookId ) &&
                Objects.equals( bookName, book.bookName ) &&
                  Objects.equals(author, book.author);
    }
    @Override
    public int hashCode() {
        return Objects.hash( bookId, bookName, author );
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder( "Book" );
        sb.append( "bookId=" ).append( bookId );
        sb.append( ", bookName='" ).append( bookName );
        sb.append( ", author='" ).append( author ).append( '\'' );
        sb.append( '}' );
        return sb.toString();
    }
}

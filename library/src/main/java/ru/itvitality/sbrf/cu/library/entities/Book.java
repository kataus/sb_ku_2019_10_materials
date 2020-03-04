package ru.itvitality.sbrf.cu.library.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer holder;
    private String title;
    private String author;
    Book(){}
    Book(String title, String author){
        this.holder=0;
        this.author=author;
        this.title=title;
    }
    public Integer getBookId() {return id;}
    public String getBookName(){return this.title;}
    public String getAuthor(){return this.author;}
    public Integer getHolder(){return this.holder;}
    public void setHolder(Client client){
        this.holder=client.getId();
    }

    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Book book = (Book)o;
        return Objects.equals( id, book.id ) &&
                Objects.equals( title, book.title ) &&
                  Objects.equals(author, book.author);
    }
    @Override
    public int hashCode() {
        return Objects.hash( id, title, author );
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder( "Book" );
        sb.append( "bookId=" ).append( id );
        sb.append( ", bookName='" ).append( title);
        sb.append( ", author='" ).append( author ).append( '\'' );
        sb.append( '}' );
        return sb.toString();
    }
}

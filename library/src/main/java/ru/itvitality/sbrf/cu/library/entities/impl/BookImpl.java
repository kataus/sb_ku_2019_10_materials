package ru.itvitality.sbrf.cu.library.entities.impl;

import com.sun.istack.NotNull;
import ru.itvitality.sbrf.cu.library.entities.Book;
import ru.itvitality.sbrf.cu.library.entities.Human;
import ru.itvitality.sbrf.cu.library.entities.LibrarianInsertable;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Book")
public class BookImpl implements Book, LibrarianInsertable {
    @Id
    @GeneratedValue(generator = "book")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "holder_id", nullable = false)
    private HumanImpl holder;
    private String title;
    private String author;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "added_by_librarian_id", nullable = false)
    private LibrarianImpl librarian;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Shelf shelf;
    BookImpl(){}
    public BookImpl(String title, String author){
        this.author=author;
        this.title=title;
        this.shelf=Shelf.defineBookShelf(author);
    }
    public Integer getBookId() {return id;}
    public String getBookName(){return this.title;}
    public String getAuthor(){return this.author;}
    public Human getHolder(){return this.holder;}

    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        BookImpl bookImpl = (BookImpl)o;
        return Objects.equals( id, bookImpl.id ) &&
                Objects.equals( title, bookImpl.title ) &&
                  Objects.equals(author, bookImpl.author);
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

    @Override
    public void setLibrarian(LibrarianImpl librarian) { this.librarian =  librarian;   }

    @Override
    public void setHolder(Human human){ this.holder = (HumanImpl) human;    }

    @Override
    public LibrarianImpl getLibrarian() {
        return null;
    }
}

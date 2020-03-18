package ru.itvitality.sbrf.cu.library.entities.impl;

import ru.itvitality.sbrf.cu.library.entities.Client;
import ru.itvitality.sbrf.cu.library.entities.LibrarianInsertable;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Client")
public class ClientImpl extends HumanImpl implements Client, LibrarianInsertable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "added_by_librarian_id", nullable = false)
    private LibrarianImpl librarian;

    public ClientImpl(String name) {
        super(name);
    }

    @Override
    public void setLibrarian(LibrarianImpl librarian) {
        this.librarian = librarian;
    }

    @Override
    public LibrarianImpl getLibrarian() {
        return librarian;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        ClientImpl clientImpl = (ClientImpl) o;
        return Objects.equals( id, clientImpl.id ) &&
                Objects.equals( name, clientImpl.name );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, name );
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder( "Client{" );
        sb.append( "id=" ).append( id );
        sb.append( ", name='" ).append( name ).append( '\'' );
        sb.append( '}' );
        return sb.toString();
    }
}

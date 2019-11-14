package ru.itvitality.sbrf.cu.library.entities;

import java.util.Objects;

public class Client {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public Client setId( Integer id ) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Client setName( String name ) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Client client = (Client) o;
        return Objects.equals( id, client.id ) &&
                Objects.equals( name, client.name );
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

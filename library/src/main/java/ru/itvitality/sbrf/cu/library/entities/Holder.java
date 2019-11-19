package ru.itvitality.sbrf.cu.library.entities;

import javax.persistence.*;

@Entity
@Table(name = "holder")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn()
public class Holder {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    public Long getId() {
        return id;
    }

    public Holder setId( Long id ) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Holder setName( String name ) {
        this.name = name;
        return this;
    }
}

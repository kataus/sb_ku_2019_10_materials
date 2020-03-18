package ru.itvitality.sbrf.cu.library.entities.impl;

import com.sun.istack.NotNull;
import ru.itvitality.sbrf.cu.library.entities.Human;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
@Table(name = "Human")
@Inheritance(strategy = InheritanceType.JOINED)
abstract public class HumanImpl implements Human {
    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    protected Integer id;

    @NotNull
    protected String name;

    protected HumanImpl(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }
}

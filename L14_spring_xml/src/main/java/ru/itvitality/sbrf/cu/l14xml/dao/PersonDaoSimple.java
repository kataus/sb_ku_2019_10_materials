package ru.itvitality.sbrf.cu.l14xml.dao;

import ru.itvitality.sbrf.cu.l14xml.domain.Person;

public class PersonDaoSimple implements PersonDao {

    public Person findByName( String name ) {
        return new Person( name, 18 );
    }
}

package ru.itvitality.sbrf.cu.l14xml.dao;

import ru.itvitality.sbrf.cu.l14xml.domain.Person;

public interface PersonDao {

    Person findByName( String name );
}

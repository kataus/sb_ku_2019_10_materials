package ru.itvitality.sbrf.cu.sfc.dao;

import ru.itvitality.sbrf.cu.sfc.domain.Person;

public class PersonDaoSimple implements PersonDao {

    public Person findByName(String name) {
        return new Person(name, 18);
    }
}

package ru.itvitality.sbrf.cu.l14.aop.dao;

import org.springframework.stereotype.Repository;
import ru.itvitality.sbrf.cu.l14.aop.domain.Person;

@Repository
public class PersonDaoSimple implements PersonDao {

    public Person findByName(String name) {
        return new Person(name, 18);
    }
}

package ru.itvitality.sbrf.cu.sfc.dao;

import org.springframework.stereotype.Service;
import ru.itvitality.sbrf.cu.sfc.domain.Person;

@Service
public class PersonDaoSimple implements PersonDao {

    public Person findByName(String name) {
        return new Person(name, 18);
    }
}

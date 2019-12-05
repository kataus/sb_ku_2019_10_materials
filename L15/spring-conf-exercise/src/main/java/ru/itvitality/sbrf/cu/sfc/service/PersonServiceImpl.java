package ru.itvitality.sbrf.cu.sfc.service;

import ru.itvitality.sbrf.cu.sfc.dao.PersonDao;
import ru.itvitality.sbrf.cu.sfc.domain.Person;

public class PersonServiceImpl implements PersonService {

    private final PersonDao dao;

    public PersonServiceImpl(PersonDao dao) {
        this.dao = dao;
    }

    public Person getByName( String name) {
        return dao.findByName(name);
    }
}

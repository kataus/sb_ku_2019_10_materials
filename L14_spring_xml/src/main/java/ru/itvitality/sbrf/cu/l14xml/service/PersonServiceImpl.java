package ru.itvitality.sbrf.cu.l14xml.service;

import ru.itvitality.sbrf.cu.l14xml.dao.PersonDao;
import ru.itvitality.sbrf.cu.l14xml.domain.Person;


public class PersonServiceImpl implements PersonService {

    private PersonDao dao;

    public PersonServiceImpl( PersonDao dao) {
        this.dao = dao;
    }

    public Person getByName( String name) {
        return dao.findByName(name);
    }
}

package ru.itvitality.sbrf.cu.sfc.service;

import org.springframework.stereotype.Service;
import ru.itvitality.sbrf.cu.sfc.dao.PersonDao;
import ru.itvitality.sbrf.cu.sfc.domain.Person;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonDao dao;

    public PersonServiceImpl(PersonDao dao) {
        this.dao = dao;
    }

    public Person getByName( String name) {
        return dao.findByName(name);
    }
}

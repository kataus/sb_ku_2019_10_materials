package ru.itvitality.sbrf.cu.l14.aop.service;

import org.springframework.stereotype.Service;
import ru.itvitality.sbrf.cu.l14.aop.dao.PersonDao;
import ru.itvitality.sbrf.cu.l14.aop.domain.Person;

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

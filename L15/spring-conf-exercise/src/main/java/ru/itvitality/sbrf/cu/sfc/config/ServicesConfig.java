package ru.itvitality.sbrf.cu.sfc.config;

import ru.itvitality.sbrf.cu.sfc.dao.PersonDao;
import ru.itvitality.sbrf.cu.sfc.service.PersonService;
import ru.itvitality.sbrf.cu.sfc.service.PersonServiceImpl;

public class ServicesConfig {

    public PersonService personService( PersonDao dao) {
        return new PersonServiceImpl(dao);
    }
}

package ru.itvitality.sbrf.cu.sfc.config;

import ru.itvitality.sbrf.cu.sfc.dao.PersonDao;
import ru.itvitality.sbrf.cu.sfc.dao.PersonDaoSimple;

public class DaoConfig {

    public PersonDao personDao() {
        return new PersonDaoSimple();
    }
}

package ru.itvitality.sbrf.cu.sfc.dao;

import ru.itvitality.sbrf.cu.sfc.domain.Person;

public interface PersonDao {

    Person findByName(String name);
}

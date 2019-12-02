package ru.itvitality.sbrf.cu.l14.aop.dao;

import ru.itvitality.sbrf.cu.l14.aop.domain.Person;

public interface PersonDao {

    Person findByName(String name);
}
